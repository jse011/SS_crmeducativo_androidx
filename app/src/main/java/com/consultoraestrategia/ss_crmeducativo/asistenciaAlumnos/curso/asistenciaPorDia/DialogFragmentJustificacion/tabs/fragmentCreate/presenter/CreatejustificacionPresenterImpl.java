package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.ui.FragmentCreateView;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetArchivoAsistenciaList;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetTipoJustificacion;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;

import java.util.ArrayList;
import java.util.List;

public class CreatejustificacionPresenterImpl extends BaseFragmentPresenterImpl<FragmentCreateView> implements CreateJusticicacionPresenter {

    String TAG=CreatejustificacionPresenterImpl.class.getSimpleName();
    GetTipoJustificacion getTipoJustificacion;
    private JustificacionUi justificacionUi;
    private List<TipoUi> listTipos;
    private TipoUi tipoSelected;
    private AsistenciaUi asistenciaUi;
    private AsistenciaJustificaBundel asistenciaJustificaBundel;
    private GetArchivoAsistenciaList getArchivoAsistenciaList;
    private List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();

    public CreatejustificacionPresenterImpl(UseCaseHandler handler, Resources res, GetTipoJustificacion getTipoJustificacion, GetArchivoAsistenciaList getArchivoAsistenciaList) {
        super(handler, res);
        this.getTipoJustificacion = getTipoJustificacion;
        this.getArchivoAsistenciaList = getArchivoAsistenciaList;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        if(singleItem instanceof TipoUi)tipoSelected=(TipoUi)singleItem;
        Log.d(TAG, "tipo selected " + tipoSelected.getNombre());
        if(view!=null)view.setTipoJustificacion(tipoSelected);
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();

    }
    public void getTipos(){
        Log.d(TAG, "getTipos");
        handler.execute(getTipoJustificacion, new GetTipoJustificacion.RequestValues(), new UseCase.UseCaseCallback<GetTipoJustificacion.ResponseValue>() {
            @Override
            public void onSuccess(GetTipoJustificacion.ResponseValue response) {
                listTipos= new ArrayList<>();
                listTipos= response.getTipoUilis();
                if(tipoSelected!=null){
                    for (TipoUi tipoUi: listTipos){
                        if(tipoUi.getId() == tipoSelected.getId()){
                            tipoSelected = tipoUi;
                            justificacionUi.setTipo(tipoUi);
                        }
                    }
                }else {
                    if(!listTipos.isEmpty())tipoSelected= listTipos.get(0);
                }
                if(view!=null)view.setTipoJustificacion(tipoSelected);
            }

            @Override
            public void onError() {
                Log.d(TAG, "no se pudo listar tipos de justificacion ");
            }
        });
    }

    private void setDatos() {
        if(view!=null)view.setDatos(asistenciaUi.getAlumnosUi(), asistenciaUi.getFecha(), justificacionUi);
    }


    @Override
    public void setExtras(Bundle extras) {
        Log.d(TAG, "setExtras");
        super.setExtras(extras);
        asistenciaJustificaBundel = AsistenciaJustificaBundel.clone(extras);
        if(asistenciaJustificaBundel!=null){
            asistenciaUi = new AsistenciaUi();
            asistenciaUi.setId(asistenciaJustificaBundel.getAsistenciaId());
            asistenciaUi.setFecha(asistenciaJustificaBundel.getFechaAsistencia());
            AlumnosUi alumnosUi = new AlumnosUi();
            alumnosUi.setId(asistenciaJustificaBundel.getAlumnoId());
            alumnosUi.setLastName(asistenciaJustificaBundel.getLastName());
            alumnosUi.setNombre(asistenciaJustificaBundel.getNombre());
            alumnosUi.setUrlProfile(asistenciaJustificaBundel.getUrlProfile());
            asistenciaUi.setAlumnosUi(alumnosUi);
            justificacionUi = new JustificacionUi();
            justificacionUi.setId(asistenciaJustificaBundel.getJustificacionId());
            justificacionUi.setRazon(asistenciaJustificaBundel.getJustificacionRazon());
            if(asistenciaJustificaBundel.getTipoJustificacionId()!=0){
                this.tipoSelected = new TipoUi();
                this.tipoSelected.setId(asistenciaJustificaBundel.getTipoJustificacionId());
            }
            List<RepositorioFileUi> repositorioFileUiList = asistenciaJustificaBundel.getRepositorioFileUiList();
            this.repositorioFileUiList.clear();
            if(repositorioFileUiList!=null&&!repositorioFileUiList.isEmpty()){
                Log.d(TAG, "repositorioFileUiList: " + repositorioFileUiList.size());
                this.repositorioFileUiList.addAll(asistenciaJustificaBundel.getRepositorioFileUiList());
                if (view!=null)view.showListArchivos(this.repositorioFileUiList);
            } else {
                getArchivos();
            }
            getTipos();
            setDatos();
        }

    }

    private void getArchivos() {
        if(justificacionUi==null)return;
        getArchivoAsistenciaList.execute(new GetArchivoAsistenciaList.Request(asistenciaUi.getId(), justificacionUi.getId()), new UseCaseSincrono.Callback<List<AsistenicaArchivoUi>>() {
            @Override
            public void onResponse(boolean success, List<AsistenicaArchivoUi> value) {
                if(success){
                    List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
                    for (AsistenicaArchivoUi asistenicaArchivoUi: value){
                        RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
                        repositorioFileUi.setArchivoId(String.valueOf(asistenicaArchivoUi.getKey()));
                        repositorioFileUi.setDescripcion(asistenicaArchivoUi.getNombre());
                        repositorioFileUi.setPath(asistenicaArchivoUi.getPath());
                        Log.d(TAG, "asistenicaArchivo Url: " + asistenicaArchivoUi.getUrl());
                        repositorioFileUi.setUrl(asistenicaArchivoUi.getUrl());
                        //repositorioFileUi.setUrl("https://github.com/git-for-windows/git/releases/download/v2.20.1.windows.1/Git-2.20.1-64-bit.exe");
                        switch (asistenicaArchivoUi.getTipo()){
                            case DEFAUL:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
                                break;
                            case MATERIALES:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
                                break;
                            case HOJA_CALCULO:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                                break;
                            case PRESENTACION:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                                break;
                            case DOCUMENTO:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                                break;
                            case IMAGEN:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                                break;
                            case VINCULO:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
                                break;
                            case VIDEO:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                                break;
                            case AUDIO:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                                break;
                            case PDF:
                                repositorioFileUi.setTipoFileU(RepositorioTipoFileU.PDF);
                                break;
                        }
                        if(TextUtils.isEmpty(repositorioFileUi.getPath())){
                            repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                        }else {
                            repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                        }

                        String fileName = repositorioFileUi.getUrl();
                        if(!TextUtils.isEmpty(fileName)){
                            String extension = "";
                            int i = fileName.lastIndexOf('.');
                            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                            String file = fileName.substring(p + 1);
                            if (i > p) {
                                extension = fileName.substring(i+1);
                            }
                            repositorioFileUi.setNombreArchivo(file);
                            repositorioFileUi.setNombreRecurso(asistenicaArchivoUi.getNombre());
                        }else {
                            repositorioFileUi.setNombreArchivo(asistenicaArchivoUi.getNombre());
                            repositorioFileUi.setNombreRecurso(asistenicaArchivoUi.getNombre());
                        }
                        repositorioFileUi.setFechaAccionArchivo(asistenicaArchivoUi.getFechaAccionArchivo());
                        repositorioFileUi.setSelect(true);
                        repositorioFileUiList.add(repositorioFileUi);
                    }
                    CreatejustificacionPresenterImpl.this.repositorioFileUiList.addAll(repositorioFileUiList);
                    if (view!=null)view.showListArchivos(CreatejustificacionPresenterImpl.this.repositorioFileUiList);
                }else {
                    if (view!=null)view.showListArchivos(new ArrayList<RepositorioFileUi>());
                }
            }
        });
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

    }

    @Override
    public void onCreateView() {
        Log.d(TAG, "onCreateView");
        super.onCreateView();
    }

    @Override
    public void showTiposJustificacion() {
        int  position= listTipos.indexOf(tipoSelected);
        Log.d(TAG, " showTiposJustificacion "+ position);
        if(view!=null)view.showListSingleChooser("Seleccione Tipo", new ArrayList<Object>(listTipos), position);
    }

    @Override
    public AsistenciaJustificaBundel saveJustificacion(String razon){
        Log.d(TAG, "razon " +razon );
        /*justificacionUi.setRazon(razon);
        justificacionUi.setTipo(tipoSelected);
        justificacionUi.setTipoUiList(listTipos);
        asistenciaUi.setJustificacionUi(justificacionUi);*/
        asistenciaJustificaBundel.setJustificacionRazon(razon);
        if(tipoSelected!=null)asistenciaJustificaBundel.setTipoJustificacionId(tipoSelected.getId());
        return asistenciaJustificaBundel;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
