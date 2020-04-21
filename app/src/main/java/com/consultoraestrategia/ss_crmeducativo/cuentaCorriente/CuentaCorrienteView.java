package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CuentaCorrienteView extends BaseView<CuentaCorrientePresenter> {

    void setCuentaCorrienteList(List<CuentaCoUI> cuentaCoUIList);

    void setNombreAlumno(Persona persona);

    void setAniosAcademicosList(List<String> aniosAcademicosList);

    void setUrlImgProfile(String URL_IMG_PROFILE);

    void showProgress();

    void hideProgress();

    void showMessageDeuda(String msj, double restante);

    void hideMessageDeuda();
}
