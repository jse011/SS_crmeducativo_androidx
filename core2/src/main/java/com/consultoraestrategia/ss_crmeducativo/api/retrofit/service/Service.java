package com.consultoraestrategia.ss_crmeducativo.api.retrofit.service;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeData;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeData2;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeUser;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeAdminService;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroNotificacion;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosChangePortalAlumno;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosEventos;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportarGlobal;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosImportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosRegistroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroLogin;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.entities.retrofit.BECambiosMovilFb;
import com.consultoraestrategia.ss_crmeducativo.entities.retrofit.BERubroEvalEnvioSimple;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosCasos;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosEstudiante;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosGrupo;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosInicioSesion;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosResultado;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosRubro;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosTarea;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosTipoNota;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosUnidades;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDrive;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEMatrizResultadoDocente;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BETransResultResponse;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by SCIEV on 24/07/2018.
 */

public interface Service {
    @POST(" ")
    Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> flst_ObtenerRubroEvaluacionProceso(@Body RequestBody body);
    @POST(" ")
    Call<RestApiResponse<Long>> fins_ListarSesion_FechaActualizacion(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosSesionAprendizaje>> flst_ObtenerSesionByUnidad(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Persona>> flst_ObtenerPersona(@Body ApiRetrofit.ApiRequestBody<ParametroChangeUser> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<AdminService>> f_BuscarUsuarioCent(@Body ApiRetrofit.ApiRequestBody<ParametroChangeAdminService> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarSesion_Android(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<BEDatosSesionAprendizaje>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosSesionAprendizaje>> fins_ListarSesiones(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    @Streaming
    Call<ResponseBody> flst_ObtenerRubroEvaluacionProceso2(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarEntidades_Global(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);

    @POST(" ")
    @Multipart
    Call<String> uploadFile(@Part() MultipartBody.Part file, @Part("name") RequestBody name,  @Part("optionTipos") RequestBody tipo1, @Part("option") RequestBody tipo2);
    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);
    @POST(" ")
    @Multipart
    Call<String> uploadFileAlumno(@Part() MultipartBody.Part fileToUpload, @Part("url2") RequestBody url, @Part("alumnoId") RequestBody alumnoId, @Part("option") RequestBody tipo2);
    @POST(" ")
    Call<RestApiResponse<BEDatosInicioSesion>> flst_getDatosInicioSesion(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosUnidades>> flst_getDatosUnidadesInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosTipoNota>> flst_getDatosTipoNotaInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEstudiante>> flst_getDatosEstudianteInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosRubro>> flst_getDatosRubricaInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosResultado>> flst_getDatosResultadoInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosRubro>> flst_getDatosRubroInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosRubro>> flst_getDatosFormulaInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosGrupo>> getDatosGrupoInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosTarea>> flst_getDatosTareaInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosCasos>> flst_getDatosCasosInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDimensionDesarrollo>> flst_getDatosDimensionDesarolloInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEstudiante>> flst_getDatosEstudianteComplejoInicial(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosAnioAcademico>> flst_getDatosAnioAcademico(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosUnidades>> flst_getDatosUnidades(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosAnioAcademico>> flst_getDatosCalendarioPeriodo(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEEventos>> flst_ObtenerCalendarioEventoDocente(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<String>> flst_saveEvento(@Body ApiRetrofit.ApiRequestBody<ParametrosEventos> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosContacto>> flst_getDatosContacto(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData2> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<String>> flst_Notificacion(@Body ApiRetrofit.ApiRequestBody<ParametroNotificacion> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<List<BERubroEvalEnvioSimple>>> fins_GuardarRubroEvaluacion(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Integer>> fupd_SimplePersonas(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<List<WebConfig>>> getWebConfig(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<List<BECambiosMovilFb>>> getCambiosFirebase(@Body ApiRetrofit.ApiRequestBody<ParametrosChangePortalAlumno> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosRubro>> flst_getDatosRubroIds(@Body ApiRetrofit.ApiRequestBody<ParametrosChangePortalAlumno> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEEventos>> flst_saveEvento2(@Body ApiRetrofit.ApiRequestBody<ParametrosEventos> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<List<BECambiosMovilFb>>> getCambiosResultado(@Body ApiRetrofit.ApiRequestBody<ParametrosChangePortalAlumno> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Usuario>> fobj_ObtenerUsuario(@Body ApiRetrofit.ApiRequestBody<ParametroChangeUser> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEMatrizResultadoDocente>> flst_RegistroEvaluacion(@Body ApiRetrofit.ApiRequestBody<ParametrosRegistroEvaluacion> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BETransResultResponse>> fupd_Resultado(@Body ApiRetrofit.ApiRequestBody<ParametrosRegistroEvaluacion> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Boolean>> fupd_cerrarCursoDocente(@Body ApiRetrofit.ApiRequestBody<ParametrosRegistroEvaluacion> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarEntidadesGlobalSimplevJse(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDrive>> getUrlDriveArchivo(@Body RequestBody body);
}
