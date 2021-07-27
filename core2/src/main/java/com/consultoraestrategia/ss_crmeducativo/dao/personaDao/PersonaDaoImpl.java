package com.consultoraestrategia.ss_crmeducativo.dao.personaDao;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dbflowCompat.DbflowCompat;
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
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by @stevecampos on 26/04/2018.
 */

public class PersonaDaoImpl extends BaseIntegerDaoImpl<Persona, Persona_Table> implements PersonaDao {

    private static PersonaDao mInstance;

    private PersonaDaoImpl() {
    }

    public static PersonaDao getInstance() {
        if (mInstance == null) {
            mInstance = new PersonaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Persona> getEntityClass() {
        return Persona.class;
    }

    @Override
    protected Class<Persona_Table> getTableclass() {
        return Persona_Table.class;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Persona_Table.personaId;
    }


    @Override
    public List<Persona> getPersonas(int usuarioId) {

        List<Persona> personaList = new ArrayList<>();

        Where<Persona> where = SQLite.select(
                Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(Usuario.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                        .where(Usuario_Table.usuarioId.eq(usuarioId));

        personaList = where.queryList();
        return personaList;
    }

    @Override
    public List<PersonaContratoQuery> getAlumnosDeCargaCurso(int cargaCursoId) {
        return searchAlumnosDeCargaCurso(true, false, null,cargaCursoId, null);
    }

    @Override
    public List<PersonaContratoQuery> getAlumnosDeCargaCurso(int cargaCursoId, DatabaseWrapper databaseWrapper) {
        return searchAlumnosDeCargaCurso(true, false, null,cargaCursoId,databaseWrapper);
    }

    @Override
    public List<PersonaContratoQuery> searchAlumnosDeCargaCurso(boolean orderByNombre, boolean orderByApellido, String persona, int cargaCursoId, DatabaseWrapper databaseWrapper) {


        List<PersonaContratoQuery> personasFiltro = new ArrayList<>();
        List<PersonaContratoQuery> personaList = new ArrayList<>();
        Log.d(getClass().getSimpleName(), "cargaCursoId "+ cargaCursoId);

        try {
            IProperty[] propertyList;
            Where<CargaCursos> cargaCursosWhere = SQLite.select()
                    .from(CargaCursos.class)
                    .where(CargaCursos_Table.cargaCursoId.eq(cargaCursoId));

            CargaCursos cargaCursos  =  databaseWrapper!= null ? cargaCursosWhere.querySingle(databaseWrapper): cargaCursosWhere.querySingle();

            if(cargaCursos.getComplejo()== 0){
                propertyList = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                Where<Persona> where = SQLite.select(propertyList)
                        .from(Persona.class)
                        .innerJoin(Contrato.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(Contrato_Table.personaId.withTable()))
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursoId));
                // .and(Contrato_Table.vigente.withTable().eq(1));

                if(orderByNombre){
                    where.orderBy(Persona_Table.nombres.withTable().asc());
                }else if(orderByApellido){
                    where.orderBy(Persona_Table.apellidoPaterno.withTable().asc());
                }
                where.groupBy(propertyList);
                personaList = databaseWrapper!=null ? new DbflowCompat(where).queryCustomList(PersonaContratoQuery.class,databaseWrapper) : where.queryCustomList((PersonaContratoQuery.class));
                Log.d(getClass().getSimpleName(), " size personas list "+personaList.size() );
            }else {
                Empleado empleado = SQLite.select()
                        .from(Empleado.class)
                        .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                        .querySingle();
                if(empleado!=null){
                    propertyList = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                    Where<Persona> where = SQLite.select(propertyList)
                            .from(Persona.class)
                            .innerJoin(CargaCursoDocenteDet.class)
                            .on(Persona_Table.personaId.withTable().eq(CargaCursoDocenteDet_Table.alumnoId.withTable()))
                            .innerJoin(CargaCursoDocente.class)
                            .on(CargaCursoDocenteDet_Table.cargaCursoDocenteId.withTable().eq(CargaCursoDocente_Table.cargaCursoDocenteId.withTable()))
                            .innerJoin(Contrato.class)
                            .on(Contrato_Table.personaId.withTable().eq(Persona_Table.personaId.withTable()))
                            .innerJoin(DetalleContratoAcad.class)
                            .on(Contrato_Table.idContrato.withTable()
                                    .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(DetalleContratoAcad_Table.cargaCursoId.withTable()
                                    .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                            //  .and(Contrato_Table.vigente.withTable().eq(1))
                            .and(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()));
                    if(orderByNombre){
                        where.orderBy(Persona_Table.apellidoPaterno.withTable().asc());
                    }else if(orderByApellido){
                        where.orderBy(Persona_Table.nombres.withTable().asc());
                    }
                    where.groupBy(propertyList);

                    personaList =  databaseWrapper!=null ? new DbflowCompat(where).queryCustomList(PersonaContratoQuery.class, databaseWrapper) : where.queryCustomList(PersonaContratoQuery.class);
                }

            }

            if(TextUtils.isEmpty(persona))return personaList;


            for (PersonaContratoQuery itemPersona : personaList){

                if(!TextUtils.isEmpty(itemPersona.getNombres())){
                    Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getNombres().toLowerCase()+" = "+persona);
                    if(itemPersona.getNombres().toLowerCase().contains(persona)){
                        personasFiltro.add(itemPersona);
                    }
                }

                if(!TextUtils.isEmpty(itemPersona.getApellidoPaterno())){
                    String apellidos = capitalize(itemPersona.getApellidoPaterno()) + " " + capitalize(itemPersona.getApellidoMaterno());
                    Log.d(getTableclass().getSimpleName(), "Filtro :"+apellidos.toLowerCase()+" = "+persona);
                    if(apellidos.toLowerCase().contains(persona.toLowerCase().trim())){
                        int posicion = personasFiltro.indexOf(itemPersona);
                        if(posicion == -1) personasFiltro.add(itemPersona);
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            personasFiltro.clear();
            personasFiltro.addAll(personaList);
        }finally {
        }

        return personasFiltro;


    }

    @Override
    public List<PersonaContratoQuery> getAlumnosDeRubro(boolean orderByNombre, boolean orderByApellido, String persona, String rubroEvaluacionId, int cargaCursoId) {
        List<PersonaContratoQuery> personaContratoList = new ArrayList<>();
        Where<Persona> personaWhere = SQLite.select(Utils.f_allcolumnTable(Persona_Table.personaId.withTable()))
                .from(Persona.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(EvaluacionProcesoC_Table.alumnoId.withTable()
                        .eq(Persona_Table.personaId.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionId));



        CargaCursos cargaCursos = SQLite.select()
                .from(CargaCursos.class)
                .where(CargaCursos_Table.cargaCursoId.eq(cargaCursoId))
                .querySingle();
        IProperty[] iProperties;
        if(cargaCursos!=null){
            if(cargaCursos.getComplejo()== 0){
                iProperties = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                personaContratoList = SQLite.select(iProperties)
                        .from(Persona.class)
                        .innerJoin(Contrato.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(Contrato_Table.personaId.withTable()))
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursoId))
                        .and(Persona_Table.personaId.withTable().in(personaWhere))
                        .groupBy(iProperties)
                        .queryCustomList(PersonaContratoQuery.class);
            }else {
                Empleado empleado = SQLite.select()
                        .from(Empleado.class)
                        .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                        .querySingle();
                if(empleado!=null){
                    iProperties = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                    personaContratoList = SQLite.select(iProperties)
                            .from(Persona.class)
                            .innerJoin(CargaCursoDocenteDet.class)
                            .on(Persona_Table.personaId.withTable().eq(CargaCursoDocenteDet_Table.alumnoId.withTable()))
                            .innerJoin(CargaCursoDocente.class)
                            .on(CargaCursoDocenteDet_Table.cargaCursoDocenteId.withTable().eq(CargaCursoDocente_Table.cargaCursoDocenteId.withTable()))
                            .innerJoin(Contrato.class)
                            .on(Persona_Table.personaId.withTable()
                                    .eq(Contrato_Table.personaId.withTable()))
                            .innerJoin(DetalleContratoAcad.class)
                            .on(Contrato_Table.idContrato.withTable()
                                    .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(DetalleContratoAcad_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                            .and(Persona_Table.personaId.withTable().in(personaWhere))
                            .groupBy(iProperties)
                            .queryCustomList(PersonaContratoQuery.class);
                }
            }


            List<Integer> integerList = new ArrayList<>();
            for(PersonaContratoQuery personaContratoQuery : personaContratoList)integerList.add(personaContratoQuery.getPersonaId());
            personaContratoList.addAll(
                    SQLite.select()
                    .from(Persona.class)
                    .innerJoin(EvaluacionProcesoC.class)
                    .on(EvaluacionProcesoC_Table.alumnoId.withTable()
                            .eq(Persona_Table.personaId.withTable()))
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionId))
                    .and(Persona_Table.personaId.notIn(integerList))
                    .queryCustomList(PersonaContratoQuery.class)
            );


        }


        if(orderByNombre){
            //Clase anónima
            Collections.sort(personaContratoList, new Comparator<PersonaContratoQuery>() {
                @Override
                public int compare(PersonaContratoQuery o1, PersonaContratoQuery o2) {
                    int result = 0;
                    try {
                        result = o1.getNombres().toLowerCase().compareTo(o2.getNombres().toLowerCase());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return result;
                }
            });


        }else if(orderByApellido){
            //Clase anónima
            Collections.sort(personaContratoList, new Comparator<PersonaContratoQuery>() {
                @Override
                public int compare(PersonaContratoQuery o1, PersonaContratoQuery o2) {
                    int result = 0;
                    try {
                        String apellidosUno = capitalize(o1.getApellidoPaterno()) + " " + capitalize(o1.getApellidoMaterno());
                        String apellidosDos = capitalize(o2.getApellidoPaterno()) + " " + capitalize(o2.getApellidoMaterno());

                        result = apellidosUno.toLowerCase().compareTo(apellidosDos.toLowerCase());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return result;
                }
            });

        }

        if(TextUtils.isEmpty(persona))return personaContratoList;

        List<PersonaContratoQuery> personasFiltro = new ArrayList<>();
        for (PersonaContratoQuery itemPersona : personaContratoList){

            if(!TextUtils.isEmpty(itemPersona.getNombres())){
                Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getNombres().toLowerCase()+" = "+persona);
                if(itemPersona.getNombres().toLowerCase().contains(persona)){
                    personasFiltro.add(itemPersona);
                }
            }

            if(!TextUtils.isEmpty(itemPersona.getApellidoPaterno())){
                String apellidos= capitalize(itemPersona.getApellidoPaterno()) + " " + capitalize(itemPersona.getApellidoMaterno());
                Log.d(getTableclass().getSimpleName(), "Filtro :"+apellidos.toLowerCase()+" = "+persona);
                if(apellidos.toLowerCase().contains(persona.toLowerCase().trim())){
                    int posicion = personasFiltro.indexOf(itemPersona);
                    if(posicion == -1) personasFiltro.add(itemPersona);
                }
            }
        }

        return personasFiltro;
    }



    @Override
    public List<Persona> getHijos(int personaPadre) {
        return SQLite.select()
                .from(Persona.class)
                .innerJoin(Relaciones.class)
                .on(Relaciones_Table.personaPrincipalId.withTable().eq(Persona_Table.personaId.withTable()))
                .where(Relaciones_Table.personaVinculadaId.withTable().is(personaPadre))
                .queryList();
    }

    @Override
    public List<PersonaContratoQuery> getAlumnosDeRubroEquipo(List<String> rubroEquipoKeyList, int cargaCursoId) {
        List<PersonaContratoQuery> personaContratoList = new ArrayList<>();

        Where<Persona> personaWhere = SQLite.select(Persona_Table.personaId.withTable())
                .from(Persona.class)
                .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.personaId.withTable()))
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(rubroEquipoKeyList));

        CargaCursos cargaCursos = SQLite.select()
                .from(CargaCursos.class)
                .where(CargaCursos_Table.cargaCursoId.eq(cargaCursoId))
                .querySingle();
        IProperty[] iProperties;
        if(cargaCursos!=null){
            if(cargaCursos.getComplejo()== 0){
                iProperties = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                personaContratoList = SQLite.select(iProperties)
                        .from(Persona.class)
                        .innerJoin(Contrato.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(Contrato_Table.personaId.withTable()))
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursoId))
                        .and(Persona_Table.personaId.withTable().in(personaWhere))
                        .groupBy(iProperties)
                        .queryCustomList(PersonaContratoQuery.class);
            }else {
                Empleado empleado = SQLite.select()
                        .from(Empleado.class)
                        .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                        .querySingle();
                if(empleado!=null){
                    iProperties = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable());
                    personaContratoList = SQLite.select(iProperties)
                            .from(Persona.class)
                            .innerJoin(CargaCursoDocenteDet.class)
                            .on(Persona_Table.personaId.withTable().eq(CargaCursoDocenteDet_Table.alumnoId.withTable()))
                            .innerJoin(CargaCursoDocente.class)
                            .on(CargaCursoDocenteDet_Table.cargaCursoDocenteId.withTable().eq(CargaCursoDocente_Table.cargaCursoDocenteId.withTable()))
                            .innerJoin(Contrato.class)
                            .on(Persona_Table.personaId.withTable()
                                    .eq(Contrato_Table.personaId.withTable()))
                            .innerJoin(DetalleContratoAcad.class)
                            .on(Contrato_Table.idContrato.withTable()
                                    .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(DetalleContratoAcad_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                            .and(Persona_Table.personaId.withTable().in(personaWhere))
                            .groupBy(iProperties)
                            .queryCustomList(PersonaContratoQuery.class);
                }
            }
        }

        return  personaContratoList;

    }

    @Override
    public PersonaContratoQuery getPersonContrato(int personaId, int cargaCursosId) {
        PersonaContratoQuery persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(personaId))
                .queryCustomSingle(PersonaContratoQuery.class);

        Contrato contrato = SQLite.select()
                .from(Contrato.class)
                .innerJoin(DetalleContratoAcad.class)
                .on(Contrato_Table.idContrato.withTable()
                        .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursosId))
                .and(Contrato_Table.personaId.withTable().eq(personaId))
                .querySingle();

        if(persona!=null&&contrato!=null){
            persona.setIdContrato(contrato.getIdContrato());
            persona.setVigente(contrato.getVigente());
        }

        return persona;
    }
}
