package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.DrawableRes;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 29/03/2018.
 */

public interface TabCursoDocenteView extends BaseView<TabCursoDocentePresenter> {

    void showEvaluacionCompetenciaDialogFilter();

    void hideFab();

    void hideFabGrupos();

    void showFabGrupos();


    void changeFabBg(@DrawableRes int drawable);


    void showFragments(int idCargaCurso, int idCurso, int idProgramaEducativo, int parametrodisenioid, int calendarioPerioId, int idCargaAcademica, boolean calendarioVignete, boolean calendarioEditar);

    void showTitle(String title);

    void showSubtitle(String subtitle);

    void showAppbarBackground(String bg, String bgColor);


    void onRubricaBidFragmentFabClicked();

    void onGrupoFragmentFabClicked(boolean isAprendizajeColegio);


    void onAsistenciaFragmentFabClicked();

    void onTareaFragmentFabClicked();

    void onEvaluacionFragmentFabClicked(Bundle bundle);

    void showRubroResultadoSilaboDialogFilter(ArrayList<CompetenciaCheck> tipoCompetencia);


    void onActualizarEvaluacionFrament();

    void onActualizarRegistroFrament();

    void onActualizarRubricaFragment(Intent intent);

    public void filtrarRubroCompetencia();

    public void filtrarRubroEnfoqueTrans();

    //A ELIMINAR ESTOS MÉTODOS!!!
    /*void callCursoDocenteFragmentLifecycle(Bundle bundle);

    void CallbackFragmentLifeCycle(Bundle bundle);*/

    void onResumenFragmentRubrica(int calendarioPeriodoId, boolean status);

    void onResumenFragmentResultado(int calendarioPeriodoId, boolean status);

    void onResumenFragmentGrupos();

    void onResumenFragmentRegistro(int calendarioPeriodoId, boolean status);

    void onResumenFragmentTareas(int calendarioPeriodoId, boolean status);

    void showActualizarRubros(BEVariables beVariables);

    void showActualizarGrupos(BEVariables beVariables);

    void showActualizarRubrica(BEVariables beVariables);

    void onResumenFragmentUnidades(int calendarioPeriodoId, boolean status);

    void onActualizarFragmentRegistro(int calendarioPeriodoId, boolean status);

    void hideBtnActualizar();
    void showBtnActualizar();

    void onEvaluacionRubroSilaboFragmentFabClicked(ArrayList<CompetenciaCheck> tipoCompetencia);
    void showPeriodoList(List<PeriodoUi> periodoList, String color);

    void changePeriodo();

    void showMsgCalendarioPeriodo();

    void hideMsgCalendarioPeriodo();
    void hidePeriodosLIst();
    void showPeriodosLIst();

    void onFiltroGrupos(boolean filtro);

    void onCallSinckService(int programaEducativoId);

    void onResumenAsistenciaCursoFragment(int idCalendarioPeriodo, boolean status);
    void onResumenFragmentComportamiento(int calendarioPeriodoId, boolean status);
    void contraerToolbar();

    void changeColorToolbar(String color1);

    void changeColorFloatButon(String color2);
    void onComportamientoFabClicked();

    void comprobarCPRubro(boolean vigente, boolean anclar);

    void comprobrarCPTarea(boolean vigente);

    void comprobarCPSesiones(boolean editar, boolean vigente);

    void progressUpdateColor(int color);

    void progressUpdateSuccess();

    void progressUpdateError();

    boolean isInternetAvailable();

    void showMessageNotConnection();

    void showProgressDialogUpdateAlumnos();

    void showSincContratoAlumnos(int silaboId, int grupoAcademicoId, int perdiodoAcademicoId, int calendarioPeriodoId, int idProgramaEducativo);

    void showItemMenuUpdateAlumno();

    void hideItemMenuUpdateAlumno();

    void showCambiarFotoAlumnoActivity(int idCargaCurso);

    void showItemMenuUpdateFotoAlumno();

    void hideItemMenuUpdateFotoAlumno();

    void showAutoHideProgress();

    void onSimpleSinckService(int programaEducativoId);

    void changeBundleCalendarioId(int idCalendarioPeriodo);

    boolean ischangeDataBase();

    void successchangeDataBase();

    void changeOrientationUnidad();

    void showActivityService2(int usuarioId, int empleadoId, int idProgramaEducativo, int idCargaCurso, int idCalendarioPeriodo, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int idCargaAcademica, int anioAcademicoId, boolean cursoComplejo);

    void showProgressService2(int usuarioId, int empleadoId, int idProgramaEducativo, int idCargaCurso, int idCalendarioPeriodo, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int idCargaAcademica, boolean cursoComplejo, int anioAcademicoId);

    void comprobarSiActulizaronCorrectementeRubros();

    void showActivityAgenda(int idUsuario, int georeferenciaId, int empleadoId, int anioAcademicoIdFinal, int entidadId, int cargaCursoId);

    void startChangeCentroProcesamiento(int idCalendarioPeriodo, int silaboId, int idCargaCurso);
}
