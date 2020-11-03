package com.consultoraestrategia.ss_crmeducativo.chats.data;

import androidx.annotation.NonNull;

import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
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
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import okhttp3.OkHttpClient;
import retrofit2.Call;

public class ChatDataRemoteSource  implements ChatDataSource {
    private static final int GUARDADO = 1,ENVIADO = 2, VISTO = 3, ELIMINADO = 0;
    //private CollectionReference collectionReferencePerson= ReferenceFirestore.getPersons();
    private CollectionReference collectionReferenceChats= ReferenceFirestore.getInstanceChat();
    private ParametrosDisenioDao parametrosDisenioDao;
    private String TAG=  ChatDataRemoteSource.class.getSimpleName();
    private int LIMIT_LIST = 40;
    private UtilServidor utilServidor;
    public ChatDataRemoteSource(UtilServidor utilServidor, ParametrosDisenioDao parametrosDisenioDao) {
        this.parametrosDisenioDao = parametrosDisenioDao;
        this.utilServidor = utilServidor;
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
                    chatUi.setImageRec(query.getString("imageReceiver"));

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

    @Override
    public RetrofitCancel sincronizarInformacion(final SuccessCallback callBack) {
        SessionUser sessionUser = SessionUser.getCurrentUser();
        final int usuarioId = sessionUser!=null?sessionUser.getUserId():0;

        UsuarioRolGeoreferencia rolGeoreferencia = SQLite.select()
                .from(UsuarioRolGeoreferencia.class)
                .innerJoin(Entidad.class)
                .on(UsuarioRolGeoreferencia_Table.entidadId.withTable()
                        .eq(Entidad_Table.entidadId.withTable()))
                .where(UsuarioRolGeoreferencia_Table.usuarioId
                        .is(usuarioId))
                .and(Entidad_Table.estadoId.eq(Entidad.ESTADO_AUTORIZADO))
                .querySingle();


        int entidadId = 0;
        int georeferenciaId = 0;
        int empleadoId = 0;
        int anioAcademicoId = 0;

        if(rolGeoreferencia!=null){
            entidadId = rolGeoreferencia.getEntidadId();
            georeferenciaId = rolGeoreferencia.getGeoReferenciaId();

            List<AnioAcademico> anioAcademicoList = SQLite.select()
                    .from(AnioAcademico.class)
                    .where(AnioAcademico_Table.georeferenciaId.eq(georeferenciaId))
                    .queryList();

            Collections.sort(anioAcademicoList, new Comparator<AnioAcademico>() {
                public int compare(AnioAcademico o1, AnioAcademico o2) {
                    return Utils.convertirfecha(o2.getFechaFin()).compareTo(Utils.convertirfecha(o1.getFechaFin()));
                }
            });


            for(AnioAcademico anioAcademico : anioAcademicoList){
                if(anioAcademico.isToogle())anioAcademicoId = anioAcademico.getIdAnioAcademico();
            }

            if(!anioAcademicoList.isEmpty()&&anioAcademicoId==0)anioAcademicoId = anioAcademicoList.get(0).getIdAnioAcademico();
        }

        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioId))
                .querySingle();

        Empleado empleado = SQLite.select()
                .from(Empleado.class)
                .where(Empleado_Table.personaId.eq(
                        usuario==null ? 0: usuario.getPersonaId()
                ))
                .querySingle();

        empleadoId = empleado==null? 0 :  empleado.getEmpleadoId();



        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        final Set<Integer> cargaCursoIdList = new LinkedHashSet<>();
        for (CargaCursos itemCargaCursos : cargaCursosList){
            cargaCursoIdList.add(itemCargaCursos.getCargaCursoId());
        }

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(15, 15, 15, TimeUnit.SECONDS);

        Call<RestApiResponse<BEDatosContacto>> responseCall = apiRetrofit.flst_getDatosContacto(usuarioId, georeferenciaId, new ArrayList<Integer>(cargaCursoIdList));
        RetrofitCancel<BEDatosContacto> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosContacto>() {
            @Override
            public void onResponse(final BEDatosContacto response) {
                if(response == null){
                    callBack.onLoad(false);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Contrato> contratoAcadList = SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                                    .from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable()
                                            .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .innerJoin(SilaboEvento.class)
                                    .on(DetalleContratoAcad_Table.cargaCursoId.withTable()
                                            .eq(SilaboEvento_Table.cargaCursoId.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaCursoId.withTable().in(cargaCursoIdList))
                                    .queryList();

                            List<Integer> contratoIdList = new ArrayList<>();
                            List<Integer> personaIdList = new ArrayList<>();
                            List<Integer> cargaCursoDocenteIdList = new ArrayList<>();
                            for (Contrato contrato: contratoAcadList){
                                contratoIdList.add(contrato.getIdContrato());
                                personaIdList.add(contrato.getPersonaId());
                            }

                            List<CargaCursoDocente> cargaCursoDocenteList = SQLite.select()
                                    .from(CargaCursoDocente.class)
                                    .where(CargaCursoDocente_Table.cargaCursoId.in(cargaCursoIdList))
                                    .queryList();

                            for (CargaCursoDocente cargaCursoDocente : cargaCursoDocenteList)cargaCursoDocenteIdList.add(cargaCursoDocente.getCargaCursoDocenteId());

                            TransaccionUtils.deleteTable(Relaciones.class, Relaciones_Table.personaPrincipalId.in(personaIdList));
                            TransaccionUtils.deleteTable(Contrato.class, Contrato_Table.idContrato.in(contratoIdList));
                            TransaccionUtils.deleteTable(CargaCursoDocente.class, CargaCursoDocente_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(CargaCursoDocenteDet.class, CargaCursoDocenteDet_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(DetalleContratoAcad.class, DetalleContratoAcad_Table.idContrato.in(contratoIdList));
                            TransaccionUtils.deleteTable(Directivos.class);

                            TransaccionUtils.fastStoreListInsert(Contrato.class, response.getContratos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, response.getDetalleContratoAcad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersonas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Usuario.class, response.getUsuariosrelacionados(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Empleado.class, response.getEmpleados(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, response.getCargaCursoDocente(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Directivos.class, response.getDirectivos(), databaseWrapper, true);


                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBack.onLoad(true);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBack.onLoad(false);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                callBack.onLoad(false);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        return retrofitCancel;
    }


}
