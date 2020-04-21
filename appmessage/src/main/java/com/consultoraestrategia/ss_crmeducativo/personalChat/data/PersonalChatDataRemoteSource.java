package com.consultoraestrategia.ss_crmeducativo.personalChat.data;



import androidx.annotation.NonNull;
import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.ReferenceFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

public class PersonalChatDataRemoteSource implements PersonalChatDataSource
{
    //typeGroup is academic=1, course=2, team=3
    //typeChat is group=1, personal=0
    //typePersonGroup o typePerson is parents=0 ,teacher=2, stuends=1

    String TAG= PersonalChatDataRemoteSource.class.getSimpleName();
    static final int LIMIT_LIST=30;
    static final String TEACHER="DO",STUDENTS="AL", PARENTS="PA" ;
    CursoDao cursoDao;
    ParametrosDisenioDao parametrosDisenioDao;

    public PersonalChatDataRemoteSource(CursoDao cursoDao,ParametrosDisenioDao parametrosDisenioDao) {
        this.cursoDao = cursoDao;
        this.parametrosDisenioDao=parametrosDisenioDao;
    }

    CollectionReference CollectionReferenceChat= ReferenceFirestore.getInstanceChat();
    CollectionReference collectionReferenceMessage= null;
    CollectionReference collectionReferencePerson= null;
    CollectionReference collectionReferenceSeen= null;

    @Override
    public void validatePersonExistence(final int idperson, final boolean internet,final SuccessCallback successCallbackPerson)
    {
        Log.d(TAG, "validatePersonExistence"  + " idperson "+ idperson);

        Query query = collectionReferencePerson.whereEqualTo("idperson", idperson);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d(TAG, " validatePersonExistence "+task.getResult().isEmpty());

                    if(task.getResult().isEmpty()){
                        if(internet)createPerson(idperson, successCallbackPerson);
                        else successCallbackPerson.onLoad(false);
                    }
                    else successCallbackPerson.onLoad(true);

                }
            }
        });

    }

    public void createPerson(final int idPerson, final SuccessCallback successCallbackPerson)
    {
        Log.d(TAG, "createPerson " );
        Map<String, Object> personnew = new HashMap<>();

        Persona person= SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(idPerson)).querySingle();
        if(person!=null)
        {
            personnew.put("name", person.getNombres());
            personnew.put("urlpicture",person.getUrlPicture());
            personnew.put("dateconexion",null);
            personnew.put("active",false);
        }

        personnew.put("idperson", idPerson);
        collectionReferencePerson.add(personnew).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "created "+ task.isSuccessful());
                if(task.isSuccessful())successCallbackPerson.onLoad(true);
                else
                {
                    Log.d(TAG, "Error creating person: ", task.getException());
                    successCallbackPerson.onLoad(false);
                }

            }
        });

    }
   public void getchatexistence(final int idsender,final  int idreceiver,final Callback<String>callback){
      CollectionReferenceChat.whereEqualTo("idreceiver", idreceiver).whereEqualTo("idsender", idsender)
       .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               Log.d(TAG, "code  query"+task.getResult().isEmpty());

               if(task.getResult().isEmpty()){
                  callback.onLoad(false, null);
               }
               else   callback.onLoad(true,idsender+"_"+idreceiver);
           }
       });
   }

    @Override
    public void validateChatExistence( final int idsender,final  int idreceiver,final Callback<String>callback)
    {

        getchatexistence(idsender, idreceiver, callback);
        getchatexistence(idreceiver, idsender, callback);

    }

    @Override
    public void listLastMessage(String codeChat, SendDataChatBundle.TypeChat  type, SendDataChatBundle.TypePerson  typePersonsGroup, final Callback<List<Object>>chatUiCallback)
    {
        Query query;
        if(type== SendDataChatBundle.TypeChat.GROUP){
            //type group
            query=collectionReferenceMessage.whereEqualTo("idchat", codeChat).whereEqualTo("typePerson", getTypeGroupPerson(typePersonsGroup))
            .orderBy("date", Query.Direction.DESCENDING).limit(LIMIT_LIST);
        }else {
            //type personal
            query = collectionReferenceMessage.whereEqualTo("idchat", codeChat).orderBy("date", Query.Direction.DESCENDING).limit(LIMIT_LIST);
        }
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<MessageUi>messageUis= new ArrayList<>();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d(TAG, "listMessage "+doc.getId() + " => " + doc.getData());

                    //   Log.d(TAG, " => " + doc.get("idchat") +  "  "+ doc.get("message"));
                    MessageUi messageUi= new MessageUi();
                    messageUi.setDate(doc.getDate("date"));
                    messageUi.setId(doc.getId());
                    messageUi.setMessage(doc.getString("message"));
                    messageUi.setState(MessageUi.State.SEND);
                    messageUi.setIdsender(doc.getLong("idsender").intValue());
                    //  objectList.add(getDate(doc.getDate("date")));
                    messageUis.add(messageUi);


                }
                Collections.reverse(messageUis);
                Set<Object> objectList = new LinkedHashSet<>();
                for(MessageUi messageUi: messageUis)
                {
                    objectList.add(getDate(messageUi.getDate()));
                    objectList.add(messageUi);
                }

                String source = queryDocumentSnapshots.getMetadata().isFromCache() ?
                        "local cache" : "server";
                Log.d(TAG, "Data fetched from " + source);
                chatUiCallback.onLoad(true,new ArrayList<Object>(objectList));

            }
        });

    }

    @Override
    public void foundChatReceiver(int idreceiver, final Callback<ChatUi>chatUiCallback) {

        collectionReferencePerson.whereEqualTo("idperson", idreceiver).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "foundChatReceiver data "+document.getId() + " => " + document.getData());
                                ChatUi chatUi= new ChatUi();
                                chatUi.setUrl_image(document.getString("urlpicture"));
                                chatUi.setNameReceiver(document.getString("name"));
                                Log.d(TAG, "dateconexion  "+ document.getDate("dateconexion"));
                                Date date=  document.getDate("dateconexion");
                                if(date!=null)chatUi.setLastDateConexion(getDateLastConexion(date));
                                chatUi.setActive(document.getBoolean("active"));
                                chatUi.setTypeChat(ChatUi.TypeChat.PERSONAL);
                                //set subtitlte

                                int idperson= document.getLong("idperson").intValue();
                                Log.d(TAG, "idperson "+ idperson);
                                List<Relaciones> relaciones= SQLite.select().from(Relaciones.class)
                                        .where(Relaciones_Table.personaVinculadaId.withTable().eq(idperson))
                                        .and(Relaciones_Table.tipoId.withTable().between(Relaciones.PADRE).and(Relaciones.MADRE))
                                        .queryList();

                                Log.d(TAG, " size : "+ relaciones.size());

                                if(!relaciones.isEmpty())
                                {
                                    //case parents
                                    String names=null;
                                    for(Relaciones relacion: relaciones)
                                    {
                                        Log.d(TAG, "father or mother of : "+ relacion.getPersonaPrincipalId());
                                        Persona persona= SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(relacion.getPersonaPrincipalId())).querySingle();
                                        if(persona!=null){
                                            if(names==null)names= persona.getNombres();
                                            else names.concat(","+persona.getNombres());
                                        }
                                    }

                                    Log.d(TAG, "names: "+ names);
                                    switch (relaciones.get(0).getTipoId())
                                    {
                                        case Relaciones.MADRE:
                                            chatUi.setSubtitle("Madre de : "+ names);
                                            break;
                                        default:
                                            chatUi.setSubtitle("Padre de : "+ names);
                                            break;
                                    }
                                }
                                else {

                                    //case student
                                    Contrato contract= SQLite.select().from(Contrato.class)
                                            .where(Contrato_Table.personaId.withTable().eq(idperson)).querySingle();

                                    if (contract != null)
                                    {
                                        Persona attorney= SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(contract.getApoderadoId())).querySingle();

                                        if(attorney!=null){
                                            Relaciones relation= SQLite.select().from(Relaciones.class)
                                                    .where(Relaciones_Table.personaVinculadaId.eq(attorney.getPersonaId()))
                                                    .and(Relaciones_Table.personaPrincipalId.eq(contract.getPersonaId()))
                                                    .querySingle();
                                            if(relation!=null)
                                            {
                                                switch (relation.getTipoId())
                                                {
                                                    case Relaciones.MADRE:
                                                        chatUi.setSubtitle("Hijo(a)  de : "+ attorney.getNombreCompleto());
                                                        break;
                                                    case Relaciones.PADRE:
                                                        chatUi.setSubtitle("Hijo(a) de : "+ attorney.getNombreCompleto());
                                                        break;
                                                    case Relaciones.TIO:
                                                        chatUi.setSubtitle("Sobrino(a) de : "+ attorney.getNombreCompleto());
                                                        break;
                                                    case Relaciones.ABUELO:
                                                        chatUi.setSubtitle("Nieto(a) de : "+ attorney.getNombreCompleto());
                                                        break;
                                                    case Relaciones.HERMANO:
                                                        chatUi.setSubtitle("Hermano(a) de : "+ attorney.getNombreCompleto());
                                                        break;
                                                    default:
                                                        chatUi.setSubtitle("Su apoderado es: "+ attorney.getNombreCompleto());
                                                        break;
                                                }

                                            }else  chatUi.setSubtitle("Su apoderado es  : "+ attorney.getNombreCompleto());
                                        }

                                    }
                                    //case other
                                    else chatUi.setSubtitle("");
                                }
                                chatUiCallback.onLoad(true, chatUi);
                            }
                        }else    chatUiCallback.onLoad(false, null);
                    }
                });
    }

    @Override
    public void saveLastConexion(int idsender) {
        collectionReferencePerson.whereEqualTo("idperson", idsender)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            String id= task.getResult().getDocuments().get(0).getId();
                            collectionReferencePerson.document(id).update("dateconexion", Calendar.getInstance().getTime(), "active", false);
                        }
                    }
                });

    }

    @Override
    public void saveActiveSession(int idsender) {
        collectionReferencePerson.whereEqualTo("idperson", idsender)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    String id= task.getResult().getDocuments().get(0).getId();
                    if(!id.isEmpty() || id!=null)collectionReferencePerson.document(id).update("active", true);
                }
            }
        });
    }

    public String getDateLastConexion(Date date){

        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);

        String dia= getDate(date).toLowerCase();
     //   int format12= calendar.get(Calendar.HOUR);
        int hora= calendar.get(Calendar.HOUR_OF_DAY);
        int minuto= calendar.get(Calendar.MINUTE);
        String dateConvert="Últ. vez "+dia+" a las "+hora + ":"+minuto+ " "+((hora>=12)? "pm":"am");
        return dateConvert;
    }


    @Override
    public void sendMessage(final String codeChat, final MessageUi messageUi,  SendDataChatBundle.TypePerson typePersonsGroup,  SendDataChatBundle.TypeChat  type,final  Callback<String>  successCallback) {
        addCodeDocument(codeChat , messageUi,type );
        Map<String, Object> message = new HashMap<>();
        message.put("date", messageUi.getDate());
        message.put("idsender",messageUi.getIdsender());
        message.put("message",messageUi.getMessage());
        message.put("idchat",codeChat);
        message.put("typePerson",getTypeGroupPerson(typePersonsGroup) );

        collectionReferenceMessage.add(message).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "create chat success  "+ task.isSuccessful());
                if(task.isSuccessful()){
                    String messageCreated= task.getResult().getId();
                    if(messageCreated!=null) {
                       // saveStateMessage(messageCreated, messageUi.getIdreceiver(), successCallback);
                    }
                }
            }
        });

    }

    private void saveStateMessage(final String messageCreated, int receiverId, final Callback<String>  successCallback) {
        Map<String, Object> seen = new HashMap<>();
        seen.put("idmessage", messageCreated);
        seen.put("state",1);
        seen.put("idreceiver",receiverId);
        collectionReferenceSeen.add(seen).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "id seen "+task.getResult().getId() );
                if(task.isSuccessful())successCallback.onLoad(true, messageCreated);
            }
        });


    }

    @Override
    public void listBeforeMessage(String codeChat, Date lastMessage, SendDataChatBundle.TypeChat type, SendDataChatBundle.TypePerson typePersonsGroup,final  Callback<List<Object>> chatUiCallback) {

        Query query;
        if(type== SendDataChatBundle.TypeChat.GROUP){
            //type group
            query= collectionReferenceMessage.whereEqualTo("idchat", codeChat).whereEqualTo("typePerson", getTypeGroupPerson(typePersonsGroup))
                    .whereLessThan("date", lastMessage)
                    .orderBy("date", Query.Direction.DESCENDING).limit(LIMIT_LIST);
        }else {
            //type personal
            query =   collectionReferenceMessage.whereEqualTo("idchat", codeChat).whereLessThan("date", lastMessage)
                    .orderBy("date", Query.Direction.DESCENDING).limit(LIMIT_LIST);
        }
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            List<MessageUi>messageUis= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "more message "+document.getId() + " => " + document.getData());
                                MessageUi messageUi= new MessageUi();
                                messageUi.setDate(document.getDate("date"));
                                messageUi.setId(document.getId());
                                messageUi.setMessage(document.getString("message"));
                                messageUi.setState(MessageUi.State.SEND);
                                messageUi.setIdsender(document.getLong("idsender").intValue());
                              //  objectList.add(getDate(document.getDate("date")));
                                messageUis.add(messageUi);

                            }

                            Collections.reverse(messageUis);
                            Set<Object> objectList = new LinkedHashSet<>();
                            for(MessageUi messageUi:messageUis)
                            {
                                objectList.add(getDate(messageUi.getDate()));
                                objectList.add(messageUi);
                            }

                            chatUiCallback.onLoad(true, new ArrayList<Object>(objectList));
                        }

                    }
                });

    }


    public String getDate(Date date){

        String dateConvert= Utils.f_fecha_letras(date.getTime()).toUpperCase();

        Calendar calendarToday=Calendar.getInstance();
        int dayToday= calendarToday.get(Calendar.DAY_OF_MONTH);
        int monthToday= calendarToday.get(Calendar.MONTH);
        int yearToday= calendarToday.get(Calendar.YEAR);


        calendarToday.setTime(date);
        int day= calendarToday.get(Calendar.DAY_OF_MONTH);
        int month= calendarToday.get(Calendar.MONTH);
        int year= calendarToday.get(Calendar.YEAR);

        if(year==yearToday&&month==monthToday &&day==dayToday)dateConvert="HOY";
        return dateConvert;
    }

    private void addCodeDocument(String codeChat, MessageUi messageUi, SendDataChatBundle.TypeChat type) {
        String[] parts = codeChat.split("_");
        if(type== SendDataChatBundle.TypeChat.PERSONAL){
            int sender= Integer.parseInt(parts[0]);
            int receiver = Integer.parseInt(parts[1]);
            //only for type personal
            existedChat(sender, receiver,messageUi);
        }else{
            int typePersonGroup;
            String  typePersonString= parts[1];
            switch (typePersonString){
                case TEACHER:
                    typePersonGroup=2;
                    break;
                case STUDENTS:
                    typePersonGroup=1;
                    break;
                default:
                    typePersonGroup=0;
                    break;
            }
            String receiver= parts[0];
            updateChatGroup( receiver, messageUi, typePersonGroup);
        }
    }

    private void updateChatGroup( String receiver, MessageUi messageUi, int typePersonGroup) {

        CollectionReferenceChat.document(receiver+"_"+getNomenclature(typePersonGroup)).update("lastmessage", messageUi.getMessage(),"lastdate",messageUi.getDate() )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "last message and date update "+ task.isSuccessful());
                    }
                });
    }

    private void existedChat(final int sender,final  int receiver,final MessageUi messageUi) {

        CollectionReferenceChat.document(sender+"_"+receiver).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    if (task.getResult().getData()==null) {
                        Log.d(TAG, "chat not existed ");
                        setChat(sender, receiver, messageUi);
                    } else {
                        Log.d(TAG, "chat  existed ");
                        updateChat(sender, receiver, messageUi);
                    }

                }
            }

        });
    }


    private void updateChat(int sender, int receiver, MessageUi messageUi) {
        CollectionReferenceChat.document(sender+"_"+receiver).update("lastmessage", messageUi.getMessage(),"lastdate",messageUi.getDate() )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "last message and date update "+ task.isSuccessful());
                    }
                });
    }

    private void setChat(final int sender, final int receiver, final MessageUi messageUi) {

        collectionReferencePerson.whereEqualTo("idperson", receiver).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot query= task.getResult().getDocuments().get(0);
                    Map<String, Object> chat = new HashMap<>();
                    chat.put("idsender", sender);
                    chat.put("idreceiver", receiver);
                    chat.put("nameReceiver", query.getString("name"));
                    chat.put("urlpictureReceiver", query.getString("urlpicture"));
                    chat.put("lastmessage", messageUi.getMessage());
                    chat.put("lastdate", messageUi.getDate());
                    chat.put("typeChatGroup",false );
                    CollectionReferenceChat.document(sender+"_"+receiver).set(chat);
                }
            }
        });

    }


    //methods for group

    public int getTypeGroupPerson(SendDataChatBundle.TypePerson typePerson){
        switch (typePerson){
            case TEACHERS:
                return 2;
            case STUDENTS:
                return 1;
            default:return 0;
        }
    }
    @Override
    public void valideGroupExistence(final String idgroup, final boolean internet, final   SendDataChatBundle.TypeGroup typeGroup, final SendDataChatBundle.TypePerson typePerson, final SuccessCallback successCallbackPerson) {
        Query query;
       if(typeGroup== SendDataChatBundle.TypeGroup.TEAM){
           query = collectionReferencePerson.whereEqualTo("idperson", idgroup)
                   .whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
       }else{
            query = collectionReferencePerson.whereEqualTo("idperson", Integer.parseInt(idgroup))
                   .whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
       }

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d(TAG, " valideGroupExistence "+task.getResult().isEmpty());

                    if(task.getResult().isEmpty()){
                        if(internet)createPersonGroup(idgroup, typeGroup,successCallbackPerson, typePerson);
                        else successCallbackPerson.onLoad(false);
                    }
                    else successCallbackPerson.onLoad(true);

                }
            }
        });
    }
    public String getsection(int idsection){
        String section="";
        Seccion sectionQuery=SQLite.select().from(Seccion.class).where(Seccion_Table.seccionId.withTable().eq(idsection)).querySingle();
        if(sectionQuery!=null)section=sectionQuery.getNombre();
        return section;
    }
    public String getPeriod(int idperiod){
        String period="";
        Periodo periodQuery=SQLite.select().from(Periodo.class).where(Periodo_Table.periodoId.withTable().eq(idperiod)).querySingle();
        if(periodQuery!=null)period=periodQuery.getNombre();
        return period;
    }

    private void createPersonGroup(String idgroup,  SendDataChatBundle.TypeGroup typeGroup, final SuccessCallback successCallbackPerson, SendDataChatBundle.TypePerson typePerson) {
        Log.d(TAG, "createPersonGroup " );
        Map<String, Object> groupnew = new HashMap<>();

        switch (typeGroup){
            case ACADEMIC:

                CargaAcademica charge= SQLite.select().from(CargaAcademica.class)
                        .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(Integer.parseInt(idgroup))).querySingle();
                if(charge!=null){
//                    String program="";
//                    ProgramasEducativo programQuery=SQLite.select().from(ProgramasEducativo.class)
//                            .innerJoin(PlanEstudios.class)
//                            .on(ProgramasEducativo_Table.programaEduId.withTable()
//                                    .eq(PlanEstudios_Table.programaEduId.withTable()))
//                            .innerJoin(CargaAcademica.class)
//                            .on(PlanEstudios_Table.planEstudiosId.withTable()
//                                    .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
//                            .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(idgroup))
//                            .querySingle();
//                    if(programQuery!=null)program=programQuery.getNombre();
                    //section
                    groupnew.put("name", getsection(charge.getSeccionId())+", "+ getPeriod(charge.getPeriodoId()));
                    groupnew.put("urlpicture", getsection(charge.getSeccionId()));
                    groupnew.put("idperson", Integer.parseInt(idgroup));

                }
                break;
            case COURSE:
                List<Integer>integers=new ArrayList<>();
                integers.add(Integer.parseInt(idgroup));
                List<CursoCustom>cursoCustoms=cursoDao.obtenerPorCargaCursos(integers);
                for(CursoCustom cursoCustom:cursoCustoms){
                    groupnew.put("name", getsection(cursoCustom.getSeccionId())+", "+ getPeriod(cursoCustom.getPeriodoId())+", "+cursoCustom.getNombre());
                    groupnew.put("urlpicture",cursoCustom.getNombre());
                    break;
                }
                groupnew.put("idperson", Integer.parseInt(idgroup));
                break;
            default:
                GrupoEquipoC team= SQLite.select().from(GrupoEquipoC.class)
                        .where(GrupoEquipoC_Table.key.withTable().eq(idgroup))
                        .querySingle();
                if(team!=null){
                    groupnew.put("name",team.getNombre());
                   // groupnew.put("urlpicture",cursoCustom.getNombre());
                }
                groupnew.put("idperson", idgroup);
                break;

        }
        groupnew.put("typePerson", getTypeGroupPerson(typePerson));
        groupnew.put("active", false);
        groupnew.put("dateconexion", null);
        //groupnew.put("typePerson",)
        collectionReferencePerson.add(groupnew).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "created "+ task.isSuccessful());
                if(task.isSuccessful())successCallbackPerson.onLoad(true);
                else
                {
                    Log.d(TAG, "Error creating person: ", task.getException());
                    successCallbackPerson.onLoad(false);
                }
            }
        });
    }


    @Override
    public void validateChatExistenceGroup(final int idsender, final String idreceiver, final SendDataChatBundle.TypePerson  typePerson, final SendDataChatBundle.TypeGroup typeGroup, final Callback<ChatUi> callback) {
        Query query;
        if(typeGroup== SendDataChatBundle.TypeGroup.TEAM){
            query= CollectionReferenceChat.whereEqualTo("idreceiver", idreceiver).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }else{
            query= CollectionReferenceChat.whereEqualTo("idreceiver", Integer.parseInt(idreceiver)).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "validateChatExistenceGroup "+ task.isSuccessful());
                Log.d(TAG, "empty "+ task.getResult().isEmpty());

                if(task.getResult().isEmpty()){

                    callback.onLoad(false, null);

                }
                else {
                    Log.d(TAG, "data "+ task.getResult());

                    getChatHeaderGroup(idreceiver,callback, typePerson,typeGroup);
                }
            }
        });

    }

    @Override
    public void getPersonsOfGroup(int senderId, String groupId,  SendDataChatBundle.TypeGroup typeGroup,SendDataChatBundle.TypePerson  typePerson,Callback<List<Integer>> callback) {

    }

    @Override
    public void createChatGroup(final int idsender,final  String idreceiver, final SendDataChatBundle.TypePerson  typePerson,final  SendDataChatBundle.TypeGroup typeGroup,final  Callback<ChatUi> callback) {

        Query query;
        if(typeGroup== SendDataChatBundle.TypeGroup.TEAM){
            query= collectionReferencePerson.whereEqualTo("idperson",idreceiver).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }else{
            query=collectionReferencePerson.whereEqualTo("idperson",Integer.parseInt(idreceiver)).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot document:task.getResult()){
                        Map<String, Object> chat = new HashMap<>();
                        chat.put("idsender", idsender);
                        chat.put("lastmessage", null);
                        chat.put("lastdate", null);
                        switch (typeGroup){
                            case COURSE:
                                chat.put("typeGroup",2 );
                                chat.put("idreceiver", Integer.parseInt(idreceiver));
                                CargaCursos course= SQLite.select().from(CargaCursos.class)
                                        .where(CargaCursos_Table.cargaCursoId.withTable().eq(Integer.parseInt(idreceiver)))
                                        .querySingle();
                                if(course!=null)   chat.put("idacademic",course.getCargaAcademicaId() );
                                break;
                            case ACADEMIC:
                                chat.put("typeGroup",1);
                                chat.put("idreceiver", Integer.parseInt(idreceiver));
                                chat.put("idacademic",Integer.parseInt(idreceiver));
                                break;
                             default:
                                 chat.put("idreceiver", idreceiver);
                                 GrupoEquipoC team=SQLite.select().from(GrupoEquipoC.class).where(GrupoEquipoC_Table.key.withTable().eq(idreceiver)).querySingle();
                                 chat.put("typeGroup",3);
                                 if(team!=null)chat.put("idacademic",team.getCargaAcademicaId());
                                 //save id charge academic
                                 break;
                        }
                        chat.put("nameReceiver",document.getString("name"));
                        chat.put("urlpictureReceiver",document.getString("urlpicture"));
                        chat.put("typePerson",document.getLong("typePerson").intValue());
                        chat.put("typeChatGroup",true );

                        CollectionReferenceChat.document(idreceiver+"_"+getNomenclature(getTypeGroupPerson(typePerson))).set(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful())getChatHeaderGroup(idreceiver,callback,typePerson, typeGroup );
                            }
                        });
                    }


                }
            }
        });

    }
    public String getNomenclature(int typePersonGroup){
        String nomenclature;
        switch (typePersonGroup){
            case 1:
                nomenclature=STUDENTS;
                break;
            case 2:
                nomenclature=TEACHER;
                break;
            default:
                nomenclature=PARENTS;
                break;
        }
        return nomenclature;
    }

    public void getChatHeaderGroup( String receiverId, final Callback<ChatUi> callback, SendDataChatBundle.TypePerson  typePerson, SendDataChatBundle.TypeGroup typeGroup){
        Query query;
        if(typeGroup== SendDataChatBundle.TypeGroup.TEAM){
            query= CollectionReferenceChat.whereEqualTo("idreceiver", receiverId).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }else {
            query= CollectionReferenceChat.whereEqualTo("idreceiver",Integer.parseInt(receiverId)).whereEqualTo("typePerson", getTypeGroupPerson(typePerson));
        }
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot:task.getResult()){
                    ChatUi chatUi = new ChatUi();
                    chatUi.setCode(documentSnapshot.getId());

                    chatUi.setUrl_image(documentSnapshot.getString("urlpictureReceiver"));
                    chatUi.setTypeChat(ChatUi.TypeChat.GROUP);
                    switch (documentSnapshot.getLong("typeGroup").intValue()){
                        case 1:
                            //academic
//                            Random rnd = new Random();
//                            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                            //chatUi.setColor(String.valueOf(color));
                            chatUi.setTypeGroup(ChatUi.TypeGroup.ACADEMIC);
                            break;

                        case 2:
                            //course
                            ParametrosDisenio colorParameters=parametrosDisenioDao.obtenerPorCargaCurso(documentSnapshot.getLong("idreceiver").intValue());
                            if(colorParameters!=null)chatUi.setColor(colorParameters.getColor1());
                            chatUi.setTypeGroup(ChatUi.TypeGroup.COURSE);
                            break;
                        default:
                            //team
                            break;
                    }
                    switch (documentSnapshot.getLong("typePerson").intValue()){
                        case 1:
                            chatUi.setNameReceiver(documentSnapshot.getString("nameReceiver")+ " - Alumnos");
                            chatUi.setTypePerson(ChatUi.TypePerson.STUDENTS);
                            break;
                        case 2:
                            chatUi.setNameReceiver(documentSnapshot.getString("nameReceiver")+ " - Docentes");
                            chatUi.setTypePerson(ChatUi.TypePerson.TEACHERS);
                            break;
                        default:
                            chatUi.setNameReceiver(documentSnapshot.getString("nameReceiver")+ " - Padres");
                            chatUi.setTypePerson(ChatUi.TypePerson.PARENTS);
                            break;
                    }

                    callback.onLoad(true, chatUi);
                }

            }
        });
    }


//    public void setDtaPerson(final int count,final int receiverId, final List<Integer> personIds,final Callback<ChatUi> callback,final int typePerson){
//
//
//        Map<String, Object> data= new HashMap<>();
//        int personId=personIds.get(count-1);
//        data.put("idperson", personId);
//        CollectionReferenceChat.document(receiverId+"_"+getNomenclature(typePerson) ).collection("persons").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    if(count==personIds.size())getChatHeaderGroup(receiverId,callback,typePerson );
//                    else setDtaPerson(count+1, receiverId,personIds, callback,typePerson);
//                }
//
//
//            }
//        });
//    }

}
