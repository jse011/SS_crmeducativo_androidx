package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.CrearRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface CrearRubroView extends BaseView<CrearRubroPresenter> {
    void hideDialog();

    void showSpinnerNotaTipo(List<TipoNotaUi> tipoNotas);

    void addCriterio(ValorTipoNotaUi valorTipoNotaUi);

    int updateCriterio(ValorTipoNotaUi valorTipoNotaUi);

    void deleteCriterio(ValorTipoNotaUi valorTipoNotaUi);

    void setCriterios(List<ValorTipoNotaUi> valorTipoNotaUis);

    void showCriterioEval();

    void hideCriterioEval();

    void showDialogCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi, int indicadorId);

    void hideDialogCriterioEval();

    void setTituloIndcador(IndicadorUi indicadorUi, TipoIndicadorUi tipoIndicadorUi);

    void showCampoAcionList(List<CamposAccionUi> camposAccionUiList);

    void showSpinnerTipoEvaluacion(List<TipoEvaluacionUi> tipoEvaluacionUis);

    void addColorCondicional(ColorCondicionalUi colorCondicionalUi);

    int updateColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void deleteColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void setColoresCondionales(List<ColorCondicionalUi> colorCondicionalUis);

    void showColorCondicionalDialog(ColorCondicionalUi colorCondicionalUi);

    void hideColorCondicionalDialog();

    List<ColorCondicionalUi> abstraerColoreCondicionales();

    void showColorPikerDialog(String titulo, int color);

    void errorTitulo(String error);

    void errorSubtitulo(String error);

    void mensajeToast(String error);

    void listenerSaveRubroSuccess(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi, int programaEducativoId);

    void showSpinnerFormaEvaluacion(List<FormaEvaluacionUi> formaEvaluacionUiList);

    void showInputTipoEvaluacion(String tipoEvaluacion);

    void showInputFormaEvaluacion(String formaEvaluacion);

    void showListaIndicadoresFragment(int silavoEventoId, int sesionAprendizaje, int nivel, int competenciaId, int calendarioPeridoId, int indicadorId, ArrayList<Integer> camposIds);

    void showTecladoNumerico(EscalaEvaluacionUi escalaEvaluacionUi);

    void hideTecladoNumerico();

    void onSetCamposTematicos(int indicadorId, ArrayList<Integer> camposIds);


    void changeStateIndicador();
    void setDatosRubro(String titulo, String subtitulo);

    void showTituloEstrategia(String estrategia);

    void showInputEstrategia();
    void hideInputEstrategia();

    void setTitulo(String tituloTarea);

    void setSubTitulo(String tituloTarea);

    void showActivityNivelLogro(int idProgramaEducativo);

    void showNivelLogro(TipoNotaUi tipoNotaUi);

    void setCompetencia(String nombre);

    void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaUi);

    void setCapacidad(String nombreCapacidad);

    void enabledVerMas(int maxLinTexDesempenio);

    void formatMinimizarTextDesmepenio(int maxLinTex);

    void changeTextoVerMasDesempenio(String texto);

    void formatMaximizarTextDesmepenio();

    void showVistaPrevia();

    void hideVistaPrevia();

    void showDialogKeyBoard(CriterioEvalUi criterioEvalUi);

}
