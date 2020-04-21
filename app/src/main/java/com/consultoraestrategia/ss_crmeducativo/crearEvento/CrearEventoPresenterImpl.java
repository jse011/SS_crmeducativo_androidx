package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetFile64;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoCalendario;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.SaveEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioView;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CrearEventoPresenterImpl extends BasePresenterImpl<CrearEventoView> implements CrearEventoPresenter{
    private static final int EVENTO=526, ACTIVIDAD=528, CITA=530, TAREA=529, NOTICIA=527;
    private GetEvento getEvento;
    private GetTipoCalendario getTipoCalendario;
    private GetTipoEvento getTipoEvento;
    private SaveEvento saveEvento;
    private GetFile64 getFile64;

    private String horaEntrega;
    private long fechaEntrega;
    private int usuarioId;
    private int georeferenciaId;
    private String enventoId;

    private List<TipoCalendarioUi> tipoCalendarioUiList = new ArrayList<>();
    private TipoCalendarioUi tipoCalendarioUiSelected;
    private List<TipoEventoUi> tipoEventoUiList = new ArrayList<>();
    private TipoEventoUi tipoEventoUiSelected;
    private int empleadoId;
    private int anioAcademicoId;
    private SeleccionarCalendarioView seleccionarCalendarioView;
    private int entidadId;
    private String imageBaseG4;
    private RetrofitCancel retrofitCancel;
    private String pathEdit;

    public CrearEventoPresenterImpl(UseCaseHandler handler, Resources res, GetEvento getEvento, GetTipoCalendario getTipoCalendario, GetTipoEvento getTipoEvento, SaveEvento saveEvento, GetFile64 getFile64) {
        super(handler, res);
        this.getEvento = getEvento;
        this.getTipoCalendario = getTipoCalendario;
        this.getTipoEvento = getTipoEvento;
        this.saveEvento = saveEvento;
        this.getFile64 = getFile64;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        usuarioId = crmBundle.getUsuarioId();
        georeferenciaId = crmBundle.getGeoreferenciaId();
        empleadoId = crmBundle.getEmpleadoId();
        anioAcademicoId = crmBundle.getAnioAcademico();
        enventoId = extras.getString("eventoId");
        entidadId = crmBundle.getEntidadId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!TextUtils.isEmpty(enventoId)){
            EventoUi eventoUi = getEvento.execute(enventoId);
            if(eventoUi!=null){

                tipoCalendarioUiSelected = new TipoCalendarioUi();
                tipoCalendarioUiSelected.setId(eventoUi.getCalendarioId());

                tipoEventoUiSelected = new TipoEventoUi();
                tipoEventoUiSelected.setId(eventoUi.getTipoEventoId());

                horaEntrega = eventoUi.getHora();
                fechaEntrega = eventoUi.getFecha();

                if(view!=null)view.setNombre(eventoUi.getNombre());
                if(view!=null)view.setDescripcion(eventoUi.getDescripcion());
                showFecha();
                showHora();

                pathEdit =  eventoUi.getPath();
                if(eventoUi.getTipoEventoId()==EVENTO||eventoUi.getTipoEventoId()==NOTICIA){
                    if(view!=null)view.showContenPreview();
                    if(view!=null)view.showImage(eventoUi.getPath());
                }else {
                    if(view!=null)view.hideContenPreview();
                    if(view!=null)view.hideImage();
                }

            }
        }

        getTipoCalendario();
        getTipoEvento();

        if(view!=null&&!view.isInternetAvailable()){
            if(view!=null)view.showOffline();
        }
    }

    private void getTipoEvento() {
        tipoEventoUiList.clear();
        tipoEventoUiList.addAll(getTipoEvento.execute());
        for (TipoEventoUi tipoEventoUi: tipoEventoUiList){
            if(tipoEventoUiSelected!=null&&tipoEventoUiSelected.getId()==tipoEventoUi.getId()){
                tipoEventoUi.setSelecionado(true);
                tipoEventoUiSelected = tipoEventoUi;
            }
        }

        if(tipoEventoUiSelected!=null){
            if(view!=null)view.setTipoEvento(tipoEventoUiSelected.getNombre());
        }else {
            if(view!=null)view.setTipoEvento("");
        }


    }

    private void getTipoCalendario() {
        tipoCalendarioUiList.clear();
        tipoCalendarioUiList.addAll(getTipoCalendario.excute(usuarioId, georeferenciaId, empleadoId, anioAcademicoId));
        for (TipoCalendarioUi tipoCalendarioUi: tipoCalendarioUiList){
            if(tipoCalendarioUiSelected!=null&& tipoCalendarioUi.getId().equals(tipoCalendarioUiSelected.getId())){
                tipoCalendarioUi.setSelecionado(true);
                tipoCalendarioUiSelected = tipoCalendarioUi;
            }
        }

        if(tipoCalendarioUiSelected!=null){
            if(view!=null)view.setTipoCalendario(tipoCalendarioUiSelected.getNombre());
        }else {
            if(view!=null)view.setTipoCalendario("");
        }
    }

    @Override
    public void onBtnCreateClicked(String nombre, String descripcion) {
        if(TextUtils.isEmpty(nombre)){
            if(view!=null)view.showMessage("Ingresé el nombre del evento");
            return;
        }

        if(TextUtils.isEmpty(descripcion)){
            if(view!=null)view.showMessage("Ingresé la descripción del evento");
            return;
        }

        if(tipoCalendarioUiSelected==null){
            if(view!=null)view.showMessage("Seleccioné un tipo calendario");
            return;
        }

        if(tipoEventoUiSelected==null){
            if(view!=null)view.showMessage("Seleccioné un tipo evento");
            return;
        }

        if(tipoEventoUiSelected.getId()==EVENTO||tipoEventoUiSelected.getId()==NOTICIA){
            if(TextUtils.isEmpty(pathEdit)){
                if(view!=null)view.showMessage("Seleccioné una imagen");
                return;
            }
        }


        if(view!=null&&view.isInternetAvailable()){
            if(view!=null)view.hideOffline();
            if(view!=null)view.viewsDisabled();
            if(view!=null)view.showDialogProgress();
            EventoUi eventoUi = new EventoUi();
            eventoUi.setId(enventoId);
            eventoUi.setNombre(nombre);
            eventoUi.setDescripcion(descripcion);
            eventoUi.setCalendarioId(tipoCalendarioUiSelected.getId());
            eventoUi.setTipoEventoId(tipoEventoUiSelected.getId());
            eventoUi.setFecha(fechaEntrega);
            eventoUi.setHora(horaEntrega);
            eventoUi.setEntidadId(entidadId);
            eventoUi.setGeoreferencia(georeferenciaId);
            eventoUi.setFoto(imageBaseG4);
            retrofitCancel = saveEvento.execute(eventoUi, new UseCaseLoginSincrono.Callback<Boolean>() {
                @Override
                public void onResponse(boolean success, Boolean value) {
                    if(success){
                        if(view!=null)view.finishActivity();
                    }else {
                        if(view!=null)view.showMessage("Server connection timeout");
                        if(view!=null)view.viewsEnabled();
                        if(view!=null)view.hideDialogProgress();
                    }
                }
            });
        }else {
            if(view!=null)view.showOffline();
            if(view!=null)view.showMessage("Sin conexión a internet");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(view!=null&&!view.isInternetAvailable()){
            if(view!=null)view.showOffline();
        }else {
            if(view!=null)view.hideOffline();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(retrofitCancel!=null)retrofitCancel.cancel();
    }

    @Override
    public void btnSelectDate() {
        if(view!=null)view.selectDate();
    }

    @Override
    public void btnSelectTime() {
        if(view!=null)view.selectTime();
    }

    @Override
    public void onClikSaveFecha(long timeInMillis) {
        this.fechaEntrega = timeInMillis;
        showFecha();
    }

    private void showFecha() {
        String fecha = (fechaEntrega==0? "": Utils.f_fecha_letras(fechaEntrega));
        if(fechaEntrega==0){
            if(view!=null)view.hideBtnCloseFecha();
        }else {
            if(view!=null)view.showBtnCloseFecha();
        }
        if(view!=null)view.setFecha(fecha);
    }

    @Override
    public void onChangeTime(int hourOfDay, int minute) {
        horaEntrega = hourOfDay + ":" + minute;
        Log.d(getTag(), "horaEntrega: " + horaEntrega);
        showHora();
    }

    @Override
    public void onClickTipoEvento() {
        int postion = -1;
        if(tipoEventoUiSelected!=null)postion = tipoEventoUiList.indexOf(tipoEventoUiSelected);
        if(view!=null)view.showListSingleChooser("Seleccionar tipo evento",new ArrayList<Object>(tipoEventoUiList) ,postion);
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        if(singleItem instanceof TipoEventoUi){
            int position = tipoEventoUiList.indexOf(singleItem);
            if(position!=-1){
                tipoEventoUiSelected = (TipoEventoUi)singleItem;


                if(tipoEventoUiSelected.getId()==NOTICIA||tipoEventoUiSelected.getId()==EVENTO){
                    if(view!=null)view.showContenPreview();
                }else {
                    if(view!=null)view.hideContenPreview();
                    pathEdit = null;
                    imageBaseG4 = null;
                    if(view!=null)view.hideImage();
                }


                if(view!=null)view.setTipoEvento(tipoEventoUiSelected.getNombre());
            }else {
                tipoEventoUiSelected = null;
                if(view!=null)view.setTipoEvento("");
                if(view!=null)view.hideContenPreview();
            }

        }

    }

    @Override
    public void onClickTipoCalendario() {
        if(view!=null)view.showDialogSearchCalendario();
    }

    @Override
    public void onItemClickTipoCalendario(TipoCalendarioUi tipoComportamientoUi) {
        tipoCalendarioUiSelected = tipoComportamientoUi;
        if(view!=null)view.setTipoCalendario(tipoCalendarioUiSelected.getNombre());

    }

    @Override
    public void onSeleccionarCalendarioViewDestroyed() {
        seleccionarCalendarioView = null;
    }

    @Override
    public void attachView(SeleccionarCalendarioView seleccionarCalendarioView) {
        this.seleccionarCalendarioView = seleccionarCalendarioView;
        seleccionarCalendarioView.showListComportamiento(tipoCalendarioUiList);
    }

    @Override
    public void onSalirSelectPiket(ArrayList<String> photoPaths) {
        if(photoPaths.isEmpty()){
            if(view!=null)view.hideImage();
            pathEdit = null;
            imageBaseG4= null;
        }else {
            pathEdit = "#";
            imageBaseG4 = getFile64.execute(photoPaths.get(0));
            if(view!=null)view.showImage(photoPaths.get(0));
        }

    }


    @Override
    public void btnCloseFecha() {
        fechaEntrega=0;
        showFecha();
    }

    @Override
    public void btnCloseHora() {
        horaEntrega="";
        showHora();
    }

    private void showHora() {
        String hora = TextUtils.isEmpty(horaEntrega)||horaEntrega==null ? "":Utils.changeTime12Hour(horaEntrega);
        if(TextUtils.isEmpty(horaEntrega)){
            if(view!=null)view.hideBtnCloseHora();
        }else {
            if(view!=null)view.showBtnCloseHora();
        }
        if(view!=null)view.setHora(hora);
    }
}
