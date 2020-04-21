package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaBundle;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui.AsistenciaView;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetAsistenciaDiariaList;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.SaveAsistencia;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AsistenciaPresenterImpl extends BasePresenterImpl<AsistenciaView>implements AsistenciaPresenter {

    private GetAsistenciaDiariaList getAsistenciaDiariaList;
    private AsistenciaUi asistenciaUi;
    private String TAG = AsistenciaPresenterImpl.class.getSimpleName();
    AsistenciaView view;
    ValorTipoNotaUi valorTipoNotaUiselected;
    private List<AsistenciaUi>asistenciaUiList;
    private SaveAsistencia saveAsistencia;
    List<List<ValorTipoNotaUi>> celdaUiList =  new ArrayList<>();
    List<ValorTipoNotaUi>columnsList = new ArrayList<>();
    List<AlumnosUi>rowsList = new ArrayList<>();
    List<ValorTipoNotaUi>valorSelectedList = new ArrayList<>();
   // JustificacionUi justificacionOld;
    private AsistenciaUi asistenciaUiOld;
    //List<AsistenicaArchivoUi> asistenicaArchivoUiListOld = new ArrayList<>();
    public AsistenciaPresenterImpl(UseCaseHandler handler, Resources res, GetAsistenciaDiariaList getAsistenciaDiariaList, SaveAsistencia saveAsistencia) {
        super(handler, res);
        this.getAsistenciaDiariaList=getAsistenciaDiariaList;
        this.saveAsistencia=saveAsistencia;
    }

    @Override
    public void setExtras(Bundle extras) {
        AsistenciaBundle asistenciaBundle = AsistenciaBundle.clone(extras);
        asistenciaUi = new AsistenciaUi();
        if(asistenciaBundle!=null){
            asistenciaUi.setIdCalendarioPeriodo(asistenciaBundle.getIdCalendarioPeriodo());
            asistenciaUi.setIdCargaCurso(asistenciaBundle.getIdCargaCurso());
            asistenciaUi.setIdProgramaEducativo(asistenciaBundle.getIdProgramaEducativo());
            asistenciaUi.setIdGeoreferencia(asistenciaBundle.getIdGeoreferencia());
            asistenciaUi.setIdDocente(asistenciaBundle.getIdDocente());
            asistenciaUi.setFecha(asistenciaBundle.getFecha());
            asistenciaUi.setIdCargaAcademica(asistenciaBundle.getIdCargaAcademica());
        }

        Log.d(TAG, " asistenciaUi " + Utils.f_fecha_letras(asistenciaUi.getFecha()));
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void attachView(AsistenciaView view) {
        Log.d(TAG, "attachView");
        this.view=view;

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        getAsitenciasAlumnos();
    }


    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }


    private void getAsitenciasAlumnos() {
        Log.d(TAG, "getAsitenciasAlumnos");
        if(view!=null)view.showProgress();
        handler.execute(getAsistenciaDiariaList, new GetAsistenciaDiariaList.RequestValues(asistenciaUi), new UseCase.UseCaseCallback<GetAsistenciaDiariaList.ResponseValue>() {
            @Override
            public void onSuccess(GetAsistenciaDiariaList.ResponseValue response) {
                Log.d(TAG, "onSuccess "  + response.getAsistenciaUiList().size());
                asistenciaUiList= response.getAsistenciaUiList();
                showList();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void showList() {
        Log.d(TAG, "showList " );
        if(view!=null)view.hideProgress();
        if(!asistenciaUiList.isEmpty()) {
            if(asistenciaUiList.get(0).getTipoNotaUi()!=null){
                setRows();
                setColmns();
                showTable();
            }else showEmptyNota();
        }
        else showEmptyView();
    }

    private void setColmns() {
        columnsList.clear();
        TipoNotaUi tipoNotaUi= asistenciaUiList.get(0).getTipoNotaUi();
        this.columnsList.addAll(tipoNotaUi.getValorTipoNotaList());

    }
    private void showTable() {
        Log.d(TAG, "setColmns "+  columnsList.size());
        Log.d(TAG, "celdas "+ celdaUiList.size() );
        String color = null;
        for (ValorTipoNotaUi valorTipoNotaUi: this.columnsList)color = valorTipoNotaUi.getColorCurso();
        if(view!=null)view.showTableAsistenciaAlumno(this.celdaUiList, this.columnsList, this.rowsList, color);
    }

    private void setRows() {
        Log.d(TAG, "setRows " );
        rowsList.clear();
        celdaUiList.clear();
        for(AsistenciaUi asistenciaUi: asistenciaUiList){
            rowsList.add(asistenciaUi.getAlumnosUi());

            List<ValorTipoNotaUi> objectList= new ArrayList<>();
            objectList.addAll(asistenciaUi.getTipoNotaUi().getValorTipoNotaList());

            celdaUiList.add(objectList);
        }

    }
    private void  showEmptyNota(){
        if (view != null) {
            view.showEmptyNota();
        }
    }

     private void showEmptyView() {
        Log.d(TAG, "showEmptyView ");
        if (view != null) {
            view.showEmptyView();
        }
    }

    private void hideProgres() {
        if (view != null) {
            view.hideProgress();
        }
    }

    public void valorSeleted(AsistenciaUi asistenciaUi){
        AsistenciaJustificaBundel asistenciaJustificaBundel = new AsistenciaJustificaBundel();
        AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();
        asistenciaJustificaBundel.setAlumnoId(alumnosUi.getId());
        asistenciaJustificaBundel.setLastName(alumnosUi.getLastName());
        asistenciaJustificaBundel.setNombre(alumnosUi.getNombre());
        asistenciaJustificaBundel.setUrlProfile(alumnosUi.getUrlProfile());
        asistenciaJustificaBundel.setAsistenciaId(asistenciaUi.getId());
        asistenciaJustificaBundel.setFechaAsistencia(asistenciaUi.getFecha());
        JustificacionUi justificacionUi = asistenciaUi.getJustificacionUi();
        if(justificacionUi!=null){
            asistenciaJustificaBundel.setJustificacionId(justificacionUi.getId());
            asistenciaJustificaBundel.setJustificacionRazon(justificacionUi.getRazon());
            TipoUi tipoUi = justificacionUi.getTipo();
            if(tipoUi!=null){
                asistenciaJustificaBundel.setTipoJustificacionId(tipoUi.getId());
            }
        }
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        List<AsistenicaArchivoUi> asistenicaArchivoUiList = asistenciaUi.getAsistenicaArchivoUiList();
        if(asistenicaArchivoUiList!=null){
            for (AsistenicaArchivoUi asistenicaArchivoUi : asistenicaArchivoUiList){
                RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
                repositorioFileUi.setSelect(true);
                repositorioFileUi.setArchivoId(asistenicaArchivoUi.getKey());
                repositorioFileUi.setUrl(asistenicaArchivoUi.getUrl());
                repositorioFileUi.setPath(asistenicaArchivoUi.getPath());
                repositorioFileUi.setNombreArchivo(asistenicaArchivoUi.getNombre());
                repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                switch (asistenicaArchivoUi.getTipo()){
                    case IMAGEN:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case HOJA_CALCULO:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case PRESENTACION:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case DOCUMENTO:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case VINCULO:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case VIDEO:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case AUDIO:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case PDF:
                        repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                }
                repositorioFileUiList.add(repositorioFileUi);
            }
        }
        asistenciaJustificaBundel.setRepositorioFileUiList(repositorioFileUiList);

        Log.d(TAG, asistenciaJustificaBundel.toString());
        if(view!=null)view.showDialogJustificacion(asistenciaJustificaBundel);
    }
    @Override
    public void valorSelected(AsistenciaUi asistenciaUi, ValorTipoNotaUi valorTipoNotaUi) {
        if(asistenciaUiOld==null)asistenciaUiOld=asistenciaUi;
        if(valorTipoNotaUiselected!=null){
                if(valorTipoNotaUiselected!=valorTipoNotaUi ) {
                    if(asistenciaUiOld!=asistenciaUi)asistenciaUiOld=asistenciaUi;
                    else {
                        asistenciaUi.setJustificacionUi(null);
                        asistenciaUi.setAsistenicaArchivoUiList(null);
                    }
                }

        }
        else valorTipoNotaUiselected=valorTipoNotaUi;

        switch (valorTipoNotaUi.getTipo()){
            case AUSENTE_JTD:
                valorSeleted(asistenciaUi);
                break;
            case TARDE_JTD:
                valorSeleted(asistenciaUi);
                break;
        }

        for(ValorTipoNotaUi valor:asistenciaUi.getTipoNotaUi().getValorTipoNotaList()){
            Log.d("ASISTENCIAuI", valorTipoNotaUi.toString());
            switch (valorTipoNotaUi.getTipo()){
                case TARDE:
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.TARDE_JTD))valor.setEnabled(true);
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.AUSENTE_JTD))valor.setEnabled(false);
                    break;
                case AUSENTE:
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.AUSENTE_JTD))valor.setEnabled(true);
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.TARDE_JTD))valor.setEnabled(false);
                    break;
                case PUNTUAL:
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.TARDE_JTD))valor.setEnabled(false);
                    if(valor.getTipo().equals(ValorTipoNotaUi.Tipo.AUSENTE_JTD))valor.setEnabled(false);
                    break;
            }
        }
        asistenciaUi.setValorTipoNotaUi(valorTipoNotaUi);
        asistenciaUi.setHora(gethora());
        asistenciaUi.setSelected(true);
        asistenciaUi.setModificado(true);
    }
    private String gethora() {
        Calendar c = Calendar.getInstance();
        int hora= c.get(Calendar.HOUR_OF_DAY);
        int minuto= c.get(Calendar.MINUTE);
        return  hora +":"+minuto;
    }

    @Override
    public void saveAsistencias() {
        Log.d(TAG, "saveAsistencias " );
        handler.execute(saveAsistencia, new SaveAsistencia.RequestValues(asistenciaUiList), new UseCase.UseCaseCallback<SaveAsistencia.ResponseValue>() {
            @Override
            public void onSuccess(SaveAsistencia.ResponseValue response) {
                Log.d(TAG, "Guardo");
                showList();
                sincronizar();
            }
            @Override
            public void onError() {
                Log.d(TAG, "Error");
            }
        });
    }

    private void sincronizar() {
        if(view!=null)view.sincronizar(asistenciaUi.getIdProgramaEducativo());
    }

    @Override
    public void valorUnSelected(AsistenciaUi asistenciaUi) {
        asistenciaUi.setModificado(true);
        int position= asistenciaUiList.indexOf(asistenciaUi);
        if(position!=-1){
            asistenciaUiList.get(position).setValorTipoNotaUi(null);
            asistenciaUiList.get(position).setSelected(false);
            asistenciaUiList.get(position).setHora(null);
            asistenciaUiList.get(position).setAsistenicaArchivoUiList(null);
        }
    }

    @Override
    public void saveJustificacion(AsistenciaJustificaBundel asistenciaJustificaBundel) {


        int position=0;
        for(AsistenciaUi asistenciaUi: asistenciaUiList)if(asistenciaUi.getId()==asistenciaJustificaBundel.getAsistenciaId()){
            asistenciaUi.setId(asistenciaJustificaBundel.getAsistenciaId());
            valorTipoNotaUiselected=asistenciaUi.getValorTipoNotaUi();
            position= asistenciaUiList.indexOf(asistenciaUi);

        }
        JustificacionUi justificacionUi = new JustificacionUi();
        Log.d(TAG ,"descripcion justici " + asistenciaJustificaBundel.getJustificacionRazon());
        justificacionUi.setId(asistenciaJustificaBundel.getJustificacionId());
        TipoUi tipoUi = new TipoUi();
        tipoUi.setId(asistenciaJustificaBundel.getTipoJustificacionId());
        justificacionUi.setTipo(tipoUi);
        justificacionUi.setRazon(asistenciaJustificaBundel.getJustificacionRazon());
        List<AsistenicaArchivoUi> asistenicaArchivoUiList = new ArrayList<>();
        List<RepositorioFileUi> repositorioFileUiList = asistenciaJustificaBundel.getRepositorioFileUiList();
        if(repositorioFileUiList!=null){
            Log.d(TAG ,"repositorioFileUiList " + repositorioFileUiList.size());
            for (RepositorioFileUi repositorioFileUi : repositorioFileUiList){
                AsistenicaArchivoUi asistenicaArchivoUi = new AsistenicaArchivoUi();
                asistenicaArchivoUi.setKey(repositorioFileUi.getArchivoId());
                asistenicaArchivoUi.setNombre(repositorioFileUi.getNombreArchivo());
                asistenicaArchivoUi.setPath(repositorioFileUi.getPath());
                asistenicaArchivoUi.setUrl(repositorioFileUi.getUrl());
                asistenicaArchivoUi.setFechaAccionArchivo(repositorioFileUi.getFechaAccionArchivo());
                switch (repositorioFileUi.getTipoFileU()){
                    case IMAGEN:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.IMAGEN);
                        break;
                    case HOJA_CALCULO:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.HOJA_CALCULO);
                        break;
                    case DIAPOSITIVA:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PRESENTACION);
                        break;
                    case DOCUMENTO:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.DOCUMENTO);
                        break;
                    case VINCULO:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VINCULO);
                        break;
                    case VIDEO:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VIDEO);
                        break;
                    case AUDIO:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.AUDIO);
                        break;
                    case PDF:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PDF);
                        break;
                    case COMPRESS:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VINCULO);
                        break;
                    case MATERIALES:
                        asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VINCULO);
                        break;
                }
                asistenicaArchivoUiList.add(asistenicaArchivoUi);
            }
        }
        if(position!=-1){
            //justificacionOld= justificacionUi;
           // asistenicaArchivoUiListOld=asistenicaArchivoUiList;
            asistenciaUiList.get(position).setJustificacionUi(justificacionUi);
            asistenciaUiList.get(position).setAsistenicaArchivoUiList(asistenicaArchivoUiList);
        }
    }


}
