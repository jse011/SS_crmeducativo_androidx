package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula;



import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 30/04/2018.
 */

public class RubroEvalRNPFormulaDaoImpl extends BaseDaoImpl<RubroEvalRNPFormulaC, RubroEvalRNPFormulaC_Table> implements RubroEvalRNPFormulaDao {

    private static RubroEvalRNPFormulaDao mInstance;

    public RubroEvalRNPFormulaDaoImpl() {
    }

    public static RubroEvalRNPFormulaDao getInstance() {
        if (mInstance == null) {
            mInstance = new RubroEvalRNPFormulaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<RubroEvalRNPFormulaC> getEntityClass() {
        return RubroEvalRNPFormulaC.class;
    }

    @Override
    protected Class<RubroEvalRNPFormulaC_Table> getTableclass() {
        return RubroEvalRNPFormulaC_Table.class;
    }

    @Override
    public List<RubroEvalRNPFormulaC> getListaRubroEvalRNPFormula(String key) {
        return SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(key))
                .queryList();
    }



    @Override
    public void rubroBidRelacionadosFormula(String keyRubro, BaseDaoImpl.Callback<List<RubroEvaluacionProcesoC>> result) {

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select(RubroEvaluacionProcesoC_Table.key.withTable())
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(keyRubro))
                .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(280))
                .queryList();

        List<String> rubroEvalProcesoIdList = new ArrayList<>();
        for(RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList)rubroEvalProcesoIdList.add(itemRubroEvaluacionProcesoC.getKey());

        List<RubroEvaluacionProcesoC> rubroFormulaList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubroEvalProcesoIdList))
                .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(280))
                .queryList();

        result.onSuccess(rubroFormulaList);
    }


    @Override
    public List<RubroEvaluacionProcesoC> getListaRubroFormula(String key) {
        return SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(key))
                .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                .and(RubroEvaluacionProcesoC_Table.formaEvaluacionId.eq(0))
                .queryList();
    }

    @Override
    public List<RubroEvalRNPFormulaC> getListaRubroEvalRNPFormula(List<String> rubroProcesoEvalaucionIdList) {
        return SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().in(rubroProcesoEvalaucionIdList))
                .queryList();
    }


}
