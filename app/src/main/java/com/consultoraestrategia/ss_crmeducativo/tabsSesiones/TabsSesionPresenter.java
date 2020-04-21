package com.consultoraestrategia.ss_crmeducativo.tabsSesiones;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;

import java.util.ArrayList;

/**
 * Created by SCIEV on 13/12/2017.
 */

public interface TabsSesionPresenter extends BasePresenter<TabsSesionView> {
   void setExtra(Bundle extra);

   void onClickAceptarDialog();

   void hideCrearRubro();

   void onSelectIndicadorCampotematicovoid(int indicadorId, ArrayList<Integer> campotematicoIds);

   void onSalirListaIndicadores();

   void onSelectTab(int posicion);

   void onSussesRubroEvaluacionCompetenciasLista(ArrayList<Integer> competenciasId);

    void onChangeRubroEvaluacion(int ruborEvaluacionId);

    void onAddRubroEvaluacion(int addruborEvaluacionId);

   void onPageChanged(Class<? extends Fragment> fragmentClass);

    void onClickFab();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onClickActualizar();

    void onClickRefrescar();

    void onClickUpdate();

    void onChangeRubroUpdate();
    void onBtnSuccesItemFilterRubro(ArrayList<CompetenciaCheck> tipoCompetencia);

    void onChange(TipoImportacion tipoImportacion, boolean success);

    void onChangeFull(boolean success);

    void onDeleteRubrica();

    void onDeleteRubro();

    void salirFrameLayoutComentarios();

    void onCreateOptionsMenu();

    void onFinishSynck();
}
