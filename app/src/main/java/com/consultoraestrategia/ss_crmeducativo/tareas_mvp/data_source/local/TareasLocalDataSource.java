package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDao;
import com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao.UnidadAprendizajeDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.UnidadAprendizajeCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.DriveFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.FormaRubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TipoRubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class TareasLocalDataSource implements TareasMvpDataSource {

    private static final String TAG = TareasLocalDataSource.class.getSimpleName();
    UnidadAprendizajeDao unidadAprendizajeDao;
    private TareasDao tareasDao;
    private ParametrosDisenioDao parametrosDisenioDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;

    public TareasLocalDataSource() {
        unidadAprendizajeDao = InjectorUtils.provideUnidadAprendizajeDao();
        tareasDao = InjectorUtils.provideTareasDao();
        parametrosDisenioDao = InjectorUtils.provideParametrosDisenioDao();
        calendarioPeriodoDao = InjectorUtils.provideCalendarioPeriodo();
    }


    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {
        for (RepositorioFileUi repositorioFileUi: repositorioFileUis){
            SQLite.update(Archivo.class)
                    .set(Archivo_Table.localpath.eq(repositorioFileUi.getPath()))
                    .where(Archivo_Table.key.eq(repositorioFileUi.getArchivoId()))
                    .execute();
        }
    }

    @Override
    public RetrofitCancel getUrlDriveArchivo(String archivoId, Callback<DriveFileUi> driveFileUiCallback) {
        return null;
    }

    @Override
    public void getTareasUIList(int idUsuario, int idCargaCurso, int tipoTarea, int sesionAprendizajeId, int calendarioPeriodoId, int anioAcademicoId, GetTareasListCallback callback) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(calendarioPeriodoId, idCargaCurso, false, databaseWrapper);

            CalendarioPeriodo calendarioPeriodo = SQLite.select()
                    .from(CalendarioPeriodo.class)
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                    .querySingle(databaseWrapper);

            boolean isEdiatar = false;

            if(calendarioPeriodo!=null)
                switch (calendarioPeriodo.getEstadoId()){
                case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                    isEdiatar = true;
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                    isEdiatar = isvigente;
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                    isEdiatar = isvigente;
                    break;
                }

            String nombreCurso = getNombreCurso(idCargaCurso);
            List<UnidadAprendizaje> unidadAprendizajeList = new ArrayList<>();
            List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList = new ArrayList<>();
            Log.d(TAG, "idCargaCurso: "+ idCargaCurso + " calendarioPeriodoId: " + calendarioPeriodoId);
            if(tipoTarea==0){
                unidadAprendizajeList.addAll(UnidadAprendizajeCargaCursoModel.SQLView()
                        .select(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(idCargaCurso, calendarioPeriodoId, anioAcademicoId)
                        .queryList(databaseWrapper));
                Log.d(TAG, "Size: "+ unidadAprendizajeList.size());
            }else {
                UnidadAprendizaje unidadAprendizaje = unidadAprendizajeDao.getUnidadAprendizajePorSesionId(sesionAprendizajeId);
                if(unidadAprendizaje!=null)unidadAprendizajeList.add(unidadAprendizaje);
            }

            for (UnidadAprendizaje unidadAprendizaje : unidadAprendizajeList) {
                HeaderTareasAprendizajeUI headerTareasAprendizajeUI = new HeaderTareasAprendizajeUI();
                headerTareasAprendizajeUI.setIdUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId());
                headerTareasAprendizajeUI.setIdSesionAprendizaje(sesionAprendizajeId);
                headerTareasAprendizajeUI.setIdSilaboEvento(unidadAprendizaje.getSilaboEventoId());
                headerTareasAprendizajeUI.setTituloSesionAprendizaje( unidadAprendizaje.getTitulo());
                headerTareasAprendizajeUI.setCalendarioEditar(isEdiatar);
                headerTareasAprendizajeUI.setDocente(true);
                List<TareasUI> tareasUIList = new ArrayList<>();
                List<TareasC> tareasList = new ArrayList<>();
                if(tipoTarea==0){
                    tareasList.addAll(tareasDao.getTareasPorUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId()));
                }else {
                    tareasList.addAll(tareasDao.getTareasPorSesionAprendizaje(sesionAprendizajeId));
                }

                for (TareasC tareas : tareasList){

                    TareasUI tareasUI = new TareasUI();
                    if (tareas.getEstadoId() == 263) {
                        tareasUI.setEstado(TareasUI.Estado.Creado);
                    }
                    if (tareas.getEstadoId() == 264) {
                        tareasUI.setEstado(TareasUI.Estado.Publicado);
                    }
                    if (tareas.getEstadoId() == 265) {
                        tareasUI.setEstado(TareasUI.Estado.Eliminado);
                    }

                    tareasUI.setKeyTarea(tareas.getKey());

                    SesionAprendizaje sesionAprendizaje = SQLite.select()
                            .from(SesionAprendizaje.class)
                            .where(SesionAprendizaje_Table.sesionAprendizajeId
                                    .eq(tareas.getSesionAprendizajeId()))
                            .querySingle();

                    tareasUI.setNombreSesion(sesionAprendizaje!=null&&tipoTarea==0?" - Sesión "+sesionAprendizaje.getNroSesion():"");
                    tareasUI.setTituloTarea(tareas.getTitulo());
                    tareasUI.setDescripcion(tareas.getInstrucciones());
                    tareasUI.setFechaCreacionTarea(tareas.getFechaCreacion());
                    tareasUI.setFechaLimite(tareas.getFechaEntrega());
                    tareasUI.setCalendarioEditar(isEdiatar);
                    tareasUI.setCalendarioVigente(isvigente);
                    tareasUI.setHoraEntrega(tareas.getHoraEntrega());
                    tareasUI.setIdUnidaddAprendizaje(tareas.getUnidadAprendizajeId());
                    tareasUI.setRubroEvalProcesoUi(getRubroEvalProcesoUi(tareas.getKey()));
                    tareasUI.setNombreCurso(nombreCurso);
                    tareasUI.setExportado(tareas.getSyncFlag()!= BaseEntity.FLAG_ADDED&&
                            tareas.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                            tareas.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                    List<TareasRecursosC> tareasRecursosList = SQLite.select()
                            .from(TareasRecursosC.class)
                            .where(TareasRecursosC_Table.tareaId.is(tareas.getKey()))
                            .queryList(databaseWrapper);

                    List<RecursosUI> recursosUIList = new ArrayList<>();
                    for (TareasRecursosC tareasRecursos : tareasRecursosList) {

                        RecursoDidacticoEventoC recursoDidacticoEvento = SQLite.select()
                                .from(RecursoDidacticoEventoC.class)
                                .where(RecursoDidacticoEventoC_Table.key.eq(tareasRecursos.getRecursoDidacticoId()))
                                .and(RecursoDidacticoEventoC_Table.estado.eq(1))
                                .querySingle(databaseWrapper);

                        if (recursoDidacticoEvento != null) {
                            RecursosUI recursosUI = new RecursosUI();
                            recursosUI.setTarea(tareasUI);
                            recursosUI.setRecursoId(tareasRecursos.getRecursoDidacticoId());
                            recursosUI.setNombreRecurso(recursoDidacticoEvento.getTitulo());
                            // recursosUI.setNombre(recursoDidacticoEvento.getTitulo());
                            recursosUI.setDescripcion(recursoDidacticoEvento.getDescripcion());
                            recursosUI.setFechaCreacionRecuros(recursoDidacticoEvento.getFechaCreacion());
                            boolean isYoutube = false;
                            switch (recursoDidacticoEvento.getTipoId()) {
                                case 379://video
                                    isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursoDidacticoEvento.getUrl()));
                                    if(!isYoutube){
                                        isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursosUI.getDescripcion()));
                                        if(isYoutube){
                                            recursosUI.setUrl(recursosUI.getDescripcion());
                                        }
                                    }
                                    if(isYoutube){
                                        recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                                        recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                                        recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                        recursosUI.setTipoFileU(RepositorioTipoFileU.YOUTUBE);
                                    }else {
                                        recursosUI.setTipoFileU(RepositorioTipoFileU.VIDEO);
                                    }
                                    break;
                                case 380://vinculo
                                    recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                                    recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                                    break;
                                case 397://Documento de texto
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                                    break;
                                case 398://Imagen
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                                    break;
                                case 399://Audio
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.AUDIO);
                                    break;
                                case 400://Hoja de Cálculo
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                                    break;
                                case 401://Diapositiva
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                                    break;
                                case 402://PDF
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.PDF);
                                    break;
                                case 403://Materiales
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.MATERIALES);
                                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                    break;
                            }
                            // Log.d(TAG,"archivo:( " + recursoDidacticoEvento.getUrl());
                            if (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_AUDIO||
                                    recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DIAPOSITIVA||
                                    recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DOCUMENTO||
                                    recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_HOJA_CALCULO||
                                    recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_IMAGEN||
                                    recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_PDF||
                                    (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO
                                            && !isYoutube)
                            ){

                                Archivo archivo = SQLite.select(Utils.f_allcolumnTable(Archivo_Table.ALL_COLUMN_PROPERTIES))
                                        .from(Archivo.class)
                                        .innerJoin(RecursoArchivo.class)
                                        .on(Archivo_Table.key.withTable().eq(RecursoArchivo_Table.archivoId.withTable()))
                                        .where(RecursoArchivo_Table.recursoDidacticoId.withTable().eq(recursoDidacticoEvento.getKey()))
                                        .querySingle(databaseWrapper);

                                Log.d(TAG,"archivo:(");
                                if(archivo!=null){
                                    Log.d(TAG,"great");
                                    recursosUI.setArchivoId(archivo.getKey());
                                    recursosUI.setNombreArchivo(archivo.getNombre());
                                    recursosUI.setPath(archivo.getLocalpath());
                                    recursosUI.setUrl(archivo.getPath());
                                    recursosUI.setFechaAccionArchivo(archivo.getFechaAccion());
                                    //recursosUI.setDescripcion(recursoDidacticoEvento.getDescripcion());
                                    if(TextUtils.isEmpty(archivo.getLocalpath())){
                                        recursosUI.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                                    }else {
                                        recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                    }
                                    recursosUI.setUrl(archivo.getPath());
                                    recursosUI.setPath(archivo.getLocalpath());
                                }else if(recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO){
                                    recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                                    recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                    recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                                    recursosUI.setFechaAccionArchivo(recursoDidacticoEvento.getFechaCreacion());
                                }

                            }else {
                                String url = recursoDidacticoEvento.getUrl();
                                if(TextUtils.isEmpty(url))url = recursoDidacticoEvento.getDescripcion();
                                recursosUI.setUrl(url);
                                recursosUI.setFechaAccionArchivo(recursoDidacticoEvento.getFechaCreacion());
                            }

                            recursosUIList.add(recursosUI);
                        }
                    }
                    tareasUI.setRecursosUIList(recursosUIList);
                    tareasUIList.add(tareasUI);
                }
                Collections.reverse(tareasUIList);
                headerTareasAprendizajeUI.setTareasUIList(tareasUIList);
                headerTareasAprendizajeUIList.add(headerTareasAprendizajeUI);
            }
            Log.d(TAG, "headerTareasAprendizajeUIList Size: "+ headerTareasAprendizajeUIList.size());

            databaseWrapper.setTransactionSuccessful();
            callback.onTareasListLoaded(headerTareasAprendizajeUIList);
        } catch (Exception e){
            e.printStackTrace();
            callback.onTareasListLoaded(new ArrayList<HeaderTareasAprendizajeUI>());
        }finally {
            databaseWrapper.endTransaction();
        }



    }


    private String getNombreCurso(int idCargaCurso){

        CargaCursos cargaCurso = SQLite.select()
                .from(CargaCursos.class)
                .where(CargaCursos_Table.cargaCursoId.eq(idCargaCurso))
                .querySingle();

        int planCursoId = 0;
        int cargaAcademicaId = 0;
        if(cargaCurso!=null){
            planCursoId = cargaCurso.getPlanCursoId();
            cargaAcademicaId = cargaCurso.getCargaAcademicaId();
        }

        Cursos curso = SQLite.select(Utils.f_allcolumnTable(Cursos_Table.ALL_COLUMN_PROPERTIES))
                .from(Cursos.class)
                .innerJoin(PlanCursos.class)
                .on(Cursos_Table.cursoId.withTable().eq(PlanCursos_Table.cursoId.withTable()))
                .where(PlanCursos_Table.planCursoId.withTable().eq(planCursoId))
                .querySingle();


        Seccion seccion = SQLite.select()
                .from(Seccion.class)
                .innerJoin(CargaAcademica.class)
                .on(Seccion_Table.seccionId.withTable().eq(CargaAcademica_Table.seccionId.withTable()))
                .where(CargaAcademica_Table.cargaAcademicaId.eq(cargaAcademicaId))
                .querySingle();

        Periodo periodo = SQLite.select()
                .from(Periodo.class)
                .innerJoin(CargaAcademica.class)
                .on(Periodo_Table.periodoId.withTable().eq(CargaAcademica_Table.periodoId.withTable()))
                .where(CargaAcademica_Table.cargaAcademicaId.eq(cargaAcademicaId))
                .querySingle();

        String aliasPeriodo = "";
        String nombreSeccion = "";
        String cursoNombre = "";
        if(periodo != null)aliasPeriodo = periodo.getAlias();
        if(seccion != null)nombreSeccion = seccion.getNombre();
        if(curso != null)cursoNombre = curso.getNombre();
        return cursoNombre +" "+aliasPeriodo+" "+nombreSeccion;
    }

    @Override
    public void getParametroDisenio(int parametroDisenioId, CallbackTareas callbackTareas) {

        try {
            Log.d(TAG,"parametroDisenioId: " +parametroDisenioId);
            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.get(parametroDisenioId);
            if(parametrosDisenio==null)parametrosDisenio = parametrosDisenioDao.obtenerDefalut();
            ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
            parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametroDisenioUi.getColor2());
            parametroDisenioUi.setNombre(parametrosDisenio.getNombre());
            parametroDisenioUi.setParametroDisenioId(parametrosDisenio.getParametroDisenioId());

            callbackTareas.onParametroDisenio(parametroDisenioUi, 1);

        }catch (Exception e){
            e.printStackTrace();
            callbackTareas.onParametroDisenio(null, 1);
        }
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {
        Archivo archivo = SQLite.select()
                .from(Archivo.class)
                .where(Archivo_Table.key.eq(archivoId))
                .querySingle();
        if(archivo!=null){
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            callback.onLoad(success, success);
        }else {
            callback.onLoad(false, false);
        }
    }

    private RubroEvalProcesoUi getRubroEvalProcesoUi(String tareaId) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.tareaId.withTable().eq(tareaId))
                .querySingle();

        if (rubroEvaluacionProcesoC == null) return null;
        RubroEvalProcesoUi rubroEvalProcesoUi = new RubroEvalProcesoUi();
        String evaluados = unidadAprendizajeDao.getCantidadAlumnosEvaluadosTarea(rubroEvaluacionProcesoC.getKey());
        rubroEvalProcesoUi.setCantidadEvaluados(evaluados);
        rubroEvalProcesoUi.setId(rubroEvaluacionProcesoC.getKey());

        switch (rubroEvaluacionProcesoC.getTiporubroid()){
            case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                rubroEvalProcesoUi.setTipoRubroEvalProcesoUi(TipoRubroEvalProcesoUi.BIDIMENCIONAL);
                break;
            case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                rubroEvalProcesoUi.setTipoRubroEvalProcesoUi(TipoRubroEvalProcesoUi.UNIDIMENCIONAL);
                break;
        }

        switch (rubroEvaluacionProcesoC.getFormaEvaluacionId()) {
            case RubroEvaluacionProcesoC.FORMAEVAL_INDIVIDUAL:
                rubroEvalProcesoUi.setFormaRubroEvalProcesoUi(FormaRubroEvalProcesoUi.INDIVIDUAL);
                break;
            case RubroEvaluacionProcesoC.FORMAEVAL_GRUPAL:
                rubroEvalProcesoUi.setFormaRubroEvalProcesoUi(FormaRubroEvalProcesoUi.GRUPAL);
                break;
        }

        rubroEvalProcesoUi.setTitulo(rubroEvaluacionProcesoC.getTitulo());
        return rubroEvalProcesoUi;
    }

}


