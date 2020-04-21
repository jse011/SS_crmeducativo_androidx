package com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.TipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaLocalDataSource implements TipoNotaDataSource {
    private String TAG = TipoNotaLocalDataSource.class.getSimpleName();

    @Override
    public void getTipoNota(int usuarioCreadorId, CallabackTipoNota callabackTipoNota) {
        Log.d(TAG,"TipoNotaLocalDataSource");
        List<TipoNotaUi> tipoNotaUiList = new ArrayList<>();
        List<TipoNotaC> tipoNotaList = SQLite.select()
                .from(TipoNotaC.class)
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable().eq(TipoNotaC_Table.tipoId.withTable()))
                //.where(Tipos_Table.tipoId.withTable().in(409, 412))
                //.and(TipoNota_Table.usuarioCreadorId.is(usuarioCreadorId))
                .queryList();
        int count = 0;
        for (TipoNotaC tipoNota : tipoNotaList) {
            count++;
            double valor = Double.parseDouble(tipoNota.getValorDefecto());
            /*tipoNotaUiList.add(new TipoNotaUi(count,
                    tipoNota.getNombre(),
                    tipoNota.getTipoNotaId()+"" ,
                    tipoNota.getTipoNotaId(),
                    tipoNota.getTipoId(),
                    tipoNota.getValorMaximo(),
                    valor,
            null));*/
        }
        callabackTipoNota.onListTipoNota(tipoNotaUiList);

    }
}
