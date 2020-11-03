package com.consultoraestrategia.ss_crmeducativo.chats.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.CategoryUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Message;
import com.consultoraestrategia.ss_crmeducativo.entities.Message_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.AppMessengetNotification;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChatDataLocalSource implements ChatDataSource {

    private String TAG= ChatDataLocalSource.class.getSimpleName();
    private CursoDao cursoDao;
    private ParametrosDisenioDao parametrosDisenioDao;

    public ChatDataLocalSource(CursoDao cursoDao,ParametrosDisenioDao parametrosDisenioDao) {
        this.cursoDao = cursoDao;
        this.parametrosDisenioDao=parametrosDisenioDao;
    }

    @Override
    public void getContacts(int senderId, Callback<List<Object>> callback) {

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

        int georeferenciaId = rolGeoreferencia!= null?rolGeoreferencia.getGeoReferenciaId():0;
        int anioAcademicoId = 0;
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

        Set<Object> objectListPerson=new LinkedHashSet<>();
        List<Directivos> directivosList = SQLite.select()
                .from(Directivos.class)
                .where(Directivos_Table.georeferenciaId.eq(georeferenciaId))
                .queryList();

        for(Directivos directivos: directivosList){
            objectListPerson.add("Directivos");
            ContactUi contactUi= new ContactUi();
            contactUi.setIdPerson(directivos.getPersonaId());
            String nombre = Utils.capitalize(Utils.getFirstWord(directivos.getNombre())) + " " + Utils.capitalize(directivos.getApellidoPaterno()) + " " + Utils.capitalize(directivos.getApellidoMaterno());
            contactUi.setName(nombre);
            contactUi.setPhoto(directivos.getFoto());
            contactUi.setDescripcion(directivos.getCargo());
            contactUi.setContactUiList(new ArrayList<ContactUi>());
            contactUi.setEnable(false);
            contactUi.setOriginIdPerson(senderId);
            contactUi.setType(ContactUi.Type.CONTACT);
            contactUi.setTypeContact(ContactUi.TypeContact.EXECUTIVE);
            objectListPerson.add(contactUi);
        }

        //list teachers

        List<Persona> queryTeachers = SQLite.select().from(Persona.class)
                .innerJoin(Empleado.class).on(Empleado_Table.personaId.withTable()
                        .eq(Persona_Table.personaId.withTable())).where(Persona_Table.personaId.withTable()
                        .notEq(senderId))
                .orderBy(Persona_Table.nombres.withTable().asc())
                .queryList();
        Log.d(TAG,"teachers size "+ queryTeachers.size());
        //personaList.addAll(queryTeachers);

        for(Persona persona: queryTeachers){
            objectListPerson.add("Docentes");
            ContactUi contactUi= new ContactUi();
            contactUi.setIdPerson(persona.getPersonaId());
            String nombre = Utils.capitalize(persona.getFirstName()) + " " + Utils.capitalize(persona.getApellidoPaterno()) + " " + Utils.capitalize(persona.getApellidoMaterno());
            contactUi.setName(nombre);
            contactUi.setPhoto(persona.getUrlPicture());
            contactUi.setContactUiList(new ArrayList<ContactUi>());
            contactUi.setEnable(false);
            contactUi.setOriginIdPerson(senderId);
            contactUi.setType(ContactUi.Type.CONTACT);
            contactUi.setTypeContact(ContactUi.TypeContact.TEACHER);
            objectListPerson.add(contactUi);

        }

        //students
        Empleado empleado= SQLite.select().from(Empleado.class).where(Empleado_Table.personaId.withTable().eq(senderId)).querySingle();
        if(empleado!=null){
            List<PersonaContratoQuery>queryStudents= SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable()))
                    .from(Persona.class)
                    .innerJoin(Contrato.class)
                    .on(Persona_Table.personaId.withTable()
                            .eq(Contrato_Table.personaId.withTable()))
                    .innerJoin(DetalleContratoAcad.class)
                    .on(Contrato_Table.idContrato.withTable()
                            .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                    .innerJoin(SilaboEvento.class)
                    .on(DetalleContratoAcad_Table.cargaCursoId.withTable()
                            .eq(SilaboEvento_Table.cargaCursoId.withTable()))
                    .where(SilaboEvento_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                    .and(DetalleContratoAcad_Table.anioAcademicoId.withTable().eq(anioAcademicoId))
                    .groupBy(Persona_Table.personaId.withTable(), Contrato_Table.idContrato.withTable())
            .queryCustomList(PersonaContratoQuery.class);
            Log.d(TAG,"students size "+ queryStudents.size());
           // personaList.addAll(queryStudents);

            for(PersonaContratoQuery persona: queryStudents){
                objectListPerson.add("Alumnos");
                ContactUi contactUi= new ContactUi();
                contactUi.setIdPerson(persona.getPersonaId());
                String nombre = Utils.capitalize(Utils.getFirstWord(persona.getNombres())) + " " + Utils.capitalize(persona.getApellidoPaterno()) + " " + Utils.capitalize(persona.getApellidoMaterno());
                contactUi.setName(nombre);
                contactUi.setPhoto(persona.getFoto());
                contactUi.setEnable(false);
                contactUi.setOriginIdPerson(senderId);
                contactUi.setType(ContactUi.Type.CONTACT);
                contactUi.setTypeContact(ContactUi.TypeContact.STUDENT);

                List<CursoCustom> cursoCustomList = SQLite.select(
                        Cursos_Table.cursoId.withTable(),
                        Cursos_Table.alias.withTable(),
                        Cursos_Table.entidadId.withTable(),
                        Cursos_Table.estadoId.withTable(),
                        Cursos_Table.nivelAcadId.withTable(),
                        Cursos_Table.nombre.withTable(),
                        Cursos_Table.tipoCursoId.withTable(),
                        CargaCursos_Table.cargaCursoId.withTable(),
                        CargaAcademica_Table.cargaAcademicaId.withTable(),
                        CargaAcademica_Table.seccionId.withTable(),
                        CargaAcademica_Table.periodoId.withTable(),
                        CargaAcademica_Table.aulaId.withTable(),
                        CargaAcademica_Table.idPlanEstudio.withTable(),
                        Seccion_Table.nombre.withTable().as("seccion"),
                        Periodo_Table.alias.withTable().as("periodo"),
                        NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                        .from(CargaCursos.class)
                        .innerJoin(CargaAcademica.class)
                        .on(CargaCursos_Table.cargaAcademicaId.withTable().eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                        .innerJoin(Seccion.class)
                        .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                        .innerJoin(Periodo.class)
                        .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                        .innerJoin(PlanEstudios.class)
                        .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                        .innerJoin(ProgramasEducativo.class)
                        .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                        .innerJoin(NivelAcademico.class)
                        .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                        .innerJoin(DetalleContratoAcad.class)
                        .on(CargaCursos_Table.cargaCursoId.withTable().eq(DetalleContratoAcad_Table.cargaCursoId.withTable()))
                        .innerJoin(SilaboEvento.class)
                        .on(CargaCursos_Table.cargaCursoId.withTable().eq(SilaboEvento_Table.cargaCursoId.withTable()))
                        .innerJoin(PlanCursos.class)
                        .on(CargaCursos_Table.planCursoId.withTable().eq(PlanCursos_Table.planCursoId.withTable()))
                        .innerJoin(Cursos.class)
                        .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                        .where(DetalleContratoAcad_Table.idContrato.withTable().eq(persona.getIdContrato()))
                        .and(DetalleContratoAcad_Table.anioAcademicoId.withTable().eq(anioAcademicoId))
                        .and(SilaboEvento_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                        .groupBy(Cursos_Table.cursoId.withTable(),
                                Cursos_Table.alias.withTable(),
                                Cursos_Table.entidadId.withTable(),
                                Cursos_Table.estadoId.withTable(),
                                Cursos_Table.nivelAcadId.withTable(),
                                Cursos_Table.nombre.withTable(),
                                Cursos_Table.tipoCursoId.withTable(),
                                CargaCursos_Table.cargaCursoId.withTable(),
                                CargaAcademica_Table.cargaAcademicaId.withTable(),
                                CargaAcademica_Table.seccionId.withTable(),
                                CargaAcademica_Table.periodoId.withTable(),
                                CargaAcademica_Table.aulaId.withTable(),
                                CargaAcademica_Table.idPlanEstudio.withTable(),
                                NivelAcademico_Table.nivelAcadId.withTable())
                        .queryCustomList(CursoCustom.class);

                StringBuilder nombreCursos = new StringBuilder();
                int count = 0;
                Set<String> nivelList = new LinkedHashSet<>();
                Set<String> cursoList = new LinkedHashSet<>();
                for (CursoCustom cursoCustom : cursoCustomList){
                    String descripcion =  cursoCustom.getPeriodo() +" "+ cursoCustom.getSeccion() + " - " + cursoCustom.getNivelAcademico();
                    nivelList.add(descripcion);
                    cursoList.add(cursoCustom.getNombre());
                    nombreCursos.append(cursoCustom.getNombre()).append(" ").append(cursoCustom.getPeriodo()).append(" ").append(cursoCustom.getSeccion()).append(" - ").append(cursoCustom.getNivelAcademico());
                    count++;
                    if(count>1&&count==cursoCustomList.size()-1){
                        nombreCursos.append(" y ");
                    }else if(count>1&&count!=cursoCustomList.size()) {
                        nombreCursos.append(" ,");
                    }
                }
                Log.d(TAG, "nivelList " + nivelList.size());
                if(cursoList.size() > 1 && nivelList.size()==1){
                    contactUi.setDescripcion(new ArrayList<>(nivelList).get(0));
                }else {
                    contactUi.setDescripcion(TextUtils.isEmpty(nombreCursos.toString())?"":nombreCursos.toString());
                }

                objectListPerson.add(contactUi);

                List<Relaciones>relacionesList= SQLite.select().from(Relaciones.class)
                        .where(Relaciones_Table.personaPrincipalId.withTable().eq(persona.getPersonaId()))
                        .queryList();

                List<ContactUi>subContactUis= new ArrayList<>();

                for(Relaciones relation:relacionesList){

                    Persona subcontact= SQLite.select().from(Persona.class)
                            .where(Persona_Table.personaId.withTable().eq(relation.getPersonaVinculadaId()))
                            .querySingle();
                    if(subcontact!=null)
                    {
                        ContactUi subContactUi= new ContactUi();
                        subContactUi.setType(ContactUi.Type.SUBCONTACT);
                        subContactUi.setTypeContact(ContactUi.TypeContact.STUDENT);
                        subContactUi.setEnable(false);
                        subContactUi.setPhoto(subcontact.getUrlPicture());
                        String nombrePadre = Utils.capitalize(Utils.getFirstWord(subcontact.getNombres())) + " " + Utils.capitalize(subcontact.getApellidoPaterno()) + " " + Utils.capitalize(subcontact.getApellidoMaterno());
                        subContactUi.setName(nombrePadre);
                        subContactUi.setOriginIdPerson(senderId);
                        subContactUi.setIdPerson(subcontact.getPersonaId());
                        subContactUi.setContactUiPrincipal(contactUi);

                        //type if relation
                        Tipos type= SQLite.select().from(Tipos.class).where(Tipos_Table.tipoId.withTable().eq(relation.getTipoId())).querySingle();
                        if(type!=null) subContactUi.setRol(type.getNombre());

                        objectListPerson.add(subContactUi);
                        subContactUis.add(subContactUi);

                    }
                }
                contactUi.setContactUiList(subContactUis);

            }
        }
        callback.onLoad(true,new ArrayList<Object>(objectListPerson) );


    }

    @Override
    public List<Object> getGroups(int personaId) {


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

        int georeferenciaId = rolGeoreferencia!= null?rolGeoreferencia.getGeoReferenciaId():0;
        int anioAcademicoId = 0;
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

        HashSet<Object>objects= new LinkedHashSet<>();
        //get academic charge
        Set<Integer> cargaCursoIdList = new LinkedHashSet<>();

        Empleado empleado= SQLite.select().from(Empleado.class).where(Empleado_Table.personaId.withTable().eq(personaId)).querySingle();
        if(empleado!=null){

            List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(CargaAcademica.class)
                    .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                            .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                    .innerJoin(AnioAcademico.class)
                    .on(CargaAcademica_Table.idAnioAcademico.withTable()
                            .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                    .where(CargaCursos_Table.empleadoId.withTable().eq(empleado.getEmpleadoId()))
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
                    .where(CargaCursoDocente_Table.docenteId.is(empleado.getEmpleadoId()))
                    .and(CargaCursos_Table.complejo.eq(1))
                    .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                    .queryList();

            cargaCursosList.addAll(cargaCursosComplejoList);

            Set<Integer> cargaAcademicaIdList = new LinkedHashSet<>();
            for (CargaCursos itemCargaCursos : cargaCursosList){
                cargaAcademicaIdList.add(itemCargaCursos.getCargaAcademicaId());
                cargaCursoIdList.add(itemCargaCursos.getCargaCursoId());
            }

            List<CargaAcademica>academicCharge= SQLite.select(Utils.f_allcolumnTable(CargaAcademica_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaAcademica.class)
                    .where(CargaAcademica_Table.cargaAcademicaId.in(cargaAcademicaIdList))
                    .queryList();

            Log.d(TAG, "academicCharge size "+ academicCharge.size());


            for(CargaAcademica academic:academicCharge){
                //objects.add("Grados");
                List<CargaCursos> cargaCursos = SQLite.select()
                        .from(CargaCursos.class)
                        .where(CargaCursos_Table.cargaAcademicaId.eq(academic.getCargaAcademicaId()))
                        .queryList();
                List<Long> docenteIdList = new ArrayList<>();
                for(CargaCursos item : cargaCursos){
                    if(item.getComplejo()==1){
                        CargaCursoDocente cargaCursoDocente = SQLite.select()
                                .from(CargaCursoDocente.class)
                                .where(CargaCursoDocente_Table.cargaCursoId.eq(item.getCargaCursoId()))
                                .querySingle();
                        if(cargaCursoDocente!=null){
                            docenteIdList.add((long)cargaCursoDocente.getDocenteId());
                        }
                    }else {
                        docenteIdList.add((long)item.getEmpleadoId());
                    }
                }

//              Random rnd = new Random();
//                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                GroupUi groupUi =new GroupUi();
                groupUi.setCargaAcademicaId(academic.getCargaAcademicaId());
                groupUi.setDocenteId(docenteIdList);
                groupUi.setIdSender(personaId);
                groupUi.setType(GroupUi.Type.ACADEMIC);
                //groupUi.setColor(String.valueOf(color));
                Seccion section=SQLite.select().from(Seccion.class).where(Seccion_Table.seccionId.withTable().eq(academic.getSeccionId())).querySingle();
                if(section!=null){
                    groupUi.setSection(section.getNombre());
                    groupUi.setIdsection(section.getSeccionId());
                }
                Periodo period=SQLite.select().from(Periodo.class).where(Periodo_Table.periodoId.withTable().eq(academic.getPeriodoId())).querySingle();
                if(period!=null){
                    groupUi.setYear(period.getAlias());
                    groupUi.setIdperiod(period.getPeriodoId());
                }


                NivelAcademico nivelAcademico = SQLite.select(Utils.f_allcolumnTable(NivelAcademico_Table.ALL_COLUMN_PROPERTIES))
                        .from(NivelAcademico.class)
                        .innerJoin(ProgramasEducativo.class)
                        .on(NivelAcademico_Table.nivelAcadId.withTable()
                                .eq(ProgramasEducativo_Table.nivelAcadId.withTable()))
                        .innerJoin(PlanEstudios.class)
                        .on(ProgramasEducativo_Table.programaEduId.withTable()
                                .eq(PlanEstudios_Table.programaEduId.withTable()))
                        .innerJoin(CargaAcademica.class)
                        .on(PlanEstudios_Table.planEstudiosId.withTable()
                                .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
                        .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(academic.getCargaAcademicaId()))
                        .querySingle();
                if(nivelAcademico!=null)groupUi.setProgramEducate(nivelAcademico.getNombre());
                groupUi.setName(groupUi.getYear()+ " "+ groupUi.getSection()+" - "+ groupUi.getProgramEducate());

                objects.add(groupUi.getName());
                Gson gson = new Gson();
                String json = gson.toJson(groupUi);

                GroupUi todos = gson.fromJson(json, GroupUi.class);
                GroupUi padres = gson.fromJson(json, GroupUi.class);
                GroupUi alumnos = gson.fromJson(json, GroupUi.class);

                todos.setGrupo(GroupUi.Grupo.Todos);
                padres.setGrupo(GroupUi.Grupo.Padre);
                alumnos.setGrupo(GroupUi.Grupo.Alumno);
                objects.add(todos);
                objects.add(padres);
                objects.add(alumnos);


            }

            //get courses


            List<CursoCustom>cursoCustoms=cursoDao.obtenerPorCargaCursos(new ArrayList<Integer>(cargaCursoIdList));

            for(CursoCustom course:cursoCustoms){
                //objects.add("Cursos");
                List<Long> docenteIdList = new ArrayList<>();
                if(course.getComplejo()==1){
                    CargaCursoDocente cargaCursoDocente = SQLite.select()
                            .from(CargaCursoDocente.class)
                            .where(CargaCursoDocente_Table.cargaCursoId.eq(course.getCargaCursoId()))
                            .querySingle();
                    if(cargaCursoDocente!=null){
                        docenteIdList.add((long)cargaCursoDocente.getDocenteId());
                    }
                }else {
                    docenteIdList.add((long)course.getEmpleadoId());
                }

                GroupUi groupUi =new GroupUi();
                groupUi.setCargaCursoId(course.getCargaCursoId());
                groupUi.setCargaAcademicaId(course.getCargaAcademicaId());
                groupUi.setDocenteId(docenteIdList);
                groupUi.setIdSender(personaId);
                groupUi.setType(GroupUi.Type.COURSE);
                ParametrosDisenio parametrosDisenio= parametrosDisenioDao.obtenerPorCargaCurso(course.getCargaCursoId());
                if(parametrosDisenio!=null){
                    groupUi.setColor(String.valueOf(parametrosDisenio.getColor1()));
                    groupUi.setPhoto(String.valueOf(parametrosDisenio.getPath()));
                }

                groupUi.setNameCourse(course.getNombre());
                Seccion section=SQLite.select().from(Seccion.class).where(Seccion_Table.seccionId.withTable().eq(course.getSeccionId())).querySingle();
                if(section!=null){
                    groupUi.setSection(section.getNombre());
                    groupUi.setIdsection(section.getSeccionId());
                }
                Periodo period=SQLite.select().from(Periodo.class).where(Periodo_Table.periodoId.withTable().eq(course.getPeriodoId())).querySingle();
                if(period!=null){
                    groupUi.setYear(period.getNombre());
                    groupUi.setIdperiod(period.getPeriodoId());
                }

                NivelAcademico nivelAcademico = SQLite.select(Utils.f_allcolumnTable(NivelAcademico_Table.ALL_COLUMN_PROPERTIES))
                        .from(NivelAcademico.class)
                        .innerJoin(ProgramasEducativo.class)
                        .on(NivelAcademico_Table.nivelAcadId.withTable()
                                .eq(ProgramasEducativo_Table.nivelAcadId.withTable()))
                        .innerJoin(PlanEstudios.class)
                        .on(ProgramasEducativo_Table.programaEduId.withTable()
                                .eq(PlanEstudios_Table.programaEduId.withTable()))
                        .innerJoin(CargaAcademica.class)
                        .on(PlanEstudios_Table.planEstudiosId.withTable()
                                .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
                        .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(course.getCargaAcademicaId()))
                        .querySingle();


                if(nivelAcademico!=null){
                    groupUi.setProgramEducate(nivelAcademico.getNombre());
                }
                groupUi.setName(groupUi.getNameCourse() +", "+ groupUi.getYear()+ " "+ groupUi.getSection()+" - "+ groupUi.getProgramEducate());

                objects.add(groupUi.getName());

                Gson gson = new Gson();
                String json = gson.toJson(groupUi);

                GroupUi todos = gson.fromJson(json, GroupUi.class);
                GroupUi padres = gson.fromJson(json, GroupUi.class);
                GroupUi alumnos = gson.fromJson(json, GroupUi.class);

                todos.setGrupo(GroupUi.Grupo.Todos);
                padres.setGrupo(GroupUi.Grupo.Padre);
                alumnos.setGrupo(GroupUi.Grupo.Alumno);
                objects.add(todos);
                objects.add(padres);
                objects.add(alumnos);

            }

            //get team for group course

            List<GrupoEquipoC>groupTeams=SQLite.select().from(GrupoEquipoC.class)
                    .where(GrupoEquipoC_Table.cargaCursoId.withTable().in(cargaCursoIdList))
                    .and(GrupoEquipoC_Table.estado.notEq(GrupoEquipoC.ELIMINADO))
                    .queryList();

            for(GrupoEquipoC team: groupTeams){
                objects.add("Equipos");
                List<Long> docenteIdList = new ArrayList<>();
                for (CursoCustom cursoCustom : cursoCustoms){
                    if(cursoCustom.getCargaCursoId()== team.getCargaCursoId()){
                        if(cursoCustom.getComplejo()==1){
                            CargaCursoDocente cargaCursoDocente = SQLite.select()
                                    .from(CargaCursoDocente.class)
                                    .where(CargaCursoDocente_Table.cargaCursoId.eq(cursoCustom.getCargaCursoId()))
                                    .querySingle();
                            if(cargaCursoDocente!=null){
                                docenteIdList.add((long)cargaCursoDocente.getDocenteId());
                            }
                        }else {
                            docenteIdList.add((long)cursoCustom.getEmpleadoId());
                        }
                        break;
                    }
                }
                if(!docenteIdList.isEmpty()){
                    GroupUi groupUi =new GroupUi();
                    groupUi.setGrupoEquipoId(team.getKey());
                    groupUi.setCargaAcademicaId(groupUi.getCargaAcademicaId());
                    groupUi.setDocenteId(docenteIdList);
                    groupUi.setIdCourse(team.getCargaCursoId());
                    groupUi.setIdSender(personaId);
                    groupUi.setType(GroupUi.Type.TEAM);
                    groupUi.setName(team.getNombre());
                    objects.add(groupUi);
                }

            }

        }
        return new ArrayList<>(objects);
    }

    @Override
    public void getListFilterGroups(int senderId, Callback<List<Object>> listCallback) {
        HashSet<Object>objects= new LinkedHashSet<>();

        Empleado empleado= SQLite.select().from(Empleado.class).where(Empleado_Table.personaId.withTable().eq(senderId)).querySingle();
        if(empleado!=null){

            objects.add("Carga académica");

            CategoryUi academic= new CategoryUi();
            academic.setName("Carga académica");
            academic.setType(CategoryUi.Type.ACADEMIC);
            objects.add(academic);
            
            //get periods

            List<Periodo>periods= SQLite.select().from(Periodo.class)
                    .innerJoin(CargaAcademica.class)
                    .on(Periodo_Table.periodoId.withTable()
                            .eq(CargaAcademica_Table.periodoId.withTable()))
                    .innerJoin(CargaCursos.class)
                    .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                            .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursos_Table.empleadoId.withTable()
                            .eq(empleado.getEmpleadoId()))
                    .groupBy(CargaCursos_Table.cargaAcademicaId.withTable())
                    .queryList();

            Log.d(TAG, "periods size "+ periods.size());

            objects.add("Por periodo");
            for(Periodo period: periods){
                CategoryUi categoryUi= new CategoryUi();
                categoryUi.setId(period.getPeriodoId());
                categoryUi.setName(period.getAlias());
                categoryUi.setType(CategoryUi.Type.PERIOD);
                objects.add(categoryUi);
            }

            //get sections

            List<Seccion>seccions= SQLite.select().from(Seccion.class)
                    .innerJoin(CargaAcademica.class)
                    .on(Seccion_Table.seccionId.withTable()
                            .eq(CargaAcademica_Table.seccionId.withTable()))
                    .innerJoin(CargaCursos.class)
                    .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                            .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursos_Table.empleadoId.withTable()
                            .eq(empleado.getEmpleadoId()))
                    .groupBy(CargaCursos_Table.cargaAcademicaId.withTable())
                    .queryList();
            Log.d(TAG, "seccions size "+ seccions.size());
            objects.add("Por Sección");
            for(Seccion section: seccions){
                CategoryUi categoryUi= new CategoryUi();
                categoryUi.setId(section.getSeccionId());
                categoryUi.setName(section.getNombre());
                categoryUi.setType(CategoryUi.Type.SECTION);
                objects.add(categoryUi);
            }

            objects.add("Carga curso");

            CategoryUi course= new CategoryUi();
            course.setName("Cursos");
            course.setType(CategoryUi.Type.COURSE);
            objects.add(course);


            objects.add("Equipos por curso ");
            List<CargaCursos>courses=SQLite.select().from(CargaCursos.class).where(CargaCursos_Table.empleadoId.withTable()
                    .eq(empleado.getEmpleadoId())).queryList();
            List<Integer>integerList= new ArrayList<>();
            for(CargaCursos c:courses)integerList.add(c.getCargaCursoId());

            List<CursoCustom>cursoCustoms=cursoDao.obtenerPorCargaCursos(integerList);
            for(CursoCustom custom: cursoCustoms){
                CategoryUi categoryUi= new CategoryUi();
                categoryUi.setId(custom.getCargaCursoId());
                categoryUi.setName(custom.getNombre());
                categoryUi.setType(CategoryUi.Type.TEAM);
                objects.add(categoryUi);
            }

        }
        listCallback.onLoad(true, new ArrayList<Object>(objects));
    }

    @Override
    public UsuarioUi getUsuarioDefault() {
        UsuarioUi usuarioUi = new UsuarioUi();
        SessionUser sessionUser = SessionUser.getCurrentUser();
        int usuarioId = sessionUser!=null?sessionUser.getUserId():0;
        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioId))
                .querySingle();
        if(usuario!=null){
            usuarioUi.setUsuarioId(usuario.getUsuarioId());
            usuarioUi.setNombreUsuario(usuario.getUsuario());

            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.eq(usuario.getPersonaId()))
                    .querySingle();

            if (persona!=null){
                usuarioUi.setPersonaId(persona.getPersonaId());
                String nombre = Utils.capitalize(persona.getFirstName()) + Utils.capitalize(persona.getApellidoPaterno()) + Utils.capitalize(persona.getApellidoMaterno());
                usuarioUi.setNombrePersona(nombre);
            }
            Empleado empleado = SQLite.select()
                    .from(Empleado.class)
                    .where(Empleado_Table.personaId.eq(persona!=null?persona.getPersonaId():0))
                    .querySingle();
            usuarioUi.setDocenteId(empleado!=null?empleado.getEmpleadoId():0);
        }

        return usuarioUi;
    }

    @Override
    public ListenerFirebase getPersonaChats(int personaId, Callback<List<ChatUi>> listCallback) {
        return null;
    }

    private List<CursoCustom> cache1 = new ArrayList<>();
    private List<CursoCustom> cache2 = new ArrayList<>();
    private List<String> mensajeEnviad = new ArrayList<>();
    @Override
    public RetrofitCancel getDatosChat(int personaId, List<ChatUi> chatUiList, Callback<List<ChatUi>> listCallback) {
        for (ChatUi chatUi : chatUiList){

            Message messageUltimo = SQLite.select()
                    .from(Message.class)
                    .where(Message_Table.chatId.eq(chatUi.getId()))
                    .and(Message_Table.estado.eq(1))
                    .and(Message_Table.id.notLike("%Notify_%"))
                    .orderBy(Message_Table.lastdate.desc())
                    .querySingle();

            Message message = SQLite.select()
                    .from(Message.class)
                    .where(Message_Table.id.eq(chatUi.getMensageId()))
                    .querySingle();

            if(message==null){
                message = new Message();
                message.setId(chatUi.getMensageId());
                message.setCargaAcademicaId(chatUi.getCargaAcademicaId());
                message.setCargaCursoId(chatUi.getCargaCursoId());
                message.setPersonaId(chatUi.getIdSender());
                message.setChatId(chatUi.getId());
                message.setMensaje(chatUi.getLastMsg());
                message.setLastdate(chatUi.getLastDate());
                message.setNombreChat(chatUi.getName());
                message.save();
            }

            long count;
            if(messageUltimo!=null){
                count = SQLite.selectCountOf()
                        .from(Message.class)
                        .where(Message_Table.lastdate.greaterThan(messageUltimo.getLastdate()))
                        .and(Message_Table.chatId.eq(chatUi.getId()))
                        .and(Message_Table.estado.notEq(1))
                        .and(Message_Table.id.notLike("%Notify_%"))
                        .count();
            }else {
                Calendar hoy = Calendar.getInstance();
                int anioHoy = hoy.get(Calendar.YEAR);
                int mesHoy = hoy.get(Calendar.MONTH);
                int diaHoy = hoy.get(Calendar.DAY_OF_MONTH);

                Calendar fecha = Calendar.getInstance();
                fecha.setTime(chatUi.getLastDate());
                int anio = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);

                if(anioHoy == anio && mesHoy == mes && diaHoy == dia){
                    count = SQLite.selectCountOf()
                            .from(Message.class)
                            .where(Message_Table.chatId.eq(chatUi.getId()))
                            .and(Message_Table.estado.notEq(1))
                            .and(Message_Table.id.notLike("%Notify_%"))
                            .count();
                }else {
                    count = 0;
                }

            }


            chatUi.setCount(count);


            if(TextUtils.isEmpty(chatUi.getSalaTipo())){
                int personaExternaId = 0;
                if(chatUi.getIdReceiver()!=personaId){
                    personaExternaId = chatUi.getIdReceiver();
                }else {
                    personaExternaId = chatUi.getIdSender();
                }

                Persona persona = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.eq(personaExternaId))
                        .querySingle();

                Directivos directivos = SQLite.select()
                        .from(Directivos.class)
                        .where(Directivos_Table.personaId.eq(personaExternaId))
                        .querySingle();

                if(persona!=null){
                    String nombre = Utils.capitalize(persona.getFirstName())+ " "+Utils.capitalize(persona.getApellidoPaterno())+" "+Utils.capitalize(persona.getApellidoMaterno());
                    chatUi.setName(nombre);
                    chatUi.setImageRec(persona.getFoto());
                }else if(directivos!=null){
                    String nombre = Utils.capitalize(Utils.getFirstWord(directivos.getNombre()))+ " "+Utils.capitalize(directivos.getApellidoPaterno())+" "+Utils.capitalize(directivos.getApellidoMaterno());
                    chatUi.setName(nombre);
                    chatUi.setImageRec(directivos.getFoto());
                }else if(TextUtils.isEmpty(chatUi.getName())){
                    chatUi.setName("Desconocido");
                }

                chatUi.setTypeChat(ChatUi.TypeChat.PERSONAL);
            }else {
                int cargaCursoId = 0;
                int cargaAcademicaId = 0;
                String grupoEquipoId = "";

                if (chatUi.getSalaTipo().equals(TipoSalaEnum.CURSO_GENERAL.getNombre())) {
                    cargaCursoId = chatUi.getCargaCursoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.TEACHERS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.COURSE);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.CURSO_PADRES.getNombre())) {
                    cargaCursoId = chatUi.getCargaCursoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.PARENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.COURSE);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.CURSO_ALUMNOS.getNombre())) {
                    cargaCursoId = chatUi.getCargaCursoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.STUDENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.COURSE);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.SALON_GENERAL.getNombre())) {
                    cargaAcademicaId = chatUi.getCargaAcademicaId();
                    chatUi.setTypePerson(ChatUi.TypePerson.TEACHERS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.ACADEMIC);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.SALON_PADRES.getNombre())) {
                    cargaAcademicaId = chatUi.getCargaAcademicaId();
                    chatUi.setTypePerson(ChatUi.TypePerson.PARENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.ACADEMIC);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.SALON_ALUMNOS.getNombre())) {
                    cargaAcademicaId = chatUi.getCargaAcademicaId();
                    chatUi.setTypePerson(ChatUi.TypePerson.STUDENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.ACADEMIC);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.LISTA_GENERAL.getNombre())) {
                    grupoEquipoId = chatUi.getGrupoEquipoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.TEACHERS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.TEAM);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.LISTA_PADRES.getNombre())) {
                    grupoEquipoId = chatUi.getGrupoEquipoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.PARENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.TEAM);
                } else if (chatUi.getSalaTipo().equals(TipoSalaEnum.LISTA_ALUMNOS.getNombre())) {
                    grupoEquipoId = chatUi.getGrupoEquipoId();
                    chatUi.setTypePerson(ChatUi.TypePerson.STUDENTS);
                    chatUi.setTypeGroup(ChatUi.TypeGroup.TEAM);
                }

                chatUi.setTypeChat(ChatUi.TypeChat.GROUP);
                if(TextUtils.isEmpty(chatUi.getName())) chatUi.setName("Desconocido");
                if (cargaCursoId != 0) {
                    CursoCustom cursoCustom = null;
                    for (CursoCustom item: cache1){
                       if(item.getCargaCursoId()==cargaCursoId){
                           cursoCustom = item;
                           break;
                       }
                   }
                   if(cursoCustom==null){
                       cursoCustom = SQLite.select(
                               Cursos_Table.cursoId.withTable(),
                               Cursos_Table.alias.withTable(),
                               Cursos_Table.entidadId.withTable(),
                               Cursos_Table.estadoId.withTable(),
                               Cursos_Table.nivelAcadId.withTable(),
                               Cursos_Table.nombre.withTable(),
                               Cursos_Table.tipoCursoId.withTable(),
                               CargaCursos_Table.cargaCursoId.withTable(),
                               CargaAcademica_Table.cargaAcademicaId.withTable(),
                               CargaAcademica_Table.seccionId.withTable(),
                               CargaAcademica_Table.periodoId.withTable(),
                               CargaAcademica_Table.aulaId.withTable(),
                               CargaAcademica_Table.idPlanEstudio.withTable(),
                               Seccion_Table.nombre.withTable().as("seccion"),
                               Periodo_Table.alias.withTable().as("periodo"),
                               NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                               .from(CargaCursos.class)
                               .innerJoin(CargaAcademica.class)
                               .on(CargaCursos_Table.cargaAcademicaId.withTable().eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                               .innerJoin(Seccion.class)
                               .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                               .innerJoin(Periodo.class)
                               .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                               .innerJoin(PlanEstudios.class)
                               .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                               .innerJoin(ProgramasEducativo.class)
                               .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                               .innerJoin(NivelAcademico.class)
                               .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                               .innerJoin(PlanCursos.class)
                               .on(CargaCursos_Table.planCursoId.withTable().eq(PlanCursos_Table.planCursoId.withTable()))
                               .innerJoin(Cursos.class)
                               .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                               .where(CargaCursos_Table.cargaCursoId.withTable().eq(cargaCursoId))
                               .queryCustomSingle(CursoCustom.class);

                       if(cursoCustom!=null)cache1.add(cursoCustom);
                   }


                    chatUi.setName(cursoCustom != null ? cursoCustom.getNombre() + " " + cursoCustom.getPeriodo() + " " + cursoCustom.getSeccion() + " - " + cursoCustom.getNivelAcademico() : "");
                    ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
                    if (parametrosDisenio != null){
                        chatUi.setColor(String.valueOf(parametrosDisenio.getColor1()));
                        chatUi.setImageRec(parametrosDisenio.getPath());

                    }
                }else if(cargaAcademicaId!=0){
                    CursoCustom cargaAcademicaCustom = null;
                    for (CursoCustom item: cache2){
                        if(item.getCargaAcademicaId()==cargaAcademicaId){
                            cargaAcademicaCustom = item;
                            break;
                        }
                    }
                    if(cargaAcademicaCustom==null){
                        cargaAcademicaCustom
                                = SQLite.select(
                                CargaAcademica_Table.cargaAcademicaId.withTable(),
                                CargaAcademica_Table.seccionId.withTable(),
                                CargaAcademica_Table.periodoId.withTable(),
                                CargaAcademica_Table.aulaId.withTable(),
                                CargaAcademica_Table.idPlanEstudio.withTable(),
                                Seccion_Table.nombre.withTable().as("seccion"),
                                Periodo_Table.alias.withTable().as("periodo"),
                                NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                                .from(CargaAcademica.class)
                                .innerJoin(Seccion.class)
                                .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                                .innerJoin(Periodo.class)
                                .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                                .innerJoin(PlanEstudios.class)
                                .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                                .innerJoin(ProgramasEducativo.class)
                                .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                                .innerJoin(NivelAcademico.class)
                                .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                                .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId))
                                .queryCustomSingle(CursoCustom.class);
                        if(cargaAcademicaCustom!=null)cache2.add(cargaAcademicaCustom);
                    }

                    if(cargaAcademicaCustom!=null&&
                            !TextUtils.isEmpty(cargaAcademicaCustom.getSeccion())){
                        String j="";
                        for(int i=0;cargaAcademicaCustom.getSeccion().length()>i; i++){
                            j=j+cargaAcademicaCustom.getSeccion().charAt(i);
                            if(i==2)break;
                        }
                        chatUi.setImageRec(j);
                    }


                    chatUi.setName(cargaAcademicaCustom!=null?cargaAcademicaCustom.getPeriodo()+" "+cargaAcademicaCustom.getSeccion()+" - "+cargaAcademicaCustom.getNivelAcademico():"");

                }else if(!TextUtils.isEmpty(grupoEquipoId)){
                    GrupoEquipoC grupoEquipoC = SQLite.select()
                            .from(GrupoEquipoC.class)
                            .where(GrupoEquipoC_Table.key.eq(grupoEquipoId))
                            .querySingle();

                    CursoCustom cursoCustomGrupo = null;
                    for (CursoCustom item: cache1){
                        if(item.getCargaCursoId()==(grupoEquipoC!=null?grupoEquipoC.getCargaCursoId():0)){
                            cursoCustomGrupo = item;
                            break;
                        }
                    }

                    if(cursoCustomGrupo==null){
                        cursoCustomGrupo = SQLite.select(
                                Cursos_Table.cursoId.withTable(),
                                Cursos_Table.alias.withTable(),
                                Cursos_Table.entidadId.withTable(),
                                Cursos_Table.estadoId.withTable(),
                                Cursos_Table.nivelAcadId.withTable(),
                                Cursos_Table.nombre.withTable(),
                                Cursos_Table.tipoCursoId.withTable(),
                                CargaCursos_Table.cargaCursoId.withTable(),
                                CargaAcademica_Table.cargaAcademicaId.withTable(),
                                CargaAcademica_Table.seccionId.withTable(),
                                CargaAcademica_Table.periodoId.withTable(),
                                CargaAcademica_Table.aulaId.withTable(),
                                CargaAcademica_Table.idPlanEstudio.withTable(),
                                Seccion_Table.nombre.withTable().as("seccion"),
                                Periodo_Table.alias.withTable().as("periodo"),
                                NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                                .from(CargaCursos.class)
                                .innerJoin(CargaAcademica.class)
                                .on(CargaCursos_Table.cargaAcademicaId.withTable().eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                                .innerJoin(Seccion.class)
                                .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                                .innerJoin(Periodo.class)
                                .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                                .innerJoin(PlanEstudios.class)
                                .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                                .innerJoin(ProgramasEducativo.class)
                                .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                                .innerJoin(NivelAcademico.class)
                                .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                                .innerJoin(PlanCursos.class)
                                .on(CargaCursos_Table.planCursoId.withTable().eq(PlanCursos_Table.planCursoId.withTable()))
                                .innerJoin(Cursos.class)
                                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                                .where(CargaCursos_Table.cargaCursoId.withTable().eq(grupoEquipoC!=null?grupoEquipoC.getCargaCursoId():0))
                                .queryCustomSingle(CursoCustom.class);
                        if(cursoCustomGrupo!=null)cache1.add(cursoCustomGrupo);
                    }

                    String nombre = grupoEquipoC!=null?grupoEquipoC.getNombre():"";
                    String descripcion = cursoCustomGrupo!=null?cursoCustomGrupo.getNombre()+" "+cursoCustomGrupo.getPeriodo()+" "+cursoCustomGrupo.getSeccion()+" - "+cursoCustomGrupo.getNivelAcademico():"";
                    chatUi.setName(nombre);
                    chatUi.setDescripcion(descripcion);
                }

            }
        }
        listCallback.onLoad(true, chatUiList);
        return null;
    }

    @Override
    public ListenerFirebase getGrupoChats(int docenteId, int personaId, Callback<List<ChatUi>> listCallback) {
        return null;
    }

    @Override
    public RetrofitCancel sincronizarInformacion(SuccessCallback callBack) {
        return null;
    }

    @Override
    public void getSenderInformation(int idSender, Callback<UserUi> chatUiCallback) {

    }

    @Override
    public void getChats(int senderId, int type, Callback<List<ChatUi>> callback) {

    }

    @Override
    public void getChatsGroups(int senderId, Callback<List<ChatUi>> callback) {

    }


}
