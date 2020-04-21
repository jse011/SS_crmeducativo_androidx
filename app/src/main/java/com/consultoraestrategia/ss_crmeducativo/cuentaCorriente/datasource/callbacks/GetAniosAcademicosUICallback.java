package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks;


import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetAniosAcademicosUICallback {
    void onAniosAcademicosUILoaded(List<String> anioAcademicoList);

    void onError(String error);
}
