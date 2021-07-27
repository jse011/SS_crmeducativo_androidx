package com.consultoraestrategia.ss_crmeducativo.main;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.NuevaVersionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.Calendar;
import java.util.List;

/**
 * Created by irvinmarin on 23/02/2017.
 */

public interface MainView extends BaseView<MainPresenter>,MainConfiguracion{

    void showImgProfileUser(String UrlUser);

    void showImgProfileBackgroundUser(String UrlUser);

    void showImgHijo(String UrlImgHijo);

    void hideImgHijo();

    void showNombreUsuario(String fullNameUser);

    void showLetraPerfil(String letra);

    void showEmailUsuario(String emailUser);

    void showMenuList(List<Object> objects);

    void changeProgramaEducativoList();

    void showActualProgramaEdu(ProgramaEduactivosUI nombrePrograma);


    void setCursosList(List<CursosUI> objectUIList, boolean efectoLista);

    void starActivityLogin(String error);

    void starActivityTabs(Class tabClase, CursosUI cursosUI, int programaEducativoId, int georeferenciaId, int empleadoId, int entidadId, int anioAcademicoId, int idUsuario, boolean complejo);

    void showDialogListHijos(List<Persona> personaList);

    void subscribe(String celular);

    void setProgramasVacio(String s);

    void close();

    void startActivityTipoNota(UsuarioAccesoUI usuarioAccesoUI);

    void setAnioSelectedText(String text);

    void showHorarioAnioAcademico(int programaEducativoId, int empleado, int anioAcademicoId);

    void showHorarioCargaCurso(int programaEducativoId, int empleadoId, int cargaCursoId, int anioAcademicoId);

    void showAsistenciaGradoSeccion(int usuarioId, int programaEducativoId);

    void mostrarDialogoBorrarMemoriaCahe();

    void showImportarCalendario(BEVariables beVariables);

    void viewActivityImportLogin(BEVariables beVariables);

    void callSynckService(int programaEducativoId);
    void changePeriodo(PeriodoUi oldSelected, PeriodoUi periodoSelected);
    void showPeriodoList(List<PeriodoUi> periodoList);

    void setGradosList(List<Object> objectUIList, CRMBundle crmBundle);
    void showContenAsistencia();
    void hideContenAsistencia();
    void showContenCurso();
    void hideContenCurso();
    void updateCalendar(CRMBundle crmbundle);

    void mostrarDialogoConfigAlarma();

    void startAlarm(Calendar currCalendar, int programaEducativoId);

    void setNombreEntidad(String nombreEntidad, String nombreGeoreferencia);

    void showBtnEntidadSelect();
    void hideBtnEntidadSelect();

    void showPopListEntidad(final List<UsuarioRolGeoReferenciaUi> usuarioRolGeoReferenciaUis);

    void showMensaje();

    void hideMensaje();

    void mostrarDialogoCerrarSesion(List<String> tableChange, boolean isUpdateTable);

    void showDialogInformacion(String empresa, String app, String desarrolladores);

    void progressUpdateColor(int color);

    void progressUpdateSuccess();

    void progressUpdateError();


    boolean isInternetAvailable();

    void showMessageNotConnection();

    void showCambiarFotoAlumnoActivity(int idCargaCurso, int georeferenciaId, int entidadId);

    void showActivityService2(int idUsuario, int empleadoId, int anioAcademicoIdFinal, int idPrograma, int georeferenciaId, int entidadId);

    void hideProgressInit();

    void showProgressInit();

    void showDialogFastData(int anioAcademicoIdFinal, int idUsuario);

    void showSeleccionAnioAcademico();

    void showPerfil();

    void showCamaraReconocimientos(int idUsuario);

    void showActivityAgenda(int idUsuario, int georeferenciaId, int empleadoId, int anioAcademicoIdFinal, int entidadId, int tutorCargaAdemicaId);

    void subscribeToTopic(int personaId);

    void showReconocimientoActivity(int cargaCursoId, int georeferenciaId, int entidadId);

    void startCropImageActivity(String path);

    void callSynckServiceFB();

    void showBtnAgenda();

    void hideBtnAgenda();

    void showCentroProcesoActivty(CursosUI cursosUiRecurso, int idPrograma, int anioAcademicoIdFinal);

    void showDialogNuevaVersion(NuevaVersionUi nuevaVersionUi);

    void updateCalendarioPeridoyOtros();
}
