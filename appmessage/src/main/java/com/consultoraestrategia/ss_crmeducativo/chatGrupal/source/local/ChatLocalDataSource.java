package com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
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
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatLocalDataSource implements ChatDataSource {
    private static final String TAG = "ChatLocalDataSourceTAG";
    private ParametrosDisenioDao parametrosDisenioDao;

    public ChatLocalDataSource(ParametrosDisenioDao parametrosDisenioDao) {
        this.parametrosDisenioDao = parametrosDisenioDao;
    }

    @Override
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {

    }

    @Override
    public PersonaUi getPersona(int personaId) {
        PersonaUi personaUi = new PersonaUi();
        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(personaId))
                .querySingle();

        if(persona!=null){
            personaUi.setId(persona.getPersonaId());
            personaUi.setNombre(Utils.capitalize(persona.getFirstName()));
            personaUi.setFoto(persona.getFoto());
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
    public ListenerFirebase getListaMessage(String salaId, int personaId, ListaMessageCallback callback) {
        return null;
    }

    @Override
    public void getListlastMessage(String salaId, int personaId, Date date, Callback<List<Object>> callback) {

    }


    @Override
    public void saveKeyPress(int person, int personaExterna) {

    }

    @Override
    public ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback) {
        return null;
    }

    @Override
    public SalaUi getSala(int cargaAcademicaId, int cargaCursoId, String grupoEquipoId, TipoSalaEnum tipo) {
        String nombre = "";
        String alias = "";
        String descripcion = "";
        String color = "";
        switch (tipo){
            case CURSO_GENERAL:
            case CURSO_PADRES:
            case CURSO_ALUMNOS:

                CursoCustom cursoCustom = SQLite.select(
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

                Log.d(TAG, "cursoCustom: "+cargaCursoId );
                if(cursoCustom!=null){
                    StringBuilder j= new StringBuilder();
                    for(int i=0;cursoCustom.getNombre().length()>i; i++){
                        j.append(cursoCustom.getNombre().charAt(i));
                        if(i==2)break;
                    }
                    alias = j.toString();
                }

                nombre = cursoCustom!=null?cursoCustom.getNombre():"";
                descripcion = cursoCustom!=null?cursoCustom.getPeriodo()+" "+cursoCustom.getSeccion()+" - "+cursoCustom.getNivelAcademico():"";
                ParametrosDisenio parametrosDisenio= parametrosDisenioDao.obtenerPorCargaCurso(cursoCustom!=null?cursoCustom.getCargaCursoId():0);
                color = parametrosDisenio!=null?parametrosDisenio.getColor1():"";

                break;
            case SALON_GENERAL:
            case SALON_PADRES:
            case SALON_ALUMNOS:

                CursoCustom cargaAcademicaCustom
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

                if(cargaAcademicaCustom!=null){
                    String j = "";
                    for(int i=0;cargaAcademicaCustom.getSeccion().length()>i; i++){
                        j=j+cargaAcademicaCustom.getSeccion().charAt(i);
                        if(i==2)break;
                    }
                    alias = j;
                }
                nombre = cargaAcademicaCustom!=null?cargaAcademicaCustom.getPeriodo()+" "+cargaAcademicaCustom.getSeccion()+" - "+cargaAcademicaCustom.getNivelAcademico():"";


                break;
            case LISTA_GENERAL:
            case LISTA_PADRES:
            case LISTA_ALUMNOS:
                GrupoEquipoC grupoEquipoC = SQLite.select()
                        .from(GrupoEquipoC.class)
                        .where(GrupoEquipoC_Table.key.eq(grupoEquipoId))
                        .querySingle();

                CursoCustom cursoCustomGrupo = SQLite.select(
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

                nombre = grupoEquipoC!=null?grupoEquipoC.getNombre():"";
                descripcion = cursoCustomGrupo!=null?cursoCustomGrupo.getNombre()+" "+cursoCustomGrupo.getPeriodo()+" "+cursoCustomGrupo.getSeccion()+" - "+cursoCustomGrupo.getNivelAcademico():"";
                break;
        }

        SalaUi salaUi = new SalaUi();
        salaUi.setNombre(nombre);
        salaUi.setAlias(alias);
        salaUi.setDescripcion(descripcion);
        salaUi.setColor(color);
        salaUi.setTipoSala(tipo);
        return salaUi;
    }

    @Override
    public PersonaUi getPersonaChatMessage(int emisorId) {
        Log.d(TAG,"emisorId: "+emisorId);
        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(emisorId))
                .querySingle();

        if(persona!=null){
            PersonaUi personaUi = new PersonaUi();
            personaUi.setId(persona.getPersonaId());
            String nombre = Utils.capitalize(persona.getFirstName()) + " " + Utils.capitalize(persona.getApellidoPaterno());
            personaUi.setNombre(nombre);
            personaUi.setFoto(persona.getFoto());
            return personaUi;
        }else {
            return null;
        }
    }

    @Override
    public RetrofitCancel sendNotificacion(MessageUi2 messageUi2, List<String> tokens, MessageCallback messageCallback) {
        return null;
    }

    private List<CursoCustom> cache1 = new ArrayList<>();
    private List<CursoCustom> cache2 = new ArrayList<>();
    @Override
    public void getDatosChat(MessageUi2 messageUi2) {
        int cargaCursoId = 0;
        int cargaAcademicaId = 0;
        String grupoEquipoId = "";

        if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.CURSO_GENERAL.getNombre())) {
            cargaCursoId = messageUi2.getCargaCursoId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.CURSO_PADRES.getNombre())) {
            cargaCursoId = messageUi2.getCargaCursoId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.CURSO_ALUMNOS.getNombre())) {
            cargaCursoId = messageUi2.getCargaCursoId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.SALON_GENERAL.getNombre())) {
            cargaAcademicaId = messageUi2.getCargaAcademicaId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.SALON_PADRES.getNombre())) {
            cargaAcademicaId = messageUi2.getCargaAcademicaId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.SALON_ALUMNOS.getNombre())) {
            cargaAcademicaId = messageUi2.getCargaAcademicaId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.LISTA_GENERAL.getNombre())) {
            grupoEquipoId = messageUi2.getGrupoEquipoId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.LISTA_PADRES.getNombre())) {
            grupoEquipoId = messageUi2.getGrupoEquipoId();
        } else if (messageUi2.getSalaTipo().equals(com.consultoraestrategia.ss_crmeducativo.chats.entities.TipoSalaEnum.LISTA_ALUMNOS.getNombre())) {
            grupoEquipoId = messageUi2.getGrupoEquipoId();
        }

        messageUi2.setNombreGrupo("Desconocido");
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

            messageUi2.setNombreGrupo(cursoCustom != null ? cursoCustom.getNombre() + " " + cursoCustom.getPeriodo() + " " + cursoCustom.getSeccion() + " - " + cursoCustom.getNivelAcademico() : "");
            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
            if (parametrosDisenio != null) messageUi2.setColorGrupo(String.valueOf(parametrosDisenio.getColor1()));
            StringBuilder j = new StringBuilder();
            if(!TextUtils.isEmpty(messageUi2.getNombreGrupo())){
                for(int i=0;messageUi2.getNombreGrupo().length()>i; i++){
                    j.append(messageUi2.getNombreGrupo().charAt(i));
                    if(i==2)break;
                }
            }
            messageUi2.setAliasGrupo(j.toString());

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
                StringBuilder j= new StringBuilder();
                for(int i=0;cargaAcademicaCustom.getSeccion().length()>i; i++){
                    j.append(cargaAcademicaCustom.getSeccion().charAt(i));
                    if(i==2)break;
                }
                messageUi2.setAliasGrupo(j.toString());
            }


            messageUi2.setNombreGrupo(cargaAcademicaCustom!=null?cargaAcademicaCustom.getPeriodo()+" "+cargaAcademicaCustom.getSeccion()+" - "+cargaAcademicaCustom.getNivelAcademico():"");

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
            messageUi2.setNombreGrupo(nombre+" "+descripcion);
        }

    }

    @Override
    public void getTokensSala(String salaId, Callback<List<String>> callback) {

    }

    @Override
    public void updateTaken(String salaId, String personaId) {

    }

    @Override
    public void getSalaIntegrantes(String salaId, Callback<List<PersonaUi>> listCallback) {

    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback messageImageCallback) {

    }


}
