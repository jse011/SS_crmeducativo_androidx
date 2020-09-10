package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities;

import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.ArrayList;


/**
 * Created by kike on 29/03/2018.
 */

public interface TabCursoDocentePresenter extends BasePresenter<TabCursoDocenteView> {

    void onFabClicked(int pagePosition);
    void onFabClickedGrupos();

    void onPageChanged(Class<? extends Fragment> fragmentClass);

    void onBtnSuccesItemFilterRubro(ArrayList<CompetenciaCheck> tipoCompetencia);

    void onPeriodoSelected(PeriodoUi periodoUi);

    void actualizarRubroTipoAnclaFragmentRegistro();

    void onActualizarItemRubrica();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onClickUpdate();

    void onChangeRubroUpdate();

    void onClickRefrescar();

    void onChange(TipoImportacion tipoImportacion, boolean success);

    void onAceptarBottomDialog(Object o);

    void onChangeFull(boolean success);

    void delteRubrica();

    void onDeleteRubro();

    void onFinishSynck();

    void onCreateOptionsMenu();

    void onClickUpadteAlumnos();

    void onClickUpadteFotoAlumnos();

    void PostDelaychangePeriodo();

    void onConfigurationChanged();

    void onChangeDatabseDesdeTabSesion();

    void onChangeDatabseDesdeService2();

    void onClickAceptarRevizarErroresActualizar();

    void onClickCalendario();


    //a Eliminar!!!
    //void onActivityResult(int requestCode, int resultCode, Intent data);
}
