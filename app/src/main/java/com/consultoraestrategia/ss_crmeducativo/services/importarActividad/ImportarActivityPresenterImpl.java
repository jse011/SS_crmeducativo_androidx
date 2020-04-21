package com.consultoraestrategia.ss_crmeducativo.services.importarActividad;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivityView;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosCalendarioPeriodoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosEnvioGrupoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosEnvioTipoNotaImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosListEnvioGrupoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosLoginImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosTareaRecursoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosListRubroEvaluacionProcesoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosRubroEvaluacionProcesoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosSesionAprendizajeImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEListDatosSesionAprendizajeImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.SaveDatosCompletosLogin;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ImportarActivityPresenterImpl extends BasePresenterImpl<ImportarActivityView> implements ImportarActivityPresenter {

    private SaveGEDatosRubroEvaluacionProcesoImport saveGEDatosRubroEvaluacionProcesoImport;
    private SaveGEDatosSesionAprendizajeImport saveGEDatosSesionAprendizajeImport;
    private SaveGEListDatosSesionAprendizajeImport saveGEListDatosSesionAprendizajeImport;
    private SaveBEDatosEnvioGrupoImport saveBEDatosEnvioGrupoImport;
    private SaveGEDatosListRubroEvaluacionProcesoImport saveGEDatosListRubroEvaluacionProcesoImport;
    private SaveBEDatosListEnvioGrupoImport saveBEDatosListEnvioGrupoImport;
    private SaveBEDatosTareaRecursoImport saveBEDatosTareaRecursoImport;
    private SaveBEDatosEnvioTipoNotaImport saveBEDatosEnvioTipoNotaImport;
    private SaveBEDatosCalendarioPeriodoImport saveBEDatosCalendarioPeriodoImport;
    private SaveBEDatosLoginImport saveBEDatosLoginImport;
    private BEVariables beVariables;
    private TipoImportacion tipoImportacion;


    public ImportarActivityPresenterImpl(UseCaseHandler handler, Resources res, SaveGEDatosRubroEvaluacionProcesoImport saveGEDatosRubroEvaluacionProcesoImport,  SaveGEDatosSesionAprendizajeImport saveGEDatosSesionAprendizajeImport, SaveBEDatosEnvioGrupoImport saveBEDatosEnvioGrupoImport, SaveGEListDatosSesionAprendizajeImport saveGEListDatosSesionAprendizajeImport,SaveGEDatosListRubroEvaluacionProcesoImport saveGEDatosListRubroEvaluacionProcesoImport, SaveBEDatosListEnvioGrupoImport saveBEDatosListEnvioGrupoImport,SaveBEDatosTareaRecursoImport saveBEDatosTareaRecursoImport, SaveBEDatosEnvioTipoNotaImport saveBEDatosEnvioTipoNotaImport,
                                         SaveBEDatosCalendarioPeriodoImport saveBEDatosCalendarioPeriodoImport, SaveBEDatosLoginImport saveBEDatosLoginImport) {
        super(handler, res);
        this.saveGEDatosRubroEvaluacionProcesoImport = saveGEDatosRubroEvaluacionProcesoImport;
        this.saveGEDatosSesionAprendizajeImport = saveGEDatosSesionAprendizajeImport;
        this.saveBEDatosEnvioGrupoImport =saveBEDatosEnvioGrupoImport;
        this.saveGEListDatosSesionAprendizajeImport = saveGEListDatosSesionAprendizajeImport;
        this.saveGEDatosListRubroEvaluacionProcesoImport = saveGEDatosListRubroEvaluacionProcesoImport;
        this.saveBEDatosListEnvioGrupoImport = saveBEDatosListEnvioGrupoImport;
        this.saveBEDatosTareaRecursoImport = saveBEDatosTareaRecursoImport;
        this.saveBEDatosEnvioTipoNotaImport = saveBEDatosEnvioTipoNotaImport;
        this.saveBEDatosCalendarioPeriodoImport = saveBEDatosCalendarioPeriodoImport;
        this.saveBEDatosLoginImport = saveBEDatosLoginImport;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final int maxSecondsInMillis = 360000;
        final int countInterval = 1000;
        if(view!=null)view.onStartProgress(maxSecondsInMillis,countInterval);
        if(tipoImportacion == null)return;
        switch (tipoImportacion){
            default:
                handleImportantError("No existe Importacion con el tipo "+ tipoImportacion.toString());
                break;
            case RUBROEVALUACION:
                SaveGEDatosRubroEvaluacionProcesoImport();
                break;
            case SESIONES:
                SaveGEDatosSesionAprendizajeImport();
                break;
            case GRUPO:
                SaveGEDatosEnvioGrupoImport();
                break;
            case UNIDAD:
                SaveGEDatosUnidadAprendizajeImport();
                break;
            case RUBROEVALUACION_CALENDARIO:
                SaveGEDatosRubroEvaluacionProcesoCalendarioImport();
                break;
            case GRUPOS:
                SaveGEDatosListEnvioGrupoImport();
                break;
            case TAREA_UNIDAD:
                //SaveGEDatosListEnvioGrupoImport();
                break;
            case TAREA:
                SaveBEDatosTareaRecursoImport();
                break;
            case TIPONOTA:
                SaveBEDatosTipoNotaImport();
                break;
            case CALENDARIO_PERIODO:
                SaveBECalendarioPeriodo();
                break;
            case CONTACTOS:
                SaveBEDatosLogin();
                break;
        }
    }

    private void SaveBEDatosLogin() {
        handler.execute(saveBEDatosLoginImport,
                new SaveBEDatosLoginImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosLoginImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosLoginImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveBECalendarioPeriodo() {
        handler.execute(saveBEDatosCalendarioPeriodoImport,
                new SaveBEDatosCalendarioPeriodoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosCalendarioPeriodoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosCalendarioPeriodoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveBEDatosTipoNotaImport() {
        handler.execute(saveBEDatosEnvioTipoNotaImport,
                new SaveBEDatosEnvioTipoNotaImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosEnvioTipoNotaImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosEnvioTipoNotaImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveBEDatosTareaRecursoImport() {
        handler.execute(saveBEDatosTareaRecursoImport,
                new SaveBEDatosTareaRecursoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosTareaRecursoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosTareaRecursoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveGEDatosListEnvioGrupoImport() {
        handler.execute(saveBEDatosListEnvioGrupoImport,
                new SaveBEDatosListEnvioGrupoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosListEnvioGrupoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosListEnvioGrupoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveGEDatosRubroEvaluacionProcesoCalendarioImport() {
        handler.execute(saveGEDatosListRubroEvaluacionProcesoImport,
                new SaveGEDatosListRubroEvaluacionProcesoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveGEDatosListRubroEvaluacionProcesoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGEDatosListRubroEvaluacionProcesoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveGEDatosUnidadAprendizajeImport() {
        handler.execute(saveGEListDatosSesionAprendizajeImport,
                new SaveGEListDatosSesionAprendizajeImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveGEListDatosSesionAprendizajeImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGEListDatosSesionAprendizajeImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });

    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        try {
            beVariables = (BEVariables) extras.getSerializable(ImportarActivity.BEVARIABLES);
            tipoImportacion = TipoImportacion.valueOf(extras.getString(ImportarActivity.TIPOIMPORTACION));
        }catch (Exception e){
            beVariables = new BEVariables();
            tipoImportacion = null;
        }

        Log.d(getTag(),beVariables.toString());
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return ImportarActivityPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onImportarCountDownTimerFinish() {
        showFinalMessage(res.getString(R.string.unknown_error));
    }

    private void handleImportantError(String message) {
        showFinalMessage(message);
        detenerProgress();
    }

    private void detenerProgress(){
        if(view != null) view.onEndProgress();
    }

    private final String[] progressMessages = res.getStringArray(R.array.activity_login_progress_messages_array);
    private final String[] progressMessagesSlowConnection = res.getStringArray(R.array.activity_login_progress_slow_connection_messages_array);
    private final int prgsMssgsSize = progressMessages.length;
    private final int slowConnectionSize = progressMessagesSlowConnection.length;

    @Override
    public void onImportarCountDownTimerCount(int count) {

        if (count % 2 == 0) {//CADA 2 SEGUNDOS, ACTUALIZAR LA VISTA.
            int position = count / 2;
            String message = "";
            if (position < prgsMssgsSize) {
                message = progressMessages[position];
            } else {
                int randomNum  = (int) ((Math.random() * slowConnectionSize) + 1);
               // int randomNum = ThreadLocalRandom.current().nextInt(0, slowConnectionSize);
                message = progressMessagesSlowConnection[randomNum];
            }

            updateProgressText(message);
        }
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void postImagenSucces() {
       if(view!=null)view.hideActivity();
    }

    @Override
    public void postImagenError() {
        handleImportantError(res.getString(R.string.unknown_error));
    }

    private void updateProgressText(String message) {
        if (view != null) view.updateProgressText(message);
    }

    private void SaveGEDatosRubroEvaluacionProcesoImport(){
        handler.execute(saveGEDatosRubroEvaluacionProcesoImport,
                new SaveGEDatosRubroEvaluacionProcesoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveGEDatosRubroEvaluacionProcesoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGEDatosRubroEvaluacionProcesoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveGEDatosSesionAprendizajeImport(){
        handler.execute(saveGEDatosSesionAprendizajeImport,
                new SaveGEDatosSesionAprendizajeImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveGEDatosSesionAprendizajeImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGEDatosSesionAprendizajeImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void SaveGEDatosEnvioGrupoImport(){
        handler.execute(saveBEDatosEnvioGrupoImport,
                new SaveBEDatosEnvioGrupoImport.RequestValues(beVariables),
                new UseCase.UseCaseCallback<SaveBEDatosEnvioGrupoImport.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveBEDatosEnvioGrupoImport.ResponseValue response) {
                        mostarImagenSucces();
                    }

                    @Override
                    public void onError() {
                        mostarImagenError();
                    }
                });
    }

    private void mostarImagenSucces(){
       if(view!=null)view.showImagenSucces();
    }

    private void mostarImagenError(){
        if(view!=null)view.showImagenError();
    }



}
