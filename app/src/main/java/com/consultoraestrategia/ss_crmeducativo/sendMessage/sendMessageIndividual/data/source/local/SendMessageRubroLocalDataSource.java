package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.SendMessageRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetNotasRubrosStringUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.ui.entities.DataImportantMessageUI;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class SendMessageRubroLocalDataSource implements SendMessageRubroDataSource {

    private static final String TAG = SendMessageRubroLocalDataSource.class.getSimpleName();
    private CompetenciaDao competenciaDao;

    public SendMessageRubroLocalDataSource() {
        competenciaDao = InjectorUtils.provideCompetenciaDao();
    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback) {

        CargaCursos cargaCursos = CargaCursos.getCargaCurso(requestValues.getCargaCursoId());
        if (cargaCursos != null) {
            PlanCursos planCursos = PlanCursos.getPlancursoById(cargaCursos.getPlanCursoId());
            Cursos cursos = Cursos.getCurso(planCursos.getCursoId());
            String nombreCurso = "";
            int cursoId = 0;
            if (cursos != null) {
                nombreCurso = cursos.getNombre();
                cursoId = cursos.getCursoId();
            }

            if (planCursos != null) {
                PlanEstudios planEstudios = SQLite.select()
                        .from(PlanEstudios.class)
                        .where(PlanEstudios_Table.planEstudiosId.is(planCursos.getPlanEstudiosId()))
                        .querySingle();
                if (planEstudios == null) callback.onError("");
                CargaAcademica cargaAcademica = CargaAcademica.getCargaAcadByIdAndPlanEstudio(cargaCursos.getCargaAcademicaId(), planCursos.getPlanEstudiosId());
                if (cargaAcademica != null) {
                    Periodo periodo = Periodo.getPeriodo(cargaAcademica.getPeriodoId());
                    String aliasPeriodo = "";
                    String aliasSeccion = "";
                    if (periodo != null)
                        aliasPeriodo = periodo.getAlias();
                    Seccion seccion = Seccion.getSeccion(cargaAcademica.getSeccionId());
                    if (seccion != null)
                        aliasSeccion = seccion.getNombre();

                    String cargaAcademicaFullName = aliasPeriodo + " - " + aliasSeccion;

                    callback.onGetDataImportantMessageLoaded(new DataImportantMessageUI(cargaAcademicaFullName, aliasPeriodo, aliasSeccion, "", nombreCurso, cursoId));
                } else {
                    callback.onError("");
                }
            }
        }
    }


    @Override
    public void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback callback) {

//        if (requestValues.ge)

        Log.d(TAG, "getIdAlumno: " + requestValues.getIdAlumno());
        Log.d(TAG, "getIdCargaCurso: " + requestValues.getIdCargaCurso());
        Log.d(TAG, "getRubroId: " + requestValues.getRubroId());

        String periodo;

        try {
            String rubroEvaluacionId = requestValues.getRubroId();
            int cargaCursoId = requestValues.getIdCargaCurso();

            int alumnoId = requestValues.getIdAlumno();

            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvaluacionId))
                    .querySingle();


            EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionId))
                    .and(EvaluacionProcesoC_Table.alumnoId.eq(alumnoId))
                    .querySingle();

            double notaCabecera = evaluacionProcesoC.getNota();

            ValorTipoNotaC tipoNotaC = SQLite.select()
                    .from(ValorTipoNotaC.class)
                    .where(ValorTipoNotaC_Table.key.eq(evaluacionProcesoC.getValorTipoNotaId()))
                    .querySingle();
            String logro = "Sin Evaluar";

            if (tipoNotaC != null) logro = tipoNotaC.getAlias();
            if (tipoNotaC == null) Log.d(TAG, "tipoNotaC Header: null ");


            List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaC_ROWS_CONTENT = SQLite.select()
                    .from(RubroEvalRNPFormulaC.class)
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubroEvaluacionId))
                    .queryList();

            RubroEvaluacionProcesoC detalleprimerrubroC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalRNPFormulaC_ROWS_CONTENT.get(0).getRubroEvaluacionSecId()))
                    .querySingle();


            List<ValorTipoNotaC> columnas_HEADERS_COLUMNS = SQLite.select()
                    .from(ValorTipoNotaC.class)
                    .where(ValorTipoNotaC_Table.tipoNotaId.eq(detalleprimerrubroC.getTipoNotaId()))
                    .queryList();

            Collections.sort(columnas_HEADERS_COLUMNS, new Comparator<ValorTipoNotaC>() {
                @Override
                public int compare(ValorTipoNotaC o1, ValorTipoNotaC o2) {
                    String limiteA = String.valueOf(o1.getLimiteSuperior());
                    String limiteB = String.valueOf(o2.getLimiteSuperior());
                    return limiteB.compareTo(limiteA);
                }
            });

            List<String> cuadros = new ArrayList<>();
            cuadros.add("Indicadores");
            cuadros.add("%25");


            for (ValorTipoNotaC valorTipoNotaC : columnas_HEADERS_COLUMNS) {
                String nombre = valorTipoNotaC.getKey();
                double valor = valorTipoNotaC.getValorNumerico();
                int valorEntero = (int) valor;
                cuadros.add(nombre + "-" + valorEntero);
            }

            int columnsSize = columnas_HEADERS_COLUMNS.size() + 2;


            double puntoBase = 0;
            for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC : rubroEvalRNPFormulaC_ROWS_CONTENT) {
                RubroEvaluacionProcesoC rubroEvaluacionProcesoDetalle = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId()))
                        .querySingle();


                String indicadorTitutlo = rubroEvaluacionProcesoDetalle.getTitulo();
                cuadros.add(indicadorTitutlo);
                double peso = itemRubroEvalRNPFormulaC.getPeso();
//                cuadros.add(String.valueOf(peso));

                EvaluacionProcesoC evaluacionProcesoCDetalle = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId()),
                                EvaluacionProcesoC_Table.alumnoId.eq(alumnoId))
//                            EvaluacionProcesoC_Table..eq(alumnoId))
                        .querySingle();


                ValorTipoNotaC VALOR_NOTA_CONTENT = SQLite.select()
                        .from(ValorTipoNotaC.class)
                        .where(ValorTipoNotaC_Table.key.eq(evaluacionProcesoCDetalle.getValorTipoNotaId()))
                        .querySingle();


                if (VALOR_NOTA_CONTENT == null)
                    Log.d(TAG, "getMesnajesPredternidadosUIList: VALOR_NOTA_ITEM - NULL ");
                Log.d(TAG, "getMesnajesPredternidadosUIList: evaluacionProcesoCDetalle ValorTipoNota" + evaluacionProcesoCDetalle.toString());


                Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(rubroEvaluacionProcesoDetalle.getCompetenciaId());

                switch (competencia.getTipoId()) {
                    case Competencia.COMPETENCIA_BASE:
                        cuadros.add("base-" + peso);
                        break;
                    case Competencia.COMPETENCIA_ENFQ:
                        cuadros.add("enfoque-" + peso);
                        break;
                    case Competencia.COMPETENCIA_TRANS:
                        cuadros.add("trans" + peso);
                        break;
                }

                for (ValorTipoNotaC VALOR_NOTA_COLUMN_CONTENT : columnas_HEADERS_COLUMNS) {
                    Log.d(TAG, "getMesnajesPredternidadosUIList: VALOR_NOTA_COLUMN_CONTENT : " + VALOR_NOTA_COLUMN_CONTENT.getKey());
                    if (VALOR_NOTA_COLUMN_CONTENT.getKey().equals(VALOR_NOTA_CONTENT.getKey())) {
                        cuadros.add("x");
                    } else {
                        cuadros.add("");
                    }
                }


                EscalaEvaluacion escalaEvaluacion = SQLite.select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                        .from(EscalaEvaluacion.class)
                        .innerJoin(TipoNotaC.class)
                        .on(TipoNotaC_Table.escalaEvaluacionId.withTable()
                                .eq(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()))
                        .where(TipoNotaC_Table.key.eq(rubroEvaluacionProcesoDetalle.getTipoNotaId()))
                        .querySingle();

                Log.d(TAG, "getMesnajesPredternidadosUIList: escalaEvaluacion " + escalaEvaluacion.toString());

                puntoBase += escalaEvaluacion.getValorMaximo() - escalaEvaluacion.getValorMinimo();

            }


            EscalaEvaluacion escalaEvaluacion = SQLite.select()
                    .from(EscalaEvaluacion.class)
                    .innerJoin(TipoNotaC.class)
                    .on(TipoNotaC_Table.escalaEvaluacionId.withTable()
                            .eq(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()))
                    .where(TipoNotaC_Table.key.eq(rubroEvaluacionProcesoC.getTipoNotaId()))
                    .querySingle();

            double puntosActuales = transformacionInvariante(escalaEvaluacion, escalaEvaluacion.getValorMinimo(), escalaEvaluacion.getValorMaximo(), notaCabecera, 0, puntoBase);

            Log.d(TAG, "getMesnajesPredternidadosUIList:  puntosActuales " + puntosActuales);
            Log.d(TAG, "getMesnajesPredternidadosUIList:  puntoBase " + puntoBase);

            String puntos = (String.valueOf(Math.round(puntosActuales) + "/" + Math.round(puntoBase)));
            double desempenio = puntosActuales / puntoBase;
            Log.d(TAG, "getMesnajesPredternidadosUIList: Puntos " + puntos);
            Log.d(TAG, "getMesnajesPredternidadosUIList: notaCabecera " + notaCabecera);

            CargaCursos cargaCursos = CargaCursos.getCargaCurso(requestValues.getIdCargaCurso());
            String nombreCurso = "";

            String periodoAcad = "";
            String seccion = "";
            String programaEducativo = "";

//            grupo = 1 &
//                    periodo_academico = Secundaria &
//                    seccion = CALEB & punt

            if (cargaCursos != null) {
                PlanCursos planCursos = PlanCursos.getPlancursoById(cargaCursos.getPlanCursoId());
                Cursos cursos = Cursos.getCurso(planCursos.getCursoId());
                if (cuadros != null) nombreCurso = cursos.getNombre();

                PlanEstudios planEstudios = SQLite.select()
                        .from(PlanEstudios.class)
                        .where(PlanEstudios_Table.planEstudiosId.is(planCursos.getPlanEstudiosId()))
                        .querySingle();

                CargaAcademica cargaAcademica = SQLite.select()
                        .from(CargaAcademica.class)
                        .where(CargaAcademica_Table.idPlanEstudio.is(planEstudios.getPlanEstudiosId()))
                        .and(CargaAcademica_Table.cargaAcademicaId.is(cargaCursos.getCargaAcademicaId()))
                        .querySingle();
                Seccion seccionC = Seccion.getSeccion(cargaAcademica.getSeccionId());
                Periodo periodoC = Periodo.getPeriodo(cargaAcademica.getPeriodoId());
                seccion = seccionC.getNombre();
                periodoAcad = periodoC.getAlias();


                ProgramasEducativo programasEducativoCarga = SQLite.select()
                        .from(ProgramasEducativo.class)
                        .where(ProgramasEducativo_Table.programaEduId.is(planEstudios.getProgramaEduId()))
                        .querySingle();
                if (programasEducativoCarga != null)
                    programaEducativo = programasEducativoCarga.getNombre();
            }

            Persona persona = Persona.getPersona(alumnoId);
            String nombreAlumno = "";
            String urlImageProfile = "";
            if (persona != null) nombreAlumno = persona.getNombreCompleto();
            if (persona != null) urlImageProfile = persona.getFoto();
//            constructImgTableUrl(String title, String nombre, String puntos, String desempenio, String logro, int columns, String tableValues)
            callback.onListLoaded(nombreCurso, nombreAlumno, puntos, desempenio, logro, urlImageProfile, programaEducativo, columnsSize, cuadros, seccion, periodoAcad);
            Log.d(TAG, "getMesnajesPredternidadosUIList: " + cuadros.toString());

        } catch (Exception e) {

            callback.onError("Error");
            Log.d(TAG, "getMesnajesPredternidadosUIList: Error AlumnosUi sin evaluacion");
            Log.d(TAG, e.toString());

        }


    }

    private double transformacionInvariante(EscalaEvaluacion escalaEvaluacionUI, double valorMinimo, double valorMaximo, double nota, double valorMinimoDos, double valorMaximoDos) {

        if (escalaEvaluacionUI == null) return escalaEvaluacionUI.getValorMinimo();
        if (nota <= escalaEvaluacionUI.getValorMinimo()) {
            nota = escalaEvaluacionUI.getValorMinimo();
        }
        return Utils.transformacionInvariante(valorMinimo, valorMaximo, nota, valorMinimoDos, valorMaximoDos);
    }

}


