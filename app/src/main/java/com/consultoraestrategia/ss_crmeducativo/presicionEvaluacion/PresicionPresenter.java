package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion;


import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.TecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionView;

public interface PresicionPresenter extends BaseFragmentPresenter<PresicionView> {
    void onClickCalAceptar();
    void onClickCalEspacio();
    void onClickCalNumero(TecladoNumerico tecladoNumerico);
    void onClickCalBorrar();
    void onClickCalPunto();
    void onClickDirectoPresicionItem(NotaCircularUi notaCircularUi);
}
