package com.consultoraestrategia.ss_crmeducativo.services.data.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.*;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jse on 30/12/2018.
 */

public class ServiceLocalDataRepositoryImpl implements ServiceLocalDataRepository {

    private SessionUserDao sessionUserDao;
    private EvaluacionProcesoDao evaluacionProcesoDao;
    private String TAG = ServiceLocalDataRepositoryImpl.class.getSimpleName();

    public ServiceLocalDataRepositoryImpl(SessionUserDao sessionUserDao) {
        this.sessionUserDao = sessionUserDao;
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
    }


    @Override
    public List<String> comprobrarCambiosBaseDatosDaocenteMentor() {
        List<String> stringList = new ArrayList<>();
        if(countBEDatosAsistencia()!=0){
            stringList.add("code: asistencia export");
        }
        if(countBEDatosEnvioGrupo()!=0){
            stringList.add("code: grupo export");
        }
        if(countBEDatosEnvioMensajeria()!=0){
            stringList.add("code: mensajeria export");
        }
        if(countGEDatosRubroEvaluacionProceso()!=0){
            stringList.add("code: rubro export");
        }
        if(countBEDatosSesionAprendizaje()!=0){
            stringList.add("code: sesion export");
        }
        if(countGEDatosTareasRecursos()!=0){
            stringList.add("code: tarea export");
        }
        if(countGEDatosCargaAcademica()!=0){
            stringList.add("code: cargaAcadmica export");
        }
        if(countGEDatosCasos()!=0){
            stringList.add("code: Caso export");
        }
        Log.d(TAG, " comprobrarCambiosBaseDatosDaocenteMentor: " + stringList.toString());
        return stringList;
    }

    private long countGEDatosCargaAcademica() {
        return ConsultaUtils.countChangeItemsTable(CargaCursoCalendarioPeriodo.class);
    }

    private long countGEDatosCasos() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(Caso.class);
        Log.d(TAG, " Caso: " + count);
        count +=ConsultaUtils.countChangeItemsTable(CasoArchivo.class);
        Log.d(TAG, " CasoArchivo: " + count);
        return  count;
    }

    //#region comprobrarCambiosBaseDatosDaocenteMentor
    //#region countBEDatosAsistencia
    private long countBEDatosAsistencia() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(AsistenciaSesionAlumnoC.class);
        Log.d(TAG, " AsistenciaSesionAlumnoC: " + count);
        count +=ConsultaUtils.countChangeItemsTable(JustificacionC.class);
        Log.d(TAG, " JustificacionC: " + count);
        count +=ConsultaUtils.countChangeItemsTable(ArchivoAsistencia.class);
        Log.d(TAG, " ArchivoAsistencia" + count);
        count +=ConsultaUtils.countChangeItemsTable(TipoNotaC.class);
        Log.d(TAG, " TipoNotaC" + count);
        count +=ConsultaUtils.countChangeItemsTable(ValorTipoNotaC.class);
        Log.d(TAG, " ValorTipoNotaC" + count);
        count += ConsultaUtils.countChangeItemsTable(RelProgramaEducativoTipoNota.class);
        Log.d(TAG, " RelProgramaEducativoTipoNota" + count);
        return  count;
    }
    //#endregion countBEDatosAsistencia

    //#region countBEDatosEnvioGrupo BEDatosEnvioGrupo
    private long countBEDatosEnvioGrupo() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(GrupoEquipoC.class);
        count += ConsultaUtils.countChangeItemsTable(EquipoIntegranteC.class);
        count += ConsultaUtils.countChangeItemsTable(EquipoC.class);
        return count;
    }
    //#endregion countBEDatosEnvioGrupo BEDatosEnvioGrupo

    //#region countBEDatosEnvioMensajeria BEDatosEnvioMensajeria
    private long countBEDatosEnvioMensajeria() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(MensajeUsuarioC.class);
        count += ConsultaUtils.countChangeItemsTable(MensajeIntencionItemC.class);
        count += ConsultaUtils.countChangeItemsTable(CanalDestinoEstadoC.class);
        count += ConsultaUtils.countChangeItemsTable(MensajeC.class);
        count += ConsultaUtils.countChangeItemsTable(InteraccionTextual.class);
        return count;
    }
    //#endregion countBEDatosEnvioMensajeria BEDatosEnvioMensajeria

    //#region countGEDatosRubroEvaluacionProceso GEDatosRubroEvaluacionProceso
    private long countGEDatosRubroEvaluacionProceso() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(TipoNotaC.class);
        Log.d(TAG, " TipoNotaC: " +count);
        count += ConsultaUtils.countChangeItemsTable(ValorTipoNotaC.class);
        Log.d(TAG, " ValorTipoNotaC: " +count);
        count += ConsultaUtils.countChangeItemsTable(RelProgramaEducativoTipoNota.class);
        Log.d(TAG, " RelProgramaEducativoTipoNota: " +count);
        count += ConsultaUtils.countChangeItemsTable(RubroEvaluacionProcesoC.class);
        Log.d(TAG, " RubroEvaluacionProcesoC: " +count);
        count += ConsultaUtils.countChangeItemsTable(RubroEvalRNPFormulaC.class);
        Log.d(TAG, " RubroEvalRNPFormulaC: " +count);
        count += ConsultaUtils.countChangeItemsTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class);
        Log.d(TAG, " T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC: " +count);
        count += ConsultaUtils.countChangeItemsTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class);
        Log.d(TAG, " T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC: " +count);
        count += ConsultaUtils.countChangeItemsTable(EquipoEvaluacionProcesoC.class);
        Log.d(TAG, " EquipoEvaluacionProcesoC: " +count);
        count += ConsultaUtils.countChangeItemsTable(RubroEvaluacionProcesoCampotematicoC.class);
        Log.d(TAG, " RubroEvaluacionProcesoCampotematicoC: " +count);
        count += ConsultaUtils.countChangeItemsTable(CriterioRubroEvaluacionC.class);
        Log.d(TAG, " CriterioRubroEvaluacionC: " +count);
        count += ConsultaUtils.countChangeItemsTable(EvaluacionProcesoC.class);
        Log.d(TAG, " EvaluacionProcesoC: " +count);
        count += ConsultaUtils.countChangeItemsTable(RubroEvaluacionProcesoComentario.class);
        Log.d(TAG, " RubroEvaluacionProcesoComentario: " +count);
        count += ConsultaUtils.countChangeItemsTable(ArchivosRubroProceso.class);
        Log.d(TAG, " ArchivosRubroProceso: " +count);
        //count += ConsultaUtils.countChangeItemsTable(TareaRubroEvaluacionProceso.class);
       // Log.d(TAG, " TareaRubroEvaluacionProceso: " +count);
        count += ConsultaUtils.countChangeItemsTable(RubroEvaluacionResultado.class);
        Log.d(TAG, " RubroEvaluacionResultado: " +count);
        count += ConsultaUtils.countChangeItemsTable(EvaluacionResultadoC.class);
        Log.d(TAG, " EvaluacionResultado: " +count);
        return count;
    }
    //#endregion countGEDatosRubroEvaluacionProceso GEDatosRubroEvaluacionProceso

    //#region countBEDatosSesionAprendizaje BEDatosSesionAprendizaje
    private long countBEDatosSesionAprendizaje() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(SesionAprendizaje.class);
        Log.d(TAG, " SesionAprendizaje: " +count);/*
        count += ConsultaUtils.countChangeItemsTable(Competencia.class);
        Log.d(TAG, " Competencia: " +count);
        count += ConsultaUtils.countChangeItemsTable(Competencia.class);
        Log.d(TAG, " SesionAprendizaje: " +count);
        count += ConsultaUtils.countChangeItemsTable(DesempenioIcd.class);
        Log.d(TAG, " DesempenioIcd: " +count);
        count += ConsultaUtils.countChangeItemsTable(Icds.class);
        Log.d(TAG, " Icds: " +count);
        count += ConsultaUtils.countChangeItemsTable(CampoTematico.class);
        Log.d(TAG, " CampoTematico: " +count);
        count += ConsultaUtils.countChangeItemsTable(RecursoDidacticoEventoC.class);
        Log.d(TAG, " RecursoDidacticoEventoC: " +count);
        count += ConsultaUtils.countChangeItemsTable(TareasC.class);
        Log.d(TAG, " TareasC: " +count);
        count += ConsultaUtils.countChangeItemsTable(TareasRecursosC.class);
        Log.d(TAG, " TareasRecursosC: " +count);
        count += ConsultaUtils.countChangeItemsTable(ActividadAprendizaje.class);
        Log.d(TAG, " ActividadAprendizaje: " +count);
        count += ConsultaUtils.countChangeItemsTable(UnidadAprendizaje.class);
        Log.d(TAG, " UnidadAprendizaje: " +count);*/
        return count;
    }
    //#endregion countBEDatosSesionAprendizaje BEDatosSesionAprendizaje

    //#region countGEDatosTareasRecursos GEDatosTareasRecursos
    private long countGEDatosTareasRecursos() {
        long count = 0;
        count += ConsultaUtils.countChangeItemsTable(TareasC.class);
        count += ConsultaUtils.countChangeItemsTable(RecursoDidacticoEventoC.class);
        count += ConsultaUtils.countChangeItemsTable(TareasRecursosC.class);
        count += ConsultaUtils.countChangeItemsTable(RecursoArchivo.class);
        count += ConsultaUtils.countChangeItemsTable(Archivo.class);
        return count;
    }
    //#endregion countGEDatosTareasRecursos GEDatosTareasRecursos
    //#endregion

    @Override
    public void changeEstadoGlobal(BEGuardarEntidadesGlobal item, BERespuesta respuesta, int syncFlag, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            if(respuesta.getCommit_Asistencia()){
                GEDatosEnvioAsistencia geDatosEnvioAsistencia = item.getAsistencia();
                changeEstadoGlobals(geDatosEnvioAsistencia,syncFlag,databaseWrapper);
            }

            if(respuesta.getCommit_Grupo()){
                BEDatosEnvioGrupo beDatosEnvioGrupo = item.getGrupo();
                changeEstadoGlobals(beDatosEnvioGrupo,syncFlag,databaseWrapper);
            }

            if(respuesta.getCommit_Mensajes()){
                BEDatosEnvioMensajeria beDatosEnvioMensajeria = item.getMensajeria();
                changeEstadoGlobals(beDatosEnvioMensajeria,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_RubroEvaluacionProceso()){
                GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = item.getRubroEvaluacionProceso();
                changeEstadoGlobals(geDatosRubroEvaluacionProceso,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_Sesion()){
                BEDatosSesionAprendizaje beDatosSesionAprendizaje = item.getSesionAprendizaje();
                changeEstadoGlobals(beDatosSesionAprendizaje,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_TareaRecurso()){
                GEDatosTareasRecursos geDatosTareasRecursos = item.getTareaRecursos();
                changeEstadoGlobals(geDatosTareasRecursos,syncFlag,databaseWrapper);
            }

            BEDatosCasos beDatosCasos = item.getCasos();
            changeEstadoGlobals(beDatosCasos,syncFlag,databaseWrapper);

            BEDatosCargaAcademica beDatosCargaAcademica = item.getCargaAcademica();
            if(beDatosCargaAcademica!=null){
                changeEstadoGlobals(beDatosCargaAcademica, syncFlag, databaseWrapper);
            }

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }



    //#region changeEstadoGlobals

    private void changeEstadoGlobals(BEDatosCargaAcademica beDatosCargaAcademica, int syncFlag, DatabaseWrapper databaseWrapper) {
        if(beDatosCargaAcademica!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(CargaCursoCalendarioPeriodo.class, beDatosCargaAcademica.getObtenerCargaCursosCalendarioPeriodo(), syncFlag, databaseWrapper, false);
        }
    }


    private void changeEstadoGlobals(BEDatosCasos beDatosCasos, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(Caso.class, beDatosCasos.getCaso(), syncFlag, databaseWrapper, false);
        SQLite.update(CasoArchivo.class)
                .set(CasoArchivo_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CasoReporte.class, beDatosCasos.getCasoReporte(), syncFlag,databaseWrapper, false);
    }

    //#region changeEstadoGlobals GEDatosEnvioAsistencia
    private void changeEstadoGlobals(GEDatosEnvioAsistencia geDatosEnvioAsistencia, int syncFlag, DatabaseWrapper databaseWrapper) {
        BEDatosEnvioAsistencia beDatosEnvioAsistencia = geDatosEnvioAsistencia.getBeDatosEnvioAsistencia();
        TransaccionUtils.fastStoreListSyncFlagUpdate(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), syncFlag, databaseWrapper, false);

        SQLite.update(ArchivoAsistencia.class)
                .set(ArchivoAsistencia_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);

        //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), syncFlag,databaseWrapper, false);

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosEnvioAsistencia.getBeDatosEnvioTipoNota();
        TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals GEDatosEnvioAsistencia

    //#region changeEstadoGlobals BEDatosEnvioGrupo
    private void changeEstadoGlobals(BEDatosEnvioGrupo beDatosEnvioGrupo, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioGrupo

    //#region changeEstadoGlobals BEDatosEnvioMensajeria
    private void changeEstadoGlobals(BEDatosEnvioMensajeria beDatosEnvioMensajeria, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioMensajeria

    //#region changeEstadoGlobals GEDatosRubroEvaluacionProceso
    private void changeEstadoGlobals(GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso, int syncFlag, DatabaseWrapper databaseWrapper) {
        BEDatosEnvioGrupo beDatosEnvioGrupo = geDatosRubroEvaluacionProceso.getBeDatosEnvioGrupo();
        TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);

        //BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosRubroEvaluacionProceso.getBeDatosEnvioTipoNota();
        //TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag,databaseWrapper, false);

        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionProceso();
        TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), syncFlag, databaseWrapper, true);

        TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);

        Log.d(TAG, "Size eval: "+ beDatosRubroEvaluacionProceso.getEvaluacionProceso().size());
        TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), syncFlag,databaseWrapper, true);

        TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoComentario.class,beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), syncFlag, databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class,beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), syncFlag, databaseWrapper, false);
        SQLite.update(ArchivosRubroProceso.class)
                .set(ArchivosRubroProceso_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);

        //TransaccionUtils.fastStoreListSyncFlagUpdate(ComentarioPredeterminado.class, beDatosRubroEvaluacionProceso.getComentarioPredeterminado(), syncFlag,databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), syncFlag,databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), syncFlag,databaseWrapper, false);

        GEDatosTareasRecursos beDatosTareaRecursos = geDatosRubroEvaluacionProceso.getBeDatosTareaRecursos();

        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class,beDatosTareaRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class,beDatosTareaRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class,beDatosTareaRecursos.getTareas(), syncFlag, databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdate(TareaRubroEvaluacionProceso.class,beDatosTareaRecursos.getTareaRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);

        BEDatosEvaluacionResultado beDatosEvaluacionResultado = geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionResultado();
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionResultado.class,beDatosEvaluacionResultado.getRubroEvaluacionResultado(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(EvaluacionResultadoC.class,beDatosEvaluacionResultado.getEvaluacionResultado(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals GEDatosRubroEvaluacionProceso

    //#region changeEstadoGlobals BEDatosSesionAprendizaje
    private void changeEstadoGlobals(BEDatosSesionAprendizaje beDatosSesionAprendizaje, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(SesionAprendizaje.class, beDatosSesionAprendizaje.getSesionAprendizaje(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCompetencias(),syncFlag,databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCapacidades(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(DesempenioIcd.class, beDatosSesionAprendizaje.getDesempenioIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Icds.class, beDatosSesionAprendizaje.getIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(CampoTematico.class, beDatosSesionAprendizaje.getCampoTematico(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, beDatosSesionAprendizaje.getRecursoDidacticoEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, beDatosSesionAprendizaje.getTareaEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, beDatosSesionAprendizaje.getTareaEventoRecurso(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(ActividadAprendizaje.class, beDatosSesionAprendizaje.getActividadEvento(),syncFlag,databaseWrapper,  false);

        TransaccionUtils.fastStoreListSyncFlagUpdateRel(UnidadAprendizaje.class, beDatosSesionAprendizaje.getUnidadAprendizaje(),syncFlag,databaseWrapper,  false);
    }
    //#endregion changeEstadoGlobals BEDatosSesionAprendizaje

    //#region changeEstadoGlobals GEDatosTareasRecursos
    private void changeEstadoGlobals(GEDatosTareasRecursos geDatosTareasRecursos, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, geDatosTareasRecursos.getTareas(), syncFlag, databaseWrapper, true);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, geDatosTareasRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RecursoArchivo.class, geDatosTareasRecursos.getRecursoArchivo(), syncFlag, databaseWrapper, false);
        SQLite.update(Archivo.class)
                .set(Archivo_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);
    }
    //#endregion changeEstadoGlobals GEDatosTareasRecursos
    //#endregion

    @Override
    public void saveDatosGlobal(BERespuesta item, ServiceDataSource.SuccessCallBack callBack) {
        Log.d(getClass().getSimpleName(), "Time init: " + new Date().getTime() );
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            saveDatosGlobal(item.getCargaAcademica(),databaseWrapper);
            saveDatosGlobal(item.getAsistencia(),databaseWrapper);
            saveDatosGlobal(item.getGrupo(),databaseWrapper);
            saveDatosGlobal(item.getEnvioHorario(),databaseWrapper);
            saveDatosGlobal(item.getMensajeria(),databaseWrapper);
            saveDatosGlobal(item.getTiponota(),databaseWrapper);
            saveDatosGlobal(item.getRubroevaluacionResultado(),databaseWrapper);
            saveDatosGlobal(item.getRubroEvaluacionProceso(),databaseWrapper);
            saveDatosGlobal(item.getSilaboEvento(), databaseWrapper);
            saveDatosGlobal(item.getLogin(), databaseWrapper);

            SessionUser user = SessionUser.getCurrentUser();
            if (user != null) {
                user.setDataImported(true);
                Log.d(getClass().getSimpleName(),"fecha servidor: " + item.getFecha_servidor());
                user.setFechaServidor(item.getLogin().getFecha_servidor());
                user.save();
            } else {
                throw new Error("NO se puede encontrar la SessionUser");
            }
            Log.d(getClass().getSimpleName(), "Time sendFinish: " + new Date().getTime() );
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    //#region saveDatosGlobal
    //#region saveDatosGlobal BEDatosCargaAcademica
    public void saveDatosGlobal(BEDatosCargaAcademica beDatosCargaAcademica,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(Contrato.class, beDatosCargaAcademica.getContratos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, beDatosCargaAcademica.getDetalleContratoAcad(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, beDatosCargaAcademica.getCargaCursoDocente(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, beDatosCargaAcademica.getCargaCursoDocenteDet(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursos.class, beDatosCargaAcademica.getCargaCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaAcademica.class, beDatosCargaAcademica.getCargasAcademicas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Empleado.class, beDatosCargaAcademica.getEmpleados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanCursos.class, beDatosCargaAcademica.getPlanCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Cursos.class, beDatosCargaAcademica.getCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Periodo.class, beDatosCargaAcademica.getPeriodos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Seccion.class, beDatosCargaAcademica.getSecciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, beDatosCargaAcademica.getCalendarioAcademicos(), databaseWrapper, true);

        TransaccionUtils.fastStoreListInsert(DimensionDesarrollo.class, beDatosCargaAcademica.getDimensionDesarrollo(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(DimensionDesarrolloDetalle.class, beDatosCargaAcademica.getDimensionDesarrolloDetalle(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(DimensionDesarrolloEstrategiaEvaluacionC.class, beDatosCargaAcademica.getDimensionDesarrolloEstrategiaEval(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(EstrategiaEvaluacion.class, beDatosCargaAcademica.getEstrategiaEvaluacion(), databaseWrapper, true);//1

        List<ProgramasEducativo> lastprogramasEducativoList = SQLite.select()
                .from(ProgramasEducativo.class)
                .queryList();


        List<ProgramasEducativo> programasEducativoList = beDatosCargaAcademica.getProgramasEducativos();

        for (ProgramasEducativo programasEducativo: programasEducativoList){
            for (ProgramasEducativo lastProgramasEducativo: lastprogramasEducativoList){
                if(lastProgramasEducativo.getProgramaEduId() == programasEducativo.getProgramaEduId()){
                    programasEducativo.setToogle(lastProgramasEducativo.isToogle());
                }
            }
        }



        TransaccionUtils.fastStoreListInsert(ProgramasEducativo.class,programasEducativoList , databaseWrapper, true);


        TransaccionUtils.fastStoreListInsert(NivelAcademico.class, beDatosCargaAcademica.getNivelesAcademicos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanEstudios.class, beDatosCargaAcademica.getPlanEstudios(), databaseWrapper, true);
        //transaccionUtils.add(TransaccionUtils.fastStoreListInsert(PlanesEstudiosAlumno.class, beDatosCargaAcademica.getPlanEstudiosAlumno(), transaccionUtils);
        TransaccionUtils.fastStoreListInsert(Aula.class, beDatosCargaAcademica.getAulas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, beDatosCargaAcademica.getCalendarioPeriodos(), databaseWrapper, true);//1

        List<Archivo> archivoList = SQLite.select()
                .from(Archivo.class)
                .queryList();
        List<Archivo> archivoListNew =  beDatosCargaAcademica.getArchivo();
        for (Archivo archivo: archivoListNew){
            for (Archivo antiguoArchivo : archivoList){
                if(antiguoArchivo.getKey().equals(archivo.getKey())){
                    archivo.setLocalpath(antiguoArchivo.getLocalpath());
                    break;
                }
            }
        }

        TransaccionUtils.fastStoreListInsert(Archivo.class,archivoListNew, databaseWrapper, true);

        //region Caso
        List<String> casoIdList = new ArrayList<>();
        for (Caso caso : beDatosCargaAcademica.getCaso())casoIdList.add(caso.getKey());

        TransaccionUtils.fastStoreListInsert(Caso.class, beDatosCargaAcademica.getCaso(), databaseWrapper, true);//1
        List<CasoArchivo> casoArchivoList = SQLite.select()
                .from(CasoArchivo.class)
                .queryList();

        List<CasoArchivo> casoArchivoListNew =  beDatosCargaAcademica.getCasoArchivo();
        for (CasoArchivo archivo: casoArchivoListNew){
            for (CasoArchivo antiguoArchivo : casoArchivoList){
                try {
                    if(antiguoArchivo.getPath().equals(archivo.getPath())){
                        archivo.setLocalPath(antiguoArchivo.getLocalPath());
                        break;
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }

        TransaccionUtils.deleteTable(CasoArchivo.class, CasoArchivo_Table.casoId.in(casoIdList));
        TransaccionUtils.fastStoreListInsert(CasoArchivo.class, casoArchivoListNew, databaseWrapper, true);//1

        TransaccionUtils.fastStoreListInsert(CasoReporte.class, beDatosCargaAcademica.getCasoReporte(), databaseWrapper, true);//1
        //endregion

        TransaccionUtils.fastStoreListInsert(PersonaGeoOrg.class, beDatosCargaAcademica.getPersonaGeoOrg(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(Organigrama.class, beDatosCargaAcademica.getOrganigrama(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(GeoRefOrganigrama.class, beDatosCargaAcademica.getGeoRefOrganigrama(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(TipoEntidadGeo.class, beDatosCargaAcademica.getTipoEntidadGeo(), databaseWrapper, true);//1
    }
    //#endregion saveDatosGlobal BEDatosCargaAcademica

    //#region saveDatosGlobal BEDatosEnvioAsistencia
    public void saveDatosGlobal(BEDatosEnvioAsistencia beDatosEnvioAsistencia,DatabaseWrapper databaseWrapper){
          TransaccionUtils.fastStoreListInsert(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioAsistencia

    //#region saveDatosGlobal BEDatosEnvioGrupo
    public void saveDatosGlobal(BEDatosEnvioGrupo beDatosEnvioGrupo,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(EquipoC.class, beDatosEnvioGrupo.getEquipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioGrupo

    //#region saveDatosGlobal BEDatosEnvioHorario
    public void saveDatosGlobal(BEDatosEnvioHorario beDatosEnvioHorario,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(Hora.class, beDatosEnvioHorario.getHora(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioPrograma.class, beDatosEnvioHorario.getHorarioPrograma(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioHora.class, beDatosEnvioHorario.getHorarioHora(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DetalleHorario.class, beDatosEnvioHorario.getDetalleHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Dia.class, beDatosEnvioHorario.getDia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Horario.class, beDatosEnvioHorario.getHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioDia.class, beDatosEnvioHorario.getHorarioDia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CursosDetHorario.class, beDatosEnvioHorario.getCursosDetHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DiaHora.class, beDatosEnvioHorario.getObtenerDiaHora(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioHorario

    //#region saveDatosGlobal BEDatosEnvioMensajeria
    public void saveDatosGlobal(BEDatosEnvioMensajeria beDatosEnvioMensajeria,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UsuarioCanalComunicacion.class, beDatosEnvioMensajeria.getUsCanalComunicacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CanalComunicacion.class, beDatosEnvioMensajeria.getCanalComunicacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Intencion.class, beDatosEnvioMensajeria.getIntenciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(IntencionItem.class, beDatosEnvioMensajeria.getIntencionItems(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ListaUsuario.class, beDatosEnvioMensajeria.getListaUsuarios(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ListaUsuarioDetalle.class, beDatosEnvioMensajeria.getListUsuarioDetalle(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredeterminado.class, beDatosEnvioMensajeria.getListaMensajePredeterminado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredeterminadoDetalle.class, beDatosEnvioMensajeria.getListMensajePredeterminadoDetalle(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredIntencion.class, beDatosEnvioMensajeria.getListMensajePredIntencion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), databaseWrapper, false);
    }
    //#endregion saveDatosGlobal BEDatosEnvioMensajeria

    //#region saveDatosGlobal BEDatosEnvioTipoNota
    public void saveDatosGlobal(BEDatosEnvioTipoNota beDatosEnvioTipoNota,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, true);

    }
    //#endregion saveDatosGlobal BEDatosEnvioTipoNota

    //#region saveDatosGlobal BEDatosEvaluacionResultado
    public void saveDatosGlobal(BEDatosEvaluacionResultado beDatosEvaluacionResultado, DatabaseWrapper databaseWrapper){
          TransaccionUtils.fastStoreListInsert(RubroEvaluacionResultado.class, beDatosEvaluacionResultado.getRubroEvaluacionResultado(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(RubroEvalRNRFormula.class, beDatosEvaluacionResultado.getRubroEvalResultadoFormula(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(EvaluacionResultadoC.class, beDatosEvaluacionResultado.getEvaluacionResultado(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioTipoNota

    //#region saveDatosGlobal BEDatosRubroEvaluacionProceso
    public void saveDatosGlobal(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso, DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), databaseWrapper, true);

        List<ArchivosRubroProceso> casoArchivoList = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .queryList();

        List<ArchivosRubroProceso> casoArchivoListNew =  beDatosRubroEvaluacionProceso.getArchivoRubroProceso();
        for (ArchivosRubroProceso archivo: casoArchivoListNew){
            for (ArchivosRubroProceso antiguoArchivo : casoArchivoList){
                if(antiguoArchivo.getKey().equals(archivo.getKey())){
                    archivo.setLocalpath(antiguoArchivo.getLocalpath());
                    break;
                }
            }
        }

        //region ComentarioPredeterminado
        List<String> stringList = new ArrayList<>();
        if(beDatosRubroEvaluacionProceso.getEvaluacionProceso()!=null)
            for (EvaluacionProcesoC evaluacionProcesoC: beDatosRubroEvaluacionProceso.getEvaluacionProceso())stringList.add(evaluacionProcesoC.getKey());
        TransaccionUtils.deleteTable(RubroEvaluacionProcesoComentario.class, RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.in(stringList));
        TransaccionUtils.deleteTable(ArchivosRubroProceso.class, ArchivosRubroProceso_Table.evaluacionProcesoId.in(stringList));

        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), databaseWrapper, true);
        //endregion ComentarioPredeterminado

    }
    //#endregion saveDatosGlobal BEDatosRubroEvaluacionProceso

    //#region saveDatosGlobal GEDatosSilaboEventoEnvio
    public void saveDatosGlobal(GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio, DatabaseWrapper databaseWrapper){

        //region RecursoDidacticoEventoC
        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = beDatosSilaboEventoEnvio.getRecursoDidactico();
        List<String> recursoDiactivoIdList = new ArrayList<>();
        if(recursoDidacticoEventoCList!=null)for(RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recursoDiactivoIdList.add(recursoDidacticoEventoC.getKey());
        TransaccionUtils.deleteTable(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(RecursoReferenciaC.class, RecursoReferenciaC_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(TareasRecursosC.class, TareasRecursosC_Table.recursoDidacticoId.in(recursoDiactivoIdList));

        TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, recursoDidacticoEventoCList, databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, beDatosSilaboEventoEnvio.getRecursoArchivo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, beDatosSilaboEventoEnvio.getRecursoReferencia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, beDatosSilaboEventoEnvio.getTareasRecursos(), databaseWrapper, true);
        //endregion RecursoDidacticoEventoC

        TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, beDatosSilaboEventoEnvio.getActividad(), databaseWrapper, true);
        //final Gson gson = new Gson();
        //final String representacionJSON = gson.toJson(beDatosSilaboEventoEnvio.getRecursoDidactico());
        //ApiRetrofit.Log.d(getClass().getSimpleName(), "getRecursoDidactico : " + representacionJSON);

        TransaccionUtils.fastStoreListInsert(TareasC.class, beDatosSilaboEventoEnvio.getTareas(), databaseWrapper, true);
        /*Objeto a eliminar est repetida */
        TransaccionUtils.fastStoreListInsertRel(UnidadTipo.class, beDatosSilaboEventoEnvio.getUnidadTipo(), databaseWrapper, true);


        //region sesion aprendizaje
        List<SesionAprendizaje> sesionAprendizajeList = beDatosSilaboEventoEnvio.getSesiones();
        List<Integer> sesionAprendizajeIdLis = new ArrayList<>();
        if(sesionAprendizajeList!=null)for (SesionAprendizaje sesionAprendizaje: sesionAprendizajeList)sesionAprendizajeIdLis.add(sesionAprendizaje.getSesionAprendizajeId());
        List<SesionEventoCompetenciaDesempenioIcd> sesionEventoCompetenciaDesempenioIcds = ConsultaUtils.getChangeItemsTableChild(SesionEventoCompetenciaDesempenioIcd.class, SesionEventoCompetenciaDesempenioIcd_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        List<Integer> sesionCompetenciaDesempenioIcdIdList = new ArrayList<>();
        for (SesionEventoCompetenciaDesempenioIcd sesionEventoCompetenciaDesempenioIcd: sesionEventoCompetenciaDesempenioIcds)sesionCompetenciaDesempenioIcdIdList.add(sesionEventoCompetenciaDesempenioIcd.getSesionCompetenciaDesempenioIcdId());
        TransaccionUtils.deleteTable(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        TransaccionUtils.deleteTable(SesionEventoCompetenciaDesempenioIcd.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        TransaccionUtils.deleteTable(SesionEventoDesempenioIcdCampotematico.class, SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.in(sesionCompetenciaDesempenioIcdIdList));

        TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, sesionAprendizajeList, databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, beDatosSilaboEventoEnvio.getCompetenciaSesion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_sesion_evento_competencia_desempenioicd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSesion_desempenio_icd_campotematico(), databaseWrapper, true);
        //endregion sesion aprendizaje

        TransaccionUtils.fastStoreListInsert(Competencia.class, beDatosSilaboEventoEnvio.getCompetencias(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_desempenio_icd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Icds.class, beDatosSilaboEventoEnvio.getIcds(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CampoTematico.class, beDatosSilaboEventoEnvio.getCampoTematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsertRel(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, beDatosSilaboEventoEnvio.getRel_unidad_apren_evento_tipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(AreaInstrumento.class, beDatosSilaboEventoEnvio.getObtenerAreaInstrumento(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TipoInstrumento.class, beDatosSilaboEventoEnvio.getObtenerTipoInstrumento(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Dimension.class, beDatosSilaboEventoEnvio.getObtenerDimension(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DimensionCaracteristica.class, beDatosSilaboEventoEnvio.getObtenerDimensionCaracteristica(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DimensionObservada.class, beDatosSilaboEventoEnvio.getObtenerDimensionObservada(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, beDatosSilaboEventoEnvio.getObtenerInstrumentoObservado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, beDatosSilaboEventoEnvio.getInstrumento_eval(), databaseWrapper, true);
        //TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, beDatosSilaboEventoEnvio.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, beDatosSilaboEventoEnvio.getObtenerCalendarioPeriodoDetalle(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, beDatosSilaboEventoEnvio.getObtenerCargaCursosCalendarioPeriodo(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(Desempenio.class, beDatosSilaboEventoEnvio.getObtenerDesempenio(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ProductoAprendizajeEvento.class, beDatosSilaboEventoEnvio.getProductoAprendizaje(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ProductoEventoReferencia.class, beDatosSilaboEventoEnvio.getProducto_evento_referencia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ProductoEventoCampoTematico.class, beDatosSilaboEventoEnvio.getObtenerProductoEventoCampoTematico(), databaseWrapper, true);

        //#region SilaboEvento
        List<Integer> listSilabosId = new ArrayList<>();
        List<SilaboEvento> silaboEventos = beDatosSilaboEventoEnvio.getSilaboEvento();
        if (silaboEventos!=null)for (SilaboEvento s:silaboEventos) listSilabosId.add(s.getSilaboEventoId());
        List<Integer>listSilabosCompetencaiId = new ArrayList<>();
        List<SilaboCompetencia> silaboCompetencias = ConsultaUtils.getChangeItemsTableChild(SilaboCompetencia.class, SilaboCompetencia_Table.silaboEventoId.in(listSilabosId));
        for (SilaboCompetencia silaboCompetencia:silaboCompetencias)listSilabosCompetencaiId.add(silaboCompetencia.getSilaboEventoCompetenciaId());
        List<Integer> silabocompetenciadesempenioicdIdList = new ArrayList<>();
        List<SilaboEventoCompentenciaDesempenioIcd> silaboEventoCompentenciaDesempenioIcds = ConsultaUtils.getChangeItemsTableChild(SilaboEventoCompentenciaDesempenioIcd.class, SilaboEventoCompentenciaDesempenioIcd_Table.silaboEventoCompetenciaId.in(listSilabosCompetencaiId));
        for(SilaboEventoCompentenciaDesempenioIcd silaboEventoCompentenciaDesempenioIcd: silaboEventoCompentenciaDesempenioIcds)silabocompetenciadesempenioicdIdList.add(silaboEventoCompentenciaDesempenioIcd.getSilaboCompetenciaDesempenioIcdId());

        TransaccionUtils.deleteTable(SilaboCompetencia.class, SilaboCompetencia_Table.silaboEventoId.in(listSilabosId));
        TransaccionUtils.deleteTable(SilaboEventoCompentenciaDesempenioIcd.class, SilaboEventoCompentenciaDesempenioIcd_Table.silaboEventoCompetenciaId.in(listSilabosCompetencaiId));
        TransaccionUtils.deleteTable(SilaboEventoDesempenioIcdCampotematico.class, SilaboEventoDesempenioIcdCampotematico_Table.silabocompetenciadesempenioicdId.in(silabocompetenciadesempenioicdIdList));

        TransaccionUtils.fastStoreListInsert(SilaboCompetencia.class, beDatosSilaboEventoEnvio.getSilabocompetencia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEventoCompentenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getSilaboeventocompetenciadesempenioicd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSilaboeventodesempenioicdcampotematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEvento.class, silaboEventos, databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumento.class, beDatosSilaboEventoEnvio.getObtenerUnidadEventoCompetenciaDesempenioICDInstrumento(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica.class, beDatosSilaboEventoEnvio.getObtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TecnicaEvaluacion.class, beDatosSilaboEventoEnvio.getTecnicaEvaluacion(), databaseWrapper, true);

        //#endregion SilaboEvento

        //region UnidadAprendizaje
        List<Integer> listUnidadAprendizajeId = new ArrayList<>();
        List<UnidadAprendizaje> listUnidadAprendizaje= beDatosSilaboEventoEnvio.getUnidadAprendizaje();
        if(listUnidadAprendizaje!=null)for(UnidadAprendizaje unidadAprendizaje: listUnidadAprendizaje)listUnidadAprendizajeId.add(unidadAprendizaje.getUnidadAprendizajeId());
        List<Integer> unidadCompetenciaIdList = new ArrayList<>();
        List<CompetenciaUnidad> competenciaUnidadList = ConsultaUtils.getChangeItemsTableChild(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
        for(CompetenciaUnidad competenciaUnidad: competenciaUnidadList)unidadCompetenciaIdList.add(competenciaUnidad.getCompetenciaId());
        List<Integer> unidadCompetenciaDesempenioIcdIdList = new ArrayList<>();
        List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icds = ConsultaUtils.getChangeItemsTableChild(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(listUnidadAprendizajeId));
        for(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD evento_competencia_desempenio_icd : rel_unidad_evento_competencia_desempenio_icds)unidadCompetenciaDesempenioIcdIdList.add(evento_competencia_desempenio_icd.getUnidadCompetenciaDesempenioIcdId());

        TransaccionUtils.deleteTable(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
        TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(unidadCompetenciaIdList));
        TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.in(unidadCompetenciaDesempenioIcdIdList));

        TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, beDatosSilaboEventoEnvio.getCompetenciaUnidad(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd_campo_tematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, beDatosSilaboEventoEnvio.getUnidadAprendizaje(), databaseWrapper, true);
        //endregion

    }
    //#endregion saveDatosGlobal GEDatosSilaboEventoEnvio

    //#region saveDatosGlobal GEDatosTareasRecursos
    public void saveDatosGlobal(GEDatosTareasRecursos geDatosTareasRecursos, DatabaseWrapper databaseWrapper){
        /*
                TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, geDatosTareasRecursos.|(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareasC.class, geDatosTareasRecursos.getTareas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, geDatosTareasRecursos.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), databaseWrapper, true);*/
    }
    //#endregion saveDatosGlobal GEDatosTareasRecursos

    //#region saveDatosGlobal BEObtenerDatosLogin
    public void saveDatosGlobal(BEObtenerDatosLogin beObtenerDatosLogin, DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(AnioAcademico.class, beObtenerDatosLogin.getAnioAcademicos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CuentaCorriente.class, beObtenerDatosLogin.getCuentaCorriente(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanCursosAlumno.class, beObtenerDatosLogin.getPlanCursosAlumno(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Estados.class, beObtenerDatosLogin.getEstados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Tipos.class, beObtenerDatosLogin.getTipos(), databaseWrapper, true);

       //#region Persona
        List<Persona> personaList = beObtenerDatosLogin.getPersonas();
        //List<Integer> personaIdList = new ArrayList<>();
        //for (Persona persona: personaList)personaIdList.add(persona.getPersonaId());
        //List<Persona> personaListAntigua = ConsultaUtils.getChangeItemsTableChild(Persona.class, Persona_Table.personaId.in(personaIdList));

        //for (Persona personaAntigua: personaListAntigua){
            //for (Persona persona : personaList){
               // if(persona.getPersonaId() == personaAntigua.getPersonaId()){
              //      persona.setPath(personaAntigua.getPath());
            //    }
          //  }
        //}
        TransaccionUtils.fastStoreListInsert(Persona.class, personaList, databaseWrapper, true);
        //#endregion

        TransaccionUtils.fastStoreListInsert(Relaciones.class, beObtenerDatosLogin.getRelaciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CursoCompetencia.class, beObtenerDatosLogin.getCursoCompetencias(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ColorCondicionalC.class, beObtenerDatosLogin.getColorCondicional(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(IndicarLogro.class, beObtenerDatosLogin.getIndicarLogro(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Usuario.class, beObtenerDatosLogin.getUsuariosrelacionados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ParametrosDisenio.class, beObtenerDatosLogin.getObtener_parametros_disenio(), databaseWrapper, true);
        TransaccionUtils.fastStoreListSave(Rutas.class, beObtenerDatosLogin.getRutas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListSave(ParametroConfiguracion.class, beObtenerDatosLogin.getParametroConfiguracions(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Ubicaciones.class, beObtenerDatosLogin.getUbicaciones(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEObtenerDatosLogin
    //#endregion

    @Override
    public void getDatosExportarGlobal(CallBack<BEGuardarEntidadesGlobal> callBack) {
       try {

           SessionUser user = SessionUser.getCurrentUser();
           clearDataRubroEvaluacionProceso();

           BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
           beGuardarEntidadesGlobal.setAsistencia(getGEDatosEnvioAsistencia());
           beGuardarEntidadesGlobal.setGrupo(getBEDatosEnvioGrupo());
           beGuardarEntidadesGlobal.setMensajeria(getBEDatosEnvioMensajeria());
           beGuardarEntidadesGlobal.setRubroEvaluacionProceso(getGEDatosRubroEvaluacionProceso());
           beGuardarEntidadesGlobal.setSesionAprendizaje(getBEDatosSesionAprendizaje());
           beGuardarEntidadesGlobal.setTareaRecursos(getGEDatosTareasRecursos());
           beGuardarEntidadesGlobal.setUsuarioId(user.getUserId());
           beGuardarEntidadesGlobal.setCasos(getBEDatosCasos());
           beGuardarEntidadesGlobal.setCargaAcademica(getBeDatosCargaAcademica());
           Date date =  new Date(user.getFechaServidor());
           Calendar cal = Calendar.getInstance(); // locale-specific
           cal.setTime(date);
           //cal.set(Calendar.HOUR_OF_DAY, 0);
           //cal.set(Calendar.MINUTE, 0);
           cal.set(Calendar.SECOND, 0);
           cal.set(Calendar.MILLISECOND, 0);
           beGuardarEntidadesGlobal.setFecha_servidor(cal.getTimeInMillis());
            ///agregar programa educativo  solo cuado
           callBack.onResponse(true, beGuardarEntidadesGlobal);
       }catch (Exception e){
           e.printStackTrace();
           callBack.onResponse(false, null);
       }

    }

    @Override
    public BEGuardarEntidadesGlobal getDatosExportarGlobalSimple() {
        SessionUser user = SessionUser.getCurrentUser();
        clearDataRubroEvaluacionProceso();

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        beGuardarEntidadesGlobal.setAsistencia(getGEDatosEnvioAsistencia());
        beGuardarEntidadesGlobal.setGrupo(getBEDatosEnvioGrupo());
        beGuardarEntidadesGlobal.setMensajeria(getBEDatosEnvioMensajeria());
        beGuardarEntidadesGlobal.setRubroEvaluacionProceso(getGEDatosRubroEvaluacionProceso2());
        beGuardarEntidadesGlobal.setSesionAprendizaje(getBEDatosSesionAprendizaje());
        beGuardarEntidadesGlobal.setTareaRecursos(getGEDatosTareasRecursos());
        beGuardarEntidadesGlobal.setUsuarioId(user.getUserId());
        beGuardarEntidadesGlobal.setCasos(getBEDatosCasos());
        beGuardarEntidadesGlobal.setCargaAcademica(getBeDatosCargaAcademica());
        Date date =  new Date(user.getFechaServidor());
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        //cal.set(Calendar.HOUR_OF_DAY, 0);
        //cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        beGuardarEntidadesGlobal.setFecha_servidor(cal.getTimeInMillis());
        return beGuardarEntidadesGlobal;
    }

    private void clearDataRubroEvaluacionProceso() {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCListEliminado = SQLite.select().
                    from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.estadoId.eq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                    .queryList(databaseWrapper);
            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new ArrayList<>();
           for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoCListEliminado){
              String key = rubroEvaluacionProcesoC.getKey();
              if(TextUtils.isEmpty(key))rubroEvaluacionProcesoCList.add(rubroEvaluacionProcesoC);
           }

            List<String> keyLis = new ArrayList<>();
            for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoCList)keyLis.add(rubroEvaluacionProcesoC.getKey());

            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<String> evaluacionKeyList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList)evaluacionKeyList.add(evaluacionProcesoC.getKey());

            List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = SQLite.select()
                    .from(RubroEvalRNPFormulaC.class)
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(keyLis))
                    .queryList(databaseWrapper);

            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> tRnMaeRubroEvaluacionProcesoEquipocList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);
            List<String> rubroEquipokeyList = new ArrayList<>();
            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipoc: tRnMaeRubroEvaluacionProcesoEquipocList)rubroEquipokeyList.add(rubroEvaluacionProcesoEquipoc.getKey());

            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> tRnMaeRubroEvaluacionProcesoIntegrantecList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(rubroEquipokeyList))
                    .queryList(databaseWrapper);

            List<EquipoEvaluacionProcesoC> equipoEvaluacionProcesoCList = SQLite.select()
                    .from(EquipoEvaluacionProcesoC.class)
                    .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(keyLis))
                    .queryList();

            List<RubroEvaluacionProcesoCampotematicoC> rubroEvaluacionProcesoCampotematicoCList = SQLite.select()
                    .from(RubroEvaluacionProcesoCampotematicoC.class)
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<CriterioRubroEvaluacionC> criterioRubroEvaluacionCList = SQLite.select()
                    .from(CriterioRubroEvaluacionC.class)
                    .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<RubroEvaluacionProcesoComentario> evaluacionProcesoComentarioList = SQLite.select()
                    .from(RubroEvaluacionProcesoComentario.class)
                    .where(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.in(evaluacionKeyList))
                    .queryList(databaseWrapper);

            List<ArchivosRubroProceso> archivosRubroProcesoList = SQLite.select()
                    .from(ArchivosRubroProceso.class)
                    .where(ArchivosRubroProceso_Table.evaluacionProcesoId.in(evaluacionKeyList))
                    .queryList(databaseWrapper);

            /*List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = SQLite.select()
                    .from(TareaRubroEvaluacionProceso.class)
                    .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);*/



            Log.d(TAG,"rubroEvaluacionProcesoCList delete export: " + rubroEvaluacionProcesoCList.size());
            Log.d(TAG,"evaluacionProcesoCList delete export: " + evaluacionProcesoCList.size());

            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, rubroEvaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, evaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, rubroEvalRNPFormulaCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, tRnMaeRubroEvaluacionProcesoEquipocList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, tRnMaeRubroEvaluacionProcesoIntegrantecList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, rubroEvaluacionProcesoCampotematicoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, criterioRubroEvaluacionCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoComentario.class, evaluacionProcesoComentarioList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class, archivosRubroProcesoList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            //TransaccionUtils.fastStoreListSyncFlagUpdate(TareaRubroEvaluacionProceso.class, tareaRubroEvaluacionProcesoList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, equipoEvaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    private BEDatosCargaAcademica getBeDatosCargaAcademica() {
        BEDatosCargaAcademica beDatosCargaAcademica = new BEDatosCargaAcademica();
        beDatosCargaAcademica.setObtenerCargaCursosCalendarioPeriodo(ConsultaUtils.getChangeItemsTable(CargaCursoCalendarioPeriodo.class));
        return beDatosCargaAcademica;
    }

    //#region getDatosExportarGlobal
    private GEDatosEnvioAsistencia getGEDatosEnvioAsistencia() {

        BEDatosEnvioAsistencia beDatosEnvioAsistencia = new BEDatosEnvioAsistencia();
        beDatosEnvioAsistencia.setAsistenciaAlumnos(ConsultaUtils.getChangeItemsTable(AsistenciaSesionAlumnoC.class));
        List<String> asistenciaSesionAlumnoKey = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: beDatosEnvioAsistencia.getAsistenciaAlumnos()){
            asistenciaSesionAlumnoKey.add(asistenciaSesionAlumnoC.getKey());
        }
        List<JustificacionC> justificacionCList = ConsultaUtils.getChangeItemsTableChild(JustificacionC.class, JustificacionC_Table.asistenciaSesionId.in(asistenciaSesionAlumnoKey));
        List<String> justificacionKeys = new ArrayList<>();
        for (JustificacionC justificacionC : justificacionCList)justificacionKeys.add(justificacionC.getKey());
        beDatosEnvioAsistencia.setJustificacion(justificacionCList);

        List<ArchivoAsistencia> archivoAsistenciaList = ConsultaUtils.getChangeItemsTableChild(ArchivoAsistencia.class, ArchivoAsistencia_Table.justificacionId.in(justificacionKeys));
        for (ArchivoAsistencia archivo : archivoAsistenciaList){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
            }
        }

        beDatosEnvioAsistencia.setArchivoAsistencia(archivoAsistenciaList);

        List<String> tipoNotakeys = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: beDatosEnvioAsistencia.getAsistenciaAlumnos()) {
            TipoNotaC tipoNotaC = SQLite.select()
                    .from(TipoNotaC.class)
                    .innerJoin(ValorTipoNotaC.class)
                    .on(TipoNotaC_Table.key.withTable()
                            .eq(ValorTipoNotaC_Table.tipoNotaId.withTable()))
                    .where(ValorTipoNotaC_Table.key.withTable().is(asistenciaSesionAlumnoC.getValorTipoNotaId()))
                    .querySingle();
            if(tipoNotaC == null)continue;

            tipoNotakeys.add(tipoNotaC.getKey());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));

        GEDatosEnvioAsistencia geDatosEnvioAsistencia = new GEDatosEnvioAsistencia();
        geDatosEnvioAsistencia.setBeDatosEnvioAsistencia(beDatosEnvioAsistencia);
        geDatosEnvioAsistencia.setBeDatosEnvioTipoNota(beDatosEnvioTipoNota);
        return geDatosEnvioAsistencia;
    }

    private BEDatosCasos getBEDatosCasos() {
        BEDatosCasos beDatosCasos = new BEDatosCasos();
        List<Caso> casoList = ConsultaUtils.getChangeItemsTable(Caso.class);
        beDatosCasos.setCaso(casoList);
        List<String> casoIdList = new ArrayList<>();

        for (Caso caso: casoList)casoIdList.add(caso.getKey());

        List<CasoArchivo> casoArchivos = SQLite.select()
                .from(CasoArchivo.class)
                .where(CasoArchivo_Table.casoId.in(casoIdList))
                .queryList();

        for (CasoArchivo archivo : casoArchivos){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
            }
        }
        beDatosCasos.setCasoArchivo(casoArchivos);
        beDatosCasos.setCasoReporte(ConsultaUtils.getChangeItemsTableChild(CasoReporte.class, CasoReporte_Table.casoId.in(casoIdList)));
        return beDatosCasos;
    }

    private BEDatosEnvioGrupo getBEDatosEnvioGrupo(){
        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTable(EquipoC.class));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTable(EquipoIntegranteC.class));
        beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTable(GrupoEquipoC.class));
        return beDatosEnvioGrupo;
    }

    private BEDatosEnvioMensajeria getBEDatosEnvioMensajeria(){
        BEDatosEnvioMensajeria beDatosEnvioMensajeria = new BEDatosEnvioMensajeria();

        List<MensajeUsuarioC> mensajeUsuario = ConsultaUtils.getChangeItemsTable(MensajeUsuarioC.class);//no se modifico el id de tipo int a String
        List<MensajeIntencionItemC> mensajeIntencionItem = ConsultaUtils.getChangeItemsTable(MensajeIntencionItemC.class);
        //List<UsuarioCanalComunicacion> usCanalComunicacion = ConsultaUtils.getChangeItemsTable(UsuarioCanalComunicacion.class);
        //List<CanalComunicacion> canalComunicacion = ConsultaUtils.getChangeItemsTable(CanalComunicacion.class);
        List<CanalDestinoEstadoC> canalDestinoEstado = ConsultaUtils.getChangeItemsTable(CanalDestinoEstadoC.class);
        List<MensajeC> mensajes = ConsultaUtils.getChangeItemsTable(MensajeC.class);
        //List<Intencion> intenciones = ConsultaUtils.getChangeItemsTable(Intencion.class);
        //List<IntencionItem> intencionItems = ConsultaUtils.getChangeItemsTable(IntencionItem.class);
        //List<ListaUsuario> listaUsuarios = ConsultaUtils.getChangeItemsTable(ListaUsuario.class);
        //List<ListaUsuarioDetalle> listUsuarioDetalle = ConsultaUtils.getChangeItemsTable(ListaUsuarioDetalle.class);
        List<MensajePredeterminado> listaMensajePredeterminado = ConsultaUtils.getChangeItemsTable(MensajePredeterminado.class);
        List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle = ConsultaUtils.getChangeItemsTable(MensajePredeterminadoDetalle.class);
        List<MensajePredIntencion> listMensajePredIntencion = ConsultaUtils.getChangeItemsTable(MensajePredIntencion.class);

        beDatosEnvioMensajeria.setMensajeUsuario(mensajeUsuario);
        beDatosEnvioMensajeria.setMensajeIntencionItem(mensajeIntencionItem);
        beDatosEnvioMensajeria.setCanalDestinoEstado(canalDestinoEstado);
        beDatosEnvioMensajeria.setMensajes(mensajes);
        beDatosEnvioMensajeria.setListaMensajePredeterminado(listaMensajePredeterminado);
        beDatosEnvioMensajeria.setListMensajePredeterminadoDetalle(listMensajePredeterminadoDetalle);
        beDatosEnvioMensajeria.setListMensajePredIntencion(listMensajePredIntencion);

        beDatosEnvioMensajeria.setListInteraccionTextual(ConsultaUtils.getChangeItemsTable(InteraccionTextual.class));
        return beDatosEnvioMensajeria;
    }

    private GEDatosRubroEvaluacionProceso getGEDatosRubroEvaluacionProceso() {

        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();

        //#region RubroEvaluacionProcesoC

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoC.class);
        List<String> rubroEvaluacionProcesoKey = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoList)rubroEvaluacionProcesoKey.add(rubroEvaluacionProcesoC.getKey());

        //#region RubroEvalRNPFormulaC

        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList =  SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionProcesoKey))
                .queryList();

        beDatosRubroEvaluacionProceso.setRubroEvalProcesoFormula(rubroEvalRNPFormulaCList);

        //#endregion RubroEvaluacionProcesoC

        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula()) {
            String key = rubroEvalRNPFormulaC.getRubroEvaluacionSecId();
            if (rubroEvaluacionProcesoKey.indexOf(key) == -1) {
                rubroEvaluacionProcesoKey.add(key);
                //#region RubroEvaluacionProcesoC Secundario
                rubroEvaluacionProcesoList.add(ConsultaUtils.getChangeItemTableChild(RubroEvaluacionProcesoC.class, RubroEvaluacionProcesoC_Table.key.is(key)));
                //#endregion RubroEvaluacionProcesoC Secundario
            }
        }
        beDatosRubroEvaluacionProceso.setRubroEvaluacionProceso(rubroEvaluacionProcesoList);
        //#endregion RubroEvaluacionProcesoC

        //#region EvaluacionProcesoC
        HashSet<EvaluacionProcesoC> evaluacionProcesoCList = new LinkedHashSet<>(ConsultaUtils.getChangeItemsTableChild(EvaluacionProcesoC.class, EvaluacionProcesoC_Table.key.in(rubroEvaluacionProcesoKey)));
        evaluacionProcesoCList.addAll(ConsultaUtils.getChangeItemsTable(EvaluacionProcesoC.class));
        beDatosRubroEvaluacionProceso.setEvaluacionProceso(new ArrayList<>(evaluacionProcesoCList));
        //#endregion EvaluacionProcesoC

        //#region EquipoEvaluacionProcesoC
        beDatosRubroEvaluacionProceso.setObtenerEquipoEvaluacionProceso(
                ConsultaUtils.getChangeItemsTableChild(
                        EquipoEvaluacionProcesoC.class, EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion EquipoEvaluacionProcesoC

        //#region RubroEvaluacionProcesoCampotematicoC
        beDatosRubroEvaluacionProceso.setRubro_evaluacion_proceso_campotematico(ConsultaUtils.getChangeItemsTableChild(RubroEvaluacionProcesoCampotematicoC.class, RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion RubroEvaluacionProcesoCampotematicoC

        //#region CriterioRubroEvaluacionC
        beDatosRubroEvaluacionProceso.setObtenerCriterioRubroEvaluacionProceso(ConsultaUtils.getChangeItemsTableChild(CriterioRubroEvaluacionC.class, CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion CriterioRubroEvaluacionC

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoEquipo(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC

        List<String> equipoRubroEvaluacionProcesoKey = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc: beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()){
            equipoRubroEvaluacionProcesoKey.add(procesoEquipoc.getKey());
        }

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoIntegrante(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(equipoRubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC

        List<String> evalaucionListIds = new ArrayList<>();
        for (EvaluacionProcesoC evaluacionProcesoC: beDatosRubroEvaluacionProceso.getEvaluacionProceso())evalaucionListIds.add(evaluacionProcesoC.getKey());

        beDatosRubroEvaluacionProceso.setRubroEvaluacionProcesoComentario(ConsultaUtils.getChangeItemsTableChild(RubroEvaluacionProcesoComentario.class, RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.in(evalaucionListIds)));
        List<ArchivosRubroProceso> archivoList = ConsultaUtils.getChangeItemsTableChild(ArchivosRubroProceso.class,  ArchivosRubroProceso_Table.evaluacionProcesoId.in(evalaucionListIds));
        for (ArchivosRubroProceso archivo : archivoList){
            String fileName = archivo.getUrl();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setUrl(file);
                Log.d(getClass().getSimpleName(),"file ArchivosRubroProceso: " + file);
            }
        }

        beDatosRubroEvaluacionProceso.setArchivoRubroProceso(archivoList);

        GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);

        List<String> tipoNotakeys = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoList) {
            tipoNotakeys.add(rubroEvaluacionProcesoC.getTipoNotaId());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));
        geDatosRubroEvaluacionProceso.setBeDatosEnvioTipoNota(beDatosEnvioTipoNota);

        List<String> equipokeys = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC maeRubroEvaluacionProcesoEquipoc: beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()) {
            equipokeys.add(maeRubroEvaluacionProcesoEquipoc.getEquipoId());
        }
        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTableChild(EquipoC.class, EquipoC_Table.key.in(equipokeys)));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTableChild(EquipoIntegranteC.class, EquipoIntegranteC_Table.equipoId.in(equipokeys)));

        List<String> grupokeys = new ArrayList<>();
        for (EquipoC equipo: beDatosEnvioGrupo.getEquipo()) {
            grupokeys.add(equipo.getGrupoEquipoId());
        }
        beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTableChild(GrupoEquipoC.class, GrupoEquipoC_Table.key.in(grupokeys)));
        geDatosRubroEvaluacionProceso.setBeDatosEnvioGrupo(beDatosEnvioGrupo);

        List<String> rubroEvaluacionKey = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC:
                rubroEvaluacionProcesoList)rubroEvaluacionKey.add(rubroEvaluacionProcesoC.getKey());

        final GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();

        /*List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                    TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(rubroEvaluacionKey));*/

        List<String> TareaKeyList = new ArrayList<>();
        /*for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso: tareaRubroEvaluacionProcesoList){
                TareaKeyList.add(tareaRubroEvaluacionProceso.getTareaId());
        }*/
        List<TareasC> tareasCList = ConsultaUtils.getChangeItemsTableChild(TareasC.class,
                TareasC_Table.key.in(TareaKeyList));

        beDatosTareasRecursos.setTareas(tareasCList);

        List<String> tareaKey = new ArrayList<>();
        for (TareasC tareasC: beDatosTareasRecursos.getTareas()){
            tareaKey.add(tareasC.getKey());
        }
        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaKey)));
        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }
        beDatosTareasRecursos.setRecursoDidactico(ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey)));

        //beDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);
        //Log.d(getClass().getSimpleName(),"size tarea recuros" +  beDatosTareasRecursos.getTareaRubroEvaluacionProceso().size());
        geDatosRubroEvaluacionProceso.setBeDatosTareaRecursos(beDatosTareasRecursos);

        BEDatosEvaluacionResultado beDatosEvaluacionResultado = new BEDatosEvaluacionResultado();
        List<RubroEvaluacionResultado> rubroEvaluacionResultado = ConsultaUtils.getChangeItemsTable(RubroEvaluacionResultado.class);
        List<EvaluacionResultadoC> evaluacionResultados = ConsultaUtils.getChangeItemsTable(EvaluacionResultadoC.class);
        beDatosEvaluacionResultado.setRubroEvaluacionResultado(rubroEvaluacionResultado);
        beDatosEvaluacionResultado.setEvaluacionResultado(evaluacionResultados);
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionResultado(beDatosEvaluacionResultado);
        return geDatosRubroEvaluacionProceso;
    }

    private BEDatosSesionAprendizaje getBEDatosSesionAprendizaje(){
        BEDatosSesionAprendizaje beDatosSesionAprendizaje = new BEDatosSesionAprendizaje();
        beDatosSesionAprendizaje.setSesionAprendizaje(ConsultaUtils.getChangeItemsTable(SesionAprendizaje.class));
        return beDatosSesionAprendizaje;
    }

    private GEDatosTareasRecursos getGEDatosTareasRecursos(){
        final GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();
        List<TareasC> tareasCList = ConsultaUtils.getChangeItemsTable(TareasC.class);
        beDatosTareasRecursos.setTareas(tareasCList);

        List<String> tareaKey = new ArrayList<>();
        for (TareasC tareasC: beDatosTareasRecursos.getTareas()){
            tareaKey.add(tareasC.getKey());
        }

        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaKey)));

        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }

        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey));
        beDatosTareasRecursos.setRecursoDidactico(recursoDidacticoEventoCList);

        List<String> recusoDidacticoIdList = new ArrayList<>();
        for (RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recusoDidacticoIdList.add(recursoDidacticoEventoC.getKey());

        List<RecursoArchivo> recursoArchivoList = ConsultaUtils.getChangeItemsTableChild(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recusoDidacticoIdList));
        beDatosTareasRecursos.setRecursoArchivo(recursoArchivoList);

        List<String> archivoIdList = new ArrayList<>();
        for (RecursoArchivo recursoArchivo: recursoArchivoList)archivoIdList.add(recursoArchivo.getArchivoId());
        List<Archivo> archivoList = ConsultaUtils.getChangeItemsTableChild(Archivo.class, Archivo_Table.key.in(archivoIdList));
        for (Archivo archivo : archivoList){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
                Log.d(getClass().getSimpleName(),"file: " + file);
            }
        }
        beDatosTareasRecursos.setArchivo(archivoList);
        /*List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                TareaRubroEvaluacionProceso_Table.tareaId.in(tareaKey));

        beDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);*/

        return beDatosTareasRecursos;
    }

    private GEDatosRubroEvaluacionProceso getGEDatosRubroEvaluacionProceso2() {


        Set<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new LinkedHashSet<>(ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoC.class));
        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> tRnMaeRubroEvaluacionProcesoEquipocList = ConsultaUtils.getChangeItemsTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class);
        List<EquipoEvaluacionProcesoC> equipoEvaluacionProcesoCList = ConsultaUtils.getChangeItemsTable(EquipoEvaluacionProcesoC.class);
        Set<EvaluacionProcesoC> evaluacionProcesoCList = new LinkedHashSet<>(ConsultaUtils.getChangeItemsTable(EvaluacionProcesoC.class));
        List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentarioList = ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoComentario.class);
        List<ArchivosRubroProceso> archivosRubroProcesoList = ConsultaUtils.getChangeItemsTable(ArchivosRubroProceso.class);
        List<CriterioRubroEvaluacionC> criterioRubroEvaluacionCList = ConsultaUtils.getChangeItemsTable(CriterioRubroEvaluacionC.class);
        //List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTable(TareaRubroEvaluacionProceso.class);
        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = ConsultaUtils.getChangeItemsTable(RubroEvalRNPFormulaC.class);

        Set<String> evaluacionProcesoIdList = new LinkedHashSet<>();
        for (RubroEvaluacionProcesoComentario itemComentario : rubroEvaluacionProcesoComentarioList) {
            evaluacionProcesoIdList.add(itemComentario.getEvaluacionProcesoId());
        }
        for (ArchivosRubroProceso itemArchivo : archivosRubroProcesoList) {
            evaluacionProcesoIdList.add(itemArchivo.getEvaluacionProcesoId());
        }
        evaluacionProcesoCList.addAll(SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.in(evaluacionProcesoIdList))
                .queryList());

        Set<String> rubroEvaluacionIdList = new LinkedHashSet<>();
        for (RubroEvaluacionProcesoC itemRubroEvaluacion : rubroEvaluacionProcesoCList) {
            Log.d(TAG, "itemRubroEvaluacion : " + itemRubroEvaluacion.key);
            rubroEvaluacionIdList.add(itemRubroEvaluacion.getKey());
        }

        for (RubroEvaluacionProcesoC itemRubroEvaluacion : rubroEvaluacionProcesoCList) {
            Log.d(TAG, "itemRubroEvaluacion : " + itemRubroEvaluacion.key);
            rubroEvaluacionIdList.add(itemRubroEvaluacion.getKey());
        }

        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemEquipo : tRnMaeRubroEvaluacionProcesoEquipocList) {
            Log.d(TAG, "T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC : " + itemEquipo.key);
            rubroEvaluacionIdList.add(itemEquipo.getRubroEvalProcesoId());
        }
        for (EquipoEvaluacionProcesoC itemEquipoEvaluacion : equipoEvaluacionProcesoCList) {
            Log.d(TAG, "itemEquipoEvaluacion : " + itemEquipoEvaluacion.key);
            rubroEvaluacionIdList.add(itemEquipoEvaluacion.getRubroEvalProcesoId());
        }
        for (EvaluacionProcesoC itemEvaluacion : evaluacionProcesoCList) {
            Log.d(TAG, "itemEvaluacion : " + itemEvaluacion.key);
            rubroEvaluacionIdList.add(itemEvaluacion.getRubroEvalProcesoId());
        }
        for (CriterioRubroEvaluacionC itemCriterio : criterioRubroEvaluacionCList) {
            Log.d(TAG, "itemCriterio : " + itemCriterio.key);
            rubroEvaluacionIdList.add(itemCriterio.getRubroEvalProcesoId());
        }
        /*
        for (TareaRubroEvaluacionProceso itemTarea : tareaRubroEvaluacionProcesoList) {
            Log.d(TAG, "itemTarea : " + itemTarea.key);
            rubroEvaluacionIdList.add(itemTarea.getRubroEvalProcesoId());
        }*/

        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : rubroEvalRNPFormulaCList) {
            Log.d(TAG, "rubroEvalRNPFormulaC : " + rubroEvalRNPFormulaC.key);
            rubroEvaluacionIdList.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
        }


        GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();
        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();
        getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, new ArrayList<String>(rubroEvaluacionIdList));
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);

        List<String> tareaIdList = new ArrayList<>();
        /*
        for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso : tareaRubroEvaluacionProcesoList){
            tareaIdList.add(tareaRubroEvaluacionProceso.getTareaId());
        }*/

        GEDatosTareasRecursos geDatosTareasRecursos = getTareasRecursos(tareaIdList);
        //geDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);
        geDatosRubroEvaluacionProceso.setBeDatosTareaRecursos(geDatosTareasRecursos);
        geDatosRubroEvaluacionProceso.setBeDatosEnvioGrupo(getGrupoRubro(tRnMaeRubroEvaluacionProcesoEquipocList));


        BEDatosEvaluacionResultado beDatosEvaluacionResultado = new BEDatosEvaluacionResultado();
        List<RubroEvaluacionResultado> rubroEvaluacionResultado = ConsultaUtils.getChangeItemsTable(RubroEvaluacionResultado.class);
        List<EvaluacionResultadoC> evaluacionResultados = ConsultaUtils.getChangeItemsTable(EvaluacionResultadoC.class);
        beDatosEvaluacionResultado.setRubroEvaluacionResultado(rubroEvaluacionResultado);
        beDatosEvaluacionResultado.setEvaluacionResultado(evaluacionResultados);
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionResultado(beDatosEvaluacionResultado);

        return geDatosRubroEvaluacionProceso;
    }

    private GEDatosTareasRecursos getTareasRecursos(List<String> tareaIdList){
        GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();

        List<TareasC> tareasCList = SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.key.in(tareaIdList))
                .and(TareasC_Table.syncFlag.in(TareasC.FLAG_ADDED, TareasC.FLAG_UPDATED))
                .queryList();
        tareaIdList.clear();
        for (TareasC tareasC : tareasCList)tareaIdList.add(tareasC.getKey());

        beDatosTareasRecursos.setTareas(tareasCList);

        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaIdList)));

        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }

        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey));
        beDatosTareasRecursos.setRecursoDidactico(recursoDidacticoEventoCList);

        List<String> recusoDidacticoIdList = new ArrayList<>();
        for (RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recusoDidacticoIdList.add(recursoDidacticoEventoC.getKey());

        List<RecursoArchivo> recursoArchivoList = ConsultaUtils.getChangeItemsTableChild(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recusoDidacticoIdList));
        beDatosTareasRecursos.setRecursoArchivo(recursoArchivoList);

        List<String> archivoIdList = new ArrayList<>();
        for (RecursoArchivo recursoArchivo: recursoArchivoList)archivoIdList.add(recursoArchivo.getArchivoId());
        List<Archivo> archivoList = ConsultaUtils.getChangeItemsTableChild(Archivo.class, Archivo_Table.key.in(archivoIdList));
        for (Archivo archivo : archivoList){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
                Log.d(getClass().getSimpleName(),"file: " + file);
            }
        }
        beDatosTareasRecursos.setArchivo(archivoList);

        return beDatosTareasRecursos;
    }

    private void getRubroEvaluacionProceso(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso ,List<String> rubroEvaluacionId){

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoC_Table.syncFlag.in(RubroEvaluacionProcesoC.FLAG_ADDED, RubroEvaluacionProcesoC.FLAG_UPDATED))
                .queryList();

        beDatosRubroEvaluacionProceso.addRubroEvaluacionProceso(rubroEvaluacionProcesoCList);


        beDatosRubroEvaluacionProceso.addRubro_evaluacion_proceso_campotematico(SQLite.select()
                .from(RubroEvaluacionProcesoCampotematicoC.class)
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoCampotematicoC_Table.syncFlag.in(RubroEvaluacionProcesoCampotematicoC.FLAG_ADDED,RubroEvaluacionProcesoCampotematicoC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerCriterioRubroEvaluacionProceso(SQLite.select()
                .from(CriterioRubroEvaluacionC.class)
                .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(CriterioRubroEvaluacionC_Table.syncFlag.in(CriterioRubroEvaluacionC.FLAG_ADDED,CriterioRubroEvaluacionC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerRubroEvaluacionProcesoEquipo(SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.syncFlag.withTable().in(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.FLAG_ADDED,T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerRubroEvaluacionProcesoIntegrante(SQLite.select(Utils.f_allcolumnTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.ALL_COLUMN_PROPERTIES))
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .on(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable().eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable()))
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.syncFlag.withTable().in(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.FLAG_ADDED,T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerEquipoEvaluacionProceso(SQLite.select()
                .from(EquipoEvaluacionProcesoC.class)
                .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(EquipoEvaluacionProcesoC_Table.syncFlag.in(EquipoEvaluacionProcesoC.FLAG_ADDED,EquipoEvaluacionProcesoC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addEvaluacionProceso(SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(EvaluacionProcesoC_Table.syncFlag.in(EvaluacionProcesoC.FLAG_ADDED, EvaluacionProcesoC.FLAG_UPDATED))
                .queryList());

        beDatosRubroEvaluacionProceso.addRubroEvaluacionProcesoComentario(SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoComentario_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoComentario.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.withTable().eq(EvaluacionProcesoC_Table.key.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoComentario_Table.syncFlag.withTable().in(RubroEvaluacionProcesoComentario.FLAG_ADDED, RubroEvaluacionProcesoComentario.FLAG_UPDATED))
                .queryList());
        beDatosRubroEvaluacionProceso.addArchivoRubroProceso(SQLite.select(Utils.f_allcolumnTable(ArchivosRubroProceso_Table.ALL_COLUMN_PROPERTIES))
                .from(ArchivosRubroProceso.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(ArchivosRubroProceso_Table.evaluacionProcesoId.withTable().eq(EvaluacionProcesoC_Table.key.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(ArchivosRubroProceso_Table.syncFlag.withTable().in(ArchivosRubroProceso.FLAG_ADDED, ArchivosRubroProceso.FLAG_UPDATED))
                .queryList());

        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.syncFlag.withTable().in(RubroEvalRNPFormulaC.FLAG_ADDED, RubroEvalRNPFormulaC.FLAG_UPDATED))
                .queryList();

        beDatosRubroEvaluacionProceso.addRubroEvalProcesoFormula(rubroEvalRNPFormulaCList);

        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList2 = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionId))
                .queryList();

        Set<String> rubroDetalleId = new LinkedHashSet<>();
        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : rubroEvalRNPFormulaCList2)rubroDetalleId.add(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());

        if(!rubroDetalleId.isEmpty()){
            Log.d(TAG, "rubroDetalleId: " + rubroDetalleId);
            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, new ArrayList<String>(rubroDetalleId));
        }


        /*

        rubroActualizarUi.setTareaRubroEvaluacionProcesoList(new ArrayList<Object>(SQLite.select()
                .from(TareaRubroEvaluacionProceso.class)
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                .and(TareaRubroEvaluacionProceso_Table.syncFlag.in(TareaRubroEvaluacionProceso.FLAG_ADDED, TareaRubroEvaluacionProceso.FLAG_UPDATED))
                .queryList()));

        rubroActualizarUi.setTareaCList(new ArrayList<Object>(SQLite.select(Utils.f_allcolumnTable(TareasC_Table.ALL_COLUMN_PROPERTIES))
                .from(TareasC.class)
                .innerJoin(TareaRubroEvaluacionProceso.class)
                .on(TareasC_Table.key.withTable().eq(TareaRubroEvaluacionProceso_Table.tareaId.withTable()))
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionProcesoC.getKey()))
                .and(TareaRubroEvaluacionProceso_Table.syncFlag.withTable().in(TareaRubroEvaluacionProceso.FLAG_ADDED, TareaRubroEvaluacionProceso.FLAG_UPDATED))
                .queryList()));*/


    }


    private BEDatosEnvioGrupo getGrupoRubro(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacionProcesoEquipocs){

        Set<String> equiposIdList = new LinkedHashSet<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipoc : evaluacionProcesoEquipocs)equiposIdList.add(rubroEvaluacionProcesoEquipoc.getEquipoId());

        List<EquipoC> equipoCList = SQLite.select()
                .from(EquipoC.class)
                .where(EquipoC_Table.key.in(equiposIdList))
                .and(EquipoC_Table.syncFlag.in(EquipoC.FLAG_ADDED, EquipoC.FLAG_UPDATED))
                .queryList();

        List<EquipoIntegranteC> equipoIntegranteCS = SQLite.select()
                .from(EquipoIntegranteC.class)
                .where(EquipoIntegranteC_Table.equipoId.in(equiposIdList))
                .and(EquipoIntegranteC_Table.syncFlag.in(EquipoIntegranteC.FLAG_ADDED, EquipoIntegranteC.FLAG_UPDATED))
                .queryList();

        Set<String> grupoEquipoIdList = new LinkedHashSet<>();
        for (EquipoC equipoC: equipoCList)grupoEquipoIdList.add(equipoC.getGrupoEquipoId());

        List<GrupoEquipoC> grupoEquipoCList = SQLite.select()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.key.in(grupoEquipoIdList))
                .and(GrupoEquipoC_Table.syncFlag.in(GrupoEquipoC.FLAG_ADDED, GrupoEquipoC.FLAG_UPDATED))
                .queryList();

        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setGrupo_equipo(grupoEquipoCList);
        beDatosEnvioGrupo.setEquipo(equipoCList);
        beDatosEnvioGrupo.setEquipo_integrante(equipoIntegranteCS);
        return beDatosEnvioGrupo;
    }


    //#endregion getDatosExportarGlobal

}
