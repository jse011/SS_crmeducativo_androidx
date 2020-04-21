package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera;


import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

import java.util.List;

/**
 * Created by @stevecampos on 15/02/2018.
 */

public interface CabeceraView {

    String getEdtRubrica();
    String getEdtAlias();

    void showTipoNotaSelected(TipoNotaUi tipoNota);
    void showTipoEvaluacionSelected(String tipoEvaluacionTitulo);
    void showFormaEvaluacionSelected(String formaEvaluacionTitulo);
    void showEscalaSelected(String escalaTitulo);

    void showCompetenciaList(List<CompetenciaUi> competenciaList);
    void showCampoAccionList(List<CampoAccionUi> campoAccionList);


    void showTipoNotaChooser(List<TipoNotaUi> tipoNotaList);
    void showTipoEvaluacionChooser(List<TipoUi> tipoEvaluacionList);
    void showFormaEvaluacionChooser(List<TipoUi> formaEvaluacionList);
    void showEscalaChooser(List<EscalaEvaluacionUi> escalaEvaluacionList);

    void showIndicadorChooser(List<CompetenciaUi> competenciaList);
    void showCampoAccionChooser(List<IndicadorUi> indicadorList);

    void disabledFormaEvaluacion();

    void disabledNivelLogroRubrica();

    void hideEstrategiaInput();

    void showEstrategiaInput();

    void showTituloEstrategiaSelected(String estrategia);

    void setSubTitulo(String tituloTarea);

    void setTitulo(String tituloTarea);

    void hideCompetenciaList();

    void hideCampoAccionList();
}
