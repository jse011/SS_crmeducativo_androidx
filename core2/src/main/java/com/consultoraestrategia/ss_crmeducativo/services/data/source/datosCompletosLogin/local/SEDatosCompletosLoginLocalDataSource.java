package com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.local;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.*;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.entities.CanalDestinoEstadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.ColorCondicionalC;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.CuentaCorriente;
import com.consultoraestrategia.ss_crmeducativo.entities.CursoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.DiaHora;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.Estados;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Hora;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioHora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.IndicarLogro;
import com.consultoraestrategia.ss_crmeducativo.entities.Intencion;
import com.consultoraestrategia.ss_crmeducativo.entities.IntencionItem;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeIntencionItemC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursosAlumno;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboCompetencia;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoCompentenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadTipo;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Date;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class SEDatosCompletosLoginLocalDataSource extends ServiceLocalDataSource<SEDatosCompletosLogin> implements SEDatosCompletosLoginDataSource {

    private static final String TAG = SEDatosCompletosLoginLocalDataSource.class.getSimpleName();

    @Override
    public void save(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack) {
        manipulateInTransaction(seDatosCompletosLogin, callBack);
    }

    @Override
    public void saveImportar(final SEDatosCompletosLogin seDatosCompletosLogin, final SuccessCallBack callBack) {
        /*Log.d(TAG, "manipulateInTransaction init time: " + new Date().getTime());
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            BEDatosEnvioAsistencia beDatosEnvioAsistencia = seDatosCompletosLogin.getBeDatosEnvioAsistencia();
            TransaccionUtils.fastStoreListInsert(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(JustificacionC.class, beDatosEnvioAsistencia.getObtenerjustificacion(), databaseWrapper, true);

            //BEDatosEnvioGrupo
            BEDatosEnvioGrupo beDatosEnvioGrupo = seDatosCompletosLogin.getBeDatosEnvioGrupo();
            TransaccionUtils.fastStoreListInsert(EquipoC.class, beDatosEnvioGrupo.getEquipo(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), databaseWrapper, true);

            BEDatosEnvioMensajeria beDatosEnvioMensajeria = seDatosCompletosLogin.getBeDatosEnvioMensajeria();
            TransaccionUtils.fastStoreListInsert(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(UsuarioCanalComunicacion.class, beDatosEnvioMensajeria.getUsCanalComunicacion(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CanalComunicacion.class, beDatosEnvioMensajeria.getCanalComunicacion(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(Intencion.class, beDatosEnvioMensajeria.getIntenciones(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(IntencionItem.class, beDatosEnvioMensajeria.getIntencionItems(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(ListaUsuario.class, beDatosEnvioMensajeria.getListaUsuarios(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(ListaUsuarioDetalle.class, beDatosEnvioMensajeria.getListUsuarioDetalle(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(MensajePredeterminado.class, beDatosEnvioMensajeria.getListaMensajePredeterminado(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(MensajePredeterminadoDetalle.class, beDatosEnvioMensajeria.getListMensajePredeterminadoDetalle(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(MensajePredIntencion.class, beDatosEnvioMensajeria.getListMensajePredIntencion(), databaseWrapper, true);



            BEDatosEnvioTipoNota beDatosEnvioTipoNota = seDatosCompletosLogin.getBeDatosEnvioTipoNota();
            TransaccionUtils.fastStoreListInsert(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, true);

            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso();
            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), databaseWrapper, true);

            GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio = seDatosCompletosLogin.getBeDatosSilaboEventoEnvio();
            TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, beDatosSilaboEventoEnvio.getActividad(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, beDatosSilaboEventoEnvio.getRecursoArchivo(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, beDatosSilaboEventoEnvio.getRecursoReferencia(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, beDatosSilaboEventoEnvio.getTareasRecursos(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, beDatosSilaboEventoEnvio.getRecursoDidactico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareasC.class, beDatosSilaboEventoEnvio.getTareas(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, beDatosSilaboEventoEnvio.getUnidadAprendizaje(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(UnidadTipo.class, beDatosSilaboEventoEnvio.getUnidadTipo(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, beDatosSilaboEventoEnvio.getSesiones(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SilaboEvento.class, beDatosSilaboEventoEnvio.getSilaboEvento(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SilaboEventoCompentenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getSilaboeventocompetenciadesempenioicd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SilaboEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSilaboeventodesempenioicdcampotematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SilaboCompetencia.class, beDatosSilaboEventoEnvio.getSilabocompetencia(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, beDatosSilaboEventoEnvio.getCompetenciaSesion(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_sesion_evento_competencia_desempenioicd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSesion_desempenio_icd_campotematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd_campo_tematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, beDatosSilaboEventoEnvio.getCompetenciaUnidad(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(Competencia.class, beDatosSilaboEventoEnvio.getCompetencias(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_desempenio_icd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(Icds.class, beDatosSilaboEventoEnvio.getIcds(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CampoTematico.class, beDatosSilaboEventoEnvio.getCampoTematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, beDatosSilaboEventoEnvio.getRel_unidad_apren_evento_tipo(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, beDatosSilaboEventoEnvio.getCalendarioPeriodos(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(AreaInstrumento.class, beDatosSilaboEventoEnvio.getObtenerAreaInstrumento(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TipoInstrumento.class, beDatosSilaboEventoEnvio.getObtenerTipoInstrumento(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(Dimension.class, beDatosSilaboEventoEnvio.getObtenerDimension(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(DimensionCaracteristica.class, beDatosSilaboEventoEnvio.getObtenerDimensionCaracteristica(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(DimensionObservada.class, beDatosSilaboEventoEnvio.getObtenerDimensionObservada(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, beDatosSilaboEventoEnvio.getObtenerInstrumentoObservado(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, beDatosSilaboEventoEnvio.getInstrumento_eval(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, beDatosSilaboEventoEnvio.getTareaRubroEvaluacionProceso(), databaseWrapper, true);

            /*GEDatosTareasRecursos geDatosTareasRecursos = beDatosSilaboEventoEnvio.getDatosTareaRecursos();
            TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, geDatosTareasRecursos.getTareasRecursos(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareasC.class, geDatosTareasRecursos.getTareas(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, geDatosTareasRecursos.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), databaseWrapper, true);*/
/*
            SessionUser user = SessionUser.getCurrentUser();
            if (user != null) {
                user.setDataImported(true);
                Log.d(TAG, "getFecha_servidor: " + user.getFechaServidor());
                user.setFechaServidor(beDatosSilaboEventoEnvio.getFecha_servidor());
                user.save();
            } else {
                throw new Error("NO se puede encontrar la SessionUser");
            }
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e) {
            callBack.onResponse(false);
        } finally {
            databaseWrapper.endTransaction();
        }}*/
    }

    @Override
    public void borrarSessionUsuario(SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            SQLite.delete()
                    .from(Entidad.class)
                    .execute();
            SQLite.delete()
                    .from(Georeferencia.class)
                    .execute();
            SQLite.delete()
                    .from(Rol.class)
                    .execute();
            SQLite.delete()
                    .from(UsuarioRolGeoreferencia.class)
                    .execute();
            SQLite.delete()
                    .from(PersonaGeoreferencia.class)
                    .execute();
            SQLite.delete()
                    .from(UsuarioAcceso.class)
                    .execute();
            SQLite.delete()
                    .from(SessionUser.class)
                    .execute();

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e) {
            callBack.onResponse(false);
        } finally {
            databaseWrapper.endTransaction();
        }
    }

    private void manipulateInTransaction(final SEDatosCompletosLogin seDatosCompletosLogin, final SuccessCallBack callBack) {
        Log.d(TAG, "manipulateInTransaction init time: " + new Date().getTime());


        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        appDatabase.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                BEDatosCargaAcademica beDatosCargaAcademica = seDatosCompletosLogin.getBeDatosCargaAcademica();
                TransaccionUtils.fastStoreListInsert(Contrato.class, beDatosCargaAcademica.getContratos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, beDatosCargaAcademica.getDetalleContratoAcad(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, beDatosCargaAcademica.getCargaCursoDocente(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, beDatosCargaAcademica.getCargaCursoDocenteDet(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CargaCursos.class, beDatosCargaAcademica.getCargaCursos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CargaAcademica.class, beDatosCargaAcademica.getCargasAcademicas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Empleado.class, beDatosCargaAcademica.getEmpleados(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(PlanCursos.class, beDatosCargaAcademica.getPlanCursos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Cursos.class, beDatosCargaAcademica.getCursos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Periodo.class, beDatosCargaAcademica.getPeriodos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Seccion.class, beDatosCargaAcademica.getSecciones(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, beDatosCargaAcademica.getCalendarioAcademicos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ProgramasEducativo.class, beDatosCargaAcademica.getProgramasEducativos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(NivelAcademico.class, beDatosCargaAcademica.getNivelesAcademicos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(PlanEstudios.class, beDatosCargaAcademica.getPlanEstudios(), databaseWrapper, true);
                //transaccionUtils.add(TransaccionUtils.fastStoreListInsert(PlanesEstudiosAlumno.class, beDatosCargaAcademica.getPlanEstudiosAlumno(), transaccionUtils);
                TransaccionUtils.fastStoreListInsert(Aula.class, beDatosCargaAcademica.getAulas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, beDatosCargaAcademica.getCalendarioPeriodos(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(Archivo.class, beDatosCargaAcademica.getArchivo(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(Caso.class, beDatosCargaAcademica.getCaso(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(CasoArchivo.class, beDatosCargaAcademica.getCasoArchivo(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(CasoReporte.class, beDatosCargaAcademica.getCasoReporte(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(PersonaGeoOrg.class, beDatosCargaAcademica.getPersonaGeoOrg(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(Organigrama.class, beDatosCargaAcademica.getOrganigrama(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(GeoRefOrganigrama.class, beDatosCargaAcademica.getGeoRefOrganigrama(), databaseWrapper, true);//1

                TransaccionUtils.fastStoreListInsert(TipoEntidadGeo.class, beDatosCargaAcademica.getTipoEntidadGeo(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(Comportamiento.class, beDatosCargaAcademica.getComportamiento(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(DimensionDesarrollo.class, beDatosCargaAcademica.getDimensionDesarrollo(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(DimensionDesarrolloDetalle.class, beDatosCargaAcademica.getDimensionDesarrolloDetalle(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(DimensionDesarrolloEstrategiaEvaluacionC.class, beDatosCargaAcademica.getDimensionDesarrolloEstrategiaEval(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(EstrategiaEvaluacion.class, beDatosCargaAcademica.getEstrategiaEvaluacion(), databaseWrapper, true);//1


                BEDatosEnvioAsistencia beDatosEnvioAsistencia = seDatosCompletosLogin.getBeDatosEnvioAsistencia();
                TransaccionUtils.fastStoreListInsert(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListSetData(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), databaseWrapper, true);

                //BEDatosEnvioGrupo
                BEDatosEnvioGrupo beDatosEnvioGrupo = seDatosCompletosLogin.getBeDatosEnvioGrupo();
                TransaccionUtils.fastStoreListInsert(EquipoC.class, beDatosEnvioGrupo.getEquipo(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), databaseWrapper, true);


                BEDatosEnvioHorario beDatosEnvioHorario = seDatosCompletosLogin.getBeDatosEnvioHorario();
                TransaccionUtils.fastStoreListInsert(Hora.class, beDatosEnvioHorario.getHora(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(HorarioPrograma.class, beDatosEnvioHorario.getHorarioPrograma(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(HorarioHora.class, beDatosEnvioHorario.getHorarioHora(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DetalleHorario.class, beDatosEnvioHorario.getDetalleHorario(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Dia.class, beDatosEnvioHorario.getDia(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Horario.class, beDatosEnvioHorario.getHorario(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(HorarioDia.class, beDatosEnvioHorario.getHorarioDia(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CursosDetHorario.class, beDatosEnvioHorario.getCursosDetHorario(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DiaHora.class, beDatosEnvioHorario.getObtenerDiaHora(), databaseWrapper, true);

                BEDatosEnvioMensajeria beDatosEnvioMensajeria = seDatosCompletosLogin.getBeDatosEnvioMensajeria();
                TransaccionUtils.fastStoreListInsert(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(UsuarioCanalComunicacion.class, beDatosEnvioMensajeria.getUsCanalComunicacion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CanalComunicacion.class, beDatosEnvioMensajeria.getCanalComunicacion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Intencion.class, beDatosEnvioMensajeria.getIntenciones(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(IntencionItem.class, beDatosEnvioMensajeria.getIntencionItems(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ListaUsuario.class, beDatosEnvioMensajeria.getListaUsuarios(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ListaUsuarioDetalle.class, beDatosEnvioMensajeria.getListUsuarioDetalle(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(MensajePredeterminado.class, beDatosEnvioMensajeria.getListaMensajePredeterminado(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(MensajePredeterminadoDetalle.class, beDatosEnvioMensajeria.getListMensajePredeterminadoDetalle(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(MensajePredIntencion.class, beDatosEnvioMensajeria.getListMensajePredIntencion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), databaseWrapper, false);

                BEDatosEnvioTipoNota beDatosEnvioTipoNota = seDatosCompletosLogin.getBeDatosEnvioTipoNota();
                TransaccionUtils.fastStoreListInsert(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, true);

                /*BEDatosEvaluacionResultado beDatosEvaluacionResultado = seDatosCompletosLogin.getBeDatosEvaluacionResultado();
                TransaccionUtils.fastStoreListInsert(RubroEvaluacionResultado.class, beDatosEvaluacionResultado.getRubroEvaluacionResultado(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RubroEvalRNRFormula.class, beDatosEvaluacionResultado.getRubroEvalResultadoFormula(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EvaluacionResultado.class, beDatosEvaluacionResultado.getEvaluacionResultado(), databaseWrapper, true);*/

                BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso();
                TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ComentarioPredeterminado.class, beDatosRubroEvaluacionProceso.getComentarioPredeterminado(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), databaseWrapper, true);

                GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio = seDatosCompletosLogin.getBeDatosSilaboEventoEnvio();
                TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, beDatosSilaboEventoEnvio.getActividad(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, beDatosSilaboEventoEnvio.getRecursoArchivo(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, beDatosSilaboEventoEnvio.getRecursoReferencia(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, beDatosSilaboEventoEnvio.getTareasRecursos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, beDatosSilaboEventoEnvio.getRecursoDidactico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareasC.class, beDatosSilaboEventoEnvio.getTareas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, beDatosSilaboEventoEnvio.getUnidadAprendizaje(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(UnidadTipo.class, beDatosSilaboEventoEnvio.getUnidadTipo(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, beDatosSilaboEventoEnvio.getSesiones(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SilaboEvento.class, beDatosSilaboEventoEnvio.getSilaboEvento(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SilaboEventoCompentenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getSilaboeventocompetenciadesempenioicd(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SilaboEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSilaboeventodesempenioicdcampotematico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Desempenio.class, beDatosSilaboEventoEnvio.getObtenerDesempenio(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SilaboCompetencia.class, beDatosSilaboEventoEnvio.getSilabocompetencia(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, beDatosSilaboEventoEnvio.getCompetenciaSesion(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_sesion_evento_competencia_desempenioicd(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSesion_desempenio_icd_campotematico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd_campo_tematico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, beDatosSilaboEventoEnvio.getCompetenciaUnidad(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Competencia.class, beDatosSilaboEventoEnvio.getCompetencias(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_desempenio_icd(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Icds.class, beDatosSilaboEventoEnvio.getIcds(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CampoTematico.class, beDatosSilaboEventoEnvio.getCampoTematico(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, beDatosSilaboEventoEnvio.getRel_unidad_apren_evento_tipo(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(AreaInstrumento.class, beDatosSilaboEventoEnvio.getObtenerAreaInstrumento(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TipoInstrumento.class, beDatosSilaboEventoEnvio.getObtenerTipoInstrumento(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Dimension.class, beDatosSilaboEventoEnvio.getObtenerDimension(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DimensionCaracteristica.class, beDatosSilaboEventoEnvio.getObtenerDimensionCaracteristica(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(DimensionObservada.class, beDatosSilaboEventoEnvio.getObtenerDimensionObservada(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, beDatosSilaboEventoEnvio.getObtenerInstrumentoObservado(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, beDatosSilaboEventoEnvio.getInstrumento_eval(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, beDatosSilaboEventoEnvio.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, beDatosSilaboEventoEnvio.getObtenerCalendarioPeriodoDetalle(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, beDatosSilaboEventoEnvio.getObtenerCargaCursosCalendarioPeriodo(), databaseWrapper, true);//1
                TransaccionUtils.fastStoreListInsert(ProductoEventoReferencia.class, beDatosSilaboEventoEnvio.getProducto_evento_referencia(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ProductoAprendizajeEvento.class, beDatosSilaboEventoEnvio.getProductoAprendizaje(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumento.class, beDatosSilaboEventoEnvio.getObtenerUnidadEventoCompetenciaDesempenioICDInstrumento(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica.class, beDatosSilaboEventoEnvio.getObtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TecnicaEvaluacion.class, beDatosSilaboEventoEnvio.getTecnicaEvaluacion(), databaseWrapper, true);

                TransaccionUtils.fastStoreListInsert(ProductoEventoCampoTematico.class, beDatosSilaboEventoEnvio.getObtenerProductoEventoCampoTematico(), databaseWrapper,true);
                /*GEDatosTareasRecursos geDatosTareasRecursos = beDatosSilaboEventoEnvio.getDatosTareaRecursos();
                TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, geDatosTareasRecursos.|(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareasC.class, geDatosTareasRecursos.getTareas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, geDatosTareasRecursos.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), databaseWrapper, true);*/

                BEObtenerDatosLogin beObtenerDatosLogin = seDatosCompletosLogin.getBeObtenerDatosLogin();
                TransaccionUtils.fastStoreListInsert(AnioAcademico.class, beObtenerDatosLogin.getAnioAcademicos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CuentaCorriente.class, beObtenerDatosLogin.getCuentaCorriente(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(PlanCursosAlumno.class, beObtenerDatosLogin.getPlanCursosAlumno(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Estados.class, beObtenerDatosLogin.getEstados(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Tipos.class, beObtenerDatosLogin.getTipos(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Persona.class, beObtenerDatosLogin.getPersonas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Relaciones.class, beObtenerDatosLogin.getRelaciones(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(CursoCompetencia.class, beObtenerDatosLogin.getCursoCompetencias(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ColorCondicionalC.class, beObtenerDatosLogin.getColorCondicional(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(IndicarLogro.class, beObtenerDatosLogin.getIndicarLogro(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Usuario.class, beObtenerDatosLogin.getUsuariosrelacionados(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ParametrosDisenio.class, beObtenerDatosLogin.getObtener_parametros_disenio(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Rutas.class, beObtenerDatosLogin.getRutas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(ParametroConfiguracion.class, beObtenerDatosLogin.getParametroConfiguracions(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(Ubicaciones.class, beObtenerDatosLogin.getUbicaciones(), databaseWrapper, true);


                SessionUser user = SessionUser.getCurrentUser();
                if (user != null) {
                    user.setDataImported(true);
                    user.setFechaServidor(beDatosCargaAcademica.getFecha_servidor());
                    Log.d(getClass().toString(),  String.valueOf(beDatosCargaAcademica.getFecha_servidor()));
                    user.save();
                } else {
                    throw new Error("NO se puede encontrar la SessionUser");
                }
            }

        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callBack.onResponse(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.onResponse(false);
            }
        }).build().execute();
    }



}
