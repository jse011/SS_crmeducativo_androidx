package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.InfoCriterioEvalDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;


public class InfoCriteriosEvalLocalDataSource implements InfoCriterioEvalDataSource {

    public InfoCriteriosEvalLocalDataSource() {
    }

    @Override
    public void getInformacionIndicador(int indicadorId, Callback<IndicadorUi> callback) {

        Log.d("idIcd", " "+indicadorId);
        try {
            Icds icds = SQLite.select()
                    .from(Icds.class)
                    .where(Icds_Table.icdId.eq(indicadorId))
                    .querySingle();

            DesempenioIcd desempenioIcd = SQLite.select()
                    .from(DesempenioIcd.class)
                    .where(DesempenioIcd_Table.icdId.withTable().eq(icds.getIcdId()))
                    .querySingle();
            if (icds != null) {
                IndicadorUi indicadorUi = new IndicadorUi();
                indicadorUi.setDescripcion(desempenioIcd.getDescripcion());
                indicadorUi.setSubtitulo(icds.getAlias());
                indicadorUi.setTitulo(icds.getTitulo());
                indicadorUi.setUrl(desempenioIcd.getUrl());
                switch (icds.getTipoId()){
                    case Icds.TIPO_HACER:
                        indicadorUi.setTipoIndicador(TipoIndicadorUi.HACER);
                        break;
                    case Icds.TIPO_SABER:
                        indicadorUi.setTipoIndicador(TipoIndicadorUi.SABER);
                        break;
                    case Icds.TIPO_SER:
                        indicadorUi.setTipoIndicador(TipoIndicadorUi.SER);
                        break;
                    default:
                        indicadorUi.setTipoIndicador(TipoIndicadorUi.DEFAULT);
                        break;
                }

                callback.onLoad(true, indicadorUi);
            }

        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(false, null);
        }
    }
}
