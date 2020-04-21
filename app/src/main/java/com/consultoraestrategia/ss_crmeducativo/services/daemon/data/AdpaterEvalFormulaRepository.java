package com.consultoraestrategia.ss_crmeducativo.services.daemon.data;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.utils.InjectorRepositoryRubroList;


/**
 * Created by SCIEV on 7/08/2018.
 */

public class AdpaterEvalFormulaRepository {


     public interface SuccessCallBack<T>{
          void onSuccess(boolean success);
     }

     private RubroEvaluacionProcesoListaDataSource evaluacionFormulaRepository;

     public AdpaterEvalFormulaRepository() {
          this.evaluacionFormulaRepository = InjectorRepositoryRubroList.getEvaluacionFormulaRepository();
     }

     public void onUpdateEvaluacionFormula(final SuccessCallBack callBack){
         try {
              evaluacionFormulaRepository.onUpdateEvaluacionFormula( new RubroEvaluacionProcesoListaDataSource.SimpleSuccessCallBack() {
                   @Override
                   public void onSuccess(boolean success) {
                        callBack.onSuccess(success);
                   }
              });
         }catch (Exception e){
              e.printStackTrace();
              callBack.onSuccess(false);
         }

     }

}
