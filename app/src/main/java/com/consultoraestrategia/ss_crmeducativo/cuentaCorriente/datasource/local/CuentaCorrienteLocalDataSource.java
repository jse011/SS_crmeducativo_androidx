package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.local;

import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetAniosAcademicosUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetCuentasCoUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetPersonaCallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CuentaCorriente;
import com.consultoraestrategia.ss_crmeducativo.entities.CuentaCorriente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CuentaCorrienteLocalDataSource implements CuentaCorrienteDataSource {

    private static final String TAG = CuentaCorrienteLocalDataSource.class.getSimpleName();

    public CuentaCorrienteLocalDataSource() {
    }

    @Override
    public void getPersonas(int idPersona, GetPersonaCallback callback) {


        Log.d(TAG, "getPersonas idPersona: " + idPersona);

        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.is(idPersona))
                .querySingle();
        if (persona != null) callback.onPersonaLoaded(persona);
        else callback.onError("No se a encontrado a la persona");
    }

    @Override
    public void getCuentaCorrienteList(int idPersona, String anioSelected, GetCuentasCoUICallback callback) {

        List<CuentaCorriente> cuentaCorrientes = SQLite.select()
                .from(CuentaCorriente.class)
                .where(CuentaCorriente_Table.personaId.is(idPersona))
                .and(CuentaCorriente_Table.anio.is(Integer.parseInt(anioSelected)))
                .queryList();

        List<CuentaCoUI> cuentaCoUIList = new ArrayList<>();

        if (cuentaCorrientes.size() > 0) {
            for (CuentaCorriente cuenta : cuentaCorrientes) {
                cuentaCoUIList.add(new CuentaCoUI(
                        cuenta.getCtaCorrienteId(),
                        cuenta.getGlosa(),
                        cuenta.getFecha(),
                        cuenta.getDebito(),
                        cuenta.getCredito()
                ));
            }

            double totalCredito = 0;
            double totalDebito = 0;
            double restante = 0;

            for (CuentaCorriente cuenta : cuentaCorrientes) {
                if (cuenta.getCredito() != 0) {
                    totalCredito = totalCredito + cuenta.getCredito();
                }
                if (cuenta.getDebito() != 0) {
                    totalDebito = totalDebito + cuenta.getDebito();
                }
            }

            restante = totalDebito - totalCredito;

            Log.d(TAG, "cuentaCoUIList object: " + cuentaCoUIList.toString());
            Log.d(TAG, "totalDebito: " + totalDebito);
            Log.d(TAG, "totalCredito: " + totalCredito);
            Log.d(TAG, "restante: " + restante);
            callback.onCuentasCoUILoaded(restante, totalCredito, totalDebito, cuentaCoUIList);
        } else {
            callback.onError("No tiene Ingresos en este Año");
        }

    }

    @Override
    public void getAniosAcademicosList(int idPersona, GetAniosAcademicosUICallback callback) {
        List<String> aniosAcademicosList = new ArrayList<>();
        List<AnioAcademico> anioAcademicos = SQLite.select()
                .from(AnioAcademico.class)
                .queryList();
        for (AnioAcademico anioAcademico : anioAcademicos) {
            aniosAcademicosList.add(anioAcademico.getNombre());
        }
        callback.onAniosAcademicosUILoaded(aniosAcademicosList);
    }
}
