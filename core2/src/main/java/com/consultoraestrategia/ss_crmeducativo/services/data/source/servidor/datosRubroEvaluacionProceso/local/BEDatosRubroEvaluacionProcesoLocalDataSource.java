package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosRubroEvaluacionProcesoLocalDataSource extends ServiceLocalDataSource<BEDatosRubroEvaluacionProceso> implements BEDatosRubroEvaluacionProcesoDataSource {
    EvaluacionProcesoDao evaluacionProcesoDao;
    RubroProcesoDao rubroProcesoDao;

    public BEDatosRubroEvaluacionProcesoLocalDataSource(EvaluacionProcesoDao evaluacionProcesoDao, RubroProcesoDao rubroProcesoDao) {
        this.evaluacionProcesoDao = evaluacionProcesoDao;
        this.rubroProcesoDao = rubroProcesoDao;
    }

    @Override
    public void getDatosExportar(final ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {
        getRubroEvalDatosExportar(null, callBack);
    }


    @Override
    public void getSimpleDatosExportar(ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {
        getDatosExportar(callBack);
    }

    @Override
    public void getRubroEvalDatosExportar(List<String> rubroEvalIdList, ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {
        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();

        //#region RubroEvaluacionProcesoC

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList;
        if(rubroEvalIdList == null){
            rubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoC.class);
        }else {
            rubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(RubroEvaluacionProcesoC.class,
                    RubroEvaluacionProcesoC_Table.key.in(rubroEvalIdList));
        }

        beDatosRubroEvaluacionProceso.setRubroEvaluacionProceso(rubroEvaluacionProcesoList);
        //#endregion RubroEvaluacionProcesoC

        List<String> rubroEvaluacionProcesoKey = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso()){
            rubroEvaluacionProcesoKey.add(rubroEvaluacionProcesoC.getKey());
        }
        //#region RubroEvalRNPFormulaC
        beDatosRubroEvaluacionProceso.setRubroEvalProcesoFormula(
                ConsultaUtils.getChangeItemsTableChild(RubroEvalRNPFormulaC.class,
                        RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionProcesoKey)));
        //#endregion RubroEvaluacionProcesoC

        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC: beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula()){
            String key = rubroEvalRNPFormulaC.getRubroEvaluacionSecId();
            if(rubroEvaluacionProcesoKey.indexOf(key) == -1){
                rubroEvaluacionProcesoKey.add(key);
                //#region RubroEvaluacionProcesoC Secundario
                rubroEvaluacionProcesoList.add(
                        ConsultaUtils.getChangeItemTableChild(
                                RubroEvaluacionProcesoC.class,
                                T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.is(key)));
                //#endregion RubroEvaluacionProcesoC Secundario
            }
        }

        //#region EvaluacionProcesoC
        beDatosRubroEvaluacionProceso.setEvaluacionProceso(
                ConsultaUtils.getChangeItemsTableChild(
                        EvaluacionProcesoC.class, EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion EvaluacionProcesoC

        //#region EquipoEvaluacionProcesoC
        beDatosRubroEvaluacionProceso.setObtenerEquipoEvaluacionProceso(
                ConsultaUtils.getChangeItemsTableChild(
                        EquipoEvaluacionProcesoC.class, EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion EquipoEvaluacionProcesoC

        //#region RubroEvaluacionProcesoCampotematicoC
        beDatosRubroEvaluacionProceso.setRubro_evaluacion_proceso_campotematico(ConsultaUtils.getChangeItemsTableChild(RubroEvaluacionProcesoCampotematicoC.class, RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion RubroEvaluacionProcesoCampotematicoC

        //#region CriterioRubroEvaluacionC
        beDatosRubroEvaluacionProceso.setObtenerCriterioRubroEvaluacionProceso(ConsultaUtils.getChangeItemsTableChild(CriterioRubroEvaluacionC.class, CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion CriterioRubroEvaluacionC

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoEquipo(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC

        List<String> equipoRubroEvaluacionProcesoKey = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc: beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()){
            equipoRubroEvaluacionProcesoKey.add(procesoEquipoc.getKey());
        }

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoIntegrante(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(equipoRubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC

        callBack.onResponse(true,beDatosRubroEvaluacionProceso);
    }

    @Override
    public void onUpdateEvaluacionFormula(SuccessCallBack callBack) {

        try {
            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                    .queryList();
            List<String> rubrosAsociadosIdList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList)rubrosAsociadosIdList.add(evaluacionProcesoC.getRubroEvalProcesoId());

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"size: " + evaluacionProcesoCList.size());

            IProperty[] parametros = Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES);
            List<RubroEvalRNPFormulaC> rubroFormulaList = SQLite.select(parametros)
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .groupBy(parametros)
                    .queryList();
            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList query: " + SQLite.select(RubroEvalRNPFormulaC_Table.key.withTable())
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .getQuery());
            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList size: " + rubroFormulaList.size());

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList: " + rubroFormulaList.size());

            List<String> listIdRubrosActualizados = new ArrayList<>();
            for (EvaluacionProcesoC itemEvaluacionProcesoC: evaluacionProcesoCList){
                RubroEvalRNPFormulaC rubroEvalRNPFormulaC = null;
                for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC: rubroFormulaList){
                    if(itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId())){
                        rubroEvalRNPFormulaC = itemRubroEvalRNPFormulaC;
                        break;
                    }
                }
                if(rubroEvalRNPFormulaC != null){

                    boolean success = evaluacionProcesoDao.evaluarRubroFormulaPersona(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId(),itemEvaluacionProcesoC.getAlumnoId());
                    if(success){
                        //Log.d(EvaluacionFormulaLocal.class.getSimpleName(),"success: " + success);
                        int poscion = listIdRubrosActualizados.indexOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                        if(poscion!=-1)listIdRubrosActualizados.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                    }
                }
                itemEvaluacionProcesoC.setFormulaSinc(false);
                itemEvaluacionProcesoC.save();
            }

            for (String itemId: listIdRubrosActualizados){
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroProcesoDao.get(itemId);
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();
            }

            callBack.onResponse(true);
        }catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }
    }


}
