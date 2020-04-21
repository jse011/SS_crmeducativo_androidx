package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.PresicionPresenter;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;

import java.util.List;

public interface PresicionView extends BaseView<PresicionPresenter> {
    void setListItems(List<NotaCircularUi> listItems);
    void setValorNotaAsignado(String nota, String color);
    void setImagenNota(String url);
    void setSelectorNota(String valor);
    void setDescripcionNota(String descripcion);
    void setRangoNota(String rango);
    void setCalValorNota(String nota);
    void setRubroEvaluacion(String nombre);
    void setColorFondo(String color);
    void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId);
}
