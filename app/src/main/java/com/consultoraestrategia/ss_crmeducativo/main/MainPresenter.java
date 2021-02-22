package com.consultoraestrategia.ss_crmeducativo.main;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.main.changePerfil.ChangePerfilView;
import com.consultoraestrategia.ss_crmeducativo.main.dialogProgress.ProgressDialogView;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ConfiguracionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.NuevaVersionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;
import com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio.SeleccionarAnioAcademicoView;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

/**
 * Created by irvinmarin on 27/02/2017.
 */

public interface MainPresenter extends BasePresenter<MainView> {

    void setExtras(Bundle extras);

    void onCursoUIClicked(List<CursosUI> cursosUIList);

    void onProgramaEducativoUIClicked(ProgramaEduactivosUI programaEducativo);

    void onIbtnProgramaClicked();

    void onCerrarSesionClicked();

    void onCursoSelected(CursosUI cursosUI);

    void onImgHijoClicked();

    void getCuentaHijo(int idHijoSelected);

    void onAccesoSelected(UsuarioAccesoUI usuarioAccesoUI);

    void onBtnSelectorAnioCLicked();

    void onClickActionHorario();

    void onSelectHorarioCurso(CursosUI cursosUI, int cargaCurso);

    void onClickBtnAcceso();

    void onClickBtnConfiguracion();

    void onClickAsistenciaGradoSeccion();

    void onClickConfiguracion(ConfiguracionUi configuracionUi);

    void onClickDialogoCerrarSesion();

    void onClickDialogoBorrarCahe();

    void updateCalendarioPeriodo();

    void onClickRefrescar();
    void onPeriodoSelected(PeriodoUi periodoUi);
    void onSelectedGrado(GradoUi gradoUi);

    void onClickListCursos();

    void selectedHoraMinuteAlarma(int hour, int minute);

    void aceptarHora();

    AlarmaUi getAlarma();

    void onChangeFull(boolean b);

    void onClickedMenuEntidad();

    void onSelectedEntidad(UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi);

    void onCreateOptionsMenu();

    void onFinishSynck();

    void onClickTutoriaCursoSelected(CursosUI cursosUI, int cargaCursoId);

    void onProgressDialogViewDestroyed();

    void attachView(ProgressDialogView progressDialogView);

    void onChangeDatabseDesdeService2();

    void onClickAnioSelected(AnioAcademicoUi anioAcademicoUi);

    void attachView(SeleccionarAnioAcademicoView f);

    void onSeleccionarAnioAcademicoViewDestroyed();

    void onChangePerfilViewDestroyed();

    void attachView(ChangePerfilView changePerfilView);

    void onClickReconocimiento();

    void onClickActionAgenta();

    void onClickReconocimientoCursoSelected(CursosUI cursosUI, int cargaCurso);

    void onClickGuardarPerfil(String telefono, String email);

    void changeFile();

    void onCropImageActivityResult(String filePath);

    void onClickResultadoCursoSelected(CursosUI cursosUiRecurso);

    void onClickEvaCursoSelected(CursosUI cursosUiRecurso);

    void onClickMovilCursoSelected(CursosUI cursosUiRecurso);

    void onClickAgendaCursoSelected(CursosUI cursosUiRecurso);

    void onVersionChecker(NuevaVersionUi nuevaVersionUi);
}
