package com.consultoraestrategia.ss_crmeducativo.personalChat.data;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class PersonalChatDataLocalSource implements PersonalChatDataSource {
    CursoDao cursoDao;

    public PersonalChatDataLocalSource(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    @Override
    public void validateChatExistence(int idsender, int idreceiver, Callback<String> callback) {

    }

    @Override
    public void validatePersonExistence(int idperson, boolean internet, SuccessCallback successCallbackPerson) {

    }

    @Override
    public void listLastMessage(String codeChat,  SendDataChatBundle.TypeChat  type, SendDataChatBundle.TypePerson  typePersonsGroup, Callback<List<Object>> chatUiCallback) {

    }

    @Override
    public void foundChatReceiver(int idreceiver, Callback<ChatUi> chatUiCallback) {

    }

    @Override
    public void saveLastConexion(int idsender) {

    }

    @Override
    public void saveActiveSession(int idsender) {

    }

    @Override
    public void sendMessage(String codeChat, MessageUi messageUi, SendDataChatBundle.TypePerson  typePersonsGroup, SendDataChatBundle.TypeChat  type,Callback<String> successCallback) {

    }

    @Override
    public void listBeforeMessage(String codeChat, Date lastMessage,   SendDataChatBundle.TypeChat  type, SendDataChatBundle.TypePerson  typePersonsGroup,Callback<List<Object>> chatUiCallback) {

    }

    @Override
    public void valideGroupExistence(String idgroup, boolean internet,  SendDataChatBundle.TypeGroup typeGroup,  SendDataChatBundle.TypePerson typePerson,SuccessCallback successCallbackPerson) {

    }

    @Override
    public void validateChatExistenceGroup(int idsender, String idreceiver, SendDataChatBundle.TypePerson  typePerson,  SendDataChatBundle.TypeGroup typeGroup,Callback<ChatUi> callback) {

    }

    @Override
    public void getPersonsOfGroup(int senderId, String groupId, SendDataChatBundle.TypeGroup typeGroup,SendDataChatBundle.TypePerson  typePerson,Callback<List<Integer>> callback) {

        HashSet<Integer> persons=new LinkedHashSet<>();
        persons.add(senderId);
        switch (typeGroup){
            case COURSE:
                Log.d(getClass().getSimpleName(), "group course"+ groupId);
                CargaCursos course= SQLite.select().from(CargaCursos.class)
                        .where(CargaCursos_Table.cargaCursoId.withTable().eq(Integer.parseInt(groupId))).querySingle();
                if(course!=null){
                    switch (typePerson){
                        case STUDENTS:
                            //with students
                            List<Contrato>studentList=SQLite.select().from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable().eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaCursoId.withTable().eq(course.getCargaCursoId()))
                                    .queryList();
                            for(Contrato contract: studentList)persons.add(contract.getPersonaId());
                            Log.d(getClass().getSimpleName(), "students size "+ studentList.size());
                            break;
                        case TEACHERS:
                            //with teachers
                            List<Persona>teachersList=SQLite.select().from(Persona.class)
                                    .innerJoin(Empleado.class)
                                    .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()))
                                    .innerJoin(CargaCursos.class)
                                    .on(Empleado_Table.empleadoId.withTable().eq(CargaCursos_Table.empleadoId.withTable()))
                                    .where(CargaCursos_Table.cargaCursoId.withTable().eq(course.getCargaCursoId()))
                                     .and(Persona_Table.personaId.withTable().notEq(senderId)).queryList();
                            Log.d(getClass().getSimpleName(), "teachersList size "+ teachersList.size());
                            for(Persona person: teachersList)persons.add(person.getPersonaId());
                            break;
                        default:
                            //with fathers
                            List<Contrato>personList=SQLite.select().from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable().eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaCursoId.withTable().eq(course.getCargaCursoId()))
                                    .queryList();
                            Log.d(getClass().getSimpleName(), "fathers size" +personList.size());
                            for(Contrato contract: personList)persons.add(contract.getApoderadoId());
                            break;
                    }

                }

                break;
            case ACADEMIC:
                Log.d(getClass().getSimpleName(), "group academic"+ groupId);
                CargaAcademica charge=SQLite.select().from(CargaAcademica.class).where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(Integer.parseInt(groupId))).querySingle();
                if(charge!=null){
                    switch (typePerson){
                        case STUDENTS:
                            //with students
                            List<Contrato>studentList=SQLite.select().from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable().eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaAcademicaId.withTable().eq(charge.getCargaAcademicaId()))
                                    .groupBy(DetalleContratoAcad_Table.idContrato.withTable())
                                    .queryList();
                            for(Contrato contract: studentList)persons.add(contract.getPersonaId());
                            Log.d(getClass().getSimpleName(), "students size "+ studentList.size());
                            break;
                        case TEACHERS:
                            //with teachers
                            List<Persona>personaList=SQLite.select().from(Persona.class)
                                    .innerJoin(Empleado.class)
                                    .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()))
                                    .innerJoin(CargaCursos.class)
                                    .on(Empleado_Table.empleadoId.withTable().eq(CargaCursos_Table.empleadoId.withTable()))
                                    .where(CargaCursos_Table.cargaAcademicaId.withTable().eq(charge.getCargaAcademicaId()))
                              //      .and(Persona_Table.personaId.withTable().notEq(senderId)).
                                    .groupBy(CargaCursos_Table.empleadoId.withTable()).queryList();
                            Log.d(getClass().getSimpleName(), "personaList size "+ personaList.size());
                            for(Persona person: personaList)persons.add(person.getPersonaId());
                            break;
                        default:
                            //with fathers
                            List<Contrato>personList=SQLite.select().from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable().eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaAcademicaId.withTable().eq(charge.getCargaAcademicaId()))
                                    .groupBy(DetalleContratoAcad_Table.idContrato.withTable())
                                    .queryList();
                            Log.d(getClass().getSimpleName(), "fathers size" +personList.size());
                            for(Contrato contract: personList)persons.add(contract.getApoderadoId());
                            break;
                    }
                }
                break;
            case TEAM:
        }
        callback.onLoad(true, new ArrayList<Integer>(persons));

    }

    @Override
    public void createChatGroup(int idsender, String idreceiver, SendDataChatBundle.TypePerson  typePerson, SendDataChatBundle.TypeGroup typeGroup, Callback<ChatUi> callback) {

    }
}
