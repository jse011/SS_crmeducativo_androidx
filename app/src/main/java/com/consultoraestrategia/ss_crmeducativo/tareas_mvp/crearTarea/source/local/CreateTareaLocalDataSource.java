package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.local;


import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.recursoDidacticoEventoDao.RecursoDidacticoEventoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasRecursoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.CreateTareaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.CrearTareaCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.EliminarRecursoCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTareaLocalDataSource implements CreateTareaDataSource {

    private static final String TAG = CreateTareaLocalDataSource.class.getSimpleName();
    private TareasDao tareasDao;
    private RecursoDidacticoEventoDao recursoDidacticoEventoDao;
    private TareasRecursoDao tareasRecursosDao;

    public CreateTareaLocalDataSource(TareasDao tareasDao, RecursoDidacticoEventoDao recursoDidacticoEventoDao, TareasRecursoDao tareasRecursosDao) {
        this.tareasDao = tareasDao;
        this.recursoDidacticoEventoDao = recursoDidacticoEventoDao;
        this.tareasRecursosDao = tareasRecursosDao;
    }

    @Override
    public void crearTarea(CrearTareaUseCase.RequestValues requestValues, CrearTareaCallback callback) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TareasC tarea = null;
            TareasUI.Estado estado = TareasUI.Estado.Creado;
            if(requestValues.getTareasUI()!=null){
              tarea = tareasDao.get(requestValues.getTareasUI().getKeyTarea());
                estado = requestValues.getTareasUI().getEstado();
            }
            if(tarea==null){
                tarea = new TareasC();
                tarea.setUnidadAprendizajeId(requestValues.getIdUnidadAprendizaje());
                tarea.setSesionAprendizajeId(requestValues.getIdSesionAprendizaje());
                tarea.setSyncFlag(BaseEntity.FLAG_ADDED);
                tarea.setEstadoId(263);
            }else {
                Log.d("creandoTarea", "as"+requestValues.getEstadoTarea());
                switch (estado){
                    case Creado:
                        tarea.setEstadoId(263);
                        break;
                    case Eliminado:
                        tarea.setEstadoId(265);
                        break;
                    case Publicado:
                        tarea.setEstadoId(264);
                        break;
                }
                tarea.setSyncFlag(TareasC.FLAG_UPDATED);
            }

            tarea.setTitulo(requestValues.getTitulo());
            tarea.setInstrucciones(requestValues.getInstruciones());
            tarea.setFechaEntrega(requestValues.getFechaEntrega());
            tarea.setHoraEntrega(requestValues.getHoraEntrega());
            tarea.save(databaseWrapper);

            List<TareasRecursosC> tareasRecursosCList = SQLite.select()
                    .from(TareasRecursosC.class)
                    .where(TareasRecursosC_Table.tareaId.eq(tarea.getKey()))
                    .queryList();

            List<String> recursoIdStringList = new ArrayList<>();
            for (TareasRecursosC tareasRecursosC: tareasRecursosCList)recursoIdStringList.add(tareasRecursosC.getRecursoDidacticoId());
            SQLite.delete()
                    .from(RecursoArchivo.class)
                    .where(RecursoArchivo_Table.recursoDidacticoId.in(recursoIdStringList))
                    .execute(databaseWrapper);

            SQLite.delete()
                    .from(TareasRecursosC.class)
                    .where(TareasRecursosC_Table.tareaId.eq(tarea.getKey()))
                    .execute(databaseWrapper);

            List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
            if(requestValues.getRecursosUIList() != null)repositorioFileUiList.addAll(requestValues.getRecursosUIList());
            for (RepositorioFileUi recursosUI: repositorioFileUiList){
                int tipoArchivoId = 0;
                switch (recursosUI.getTipoFileU()){
                    case YOUTUBE:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_VIDEO;
                        break;
                    case VINCULO:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_VINCULO;
                        break;
                    case PDF:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_PDF;
                        break;
                    case AUDIO:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_AUDIO;
                        break;
                    case VIDEO:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_VIDEO;
                        break;
                    case IMAGEN:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_IMAGEN;
                        break;
                    case MATERIALES:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_VINCULO;
                        break;
                    case COMPRESS:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_VINCULO;
                        break;
                    case DOCUMENTO:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_DOCUMENTO;
                        break;
                    case DIAPOSITIVA:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_DIAPOSITIVA;
                        break;
                    case HOJA_CALCULO:
                        tipoArchivoId = RecursoDidacticoEventoC.TIPO_HOJA_CALCULO;
                        break;
                }

                PlanCursos planCursos = PlanCursos.getPlancursoPorCurso(requestValues.getmIdCurso());
                RecursoDidacticoEventoC recursoDidacticoEventoC = null;
                if(recursosUI instanceof RecursosUI){
                    Log.d(TAG,"cast RecursosUI");
                    recursoDidacticoEventoC = SQLite.select()
                            .from(RecursoDidacticoEventoC.class)
                            .where(RecursoDidacticoEventoC_Table.key.eq(((RecursosUI)recursosUI).getRecursoId()))
                            .querySingle(databaseWrapper);
                }


                if(recursoDidacticoEventoC==null){
                    recursoDidacticoEventoC = new RecursoDidacticoEventoC();
                    recursoDidacticoEventoC.setKey(recursosUI.getRecursoId());
                    recursoDidacticoEventoC.setTitulo(recursosUI.getNombreRecurso());
                    switch (recursosUI.getTipoFileU()){
                        case VINCULO:
                            recursoDidacticoEventoC.setDescripcion(recursosUI.getUrl());
                            break;
                        default:
                            recursoDidacticoEventoC.setDescripcion(recursosUI.getDescripcion());
                            break;
                    }
                    recursoDidacticoEventoC.setUrl(recursosUI.getUrl());
                    recursoDidacticoEventoC.setTipoId(tipoArchivoId);
                    recursoDidacticoEventoC.setSilaboEventoId(requestValues.getIdSilaboEvento());
                    recursoDidacticoEventoC.setUnidadAprendizajeId(requestValues.getIdUnidadAprendizaje());
                    recursoDidacticoEventoC.setActividadAprendizajeId(0);
                    recursoDidacticoEventoC.setSesionAprendizajeId(requestValues.getIdSesionAprendizaje());
                    recursoDidacticoEventoC.setPlanCursoId(planCursos.getPlanCursoId());
                    recursoDidacticoEventoC.setEstadoExportado(0);
                    recursoDidacticoEventoC.setSyncFlag(BaseEntity.FLAG_ADDED);
                    recursoDidacticoEventoC.setEstado(1);
                    recursoDidacticoEventoC.setSilaboEventoId(requestValues.getIdSilaboEvento());
                    recursoDidacticoEventoC.save(databaseWrapper);
                }else {
                    recursoDidacticoEventoC.setTitulo(recursosUI.getNombreRecurso());
                    switch (recursosUI.getTipoFileU()){
                        case VINCULO:
                            recursoDidacticoEventoC.setDescripcion(recursosUI.getUrl());
                            break;
                        default:
                            recursoDidacticoEventoC.setDescripcion(recursosUI.getDescripcion());
                            break;
                    }
                    recursoDidacticoEventoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    recursoDidacticoEventoC.save(databaseWrapper);
                }

                TareasRecursosC tareasRecursos = new TareasRecursosC();
                tareasRecursos.setTareaId(tarea.getKey());
                tareasRecursos.setRecursoDidacticoId(recursoDidacticoEventoC.getKey());
                tareasRecursos.setEstadoExportado(0);
                tareasRecursos.setSyncFlag(BaseEntity.FLAG_ADDED);
                tareasRecursos.save(databaseWrapper);

                if(recursosUI.getTipoFileU() != RepositorioTipoFileU.VINCULO &&  !isLinkYoutube(recursosUI.getUrl())){
                    Log.d(TAG,"archivo");
                    Archivo archivo = SQLite.select()
                            .from(Archivo.class)
                            .where(Archivo_Table.key.eq(recursosUI.getArchivoId()))
                            .querySingle(databaseWrapper);

                    if(archivo==null){
                        Log.d(TAG,"crear archivp");
                        archivo = new Archivo();
                        archivo.setNombre(recursosUI.getNombreArchivo());
                        archivo.setArchivoId(recursosUI.getArchivoId());
                        archivo.setPath(recursosUI.getUrl());
                        Log.d(TAG,"crear archivo: " + recursosUI.getUrl());
                        archivo.setLocalpath(recursosUI.getPath());
                        archivo.setTipoId(tipoArchivoId);
                        archivo.setPlanCursoId(planCursos.getPlanCursoId());
                        archivo.setSyncFlag(BaseEntity.FLAG_ADDED);
                        archivo.save(databaseWrapper);
                    }
                    Log.d(TAG," archivoid: "+archivo.getKey()+ " recursoDidacticoId: " + recursoDidacticoEventoC.getKey());
                    RecursoArchivo recursoArchivo = new RecursoArchivo();
                    recursoArchivo.setArchivoId(archivo.getKey());
                    recursoArchivo.setRecursoDidacticoId(recursoDidacticoEventoC.getKey());
                    recursoArchivo.save(databaseWrapper);
                }

            }


            TareasUI tareasUI = new TareasUI();

            if (tarea.getEstadoId() == 263) {
                tareasUI.setEstado(TareasUI.Estado.Creado);
            }else if (tarea.getEstadoId() == 264) {
                tareasUI.setEstado(TareasUI.Estado.Publicado);
            }else if (tarea.getEstadoId() == 265) {
                tareasUI.setEstado(TareasUI.Estado.Eliminado);
            }else {
                tareasUI.setEstado(TareasUI.Estado.Creado);
            }
            tareasUI.setKeyTarea(tarea.getKey());
            tareasUI.setTituloTarea(tarea.getTitulo());
            tareasUI.setDescripcion(tarea.getInstrucciones());
            tareasUI.setFechaCreacionTarea(tarea.getFechaCreacion());
            tareasUI.setFechaLimite(tarea.getFechaEntrega());
            tareasUI.setHoraEntrega(tarea.getHoraEntrega());
            tareasUI.setIdUnidaddAprendizaje(tarea.getUnidadAprendizajeId());
            tareasUI.setIdSesionAprendizaje(tarea.getSesionAprendizajeId());


            int unidadAprendizajeId = tarea.getUnidadAprendizajeId();
            if(unidadAprendizajeId==0){
                SesionAprendizaje sesionAprendizaje = SQLite.select()
                        .from(SesionAprendizaje.class)
                        .where(SesionAprendizaje_Table.sesionAprendizajeId.eq(tarea.getSesionAprendizajeId()))
                        .querySingle(databaseWrapper);

                if(sesionAprendizaje!=null)unidadAprendizajeId = sesionAprendizaje.getUnidadAprendizajeId();
            }

            CargaCursos cargaCurso = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(SilaboEvento.class)
                    .on(SilaboEvento_Table.cargaCursoId.withTable().eq(CargaCursos_Table.cargaCursoId.withTable()))
                    .innerJoin(UnidadAprendizaje.class)
                    .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                    .where(UnidadAprendizaje_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId))
                    .querySingle(databaseWrapper);

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
                    .querySingle(databaseWrapper);


            Seccion seccion = SQLite.select()
                    .from(Seccion.class)
                    .innerJoin(CargaAcademica.class)
                    .on(Seccion_Table.seccionId.withTable().eq(CargaAcademica_Table.seccionId.withTable()))
                    .where(CargaAcademica_Table.cargaAcademicaId.eq(cargaAcademicaId))
                    .querySingle(databaseWrapper);

            Periodo periodo = SQLite.select()
                    .from(Periodo.class)
                    .innerJoin(CargaAcademica.class)
                    .on(Periodo_Table.periodoId.withTable().eq(CargaAcademica_Table.periodoId.withTable()))
                    .where(CargaAcademica_Table.cargaAcademicaId.eq(cargaAcademicaId))
                    .querySingle(databaseWrapper);

            String aliasPeriodo = "";
            String nombreSeccion = "";
            String cursoNombre = "";
            if(periodo != null)aliasPeriodo = periodo.getAlias();
            if(seccion != null)nombreSeccion = seccion.getNombre();
            if(curso != null)cursoNombre = curso.getNombre();
            cursoNombre = cursoNombre +" "+aliasPeriodo+" "+nombreSeccion;

            tareasUI.setNombreCurso(cursoNombre);

            databaseWrapper.setTransactionSuccessful();
            callback.onTareasCreated("Correcto", tareasUI);
        } catch (Exception e){
            e.printStackTrace();
            callback.onTareasCreated("incorecto Correcto", null);
        }finally {
            databaseWrapper.endTransaction();
        }


    }

    private boolean isLinkYoutube(String txtUrlVideo) {
        Log.d(TAG,"txtUrlVideo: " + txtUrlVideo);
        String[] linkYoutube = {"youtube.com", "youtu.be"};
        if(TextUtils.isEmpty(txtUrlVideo)){
            return false;
        }else if(txtUrlVideo.contains(linkYoutube[0])||txtUrlVideo.contains(linkYoutube[1])){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void eliminarRecurso(EliminarRecursoUseCase.RequestValues requestValues, EliminarRecursoCallback callback) {

        Log.d(TAG, "requestValues : " + requestValues.toString());

        RecursoDidacticoEventoC recursoDidacticoEventoC = SQLite.select()
                .from(RecursoDidacticoEventoC.class)
                .where(RecursoDidacticoEventoC_Table.key.eq(requestValues.getRecursosUI().getRecursoId()))
                .querySingle();
        if (recursoDidacticoEventoC != null) {
            recursoDidacticoEventoC.setEstadoExportado(0);
            recursoDidacticoEventoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            recursoDidacticoEventoC.setEstado(0);
            recursoDidacticoEventoC.save();
        } else {
            Log.d(TAG, "No se elimino nada");
        }
        callback.onRecursoDeleted("Recurso  Eliminadao");
    }

    @Override
    public void getArchivosUsuario(String tareaId, Callback<List<RecursosUI>> callback) {
        List<RecursosUI> repositorioFileUiList = new ArrayList<>();
        List<Archivo> archivoList = SQLite.select(Utils.f_allcolumnTable(Archivo_Table.ALL_COLUMN_PROPERTIES))
                .from(Archivo.class)
                .innerJoin(RecursoArchivo.class)
                .on(RecursoArchivo_Table.archivoId.withTable()
                        .eq(Archivo_Table.key.withTable()))
                .innerJoin(RecursoDidacticoEventoC.class)
                .on(RecursoArchivo_Table.recursoDidacticoId.withTable()
                        .eq(RecursoDidacticoEventoC_Table.key.withTable()))
                .innerJoin(TareasRecursosC.class)
                .on(RecursoArchivo_Table.recursoDidacticoId.withTable()
                        .eq(TareasRecursosC_Table.recursoDidacticoId.withTable()))
                .innerJoin(TareasC.class)
                .on(TareasRecursosC_Table.tareaId.withTable()
                        .eq(TareasC_Table.key.withTable()))
                .where(TareasC_Table.key.withTable().eq(tareaId))
                .and(TareasC_Table.estadoId.withTable().notEq(265))
                .and(RecursoDidacticoEventoC_Table.estado.withTable().eq(1))
                .groupBy(Utils.f_allcolumnTable(Archivo_Table.ALL_COLUMN_PROPERTIES))
                .orderBy(Archivo_Table.fechaCreacion.withTable().asc())
                .queryList();

        for (Archivo archivo: archivoList){
            RecursosUI repositorioFileUi = new RecursosUI();
            repositorioFileUi.setArchivoId(archivo.getKey());
            repositorioFileUi.setNombreArchivo(archivo.getNombre());
            repositorioFileUi.setNombreRecurso(archivo.getNombre());
            repositorioFileUi.setUrl(archivo.getPath());
            repositorioFileUi.setPath(archivo.getLocalpath());
            repositorioFileUi.setFechaAccionArchivo(archivo.getFechaAccion());
            //repositorioFileUi.setUrl("https://github.com/git-for-windows/git/releases/download/v2.20.1.windows.1/Git-2.20.1-64-bit.exe");
            switch (archivo.getTipoId()){
                case Archivo.TIPO_AUDIO:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                    break;
                case Archivo.TIPO_DIAPOSITIVA:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                    break;
                case Archivo.TIPO_DOCUMENTO:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                    break;
                case Archivo.TIPO_HOJA_CALCULO:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                    break;
                case Archivo.TIPO_IMAGEN:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                    break;
                case Archivo.TIPO_PDF:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.PDF);
                    break;
                case Archivo.TIPO_VIDEO:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                    break;
                case Archivo.TIPO_VINCULO:
                    repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
                    break;
            }

            if(TextUtils.isEmpty(archivo.getLocalpath())){
                repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
            }else {
                repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
            }

            repositorioFileUiList.add(repositorioFileUi);
        }

        callback.onLoad(true, repositorioFileUiList);
    }

    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {

    }


}
