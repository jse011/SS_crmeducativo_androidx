package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado.RubroEvaluacionResultadoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.SilaboEventoCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.EvaluacionResultadoQuery;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.HijosFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.PadreFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.RubroEvalRNRFormulaUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciaLocal implements EvaluacionCompetenciaDataSource {

    private final static String EVALUACION_COMPETENCIA_TAG = EvaluacionCompetenciaLocal.class.getSimpleName();

    private RubroEvaluacionResultadoDao resultadoDao;
    private ParametrosDisenioDao parametrosDisenioDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;
    private PersonaDao personaDao;

    public EvaluacionCompetenciaLocal(RubroEvaluacionResultadoDao resultadoDao, ParametrosDisenioDao parametrosDisenioDao,  CalendarioPeriodoDao calendarioPeriodoDao,PersonaDao personaDao) {
        this.resultadoDao = resultadoDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
        this.calendarioPeriodoDao=calendarioPeriodoDao;
        this.personaDao = personaDao;
    }

    @Override

    public void getCompenteciaLista(int periodoId, int cargaCursoId, int cursoId, List<FiltradoUi> filtradoUiList, CallBack callBack) {

      Log.d(EVALUACION_COMPETENCIA_TAG, "GETCOMPETENCIAlIST  :  periodoId : " + periodoId + "  /  " + cargaCursoId + " / cursoId " + cursoId);
        SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                .select(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(cargaCursoId)
                .querySingle();

        if (silaboEvento == null) {
            callBack.onLista(new ArrayList<Object>());
            return;
        }
        List<Object> items = new ArrayList<>();
        //Si el Periodo == 0
        if (periodoId == 0) {

        } else {

        /*Lista de Evaluacion Competencia-Padre */
            Where<Competencia> competenciaWhere = SQLite.select()
                    .from(Competencia.class)
                    .innerJoin(RubroEvaluacionResultado.class)
                    .on(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(Competencia_Table.competenciaId.withTable()))
                    .innerJoin(CalendarioPeriodo.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                    .innerJoin(SilaboEvento.class)
                    .on(SilaboEvento_Table.silaboEventoId.withTable().eq(RubroEvaluacionResultado_Table.silaboEventoId.withTable()))
                    .where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEvento.getSilaboEventoId()))
                    .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                    .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(periodoId));

            Set<Integer> integerList = new LinkedHashSet<>();
            for (FiltradoUi filtradoUi: filtradoUiList){
                switch (filtradoUi.getTipo()){
                    case COMPETENCIA_BASE:
                        if(filtradoUi.isaBoolean())integerList.add(347);
                        break;
                    case COMPETENCIA_TRANSVERSAL_ENFOQUE:
                        if(filtradoUi.isaBoolean())integerList.add(348);
                        if(filtradoUi.isaBoolean())integerList.add(349);
                        break;
                }
            }

            List<Competencia> competenciaList = competenciaWhere.and(Competencia_Table.tipoId.withTable().in(integerList))
                    .and(Competencia_Table.superCompetenciaId.withTable().is(0))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .queryList();

           // Log.d(EVALUACION_COMPETENCIA_TAG, "Contar : " + competenciaList.size());



            List<HijosFinalBimestreUi> hijosFinalBimestres = new ArrayList<>();
            PadreFinalBimestreUi padreFinalBimestre = new PadreFinalBimestreUi();

            padreFinalBimestre.setNombre("FINAL BIMESTRE ");
            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
            ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
            parametroDisenioUi.setColor3(parametroDisenioUi.getColor3());
            padreFinalBimestre.setColor(parametrosDisenio.getColor1());
            int contadorFinalBimestre = 0;
            List<RubroEvalRNRFormulaUi> rnrFormulaUiList = null;

            for (Competencia compentenciaQueryModel : competenciaList) {

//                Log.d(EVALUACION_COMPETENCIA_TAG, "getSuperCompetenciaId : " + compentenciaQueryModel.getSuperCompetenciaId());
//                Log.d(EVALUACION_COMPETENCIA_TAG, "getCompetenciaId : " + compentenciaQueryModel.getCompetenciaId());
//
//                Log.d(EVALUACION_COMPETENCIA_TAG, "competenciaList : " + compentenciaQueryModel.getNombre());
                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setNombre(compentenciaQueryModel.getNombre());
                competenciaUi.setCompetenciaId(compentenciaQueryModel.getCompetenciaId());
                competenciaUi.setSuperComeptenciaId(compentenciaQueryModel.getSuperCompetenciaId());
                competenciaUi.setStado(compentenciaQueryModel.isToogle());
                Log.d(EVALUACION_COMPETENCIA_TAG, "cargaCursoId : " + cargaCursoId);
                competenciaUi.setParametroDisenioUi(parametroDisenioUi);
                competenciaUi.setCargaCursoId(cargaCursoId);
                /*Lista de Evaluacion Competencia-Hijo - Capacidad*/
                List<RubroEvaluacionResultado> capacidadList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                        .from(RubroEvaluacionResultado.class)
                        .innerJoin(SilaboEvento.class)
                        .on(SilaboEvento_Table.silaboEventoId.withTable().eq(RubroEvaluacionResultado_Table.silaboEventoId.withTable()))
                        .innerJoin(CalendarioPeriodo.class)
                        .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                        .innerJoin(Competencia.class)
                        .on(Competencia_Table.competenciaId.withTable().eq(RubroEvaluacionResultado_Table.competenciaId.withTable()))
                        .where(RubroEvaluacionResultado_Table.silaboEventoId.withTable().is(silaboEvento.getSilaboEventoId()))
                        .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().is(periodoId))
                        .and(Competencia_Table.superCompetenciaId.withTable().is(compentenciaQueryModel.getCompetenciaId()))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                        .queryList();

              //  Log.d(EVALUACION_COMPETENCIA_TAG, "competenciaList : " + capacidadList.size());

                int contadorCapacidad = 0;
                List<CapacidadUi> capacidadUiList = new ArrayList<>();

                for (RubroEvaluacionResultado CapacidadQueryModel : capacidadList) {
                    contadorCapacidad++;
                    Log.d(EVALUACION_COMPETENCIA_TAG, "listaIdCapacidades : " + CapacidadQueryModel.getCompetenciaId());


                    RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select().from(RubroEvaluacionResultado.class)
                            .where(RubroEvaluacionResultado_Table.competenciaId.is(CapacidadQueryModel.getCompetenciaId()))
                            .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                            .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId()))
                            .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                            .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                            .querySingle();


//                    Log.d(EVALUACION_COMPETENCIA_TAG, "COMPENTECIA:HIJOSBASE : " + SQLite.select().from(RubroEvaluacionResultado.class)
//                            .where(RubroEvaluacionResultado_Table.competenciaId.is(CapacidadQueryModel.getCompetenciaId()))
//                            .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
//                            .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId()))
//                            .getQuery());



                    if (rubroEvaluacionResultado != null) {
                        long dateLongHijos = rubroEvaluacionResultado.getFechaAccion();
                        CapacidadUi capacidadUiPrimero = new CapacidadUi();
                        capacidadUiPrimero.setRubroEvaluacionId(String.valueOf(rubroEvaluacionResultado.getRubroEvalResultadoId()));
                        capacidadUiPrimero.setContador(contadorCapacidad);
                        capacidadUiPrimero.setTitulo(rubroEvaluacionResultado.getTitulo());
                        capacidadUiPrimero.setAlias(rubroEvaluacionResultado.getSubtitulo());
                        capacidadUiPrimero.setFecha(Utils.f_fecha_letras(dateLongHijos));
                        String media = rubroEvaluacionResultado.getPromedio()+"("+rubroEvaluacionResultado.getDesviacionEstandar()+")";
                        capacidadUiPrimero.setMedia(media);
                        capacidadUiPrimero.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        capacidadUiPrimero.setCompetenciaId(rubroEvaluacionResultado.getCompetenciaId());
                        capacidadUiPrimero.setTipo(CapacidadUi.Tipo.RESULTADO_CAPACIDAD);
                        Log.d(getClass().getSimpleName(), " "+rubroEvaluacionResultado.getRubroEvalResultadoId() + " estado: " + rubroEvaluacionResultado.getEstadoId());
                        if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.ANCLADA) {
                            capacidadUiPrimero.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.ANCLADA);
                        } else if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.EVALUADO) {
                            capacidadUiPrimero.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.EVALUADA);
                        } else {
                            capacidadUiPrimero.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                        }
                     //   Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionProcesoCADD : " + rubroEvaluacionResultado.getRubroEvalResultadoId() + " " + rubroEvaluacionResultado.getTitulo());
                        //     }
                        capacidadUiPrimero.setCompetenciaUi(competenciaUi);
                        capacidadUiList.add(capacidadUiPrimero);
                    }

                }

                /*RubroEvaluacion Resultado -Competencia Base */
                RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select()
                        .from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.competenciaId.is(compentenciaQueryModel.getCompetenciaId()))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId()))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                        .querySingle();

                if (rubroEvaluacionResultado != null) {
                    List<CapacidadUi> formulaLista = getFormulaLista(capacidadUiList);
                    long dateLongPadre = rubroEvaluacionResultado.getFechaAccion();
                    CapacidadUi capacidadUi = new CapacidadUi();
                    capacidadUi.setRubroEvaluacionId(String.valueOf(rubroEvaluacionResultado.getRubroEvalResultadoId()));
                    capacidadUi.setContador(contadorCapacidad + 1);
                    capacidadUi.setTitulo(rubroEvaluacionResultado.getTitulo());
                    capacidadUi.setAlias(rubroEvaluacionResultado.getSubtitulo());
                    capacidadUi.setFecha(Utils.f_fecha_letras(dateLongPadre));
                    String media = rubroEvaluacionResultado.getPromedio()+"("+rubroEvaluacionResultado.getDesviacionEstandar()+")";
                    capacidadUi.setMedia(media);
                    capacidadUi.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                    capacidadUi.setCapacidadUiList(formulaLista);
                    capacidadUi.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                    capacidadUi.setTipo(CapacidadUi.Tipo.RESULTADO_COMPETENCIA);
                    capacidadUi.setCompetenciaId(rubroEvaluacionResultado.getCompetenciaId());
                    capacidadUi.setCompetenciaUi(competenciaUi);
                    capacidadUiList.add(capacidadUi);


//                    Log.d(EVALUACION_COMPETENCIA_TAG, "RubroResultadoBase : +" + rubroEvaluacionResultado.getTitulo());
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "CompetenciaBaseId : +" + rubroEvaluacionResultado.getRubroEvalResultadoId() + " " + rubroEvaluacionResultado.getTitulo());

                }

                //Lista de Hijos
                List<RubroEvaluacionResultado> listHijosFinalesBimestre = SQLite.select((Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES)))
                        .from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.competenciaId.is(compentenciaQueryModel.getCompetenciaId()))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId()))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                        .groupBy(RubroEvaluacionResultado_Table.titulo,
                                RubroEvaluacionResultado_Table.subtitulo,
                                RubroEvaluacionResultado_Table.fechaAccion)
                        .queryList();


                for (RubroEvaluacionResultado listHijosFinalesBim : listHijosFinalesBimestre) {
                    contadorFinalBimestre++;

                    long dateHijosPadre = listHijosFinalesBim.getFechaAccion();
                    rnrFormulaUiList = getListaRubroEvalFormula(rubroEvaluacionResultado);

                    String media = listHijosFinalesBim.getPromedio()+"("+listHijosFinalesBim.getDesviacionEstandar()+")";
                    hijosFinalBimestres.add(new HijosFinalBimestreUi(contadorFinalBimestre,
                            listHijosFinalesBim.getRubroEvalResultadoId(),
                            listHijosFinalesBim.getTitulo(),
                            listHijosFinalesBim.getSubtitulo(),
                            Utils.f_fecha_letras(dateHijosPadre),
                            media,
                            rnrFormulaUiList,
                            padreFinalBimestre,
                            HijosFinalBimestreUi.TipoFinalBimestre.TIPO_FINAL_BASE, rubroEvaluacionResultado.getRubroEvalResultadoId(),
                            parametrosDisenio.getColor1()));


//                    Log.d(EVALUACION_COMPETENCIA_TAG, "hijosFinalBimestresRubroResultadoBase : +" + listHijosFinalesBim.getTitulo());
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "hijosFinalBimestresdCompetenciaBaseId : +" + listHijosFinalesBim.getRubroEvalResultadoId());

                }
             //   Log.d(EVALUACION_COMPETENCIA_TAG, "hijosListSize : +" + listHijosFinalesBimestre.size());

                competenciaUi.setCapacidadUiList(capacidadUiList);
                competenciaUi.setContador(capacidadUiList.size() + "");
                items.add(competenciaUi);
            }


            /*Nota Final del Bimestre*/
            RubroEvaluacionResultado listNotaFinalBimestre = SQLite.select((Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES)))
                    .from(RubroEvaluacionResultado.class)
                    .innerJoin(CalendarioPeriodo.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                    .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().is(periodoId))
                    .and(RubroEvaluacionResultado_Table.competenciaId.withTable().is(0))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().is(silaboEvento.getSilaboEventoId()))
                    .and(RubroEvaluacionResultado_Table.tipoFormulaId.withTable().greaterThan(0))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .groupBy(RubroEvaluacionResultado_Table.titulo,
                            RubroEvaluacionResultado_Table.subtitulo,
                            RubroEvaluacionResultado_Table.fechaAccion, RubroEvaluacionResultado_Table.rubroEvalResultadoId)
                    .querySingle();
            if (listNotaFinalBimestre == null){
              //  Log.d(EVALUACION_COMPETENCIA_TAG,"listNotaFinalBimestre null");
                callBack.onLista(new ArrayList<Object>());
                return;
            }
            // List<CapacidadUi> formulaLista = getFormulaLista(capacidadUiList);
            HijosFinalBimestreUi hijosFinalBimestreNotaFinal = new HijosFinalBimestreUi();
            hijosFinalBimestreNotaFinal.setTipoFinalBimestre(HijosFinalBimestreUi.TipoFinalBimestre.TIPO_FINAL_BIMESTRE);
            hijosFinalBimestreNotaFinal.setTitulo(listNotaFinalBimestre.getTitulo());
            hijosFinalBimestreNotaFinal.setAlias(listNotaFinalBimestre.getSubtitulo());
            long dateNotaFinalBimestre = listNotaFinalBimestre.getFechaAccion();
            hijosFinalBimestreNotaFinal.setFecha(Utils.f_fecha_letras(dateNotaFinalBimestre));

            List<RubroEvalRNRFormulaUi> rnrFormulaUiListpadre = null;
            rnrFormulaUiListpadre= getListaRubroEvalFormula(listNotaFinalBimestre);
            hijosFinalBimestreNotaFinal.setRubroEvalRNRFormulas(rnrFormulaUiListpadre);
            String media = listNotaFinalBimestre.getPromedio()+"("+listNotaFinalBimestre.getDesviacionEstandar()+")";
            hijosFinalBimestreNotaFinal.setMedia(media);
            hijosFinalBimestreNotaFinal.setRubroEvalId(listNotaFinalBimestre.getRubroEvalResultadoId());
            hijosFinalBimestres.add(hijosFinalBimestreNotaFinal);
            padreFinalBimestre.setHijosBimestreList(hijosFinalBimestres);

            padreFinalBimestre.setContador(" * ");
            boolean competenciaBase = false;
            for (FiltradoUi filtradoUi: filtradoUiList){
                competenciaBase = filtradoUi.getTipo() == FiltradoUi.Tipo.COMPETENCIA_BASE &&  filtradoUi.isaBoolean();
                if(competenciaBase)break;
            }
            if(competenciaBase){
                items.add(padreFinalBimestre);
            }

          //  Log.d(EVALUACION_COMPETENCIA_TAG,"CompetenciaBaseId" + listNotaFinalBimestre.getRubroEvalResultadoId() + " " + listNotaFinalBimestre.getTitulo() + " " + listNotaFinalBimestre.getSubtitulo()+" " + hijosFinalBimestreNotaFinal.getMedia());
        }
        callBack.onLista(items);
    }

    private List<RubroEvalRNRFormulaUi> getListaRubroEvalFormula(RubroEvaluacionResultado listHijosFinalesBimestre) {
        List<RubroEvalRNRFormulaUi> evalRNRFormulaUis = new ArrayList<>();
        if(listHijosFinalesBimestre!=null){
            Log.d(EVALUACION_COMPETENCIA_TAG, "getListaRubroEvalFormula : " + listHijosFinalesBimestre.getRubroEvalResultadoId());
            Log.d(EVALUACION_COMPETENCIA_TAG, "getListaRubroEvalFormulaBase : " + listHijosFinalesBimestre.getTitulo());

            List<RubroEvalRNRFormula> rnrFormulaUis = SQLite.select(Utils.f_allcolumnTable(RubroEvalRNRFormula_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvalRNRFormula.class)
                    .innerJoin(RubroEvaluacionResultado.class)
                    .on(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable()
                            .eq(RubroEvalRNRFormula_Table.rubroEvaluacionPrinId.withTable()))
                    .where(RubroEvalRNRFormula_Table.rubroEvaluacionPrinId.withTable().
                            eq(listHijosFinalesBimestre.getRubroEvalResultadoId()))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .queryList();


            int contar = 0;
            Log.d(EVALUACION_COMPETENCIA_TAG, "rnrFormulaUis sixe : " + rnrFormulaUis.size());
            for (RubroEvalRNRFormula rubroEvalRNRFormula : rnrFormulaUis) {
                Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionPrinId : " + rubroEvalRNRFormula.getRubroEvaluacionSecId());

                RubroEvaluacionResultado rubroSecundario= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                        .from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable()
                                .eq(rubroEvalRNRFormula.getRubroEvaluacionSecId()))
                        .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                       // .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .querySingle();

                if(rubroSecundario!=null&&rubroSecundario.getEvaluable()==1){
                    contar++;
                    // Log.d(EVALUACION_COMPETENCIA_TAG, "rubro primario "+rubroEvalRNRFormula.getRubroEvaluacionPrinId()+ " secundarioa id "+ rubroEvalRNRFormula.getRubroEvaluacionSecId()+ " formula "+rubroEvalRNRFormula.getRubroFormulaId()  );
                    evalRNRFormulaUis.add(new RubroEvalRNRFormulaUi(rubroEvalRNRFormula.getRubroFormulaId(),
                            rubroEvalRNRFormula.getRubroEvaluacionPrinId(),
                            rubroEvalRNRFormula.getRubroEvaluacionSecId(),
                            contar,
                            rubroEvalRNRFormula.getPeso(),
                            rubroEvalRNRFormula.getOrientacion()));
                }
            }
        }

        return evalRNRFormulaUis;

    }


    private List<CapacidadUi> getFormulaLista(List<CapacidadUi> capacidadUiList) {

        /*Capacidad con formula*/
        List<CapacidadUi> capacidadUis = new ArrayList<>();
        int counter = 0;
        for (CapacidadUi capacidadUi : capacidadUiList) {
            counter++;
            capacidadUis.add(new CapacidadUi(
                    String.valueOf(capacidadUi.getRubroEvaluacionId()),
                    counter,
                    capacidadUi.getTitulo(),
                    capacidadUi.getAlias(),
                    capacidadUi.getFecha(),
                    "20.1(2.5)",
                    capacidadUi.getSilaboEventoId(),
                    capacidadUi.getCompetenciaId())
            );
        }


        return capacidadUis;
    }


    @Override
    public void getEnfoqueTransVersal(int periodoId, int cargaCursoId, int cursoId,CallBack callBack) {
        //Log.d(EVALUACION_COMPETENCIA_TAG, "GETCOMPETENCIAlIST  :  periodoId : " + periodoId + "  /  " + cargaCursoId + " / cursoId " + cursoId);
        /*SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                .select(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(cargaCursoId)
                .querySingle();

        if (silaboEvento == null) {
            callBack.onLista(new ArrayList<Object>());
            return;
        }

       // Log.d(EVALUACION_COMPETENCIA_TAG, "silaboEvento + : " + silaboEvento.getSilaboEventoId());

        List<Object> items = new ArrayList<>();

        List<Competencia> competenciaList = SQLite.select()
                .from(Competencia.class)
                .innerJoin(RubroEvaluacionResultado.class)
                .on(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(Competencia_Table.competenciaId.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(SilaboEvento_Table.silaboEventoId.withTable().eq(RubroEvaluacionResultado_Table.silaboEventoId.withTable()))
                .where(RubroEvaluacionResultado_Table.silaboEventoId.withTable().is(silaboEvento.getSilaboEventoId()))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().is(periodoId))
                .and(Competencia_Table.tipoId.withTable().in(348, 349))
                .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                .queryList();

      *//*  List<HijosFinalBimestre> hijosFinalBimestres = new ArrayList<>();
        PadreFinalBimestre padreFinalBimestre = new PadreFinalBimestre();
        padreFinalBimestre.setNombre("FINAL BIMESTRE ");*//*

        for (Competencia compentenciaQueryModel : competenciaList) {

            //Log.d(EVALUACION_COMPETENCIA_TAG, "getEnfoqueTransVersal : " + compentenciaQueryModel.getNombre());

            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setNombre(compentenciaQueryModel.getNombre());
            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
            ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
            parametroDisenioUi.setColor3(parametroDisenioUi.getColor3());
            competenciaUi.setParametroDisenioUi(parametroDisenioUi);


             *//*Listado Hijos - Capacidad*//*
            List<RubroEvaluacionResultado> capacidadList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .innerJoin(SilaboEvento.class)
                    .on(SilaboEvento_Table.silaboEventoId.withTable().eq(RubroEvaluacionResultado_Table.silaboEventoId.withTable()))
                    .innerJoin(CalendarioPeriodo.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                    .innerJoin(Competencia.class)
                    .on(Competencia_Table.competenciaId.withTable().eq(RubroEvaluacionResultado_Table.competenciaId.withTable()))
                    .where(RubroEvaluacionResultado_Table.silaboEventoId.withTable().is(silaboEvento.getSilaboEventoId()))
                    .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().is(periodoId))
                    .and(Competencia_Table.superCompetenciaId.withTable().is(compentenciaQueryModel.getCompetenciaId()))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .queryList();

            int contadorCapacidad = 0;
            List<CapacidadUi> capacidadUiList = new ArrayList<>();


            for (RubroEvaluacionResultado CapacidadQueryModel : capacidadList) {
                contadorCapacidad++;

               // Log.d(EVALUACION_COMPETENCIA_TAG, "listaIdCapacidades : " + CapacidadQueryModel.getCompetenciaId());
                RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select().from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.competenciaId.is(CapacidadQueryModel.getCompetenciaId()))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId()))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                        .querySingle();


                if (rubroEvaluacionResultado != null) {
                    long dateLongHijos = rubroEvaluacionResultado.getFechaAccion();
                  //  Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionResultado : " + rubroEvaluacionResultado.getTitulo() + " " + rubroEvaluacionResultado.getRubroEvalResultadoId());

                    CapacidadUi  capacidadUiTransVersal = new CapacidadUi();
                    capacidadUiTransVersal.setId(String.valueOf(rubroEvaluacionResultado.getRubroEvalResultadoId()));
                    capacidadUiTransVersal.setContador(contadorCapacidad);
                    capacidadUiTransVersal.setTitulo(rubroEvaluacionResultado.getTitulo());
                    capacidadUiTransVersal.setAlias(rubroEvaluacionResultado.getSubtitulo());
                    capacidadUiTransVersal.setFecha(Utils.f_fecha_letras(dateLongHijos));
                    String media =   String.format("%.1f", rubroEvaluacionResultado.getPromedio())+ "("+String.format("%.2f", rubroEvaluacionResultado.getDesviacionEstandar())+")";
                    capacidadUiTransVersal.setMedia(media);
                    capacidadUiTransVersal.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                    capacidadUiTransVersal.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                    if (rubroEvaluacionResultado.getEstadoId() == 313) {
                        capacidadUiTransVersal.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.ANCLADA);
                    } else if (rubroEvaluacionResultado.getEstadoId() == 314) {
                        capacidadUiTransVersal.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.EVALUADA);
                    } else {
                        capacidadUiTransVersal.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                    }
                    capacidadUiTransVersal.setCompetenciaUi(competenciaUi);
                    capacidadUiList.add(capacidadUiTransVersal);

                    *//*capacidadUiList.add(new CapacidadUi(
                            String.valueOf(rubroEvaluacionResultado.getRubroEvalResultadoId()),
                            contadorCapacidad,
                            rubroEvaluacionResultado.getTitulo(),
                            rubroEvaluacionResultado.getSubtitulo(),
                            Utils.f_fecha_letras(dateLongHijos),
                            "20.1(2.5)",
                            rubroEvaluacionResultado.getSilaboEventoId()
                    ));*//*
                }

            }



             *//*Competencia Base *//*
            RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select()
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.competenciaId.is(compentenciaQueryModel.getCompetenciaId()))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .querySingle();


            if (rubroEvaluacionResultado != null) {
                *//*Competencia Base *//*
                //Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionResultadoytre" + rubroEvaluacionResultado.getTitulo() + " " + rubroEvaluacionResultado.getRubroEvalResultadoId());

                List<CapacidadUi> formulaLista = getFormulaLista(capacidadUiList);
                long dateLongPadre = rubroEvaluacionResultado.getFechaAccion();
                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(String.valueOf(rubroEvaluacionResultado.getRubroEvalResultadoId()));
                capacidadUi.setContador(contadorCapacidad + 1);
                capacidadUi.setTitulo(rubroEvaluacionResultado.getTitulo());
                capacidadUi.setAlias(rubroEvaluacionResultado.getSubtitulo());
                capacidadUi.setFecha(Utils.f_fecha_letras(dateLongPadre));
                String media =   String.format("%.1f", rubroEvaluacionResultado.getPromedio())+ "("+String.format("%.2f", rubroEvaluacionResultado.getDesviacionEstandar())+")";
                capacidadUi.setMedia(media);
                capacidadUi.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                capacidadUi.setCapacidadUiList(formulaLista);
                capacidadUi.setCompetenciaUi(competenciaUi);
                capacidadUiList.add(capacidadUi);


            }


            //Lista de Hijos
           *//* List<RubroEvaluacionResultado> listHijosFinalesBimestre = SQLite.select()
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.competenciaId.is(compentenciaQueryModel.getCompetenciaId()))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.is(periodoId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.is(silaboEvento.getSilaboEventoId())) //silaboEvento
                    .queryList();


            if (listHijosFinalesBimestre.size() > 0) {
                for (int i = 0; i < listHijosFinalesBimestre.size(); i++) {
                    RubroEvaluacionResultado resultado = listHijosFinalesBimestre.get(i);
                    Log.d(EVALUACION_COMPETENCIA_TAG, "2|rubroEvaluacionResultado" + resultado.getTitulo());
                    long dateLongPadre = Long.parseLong(resultado.getFechaAccion());
                    hijosFinalBimestres.add(new HijosFinalBimestre(contadorCapacidad,
                            rubroEvaluacionResultado.getTitulo(),
                            rubroEvaluacionResultado.getSubtitulo(),
                            Utils.f_fecha_letras(dateLongPadre),
                            "20.1(2.5)"));
                }
            } else {
                Log.d(EVALUACION_COMPETENCIA_TAG, "listaHijosFinalesFallo");
            }*//*

            competenciaUi.setCapacidadUiList(capacidadUiList);
            competenciaUi.setCargaCursoId(cargaCursoId);
            competenciaUi.setContador(capacidadUiList.size() + "");
            items.add(competenciaUi);
        }
       *//* padreFinalBimestre.setHijosBimestreList(hijosFinalBimestres);
        padreFinalBimestre.setContador(hijosFinalBimestres.size() + "");
        items.add(padreFinalBimestre);*//*
        callBack.onLista(items);*/
    }

    @Override
    public void onEvaluacionResultado(Object object, int periodoId, ObjectCallback objectCallback) {

        CapacidadUi capacidadUi = (CapacidadUi) object;
        CompetenciaUi capacidadUiCompetenciaUi = capacidadUi.getCompetenciaUi();
        int rubroEvaluacionResultadoId = Integer.parseInt(capacidadUi.getRubroEvaluacionId());
        int capacidadId = capacidadUi.getCompetenciaId();
        int competenciaId = capacidadUiCompetenciaUi.getCompetenciaId();
        int silaboEventoId = capacidadUi.getSilaboEventoId();
        int estadoEvaluador = 314;
        int docenteId = 0;
        int planCursoId = 0;
        int cargaCursoId = 0;
        //Log.d(EVALUACION_COMPETENCIA_TAG, "capacidadUi id " + competenciaId);
       // Log.d(EVALUACION_COMPETENCIA_TAG, "COMPETENCIAuD id " +capacidadUiCompetenciaUi.getCompetenciaId()+" / supercompetnecia "+ capacidadUiCompetenciaUi.getSuperComeptenciaId() );
        try {

            SessionUser sessionUser = SessionUser.getCurrentUser();
            if(sessionUser!=null){
                Empleado empleado = SQLite.select()
                        .from(Empleado.class)
                        .where(Empleado_Table.personaId.eq(sessionUser.getPersonaId()))
                        .querySingle();

                if(empleado!=null)docenteId=empleado.getEmpleadoId();
            }

            CargaCursos cargaCursos = SQLite.select()
                    .from(CargaCursos.class)
                    .where(CargaCursos_Table.cargaCursoId.eq(capacidadUiCompetenciaUi.getCargaCursoId()))
                    .querySingle();

            if(cargaCursos!=null){
                planCursoId = cargaCursos.getPlanCursoId();
                cargaCursoId = cargaCursos.getCargaCursoId();
            }

            RubroEvaluacionResultado rubroEvaluacionResultadoCapacidad = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().eq(rubroEvaluacionResultadoId))
                    //.and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .querySingle();

            if(rubroEvaluacionResultadoCapacidad!=null){
                TipoNotaC tipoNotaC= SQLite.select().from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.withTable()
                                .eq(rubroEvaluacionResultadoCapacidad.getTipoNotaId()))
                        .querySingle();
                if(tipoNotaC!=null){
                    List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select(Utils.f_allcolumnTable(EvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                            .from(EvaluacionProcesoC.class)
                            .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                    .eq(rubroEvaluacionResultadoCapacidad.getRubroEvalProcesoId()))
                            .queryList();

                    int count = 0;
                    boolean error = false;
                    int alumnoIdError = 0;
                    for(EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
                        //Log.d(EVALUACION_COMPETENCIA_TAG, "evaluacionProcesoC " + evaluacionProcesoC.getAlumnoId());
                        Log.d(EVALUACION_COMPETENCIA_TAG, "alumno "+evaluacionProcesoC.getAlumnoId());
                        Log.d(EVALUACION_COMPETENCIA_TAG, "nota "+evaluacionProcesoC.getNota());
                        Log.d(EVALUACION_COMPETENCIA_TAG, "valortiponota "+evaluacionProcesoC.getValorTipoNotaId());

                        Log.d(EVALUACION_COMPETENCIA_TAG, "resultado "+evaluacionProcesoC.getRubroEvalProcesoId());

                        PersonaContratoQuery personaContratoQuery = personaDao.getPersonContrato(evaluacionProcesoC.getAlumnoId(), capacidadUiCompetenciaUi.getCargaCursoId());
                        if(personaContratoQuery==null||personaContratoQuery.getVigente()==0)continue;

                        EvaluacionResultadoC evaluacionResultado = SQLite.select()
                                .from(EvaluacionResultadoC.class)
                                .where(EvaluacionResultadoC_Table.alumnoId.withTable().eq(evaluacionProcesoC.getAlumnoId()))
                                .and(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable()
                                        .eq(rubroEvaluacionResultadoCapacidad.getRubroEvalResultadoId()))
                                .querySingle();

                        if(evaluacionResultado==null){
                            //evaluacionResultado = createRubrica(rubroEvaluacionResultadoCapacidad, evaluacionProcesoC.getAlumnoId(), docenteId, planCursoId, cargaCursoId);
                            error = true;
                            alumnoIdError = evaluacionProcesoC.getAlumnoId();
                            Log.d(EVALUACION_COMPETENCIA_TAG, "resultado "+rubroEvaluacionResultadoCapacidad.getRubroEvalResultadoId());
                        }else {
                            count++;
                            setvalortiponota(tipoNotaC, evaluacionResultado, evaluacionProcesoC, objectCallback);
                        }

                    }


                    if(count!=0){
                        capacidadUi.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.EVALUADA);
                        rubroEvaluacionResultadoCapacidad.setEstadoId(RubroEvaluacionResultado.EVALUADO);
                        rubroEvaluacionResultadoCapacidad.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                        rubroEvaluacionResultadoCapacidad.save();

                        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                                .from(RubroEvaluacionProcesoC.class)
                                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvaluacionResultadoCapacidad.getRubroEvalProcesoId()))
                                .querySingle();

                        if(rubroEvaluacionProcesoC!=null){
                            rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionResultado.EVALUADO);
                            rubroEvaluacionProcesoC.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                            rubroEvaluacionProcesoC.save();
                        }

                        recalcularMediaDv(rubroEvaluacionResultadoCapacidad.getRubroEvalResultadoId());
                    }else {
                        objectCallback.onError("Resultado " + rubroEvaluacionResultadoCapacidad.getTitulo()+ " sin evaluaciones");
                    }

                    if(error){
                        Persona persona = SQLite.select()
                                .from(Persona.class)
                                .where(Persona_Table.personaId.eq(alumnoIdError))
                                .querySingle();

                        String nombre = "";
                        if(persona!=null){
                            nombre = Utils.capitalize(persona.getApellidoPaterno()) + " " +  Utils.capitalize(persona.getApellidoMaterno()) + " " +  Utils.capitalize(persona.getNombres());
                        }
                        objectCallback.onError("No existen evaluación resultado del alumno "+nombre+" evaluaciónes resultado");
                    }
                    //  Log.d(EVALUACION_COMPETENCIA_TAG, "capacidadUi " +capacidadUi.getEstadoCapacidad()+ " capacidad "+ capacidadUi.getId());

                }else {
                    objectCallback.onError("No existe el tipo nota " + rubroEvaluacionResultadoCapacidad.getTipoNotaId());
                }
            }else {
                objectCallback.onError("No existe rubro resultado asociado con esta capacidad");
            }




            //listar Evalaucion resultado de la capacidad
            List<EvaluacionResultadoC> evaluacionResultadoCapacidadList = SQLite.select()
                    .from(EvaluacionResultadoC.class)
                    .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().eq(rubroEvaluacionResultadoId))
                    .queryList();


            // change evaluacion resultado por competencia
            RubroEvaluacionResultado rubroEvaluacionResultadoCompetencia= SQLite.select().from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.competenciaId.eq(competenciaId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()
                            .eq(periodoId))
                    .querySingle();

            if(rubroEvaluacionResultadoCompetencia!=null){

                for (EvaluacionResultadoC evaluacionResultado: evaluacionResultadoCapacidadList) {

                    List<RubroEvaluacionResultado> rubroEvaluacionResultadosCapacidades = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                            .from(RubroEvaluacionResultado.class)
                            .innerJoin(Competencia.class)
                            .on(RubroEvaluacionResultado_Table.competenciaId.withTable()
                                    .eq(Competencia_Table.competenciaId.withTable()))
                            .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(periodoId))
                            .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                            .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                            .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                            .and(Competencia_Table.superCompetenciaId.withTable().eq(competenciaId)).queryList();

                    setEvaluacionResultado(rubroEvaluacionResultadoCompetencia, rubroEvaluacionResultadosCapacidades, evaluacionResultado.getAlumnoId(), docenteId, planCursoId, cargaCursoId);

                }

                recalcularMediaDv(rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId());
            }
            else  objectCallback.onError("No existe el rubro resultado de la competencia");


            int rubroEvalResultadoCompetenciaId = 0;
            if(rubroEvaluacionResultadoCompetencia!=null)rubroEvalResultadoCompetenciaId = rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId();

            List<EvaluacionResultadoC> evaluacionResultadoCompetenciaList = SQLite.select()
                    .from(EvaluacionResultadoC.class)
                    .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().eq(rubroEvalResultadoCompetenciaId))
                    .queryList();

            //change evaluacion resultado por bimestres finales
            RubroEvaluacionResultado rubroEvaluacionReBimestre=  SQLite.select().from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(periodoId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                    .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                    .and(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(0)).querySingle();


            if(rubroEvaluacionReBimestre!=null){


                for (EvaluacionResultadoC evaluacionResultado: evaluacionResultadoCompetenciaList) {

                    List<RubroEvaluacionResultado> rubroEvaluacionResultadosCompetenciaList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                            .from(RubroEvaluacionResultado.class).innerJoin(RubroEvalRNRFormula.class)
                            .on(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().eq(RubroEvalRNRFormula_Table.rubroEvaluacionSecId.withTable()))
                            .where(RubroEvalRNRFormula_Table.rubroEvaluacionPrinId.withTable().eq(rubroEvaluacionReBimestre.getRubroEvalResultadoId()))
                            .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                            .and(RubroEvaluacionResultado_Table.estadoId.withTable().notEq(RubroEvaluacionResultado.ELIMINADO))
                            .queryList();


                    setEvaluacionResultado(rubroEvaluacionReBimestre, rubroEvaluacionResultadosCompetenciaList, evaluacionResultado.getAlumnoId(), docenteId, planCursoId, cargaCursoId);

                }

                recalcularMediaDv(rubroEvaluacionReBimestre.getRubroEvalResultadoId());
            }
            else  objectCallback.onError("No existe el rubro resultado del bimestre");
            // Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionReBimestre " +rubroEvaluacionReBimestre.getRubroEvalResultadoId());

            // Log.d(EVALUACION_COMPETENCIA_TAG, "actualizarEstadoRubroEvaluar " );

            objectCallback.onObject(capacidadUi, true);

        }catch (Exception e){
            e.printStackTrace();
            objectCallback.onObject(capacidadUi, false);
        }



    }

    private void setEvaluacionResultado(RubroEvaluacionResultado rubroEvaluacionResultadoCabecera, List<RubroEvaluacionResultado> rubroEvaluacionResultadoList, int alumnoId, int docenteId, int planCursoId, int cargaCursoId) {

        double nota = 0.0;
        int count = 0;
        for (RubroEvaluacionResultado rubroEvaluacionResultado : rubroEvaluacionResultadoList) {
            count = count + 1;
            EvaluacionResultadoC evaluacionResultadoCapacidad = SQLite.select(Utils.f_allcolumnTable(EvaluacionResultadoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(EvaluacionResultadoC.class)
                    .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().eq(rubroEvaluacionResultado.getRubroEvalResultadoId()))
                    .and(EvaluacionResultadoC_Table.alumnoId.withTable().eq(alumnoId))
                    .querySingle();
            if (evaluacionResultadoCapacidad == null) continue;

            nota = nota + evaluacionResultadoCapacidad.getNota();
        }

        double notaFinal = nota / count;
        double notaTransformada = (double) Math.round(notaFinal * 100) / 100;
        String valorTipoNotaId = null;

        TipoNotaC tipoNotaC = SQLite.select().from(TipoNotaC.class)
                .where(TipoNotaC_Table.tipoNotaId.withTable()
                        .eq(rubroEvaluacionResultadoCabecera.getTipoNotaId())).querySingle();

        String tipoNotaId = "";
        boolean intervalo = true;
        if (tipoNotaC != null) {
            tipoNotaId = tipoNotaC.getTipoNotaId();
            intervalo = tipoNotaC.isIntervalo();
        }

        List<ValorTipoNotaC> valorTipoNotaCList = SQLite.select().from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.withTable()
                        .eq(tipoNotaId)).queryList();

        ValorTipoNotaC valorTipoNota = null;
        if (intervalo) {
            for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                if (itemValorTipoNota.getLimiteInferior() <= notaTransformada && itemValorTipoNota.getLimiteSuperior() >= notaTransformada) {
                    valorTipoNota = itemValorTipoNota;
                }
                notaTransformada = Utils.formatearDecimales(notaTransformada, 2);
            }
        } else {
            notaTransformada = Math.round(notaTransformada);
            if (!valorTipoNotaCList.isEmpty()) {

                ValorTipoNotaC minimoValorNumerico = null;
                for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                    if (itemValorTipoNota.getValorNumerico() == notaTransformada) {
                        valorTipoNota = itemValorTipoNota;
                    }
                    if (minimoValorNumerico == null || minimoValorNumerico.getValorNumerico() > itemValorTipoNota.getValorNumerico()) {
                        minimoValorNumerico = itemValorTipoNota;
                    }
                }
                if (valorTipoNota == null)
                    valorTipoNota = minimoValorNumerico;

            }
        }

        if (valorTipoNota != null) valorTipoNotaId = valorTipoNota.getValorTipoNotaId();


        EvaluacionResultadoC evaluacionResultado = SQLite.select()
                .from(EvaluacionResultadoC.class)
                .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(rubroEvaluacionResultadoCabecera.getRubroEvalResultadoId()))
                .and(EvaluacionResultadoC_Table.alumnoId.eq(alumnoId))
                .querySingle();

        if(evaluacionResultado==null){
            Log.d(getClass().getSimpleName(),"evaluacionResultado: " + evaluacionResultado);
            Log.d(getClass().getSimpleName(),"alumnoId: " + alumnoId);
            Log.d(getClass().getSimpleName(),"rubroEvalResultadoId: " + rubroEvaluacionResultadoCabecera.getCompetenciaId());
            //evaluacionResultado = createRubrica(rubroEvaluacionResultadoCabecera, alumnoId, docenteId, planCursoId, cargaCursoId);
        }else {
            evaluacionResultado.setNota(notaTransformada);
            evaluacionResultado.setValorTipoNotaId(valorTipoNotaId);
            evaluacionResultado.setSyncFlag(BaseEntity.FLAG_UPDATED);
            evaluacionResultado.save();
        }


    }

    private void setvalortiponota(TipoNotaC tipoNotaC, EvaluacionResultadoC evaluacionResultado, EvaluacionProcesoC evaluacionProcesoC, ObjectCallback objectCallback){
//           Log.d(EVALUACION_COMPETENCIA_TAG, "TIPONOTA "+ tipoNotaC.getNombre()+ " id "+ tipoNotaC.getTipoNotaId() );
//        Log.d(EVALUACION_COMPETENCIA_TAG, "evaluacion resultado  "+ evaluacionResultado.getEvaluacionResultadoId());

        EscalaEvaluacion escalaEvaluacion= SQLite.select().from(EscalaEvaluacion.class)
                .where(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()
                        .eq(tipoNotaC.getEscalaEvaluacionId())).querySingle();

        if(escalaEvaluacion==null)objectCallback.onError("No existe la escala evaluación " + tipoNotaC.getEscalaEvaluacionId());

        if(escalaEvaluacion!=null){
            double nota = evaluacionProcesoC.getNota();

            EscalaEvaluacion escalaEvaluacionProceso= SQLite
                    .select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                    .from(EscalaEvaluacion.class)
                    .innerJoin(TipoNotaC.class)
                    .on(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()
                            .eq(TipoNotaC_Table.escalaEvaluacionId.withTable()))
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable()
                            .eq(TipoNotaC_Table.key.withTable()))
                    .where(RubroEvaluacionProcesoC_Table.key.withTable()
                            .eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                    .querySingle();

            double notaTransformada=0D;

            if(escalaEvaluacionProceso!=null){
                Log.d(EVALUACION_COMPETENCIA_TAG, "escalaEvaluacionProceso "+ escalaEvaluacionProceso.getValorMaximo());
                if (nota <= escalaEvaluacionProceso.getValorMinimo()) {
                    nota = escalaEvaluacionProceso.getValorMinimo();
                }
                notaTransformada = Utils.transformacionInvariante(escalaEvaluacionProceso.getValorMinimo(), escalaEvaluacionProceso.getValorMaximo(), nota,escalaEvaluacion.getValorMinimo(),escalaEvaluacion.getValorMaximo());
            Log.d(EVALUACION_COMPETENCIA_TAG, "nota transformada "+ notaTransformada);
            }


            List<ValorTipoNotaC >valorTipoNotaCList= SQLite.select().from(ValorTipoNotaC.class)
                    .where(ValorTipoNotaC_Table.tipoNotaId.withTable()
                            .eq(tipoNotaC.getTipoNotaId())).queryList();
//            Log.d(EVALUACION_COMPETENCIA_TAG, "tipoNotaC.isIntervalo()"+ tipoNotaC.isIntervalo());
//            Log.d(EVALUACION_COMPETENCIA_TAG, "valorTipoNotaCList"+ valorTipoNotaCList.size());

            ValorTipoNotaC valorTipoNota = null;
            if(tipoNotaC.isIntervalo()){
                for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                    if (itemValorTipoNota.getLimiteInferior() <= notaTransformada && itemValorTipoNota.getLimiteSuperior() >= notaTransformada) {
                        valorTipoNota = itemValorTipoNota;
                    }
                    evaluacionResultado.setNota(Utils.formatearDecimales(notaTransformada, 2));
                }
            }else {

                if(!valorTipoNotaCList.isEmpty()){
                    long notaEntera = Math.round(notaTransformada);
                    ValorTipoNotaC minimoValorNumerico = null;
                    for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaCList) {
                        if (itemValorTipoNota.getValorNumerico() == notaEntera) {
                            valorTipoNota = itemValorTipoNota;
                        }
                        if(minimoValorNumerico==null||minimoValorNumerico.getValorNumerico() > itemValorTipoNota.getValorNumerico()){
                            minimoValorNumerico = itemValorTipoNota;
                        }
                        evaluacionResultado.setNota(notaEntera);
                    }
                    if(valorTipoNota==null){
                        valorTipoNota = minimoValorNumerico;
                    }
                }else
                    evaluacionResultado.setNota(Utils.formatearDecimales(notaTransformada, 2));

            }
            if(valorTipoNota!=null)
                evaluacionResultado.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());

            evaluacionResultado.setSyncFlag(BaseEntity.FLAG_UPDATED);
            evaluacionResultado.save();
        }
    }

    /*
    private EvaluacionResultadoC createRubrica(RubroEvaluacionResultado rubroEvaluacionResultado, int alumnoId, int docenteId, int planCursoId, int cargaCursoId){
        EvaluacionResultadoC evaluacionResultadoC = new EvaluacionResultadoC();
        evaluacionResultadoC.setEvaluacionResultadoId(0);
        evaluacionResultadoC.setCalendarioPeriodoId(rubroEvaluacionResultado.getCalendarioPeriodoId());
        evaluacionResultadoC.setCompetenciaId(rubroEvaluacionResultado.getCompetenciaId());
        evaluacionResultadoC.setAlumnoId(alumnoId);
        evaluacionResultadoC.setDocenteId(docenteId);
        evaluacionResultadoC.setNota(0);
        evaluacionResultadoC.setPlanCursoId(planCursoId);
        evaluacionResultadoC.setCargaCursoId(cargaCursoId);
        //evaluacionResultado.setEscala("");
        evaluacionResultadoC.setRubroEvalResultadoId(rubroEvaluacionResultado.getRubroEvalResultadoId());
        evaluacionResultadoC.setValorTipoNotaId("");
        evaluacionResultadoC.setIcdId(rubroEvaluacionResultado.getIcdId());
        evaluacionResultadoC.setSyncFlag(BaseRelEntity.FLAG_ADDED);
        evaluacionResultadoC.save();

        Log.d(EVALUACION_COMPETENCIA_TAG,"RubroEvalResultadoId: " + evaluacionResultadoC.getRubroEvalResultadoId());
        return evaluacionResultadoC;
    }*/

    @Override
    public boolean changeToogleCompetencia(int competenciaId, boolean toogle) {
        try {
            Competencia competencia = SQLite.select()
                    .from(Competencia.class)
                    .where(Competencia_Table.competenciaId.eq(competenciaId))
                    .querySingle();
            if(competencia!=null){
                competencia.setToogle(toogle);
                competencia.save();
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void anclarResultados(int calendarioPeriodoId, int cargaCursoId,List<FiltradoUi> filtradoUiList, final Callback2 callback) {

        final List<Object> list = new ArrayList<>();
        getCompenteciaLista(calendarioPeriodoId, cargaCursoId, 0, filtradoUiList, new CallBack() {
            @Override
            public void onLista(List<Object> objectList) {
                list.addAll(objectList);
            }
        });

        List<CapacidadUi> capacidadUiList = new ArrayList<>();
        for(Object o: list){
            if(o instanceof CompetenciaUi){
                CompetenciaUi competenciaUi = ((CompetenciaUi)o);
                List<CapacidadUi> capacidadUis = competenciaUi.getCapacidadUiList();
                if(capacidadUis != null){
                    for (CapacidadUi capacidadUi: capacidadUis){
                        if(capacidadUi.getEstadoCapacidad() == CapacidadUi.EstadoCapacidad.ANCLADA||
                                capacidadUi.getEstadoCapacidad() == CapacidadUi.EstadoCapacidad.EVALUADA){
                            capacidadUi.setCompetenciaUi(competenciaUi);
                            capacidadUiList.add(capacidadUi);
                        }
                    }

                }
            }
        }


        for (CapacidadUi capacidadUi : capacidadUiList){
            onEvaluacionResultado(capacidadUi, calendarioPeriodoId, new ObjectCallback() {
                @Override
                public void onObject(CapacidadUi capacidadUi, boolean success) {

                }

                @Override
                public void onError(String mensaje) {
                    callback.onLoad(false, mensaje);
                }
            });
        }

        callback.onLoad(true, "");

//        Log.d(EVALUACION_COMPETENCIA_TAG, "cursocargaid "+ cargaCursoId);
//        SilaboEvento evento= SQLite.select().from(SilaboEvento.class)
//                .where(SilaboEvento_Table.cargaCursoId.withTable()
//                        .eq(cargaCursoId))
//                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO)).querySingle();
//
//        if(evento!=null){
//            List<RubroEvaluacionResultado> rubroEvaluacionResultadoList= SQLite.select().from(RubroEvaluacionResultado.class)
//                    .where(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(evento.getSilaboEventoId()))
//                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
//                    .and(RubroEvaluacionResultado_Table.estadoId.eq(RubroEvaluacionResultado.ANCLADA))
//                    .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
//                    .queryList();
//            Log.d(EVALUACION_COMPETENCIA_TAG, "size  "+ rubroEvaluacionResultadoList.size());
//          if(rubroEvaluacionResultadoList.size()==0)callback.onLoad(false);
//
//
//            for(RubroEvaluacionResultado rubro: rubroEvaluacionResultadoList){
//                Log.d(EVALUACION_COMPETENCIA_TAG, "nombre "+ rubro.getTitulo()+ " / " +rubro.getRubroEvalResultadoId()+ " / proceso "+ rubro.getRubroEvalProcesoId());
//
//
//                TipoNotaC tipoNotaC= SQLite.select().from(TipoNotaC.class)
//                        .where(TipoNotaC_Table.tipoNotaId.withTable()
//                                .eq(rubro.getTipoNotaId())).querySingle();
//
//                List<EvaluacionProcesoC> evaluacionProcesoCList= SQLite.select(Utils.f_allcolumnTable(EvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
//                        .from(EvaluacionProcesoC.class).where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(rubro.getRubroEvalProcesoId()))
//                        .queryList();
//                  Log.d(EVALUACION_COMPETENCIA_TAG, "evaluacionProcesoCList " + evaluacionProcesoCList.size());
//
//                for(EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
//                    //Log.d(EVALUACION_COMPETENCIA_TAG, "evaluacionProcesoC " + evaluacionProcesoC.getAlumnoId());
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "alumno "+evaluacionProcesoC.getAlumnoId());
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "nota "+evaluacionProcesoC.getNota());
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "valortiponota "+evaluacionProcesoC.getValorTipoNotaId());
//
//                    Log.d(EVALUACION_COMPETENCIA_TAG, "resultado "+evaluacionProcesoC.getRubroEvalProcesoId());
//
//                    EvaluacionResultado evaluacionResultado = SQLite.select()
//                            .from(EvaluacionResultado.class)
//                            .where(EvaluacionResultado_Table.alumnoId.withTable().eq(evaluacionProcesoC.getAlumnoId()))
//                            .and(EvaluacionResultado_Table.rubroEvalResultadoId.withTable()
//                                    .eq(rubro.getRubroEvalResultadoId()))
//                            .querySingle();
//
//                    if(evaluacionResultado==null) callback.onLoad(false);
//                    //tipo de nota de resultado
//
//                    if(tipoNotaC!=null)setvalortiponota(tipoNotaC, evaluacionResultado, evaluacionProcesoC);
//                }
//
//                //change evaluacion resultado por competencia
//                RubroEvaluacionResultado rubroEvaluacionResultadoCompetencia= SQLite.select().from(RubroEvaluacionResultado.class)
//                        .where(RubroEvaluacionResultado_Table.competenciaId.eq(rubro.getCompetenciaId()))
//                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(rubro.getSilaboEventoId()))
//                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(rubro.getCalendarioPeriodoId()))
//                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1)).querySingle();
//                setnotas(rubro.getCalendarioPeriodoId(),rubro.getSilaboEventoId(),rubroEvaluacionResultadoCompetencia.getRubroEvalResultadoId(),rubroEvaluacionResultadoCompetencia.getCompetenciaId() );
//
//
//                //change evaluacion resultado por bimestres finales
//                RubroEvaluacionResultado rubroEvaluacionReBimestre=  SQLite.select().from(RubroEvaluacionResultado.class)
//                        .where(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(rubro.getCalendarioPeriodoId()))
//                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(rubro.getSilaboEventoId())).
//                                and(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(0))
//                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1)).querySingle();
//
//                // Log.d(EVALUACION_COMPETENCIA_TAG, "rubroEvaluacionReBimestre " +rubroEvaluacionReBimestre.getRubroEvalResultadoId());
//                setnotas(rubro.getCalendarioPeriodoId(),rubro.getSilaboEventoId(),rubroEvaluacionReBimestre.getRubroEvalResultadoId(),0 );
//
//                // Log.d(EVALUACION_COMPETENCIA_TAG, "actualizarEstadoRubroEvaluar " );
//
//                resultadoDao.actualizarEstadoRubroEvaluar(rubro.getRubroEvalResultadoId(),rubro.getCalendarioPeriodoId(),rubro.getSilaboEventoId(),314);
//                callback.onLoad(true);
//            }
//        }

    }

    @Override
    public boolean validarAnclaCalendarioPeriodo(int idCalendarioPeriodo, int idCargaCurso, boolean isRubroResultado) {
        return calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(idCalendarioPeriodo, idCargaCurso, isRubroResultado, null);
    }

    @Override
    public boolean recalcularMediaDv(int rubroEvaluacionResultadoId) {
        return resultadoDao.f_mediaDesviacionEstandar(rubroEvaluacionResultadoId);
    }


}
