package com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.equipo.EquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo.GrupoDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo.IntegranteDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTeamLocalDataSource implements CreateTeamDataSource {

    private static final String TAG = CreateTeamLocalDataSource.class.getSimpleName();

    private GrupoDeEquipoDao grupoDeEquipoDao;
    private EquipoDao equipoDao;
    private IntegranteDeEquipoDao integranteDeEquipoDao;
    private PersonaDao personaDao;
    private DimensionObservadaDao dimensionObservadaDao;

    public CreateTeamLocalDataSource(GrupoDeEquipoDao grupoDeEquipoDao, EquipoDao equipoDao, IntegranteDeEquipoDao integranteDeEquipoDao, PersonaDao personaDao, DimensionObservadaDao dimensionObservadaDao) {
        this.grupoDeEquipoDao = grupoDeEquipoDao;
        this.equipoDao = equipoDao;
        this.integranteDeEquipoDao = integranteDeEquipoDao;
        this.personaDao = personaDao;
        this.dimensionObservadaDao = dimensionObservadaDao;
    }

    private List<PersonaContratoQuery> getAlumnosSinGrupo(int cargaCursoId, String grupoEquipoId) {
        List<PersonaContratoQuery> alumnosDeCargaCursoVigentes = new ArrayList<>();
        for(PersonaContratoQuery personaContratoQuery: personaDao.getAlumnosDeCargaCurso(cargaCursoId)){
            if(personaContratoQuery.getVigente()==1)alumnosDeCargaCursoVigentes.add(personaContratoQuery);
        }
        return alumnosDeCargaCursoVigentes;
    }


    private Person transform(PersonaContratoQuery persona) {
        if (persona == null) return null;
        return new Person(
                String.valueOf(persona.getPersonaId()),
                Utils.getFirstWord(persona.getNombres()),
                capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()) + ", " + capitalize(persona.getNombres()),
                persona.getFoto());
    }

    @Override
    public void getPersonas(String cargaCursoId, String grupoEquipoId, int entidadId, int georeferenciaId, GetPersonasCallback callback) {
        Log.d(TAG, "getAlumnosSinGrupo cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "getAlumnosSinGrupo grupoEquipo: " + grupoEquipoId);
        int idCargaCurso = Integer.parseInt(cargaCursoId);
        List<Person> personList = new ArrayList<>();
        List<PersonaContratoQuery> personaList = getAlumnosSinGrupo(idCargaCurso, grupoEquipoId);
        for (PersonaContratoQuery itemPersona: personaList){
            if (itemPersona.getVigente()==0)continue;
            Person person = transform(itemPersona);
            personList.add(person);
            addListDimencion(person, entidadId, georeferenciaId);
        }
        callback.onPersonasLoaded(personList);
    }


    @Override
    public void createTeam(final Team team, final CreateTeamCallBack callback) {
        Log.d(TAG, "createTeam: " + team.toString());
        EquipoC equipo = new EquipoC();
        equipo.setKey(team.getId());
        equipo.setGrupoEquipoId(team.getGroupId());
        equipo.setNombre(team.getName());
        equipo.setUrlImagen(team.getUrlImage());
        equipo.setOrden(team.getOrden());
        equipo.setEstado(EquipoC.ESTADO_CREADO);
        String equipoId = equipo.getKey();
        Log.d(TAG, "createTeam equipoId: " + equipoId);
        List<Person> members = team.getPersonList();
        List<EquipoIntegranteC> integrantes = new ArrayList<>();
        for (Person person : members) {
            //Guardar los integrantes que pertenecen al equipo
            EquipoIntegranteC integrante = new EquipoIntegranteC();
            integrante.setAlumnoId(Integer.parseInt(person.getId()));
            integrante.setEquipoId(equipoId);
            integrantes.add(integrante);
        }

        equipo.setIntegrantes(integrantes);
        equipoDao.createEquipoConIntegrantes(equipo, new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean success) {
                Log.d(TAG, "onSuccess: " + success);
                if (success) {
                    callback.localSuccess(success);
                    return;
                }
                callback.localSuccess(false);
            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: " + error.getMessage());
                callback.localSuccess(false);
            }
        });
    }

    @Override
    public void deleteTeam(final List<Team> teams, final TeamCallback callback) {
        Log.d(TAG, "deleteTeam: " + teams.toString());

        List<EquipoC> equipoCList = new ArrayList<>();

        for (Team team : teams){
            EquipoC equipoC = equipoDao.getEquipoConIntegrantes(team.getId());
            if(equipoC != null)equipoCList.add(equipoC);
        }
        Log.d(TAG, "equipoCList "+ equipoCList.size());
        equipoDao.deleteEquipoConIntegrantes(equipoCList, new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    Log.d(TAG, "onSuccess ");
                    callback.onSuccess(new Team());
                    return;
                }else     {
                    Log.d(TAG, "null ");
                    callback.onSuccess(null);
                    return;
                }
                //onError(new Error("Error eliminando equipo"));
            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: " + error.getMessage());
                callback.onError(error.getMessage());
            }
        });
    }

    @Override
    public void getTeam(String teamId, int entidadId, int georeferenciaId,Callback<Team> callback) {
        EquipoC equipo = equipoDao.getEquipoConIntegrantesConSuPersona(teamId);
        Team team = Team.transform(equipo);
        try {
            for (Person person: team.getPersonList()){
                addListDimencion( person, entidadId, georeferenciaId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        callback.onSuccess(team);
    }

    @Override
    public List<DimensionUi> getDimensiones() {

        Log.d(TAG, "getDimensiones ");
        List<DimensionUi> dimensionUiList= new ArrayList<>();
        List<Dimension>dimensions= SQLite.select().from(Dimension.class)
                .orderBy(Dimension_Table.orden.asc())
                .queryList();
        for(Dimension dimension: dimensions){
            Log.d(TAG, "dimension "+ dimension.getNombre());
            DimensionUi dimensionUi= new DimensionUi();
            dimensionUi.setId(dimension.getDimensionId());
            dimensionUi.setDescripcion(dimension.getDescripcion());
            dimensionUi.setNombre(dimension.getNombre());
            dimensionUiList.add(dimensionUi);
        }
        return dimensionUiList;
    }

    private void addListDimencion(Person person, int entidadId, int georeferenciaId){
        List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumno(Integer.valueOf(person.getId()), entidadId, georeferenciaId);

        Log.d(TAG,"dimensionObservadaList Size:" + dimensionObservadaList.size());
        List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();

        for (DimensionObservada dimensionObservada: dimensionObservadaList){
            Dimension dimension = SQLite.select()
                    .from(Dimension.class)
                    .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                    .querySingle();


            DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
            dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
            int valor = (int) (dimensionObservada.getValor()*100);
            dimensionObservadaUi.setValor(valor);
            dimensionObservadaUiList.add(dimensionObservadaUi);
            if(dimension==null)continue;
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

        List<DimensionObservadaUi> NewimensionObservadaList = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
        person.setDimensionObservadasUiList(NewimensionObservadaList);

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
            dimensionObservadaUisUno.setPorcentaje((int) (porsentajeOne*100));

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
            e.printStackTrace();
        }


        return dimensionObservadaUiListNew;

    }
}
