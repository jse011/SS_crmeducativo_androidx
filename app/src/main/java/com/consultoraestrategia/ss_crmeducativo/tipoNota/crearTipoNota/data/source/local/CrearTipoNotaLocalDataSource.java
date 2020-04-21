package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class CrearTipoNotaLocalDataSource implements CrearTipoNotaDataSource {
    private String TAG= CrearTipoNotaLocalDataSource.class.getSimpleName();
    @Override
    public void getTipoSelector(int usuarioCreadorId,Callback<List<TipoSelectorUi>> callback) {

        List<Tipos> tiposList = SQLite.select(Tipos_Table.tipoId.withTable(),
                Tipos_Table.nombre.withTable())
                .from(Tipos.class)
                .innerJoin(TipoNotaC.class)
                .on(TipoNotaC_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                .where(TipoNotaC_Table.usuarioCreacionId.is(usuarioCreadorId))
                .queryList();
        List<TipoSelectorUi> tipoSelectorUiList = new ArrayList<>();
        tipoSelectorUiList.add(new TipoSelectorUi(0, "Seleccione"));
        for (Tipos tipos : tiposList){
            Log.d(TAG,"tipos " +tipos.getTipoId() + "/ Nombre ; "+ tipos.getNombre());
            tipoSelectorUiList.add(new TipoSelectorUi(tipos.getTipoId(),tipos.getNombre()));
        }
        callback.onLoaded(tipoSelectorUiList);
    }

    @Override
    public void getTipoEscalaEvaluacion(int usuarioCreadorId,Callback<List<TipoEscalaEvaluacionUi>> callback) {
        List<EscalaEvaluacion> tiposList = SQLite.select()
                .from(EscalaEvaluacion.class)
                .queryList();

        List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUiList = new ArrayList<>();
        tipoEscalaEvaluacionUiList.add(new TipoEscalaEvaluacionUi(0, "Seleccione"));
        for (EscalaEvaluacion escalaEvaluacion : tiposList) {
            tipoEscalaEvaluacionUiList.add(new TipoEscalaEvaluacionUi(escalaEvaluacion.getEscalaEvaluacionId(),
                    escalaEvaluacion.getNombre()));
        }
        callback.onLoaded(tipoEscalaEvaluacionUiList);
    }
}
