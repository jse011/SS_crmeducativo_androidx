package com.consultoraestrategia.ss_crmeducativo.comportamiento.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao.AlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.comportamientoDao.ComportamientoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Comportamiento;
import com.consultoraestrategia.ss_crmeducativo.entities.Comportamiento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GeoRefOrganigrama;
import com.consultoraestrategia.ss_crmeducativo.entities.GeoRefOrganigrama_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.PersonaGeoOrg;
import com.consultoraestrategia.ss_crmeducativo.entities.PersonaGeoOrg_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoEntidadGeo;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoEntidadGeo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

public class ComportamientoDataLocalSource implements ComportamientoDataSource {

    ComportamientoDao comportamientoDao;
    PersonaDao personaDao;
    CursoDao cursoDao;
    ParametrosDisenioDao parametrosDisenioDao;
    SessionUserDao sessionUserDao;
    AlumnoDao alumnoDao;
    private String TAG = ComportamientoDataLocalSource.class.getSimpleName();

    public ComportamientoDataLocalSource(ComportamientoDao comportamientoDao, PersonaDao personaDao, CursoDao cursoDao, ParametrosDisenioDao parametrosDisenioDao, SessionUserDao sessionUserDao, AlumnoDao alumnoDao) {
        this.comportamientoDao = comportamientoDao;
        this.personaDao = personaDao;
        this.cursoDao = cursoDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
        this.sessionUserDao = sessionUserDao;
        this.alumnoDao = alumnoDao;

    }

    @Override
    public void getListComportamiento(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId, int idcalendarioPeriodo, int georeferencia, int entidadId, SucessCallback<List<AlumnoUi>> callback) {

        try {
            List<PersonaContratoQuery> lista = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            List<AlumnoUi> alumnoUiList = new ArrayList<>();
            for (PersonaContratoQuery persona : lista) {
                List<Caso> casosList = SQLite.select(Utils.f_allcolumnTable(Caso_Table.ALL_COLUMN_PROPERTIES))
                        .from(Caso.class).
                                where(Caso_Table.calendarioPeriodoId.withTable().eq(idcalendarioPeriodo))
                        .and(Caso_Table.programaEducativoId.withTable().eq(programaEducativoId))
                        .and(Caso_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId))
                        .and(Caso_Table.cargaCursoId.withTable().eq(cargaCursoId))
                        .and(Caso_Table.docenteId.withTable().eq(docenteId))
                        .and(Caso_Table.alumnoId.withTable().eq(persona.getPersonaId()))
                        .and(Caso_Table.estadoId.withTable().notEq(Caso.ESTADO_ELIMINADO)).queryList();
                int cantidadt = casosList.size();
                //traer los casos reportes
                Usuario usuario = getUsuario(docenteId);
                List<Caso> casoListReporte = SQLite.select().from(Caso.class).
                        innerJoin(CasoReporte.class)
                        .on(Caso_Table.key.withTable().eq(CasoReporte_Table.key.withTable()))
                        .where(Caso_Table.alumnoId.withTable().eq(persona.getPersonaId()))
                        .and(CasoReporte_Table.usuarioDestinoId.withTable().eq(usuario.getUsuarioId())).queryList();

                // Log.d(TAG, "casoListReporte "+ casoListReporte.size());
                for (Caso reporteCaso : casoListReporte) {
                    int position = casosList.indexOf(reporteCaso);
                    if (position == -1) casosList.add(reporteCaso);
                }
                if (cantidadt == 0) continue;

                AlumnoUi alumnoUi = new AlumnoUi();
                alumnoUi.setCargacursoId(cargaCursoId);
                alumnoUi.setId(persona.getPersonaId());
                alumnoUi.setLastName(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
                alumnoUi.setNombre(Utils.getFirstWord(persona.getNombres()));
                alumnoUi.setUrlProfile(persona.getFoto());
                //  Log.d(TAG, "CASO cantidad "+ cantidadt);

                List<TipoUi> tipoUiList = new ArrayList<>();
                for (Caso caso : casosList) {

                    Log.d(TAG, "CASO t "+ caso.getDescripcion()+ " alumno "+caso.getAlumnoId() );
                    Comportamiento comportamiento = SQLite.select(Utils.f_allcolumnTable(Comportamiento_Table.ALL_COLUMN_PROPERTIES)).from(Comportamiento.class)
                            .where(Comportamiento_Table.comportamientoId.withTable().eq(caso.getComportamientoId()))
                            .querySingle();

                    if (comportamiento != null) {
                        TipoUi tipoUi = new TipoUi();
                        tipoUi.setId(comportamiento.getTipoCasoId());
                        int position = tipoUiList.indexOf(tipoUi);
                        if(position == -1){
                            Tipos tipo = SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES))
                                    .from(Tipos.class).where(Tipos_Table.tipoId.withTable().eq(comportamiento.getTipoCasoId()))
                                    .querySingle();
                            if(tipo!=null){
                                tipoUi.setNombre(tipo.getNombre());
                            }else {
                                tipoUi.setNombre("desconocido");
                            }

                            tipoUiList.add(tipoUi);
                        }else {
                            tipoUi = tipoUiList.get(position);
                        }

                        int cantidad = tipoUi.getCantidad() + comportamiento.getCantidad();
                        tipoUi.setCantidad(cantidad);
                    }

                }

                for (TipoUi tipoUi : tipoUiList){
                    tipoUi.setPorcentaje(tipoUi.getCantidad() * 100 / cantidadt);
                }
                Collections.sort(tipoUiList, new Comparator<TipoUi>() {
                    @Override
                    public int compare(TipoUi o1, TipoUi o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });

                alumnoUi.setTipoUiList(tipoUiList);
                alumnoUiList.add(alumnoUi);
            }

            callback.onLoad(true, alumnoUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(true, new ArrayList<AlumnoUi>());
        }

    }

    public List<Integer> getPadresInteger(int georeferenciaId, int entidadId) {
        List<Integer> listaTipos = new ArrayList<>();

        From<Tipos> tiposFrom = SQLite.select()
                .from(Tipos.class)
                .innerJoin(Comportamiento.class)
                .on(Comportamiento_Table.tipoCasoId.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(TipoEntidadGeo.class)
                .on(TipoEntidadGeo_Table.comportamientoId.withTable()
                        .eq(Comportamiento_Table.comportamientoId.withTable()));


        List<Tipos> tipoEntidadGeoList = new ArrayList<>(tiposFrom
                .where(TipoEntidadGeo_Table.georeferenciaId.withTable().eq(georeferenciaId))
                .queryList());

        List<Integer> entidadIdList = new ArrayList<>();
        int entidadSearch = entidadId;
        boolean estado = true;

        entidadIdList.add(entidadSearch);

        while (estado){

            Entidad entidad = SQLite.select()
                    .from(Entidad.class)
                    .where(Entidad_Table.entidadId.eq(entidadSearch))
                    .querySingle();

            if(entidad==null||entidad.getParentId()==0){
                estado = false;
            }else {
                entidadSearch = entidad.getParentId();
                entidadIdList.add(entidadSearch);
                Log.d(TAG,"entidadSearch: " + entidadSearch);
            }
        }

        tipoEntidadGeoList.addAll(tiposFrom
                .where(TipoEntidadGeo_Table.entidadId.withTable().in(entidadIdList))
                .queryList());

        for (Tipos tipo : tipoEntidadGeoList) {
            int position = listaTipos.indexOf(tipo.getTipoId());
            if (position == -1) listaTipos.add(tipo.getTipoId());
        }
        return listaTipos;

    }

    @Override
    public void getListComportamientoAlumno(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId, int idcalendarioPeriodo, int idalumno, SucessCallback<AlumnoUi> callback) {

        try {
            Persona persona = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                    .from(Persona.class)
                    .where(Persona_Table.personaId.withTable().eq(idalumno)).querySingle();

            if (persona != null) {
                AlumnoUi alumnoUi = new AlumnoUi();
                alumnoUi.setNombre(persona.getFirstName());
                alumnoUi.setLastName(persona.getApellidos());
                alumnoUi.setUrlProfile(persona.getUrlPicture());
                alumnoUi.setId(persona.getPersonaId());


                List<Caso> casosList = SQLite.select(Utils.f_allcolumnTable(Caso_Table.ALL_COLUMN_PROPERTIES))
                        .from(Caso.class).
                                where(Caso_Table.calendarioPeriodoId.withTable().eq(idcalendarioPeriodo))
                        .and(Caso_Table.programaEducativoId.withTable().eq(programaEducativoId))
                        .and(Caso_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId))
                        .and(Caso_Table.cargaCursoId.withTable().eq(cargaCursoId))
                        .and(Caso_Table.docenteId.withTable().eq(docenteId))
                        .and(Caso_Table.alumnoId.withTable().eq(idalumno))
                        .and(Caso_Table.estadoId.withTable().notEq(Caso.ESTADO_ELIMINADO))
                        .orderBy(Caso_Table.fechaCreacion.desc())
                        .queryList();

                List<ComportamientoUi> comportamientoUiList = new ArrayList<>();
                for (Caso caso : casosList) {

                    ComportamientoUi comportamientoUi = new ComportamientoUi();
                    comportamientoUi.setId(caso.key);
                    comportamientoUi.setDescripcion(caso.getDescripcion());
                    comportamientoUi.setFecha(caso.getFechaCaso());
                    comportamientoUi.setIdprogramaEducativo(caso.getProgramaEducativoId());
                    comportamientoUi.setDocenteId(caso.getDocenteId());
                    comportamientoUi.setCargaCursoId(caso.getCargaCursoId());
                    comportamientoUi.setCalendarioPeridoId(caso.getCalendarioPeriodoId());
                    comportamientoUi.setCargaAcademicaId(caso.getCargaAcademicaId());
                    comportamientoUi.setTipo(ComportamientoUi.Tipo.ORIGEN);

                    //traer tipo caso
                    Comportamiento comportamiento = SQLite.select(Utils.f_allcolumnTable(Comportamiento_Table.ALL_COLUMN_PROPERTIES)).from(Comportamiento.class)
                            .where(Comportamiento_Table.comportamientoId.withTable().eq(caso.getComportamientoId())).querySingle();

                    if (comportamiento != null) {

                        TipoComportamientoUi tipoComportamientoUi = new TipoComportamientoUi();
                        tipoComportamientoUi.setId(comportamiento.getComportamientoId());
                        tipoComportamientoUi.setTitulo(comportamiento.getNombre());
                        tipoComportamientoUi.setDescripcion(comportamiento.getDescripcion());
                        tipoComportamientoUi.setCantidad(comportamiento.getCantidad());
                        tipoComportamientoUi.setSelected(false);

                        comportamientoUi.setTipoComportamientoUi(tipoComportamientoUi);

                        Tipos tippadre = SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES)).from(Tipos.class)
                                .where(Tipos_Table.tipoId.withTable().eq(comportamiento.getTipoCasoId())).querySingle();

                        if (tippadre != null) {
                            TipoUi tipoUiPadre = new TipoUi();
                            tipoUiPadre.setSelected(true);
                            tipoUiPadre.setId(tippadre.getTipoId());
                            tipoUiPadre.setConcepto(tippadre.getConcepto());
                            tipoUiPadre.setNombre(tippadre.getNombre());
                            tipoUiPadre.setObjeto(tippadre.getObjeto());
                            tipoUiPadre.setHerencia(TipoUi.Herencia.PADRE);
                            //padre
                            comportamientoUi.setTipoConducta(tipoUiPadre);
                        }
                    }




                    comportamientoUiList.add(comportamientoUi);

                    List<ArchivoUi> archivoUiList = new ArrayList<>();
                    List<CasoArchivo> casoArchivoList = SQLite.select()
                            .from(CasoArchivo.class)
                            .where(CasoArchivo_Table.casoId.eq(caso.getKey()))
                            .orderBy(CasoArchivo_Table.nombre.asc())
                            .queryList();

                    for (CasoArchivo casoArchivo : casoArchivoList) {
                        ArchivoUi archivoUi = new ArchivoUi();
                        archivoUi.setArchivoId(casoArchivo.getArchivoCasoId());
                        archivoUi.setNombreArchivo(casoArchivo.getNombre());
                        archivoUi.setNombreRecurso(casoArchivo.getNombre());
                        switch (casoArchivo.getTipoId()) {
                            case CasoArchivo.TIPO_IMAGEN:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                                break;
                            case CasoArchivo.TIPO_VIDEO:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                                break;
                            case CasoArchivo.TIPO_DOCUMENTO:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                                break;
                            case CasoArchivo.TIPO_AUDIO:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                                break;
                            case CasoArchivo.TIPO_HOJA_CALCULO:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                                break;
                            case CasoArchivo.TIPO_DIAPOSITIVA:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                                break;
                            case CasoArchivo.TIPO_PDF:
                                archivoUi.setTipoFileU(RepositorioTipoFileU.PDF);
                                break;
                        }

                        archivoUi.setUrl(casoArchivo.getPath());
                        archivoUi.setPath(casoArchivo.getLocalPath());
                        if (TextUtils.isEmpty(casoArchivo.getLocalPath())) {
                            archivoUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                        } else {
                            archivoUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                        }
                        archivoUi.setSelect(true);
                        archivoUiList.add(archivoUi);

                    }
                    comportamientoUi.setArchivoUiList(archivoUiList);

                }

                alumnoUi.setComportamientoUiList(comportamientoUiList);

                //usuario conectado
                SessionUser sessionUser = sessionUserDao.getCurrentUser();
                if (sessionUser != null) alumnoUi.setUsername(sessionUser.getUsername());
                callback.onLoad(true, alumnoUi);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(true, new AlumnoUi());
        }

    }

    public AlumnoUi getalumno(int alumnoId) {

        Persona persona = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .where(Persona_Table.personaId.withTable().eq(alumnoId)).querySingle();
        AlumnoUi alumnoUi = new AlumnoUi();
        if (persona != null) {
            alumnoUi.setNombre(persona.getFirstName());
            alumnoUi.setLastName(persona.getApellidos());
            alumnoUi.setUrlProfile(persona.getUrlPicture());
            alumnoUi.setId(persona.getPersonaId());
        }

        return alumnoUi;

    }


    public CursoUi getCursoUi(int idcargaCurso) {


        List<Integer> integerList = new ArrayList<>();
        integerList.add(idcargaCurso);
        List<CursoCustom> listaCursos = cursoDao.obtenerPorCargaCursos(integerList);
        CursoCustom cursoCustom = listaCursos.get(0);
        CursoUi cursoUi = new CursoUi();
        if (cursoCustom != null) {
            cursoUi.setCursoId(cursoCustom.getCursoId());
            cursoUi.setAlias(cursoCustom.getAlias());
            cursoUi.setCargaCursoId(cursoCustom.getCargaCursoId());
            cursoUi.setDescripcion(cursoCustom.getDescripcion());
            cursoUi.setNombre(cursoCustom.getNombre());
            cursoUi.setPeriodo(cursoCustom.getPeriodo());
            cursoUi.setSeccion(cursoCustom.getSeccion());

            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(idcargaCurso);
            if (parametrosDisenio != null) {
                cursoUi.setColor1(parametrosDisenio.getColor1());
                cursoUi.setColor2(parametrosDisenio.getColor2());
                cursoUi.setColor3(parametrosDisenio.getColor3());
            }

        }
        return cursoUi;

    }

    @Override
    public void getCurso(int idcargaCurso, int alumnoId, SucessCallback<CursoUi> callback) {
        CursoUi cursoUi = getCursoUi(idcargaCurso);
        cursoUi.setAlumnoUi(getalumno(alumnoId));
        callback.onLoad(true, cursoUi);
    }

    @Override
    public void deleteComportamiento(String comportamientoId, CallbackSuccess callbackSuccess) {
            Caso caso = SQLite.select().from(Caso.class).where(Caso_Table.key.withTable().eq(comportamientoId)).querySingle();
            if (caso != null) {
                caso.setSyncFlag(BaseEntity.FLAG_UPDATED);
                caso.setEstadoId(Caso.ESTADO_ELIMINADO);
                caso.save();
            }

            callbackSuccess.onLoad(true);


    }

    @Override
    public void getAlumnos(int cargaCursoId, SucessCallback<List<AlumnoUi>> callback) {

        try {
            List<PersonaContratoQuery> lista = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            List<AlumnoUi> alumnoUiList = new ArrayList<>();
            for (PersonaContratoQuery persona : lista) {
                AlumnoUi alumnoUi = new AlumnoUi();
                alumnoUi.setId(persona.getPersonaId());
                alumnoUi.setUrlProfile(persona.getFoto());
                alumnoUi.setLastName(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
                alumnoUi.setNombre(Utils.getFirstWord(persona.getNombres()));
                alumnoUi.setCursoUi(getCursoUi(cargaCursoId));
                alumnoUiList.add(alumnoUi);
            }
            callback.onLoad(true, alumnoUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(true, new ArrayList<AlumnoUi>());
        }

    }

    @Override
    public boolean saveComportamiento(ComportamientoUi comportamientoUi, DestinoUi destinoUi, List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(TAG, "saveComportamiento ");
        boolean success = false;
        try {
            Caso caso = SQLite.select(Utils.f_allcolumnTable(Caso_Table.ALL_COLUMN_PROPERTIES))
                    .from(Caso.class).where(Caso_Table.key.withTable().eq(comportamientoUi.getId())).querySingle();

            String casoKey;
            if (caso != null) {
                Log.d(TAG, "caso.getKey() ");
                casoKey = caso.getKey();
                caso.setFechaCaso(comportamientoUi.getFecha());
                //caso.setTipoCasoId(comportamientoUi.getTipoConducta().getId());
                caso.setComportamientoId(comportamientoUi.getTipoComportamientoUi().getId());
                caso.setDescripcion(comportamientoUi.getDescripcion());
                caso.setEstadoId(Caso.ESTADO_ACTUALIUZADO);
                caso.setSyncFlag(BaseEntity.FLAG_UPDATED);
                caso.update();
                saveArchivos(casoKey, repositorioFileUiList);
                success = true;
            } else {
                Caso casoCreated = new Caso();
                casoCreated.setFechaCaso(comportamientoUi.getFecha());
                casoCreated.setProgramaEducativoId(comportamientoUi.getIdprogramaEducativo());
                //casoCreated.setTipoCasoId(comportamientoUi.getTipoConducta().getId());
                casoCreated.setComportamientoId(comportamientoUi.getTipoComportamientoUi().getId());
                casoCreated.setDescripcion(comportamientoUi.getDescripcion());
                casoCreated.setDocenteId(comportamientoUi.getDocenteId());
                casoCreated.setCargaCursoId(comportamientoUi.getCargaCursoId());
                casoCreated.setCargaAcademicaId(comportamientoUi.getCargaAcademicaId());
                casoCreated.setAlumnoId(comportamientoUi.getAlumnoUi().getId());
                casoCreated.setCalendarioPeriodoId(comportamientoUi.getCalendarioPeridoId());
                casoCreated.setEstadoId(Caso.ESTADO_CREADO);
                casoCreated.setSyncFlag(BaseEntity.FLAG_ADDED);
                casoCreated.save();
                casoKey = casoCreated.getKey();
                //consulto si existe caso reporte
                if (destinoUi.getDestinosIds().size() > 0) {

                    GeoRefOrganigrama geoRefOrganigrama = SQLite.select().from(GeoRefOrganigrama.class)
                            .where(GeoRefOrganigrama_Table.geoReferenciaId.withTable().eq(destinoUi.getGeoreferenciaId()))
                            .and(GeoRefOrganigrama_Table.activo.withTable().eq(true)).querySingle();

                    if (geoRefOrganigrama != null) {
                        for (Integer usuairoId : destinoUi.getDestinosIds()) {
                            Log.d(TAG, "usuairoId destinos: " + usuairoId);
                            CasoReporte casoReporte = new CasoReporte();
                            casoReporte.setCasoId(casoCreated.getKey());
                            casoReporte.setUsuarioDestinoId(usuairoId);
                            casoReporte.setOrganigramaId(geoRefOrganigrama.getOrganigramaId());
                            casoReporte.setSyncFlag(BaseEntity.FLAG_ADDED);
                            casoReporte.save();
                        }
                    }

                }
                saveArchivos(casoKey, repositorioFileUiList);
                success = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return success;

    }

    public void saveArchivos(String keyCaso, List<RepositorioFileUi> repositorioFileUiList) {

        try {
            Where<CasoArchivo> casoArchivoWhere = SQLite.select()
                    .from(CasoArchivo.class)
                    .where(CasoArchivo_Table.casoId.eq(keyCaso));

            List<CasoArchivo> casoArchivoList = casoArchivoWhere.queryList();

            if (!casoArchivoList.isEmpty()) {
                SQLite.delete().from(CasoArchivo.class)
                        .where(CasoArchivo_Table.casoId.eq(keyCaso))
                        //.and(CasoArchivo_Table.key.in(casoArchivoWhere))
                        .execute();
            }

            for (RepositorioFileUi repositorioFileUi : repositorioFileUiList) {
                CasoArchivo casoArchivo = new CasoArchivo();
                casoArchivo.setCasoId(keyCaso);
                casoArchivo.setNombre(repositorioFileUi.getNombreArchivo());
                casoArchivo.setLocalPath(repositorioFileUi.getPath());
                casoArchivo.setPath(repositorioFileUi.getUrl());
                switch (repositorioFileUi.getTipoFileU()) {
                    case IMAGEN:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_IMAGEN);
                        break;
                    case VIDEO:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_VIDEO);
                        break;
                    case HOJA_CALCULO:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_HOJA_CALCULO);
                        break;
                    case AUDIO:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_AUDIO);
                        break;
                    case DIAPOSITIVA:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_DIAPOSITIVA);
                        break;
                    case DOCUMENTO:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_DOCUMENTO);
                        break;
                    case PDF:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_PDF);
                        break;
                    default:
                        casoArchivo.setTipoId(CasoArchivo.TIPO_IMAGEN);
                        break;
                }
                casoArchivo.setSyncFlag(BaseEntity.FLAG_ADDED);
                casoArchivo.save();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TipoUi> getTipos(int georeferenciaId, int entidadId) {
        Log.d(TAG, "georeferenciaId "+ georeferenciaId+ " entidadId "+entidadId);

        try {
            Set<Comportamiento> comportamientoLinkedHashSet = new LinkedHashSet<Comportamiento>();

            From<Comportamiento> comportamientoFrom = SQLite.select(Utils.f_allcolumnTable(Comportamiento_Table.ALL_COLUMN_PROPERTIES))
                    .from(Comportamiento.class)
                    .innerJoin(TipoEntidadGeo.class)
                    .on(TipoEntidadGeo_Table.comportamientoId.withTable()
                            .eq(Comportamiento_Table.comportamientoId.withTable()));


            comportamientoLinkedHashSet.addAll(comportamientoFrom
                        .where(TipoEntidadGeo_Table.georeferenciaId.withTable().eq(georeferenciaId))
                        .queryList());

                List<Integer> entidadIdList = new ArrayList<>();
                int entidadSearch = entidadId;
                boolean estado = true;

                entidadIdList.add(entidadSearch);

                while (estado){

                    Entidad entidad = SQLite.select()
                            .from(Entidad.class)
                            .where(Entidad_Table.entidadId.eq(entidadSearch))
                            .querySingle();

                    if(entidad==null||entidad.getParentId()==0){
                        estado = false;
                    }else {
                        entidadSearch = entidad.getParentId();
                        entidadIdList.add(entidadSearch);
                        Log.d(TAG,"entidadSearch: " + entidadSearch);
                    }
                }

            comportamientoLinkedHashSet.addAll(comportamientoFrom
                        .where(TipoEntidadGeo_Table.entidadId.withTable().in(entidadIdList))
                        .queryList());



            List<Integer> listaIntegerPadres = new ArrayList<>();
            List<TipoComportamientoUi> tipoComportamientoUiList = new ArrayList<>();
            Log.d(TAG, "comportamientoLinkedHashSet "+ comportamientoLinkedHashSet.size());
            for (Comportamiento comportamiento : comportamientoLinkedHashSet) {

                Tipos tipo = SQLite.select().from(Tipos.class).where(Tipos_Table.tipoId.withTable().eq(comportamiento.getTipoCasoId())).querySingle();
                if (tipo != null) {
                    TipoComportamientoUi tipoComportamientoUi = new TipoComportamientoUi();
                    tipoComportamientoUi.setId(comportamiento.getComportamientoId());
                    tipoComportamientoUi.setTitulo(comportamiento.getNombre());
                    tipoComportamientoUi.setCantidad(comportamiento.getCantidad());
                    tipoComportamientoUi.setDescripcion(comportamiento.getDescripcion());
                    tipoComportamientoUi.setTipoId(tipo.getTipoId());
                    tipoComportamientoUiList.add(tipoComportamientoUi);
                    int position = listaIntegerPadres.indexOf(comportamiento.getTipoCasoId());
                    if (position == -1) listaIntegerPadres.add(comportamiento.getTipoCasoId());
                }
            }
            //traer los padres
            List<TipoUi> listaTipoUisPadres = new ArrayList<>();
            List<Tipos> tiposPadres = SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES))
                    .from(Tipos.class).where(Tipos_Table.tipoId.withTable().in(listaIntegerPadres)).queryList();

                for (Tipos tipos : tiposPadres) {
                    TipoUi tipoUi = new TipoUi();
                    tipoUi.setHerencia(TipoUi.Herencia.PADRE);
                    tipoUi.setNombre(tipos.getNombre());
                    tipoUi.setId(tipos.getTipoId());

                    List<TipoComportamientoUi> comportamientoUiList = new ArrayList<>();
                    for(TipoComportamientoUi tipoComportamientoUi :  tipoComportamientoUiList){
                        if(tipoUi.getId()==tipoComportamientoUi.getTipoId()){
                            comportamientoUiList.add(tipoComportamientoUi);
                        }
                    }
                    tipoUi.setTipoUiListHijos(comportamientoUiList);

                    listaTipoUisPadres.add(tipoUi);
            }
            Log.d(TAG,"getTiposPadres "+listaTipoUisPadres.size());

            return listaTipoUisPadres;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ComportamientoUi getComportamiento(String idComportamiento) {
        ComportamientoUi comportamientoUi = new ComportamientoUi();
        try {
            Caso caso = SQLite.select(Utils.f_allcolumnTable(Caso_Table.ALL_COLUMN_PROPERTIES))
                    .from(Caso.class).where(Caso_Table.key.withTable().eq(idComportamiento)).querySingle();
            if (caso != null) {
                comportamientoUi.setId(caso.key);
                comportamientoUi.setCalendarioPeridoId(caso.getCalendarioPeriodoId());
                comportamientoUi.setCargaCursoId(caso.getCargaCursoId());
                comportamientoUi.setDocenteId(caso.getDocenteId());
                comportamientoUi.setIdprogramaEducativo(caso.getProgramaEducativoId());
                comportamientoUi.setFecha(caso.getFechaCaso());
                comportamientoUi.setDescripcion(caso.getDescripcion());
                List<ArchivoUi> archivoUiList = new ArrayList<>();
                List<CasoArchivo> casoArchivoList = SQLite.select()
                        .from(CasoArchivo.class)
                        .where(CasoArchivo_Table.casoId.eq(caso.getKey()))
                        .orderBy(CasoArchivo_Table.nombre.asc())
                        .queryList();
                for (CasoArchivo casoArchivo : casoArchivoList) {
                    ArchivoUi archivoUi = new ArchivoUi();
                    archivoUi.setArchivoId(casoArchivo.getArchivoCasoId());
                    archivoUi.setNombreArchivo(casoArchivo.getNombre());
                    archivoUi.setNombreRecurso(casoArchivo.getNombre());
                    switch (casoArchivo.getTipoId()) {
                        case CasoArchivo.TIPO_IMAGEN:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                            break;
                        case CasoArchivo.TIPO_VIDEO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                            break;
                        case CasoArchivo.TIPO_DOCUMENTO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                            break;
                        case CasoArchivo.TIPO_AUDIO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                            break;
                        case CasoArchivo.TIPO_HOJA_CALCULO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                            break;
                        case CasoArchivo.TIPO_DIAPOSITIVA:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                            break;
                        case CasoArchivo.TIPO_PDF:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.PDF);
                            break;
                    }

                    archivoUi.setUrl(casoArchivo.getPath());
                    archivoUi.setPath(casoArchivo.getLocalPath());
                    if (TextUtils.isEmpty(casoArchivo.getLocalPath())) {
                        archivoUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                    } else {
                        archivoUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                    }
                    archivoUi.setSelect(true);
                    archivoUiList.add(archivoUi);
                }
                comportamientoUi.setArchivoUiList(archivoUiList);
                //traer alumno
                AlumnoUi alumnoUi = getalumno(caso.getAlumnoId());
                alumnoUi.setCursoUi(getCursoUi(caso.getCargaCursoId()));
                comportamientoUi.setAlumnoUi(alumnoUi);
                //traer tipo caso
                Comportamiento comportamiento = SQLite.select(Utils.f_allcolumnTable(Comportamiento_Table.ALL_COLUMN_PROPERTIES)).from(Comportamiento.class)
                        .where(Comportamiento_Table.comportamientoId.withTable().eq(caso.getComportamientoId())).querySingle();


                if (comportamiento != null) {

                    TipoComportamientoUi tipoComportamientoUi = new TipoComportamientoUi();
                    tipoComportamientoUi.setId(comportamiento.getComportamientoId());
                    tipoComportamientoUi.setTitulo(comportamiento.getNombre());
                    tipoComportamientoUi.setCantidad(comportamiento.getCantidad());
                    tipoComportamientoUi.setDescripcion(comportamiento.getDescripcion());
                    tipoComportamientoUi.setSelected(false);

                    comportamientoUi.setTipoComportamientoUi(tipoComportamientoUi);


                    Tipos tippadre = SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES)).from(Tipos.class)
                            .where(Tipos_Table.tipoId.withTable().eq(comportamiento.getTipoCasoId())).querySingle();

                    if (tippadre != null) {
                        TipoUi tipoUiPadre = new TipoUi();
                        tipoUiPadre.setSelected(true);
                        tipoUiPadre.setId(tippadre.getTipoId());
                        tipoUiPadre.setConcepto(tippadre.getConcepto());
                        tipoUiPadre.setNombre(tippadre.getNombre());
                        tipoUiPadre.setObjeto(tippadre.getObjeto());
                        tipoUiPadre.setHerencia(TipoUi.Herencia.PADRE);
                        //padre
                        comportamientoUi.setTipoConducta(tipoUiPadre);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comportamientoUi;
    }

    @Override
    public ParametroDisenioUi getParametroDisenio(int idcargaCurso) {
        try {
            ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(idcargaCurso);
            if (parametrosDisenio != null) {
                parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
                parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
                parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
                parametroDisenioUi.setConcepto(parametrosDisenio.getConcepto());
                parametroDisenioUi.setNombre(parametrosDisenio.getNombre());
                parametroDisenioUi.setObjeto(parametrosDisenio.getObjeto());
            }
            return parametroDisenioUi;
        } catch (Exception e) {
            e.printStackTrace();
            return new ParametroDisenioUi();
        }
    }

    @Override
    public void getUsuariosDestino(int georeferenciaId, SucessCallback<List<UsuarioUi>> sucessCallback) {
        List<UsuarioUi> usuarioUiList = getUsuariosGeoreferncia(georeferenciaId);
        Log.d(TAG, "getUsuariosDestino size " + usuarioUiList.size());
        sucessCallback.onLoad(true, usuarioUiList);

    }


    public List<UsuarioUi> getUsuariosGeoreferncia(int georeferenciaId) {

        Log.d(TAG, "georeferenciaId " + georeferenciaId);

        List<UsuarioUi> usuarioUiList = new ArrayList<>();
        try {
            GeoRefOrganigrama geoRefOrganigrama = SQLite.select()
                    .from(GeoRefOrganigrama.class)
                    .where(GeoRefOrganigrama_Table.geoReferenciaId.withTable().eq(georeferenciaId))
                    .and(GeoRefOrganigrama_Table.activo.withTable().eq(true)).querySingle();


            if (geoRefOrganigrama != null) {
                List<PersonaGeoOrg> personaGeoOrgList = SQLite.select().from(PersonaGeoOrg.class)
                        .where(PersonaGeoOrg_Table.geoRefOrganigramaId.withTable().eq(geoRefOrganigrama.getId())).queryList();
                if (personaGeoOrgList != null) {
                    for (PersonaGeoOrg personaGeoOrg : personaGeoOrgList) {
                        Log.d(TAG, "persona " + personaGeoOrg.getPersonaId());
                        UsuarioUi usuarioUi = new UsuarioUi();
                        Usuario usuario = SQLite.select().from(Usuario.class)
                                .where(Usuario_Table.personaId.withTable().eq(personaGeoOrg.getPersonaId())).querySingle();
                        if (usuario != null) {
                            Persona persona = SQLite.select().from(Persona.class)
                                    .where(Persona_Table.personaId.withTable().eq(personaGeoOrg.getPersonaId())).querySingle();
                            if (persona != null) {
                                usuarioUi.setUsuarioId(usuario == null ? 0 : usuario.getUsuarioId());
                                usuarioUi.setPersonaId(persona.getPersonaId());
                                usuarioUi.setApellidoPersona(persona.getApellidos());
                                usuarioUi.setNombrePersona(persona.getFirstName());
                                usuarioUi.setUrlpicture(persona.getUrlPicture());
                                usuarioUi.setEnabled(true);
                                usuarioUiList.add(usuarioUi);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioUiList;
    }

    @Override
    public DestinoUi getDestino(String idComportamiento, int georeferenciaId) {
        Log.d(TAG, "getDestino" + idComportamiento);
        try {
            DestinoUi destinoUi = new DestinoUi();
            List<CasoReporte> casoReporteList = SQLite.select().from(CasoReporte.class)
                    .where(CasoReporte_Table.casoId.withTable().eq(idComportamiento)).queryList();

            Caso caso = SQLite.select().from(Caso.class).where(Caso_Table.key.withTable().eq(idComportamiento)).querySingle();
            if (caso != null) {
                List<DestinoUi.Tipo> tipoList = new ArrayList<>();
                List<UsuarioUi> usuarioUiList = new ArrayList<>();

                for (CasoReporte casoReporte : casoReporteList) {
                    Log.d(TAG, "caso reporte key " + casoReporte.key);
                    Usuario usuario = SQLite.select().from(Usuario.class)
                            .where(Usuario_Table.usuarioId.withTable().eq(casoReporte.getUsuarioDestinoId())).querySingle();
                    if (usuario != null) {

                        Relaciones relacion = SQLite.select().from(Relaciones.class)
                                .innerJoin(Usuario.class)
                                .on(Relaciones_Table.personaVinculadaId.withTable().eq(Usuario_Table.personaId.withTable()))
                                .innerJoin(CasoReporte.class)
                                .on(Usuario_Table.usuarioId.withTable().eq(CasoReporte_Table.usuarioDestinoId.withTable()))
                                .where(CasoReporte_Table.key.withTable().eq(casoReporte.key))
                                .and(Relaciones_Table.personaVinculadaId.withTable().eq(usuario.getPersonaId()))
                                .and(Relaciones_Table.personaPrincipalId.withTable().eq(caso.getAlumnoId())).querySingle();

                        if (getTutor(caso.getCargaAcademicaId(), casoReporte.key) != null)
                            tipoList.add(DestinoUi.Tipo.TUTOR);

                        if (relacion != null)
                            if (relacion.getTipoId() == Relaciones.MADRE || relacion.getTipoId() == Relaciones.PADRE)
                                tipoList.add(DestinoUi.Tipo.PADRES);

                        Contrato contrato = SQLite.select().from(Contrato.class).
                                innerJoin(Usuario.class)
                                .on(Contrato_Table.apoderadoId.withTable().eq(Usuario_Table.personaId.withTable()))
                                .innerJoin(CasoReporte.class)
                                .on(Usuario_Table.usuarioId.withTable().eq(CasoReporte_Table.usuarioDestinoId.withTable()))
                                .where(CasoReporte_Table.key.withTable().eq(casoReporte.key))
                                .and(Usuario_Table.usuarioId.withTable().eq(casoReporte.getUsuarioDestinoId())).querySingle();

                        if (contrato != null) tipoList.add(DestinoUi.Tipo.ADODERADO);
                    }

                }
                //Listar usuarios del organigrama
                List<UsuarioUi> usuariosOrgaLIst = getUsuariosGeoreferncia(georeferenciaId);
                for (UsuarioUi usuarioOrga : usuariosOrgaLIst) {
                    Persona persona = SQLite.select().
                            from(Persona.class)
                            .where(Persona_Table.personaId.withTable().eq(usuarioOrga.getPersonaId())).querySingle();
                    Log.d(TAG, "persona " + persona.getPersonaId());
                    if (persona != null) {
                        UsuarioUi usuarioUi = new UsuarioUi();
                        CasoReporte casoReporteSeletdeU = SQLite.select().
                                from(CasoReporte.class).
                                innerJoin(Caso.class)
                                .on(CasoReporte_Table.casoId.withTable().eq(Caso_Table.key.withTable()))
                                .where(CasoReporte_Table.usuarioDestinoId.withTable().eq(usuarioOrga.getUsuarioId()))
                                .and(CasoReporte_Table.casoId.withTable().eq(caso.key)).querySingle();
                        if (casoReporteSeletdeU != null) usuarioUi.setSelected(true);
                        Log.d(TAG, "PERSONA NMBRE " + persona.getApellidos());
                        usuarioUi.setPersonaId(persona.getPersonaId());
                        usuarioUi.setApellidoPersona(persona.getApellidos());
                        usuarioUi.setUsuarioId(usuarioOrga.getUsuarioId());
                        usuarioUi.setNombrePersona(persona.getFirstName());
                        usuarioUi.setUrlpicture(persona.getUrlPicture());
                        usuarioUi.setEnabled(false);
                        usuarioUiList.add(usuarioUi);
                    }

                }

                destinoUi.setUsuarioUiList(usuarioUiList);
                destinoUi.setTipos(tipoList);
            }
            return destinoUi;
        } catch (Exception e) {
            e.printStackTrace();
            return new DestinoUi();
        }
    }

    public CargaAcademica getTutor(int cargaAcademicaId, String casoReporteId) {

        CargaAcademica cargaAcademica = SQLite.select().from(CargaAcademica.class)
                .innerJoin(Empleado.class)
                .on(CargaAcademica_Table.idEmpleadoTutor.withTable().eq(Empleado_Table.empleadoId.withTable()))
                .innerJoin(Usuario.class)
                .on(Empleado_Table.personaId.withTable().eq(Usuario_Table.personaId.withTable()))
                .innerJoin(CasoReporte.class)
                .on(Usuario_Table.usuarioId.withTable().eq(CasoReporte_Table.usuarioDestinoId.withTable()))
                .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId))
                .and(CasoReporte_Table.key.withTable().eq(casoReporteId))
                .querySingle();
        return cargaAcademica;
    }

    public Usuario getUsuario(int idempleado) {

        Empleado empleado = SQLite.select().from(Empleado.class)
                .where(Empleado_Table.empleadoId.withTable().eq(idempleado)).querySingle();
        if (empleado == null) return new Usuario();

        Persona tutorPersona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.withTable().eq(empleado.getPersonaId()))
                .querySingle();
        if (tutorPersona == null) return new Usuario();

        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.personaId.withTable().eq(tutorPersona.getPersonaId()))
                .querySingle();

        return usuario;
    }

    @Override
    public List<Integer> validarUsuario(DestinoUi.Tipo tipo, int alumnoId, int cargaAcademicaId, int georeferenciaId) {
         Log.d(TAG, "cargaAcademicaId"+ cargaAcademicaId + "georeferenciaId: " + georeferenciaId + "alumnoId: " + alumnoId);
        GeoRefOrganigrama geoRefOrganigrama = SQLite.select().from(GeoRefOrganigrama.class)
                .where(GeoRefOrganigrama_Table.geoReferenciaId.withTable().eq(georeferenciaId))
                .and(GeoRefOrganigrama_Table.activo.withTable().eq(true)).querySingle();

        if(geoRefOrganigrama==null)return  null;
        List<Integer> destinosList = new ArrayList<>();
        switch (tipo) {
            case PADRES:
                List<Usuario> usuarioList = alumnoDao.getPadres(alumnoId);
                for (Usuario u : usuarioList) destinosList.add(u.getUsuarioId());
                break;
            case ADODERADO:
                Usuario usuario = alumnoDao.getApoderado(alumnoId);
                if (usuario.getUsuarioId() != 0) destinosList.add(usuario.getUsuarioId());
                break;
            default:
                Usuario usuarioTutor = alumnoDao.getTutor(cargaAcademicaId);
                if (usuarioTutor.getUsuarioId() != 0) destinosList.add(usuarioTutor.getUsuarioId());
                break;
        }
        Log.d(TAG, "destinosList "+ destinosList.size() );
        return destinosList;
    }

    @Override
    public void getComporamientosDestinosList(int docenteId, int alumnoId, SucessCallback<List<ComportamientoUi>> callback) {
        try {
            Usuario usuario = getUsuario(docenteId);
            List<Caso> casoListReporte = SQLite.select().from(Caso.class).
                    innerJoin(CasoReporte.class)
                    .on(CasoReporte_Table.casoId.withTable().eq(Caso_Table.key.withTable()))
                    .where(Caso_Table.alumnoId.withTable().eq(alumnoId))
                    .and(CasoReporte_Table.usuarioDestinoId.withTable().eq(usuario.getUsuarioId())).queryList();
            // Log.d(TAG, "casoList"+ casoListReporte.size());
            List<ComportamientoUi> comportamientoUiList = new ArrayList<>();
            for (Caso caso : casoListReporte) {
                ComportamientoUi comportamientoUi = new ComportamientoUi();
                comportamientoUi.setId(caso.key);
                comportamientoUi.setDescripcion(caso.getDescripcion());
                comportamientoUi.setFecha(caso.getFechaCaso());
                //comportamientoUi.setTipoConducta(getTipoUiHijo(caso.getTipoCasoId()));
                comportamientoUi.setIdprogramaEducativo(caso.getProgramaEducativoId());
                comportamientoUi.setDocenteId(caso.getDocenteId());
                comportamientoUi.setCargaCursoId(caso.getCargaCursoId());
                comportamientoUi.setCalendarioPeridoId(caso.getCalendarioPeriodoId());
                comportamientoUi.setCargaAcademicaId(caso.getCargaAcademicaId());
                comportamientoUi.setTipo(ComportamientoUi.Tipo.DESTINO);

                Usuario usuarioorigen = SQLite.select().from(Usuario.class)
                        .where(Usuario_Table.usuarioId.withTable().eq(caso.getUsuarioCreacionId())).querySingle();

                if (usuarioorigen != null) {
                    UsuarioUi usuarioUi = new UsuarioUi();
                    Persona persona = SQLite.select().from(Persona.class).where(Persona_Table.personaId.withTable().eq(usuarioorigen.getPersonaId())).querySingle();
                    usuarioUi.setUsuarioId(usuarioorigen.getUsuarioId());
                    if (persona != null) {
                        usuarioUi.setUrlpicture(persona.getUrlPicture());
                        usuarioUi.setNombrePersona(persona.getFirstName());
                        usuarioUi.setApellidoPersona(persona.getApellidos());
                        usuarioUi.setPersonaId(persona.getPersonaId());
                    }
                    comportamientoUi.setUsuarioDestino(usuarioUi);
                }

                List<ArchivoUi> archivoUiList = new ArrayList<>();
                List<CasoArchivo> casoArchivoList = SQLite.select()
                        .from(CasoArchivo.class)
                        .where(CasoArchivo_Table.casoId.eq(caso.getKey()))
                        .orderBy(CasoArchivo_Table.nombre.asc())
                        .queryList();

                for (CasoArchivo casoArchivo : casoArchivoList) {
                    ArchivoUi archivoUi = new ArchivoUi();
                    archivoUi.setArchivoId(casoArchivo.getArchivoCasoId());
                    archivoUi.setNombreArchivo(casoArchivo.getNombre());
                    archivoUi.setNombreRecurso(casoArchivo.getNombre());
                    switch (casoArchivo.getTipoId()) {
                        case CasoArchivo.TIPO_IMAGEN:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                            break;
                        case CasoArchivo.TIPO_VIDEO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                            break;
                        case CasoArchivo.TIPO_DOCUMENTO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                            break;
                        case CasoArchivo.TIPO_AUDIO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                            break;
                        case CasoArchivo.TIPO_HOJA_CALCULO:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                            break;
                        case CasoArchivo.TIPO_DIAPOSITIVA:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                            break;
                        case CasoArchivo.TIPO_PDF:
                            archivoUi.setTipoFileU(RepositorioTipoFileU.PDF);
                            break;
                    }

                    archivoUi.setUrl(casoArchivo.getPath());
                    archivoUi.setPath(casoArchivo.getLocalPath());
                    if (TextUtils.isEmpty(casoArchivo.getLocalPath())) {
                        archivoUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                    } else {
                        archivoUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                    }
                    archivoUi.setSelect(true);
                    archivoUiList.add(archivoUi);
                }
                comportamientoUi.setArchivoUiList(archivoUiList);

                comportamientoUiList.add(comportamientoUi);
            }
            Log.d(TAG, "comportamientoUiList DESTINOS " + comportamientoUiList.size());
            callback.onLoad(true, comportamientoUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(true, new ArrayList<ComportamientoUi>());
        }

    }

    @Override
    public void updateSucessDowload(String archivoId, String path, CallbackSuccess callback) {
        CasoArchivo archivo = SQLite.select()
                .from(CasoArchivo.class)
                .where(CasoArchivo_Table.key.eq(archivoId))
                .querySingle();
        if (archivo != null) {
            archivo.setLocalPath(path);
            boolean success = archivo.save();
            callback.onLoad(success);
        } else {
            callback.onLoad(false);
        }
    }


}

