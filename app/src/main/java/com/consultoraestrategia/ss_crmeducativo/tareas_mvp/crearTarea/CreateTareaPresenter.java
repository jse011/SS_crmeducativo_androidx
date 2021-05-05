package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea;

import android.os.Bundle;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CreateTareaPresenter extends BasePresenter<CreateTareaView> {

    void setExtras(Bundle extras);

    int validateField(EditText editText);

    void correctImputs(int error, String titulo, String instrucciones, int estadoTareaCicked, List<RepositorioFileUi> recursosUIList);

    void btnSelectTime();

    void btnSelectDate();

    void onClikSaveFecha(long timeInMillis);

    void onChangeTime(int hourOfDay, int minute);

    void btnCloseFecha();

    void btnCloseHora();
}
