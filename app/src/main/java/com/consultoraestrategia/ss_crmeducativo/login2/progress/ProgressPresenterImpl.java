package com.consultoraestrategia.ss_crmeducativo.login2.progress;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListActualizar;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.List;

public class ProgressPresenterImpl extends BaseFragmentPresenterImpl<ProgressView> implements ProgressPrenter{
    private int usuarioId;
    private int empleadoId;
    private int programaEducativoId;
    private int cargaCursoId;
    private int calendarioPeriodoId;
    private int georeferenciaId;
    private int entidadId;
    private int silaboEventoId;
    private int cursoId;
    private int cargaAcademicaId;
    private boolean cursoComplejo;
    private GetListActualizar getListServicioActualizar;
    private GetDatosServidor getDatos;
    private List<ActualizarUi> actualizarUiList = new ArrayList();
    private ActualizarUi actualizarUiSelected;
    private RetrofitCancel retrofitCancel;
    private int porcentaje = 0;
    private int cantidad;

    public ProgressPresenterImpl(UseCaseHandler handler, Resources res, GetListActualizar getListServicioActualizar, GetDatosServidor getDatos) {
        super(handler, res);
        this.getListServicioActualizar = getListServicioActualizar;
        this.getDatos = getDatos;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void getListServicioActualizar() {
        actualizarUiList.clear();
        actualizarUiList.addAll(getListServicioActualizar.getServicioEnvio(usuarioId,empleadoId,programaEducativoId,calendarioPeriodoId, cargaCursoId, georeferenciaId,entidadId,silaboEventoId,cursoId, cargaAcademicaId, cursoComplejo));
    }

    private void starActualizacion() {
        actualizarUiSelected = null;
        for (ActualizarUi item: actualizarUiList){
            if(item.isEncoloa()){
                actualizarUiSelected = item;
                break;
            }
        }
        if(actualizarUiSelected!=null){
            actualizarUiSelected.setActivo(true);
            retrofitCancel = getDatos.execute(actualizarUiSelected, new UseCaseLoginSincrono.Callback<GetDatosServidor.Response>() {
                @Override
                public void onResponse(boolean success, GetDatosServidor.Response value) {
                    try {
                        if(value instanceof GetDatosServidor.ResponseDownloadProgress){
                            ActualizarUi actualizarUi = value.getActualizarUi();
                            porcentaje  = (int)(((double)actualizarUi.getDowloadProgress()/(double)cantidad) + (double) porcentaje);
                            if(view!=null)view.updatePorcebtaje(porcentaje);

                        }else if(value instanceof GetDatosServidor.ResponseUploadProgress){
                            //if(view!=null)view.updatePorcebtaje(value.getActualizarUi());

                        }else {
                            if(success){
                              //  if(view!=null)view.notifyChangeDataBase();
                                value.getActualizarUi().setSuccess(1);
                            }else {
                                value.getActualizarUi().setSuccess(-1);

                                for (ActualizarUi item: actualizarUiList){
                                    item.setActivo(false);
                                    item.setEncoloa(false);
                                }
                            }

                            value.getActualizarUi().setActivo(false);
                            value.getActualizarUi().setEncoloa(false);
                            //if(view!=null)view.updateListaActualizar(value.getActualizarUi());
                            starActualizacion();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        value.getActualizarUi().setSuccess(-1);
                        for (ActualizarUi item: actualizarUiList){
                            item.setActivo(false);
                            item.setEncoloa(false);
                        }
                        //if(view!=null)view.updateListaActualizar(value.getActualizarUi());
                        starActualizacion();
                    }


                }
            });

        }else {
            boolean error = false;
            for (ActualizarUi item: actualizarUiList){
                if(item.getSuccess()==-1){
                    error = true;
                    break;
                }
            }
            if(error){
                if (view!=null)view.showDialogError();
            }else {
                if (view!=null)view.finshedDialog();
            }

        }
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.usuarioId =  crmBundle.getUsuarioId();
        this.empleadoId =  crmBundle.getEmpleadoId();
        this.programaEducativoId =  crmBundle.getProgramaEducativoId();
        this.cargaCursoId = crmBundle.getCargaCursoId();
        this.calendarioPeriodoId = crmBundle.getCalendarioPeriodoId();
        this.georeferenciaId = crmBundle.getGeoreferenciaId();
        this.entidadId = crmBundle.getEntidadId();
        this.silaboEventoId = crmBundle.getSilaboEventoId();
        this.cursoId = crmBundle.getCursoId();
        this.cargaAcademicaId = crmBundle.getCargaAcademicaId();
        this.cursoComplejo = crmBundle.isComplejo();

        getListServicioActualizar();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                porcentaje = 0;
                cantidad = 0;
                for (ActualizarUi item: actualizarUiList){
                        if(item.isEncoloa())cantidad++;
                }

                if(view!=null)view.updatePorcebtaje(1);
                starActualizacion();
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (ActualizarUi item: actualizarUiList){
            item.setActivo(false);
            item.setEncoloa(false);
        }
        if(retrofitCancel!=null)retrofitCancel.cancel();
    }
}
