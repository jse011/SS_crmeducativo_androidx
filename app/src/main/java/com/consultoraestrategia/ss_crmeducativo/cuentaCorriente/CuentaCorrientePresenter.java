package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;




/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CuentaCorrientePresenter extends BasePresenter<CuentaCorrienteView> {

    void setExtras(Bundle extras);

    void findCuotas(String s);
}
