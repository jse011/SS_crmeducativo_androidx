package com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public class ValorTipoNotaDaoImpl extends BaseDaoImpl<ValorTipoNotaC, ValorTipoNotaC_Table> implements ValorTipoNotaDao {


    private static ValorTipoNotaDao mInstance;

    private ValorTipoNotaDaoImpl() {
    }

    public static ValorTipoNotaDao getInstance() {
        if (mInstance == null) {
            mInstance = new ValorTipoNotaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<ValorTipoNotaC> getEntityClass() {
        return ValorTipoNotaC.class;
    }

    @Override
    protected Class<ValorTipoNotaC_Table> getTableclass() {
        return ValorTipoNotaC_Table.class;
    }


    @Override
    public List<ValorTipoNotaC> getListPorTipoNotaId(String tipoNotaId) {
        return SQLite
                .select()
                .from(getEntityClass())
                .where(ValorTipoNotaC_Table.tipoNotaId.eq(tipoNotaId))
                .orderBy(ValorTipoNotaC_Table.valorNumerico, false)
                .queryList();
    }

    @Override
    public List<ValorTipoNotaC> getListValorTipoNotaAsistencia() {
        return SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.estadoId.is(285))
                .or(ValorTipoNotaC_Table.estadoId.is(286))
                .or(ValorTipoNotaC_Table.estadoId.is(287))
                .or(ValorTipoNotaC_Table.estadoId.is(288))
                .or(ValorTipoNotaC_Table.estadoId.is(289))
                .orderBy(ValorTipoNotaC_Table.valorNumerico,false)
                .queryList();

    }

    @Override
    public List<ValorTipoNotaC> getListPorRubros(List<String> rubroEvaluacionKeyList) {
        return SQLite.select(Utils.f_allcolumnTable(ValorTipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(ValorTipoNotaC.class)
                .innerJoin(TipoNotaC.class)
                .on(ValorTipoNotaC_Table.tipoNotaId.withTable()
                        .eq(TipoNotaC_Table.key.withTable()))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(TipoNotaC_Table.key.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico,false)
                .queryList();
    }

    @Override
    public ValorTipoNotaC getValorTipoNotaPorTipoNota(String valorTipoNotaTipoId, int estadoValorTipoNota) {
        return SQLite
                .select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.eq(valorTipoNotaTipoId))
                .and(ValorTipoNotaC_Table.estadoId.eq(estadoValorTipoNota))
                .querySingle();
    }

    @Override
    public ValorTipoNotaC getValorTipoNotaPorId(String valorTipoNotaTipoId) {
        return  SQLite
                .select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.valorTipoNotaId.eq(valorTipoNotaTipoId))
                .querySingle();
    }


}
