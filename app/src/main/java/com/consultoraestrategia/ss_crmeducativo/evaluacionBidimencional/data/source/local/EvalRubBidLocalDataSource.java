package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.local;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.equipoEvaluacionProceso.EquipoEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEquipo.RubroEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroIntegrante.RubroIntegranteDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EscalaEvaluacionUI;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoCompenciaEnum;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetGrupoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class EvalRubBidLocalDataSource implements EvalRubBidDataSource {
    public static final String TAG = EvalRubBidLocalDataSource.class.getSimpleName();
    public static final int COMPETENCIA_BASE = 347;
    public static final int COMPETENCIA_TRANS = 348;
    public static final int COMPETENCIA_ENFQ = 349;
    private final RubroProcesoDao rubroEvalProcesoDao;
    private final TipoNotaDao tipoNotaDao;
    private final CompetenciaDao competenciaDao;
    private final CalendarioPeriodoDao calendarioPeriodoDao;
    private final EvaluacionProcesoDao evaluacionProcesoDao;
    private final EquipoEvaluacionProcesoDao equipoEvaluacionProcesoDao;
    private final RubroEquipoDao rubroEquipoDao;
    private final RubroIntegranteDao rubroIntegrante;
    private PersonaDao personaDao;
    private DimensionObservadaDao dimensionObservadaDao;
    private IndicadorDao indicadorDao;

    public EvalRubBidLocalDataSource() {
        personaDao = InjectorUtils.providePersonaDao();
        dimensionObservadaDao = InjectorUtils.provideDimensionObservadaDao();
        rubroEvalProcesoDao = InjectorUtils.provideRubroProcesoDao();
        tipoNotaDao = InjectorUtils.provideTipoNotaDao();
        competenciaDao = InjectorUtils.provideCompetenciaDao();
        calendarioPeriodoDao = InjectorUtils.provideCalendarioPeriodo();
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
        equipoEvaluacionProcesoDao = InjectorUtils.provideEquipoEvaluacionProcesoDao();
        rubroEquipoDao = InjectorUtils.provideRubroEquipo();
        rubroIntegrante = InjectorUtils.provideRubroIntegrateDao();
        indicadorDao = InjectorUtils.provideIndicadorDao();
    }

    @Override
    public boolean deleteComentario(MensajeUi mensajeUi) {
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(mensajeUi.getRubroEvaluacionId()))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(mensajeUi.getAlumnoId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;
        /*evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        evaluacionProcesoC.save();*/

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario = SQLite.select()
                .from(RubroEvaluacionProcesoComentario.class)
                .where(RubroEvaluacionProcesoComentario_Table.key.eq(mensajeUi.getId()))
                .querySingle();

        if(rubroEvaluacionProcesoComentario!=null){
            rubroEvaluacionProcesoComentario.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoComentario.setDelete(1);
            rubroEvaluacionProcesoComentario.save();
        }

         return true;
    }

    @Override
    public boolean deleteArchivoComentario(ArchivoUi archivoComentarioUi) {

        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(archivoComentarioUi.getRubroEvaluacionId()))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(archivoComentarioUi.getAlumnoId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;
        evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        evaluacionProcesoC.save();

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        ArchivosRubroProceso archivosRubroProceso = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.key.eq(archivoComentarioUi.getId()))
                .querySingle();

        if(archivosRubroProceso!=null){
            archivosRubroProceso.setSyncFlag(BaseEntity.FLAG_UPDATED);
            archivosRubroProceso.setDelete(1);
            archivosRubroProceso.save();
        }
        return true;
    }

    @Override
    public PublicarEvaluacionUi getPublicacionEvaluacion(String rubroEvaluacionId, int personaId) {
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionId))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(personaId))
                .querySingle();

        PublicarEvaluacionUi publicarEvaluacionUi = new PublicarEvaluacionUi();
        if(evaluacionProcesoC!=null){
            publicarEvaluacionUi.setEvaluacionId(evaluacionProcesoC.getKey());
            publicarEvaluacionUi.setSelected(evaluacionProcesoC.getPublicado()==1);
        }

        return publicarEvaluacionUi;
    }

    @Override
    public void updatePublicacionEvaluacion(PublicarEvaluacionUi publicarEvaluacionUi) {
        try {

            EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.key.eq(publicarEvaluacionUi.getEvaluacionId()))
                    .querySingle();

            Log.d(TAG, "optionPublicar: " + publicarEvaluacionUi.getEvaluacionId());

            if(evaluacionProcesoC!=null){
                evaluacionProcesoC.setPublicado(publicarEvaluacionUi.isSelected()? 1 : 0);
                evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                evaluacionProcesoC.save();

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                        .querySingle();
                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateEvaluacionFormula(final UpdateEvaluacionFormulaCallback callback) {
        evaluacionProcesoDao.onUpdateEvaluacionFormula(new EvaluacionProcesoDao.UpdateEvaluacionFormulaCallback() {
            @Override
            public void onPreload() {
                callback.onPreLoad();
            }

            @Override
            public void onSucces() {
                callback.onLoaded(true);
            }

            @Override
            public void onError() {
                callback.onLoaded(false);
            }
        });
    }


    @Override
    public void getRubBid(GetRubBid.RequestValues requestValues, Callback<RubBidUi> callback) {
        Log.d(TAG, "getRubBid");
        String rubricaBidimencionalId = requestValues.getRubBidId();
        int cargaCursoID = requestValues.getCargaCursoId();
        int cursoId = requestValues.getCursoId();
        Log.d(TAG, "id: " + rubricaBidimencionalId);
        Log.d(TAG, "cargaCursoID: " + cargaCursoID);
        Log.d(TAG, "cursoId: " + cursoId);

        //RubroEvaluacionProcesoC proceso = getRubEvalProc(rubricaBidimencionalId);
        List<RubroEvalRNPFormulaC> formulaList = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubricaBidimencionalId))
                .queryList();

        List<String> rubroEvalProcesoKeyList = new ArrayList<>();
        rubroEvalProcesoKeyList.add(rubricaBidimencionalId);
        for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC : formulaList)
            rubroEvalProcesoKeyList.add(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId());
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = rubroEvalProcesoDao.get(rubroEvalProcesoKeyList);
        List<TipoNotaC> tipoNotaCList = tipoNotaDao.getListPorRubrosEscala(rubroEvalProcesoKeyList);
        TreeMap<Integer, Competencia> competenciaList = competenciaDao.getCompetenciaRubro(rubroEvalProcesoKeyList);
        List<CalendarioPeriodo> calendarioPeriodoList = calendarioPeriodoDao.getRubrosEvalProceso(rubroEvalProcesoKeyList);
        List<IndicadorQuery> indicadorUiList = indicadorDao.getIcdsporRubroProceso(rubroEvalProcesoKeyList);

        RubroEvaluacionProcesoC proceso = new RubroEvaluacionProcesoC();
        if (!rubroEvaluacionProcesoCList.isEmpty())
            proceso = rubroEvaluacionProcesoCList.get(0);

        TipoNotaUi tipoNotaUi = getTipoNota(proceso, tipoNotaCList);


        Log.d(TAG, "tipoNotaUi: " + tipoNotaUi.toString());
        if (tipoNotaUi == null) {
            callback.onLoaded(null);
            return;
        }

        RubBidUi rubBidUi = new RubBidUi();
        rubBidUi.setId(rubricaBidimencionalId);
        rubBidUi.setTitle(proceso.getTitulo());
        rubBidUi.setAlias(proceso.getSubtitulo());
        rubBidUi.setTipoNota(tipoNotaUi);


        //Importacion
        rubBidUi.setCalendarioPeriodoId(proceso.getCalendarioPeriodoId());
        //Importacion

        List<RubEvalProcUi> rubEvalProcUis = new ArrayList<>();
        for (RubroEvalRNPFormulaC formula : formulaList) {
            String secId = formula.getRubroEvaluacionSecId();
            //RubroEvaluacionProcesoC procesoDetalle = getRubEvalProc(secId);
            RubroEvaluacionProcesoC procesoDetalle = null;
            for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList) {
                if (itemRubroEvaluacionProcesoC.getKey().equals(secId)) {
                    procesoDetalle = itemRubroEvaluacionProcesoC;
                    break;
                }
            }
            if (procesoDetalle == null) continue;

            RubEvalProcUi rubEvalProc = new RubEvalProcUi();
            rubEvalProc.setId(secId);
            rubEvalProc.setTitulo(procesoDetalle.getTitulo());
            rubEvalProc.setPeso(formula.getPeso());
            rubEvalProc.setIcdsId(procesoDetalle.getDesempenioIcdId());
            rubEvalProcUis.add(rubEvalProc);
            TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
            tipoNotaUiDetalle.setRubEvalProcUi(rubEvalProc);
            rubEvalProc.setTipoNotaUi(tipoNotaUiDetalle);

            /*Competencia competencia = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .from(Competencia.class)
                    .innerJoin(Competencia.class).as("Capacidad")
                    .on(Competencia_Table.competenciaId.withTable()
                            .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                    .where(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build()).is(procesoDetalle.getCompetenciaId()))
                    .querySingle();*/

            Competencia competencia = null;
            for (Map.Entry<Integer, Competencia> entry : competenciaList.entrySet()) {
                int capaciadId = entry.getKey();
                Competencia itemCompetencia = entry.getValue();
                Log.d(TAG, capaciadId + "==" + procesoDetalle.getCompetenciaId());
                if (capaciadId == procesoDetalle.getCompetenciaId()) {
                    competencia = itemCompetencia;
                    break;
                }
            }

            if (competencia != null) {
                Log.d(TAG, "TipoId: " + competencia.getTipoId());
                switch (competencia.getTipoId()) {
                    case COMPETENCIA_BASE:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_BASE);
                        break;
                    case COMPETENCIA_ENFQ:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_ENFQ);
                        break;
                    case COMPETENCIA_TRANS:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_TRANS);
                        break;
                }
            }
            IndicadorQuery indicadorQuery = null;
            Log.d(TAG, "indicadorUiList size: " + indicadorUiList.size());
            for (IndicadorQuery itemIndicadorQuery : indicadorUiList) {
                if (itemIndicadorQuery.getDesempenioIcdId() == procesoDetalle.getDesempenioIcdId()) {
                    indicadorQuery = itemIndicadorQuery;
                    break;
                }
            }
            if (indicadorQuery != null) {
                Log.d(TAG, "indicadorQuery tipo " + indicadorQuery.getTipoId());
                rubEvalProc.setUrl(indicadorQuery.getUrl());
                switch (indicadorQuery.getTipoId()) {
                    case Icds.TIPO_HACER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                        break;
                    case Icds.TIPO_SABER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                        break;
                    case Icds.TIPO_SER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SER);
                        break;
                    default:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                        break;
                }
            }

        }

        int evalProcListSize = rubEvalProcUis.size();
        if (evalProcListSize < 1) {
            callback.onLoaded(null);
            return;
        }
        rubBidUi.setRubroEvalProcesoList(rubEvalProcUis);

        RubroEvaluacionProcesoC procesoDetalle = rubroEvaluacionProcesoCList.get(1);
        TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
        if (tipoNotaUiDetalle == null) {
            callback.onLoaded(null);
            return;
        }

        List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUiDetalle.getValorTipoNotaList();
        rubBidUi.setValorTipoNotaUiList(valorTipoNotaUiList);
        Log.d(TAG, "tipoNotaUi: " + tipoNotaUi.getEscalaEvaluacionUi().toString());

        rubBidUi.setDisabledEval(true);
        callback.onLoaded(rubBidUi);

    }

    public RubBidUi getRubBid(List<RubroEvalRNPFormulaC> formulaList, List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList, List<TipoNotaC> tipoNotaCList, TreeMap<Integer, Competencia> competenciaList, List<CalendarioPeriodo> calendarioPeriodoList, List<IndicadorQuery> indicadorUiList) {

        RubroEvaluacionProcesoC proceso = rubroEvaluacionProcesoCList.get(0);

        if (proceso != null) {
            TipoNotaUi tipoNotaUi = getTipoNota(proceso, tipoNotaCList);

            RubBidUi rubBidUi = new RubBidUi();
            rubBidUi.setId(proceso.getKey());
            rubBidUi.setTitle(proceso.getTitulo());
            rubBidUi.setAlias(proceso.getSubtitulo());
            rubBidUi.setTipoNota(tipoNotaUi);
            //Importacion
            rubBidUi.setCalendarioPeriodoId(proceso.getCalendarioPeriodoId());
            //Importacion
            Log.d(TAG, "Aqui Sixe: " + formulaList.size());
            List<RubEvalProcUi> rubEvalProcUis = new ArrayList<>();
            for (RubroEvalRNPFormulaC formula : formulaList) {
                String secId = formula.getRubroEvaluacionSecId();
                //RubroEvaluacionProcesoC procesoDetalle = getRubEvalProc(secId);
                RubroEvaluacionProcesoC procesoDetalle = null;
                for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList) {
                    Log.d(TAG, "Rubro :" + itemRubroEvaluacionProcesoC.getKey() + "==" + secId);
                    if (itemRubroEvaluacionProcesoC.getKey().equals(secId)) {
                        procesoDetalle = itemRubroEvaluacionProcesoC;
                        break;
                    }
                }
                if (procesoDetalle == null) continue;

                RubEvalProcUi rubEvalProc = new RubEvalProcUi();
                rubEvalProc.setId(secId);
                rubEvalProc.setTitulo(procesoDetalle.getTitulo());
                rubEvalProc.setPeso(formula.getPeso());
                rubEvalProc.setIcdsId(procesoDetalle.getDesempenioIcdId());
                rubEvalProcUis.add(rubEvalProc);
                TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
                tipoNotaUiDetalle.setRubEvalProcUi(rubEvalProc);
                rubEvalProc.setTipoNotaUi(tipoNotaUiDetalle);

            /*Competencia competencia = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .from(Competencia.class)
                    .innerJoin(Competencia.class).as("Capacidad")
                    .on(Competencia_Table.competenciaId.withTable()
                            .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                    .where(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build()).is(procesoDetalle.getCompetenciaId()))
                    .querySingle();*/

                Competencia competencia = null;
                Log.d(TAG, "Competencia size: " + competenciaList.size());
                for (Map.Entry<Integer, Competencia> entry : competenciaList.entrySet()) {
                    int capaciadId = entry.getKey();
                    Competencia itemCompetencia = entry.getValue();
                    Log.d(TAG, "Competencia: " + capaciadId + "==" + procesoDetalle.getCompetenciaId());
                    if (capaciadId == procesoDetalle.getCompetenciaId()) {
                        competencia = itemCompetencia;
                        break;
                    }
                }
                if (competencia != null) {
                    Log.d(TAG, "TipoId: " + competencia.getTipoId());
                    switch (competencia.getTipoId()) {
                        case COMPETENCIA_BASE:
                            rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_BASE);
                            break;
                        case COMPETENCIA_ENFQ:
                            rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_ENFQ);
                            break;
                        case COMPETENCIA_TRANS:
                            rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_TRANS);
                            break;
                    }
                }

                IndicadorQuery indicadorQuery = null;
                Log.d(TAG, "indicadorUiList size: " + indicadorUiList.size());
                for (IndicadorQuery itemIndicadorQuery : indicadorUiList) {
                    if (itemIndicadorQuery.getDesempenioIcdId() == procesoDetalle.getDesempenioIcdId()) {
                        indicadorQuery = itemIndicadorQuery;
                        break;
                    }
                }
                if (indicadorQuery != null) {
                    Log.d(TAG, "indicadorQuery tipo " + indicadorQuery.getTipoId());
                    rubEvalProc.setUrl(indicadorQuery.getUrl());
                    switch (indicadorQuery.getTipoId()) {
                        case Icds.TIPO_HACER:
                            rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                            break;
                        case Icds.TIPO_SABER:
                            rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                            break;
                        case Icds.TIPO_SER:
                            rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SER);
                            break;
                        default:
                            rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                            break;
                    }
                }
            }

            int evalProcListSize = rubEvalProcUis.size();
            if (evalProcListSize < 1) {
                return null;
            }
            rubBidUi.setRubroEvalProcesoList(rubEvalProcUis);

            RubroEvaluacionProcesoC procesoDetalle = rubroEvaluacionProcesoCList.get(1);
            TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
            if (tipoNotaUiDetalle == null) {
                return null;
            }
            List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUiDetalle.getValorTipoNotaList();
            rubBidUi.setValorTipoNotaUiList(valorTipoNotaUiList);
            //Log.d(TAG, "tipoNotaUi: :(" + tipoNotaUi.getEscalaEvaluacionUi().toString());

            rubBidUi.setDisabledEval(true);

        }
        TipoNotaUi tipoNotaUi = getTipoNota(proceso, tipoNotaCList);

        RubBidUi rubBidUi = new RubBidUi();
        rubBidUi.setId(proceso.getKey());
        rubBidUi.setTitle(proceso.getTitulo());
        rubBidUi.setAlias(proceso.getSubtitulo());
        rubBidUi.setTipoNota(tipoNotaUi);
        //Importacion
        rubBidUi.setCalendarioPeriodoId(proceso.getCalendarioPeriodoId());
        //Importacion
        Log.d(TAG, "Aqui Sixe: " + formulaList.size());
        List<RubEvalProcUi> rubEvalProcUis = new ArrayList<>();
        for (RubroEvalRNPFormulaC formula : formulaList) {
            String secId = formula.getRubroEvaluacionSecId();
            //RubroEvaluacionProcesoC procesoDetalle = getRubEvalProc(secId);
            RubroEvaluacionProcesoC procesoDetalle = null;
            for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList) {
                Log.d(TAG, "Rubro :" + itemRubroEvaluacionProcesoC.getKey() + "==" + secId);
                if (itemRubroEvaluacionProcesoC.getKey().equals(secId)) {
                    procesoDetalle = itemRubroEvaluacionProcesoC;
                    break;
                }
            }
            if (procesoDetalle == null) continue;

            RubEvalProcUi rubEvalProc = new RubEvalProcUi();
            rubEvalProc.setId(secId);
            rubEvalProc.setTitulo(procesoDetalle.getTitulo());
            rubEvalProc.setPeso(formula.getPeso());
            rubEvalProc.setIcdsId(procesoDetalle.getDesempenioIcdId());
            rubEvalProcUis.add(rubEvalProc);
            TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
            tipoNotaUiDetalle.setRubEvalProcUi(rubEvalProc);
            rubEvalProc.setTipoNotaUi(tipoNotaUiDetalle);

            /*Competencia competencia = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .from(Competencia.class)
                    .innerJoin(Competencia.class).as("Capacidad")
                    .on(Competencia_Table.competenciaId.withTable()
                            .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                    .where(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build()).is(procesoDetalle.getCompetenciaId()))
                    .querySingle();*/

            Competencia competencia = null;
            Log.d(TAG, "Competencia size: " + competenciaList.size());
            for (Map.Entry<Integer, Competencia> entry : competenciaList.entrySet()) {
                int capaciadId = entry.getKey();
                Competencia itemCompetencia = entry.getValue();
                Log.d(TAG, "Competencia: " + capaciadId + "==" + procesoDetalle.getCompetenciaId());
                if (capaciadId == procesoDetalle.getCompetenciaId()) {
                    competencia = itemCompetencia;
                    break;
                }
            }
            if (competencia != null) {
                Log.d(TAG, "TipoId: " + competencia.getTipoId());
                switch (competencia.getTipoId()) {
                    case COMPETENCIA_BASE:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_BASE);
                        break;
                    case COMPETENCIA_ENFQ:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_ENFQ);
                        break;
                    case COMPETENCIA_TRANS:
                        rubEvalProc.setTipoCompenciaEnum(TipoCompenciaEnum.COMPETENCIA_TRANS);
                        break;
                }
            }

            IndicadorQuery indicadorQuery = null;
            for (IndicadorQuery itemIndicadorQuery : indicadorUiList) {
                if (itemIndicadorQuery.getDesempenioIcdId() == procesoDetalle.getDesempenioIcdId()) {
                    indicadorQuery = itemIndicadorQuery;
                    break;
                }
            }
            if (indicadorQuery != null) {
                Log.d(TAG, "indicadorQuery tipo " + indicadorQuery.getTipoId());
                rubEvalProc.setUrl(indicadorQuery.getUrl());
                switch (indicadorQuery.getTipoId()) {
                    case Icds.TIPO_HACER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.HACER);
                        break;
                    case Icds.TIPO_SABER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SABER);
                        break;
                    case Icds.TIPO_SER:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.SER);
                        break;
                    default:
                        rubEvalProc.setTipoIndicadorUi(TipoIndicadorUi.DEFAULT);
                        break;
                }
            }
        }

        int evalProcListSize = rubEvalProcUis.size();
        if (evalProcListSize < 1) {
            return null;
        }
        rubBidUi.setRubroEvalProcesoList(rubEvalProcUis);

        RubroEvaluacionProcesoC procesoDetalle = new RubroEvaluacionProcesoC();
        if (rubroEvaluacionProcesoCList.size()>1)
            procesoDetalle = rubroEvaluacionProcesoCList.get(1);
        TipoNotaUi tipoNotaUiDetalle = getTipoNota(procesoDetalle, tipoNotaCList);
        if (tipoNotaUiDetalle == null) {
            return null;
        }
        List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUiDetalle.getValorTipoNotaList();
        rubBidUi.setValorTipoNotaUiList(valorTipoNotaUiList);

        if(tipoNotaUi.getEscalaEvaluacionUi()==null){
            Log.d(TAG, "tipoNotaUi: :(" + tipoNotaUi.getId());
        }else {
            Log.d(TAG, "tipoNotaUi: :)" + tipoNotaUi.getId());
        }
//        Log.d(TAG, "tipoNotaUi: :(" + tipoNotaUi.getEscalaEvaluacionUi().toString());

        rubBidUi.setDisabledEval(true);

        return rubBidUi;
    }

    @Override
    public void getGrupoConNotasProceso(GetGrupoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {
        Log.d(TAG, "getAlumnosConNotasProceso");
        int cargaCursoId = requestValues.getCargaCursoId();
        String rubBidId = requestValues.getRubBidId();
        int entidadId = requestValues.getEntidadId();
        int georeferenciaId = requestValues.getGeoreferenciaId();
        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "rubBidId: " + rubBidId);


        List<RubroEvalRNPFormulaC> formulaList = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubBidId))
                .queryList();

        List<String> rubroEvalProcesoKeyList = new ArrayList<>();
        rubroEvalProcesoKeyList.add(rubBidId);
        for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC : formulaList)
            rubroEvalProcesoKeyList.add(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId());
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = rubroEvalProcesoDao.get(rubroEvalProcesoKeyList);
        List<TipoNotaC> tipoNotaCList = tipoNotaDao.getListPorRubrosEscala(rubroEvalProcesoKeyList);
        TreeMap<Integer, Competencia> competenciaList = competenciaDao.getCompetenciaRubro(rubroEvalProcesoKeyList);
        List<IndicadorQuery> indicadorQueryList = indicadorDao.getIcdsporRubroProceso(rubroEvalProcesoKeyList);
        List<CalendarioPeriodo> calendarioPeriodoList = calendarioPeriodoDao.getRubrosEvalProceso(rubroEvalProcesoKeyList);


        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> todosEquipoList = rubroEquipoDao.getEquipoRubroList(rubroEvalProcesoKeyList);
        List<String> equipoRubroKeys = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemRubroEquipo : todosEquipoList)
            equipoRubroKeys.add(itemRubroEquipo.getKey());
        List<EquipoEvaluacionProcesoC> todosEquipoEvaluacionProcesoCList = equipoEvaluacionProcesoDao.getEquipoEvaluacionProcesoRubrica(rubroEvalProcesoKeyList, equipoRubroKeys);
        List<EvaluacionProcesoC> todosEvaluacionProcesoCList = evaluacionProcesoDao.getEvaluacionProcesoRubricaEquipo(rubroEvalProcesoKeyList, equipoRubroKeys);
        List<String> evalauciones = new ArrayList<>();
        for (EvaluacionProcesoC evaluacionProcesoC: todosEvaluacionProcesoCList)evalauciones.add(evaluacionProcesoC.getKey());

        List<Persona> personaComentarios = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(EvaluacionProcesoC_Table.alumnoId.withTable()
                        .eq(Persona_Table.personaId.withTable()))
                .innerJoin(RubroEvaluacionProcesoComentario.class)
                .on(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.withTable()
                        .eq(EvaluacionProcesoC_Table.key.withTable()))
                .where(EvaluacionProcesoC_Table.key.withTable().in(evalauciones))
                .and(RubroEvaluacionProcesoComentario_Table.delete.withTable().notEq(1))
                .groupBy(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .queryList();


        RubBidUi rubBidUi = getRubBid(formulaList, rubroEvaluacionProcesoCList, tipoNotaCList, competenciaList, calendarioPeriodoList, indicadorQueryList);

        List<RubEvalProcUi> rubEvalProcList = rubBidUi.getRubroEvalProcesoList();

        /*for (RubroEvalRNPFormulaC formula : formulaList) {
            RubroEvaluacionProcesoC procesoDetalle = getRubEvalProc(formula.getRubroEvaluacionSecId());
            if (procesoDetalle == null) continue;
            RubEvalProcUi rubEvalProc = new RubEvalProcUi();
            rubEvalProc.setId(formula.getRubroEvaluacionSecId());
            rubEvalProc.setTitulo(procesoDetalle.getTitulo());
            rubEvalProc.setTipoNotaId(procesoDetalle.getTipoNotaId());
            rubEvalProc.setIcdsId(procesoDetalle.getDesempenioIcdId());
            rubEvalProcList.add(rubEvalProc);
        }*/

        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> rubricaEquipoList = getEquipoList(rubBidId, todosEquipoList);
        List<String> rubroEquipoKeyList = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemRubroEquipo : rubricaEquipoList)
            rubroEquipoKeyList.add(itemRubroEquipo.getKey());
        List<PersonaContratoQuery> rubricaPersonList = personaDao.getAlumnosDeRubroEquipo(rubroEquipoKeyList, cargaCursoId);

        List<ColumnHeader> gruposProcesoLists = new ArrayList<>();
        List<List<Cell>> alumnoEvaluacionLists = new ArrayList<>();
        List<GrupoProcesoUi> grupoProcesoUiList = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemEquipo : rubricaEquipoList) {
            GrupoProcesoUi grupoProcesoUi = new GrupoProcesoUi();
            grupoProcesoUi.setId(itemEquipo.getKey());
            grupoProcesoUi.setNombre(itemEquipo.getNombreEquipo());
            //grupoProcesoUi.setUrlProfile(itemEquipo.get);
            grupoProcesoUiList.add(grupoProcesoUi);
            gruposProcesoLists.add(grupoProcesoUi);
            List<EvalProcUi> notaUiListGrupo = new ArrayList<>();

            for (RubEvalProcUi rubEvalProcUi : rubEvalProcList) {
                EvalProcUi evalProcUi = new EvalProcUi();
                evalProcUi.setRubEvalProcId(rubEvalProcUi.getId());
                evalProcUi.setEquipoId(itemEquipo.getKey());
                evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.GRUPAL);
                notaUiListGrupo.add(evalProcUi);

                /*TipoNotaC tipoNota = SQLite.select()
                        .from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.is(rubEvalProcUi.getTipoNotaId()))
                        .querySingle();*/
                TipoNotaUi tipoNota = rubEvalProcUi.getTipoNotaUi();
                if (tipoNota == null) continue;
                switch (tipoNota.getTipoId()) {
                    case 409:
                        evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_ICONOS);
                        break;
                    case 412:
                        evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_VALORES);
                        break;
                }

                EquipoEvaluacionProcesoC equipoEvaluacionProceso = null;
                try {

                    /*T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = SQLite.select()
                            .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                            .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.eq(rubEvalProcUi.getId()))
                            .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.equipoId.eq(itemEquipo.getEquipoId()))
                            .querySingle();*/
                    T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = null;
                    for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemRubroEquipo : todosEquipoList) {
                        if (itemRubroEquipo.getRubroEvalProcesoId().equals(rubEvalProcUi.getId())
                                && itemRubroEquipo.getEquipoId().equals(itemEquipo.getEquipoId())) {
                            procesoEquipoc = itemRubroEquipo;
                        }
                    }
                    /*equipoEvaluacionProceso = SQLite.select()
                            .from(EquipoEvaluacionProcesoC.class)
                            .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubEvalProcUi.getId()))
                            .and(EquipoEvaluacionProcesoC_Table.equipoId.is(procesoEquipoc.getKey()))
                            .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                            .querySingle();*/

                    for (EquipoEvaluacionProcesoC itemEquipoEvaluacionProcesoC : todosEquipoEvaluacionProcesoCList) {
                        if (itemEquipoEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubEvalProcUi.getId())
                                && itemEquipoEvaluacionProcesoC.getEquipoId().equals(procesoEquipoc.getKey())) {
                            equipoEvaluacionProceso = itemEquipoEvaluacionProcesoC;
                            break;
                        }
                    }

                } catch (Exception e) {
                    Log.d(TAG, "No se encuentra la evaluacion");
                }


                if (equipoEvaluacionProceso == null) continue;
                evalProcUi.setNota(equipoEvaluacionProceso.getNota());
                evalProcUi.setEscala(equipoEvaluacionProceso.getEscala());
                evalProcUi.setValorTipoNotaId(equipoEvaluacionProceso.getValorTipoNotaId());

                try {

                    /*ValorTipoNotaC valorTipoNotaC = SQLite.select()
                            .from(ValorTipoNotaC.class)
                            .where(ValorTipoNotaC_Table.valorTipoNotaId.eq(equipoEvaluacionProceso.getValorTipoNotaId()))
                            .querySingle();*/
                    ValorTipoNotaUi valorTipoNotaUi = null;
                    for (ValorTipoNotaUi itemValorTipoNotaUi : tipoNota.getValorTipoNotaList()) {
                        if (itemValorTipoNotaUi.getId().equals(equipoEvaluacionProceso.getValorTipoNotaId())) {
                            valorTipoNotaUi = itemValorTipoNotaUi;
                        }
                    }
                    switch (evalProcUi.getTipo()) {
                        case SELECTOR_ICONOS:
                            evalProcUi.setEscala(valorTipoNotaUi.getIcono());
                            break;
                        case SELECTOR_VALORES:
                            evalProcUi.setEscala(valorTipoNotaUi.getTitulo());
                            break;
                    }
                } catch (Exception ignored) {
                }

            }
            grupoProcesoUi.setEvalProcList(notaUiListGrupo);

            NotaUi notaTotaUiGrupo = new NotaUi();
            grupoProcesoUi.setNotaUi(notaTotaUiGrupo);
            notaTotaUiGrupo.setPublicarVisible(false);
            /*EquipoEvaluacionProcesoC equipoEvaluacionProcesoBidimencional = SQLite.select()
                    .from(EquipoEvaluacionProcesoC.class)
                    .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubBidId))
                    .and(EquipoEvaluacionProcesoC_Table.equipoId.is(itemEquipo.getKey()))
                    .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                    .querySingle();*/
            EquipoEvaluacionProcesoC equipoEvaluacionProcesoBidimencional = null;
            for (EquipoEvaluacionProcesoC itemEquipoEvaluacionProcesoC : todosEquipoEvaluacionProcesoCList) {
                if (itemEquipoEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubBidId) &&
                        itemEquipoEvaluacionProcesoC.getEquipoId().equals(itemEquipo.getKey())) {
                    equipoEvaluacionProcesoBidimencional = itemEquipoEvaluacionProcesoC;
                    break;
                }
            }

            if (equipoEvaluacionProcesoBidimencional != null) {
                notaTotaUiGrupo.setId(equipoEvaluacionProcesoBidimencional.getKey());
                notaTotaUiGrupo.setNota(equipoEvaluacionProcesoBidimencional.getNota());
            }

            List<Cell> cellList = new ArrayList<>();
            cellList.add(notaTotaUiGrupo);
            cellList.addAll(notaUiListGrupo);
            alumnoEvaluacionLists.add(cellList);

            /*List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.is(itemEquipo.getKey()))
                    .queryList();*/
            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = itemEquipo.getIntegrantecList();

            List<AlumnoProcesoUi> alumnoProcesoUiList = new ArrayList<>();
            grupoProcesoUi.setAlumnoProcesoUis(alumnoProcesoUiList);
            List<List<Cell>> alumnosCell = new ArrayList<>();
            grupoProcesoUi.setCellList(alumnosCell);
            int posicion = 0;
            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC integrante : integranteList) {

                /*PersonaUi persona = SQLite.select()
                        .from(PersonaUi.class)
                        .where(Persona_Table.personaId.is(integrante.getPersonaId()))
                        .querySingle();*/
                PersonaContratoQuery persona = null;
                for (PersonaContratoQuery itemPersona : rubricaPersonList) {
                    if (itemPersona.getPersonaId() == integrante.getPersonaId()) {
                        persona = itemPersona;
                        break;
                    }
                }

                if (persona == null) continue;

                String urlProfile = persona.getFoto();
                /*if (TextUtils.isEmpty(urlProfile)) {
                    if (persona.getGenero().equals("M")) {
                        urlProfile = "https://www.invest-direct-online.com/images/img-school-fee-planning-profile.jpg";
                    } else if (persona.getGenero().equals("F")) {
                        urlProfile = "https://marketplace.canva.com/MAB4Yqx-uWs/1/thumbnail/canva-young-executive-woman-profile-icon--MAB4Yqx-uWs.png";
                    }
                }*/
                posicion++;
                AlumnoProcesoUi alumnoProcesoUi = new AlumnoProcesoUi();
                alumnoProcesoUi.setPocision(posicion);
                alumnoProcesoUi.setId(persona.getPersonaId());
                alumnoProcesoUi.setNombre(Utils.getFirstWord(persona.getNombres()));
                alumnoProcesoUi.setApellidos(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
                alumnoProcesoUi.setUrlProfile(urlProfile);
                alumnoProcesoUi.setGrupoProcesoUi(grupoProcesoUi);
                alumnoProcesoUi.setEquipoId(integrante.getRubroEvaluacionEquipoId());
                alumnoProcesoUi.setVigente(persona.getVigente() != 0);
                for (Persona personacometario: personaComentarios){
                   if(persona.getPersonaId() == personacometario.getPersonaId()){
                       alumnoProcesoUi.setComentario(true);
                       break;
                   }
                }

                List<EvalProcUi> evalProcUiList = new ArrayList<>();
                for (RubEvalProcUi rubEvalProcUi : rubEvalProcList) {
                    EvalProcUi evalProcUi = new EvalProcUi();
                    evalProcUi.setRubEvalProcId(rubEvalProcUi.getId());
                    evalProcUi.setAlumnoId(persona.getPersonaId());
                    evalProcUi.setAlumnoActivo(persona.getVigente() !=0);
                    evalProcUi.setEquipoId(itemEquipo.getKey());
                    evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.INDIVIDUAL);
                    evalProcUiList.add(evalProcUi);

                    /*TipoNotaC tipoNota = SQLite.select()
                            .from(TipoNotaC.class)
                            .where(TipoNotaC_Table.tipoNotaId.is(rubEvalProcUi.getTipoNotaId()))
                            .querySingle();*/
                    TipoNotaUi tipoNota = rubEvalProcUi.getTipoNotaUi();

                    if (tipoNota == null) continue;
                    switch (tipoNota.getTipoId()) {
                        case 409:
                            evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_ICONOS);
                            break;
                        case 412:
                            evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_VALORES);
                            break;
                    }


                    EvaluacionProcesoC evaluacionProceso = null;
                    try {

                        /*T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = SQLite.select()
                                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.eq(rubEvalProcUi.getId()))
                                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.equipoId.eq(itemEquipo.getEquipoId()))
                                .querySingle();*/

                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = null;
                        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemRubroEquipo : todosEquipoList) {
                            if (itemRubroEquipo.getRubroEvalProcesoId().equals(rubEvalProcUi.getId())
                                    && itemRubroEquipo.getEquipoId().equals(itemEquipo.getEquipoId())) {
                                procesoEquipoc = itemRubroEquipo;
                            }
                        }


                        /*evaluacionProceso = SQLite.select()
                                .from(EvaluacionProcesoC.class)
                                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubEvalProcUi.getId()))
                                .and(EvaluacionProcesoC_Table.alumnoId.is(persona.getPersonaId()))
                                .and(EvaluacionProcesoC_Table.equipoId.eq(procesoEquipoc.getKey()))
                                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                                .querySingle();*/

                        for (EvaluacionProcesoC itemEvaluacionProcesoC : todosEvaluacionProcesoCList) {
                            if (itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubEvalProcUi.getId()) &&
                                    itemEvaluacionProcesoC.getAlumnoId() == persona.getPersonaId() &&
                                    itemEvaluacionProcesoC.getEquipoId().equals(procesoEquipoc.getKey())) {
                                evaluacionProceso = itemEvaluacionProcesoC;
                                break;
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, "No se encuentra la evaluacion rubroEvalProcesoId: " + rubEvalProcUi.getId() + " equipoId: " + itemEquipo.getKey());
                    }


                    if (evaluacionProceso == null) continue;
                    evalProcUi.setNota(evaluacionProceso.getNota());
                    evalProcUi.setEscala(evaluacionProceso.getEscala());
                    Log.d(TAG, "ValorTipoNotaId: " + evaluacionProceso.getValorTipoNotaId());
                    evalProcUi.setValorTipoNotaId(evaluacionProceso.getValorTipoNotaId());
                    try {

                        /*ValorTipoNotaC valorTipoNotaC = SQLite.select()
                                .from(ValorTipoNotaC.class)
                                .where(ValorTipoNotaC_Table.valorTipoNotaId.eq(evaluacionProceso.getValorTipoNotaId()))
                                .querySingle();*/
                        ValorTipoNotaUi valorTipoNotaUi = null;
                        for (ValorTipoNotaUi itemValorTipoNotaUi : tipoNota.getValorTipoNotaList()) {
                            if (itemValorTipoNotaUi.getId().equals(evaluacionProceso.getValorTipoNotaId())) {
                                valorTipoNotaUi = itemValorTipoNotaUi;
                                break;
                            }
                        }


                        switch (evalProcUi.getTipo()) {
                            case SELECTOR_ICONOS:
                                evalProcUi.setEscala(valorTipoNotaUi.getIcono());
                                break;
                            case SELECTOR_VALORES:
                                evalProcUi.setEscala(valorTipoNotaUi.getTitulo());
                                break;
                        }
                    } catch (Exception ignored) {
                    }
                }
                alumnoProcesoUi.setEvalProcList(evalProcUiList);
                //gruposProcesoLists.add(alumnoProcesoUi);
                addListDimencion(alumnoProcesoUi, entidadId, georeferenciaId);

                alumnoProcesoUiList.add(alumnoProcesoUi);
                NotaUi notaTotaUi = new NotaUi();
                alumnoProcesoUi.setNotaUi(notaTotaUi);


                /*EvaluacionProcesoC evaluacionProcesoBidimencional = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubBidId))
                        .and(EvaluacionProcesoC_Table.alumnoId.is(persona.getPersonaId()))
                        .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                        .querySingle();*/
                EvaluacionProcesoC evaluacionProcesoBidimencional = null;
                for (EvaluacionProcesoC itemEvaluacionProcesoC : todosEvaluacionProcesoCList) {
                    if (itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubBidId) &&
                            itemEvaluacionProcesoC.getAlumnoId() == persona.getPersonaId()) {
                        evaluacionProcesoBidimencional = itemEvaluacionProcesoC;
                        break;
                    }
                }


                if (evaluacionProcesoBidimencional != null) {
                    notaTotaUi.setId(evaluacionProcesoBidimencional.getKey());
                    notaTotaUi.setNota(evaluacionProcesoBidimencional.getNota());
                    notaTotaUi.setMsj(evaluacionProcesoBidimencional.getMsje());
                    notaTotaUi.setPublicar(evaluacionProcesoBidimencional.getPublicado()==1);
                    notaTotaUi.setAlumnoActivo(persona.getVigente() !=0);
                }
                notaTotaUi.setPublicarVisible(true);

                List<Cell> cells = new ArrayList<>();
                cells.add(notaTotaUi);
                cells.addAll(evalProcUiList);
                alumnosCell.add(cells);

            }
            //gruposProcesoLists.addAll(alumnoProcesoUiList);
            //alumnoEvaluacionLists.addAll(alumnosCell);

        }

        Log.d(TAG, "gruposProcesoLists: " + gruposProcesoLists.size());
        callback.onLoaded(rubBidUi, gruposProcesoLists, alumnoEvaluacionLists, grupoProcesoUiList);
    }

    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getEquipoList(String rubroEvalProcesoKey, List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> todosEquipoList) {
        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> resultado = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemRubroEquipo : todosEquipoList) {
            if (itemRubroEquipo.getRubroEvalProcesoId().equals(rubroEvalProcesoKey)) {
                itemRubroEquipo.setIntegrantecList(rubroIntegrante.getRubroIntegranteListPorRubroGrupo(itemRubroEquipo.getKey()));
                resultado.add(itemRubroEquipo);
            }
        }
        return resultado;
    }

    private void addListDimencion(AlumnoProcesoUi alumnoProcesoUi, int entidadId, int georeferenciaId) {
        List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumno(alumnoProcesoUi.getId(), entidadId, georeferenciaId);
        List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();
        Log.d(TAG, "dimensionObservadaList Size:" + dimensionObservadaList.size());
        for (DimensionObservada dimensionObservada : dimensionObservadaList) {
            Dimension dimension = SQLite.select()
                    .from(Dimension.class)
                    .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                    .querySingle();

            if (dimension != null) {
                DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
                dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
                int valor = (int) (dimensionObservada.getValor() * 100);
                dimensionObservadaUi.setValor(valor);
                DimensionUi dimensionUi = new DimensionUi();
                dimensionUi.setId(dimension.getDimensionId());
                dimensionUi.setNombre(dimension.getNombre());
                dimensionUi.setDescripcion(dimension.getDescripcion());
                dimensionUi.setInstrumentoEvalId(dimension.getInstrumentoEvalId());
                dimensionUi.setSigla(dimension.getSigla());
                dimensionUi.setEnfoque(dimension.getEnfoque());
                dimensionUi.setConfiabilidad(dimension.getConfiabilidad());
                dimensionUi.setIntervaloInicio(dimension.getIntervaloInicio());
                dimensionUi.setIntervaloFin(dimension.getIntervaloFin());
                dimensionUi.setIncluidoIInicio(dimension.getIncluidoIInicio());
                dimensionUi.setIncluidoIFin(dimension.getIncluidoIFin());
                dimensionUi.setColor(dimension.getColor());
                dimensionUi.setIcono(dimension.getIcono());
                dimensionUi.setMedida(dimension.getMedida());
                dimensionUi.setOrden(dimension.getOrden());
                dimensionObservadaUi.setDimensionUi(dimensionUi);
                dimensionObservadaUiList.add(dimensionObservadaUi);
            }
        }

        List<DimensionObservadaUi> newdimensionObservadaUiList = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
        if(newdimensionObservadaUiList.size()>=1)alumnoProcesoUi.setDimensionObservadaUi(newdimensionObservadaUiList.get(0));

    }

    private List<DimensionObservadaUi> getNewdimensionObservadaUiListOrderValor(List<DimensionObservadaUi> dimensionObservadaUiList) {

        if(dimensionObservadaUiList.size()<4)return new ArrayList<>();

        List<DimensionObservadaUi> dimensionObservadaUiListNew = new ArrayList<>(dimensionObservadaUiList);
        try {


            int cantidadTotal = 0;
            DimensionObservadaUi dimensionObservadaUisUno = dimensionObservadaUiList.get(0);
            DimensionObservadaUi dimensionObservadaUisDos = dimensionObservadaUiList.get(1);
            DimensionObservadaUi dimensionObservadaUisTres = dimensionObservadaUiList.get(2);
            DimensionObservadaUi dimensionObservadaUisCuatro = dimensionObservadaUiList.get(3);
            int areaUno = (int)(dimensionObservadaUisUno.getValor() * dimensionObservadaUisDos.getValor() / 2);
            int areaDos = (int)(dimensionObservadaUisDos.getValor() * dimensionObservadaUisTres.getValor() / 2);
            int areaTres = (int)(dimensionObservadaUisTres.getValor() * dimensionObservadaUisCuatro.getValor() / 2);
            int areaCuatro = (int)(dimensionObservadaUisCuatro.getValor() * dimensionObservadaUisUno.getValor() / 2);
            cantidadTotal = areaUno + areaDos + areaTres + areaCuatro;

            dimensionObservadaUisUno.setArea(areaUno);
            float porsentajeOne = (float) areaUno / cantidadTotal;
            porsentajeOne = porsentajeOne * 100;
            dimensionObservadaUisUno.setPorcentaje((int) (porsentajeOne));

            dimensionObservadaUisDos.setArea(areaDos);
            float porsentajeDos =(float) areaDos/cantidadTotal;
            porsentajeDos = porsentajeDos * 100;
            dimensionObservadaUisDos.setPorcentaje((int)porsentajeDos);
            dimensionObservadaUisTres.setArea(areaTres);
            float porsentajeTres = (float)areaTres/cantidadTotal;
            porsentajeTres = porsentajeTres * 100;
            dimensionObservadaUisTres.setPorcentaje((int)porsentajeTres);
            dimensionObservadaUisCuatro.setArea(areaCuatro);
            float porsentajeCuatro = (float)areaCuatro/cantidadTotal;
            porsentajeCuatro = porsentajeCuatro * 100;
            dimensionObservadaUisCuatro.setPorcentaje((int)porsentajeCuatro);
            Log.d(TAG,"dimensionObservadaUisUno: " + (porsentajeOne) );
            Log.d(TAG,"dimensionObservadaUisDos: " + (porsentajeDos) );
            Log.d(TAG,"dimensionObservadaUisTres: " + (porsentajeTres) );
            Log.d(TAG,"dimensionObservadaUisCuatro: " + (porsentajeCuatro) );

            Collections.sort(dimensionObservadaUiListNew, new Comparator<DimensionObservadaUi>() {
                @Override
                public int compare(DimensionObservadaUi p1, DimensionObservadaUi p2) {
                    return Integer.compare(p2.getArea(), p1.getArea());
                }
            });

            int posicion2=0;
            for (DimensionObservadaUi dimensionObservadaUi: dimensionObservadaUiListNew){
                posicion2++;
                dimensionObservadaUi.setPoscion(posicion2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return dimensionObservadaUiListNew;

    }

    private TipoNotaUi getTipoNota(RubroEvaluacionProcesoC rubroEvaluacionProceso, List<TipoNotaC> tipoNotaCList) {
/*        Log.d(TAG,"getTipoNota: ");
        if(rubroEvaluacionProceso == null)return null;
        TipoNotaC tipoNota = SQLite.select()
                .from(TipoNotaC.class)
                .where(TipoNotaC_Table.tipoNotaId.eq(rubroEvaluacionProceso.getTipoNotaId()))
                .querySingle();*/

        if (rubroEvaluacionProceso==null){
            Log.d(TAG, "rubroEvaluacionProceso: null");
            return null;

        }
        TipoNotaC tipoNota = null;
        for (TipoNotaC itemTipoNotaC : tipoNotaCList) {
            if (itemTipoNotaC.getKey().equals(rubroEvaluacionProceso.getTipoNotaId())) {
                tipoNota = itemTipoNotaC;
                break;
            }
        }

        if (tipoNota == null){
            Log.d(TAG, "rubrica: "+rubroEvaluacionProceso.getKey()+"");
            Log.d(TAG, "tipoNota: "+rubroEvaluacionProceso.getTipoNotaId()+" is null");
            return null;
        }
        Log.d(TAG, "TipoNotaUi: ");
        TipoNotaUi tipoNotaUi = new TipoNotaUi();
        List<ValorTipoNotaUi> valorTipoNotaCabeceraUiList = new ArrayList<>();
        tipoNotaUi.setId(String.valueOf(tipoNota.getKey()));
        tipoNotaUi.setNombre(tipoNota.getNombre());
        tipoNotaUi.setValorDefecto(tipoNota.getValorDefecto());
        tipoNotaUi.setValorMinimo(tipoNota.getValorMinino());
        tipoNotaUi.setValorMaximo(tipoNota.getValorMaximo());
        tipoNotaUi.setLongitudPaso(tipoNota.getLongitudPaso());
        tipoNotaUi.setTipoId(tipoNota.getTipoId());
        tipoNotaUi.setValorTipoNotaList(valorTipoNotaCabeceraUiList);
        tipoNotaUi.setIntervalo(tipoNota.isIntervalo());

        EscalaEvaluacion escalaEvaluacion = tipoNota.getEscalaEvaluacion();

        EscalaEvaluacionUI escalaEvaluacionUi = new EscalaEvaluacionUI();
        tipoNotaUi.setEscalaEvaluacionUi(escalaEvaluacionUi);

        if (escalaEvaluacion != null) {
            Log.d(TAG, "escalaEvaluacion: :)");
            escalaEvaluacionUi.setName(escalaEvaluacion.getNombre());
            escalaEvaluacionUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
            escalaEvaluacionUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
            escalaEvaluacionUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
        }

        switch (tipoNota.getTipoId()) {
            case 409:
                tipoNotaUi.setTipo(TipoNotaUi.Tipo.SELECTOR_ICONOS);
                break;
            case 411:
                tipoNotaUi.setTipo(TipoNotaUi.Tipo.SELECTOR_VALORES);
                break;
        }


        /*List<ValorTipoNotaC> valorTipoNotaCabeceraList = SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.eq(tipoNota.getKey()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico,false)
                .queryList();*/

        List<ValorTipoNotaC> valorTipoNotaCabeceraList = tipoNota.getValorTipoNotaList();
        int count = 0;
        for (ValorTipoNotaC valorTipoNota : valorTipoNotaCabeceraList) {
            ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
            valorTipoNotaUi.setId(String.valueOf(valorTipoNota.getValorTipoNotaId()));
            valorTipoNotaUi.setTitulo(valorTipoNota.getTitulo());
            valorTipoNotaUi.setAlias(valorTipoNota.getAlias());
            valorTipoNotaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
            valorTipoNotaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
            valorTipoNotaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
            valorTipoNotaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
            valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
            valorTipoNotaUi.setValorNumerico(valorTipoNota.getValorNumerico());
            valorTipoNotaUi.setValorAsignado(valorTipoNota.getValorNumerico());
            valorTipoNotaCabeceraUiList.add(valorTipoNotaUi);
            valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
            valorTipoNotaUi.setValidar(tipoNotaDao.validarTipoNota(valorTipoNota.getValorTipoNotaId()));
            Log.d(TAG, "validarTipoNota" + valorTipoNotaUi.isValidar());
            switch (count) {
                case 0:
                    valorTipoNotaUi.setEstilo(ValorTipoNotaUi.Estilo.AZUL);
                    break;
                case 1:
                    valorTipoNotaUi.setEstilo(ValorTipoNotaUi.Estilo.VERDE);
                    break;
                case 2:
                    valorTipoNotaUi.setEstilo(ValorTipoNotaUi.Estilo.ANARANJADO);
                    break;
                case 3:
                    valorTipoNotaUi.setEstilo(ValorTipoNotaUi.Estilo.ROJO);
                    break;
            }
            count++;
        }


        return tipoNotaUi;
    }

    @Override
    public void evalAlumnosConProceso(final EvalAlumnosProcesoBid.RequestValues requestValues, final Callback<EvalProcUi> callback) {
        Log.d(TAG, "evalAlumnosConProceso");

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                EvalProcUi evalProcUiGrupal = requestValues.getEvalProcUi();
                String equipoId = null;
                try {
                    T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC principalprocesoEquipoc = SQLite.select()
                            .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                            .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.eq(evalProcUiGrupal.getEquipoId()))
                            .querySingle(databaseWrapper);

                    if (principalprocesoEquipoc != null)
                        equipoId = principalprocesoEquipoc.getEquipoId();
                } catch (Exception ignored) {
                }

                evalProcUiGrupal.setEquipoId(equipoId);

                switch (evalProcUiGrupal.getFormaEvaluar()) {
                    case INDIVIDUAL:
                        guardarEvaluacionSimple(evalProcUiGrupal,databaseWrapper);
                        break;
                    case GRUPAL:
                        guardarEvaluacionGrupal(evalProcUiGrupal,databaseWrapper);
                        break;
                }
                for (EvalProcUi itemEvalProcUi : requestValues.getEvalProcUiList()) {

                    itemEvalProcUi.setEquipoId(equipoId);
                 switch (itemEvalProcUi.getFormaEvaluar()) {
                        case INDIVIDUAL:
                            guardarEvaluacionSimple(itemEvalProcUi, databaseWrapper);
                            break;
                        case GRUPAL:
                            guardarEvaluacionGrupal(itemEvalProcUi, databaseWrapper);
                            break;
                    }

                }


            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                Log.d(TAG, "evalAlumnosConProceso onSucess");
                callback.onLoaded(requestValues.getEvalProcUi());
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.d(TAG, "evalAlumnosConProceso onError: " + error);
                callback.onLoaded(null);
            }
        }).build();

        transaction.execute();

    }

    @Override
    public void deleteEvalAlumnosConProceso(DeleteAlumnosProcesoBid.RequestValues requestValues, Callback<Boolean> callback) {

    }

    @Override
    public void getAlumnosConNotasProceso(GetAlumnoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {
        Log.d(TAG, "getAlumnosConNotasProceso");
        int cargaCursoId = requestValues.getCargaCursoId();
        String rubBidId = requestValues.getRubBidId();
        int entidadId = requestValues.getEntidadId();
        int georeferenciaId = requestValues.getGeoreferenciaId();
        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "rubBidId: " + rubBidId);
        List<String> rubricaIdlist = new ArrayList<>();
        List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeRubroEquipo(rubricaIdlist, cargaCursoId);
        getAlumnosConNotasProceso(rubBidId, personaList, entidadId, georeferenciaId, callback);
    }

    @Override
    public void getIndicador(GetIndicadorRubro.RequestValues requestValues, Callback<IndicadorUi> callback) {
        Log.d(TAG, "icdId: " + requestValues.getRubroEvalProcesoId());


        IndicadorUi indicadorUi = new IndicadorUi();
        Icds icds = SQLite.select(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.is(requestValues.getIndicadorId()))
                .querySingle();

        if (icds != null) {
            indicadorUi.setId(String.valueOf(icds.getIcdId()));
            indicadorUi.setDescripcion(icds.getDescripcion());
            indicadorUi.setTitulo(icds.getAlias());
            indicadorUi.setSubtitulo(icds.getTitulo());
            switch (icds.getTipoId()) {
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
        }
        List<CampoTematico> campoTematicos = SQLite.select()
                .from(CampoTematico.class)
                .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                .on(CampoTematico_Table.campoTematicoId.withTable()
                        .eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable().eq(requestValues.getRubroEvalProcesoId()))
                .queryList();

        indicadorUi.setCamposTemaicos(getCampotematicoSilavo(campoTematicos));


        DesempenioIcd desempenioIcd = SQLite.select()
                .from(DesempenioIcd.class)
                .where(DesempenioIcd_Table.desempenioIcdId.eq(requestValues.getIndicadorId()))
                .querySingle();


        if (desempenioIcd != null) indicadorUi.setDesempenioDesc(desempenioIcd.getDescripcion());

        int competenciaId = 0;
        Competencia capacidad = SQLite.select()
                .from(Competencia.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.competenciaId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(requestValues.getRubroEvalProcesoId()))
                .querySingle();

        if (capacidad != null) indicadorUi.setCapacidadDesc(capacidad.getNombre());
        if (capacidad != null) competenciaId = capacidad.getSuperCompetenciaId();

        Competencia competencia = SQLite.select()
                .from(Competencia.class)
                .where(Competencia_Table.competenciaId.eq(competenciaId))
                .querySingle();
        if (competencia != null) indicadorUi.setCompetenciaDesc(competencia.getNombre());

        callback.onLoaded(indicadorUi);

    }

    @Override
    public void searchAlumnosConNotasProceso(FiltroTableUi filtroTableUi, String rubroBidemencionalId, int cargaCursoId, int entidadId, int georeferenciaId, final GetTableCallback callback) {
        try {
            List<PersonaContratoQuery> personaList = new ArrayList<>();
            switch (filtroTableUi.getOrderByASC()) {
                case NOMBRE:
                    personaList = personaDao.getAlumnosDeRubro(true, false, filtroTableUi.getPersona(), rubroBidemencionalId, cargaCursoId);
                    break;
                case APELLIDO:
                    personaList = personaDao.getAlumnosDeRubro(false, true, filtroTableUi.getPersona(), rubroBidemencionalId, cargaCursoId);
                    break;
            }

            getAlumnosConNotasProceso(rubroBidemencionalId, personaList, entidadId, georeferenciaId,new GetAlumnConProcLisTCallback() {
                @Override
                public void onLoaded(RubBidUi rubBidUi, List<ColumnHeader> columnHeaderList, List<List<Cell>> cellsList, List<GrupoProcesoUi> grupoProcesoUis) {
                    callback.onLoaded(true, rubBidUi, columnHeaderList, cellsList, grupoProcesoUis);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoaded(false, null, null, null, null);
        }

    }

    @Override
    public boolean calculaMediaDesviacion(RubBidUi rubrica) {
        boolean successR = false;
        try {
            successR = evaluacionProcesoDao.f_mediaDesviacionEstandar(rubrica.getId());
            Log.d(TAG, " getRubroEvalProcesoList " + rubrica.getRubroEvalProcesoList().size());
            for (RubEvalProcUi rubEvalProcUi : rubrica.getRubroEvalProcesoList()) {
                successR = evaluacionProcesoDao.f_mediaDesviacionEstandar(rubEvalProcUi.getId());
            }
            return successR;
        } catch (Exception e) {
            e.getStackTrace();
            return successR;
        }
    }





    private String getCampotematicoSilavo(List<CampoTematico> campoTematicos) {

        List<CampotematicoUi> campotematicoUipadresList = new ArrayList<>();
        for (CampoTematico itemCampoTematico : campoTematicos) {

            CampotematicoUi campotematicoUi = new CampotematicoUi();
            campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());

            CampoTematico campoTematicoPadre = SQLite.select()
                    .from(CampoTematico.class)
                    .where(CampoTematico_Table.parentId.withTable().is(0))
                    .and(CampoTematico_Table.campoTematicoId.is(itemCampoTematico.getParentId()))
                    .querySingle();

            if (campoTematicoPadre != null) {
                CampotematicoUi campotematicoUiPadre = new CampotematicoUi();
                campotematicoUiPadre.setId(campoTematicoPadre.getCampoTematicoId());
                campotematicoUiPadre.setDisable(true);
                campotematicoUiPadre.setHijo(false);
                int posicionPadre = campotematicoUipadresList.indexOf(campotematicoUiPadre);
                if (posicionPadre == -1) {
                    campotematicoUiPadre.setNumeracion(campotematicoUipadresList.size() + 1);
                    //campotematicoUiPadre.setDescripcion(campotematicoUiPadre.getNumeracion() + " " + campoTematicoPadre.getTitulo());
                    campotematicoUiPadre.setDescripcion(campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setNumeracion(posicionPadre + 1);
                    //campotematicoUiPadre.setDescripcion(campotematicoUiPadre.getNumeracion() + " " + campoTematicoPadre.getTitulo());
                    campotematicoUiPadre.setDescripcion(campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }
                campotematicoUi.setDisable(false);
                campotematicoUi.setHijo(true);
                //campotematicoUi.setDescripcion(campotematicoUiPadre.getNumeracion() + "." + (campotematicoUiPadre.getCampotematicoUis().size() + 1) +" " + itemCampoTematico.getTitulo());
                campotematicoUi.setDescripcion("- " + itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampotematicos(campotematicoUi);
                campotematicoUi.setCampotematicoUiPadre(campotematicoUiPadre);

            } else {
                campotematicoUi.setNumeracion(campotematicoUipadresList.size() + 1);
                campotematicoUi.setDescripcion(campotematicoUi.getNumeracion() + " " + itemCampoTematico.getTitulo());
                campotematicoUi.setDescripcion(itemCampoTematico.getTitulo());
                campotematicoUi.setDisable(false);
                campotematicoUi.setHijo(false);
                campotematicoUipadresList.add(campotematicoUi);
            }
        }


        StringBuilder campotematicoList = new StringBuilder();
        for (CampotematicoUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            campotematicoList.append(itemCampotematicoUiPadre.getNumeracion()).append(". ").append(eclipse(itemCampotematicoUiPadre.getDescripcion(), 40)).append("\n");
            for (CampotematicoUi itemCampotematicoList : itemCampotematicoUiPadre.getCampotematicoUis()) {
                campotematicoList.append("\t").append(eclipse(itemCampotematicoList.getDescripcion(), 38)).append("\n");
            }
        }
        return campotematicoList.toString();

    }


    private String eclipse(String texto, int cantidad) {
        StringBuilder eclipse = new StringBuilder();
        if (TextUtils.isEmpty(texto)) return "";
        if (texto.length() > cantidad) {
            eclipse.append(texto.substring(0, cantidad)).append("...");
        } else {
            eclipse.append(texto);
        }

        return eclipse.toString();
    }

    //region Evaluacion Individual
    private void guardarEvaluacionSimple(EvalProcUi evalProcUi, DatabaseWrapper databaseWrapper) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.is(evalProcUi.getRubEvalProcId()))
                .querySingle(databaseWrapper);


        if (rubroEvaluacionProcesoC != null) {
            switch (evalProcUi.getFormaEvaluar()) {
                case GRUPAL:
                    if(evalProcUi.isAlumnoActivo())guardarEvaluacionIndividualGrupal(evalProcUi,
                            rubroEvaluacionProcesoC.getCalendarioPeriodoId(),
                            rubroEvaluacionProcesoC.getTiporubroid(),databaseWrapper);
                    break;
                case INDIVIDUAL:
                    guardarEvaluacionIndividual(evalProcUi,
                            rubroEvaluacionProcesoC.getCalendarioPeriodoId(),
                            rubroEvaluacionProcesoC.getTiporubroid(),databaseWrapper);
                    break;
            }

            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save(databaseWrapper);
        }
    }

    private void guardarEvaluacionIndividual(EvalProcUi evalProcUi, int calendarioPeriodoId, int tiporubroid, DatabaseWrapper databaseWrapper) {
        EvaluacionProcesoC evaluacionProceso = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(evalProcUi.getRubEvalProcId()))
                .and(EvaluacionProcesoC_Table.alumnoId.is(evalProcUi.getAlumnoId()))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .querySingle(databaseWrapper);

        if (evaluacionProceso != null) {
            if (!evalProcUi.isEstado()) {
                if (evaluacionProceso != null) {
                    evaluacionProceso.setNota(0);
                    evaluacionProceso.setEscala(null);
                    evaluacionProceso.setValorTipoNotaId(null);
                    if (tiporubroid == RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE)
                        evaluacionProceso.setFormulaSinc(true);
                    evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                    boolean success = evaluacionProceso.save(databaseWrapper);
                    if (!success) throw new Error("Error updating Evaluación processo!!!");
                }
                return;
            }


            //if(evaluacionProceso == null)evaluacionProceso = new EvaluacionProcesoC();
            evaluacionProceso.setNota(Utils.formatearDecimales(evalProcUi.getNota(), 2));
            evaluacionProceso.setValorTipoNotaId(evalProcUi.getValorTipoNotaId());
            evaluacionProceso.setAlumnoId(evalProcUi.getAlumnoId());
            evaluacionProceso.setRubroEvalProcesoId(evalProcUi.getRubEvalProcId());
            evaluacionProceso.setEscala(evalProcUi.getEscala());
            evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
            evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);
            if (tiporubroid == RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE)
                evaluacionProceso.setFormulaSinc(true);
            boolean success = evaluacionProceso.save(databaseWrapper);
            if (!success) throw new Error("Error updating Evaluación processo!!!");
        }
    }

    private void guardarEvaluacionIndividualGrupal(EvalProcUi evalProcUi, int calendarioPeriodoId, int tiporubroid, DatabaseWrapper databaseWrapper) {

        Log.d(TAG, "guardarEvaluacionIndividualGrupal "+ evalProcUi.getAlumnoId()+ " / "+ evalProcUi.isAlumnoActivo());

        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.eq(evalProcUi.getRubEvalProcId()))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.equipoId.eq(evalProcUi.getEquipoId()))
                .querySingle(databaseWrapper);

        EvaluacionProcesoC evaluacionProceso = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(evalProcUi.getRubEvalProcId()))
                .and(EvaluacionProcesoC_Table.alumnoId.is(evalProcUi.getAlumnoId()))
                .and(EvaluacionProcesoC_Table.equipoId.is(procesoEquipoc.getKey()))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .querySingle(databaseWrapper);


        if (evaluacionProceso != null) {
            if (!evalProcUi.isEstado()) {
                    evaluacionProceso.setNota(0);
                    evaluacionProceso.setEscala(null);
                    evaluacionProceso.setValorTipoNotaId(null);
                    evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                    if (tiporubroid == RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE)
                        evaluacionProceso.setFormulaSinc(true);
                    boolean success = evaluacionProceso.save(databaseWrapper);
                    if (!success) throw new Error("Error updating Evaluación processo!!!");

                return;
            }


            //if(evaluacionProceso == null)evaluacionProceso = new EvaluacionProcesoC();
        ;
            evaluacionProceso.setNota(Utils.formatearDecimales(evalProcUi.getNota(), 2));
            evaluacionProceso.setValorTipoNotaId(evalProcUi.getValorTipoNotaId());
            evaluacionProceso.setAlumnoId(evalProcUi.getAlumnoId());
            evaluacionProceso.setRubroEvalProcesoId(evalProcUi.getRubEvalProcId());
            evaluacionProceso.setEscala(evalProcUi.getEscala());
            evaluacionProceso.setEquipoId(procesoEquipoc.getKey());
            evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
            evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);

            if (tiporubroid == RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE)
                evaluacionProceso.setFormulaSinc(true);

            boolean success = evaluacionProceso.save(databaseWrapper);
            if (!success) throw new Error("Error updating Evaluación processo!!!");
        }
    }
    //endregion Evaluacion Individual

    private void guardarEvaluacionGrupal(EvalProcUi evalProcUi, DatabaseWrapper databaseWrapper) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.is(evalProcUi.getRubEvalProcId()))
                .querySingle(databaseWrapper);


        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc = SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.eq(evalProcUi.getRubEvalProcId()))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.equipoId.eq(evalProcUi.getEquipoId()))
                .querySingle(databaseWrapper);

        int calendarioPeriodoId = rubroEvaluacionProcesoC.getCalendarioPeriodoId();
        rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        rubroEvaluacionProcesoC.save(databaseWrapper);


        EquipoEvaluacionProcesoC equipoEvaluacionProceso = SQLite.select()
                .from(EquipoEvaluacionProcesoC.class)
                .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.is(evalProcUi.getRubEvalProcId()))
                .and(EquipoEvaluacionProcesoC_Table.equipoId.is(procesoEquipoc.getKey()))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .querySingle(databaseWrapper);


        if (equipoEvaluacionProceso!=null){
            if (!evalProcUi.isEstado()) {
                if (equipoEvaluacionProceso != null) {
                    equipoEvaluacionProceso.setNota(0);
                    equipoEvaluacionProceso.setEscala(null);
                    equipoEvaluacionProceso.setValorTipoNotaId(null);
                    equipoEvaluacionProceso.setSesionAprendizajeId(rubroEvaluacionProcesoC.getSesionAprendizajeId());
                    equipoEvaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                    boolean success = equipoEvaluacionProceso.save(databaseWrapper);
                    if (!success) throw new Error("Error updating Evaluación processo!!!");
                }

                List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = SQLite.select(Utils.f_allcolumnTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.ALL_COLUMN_PROPERTIES))
                        .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                        .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                        .on(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable()
                                .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable()))
                        .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable().is(procesoEquipoc.getKey()))
                        .queryList();


                for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC integrante : integranteList) {
                    evalProcUi.setAlumnoId(integrante.getPersonaId());
                    evalProcUi.setEstado(false);
                    guardarEvaluacionSimple(evalProcUi, databaseWrapper);
                }
                return;
            }


            //if(equipoEvaluacionProceso == null)equipoEvaluacionProceso = new EquipoEvaluacionProcesoC();
            equipoEvaluacionProceso.setNota(evalProcUi.getNota());
            equipoEvaluacionProceso.setValorTipoNotaId(evalProcUi.getValorTipoNotaId());
            equipoEvaluacionProceso.setRubroEvalProcesoId(evalProcUi.getRubEvalProcId());
            equipoEvaluacionProceso.setEscala(evalProcUi.getEscala());
            equipoEvaluacionProceso.setEquipoId(procesoEquipoc.getKey());
            equipoEvaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
            boolean success = equipoEvaluacionProceso.save(databaseWrapper);
            if (!success) throw new Error("Error updating EquipoEvaluacionProceso processo!!!");
        }

        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = SQLite.select(Utils.f_allcolumnTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.ALL_COLUMN_PROPERTIES))
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .on(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable()
                        .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable()))
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable().is(procesoEquipoc.getKey()))
                .queryList();


        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC integrante : integranteList) {
            evalProcUi.setAlumnoId(integrante.getPersonaId());
            Contrato contrato= SQLite.select().from(Contrato.class)
                    .where(Contrato_Table.personaId.withTable().eq(integrante.getPersonaId())).querySingle();
            if(contrato!=null)
                evalProcUi.setAlumnoActivo(contrato.getVigente() !=0);
            guardarEvaluacionSimple(evalProcUi, databaseWrapper);
        }
    }

    public void getAlumnosConNotasProceso(String rubBidId, List<PersonaContratoQuery> personaList, int entidadId, int georeferenciaId, GetAlumnConProcLisTCallback callback) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<RubroEvalRNPFormulaC> formulaList = SQLite.select()
                    .from(RubroEvalRNPFormulaC.class)
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubBidId))
                    .queryList(databaseWrapper);

            List<String> rubroEvalProcesoKeyList = new ArrayList<>();
            rubroEvalProcesoKeyList.add(rubBidId);
            for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC : formulaList)
                rubroEvalProcesoKeyList.add(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId());
            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = rubroEvalProcesoDao.get(rubroEvalProcesoKeyList);
            List<TipoNotaC> tipoNotaCList = tipoNotaDao.getListPorRubrosEscala(rubroEvalProcesoKeyList);
            TreeMap<Integer, Competencia> competenciaList = competenciaDao.getCompetenciaRubro(rubroEvalProcesoKeyList);
            List<IndicadorQuery> indicadorQueryList = indicadorDao.getIcdsporRubroProceso(rubroEvalProcesoKeyList);
            List<CalendarioPeriodo> calendarioPeriodoList = calendarioPeriodoDao.getRubrosEvalProceso(rubroEvalProcesoKeyList);

            //List<PersonaUi> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            List<Integer> personaIdList = new ArrayList<>();
            for (PersonaContratoQuery persona : personaList) personaIdList.add(persona.getPersonaId());
            List<EvaluacionProcesoC> todosEvaluacionProcesoCList = evaluacionProcesoDao.getEvaluacionProcesoRubrica(rubroEvalProcesoKeyList, personaIdList);
            List<String> evalauciones = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC: todosEvaluacionProcesoCList)evalauciones.add(evaluacionProcesoC.getKey());

            List<Persona> personaComentarios = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                    .from(Persona.class)
                    .innerJoin(EvaluacionProcesoC.class)
                    .on(EvaluacionProcesoC_Table.alumnoId.withTable()
                            .eq(Persona_Table.personaId.withTable()))
                    .innerJoin(RubroEvaluacionProcesoComentario.class)
                    .on(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.withTable()
                            .eq(EvaluacionProcesoC_Table.key.withTable()))
                    .where(EvaluacionProcesoC_Table.key.withTable().in(evalauciones))
                    .and(RubroEvaluacionProcesoComentario_Table.delete.withTable().notEq(1))
                    .groupBy(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                    .queryList(databaseWrapper);


            RubBidUi rubBidUi = getRubBid(formulaList, rubroEvaluacionProcesoCList, tipoNotaCList, competenciaList, calendarioPeriodoList, indicadorQueryList);

            List<RubEvalProcUi> rubEvalProcList = rubBidUi.getRubroEvalProcesoList();

            List<ColumnHeader> alumnoProcesoUiList = new ArrayList<>();
            List<List<Cell>> alumnoEvaluacionLists = new ArrayList<>();
            List<GrupoProcesoUi> grupoProcesoUiList = new ArrayList<>();

            int posicion = 0;
            for (PersonaContratoQuery persona : personaList) {
                posicion++;
                String urlProfile = persona.getFoto();
                AlumnoProcesoUi alumnoProcesoUi = new AlumnoProcesoUi();
                alumnoProcesoUi.setId(persona.getPersonaId());
                alumnoProcesoUi.setNombre(Utils.getFirstWord(persona.getNombres()));
                alumnoProcesoUi.setPocision(posicion);
                alumnoProcesoUi.setApellidos(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
                alumnoProcesoUi.setUrlProfile(urlProfile);
                alumnoProcesoUi.setVigente(persona.getVigente() !=0);
                for (Persona personacometario: personaComentarios){
                    if(persona.getPersonaId() == personacometario.getPersonaId()){
                        alumnoProcesoUi.setComentario(true);
                        break;
                    }
                }

                List<EvalProcUi> evalProcUiList = new ArrayList<>();
                for (RubEvalProcUi rubEvalProcUi : rubEvalProcList) {
                    EvalProcUi evalProcUi = new EvalProcUi();
                    evalProcUi.setRubEvalProcId(rubEvalProcUi.getId());
                    evalProcUi.setAlumnoId(persona.getPersonaId());
                    evalProcUi.setAlumnoActivo(persona.getVigente() !=0);
                    evalProcUiList.add(evalProcUi);

                    TipoNotaUi tipoNota = rubEvalProcUi.getTipoNotaUi();
                    if (tipoNota == null) continue;
                    switch (tipoNota.getTipoId()) {
                        case 409:
                            evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_ICONOS);
                            break;
                        case 412:
                            evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_VALORES);
                            break;
                    }


                    /*EvaluacionProcesoC evaluacionProceso = SQLite.select()
                            .from(EvaluacionProcesoC.class)
                            .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubEvalProcUi.getId()))
                            .and(EvaluacionProcesoC_Table.alumnoId.is(persona.getPersonaId()))
                            //.and(EvaluacionProcesoC_Table.EquipoId.isNull())
                            .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                            .querySingle();*/
                    EvaluacionProcesoC evaluacionProceso = null;
                    for (EvaluacionProcesoC itemEvaluacionProcesoC : todosEvaluacionProcesoCList) {
                        if (itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubEvalProcUi.getId()) &&
                                itemEvaluacionProcesoC.getAlumnoId() == persona.getPersonaId()) {
                            evaluacionProceso = itemEvaluacionProcesoC;
                        }
                    }

                    if (evaluacionProceso == null) continue;
                    evalProcUi.setNota(evaluacionProceso.getNota());
                    evalProcUi.setEscala(evaluacionProceso.getEscala());
                    Log.d(TAG, "ValorTipoNotaId: " + evaluacionProceso.getValorTipoNotaId());
                    evalProcUi.setValorTipoNotaId(evaluacionProceso.getValorTipoNotaId());
                    try {

                        /*ValorTipoNotaC valorTipoNotaC = SQLite.select()
                                .from(ValorTipoNotaC.class)
                                .where(ValorTipoNotaC_Table.valorTipoNotaId.eq(evaluacionProceso.getValorTipoNotaId()))
                                .querySingle();*/
                        ValorTipoNotaUi valorTipoNotaUi = null;
                        for (ValorTipoNotaUi itemValorTipoNotaUi : tipoNota.getValorTipoNotaList()) {
                            if (itemValorTipoNotaUi.getId().equals(evaluacionProceso.getValorTipoNotaId())) {
                                valorTipoNotaUi = itemValorTipoNotaUi;
                                break;
                            }
                        }

                        switch (evalProcUi.getTipo()) {
                            case SELECTOR_ICONOS:
                                if(valorTipoNotaUi!=null)evalProcUi.setEscala(valorTipoNotaUi.getIcono());
                                break;
                            case SELECTOR_VALORES:
                                if(valorTipoNotaUi!=null)evalProcUi.setEscala(valorTipoNotaUi.getTitulo());
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                alumnoProcesoUi.setEvalProcList(evalProcUiList);
                //gruposProcesoLists.add(alumnoProcesoUi);
                addListDimencion(alumnoProcesoUi, entidadId, georeferenciaId);
                alumnoProcesoUiList.add(alumnoProcesoUi);
                NotaUi notaTotaUi = new NotaUi();
                alumnoProcesoUi.setNotaUi(notaTotaUi);

                /*EvaluacionProcesoC evaluacionProcesoBidimencional = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(rubBidId))
                        .and(EvaluacionProcesoC_Table.alumnoId.is(persona.getPersonaId()))
                        //.and(EquipoEvaluacionProcesoC_Table.EquipoId.isNull())
                        .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                        .querySingle();*/
                EvaluacionProcesoC evaluacionProcesoBidimencional = null;
                for (EvaluacionProcesoC itemEvaluacionProcesoC : todosEvaluacionProcesoCList) {
                    if (itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(rubBidId) &&
                            itemEvaluacionProcesoC.getAlumnoId() == persona.getPersonaId()) {
                        evaluacionProcesoBidimencional = itemEvaluacionProcesoC;
                        break;
                    }
                }


                if (evaluacionProcesoBidimencional != null) {
                    notaTotaUi.setId(evaluacionProcesoBidimencional.getKey());
                    notaTotaUi.setNota(evaluacionProcesoBidimencional.getNota());
                    notaTotaUi.setMsj(evaluacionProcesoBidimencional.getMsje());
                    notaTotaUi.setPublicar(evaluacionProcesoBidimencional.getPublicado()==1);
                }

                notaTotaUi.setPublicarVisible(true);

                List<Cell> cells = new ArrayList<>();
                cells.add(notaTotaUi);
                cells.addAll(evalProcUiList);
                alumnoEvaluacionLists.add(cells);

            }


            databaseWrapper.setTransactionSuccessful();
            callback.onLoaded(rubBidUi, alumnoProcesoUiList, alumnoEvaluacionLists, grupoProcesoUiList);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public List<MensajeUi> getComentarioPred(EvalProcUi evalProcUi) {
        return null;
    }

    @Override
    public List<MensajeUi> getComentarios(String rubricaBidimencionalId, int alumnoId) {

        List<MensajeUi> mensajeUiList = new ArrayList<>();
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubricaBidimencionalId))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(alumnoId))
                .querySingle();

        if(evaluacionProcesoC==null)return mensajeUiList;

        List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentarios = SQLite.select()
                .from(RubroEvaluacionProcesoComentario.class)
                .where(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.eq(evaluacionProcesoC.getKey()))
                .and(RubroEvaluacionProcesoComentario_Table.delete.withTable().notEq(1))
                .queryList();

        for (RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario : rubroEvaluacionProcesoComentarios){
            MensajeUi mensajeUi = new MensajeUi();
            mensajeUi.setId(rubroEvaluacionProcesoComentario.getKey());
            mensajeUi.setDescripcion(rubroEvaluacionProcesoComentario.getDescripcion());
            mensajeUi.setAlumnoId(evaluacionProcesoC.getAlumnoId());
            mensajeUi.setRubroEvaluacionId(evaluacionProcesoC.getRubroEvalProcesoId());
            mensajeUi.setTipoMensaje(MensajeUi.TipoMensaje.PREDETERMINADO);
            mensajeUi.setFechaCreacion(rubroEvaluacionProcesoComentario.getFechaCreacion());
            mensajeUiList.add(mensajeUi);
        }

        return mensajeUiList;
    }

    @Override
    public boolean saveComentario(MensajeUi mensajeUi) {
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(mensajeUi.getRubroEvaluacionId()))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(mensajeUi.getAlumnoId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  rubroEvalProcesoDao.get(evaluacionProcesoC.getRubroEvalProcesoId());
        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario = new RubroEvaluacionProcesoComentario();
        rubroEvaluacionProcesoComentario.setComentarioId("");
        rubroEvaluacionProcesoComentario.setDescripcion(mensajeUi.getDescripcion());
        rubroEvaluacionProcesoComentario.setEvaluacionProcesoComentarioId(mensajeUi.getId());
        rubroEvaluacionProcesoComentario.setKey(mensajeUi.getId());
        rubroEvaluacionProcesoComentario.setEvaluacionProcesoId(evaluacionProcesoC.getKey());
        rubroEvaluacionProcesoComentario.setSyncFlag(BaseEntity.FLAG_ADDED);
        //evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        //evaluacionProcesoC.save();
        return rubroEvaluacionProcesoComentario.save();

    }

    @Override
    public List<ArchivoUi> getArchivoComentarioList(String rubroEvaluacionId, int personaId) {
        List<ArchivoUi> archivoComentarioUis = new ArrayList<>();

        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionId))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(personaId))
                .querySingle();

        if(evaluacionProcesoC==null)return archivoComentarioUis;

        List<ArchivosRubroProceso> archivosRubroProcesoList = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.evaluacionProcesoId.eq(evaluacionProcesoC.getKey()))
                .and(ArchivosRubroProceso_Table.delete.notEq(1))
                .queryList();

        for (ArchivosRubroProceso archivosRubroProceso: archivosRubroProcesoList){
            ArchivoUi archivoUi = new ArchivoUi();
            archivoUi.setAlumnoId(personaId);
            archivoUi.setRubroEvaluacionId(rubroEvaluacionId);
            archivoUi.setId(archivosRubroProceso.getKey());
            String file = "";
            try {
                int p = Math.max(archivosRubroProceso.getUrl().lastIndexOf('/'), archivosRubroProceso.getUrl().lastIndexOf('\\'));
                file = archivosRubroProceso.getUrl().substring(p + 1);
            }catch (Exception e){
                e.printStackTrace();
            }

            archivoUi.setNombre(file);
            archivoUi.setUrl(archivosRubroProceso.getUrl());
            archivoComentarioUis.add(archivoUi);
        }
        return archivoComentarioUis;
    }



}
