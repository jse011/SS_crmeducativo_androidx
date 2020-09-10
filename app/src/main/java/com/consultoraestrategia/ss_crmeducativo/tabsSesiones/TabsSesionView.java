package com.consultoraestrategia.ss_crmeducativo.tabsSesiones;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;

import java.util.ArrayList;

/**
 * Created by SCIEV on 13/12/2017.
 */

public interface TabsSesionView extends BaseView<TabsSesionPresenter> {
    void posicionarTab(int posicion);

    void showListaIndicadores(int sesionAprendizajeId, int rubroEvaluacionResultadoId, int nivel);

    void hideListaIndicadores();

    void showCrearRubro(int sesionAprendizajeId, int indicadorId, ArrayList<Integer> campotematicoIds);

    void showfloatButon();

    void hidefloatButon();

    void updateFragmentoEvaluacion();

    void updateFragmentosRubro(ArrayList<Integer> competenciasId);

    void startCreateRubBid(int calendarioPeriodoId, int silaboEventoId, int rubroEvalResultadoId, int programaEducativoId);

    void setupAdapter(int nivel, int calendarioPerioId, int silavoEventoId, int RubroEvaluacionResultadoId, int programaEducativoId, int sesionAprendizajeId, int parametrosDisenioId, int cargaAcademicaId, int cursoId, int cargaCursoId, SesionAprendizajeUi sesionAprendizajeUi, int personaId, int backgroudColor, int entidadId, int georeferenciaId);

    void updateFragmentosRubro(int rubroEvaluacionId);

    void addFragmentosRubro(int updateruborEvaluacionId);

    void changeFabBg(int drawable);

    void hideFab();

    void showFiltroCamposTematicos(ArrayList<CompetenciaCheck> tipoCompetencia);

    void onActualizarItemRubrica();

    void onResumenFragmentAprendizaje();

    void onResumenFragmentActividades();

    void onResumenFragmentRubrica();

    void onResumenFragmentRegistro();

    void onResumenTarea();

    void onResumenFragmentSituacion();

    void showMsgActualizacion();

    void hideMsgActualizacion();

    void showActualizarSesionAprendizaje(int sesionAprendizajeId);

    void onRefrescarFragmentRegistro();

    void showActualizarRubrica(BEVariables beVariables);

    void showActualizarGrupos(BEVariables beVariables);

    void showActualizarRubros(BEVariables beVariables);
    void onSesionCompetenciaFragmentFabClicked(ArrayList<CompetenciaCheck> tipoCompetencia);

    void onResumenFragmentComentarios();

    void expandirToolbar();

    void callSynckService(int programaEducativoId);

    void hideViewPager();

    void showViewPager();

    void initComentarios(SesionAprendizajeUi sesionAprendizajeUi, int personaId, int backgroudColor, int CargaCursoId, int CursoId, int cargaAcademicaId,  int parametroDisenioId);

    void hideFrameLayoutComentarios();

    void showToolbar();

    void hideToolbar();

    void startTarea();

    void changeColorFloatButton();

    void changeColorToolbar();

    void onComprobarCPTareas(boolean vigente);

    void onComprobarCPRubro(boolean editar, boolean anclar);

    void progressUpdateSuccess();

    void progressUpdateError();

    boolean isInternetAvailable();

    void showMessageNotConnection();

    void hideMsgCalendarioPeriodo();

    void showMsgCalendarioPeriodo();

    boolean ischangeDataBase();

    void successchangeDataBase();

    void saveNotifyChangeTabCurso();

    void comprobarSiActulizaronCorrectementeRubros();

}

