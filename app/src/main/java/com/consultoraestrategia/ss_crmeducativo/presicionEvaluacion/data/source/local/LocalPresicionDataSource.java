package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.local;

import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.RubroEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.PresicionDataSource;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.ValorTipoNotaPrecisionUi;

public class LocalPresicionDataSource implements PresicionDataSource {

    RubroEvaluacionDao rubroEvaluacionDao;
    EvaluacionProcesoDao evaluacionProcesoDao;
    ValorTipoNotaDao valorTipoNotaDao;
    TipoNotaDao tipoNotaDao;

    public LocalPresicionDataSource(RubroEvaluacionDao rubroEvaluacionDao, EvaluacionProcesoDao evaluacionProcesoDao, ValorTipoNotaDao valorTipoNotaDao, TipoNotaDao tipoNotaDao) {
        this.rubroEvaluacionDao = rubroEvaluacionDao;
        this.evaluacionProcesoDao = evaluacionProcesoDao;
        this.valorTipoNotaDao = valorTipoNotaDao;
        this.tipoNotaDao = tipoNotaDao;
    }

    @Override
    public void getTipoNota(String rubroEvaluacionId, String valorTipoNotaId, Callback<ValorTipoNotaPrecisionUi> callback) {
        try {

            ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi = new ValorTipoNotaPrecisionUi();
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroEvaluacionDao.get(rubroEvaluacionId);
            valorTipoNotaPrecisionUi.setRubroNombre(rubroEvaluacionProcesoC.getTitulo());
            ValorTipoNotaC valorTipoNotaC = valorTipoNotaDao.get(valorTipoNotaId);
            valorTipoNotaPrecisionUi.setDescripcion(valorTipoNotaC.getAlias());
            valorTipoNotaPrecisionUi.setLimiteInferior(valorTipoNotaC.getLimiteInferior());
            valorTipoNotaPrecisionUi.setLimiteSuperior(valorTipoNotaC.getLimiteSuperior());
            valorTipoNotaPrecisionUi.setIncluidoLInferior(valorTipoNotaC.isIncluidoLInferior());
            valorTipoNotaPrecisionUi.setIncluidoLSuperior(valorTipoNotaC.isIncluidoLSuperior());
            TipoNotaC tipoNotaC = tipoNotaDao.get(valorTipoNotaC.getTipoNotaId());
            switch (tipoNotaC.getTipoId()){
                case TipoNotaC.SELECTOR_ICONOS:
                    valorTipoNotaPrecisionUi.setTipo(ValorTipoNotaPrecisionUi.TIPO.SELECTOR_ICONOS);
                    valorTipoNotaPrecisionUi.setIcono(valorTipoNotaC.getIcono());
                    break;
                case  TipoNotaC.SELECTOR_VALORES:
                    valorTipoNotaPrecisionUi.setTipo(ValorTipoNotaPrecisionUi.TIPO.SELECTOR_VALORES);
                    valorTipoNotaPrecisionUi.setAlias(valorTipoNotaC.getTitulo());
                    break;
            }
            callback.onLoad(true, valorTipoNotaPrecisionUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }
    }

}
