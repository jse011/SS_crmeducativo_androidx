package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.local;

import android.text.TextUtils;
import android.util.Log;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.SilaboEventoCargaCursoModel;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.OrigenUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.RubricaBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaBidLocal implements RubricaBidDataSource {

    private static final String TAG = RubricaBidLocal.class.getSimpleName();
    private static String RUBRICA_BID_LOCAL_TAG = RubricaBidLocal.class.getSimpleName();
    RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;
    CalendarioPeriodoDao calendarioPeriodoDao;

    public RubricaBidLocal(RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao,  CalendarioPeriodoDao calendarioPeriodoDao) {
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
        this.calendarioPeriodoDao = calendarioPeriodoDao;
    }
    @Override
    public void getPeriodoList(int periodoId, int cargaCursoid,CallBackList callBackList) {
        Log.d(RUBRICA_BID_LOCAL_TAG, "getPeriodoList");
        Log.d(RUBRICA_BID_LOCAL_TAG, "cargaCursoid: " + cargaCursoid);
        Log.d(RUBRICA_BID_LOCAL_TAG, "cursoId: " + periodoId);
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<CalendarioPeriodo> calendarioPeriodoList = new ArrayList<>();

            Log.d(RUBRICA_BID_LOCAL_TAG, "SIZEPERIODO1 : " + calendarioPeriodoList.size());

            List<PeriodoUi> list = new ArrayList<>();
            boolean seleccionado = false;
            for (CalendarioPeriodo itemPeriodo : calendarioPeriodoList) {
                Log.d(RUBRICA_BID_LOCAL_TAG, "COUNT : " + calendarioPeriodoList.size() + " /  periodo : " + itemPeriodo.getTipoId());
                Tipos tipo = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(itemPeriodo.getTipoId()))
                        .querySingle();
                PeriodoUi periodoUi = new PeriodoUi(String.valueOf(itemPeriodo.getCalendarioPeriodoId()), tipo.getNombre(), "", false);
                list.add(periodoUi);
                if(itemPeriodo.getEstadoId()==CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE){
                    periodoUi.setStatus(true);
                    seleccionado=true;
                    periodoUi.setVigente(true);
                }
            }

            if (!seleccionado && calendarioPeriodoList.size() > 0) {
                PeriodoUi firstPositionList = list.get(0);
                firstPositionList.setStatus(true);
            }

            callBackList.onLista(list);

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
            callBackList.onLista(new ArrayList<PeriodoUi>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }



    @Override
    public void getRubricasBid(int calendarioPeriodoId, int cargaCursoid, int cursoId, CallBackListRub callBackList) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                    .select(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(cargaCursoid)
                    .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                    .querySingle(databaseWrapper);
            Log.d(TAG, "");
            if (silaboEvento == null) {
                callBackList.onListaRubBidList(new ArrayList<RubBidUi>());
                return;
            }

            boolean calendarioVigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(calendarioPeriodoId, cargaCursoid, false,databaseWrapper);

            CalendarioPeriodo calendarioPeriodo = SQLite.select()
                    .from(CalendarioPeriodo.class)
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                    .querySingle(databaseWrapper);

            boolean calendarioEditar = false;

            if(calendarioPeriodo!=null){
                switch (calendarioPeriodo.getEstadoId()){
                    case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                        calendarioEditar = true;
                        break;
                    case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                        calendarioEditar= calendarioVigente;
                        break;
                    case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                        calendarioEditar= calendarioVigente;
                        break;
                }
            }
            List<RubroEvaluacionProcesoC> list = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL))
                    .and(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().asc())
                    .queryList(databaseWrapper);

            List<RubBidUi> rubBidList = new ArrayList<>();
            int conteo=0;
            for (RubroEvaluacionProcesoC proceso : list) {
                int formaEvaluacionId = proceso.getFormaEvaluacionId();
                int tipoEvaluacionId = proceso.getTipoEvaluacionId();
                String tipoNotaId = proceso.getTipoNotaId();

                Tipos tipoFormulacion = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.is(formaEvaluacionId))
                        .querySingle(databaseWrapper);

                T_RN_MAE_TIPO_EVALUACION tipoEval = SQLite.select()
                        .from(T_RN_MAE_TIPO_EVALUACION.class)
                        .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.is(tipoEvaluacionId))
                        .querySingle(databaseWrapper);

                /*TipoNotaC tipoNotaC = SQLite.select()
                        .from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.is(tipoNotaId))
                        .querySingle(databaseWrapper);*/

                conteo++;
                if (tipoFormulacion == null || tipoEval == null) continue;
                TipoUi formaEvaluacion = new TipoUi();
                formaEvaluacion.setId(formaEvaluacionId);
                formaEvaluacion.setTitle(tipoFormulacion.getNombre());

                TipoUi tipoEvaluacion = new TipoUi();
                tipoEvaluacion.setId(tipoEvaluacionId);
                tipoEvaluacion.setTitle(tipoEval.getNombre());

                /*if (tipoNotaC == null ) continue;
                TipoNotaUi tipoNotaUi = new TipoNotaUi();
                tipoNotaUi.setId(tipoNotaC.getTipoNotaId());
                tipoNotaUi.setNombre(tipoNotaC.getNombre());
                tipoNotaUi.setValorDefecto(tipoNotaC.getValorDefecto());*/

                RubBidUi rubBidUi = new RubBidUi();
                rubBidUi.setKey(proceso.getKey());
                rubBidUi.setEstadoMsje(proceso.getMsje());
                rubBidUi.setPosicion(conteo);
                rubBidUi.setTitle(proceso.getTitulo());
                rubBidUi.setAlias(proceso.getSubtitulo());
                rubBidUi.setFormaEvaluacion(formaEvaluacion);
                rubBidUi.setTipoEvaluacion(tipoEvaluacion);
                rubBidUi.setExportado(proceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                        proceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                        proceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                //rubBidUi.setTipoNota(tipoNotaUi);
                rubBidUi.setSesionAprendizajeId(proceso.getSesionAprendizajeId());

                if(proceso.getSesionAprendizajeId()!=0){
                    rubBidUi.setOrigenUi(OrigenUi.SESION);
                }else {
                    rubBidUi.setOrigenUi(OrigenUi.AREA);
                }


                Where<EvaluacionProcesoC> evaluacionProcesoCWhere = SQLite.selectCountOf()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(proceso.getKey()));

                long cantidadEval = evaluacionProcesoCWhere
                        .count(databaseWrapper);
                long cantidadEvalPublicado = evaluacionProcesoCWhere
                        .and(EvaluacionProcesoC_Table.publicado.eq(1))
                        .count(databaseWrapper);

                if(cantidadEval == 0){
                    rubBidUi.setPublicarEval(RubBidUi.PublicarEval.NINGUNO);
                } else if(cantidadEval == cantidadEvalPublicado){
                    rubBidUi.setPublicarEval(RubBidUi.PublicarEval.TODOS);
                }else if(cantidadEvalPublicado == 0){
                    rubBidUi.setPublicarEval(RubBidUi.PublicarEval.NINGUNO);
                }else {
                    rubBidUi.setPublicarEval(RubBidUi.PublicarEval.PARCIAL);
                }

                if(!TextUtils.isEmpty(proceso.getTareaId())){
                    rubBidUi.setOrigenUi(OrigenUi.TAREA);
                }

                rubBidUi.setFecha(Utils.f_fecha_letras(proceso.getFechaCreacion()));
                switch (proceso.getFormaEvaluacionId()) {
                    case 477:
                        rubBidUi.setForma(RubBidUi.Forma.INDIVIDUAL);
                        break;
                    case 478:
                        rubBidUi.setForma(RubBidUi.Forma.GRUPAL);
                        break;
                }
                Log.d(TAG, "Separacion ");
                Log.d(TAG, "PROMEDIO "+ proceso.getPromedio());
                rubBidUi.setRubroEvalProcesoList(listaRubroEvalProc(rubBidUi));
                rubBidUi.setMedia( proceso.getPromedio());
                rubBidUi.setDesviacionE(  proceso.getDesviacionEstandar());


                rubBidUi.setEditar(calendarioEditar);
                rubBidUi.setDisabledEval(calendarioVigente);
                rubBidList.add(rubBidUi);
            }
            databaseWrapper.setTransactionSuccessful();
            Collections.reverse(rubBidList);
            callBackList.onListaRubBidList(rubBidList);
        } catch (Exception e){
            e.printStackTrace();
            callBackList.onListaRubBidList(new ArrayList<RubBidUi>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }



    private List<RubEvalProcUi> listaRubroEvalProc(RubBidUi rubBidUi) {
        List<RubEvalProcUi> evalProcUis = new ArrayList<>();
        int countItem = 0;

        List<RubroEvalRNPFormulaC> listaRubrosAsociados = rnpFormulaCList(rubBidUi.getKey());
        if (listaRubrosAsociados == null) return new ArrayList<>();

        for (int i = 0; i < listaRubrosAsociados.size(); i++) {
            countItem++;
            evalProcUis.add(new RubEvalProcUi(1, String.valueOf(countItem), 2.0));
        }
        return evalProcUis;
    }
    private List<RubroEvalRNPFormulaC> rnpFormulaCList(String key) {
        return rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(key);
    }

}
