package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.bundle.CrearTareaBundle;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.MoverArchivosAlaCarpetaTarea;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class CreateTareaPresenterImpl extends BasePresenterImpl<CreateTareaView> implements CreateTareaPresenter {

    private static final String TAG = CreateTareaPresenterImpl.class.getSimpleName();
    private CreateTareaView view;
    private UseCaseHandler useCaseHandler;
    private CrearTareaUseCase crearTareaUseCase;
    private EliminarRecursoUseCase eliminarRecursoUseCase;
    private int silavoEventoId= 0;
    HeaderTareasAprendizajeUI headerTareasAprendizajeUI;
    private int mIdUsuario = 0;
    private int mIdCurso = 0;
    private TareasUI tareasUI;
    private long fechaEntrega;
    private String horaEntrega;
    private int programaEducativoId;
    int estadoTarea = 263;
    private int opcion;
    private MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea;

    public CreateTareaPresenterImpl(UseCaseHandler useCaseHandler, Resources resources,
                                    CrearTareaUseCase crearTareaUseCase,
                                    EliminarRecursoUseCase eliminarRecursoUseCase,
                                    MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea) {
        super(useCaseHandler, resources);
        this.useCaseHandler = useCaseHandler;
        this.crearTareaUseCase = crearTareaUseCase;
        this.eliminarRecursoUseCase = eliminarRecursoUseCase;
        this.moverArchivosAlaCarpetaTarea = moverArchivosAlaCarpetaTarea;
    }

    @Override
    protected String getTag() {
        return CreateTareaPresenterImpl.class.getSimpleName();
    }

    @Override
    public void attachView(CreateTareaView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        if(tareasUI!=null){
            fechaEntrega = tareasUI.getFechaLimite();
            horaEntrega = tareasUI.getHoraEntrega();
            opcion=1;
            if(view!=null)view.setDataFields(tareasUI);

            if(tareasUI.getRecursosUIList()!=null){
                for (RecursosUI recursosUI : tareasUI.getRecursosUIList())recursosUI.setSelect(true);
                if(view!=null)view.showListArchivos(new ArrayList<RepositorioFileUi>(tareasUI.getRecursosUIList()));
            }
        }
        showHora();
        showFecha();
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
        if(view!=null)view.actualiceTarea(programaEducativoId);
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CrearTareaBundle crearTareaBundle = new CrearTareaBundle(extras);
       headerTareasAprendizajeUI = crearTareaBundle.getHeaderTareasAprendizajeUI();
       tareasUI = crearTareaBundle.getTareasUI();
       silavoEventoId = crearTareaBundle.getSilavoId();
       mIdUsuario = crearTareaBundle.getUsuarioId();
       mIdCurso = crearTareaBundle.getCursoId();
       programaEducativoId = crearTareaBundle.getProgramaEducativoId();
        Log.d(TAG, "tareasUI!=null " + (tareasUI!=null));
    }


    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {
    }


    public void createTarea(String titulo, String instruciones, int estadoTarea, final List<RepositorioFileUi> recursosUIList) {

        useCaseHandler.execute(crearTareaUseCase,
                new CrearTareaUseCase.RequestValues(
                        null,
                        0,//0=crear 1= editar
                        headerTareasAprendizajeUI.getIdUnidadAprendizaje(),
                        silavoEventoId,
                        mIdUsuario,
                        titulo,
                        instruciones,
                        fechaEntrega,
                        horaEntrega,
                        mIdCurso,
                        headerTareasAprendizajeUI.getIdSesionAprendizaje(),
                        estadoTarea,
                        recursosUIList
                ),

                new UseCase.UseCaseCallback<CrearTareaUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(CrearTareaUseCase.ResponseValue response) {

                        if(response.getTareasUI()!=null){
                            moverArchivosAlaCarpetaTarea.execute(response.getTareasUI().getNombreCurso(),response.getTareasUI().getTituloTarea() , recursosUIList);
                        }

                        if(view!=null)view.showMsjs("CorrectoCreated");
                        if(view!=null)view.limpiarInputs(response.getTareasUI());
                        if(view!=null)view.notyDataBaseChange();
                        //view.addTareas(response.getTareasUI());

                    }

                    @Override
                    public void onError() {
                        if(view!=null)view.showMsjs("error");
                    }
                });
    }


    public void editarTarea(String titulo, String instruciones, int estadoTarea, List<RepositorioFileUi> recursosUIList) {
//        recursosUIList = tareasUI.getRecursosUIList();
        useCaseHandler.execute(crearTareaUseCase,
                new CrearTareaUseCase.RequestValues(
                        tareasUI,
                        1,//0=crear 1= editar
                        headerTareasAprendizajeUI.getIdUnidadAprendizaje(),
                        silavoEventoId,
                        mIdUsuario,
                        titulo,
                        instruciones,
                        fechaEntrega,
                        horaEntrega,
                        mIdCurso,
                        headerTareasAprendizajeUI.getIdSesionAprendizaje(),
                        estadoTarea,
                        recursosUIList),

                new UseCase.UseCaseCallback<CrearTareaUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(CrearTareaUseCase.ResponseValue response) {
                        if(view!=null)view.limpiarInputs(response.getTareasUI());
                        if(view!=null)view.notyDataBaseChange();

                    }

                    @Override
                    public void onError() {
                        if(view!=null)view.showMsjs("error");
                    }
                });
    }


    private int error = 0;

    @Override
    public int validateField(EditText editText) {
        if(view!=null)view.setEnableInputs(false);
        if (editText.getText().toString().equals("")) {
            editText.setError("Ingrese este campo ");
            error++;

        }
        return error;
    }

    @Override
    public void correctImputs(int errorParam, String titulo, String instrucciones, int estadoTareaCicked, List<RepositorioFileUi> recursosUIList) {
        Log.d(TAG, "nro Errores : " + errorParam);
        Log.d(TAG, "opcion : " + opcion);
        estadoTarea = estadoTareaCicked;
        if (errorParam == 0) {
            if (opcion == 0) {
               createTarea(titulo, instrucciones, estadoTarea, recursosUIList);
            } else {
               editarTarea(titulo, instrucciones, estadoTarea, recursosUIList);
            }

        } else if (errorParam > 0) {
            if(view!=null)view.setEnableInputs(true);
        }
        error = 0;

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
        Log.d(TAG, "horaEntrega: " + horaEntrega);
        showHora();
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
