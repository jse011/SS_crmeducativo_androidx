package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;;

/**
 * Created by SCIEV on 5/10/2017.
 */

public interface RegistroPresenter extends BaseFragmentPresenter<RegistroView> {
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
    void onSelectAlumnoEvaluacion(NotaUi notaUi);
    void onSelectAlumnoGrupo(Object itemEvaluacionUi);
    void onAddCrear();
    void onChangeAlumnoList();
    void onSelectAlumnoEvaluacionTecladoNumerico(NotaUi nota);
    void changeEvaluacion();
    void onClickIndicador();
    void onLongClickNota(NotaUi nota);
    void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId);
    void setCleanList();
    void onClickBtnPublicar(OptionPublicar optionPublicar);
    void onClickBtnComentario(OptionComentario optionComentario);
    void onSelectAlumnoEvaluacionSelectorNota(NotaUi notaUi);
    void onClickEye();
    void onClickFooter();
}
