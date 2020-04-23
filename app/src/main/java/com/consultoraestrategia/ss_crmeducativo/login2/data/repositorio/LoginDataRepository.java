package com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio;

import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.DatosProgressUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioExternoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public interface LoginDataRepository {
    RetrofitCancel getUsuarioExterno(String urlAdminServicio, String usuario, String password, Callback<UsuarioExternoUi> callback);

    void saveUrlServidorLocal(String url);

    RetrofitCancel getUsuarioLocalPorCorreo(String urlAdminServicio, String usuario, String password, String correo, Callback<UsuarioExternoUi> callback);

    RetrofitCancel getUsuarioLocalPorDni(String urlAdminServicio, String usuario, String password, String correo, String dni, Callback<UsuarioExternoUi> callback);

    RetrofitCancel getDatosInicioSesion(int usuarioId, CallBackComplejo<DatosProgressUi> callback);

    RetrofitCancel getPersonaLocal(String usuario, Callback<PersonaUi> callback);

    RetrofitCancel getUsuarioLocal(int usuarioId, Callback<UsuarioUi> callback);

    List<ActualizarUi>  geTodosLosCurso(int usuarioId, int AnioAcademicoId);

    String getNombreColegio(int usuarioId);

    String getNombreAnioActual(int anioAcademicoId);

    List<ProgramaEducativoUi> getCursosAnioPrograma(int usuarioId, int anioAcademicoId, int calendarioPeriodoId, int programaEducativoId);

    RetrofitCancel saveDatosUnidades(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosResultado(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosRubro(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosRubrica(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosFormula(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosTarea(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosGrupo(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosCasos(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    RetrofitCancel saveDatosCerrarCurso(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces);

    AlarmaUi getPlanSinck();

    boolean savePlanSinck(int hora, int minute);

    interface Callback<S>{
        void onResponse(boolean success, S value);
    }

    interface CallBackSucces<T>{
        void onLoad(boolean success, T item);
        void onRequestProgress(int progress);
        void onResponseProgress(int progress);
    }

    interface CallBackComplejo<T>{
        void onResponse(boolean success, T value);
        void onChangeRetrofit(RetrofitCancel retrofitCancel);
    }
    CalendarioPeriodoUi getCalendarioPeriodo(int calendarioPeriodo);

    boolean isfirstTimeHere(int cargaCursoId, int calendarioPeridoId);

    long getTimeSesionData(ActualizarUi tipoNota);

    RetrofitCancel getDatosUnidades(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosUnidades(ProgramaEducativoUi cursosUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosTipoNota(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosTipoNota(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosEstudiantes(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosEstudiantes(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosResultado(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosResultado(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosRubro(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosRubro(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosGrupo(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosGrupo(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosTarea(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosTarea(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDatosCasos(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDatosCasos(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    RetrofitCancel getDimendinoDesarrollo(ActualizarUi actualizarUi, CallBackSucces<ActualizarUi> callBackSucces);

    RetrofitCancel getDimendinoDesarrollo(ProgramaEducativoUi programaEducativoUi, CallBackSucces<ProgramaEducativoUi> callBackSucces);

    void updateTimeSesionData(ActualizarUi actualizarUi);

    List<ServiceEnvioUi> getDataForSynck(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaAcademicoId);

    List<CalendarioPeriodoUi> getListCalendarioAcademico(int anioAcademicoId, int programaEducativoId);
}

