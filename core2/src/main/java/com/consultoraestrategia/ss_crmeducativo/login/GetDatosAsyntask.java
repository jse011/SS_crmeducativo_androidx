package com.consultoraestrategia.ss_crmeducativo.login;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.asyntasks.GetDatosInterface;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.BEDatosEnvio;
import com.consultoraestrategia.ss_crmeducativo.entities.CanalDestinoEstadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.ColorCondicionalC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.CuentaCorriente;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.Estados;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Hora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioHora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.IndicarLogro;
import com.consultoraestrategia.ss_crmeducativo.entities.Intencion;
import com.consultoraestrategia.ss_crmeducativo.entities.IntencionItem;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeIntencionItemC;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursosAlumno;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboCompetencia;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoCompentenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadTipo;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;


/**
 * Created by irvinmarin on 28/04/2017.
 */

public class GetDatosAsyntask extends AsyncTask<Integer, Integer, String> {
    private static final String TAG = "GetDatosAsyntask";

    private JSONObject jsonObject = null;
    private GetDatosInterface listener;
    private RestAPI restAPI;
    private Context context;
    private ObjectMapper mapper;
    private BEDatosEnvio beDatosEnvio;
    private int estado = -1;
    DatabaseDefinition database;

    public GetDatosAsyntask(GetDatosInterface getDatosInterface, Context context) {
        Log.d(TAG, "GetDatosAsyntask");
        this.listener = getDatosInterface;
        database = FlowManager.getDatabase(AppDatabase.class);
        restAPI = new RestAPI();
        this.context = context;
    }

    int count = 1;

    @Override
    protected String doInBackground(Integer... params) {
        Log.d(TAG, "doInBackground time: " + new Date().getTime());
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            fireListenerInf(GetUsuarioUI.INF_LOADING_CONSUMING_API);

            jsonObject = restAPI.flst_ObtenerDatos(params[0]);

            Log.d(TAG, "json returned time: " + new Date().getTime());


            if (Utils.isSuccess(jsonObject)) {
                Log.d(TAG, "mapper readValue time: " + new Date().getTime());
                fireListenerInf(GetUsuarioUI.INF_LOADING_PARSING_RESPONSE);
                beDatosEnvio = mapper.readValue(jsonObject.optString("Value"), BEDatosEnvio.class);
                //JsonNode jsonNode = convertJsonFormat(jsonObject);
                //beDatosEnvio = mapper.readValue(new TreeTraversingParser(jsonNode), BEDatosEnvio.class);
                Log.d(TAG, "mapper readValue end time: " + new Date().getTime());
                if (beDatosEnvio != null) {
                    manipulateInTransaction(params[0]);
                } else {
                    estado = -1;
                }
            } else {
                estado = -2;
            }
        } catch (Exception e) {
            estado = -2;
        }
        //  }
        return null;
    }


    private <T extends BaseModel> void fastStoreList(final Class<T> clazz, List<T> list) {
        if (list != null && !list.isEmpty()) {
            FastStoreModelTransaction fastStoreModelTransaction = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(clazz))
                    .addAll(list)
                    .build();
            database
                    .beginTransactionAsync(fastStoreModelTransaction)
                    .error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            Log.d(TAG, "fastStoreList onError: " + error.getMessage());
                            estado = -1;
                            listener.GetDatosError("Error");
                        }
                    })
                    .build()
                    .execute();
        }
    }


    private void fireListenerInf(int informationLoadingType) {
        if (listener != null) {
            listener.onProgressInformationChanged(informationLoadingType);
        }
    }

    private void manipulateInTransaction(int idUs) {
        Log.d(TAG, "manipulateInTransaction init time: " + new Date().getTime());

        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                fastStoreList(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosEnvio.getRel_unidad_evento_competencia_desempenio_icd());

                fastStoreList(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosEnvio.getRel_unidad_evento_competencia_desempenio_icd());
                fastStoreList(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, beDatosEnvio.getRel_unidad_evento_competencia_desempenio_icd_campo_tematico());
                fastStoreList(AnioAcademico.class, beDatosEnvio.getAnioAcademicos());
                fastStoreList(CargaAcademica.class, beDatosEnvio.getCargasAcademicas());
                fastStoreList(CargaCursos.class, beDatosEnvio.getCargaCursos());
                fastStoreList(Empleado.class, beDatosEnvio.getEmpleados());
                fastStoreList(PlanCursos.class, beDatosEnvio.getPlanCursos());
                fastStoreList(Cursos.class, beDatosEnvio.getCursos());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_CURSOS);
                fastStoreList(Periodo.class, beDatosEnvio.getPeriodos());
                fastStoreList(Seccion.class, beDatosEnvio.getSecciones());
                fastStoreList(Aula.class, beDatosEnvio.getAulas());
                fastStoreList(CuentaCorriente.class, beDatosEnvio.getCuentaCorriente());
                fastStoreList(NivelAcademico.class, beDatosEnvio.getNivelesAcademicos());
                fastStoreList(ProgramasEducativo.class, beDatosEnvio.getProgramasEducativos());
                fastStoreList(PlanEstudios.class, beDatosEnvio.getPlanEstudios());
                fastStoreList(PlanCursosAlumno.class, beDatosEnvio.getPlanCursosAlumno());
                fastStoreList(Estados.class, beDatosEnvio.getEstados());
                fastStoreList(Tipos.class, beDatosEnvio.getTipos());
                fastStoreList(Persona.class, beDatosEnvio.getPersonas());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_PERSONAS);
                fastStoreList(Relaciones.class, beDatosEnvio.getRelaciones());
                fastStoreList(Competencia.class, beDatosEnvio.getCompetencias());
                //fastStoreList(CursoCompetencia.class, beDatosEnvio.getCursoCompetencias());
                fastStoreList(CalendarioAcademico.class, beDatosEnvio.getCalendarioAcademicos());
                fastStoreList(CalendarioPeriodo.class, beDatosEnvio.getCalendarioPeriodos());
                fastStoreList(Hora.class, beDatosEnvio.getHora());
                fastStoreList(HorarioPrograma.class, beDatosEnvio.getHorarioPrograma());
                fastStoreList(HorarioHora.class, beDatosEnvio.getHorarioHora());
                fastStoreList(CursosDetHorario.class, beDatosEnvio.getCursosDetHorario());
                fastStoreList(DetalleHorario.class, beDatosEnvio.getDetalleHorario());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_PERIODOS);
                fastStoreList(Dia.class, beDatosEnvio.getDia());
                fastStoreList(HorarioDia.class, beDatosEnvio.getHorarioDia());
                fastStoreList(Usuario.class, beDatosEnvio.getUsuariosrelacionados());
                fastStoreList(AsistenciaSesionAlumnoC.class, beDatosEnvio.getAsistenciaAlumnos());
                fastStoreList(Contrato.class, beDatosEnvio.getContratos());
                Log.d(TAG, "manipulate end Contrato....");
                fastStoreList(DetalleContratoAcad.class, beDatosEnvio.getDetalleContratoAcad());
                Log.d(TAG, "manipulate end DetalleContratoAcad....");
                fastStoreList(RecursoDidacticoEventoC.class, beDatosEnvio.getRecursoDidactico());
                Log.d(TAG, "manipulate end RecursoDidacticoEvento....");
                fastStoreList(TipoNotaC.class, beDatosEnvio.getTipoNota());
                Log.d(TAG, "manipulate end TipoNotaC....");
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_USUARIOS);
                fastStoreList(ValorTipoNotaC.class, beDatosEnvio.getValorTipoNota());
                Log.d(TAG, "manipulate end ValorTipoNotaC....");
                fastStoreList(ColorCondicionalC.class, beDatosEnvio.getColorCondicional());


                fastStoreList(RubroEvaluacionResultado.class, beDatosEnvio.getRubroEvaluacionResultado());
                fastStoreList(RubroEvalRNRFormula.class, beDatosEnvio.getRubroEvalResultadoFormula());
                fastStoreList(RubroEvalRNPFormulaC.class, beDatosEnvio.getRubroEvalProcesoFormula());
                fastStoreList(EvaluacionResultadoC.class, beDatosEnvio.getEvaluacionResultado());
                Log.d(TAG, "manipulate end EvaluacionResultado....");
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_RESULTADO);
                fastStoreList(RubroEvaluacionProcesoC.class, beDatosEnvio.getRubroEvaluacionProceso());
                fastStoreList(ActividadAprendizaje.class, beDatosEnvio.getActividad());
                fastStoreList(SesionAprendizaje.class, beDatosEnvio.getSesiones());
                fastStoreList(CampoTematico.class, beDatosEnvio.getCampoTematico());
                fastStoreList(CompetenciaUnidad.class, beDatosEnvio.getCompetenciaUnidad());
                fastStoreList(IndicarLogro.class, beDatosEnvio.getIndicarLogro());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_CAMPO_TEMATICO);
//                fastStoreList(TareasRecursosC.class, beDatosEnvio.getTareasRecursos());
//                fastStoreList(TareasC.class, beDatosEnvio.getTareas());
                fastStoreList(SilaboCompetencia.class, beDatosEnvio.getSilabocompetencia());
                fastStoreList(UnidadTipo.class, beDatosEnvio.getUnidadTipo());
                fastStoreList(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, beDatosEnvio.getCompetenciaSesion());

                fastStoreList(CanalDestinoEstadoC.class, beDatosEnvio.getCanalDestinoEstado());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_SILABO_COMPETENCIA);
                fastStoreList(MensajeIntencionItemC.class, beDatosEnvio.getMensajeIntencionItem());
                fastStoreList(Intencion.class, beDatosEnvio.getIntenciones());
                fastStoreList(IntencionItem.class, beDatosEnvio.getIntencionItems());
                fastStoreList(ListaUsuario.class, beDatosEnvio.getListaUsuarios());
                fastStoreList(ListaUsuarioDetalle.class, beDatosEnvio.getListUsuarioDetalle());
                fastStoreList(CanalComunicacion.class, beDatosEnvio.getCanalComunicacion());
                fastStoreList(UsuarioCanalComunicacion.class, beDatosEnvio.getUsCanalComunicacion());
                fastStoreList(SilaboEvento.class, beDatosEnvio.getSilaboEvento());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_SILABO_EVENTO);
                fastStoreList(UnidadAprendizaje.class, beDatosEnvio.getUnidadAprendizaje());
                fastStoreList(RecursoReferenciaC.class, beDatosEnvio.getRecursoReferencia());
                fastStoreList(AsistenciaSesionAlumnoC.class, beDatosEnvio.getAsistenciaAlumnos());
                fastStoreList(EscalaEvaluacion.class, beDatosEnvio.getEscalaEvaluacion());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_ASISTENCIA_ALUMNOS);

                fastStoreList(EvaluacionProcesoC.class, beDatosEnvio.getEvaluacionProceso());

                fastStoreList(Icds.class, beDatosEnvio.getIcds());
                fastStoreList(SesionEventoDesempenioIcd.class, beDatosEnvio.getRel_sesion_desempenio_icd());
                fastStoreList(DesempenioIcd.class, beDatosEnvio.getRel_desempenio_icd());
                fastStoreList(SesionEventoCompetenciaDesempenioIcd.class, beDatosEnvio.getRel_sesion_evento_competencia_desempenioicd());
                fastStoreList(SesionEventoDesempenioIcdCampotematico.class, beDatosEnvio.getSesion_desempenio_icd_campotematico());
                fastStoreList(SilaboEventoCompentenciaDesempenioIcd.class, beDatosEnvio.getSilaboeventocompetenciadesempenioicd());
                fastStoreList(SilaboEventoDesempenioIcdCampotematico.class, beDatosEnvio.getSilaboeventodesempenioicdcampotematico());
                fastStoreList(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, beDatosEnvio.getRel_unidad_apren_evento_tipo());
                fastStoreList(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvio.getRn_mae_tipo_evaluacion());
                fireListenerInf(GetUsuarioUI.INFO_LOADING_PARGING_INDICADORES);
                fastStoreList(ParametrosDisenio.class, beDatosEnvio.getObtener_parametros_disenio());

                fastStoreList(MensajeC.class, beDatosEnvio.getMensajes());
                fastStoreList(MensajeUsuarioC.class, beDatosEnvio.getMensajeUsuario());


//                // publishProgress();
//                if (beDatosEnvio.getTareasRecursos() != null && !beDatosEnvio.getTareasRecursos().isEmpty()) {
//                    for (TareasRecursos tareasRecursos : beDatosEnvio.getTareasRecursos()) {
//                        tareasRecursos.setEstadoExportado(1);
//                        tareasRecursos.save();
//                    }
//                }


                //Guardar tabla tareas y asiganas los recursos a cada tarea
//                if (beDatosEnvio.getTareas() != null && !beDatosEnvio.getTareas().isEmpty()) {
//                    for (Tareas model : beDatosEnvio.getTareas()) {
//                        model.setEstadoExportado(1);
//                        //importar tabla Tareas
//                        model.save();
//                        //obtener idTareaAndroid
//                        Tareas tareasSaved = SQLite.select()
//                                .from(Tareas.class)
//                                .where(Tareas_Table.tareaId.is(model.getTareaId()))
//                                .querySingle();
//                        if (tareasSaved == null) break;
//                        //listar Tareas Recursos por TareaID
//                        List<TareasRecursos> tareasRecursosList = SQLite.select()
//                                .from(TareasRecursos.class)
//                                .where(TareasRecursos_Table.tareaId.is(tareasSaved.getTareaId()))
//                                .queryList();
//                        for (TareasRecursos tareasRecursos : tareasRecursosList) {
//                            //buscar recrusosDicdacticos por recursoDidactico de tabla de muchos a muchos
//                            RecursoDidacticoEvento recursoDidacticoEvento = SQLite.select()
//                                    .from(RecursoDidacticoEvento.class)
//                                    .where(RecursoDidacticoEvento_Table.recursoDidacticoId.is(tareasRecursos.getRecursoDidacticoId()))
//                                    .querySingle();
//                            //setear TareaIdAndroid
//                            if (recursoDidacticoEvento != null) {
//                                tareasRecursos.setTareaIdAndroid(tareasSaved.getTareaIdAndroid());
//                                //setear recursodidacticoIdAndroid
//                                tareasRecursos.setRecursoDidacticoIdAndroid(recursoDidacticoEvento.getRecursoDidacticoIdAndroid());
//                                //guardando tareasRecursos
//                                tareasRecursos.save();
//                            }
//
//                        }
//                    }
//                }

                Log.d(TAG, "manipulate end....");
            }
        })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(@NonNull Transaction transaction) {
                        Log.d(TAG, "manipulate onSuccess");
                        estado = 1;
                        if(listener!=null)listener.GetDatosCorrecto("Success");
                    }
                })
                .error(new Transaction.Error() {
                    @Override
                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                        Log.d(TAG, "manipulate onError");
                        estado = -1;
                        if(listener!=null)listener.GetDatosError("Error");
                    }
                })
                .build();
        transaction.execute(); // execute
        //Log.d(TAG, "Objeto: " + beDatosEnvio.toString());
        Log.d(TAG, "manipulateInTransaction end time: " + new Date().getTime());
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onPostExecute(String s) {
        /*switch (estado) {
            case 1:
                listener.GetDatosCorrecto("SuccessFull");
                break;
            case -1:
                listener.GetDatosError("Error: No se exportaron los datos al Servidor");
                break;
            case -2:
                listener.GetDatosErrorProcedimiento("Error -2 ");
        }*/
        Log.d(TAG, "estado: " + estado);
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate : " + values[0]);
    }


}