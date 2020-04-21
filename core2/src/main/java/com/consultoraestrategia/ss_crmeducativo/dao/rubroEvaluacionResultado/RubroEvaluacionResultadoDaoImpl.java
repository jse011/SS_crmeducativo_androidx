package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado;


import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kike on 22/05/2018.
 */

public class RubroEvaluacionResultadoDaoImpl extends BaseDaoImpl<RubroEvaluacionResultado, RubroEvaluacionResultado_Table> implements RubroEvaluacionResultadoDao {
    public static final String TAG = RubroEvaluacionResultadoDaoImpl.class.getSimpleName();
    private static RubroEvaluacionResultadoDao mInstance;
    public static RubroEvaluacionResultadoDao getInstance() {
        if (mInstance == null) {
            mInstance = new RubroEvaluacionResultadoDaoImpl();
        }
        return mInstance;
    }
    @Override
    protected Class<RubroEvaluacionResultado> getEntityClass() {
        return RubroEvaluacionResultado.class;
    }

    @Override
    protected Class<RubroEvaluacionResultado_Table> getTableclass() {
        return RubroEvaluacionResultado_Table.class;
    }

    @Override
    public void actualizarResultadodoAncla(int compentenciaId, int calendarioPeriodoId, int silaboEventoId, String rubroProcesoKey, int anclado) {
        Log.d(TAG, "actualizarResultadodoAncla " + rubroProcesoKey + " anclado "+ anclado+ " compentenciaId " +compentenciaId +" silaboEventoId "+ silaboEventoId +" calendarioPeriodoId "+calendarioPeriodoId);

        try {

            RubroEvaluacionResultado rubroEvaluacionResultado= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(compentenciaId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                    .querySingle();

            if(rubroEvaluacionResultado!=null){

                if(anclado==RubroEvaluacionResultado.ACTUALIZADO){

                    //#region volver a 0 la RubrosResultado
                    List<EvaluacionResultadoC> evaluacionResultadoList = SQLite.select()
                            .from(EvaluacionResultadoC.class)
                            .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(rubroEvaluacionResultado.getRubroEvalResultadoId()))
                            .queryList();

                    for (EvaluacionResultadoC evaluacionResultado : evaluacionResultadoList){
                        evaluacionResultado.setNota(0f);
                        evaluacionResultado.setValorTipoNotaId("");
                        evaluacionResultado.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                        evaluacionResultado.save();
                    }
                    //#endregion volver a 0 la RubrosResultado

                    Competencia competencia = SQLite.select()
                            .from(Competencia.class)
                            .where(Competencia_Table.competenciaId.eq(compentenciaId))
                            .querySingle();

                    //change evaluacion resultado por competencia
                    RubroEvaluacionResultado rubroEvaluacionResultadoCompetencia= SQLite.select().from(RubroEvaluacionResultado.class)
                            .where(RubroEvaluacionResultado_Table.competenciaId.eq(competencia.getSuperCompetenciaId()))
                            .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                            .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                            .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(rubroEvaluacionResultado.getCalendarioPeriodoId())).querySingle();
                    setnotas(rubroEvaluacionResultado.getCalendarioPeriodoId(),silaboEventoId,rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId(),rubroEvaluacionResultadoCompetencia.getCompetenciaId() );
                    Log.d(getClass().getSimpleName(), "rubroEvaluacioncompetencia " +rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId());
                    f_mediaDesviacionEstandar(rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId());

                    //change evaluacion resultado por bimestres finales
                    RubroEvaluacionResultado rubroEvaluacionReBimestre=  SQLite.select().from(RubroEvaluacionResultado.class)
                            .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(rubroEvaluacionResultado.getCalendarioPeriodoId()))
                            .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId)).
                                    and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1)).
                                    and(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(0)).querySingle();

                    Log.d(getClass().getSimpleName(), "rubroEvaluacionReBimestre " +rubroEvaluacionReBimestre.getRubroEvalResultadoId());
                    setnotas(rubroEvaluacionResultado.getCalendarioPeriodoId(),silaboEventoId,rubroEvaluacionReBimestre.getRubroEvalResultadoId(),0 );
                    f_mediaDesviacionEstandar(rubroEvaluacionReBimestre.getRubroEvalResultadoId());
                }

                rubroEvaluacionResultado.setRubroEvalProcesoId(rubroProcesoKey);
                rubroEvaluacionResultado.setEstadoId(anclado);
                rubroEvaluacionResultado.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionResultado.save();

                f_mediaDesviacionEstandar(rubroEvaluacionResultado.getRubroEvalResultadoId());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean f_mediaDesviacionEstandar(int rubroEvaluacionResultadoId) {
        return f_mediaDesviacionEstandarFormula(rubroEvaluacionResultadoId);
    }

    private boolean f_mediaDesviacionEstandarFormula(int rubroEvaluacionResultadoId) {
        List<Double>listNotas= new ArrayList<>();
        double media=0.0;
        List<EvaluacionResultadoC>evaluciones= SQLite.select().from(EvaluacionResultadoC.class)
                .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(rubroEvaluacionResultadoId)).
                        queryList();

        for(EvaluacionResultadoC e: evaluciones){
            media+=e.getNota()/evaluciones.size();
            listNotas.add(e.getNota());
        }


        Log.d(TAG, "media " + media);
        double stdDev = Utils.stdDev(media, listNotas);
        Log.d(TAG, "desviacion " + stdDev);

        RubroEvaluacionResultado rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionResultado.class)
                .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable()
                        .eq(rubroEvaluacionResultadoId))
                .querySingle();
        Log.d(TAG, "rubroEvaluacionProcesoC Jse"+ rubroEvaluacionResultadoId);

        if (rubroEvaluacionProcesoC == null) return false;
        rubroEvaluacionProcesoC.setPromedio(Utils.formatearDecimales(media, 2));
        rubroEvaluacionProcesoC.setDesviacionEstandar(Utils.formatearDecimales(stdDev, 2));
        rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        rubroEvaluacionProcesoC.save();
        return true;

    }

    private void setnotas(int periodoId, int silaboEventoId, int evaluacionResultadoId, int competenciaId){
        List<EvaluacionResultadoC> evaluacionResultadoList= SQLite.select().from(EvaluacionResultadoC.class).where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(evaluacionResultadoId)).queryList();
         Log.d(getClass().getSimpleName(), "evaluacionResultadoList size "+ evaluacionResultadoList.size());

        for(EvaluacionResultadoC evaluacionResultadoCompetencia:evaluacionResultadoList){
            RubroEvaluacionResultado rubroEvaluacionResultadoMayor= SQLite.select().from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable()
                            .eq(evaluacionResultadoCompetencia.getRubroEvalResultadoId())).querySingle();

            Log.d(getClass().getSimpleName(), "evaluacionResultadoCompetencia "+ evaluacionResultadoCompetencia.getEvaluacionResultadoId());
            double nota=0.0;
            double notaFinal=0.0;
            int count=0;


            List<RubroEvaluacionResultado> rubroEvaluacionResultadosCapacidades;
            if(competenciaId!=0){
                rubroEvaluacionResultadosCapacidades= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                        .from(RubroEvaluacionResultado.class)
                        .innerJoin(Competencia.class)
                        .on(RubroEvaluacionResultado_Table.competenciaId.withTable()
                                .eq(Competencia_Table.competenciaId.withTable()))
                        .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(periodoId))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .and(Competencia_Table.superCompetenciaId.withTable().eq(competenciaId)).queryList();

            }
            else{

                // Log.d(EVALUACION_COMPETENCIA_TAG, "evaluacion.getEvaluacionResultadoId()e "+ evaluacion.getEvaluacionResultadoId());
                rubroEvaluacionResultadosCapacidades= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                        .from(RubroEvaluacionResultado.class).innerJoin(RubroEvalRNRFormula.class)
                        .on(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().eq(RubroEvalRNRFormula_Table.rubroEvaluacionSecId.withTable()))
                        .where(RubroEvalRNRFormula_Table.rubroEvaluacionPrinId.withTable().eq(evaluacionResultadoCompetencia.getRubroEvalResultadoId()))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1)).queryList();

            }

            // Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionResultadosCapacidades size "+ rubroEvaluacionResultadosCapacidades.size());
            for(RubroEvaluacionResultado r:rubroEvaluacionResultadosCapacidades){
                //Log.d(EVALUACION_COMPETENCIA_TAG, "getRubroEvalResultadoId "+ r.getRubroEvalResultadoId());

                count=count+1;

                EvaluacionResultadoC evaluacionResultadoCapacidad= SQLite.select(Utils.f_allcolumnTable(EvaluacionResultadoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(EvaluacionResultadoC.class)
                        .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().eq(r.getRubroEvalResultadoId()))
                        .and(EvaluacionResultadoC_Table.alumnoId.withTable().eq(evaluacionResultadoCompetencia.getAlumnoId()))
                        .querySingle();
                if(evaluacionResultadoCapacidad==null)continue;

                nota = nota + evaluacionResultadoCapacidad.getNota();

            }
            notaFinal=nota/count;
            double notaTransformada = (double) Math.round(notaFinal * 100) / 100;

            if(rubroEvaluacionResultadoMayor!=null) {
                TipoNotaC tipoNotaC = SQLite.select().from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.withTable()
                                .eq(rubroEvaluacionResultadoMayor.getTipoNotaId())).querySingle();

                String tipoNotaId = "";
                boolean intervalo = true;

                if(tipoNotaC!=null){
                    tipoNotaId = tipoNotaC.getTipoNotaId();
                    intervalo = tipoNotaC.isIntervalo();
                }
                List<ValorTipoNotaC>valorTipoNotaCList= SQLite.select().from(ValorTipoNotaC.class)
                        .where(ValorTipoNotaC_Table.tipoNotaId.withTable()
                                .eq(tipoNotaId))
                        .queryList();

                ValorTipoNotaC valorTipoNota = null;
                if(intervalo){
                    for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                        if (itemValorTipoNota.getLimiteInferior() <= notaTransformada && itemValorTipoNota.getLimiteSuperior() >= notaTransformada) {
                            valorTipoNota = itemValorTipoNota;
                        }
                        evaluacionResultadoCompetencia.setNota(Utils.formatearDecimales(notaTransformada, 2));
                    }
                }else {
                    long notaEntera = Math.round(notaTransformada);
                    if(!valorTipoNotaCList.isEmpty()){

                        ValorTipoNotaC minimoValorNumerico = null;
                        for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                            if (itemValorTipoNota.getValorNumerico() == notaEntera) {
                                valorTipoNota = itemValorTipoNota;
                            }
                            if(minimoValorNumerico==null||minimoValorNumerico.getValorNumerico() > itemValorTipoNota.getValorNumerico()){
                                minimoValorNumerico = itemValorTipoNota;
                            }

                        }
                        if(valorTipoNota==null)
                            valorTipoNota = minimoValorNumerico;

                    }
                    evaluacionResultadoCompetencia.setNota(notaEntera);

                }
                if(valorTipoNota!=null)
                    evaluacionResultadoCompetencia.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());

                evaluacionResultadoCompetencia.setSyncFlag(BaseEntity.FLAG_UPDATED);
                evaluacionResultadoCompetencia.save();
            }

        }

    }




}
