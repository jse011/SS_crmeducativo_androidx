package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso.TareaRubroEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.OrigenUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 20/03/2018.
 */

public class RubricaBidLocal implements RubricaBidDataSource {

    public static final String TAG = RubricaBidLocal.class.getSimpleName();

    private RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;
    private RubroProcesoDao rubroProcesoDao;
    private TareaRubroEvaluacionProcesoDao tareaRubroEvaluacionProcesoDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;

    public RubricaBidLocal(RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao, RubroProcesoDao rubroProcesoDao, TareaRubroEvaluacionProcesoDao tareaRubroEvaluacionProcesoDao, CalendarioPeriodoDao calendarioPeriodoDao) {
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
        this.rubroProcesoDao = rubroProcesoDao;
        this.tareaRubroEvaluacionProcesoDao = tareaRubroEvaluacionProcesoDao;
        this.calendarioPeriodoDao = calendarioPeriodoDao;
    }

    @Override
    public void getRubricaBidItem(int rubroEvalBidId, int countIndicador, CallBackRubricaBid callBackRubricaBid) {
        RubroEvaluacionProcesoC proceso = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.androidId.is(rubroEvalBidId))
                .querySingle();

        RubBidUi rubBidUi = null;
        if (proceso != null) {
            int formaEvaluacionId = proceso.getFormaEvaluacionId();
            int tipoEvaluacionId = proceso.getTipoEvaluacionId();
            Tipos tipoFormulacion = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.is(formaEvaluacionId))
                    .querySingle();
            T_RN_MAE_TIPO_EVALUACION tipoEval = SQLite.select()
                    .from(T_RN_MAE_TIPO_EVALUACION.class)
                    .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.is(tipoEvaluacionId))
                    .querySingle();


            TipoUi formaEvaluacion = new TipoUi();
            formaEvaluacion.setId(formaEvaluacionId);
            if(tipoFormulacion!=null)formaEvaluacion.setTitle(tipoFormulacion.getNombre());

            TipoUi tipoEvaluacion = new TipoUi();
            tipoEvaluacion.setId(tipoEvaluacionId);
            if(tipoEval!=null)tipoEvaluacion.setTitle(tipoEval.getNombre());

            // rubBidUi = new RubBidUi();

            rubBidUi = new RubBidUi(proceso.getAndroidId(), proceso.getTitulo());
            // rubBidUi.setId(proceso.getAndroidId());
            // rubBidUi.setTitle(proceso.getTitulo());
            rubBidUi.setAlias(proceso.getSubtitulo());
            rubBidUi.setFormaEvaluacion(formaEvaluacion);
            rubBidUi.setTipoEvaluacion(tipoEvaluacion);

            if(rubBidUi.getSesionAprendizajeId()!=0){
                rubBidUi.setOrigenUi(OrigenUi.SESION);
            }else {
                rubBidUi.setOrigenUi(OrigenUi.AREA);
            }

            TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso = SQLite.select()
                    .from(TareaRubroEvaluacionProceso.class)
                    .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(proceso.getKey()))
                    .querySingle();

            if(tareaRubroEvaluacionProceso!=null){
                rubBidUi.setOrigenUi(OrigenUi.TAREA);
            }

            rubBidUi.setSesionAprendizajeId(proceso.getSesionAprendizajeId());
            rubBidUi.setFecha(Utils.f_fecha_letras(proceso.getFechaCreacion()));

            switch (proceso.getFormaEvaluacionId()) {
                case 477:
                    rubBidUi.setForma(RubBidUi.Forma.INDIVIDUAL);
                    break;
                case 478:
                    rubBidUi.setForma(RubBidUi.Forma.GRUPAL);
                    break;
            }
            //    rubBidUi.setRubroEvalProcesoList(listaRubroEvalProc(rubBidUi, proceso, countIndicador));

        }
        callBackRubricaBid.onActualizacionRubricaBid(rubBidUi);
    }

    @Override
    public void deleteRubricaBidItem(final RubBidUi rubBidUi, final CallBackEliminarRubricaBid callBackRubricaBid) {
        Log.d(TAG, "validacion : " + rubBidUi.toString());
        final String keyRubro = rubBidUi.getKey();
        final List<RubroEvalRNPFormulaC> procUiList = rnpFormulaCList(keyRubro);
        if (procUiList == null) return;

        rubroEvalRNPFormulaDao.rubroBidRelacionadosFormula(keyRubro, new BaseDaoImpl.Callback<List<RubroEvaluacionProcesoC>>() {
            @Override
            public void onSuccess(List<RubroEvaluacionProcesoC> result) {

                boolean validacion =  result.size() == 0;
                if (validacion) {
                    onActualizarRubricaPrincipal(keyRubro);
                    TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso = tareaRubroEvaluacionProcesoDao.getTareaRubroPorRubroId(keyRubro);
                    if(tareaRubroEvaluacionProceso!=null)tareaRubroEvaluacionProcesoDao.elimarTareaRubroEvaluacionProceso(keyRubro);
                    for (RubroEvalRNPFormulaC formulaC : procUiList) {
                        onActualizarRubrosEliminados(formulaC);
                    }
                    callBackRubricaBid.onEliminarRubricaBid(rubBidUi, RubricasAbstractPresenterImpl.REGISTRO_SUCCESS, null);
                    Log.d(TAG, "validacion : " + validacion);
                } else {
                    List<RubEvalProcUi> rubEvalProcUis = new ArrayList<>();
                    for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: result){
                        RubEvalProcUi rubEvalProcUi = new RubEvalProcUi();
                        rubEvalProcUi.setKey(rubroEvaluacionProcesoC.getKey());
                        rubEvalProcUi.setTitulo(rubroEvaluacionProcesoC.getTitulo());
                        rubEvalProcUis.add(rubEvalProcUi);
                    }
                    Log.d(TAG, "validacion : " + validacion);
                    callBackRubricaBid.onEliminarRubricaBid(rubBidUi, RubricasAbstractPresenterImpl.REGISTRO_MESSAGE,rubEvalProcUis);
                }
            }

            @Override
            public void onError(Throwable error) {
                callBackRubricaBid.onEliminarRubricaBid(rubBidUi, RubricasAbstractPresenterImpl.REGISTRO_ERROR, null);
            }
        });


         /* rubroProcesoDao.actualizarRubroEliminado(keyRubro, new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    List<RubroEvalRNPFormulaC> rnpFormulaCList = rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(rubBidUi.getKey());
                    if (rnpFormulaCList == null) return;
                    for (RubroEvalRNPFormulaC formulaC : rnpFormulaCList) {
                        //los hijos del rubro tienen ver si hay un padre con ellos
                        RubroEvaluacionProcesoC procesoC = rubroProcesoDao.getMyRegistro(formulaC.getRubroEvaluacionSecId());


                    }
                }
            }

            @Override
            public void onError(Throwable error) {
                callBackRubricaBid.onEliminarRubricaBid(rubBidUi, RubricasAbstractPresenterImpl.REGISTRO_ERROR);
            }
        });*/


    }

    private void onActualizarRubricaPrincipal(String keyRubro) {
        rubroProcesoDao.actualizarRubroEliminado(keyRubro, new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) Log.d(TAG, "resultRubro :" + result);
            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "resultError :" + error.getMessage());
            }
        });
    }

    private void onActualizarRubrosEliminados(RubroEvalRNPFormulaC formulaC) {
        rubroProcesoDao.actualizarRubroEliminado(formulaC.getRubroEvaluacionSecId(), new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) Log.d(TAG, "resultRubro :" + result);
            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "resultError :" + error.getMessage());
            }
        });
    }


    private List<RubroEvalRNPFormulaC> rnpFormulaCList(String keyRubro) {
        return rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(keyRubro);
    }

    @Override
    public boolean publicarEvaluacion(String rubricaEvaluacionId) {
        boolean success = false;
        try {
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubricaEvaluacionId))
                    .querySingle();

            if(rubroEvaluacionProcesoC!=null){
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();

                List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                        .queryList();

                for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
                    evaluacionProcesoC.setPublicado(1);
                    evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    evaluacionProcesoC.save();
                }

                success = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public PeriodoUi getPeriodo(int calendarioPeriodoId, int cargaCursoId) {
        PeriodoUi periodoUi = new PeriodoUi();
        try {

            CalendarioPeriodo calendarioPeriodo =  calendarioPeriodoDao.get(calendarioPeriodoId);

            Tipos tipo = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();
            String tipoNombre = "";
            if(tipo!=null)tipoNombre = tipo.getNombre();
            periodoUi.setTipoName(tipoNombre);
            periodoUi.setIdCalendarioPeriodo(String.valueOf(calendarioPeriodo.getCalendarioPeriodoId()));

            boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(calendarioPeriodo.getCalendarioPeriodoId(), cargaCursoId, false, null);
            periodoUi.setVigente(isvigente);

            switch (calendarioPeriodo.getEstadoId()){
                case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                    periodoUi.setEditar(true);
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                    periodoUi.setEditar(isvigente);
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                    periodoUi.setEditar(isvigente);
                    break;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return periodoUi;
    }
}
