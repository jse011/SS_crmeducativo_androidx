package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

/**
 * Created by SCIEV on 5/10/2017.
 */

public interface RegistroPresenter extends BasePresenter<RegistroView> {

    void actionDowButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY, float posicionElementoX, float posicionElementoY);
    void actionMoveButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY);
    void actionUpButtonFloat();
    void onClickEvaluacion(NotaUi evaluacion);
    void onClickLongEvaluacion(NotaUi evaluacion);
    void onItemSelectRubroEval(RubroEvaluacionUi rubroEvaluacionUi);
    void onClickItemIndicador(IndicadorUi indicadorUi);
    void setExtras(Bundle extras);
    void onChangeCrearRubro();
    void onChangeRubro();
    void onSelectAlumnoEvaluacion(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi);
    void onSelectAlumnoEvaluacionSelectorNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi);
    void onSelectAlumnoEvaluacionTecladoNumerico(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi);
    void changeEvaluacion();
    void onClickIndicador();
    void onClickAlumno(AlumnosEvaluacionUi item);
    void updateTableView(FiltroTableUi filtroTableUi);
    void onLongClickNota(AlumnosEvaluacionUi item, NotaUi nota);
    void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId);

    void setAlumnosList();

    void onClickBtnPublicar(AlumnosEvaluacionUi item, OptionPublicar optionPublicar);

    void onClickBtnComentario(AlumnosEvaluacionUi item, OptionComentario comentario);

    void onActivityCreated();

    void onClikCornerTableView();

    void onClickEye();

    void onClickFooter();

    void onClickClear();
}
