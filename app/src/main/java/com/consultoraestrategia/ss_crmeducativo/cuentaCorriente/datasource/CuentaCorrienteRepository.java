package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource;


import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetAniosAcademicosUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetCuentasCoUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetPersonaCallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.local.CuentaCorrienteLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.remote.CuentaCorrienteRemoteDataSource;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CuentaCorrienteRepository implements CuentaCorrienteDataSource {

    public static final String TAG = CuentaCorrienteRepository.class.getSimpleName();

    public static CuentaCorrienteRepository INSTANCE = null;
    private CuentaCorrienteLocalDataSource localDataSource;
    private CuentaCorrienteRemoteDataSource remoteDataSource;

    public static CuentaCorrienteRepository getInstance(CuentaCorrienteLocalDataSource localDataSource, CuentaCorrienteRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CuentaCorrienteRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private CuentaCorrienteRepository(CuentaCorrienteLocalDataSource localDataSource, CuentaCorrienteRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void getPersonas(int idPersona, GetPersonaCallback callback) {
        localDataSource.getPersonas(idPersona, callback);

    }

    @Override
    public void getCuentaCorrienteList(int idPersona, String anioSelected, GetCuentasCoUICallback callback) {
        localDataSource.getCuentaCorrienteList(idPersona, anioSelected, callback);

    }


    @Override
    public void getAniosAcademicosList(int idPersona, GetAniosAcademicosUICallback callback) {
        localDataSource.getAniosAcademicosList(idPersona, callback);

    }
}
