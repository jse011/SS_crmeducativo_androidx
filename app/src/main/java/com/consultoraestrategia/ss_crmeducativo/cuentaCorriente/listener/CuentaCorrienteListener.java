package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.listener;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;

/**
 * Created by @stevecampos on 19/09/2017.
 */

public interface CuentaCorrienteListener {
    void onCuentaCoSelected(Person person);
    void onCuentaCoUnselected(Person person);
}
