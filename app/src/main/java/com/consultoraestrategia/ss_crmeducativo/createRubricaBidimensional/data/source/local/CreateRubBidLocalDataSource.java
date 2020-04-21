package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.CreateRubBid;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotas;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.silaboEventoDao.SilaboEventoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TipoEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CampotematicoSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CampotematicoUnidadAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadUnidadAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaUnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.IcdsSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.IcdsUnidadEventoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class CreateRubBidLocalDataSource implements CreateRubBidDataSource {
    public static final String TAG = CreateRubBidLocalDataSource.class.getSimpleName();

    private TiposDao tiposDao;
    private EscalaEvaluacionDao escalaEvaluacionDao;
    private SilaboEventoDao silaboEventoDao;
    private CompetenciaDao competenciaDao;
    private TipoEvaluacionDao tipoEvaluacionDao;
    private TipoNotaDao tipoNotaDao;
    private EvaluacionProcesoDao evaluacionProcesoDao;
    private TareasDao tareasDao;


    public CreateRubBidLocalDataSource(TiposDao tiposDao, EscalaEvaluacionDao escalaEvaluacionDao, SilaboEventoDao silaboEventoDao, CompetenciaDao competenciaDao, TipoEvaluacionDao tipoEvaluacionDao, TipoNotaDao tipoNotaDao, TareasDao tareasDao) {
        this.tiposDao = tiposDao;
        this.escalaEvaluacionDao = escalaEvaluacionDao;
        this.silaboEventoDao = silaboEventoDao;
        this.competenciaDao = competenciaDao;
        this.tipoEvaluacionDao = tipoEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
        this.tareasDao = tareasDao;
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
    }

    private SilaboEvento getSilaboEventoByCargaCurso(int cargaCursoId) {
        return silaboEventoDao.getByCargaCurso(cargaCursoId);
    }

    @Override
    public void getCompetencias(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CompetenciaUi> callback) {
        SilaboEvento silaboEvento = getSilaboEventoByCargaCurso(cargaCursoId);

        if (silaboEvento == null) {
            callback.onLoaded(new ArrayList<CompetenciaUi>());
            return;
        }

        try {
            List<Integer> integerList = new ArrayList<>();
            List<Competencia> competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(silaboEvento.getSilaboEventoId(), calendarioPeriodoId)
                    .queryList();
            for (Competencia competencia: competenciaList)integerList.add(competencia.getCompetenciaId());

            List<CompetenciaUi> competenciaUiList = new ArrayList<>();

            for (Competencia itemCompetencia : competenciaList) {
                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setId(itemCompetencia.getCompetenciaId());
                competenciaUi.setTitle(itemCompetencia.getNombre());
                competenciaUi.setTipoId(itemCompetencia.getTipoId());
                competenciaUiList.add(competenciaUi);

                List<Competencia> capacidadList = CapacidadUnidadAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silaboEvento.getSilaboEventoId(), calendarioPeriodoId, itemCompetencia.getCompetenciaId())
                        .queryList();

                List<CapacidadUi> capacidadUiList = new ArrayList<>();
                for (Competencia itemCapacidad : capacidadList) {
                    CapacidadUi capacidadUi = new CapacidadUi();
                    capacidadUi.setId(itemCapacidad.getCompetenciaId());
                    capacidadUi.setTitle(itemCapacidad.getNombre());
                    capacidadUi.setCantidadRubros(SQLite.selectCountOf()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.competenciaId.eq(itemCapacidad.getCompetenciaId()))
                            .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                            .and(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                            .and(RubroEvaluacionProcesoC_Table.tiporubroid.in(RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE, RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL, 0))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                            //.or(RubroEvaluacionProcesoC_Table.tipoFormulaId.notEq(0))
                            .count());

                    Log.d(TAG, "getCantidadRubros Size(): " + capacidadUi.getCantidadRubros());

                    capacidadUiList.add(capacidadUi);

                    List<IndicadorQuery> icdsList = IcdsUnidadEventoModel.SQLView()
                            .select(Utils.f_allcolumnTable(Icds_Table.icdId,
                                    Icds_Table.titulo,
                                    Icds_Table.alias,
                                    Icds_Table.descripcion,
                                    Icds_Table.estado,
                                    Icds_Table.peso,
                                    DesempenioIcd_Table.desempenioId.withTable(),
                                    DesempenioIcd_Table.desempenioIcdId,
                                    DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                                    DesempenioIcd_Table.tipoId,
                                    DesempenioIcd_Table.url))
                            .getQuery(silaboEvento.getSilaboEventoId(), itemCapacidad.getCompetenciaId(), calendarioPeriodoId,
                                    Utils.f_allcolumnTable(Icds_Table.icdId,
                                            Icds_Table.titulo,
                                            Icds_Table.alias,
                                            Icds_Table.descripcion,
                                            Icds_Table.estado,
                                            Icds_Table.peso,
                                            DesempenioIcd_Table.desempenioId.withTable(),
                                            DesempenioIcd_Table.desempenioIcdId,
                                            DesempenioIcd_Table.descripcion,
                                            DesempenioIcd_Table.tipoId,
                                            DesempenioIcd_Table.url))
                            .queryCustomList(IndicadorQuery.class);
                    ArrayList<IndicadorUi> indicadorUisList = new ArrayList<>();
                    for (IndicadorQuery itemIcds : icdsList) {
                        Desempenio desempenio=getDesempenio(itemIcds);
//                        Log.d(TAG, " getDesempenioId "+itemIcds.getDesempenioId());
//                        Log.d(TAG, " getDesempenioIcdId "+itemIcds.getDesempenioIcdId());
                        IndicadorUi indicadorUi = new IndicadorUi();
                        indicadorUi.setIndicadorId(itemIcds.getDesempenioIcdId());
                        indicadorUi.setCompetenciaOwner(competenciaUi);
                        indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                        if(desempenio!=null) indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                        indicadorUi.setTitulo(itemIcds.getTitulo());
                        indicadorUi.setAlias(itemIcds.getAlias());
                        indicadorUi.setUrl(itemIcds.getUrl());
                        if (itemIcds.getAlias() == null || itemIcds.getAlias().trim().isEmpty()) {
                            indicadorUi.setTituloRubro(itemIcds.getTitulo());
                        } else {
                            indicadorUi.setTituloRubro(itemIcds.getAlias());
                        }
                        indicadorUi.setEstado(itemIcds.getEstado());
                        Log.d(TAG,"tipo: " + itemIcds.getTipoId());
                        switch (itemIcds.getTipoId()) {
                            case Icds.TIPO_HACER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SER);
                                break;
                            default:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                                break;
                        }
                        indicadorUi.setPeso(itemIcds.getPeso());
                        indicadorUisList.add(indicadorUi);

                        List<CampoTematico> campoTematicos = CampotematicoUnidadAprendizajeModel.SQLView()
                                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                                .getQuery(silaboEvento.getSilaboEventoId(), itemIcds.getDesempenioIcdId(),calendarioPeriodoId )
                                .queryList();

                        Log.d(TAG, "campoTematicos Size(): " + campoTematicos.size());

                        indicadorUi.setCampoAccionList(getListCampoAccion(campoTematicos, indicadorUi, capacidadUi, competenciaUi, false));
                    }
                    capacidadUi.setIndicadorList(indicadorUisList);

                }
                competenciaUi.setCapacidadList(capacidadUiList);

            }
            callback.onLoaded(competenciaUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoaded(new ArrayList<CompetenciaUi>());
        }


    }

    private void callbackCompetencias(List<Competencia> competenciaList, int idSesionAprendizaje, int nivel, Callback<CompetenciaUi> callback) {
        List<CompetenciaUi> competenciaUiList = new ArrayList<>();

        try {
            for (Competencia itemCompetencia : competenciaList) {

                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setId(itemCompetencia.getCompetenciaId());
                competenciaUi.setTitle(itemCompetencia.getNombre());
                competenciaUi.setTipoId(itemCompetencia.getTipoId());
                competenciaUiList.add(competenciaUi);
                List<CapacidadUi> capacidadUisList = new ArrayList<>();
                competenciaUi.setCapacidadList(capacidadUisList);

                List<Competencia> capacidades = CapacidadSesionAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(itemCompetencia.getCompetenciaId(), idSesionAprendizaje)
                        .queryList();

                for (Competencia itemCapacidad : capacidades) {

                    List<IndicadorQuery> icdss = IcdsSesionAprendizajeModel.SQLView()
                            .select(Utils.f_allcolumnTable(Icds_Table.icdId,
                                    Icds_Table.titulo,
                                    Icds_Table.alias,
                                    Icds_Table.descripcion,
                                    Icds_Table.estado,
                                    Icds_Table.peso,
                                    DesempenioIcd_Table.desempenioId.withTable(),
                                    DesempenioIcd_Table.desempenioIcdId,
                                    DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                                    DesempenioIcd_Table.tipoId,
                                    DesempenioIcd_Table.url))
                            .getQuery(itemCapacidad.getCompetenciaId(), idSesionAprendizaje,
                                    Utils.f_allcolumnTable(Icds_Table.icdId,
                                            Icds_Table.titulo,
                                            Icds_Table.alias,
                                            Icds_Table.descripcion,
                                            Icds_Table.estado,
                                            Icds_Table.peso,
                                            DesempenioIcd_Table.desempenioId.withTable(),
                                            DesempenioIcd_Table.desempenioIcdId.withTable(),
                                            DesempenioIcd_Table.descripcion.withTable()))
                            .queryCustomList(IndicadorQuery.class);

                    Log.d(TAG, "indicadorList size: " + icdss.size());
                    CapacidadUi capacidadUi = new CapacidadUi();
                    capacidadUi.setId(itemCapacidad.getCompetenciaId());
                    capacidadUi.setTitle(itemCapacidad.getNombre());
                    capacidadUi.setCantidadRubros(SQLite.selectCountOf()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.competenciaId.eq(itemCapacidad.getCompetenciaId()))
                            .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(idSesionAprendizaje))
                            .and(RubroEvaluacionProcesoC_Table.tiporubroid.in(RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE, RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                            .count());


                    capacidadUisList.add(capacidadUi);
                    List<IndicadorUi> indicadorUisList = new ArrayList<>();
                    for (IndicadorQuery itemIcds : icdss) {

                        Desempenio desempenio=getDesempenio(itemIcds);
                        IndicadorUi indicadorUi = new IndicadorUi();
                        indicadorUi.setIndicadorId(itemIcds.getDesempenioIcdId());
                        indicadorUi.setCompetenciaOwner(competenciaUi);
                        indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                        if(desempenio!=null)indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                        indicadorUi.setTitulo(itemIcds.getTitulo());
                        indicadorUi.setAlias(itemIcds.getAlias());
                        indicadorUi.setUrl(itemIcds.getUrl());
                        if (itemIcds.getAlias() == null || itemIcds.getAlias().trim().isEmpty()) {
                            indicadorUi.setTituloRubro(itemIcds.getTitulo());
                        } else {
                            indicadorUi.setTituloRubro(itemIcds.getAlias());
                        }
                        indicadorUi.setPeso(itemIcds.getPeso());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        switch (itemIcds.getTipoId()) {
                            case Icds.TIPO_HACER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SER);
                                break;
                            default:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                                break;
                        }
                        indicadorUisList.add(indicadorUi);

                        List<CampoTematico> campoTematicos = CampotematicoSesionAprendizajeModel.SQLView()
                                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                                .getQuery(itemIcds.getDesempenioIcdId(), idSesionAprendizaje)
                                .queryList();


                        indicadorUi.setCampoAccionList(getListCampoAccion(campoTematicos, indicadorUi, capacidadUi, competenciaUi, false));
                    }
                    capacidadUi.setIndicadorList(indicadorUisList);
                }
            }


            callback.onLoaded(competenciaUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoaded(new ArrayList<CompetenciaUi>());
        }
    }

    @Override
    public void getCamposAccion(int sesionAprendizajeId, Callback<CampoAccionUi> callback) {

            List<Competencia> competencias = CompetenciaSesionAprendizajeModel.SQLView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(sesionAprendizajeId)
                    .queryList();
            callbackCamposAccion(competencias, sesionAprendizajeId, 3, callback);

    }

    private void callbackCamposAccion(List<Competencia> competenciaList, int sesionAprendizajeId, int i, Callback<CampoAccionUi> callback) {
        List<CampoAccionUi> campoAccionUiList = new ArrayList<>();

        try {
            for (Competencia itemCompetencia : competenciaList) {

                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setId(itemCompetencia.getCompetenciaId());
                competenciaUi.setTitle(itemCompetencia.getNombre());

                List<CapacidadUi> capacidadUisList = new ArrayList<>();
                competenciaUi.setCapacidadList(capacidadUisList);


                List<Competencia> capacidades = CapacidadSesionAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(itemCompetencia.getCompetenciaId(), sesionAprendizajeId)
                        .queryList();

                for (Competencia itemCapacidad : capacidades) {
                    List<IndicadorQuery> icdss = IcdsSesionAprendizajeModel.SQLView()
                            .select(Utils.f_allcolumnTable(Icds_Table.icdId,
                                    Icds_Table.desempenioId,
                                    Icds_Table.descripcion,
                                    Icds_Table.titulo,
                                    Icds_Table.alias,
                                    Icds_Table.descripcion,
                                    Icds_Table.estado,
                                    Icds_Table.peso,
                                    DesempenioIcd_Table.desempenioIcdId,
                                    DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                                    DesempenioIcd_Table.tipoId,
                                    DesempenioIcd_Table.url))
                            .getQuery(itemCapacidad.getCompetenciaId(), sesionAprendizajeId, Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES, DesempenioIcd_Table.desempenioIcdId.withTable(), DesempenioIcd_Table.descripcion.withTable()))
                            .queryCustomList(IndicadorQuery.class);

                    Log.d(TAG, "indicadorList size: " + icdss.size());
                    CapacidadUi capacidadUi = new CapacidadUi();
                    capacidadUi.setId(itemCapacidad.getCompetenciaId());
                    capacidadUi.setTitle(itemCapacidad.getNombre());
                    capacidadUisList.add(capacidadUi);
                    List<IndicadorUi> indicadorUisList = new ArrayList<>();
                    for (IndicadorQuery itemIcds : icdss) {

                        Desempenio desempenio=getDesempenio(itemIcds);

                        IndicadorUi indicadorUi = new IndicadorUi();
                        indicadorUi.setIndicadorId(itemIcds.getDesempenioIcdId());
                        indicadorUi.setCompetenciaOwner(competenciaUi);
                        if(desempenio!=null) indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                        indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                        indicadorUi.setTitulo(itemIcds.getTitulo());
                        indicadorUi.setAlias(itemIcds.getAlias());
                        indicadorUi.setUrl(itemIcds.getUrl());
                        if (itemIcds.getAlias() == null || itemIcds.getAlias().trim().isEmpty()) {
                            indicadorUi.setTituloRubro(itemIcds.getTitulo());
                        } else {
                            indicadorUi.setTituloRubro(itemIcds.getAlias());
                        }
                        indicadorUi.setPeso(itemIcds.getPeso());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        switch (itemIcds.getTipoId()) {
                            case Icds.TIPO_HACER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SER);
                                break;
                            default:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                                break;
                        }
                        indicadorUisList.add(indicadorUi);

                        List<CampoTematico> campoTematicos = CampotematicoSesionAprendizajeModel.SQLView()
                                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                                .getQuery(itemIcds.getDesempenioIcdId(), sesionAprendizajeId)
                                .queryList();

                        List<CampoAccionUi> itemCampoAccionUiList = getListCampoAccion(campoTematicos, indicadorUi, capacidadUi, competenciaUi, false);
                        indicadorUi.setCampoAccionList(itemCampoAccionUiList);
                        campoAccionUiList.addAll(itemCampoAccionUiList);
                    }
                    capacidadUi.setIndicadorList(indicadorUisList);
                }
            }

            callback.onLoaded(campoAccionUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoaded(new ArrayList<CampoAccionUi>());
        }

    }

    @Override
    public void getCamposAccion(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CampoAccionUi> callback) {

        Log.d(TAG, "cursoId: " + cursoId);
        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "calendarioPeriodoId: " + calendarioPeriodoId);

        SilaboEvento silaboEvento = getSilaboEventoByCargaCurso(cargaCursoId);

        if (silaboEvento == null) {
            callback.onLoaded(new ArrayList<CampoAccionUi>());
            return;
        }

        Log.d(TAG, "Silavoevento Id " + silaboEvento.getSilaboEventoId());
        try {
            List<Competencia> competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(silaboEvento.getSilaboEventoId(), calendarioPeriodoId)
                    .queryList();

            List<CampoAccionUi> campoAccionUiList = new ArrayList<>();

            for (Competencia itemCompetencia : competenciaList) {
                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setId(itemCompetencia.getCompetenciaId());
                competenciaUi.setTitle(itemCompetencia.getNombre());
                competenciaUi.setTipoId(itemCompetencia.getTipoId());

                List<Competencia> capacidadList = CapacidadUnidadAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silaboEvento.getSilaboEventoId(), calendarioPeriodoId, itemCompetencia.getCompetenciaId())
                        .queryList();
                List<CapacidadUi> capacidadUiList = new ArrayList<>();
                for (Competencia itemCapacidad : capacidadList) {
                    CapacidadUi capacidadUi = new CapacidadUi();
                    capacidadUi.setId(itemCapacidad.getCompetenciaId());
                    capacidadUi.setTitle(itemCapacidad.getNombre());
                    capacidadUiList.add(capacidadUi);

                    List<IndicadorQuery> icdsList = IcdsUnidadEventoModel.SQLView()
                            .select(Utils.f_allcolumnTable(Icds_Table.icdId,
                                    Icds_Table.titulo,
                                    Icds_Table.alias,
                                    Icds_Table.descripcion,
                                    Icds_Table.estado,
                                    Icds_Table.peso,
                                    DesempenioIcd_Table.desempenioId.withTable(),
                                    DesempenioIcd_Table.desempenioIcdId,
                                    DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                                    DesempenioIcd_Table.tipoId,
                                    DesempenioIcd_Table.url))
                            .getQuery(silaboEvento.getSilaboEventoId(), itemCapacidad.getCompetenciaId(), calendarioPeriodoId,
                                    Utils.f_allcolumnTable(Icds_Table.icdId,
                                            Icds_Table.titulo,
                                            Icds_Table.alias,
                                            Icds_Table.descripcion,
                                            Icds_Table.estado,
                                            Icds_Table.peso,
                                            DesempenioIcd_Table.desempenioId.withTable(),
                                            DesempenioIcd_Table.desempenioIcdId,
                                            DesempenioIcd_Table.descripcion,
                                            DesempenioIcd_Table.tipoId,
                                            DesempenioIcd_Table.url))
                            .queryCustomList(IndicadorQuery.class);

                    ArrayList<IndicadorUi> indicadorUisList = new ArrayList<>();
                    for (IndicadorQuery itemIcds : icdsList) {

                        Desempenio desempenio=getDesempenio(itemIcds);
                        IndicadorUi indicadorUi = new IndicadorUi();
                        indicadorUi.setIndicadorId(itemIcds.getDesempenioIcdId());
                        indicadorUi.setCompetenciaOwner(competenciaUi);
                        if(desempenio!=null) indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                        indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                        indicadorUi.setTitulo(itemIcds.getTitulo());
                        indicadorUi.setAlias(itemIcds.getAlias());
                        indicadorUi.setUrl(itemIcds.getUrl());
                        if (itemIcds.getAlias() == null || itemIcds.getAlias().trim().isEmpty()) {
                            indicadorUi.setTituloRubro(itemIcds.getTitulo());
                        } else {
                            indicadorUi.setTituloRubro(itemIcds.getAlias());
                        }
                        indicadorUi.setPeso(itemIcds.getPeso());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        indicadorUi.setEstado(itemIcds.getEstado());
                        switch (itemIcds.getTipoId()) {
                            case Icds.TIPO_HACER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SER);
                                break;
                            default:
                                indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                                break;
                        }
                        indicadorUisList.add(indicadorUi);

                        List<CampoTematico> campoTematicos = CampotematicoUnidadAprendizajeModel.SQLView()
                                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                                .getQuery(silaboEvento.getSilaboEventoId(), itemIcds.getDesempenioIcdId(), calendarioPeriodoId)
                                .queryList();



                        List<CampoAccionUi> itemcampoAccionUiList = getListCampoAccion(campoTematicos, indicadorUi, capacidadUi, competenciaUi, false);

                        indicadorUi.setCampoAccionList(itemcampoAccionUiList);
                        campoAccionUiList.addAll(itemcampoAccionUiList);
                    }
                    capacidadUi.setIndicadorList(indicadorUisList);

                }
                competenciaUi.setCapacidadList(capacidadUiList);

            }
            callback.onLoaded(campoAccionUiList);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoaded(new ArrayList<CampoAccionUi>());
        }

    }

    @Override
    public EstrategiaEvalUi getTituloRubrica(String rubroEvalaucionId) {

        EstrategiaEvalUi estrategiaEvalUi= new EstrategiaEvalUi();
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalaucionId))
                .querySingle();
        if(rubroEvaluacionProcesoC!=null){
            estrategiaEvalUi.setEstrategiaId(rubroEvaluacionProcesoC.getEstrategiaEvaluacionId());
            estrategiaEvalUi.setDescripcionRubro(rubroEvaluacionProcesoC.getSubtitulo());
            estrategiaEvalUi.setTituloRubro(rubroEvaluacionProcesoC.getTitulo());
            EstrategiaEvaluacion estrategiaEvaluacion = SQLite.select()
                    .from(EstrategiaEvaluacion.class)
                    .where(EstrategiaEvaluacion_Table.estrategiaEvaluacionId.eq(rubroEvaluacionProcesoC.getEstrategiaEvaluacionId()))
                    .querySingle();
            if(estrategiaEvaluacion!=null){
                estrategiaEvalUi.setEstrategia(estrategiaEvaluacion.getNombre());
            }

        }
        return estrategiaEvalUi;
    }

    @Override
    public TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipos) {
        List<Integer> tipoIdList = new ArrayList<>();
        for (TipoNotaUi.Tipo tipo : tipos){
            if (tipo == TipoNotaUi.Tipo.VALOR_NUMERICO){
                tipoIdList.add(TipoNotaC.VALOR_NUMERICO);
            }else if (tipo == TipoNotaUi.Tipo.SELECTOR_ICONOS){
                tipoIdList.add(TipoNotaC.SELECTOR_ICONOS);
            }else if (tipo == TipoNotaUi.Tipo.SELECTOR_VALORES){
                tipoIdList.add(TipoNotaC.SELECTOR_VALORES);
            }else if (tipo == TipoNotaUi.Tipo.SELECTOR_NUMERICO){
                tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);
            }
        }
        List<TipoNotaC> tipoNotaUiList = tipoNotaDao.getTipoNotaList(progrmaEducativoId, tipoIdList);

        Log.d(TAG, "tipoNotaUiList " + tipoNotaUiList.size());
        TipoNotaUi tipoNotaUi = null;
        if(!tipoNotaUiList.isEmpty()){
            tipoNotaUi = TipoNotaUi.transform(tipoNotaUiList.get(0));
            EscalaEvaluacion escalaEvaluacion = SQLite.select()
                    .from(EscalaEvaluacion.class)
                    .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotaUiList.get(0).getEscalaEvaluacionId()))
                    .querySingle();
            EscalaEvaluacionUi escalaUi = new EscalaEvaluacionUi();
            if(escalaEvaluacion != null){
                escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
                escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
                escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
                escalaUi.setNombre(escalaEvaluacion.getNombre());
            }
            tipoNotaUi.setEscalaEvaluacionUi(escalaUi);
        }
        return tipoNotaUi;
    }

    @Override
    public void getCompetencias(int idSesionAprendizaje, Callback<CompetenciaUi> callback) {
        Log.d(TAG, "getCompetencias");
        List<Competencia> competencias = CompetenciaSesionAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(idSesionAprendizaje)
                .queryList();
        callbackCompetencias(competencias, idSesionAprendizaje, 3, callback);
    }

    @Override
    public void getIndicadores(int idCompetencia, Callback<IndicadorUi> callback) {

    }

    @Override
    public void getTipoNotas(Callback<TipoNotaUi> callback, GetTipoNotas.RequestValues values) {
        int programaEducativoId = values.getProgramaEducativoId();
        List<TipoNotaC> tipoNotas = new ArrayList<>();
        tipoNotas.addAll(SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(TipoNotaC.class)
                .innerJoin(Tipos.class)
                .on(TipoNotaC_Table.tipoId.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(RelProgramaEducativoTipoNota.class)
                .on(TipoNotaC_Table.key.withTable()
                        .eq(RelProgramaEducativoTipoNota_Table.tipoNotaId.withTable()))
                .where(Tipos_Table.tipoId.withTable().in(TipoNotaC.SELECTOR_ICONOS, TipoNotaC.SELECTOR_VALORES))
                .and(RelProgramaEducativoTipoNota_Table.programaEducativoId.withTable().is(programaEducativoId))
                .groupBy(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .queryList());

        SessionUser sessionUser = SessionUser.getCurrentUser();
        if (sessionUser != null) {
            tipoNotas.addAll(SQLite.select()
                    .from(TipoNotaC.class)
                    .innerJoin(Tipos.class)
                    .on(TipoNotaC_Table.tipoId.withTable()
                            .eq(Tipos_Table.tipoId.withTable()))
                    .where(Tipos_Table.tipoId.withTable().in(TipoNotaC.SELECTOR_ICONOS, TipoNotaC.SELECTOR_VALORES))
                    .and(TipoNotaC_Table.usuarioCreacionId.is(sessionUser.getUserId()))
                    .queryList());
        }


        List<TipoNotaUi> tipoNotaUiList = TipoNotaUi.transform(tipoNotas);

        //Clase anónima
        Collections.sort(tipoNotaUiList, new Comparator<TipoNotaUi>() {
            @Override
            public int compare(TipoNotaUi o1, TipoNotaUi o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        callback.onLoaded(tipoNotaUiList);
    }

    @Override
    public void getTipoEvaluacionList(Callback<TipoUi> callback) {

            List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacionList = tipoEvaluacionDao.getAll();
            List<TipoUi> tipoEvaluacionUis = TipoUi.transformTipoEvaluacion(tipoEvaluacionList);
            callback.onLoaded(tipoEvaluacionUis);


    }

    @Override
    public void getEscalaEvaluacionList(Callback<EscalaEvaluacionUi> callback) {
        List<EscalaEvaluacion> escalaEvaluacionList = escalaEvaluacionDao.getAll();
        List<EscalaEvaluacionUi> escalaEvaluacionUiList = EscalaEvaluacionUi.transform(escalaEvaluacionList);
        callback.onLoaded(escalaEvaluacionUiList);
    }

    @Override
    public void getFormaEvaluacion(Callback<TipoUi> callback) {
        List<Tipos> tiposList = tiposDao.getFormaEvaluacionList();
        List<TipoUi> tipoUiList = TipoUi.transform(tiposList);
        callback.onLoaded(tipoUiList);
    }

    private static final int TIPO_RUBRO_BIDIMENSIONAL_CABECERA = 471;
    private static final int TIPO_RUBRO_BIDIMENSIONAL_DETALLE = 473;
    private String rubroEvaluacionProcesoId;

    @Override
    public void createRubBid(final CreateRubBid.RequestValues requestValues, final SaveCallback callback) {
        Log.d(TAG, "createRubBid");

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();

        Log.d(TAG, "getVersion() " + databaseWrapper.getVersion());
        try {
            databaseWrapper.beginTransaction();
            TipoUi tipoEvaluacion = requestValues.getTipoEvaluacionSelected();
            if(TextUtils.isEmpty(requestValues.getRubricaKeyEdit())){
                int rubroEvalResultadoId = requestValues.getRubroEvalResultadoId();
                int cargaCursoId = requestValues.getCargaCursoId();
                int silaboEventoId = requestValues.getSilaboEventoId();

                TipoUi formaEvaluacion = requestValues.getFormaEvaluacionSelected();
                TipoNotaUi tipoNota = requestValues.getTipoNotaSelected();
                EscalaEvaluacionUi escala = requestValues.getEscalaSelected();
                TipoNotaUi tipoNivelSelected = requestValues.getTipoNivelSelected();
                String tareaId = requestValues.getTareaId();

                if (silaboEventoId == 0) {
                    SilaboEvento silaboEvento = getSilaboEventoByCargaCurso(cargaCursoId);
                    if (silaboEvento != null) {
                        silaboEventoId = silaboEvento.getSilaboEventoId();
                    } else {
                        callback.onError();
                        return;
                    }
                }


                RubroEvaluacionProcesoC proceso = new RubroEvaluacionProcesoC();
                proceso.setTitulo(requestValues.getRubricaTitle());
                proceso.setSubtitulo(requestValues.getRubricaAlias());
                proceso.setTiporubroid(TIPO_RUBRO_BIDIMENSIONAL_CABECERA);
                proceso.setCalendarioPeriodoId(requestValues.getCalendarioPeriodoId());
                proceso.setCompetenciaId(0);
                proceso.setSesionAprendizajeId(requestValues.getSesionAprendizajeId());
                proceso.setTipoNotaId(tipoNota.getId());
                proceso.setFormaEvaluacionId(formaEvaluacion.getId());
                proceso.setTipoEvaluacionId(tipoEvaluacion.getId());
                proceso.setSilaboEventoId(silaboEventoId);
                proceso.setEstadoId(237);
                proceso.setSyncFlag(LocalEntity.FLAG_ADDED);
                proceso.setCountIndicador(requestValues.getIndicadorListSelected().size());
                proceso.setEstrategiaEvaluacionId(requestValues.getIdEstrategiaEvaluacion());

                boolean succes = proceso.save();

                Log.d(TAG, "idProcesoCabecera: " + proceso.getKey());
                if (!succes) throw new Error("Saving Cabecera failed!!!");

                List<IndicadorUi> indicadorList = requestValues.getIndicadorListSelected();
                List<ValorTipoNotaUi> nivelesSelected = requestValues.getValorTipoNotaList();
                int indicadorSize = indicadorList.size();
                int nivelListSize = nivelesSelected.size();

                List<List<Cell>> bodyList = requestValues.getBodyList();

                if (proceso.getFormaEvaluacionId() == 477) {
                    if (!evaluacionProcesoDao.crearEvaluacionProceso(proceso.getKey(), requestValues.getCargaCursoId())) {
                        throw new Error("no se guardó correctamente el EvaluacionProceso Detalle");
                    }
                }

                for (int i = 0; i < indicadorSize; i++) {
                    IndicadorUi indicador = indicadorList.get(i);
                    String title = indicador.getTitle();
                    String alias = indicador.getTituloRubro();
                    if (alias==null || TextUtils.isEmpty(alias.trim())) {
                        alias = title;
                    }


                    CapacidadUi capacidadUi = indicador.getCapacidadOwner();
                    double peso = Double.valueOf(indicador.getPeso());//percentParts[i];
                    RubroEvaluacionProcesoC procesoDetalle = new RubroEvaluacionProcesoC();
                    procesoDetalle.setTitulo(alias);
                    procesoDetalle.setSubtitulo(indicador.getSubTituloRubro());
                    procesoDetalle.setDesempenioIcdId(indicador.getIndicadorId());
                    procesoDetalle.setTiporubroid(TIPO_RUBRO_BIDIMENSIONAL_DETALLE);
                    procesoDetalle.setTipoNotaId(tipoNivelSelected.getId());
                    procesoDetalle.setSilaboEventoId(silaboEventoId);
                    procesoDetalle.setCompetenciaId(capacidadUi.getId());
                    procesoDetalle.setCalendarioPeriodoId(requestValues.getCalendarioPeriodoId());
                    procesoDetalle.setSesionAprendizajeId(requestValues.getSesionAprendizajeId());
                    procesoDetalle.setTipoEvaluacionId(tipoEvaluacion.getId());
                    procesoDetalle.setFormaEvaluacionId(formaEvaluacion.getId());
                    procesoDetalle.setEstadoId(237);
                    Log.d(TAG, "indicador.getCompetenciaOwner().isBase(): " + indicador.getCompetenciaOwner().isBase());
                    if (indicador.getCompetenciaOwner().isBase()) procesoDetalle.setRubroFormal(1);
                    procesoDetalle.setSyncFlag(LocalEntity.FLAG_ADDED);
                    boolean succesDetalle = procesoDetalle.save();
                    Log.d(TAG, "idProcesoDetalle: " + procesoDetalle.getKey());
                    if (!succesDetalle) throw new Error("Saving Detalle failed!!!");
                    rubroEvaluacionProcesoId = proceso.getKey();

                    if (procesoDetalle.getFormaEvaluacionId() == 477) {
                        if (!evaluacionProcesoDao.crearEvaluacionProceso(procesoDetalle.getKey(), requestValues.getCargaCursoId())) {
                            throw new Error("no se guardó correctamente el EvaluacionProceso Detalle");
                        }
                    }

                    RubroEvalRNPFormulaC formula = new RubroEvalRNPFormulaC();
                    formula.setRubroEvaluacionPrimId(proceso.getKey());
                    formula.setRubroEvaluacionSecId(procesoDetalle.getKey());
                    formula.setPeso(peso);
                    formula.setSyncFlag(LocalEntity.FLAG_ADDED);
                    boolean succesformula = formula.save();
                    Log.d(TAG, "formulaId: " + formula.getKey());
                    if (!succesformula) throw new Error("Error saving Formula!!!");

                    List<Cell> row = bodyList.get(i);
                    int rowSize = row.size();
                    Log.d(TAG, "rowSize: " + rowSize);
                    if (rowSize >= 1) {
                        row.remove(0);//REMOVER LA COLUMNA DE PESOS
                    }
                    int rowSizeWithoutPesoColumn = row.size();


                    Log.d(TAG, "rowSizeWithoutPesoColumn: " + rowSizeWithoutPesoColumn);
                    for (int j = 0; j < rowSizeWithoutPesoColumn; j++) {
                        if (j >= nivelListSize)
                            throw new Error("El índice del nivel está fuera del límite, index: " + j + ", size: " + nivelListSize);
                        if (!(row.get(j) instanceof CriterioEvaluacionUi)) continue;
                        CriterioEvaluacionUi criterioEvaluacionUi = (CriterioEvaluacionUi) row.get(j);
                        ValorTipoNotaUi nivel = criterioEvaluacionUi.getValorTipoNotaUi();
                        if (nivel == null) continue;
                        String cellTitle = criterioEvaluacionUi.getTitulo();
                        CriterioRubroEvaluacionC criterioRubroEvaluacion = new CriterioRubroEvaluacionC();
                        criterioRubroEvaluacion.setDescripcion(cellTitle);
                        criterioRubroEvaluacion.setRubroEvalProcesoId(procesoDetalle.getKey());
                        criterioRubroEvaluacion.setValorTipoNotaId(nivel.getId());
                        criterioRubroEvaluacion.setSyncFlag(LocalEntity.FLAG_ADDED);
                        boolean succesCritRubroEval = criterioRubroEvaluacion.save();
                        Log.d(TAG, "critRubroEvalId: " + criterioRubroEvaluacion.getKey());
                        Log.d(TAG, "critRubroEvalId: " + criterioRubroEvaluacion.toString());
                        if (!succesCritRubroEval)
                            throw new Error("Saving criterioRubroEvaluacion failed!!!");
                    }

                    List<CampoAccionUi> campoAccionUiList = requestValues.getSelectCampoAccionList();
                    for (CampoAccionUi campoAccionUi : campoAccionUiList) {
                        if (!indicador.equals(campoAccionUi.getIndicadorUi())) continue;
                        RubroEvaluacionProcesoCampotematicoC rubroCampotematico = new RubroEvaluacionProcesoCampotematicoC();
                        rubroCampotematico.setCampoTematicoId(campoAccionUi.getId());
                        rubroCampotematico.setRubroEvalProcesoId(procesoDetalle.getKey());
                        rubroCampotematico.setSyncFlag(BaseEntity.FLAG_ADDED);
                        boolean succesCampoAccion = rubroCampotematico.save();
                        if (!succesCampoAccion)
                            throw new Error("Saving RubroEvaluacionProcesoCampotematicoC failed!!!");
                    }


                }

                if (!TextUtils.isEmpty(tareaId)) {
                    TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso = new TareaRubroEvaluacionProceso();
                    tareaRubroEvaluacionProceso.setRubroEvalProcesoId(proceso.getKey());
                    tareaRubroEvaluacionProceso.setTareaId(tareaId);
                    tareaRubroEvaluacionProceso.setSyncFlag(TareaRubroEvaluacionProceso.FLAG_ADDED);
                    boolean succesTarea = tareaRubroEvaluacionProceso.save();
                    if (!succesTarea)
                        throw new Error("Error al guardar TareaRubroEvaluacionProceso");
                    TareasC tareasC = SQLite.select()
                            .from(TareasC.class)
                            .where(TareasC_Table.key.eq(tareaId))
                            .querySingle();
                    if (tareasC != null) {
                        tareasC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                        tareasC.save();
                    }
                }
            }else {
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(requestValues.getRubricaKeyEdit()))
                        .querySingle();
                if(rubroEvaluacionProcesoC==null) throw new Error("Error al encontrar la rubrica");
                rubroEvaluacionProcesoC.setTitulo(requestValues.getRubricaTitle());
                rubroEvaluacionProcesoC.setSubtitulo(requestValues.getRubricaAlias());
                rubroEvaluacionProcesoC.setEstrategiaEvaluacionId(requestValues.getIdEstrategiaEvaluacion());
                rubroEvaluacionProcesoC.setTipoEvaluacionId(tipoEvaluacion.getId());
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();

                for (List<Cell> list : requestValues.getBodyList()){
                    for (Cell cell : list){
                        if (cell instanceof CriterioEvaluacionUi){
                            CriterioEvaluacionUi criterioEvaluacionUi = (CriterioEvaluacionUi)cell;
                            Log.d("CriterioEvaluacion", criterioEvaluacionUi.toString());
                            CriterioRubroEvaluacionC criterioRubroEvaluacionC = SQLite.select()
                                    .from(CriterioRubroEvaluacionC.class)
                                    .where(CriterioRubroEvaluacionC_Table.key.eq(criterioEvaluacionUi.getKey()))
                                    .querySingle();
                            if(criterioRubroEvaluacionC==null) throw new Error("Error al encontrar un criterio");
                            criterioRubroEvaluacionC.setDescripcion(criterioEvaluacionUi.getTitulo());
                            criterioRubroEvaluacionC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                            criterioRubroEvaluacionC.save();
                        }
                    }
                }


                for (IndicadorUi indicadorUi: requestValues.getIndicadorListSelected()){
                    RubroEvaluacionProcesoC rubroEvaluacionProcesoDetalle = SQLite.select()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(indicadorUi.getRubroId()))
                            .querySingle();
                    if(rubroEvaluacionProcesoDetalle==null) throw new Error("Error al encontrar el rubro detalle");
                    rubroEvaluacionProcesoDetalle.setTitulo(indicadorUi.getTituloRubro());
                    rubroEvaluacionProcesoDetalle.setSubtitulo(indicadorUi.getSubTituloRubro());
                    rubroEvaluacionProcesoDetalle.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    rubroEvaluacionProcesoDetalle.save();
                }

            }
            databaseWrapper.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
            callback.onError();
        }finally {
            databaseWrapper.endTransaction();
        }

        callback.onSuccess(rubroEvaluacionProcesoId);

    }

    @Override
    public void getTipoNota(String id, CallbackSingle<TipoNotaUi> callback) {
        Log.d(TAG, "getTipoNota: " + id);

        TipoNotaC tipoNotac = tipoNotaDao.getTipoNotaConValores(id);
        TipoNotaUi tipoNotaUi = new TipoNotaUi();
        if (tipoNotac != null) {
            tipoNotaUi = TipoNotaUi.transform(tipoNotac);
            tipoNotaUi.setTitle(tipoNotac.getNombre());
            EscalaEvaluacion escalaEvaluacion = SQLite.select()
                    .from(EscalaEvaluacion.class)
                    .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotac.getEscalaEvaluacionId()))
                    .querySingle();
            EscalaEvaluacionUi escalaUi = new EscalaEvaluacionUi();
            if(escalaEvaluacion != null){
                escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
                escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
                escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
                escalaUi.setNombre(escalaEvaluacion.getNombre());
            }
            tipoNotaUi.setEscalaEvaluacionUi(escalaUi);
        }
        callback.onLoaded(tipoNotaUi);
    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {
            TareasC tareasC = tareasDao.getTareaId(id);
            if (tareasC != null){
                TareasUI tareasUI = new TareasUI();
                tareasUI.setDescripcion(tareasC.getInstrucciones());
                tareasUI.setFechaCreacionTarea(tareasC.getFechaEntrega());
                tareasUI.setTituloTarea(tareasC.getTitulo());
                getTareaUICallback.onTareaLoaded(tareasUI);
            }
    }

    @Override
    public TipoUi getFormaEvaluacion(String rubroEvalaucionId) {
        TipoUi tipoUi = null;
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalaucionId))
                .querySingle();
        if(rubroEvaluacionProcesoC!=null){
            Tipos tipos = tiposDao.get(rubroEvaluacionProcesoC.getFormaEvaluacionId());
            if(tipos!=null){
                tipoUi = new TipoUi(
                        tipos.getTipoId(),
                        tipos.getNombre()
                );
            }
        }

        return tipoUi;
    }

    @Override
    public TipoUi getTipoEvaluacion(String rubroEvalaucionId) {
        TipoUi tipoUi = null;
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalaucionId))
                .querySingle();
        if(rubroEvaluacionProcesoC!=null){
            T_RN_MAE_TIPO_EVALUACION tipos = tipoEvaluacionDao.get(rubroEvaluacionProcesoC.getTipoEvaluacionId());
            if(tipos!=null){
                tipoUi = new TipoUi(
                        tipos.getTipoEvaluacionId(),
                        tipos.getNombre()
                );
            }
        }
        return tipoUi;
    }

    @Override
    public TipoNotaUi getTipoNotaRubrica(String rubroEvalaucionId) {
        TipoNotaUi tipoNotaUi = null;

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalaucionId))
                .querySingle();
        if(rubroEvaluacionProcesoC!=null){
            TipoNotaC tipoNotaC = tipoNotaDao.getTipoNotaConValores(rubroEvaluacionProcesoC.getTipoNotaId());
            if(tipoNotaC!=null){
                tipoNotaUi = TipoNotaUi.transform(tipoNotaC);

                EscalaEvaluacion escalaEvaluacion = SQLite.select()
                        .from(EscalaEvaluacion.class)
                        .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotaC.getEscalaEvaluacionId()))
                        .querySingle();
                EscalaEvaluacionUi escalaUi = new EscalaEvaluacionUi();
                if(escalaEvaluacion != null){
                    escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
                    escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
                    escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
                    escalaUi.setNombre(escalaEvaluacion.getNombre());
                }
                tipoNotaUi.setEscalaEvaluacionUi(escalaUi);
            }

        }
        return tipoNotaUi;
    }

    @Override
    public List<CompetenciaUi> getCompetencias(String rubroEvalaucionId) {
        Log.d(TAG, "getCompetencias: " +rubroEvalaucionId);
        List<CompetenciaUi> competenciaUiList = new ArrayList<>();

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                        .eq(rubroEvalaucionId))
                .queryList();

        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
            List<String> rubroEvalaucionIdList = new ArrayList<>();
            rubroEvalaucionIdList.add(rubroEvaluacionProcesoC.getKey());
            TreeMap<Integer, Competencia> competenciaRubro = competenciaDao.getCompetenciaRubro(rubroEvalaucionIdList);
            if(!competenciaRubro.isEmpty()){
                Map.Entry<Integer, Competencia> entry = competenciaRubro.firstEntry();
                Competencia competencia = entry.getValue();
                Competencia capacidad  = competenciaDao.get(entry.getKey());
                Log.d(TAG, "sss: " + competencia.getNombre());
                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setId(competencia.getCompetenciaId());
                List<CapacidadUi> capacidadUiList = new ArrayList<>();
                int position = competenciaUiList.indexOf(competenciaUi);
                if(position==-1){
                    competenciaUi.setTitle(competencia.getNombre());
                    competenciaUi.setTipoId(competencia.getTipoId());
                    competenciaUi.setSelected(true);
                    competenciaUi.setCapacidadList(capacidadUiList);
                    competenciaUiList.add(competenciaUi);
                }else {
                    competenciaUi = competenciaUiList.get(position);
                    capacidadUiList = competenciaUi.getCapacidadList();
                }

                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(capacidad.getCompetenciaId());
                capacidadUi.setTitle(capacidad.getNombre());
                capacidadUi.setSelected(true);
                capacidadUiList.add(capacidadUi);

                List<IndicadorUi> indicadorUiList = new ArrayList<>();
                capacidadUi.setIndicadorList(indicadorUiList);
                IndicadorQuery itemIcds = SQLite.select(Utils.f_allcolumnTable(Icds_Table.icdId,
                        Icds_Table.titulo,
                        Icds_Table.alias,
                        Icds_Table.descripcion,
                        Icds_Table.estado,
                        Icds_Table.peso,
                        DesempenioIcd_Table.desempenioId.withTable(),
                        DesempenioIcd_Table.desempenioIcdId,
                        DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                        DesempenioIcd_Table.tipoId,
                        DesempenioIcd_Table.url))
                        .from(Icds.class)
                        .innerJoin(DesempenioIcd.class)
                        .on(Icds_Table.icdId.withTable()
                                .eq(DesempenioIcd_Table.icdId.withTable()))
                        .where(DesempenioIcd_Table.desempenioIcdId.eq(rubroEvaluacionProcesoC.getDesempenioIcdId()))
                        .queryCustomSingle(IndicadorQuery.class);

                if(itemIcds!=null){
                    Desempenio desempenio=getDesempenio(itemIcds);
                    IndicadorUi indicadorUi = new IndicadorUi();
                    indicadorUi.setIndicadorId(itemIcds.getDesempenioIcdId());
                    indicadorUi.setCompetenciaOwner(competenciaUi);
                    indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                    if(desempenio!=null)indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                    indicadorUi.setTitulo(itemIcds.getTitulo());
                    indicadorUi.setAlias(itemIcds.getAlias());
                    indicadorUi.setRubroId(rubroEvaluacionProcesoC.getKey());
                    indicadorUi.setTituloRubro(rubroEvaluacionProcesoC.getTitulo());
                    indicadorUi.setSubTituloRubro(rubroEvaluacionProcesoC.getSubtitulo());
                    indicadorUi.setUrl(itemIcds.getUrl());

                    indicadorUi.setEstado(itemIcds.getEstado());
                    switch (itemIcds.getTipoId()) {
                        case Icds.TIPO_HACER:
                            indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                            break;
                        case Icds.TIPO_SABER:
                            indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                            break;
                        case Icds.TIPO_SER:
                            indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.SER);
                            break;
                        default:
                            indicadorUi.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                            break;
                    }
                    indicadorUi.setPeso(itemIcds.getPeso());

                    indicadorUi.setSelected(true);
                    indicadorUi.setChecked(true);
                    List<CampoTematico> campoTematicoList = SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                            .from(CampoTematico.class)
                            .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                            .on(CampoTematico_Table.campoTematicoId.withTable()
                                    .eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                            .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                            .queryList();

                    indicadorUi.setCampoAccionList(getListCampoAccion(campoTematicoList, indicadorUi, capacidadUi, competenciaUi, true));
                    indicadorUiList.add(indicadorUi);

                    List<CriterioEvaluacionUi> criterioEvaluacionUiList = new ArrayList<>();
                    List<CriterioRubroEvaluacionC> criterioRubroEvaluacionCList = SQLite.select()
                            .from(CriterioRubroEvaluacionC.class)
                            .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                            .queryList();

                    for (CriterioRubroEvaluacionC criterioRubroEvaluacionC : criterioRubroEvaluacionCList){
                        CriterioEvaluacionUi criterioEvaluacionUi = new CriterioEvaluacionUi();
                        criterioEvaluacionUi.setKey(criterioRubroEvaluacionC.getKey());
                        criterioEvaluacionUi.setIndicadorUi(indicadorUi);
                        ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
                        valorTipoNotaUi.setId(criterioRubroEvaluacionC.getValorTipoNotaId());
                        criterioEvaluacionUi.setValorTipoNotaUi(valorTipoNotaUi);
                        criterioEvaluacionUi.setTitulo(criterioRubroEvaluacionC.getDescripcion());
                        criterioEvaluacionUi.setRubroEvalProcesoId(criterioRubroEvaluacionC.getRubroEvalProcesoId());
                        criterioEvaluacionUiList.add(criterioEvaluacionUi);
                    }

                    indicadorUi.setCriterioEvaluacionUiList(criterioEvaluacionUiList);
                }
            }
        }




        return competenciaUiList;
    }

    @Override
    public TipoNotaUi getNivelesTipoNotaRubrica(String keyRubroEvaluacion) {
        TipoNotaUi  tipoNotaUi = null;
       RubroEvaluacionProcesoC rubroEvaluacionProceso = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                        .eq(keyRubroEvaluacion))
                .querySingle();

        if(rubroEvaluacionProceso!=null){
            TipoNotaC tipoNotaC = tipoNotaDao.getTipoNotaConValores(rubroEvaluacionProceso.getTipoNotaId());
            if(tipoNotaC!=null){
                tipoNotaUi = TipoNotaUi.transform(tipoNotaC);
                EscalaEvaluacion escalaEvaluacion = SQLite.select()
                        .from(EscalaEvaluacion.class)
                        .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotaC.getEscalaEvaluacionId()))
                        .querySingle();
                EscalaEvaluacionUi escalaUi = new EscalaEvaluacionUi();
                if(escalaEvaluacion != null){
                    escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
                    escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
                    escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
                    escalaUi.setNombre(escalaEvaluacion.getNombre());
                }
                tipoNotaUi.setEscalaEvaluacionUi(escalaUi);
            }
        }
        return tipoNotaUi;
    }

    @Override
    public List<EstrategiaEvalUi> getEstrategiasEvaluacion(int cursoId) {

        List<EstrategiaEvaluacion>estrategiaEvaluacions= SQLite.select(Utils.f_allcolumnTable(EstrategiaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                .from(EstrategiaEvaluacion.class)
                .innerJoin(DimensionDesarrolloEstrategiaEvaluacionC.class)
                .on(EstrategiaEvaluacion_Table.estrategiaEvaluacionId.withTable().eq(DimensionDesarrolloEstrategiaEvaluacionC_Table.estrategiaEvaluacionId.withTable()))
                .innerJoin(DimensionDesarrollo.class)
                .on(DimensionDesarrolloEstrategiaEvaluacionC_Table.dimensionDesarrolloId.withTable().eq(DimensionDesarrollo_Table.dimensionDesarrolloId.withTable()))
                .innerJoin(DimensionDesarrolloDetalle.class)
                .on(DimensionDesarrollo_Table.dimensionDesarrolloId.withTable().eq(DimensionDesarrolloDetalle_Table.dimensionDesarrolloId.withTable()))
                .innerJoin(Cursos.class)
                .on(DimensionDesarrolloDetalle_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .where(Cursos_Table.cursoId.withTable().eq(cursoId))
                //.groupBy(Utils.f_allcolumnTable(EstrategiaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
        Log.d(TAG, "estrategiaEvaluacions " +estrategiaEvaluacions.size());

        List<EstrategiaEvalUi >estrategiaEvalUiList= new ArrayList<>();
        for(EstrategiaEvaluacion estrategiaEvaluacion: estrategiaEvaluacions){
            EstrategiaEvalUi estrategiaEvalUi= new EstrategiaEvalUi();
            estrategiaEvalUi.setEstrategiaId(estrategiaEvaluacion.getEstrategiaEvaluacionId());
            estrategiaEvalUi.setEstrategia(estrategiaEvaluacion.getNombre());
            estrategiaEvalUiList.add(estrategiaEvalUi);
        }


//        List<EstrategiaEvalUi >estrategiaEvalUiList2= new ArrayList<>();
//        EstrategiaEvalUi e1= new EstrategiaEvalUi();
//        e1.setEstrategiaId(1);
//        e1.setEstrategia("Resolucion de CAso");
//        estrategiaEvalUiList2.add(e1);
//        EstrategiaEvalUi e2= new EstrategiaEvalUi();
//        e2.setEstrategiaId(2);
//        e2.setEstrategia("Experimento");
//        estrategiaEvalUiList2.add(e2);
//
//        EstrategiaEvalUi e3= new EstrategiaEvalUi();
//        e3.setEstrategiaId(3);
//        e3.setEstrategia("Taller de Clase");
//        estrategiaEvalUiList2.add(e3);

        return estrategiaEvalUiList;
    }

    public Desempenio getDesempenio(IndicadorQuery itemIcds){
        return   SQLite.select()
                .from(Desempenio.class)
                .where(Desempenio_Table.desempenioId.eq(itemIcds.getDesempenioId()))
                .querySingle();
    }


    private List<CampoAccionUi> getListCampoAccion(List<CampoTematico> campoTematicoList, IndicadorUi indicadorUi, CapacidadUi capacidadUi, CompetenciaUi competenciaUi, boolean select) {


        List<Integer> parentCapotematicoIdList = new ArrayList<>();
        List<Integer> CapotematicoIdList = new ArrayList<>();
        for (CampoTematico campoTematico: campoTematicoList){
            CapotematicoIdList.add(campoTematico.getCampoTematicoId());
            parentCapotematicoIdList.add(campoTematico.getParentId());
        }
        List<CampoTematico> campoTematicoListPadres = SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                .from(CampoTematico.class)
                .where(CampoTematico_Table.campoTematicoId.in(parentCapotematicoIdList))
                .groupBy(CampoTematico_Table.ALL_COLUMN_PROPERTIES)
                .queryList();

        List<CampoTematico> campoTematicoSinHijo = SQLite.select(CampoTematico_Table.ALL_COLUMN_PROPERTIES)
                .from(CampoTematico.class)
                .where(CampoTematico_Table.parentId.eq(0))
                .and(CampoTematico_Table.campoTematicoId.in(CapotematicoIdList))
                .groupBy(CampoTematico_Table.ALL_COLUMN_PROPERTIES)
                .queryList();


        List<CampoAccionUi> campotematicoUiList = new ArrayList<>();
        for (CampoTematico itemCampoTematicoPadre: campoTematicoListPadres){

            CampoAccionUi campotematicoPadreUi = new CampoAccionUi();
            campotematicoPadreUi.setId(itemCampoTematicoPadre.getCampoTematicoId());
            campotematicoPadreUi.setCapacidadUi(capacidadUi);
            campotematicoPadreUi.setCompetenciaUi(competenciaUi);
            campotematicoPadreUi.setIndicadorUi(indicadorUi);
            campotematicoPadreUi.setChecked(select);
            campotematicoPadreUi.setTipo(CampoAccionUi.Tipo.PARENT);
            campotematicoPadreUi.setTitulo(itemCampoTematicoPadre.getTitulo());
            campotematicoUiList.add(campotematicoPadreUi);
            List<CampoAccionUi> campoAccionUis = new ArrayList<>();
            for (CampoTematico itemCampoTematico: campoTematicoList){
                if(itemCampoTematico.getParentId() == itemCampoTematicoPadre.getCampoTematicoId()){
                    CampoAccionUi campotematicoUi = new CampoAccionUi();
                    campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());
                    campotematicoUi.setCapacidadUi(capacidadUi);
                    campotematicoUi.setCompetenciaUi(competenciaUi);
                    campotematicoUi.setIndicadorUi(indicadorUi);
                    campotematicoUi.setChecked(select);
                    campotematicoUi.setTitulo(itemCampoTematico.getTitulo());
                    campotematicoUi.setTipo(CampoAccionUi.Tipo.CHILDREN);
                    campotematicoUi.setPadre(campotematicoPadreUi);
                    campoAccionUis.add(campotematicoUi);
                    campotematicoUiList.add(campotematicoUi);
                }
            }
            campotematicoPadreUi.setCampoAccionUiList(campoAccionUis);

        }

        for (CampoTematico itemCampoTematico: campoTematicoSinHijo) {
            CampoAccionUi campotematicoUi = new CampoAccionUi();
            campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());
            campotematicoUi.setCapacidadUi(capacidadUi);
            campotematicoUi.setCompetenciaUi(competenciaUi);
            campotematicoUi.setIndicadorUi(indicadorUi);
            campotematicoUi.setChecked(select);
            campotematicoUi.setTipo(CampoAccionUi.Tipo.DEFAULD);
            campotematicoUi.setTitulo(itemCampoTematico.getTitulo());
            campotematicoUiList.add(campotematicoUi);
        }

        return campotematicoUiList;
    }

}
