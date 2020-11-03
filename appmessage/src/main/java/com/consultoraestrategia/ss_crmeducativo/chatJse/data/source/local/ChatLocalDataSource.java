package com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

//import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
//import com.consultoraestrategia.ss_crmeducativo.entities.Directivos_Table;

public class ChatLocalDataSource implements ChatDataSource {
    private static final String TAG = "ChatLocalDataSourceTAG";

    @Override
    public PersonaUi getPersona(int personaId) {
        PersonaUi personaUi = new PersonaUi();
        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(personaId))
                .querySingle();

        /*Directivos directivos = SQLite.select()
                .from(Directivos.class)
                .where(Directivos_Table.personaId.eq(personaId))
                .querySingle();*/

        if(persona!=null){
            String nombre = Utils.capitalize(persona.getFirstName())+ " "+ Utils.capitalize(persona.getApellidoPaterno())+" "+ Utils.capitalize(persona.getApellidoMaterno());
            personaUi.setNombre(nombre);
            personaUi.setFoto(persona.getFoto());
        }/*else if(directivos!=null){
            String nombre = Utils.capitalize(Utils.getFirstWord(directivos.getNombre()))+ " "+ Utils.capitalize(directivos.getApellidoPaterno())+" "+ Utils.capitalize(directivos.getApellidoMaterno());
            personaUi.setNombre(nombre);
            personaUi.setFoto(directivos.getFoto());
        }*/else {
            personaUi.setNombre("Desconocido");
        }


        Log.d(TAG, "personaId "+ personaId);
        List<Relaciones> relaciones= SQLite.select().from(Relaciones.class)
                .where(Relaciones_Table.personaVinculadaId.withTable().eq(personaId))
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
                Persona personaRelacion= SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(relacion.getPersonaPrincipalId())).querySingle();
                if(persona!=null){
                    if(names==null)names= persona.getNombres();
                    else names.concat(","+persona.getNombres());
                }
            }

            Log.d(TAG, "names: "+ names);
            switch (relaciones.get(0).getTipoId())
            {
                case Relaciones.MADRE:
                    personaUi.setDescripcion("Madre de : "+ names);
                    break;
                default:
                    personaUi.setDescripcion("Padre de : "+ names);
                    break;
            }
        }
        else {

            //case student
            Contrato contract= SQLite.select().from(Contrato.class)
                    .where(Contrato_Table.personaId.withTable().eq(personaId)).querySingle();

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
                                personaUi.setDescripcion("Hijo(a)  de : "+ attorney.getNombreCompleto());
                                break;
                            case Relaciones.PADRE:
                                personaUi.setDescripcion("Hijo(a) de : "+ attorney.getNombreCompleto());
                                break;
                            case Relaciones.TIO:
                                personaUi.setDescripcion("Sobrino(a) de : "+ attorney.getNombreCompleto());
                                break;
                            case Relaciones.ABUELO:
                                personaUi.setDescripcion("Nieto(a) de : "+ attorney.getNombreCompleto());
                                break;
                            case Relaciones.HERMANO:
                                personaUi.setDescripcion("Hermano(a) de : "+ attorney.getNombreCompleto());
                                break;
                            default:
                                personaUi.setDescripcion("Su apoderado es: "+ attorney.getNombreCompleto());
                                break;
                        }

                    }else  personaUi.setDescripcion("Su apoderado es  : "+ attorney.getNombreCompleto());
                }

            }
            //case other
            else personaUi.setDescripcion("");
        }
        return personaUi;
    }

    @Override
    public void saveMensaje(MessageUi2 messageUi2, MessageCallback messageCallback) {

    }

    @Override
    public ListenerFirebase getListaMessage(int emisor, int reseptor, ListaMessageCallback callback) {
        return null;
    }

    @Override
    public ListenerFirebase getListlastMessage(int emisor, int reseptor, Date date, ListaMessageCallback messageCallback) {
        return null;
    }

    @Override
    public void saveKeyPress(int person, int personaExterna) {

    }

    @Override
    public ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback) {
        return null;
    }

    @Override
    public void changeEstado(List<MessageUi2> messageUi2List, Callback<List<MessageUi2>> listCallback) {

    }

    @Override
    public RetrofitCancel sendNotificacion(MessageUi2 messageUi2, String token, MessageCallback messageCallback) {
        return null;
    }

    @Override
    public void getTokensSala(String salaId, Callback<String> stringCallback) {

    }

    @Override
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {

    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback callback) {

    }
}
