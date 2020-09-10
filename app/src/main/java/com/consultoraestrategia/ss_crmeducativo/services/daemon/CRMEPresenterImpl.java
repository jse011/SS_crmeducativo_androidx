package com.consultoraestrategia.ss_crmeducativo.services.daemon;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.data.AdpaterEvalFormulaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.ui.CRMEEvent;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.ui.ExportarIntentService;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.GEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
/**
 * Created by SCIEV on 24/05/2018.
 */

public class CRMEPresenterImpl implements CRMEPresenter {
    private final static String TAG = CRMEPresenterImpl.class.getSimpleName();
    private CRMEEvent event;
    private Thread thread;
    private BEDatosTareaRecursosRepository datosTareaRecursosRepository;
    private GEDatosEnvioAsistenciaRepository geDatosEnvioAsistenciaRepository;
    private GEDatosRubroEvaluacionProcesoRepository geDatosRubroEvaluacionProcesoRepository;
    private BEDatosSesionAprendizajeRepository beDatosSesionAprendizajeRepository;
    private BEDatosEnvioGrupoRepository beDatosEnvioGrupoRepository;
    private BEDatosRubroEvaluacionProcesoRepository beDatosRubroEvaluacionProcesoRepository;
    private AdpaterEvalFormulaRepository evalFormulaRepository;
    private TipoExportacion tipoExportacion = TipoExportacion.DEFAULT;
    private BEDatosEnvioMensajeriaRepository beDatosEnvioMensajeriaRepository;
    public CRMEPresenterImpl(BEDatosTareaRecursosRepository datosTareaRecursosRepository, GEDatosEnvioAsistenciaRepository geDatosEnvioAsistenciaRepository, GEDatosRubroEvaluacionProcesoRepository geDatosRubroEvaluacionProcesoRepository, BEDatosSesionAprendizajeRepository beDatosSesionAprendizajeRepository, BEDatosEnvioGrupoRepository beDatosEnvioGrupoRepository,AdpaterEvalFormulaRepository evalFormulaRepository,BEDatosRubroEvaluacionProcesoRepository beDatosRubroEvaluacionProcesoRepository, BEDatosEnvioMensajeriaRepository beDatosEnvioMensajeriaRepository) {
        this.datosTareaRecursosRepository = datosTareaRecursosRepository;
        this.geDatosEnvioAsistenciaRepository = geDatosEnvioAsistenciaRepository;
        this.geDatosRubroEvaluacionProcesoRepository = geDatosRubroEvaluacionProcesoRepository;
        this.beDatosSesionAprendizajeRepository = beDatosSesionAprendizajeRepository;
        this.beDatosEnvioGrupoRepository = beDatosEnvioGrupoRepository;
        this.evalFormulaRepository = evalFormulaRepository;
        this.beDatosRubroEvaluacionProcesoRepository = beDatosRubroEvaluacionProcesoRepository;
        this.beDatosEnvioMensajeriaRepository = beDatosEnvioMensajeriaRepository;
    }

    @Override
    public void attachView(CRMEEvent event) {
        this.event = event;
    }

    @Override
    public void onCreate() {

    }

    private void simularProgreso(final int cantidad, String mensaje) {
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, mensaje,tipoExportacion.toString()+" Procesando...", cantidad, 0);
        try {
            // Bucle de simulación
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(1000);
                if(event == null)return;
                event.setNotificacionProgress(cantidad,i);
                Log.d(TAG,tipoExportacion.toString()+" i:"+i);
            }

        } catch (InterruptedException e) {
                    e.printStackTrace();
        }
        event.hideNotificacionProgress();
                //event.destroyService();
    }

    @Override
    public void onDestroy() {
        this.datosTareaRecursosRepository = null;
        this.geDatosEnvioAsistenciaRepository = null;
        this.geDatosRubroEvaluacionProcesoRepository = null;
        this.beDatosSesionAprendizajeRepository = null;
        this.beDatosEnvioGrupoRepository = null;
        this.evalFormulaRepository = null;
        this.beDatosRubroEvaluacionProcesoRepository = null;
        this.event = null;
    }

    @Override
    public void onHandleIntent() {
        try {

            int usarioId = SessionUser.getCurrentUser().getUserId();
            showNotificacionProgress(usarioId);
            onChangeDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setExtra(Bundle extra) {
        if(extra== null)return;
        try {
            this.tipoExportacion = TipoExportacion.valueOf(extra.getString(ExportarIntentService.ENUM_TIPOEXPORTACION));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(this.tipoExportacion==null)this.tipoExportacion = TipoExportacion.DEFAULT;
        }
        Log.d(TAG,"extra: "+extra);
    }

    private void onChangeDataBase() {
        switch (tipoExportacion){
            case TAREA:
                changenTareaRecursos();
                break;
            case SESIONES:
                changenSesionAprendizaje();
                break;
            case ASISTENCIA:
                changenAsistenciaEvaluacion();
                break;
            case RUBROEVALUACION:
                changenRubroEvaluacionProceso();
                break;
            case DEFAULT:
                changenRubroEvaluacionProceso();
                changenAsistenciaEvaluacion();
                changenTareaRecursos();
                changenSesionAprendizaje();
                changenGrupoEquipo();
                break;
            case GRUPO:
                changenGrupoEquipo();
                break;
            case RUBROEVALUACION_FORMULA_ASOCIADOS:
                updateRubrosFormula();
                break;
            case MENSAJE:
                changeMensajes();
                break;
            case INTERACCIONTEXTUAL:
                break;
        }

    }


    private void changeMensajes() {
        beDatosEnvioMensajeriaRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<BEDatosEnvioMensajeria>() {
            @Override
            public void onResponse(boolean success, BEDatosEnvioMensajeria item) {
                Log.d(TAG,"Exportar changeMensajes is "+ success);
                SuccessNotificacion(success, item);
            }
        });
    }


    private void updateRubrosFormula(){
        evalFormulaRepository.onUpdateEvaluacionFormula( new AdpaterEvalFormulaRepository.SuccessCallBack() {
            @Override
            public void onSuccess(boolean success) {
                Log.d(TAG, "onUpdateEvaluacionFormula is " + success);
                changenRubroEvaluacionProceso();
            }
        });
    }

    private void changenSesionAprendizaje() {
        beDatosSesionAprendizajeRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<BEDatosSesionAprendizaje>() {
            @Override
            public void onResponse(boolean success, BEDatosSesionAprendizaje item) {
                SuccessNotificacion(success, item);
            }
        });
    }

    private void changenTareaRecursos() {
        datosTareaRecursosRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<GEDatosTareasRecursos>() {
            @Override
            public void onResponse(boolean success, GEDatosTareasRecursos item) {
                Log.d(TAG,"Exportar changenRubroEvaluacionProceso is "+ success);
                SuccessNotificacion(success, item);
            }
        });

    }

    private void changenAsistenciaEvaluacion() {
        geDatosEnvioAsistenciaRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<GEDatosEnvioAsistencia>() {
            @Override
            public void onResponse(boolean success, GEDatosEnvioAsistencia item) {
                Log.d(TAG,"Exportar changenAsistencia is "+ success);
                SuccessNotificacion(success, item);
            }
        });
    }

    private void changenRubroEvaluacionProceso() {
        geDatosRubroEvaluacionProcesoRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<GEDatosRubroEvaluacionProceso>() {
            @Override
            public void onResponse(boolean success, GEDatosRubroEvaluacionProceso item) {
                Log.d(TAG,"Exportar changenRubros is "+ success);
                SuccessNotificacion(success, item);
            }
        });
    }

    private void changenGrupoEquipo() {
        beDatosEnvioGrupoRepository.getDatosExportar(new ServiceDataSource.ObjectCallBack<BEDatosEnvioGrupo>() {
            @Override
            public void onResponse(boolean success, BEDatosEnvioGrupo item) {
                Log.d(TAG,"Exportar changenRubros is "+ success);
                SuccessNotificacion(success, item);
            }
        });
    }

    private void SuccessNotificacion(boolean success, Object item){
        int usarioId = SessionUser.getCurrentUser().getUserId();
        if (success){
            if(item != null){
                showNotificacionProgressFinalizo(usarioId);
            }else {
                if(event!=null)event.hideNotificacion();
            }
        }else {
            showNotificacionProgressError(usarioId);
        }
    }

    private String mensajeNotificacion(){
        String tipo = "";
        switch (tipoExportacion){
            case TAREA:
                tipo += "las Tareas";
                break;
            case SESIONES:
                tipo += "las Sesiones de Aprendizaje";
                break;
            case ASISTENCIA:
                tipo += "las Asistencias";
                break;
            case RUBROEVALUACION:
                tipo += "los rubros de evaluaciones";
                break;
            case RUBROEVALUACION_FORMULA_ASOCIADOS:
                tipo += "los Rubros de Evaluación";
                break;
            case GRUPO:
                tipo += "las listas de Grupos";
                break;
            case MENSAJE:
                tipo += "las listas de Mensajes y Comentarios";
                break;
            case DEFAULT:
                tipo += "todos los Módulos";
                break;
        }
        return tipo;
    }

    private void showNotificacionProgress(int id){
        //if(event == null)return;
        //event.showNotificacion(R.drawable.logo_consultoraa, "Exportación", "Se empezó a comprobar si hay datos para exportar de " + mensajeNotificacion(),"" ,id);
    }

    private void showNotificacionProgressFinalizo(int id){
        //if(event == null)return;
        //event.showNotificacion(R.drawable.logo_consultoraa, "Exportación", "Se concluyó la exportación de "+ mensajeNotificacion(),"", id);
    }

    private void showNotificacionProgressError(int id){
        if(event == null)return;
        event.showNotificacion(R.drawable.logo_consultoraa, "Mensaje de actualización", "Se concluyó con error la exportación de "+ mensajeNotificacion(),"" , id);
    }


}
