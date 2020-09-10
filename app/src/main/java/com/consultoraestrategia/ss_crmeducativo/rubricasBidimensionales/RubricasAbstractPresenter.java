package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public interface RubricasAbstractPresenter extends BaseFragmentPresenter<RubricasView> {
    void setExtras(Bundle extras);

    /*metodos Rubrica*/
    void onRubricaSelected(RubBidUi rubBidUi);

    void actualizarItem(Bundle bundle);

    void onParentFabClicked();

    /*Agrega o Actualiza*/
    void onActualizarRubricaFragment(Intent intent);

    /*Opciones*/

    void onSelectOpcionMenuRubricas(RubBidUi rubBidUi, int posicion);

    void onAceptEliminar(RubBidUi rubBidUi, int programaEDucativoId);

    /*StarActivityForResult*/

    void onStartActivityForResult(Intent intent, int requestCode, @Nullable Bundle options);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResumeFragment(String idcalendarioPeriodo);

    void onClickBtnAceptarPublicarTodoEvaluaciones(RubBidUi rubBidUi);

    void comprobarActualizacionRubros(List<RubBidUi> rubBidUiList);

}
