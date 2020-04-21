package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEvaluacionResultado  extends BEDatosServidor {
    private List<RubroEvaluacionResultado> rubroEvaluacionResultado;
    private List<RubroEvalRNRFormula> rubroEvalResultadoFormula;

    private List<EvaluacionResultadoC> evaluacionResultado;

    //public List<BECriterioRubroEvalResultado> ObtenerCriterioRubroEvaluacionResultado { get; set; }


    public BEDatosEvaluacionResultado() {
    }


    public List<RubroEvaluacionResultado> getRubroEvaluacionResultado() {
        return rubroEvaluacionResultado;
    }

    public List<RubroEvalRNRFormula> getRubroEvalResultadoFormula() {
        return rubroEvalResultadoFormula;
    }

    public List<EvaluacionResultadoC> getEvaluacionResultado() {
        return evaluacionResultado;
    }

    public void setRubroEvaluacionResultado(List<RubroEvaluacionResultado> rubroEvaluacionResultado) {
        this.rubroEvaluacionResultado = rubroEvaluacionResultado;
    }

    public void setRubroEvalResultadoFormula(List<RubroEvalRNRFormula> rubroEvalResultadoFormula) {
        this.rubroEvalResultadoFormula = rubroEvalResultadoFormula;
    }

    public void setEvaluacionResultado(List<EvaluacionResultadoC> evaluacionResultado) {
        this.evaluacionResultado = evaluacionResultado;
    }
}
