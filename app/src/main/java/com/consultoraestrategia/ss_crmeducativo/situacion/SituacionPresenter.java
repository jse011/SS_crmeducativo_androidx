package com.consultoraestrategia.ss_crmeducativo.situacion;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.nio.BufferUnderflowException;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public interface SituacionPresenter extends BasePresenter<SituacionView> {


    void onResumeFragment();
    void setExtras(Bundle bundle);

    void onViewCreated();
}
