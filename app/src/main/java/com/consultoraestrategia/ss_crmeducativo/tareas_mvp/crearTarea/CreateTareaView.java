package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CreateTareaView extends BaseView<CreateTareaPresenter> {

    void showMsjs(String msj);

    void limpiarInputs(TareasUI tareasUI);


    void setEnableInputs(boolean habilitar);

    void setDataFields(TareasUI tareasUI);


    void guardarTarea(int estadoTareaCicked, List<RepositorioFileUi> recursosUIList);

    void selectDate();

    void selectTime();

    void showListArchivos(List<RepositorioFileUi> value);

    void setFecha(String fecha);

    void showTextEmpty();

    void actualiceTarea(int programaEducativoId);

    void notyDataBaseChange();

    void setHora(String hora);

    void showBtnCloseFecha();
    void hideBtnCloseFecha();

    void showBtnCloseHora();
    void hideBtnCloseHora();
}
