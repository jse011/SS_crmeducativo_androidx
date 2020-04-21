package com.consultoraestrategia.ss_crmeducativo.programahorario;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ui.ProgramaHorarioView;

public interface ProgramaHorarioPresenter extends BasePresenter<ProgramaHorarioView> {
    void ProgramaHorarioUi(ProgramaHorarioUi programaHorarioUi);
    void postShowHorario();

}
