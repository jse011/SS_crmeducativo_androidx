package com.consultoraestrategia.ss_crmeducativo.asyntasks;

/**
 * Created by irvinmarin on 28/04/2017.
 */

public interface GetDatosInterface {

    void GetDatosCorrecto(String mensaje);

    void GetDatosError(String mensaje);

    void GetDatosErrorProcedimiento(String mensaje);

    void onProgressInformationChanged(int informationType);
}
