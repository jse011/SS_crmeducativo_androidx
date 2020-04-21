package com.consultoraestrategia.ss_crmeducativo.chats.data;

import androidx.annotation.NonNull;

import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.ReferenceFirestore;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class ChatDataRemoteSource  implements ChatDataSource {
    private static final int GUARDADO = 1,ENVIADO = 2, VISTO = 3, ELIMINADO = 0;
    //private CollectionReference collectionReferencePerson= ReferenceFirestore.getPersons();
    private CollectionReference collectionReferenceChats= ReferenceFirestore.getInstanceChat();
    private ParametrosDisenioDao parametrosDisenioDao;
    private String TAG=  ChatDataRemoteSource.class.getSimpleName();
    private int LIMIT_LIST = 40;

    public ChatDataRemoteSource(ParametrosDisenioDao parametrosDisenioDao) {
        this.parametrosDisenioDao = parametrosDisenioDao;
    }

    @Override
    public void getSenderInformation(final int idSender, final Callback<UserUi> chatUiCallback) {
        getUserAbstract(idSender,chatUiCallback);

    }

    private void getUserAbstract(final int idSender, final Callback<UserUi> chatUiCallback) {


    }
    public void createPerson(final int idPerson
                             ,final Callback<UserUi> chatUiCallback)
    {
        Log.d(TAG, "createPerson ");
        Map<String, Object> personnew = new HashMap<>();

        Persona person= SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(idPerson)).querySingle();
        if(person!=null)
        {
            personnew.put("name", person.getNombreCompleto());
            personnew.put("urlpicture",person.getUrlPicture());
            personnew.put("dateconexion", Calendar.getInstance().getTime());
            personnew.put("active",false);
        }

        personnew.put("idperson", idPerson);
        set(personnew, chatUiCallback, person);

    }

    private void set(Map<String, Object> personnew, final Callback<UserUi> chatUiCallback, final Persona person) {

    }

    @Override
    public void getChats(final int senderId, int type, final Callback<List<ChatUi>> callback) {


    }

    public List<Integer> getListChargeAcademic(int senderId){
        HashSet<Integer> integers= new LinkedHashSet<>();
        List<CargaCursos >list=SQLite.select().from(CargaCursos.class)
                .innerJoin(Empleado.class)
                .on(CargaCursos_Table.empleadoId.withTable().eq(Empleado_Table.empleadoId.withTable()))
                .innerJoin(Persona.class)
                .on(Empleado_Table.personaId.withTable().eq(Persona_Table.personaId.withTable()))
                .where(Persona_Table.personaId.withTable().eq(senderId))
                .groupBy(CargaCursos_Table.cargaAcademicaId.withTable()).queryList();
        for(CargaCursos course: list)integers.add(course.getCargaAcademicaId());
        return  new ArrayList<>(integers);

    }

    @Override
    public void getChatsGroups(final int senderId, final Callback<List<ChatUi>> callback) {

     Log.d(TAG, "getChatsGroups list "+ getListChargeAcademic(senderId).size());

        /*collectionReferenceChats.whereEqualTo("typeChatGroup", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.getResult().isEmpty()) validePersonIntoGroup(1, new ArrayList<ChatUi>(),getListChargeAcademic(senderId), callback);
                else callback.onLoad(false, new ArrayList<ChatUi>());
            }
        });*/
    }

    @Override
    public void getContacts(int senderId, Callback<List<Object>> callback) {

    }

    @Override
    public List<Object> getGroups(int personaId) {
        return null;
    }


    @Override
    public void getListFilterGroups(int senderId, Callback<List<Object>> listCallback) {

    }

    @Override
    public UsuarioUi getUsuarioDefault() {
        return null;
    }

    @Override
    public ListenerFirebase getPersonaChats(final int personaId, final Callback<List<ChatUi>> listCallback) {
        Log.d(TAG, "getChats");

       return new ListenerFirebaseImpl(collectionReferenceChats.whereArrayContains("reference", String.valueOf(personaId))
               .orderBy("lastdate", Query.Direction.DESCENDING)
                //.whereEqualTo("idsender",personaId )
                //.whereEqualTo("typeChatGroup", false)
                .limit(LIMIT_LIST)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable final QuerySnapshot queryDocumentSnapshots1, @Nullable final FirebaseFirestoreException e1) {
                        List<ChatUi> chatUiList = new ArrayList<>();
                        if (e1 == null) {
                            if(queryDocumentSnapshots1!=null){
                                chatUiList.addAll(getChat(personaId,queryDocumentSnapshots1));
                            }
                        } else {
                            e1.printStackTrace();
                        }

                        listCallback.onLoad(true, chatUiList);
                    }
                }));


    }

    @Override
    public RetrofitCancel getDatosChat(int personaId, List<ChatUi> chatUiList, Callback<List<ChatUi>> listCallback) {
        return null;
    }

    private List<String> cache = new ArrayList<>();

    private List<ChatUi> getChat(int personaId, QuerySnapshot queryDocumentSnapshots1){
        List<ChatUi> chatUiList = new ArrayList<>();
        int change = 0;
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        for (DocumentSnapshot query : queryDocumentSnapshots1) {
            try {
                Log.d(TAG, " last message " + query.getString("lastmessage") + " last date " + query.getDate("lastdate"));
                ChatUi chatUi = null;
                try {
                    chatUi = new ChatUi();
                    chatUi.setId(query.getId());
                    int sender = query.getLong("idsender").intValue();
                    chatUi.setName(query.getString("nameReceiver"));
                    chatUi.setImageRec(query.getString("urlpictureReceiver"));
                    chatUi.setLastMsg(query.getString("lastmessage"));
                    chatUi.setLastDate(query.getDate("lastdate"));
                    chatUi.setIdReceiver(query.getLong("idreceiver").intValue());
                    chatUi.setIdSender(sender);
                    chatUi.setCode(query.getId());
                    chatUi.setSalaId(query.getString("idclassroom"));
                    chatUi.setSalaTipo(query.getString("typeclassroom"));

                    Long cargaCursoId = query.getLong("cargaCursoId");
                    Long cargaAcademicaId = query.getLong("cargaAcademicaId");
                    List<Long> docenteId = (List<Long>) query.get("docenteId");
                    chatUi.setCargaCursoId(cargaCursoId!=null?cargaCursoId.intValue():0);
                    chatUi.setCargaAcademicaId(cargaAcademicaId!=null?cargaAcademicaId.intValue():0);
                    chatUi.setGrupoEquipoId(query.getString("grupoEquipoId"));
                    chatUi.setDocenteId(docenteId!=null?docenteId:new ArrayList<Long>());
                    chatUi.setMensageId(query.getString("idmessage"));


                    //for now personal
                    int state = query.getLong("state")!=null?query.getLong("state").intValue():0;
                    switch (state){
                        case GUARDADO:
                            chatUi.setEstado(ChatUi.ESTADO.GUARDADO);
                            if(!queryDocumentSnapshots1.getMetadata().hasPendingWrites()){
                                if (personaId != chatUi.getIdSender()&&chatUi.getIdSender()!=0) {
                                    String estadoCache = query.getId()+"_state"+ENVIADO;
                                    int position = cache.indexOf(estadoCache);
                                    if(position==-1){
                                        cache.add(estadoCache);
                                        change++;
                                        batch.update(collectionReferenceChats.document(query.getId()),"state", ENVIADO);
                                    }
                                }
                            }
                            break;
                        case ENVIADO:
                            chatUi.setEstado(ChatUi.ESTADO.ENVIADO);
                            break;
                        case VISTO:
                            chatUi.setEstado(ChatUi.ESTADO.VISTO);
                            break;
                        default:
                            chatUi.setEstado(ChatUi.ESTADO.ELIMINADO);
                            break;
                    }

                }catch (Exception e){
                    chatUi =null;
                    e.printStackTrace();
                }
                if(chatUi!=null)chatUiList.add(chatUi);



                // Log.d(TAG, "getChats de donde " +   query.getMetadata().isFromCache());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if(change>0){
            // Commit the batch
            batch.commit();
        }

        return chatUiList;
    }

    @Override
    public ListenerFirebase getGrupoChats(int docenteId, final int personaId, final Callback<List<ChatUi>> listCallback) {
        Log.d(TAG, "docente_"+docenteId);
        return new ListenerFirebaseImpl(collectionReferenceChats.whereArrayContains("reference", "docente_"+docenteId)
                .orderBy("lastdate", Query.Direction.DESCENDING)
                //.whereEqualTo("idsender",personaId )
                //.whereEqualTo("typeChatGroup", false)
                .limit(LIMIT_LIST)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable final QuerySnapshot queryDocumentSnapshots1, @Nullable final FirebaseFirestoreException e1) {
                        Log.d(TAG, "getGrupoChats");
                        List<ChatUi> chatUiList = new ArrayList<>();
                        if (e1 == null) {
                            if(queryDocumentSnapshots1!=null){
                                chatUiList.addAll(getChat(personaId, queryDocumentSnapshots1));
                            }
                        } else {
                            e1.printStackTrace();
                        }

                        listCallback.onLoad(true, chatUiList);
                    }
                }));
    }


}
