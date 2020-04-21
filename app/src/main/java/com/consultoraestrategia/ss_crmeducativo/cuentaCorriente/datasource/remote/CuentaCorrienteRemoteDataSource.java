package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.remote;


import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetAniosAcademicosUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetCuentasCoUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetPersonaCallback;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CuentaCorrienteRemoteDataSource implements CuentaCorrienteDataSource {


    public CuentaCorrienteRemoteDataSource() {
    }


    @Override
    public void getPersonas(int idPersona, GetPersonaCallback callback) {

    }

    @Override
    public void getCuentaCorrienteList(int idPersona, String anioSelected, GetCuentasCoUICallback callback) {

    }



    @Override
    public void getAniosAcademicosList(int idPersona, GetAniosAcademicosUICallback callback) {

    }
}
