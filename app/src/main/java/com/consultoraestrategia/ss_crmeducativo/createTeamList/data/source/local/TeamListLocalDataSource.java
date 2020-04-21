package com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.equipo.EquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo.GrupoDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo.IntegranteDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public class TeamListLocalDataSource implements TeamListDataSource {

    private static final String TAG = TeamListLocalDataSource.class.getSimpleName();

    private GrupoDeEquipoDao grupoDeEquipoDao;
    private EquipoDao equipoDao;
    private IntegranteDeEquipoDao integranteDeEquipoDao;
    private PersonaDao personaDao;
    private DimensionObservadaDao dimensionObservadaDao;

    public TeamListLocalDataSource(GrupoDeEquipoDao grupoDeEquipoDao, EquipoDao equipoDao, IntegranteDeEquipoDao integranteDeEquipoDao, PersonaDao personaDao, DimensionObservadaDao dimensionObservadaDao) {
        this.grupoDeEquipoDao = grupoDeEquipoDao;
        this.equipoDao = equipoDao;
        this.integranteDeEquipoDao = integranteDeEquipoDao;
        this.personaDao = personaDao;
        this.dimensionObservadaDao = dimensionObservadaDao;
    }

    private List<PersonaContratoQuery> getAlumnosVigentes(List<PersonaContratoQuery> alumnosDeCargaCurso){
        List<PersonaContratoQuery> alumnosDeCargaCursoVigentes = new ArrayList<>();
        for(PersonaContratoQuery personaContratoQuery: alumnosDeCargaCurso){
            if(personaContratoQuery.getVigente()==1)alumnosDeCargaCursoVigentes.add(personaContratoQuery);
        }
        return alumnosDeCargaCursoVigentes;
    }
    @Override
    public void createGroup(Group group, final CreateGroupCallback callback) {
        Log.d(TAG, "createGroup");
        String grupoEquipoId = group.getId();
        int cargaCursoId = Integer.parseInt(group.getCargaCursoId());
        Log.d(TAG, "createGroup grupoEquipoId: " + grupoEquipoId);
        Log.d(TAG, "createGroup cargaCursoId: " + cargaCursoId);

        List<PersonaContratoQuery> alumnosDeCargaCursoVigentes = getAlumnosVigentes(personaDao.getAlumnosDeCargaCurso(cargaCursoId));

        int cantidad = 0;
        try {
            for (Team team : group.getTeams()) {
                cantidad += team.getPersonList().size();
            }
        } catch (Exception e) {
            cantidad = 0;
        }

        if (alumnosDeCargaCursoVigentes.size() == 0) {
            //throw new Error("No hay alumnos en esta carga curso.");
            callback.onError("No hay alumnos en esta carga curso.");
            return;
        }
        if (cantidad < alumnosDeCargaCursoVigentes.size()) {
            //throw new Error("No todos los integrantes pertenecen a un equipo.");
            callback.onError("No todos los integrantes pertenecen a un equipo.");
            return;
        }

        try {

            List<EquipoIntegranteC> integrantes = SQLite.select(Utils.f_allcolumnTable(EquipoIntegranteC_Table.ALL_COLUMN_PROPERTIES))
                    .from(EquipoIntegranteC.class)
                    .innerJoin(EquipoC.class)
                    .on(EquipoIntegranteC_Table.equipoId.withTable()
                            .eq(EquipoC_Table.key.withTable()))
                    .innerJoin(GrupoEquipoC.class)
                    .on(EquipoC_Table.grupoEquipoId.withTable()
                            .eq(GrupoEquipoC_Table.key.withTable()))
                    .where(GrupoEquipoC_Table.key.withTable().is(grupoEquipoId))
                    .queryList();

            GrupoEquipoC grupoEquipo = new GrupoEquipoC();
            grupoEquipo.setKey(grupoEquipoId);
            grupoEquipo.setTipoId(group.getTipoId());
            grupoEquipo.setCargaCursoId(Integer.parseInt(group.getCargaCursoId()));
            grupoEquipo.setNombre(group.getTitle());
            grupoEquipo.setFinished(true);
            CargaCursos cargaCursos = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .where(CargaCursos_Table.cargaCursoId.is(Integer.parseInt(group.getCargaCursoId())))
                    .querySingle();
            if (cargaCursos != null)
                grupoEquipo.setCargaAcademicaId(cargaCursos.getCargaAcademicaId());

            Empleado empleado = SQLite.select()
                    .from(Empleado.class)
                    .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                    .querySingle();
            if (empleado != null) {
                grupoEquipo.setDocenteId(empleado.getEmpleadoId());
                grupoEquipo.setEstado(GrupoEquipoC.CREADO);
            }

            boolean success = grupoDeEquipoDao.create(grupoEquipo);

            if (!success) {
                throw new Error("Error creando grupo de equipos!");
            }
            Log.d(TAG, "teams "+ group.getTeams().size());
            for (Team team : group.getTeams()) {
                Log.d(TAG, "personas "+ team.getPersonList().size());
                EquipoC equipo = new EquipoC();
                if (TextUtils.isEmpty(team.getId())) {
                    equipo.setKey(IdGenerator.generateId());
                } else {
                    equipo.setKey(team.getId());
                }
                equipo.setGrupoEquipoId(team.getGroupId());
                equipo.setNombre(team.getName());
                equipo.setUrlImagen(team.getUrlImage());
                equipo.setOrden(team.getOrden());
                equipo.setEstado(EquipoC.CREADO);
                equipo.setSyncFlag(BaseEntity.FLAG_UPDATED);
                String equipoId = equipo.getKey();
                Log.d(TAG, "createTeam equipoId: " + equipoId);
                boolean successEquipo = false;
                try {
                    equipo.insert();
                    successEquipo = true;
                } catch (Exception e) {
                    successEquipo = equipo.save();
                }

                if (!successEquipo) {
                    throw new Error("Error guardando equipo!!!");
                }


                if (!integrantes.isEmpty()) {
                    List<Person> personList = team.getPersonList();

                    for (Person person : personList) {
                        Log.d(TAG, "person: " + person.getFirstName()+ "id "+ person.getId());
                        for (EquipoIntegranteC equipoIntegranteC : integrantes) {
                            Log.d(TAG, "equipoIntegranteC: " + equipoIntegranteC.getAlumnoId());
                            if (Integer.parseInt(person.getId()) == equipoIntegranteC.getAlumnoId()) {
                                equipoIntegranteC.setEquipoId(equipoId);
                                equipoIntegranteC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                                boolean integrantesaved = equipoIntegranteC.save();
                                if (!integrantesaved) {
                                    throw new Error("Error guardando equipo!!!");
                                }
                            }
                        }
                    }

                } else {
                    for (Person person : team.getPersonList()) {
                        //Guardar los integrantes que pertenecen al equipo
                        Log.d(TAG, "EquipoIntegranteId :" + person.getEquipoIntegranteId());
                        EquipoIntegranteC integrante = new EquipoIntegranteC();
                        integrante.setAlumnoId(Integer.parseInt(person.getId()));
                        integrante.setEquipoId(equipoId);
                        integrante.setSyncFlag(BaseEntity.FLAG_UPDATED);
                        boolean integrantesaved = integrante.save();
                        if (!integrantesaved) {
                            throw new Error("Error guardando equipo!!!");
                        }
                    }
                }
            }
            callback.onSucess(group);

        } catch (Exception e) {
            callback.onError("Error creando grupo de equipos!");
        }

    }

    @Override
    public void createGroup(String cargaCursoId, final CreateGroupCallback callback) {
        Log.d(TAG, "createGroup cargaCursoId: " + cargaCursoId);
        SQLite.delete()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.finished.is(false))
                .execute();

        GrupoEquipoC grupoDeEquipos = new GrupoEquipoC();
        grupoDeEquipos.setCargaCursoId(Integer.parseInt(cargaCursoId));
        grupoDeEquipos.setFinished(false);
        String grupoDeEquipoId = grupoDeEquipos.getKey();
        grupoDeEquipos.setSyncFlag(GrupoEquipoC.FLAG_DELETED);
        boolean success = grupoDeEquipos.save();
        //boolean success = grupoDeEquipoDao.create(grupoDeEquipos);
        Log.d(TAG, "grupoDeEquipoId: " + grupoDeEquipoId);
        if (success) {
            callback.onSucess(new Group(grupoDeEquipoId, cargaCursoId, "", "", "", new ArrayList<Team>(), false));
            return;
        }
        callback.onError("No se pudo crear al equipo");
    }

    @Override
    public void getGroup(String grupoId, int entidadId, int georeferenciaId,GetGroupCallback callback) {
        Log.d(TAG, "getGroup: " + grupoId);
        GrupoEquipoC grupoDeEquipo = grupoDeEquipoDao.getGrupoConEquiposEIntegrantes(grupoId);
        Group group = Group.transform(grupoDeEquipo);
        try {
            for (Team team : group.getTeams()) {

                for (Person person : team.getPersonList()) {
                    List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumno(Integer.valueOf(person.getId()), entidadId, georeferenciaId);

                    Log.d(TAG, "dimensionObservadaList Size:" + dimensionObservadaList.size());
                    List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();

                    for (DimensionObservada dimensionObservada : dimensionObservadaList) {
                        Dimension dimension = SQLite.select()
                                .from(Dimension.class)
                                .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                                .querySingle();


                        DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
                        dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
                        int valor = (int) (dimensionObservada.getValor() * 100);
                        dimensionObservadaUi.setValor(valor);
                        dimensionObservadaUiList.add(dimensionObservadaUi);
                        if (dimension == null) continue;
                        DimensionUi dimensionUi = new DimensionUi();
                        dimensionUi.setId(dimension.getDimensionId());
                        dimensionUi.setNombre(dimension.getNombre());
                        dimensionUi.setDescripcion(dimension.getDescripcion());
                        dimensionUi.setInstrumentoEvalId(dimension.getInstrumentoEvalId());
                        dimensionUi.setSigla(dimension.getSigla());
                        dimensionUi.setEnfoque(dimension.getEnfoque());
                        dimensionUi.setConfiabilidad(dimension.getConfiabilidad());
                        dimensionUi.setIntervaloInicio(dimension.getIntervaloInicio());
                        dimensionUi.setIntervaloFin(dimension.getIntervaloFin());
                        dimensionUi.setIncluidoIInicio(dimension.getIncluidoIInicio());
                        dimensionUi.setIncluidoIFin(dimension.getIncluidoIFin());
                        dimensionUi.setColor(dimension.getColor());
                        dimensionUi.setIcono(dimension.getIcono());
                        dimensionUi.setMedida(dimension.getMedida());
                        dimensionUi.setOrden(dimension.getOrden());
                        dimensionObservadaUi.setDimensionUi(dimensionUi);

                    }

                    List<DimensionObservadaUi> newDimensionObservadaUiList = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
                    person.setDimensionObservadasUiList(newDimensionObservadaUiList);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (group == null) callback.onDataNotAvailable();
        callback.onGroupLoaded(group);
    }

    private List<DimensionObservadaUi> getNewdimensionObservadaUiListOrderValor(List<DimensionObservadaUi> dimensionObservadaUiList) {

        List<DimensionObservadaUi> dimensionObservadaUiListNew = new ArrayList<>(dimensionObservadaUiList);
        try {
            int cantidadTotal = 0;
            DimensionObservadaUi dimensionObservadaUisUno = dimensionObservadaUiList.get(0);
            DimensionObservadaUi dimensionObservadaUisDos = dimensionObservadaUiList.get(1);
            DimensionObservadaUi dimensionObservadaUisTres = dimensionObservadaUiList.get(2);
            DimensionObservadaUi dimensionObservadaUisCuatro = dimensionObservadaUiList.get(3);
            int areaUno = (int)(dimensionObservadaUisUno.getValor() * dimensionObservadaUisDos.getValor() / 2);
            int areaDos = (int)(dimensionObservadaUisDos.getValor() * dimensionObservadaUisTres.getValor() / 2);
            int areaTres = (int)(dimensionObservadaUisTres.getValor() * dimensionObservadaUisCuatro.getValor() / 2);
            int areaCuatro = (int)(dimensionObservadaUisCuatro.getValor() * dimensionObservadaUisUno.getValor() / 2);
            cantidadTotal = areaUno + areaDos + areaTres + areaCuatro;

            dimensionObservadaUisUno.setArea(areaUno);
            float porsentajeOne = (float) areaUno / cantidadTotal;
            porsentajeOne = porsentajeOne * 100;
            dimensionObservadaUisUno.setPorcentaje((int) porsentajeOne);

            dimensionObservadaUisDos.setArea(areaDos);
            float porsentajeDos =(float) areaDos/cantidadTotal;
            porsentajeDos = porsentajeDos * 100;
            dimensionObservadaUisDos.setPorcentaje((int)porsentajeDos);
            dimensionObservadaUisTres.setArea(areaTres);
            float porsentajeTres = (float)areaTres/cantidadTotal;
            porsentajeTres = porsentajeTres * 100;
            dimensionObservadaUisTres.setPorcentaje((int)porsentajeTres);
            dimensionObservadaUisCuatro.setArea(areaCuatro);
            float porsentajeCuatro = (float)areaCuatro/cantidadTotal;
            porsentajeCuatro = porsentajeCuatro * 100;
            dimensionObservadaUisCuatro.setPorcentaje((int)porsentajeCuatro);


            Collections.sort(dimensionObservadaUiListNew, new Comparator<DimensionObservadaUi>() {
                @Override
                public int compare(DimensionObservadaUi p1, DimensionObservadaUi p2) {
                    return Integer.compare(p2.getArea(), p1.getArea());
                }
            });

            int posicion2=0;
            for (DimensionObservadaUi dimensionObservadaUi: dimensionObservadaUiListNew){
                posicion2++;
                dimensionObservadaUi.setPoscion(posicion2);
            }

        }catch (Exception e){
            Log.d(TAG ,"ERROR  getNewdimensionObservadaUiListOrderValor");
            e.printStackTrace();
        }


        return dimensionObservadaUiListNew;

    }

    @Override
    public void getLastGroup(GetGroupCallback callback) {
        callback.onDataNotAvailable();
    }

    private static Person transform(PersonaContratoQuery persona) {
        if (persona == null) return null;
        return new Person(
                String.valueOf(persona.getPersonaId()),
                Utils.getFirstWord(persona.getNombres()),
                capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()) + ", " + capitalize(persona.getNombres()),
                persona.getFoto());
    }

    public List<Person> getListPerson(int cargaCursoId, int entidadId, int georeferenciaId) {
        List<PersonaContratoQuery> personaList = getAlumnosVigentes(personaDao.getAlumnosDeCargaCurso(cargaCursoId));
        List<Person> personList = new ArrayList<>();
        for (PersonaContratoQuery persona : personaList) {
            Person person = transform(persona);
            if (person == null) continue;
            person.setMember(true);
            personList.add(person);

            Log.d(TAG, "person.getId() "+ person.getId());
            List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumno(Integer.valueOf(person.getId()), entidadId, georeferenciaId);

            Log.d(TAG, "dimensionObservadaList Size:" + dimensionObservadaList.size());
            List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();

            for (DimensionObservada dimensionObservada : dimensionObservadaList) {
                Dimension dimension = SQLite.select()
                        .from(Dimension.class)
                        .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                        .querySingle();


                DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
                dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
                int valor = (int) (dimensionObservada.getValor() * 100);
                dimensionObservadaUi.setValor(valor);
                dimensionObservadaUiList.add(dimensionObservadaUi);
                if (dimension == null) continue;
                DimensionUi dimensionUi = new DimensionUi();
                dimensionUi.setId(dimension.getDimensionId());
                dimensionUi.setNombre(dimension.getNombre());
                dimensionUi.setDescripcion(dimension.getDescripcion());
                dimensionUi.setInstrumentoEvalId(dimension.getInstrumentoEvalId());
                dimensionUi.setSigla(dimension.getSigla());
                dimensionUi.setEnfoque(dimension.getEnfoque());
                dimensionUi.setConfiabilidad(dimension.getConfiabilidad());
                dimensionUi.setIntervaloInicio(dimension.getIntervaloInicio());
                dimensionUi.setIntervaloFin(dimension.getIntervaloFin());
                dimensionUi.setIncluidoIInicio(dimension.getIncluidoIInicio());
                dimensionUi.setIncluidoIFin(dimension.getIncluidoIFin());
                dimensionUi.setColor(dimension.getColor());
                dimensionUi.setIcono(dimension.getIcono());
                dimensionUi.setMedida(dimension.getMedida());
                dimensionUi.setOrden(dimension.getOrden());
                dimensionObservadaUi.setDimensionUi(dimensionUi);

            }
            List<DimensionObservadaUi> newDimensionObservadaUiList = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
            person.setDimensionObservadasUiList(newDimensionObservadaUiList);

        }
        return personList;
    }

    @Override
    public void getTeamList(int cargaCursoId,int cantidad, Group group, int tipo, int entidadId, int georeferenciaId, Callback<List<Team>> callback) {
        List<Person> personaList= getListPerson(cargaCursoId, entidadId, georeferenciaId);
        List<Team> teamList = new ArrayList<>();
        try {
            switch (tipo){
                case 1 ://team agrupado aleatoriamente
                    //Permuta aleatoriamente la lista.
                    Collections.shuffle(personaList);

                    List<List<Person>> output = Utils.chunkList(personaList,cantidad);
                    int posicion = 0;
                    for (List<Person> list : output){
                        Team team = new Team();
                        team.setId(IdGenerator.generateId());
                        team.setName(String.valueOf("Equipo " + (posicion+1)));
                        team.setPersonList(list);
                        team.setOrden(posicion);
                        team.setGroup(group);
                        team.setGroupId(group.getId());
                        teamList.add(team);
                        posicion ++;
                    }
                    break;
                case 3://team agrupado por aprendizaje diferente
                    List<Person> personListDimen = new ArrayList<>();
                    List<Person> personListSinDimen = new ArrayList<>();
                    for (Person person : personaList){
                        if (person.getDimensionObservadasUiList().size()==0)personListSinDimen.add(person);
                        else personListDimen.add(person);
                    }
                    Collections.shuffle(personListDimen);
                    Collections.sort(personListDimen, new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            try {
                                DimensionUi d1= getDimensionUiMayor(o1.getDimensionObservadasUiList());
                                DimensionUi d2= getDimensionUiMayor(o2.getDimensionObservadasUiList());
                                return Integer.compare(d1.getId(), d2.getId());
                            }
                            catch (Exception e){e.printStackTrace();
                            return -1;
                            }
                        }
                    });

                    for (Person list : personListSinDimen)personListDimen.add(list);


                    List<List<Person>> output2 = new ArrayList<>();
                    for(int i =0; i < cantidad; i++ ){
                        List<Person> personTeamList = new ArrayList<>();
                        for (int j =0; j < personListDimen.size(); j ++){
                            int post = (j*(cantidad)+i);
                            if(personListDimen.size()>post)personTeamList.add(personListDimen.get(post));
                        }
                        output2.add(personTeamList);
                    }
                    int posicion2 = 0;
                    for (List<Person> list : output2){
                        Team team = new Team();
                        team.setId(IdGenerator.generateId());
                        team.setName(String.valueOf("Equipo " + (posicion2+1)));
                        team.setPersonList(list);
                        team.setOrden(posicion2);
                        team.setGroup(group);
                        team.setGroupId(group.getId());
                        teamList.add(team);
                        posicion2 ++;
                    }

                    break;
                case 4://team agrupado por aprendizaje unico
                    List<Dimension>dimensions= SQLite.select().from(Dimension.class)
                            .orderBy(Dimension_Table.orden.asc()).queryList();
                    Set<Person> personListSinDimensi = new LinkedHashSet<>();
                    for(Dimension dimension: dimensions){
                        List<Person> personListDimensi = new ArrayList<>();
                        for(Person person: personaList){
                            if (person.getDimensionObservadasUiList().size()==0) personListSinDimensi.add(person);
                            else if(getDimensionMaxALumno(person.dimensionObservadasUiList)==dimension.getDimensionId())personListDimensi.add(person);
                        }

                        if (dimension!=null){
                            particionarTeams(personListDimensi,teamList,dimension.getNombre(), group);
                        }
                    }
                    particionarTeams(new ArrayList<>(personListSinDimensi), teamList, "Sin Aprendizaje", group);
                    break;
            }
            callback.onLoad(true, teamList);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Team>());
        }
    }
    public void particionarTeams(List<Person> personList, List<Team>teamList, String  titulo, Group group){
        if (personList.size()>0 && personList.size()<6){
            for (Team team: agruparAprendizaje(personList, group, 1,titulo))teamList.add(team);
        } else if (personList.size()>5 && personList.size()<11){
            for (Team team: agruparAprendizaje(personList, group, 2, titulo))teamList.add(team);
        } else if (personList.size()>10 && personList.size()<16){
            for (Team team: agruparAprendizaje(personList, group, 3, titulo))teamList.add(team);
        } else if (personList.size()>15 && personList.size()<21){
            for (Team team: agruparAprendizaje(personList, group, 4, titulo))teamList.add(team);
        } else if (personList.size()>20){
            for (Team team: agruparAprendizaje(personList, group, 5, titulo))teamList.add(team);
        }

    }
   private DimensionUi getDimensionUiMayor(List<DimensionObservadaUi> list){
        DimensionObservadaUi dimensionObservadaUiMayor= null;
        for(DimensionObservadaUi dimensionObservadaUi: list){
            if(dimensionObservadaUiMayor==null || dimensionObservadaUiMayor.getArea()<dimensionObservadaUi.getArea())dimensionObservadaUiMayor=dimensionObservadaUi;
        }

        DimensionUi dimensionUi = null;
        if(dimensionObservadaUiMayor!=null)dimensionUi = dimensionObservadaUiMayor.getDimensionUi();
       return dimensionUi;
    }

    public int getDimensionMaxALumno(List<DimensionObservadaUi> dimensionObservadaList) {
        int areaActual = 0;
        int areaMayor = 0;
        int dimensionId = 0;

        for (DimensionObservadaUi dimensionObservada: dimensionObservadaList){
            areaActual = (int) dimensionObservada.getArea();
            if (areaActual>areaMayor){
                areaMayor=areaActual;
                dimensionId = dimensionObservada.getDimensionUi().getId();
            }
        }

        return dimensionId;
    }

    public  List<Team> agruparAprendizaje(List<Person> personList, Group group, int cantidad, String tipo){
        List<Team> teamList = new ArrayList<>();

        int position = 0;
        List<List<Person>> output = Utils.chunkList(personList,cantidad);
        for (List<Person> list : output) {
            Team team = new Team();
            team.setId(IdGenerator.generateId());
            team.setName(String.valueOf("Equipo " + tipo + " "+(position + 1)));
            team.setPersonList(list);
            team.setOrden(position);
            team.setGroup(group);
            team.setGroupId(group.getId());
            teamList.add(team);
            position++;
        }
        return teamList;
    }
}
