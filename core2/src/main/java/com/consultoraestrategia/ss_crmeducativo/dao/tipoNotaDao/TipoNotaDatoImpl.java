package com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public class TipoNotaDatoImpl extends BaseDaoImpl<TipoNotaC, TipoNotaC_Table> implements TipoNotaDao {

    private static TipoNotaDatoImpl mInstance;
    private ValorTipoNotaDao valorTipoNotaDao;
    private EscalaEvaluacionDao escalaEvaluacionDao;

    public TipoNotaDatoImpl(ValorTipoNotaDao valorTipoNotaDao, EscalaEvaluacionDao escalaEvaluacionDao) {
        this.valorTipoNotaDao = valorTipoNotaDao;
        this.escalaEvaluacionDao = escalaEvaluacionDao;
    }

    public static TipoNotaDatoImpl getInstance(ValorTipoNotaDao valorTipoNotaDao, EscalaEvaluacionDao escalaEvaluacionDao) {
        if (mInstance == null) {
            mInstance = new TipoNotaDatoImpl(valorTipoNotaDao, escalaEvaluacionDao);
        }
        return mInstance;
    }

    @Override
    public TipoNotaC get(String id) {
        TipoNotaC tipoNota = super.get(id);
        if(tipoNota!=null)tipoNota.setValorTipoNotaList(valorTipoNotaDao.getListPorTipoNotaId(tipoNota.getTipoNotaId()));
        return tipoNota;
    }

    @Override
    protected Class<TipoNotaC> getEntityClass() {
        return TipoNotaC.class;
    }

    @Override
    protected Class<TipoNotaC_Table> getTableclass() {
        return TipoNotaC_Table.class;
    }

    private TipoNotaC getPorTipoId(int tipoId) {
        return getWithQuery(TipoNotaC_Table.tipoId.eq(tipoId));
    }

    @Override
    public TipoNotaC getValorNumerico() {
        return getPorTipoId(TipoNotaC.VALOR_NUMERICO);
    }

    @Override
    public TipoNotaC getSelectorNumerico() {
        return getPorTipoId(TipoNotaC.SELECTOR_NUMERICO);
    }

    @Override
    public TipoNotaC getSelectorValores() {
        return getPorTipoId(TipoNotaC.SELECTOR_VALORES);
    }

    @Override
    public TipoNotaC getSelectorIconos() {
        return getPorTipoId(TipoNotaC.SELECTOR_ICONOS);
    }

    @Override
    public TipoNotaC getMyTipoNota(String key) {
        TipoNotaC tipoNota = SQLite.select()
                .from(TipoNotaC.class)
                .where(TipoNotaC_Table.key.eq(key))
                .querySingle();
        return tipoNota;
    }

    @Override
    public List<TipoNotaC> getTipoNotaConValores() {
        List<TipoNotaC> tipoNotaList = getAll();
        llenarTipoNotaConValorTipoNota(tipoNotaList);
        return tipoNotaList;
    }

    private void llenarTipoNotaConValorTipoNota(List<TipoNotaC> tipoNotaList) {
        for (TipoNotaC tipoNota :
                tipoNotaList) {
            List<ValorTipoNotaC> valorTipoNotaList = valorTipoNotaDao.getListPorTipoNotaId(tipoNota.getTipoNotaId());
            tipoNota.setValorTipoNotaList(valorTipoNotaList);
        }
    }

    private void llenarTipoNotaConValorTipoNotaEscala(List<TipoNotaC> tipoNotaList) {
        for (TipoNotaC tipoNota :
                tipoNotaList) {
            List<ValorTipoNotaC> valorTipoNotaList = valorTipoNotaDao.getListPorTipoNotaId(tipoNota.getTipoNotaId());
            tipoNota.setValorTipoNotaList(valorTipoNotaList);
            tipoNota.setEscalaEvaluacion(escalaEvaluacionDao.getEscalaEvalPorTipoNota(tipoNota.key));
        }
    }

    private void llenarTipoNotaConValorTipoNota(TipoNotaC tipoNota) {
        if (tipoNota == null) return;
        tipoNota.setValorTipoNotaList(SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.eq(tipoNota.getKey()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico.desc())
                .queryList());
    }

    @Override
    public List<TipoNotaC> getTipoNotaListSelectores() {
        List<TipoNotaC> tipoNotaList =
                SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                        .from(TipoNotaC.class)
                        .innerJoin(Tipos.class)
                        .on(TipoNotaC_Table.tipoId.withTable()
                                .eq(Tipos_Table.tipoId.withTable()))
                        .where(Tipos_Table.tipoId.withTable().in(TipoNotaC.SELECTOR_ICONOS).and(TipoNotaC.SELECTOR_NUMERICO).and(TipoNotaC.SELECTOR_VALORES))
                        .queryList();
        llenarTipoNotaConValorTipoNota(tipoNotaList);
        return tipoNotaList;
    }

    @Override
    public TipoNotaC getTipoNotaConValores(String tipoNotaId) {
        TipoNotaC tipoNotaList = get(tipoNotaId);
        llenarTipoNotaConValorTipoNota(tipoNotaList);
        return tipoNotaList;
    }

    @Override
    public List<TipoNotaC> getListPorRubros(List<String> rubroEvaluacionKeyList) {
        List<TipoNotaC> tipoNotaList = SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(TipoNotaC.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(TipoNotaC_Table.key.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().in(rubroEvaluacionKeyList))
                .groupBy(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        llenarTipoNotaConValorTipoNota(tipoNotaList);
       return tipoNotaList;
    }

    @Override
    public List<TipoNotaC> getListPorRubrosEscala(List<String> rubroEvalProcesoKeyList) {
        Log.d(getClass().getSimpleName(), "rubroEvalProcesoKeyList: " + rubroEvalProcesoKeyList.toString());
        List<TipoNotaC> tipoNotaList = SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(TipoNotaC.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(TipoNotaC_Table.key.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().in(rubroEvalProcesoKeyList))
                .groupBy(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
        llenarTipoNotaConValorTipoNotaEscala(tipoNotaList);
        return tipoNotaList;
    }

    @Override
    public TipoNotaC getValorAsistencia(int programaEducativoId) {
        Log.d(getClass().getSimpleName(), "programaEducativoId: " + programaEducativoId);
        TipoNotaC tipoNotaC= SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(TipoNotaC.class)
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(TipoNotaC_Table.tipoId.withTable())).
                        innerJoin(RelProgramaEducativoTipoNota.class)
                .on(RelProgramaEducativoTipoNota_Table.tipoNotaId.withTable()
                        .eq(TipoNotaC_Table.tipoNotaId.withTable()))
                .where(Tipos_Table.tipoId.withTable().eq(TipoNotaC.VALOR_ASISTENCIA))
                .and(RelProgramaEducativoTipoNota_Table.programaEducativoId.withTable()
                        .eq(programaEducativoId))
                .and(RelProgramaEducativoTipoNota_Table.estado.withTable().eq(true))
                .querySingle();

    return tipoNotaC;
    }

    @Override
    public boolean validarTipoNota(String valorTipoNotaId) {
        ValorTipoNotaC valorTipoNotaC = SQLite.select(ValorTipoNotaC_Table.ALL_COLUMN_PROPERTIES)
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.valorTipoNotaId.eq(valorTipoNotaId))
                .querySingle();

        if (valorTipoNotaC.getLimiteInferior()==0 && valorTipoNotaC.getLimiteSuperior()==0)return false;
        else return true;
    }

    @Override
    public boolean validarTipoNotas(String tipoNotaId) {
        TipoNotaC tipoNotaC= SQLite.select(TipoNotaC_Table.ALL_COLUMN_PROPERTIES)
                .from(TipoNotaC.class)
                .where(TipoNotaC_Table.tipoNotaId.withTable().is(tipoNotaId))
                .querySingle();

        if (tipoNotaC.isIntervalo())return true;
        else return false;
    }

    @Override
    public List<TipoNotaC> getTipoNotaList(int programaEducativoId, List<Integer> tipo){

        IProperty[] parametros = Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES);
        Select select = SQLite.select(parametros);

        From<TipoNotaC> from = select.from(TipoNotaC.class)
                .innerJoin(Tipos.class)
                .on(TipoNotaC_Table.tipoId.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(RelProgramaEducativoTipoNota.class)
                .on(TipoNotaC_Table.key.withTable()
                        .eq(RelProgramaEducativoTipoNota_Table.tipoNotaId.withTable()));


        Where<TipoNotaC> where = from.where(Tipos_Table.tipoId.withTable().in(tipo));

        if(programaEducativoId > 0){
            where.and(RelProgramaEducativoTipoNota_Table.programaEducativoId.withTable().is(programaEducativoId));
        }

        List<TipoNotaC> tipoNotas =
                where.orderBy(TipoNotaC_Table.fechaCreacion.withTable().asc())
                        .and(RelProgramaEducativoTipoNota_Table.estado.eq(true))
                        .groupBy(parametros)
                        .queryList();

        SessionUser sessionUser = SessionUser.getCurrentUser();
        if(sessionUser != null){
            tipoNotas.addAll(SQLite.select(parametros)
                    .from(TipoNotaC.class)
                    .innerJoin(Tipos.class)
                    .on(TipoNotaC_Table.tipoId.withTable()
                            .eq(Tipos_Table.tipoId.withTable()))
                    .where(Tipos_Table.tipoId.withTable().in(tipo))
                    .and(TipoNotaC_Table.usuarioCreacionId.is(sessionUser.getUserId()))
                    .orderBy(TipoNotaC_Table.fechaCreacion.asc())
                    .queryList());
        }

        for (TipoNotaC tipoNotaC: tipoNotas){
            tipoNotaC.setValorTipoNotaList(SQLite.select()
                    .from(ValorTipoNotaC.class)
                    .where(ValorTipoNotaC_Table.tipoNotaId.eq(tipoNotaC.getKey()))
                    .orderBy(ValorTipoNotaC_Table.valorNumerico.desc())
                    .queryList());
        }

        //Clase anónima
        /*Collections.sort(tipoNotas, new Comparator<TipoNotaC>() {
            @Override
            public int compare(TipoNotaC o1, TipoNotaC o2) {
                int result = 0;
                try {
                    if (o1.getValorTipoNotaList().size() < o2.getValorTipoNotaList().size()) {
                        result = -1;
                    }
                    if (o1.getValorTipoNotaList().size() > o2.getValorTipoNotaList().size()) {
                        result = 1;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return result;
            }
        });*/

        Collections.sort(tipoNotas, new Comparator<TipoNotaC>() {
            @Override
            public int compare(TipoNotaC o1, TipoNotaC o2) {
                if(o1.getValorTipoNotaList()==null|| o2.getValorTipoNotaList()==null){
                    return 0;
                }
                else{
                    String a=String.valueOf(o1.getValorTipoNotaList().size());
                    String b=String.valueOf(o2.getValorTipoNotaList().size());
                    return b.compareTo(a);
                }
            }
        });

        return tipoNotas;
    }

}
