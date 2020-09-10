package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso.TareaRubroEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado.RubroEvaluacionResultadoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dbflowCompat.DbflowCompat;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadUnidadAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaUnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.SilaboEventoCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CampoTematicoRubroQuery;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.RubroEvalRNPFormulaQuery;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubrosAsociadosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class RubroEvaluacionProcesoListaLocalDataSource implements RubroEvaluacionProcesoListaDataSource {
    private static final String TAG = RubroEvaluacionProcesoListaLocalDataSource.class.getSimpleName();

    private CalendarioPeriodoDao calendarioPeriodoDao;
    private RubroProcesoDao rubroProcesoDao;
    private ParametrosDisenioDao parametrosDisenioDao;
    private TareaRubroEvaluacionProcesoDao tareaRubroEvaluacionProcesoDao;
    private CompetenciaDao competenciaDao;
    private RubroEvalRNPFormulaDao rnpFormulaDao;
    private EvaluacionProcesoDao evaluacionProcesoDao;
    private PersonaDao personaDao;
    private TipoNotaDao tipoNotaDao;
    private RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;
    private ValorTipoNotaDao valorTipoNotaDao;
    private EscalaEvaluacionDao escalaEvaluacionDao;
    private RubroEvaluacionResultadoDao rubroEvaluacionResultadoDao;

    private static final int TIPO_FORMULA = 0;
    private static final int TIPO_FORMULA_MEDIA_PONDERADA = 413;
    private static final int TIPO_FORMULA_MEDIA_ARITMETICA = 414;
    private static final int TIPO_FORMULA_SUMA = 415;
    private static final int TIPO_FORMULA_MAXIMO = 416;
    private static final int TIPO_FORMULA_MINIMO = 417;
    private static final int TIPO_FORMULA_MODA = 479;


    public static final int VALOR_NUMERICO = 410;
    public static final int SELECTOR_NUMERICO = 411;
    public static final int SELECTOR_VALORES = 412;
    public static final int SELECTOR_ICONOS = 409;
    public static final int SELECTOR_ICON = 414;

    public static final int REGISTRO_ERROR = 0;
    public static final int REGISTRO_SUCCESS = 1;
    public static final int REGISTRO_RUBROSVASIO = 2;

    public static final int VALORREDONDEO_SIN_REDONDEO = 425;

    public static final int TIPOEVALUACION_CUANTITATIVA = 345;
    public static final int TIPOEVALUACION_CUALITATIVA = 346;

    public RubroEvaluacionProcesoListaLocalDataSource() {
        calendarioPeriodoDao = InjectorUtils.provideCalendarioPeriodo();
        rubroProcesoDao = InjectorUtils.provideRubroProcesoDao();
        parametrosDisenioDao = InjectorUtils.provideParametrosDisenioDao();
        tareaRubroEvaluacionProcesoDao = InjectorUtils.provideTareaRubroEvaluacionProcesoDao();
        competenciaDao = InjectorUtils.provideCompetenciaDao();
        rnpFormulaDao = InjectorUtils.provideRubroEvalRNPFormulaDao();
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
        personaDao = InjectorUtils.providePersonaDao();
        tipoNotaDao = InjectorUtils.provideTipoNotaDao();
        rubroEvalRNPFormulaDao = InjectorUtils.provideRubroEvalRNPFormulaDao();
        valorTipoNotaDao = InjectorUtils.provideValorTipoNotaDao();
        escalaEvaluacionDao = InjectorUtils.provideEscalaEvaluacionDao();
        rubroEvaluacionResultadoDao = InjectorUtils.provideRubroEvaluacionResultadoDao();
    }

    @Override
    public List<RubroProcesoUi> getFormulaList(String formulaId) {


        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            RubroEvaluacionProcesoC rubroEvaluacion = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.in(formulaId))
                    .querySingle();

            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionProcesoC.class)
                    .innerJoin(RubroEvalRNPFormulaC.class)
                    .on(RubroEvaluacionProcesoC_Table.key.withTable()
                            .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().eq(formulaId))
                    .queryList();


            int siboEventoId = rubroEvaluacion!=null?rubroEvaluacion.getSilaboEventoId() : -1;
            CalendarioPeriodo calendarioPeriodo = calendarioPeriodoDao.get(rubroEvaluacion!=null?rubroEvaluacion.getCalendarioPeriodoId() : -1);

            Competencia capacidad = competenciaDao.get(rubroEvaluacion!=null?rubroEvaluacion.getCompetenciaId() : -1);
            Competencia competencia = competenciaDao.get(capacidad!=null?capacidad.getSuperCompetenciaId(): -1);
            List<Competencia> capaciadaList = new ArrayList<>();
            if(capacidad!=null)capaciadaList.add(capacidad);
            List<Competencia> competenciaList = new ArrayList<>();
            if(competencia!=null)competenciaList.add(competencia);

            List<RubroProcesoUi> rubroEvaluacionList = new ArrayList<>();
             List<Object> list = getCompetenciasRubrosList(databaseWrapper, rubroEvaluacionProcesoCList,competenciaList,capaciadaList, null, siboEventoId, calendarioPeriodo, false );

            for (Object o : list){
                if(o instanceof CapacidadUi){
                    CapacidadUi capacidadUi = (CapacidadUi)o;
                    for (RubroProcesoUi rubroProcesoUi : capacidadUi.getRubroProcesoUis()){
                        rubroProcesoUi.setCheck(true);
                        rubroProcesoUi.setCheckDisbled(true);
                        RubroEvalRNPFormulaC rubroEvalRNPFormulaC = SQLite.select()
                                .from(RubroEvalRNPFormulaC.class)
                                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(formulaId))
                                .and(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.eq(rubroProcesoUi.getKey()))
                                .querySingle();

                        rubroProcesoUi.setPeso(rubroEvalRNPFormulaC!=null?rubroEvalRNPFormulaC.getPeso():0);
                    }
                    rubroEvaluacionList.addAll(capacidadUi.getRubroProcesoUis());
                }
            }

             return rubroEvaluacionList;

        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();

        }finally {
            databaseWrapper.endTransaction();
        }


    }

    @Override
    public boolean editarFormula(RubroProcesoUi rubroProcesoUi) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setTitulo(rubroProcesoUi.getTitulo());
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            return rubroEvaluacionProcesoC.save();
        }

        return false;
    }

    @Override
    public void changeEstadoActualizacion(List<RubroProcesoUi> rubroProcesoUiList) {
        List<String> rubrosIdList = new ArrayList<>();
        for (RubroProcesoUi rubroProcesoUi: rubroProcesoUiList)rubrosIdList.add(rubroProcesoUi.getKey());

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.in(rubrosIdList))
                .queryList();

        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
            for (RubroProcesoUi rubroProcesoUi : rubroProcesoUiList){
                if(rubroEvaluacionProcesoC.getKey().equals(rubroProcesoUi.getKey())){
                    rubroProcesoUi.setExportado(rubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                            rubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                            rubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                }
            }
        }
    }

    @Override
    public void getRubroProcesoSesionList(int idrubroformal, int sesionAprendizajeId, int nivel, int calendarioPerioId, int silaboEventoId, int cargaCursoId, ListCallback<Object> callback) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            Log.d(TAG,"sesionAprendizajeId : " +sesionAprendizajeId);
            List<Competencia> competencias= new ArrayList<>();
            switch (idrubroformal){
                case 0:
                    competencias = CompetenciaSesionAprendizajeModel.SQLView()
                            .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                            .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                            .getQuery()
                            .and(Competencia_Table.tipoId.withTable().between(348).and(349))
                            .queryList(databaseWrapper);
                    break;
                case 1:
                    competencias = CompetenciaSesionAprendizajeModel.SQLView()
                            .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                            .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                            .getQuery()
                            .and(Competencia_Table.tipoId.withTable().eq(347))
                            .queryList(databaseWrapper);
                    break;
                case 3:
                    competencias = CompetenciaSesionAprendizajeModel.SQLView()
                            .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                            .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                            .getQuery()
                            .and(Competencia_Table.tipoId.withTable().between(347).and(349))
                            .queryList(databaseWrapper);
                    break;
            }

            List<Integer> competenciaIdList = new ArrayList<>();
            for (Competencia itemCompetencia : competencias)competenciaIdList.add(itemCompetencia.getCompetenciaId());

            List<Competencia> capacidades = CapacidadSesionAprendizajeModel.SQLView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                    .and(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build()).in(competenciaIdList))
                    .getQuery()
                    .queryList(databaseWrapper);

            List<Integer> capacidadIdList = new ArrayList<>();
            for (Competencia itemCapacidad : capacidades)capacidadIdList.add(itemCapacidad.getCompetenciaId());


            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList;
            if(idrubroformal!=3){
                rubroEvaluacionProcesoList = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(calendarioPerioId))
                        .and(RubroEvaluacionProcesoC_Table.competenciaId.in(capacidadIdList))
                        .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                        .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silaboEventoId))
                        .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280)).and(RubroEvaluacionProcesoC_Table.rubroFormal.eq(idrubroformal))//estado eliminado
                        .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                        .queryList(databaseWrapper);
                Log.d(TAG,"CONSULTACON01"+ rubroEvaluacionProcesoList.size());
            }else{
                rubroEvaluacionProcesoList = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(calendarioPerioId))
                        .and(RubroEvaluacionProcesoC_Table.competenciaId.in(capacidadIdList))
                        .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                        .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silaboEventoId))
                        .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))//estado eliminado
                        .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                        .queryList(databaseWrapper);
                Log.d(TAG,"CONSULTAtodos"+ rubroEvaluacionProcesoList.size());
            }

            boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(calendarioPerioId, cargaCursoId, false, databaseWrapper);
            CalendarioPeriodo calendarioPeriodo = SQLite.select()
                    .from(CalendarioPeriodo.class)
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(calendarioPerioId))
                    .querySingle(databaseWrapper);

            List<Object> competenciasRubrosList = getCompetenciasRubrosList(databaseWrapper, rubroEvaluacionProcesoList, competencias, capacidades, null, silaboEventoId, calendarioPeriodo, isvigente);
            databaseWrapper.setTransactionSuccessful();
            callback.onLoaded(competenciasRubrosList);
        } catch (Exception e){
            e.printStackTrace();
            callback.onLoaded(new ArrayList<Object>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    private void getCompetencia(int idrubroformal,List<Object> items,CompetenciaUi competenciaUi,int sesionAprendizajeId, int calendarioPerioId, int silaboEventoId){

        List<Competencia> capasidades = CapacidadSesionAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build()).is(competenciaUi.getId()))
                .getQuery()
                .queryList();

        int poscionCapacidad = 0;

        for (Competencia itemCapasidad : capasidades) {
            poscionCapacidad++;
            CapacidadUi capacidadUi = new CapacidadUi();
            capacidadUi.setCompetenciaUi(competenciaUi);
            capacidadUi.setId(itemCapasidad.getCompetenciaId());
            capacidadUi.setTitle(itemCapasidad.getNombre());
            capacidadUi.setPosicion(poscionCapacidad);
            capacidadUi.setToogle(itemCapasidad.isToogle());
            capacidadUi.setRubroProcesoUis(
                    getRubroEvaluacionProceso(
                            idrubroformal,
                            itemCapasidad,
                            capacidadUi,
                            calendarioPerioId,
                            itemCapasidad.getCompetenciaId(),
                            sesionAprendizajeId,
                            silaboEventoId)
            );
            items.add(capacidadUi);
            competenciaUi.addCapacidad(capacidadUi);

            /*List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProceso_Table.calendarioPeriodoId.is(calendarioPerioId))
                    .and(RubroEvaluacionProceso_Table.competenciaId.is(itemCapasidad.getCompetenciaId()))
                    .and(RubroEvaluacionProceso_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                    .and(RubroEvaluacionProceso_Table.silaboEventoId.is(silaboEventoId))
                    .and(RubroEvaluacionProceso_Table.estadoId.notIn(280))//estado eliminado
                    .queryList();

            capacidadUi.setCantidad(rubroEvaluacionProcesoList.size());
            int posicionRubros = 0;
            for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoList){
                posicionRubros++;
                RubroProcesoUi rubroProcesoUi = refactRubroProceso(itemRubroEvaluacionProceso);
                rubroProcesoUi.setPosicion(posicionRubros);
                capacidadUi.addRubroProceso(rubroProcesoUi);
            }*/
        }
    }

    private List<RubroProcesoUi> getRubroEvaluacionProceso(int idrubroformal,Competencia itemCapasidad, CapacidadUi capacidadUi, int calendarioPerioId, int competenciaId, int sesionAprendizajeId, int silaboEventoId) {
        List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList;
        if(idrubroformal!=3){
           rubroEvaluacionProcesoList = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(calendarioPerioId))
                    .and(RubroEvaluacionProcesoC_Table.competenciaId.is(itemCapasidad.getCompetenciaId()))
                    .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silaboEventoId))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280)).and(RubroEvaluacionProcesoC_Table.rubroFormal.eq(idrubroformal))//estado eliminado
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                    .queryList();
           Log.d(TAG,"CONSULTACON01"+ rubroEvaluacionProcesoList.size());
        }else{
            rubroEvaluacionProcesoList = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(calendarioPerioId))
                    .and(RubroEvaluacionProcesoC_Table.competenciaId.is(itemCapasidad.getCompetenciaId()))
                    .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silaboEventoId))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))//estado eliminado
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                    .queryList();
            Log.d(TAG,"CONSULTAtodos"+ rubroEvaluacionProcesoList.size());
        }

        int posicionRubros = rubroEvaluacionProcesoList.size();
        capacidadUi.setCantidad(rubroEvaluacionProcesoList.size());
        for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoList){
            RubroProcesoUi rubroProcesoUi = refactRubroProceso(itemRubroEvaluacionProceso);
            rubroProcesoUi.setPosicion(posicionRubros);
            capacidadUi.addRubroProceso(rubroProcesoUi);
            rubroProcesoUiList.add(rubroProcesoUi);
            posicionRubros--;
        }
        return rubroProcesoUiList;
    }

    private RubroProcesoUi refactRubroProceso(RubroEvaluacionProcesoC rubroEvaluacionProceso){

        RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();

        if(rubroEvaluacionProceso != null){
            rubroProcesoUi.setKey(rubroEvaluacionProceso.getKey());
            rubroProcesoUi.setTipoNotaId(rubroEvaluacionProceso.getTipoNotaId());
            rubroProcesoUi.setDesempenioIcdId(rubroEvaluacionProceso.getDesempenioIcdId());
            rubroProcesoUi.setFecha(Utils.f_fecha_letras(rubroEvaluacionProceso.getFechaCreacion()));
            rubroProcesoUi.setTitulo(rubroEvaluacionProceso.getTitulo());
            rubroProcesoUi.setExportado(rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                    rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                    rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
            rubroProcesoUi.setSubTitulo(rubroEvaluacionProceso.getSubtitulo());
            rubroProcesoUi.setCapacidadId(rubroEvaluacionProceso.getCompetenciaId());
            rubroProcesoUi.setSesionAprendizajeId(rubroEvaluacionProceso.getSesionAprendizajeId());
            rubroProcesoUi.setEstrategiaId(rubroEvaluacionProceso.getEstrategiaEvaluacionId());
            switch (rubroEvaluacionProceso.getFormaEvaluacionId()){
                case 477:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                    break;
                case 478:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                    break;
            }


            if(rubroEvaluacionProceso.getTipoFormulaId() > 0){
                rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
                int cantidadIdicadores = rubroProcesoDao.cantidadIndicadoresFormula(rubroProcesoUi.getKey());
                switch (cantidadIdicadores){
                    case 0:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.ANARANJADO);
                        break;
                    case 1:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.PLOMO);
                        break;
                    default:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.NEGRO);
                        break;
                }
            }
            Log.d(TAG , "MEDIA "+ rubroEvaluacionProceso.getPromedio());
            Log.d(TAG , "DV "+ rubroEvaluacionProceso.getPromedio());
            rubroProcesoUi.setMedia(rubroEvaluacionProceso.getDesviacionEstandar());
            rubroProcesoUi.setDesviacionE(rubroEvaluacionProceso.getDesviacionEstandar());

            T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                    .from(T_RN_MAE_TIPO_EVALUACION.class)
                    .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(rubroEvaluacionProceso.getTipoEvaluacionId()))
                    .querySingle();
            if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());

            switch (rubroEvaluacionProceso.getTiporubroid()){
                case 470:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                    break;
                case 471:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL);
                    break;
                case 473:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE);
                    try {
                        RubroEvaluacionProcesoC rubrica = SQLite.select(RubroEvaluacionProcesoC_Table.titulo.withTable())
                                .from(RubroEvaluacionProcesoC.class)
                                .innerJoin(RubroEvalRNPFormulaC.class)
                                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(rubroEvaluacionProceso.getKey()))
                                .querySingle();

                        rubroProcesoUi.setTituloRubrica(rubrica.getTitulo());
                    }catch (Exception ignored){}
                    break;
            }
            if (rubroEvaluacionProceso.getSesionAprendizajeId()!=0){//Validar lo de Sesion
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
            }else {
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
            }

            RubroEvaluacionProcesoC tareaRubroEvaluacionProceso = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                    .querySingle();

            if(tareaRubroEvaluacionProceso!=null&&!TextUtils.isEmpty(tareaRubroEvaluacionProceso.getTareaId())){
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.TAREA);
            }


            rubroProcesoUi.setDisabledEval(isDiasbleEvaluacion(rubroEvaluacionProceso.getTiporubroid(), rubroEvaluacionProceso.getCalendarioPeriodoId()));

        }

        return rubroProcesoUi;
    }

    @Override
    public void getRubroProceso(String rubroEvaluacionId, Callback<RubroProcesoUi> callback) {

        RubroEvaluacionProcesoC rubroEvaluacionProceso = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.is(rubroEvaluacionId))
                .querySingle();

        RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();
        if(rubroEvaluacionProceso != null){
            rubroProcesoUi.setTitulo(rubroEvaluacionProceso.getTitulo());
            rubroProcesoUi.setExportado(rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                    rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                    rubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
            switch (rubroEvaluacionProceso.getFormaEvaluacionId()){
                case 477:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                    break;
                case 478:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                    break;
            }
            rubroProcesoUi.setTipoFormulaId(rubroEvaluacionProceso.getTipoFormulaId());
            rubroProcesoUi.setTipoValorRedondeoId(rubroEvaluacionProceso.getValorRedondeoId());
            rubroProcesoUi.setTipoNotaId(rubroEvaluacionProceso.getTipoNotaId());
            if(rubroEvaluacionProceso.getTipoFormulaId() > 0){
                rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
                int cantidadIdicadores = rubroProcesoDao.cantidadIndicadoresFormula(rubroProcesoUi.getKey());
                switch (cantidadIdicadores){
                    case 0:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.ANARANJADO);
                        break;
                    case 1:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.PLOMO);
                        break;
                    default:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.NEGRO);
                        break;
                }
            }

            switch (rubroEvaluacionProceso.getTiporubroid()){
                case 470:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                    break;
                case 471:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL);
                    break;
                case 473:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE);
                    try {
                        RubroEvaluacionProcesoC rubrica = SQLite.select(RubroEvaluacionProcesoC_Table.titulo.withTable())
                                .from(RubroEvaluacionProcesoC.class)
                                .innerJoin(RubroEvalRNPFormulaC.class)
                                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(rubroEvaluacionProceso.getKey()))
                                .querySingle();


                        rubroProcesoUi.setTituloRubrica(rubrica.getTitulo());
                    }catch (Exception ignored){}
                    break;
            }
            rubroProcesoUi.setMedia(rubroEvaluacionProceso.getPromedio());
            rubroProcesoUi.setDesviacionE(rubroEvaluacionProceso.getDesviacionEstandar());
            rubroProcesoUi.setEstrategiaId(rubroEvaluacionProceso.getEstrategiaEvaluacionId());
            T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                    .from(T_RN_MAE_TIPO_EVALUACION.class)
                    .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(rubroEvaluacionProceso.getTipoEvaluacionId()))
                    .querySingle();
            if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());

            if (rubroEvaluacionProceso.getSesionAprendizajeId()!=0){//Validar lo de Sesion
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
            }else {
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
            }

            if(TextUtils.isEmpty(rubroEvaluacionProceso.getTareaId())){
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.TAREA);
            }
            rubroProcesoUi.setDisabledEval(isDiasbleEvaluacion(rubroEvaluacionProceso.getTiporubroid(), rubroEvaluacionProceso.getCalendarioPeriodoId()));
        }
        callback.onLoaded(rubroProcesoUi);
    }
    private boolean isDiasbleEvaluacion(int tipoRubroId,int calendarioPeriodoId){
        boolean diabledEval;
        CalendarioPeriodo calendarioPeriodo = SQLite.select(CalendarioPeriodo_Table.estadoId.withTable())
                .from(CalendarioPeriodo.class)
                .where(CalendarioPeriodo_Table.calendarioPeriodoId.is(calendarioPeriodoId))
                .querySingle();

        if(calendarioPeriodo == null){
            Log.e(TAG,"RubroEvaluacion sin CalendarioPeriodoUi Asociado");return true; }

//        Log.d(TAG,"Calendario Estado: "+ calendarioPeriodo.getEstadoId());
        switch (calendarioPeriodo.getEstadoId()){
            case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                diabledEval = true;
                break;
            case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                diabledEval = true;
                break;
            case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                diabledEval = false;
                break;
            default:
                diabledEval = true;
                break;
        }

//        Log.d(TAG,"Calendario tipoRubroId: "+ tipoRubroId);
        switch (tipoRubroId){
            case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                diabledEval = true;
                break;
            case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                diabledEval = true;
                break;
        }

        return  diabledEval;
    }

    @Override
    public void updateRubrosProceso(int rubroEvaluacionId, final Callback<Boolean> callback) {

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {

               /* List<EvaluacionProceso> evaluacionProcesos = SQLite.select()
                        .from(EvaluacionProceso.class)
                        .where(EvaluacionProceso_Table.evaluacionProcesoId.greaterThan(0))
                        .queryList();*/


                callback.onLoaded(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callback.onLoaded(false);
            }
        }).build();

        transaction.execute();

    }

    @Override
    public void showListCamposTematicosListRubros(List<RubroProcesoUi> rubroProcesoUis, Callback<List<IndicadoresCamposAccionUi>> callback) {
        try {
            List<IndicadoresCamposAccionUi>  indicadoresCamposAccionUiList = new ArrayList<>();
            for (RubroProcesoUi rubroProcesoUi: rubroProcesoUis){
                IndicadoresCamposAccionUi  indicadoresCamposAccionUi = getListCamposTematicos(rubroProcesoUi);
                rubroProcesoUi.setIndicadoresCamposAccionUi(indicadoresCamposAccionUi);
                indicadoresCamposAccionUiList.add(indicadoresCamposAccionUi);
                callback.onLoaded(indicadoresCamposAccionUiList);
            }
        }catch (Exception e){
            callback.onLoaded(new ArrayList<IndicadoresCamposAccionUi>());
        }
    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, SuccesCallback<PeriodoUi> callback) {
        try {
            CalendarioPeriodo calendarioPeriodo = calendarioPeriodoDao.get(calendarioPeriodoId);
            Tipos tipo = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();
            Log.d(TAG, "PeriodoUi : " + tipo.getTipoId() + "");
            PeriodoUi periodoUi = new PeriodoUi(String.valueOf(calendarioPeriodo.getCalendarioPeriodoId()), tipo.getNombre(), "", false);

            if(calendarioPeriodo.getEstadoId()==CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE){
                periodoUi.setStatus(true);
                periodoUi.setVigente(true);

            }
            callback.onLoaded(true, periodoUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoaded(false, null);
        }
    }

    private IndicadoresCamposAccionUi getListCamposTematicos(RubroProcesoUi rubroProcesoUi) {

        boolean isBase=false;
        boolean isEnfoque=false;
        boolean isTransversal=false;
        Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(rubroProcesoUi.getCapacidadId());
        if(competencia!=null){
            switch (competencia.getTipoId()){
                case Competencia.COMPETENCIA_BASE:
                    isBase =  true;
                    break;
                case Competencia.COMPETENCIA_ENFQ:
                    isEnfoque = true;
                    break;
                case Competencia.COMPETENCIA_TRANS:
                    isTransversal = true;
                    break;
            }
        }

        Icds icds = SQLite.select( Icds_Table.icdId.withTable(),
                Icds_Table.descripcion.withTable(),
                Icds_Table.titulo.withTable(),
                Icds_Table.alias.withTable(),
                Icds_Table.peso.withTable(),
                Icds_Table.estado.withTable(),
                DesempenioIcd_Table.tipoId.withTable(),
                DesempenioIcd_Table.url.withTable())
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.withTable().is(rubroProcesoUi.getDesempenioIcdId()))
                .querySingle();
        DesempenioIcd desempenioIcd = SQLite.select()
                .from(DesempenioIcd.class)
                .where(DesempenioIcd_Table.icdId.withTable().eq(icds.getIcdId()))
                .querySingle();

         if(icds == null) return null;

            IndicadoresCamposAccionUi indicadoresUi = new IndicadoresCamposAccionUi();
            indicadoresUi.setId(icds.getIcdId());
            indicadoresUi.setDescripcion(icds.getDescripcion());
            indicadoresUi.setTitulo(icds.getTitulo());
            indicadoresUi.setAlias(icds.getAlias());
            indicadoresUi.setPeso(icds.getPeso());
            indicadoresUi.setEstado(icds.getEstado());
            indicadoresUi.setUrl(desempenioIcd.getUrl());

            if(isBase){
                indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.BASE);
            }

            if(isEnfoque){
                indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.ENFOQUE);
            }

            if(isTransversal){
                indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.TRANSVERSAL);
            }

            switch (icds.getTipoId()){
                case Icds.TIPO_HACER:
                    indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.HACER);
                    break;
                case Icds.TIPO_SABER:
                    indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SABER);
                    break;
                case Icds.TIPO_SER:
                    indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SER);
                    break;
                default:
                    indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.DEFAULT);
                    break;
            }

            Log.d(TAG, "NombreIndicador : " + icds.getTitulo());

            List<CampoTematicoRubroQuery> campotematicos = SQLite.select(Utils.f_allcolumnTable(
                    CampoTematico_Table.ALL_COLUMN_PROPERTIES,
                    RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable() ))
                    .from(CampoTematico.class)
                    .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                    .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.is(rubroProcesoUi.getKey()))
                    .queryCustomList(CampoTematicoRubroQuery.class);

            indicadoresUi.setCampoAccionList(getListCampoAccion(campotematicos));
        return indicadoresUi;

    }

    private List<CamposAccionUi> getListCampoAccion(List<CampoTematicoRubroQuery> campoTematicoList) {
        List<CamposAccionUi> campotematicoUipadresList = new ArrayList<>();


        for (CampoTematicoRubroQuery itemCampoTematico : campoTematicoList) {
            CamposAccionUi campotematicoUi = new CamposAccionUi();
            campotematicoUi.setKey(String.valueOf(itemCampoTematico.getCampoTematicoId()));

            CampoTematico campoTematicoPadre = SQLite.select()
                    .from(CampoTematico.class)
                    .where(CampoTematico_Table.parentId.withTable().is(0))
                    .and(CampoTematico_Table.campoTematicoId.is(itemCampoTematico.getParentId()))
                    .querySingle();

            if (campoTematicoPadre != null) {
                CamposAccionUi campotematicoUiPadre = new CamposAccionUi();
                campotematicoUiPadre.setKey(String.valueOf(campoTematicoPadre.getCampoTematicoId()));
                campotematicoUiPadre.setTipo(CamposAccionUi.Tipo.PARENT);
                int posicionPadre = campotematicoUipadresList.indexOf(campotematicoUiPadre);
                if (posicionPadre == -1) {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }
                campotematicoUi.setBinieta("- ");
                campotematicoUi.setTipo(CamposAccionUi.Tipo.CHILDREN);
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampoAccion(campotematicoUi);

            } else {
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                campotematicoUi.setTipo(CamposAccionUi.Tipo.DEFAULT);
                campotematicoUipadresList.add(campotematicoUi);

            }
        }


        int count = 0;
        List<CamposAccionUi> campotematicoUiList = new ArrayList<>();
        for (CamposAccionUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            count ++;
            String binieta = count + ". ";
            itemCampotematicoUiPadre.setBinieta(binieta);
            campotematicoUiList.add(itemCampotematicoUiPadre);
            if ( itemCampotematicoUiPadre.getCampoAccionUiList() == null) continue;
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampoAccionUiList());
        }

        return campotematicoUiList;
    }
    @Override
    public boolean validarEvaluacionRubroNormal(String idRubroProceso) {
        Log.d(TAG, "validarEvaluacionRubroNormal "+ idRubroProceso);
        boolean validado=false;
        try {List<EvaluacionProcesoC>evaluacionProcesoCList= SQLite.select().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(idRubroProceso)).queryList();
            if(evaluacionProcesoCList.size()>0)validado=true;
        }catch (Exception e){e.getStackTrace();}

        return validado;
    }

    @Override
    public boolean publicarEvaluacion(String rubricaEvaluacionId) {
        boolean success = false;
        try {
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubricaEvaluacionId))
                    .querySingle();

            if(rubroEvaluacionProcesoC!=null){
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();

                List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                        .queryList();

                for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
                    evaluacionProcesoC.setPublicado(1);
                    evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    evaluacionProcesoC.save();
                }

                success = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public void getRubroProcesoSilavoList(final int idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid, ListCallback<Object> callback) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                    .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES)))
                    .getQuery(cargaCursoId)
                    .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                    .querySingle(databaseWrapper);

            List<Competencia> competenciaList = new ArrayList<>();
            if (silaboEvento == null) {
                callback.onLoaded(new ArrayList<Object>());
                return;
            }
            //   Log.d(TAG,"Idcompetencia" + idcompetencia);

            switch (idcompetencia){
                case 0:
                    competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                            .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                            .getQuery(silaboEvento.getSilaboEventoId(), idCalendarioPeriodo)
                            .and(Competencia_Table.tipoId.withTable().between(348).and(349))
                            .queryList(databaseWrapper);
                    break;
                case 1:competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silaboEvento.getSilaboEventoId(), idCalendarioPeriodo)
                        .and(Competencia_Table.tipoId.withTable().eq(347))
                        .queryList(databaseWrapper);
                    break;
                case 3:
                    competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                            .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                            .getQuery(silaboEvento.getSilaboEventoId(), idCalendarioPeriodo)
                            .and(Competencia_Table.tipoId.withTable().between(347).and(349))
                            .queryList(databaseWrapper);
                    break;
            }

            List<Integer> competenciaIdList = new ArrayList<>();
            for (Competencia itemCompetencia : competenciaList)competenciaIdList.add(itemCompetencia.getCompetenciaId());


            List<Competencia> capacidadListFull = CapacidadUnidadAprendizajeModel.SQLView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(silaboEvento.getSilaboEventoId(), idCalendarioPeriodo, competenciaIdList)
                    .queryList(databaseWrapper);
            List<Integer> capacidadIdList = new ArrayList<>();
            for (Competencia itemCapacidad : capacidadListFull)capacidadIdList.add(itemCapacidad.getCompetenciaId());

            Where<RubroEvaluacionProcesoC> procesoCWhere = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(idCalendarioPeriodo))
                    .and(RubroEvaluacionProcesoC_Table.competenciaId.in(capacidadIdList))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280));

            if(idcompetencia!=3){
                procesoCWhere.and(RubroEvaluacionProcesoC_Table.rubroFormal.eq(idcompetencia));
            }
            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoListfull = procesoCWhere
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                    .queryList(databaseWrapper);

            ParametrosDisenio parametrosDisenio = new ParametrosDisenio();
            if (parametrodisenioid!=0){
                parametrosDisenio =  parametrosDisenioDao.get(parametrodisenioid);
            }

            boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(idCalendarioPeriodo, cargaCursoId, false, databaseWrapper);
            CalendarioPeriodo calendarioPeriodo = SQLite.select()
                    .from(CalendarioPeriodo.class)
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(idCalendarioPeriodo))
                    .querySingle(databaseWrapper);

            List<Object> competenciasRubrosList = getCompetenciasRubrosList(databaseWrapper, rubroEvaluacionProcesoListfull, competenciaList, capacidadListFull, parametrosDisenio, silaboEvento.getSilaboEventoId(), calendarioPeriodo, isvigente);

            databaseWrapper.setTransactionSuccessful();

            callback.onLoaded(competenciasRubrosList);

        } catch (Exception e){
            e.printStackTrace();
            callback.onLoaded(new ArrayList<Object>());
        }finally {
            databaseWrapper.endTransaction();
        }


    }

    public List<Object> getCompetenciasRubrosList(DatabaseWrapper databaseWrapper, List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoListfull,
                                                  List<Competencia> competenciaList,
                                                  List<Competencia> capacidadListFull,
                                                  ParametrosDisenio parametrosDisenio,
                                                  int silaboEventoId,
                                                  CalendarioPeriodo calendarioPeriodo,
                                                  boolean isvigente) {
        List<Object> items = new ArrayList<>();
        List<String> rubroProcesoEvalaucionIdList = new ArrayList<>();
        List<Integer> desempenioIcdIdList = new ArrayList<>();
        for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoListfull){
           // Log.d(TAG, "itemRubroEvaluacionProcesoC "+ itemRubroEvaluacionProcesoC.getTitulo()+ "id "+itemRubroEvaluacionProcesoC.getKey() );
            rubroProcesoEvalaucionIdList.add(itemRubroEvaluacionProcesoC.getKey());
            desempenioIcdIdList.add(itemRubroEvaluacionProcesoC.getDesempenioIcdId());
        }

        List<RubroEvaluacionResultado> rubroEvaluacionResultadoList = new ArrayList<>(SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionResultado.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable()))
                .where(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable().in(rubroProcesoEvalaucionIdList))
                .queryList(databaseWrapper));
       // Log.d(TAG, "rubroEvaluacionResultadoList "+ rubroEvaluacionResultadoList.size());

        List<IndicadorQuery> icds = new DbflowCompat(SQLite.select(Utils.f_allcolumnTable(Icds_Table.icdId,
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
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.withTable().in(desempenioIcdIdList))
                .groupBy(Utils.f_allcolumnTable(Utils.f_allcolumnTable(Icds_Table.icdId,
                        Icds_Table.desempenioId,
                        Icds_Table.descripcion,
                        Icds_Table.titulo,
                        Icds_Table.alias,
                        Icds_Table.descripcion,
                        Icds_Table.estado,
                        Icds_Table.peso,
                        DesempenioIcd_Table.desempenioIcdId,
                        DesempenioIcd_Table.descripcion,
                        DesempenioIcd_Table.tipoId,
                        DesempenioIcd_Table.url))))
                .queryCustomList(IndicadorQuery.class, databaseWrapper);

        List<CampoTematicoRubroQuery> campotematicos = new DbflowCompat(SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES,
                RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable() ))
                .from(CampoTematico.class)
                .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES,
                        RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable())))
                .queryCustomList(CampoTematicoRubroQuery.class, databaseWrapper);

        List<RubroEvalRNPFormulaQuery> rubricaList = new DbflowCompat(SQLite.select(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable() ))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable())))
                .queryCustomList(RubroEvalRNPFormulaQuery.class, databaseWrapper);


        List<RubroEvalRNPFormulaQuery> rnpFormulaCList =  new DbflowCompat(SQLite.select(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable() ))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable())))
                .queryCustomList(RubroEvalRNPFormulaQuery.class, databaseWrapper);


        //ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
      //  Log.d(TAG, "competenciaList: " + competenciaList);
      //  Log.d(TAG, "rubroEvaluacionProcesoListfull: " + rubroEvaluacionProcesoListfull.size());


        boolean isEdiatar = false;

        if(calendarioPeriodo!=null)
            switch (calendarioPeriodo.getEstadoId()){
                case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                    isEdiatar = true;
                break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                    isEdiatar = isvigente;
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                    isEdiatar = isvigente;
                    break;
            }

        int posicionCompetencia = 0;
        for (Competencia itemCompetencia : competenciaList) {
            posicionCompetencia++;
            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(itemCompetencia.getCompetenciaId());
            competenciaUi.setPosicion(posicionCompetencia);
            competenciaUi.setTitulo(itemCompetencia.getNombre());
            competenciaUi.setNivel(3);

            if(parametrosDisenio!=null){
                ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
                parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
                parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
                parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
                competenciaUi.setParametroDisenioUi(parametroDisenioUi);
            }


            items.add(competenciaUi);

            List<Competencia> capacidadList = new ArrayList<>();
            for (Competencia itemCapacidad : capacidadListFull){
                if(itemCapacidad.getSuperCompetenciaId()==itemCompetencia.getCompetenciaId()){
                    capacidadList.add(itemCapacidad);
                }
            }

            for (Competencia itemCapacidad : capacidadList) {

                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setToogle(itemCapacidad.isToogle());
                capacidadUi.setCompetenciaUi(competenciaUi);
                capacidadUi.setId(itemCapacidad.getCompetenciaId());
                capacidadUi.setTitle(itemCapacidad.getNombre());
                capacidadUi.setBtnSelect(true);
                capacidadUi.setSilaboEventoId(silaboEventoId);
                capacidadUi.setNivel(3);
                capacidadUi.setCalendarioId(calendarioPeriodo.getCalendarioPeriodoId());
                capacidadUi.setTipoFormulaUi(new TipoFormulaUi());
                capacidadUi.setTipoEscalaEvaluacionUi(new TipoEscalaEvaluacionUi());
                capacidadUi.setTipoRedondeadoUi(new TipoRedondeadoUi());
                capacidadUi.setTipoEvaluacionUi(new TipoEvaluacionUi());
                capacidadUi.setTipoNotaUi(new TipoNotaUi());
                capacidadUi.setCalendarioAnclar(isvigente);
                capacidadUi.setCalendarioEditar(isEdiatar);
                //Collections.reverse(getRubroEvaluacionProceso(itemCapacidad, capacidadUi, Integer.valueOf(idCalendarioPeriodo), silaboEvento.getSilaboEventoId()));
                //capacidadUi.setRubroProcesoUis(getRubroEvaluacionProceso(idcompetencia, itemCapacidad, capacidadUi, Integer.valueOf(idCalendarioPeriodo), silaboEvento.getSilaboEventoId()));
                List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();
                List<RubroProcesoUi> rubroEvaluacionProcesoDetalles = new ArrayList<>();

                List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new ArrayList<>();
                for (RubroEvaluacionProcesoC itemRubroProceso : rubroEvaluacionProcesoListfull){
                    if(itemRubroProceso.getCompetenciaId() == itemCapacidad.getCompetenciaId()){
                        rubroEvaluacionProcesoCList.add(itemRubroProceso);
                    }
                }


                /*Validar si capacidad tiene su resultado*/
                long rubroResultado = SQLite.selectCountOf()
                        .from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.competenciaId.withTable().is(itemCapacidad.getCompetenciaId()))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().is(calendarioPeriodo.getCalendarioPeriodoId()))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().is(silaboEventoId))
                        .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1))
                        .count(databaseWrapper);

                if(rubroResultado>0){
                    capacidadUi.setTieneResultado(true);
                }else {
                    capacidadUi.setTieneResultado(false);
                }


                capacidadUi.setCantidad(rubroEvaluacionProcesoCList.size());

                int posicionRubros = rubroEvaluacionProcesoCList.size();
                for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoCList) {

                    RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();
                    rubroProcesoUi.setKey(itemRubroEvaluacionProceso.getKey());
                    rubroProcesoUi.setDesempenioIcdId(itemRubroEvaluacionProceso.getDesempenioIcdId());
                    rubroProcesoUi.setPosicion(posicionRubros);
                    rubroProcesoUi.setFecha(Utils.f_fecha_letras(itemRubroEvaluacionProceso.getFechaCreacion()));
                    rubroProcesoUi.setTitulo(itemRubroEvaluacionProceso.getTitulo());
                    rubroProcesoUi.setExportado(itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                            itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                            itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                    rubroProcesoUi.setSubTitulo(itemRubroEvaluacionProceso.getSubtitulo());
                    rubroProcesoUi.setColorRubro(itemRubroEvaluacionProceso.getTipoColorRubroProceso());
                    rubroProcesoUi.setTipoFormulaId(itemRubroEvaluacionProceso.getTipoFormulaId());
                    rubroProcesoUi.setEstrategiaId(itemRubroEvaluacionProceso.getEstrategiaEvaluacionId());
                    rubroProcesoUi.setSesionAprendizajeId(itemRubroEvaluacionProceso.getSesionAprendizajeId());
                    rubroProcesoUi.setCalendarioPeriodId(itemRubroEvaluacionProceso.getCalendarioPeriodoId());
                    rubroProcesoUi.setSilaboEventoId(itemRubroEvaluacionProceso.getSilaboEventoId());
                    rubroProcesoUi.setFormaEvaluacionId(itemRubroEvaluacionProceso.getFormaEvaluacionId());
                    rubroProcesoUi.setTipoNotaId(itemRubroEvaluacionProceso.getTipoNotaId());
                    posicionRubros--;

                    RubroEvaluacionResultado rubroEvaluacionResultado = null;
                    for(RubroEvaluacionResultado itemRubroEvaluacionResultado : rubroEvaluacionResultadoList){
                        if(itemRubroEvaluacionResultado.getRubroEvalProcesoId().equals(itemRubroEvaluacionProceso.getKey())){
                            rubroEvaluacionResultado = itemRubroEvaluacionResultado;
                            break;
                        }
                    }

                    if (rubroEvaluacionResultado != null) {
                   Log.d(TAG, "ESTADO RUBRO + "+ rubroEvaluacionResultado.getTitulo() + " / "+ rubroEvaluacionResultado.getEstadoId());
                        if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.ANCLADA) {
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.ANCLADA);
                            rubroProcesoUi.setTipoAncla(true);
                            capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                        } else if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.EVALUADO) {
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.EVALUADA);
                            rubroProcesoUi.setTipoAncla(true);
                            capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                        } else {
                           rubroProcesoUi.setTipoAncla(false);
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.DEFECTO);
                        }
                    }

                    List<RubrosAsociadosUi> rubrosAsociadosUis = new ArrayList<>();
                    int count = 0;
                    for(RubroEvalRNPFormulaQuery  rubroEvalRNPFormulaC : rnpFormulaCList){
                        if(itemRubroEvaluacionProceso.getKey().equals(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId())){
                            String rubroProcesoPrincipal = String.valueOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                            String rubroProcesoSencundario = String.valueOf(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());

                            RubrosAsociadosUi rubrosAsociadosUi =  new RubrosAsociadosUi(count,
                                    null,
                                    rubroProcesoPrincipal,
                                    rubroProcesoSencundario);
                            rubrosAsociadosUi.setNombreRubroAsociado(rubroEvalRNPFormulaC.getTitulo());
                            rubrosAsociadosUis.add(rubrosAsociadosUi);
                        }
                    }

                    switch (itemRubroEvaluacionProceso.getFormaEvaluacionId()) {
                        case 477:
                            rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                            break;
                        case 478:
                            rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                            break;
                    }

                    if (itemRubroEvaluacionProceso.getTipoFormulaId() > 0) {
                        rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
                        int cantidadIdicadores = rubroProcesoDao.cantidadIndicadoresFormula(rubroProcesoUi.getKey());
                        switch (cantidadIdicadores){
                            case 0:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.ANARANJADO);
                                break;
                            case 1:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.PLOMO);
                                break;
                            default:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.NEGRO);
                                break;
                        }
                    }


                    rubroProcesoUi.setRubrosAsociadosUiList(rubrosAsociadosUis);
                    if (rubrosAsociadosUis.size() > 0) {
                        rubroEvaluacionProcesoDetalles.add(rubroProcesoUi);
                    }
                   // Log.d(TAG, "PROMEDIO "+itemRubroEvaluacionProceso.getPromedio() );
                    //Log.d(TAG, "DV "+itemRubroEvaluacionProceso.getDesviacionEstandar() );
                    rubroProcesoUi.setMedia(itemRubroEvaluacionProceso.getPromedio());
                    rubroProcesoUi.setDesviacionE(itemRubroEvaluacionProceso.getDesviacionEstandar());
                    T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                            .from(T_RN_MAE_TIPO_EVALUACION.class)
                            .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(itemRubroEvaluacionProceso.getTipoEvaluacionId()))
                            .querySingle(databaseWrapper);
                    if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());



                    switch (itemRubroEvaluacionProceso.getTiporubroid()) {
                        case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                            break;
                        case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL);
                            break;
                        case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE);
                            RubroEvalRNPFormulaQuery rubrica = null;
                            for(RubroEvalRNPFormulaQuery itemRubrica : rubricaList){
                                if(itemRubroEvaluacionProceso.getKey().equals(itemRubrica.getRubroEvaluacionSecId())){
                                    rubrica = itemRubrica;
                                    break;
                                }
                            }
                            if(rubrica!=null)rubroProcesoUi.setTituloRubrica(rubrica.getTitulo());
                            break;
                    }

                    if (itemRubroEvaluacionProceso.getSesionAprendizajeId()!=0){//Validar lo de Sesion
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
                    }else {
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
                    }

                    RubroEvaluacionProcesoC tareaRubroEvaluacionProceso = SQLite.select()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.key.eq(itemRubroEvaluacionProceso.getKey()))
                            .querySingle(databaseWrapper);

                    if(tareaRubroEvaluacionProceso!=null&&!TextUtils.isEmpty(tareaRubroEvaluacionProceso.getTareaId())){
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.TAREA);
                    }

                    rubroProcesoUi.setDisabledEval(isDiasbleEvaluacion(itemRubroEvaluacionProceso.getTiporubroid(), itemRubroEvaluacionProceso.getCalendarioPeriodoId()));


                    IndicadorQuery indicadorQuery = null;
                    for (IndicadorQuery itemIndicadorQuery: icds){
                        if(itemIndicadorQuery.getDesempenioIcdId()== itemRubroEvaluacionProceso.getDesempenioIcdId()){
                            indicadorQuery = itemIndicadorQuery;
                            break;
                        }
                    }
                    IndicadoresCamposAccionUi indicadoresUi = new IndicadoresCamposAccionUi();
                    if(indicadorQuery!=null){
                        indicadoresUi.setId(indicadorQuery.getIcdId());
                        indicadoresUi.setDescripcion(indicadorQuery.getDescripcion());
                        indicadoresUi.setTitulo(indicadorQuery.getTitulo());
                        indicadoresUi.setAlias(indicadorQuery.getAlias());
                        indicadoresUi.setPeso(indicadorQuery.getPeso());
                        indicadoresUi.setEstado(indicadorQuery.getEstado());
                        indicadoresUi.setUrl(indicadorQuery.getUrl());

                        boolean isBase=false;
                        boolean isEnfoque=false;
                        boolean isTransversal=false;

                        switch (itemCompetencia.getTipoId()){
                                case Competencia.COMPETENCIA_BASE:
                                    isBase =  true;
                                    break;
                                case Competencia.COMPETENCIA_ENFQ:
                                    isEnfoque = true;
                                    break;
                                case Competencia.COMPETENCIA_TRANS:
                                    isTransversal = true;
                                    break;
                            }


                        if(isBase){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.BASE);
                        }

                        if(isEnfoque){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.ENFOQUE);
                        }

                        if(isTransversal){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.TRANSVERSAL);
                        }

                        switch (indicadorQuery.getTipoId()){
                            case Icds.TIPO_HACER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SER);
                                break;
                            default:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.DEFAULT);
                                break;
                        }

                       // Log.d(TAG, "NombreIndicador : " + indicadorQuery.getTitulo());
                    }


                    List<CampoTematicoRubroQuery> campoTematicoRubroQueryList = new ArrayList<>();
                    for (CampoTematicoRubroQuery itemCampoTematicoRubroQuery : campotematicos){
                        if(itemCampoTematicoRubroQuery.getRubroEvalProcesoId().equals(itemRubroEvaluacionProceso.getKey())){
                            campoTematicoRubroQueryList.add(itemCampoTematicoRubroQuery);
                        }
                    }
                    indicadoresUi.setCampoAccionList(getListCampoAccion(campoTematicoRubroQueryList));



                    Where<EvaluacionProcesoC> evaluacionProcesoCWhere = SQLite.selectCountOf()
                            .from(EvaluacionProcesoC.class)
                            .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(itemRubroEvaluacionProceso.getKey()));

                    long cantidadEval = evaluacionProcesoCWhere
                            .count(databaseWrapper);

                    long cantidadEvalPublicado = evaluacionProcesoCWhere
                            .and(EvaluacionProcesoC_Table.publicado.eq(1))
                            .count(databaseWrapper);

                    if(cantidadEval==0){
                        rubroProcesoUi.setPublicarEval(RubroProcesoUi.PublicarEval.NINGUNO);
                    }else if(cantidadEval == cantidadEvalPublicado){
                        rubroProcesoUi.setPublicarEval(RubroProcesoUi.PublicarEval.TODOS);
                    }else if(cantidadEvalPublicado == 0){
                        rubroProcesoUi.setPublicarEval(RubroProcesoUi.PublicarEval.NINGUNO);
                    }else {
                        rubroProcesoUi.setPublicarEval(RubroProcesoUi.PublicarEval.PARCIAL);
                    }

                    rubroProcesoUi.setIndicadoresCamposAccionUi(indicadoresUi);
                    rubroProcesoUiList.add(rubroProcesoUi);
                }


                for (RubroProcesoUi rubroEvaluacionProcesoUi : rubroEvaluacionProcesoDetalles) {

                   // Log.d(TAG, "rubroEvaluacionProcesoDetalles " + rubroEvaluacionProcesoDetalles.size());
                    //3 -> Si tiene lista de RubrosAsociados
                    for (RubrosAsociadosUi rubrosAsociadosUi : rubroEvaluacionProcesoUi.getRubrosAsociadosUiList()) {
                      //  Log.d(TAG, "rubroEvaluacionProcesoUi " + rubrosAsociadosUi.getNumeroRubroAsociado());

                        RubroProcesoUi rubroEvaluacionProcesoss = new RubroProcesoUi();
                        // rubroEvaluacionProcesoss.setId(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                        rubroEvaluacionProcesoss.setKey(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                        int position = rubroProcesoUiList.indexOf(rubroEvaluacionProcesoss);
                       // Log.d(TAG, "Position : " + position);
                        if (position != -1) {
                            RubroProcesoUi procesoUi = rubroProcesoUiList.get(position);
                            rubrosAsociadosUi.setNumeroRubroAsociado(procesoUi.getPosicion());
                        }

                    }
                }

                capacidadUi.setRubroProcesoUis(rubroProcesoUiList);
                items.add(capacidadUi);
                competenciaUi.addCapacidad(capacidadUi);
            }
        }

        return  items;

    }

    @Override
    public void getTipoFormula(Callback<List<TipoFormulaUi>> listCallback) {
        List<Tipos> tiposList = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.concepto.is("Formula"))
                .queryList();


        List<TipoFormulaUi> tipoFormulaUis = new ArrayList<>();
        // tipoFormulaUis.add(new TipoFormulaUi(0, "Seleccione"));
        for (Tipos tipos : tiposList) {
            String codigo = TextUtils.isEmpty(tipos.getCodigo())? "": tipos.getCodigo();
            tipoFormulaUis.add(new TipoFormulaUi(tipos.getTipoId(),
                    tipos.getNombre(),
                    codigo.equals("1")));
        }
        listCallback.onLoaded(tipoFormulaUis);
    }

    @Override
    public void getTipoNota(String tipoNotaId, CallbackObject<String, String> callBackTipoNota) {
        TipoNotaC tipoNotas = SQLite.select()
                .from(TipoNotaC.class)
                .where(TipoNotaC_Table.key.is(tipoNotaId))
                .querySingle();
        if (tipoNotas == null) return;
        Log.d(TAG, "getTipoNota : " + tipoNotas.getKey() + " / getTipoNombre " + tipoNotas.getNombre());
        callBackTipoNota.onCreateRubroEval(tipoNotas.getKey(), tipoNotas.getNombre());
    }


    @Override
    public void getTipoRedondeo(Callback<List<TipoRedondeadoUi>> listCallback) {
        List<Tipos> tiposList = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.concepto.is("Valor Redondeo"))
                .queryList();
        List<TipoRedondeadoUi> tipoRedondeadoUiList = new ArrayList<>();
        for (Tipos tipos : tiposList) {
            tipoRedondeadoUiList.add(new TipoRedondeadoUi(tipos.getTipoId(),
                    tipos.getNombre()));
        }
        listCallback.onLoaded(tipoRedondeadoUiList);
    }

    @Override
    public void getDataSavedRubroEvaluacionProceso(final RubroProcesoUi rubroProcesoUi, final int cargaCursoId, final List<RubroProcesoUi> rubroProcesoUiList, final CallbackObject<RubroProcesoUi, Boolean> rubroEvaluacionProcesoUiCallback) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                createRubroProcesoFormula(rubroProcesoUi,cargaCursoId, rubroProcesoUiList,databaseWrapper);
                rubroProcesoUi.setRubrosAsociadosUiList(insertRubrosAsociados(rubroProcesoUi, databaseWrapper));
                Log.d(TAG,"onSuccessDataSource: "+rubroProcesoUi.getRubrosAsociadosUiList().size());
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                rubroEvaluacionProcesoUiCallback.onCreateRubroEval(rubroProcesoUi, true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                rubroEvaluacionProcesoUiCallback.onCreateRubroEval(rubroProcesoUi, false);
            }
        }).build();
        transaction.execute();

    }


    private RubroProcesoUi insertRubrosAsociados(final RubroProcesoUi rubroEvaluacionProcesoUi, final List<RubroProcesoUi> rubroEvaluacionProcesoUiList) {
        String keyRubro = rubroProcesoDao.obtenerUltimoRegistroKey();
        Log.d(TAG, "keyRubro : " + keyRubro);
        List<RubroEvalRNPFormulaC> rnpFormulaCS = rnpFormulaDao.getListaRubroEvalRNPFormula(keyRubro);
        List<RubrosAsociadosUi> rubrosAsociadosUis = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormulaC : rnpFormulaCS) {
            rubrosAsociadosUis.add(new RubrosAsociadosUi(rnpFormulaC.getPosicion(),
                    null,
                    rubroEvaluacionProcesoUi.getKey(),
                    rnpFormulaC.getRubroEvaluacionSecId()));
        }
        rubroEvaluacionProcesoUi.setRubrosAsociadosUiList(rubrosAsociadosUis);
        return rubroEvaluacionProcesoUi;
    }

    private  List<RubrosAsociadosUi> insertRubrosAsociados(final RubroProcesoUi rubroEvaluacionProcesoUi,DatabaseWrapper databaseWrapper) {


        List<RubroEvalRNPFormulaC> rnpFormulaCS =   SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(rubroEvaluacionProcesoUi.getKey()))
                .queryList(databaseWrapper);


        List<RubrosAsociadosUi> rubrosAsociadosUis = new ArrayList<>();
        List<String> rubroEvalProceso = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormulaC : rnpFormulaCS) {rubroEvalProceso.add(rnpFormulaC.getRubroEvaluacionSecId());}

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvalProceso))
                .queryList(databaseWrapper);

        for (RubroEvalRNPFormulaC rnpFormulaC : rnpFormulaCS) {

            RubrosAsociadosUi rubrosAsociadosUi = new RubrosAsociadosUi(rnpFormulaC.getPosicion(),
                    null,
                    rubroEvaluacionProcesoUi.getKey(),
                    rnpFormulaC.getRubroEvaluacionSecId());

            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rnpFormulaC.getRubroEvaluacionSecId()))
                    .querySingle(databaseWrapper);

            if(rubroEvaluacionProcesoC!=null){
                for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
                    if(itemRubroEvaluacionProcesoC.getKey().equals(rnpFormulaC.getKey())){
                        rubrosAsociadosUi.setNombreRubroAsociado(rubroEvaluacionProcesoC.getTitulo());
                        break;
                    }
                }
            }
            rubrosAsociadosUis.add(rubrosAsociadosUi);
        }
        //  rubroEvaluacionProcesoUi.setRubrosAsociadosUiList(rubrosAsociadosUis);
        return rubrosAsociadosUis;
    }

    public void createRubroProcesoFormula(final RubroProcesoUi rubroProcesoUi, int cargaCursoId, final List<RubroProcesoUi> rubroProcesoUiList, DatabaseWrapper databaseWrapper) {
        SessionUser sessionUser = SessionUser.getCurrentUser();
        Log.d(TAG,"createRubroProcesoFormula cargaCursoId: "+cargaCursoId);
        RubroEvaluacionProcesoC rubroEvaluacionProceso = new RubroEvaluacionProcesoC();
        rubroEvaluacionProceso.setTitulo(rubroProcesoUi.getTitulo());
        rubroEvaluacionProceso.setSubtitulo(rubroProcesoUi.getSubTitulo());
        rubroEvaluacionProceso.setTipoFormulaId(rubroProcesoUi.getTipoFormulaId());
        rubroEvaluacionProceso.setValorRedondeoId(rubroProcesoUi.getTipoValorRedondeoId());
        rubroEvaluacionProceso.setTipoEscalaEvaluacionId(rubroProcesoUi.getTipoEscalaEvaluacionId());
        rubroEvaluacionProceso.setCompetenciaId(rubroProcesoUi.getCapacidadId());
        rubroEvaluacionProceso.setCalendarioPeriodoId(rubroProcesoUi.getCalendarioPeriodId());
        rubroEvaluacionProceso.setTipoNotaId(rubroProcesoUi.getTipoNotaId());
        rubroEvaluacionProceso.setUsuarioAccionId(sessionUser.getUserId());
        rubroEvaluacionProceso.setUsuarioCreacionId(sessionUser.getUserId());
        rubroEvaluacionProceso.setTiporubroid(RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL);
        rubroEvaluacionProceso.setFormaEvaluacionId(477);
        rubroEvaluacionProceso.setEstadoId(237);
        rubroEvaluacionProceso.setSilaboEventoId(rubroProcesoUi.getSilaboEventoId());

        Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(rubroProcesoUi.getCapacidadId());
        if(competencia.getTipoId() == Competencia.COMPETENCIA_BASE)rubroEvaluacionProceso.setRubroFormal(1);

        /**Color Tipo Formula**/
        rubroEvaluacionProceso.setTipoColorRubroProceso(rubroProcesoUi.getColorRubro());
        /*Advanced*/
        rubroEvaluacionProceso.setTipoEvaluacionId(1); //getIdtipoEvaluacionUi
        rubroEvaluacionProceso.setValorDefecto(rubroProcesoUi.getValorPorDefecto());
        rubroEvaluacionProceso.setSyncFlag(LocalEntity.FLAG_ADDED);
        rubroEvaluacionProceso.setTimestampFlag();

        int indicador = 0;
        for (RubroProcesoUi rubroProcesoUiAsociado : rubroProcesoUiList){
            if(indicador==0||indicador == rubroProcesoUiAsociado.getDesempenioIcdId()) indicador = rubroProcesoUiAsociado.getDesempenioIcdId();
            else {
                indicador=-1;
                break;
            }
        }

        if(indicador > 0)rubroEvaluacionProceso.setDesempenioIcdId(indicador);


        rubroEvaluacionProceso.save(databaseWrapper);

        Log.d(TAG, "Key : " + rubroEvaluacionProceso.getKey());
        rubroProcesoUi.setKey(rubroEvaluacionProceso.getKey());
        // rnpFormulaDao = InjectorUtils.provideRubroEvalRNPFormulaDao();
        //validacionTipoFormula(rubroProcesoUi, rubroProcesoUiList);

        for (RubroProcesoUi rubroProcesoUi1 : rubroProcesoUiList) {
            RubroEvalRNPFormulaC rubroEvalRNPFormula = new RubroEvalRNPFormulaC();
            rubroEvalRNPFormula.setRubroEvaluacionPrimId(rubroProcesoUi.getKey());
            rubroEvalRNPFormula.setRubroEvaluacionSecId(rubroProcesoUi1.getKey());
            rubroEvalRNPFormula.setPeso(rubroProcesoUi1.getPeso());
            rubroEvalRNPFormula.setPosicion(rubroProcesoUi1.getPosicion());
            rubroEvalRNPFormula.setTimestampFlag();
            rubroEvalRNPFormula.setEstado(RubroEvalRNPFormulaC.RUBRO_HIJO_FORMULA);
            rubroEvalRNPFormula.setSyncFlag(LocalEntity.FLAG_ADDED);
            rubroEvalRNPFormula.save(databaseWrapper);
        }

        /*SilaboEvento silaboEvento = SQLite.select()
                .from(SilaboEvento.class)
                .where(SilaboEvento_Table.silaboEventoId.eq(rubroProcesoUi.getSilaboEventoId()))
                .querySingle(databaseWrapper);*/

        evaluacionProcesoDao.crearEvaluacionProcesoFormula(rubroProcesoUi.getKey(), cargaCursoId ,databaseWrapper);

        evaluacionProcesoDao.f_mediaDesviacionEstandarFormula(rubroEvaluacionProceso.getKey());

    }




    @Override
    public void onEvaluacionFormulaList(int cargaCursoId, int cursoId, RubroProcesoUi rubroProcesoUi, CallBackList<List<AlumnosUi>> listcallBackList) {

        List<List<FormulaCelda>> formulaCeldasList = new ArrayList<>();
        List<AlumnosUi> alumnosConNotas = new ArrayList<>();
        Log.d(getClass().getSimpleName(),"rubroProcesoUi : "  + rubroProcesoUi.getKey());
        List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeRubro(false, true, "",  rubroProcesoUi.getKey(), cargaCursoId);
        List<RubroEvalRNPFormulaC> rubrosAsociados = getListRubroEvalRNPFormula(rubroProcesoUi.getKey());

        List<Integer> personIdList = new ArrayList<>();
        for (PersonaContratoQuery persona : personaList)personIdList.add(persona.getPersonaId());

        List<String> rubroEvaluacionKey = new ArrayList<>();
        rubroEvaluacionKey.add(rubroProcesoUi.getKey());
        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC: rubrosAsociados) rubroEvaluacionKey.add(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());
        List<TipoNotaC> tipoNotaCList = getTipoNotaCList(rubroEvaluacionKey);

        List<EvaluacionProcesoC> evaluacionProcesoCList = getEvaluacionProcesoRubrica(rubroEvaluacionKey,personIdList);

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = rubroProcesoDao.get(rubroEvaluacionKey);
        Log.d(getClass().getSimpleName(),"evaluacionProcesoCList : "  + evaluacionProcesoCList.size());
        Log.d(getClass().getSimpleName(),"tipoNotaCList : "  + tipoNotaCList.size());
        Log.d(getClass().getSimpleName(),"rubroEvaluacionProcesoCList : "  + rubroEvaluacionProcesoCList.size());
        for (PersonaContratoQuery persona : personaList) {//Inicia For
            String personaId = String.valueOf(persona.getPersonaId());
            String firstName = Utils.getFirstWord(persona.getNombres());
            String lastName = Utils.capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno());
            String urlProfile = persona.getFoto();
            boolean vigente = persona.getVigente()==1;

            AlumnosUi alumno = new AlumnosUi(personaId, firstName, lastName, urlProfile);
            alumno.setVigente(vigente);

            List<NotaUi> notas = new ArrayList<>();

            TipoNotaC tipoNota = tipoNota(rubroProcesoUi.getTipoNotaId(), tipoNotaCList);

            EvaluacionFormula_TipoNotaUi tipoNotaUi;
            if (tipoNota == null) return;

            NotaUi notaUiFinales = getRubrosFormulaFinal(rubroProcesoUi, alumno, tipoNota,evaluacionProcesoCList);
            notas.add(notaUiFinales);
            List<FormulaCelda> formulaCeldas = new ArrayList<>();
            formulaCeldas.add(notaUiFinales);

            if (rubroProcesoUi.getRubrosAsociadosUiList().size() > 0) {//Valida si tiene RubrosAsociados

                for (RubroEvalRNPFormulaC asociados : rubrosAsociados) {//Iniciando Rubros Asociados

                    EvaluacionProcesoC evaluacionNotasProceso = getEvaluacionProceso(asociados.getRubroEvaluacionSecId(), persona.getPersonaId() ,evaluacionProcesoCList );

                    RubroEvaluacionProcesoC rubroEvaluacionProceso = null;//getRubroProceso(asociados.getRubroEvaluacionSecId());
                    for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
                        if(itemRubroEvaluacionProcesoC.getKey().equals(asociados.getRubroEvaluacionSecId())){
                            rubroEvaluacionProceso = itemRubroEvaluacionProcesoC;
                            break;
                        }
                    }
                    if (rubroEvaluacionProceso == null)continue;


                    TipoNotaC tipoNotaRubrosAsociados = tipoNota(rubroEvaluacionProceso.getTipoNotaId(), tipoNotaCList);
                    if (tipoNotaRubrosAsociados == null) continue;

                    List<ValorTipoNotaC> valorTipoNotaList = tipoNotaRubrosAsociados.getValorTipoNotaList();

                    List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();

                    for (ValorTipoNotaC valorTipoNota : valorTipoNotaList) {
                        ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
                        valorTipoNotaUi.setKey(valorTipoNota.getValorTipoNotaId());
                        valorTipoNota.setAlias(valorTipoNota.getAlias());
                        valorTipoNota.setTitulo(valorTipoNota.getTitulo());
                        valorTipoNota.setLimiteInferior(valorTipoNota.getLimiteInferior());
                        valorTipoNota.setLimiteInferior(valorTipoNota.getLimiteSuperior());
                        valorTipoNota.setIcono(valorTipoNota.getIcono());
                        valorTipoNota.setTipoNotaId(valorTipoNota.getTipoNotaId());
                        valorTipoNotaUiList.add(valorTipoNotaUi);
                    }
                    tipoNotaUi = new EvaluacionFormula_TipoNotaUi(
                            String.valueOf(tipoNotaRubrosAsociados.getTipoNotaId()),
                            tipoNotaRubrosAsociados.getNombre(),
                            tipoNotaRubrosAsociados.getValorDefecto(),
                            tipoNotaRubrosAsociados.getValorMinino(),
                            tipoNotaRubrosAsociados.getValorMaximo(),
                            tipoNotaRubrosAsociados.getLongitudPaso(),
                            tipoNotaRubrosAsociados.getTipoId(),
                            valorTipoNotaUiList);

                    EvaluacionFormula_RubroEvaluacionUi rubro = new EvaluacionFormula_RubroEvaluacionUi(rubroEvaluacionProceso.getKey(), rubroEvaluacionProceso.getTitulo(), rubroEvaluacionProceso.getSubtitulo(),rubroEvaluacionProceso.getTiporubroid());

                    if (evaluacionNotasProceso == null) {
                        NotaUi nota = new NotaUi("", -1, "", "", alumno, rubro,
                                tipoNotaUi, 0, 1);
                        notas.add(nota);
                        formulaCeldas.add(nota);
                    } else {

                        double notaQuantity = evaluacionNotasProceso.getNota();
                        NotaUi nota = new NotaUi("", notaQuantity, evaluacionNotasProceso.getEscala(), evaluacionNotasProceso.getValorTipoNotaId(), alumno, rubro, tipoNotaUi, 0,
                                1
                        );

                        ValorTipoNotaC valorTipoNotaC = null;//valorTipoNotaDao.get(evaluacionNotasProceso.getValorTipoNotaId());

                        for (ValorTipoNotaC itemValorTipoNotaC: valorTipoNotaList){
                            if(itemValorTipoNotaC.getKey().equals(evaluacionNotasProceso.getValorTipoNotaId())){
                                valorTipoNotaC = itemValorTipoNotaC;
                                break;
                            }
                        }

                        if(valorTipoNotaC != null){
                            nota.setIcono(valorTipoNotaC.getIcono());
                            nota.setAlias(valorTipoNotaC.getTitulo());
                        }else {
                            nota.setNota(-1);
                        }
                        notas.add(nota);
                        formulaCeldas.add(nota);
                    }
                }//Terminando Rubro Asociados

            }

            AlumnosUi alumnoConNotas = new AlumnosUi(personaId, firstName, lastName, urlProfile, notas);
            alumnoConNotas.setVigente(vigente);
            alumnosConNotas.add(alumnoConNotas);
            formulaCeldasList.add(formulaCeldas);

        }//Termina For
        listcallBackList.onLoadList(alumnosConNotas, formulaCeldasList);
    }



    @Override
    public void onUpdateEvaluacionFormula(String rubroEvalProcesoId, int personaId, String equipoId, SimpleSuccessCallBack callBack) {

        Log.d(TAG,"rubroEvalProcesoId: " + rubroEvalProcesoId);
        Log.d(TAG,"personaId: " + personaId);
        Log.d(TAG,"equipoId: " + equipoId);
        try {

            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalProcesoId))
                    .querySingle();


            List<String> rubroEvaluacionProcesoIdList = new ArrayList<>();

            switch (rubroEvaluacionProcesoC.getTiporubroid()) {
                case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                    rubroEvaluacionProcesoIdList.add(rubroEvaluacionProcesoC.getKey());
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                    List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                            .from(RubroEvaluacionProcesoC.class)
                            .innerJoin(RubroEvalRNPFormulaC.class)
                            .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()
                                    .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                            .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().eq(rubroEvalProcesoId))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                            .queryList();
                    for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoCList) rubroEvaluacionProcesoIdList.add(itemRubroEvaluacionProcesoC.getKey());
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                    rubroEvaluacionProcesoIdList.add(rubroEvaluacionProcesoC.getKey());
                    break;
            }


            List<Persona> personList = new ArrayList<>();
            switch (personaId){
                case 0:
                    personList = SQLite.select()
                            .from(Persona.class)
                            .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                            .on(Persona_Table.personaId.withTable()
                                    .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.personaId.withTable()))
                            .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                            .on(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable()
                                    .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable()))
                            .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.withTable().eq(rubroEvalProcesoId))
                            .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.equipoId.withTable().eq(equipoId))
                            .queryList();
                    break;
                default:
                    Persona persona = new Persona();
                    persona.setPersonaId(personaId);
                    personList.add(persona);
                    break;
            }

            List<RubroEvaluacionProcesoC> rubroFormulaList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionProcesoC.class)
                    .innerJoin(RubroEvalRNPFormulaC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubroEvaluacionProcesoIdList))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                    .and(RubroEvaluacionProcesoC_Table.formaEvaluacionId.eq(0))
                    .groupBy(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                    .queryList();

            for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroFormulaList){

                RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();
                rubroProcesoUi.setKey(itemRubroEvaluacionProcesoC.getKey());
                rubroProcesoUi.setTitulo(itemRubroEvaluacionProcesoC.getTitulo());
                rubroProcesoUi.setExportado(itemRubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                        itemRubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                        itemRubroEvaluacionProcesoC.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                rubroProcesoUi.setSubTitulo(itemRubroEvaluacionProcesoC.getSubtitulo());
                rubroProcesoUi.setTipoEscalaEvaluacionId(rubroProcesoUi.getTipoEscalaEvaluacionId());
                rubroProcesoUi.setTipoFormulaId(itemRubroEvaluacionProcesoC.getTipoFormulaId());
                rubroProcesoUi.setTipoNotaId(itemRubroEvaluacionProcesoC.getTipoNotaId());
                rubroProcesoUi.setCalendarioPeriodId(itemRubroEvaluacionProcesoC.getCalendarioPeriodoId());
                TipoNotaC tipoNota =  tipoNotaDao.get(itemRubroEvaluacionProcesoC.getTipoNotaId());
                Log.d(TAG, "TipoNotaIFFINAL " + tipoNota.getKey());
                List<ValorTipoNotaC> valorTipoNotaListRubroFinales = getTipoNotaCList(tipoNota.getKey());

                EscalaEvaluacion escalaEvaluacionPadre = escalaEvaluacionDao.getEscalaEvalPorTipoNota(tipoNota.getTipoNotaId());

                List<RubroEvalRNPFormulaC> rubroEvalRNPFormula = rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(rubroProcesoUi.getKey());


                for (Persona persona : personList){
                    onUpdateEvaluacionFormulaPersona(rubroProcesoUi, persona.getPersonaId(),tipoNota,valorTipoNotaListRubroFinales,escalaEvaluacionPadre,rubroEvalRNPFormula);
                }
            }

            callBack.onSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            callBack.onSuccess(false);
        }
    }
    @Override
    public void onUpdateEvaluacionFormula(SimpleSuccessCallBack callBack) {

        try {
            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                    .queryList();
            List<String> rubrosAsociadosIdList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList)rubrosAsociadosIdList.add(evaluacionProcesoC.getRubroEvalProcesoId());

            Log.d(TAG,"size: " + evaluacionProcesoCList.size());

            IProperty[] parametros = Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES);
            List<RubroEvalRNPFormulaC> rubroFormulaList = SQLite.select(parametros)
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .groupBy(parametros)
                    .queryList();
            Log.d(TAG,"rubroFormulaList query: " + SQLite.select(RubroEvalRNPFormulaC_Table.key.withTable())
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .getQuery());
            Log.d(TAG,"rubroFormulaList size: " + rubroFormulaList.size());

            Log.d(TAG,"rubroFormulaList: " + rubroFormulaList.size());

            List<String> listIdRubrosActualizados = new ArrayList<>();
            for (EvaluacionProcesoC itemEvaluacionProcesoC: evaluacionProcesoCList){
                RubroEvalRNPFormulaC rubroEvalRNPFormulaC = null;
                for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC: rubroFormulaList){
                    if(itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId())){
                        rubroEvalRNPFormulaC = itemRubroEvalRNPFormulaC;
                        break;
                    }
                }
                if(rubroEvalRNPFormulaC != null){

                    boolean success = evaluacionProcesoDao.evaluarRubroFormulaPersona(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId(),itemEvaluacionProcesoC.getAlumnoId() );
                    if(success){
                        //Log.d(TAG(),"success: " + success);
                        int poscion = listIdRubrosActualizados.indexOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                        if(poscion!=-1)listIdRubrosActualizados.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                    }
                }
                itemEvaluacionProcesoC.setFormulaSinc(false);
                itemEvaluacionProcesoC.save();
            }

            for (String itemId: listIdRubrosActualizados){
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroProcesoDao.get(itemId);
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();
            }

            callBack.onSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            callBack.onSuccess(false);
        }
    }

    @Override
    public void saveRubroFormulaCapacidad(final int cargaCursoId, final int calendarioPeriodoId, final List<CompetenciaUi> competenciaUiList, final SimpleSuccessCallBack callBack) {

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                for(CompetenciaUi competenciaUi : competenciaUiList){
                    List<CapacidadUi> capacidadUiList = new ArrayList<>();
                    if(competenciaUi.getCapacidadUis()!=null)capacidadUiList.addAll(competenciaUi.getCapacidadUis());
                    for(CapacidadUi capacidadUi: capacidadUiList){
                        RubroProcesoUi rubroFormula = new RubroProcesoUi();
                        rubroFormula.setTitulo(capacidadUi.getTitle());
                        rubroFormula.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                        rubroFormula.setTipoFormulaId(TIPO_FORMULA_MEDIA_ARITMETICA);
                        rubroFormula.setTipoValorRedondeoId(VALORREDONDEO_SIN_REDONDEO);
                        rubroFormula.setTipoEscalaEvaluacionId(TIPOEVALUACION_CUALITATIVA);
                        rubroFormula.setCapacidadId(capacidadUi.getId());
                        rubroFormula.setSilaboEventoId(capacidadUi.getSilaboEventoId());
                        rubroFormula.setCalendarioPeriodId(calendarioPeriodoId);

                        RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select()
                                .from(RubroEvaluacionResultado.class)
                                .where(RubroEvaluacionResultado_Table.competenciaId.eq(capacidadUi.getId()))
                                .and(RubroEvaluacionResultado_Table.silaboEventoId.eq(capacidadUi.getSilaboEventoId()))
                                .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                                .querySingle(databaseWrapper);

                        if(rubroEvaluacionResultado!=null){

                            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                                    .from(RubroEvaluacionProcesoC.class)
                                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvaluacionResultado.getRubroEvalProcesoId()))
                                    .querySingle();
                            if(rubroEvaluacionProcesoC!=null){
                                rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionProcesoC.ESTADO_EVALUADO);
                                rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
                                rubroEvaluacionProcesoC.save();
                            }

                            if(rubroEvaluacionResultado.getEstadoId()==RubroEvaluacionResultado.EVALUADO){
                                rubroEvaluacionResultadoDao.actualizarResultadodoAncla(capacidadUi.getId(),
                                        capacidadUi.getCalendarioId(),
                                        capacidadUi.getSilaboEventoId(),
                                        ""
                                        , RubroEvaluacionResultado.ACTUALIZADO);
                            }

                            rubroFormula.setTipoNotaId(rubroEvaluacionResultado.getTipoNotaId());

                            List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();
                            if(capacidadUi.getRubroProcesoUis()!=null)
                                for(RubroProcesoUi rubroProcesoUi : capacidadUi.getRubroProcesoUis()){
                                    if(rubroProcesoUi.getTipoFormulaId() == 0){
                                        rubroProcesoUiList.add(rubroProcesoUi);
                                    }
                                }

                            if(!rubroProcesoUiList.isEmpty()){
                                createRubroProcesoFormula(rubroFormula,cargaCursoId, rubroProcesoUiList,databaseWrapper);
                                rubroFormula.setRubrosAsociadosUiList(insertRubrosAsociados(rubroFormula, databaseWrapper));
                                //rubroProcesoDao.actualizarRubroEstado(rubroFormula.getKey());
                                rubroEvaluacionResultado.setRubroEvalProcesoId(rubroFormula.getKey());
                                rubroEvaluacionResultado.setEstadoId(RubroEvaluacionResultado.ANCLADA);
                                rubroEvaluacionResultado.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                                rubroEvaluacionResultado.save();

                                RubroEvaluacionProcesoC newrubroEvaluacionProcesoC = SQLite.select()
                                        .from(RubroEvaluacionProcesoC.class)
                                        .where(RubroEvaluacionProcesoC_Table.key.eq(rubroFormula.getKey()))
                                        .querySingle();

                                if(newrubroEvaluacionProcesoC!=null){
                                    newrubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionResultado.ANCLADA);
                                    newrubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
                                    newrubroEvaluacionProcesoC.save();
                                }


                            }
                        }
                    }
                }
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callBack.onSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.onSuccess(false);
            }
        }).build();

        transaction.execute();
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
    public TipoNotaUi getTipoNotaRubrica(String tipoNotaId) {
        TipoNotaUi tipoNotaUi = null;


        TipoNotaC tipoNotaC = tipoNotaDao.getTipoNotaConValores(tipoNotaId);
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
        return tipoNotaUi;
    }

    private void onUpdateEvaluacionFormulaPersona(RubroProcesoUi rubroProcesoUi, int personaId, TipoNotaC tipoNota, List<ValorTipoNotaC> valorTipoNotaListRubroFinales, EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula) throws Exception{


        NotaUi notaUi = initRubrosFormulaFinal(rubroProcesoUi,new AlumnosUi(String.valueOf(personaId)), tipoNota, valorTipoNotaListRubroFinales,escalaEvaluacionPadre, rubroEvalRNPFormula);

        EvalProcUi evalProcUi = new EvalProcUi();
        evalProcUi.setRubEvalProcId(rubroProcesoUi.getKey());
        evalProcUi.setAlumnoId(personaId);
        evalProcUi.setNota(notaUi.getaDoubleNota());
        evalProcUi.setValorTipoNotaId(notaUi.getValorTipoNotaId());
        evalProcUi.setEscala(notaUi.getEscala());
        Log.d(TAG,"evalProcUi : " + evalProcUi.toString());

        EvaluacionProcesoC evaluacionProceso = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(evalProcUi.getRubEvalProcId()))
                .and(EvaluacionProcesoC_Table.alumnoId.is(evalProcUi.getAlumnoId()))
                .querySingle();

        if(evaluacionProceso == null)evaluacionProceso = new EvaluacionProcesoC();
        evaluacionProceso.setNota(Utils.formatearDecimales(evalProcUi.getNota(), 2));
        evaluacionProceso.setValorTipoNotaId(evalProcUi.getValorTipoNotaId());
        evaluacionProceso.setAlumnoId(evalProcUi.getAlumnoId());
        evaluacionProceso.setRubroEvalProcesoId(evalProcUi.getRubEvalProcId());
        evaluacionProceso.setEscala(evalProcUi.getEscala());
        evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
        evaluacionProceso.setCalendarioPeriodoId(rubroProcesoUi.getCalendarioPeriodId());
        boolean success = evaluacionProceso.save();
        if (!success) throw new Error("Error updating Evaluación processo!!!");

    }

    private NotaUi initRubrosFormulaFinal(RubroProcesoUi rubroProcesoUi, AlumnosUi alumno,  TipoNotaC tipoNota, List<ValorTipoNotaC> valorTipoNotaListRubroFinales, EscalaEvaluacion escalaEvaluacionPadre,   List<RubroEvalRNPFormulaC> rubroEvalRNPFormula) {
        NotaUi notaFinalRubros = new NotaUi();
        List<EvaluacionProcesoC> evaluacionNotasProceso = evaluacionProcesoDao.getListaEvaluacionProcesoRubroFormula(rubroProcesoUi.getKey(), Integer.parseInt(alumno.getKey()));
        notaFinalRubros.setId(String.valueOf(tipoNota.getTipoNotaId()));
        notaFinalRubros.setAlumno(alumno);
        notaFinalRubros.setTipos(1);
        if (escalaEvaluacionPadre == null) return new NotaUi();
        List<NotaUi> notaUiTesteValor = new ArrayList<>();
        double notasFinales = 0;
        Log.d(TAG, "Preview  : " + rubroProcesoUi.getTipoFormulaId());
        if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_MEDIA_PONDERADA) {
            notasFinales = initMediaPonderada(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_MEDIA_PONDERADAFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        } else if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_MEDIA_ARITMETICA) {
            notasFinales = initMediaArimetica(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_MEDIA_ARITMETICAFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        } else if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_SUMA) {
            notasFinales = initFormulaSuma(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_SUMAFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        } else if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_MAXIMO) {
            notasFinales = initFormulaMaximo(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_MAXIMOFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        } else if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_MINIMO) {
            notasFinales = initFormulaMinima(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_MINIMOFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        } else if (rubroProcesoUi.getTipoFormulaId() == TIPO_FORMULA_MODA) {
            notasFinales = initFormulaModa(escalaEvaluacionPadre, rubroEvalRNPFormula, evaluacionNotasProceso);
            Log.d(TAG, "TIPO_FORMULA_MODAFINAL  : "+ notasFinales);
            notaUiTesteValor.add(initTipoNotaValor(valorTipoNotaListRubroFinales, tipoNota.getTipoId(), notasFinales));
        }
        for (NotaUi notaUi : notaUiTesteValor){
            notaFinalRubros.setEscala(notaUi.getEscala());
            notaFinalRubros.setValorTipoNotaId(notaUi.getId());
        }
        notaFinalRubros.setNota(notasFinales);
        notaFinalRubros.setaDoubleNota(notasFinales);
        return notaFinalRubros;
    }

    private NotaUi getRubrosFormulaFinal(RubroProcesoUi rubroProcesoUi, AlumnosUi alumno, TipoNotaC tipoNota, List<EvaluacionProcesoC> evaluacionProcesoCList){
        EvaluacionFormula_RubroEvaluacionUi rubroPrincipal = new EvaluacionFormula_RubroEvaluacionUi(rubroProcesoUi.getKey(), rubroProcesoUi.getTitulo(), rubroProcesoUi.getSubTitulo(), 3);
        EvaluacionProcesoC evaluacionNotasProceso = null;
        for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
            if(evaluacionProcesoC.getRubroEvalProcesoId().equals(rubroProcesoUi.getKey()) &&
                    evaluacionProcesoC.getAlumnoId() == Integer.parseInt(alumno.getKey())){
                evaluacionNotasProceso = evaluacionProcesoC;
                break;
            }
        }

        // = evaluacionProcesoDao.getEvaluacionProceso(rubroProcesoUi.getKey(), Integer.parseInt(alumno.getKey()));

        List<ValorTipoNotaC> valorTipoNotaListRubroFinales = tipoNota.getValorTipoNotaList();

        List<ValorTipoNotaUi> valorTipoNotaUiListRubroFinales = new ArrayList<>();
        EvaluacionFormula_TipoNotaUi tipoNotaUiFinal = null;

        for (ValorTipoNotaC valorTipoNota : valorTipoNotaListRubroFinales) {
            ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
            valorTipoNotaUi.setKey(valorTipoNota.getValorTipoNotaId());
            valorTipoNota.setAlias(valorTipoNota.getAlias());
            valorTipoNota.setTitulo(valorTipoNota.getTitulo());
            valorTipoNota.setLimiteInferior(valorTipoNota.getLimiteInferior());
            valorTipoNota.setLimiteInferior(valorTipoNota.getLimiteSuperior());
            valorTipoNota.setIcono(valorTipoNota.getIcono());
            valorTipoNota.setTipoNotaId(valorTipoNota.getTipoNotaId());
            valorTipoNotaUiListRubroFinales.add(valorTipoNotaUi);
        }

        tipoNotaUiFinal = new EvaluacionFormula_TipoNotaUi(
                String.valueOf(tipoNota.getTipoNotaId()),
                tipoNota.getNombre(),
                tipoNota.getValorDefecto(),
                tipoNota.getValorMinino(),
                tipoNota.getValorMaximo(),
                tipoNota.getLongitudPaso(),
                tipoNota.getTipoId(),
                valorTipoNotaUiListRubroFinales
        );


        NotaUi notaUiFinales = new NotaUi();
        notaUiFinales.setId(String.valueOf(tipoNota.getTipoNotaId()));
        notaUiFinales.setAlumno(alumno);
        notaUiFinales.setRubro(rubroPrincipal);
        notaUiFinales.setTipos(1);
        if(evaluacionNotasProceso!=null){
            //ValorTipoNotaC valorTipoNotaC = valorTipoNotaDao.get(evaluacionNotasProceso.getValorTipoNotaId());
            ValorTipoNotaC valorTipoNotaC = null;

            for (ValorTipoNotaC itemValorTipoNotaC: valorTipoNotaListRubroFinales){
                if(itemValorTipoNotaC.getKey().equals(evaluacionNotasProceso.getValorTipoNotaId())){
                    valorTipoNotaC = itemValorTipoNotaC;
                    break;
                }
            }
            if(valorTipoNotaC != null){
                notaUiFinales.setIcono(valorTipoNotaC.getIcono());
                notaUiFinales.setAlias(valorTipoNotaC.getTitulo());
            }

            notaUiFinales.setValorTipoNotaId(evaluacionNotasProceso.getValorTipoNotaId());
            notaUiFinales.setNota(evaluacionNotasProceso.getNota());
            notaUiFinales.setTipoNota(tipoNotaUiFinal);
        }
        return notaUiFinales;
    }

    private double initFormulaModa(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        Integer integer[];
        double notass = 0;
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }
                int notaRedondeada = (int) Math.round(notaValue);
                //  aDoubleMModa = new Double[]{notaValue};
                integer = new Integer[]{notaRedondeada};
                notass = moda(integer);
            }
        }
        return notass;
    }

    private double initFormulaMinima(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        double minimoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }
                double notaValues = notaValue;
                list.add(notaValues);
                minimoValor = Collections.min(list);
                Log.d(TAG, "SIZEDOUBLE: " + list.size());
                notass = minimoValor;
            }
        }

        return notass;
    }

    private double initFormulaMaximo(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        double maximoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;

                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }
                double notaValuea = notaValue;
                list.add(notaValuea);
                maximoValor = Collections.max(list);
                Log.d(TAG, "SIZEDOUBLE: " + list.size());
                notass = maximoValor;
            }
        }
        return notass;

    }

    private double initFormulaSuma(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        double notass = 0;
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }

                notass += notaValue;
            }
        }
        return notass;
    }

    private double initMediaArimetica(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        double notaAcumulacion = 0;
        double notass = 0;
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }
                notaAcumulacion += notaValue;
                notass = notaAcumulacion / evaluacionNotasProceso.size();
            }
        }
        return notass;
    }

    private NotaUi initTipoNotaValor(List<ValorTipoNotaC> valorTipoNotaListRubroFinales, int tipoId, double notass) {
        NotaUi notaUiFinales = new NotaUi();
        Log.d(TAG, "initTipoNotaValor  : "+ notass);
        switch (tipoId) {
            case SELECTOR_NUMERICO:
                Log.d(TAG, "SELECTOR_NUMERICOFINAL  : ");
                break;
            case SELECTOR_VALORES:
                Log.d(TAG, "SELECTOR_VALORESFINAl  : ");
                for (ValorTipoNotaC valorTipoNota : valorTipoNotaListRubroFinales) {
                    Log.d(TAG, "SELECTOR_ICONOSvASalorTipoNota : " + valorTipoNota.getAlias() + " / SELECTOR_ICONOSvASalorTipoNota : " + valorTipoNota.getIcono());
                    if (valorTipoNota.getLimiteInferior() <= notass && valorTipoNota.getLimiteSuperior() >= notass) {
                        notaUiFinales.setEscala(valorTipoNota.getTitulo());
                        notaUiFinales.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());
                        Log.d(TAG, "SELECTOR_VALORESvASalorTipoNotaFINAl : " + valorTipoNota.getAlias());
                    }
                }
                break;
            case SELECTOR_ICONOS:
                Log.d(TAG, "SELECTOR_ICONOSFINAL  : ");
                String valorId,valorString;
                for (ValorTipoNotaC valorTipoNota : valorTipoNotaListRubroFinales) {
                    Log.d(TAG, "SELECTOR_ICONOSvASalorTipoNota : " + valorTipoNota.getAlias() + " / SELECTOR_ICONOSvASalorTipoNota : " + valorTipoNota.getIcono());
                    Log.d(TAG, "valorTipoNotaFINAL : "+ notass+" / LimiteInferior"+ (valorTipoNota.getLimiteInferior()+  " / LimiteSuperior : "+valorTipoNota.getLimiteSuperior()));
                    if (valorTipoNota.getLimiteInferior() <= notass && valorTipoNota.getLimiteSuperior() >= notass) {
                        valorId = valorTipoNota.getValorTipoNotaId();
                        valorString = valorTipoNota.getIcono();
                        notaUiFinales.setEscala(valorTipoNota.getTitulo());
                        notaUiFinales.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());
                        Log.d(TAG, "SELECTOR_ICONOSFINALTOTAL  : "+valorId+ " /  valorString "+valorString);
                    }
                }
                Log.d(TAG, "SELECTOR_ICONOSFINAL  : ");
                break;
            default:
                break;

        }
        return notaUiFinales;
    }

    private double initMediaPonderada(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormula, List<EvaluacionProcesoC> evaluacionNotasProceso) {
        double notaAcumulacion = 0;
        double notaPesoAcumulacion = 0;
        double pesoTotal = 0;
        double notass = 0;
        for (EvaluacionProcesoC procesoNotas : evaluacionNotasProceso) {
            for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormula) {
                double notaValue = 0;
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
                if (escalaEvaluacionAsociados == null) return 0;
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            procesoNotas.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }
                double pesoActual = rnpFormula.getPeso();

                notaAcumulacion += notaValue;
                notaPesoAcumulacion += notaValue * pesoActual;
                pesoTotal += pesoActual;
                notass = notaPesoAcumulacion / pesoTotal;
                Log.d(TAG, "notaValue: " + notaValue + " / pesoActual: " + pesoActual);
            }
        }
        return notass;
    }

    public static double moda(Integer[] valores) {

        Integer resultado = null;
        Integer contador = 0;
        Integer contadorFinal = 0;


        for (int i = 0; i < valores.length; i++) {

            contador = 0;
            for (int j = 0; j < valores.length; j++) {

                if (Integer.compare(valores[i], valores[j]) == 0)
                    contador++;
            }

            if (contador > contadorFinal) {
                resultado = valores[i];
                contadorFinal = contador;
            }


        }
        Log.d(TAG, "resultado : " + resultado + " repite total de vecves " + contadorFinal);

        return resultado;
    }

    private TipoNotaC tipoNota(String tipoNotaKey, List<TipoNotaC> tipoNotaCList) {
        TipoNotaC tipoNotaC = null;
        long startTime = System.currentTimeMillis();
        for (TipoNotaC itemTipoNotaC : tipoNotaCList){
            if(itemTipoNotaC.getKey().equals(tipoNotaKey)){
                tipoNotaC = itemTipoNotaC;
                break;
            }
        }
        Log.d(TAG, "tipoNota : " + (tipoNotaC == null) + " Time:  " + (System.currentTimeMillis() - startTime));
        return tipoNotaC;
    }

    private List<RubroEvalRNPFormulaC> getListRubroEvalRNPFormula(String rubroFormulaKey) {
        return rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(rubroFormulaKey);
    }

    private List<Persona> getPersonaList(String formulaKey) {
        return SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(Persona_Table.personaId.withTable().eq(EvaluacionProcesoC_Table.alumnoId.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(formulaKey))
                .groupBy(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    private List<EvaluacionProcesoC> getEvaluacionProcesoRubrica(List<String> key, List<Integer> personIdList) {

        return evaluacionProcesoDao.getEvaluacionProcesoRubrica(key, personIdList);
    }

    private EvaluacionProcesoC getEvaluacionProceso(String rubroProcesoKey, int personaKey, List<EvaluacionProcesoC> evaluacionProcesoCList) {
        EvaluacionProcesoC evaluacionNotasProceso = null;
        for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
            if(evaluacionProcesoC.getRubroEvalProcesoId().equals(rubroProcesoKey) && evaluacionProcesoC.getAlumnoId() == personaKey){
                evaluacionNotasProceso = evaluacionProcesoC;
                break;
            }
        }
        return evaluacionNotasProceso;

    }

    private List<ValorTipoNotaC> getTipoNotaCList(String valorTipoNotaKey) {
        List<ValorTipoNotaC> gg = valorTipoNotaDao.getListPorTipoNotaId(valorTipoNotaKey);
        return gg;
    }

    private List<TipoNotaC> getTipoNotaCList(List<String> rubroEvaluacionKeyList) {
        return tipoNotaDao.getListPorRubros(rubroEvaluacionKeyList);
    }

    @Override
    public void getPeriodoList(int cargaCursoId, int cursoId, CallbackPeriodo callbackPeriodo) {
        Log.d(TAG, "getPeriodoList");
        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "cursoId: " + cursoId);
        List<CalendarioPeriodo> calendarioPeriodoList = new ArrayList<>();


                /*CalendarioPeriodoModel.SQLView()
                .select(CalendarioPeriodo_Table.calendarioPeriodoId.withTable(),
                        CalendarioPeriodo_Table.tipoId.withTable(),
                        CalendarioPeriodo_Table.estadoId.withTable())
                .getQuery(cargaCursoId)
                .queryList();*/


        Log.d(TAG, "SIZEPERIODO1 : " + calendarioPeriodoList.size());

        boolean seleccionado = false;
        List<PeriodoUi> list = new ArrayList<>();
        if (calendarioPeriodoList.size() > 0) {
            for (CalendarioPeriodo periodo :
                    calendarioPeriodoList) {
                Log.d(TAG, "COUNT : " + calendarioPeriodoList.size() + " /  periodo : " + periodo.getTipoId());

                Tipos tipo = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(periodo.getTipoId()))
                        .querySingle();
                Log.d(TAG, "PeriodoUi : " + tipo.getTipoId() + "");
                PeriodoUi periodoUi = new PeriodoUi(String.valueOf(periodo.getCalendarioPeriodoId()), tipo.getNombre(), "", false);
                list.add(periodoUi);
                if(periodo.getEstadoId()==CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE){
                    periodoUi.setStatus(true);
                    seleccionado=true;
                    periodoUi.setVigente(true);

                }
            }

            if (!seleccionado && calendarioPeriodoList.size() > 0) {
                PeriodoUi firstPositionList = list.get(0);
                firstPositionList.setStatus(true);
            }
            callbackPeriodo.onListPeriodo(list, REGISTRO_SUCCESS);
        } else {
            callbackPeriodo.onListPeriodo(new ArrayList<PeriodoUi>(), REGISTRO_ERROR);
        }
    }

    @Override
    public void getRubroProcesoList(final String idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid,CallbackResultado callback) {

        Log.d(TAG,"getRubroProcesoList" );
        if (idCalendarioPeriodo == null) return;
        Log.d(TAG, "StringRubroEvaluacionPeriodo: " + idCalendarioPeriodo);
        Log.d(TAG, "StringRubroEvaluacionidcompetencia: " + idcompetencia);
        Log.d(TAG, "intCargaCursoId: " + cargaCursoId);

        List<Object> items = new ArrayList<>();

        int cantidadRubro = 0;

        SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES)))
                .getQuery(cargaCursoId)
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .querySingle();

        List<Competencia> competenciaList = new ArrayList<>();
        if (silaboEvento == null) {
            callback.onRubroProcesoList(new ArrayList<Object>(), 2);
            return;
        }
        Log.d(TAG,"Idcompetencia" + idcompetencia);

        switch (idcompetencia){
            case 0:
                competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silaboEvento.getSilaboEventoId(), Integer.valueOf(idCalendarioPeriodo))
                        .and(Competencia_Table.tipoId.withTable().between(348).and(349))
                        .queryList();
                break;
            case 1:competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(silaboEvento.getSilaboEventoId(), Integer.valueOf(idCalendarioPeriodo))
                    .and(Competencia_Table.tipoId.withTable().eq(347))
                    .queryList();
                break;
            case 3:
                competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silaboEvento.getSilaboEventoId(), Integer.valueOf(idCalendarioPeriodo))
                        .and(Competencia_Table.tipoId.withTable().between(347).and(349))
                        .queryList();
                break;
        }

        List<Integer> competenciaIdList = new ArrayList<>();
        for (Competencia itemCompetencia : competenciaList)competenciaIdList.add(itemCompetencia.getCompetenciaId());


        List<Competencia> capacidadListFull = CapacidadUnidadAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(silaboEvento.getSilaboEventoId(), Integer.valueOf(idCalendarioPeriodo), competenciaIdList)
                .queryList();
        List<Integer> capacidadIdList = new ArrayList<>();
        for (Competencia itemCapacidad : capacidadListFull)capacidadIdList.add(itemCapacidad.getCompetenciaId());

        Where<RubroEvaluacionProcesoC> procesoCWhere = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(Integer.valueOf(idCalendarioPeriodo)))
                .and(RubroEvaluacionProcesoC_Table.competenciaId.in(capacidadIdList))
                .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280));

        if(idcompetencia!=3){
            procesoCWhere.and(RubroEvaluacionProcesoC_Table.rubroFormal.eq(idcompetencia));
        }
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoListfull = procesoCWhere
                .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                .queryList();

        List<String> rubroProcesoEvalaucionIdList = new ArrayList<>();
        List<Integer> desempenioIcdIdList = new ArrayList<>();
        for (RubroEvaluacionProcesoC itemRubroEvaluacionProcesoC : rubroEvaluacionProcesoListfull){
            rubroProcesoEvalaucionIdList.add(itemRubroEvaluacionProcesoC.getKey());
            desempenioIcdIdList.add(itemRubroEvaluacionProcesoC.getDesempenioIcdId());
        }

        List<RubroEvaluacionResultado> rubroEvaluacionResultadoList = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionResultado.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable()))
                .where(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable().in(rubroProcesoEvalaucionIdList))
                .queryList();


        List<IndicadorQuery> icds = SQLite.select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(Icds_Table.icdId,
                Icds_Table.desempenioId,
                Icds_Table.descripcion,
                Icds_Table.titulo,
                Icds_Table.alias,
                Icds_Table.descripcion,
                Icds_Table.estado,
                Icds_Table.peso,
                DesempenioIcd_Table.desempenioIcdId,
                DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                DesempenioIcd_Table.tipoId)))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.withTable().in(desempenioIcdIdList))
                .groupBy(Icds_Table.icdId,
                        Icds_Table.desempenioId,
                        Icds_Table.descripcion,
                        Icds_Table.titulo,
                        Icds_Table.alias,
                        Icds_Table.descripcion,
                        Icds_Table.estado,
                        Icds_Table.peso,
                        DesempenioIcd_Table.desempenioIcdId,
                        DesempenioIcd_Table.descripcion,
                        DesempenioIcd_Table.tipoId)
                .queryCustomList(IndicadorQuery.class);

        List<CampoTematicoRubroQuery> campotematicos = SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES,
                RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable() ))
                .from(CampoTematico.class)
                .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES,
                        RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable()))
                .queryCustomList(CampoTematicoRubroQuery.class);

        List<RubroEvalRNPFormulaQuery> rubricaList = SQLite.select(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable() ))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable()))
                .queryCustomList(RubroEvalRNPFormulaQuery.class);


        List<RubroEvalRNPFormulaQuery> rnpFormulaCList =  SQLite.select(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable() ))
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().in(rubroProcesoEvalaucionIdList))
                .groupBy(Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES,RubroEvaluacionProcesoC_Table.titulo.withTable()))
                .queryCustomList(RubroEvalRNPFormulaQuery.class);


        ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
        Log.d(TAG, "competenciaList: " + competenciaList);
        Log.d(TAG, "rubroEvaluacionProcesoListfull: " + rubroEvaluacionProcesoListfull.size());

        int posicionCompetencia = 0;
        for (Competencia itemCompetencia : competenciaList) {
            posicionCompetencia++;
            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(itemCompetencia.getCompetenciaId());
            competenciaUi.setPosicion(posicionCompetencia);
            competenciaUi.setTitulo(itemCompetencia.getNombre());
            competenciaUi.setNivel(3);
            if (parametrosDisenio==null)return;
            ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
            parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
            competenciaUi.setParametroDisenioUi(parametroDisenioUi);

            items.add(competenciaUi);

            List<Competencia> capacidadList = new ArrayList<>();
            for (Competencia itemCapacidad : capacidadListFull){
                if(itemCapacidad.getSuperCompetenciaId()==itemCompetencia.getCompetenciaId()){
                    capacidadList.add(itemCapacidad);
                }
            }

            for (Competencia itemCapacidad : capacidadList) {

                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setToogle(itemCapacidad.isToogle());
                capacidadUi.setCompetenciaUi(competenciaUi);
                capacidadUi.setId(itemCapacidad.getCompetenciaId());
                capacidadUi.setTitle(itemCapacidad.getNombre());
                capacidadUi.setBtnSelect(true);
                capacidadUi.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                capacidadUi.setNivel(3);
                capacidadUi.setCalendarioId(Integer.valueOf(idCalendarioPeriodo));
                capacidadUi.setTipoFormulaUi(new TipoFormulaUi());
                capacidadUi.setTipoEscalaEvaluacionUi(new TipoEscalaEvaluacionUi());
                capacidadUi.setTipoRedondeadoUi(new TipoRedondeadoUi());
                capacidadUi.setTipoEvaluacionUi(new TipoEvaluacionUi());
                capacidadUi.setTipoNotaUi(new TipoNotaUi());
                List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();
                List<RubroProcesoUi> rubroEvaluacionProcesoDetalles = new ArrayList<>();

                List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new ArrayList<>();
                for (RubroEvaluacionProcesoC itemRubroProceso : rubroEvaluacionProcesoListfull){
                    if(itemRubroProceso.getCompetenciaId() == itemCapacidad.getCompetenciaId()){
                        rubroEvaluacionProcesoCList.add(itemRubroProceso);
                    }
                }
                capacidadUi.setCantidad(rubroEvaluacionProcesoCList.size());

                int posicionRubros = rubroEvaluacionProcesoCList.size();
                for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoCList) {
                    RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();
                    rubroProcesoUi.setKey(itemRubroEvaluacionProceso.getKey());
                    rubroProcesoUi.setDesempenioIcdId(itemRubroEvaluacionProceso.getDesempenioIcdId());
                    rubroProcesoUi.setPosicion(posicionRubros);
                    rubroProcesoUi.setFecha(Utils.f_fecha_letras(itemRubroEvaluacionProceso.getFechaCreacion()));
                    rubroProcesoUi.setTitulo(itemRubroEvaluacionProceso.getTitulo());
                    rubroProcesoUi.setExportado(itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                            itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                            itemRubroEvaluacionProceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                    rubroProcesoUi.setSubTitulo(itemRubroEvaluacionProceso.getSubtitulo());
                    rubroProcesoUi.setColorRubro(itemRubroEvaluacionProceso.getTipoColorRubroProceso());
                    rubroProcesoUi.setMedia(0.0);
                    rubroProcesoUi.setDesviacionE(0.0);
                    rubroProcesoUi.setTipoFormulaId(itemRubroEvaluacionProceso.getTipoFormulaId());
                    rubroProcesoUi.setSesionAprendizajeId(itemRubroEvaluacionProceso.getSesionAprendizajeId());
                    rubroProcesoUi.setTipoNotaId(itemRubroEvaluacionProceso.getTipoNotaId());
                    posicionRubros--;

                    RubroEvaluacionResultado rubroEvaluacionResultado = null;
                    for(RubroEvaluacionResultado itemRubroEvaluacionResultado : rubroEvaluacionResultadoList){
                        if(itemRubroEvaluacionResultado.getRubroEvalProcesoId().equals(itemRubroEvaluacionProceso.getKey())){
                            rubroEvaluacionResultado = itemRubroEvaluacionResultado;
                            break;
                        }
                    }

                    if (rubroEvaluacionResultado != null) {

                        if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.ANCLADA) {
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.ANCLADA);
                            rubroProcesoUi.setTipoAncla(true);
                            capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                        } else if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.EVALUADO) {
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.EVALUADA);
                            rubroProcesoUi.setTipoAncla(true);
                            capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                        } else {
                            rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.DEFECTO);
                        }

                        // rubroProcesoUi.setTipoAncla(true);

                    }

                    List<RubrosAsociadosUi> rubrosAsociadosUis = new ArrayList<>();
                    int count = 0;
                    for(RubroEvalRNPFormulaQuery  rubroEvalRNPFormulaC : rnpFormulaCList){
                        if(itemRubroEvaluacionProceso.getKey().equals(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId())){
                            String rubroProcesoPrincipal = String.valueOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                            String rubroProcesoSencundario = String.valueOf(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());

                            RubrosAsociadosUi rubrosAsociadosUi =  new RubrosAsociadosUi(count,
                                    null,
                                    rubroProcesoPrincipal,
                                    rubroProcesoSencundario);
                            rubrosAsociadosUi.setNombreRubroAsociado(rubroEvalRNPFormulaC.getTitulo());
                            rubrosAsociadosUis.add(rubrosAsociadosUi);
                        }
                    }

                    switch (itemRubroEvaluacionProceso.getFormaEvaluacionId()) {
                        case 477:
                            rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                            break;
                        case 478:
                            rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                            break;
                    }

                    if (itemRubroEvaluacionProceso.getTipoFormulaId() > 0) {
                        rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
                        int cantidadIdicadores = rubroProcesoDao.cantidadIndicadoresFormula(rubroProcesoUi.getKey());
                        switch (cantidadIdicadores){
                            case 0:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.ANARANJADO);
                                break;
                            case 1:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.PLOMO);
                                break;
                            default:
                                rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.NEGRO);
                                break;
                        }
                    }


                    rubroProcesoUi.setRubrosAsociadosUiList(rubrosAsociadosUis);
                    if (rubrosAsociadosUis.size() > 0) {
                        rubroEvaluacionProcesoDetalles.add(rubroProcesoUi);
                    }
                    Log.d(TAG, "PROMEDIO "+ itemRubroEvaluacionProceso.getPromedio());
                    Log.d(TAG, "DV "+ itemRubroEvaluacionProceso.getPromedio());
                    rubroProcesoUi.setMedia(itemRubroEvaluacionProceso.getPromedio());
                    rubroProcesoUi.setDesviacionE(itemRubroEvaluacionProceso.getDesviacionEstandar());

                    T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                            .from(T_RN_MAE_TIPO_EVALUACION.class)
                            .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(itemRubroEvaluacionProceso.getTipoEvaluacionId()))
                            .querySingle();
                    if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());

                    switch (itemRubroEvaluacionProceso.getTiporubroid()) {
                        case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                            break;
                        case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL);
                            break;
                        case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                            rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE);
                            RubroEvalRNPFormulaQuery rubrica = null;
                            for(RubroEvalRNPFormulaQuery itemRubrica : rubricaList){
                                if(itemRubroEvaluacionProceso.getKey().equals(itemRubrica.getRubroEvaluacionSecId())){
                                    rubrica = itemRubrica;
                                    break;
                                }
                            }
                            if(rubrica!=null)rubroProcesoUi.setTituloRubrica(rubrica.getTitulo());
                            break;
                    }
                    if (itemRubroEvaluacionProceso.getSesionAprendizajeId()!=0){//Validar lo de Sesion
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
                    }else {
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
                    }

                    if(!TextUtils.isEmpty(itemRubroEvaluacionProceso.getTareaId())){
                        rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.TAREA);
                    }

                    rubroProcesoUi.setDisabledEval(isDiasbleEvaluacionSilabo(itemRubroEvaluacionProceso.getTiporubroid(), itemRubroEvaluacionProceso.getCalendarioPeriodoId()));


                    IndicadorQuery indicadorQuery = null;
                    for (IndicadorQuery itemIndicadorQuery: icds){
                        if(itemIndicadorQuery.getDesempenioIcdId()== itemRubroEvaluacionProceso.getDesempenioIcdId()){
                            indicadorQuery = itemIndicadorQuery;
                            break;
                        }
                    }
                    IndicadoresCamposAccionUi indicadoresUi = new IndicadoresCamposAccionUi();
                    if(indicadorQuery!=null){
                        indicadoresUi.setId(indicadorQuery.getIcdId());
                        indicadoresUi.setDescripcion(indicadorQuery.getDescripcion());
                        indicadoresUi.setTitulo(indicadorQuery.getTitulo());
                        indicadoresUi.setAlias(indicadorQuery.getAlias());
                        indicadoresUi.setPeso(indicadorQuery.getPeso());
                        indicadoresUi.setEstado(indicadorQuery.getEstado());

                        boolean isBase=false;
                        boolean isEnfoque=false;
                        boolean isTransversal=false;

                        switch (itemCompetencia.getTipoId()){
                            case Competencia.COMPETENCIA_BASE:
                                isBase =  true;
                                break;
                            case Competencia.COMPETENCIA_ENFQ:
                                isEnfoque = true;
                                break;
                            case Competencia.COMPETENCIA_TRANS:
                                isTransversal = true;
                                break;
                        }


                        if(isBase){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.BASE);
                        }

                        if(isEnfoque){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.ENFOQUE);
                        }

                        if(isTransversal){
                            indicadoresUi.setTipoCompetencia(IndicadoresCamposAccionUi.TipoCompetencia.TRANSVERSAL);
                        }

                        switch (indicadorQuery.getTipoId()){
                            case Icds.TIPO_HACER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.HACER);
                                break;
                            case Icds.TIPO_SABER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SABER);
                                break;
                            case Icds.TIPO_SER:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.SER);
                                break;
                            default:
                                indicadoresUi.setTipoIndicador(IndicadoresCamposAccionUi.TipoIndicador.DEFAULT);
                                break;
                        }

                        Log.d(TAG, "NombreIndicador : " + indicadorQuery.getTitulo() + " " + indicadorQuery.getTipoId());
                    }

                    List<CampoTematicoRubroQuery> campoTematicoRubroQueryList = new ArrayList<>();
                    for (CampoTematicoRubroQuery itemCampoTematicoRubroQuery : campotematicos){
                        if(itemCampoTematicoRubroQuery.getRubroEvalProcesoId().equals(itemRubroEvaluacionProceso.getKey())){
                            campoTematicoRubroQueryList.add(itemCampoTematicoRubroQuery);
                        }
                    }
                    indicadoresUi.setCampoAccionList(getListCampoAccionSilabo(campoTematicoRubroQueryList));
                    rubroProcesoUi.setIndicadoresCamposAccionUi(indicadoresUi);
                    rubroProcesoUiList.add(rubroProcesoUi);
                }


                for (RubroProcesoUi rubroEvaluacionProcesoUi : rubroEvaluacionProcesoDetalles) {

                    Log.d(TAG, "rubroEvaluacionProcesoDetalles " + rubroEvaluacionProcesoDetalles.size());
                    //3 -> Si tiene lista de RubrosAsociados
                    for (RubrosAsociadosUi rubrosAsociadosUi : rubroEvaluacionProcesoUi.getRubrosAsociadosUiList()) {
                        Log.d(TAG, "rubroEvaluacionProcesoUi " + rubrosAsociadosUi.getNumeroRubroAsociado());

                        RubroProcesoUi rubroEvaluacionProcesoss = new RubroProcesoUi();
                        // rubroEvaluacionProcesoss.setId(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                        rubroEvaluacionProcesoss.setKey(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                        int position = rubroProcesoUiList.indexOf(rubroEvaluacionProcesoss);
                        Log.d(TAG, "Position : " + position);
                        if (position != -1) {
                            RubroProcesoUi procesoUi = rubroProcesoUiList.get(position);
                            rubrosAsociadosUi.setNumeroRubroAsociado(procesoUi.getPosicion());
                        }

                    }
                }

                capacidadUi.setRubroProcesoUis(rubroProcesoUiList);
                if(capacidadUi.getRubroProcesoUis()!= null)cantidadRubro += capacidadUi.getRubroProcesoUis().size();
                items.add(capacidadUi);
                competenciaUi.addCapacidad(capacidadUi);
            }
        }

        int status;
        if(cantidadRubro > 0){
            status = REGISTRO_SUCCESS;
        }else {
            status = REGISTRO_RUBROSVASIO;
        }

        Log.d(TAG, "items"+ items);
        callback.onRubroProcesoList(items, status);

    }

    @Override
    public void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback) {
        Log.d(TAG, "deleteRubroEvaluacionProceso");

        // Log.d(TAG, "rubroProcesoUiID" + rubroProcesoUi.getId() + " / Android : " + rubroProcesoUi.getAndroidId());
       /* if (rubroProcesoUi.getRubrosAsociadosUiList() == null || rubroProcesoUi.getRubrosAsociadosUiList().isEmpty()) {
            Log.d(TAG, "NULLL: ");
            List<RubrosAsociados> rubroEvaluacionProcesos = SQLite.select()
                    .from(RubrosAsociados.class)
                    .innerJoin(RubroEvaluacionProceso.class)
                    .on(RubrosAsociados_Table.idRubroEvaluacionProcesoSecundario.withTable().eq(RubroEvaluacionProceso_Table.androidId.withTable()))
                    .where(RubroEvaluacionProceso_Table.androidId.withTable().is(rubroProcesoUi.getId()))
                    .queryList();

            if (rubroEvaluacionProcesos.size() > 0) {
                for (int i = 0; i < rubroEvaluacionProcesos.size(); i++) {
                    RubrosAsociados rubrosAsociados = rubroEvaluacionProcesos.get(i);
                    RubroEvaluacionProceso rubroEvaluacionProceso = SQLite.select()
                            .from(RubroEvaluacionProceso.class)
                            .where(RubroEvaluacionProceso_Table.androidId.is(rubrosAsociados.getIdRubroEvaluacionProcesoSecundario()))
                            .querySingle();
                    objectCallback.onDelete(rubroProcesoUi, REGISTRO_MESSAGE);
                    Log.d(TAG, "PERTENECEAUNRUBRO" + rubroEvaluacionProceso.getTitulo());
                }
            } else {
                Log.d(TAG, "SinRubrosAsociados");
                SQLite.update(RubroEvaluacionProceso.class)
                        .set(RubroEvaluacionProceso_Table.estadoId.is(280))
                        .where(RubroEvaluacionProceso_Table.androidId.is(rubroProcesoUi.getId()))
                        .async()
                        .execute();

                objectCallback.onDelete(rubroProcesoUi, REGISTRO_SUCCESS);
            }


        } else if (rubroProcesoUi.getRubrosAsociadosUiList().size() > 0) {

            List<RubrosAsociados> rubroEvaluacionProcesos = SQLite.select()
                    .from(RubrosAsociados.class)
                    .innerJoin(RubroEvaluacionProceso.class)
                    .on(RubrosAsociados_Table.idRubroEvaluacionProcesoPrincipal.withTable().eq(RubroEvaluacionProceso_Table.androidId.withTable()))
                    .where(RubroEvaluacionProceso_Table.androidId.withTable().is(rubroProcesoUi.getId()))
                    .queryList();
            if (rubroEvaluacionProcesos.size() > 0) {
                Log.d(TAG, "rubroEvaluacionProcesos.size() " + rubroEvaluacionProcesos.size());

                for (int i = 0; i < rubroEvaluacionProcesos.size(); i++) {
                    rubroEvaluacionProcesos.get(i);
                    List<RubrosAsociados> rubrosAsociados = SQLite.select()
                            .from(RubrosAsociados.class)
                            .innerJoin(RubroEvaluacionProceso.class)
                            .on(RubrosAsociados_Table.idRubroEvaluacionProcesoSecundario.withTable().eq(RubroEvaluacionProceso_Table.androidId.withTable()))
                            .where(RubroEvaluacionProceso_Table.androidId.withTable().is(rubroProcesoUi.getId()))
                            .queryList();

                    if (rubrosAsociados.size() > 0) {
                        for (int j = 0; j < rubrosAsociados.size(); j++) {
                            RubrosAsociados rubrosAsociados2 = rubroEvaluacionProcesos.get(j);
                            RubroEvaluacionProceso rubroEvaluacionProceso = SQLite.select()
                                    .from(RubroEvaluacionProceso.class)
                                    .where(RubroEvaluacionProceso_Table.androidId.is(rubrosAsociados2.getIdRubroEvaluacionProcesoSecundario()))
                                    .querySingle();

                            objectCallback.onDelete(rubroProcesoUi, REGISTRO_MESSAGE);
                            Log.d(TAG, "PETENECEAUNRUBROFORMULA" + rubroEvaluacionProceso.getTitulo());
                            break;
                        }
                    } else {
                        Log.d(TAG, "SinRubrosAsociadosFORMULA");
                        boolean deleteRubrosAsociados = rubroEvaluacionProcesos.get(i).delete();
                        if (deleteRubrosAsociados) {
                            SQLite.update(RubroEvaluacionProceso.class)
                                    .set(RubroEvaluacionProceso_Table.estadoId.is(280))
                                    .where(RubroEvaluacionProceso_Table.androidId.is(rubroProcesoUi.getId()))
                                    .async()
                                    .execute();
                            Log.d(TAG, "eliminador Correctamente : " + deleteRubrosAsociados);
                            objectCallback.onDelete(rubroProcesoUi, REGISTRO_SUCCESS);
                        } else {
                            objectCallback.onDelete(rubroProcesoUi, REGISTRO_ERROR);
                            break;
                        }
                    }

                }
            }
        }*/


    }

    @Override
    public void useCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {
        /*Anclado 313*/
        int ancladao = 313;

        TipoNotaC tipoNotaC = SQLite.select(Utils.f_allcolumnTable(TipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                .from(TipoNotaC.class)
                .innerJoin(RubroEvaluacionResultado.class)
                .on(RubroEvaluacionResultado_Table.tipoNotaId.withTable()
                        .eq(TipoNotaC_Table.tipoNotaId.withTable()))
                .where(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(capacidadUi.getId()))
                .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(capacidadUi.getSilaboEventoId()))
                .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(capacidadUi.getCalendarioId()))
                .querySingle();

        if(tipoNotaC==null){
            objetoCallback.onError("No existe el nivel de logro asignado al Rubro Resultado");
            return;
        }

        if(!tipoNotaC.getTipoNotaId().equals(rubroProcesoUi.getTipoNotaId())){
            objetoCallback.onError("El nivel de logro es diferente al Rubro Resultado");
            return;
        }

        rubroEvaluacionResultadoDao.actualizarResultadodoAncla(capacidadUi.getId(),
                capacidadUi.getCalendarioId(),
                capacidadUi.getSilaboEventoId(),
                rubroProcesoUi.getKey()
                , RubroEvaluacionResultado.ANCLADA);


        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionResultado.ANCLADA);
            rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
            rubroEvaluacionProcesoC.save();
        }


        //rubroProcesoDao.actualizarRubroEstado(rubroProcesoUi.getKey());
        rubroProcesoUi.setTipoAncla(true);
        rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.ANCLADA);
        capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
        objetoCallback.onObject(capacidadUi, rubroProcesoUi);
    }

    @Override
    public void desanclarCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {

        rubroEvaluacionResultadoDao.actualizarResultadodoAncla(capacidadUi.getId(),
                capacidadUi.getCalendarioId(),
                capacidadUi.getSilaboEventoId(),
                ""
                , RubroEvaluacionResultado.ACTUALIZADO);

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionResultado.ANCLADA);
            rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }


        //rubroProcesoDao.actualizarRubroEstado(rubroProcesoUi.getKey());
        rubroProcesoUi.setTipoAncla(false);
        rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.DEFECTO);
        capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
        objetoCallback.onObject(capacidadUi, rubroProcesoUi);
    }


    private List<RubroProcesoUi> getRubroEvaluacionProcesoSilabo(int idcompetencia, Competencia itemCapacidad, CapacidadUi capacidadUi, int idCalendarioPeriodo, int silavoEventoId) {

        Log.d(TAG, "idcompetencia: " +  idcompetencia);
        List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList= new ArrayList<>();
        if(idcompetencia!=3){

            rubroEvaluacionProcesoList = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(idCalendarioPeriodo))
                    .and(RubroEvaluacionProcesoC_Table.competenciaId.is(itemCapacidad.getCompetenciaId()))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silavoEventoId))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                    .and(RubroEvaluacionProcesoC_Table.rubroFormal.eq(idcompetencia))
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                    .queryList();

        }else{
            rubroEvaluacionProcesoList= SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.is(idCalendarioPeriodo))
                    .and(RubroEvaluacionProcesoC_Table.competenciaId.is(itemCapacidad.getCompetenciaId()))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.is(silavoEventoId))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc())
                    .queryList();
        }
        capacidadUi.setCantidad(rubroEvaluacionProcesoList.size());
        int posicionRubros = rubroEvaluacionProcesoList.size();
        Log.d(TAG, "positionrubros: " +  posicionRubros);

        List<RubroProcesoUi> rubroEvaluacionProcesoDetalles = new ArrayList<>();

        //  if (tipoAncla == null) return new ArrayList<>();
        Log.d(TAG, "tipoAncla : " + itemCapacidad.getCompetenciaId() + " CalendarioPeriodoUi : " + idCalendarioPeriodo + "SilaboEvento : " + silavoEventoId);

        for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoList) {
            RubroProcesoUi rubroProcesoUi = new RubroProcesoUi();
            rubroProcesoUi.setKey(itemRubroEvaluacionProceso.getKey());
            rubroProcesoUi.setDesempenioIcdId(itemRubroEvaluacionProceso.getDesempenioIcdId());
            rubroProcesoUi.setPosicion(posicionRubros);
            rubroProcesoUi.setFecha(Utils.f_fecha_letras(itemRubroEvaluacionProceso.getFechaCreacion()));
            rubroProcesoUi.setTitulo(itemRubroEvaluacionProceso.getTitulo());
            rubroProcesoUi.setSubTitulo(itemRubroEvaluacionProceso.getSubtitulo());
            rubroProcesoUi.setColorRubro(itemRubroEvaluacionProceso.getTipoColorRubroProceso());
            rubroProcesoUi.setMedia(0.0);
            rubroProcesoUi.setDesviacionE(0.0);
            rubroProcesoUi.setTipoFormulaId(itemRubroEvaluacionProceso.getTipoFormulaId());
            rubroProcesoUi.setSesionAprendizajeId(itemRubroEvaluacionProceso.getSesionAprendizajeId());
            rubroProcesoUi.setTipoNotaId(itemRubroEvaluacionProceso.getTipoNotaId());
            posicionRubros--;

            RubroEvaluacionResultado rubroEvaluacionResultado = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable()))
                    .where(RubroEvaluacionResultado_Table.rubroEvalProcesoId.withTable().is(itemRubroEvaluacionProceso.getKey()))
                    .querySingle();


            if (rubroEvaluacionResultado != null) {

                if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.ANCLADA) {
                    rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.ANCLADA);
                    rubroProcesoUi.setTipoAncla(true);
                    capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                } else if (rubroEvaluacionResultado.getEstadoId() == RubroEvaluacionResultado.EVALUADO) {
                    rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.EVALUADA);
                    rubroProcesoUi.setTipoAncla(true);
                    capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                } else {
                    rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.DEFECTO);
                }

                // rubroProcesoUi.setTipoAncla(true);

            }

            capacidadUi.addRubroProceso(rubroProcesoUi);


            List<RubrosAsociadosUi> rubrosAsociadosUis = getListRubrosAsociadosSilabo(itemRubroEvaluacionProceso, posicionRubros);

            switch (itemRubroEvaluacionProceso.getFormaEvaluacionId()) {
                case 477:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                    break;
                case 478:
                    rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                    break;
            }


            if (itemRubroEvaluacionProceso.getTipoFormulaId() > 0) {
                rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
                int cantidadIdicadores = rubroProcesoDao.cantidadIndicadoresFormula(rubroProcesoUi.getKey());
                switch (cantidadIdicadores){
                    case 0:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.ANARANJADO);
                        break;
                    case 1:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.PLOMO);
                        break;
                    default:
                        rubroProcesoUi.setEstiloFormula(RubroProcesoUi.EstiloFormula.NEGRO);
                        break;
                }
            }


            rubroProcesoUi.setRubrosAsociadosUiList(rubrosAsociadosUis);
            rubroProcesoUiList.add(rubroProcesoUi);
            if (rubrosAsociadosUis.size() > 0) {
                rubroEvaluacionProcesoDetalles.add(rubroProcesoUi);
            }

            rubroProcesoUi.setMedia(itemRubroEvaluacionProceso.getPromedio());
            rubroProcesoUi.setDesviacionE(itemRubroEvaluacionProceso.getDesviacionEstandar());
            T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                    .from(T_RN_MAE_TIPO_EVALUACION.class)
                    .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(itemRubroEvaluacionProceso.getTipoEvaluacionId()))
                    .querySingle();
            if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());

            switch (itemRubroEvaluacionProceso.getTiporubroid()) {
                case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.UNIDIMENCIONAL);
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL);
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                    rubroProcesoUi.setTipo(RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE);
                    try {
                        RubroEvaluacionProcesoC rubrica = SQLite.select(RubroEvaluacionProcesoC_Table.titulo.withTable())
                                .from(RubroEvaluacionProcesoC.class)
                                .innerJoin(RubroEvalRNPFormulaC.class)
                                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                        .eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()))
                                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(itemRubroEvaluacionProceso.getKey()))
                                .querySingle();


                        rubroProcesoUi.setTituloRubrica(rubrica.getTitulo());
                    }catch (Exception ignored){}
                    break;
            }


            if (itemRubroEvaluacionProceso.getSesionAprendizajeId()!=0){//Validar lo de Sesion
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
            }else {
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
            }

            if(TextUtils.isEmpty(itemRubroEvaluacionProceso.getTareaId())){
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.TAREA);
            }

            rubroProcesoUi.setDisabledEval(isDiasbleEvaluacionSilabo(itemRubroEvaluacionProceso.getTiporubroid(), itemRubroEvaluacionProceso.getCalendarioPeriodoId()));


        }

        for (RubroProcesoUi rubroEvaluacionProcesoUi : rubroEvaluacionProcesoDetalles) {

            Log.d(TAG, "rubroEvaluacionProcesoDetalles " + rubroEvaluacionProcesoDetalles.size());
            //3 -> Si tiene lista de RubrosAsociados
            for (RubrosAsociadosUi rubrosAsociadosUi : rubroEvaluacionProcesoUi.getRubrosAsociadosUiList()) {
                Log.d(TAG, "rubroEvaluacionProcesoUi " + rubrosAsociadosUi.getNumeroRubroAsociado());

                RubroProcesoUi rubroEvaluacionProcesoss = new RubroProcesoUi();
                // rubroEvaluacionProcesoss.setId(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                rubroEvaluacionProcesoss.setKey(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                int position = rubroProcesoUiList.indexOf(rubroEvaluacionProcesoss);
                Log.d(TAG, "Position : " + position);
                if (position != -1) {
                    RubroProcesoUi procesoUi = rubroProcesoUiList.get(position);
                    rubrosAsociadosUi.setNumeroRubroAsociado(procesoUi.getPosicion());
                }

            }
        }
        return rubroProcesoUiList;

    }


    private List<RubrosAsociadosUi> getListRubrosAsociadosSilabo(RubroEvaluacionProcesoC rubroEvaluacionProceso, int countRubro) {
        List<RubrosAsociadosUi> rubrosAsociadosUis = new ArrayList<>();
        Log.d(TAG, "rubrosAsociadosUis");
        /*List<RubrosAsociados> rubrosAsociadosList = SQLite.select()
                .from(RubrosAsociados.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubrosAsociados_Table.idRubroEvaluacionProcesoPrincipal.withTable().eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                .where(RubrosAsociados_Table.idRubroEvaluacionProcesoPrincipal.withTable().is(rubroEvaluacionProceso.getAndroidId()))
                .queryList();*/

        List<RubroEvalRNPFormulaC> rnpFormulaCList = rnpFormulaDao.getListaRubroEvalRNPFormula(rubroEvaluacionProceso.getKey());
        int count = 0;
        for (int j = 0; j < rnpFormulaCList.size(); j++) {
            Log.d(TAG, "rnpFormulaCList");
            Log.d(TAG, "rubrosAsociadosList :" + rnpFormulaCList.get(j).getRubroEvaluacionPrimId() +
                    "rubroEvaluacionProcesoUi " + rubroEvaluacionProceso.getRubroEvalProcesoId());
            Log.d(TAG, "CountRubro " + countRubro);
            RubroEvalRNPFormulaC rubrosAsociados = rnpFormulaCList.get(j);
            String rubroProcesoPrincipal = String.valueOf(rubrosAsociados.getRubroEvaluacionPrimId());
            String rubroProcesoSencundario = String.valueOf(rubrosAsociados.getRubroEvaluacionSecId());
            RubroEvaluacionProcesoC rubroAsociado = rubroProcesoDao.get(rubroProcesoSencundario);
            RubrosAsociadosUi rubrosAsociadosUi =  new RubrosAsociadosUi(count,
                    null,
                    rubroProcesoPrincipal,
                    rubroProcesoSencundario);
            if(rubroAsociado!=null)rubrosAsociadosUi.setNombreRubroAsociado(rubroAsociado.getTitulo());


            rubrosAsociadosUis.add(rubrosAsociadosUi);
        }


        return rubrosAsociadosUis;
    }

    private boolean isDiasbleEvaluacionSilabo(int tipoRubroId, int calendarioPeriodoId) {
        boolean diabledEval;

        CalendarioPeriodo calendarioPeriodo = SQLite.select(CalendarioPeriodo_Table.estadoId.withTable())
                .from(CalendarioPeriodo.class)
                .where(CalendarioPeriodo_Table.calendarioPeriodoId.is(calendarioPeriodoId))
                .querySingle();

        if (calendarioPeriodo == null) {
            Log.e(TAG, "RubroEvaluacion sin CalendarioPeriodoUi Asociado");
            return true;
        }
        switch (calendarioPeriodo.getEstadoId()) {
            case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                diabledEval = true;
                break;
            case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                diabledEval = true;
                break;
            case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                diabledEval = false;
                break;
            default:
                diabledEval = true;
                break;
        }


        switch (tipoRubroId) {
            case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                diabledEval = true;
                break;
            case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                diabledEval = true;
                break;
        }

        return diabledEval;
    }

    private List<CamposAccionUi> getListCampoAccionSilabo(List<CampoTematicoRubroQuery> campoTematicoList) {
        List<CamposAccionUi> campotematicoUipadresList = new ArrayList<>();

        for (CampoTematicoRubroQuery itemCampoTematico : campoTematicoList) {
            CamposAccionUi campotematicoUi = new CamposAccionUi();
            campotematicoUi.setKey(String.valueOf(itemCampoTematico.getCampoTematicoId()));

            CampoTematico campoTematicoPadre = SQLite.select()
                    .from(CampoTematico.class)
                    .where(CampoTematico_Table.parentId.withTable().is(0))
                    .and(CampoTematico_Table.campoTematicoId.is(itemCampoTematico.getParentId()))
                    .querySingle();

            if (campoTematicoPadre != null) {
                CamposAccionUi campotematicoUiPadre = new CamposAccionUi();
                campotematicoUiPadre.setKey(String.valueOf(campoTematicoPadre.getCampoTematicoId()));
                campotematicoUiPadre.setTipo(CamposAccionUi.Tipo.PARENT);
                int posicionPadre = campotematicoUipadresList.indexOf(campotematicoUiPadre);
                if (posicionPadre == -1) {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }
                campotematicoUi.setBinieta("- ");
                campotematicoUi.setTipo(CamposAccionUi.Tipo.CHILDREN);
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampoAccion(campotematicoUi);

            } else {
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                campotematicoUi.setTipo(CamposAccionUi.Tipo.DEFAULT);
                campotematicoUipadresList.add(campotematicoUi);

            }
        }
        int count = 0;
        List<CamposAccionUi> campotematicoUiList = new ArrayList<>();
        for (CamposAccionUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            count ++;
            String binieta = count + ". ";
            itemCampotematicoUiPadre.setBinieta(binieta);
            campotematicoUiList.add(itemCampotematicoUiPadre);
            if ( itemCampotematicoUiPadre.getCampoAccionUiList() == null) continue;
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampoAccionUiList());
        }

        return campotematicoUiList;
    }

}
