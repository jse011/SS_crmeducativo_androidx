package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks;


import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetCuentasCoUICallback {
    void onCuentasCoUILoaded(double restante, double totalCredito, double totalDebito, List<CuentaCoUI> cuentaCoUIList);

    void onError(String error);
}
