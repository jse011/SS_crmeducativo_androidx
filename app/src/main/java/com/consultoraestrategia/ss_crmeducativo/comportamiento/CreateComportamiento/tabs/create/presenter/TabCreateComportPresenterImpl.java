package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.TabCreateComportFragment;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.TabCreateComportView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog.ListaComportamientoView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.DialogCreareComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetAlumnos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetDestino;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetTipos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetUsuarioUiDestinos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.ValidarUsuario;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.Recycler;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TabCreateComportPresenterImpl extends BaseFragmentPresenterImpl<TabCreateComportView> implements TabCreateComportPresenter {

    TipoComportamientoUi tipoComportamientoSelect;
    GetAlumnos getAlumnos;
    GetTipos getTipos;
    int cargaCursoId;
    int cargaAcademicaId;
    int docenteId;
    int programaEducativoId;
    int calendarioPeriodoId;
    int entidadId;
    int georeferenciaId;
    String idComportamiento;
    AlumnoUi alumnoUiselected;
    List<AlumnoUi >alumnoUiList;
    ComportamientoUi comportamientoUi;
    long fechaseleted;
    boolean tipo;
    GetComportamiento getComportamiento;
    List<TipoUi>tipoUiList;
    List<TipoUi>tipoUiListHijos;
    String colorDisenio;
    TipoUi tipoPadreSelected;
    private String TAG= TabCreateComportPresenterImpl.class.getSimpleName();
    private int infoAlumnoId;
    private ListaComportamientoView listaComportamientoView;


    private GetUsuarioUiDestinos getUsuarioUiDestinos;
    private List<UsuarioUi> usuarioUiList;
    private GetDestino getDestino;
    private ValidarUsuario validarUsuario;
    //private List<UsuarioUi>destinosIdsEstatico = new ArrayList<>();
    private boolean selectedTutor;
    private boolean selectedApoderado;
    private boolean selectedPadre;

    public TabCreateComportPresenterImpl( UseCaseHandler handler, Resources res, GetAlumnos getAlumnos,  GetTipos getTipos, GetComportamiento getComportamiento, GetUsuarioUiDestinos getUsuarioUiDestinos, GetDestino getDestino,
                                          ValidarUsuario validarUsuario) {
        super(handler, res);
        this.getAlumnos=getAlumnos;
        this.getTipos=getTipos;
        this.getComportamiento=getComportamiento;
        this.getUsuarioUiDestinos = getUsuarioUiDestinos;
        this.getDestino = getDestino;
        this.validarUsuario = validarUsuario;
    }

    @Override
    protected String getTag() {
        return TAG;
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

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle= new CRMBundle(extras);
        Log.d(TAG,crmBundle.toString());
        this.cargaCursoId= crmBundle.getCargaCursoId();
        this.cargaAcademicaId= crmBundle.getCargaAcademicaId();
        this.programaEducativoId=crmBundle.getProgramaEducativoId();
        this.docenteId= crmBundle.getEmpleadoId();
        this.calendarioPeriodoId= crmBundle.getCalendarioPeriodoId();
        this.entidadId=crmBundle.getEntidadId();
        this.georeferenciaId=crmBundle.getGeoreferenciaId();
        this.idComportamiento=extras.getString(DialogCreareComportamiento.ID_COMPORTAMIENTO, null);
        this.colorDisenio=extras.getString(DialogCreareComportamiento.COLOR_PARAMETRO_DISENIO, null);
        this.infoAlumnoId = extras.getInt(DialogCreareComportamiento.ID_ALUMNOID,0);
        comportamientoUi= new ComportamientoUi();
        fechaseleted = System.currentTimeMillis();


        this.georeferenciaId = crmBundle.getGeoreferenciaId();


        getTipos();
        if(!TextUtils.isEmpty(idComportamiento))getComportamiento();
        else getAlumnos();


        if(view!=null)view.setColorParametroDisenio(colorDisenio, fechaseleted);

        if(!TextUtils.isEmpty(idComportamiento))getComportamientoDestinos();
    }



    private void getComportamiento() {
        GetComportamiento.Response response= getComportamiento.execute(new GetComportamiento.Requests(idComportamiento));
        if(response!=null){
            Log.d(TAG, "getComportamiento " + response.getComportamientoUi().getDescripcion());
            this.comportamientoUi= response.getComportamientoUi();
            this.alumnoUiselected= comportamientoUi.getAlumnoUi();
            TipoUi tipoUi = comportamientoUi.getTipoConducta();

            for(TipoUi itemTipoUi: tipoUiList){
                if(tipoUi!=null && itemTipoUi.getId() == tipoUi.getId()){
                    itemTipoUi.setSelected(true);
                    this.tipoPadreSelected = itemTipoUi;
                }
            }
            this.fechaseleted= comportamientoUi.getFecha();
            this.tipoComportamientoSelect = comportamientoUi.getTipoComportamientoUi();
            if(view!=null){
                view.setDatosC(comportamientoUi);
                if(alumnoUiselected!=null)view.setDatosAlumno(alumnoUiselected);
                if(comportamientoUi.getArchivoUiList()!=null)view.setDatosArchivo(comportamientoUi.getArchivoUiList());
                if(tipoPadreSelected!=null)view.showTiposPadres(tipoUiList);
                if(tipoComportamientoSelect!=null)view.showTipoComportamiento(tipoComportamientoSelect);
                //view.showListTipos(comportamientoUi.getTipoConducta().getTipoUiListHijos());
            }
        }




    }

    private void getTipos() {
        GetTipos.Response response = getTipos.execute(new GetTipos.Requests(georeferenciaId, entidadId));
        Log.d(TAG, "getTipos " + response.getTiposlist().size());
        tipoUiList= new ArrayList<>();
        tipoUiList=response.getTiposlist();
        if(view!=null)view.showTiposPadres(tipoUiList);
    }


    public void getAlumnos() {
        handler.execute(getAlumnos, new GetAlumnos.RequestValues(cargaCursoId), new UseCase.UseCaseCallback<GetAlumnos.ResponseValue>() {
            @Override
            public void onSuccess(GetAlumnos.ResponseValue response) {
                Log.d(TAG, "onSuccess "+ response.getAlumnoUiList().size());
                alumnoUiList= response.getAlumnoUiList();
                if(infoAlumnoId!=0){
                    showAlumno();
                }else  setAutocompleteList(alumnoUiList);
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error: No se pudo listar los alumnos ");
            }
        });
    }

    private void showAlumno() {
        for(AlumnoUi alumnoUi:alumnoUiList){
            if(alumnoUi.getId()==infoAlumnoId){
                alumnoUiselected=alumnoUi;
                ComportamientoUi comportamientoUi= new ComportamientoUi();
                comportamientoUi.setAlumnoUi(alumnoUiselected);
                comportamientoUi.setDescripcion("");
                if(view!=null){
                    view.setDatosAlumno(alumnoUiselected);
                    view.setDatosC(comportamientoUi);
                }
            }
        }
    }

    @Override
    public void selectedAlumno(AlumnoUi alumnoUi) {
        alumnoUiselected= alumnoUi;
        if(view!=null)view.setDatosAlumno(alumnoUiselected);
        selectedTutor = false;
        selectedPadre = false;
        selectedApoderado = false;
        if(view!=null)view.setSelectedTutor(selectedTutor);
        if(view!=null)view.setSelectedPadre(selectedPadre);
        if(view!=null)view.setSelectedApoderado(selectedApoderado);
        Log.d(TAG, "selectedAlumno " +alumnoUiselected.getNombre() );

    }

    @Override
    public ComportamientoUi saveComportamiento(String descripcion) {

        //comportamientoUi= new ComportamientoUi();
        // if(alumnoUiselected==null){if(view!=null)view.setError();}
        comportamientoUi.setTipoConducta(tipoPadreSelected);
        comportamientoUi.setTipoComportamientoUi(tipoComportamientoSelect);
        comportamientoUi.setFecha(fechaseleted);
        comportamientoUi.setDescripcion(descripcion);
        comportamientoUi.setAlumnoUi(alumnoUiselected);
        comportamientoUi.setCargaAcademicaId(cargaAcademicaId);
        comportamientoUi.setIdprogramaEducativo(programaEducativoId);
        comportamientoUi.setCalendarioPeridoId(calendarioPeriodoId);
        comportamientoUi.setDocenteId(docenteId);
        comportamientoUi.setCargaCursoId(cargaCursoId);

        return comportamientoUi;

    }

    @Override
    public void setFecha(long fecha) {
        Log.d(TAG, "setFecha " + Utils.f_fecha_letras(fecha));
        this.fechaseleted= fecha;
    }

    @Override
    public AlumnoUi getAlumnoSelected() {
        Log.d(TAG, "LLEGO AQUI "+alumnoUiselected );
        return alumnoUiselected;
    }

    @Override
    public void attachView(ListaComportamientoView listaComportamientoView) {
        Log.d(TAG,"attachView ListaComportamientoView");
        this.listaComportamientoView = listaComportamientoView;
    }

    @Override
    public void onDestroyViewListaComportamiento() {
        listaComportamientoView = null;
    }

    @Override
    public void onResumedListaComportamiento() {
        Log.d(TAG,"onResumedListaComportamiento");
        if(tipoPadreSelected!=null&&
                tipoPadreSelected.getTipoUiListHijos()!=null&&
                listaComportamientoView!=null)listaComportamientoView.showListComportamiento(tipoPadreSelected.getTipoUiListHijos());

    }

    @Override
    public void onClickTipoMerito(TipoUi tipoUi) {
        if(tipoPadreSelected!=null)tipoPadreSelected.setSelected(false);
        tipoPadreSelected = tipoUi;
        tipoPadreSelected.setSelected(true);
        if(view!=null)view.showTiposPadres(tipoUiList);
        if(view!=null)view.showDialogComportamientoTipo();
    }

    @Override
    public void onClickTipoComportamiento(TipoComportamientoUi tipoComportamientoUi) {
        tipoComportamientoSelect = tipoComportamientoUi;
        if(view!=null)view.hideDialogComportamientoTipo();
        if(view!=null)view.showTipoComportamiento(tipoComportamientoUi);
    }

    @Override
    public void onClickClock() {
        if(view!=null)view.showHoraComportamiento(fechaseleted);
    }

    @Override
    public void onClickCalendar() {
        if(view!=null)view.showCalendarComportamineto(fechaseleted);
    }

    @Override
    public void seletedUsuario(UsuarioUi usuarioUi) {

    }

    @Override
    public DestinoUi getDestinatarios() {


        DestinoUi destinoUi= new DestinoUi();

        destinoUi.setTutor(selectedTutor);
        destinoUi.setPadre(selectedPadre);
        destinoUi.setApoderado(selectedApoderado);
        destinoUi.setGeoreferenciaId(georeferenciaId);
        return destinoUi;
    }

    @Override
    public void selectedCheckPadre() {
        selectedPadre = !selectedPadre;
    }

    @Override
    public void selectedCheckApoderado() {
        selectedApoderado = !selectedApoderado;
    }

    @Override
    public void selectedCheckTutor() {
        selectedTutor = !selectedTutor;
    }



    private void setAutocompleteList(List<AlumnoUi> alumnoUiList) {
        ArrayList<AlumnoUi>alumnos= new ArrayList<>();
        for( AlumnoUi alumnoUi : alumnoUiList)alumnos.add(alumnoUi);
        if(view!=null)view.setAutoCompleteList(alumnos);
    }

    @Override
    public void seletedTipo(TipoUi tipo) {

    }

    @Override
    public void onActivityCreated() {
        super.onActivityCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(alumnoUiselected==null)if(view!=null)view.clearEditText();
    }

    private void getComportamientoDestinos() {
        GetDestino.Response response= getDestino.execute(new GetDestino.Requests(idComportamiento, georeferenciaId));
        if(response.getDestinoUi()!=null){
            selectedTutor = response.getDestinoUi().getTutor();
            selectedPadre = response.getDestinoUi().getPadre();
            selectedApoderado = response.getDestinoUi().getApoderado();
            if(view!=null)view.setSelectedTutor(selectedTutor);
            if(view!=null)view.setSelectedPadre(selectedPadre);
            if(view!=null)view.setSelectedApoderado(selectedApoderado);
        }

    }
}
