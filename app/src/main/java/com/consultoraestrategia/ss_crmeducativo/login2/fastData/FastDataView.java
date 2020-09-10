package com.consultoraestrategia.ss_crmeducativo.login2.fastData;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;

public interface FastDataView extends BaseView<FastDataPresenter> {

    void updateListaActualizar(ProgramaEducativoUi programaEducativoUi);

    void clearListaActualizar();

    void finshedDialogWithError();

    void cerrarActividad();

    void changeDataBase();

}
