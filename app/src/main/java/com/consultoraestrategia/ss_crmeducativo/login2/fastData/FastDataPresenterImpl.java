package com.consultoraestrategia.ss_crmeducativo.login2.fastData;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetDatosPreFastData;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidorTwo;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.List;

public class FastDataPresenterImpl extends BasePresenterImpl<FastDataView> implements FastDataPresenter {

    private GetDatosPreFastData getDatosPreFastData;
    private int anioAcademicoId;
    private int usuarioId;
    private List<ProgramaEducativoUi> programaEducativoUiList = new ArrayList<>();
    private List<ActualizarTipoUi> tipoList = new ArrayList<>();
    private GetDatosServidorTwo getDatosServidor;
    private RetrofitCancel retrofitCancel;
    private boolean messageError;
    private int calendarioPerioId;
    private int programaEducativoId;

    public FastDataPresenterImpl(UseCaseHandler handler, Resources res, GetDatosPreFastData getDatosPreFastData, GetDatosServidorTwo getDatosServidor) {
        super(handler, res);
        this.getDatosPreFastData = getDatosPreFastData;
        this.getDatosServidor = getDatosServidor;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        anioAcademicoId = crmBundle.getAnioAcademico();
        usuarioId = crmBundle.getUsuarioId();
        calendarioPerioId = crmBundle.getCalendarioPeriodoId();
        programaEducativoId = crmBundle.getProgramaEducativoId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messageError = false;
        getInformacionPreFastData();
    }

    private void getInformacionPreFastData() {
        getTipos();
        this.programaEducativoUiList.clear();
        GetDatosPreFastData.Response response = getDatosPreFastData.execute(new GetDatosPreFastData.Request(usuarioId, anioAcademicoId, calendarioPerioId,programaEducativoId));
        this.programaEducativoUiList.addAll(response.getCursosUiList()!=null? response.getCursosUiList():  new ArrayList<ProgramaEducativoUi>());

        Log.d(getTag(), "response: " + this.programaEducativoUiList.size());
        starActualizacion();
    }

    private void getTipos() {
        this.tipoList.clear();
        this.tipoList.add(ActualizarTipoUi.Dimencion_Desarrollo);
        this.tipoList.add(ActualizarTipoUi.Casos);
        this.tipoList.add(ActualizarTipoUi.Tareas);
        this.tipoList.add(ActualizarTipoUi.Grupos);
        //this.tipoList.add(ActualizarTipoUi.Resultado);
        this.tipoList.add(ActualizarTipoUi.Rubros);
        this.tipoList.add(ActualizarTipoUi.Estudiantes);
        this.tipoList.add(ActualizarTipoUi.TipoNota);
        this.tipoList.add(ActualizarTipoUi.Unidades);
    }



    private void starActualizacion() {
        Log.d(getTag(), "starActualizacion");
        ProgramaEducativoUi programaEducativoUiSelected = null;
        for (ProgramaEducativoUi item: programaEducativoUiList){
            if(item.getEncola()){
                programaEducativoUiSelected = item;
                break;
            }
        }

        if(programaEducativoUiSelected !=null){
            programaEducativoUiSelected.setEncola(false);
            programaEducativoUiSelected.setStartTipo(this.tipoList.size());
            if(view!=null)view.clearListaActualizar();
            if(view!=null)view.updateListaActualizar(programaEducativoUiSelected);
            ProgramaEducativoUi bimestre = new ProgramaEducativoUi();
            bimestre.setNombre(programaEducativoUiSelected.getBimestre());
            if(view!=null)view.updateListaActualizar(bimestre);


            starActualizacionTipo(programaEducativoUiSelected);
        }else {
            if(messageError) {
                if(view!=null)view.finshedDialogWithError();
            }else {
                if(view!=null)view.changeDataBase();
                if(view!=null)view.cerrarActividad();
            }
            // getCursosUIListCallback();
        }

    }

    private void starActualizacionTipo(final ProgramaEducativoUi programaEducativoUi ){
        programaEducativoUi.setStartTipo(programaEducativoUi.getStartTipo()-1);

        if(view!=null&programaEducativoUi.getStartTipo()>-1){
            ActualizarTipoUi tipo =  this.tipoList.get(programaEducativoUi.getStartTipo());
            ProgramaEducativoUi tipoProgramaEducativoUi = programaEducativoUi.clonar();
            tipoProgramaEducativoUi.setNombre(tipo.getNombre()+": ");
            retrofitCancel = getDatosServidor.execute(new GetDatosServidorTwo.Request(tipoProgramaEducativoUi, tipo), new UseCaseLoginSincrono.Callback<GetDatosServidorTwo.Response>() {
                @Override
                public void onResponse(boolean success, GetDatosServidorTwo.Response value) {
                    try {
                        if(value instanceof GetDatosServidorTwo.ResponseDownloadProgress){
                            if(view!=null)view.updateListaActualizar(value.getProgramaEducativoUi());
                        }else if(value instanceof GetDatosServidorTwo.ResponseUploadProgress){
                            if(view!=null)view.updateListaActualizar(value.getProgramaEducativoUi());
                        }else {
                            if(success){
                                value.getProgramaEducativoUi().setSuccess(1);
                            }else {
                                value.getProgramaEducativoUi().setSuccess(-1);
                                messageError = true;
                            }
                            if(view!=null)view.updateListaActualizar(value.getProgramaEducativoUi());
                            starActualizacionTipo(programaEducativoUi);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        if(view!=null)view.updateListaActualizar(value.getProgramaEducativoUi());
                        starActualizacionTipo(programaEducativoUi);
                    }
                }
            });
        }else {
            starActualizacion();
        }

    }

    @Override
    protected String getTag() {
        return "FastDataPresenterImpl";
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ProgramaEducativoUi programaEducativoUi: programaEducativoUiList){
            programaEducativoUi.setEncola(false);
            for (ActualizarUi actualizarUi: programaEducativoUi.getActualizarUiList()){
                actualizarUi.setEncoloa(false);
                actualizarUi.setActivo(false);
            }
        }

        if(retrofitCancel!=null)retrofitCancel.cancel();
    }

    @Override
    public void reintentarDescarga() {
        messageError = false;
        getInformacionPreFastData();
    }
}
