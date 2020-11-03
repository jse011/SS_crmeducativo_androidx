package com.consultoraestrategia.ss_crmeducativo.services.importar;

import android.os.Bundle;
import android.util.Log;

import androidx.work.Data;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarEvent;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarJobService;

/**
 * Created by SCIEV on 24/05/2018.
 */

public class ImportarPresenterImpl implements ImportarPresenter {
    private final static String TAG = ImportarPresenterImpl.class.getSimpleName();
    private ImportarEvent event;

    private BEDatosSesionAprendizajeRepository beDatosSesionAprendizajeRepository;
    private TipoImportacion tipoImportacion = TipoImportacion.DEFAULT;
    private BEVariables importarWrapper;


    public ImportarPresenterImpl( BEDatosSesionAprendizajeRepository beDatosSesionAprendizajeRepository) {
        this.beDatosSesionAprendizajeRepository = beDatosSesionAprendizajeRepository;
    }

    @Override
    public void attachView(ImportarEvent event) {
        this.event = event;
    }

    @Override
    public void onCreate() {

    }


    @Override
    public void onDestroy() {
        event = null;
    }

    @Override
    public void onHandleIntent() {
        onChangeDataBase();
    }

    @Override
    public void setExtra(Data extra) {
        if(extra== null)return;
        try {
            this.tipoImportacion = TipoImportacion.valueOf(extra.getString(ImportarJobService.ENUM_TIPOIMPORTACION));
            this.importarWrapper = BEVariables.getBundle(extra);
            Log.d(TAG,"extra: "+importarWrapper.toString());
        }catch (Exception e){
            e.printStackTrace();
            if(this.tipoImportacion==null)this.tipoImportacion = TipoImportacion.DEFAULT;
            if(this.importarWrapper ==null)this.importarWrapper = new BEVariables();
        }
        Log.d(TAG,"extra: "+extra);
    }

    private void onChangeDataBase() {
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, "Mensaje de actualización", tipoImportacion.getMsgExecute(), "",this.importarWrapper.getUsuarioId());
        switch (tipoImportacion){
            case TAREA:
                //changenTareaRecursos();
                break;
            case SESIONES:
                changenSesionAprendizaje();
                break;
            case ASISTENCIA:
                //changenAsistenciaEvaluacion();
                break;
            case RUBROEVALUACION:
                //changenRubroEvaluacionProceso();
                break;
            case UNIDAD:
                changenUnidadAprendizaje();
                break;
            case TIPONOTA:
                //changenTipoNota();
                break;
            case STRATEGY_LOGIN:
                break;
            case CALENDARIO_PERIODO:
                //SaveBECalendarioPeriodo();
                break;
            case GRUPOS:
                //SaveGEDatosListEnvioGrupoImport();
            case DEFAULT:
                break;
        }

    }

    private void changenUnidadAprendizaje() {
        beDatosSesionAprendizajeRepository.getDatosImportarListSesionPorUnidades(this.importarWrapper, new ServiceDataSource.ObjectCallBack<BEDatosSesionAprendizaje>() {
            @Override
            public void onResponse(boolean success, BEDatosSesionAprendizaje item) {
                Log.d(TAG,"Importar changenUnidadAprendizaje is "+ success);
                int userId;
                try {
                    userId = SessionUser.getCurrentUser().getUserId();
                }catch (Exception e){
                    userId = -1;
                }
                if (success){
                    showNotificacionProgress(userId);
                }else {
                    showNotificacionProgressError(userId);
                }
                onFinishMessaje(tipoImportacion, success, importarWrapper);
            }
        });
    }


    void onFinishMessaje(TipoImportacion tipoImportacion, boolean success, BEVariables importarWrapper){
        if(event!=null)event.onFinishMessaje(tipoImportacion, success,importarWrapper);
    }

    private void changenSesionAprendizaje() {
        beDatosSesionAprendizajeRepository.getDatosImportar(this.importarWrapper, new ServiceDataSource.ObjectCallBack<BEDatosSesionAprendizaje>() {
            @Override
            public void onResponse(boolean success, BEDatosSesionAprendizaje item) {
                Log.d(TAG,"Importar changenRubroEvaluacionProceso is "+ success);
                int userId;
                try {
                    userId = SessionUser.getCurrentUser().getUserId();
                }catch (Exception e){
                    userId = -1;
                }
                if (success){
                    showNotificacionProgress(userId);
                }else {
                    showNotificacionProgressError(userId);
                }
                onFinishMessaje(tipoImportacion, success, importarWrapper);
            }
        });
    }

    private void showNotificacionProgress(int id){
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, "Mensaje de actualización", tipoImportacion.getMsgSuccess(), "",id);
    }

    private void showNotificacionProgressError(int id){
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, "Mensaje de actualización",  tipoImportacion.getMsgError(), "", SessionUser.getCurrentUser().getUserId());
    }

}
