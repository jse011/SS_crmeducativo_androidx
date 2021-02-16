package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.ColumnTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RespGenerarResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RowTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioExternoUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEMatrizResultadoDocente;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BETransResultResponse;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CentroProcesamientoRepositorioImpl implements CentroProcesamientoRepositorio{
    private static final String TAG = CentroProcesamientoRepositorioImpl.class.getSimpleName();
    @Override
    public RetrofitCancel getMatrizResultado(int silaboEveId, int cargaCursoId, int calendarioPerId, int rubroformal, Callback<MatrizResultadoUi> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15, TimeUnit.SECONDS);
        RetrofitCancel<BEMatrizResultadoDocente> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_RegistroEvaluacion(silaboEveId, cargaCursoId, calendarioPerId, rubroformal));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEMatrizResultadoDocente>() {
            @Override
            public void onResponse(BEMatrizResultadoDocente response) {
                if(response == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");
                    List<CabeceraUi> competenciaUiList = new ArrayList<>();
                    for (BEMatrizResultadoDocente.Competencia item : response.competencias){
                        CabeceraUi cabeceraUi = new CabeceraUi();
                        cabeceraUi.setTitulo(item.titulo);
                        cabeceraUi.setRubroResultadoId(item.rubroEvalResultadoId);
                        cabeceraUi.setCompetenciaId(item.competenciaId);
                        competenciaUiList.add(cabeceraUi);
                    }

                    List<ColumnTableRegEvalUi> capacidadUiList = new ArrayList<>();
                    for (BEMatrizResultadoDocente.Capacidad item : response.capacidades){
                        ColumnTableRegEvalUi columnUi = new ColumnTableRegEvalUi();
                        columnUi.setTitulo(item.titulo);
                        columnUi.setRubroResultadoId(item.rubroEvalResultadoId);
                        try {
                            columnUi.setCompetenciaId(Integer.parseInt(item.competenciaId));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        columnUi.setRubroPrincipalId(item.rubroEvaluacionPrinId);
                        columnUi.setParendId(item.parentId);
                        columnUi.setOrden(item.orden);
                        columnUi.setOrden2(item.orden2);
                        capacidadUiList.add(columnUi);
                    }

                    List<RowTableRegEvalUi> alumnosUiList = new ArrayList<>();
                    for (BEMatrizResultadoDocente.Alumno item : response.alumnos){
                        RowTableRegEvalUi rowUi = new RowTableRegEvalUi();
                        rowUi.setNombre(Utils.capitalize(item.nombres));
                        rowUi.setApellidos(Utils.capitalize(item.apellidoPaterno) + " " + Utils.capitalize(item.apellidoMaterno));
                        rowUi.setFoto(item.foto);
                        rowUi.setVigencia(item.vigencia);
                        rowUi.setPersonaId(item.personaId);
                        alumnosUiList.add(rowUi);
                    }

                    List<CellTableRegEvalUi> evaluacionesUiList = new ArrayList<>();
                    for (BEMatrizResultadoDocente.Evaluacion item : response.evaluaciones){
                        CellTableRegEvalUi cellUi = new CellTableRegEvalUi();
                        cellUi.setEvaluacionResultadoId(item.evaluacionResultadoId);
                        cellUi.setAlumnoId(item.alumnoId);
                        cellUi.setRubroEvalResultadoId(item.rubroEvalResultadoId);
                        cellUi.setColor(item.color);
                        cellUi.setNota(item.nota);
                        cellUi.setValorTipoNotaId(item.valorTipoNotaId);
                        cellUi.setTituloNota(item.tituloNota);
                        cellUi.setOrden(item.orden);
                        cellUi.setOrden2(item.orden2);
                        cellUi.setEvaluado(item.evaluado);
                        cellUi.setTipoId(Integer.parseInt(item.tipoId));
                        cellUi.setRFEditable(item.rFEditable);
                        cellUi.setColor(item.color);
                        cellUi.setNotaDup(item.notaDup);
                        cellUi.setConclusionDescriptiva(item.conclusionDescriptiva);
                        evaluacionesUiList.add(cellUi);
                    }
                    MatrizResultadoUi matrizResultadoUi = new MatrizResultadoUi();
                    matrizResultadoUi.setAlumnoEvalList(alumnosUiList);
                    matrizResultadoUi.setCompetenciaList(competenciaUiList);
                    matrizResultadoUi.setCapacidadList(capacidadUiList);
                    matrizResultadoUi.setEvaluacionList(evaluacionesUiList);
                    matrizResultadoUi.setHabilitado(response.Habilitado);
                    matrizResultadoUi.setRangoFecha(response.RangoFecha);
                    matrizResultadoUi.setEstadoCalendarioPeriodoId(response.EstadoCalendarioPeriodoId);
                    matrizResultadoUi.setEstadoCargaCurCalPerId(response.EstadoCargaCurCalPerId);

                    callback.onLoad(true, matrizResultadoUi);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false,null);

                Log.d(TAG,"onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public List<PeriodoUi> getCalendarioPeriodo(int programaId, int anioAcademicoId) {
        List<PeriodoUi> periodoUiList = new ArrayList<>();
        List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                .from(CalendarioPeriodo.class)
                .innerJoin(CalendarioAcademico.class)
                .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()
                        .eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                .where(CalendarioAcademico_Table.estadoId.withTable().in(212, 402))
                .and(CalendarioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .and(CalendarioAcademico_Table.programaEduId.withTable().eq(programaId))
                .groupBy(CalendarioPeriodo_Table.calendarioPeriodoId.withTable())
                .queryList();

        for (CalendarioPeriodo calendarioPeriodo : calendarioPeriodoList){
            PeriodoUi periodoUi = new PeriodoUi();
            periodoUi.setIdCalendarioPeriodo(calendarioPeriodo.getCalendarioPeriodoId());
            Tipos tipos = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();

            periodoUi.setTipoName(tipos!=null?tipos.getNombre():"");

            Calendar hoy = Calendar.getInstance();
            hoy.set(Calendar.MILLISECOND, 0);
            hoy.set(Calendar.SECOND, 0);
            hoy.set(Calendar.MINUTE, 0);
            hoy.set(Calendar.HOUR_OF_DAY, 0);

            Calendar a = Calendar.getInstance();
            a.setTimeInMillis(calendarioPeriodo.getFechaInicio());
            a.set(Calendar.MILLISECOND, 0);
            a.set(Calendar.SECOND, 0);
            a.set(Calendar.MINUTE, 0);
            a.set(Calendar.HOUR_OF_DAY, 0);

            Calendar b = Calendar.getInstance();
            b.setTimeInMillis(calendarioPeriodo.getFechaFin());
            b.set(Calendar.MILLISECOND, 0);
            b.set(Calendar.SECOND, 0);
            b.set(Calendar.MINUTE, 0);
            b.set(Calendar.HOUR_OF_DAY, 0);

            if(a.compareTo(hoy) * hoy.compareTo(b) >= 0){
                periodoUi.setStatus(true);
            }
            periodoUiList.add(periodoUi);
        }

        return periodoUiList;
    }

    @Override
    public RetrofitCancel promediarNotaResultado(int silaboEventoId, int cargaCursoId, int calendarioPeriodoId, int rubroFormal, Callback< List<RespGenerarResultadoUi> > callback) {
        int usuarioId = SessionUser.getCurrentUser().getPersonaId();
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(60,60,60, TimeUnit.SECONDS);
        RetrofitCancel<BETransResultResponse> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.fupd_Resultado(silaboEventoId, cargaCursoId, calendarioPeriodoId, rubroFormal, usuarioId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BETransResultResponse>() {
            @Override
            public void onResponse(BETransResultResponse response) {
                if(response!=null){
                    List<RespGenerarResultadoUi> responseError = new ArrayList<>();
                    if(response.errores!=null){
                        for (BETransResultResponse.Error error : response.errores){
                            RespGenerarResultadoUi respGenerarResultadoUi = new RespGenerarResultadoUi();
                            respGenerarResultadoUi.setAlumnoId(error.PersonaId);
                            respGenerarResultadoUi.setTipo(error.tipo);
                            respGenerarResultadoUi.setResultadoEvalId(error.resultadoEvalId);
                            responseError.add(respGenerarResultadoUi);
                        }
                    }
                    callback.onLoad(true, responseError);
                }else {
                    callback.onLoad(false, null);
                }


            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false, null);
            }
        });

        return retrofitCancel;
    }

    @Override
    public RetrofitCancel updateCargaCursoCalendarioPeriodo(int cargaCursoId, int calendarioPeriodoId, SuccessCallback callback) {
        int usuarioId = SessionUser.getCurrentUser().getPersonaId();
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(30,30,30, TimeUnit.SECONDS);
        RetrofitCancel<Boolean> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.fupd_cerrarCursoDocente(cargaCursoId, calendarioPeriodoId, usuarioId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                if(response!=null){
                    callback.onLoad(response);
                }else {
                    callback.onLoad(false);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false);
            }
        });

        return retrofitCancel;
    }
}
