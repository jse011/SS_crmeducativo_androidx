package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui.CrearRubroView;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface CrearRubroPresenter extends BaseFragmentPresenter<CrearRubroView> {
    void onClickCancelar();

    void onClickAceptar(String titulo, String subtitulo, String valorDefecto, List<ValorTipoNotaUi> valorTipoNotaUiList);

    void onSelectTipoNota(TipoNotaUi tipoNota);

    void onClickItemCriterioEval(CriterioEvalUi criterioEvalUi, List<ValorTipoNotaUi> valorTipoNotaUi);

    void editCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi);

    void onClickSaveCriterioEval(ValorTipoNotaUi valorTipoNotaUi);

    void onClickCloseCriterioEval();

    void setExtras(Bundle extras);

    void onSelectTipoEvaluacion(TipoEvaluacionUi tipoEvaluacionUi);

    void onClickAddColorCondicional();

    void onClickItemColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void onClickSaveItemColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void onClickCloseItemColorCondicional();

    void onClickItemCheckDesdeColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void onClickItemCheckHastaColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void onClickItemEliminarColorCondicional(ColorCondicionalUi colorCondicionalUi);

    void onClickColorTexto(ColorCondicionalUi colorCondicionalUi);

    void onClickColorFondo(ColorCondicionalUi colorCondicionalUi);

    void onSelectColor(int color);

    void onSelectFormaEvaluacion(FormaEvaluacionUi formaEvaluacionUi);

    void onSelectTipoNota(String tipoNotaId);

    void onBtnTipoEvaluacionClicked();

    void onClickFormaEvaluacion();

    void onClickIndicador();

    void onChangeIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds);

    void onClickEstrategiaEval();

    void onTextChangedSubtitulo(String texto);

    void onSingleItemSelectedTipoNota(String tipoNotaId);

    void onClickNivel();

    void onClickInfoTipoNota();

    void postCantidadLineasDesempenio(int lineCount);

    void onClickVerMasDesempenio();

    void onClickAceptarDialogkeyboard(String contenido);

    void onDismissDialogkeyboard();

    void onCreateDialogKeyBoard(DialogkeyBoardView view);
}
