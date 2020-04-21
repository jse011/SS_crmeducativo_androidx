package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvalResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;

import java.util.List;

public class BEDatosResultado {
    private List<RubroEvaluacionResultado> rubroEvaluacionResultado;

    private List<EvaluacionResultadoC> evaluacionResultado;

    private List<CriterioRubroEvalResultadoC> criterioRubroEvaluacionResultado;

    private List<RubroEvalRNRFormula> rubroEvalResultadoRnrFormula;

    private long fecha_servidor;

    public List<RubroEvaluacionResultado> getRubroEvaluacionResultado() {
        return rubroEvaluacionResultado;
    }

    public void setRubroEvaluacionResultado(List<RubroEvaluacionResultado> rubroEvaluacionResultado) {
        this.rubroEvaluacionResultado = rubroEvaluacionResultado;
    }

    public List<EvaluacionResultadoC> getEvaluacionResultado() {
        return evaluacionResultado;
    }

    public void setEvaluacionResultado(List<EvaluacionResultadoC> evaluacionResultado) {
        this.evaluacionResultado = evaluacionResultado;
    }

    public List<CriterioRubroEvalResultadoC> getCriterioRubroEvaluacionResultado() {
        return criterioRubroEvaluacionResultado;
    }

    public void setCriterioRubroEvaluacionResultado(List<CriterioRubroEvalResultadoC> criterioRubroEvaluacionResultado) {
        this.criterioRubroEvaluacionResultado = criterioRubroEvaluacionResultado;
    }

    public List<RubroEvalRNRFormula> getRubroEvalResultadoRnrFormula() {
        return rubroEvalResultadoRnrFormula;
    }

    public void setRubroEvalResultadoRnrFormula(List<RubroEvalRNRFormula> rubroEvalResultadoRnrFormula) {
        this.rubroEvalResultadoRnrFormula = rubroEvalResultadoRnrFormula;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
}
