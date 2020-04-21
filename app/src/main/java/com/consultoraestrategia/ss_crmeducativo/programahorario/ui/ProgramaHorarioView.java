package com.consultoraestrategia.ss_crmeducativo.programahorario.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public interface ProgramaHorarioView extends BaseView<ProgramaHorarioPresenter> {
    void showHorario(List<DiaUi> columna, List<HoraUi> fila, List<List<Object>> celdaHorarioUiListList);
    void showListaProgramaEducativo(List<ProgramaHorarioUi> programaHorarioUiList);
    void showCurso(List<CursoUi> cursoUiList);


    void showTextEmpty();

    void showTabPrograma();

    void hideTabPrograma();
}
