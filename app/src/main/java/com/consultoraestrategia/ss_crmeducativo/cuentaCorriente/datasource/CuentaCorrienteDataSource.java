package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource;


import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetAniosAcademicosUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetCuentasCoUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetPersonaCallback;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public interface CuentaCorrienteDataSource {
    void getPersonas(int idPersona, GetPersonaCallback callback);

    void getCuentaCorrienteList(int idPersona, String anioSelected, GetCuentasCoUICallback callback);

    void getAniosAcademicosList(int idPersona, GetAniosAcademicosUICallback callback);
}
