package com.consultoraestrategia.ss_crmeducativo.login2.service2;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.Firebase.GetListaCambiosFB;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeridoList;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetPlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.SavePlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListActualizar;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListServicioEnvio;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.SaveDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CasosEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.GrupoEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ResultadoEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.RubroEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.SesionesEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.TareaEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.List;

public class ServicesPresenterImpl extends BasePresenterImpl<ServicesView> implements ServicesPresenter {

    private boolean notificacion = false;
    private boolean programarHorarioEnvio = false;
    private GetListActualizar getListServicioActualizar;
    private GetListaCambiosFB getListaCambiosFB;
    private GetListServicioEnvio getListServicioEnvio;
    private GetCalendarioPeriodo getCalendarioPeriodo;
    private GetDatosServidor getDatos;
    private int cargaCursoId;
    private int calendarioPeriodoId;
    private List<ServiceEnvioUi> serviceEnvioUiList = new ArrayList<>();
    private List<ServiceEnvioUi> serviceEnvioUiFBList = new ArrayList<>();
    private List<ActualizarUi> actualizarUiList = new ArrayList<>();
    private static final String TAG = ServicesPresenterImpl.class.getSimpleName();
    private int usuarioId;
    private int empleadoId;
    private int programaEducativoId;
    private ActualizarUi actualizarUiSelected = null;
    private RetrofitCancel retrofitCancel;
    private int georeferenciaId;
    private int entidadId;
    private boolean changeDataBase = false;
    private int silaboEventoId;
    private int cursoId;
    private int cargaAcademicaId;
    private boolean cursoComplejo;
    private CalendarioPeriodoUi calendarioPeriodoUi;
    private int anioAcademicoId;
    private SaveDatosServidor saveDatosServidor;
    private SavePlanificarSinck savePlanificarSinck;
    private GetPlanificarSinck getPlanificarSinck;
    private ServiceEnvioUi serviceEnviarUiSelected = null;
    private int horaTimePicker;
    private int minutoTimePicker;
    private GetCalendarioPeridoList getCalendarioPeridoList;
    private CalendarioPeriodoUi calendarioPeriodoUiVerificationSelect;
    private boolean isServices;
    private boolean showRestaurar;
    private boolean starLoadFirebse;

    public ServicesPresenterImpl(UseCaseHandler handler, Resources res,
                                 GetListActualizar getListServicioActualizar,
                                 GetListServicioEnvio getListServicioEnvio, GetCalendarioPeriodo getCalendarioPeriodo, GetDatosServidor getDatos, SaveDatosServidor saveDatosServidor,
                                 SavePlanificarSinck savePlanificarSinck, GetPlanificarSinck getPlanificarSinck, GetCalendarioPeridoList getCalendarioPeridoList, GetListaCambiosFB getListaCambiosFB) {
        super(handler, res);
        this.getListServicioActualizar = getListServicioActualizar;
        this.getListServicioEnvio = getListServicioEnvio;
        this.getCalendarioPeriodo = getCalendarioPeriodo;
        this.getDatos = getDatos;
        this.saveDatosServidor = saveDatosServidor;
        this.savePlanificarSinck = savePlanificarSinck;
        this.getPlanificarSinck = getPlanificarSinck;
        this.getCalendarioPeridoList = getCalendarioPeridoList;
        this.getListaCambiosFB = getListaCambiosFB;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(calendarioPeriodoId!=0){
            getCalendarioPeriodo();
            if(view!=null)view.showNombreCalendario(calendarioPeriodoUi.getNombre());
            getListServicioActualizar();

            if(showRestaurar){
                showServicioActualizar();
                if(view!=null)view.setListServicioActualizar(actualizarUiList);
            }else {
                hideServicioActualizar();
                if(view!=null)view.setListServicioActualizar(new ArrayList<>());
            }

            hideListAnioCalendario();
            /*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    starActualizacion();
                }
            }, 2000);*/
        }else {
            if(view!=null)view.hideNombreCalendario();
            hideServicioActualizar();
            List<CalendarioPeriodoUi> calendarioPeriodoUiList = getCalendarioPeridoList.execute(anioAcademicoId, programaEducativoId);
            showListAnioCalendario(calendarioPeriodoUiList);
        }
        showListaCambiosFB();
        getListServicioEnvio();
        showServicioEnvio();
        setDescripcionNotificacion();
        setDescripcionProgramarHorarioEnvio();

    }

    private void showListaCambiosFB() {
        getListaCambiosFB.execute(usuarioId, false, new GetListaCambiosFB.Callback() {
            @Override
            public void onLoad(boolean success, List<ServiceEnvioFbUi> serviceEnvioUiList) {
                if(serviceEnvioUiList.isEmpty()){
                    if(view!=null)view.hideContentStarFirebase();
                    if(view!=null)view.stopGirarImagePA();
                }else {
                    if(view!=null)view.changeDescripcionStarFirebase("Existe cambios", "Buscando cambios en sus evaluaciones");
                    if(view!=null)view.showContentStarFirebase();
                    if(view!=null)view.stopGirarImagePA();
                }
            }
        });
    }

    private void showListAnioCalendario(List<CalendarioPeriodoUi> calendarioPeriodoUiList){
        if(view!=null)view.showListAnioCalendario(calendarioPeriodoUiList);
    }

    private void hideListAnioCalendario(){
        if(view!=null)view.hideListAnioCalendario();
    }

    private void hideServicioActualizar() {
        if(view!=null)view.hideServicioActualizar();
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
                            if(view!=null)view.showItemDownloadProgress(value.getActualizarUi());
                        }else if(value instanceof GetDatosServidor.ResponseUploadProgress){
                            if(view!=null)view.showItemUpaloadProgress(value.getActualizarUi());
                        }else {
                            if(success){
                                if(view!=null)view.notifyChangeDataBase();
                                value.getActualizarUi().setSuccess(1);
                            }else {
                                value.getActualizarUi().setSuccess(-1);
                            }
                            value.getActualizarUi().setActivo(false);
                            value.getActualizarUi().setEncoloa(false);
                            if(view!=null)view.updateListaActualizar(value.getActualizarUi());
                            starActualizacion();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        value.getActualizarUi().setActivo(false);
                        value.getActualizarUi().setEncoloa(false);
                        if(view!=null)view.updateListaActualizar(value.getActualizarUi());
                        starActualizacion();
                    }


                }
            });

        }
    }

    //Si el servicio es iniciado por un Worker es nesario que que el envio se asyncron y no syncrono
    private void starEnviarCambios(boolean modoSyncrono) {
       serviceEnviarUiSelected = null;
        for (ServiceEnvioUi item: serviceEnvioUiList){
            if(item.isEncoloa()){
                serviceEnviarUiSelected = item;
                break;
            }
        }

        if(serviceEnviarUiSelected!=null){
            Log.d(TAG, serviceEnviarUiSelected.getNombre() + " " + serviceEnviarUiSelected.getDescripcion());
            Log.d(TAG, " modoSyncrono: " + modoSyncrono);
            Log.d(TAG, " programaEducativoId: " + programaEducativoId);
            if(serviceEnviarUiSelected instanceof RubroEnviarUi)Log.d(TAG, " Lista: " + ((RubroEnviarUi)serviceEnviarUiSelected).getRubroEvaluacionIdList());
            serviceEnviarUiSelected.setActivo(true);
            serviceEnviarUiSelected.setSyncrono(modoSyncrono);
            serviceEnviarUiSelected.setProgramaEducativoId(programaEducativoId);
            retrofitCancel = saveDatosServidor.execute(serviceEnviarUiSelected, new UseCaseLoginSincrono.Callback<SaveDatosServidor.Response>() {
                @Override
                public void onResponse(boolean success, SaveDatosServidor.Response value) {
                    try {
                        if(value instanceof SaveDatosServidor.ResponseDownloadProgress){
                            if(view!=null)view.updateListaEnviar(value.getServiceEnvioUi());
                        }else if(value instanceof SaveDatosServidor.ResponseUploadProgress){
                            if(view!=null)view.updateListaEnviar(value.getServiceEnvioUi());
                        }else {
                            if(success){
                                //if(view!=null)view.notifyChangeDataBase();
                                value.getServiceEnvioUi().setSuccess(1);
                                if(view!=null)view.removeListaEnviar(value.getServiceEnvioUi());
                            }else {
                                value.getServiceEnvioUi().setSuccess(-1);
                                if(view!=null)view.updateListaEnviar(value.getServiceEnvioUi());
                            }
                            value.getServiceEnvioUi().setActivo(false);
                            value.getServiceEnvioUi().setEncoloa(false);

                            starEnviarCambios(isServices);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        value.getServiceEnvioUi().setActivo(false);
                        value.getServiceEnvioUi().setEncoloa(false);
                        if(view!=null)view.updateListaEnviar(value.getServiceEnvioUi());
                        starEnviarCambios(isServices);
                    }


                }
            });

        }else {

            List<ServiceEnvioUi> serviceEnvioUiList = new ArrayList<>();
            for (ServiceEnvioUi serviceEnvioUi : this.serviceEnvioUiList){
                if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1){
                    serviceEnvioUiList.add(serviceEnvioUi);
                }
            }
            this.serviceEnvioUiList.clear();
            this.serviceEnvioUiList.addAll(serviceEnvioUiList);

            showServicioEnvio();
            if(view!=null)view.stopGirarBtnRevisionDatos();
        }
    }

    private void getCalendarioPeriodo() {
        calendarioPeriodoUi = getCalendarioPeriodo.execute(calendarioPeriodoId);

    }

    private void showServicioEnvio() {

        if(serviceEnvioUiList.isEmpty()){
            if(view!=null)view.setTitleMantenimiento("No se encontraron problemas");
            if(view!=null)view.hideBtnMantenimiento();
            if(view!=null)view.hideListServicioEnvio();
        }else {
            if(serviceEnvioUiList.size() == 1){
                if(view!=null)view.setTitleMantenimiento("Se encontró un problema");
            }else {
                if(view!=null)view.setTitleMantenimiento("Se encontraron " +(serviceEnvioUiList.size())+ " problemas");
            }

            if(view!=null)view.showBtnMantenimiento();
            if(view!=null)view.showListServicioEnvio(this.serviceEnvioUiList);
        }

    }


    private void showServicioActualizar() {
        if(view!=null)view.showListServicioActualizar();
    }

    private void getListServicioEnvio() {
        serviceEnvioUiList.clear();
        serviceEnvioUiList .addAll(getListServicioEnvio.execute(0, 0, 0, 0,0));
        //serviceEnvioUiList .addAll(getListServicioEnvio.execute(anioAcademicoId, cargaCursoId, calendarioPeriodoId, silaboEventoId,programaEducativoId));
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
        this.anioAcademicoId = crmBundle.getAnioAcademico();
        this.isServices = extras.getBoolean("IsServices", false);
    }

    private void getListServicioActualizar() {
        actualizarUiList.clear();
        actualizarUiList.addAll(getListServicioActualizar.getServicioEnvio(usuarioId,empleadoId,programaEducativoId,calendarioPeriodoId, cargaCursoId, georeferenciaId,entidadId,silaboEventoId,cursoId, cargaAcademicaId, cursoComplejo));
    }

    private void setDescripcionProgramarHorarioEnvio() {
        AlarmaUi alarmaUi = getPlanificarSinck.execute();
        programarHorarioEnvio = alarmaUi!=null;
        if(programarHorarioEnvio){
            this.horaTimePicker = alarmaUi.getHora();
            this.minutoTimePicker = alarmaUi.getMinute();
            if(view!=null)view.changeSelectedProgramaHorario(true);
            if(view!=null)view.setDescripcionProgramarHorarioEnvio("Todo los días a las "+getHoraAmPm(alarmaUi.getHora()) + getMinuto(alarmaUi.getMinute()) + " " + getAmPm(alarmaUi.getHora()));
        }else {
            if(view!=null)view.changeSelectedProgramaHorario(false);
            if(view!=null)view.setDescripcionProgramarHorarioEnvio(res.getString(R.string.txt_prog_horario_envio_desactivado));
        }
    }

    private int getHoraAmPm(int hora){
        if(hora==0)return 12;
        else if(hora>12)return hora - 12;
        else return hora;
    }

    private String getAmPm(int hora) {
        if (hora < 12) return "AM";
        else return "PM";
    }

    private String getMinuto(int minuto) {
        int cantidad = 0;
        int iTemp = minuto;
        while (iTemp > 0) {
            iTemp = iTemp / 10;
            cantidad++;
        }
        if (cantidad > 1) return ":" + minuto;
        else return ":0" + minuto;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void changeSwitchNotifiacion(boolean isChecked) {
        notificacion = isChecked;
        setDescripcionNotificacion();
    }

    private void setDescripcionNotificacion() {
        if(notificacion){
            if(view!=null)view.setDescripcionNotificacion(res.getString(R.string.txt_notificacion_activado));
        }else {
            if(view!=null)view.setDescripcionNotificacion(res.getString(R.string.txt_notificacion_desactivado));
        }
    }

    @Override
    public void changeSwitchProgramaEnvio(boolean isChecked) {
        programarHorarioEnvio = isChecked;
        if(programarHorarioEnvio){
            AlarmaUi alarmaUi = getPlanificarSinck.execute();
            if(alarmaUi==null&&horaTimePicker!=-1&&minutoTimePicker!=-1){
                alarmaUi = new AlarmaUi();
                alarmaUi.setHora(horaTimePicker);
                alarmaUi.setMinute(minutoTimePicker);
            }
            if(view!=null)view.showProgramaHorario(alarmaUi);
        }else {
            savePlanificarSinck.execute(-1,-1);
            setDescripcionProgramarHorarioEnvio();
        }

    }

    @Override
    public void onClickRevisionDatos() {

        if(revisionDatos()){
            if(view!=null)view.showStopMensajeRevision();
        }else {
            if(serviceEnvioUiList.isEmpty()){
               if(view!=null)view.showMessage("No existen cambios que enviar");
            }else {
                //if(view!=null)view.showStartMensajeRevision();
                onSelectedStartRevisionDatos();
            }
        }
    }

    private boolean revisionDatos() {
        boolean result = false;
        for(ServiceEnvioUi serviceEnvioUi : serviceEnvioUiList)
            if(serviceEnvioUi.getSuccess()==0&&serviceEnvioUi.isActivo()){
                result = true;
                break;
            }
        return result;
    }

    @Override
    public void onSelectedStartRevisionDatos() {
        for (ActualizarUi actualizarUi:  actualizarUiList)actualizarUi.setEncoloa(false);
        for (ServiceEnvioUi serviceEnvioUi: serviceEnvioUiList)serviceEnvioUi.setEncoloa(true);

        starEnviarCambios(isServices);
        //for (ActualizarUi actualizarUi: actualizarUiList)actualizarUi.setActivate(revisionDatos);
        //if(view!=null)view.showListServicioActualizar(actualizarUiList);

    }

    @Override
    public void onSelectedStoptRevisionDatos() {
        for (ServiceEnvioUi serviceEnvioUi: serviceEnvioUiList)serviceEnvioUi.setEncoloa(false);
        if(retrofitCancel!=null)retrofitCancel.cancel();

        if(view!=null)view.stopGirarBtnRevisionDatos();

        showServicioEnvio();

    }

    @Override
    public void onClickActualizarItem(ActualizarUi actualizarUi) {

        if(serviceEnviarActivo()){
            if(view!=null)view.showMessage("Acción denegada, cancelar los envios pendientes");
        }else if(serviceEnviarPendiente(actualizarUi)){
            this.actualizarUiSelected = actualizarUi;
            this.calendarioPeriodoUiVerificationSelect = null;
            if(view!=null)view.showMessageRevision();
        }else {

            if(actualizarUi.isActivo()){
                if(retrofitCancel!=null){
                    retrofitCancel.cancel();
                    actualizarUi.setUploadProgress(0);
                    actualizarUi.setDowloadProgress(0);
                }
            }

            if(!actualizarUi.isEncoloa()){
                boolean activo = false;
                for (ActualizarUi actualizarActivo: actualizarUiList){
                    if(actualizarActivo.isActivo())activo = true;
                }

                if(!activo){
                    actualizarUi.setEncoloa(true);
                    starActualizacion();
                }else {
                    actualizarUi.setEncoloa(true);
                }

            }else {
                actualizarUi.setEncoloa(false);
            }

            if(view!=null)view.updateListaActualizar(actualizarUi);
        }
    }

    private boolean serviceEnviarPendiente(ActualizarUi actualizarUi) {
        boolean status = false;
        for (ServiceEnvioUi serviceEnvioUi : serviceEnvioUiList){
            switch (actualizarUi.getTipo()){
                case Unidades:
                    if(serviceEnvioUi instanceof SesionesEnviarUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
                case Grupos:
                    if(serviceEnvioUi instanceof GrupoEnviarUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
                case Tareas:
                    if(serviceEnvioUi instanceof TareaEnviarUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
                case Rubros:
                    if(serviceEnvioUi instanceof RubroEnviarUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
                case Resultado:
                    if(serviceEnvioUi instanceof ResultadoEnvioUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
                case Casos:
                    if(serviceEnvioUi instanceof CasosEnviarUi)
                        if(serviceEnvioUi.getSuccess()==0||serviceEnvioUi.getSuccess()==-1)status=true;
                    break;
            }
        }
        return status;
    }

    @Override
    public void onClickEnviarItem(ServiceEnvioUi serviceEnvioUi) {

        if(serviceActualizarActivo()){
                if(view!=null)view.showMessage("Acción denegada, cancelar las actualizaciones pendientes");
        }else {
            if(serviceEnvioUi.isActivo()){
                if(retrofitCancel!=null){
                    retrofitCancel.cancel();
                    serviceEnvioUi.setUploadProgress(0);
                    serviceEnvioUi.setDowloadProgress(0);
                }
            }

            if(!serviceEnvioUi.isEncoloa()){
                boolean activo = false;
                for (ActualizarUi actualizarActivo: actualizarUiList){
                    if(actualizarActivo.isActivo())activo = true;
                }

                if(!activo){
                    serviceEnvioUi.setEncoloa(true);
                    starEnviarCambios(isServices);
                }else {
                    serviceEnvioUi.setEncoloa(true);
                }

            }else {
                serviceEnvioUi.setEncoloa(false);
            }

            if(view!=null)view.updateListaEnviar(serviceEnvioUi);

        }


    }

    @Override
    public void onSelectedActualizarDatos() {
        if(serviceEnviarActivo()){
            if(view!=null)view.showMessage("Acción denegada, cancelar los envios de datos pendientes");
        }else if(actualizarUiSelected!=null){
            if(actualizarUiSelected.isActivo()){
                if(retrofitCancel!=null){
                    retrofitCancel.cancel();
                    actualizarUiSelected.setUploadProgress(0);
                    actualizarUiSelected.setDowloadProgress(0);
                }
            }

            if(!actualizarUiSelected.isEncoloa()){
                boolean activo = false;
                for (ActualizarUi actualizarActivo: actualizarUiList){
                    if(actualizarActivo.isActivo())activo = true;
                }

                if(!activo){
                    actualizarUiSelected.setEncoloa(true);
                    starActualizacion();
                }else {
                    actualizarUiSelected.setEncoloa(true);
                }

            }else {
                actualizarUiSelected.setEncoloa(false);
            }

            if(view!=null)view.updateListaActualizar(actualizarUiSelected);

        }else if(calendarioPeriodoUiVerificationSelect !=null){
            if(view!=null)view.showFastData(usuarioId, anioAcademicoId,calendarioPeriodoUiVerificationSelect.getCalendarioId(), programaEducativoId);
        }else{
            if(view!=null)view.showMessage("Acción denegada");
        }
    }

    @Override
    public void onSelectedHoraMinuteTimePicker(int hour, int minute) {
        this.horaTimePicker = hour;
        this.minutoTimePicker = minute;
    }

    @Override
    public void onSelectedAceptarTimePicker() {
        savePlanificarSinck.execute(horaTimePicker, minutoTimePicker);
        setDescripcionProgramarHorarioEnvio();
    }

    @Override
    public void onSelectedCancelarTimePicker() {
        setDescripcionProgramarHorarioEnvio();
    }

    @Override
    public void onViewClickedCardProgEnvio() {
        AlarmaUi alarmaUi = getPlanificarSinck.execute();
        if(alarmaUi==null&&horaTimePicker!=-1&&minutoTimePicker!=-1){
            alarmaUi = new AlarmaUi();
            alarmaUi.setHora(horaTimePicker);
            alarmaUi.setMinute(minutoTimePicker);
        }
        if(view!=null)view.showProgramaHorario(alarmaUi);
    }

    @Override
    public void onClicAnioCalendario(CalendarioPeriodoUi calendarioPeriodoUi) {
        if(!serviceEnvioUiList.isEmpty()){
            if(view!=null)view.showMessageRevision();
            this.calendarioPeriodoUiVerificationSelect = calendarioPeriodoUi;
            this.actualizarUiSelected = null;
        }else {
            if(view!=null)view.showFastData(usuarioId, anioAcademicoId,calendarioPeriodoUi.getCalendarioId(), programaEducativoId);
        }
    }

    @Override
    public void onStartSynckService() {
        getListServicioEnvio();
        Log.d(TAG, "getListServicioEnvio size: " + serviceEnvioUiList.size() );
        for (ServiceEnvioUi serviceEnvioUi : serviceEnvioUiList)serviceEnvioUi.setEncoloa(true);
        starEnviarCambios(isServices);
    }

    @Override
    public void onClickContRestaurar() {
        showRestaurar = !showRestaurar;
        if(showRestaurar){
            showServicioActualizar();
            if(view!=null)view.setListServicioActualizar(actualizarUiList);
        }else {
            hideServicioActualizar();
            if(view!=null)view.setListServicioActualizar(new ArrayList<>());
        }
    }

    @Override
    public void onLoadFirebase(int count) {
        starLoadFirebse = true;
        if(count==-1){
            if(view!=null)view.changeDescripcionStarFirebase("Buscando", "Buscando cambios en sus evaluaciones");
            if(view!=null)view.girarImagePA();
        }else {
            if(view!=null)view.changeDescripcionStarFirebase("Actualización en curso", "Se a detectado cambios en sus evaluaciones");
            if(view!=null)view.girarImagePA();
        }
        if(view!=null)view.showContentStarFirebase();
        Log.d(getTag(),"onLoadFirebase " + count );
    }

    @Override
    public void onFinishFirebase() {
        starLoadFirebse = false;
        showListaCambiosFB();
    }

    @Override
    public void onClickedImgActualizar() {
        if(starLoadFirebse){
            if(view!=null)view.showMessage("Actualización en curso");
        }else {
            if(view!=null)view.starSynckFB();
        }
    }

    @Override
    public void onSelectedMensageStopSynckFB() {
        starLoadFirebse = false;
    }

    @Override
    public void onRefresh() {
        if(!starLoadFirebse){
            if(view!=null)view.starSynckFB();
        }else {
            if(view!=null)view.showMessage("Actualización en curso");
        }

        if(view!=null)view.closeRefresh();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        for(ServiceEnvioUi serviceEnvioUi: serviceEnvioUiList)serviceEnvioUi.setEncoloa(false);
        for(ActualizarUi actualizarUi :actualizarUiList)actualizarUi.setEncoloa(false);
        if(retrofitCancel!=null)retrofitCancel.cancel();
        super.onDestroy();
    }

    private boolean serviceActualizarActivo(){
        boolean status = false;
        for (ActualizarUi actualizarUi : actualizarUiList){
            if(actualizarUi.isEncoloa()||actualizarUi.isActivo()){
                status = true;
                break;
            }
        }

        return status;
    }

    private boolean serviceEnviarActivo(){
        boolean status = false;
        for (ServiceEnvioUi serviceEnvioUi : serviceEnvioUiList){
            if(serviceEnvioUi.isEncoloa()||serviceEnvioUi.isActivo()){
                status = true;
                break;
            }
        }
        return status;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(serviceActualizarActivo()) {
            if(view!=null)view.showFinalMessageAceptCancel("Existen actualizaciones pendientes, ¿Seguro que quiere salir?", res.getString(R.string.msg_confirmacionTitlle));
        }else if(serviceEnviarActivo()){
            if(view!=null)view.showFinalMessageAceptCancel("Existen envios de datos pendientes, ¿Seguro que quiere salir?", res.getString(R.string.msg_confirmacionTitlle));
        }else {
            if(view!=null)view.finishActivity();
        }
    }
}
