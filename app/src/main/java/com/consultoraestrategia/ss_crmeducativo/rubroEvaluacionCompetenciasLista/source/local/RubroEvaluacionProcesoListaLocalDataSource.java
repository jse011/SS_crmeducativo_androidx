package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class RubroEvaluacionProcesoListaLocalDataSource implements RubroEvaluacionProcesoListaDataSource {
    private static final String TAG = RubroEvaluacionProcesoListaLocalDataSource.class.getSimpleName();

    @Override
    public void getCompetenciaList(ArrayList<Integer> competenciaIdList, int sesionAprendizajeId, int nivel, ListCallback<CompetenciaUi> callback) {
        List<CompetenciaUi> items = new ArrayList<>();

        List<Competencia> competencias = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build())
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable()))
                .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        int posicionCompetencia = 0;
        for (Competencia itemCompetencia : competencias) {
            posicionCompetencia++;
            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(itemCompetencia.getCompetenciaId());
            competenciaUi.setPosicion(posicionCompetencia);
            competenciaUi.setTitulo(itemCompetencia.getNombre());
            items.add(competenciaUi);
            /*
            if(competenciaIdList == null)continue;
             int posicionId = competenciaIdList.indexOf(itemCompetencia.getCompetenciaId());
             if(posicionId != -1){
                 competenciaUi.setChecked(true);
             }*/
        }
        callback.onLoaded(items);
    }
}
