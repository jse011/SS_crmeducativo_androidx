package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.local;

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
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.OrigenUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.RubricaSesionDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.gms.common.util.ArrayUtils;
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

public class RubricaSesionLocal implements RubricaSesionDataSource {

    private static String RUBRICA_SESION_TAG = RubricaSesionLocal.class.getSimpleName();

    int countIndicador;
    private CalendarioPeriodoDao calendarioPeriodoDao;
    private RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;

    public RubricaSesionLocal(CalendarioPeriodoDao calendarioPeriodoDao, RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao) {
        this.calendarioPeriodoDao = calendarioPeriodoDao;
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
    }

    @Override
    public void getRubricasSesion(int sesionAprendizajeId, int cargaCursoid, int cursoId, int calendarioPeriodoId, CallBackListRub callBackListRub) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<RubroEvaluacionProcesoC> list = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.tiporubroid.eq(471))
                    .and(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(sesionAprendizajeId))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))//estado eliminado
                    .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().asc())
                    .queryList(databaseWrapper);

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

            int conteo = 0;
            List<RubBidUi> rubBidList = new ArrayList<>();
            for (RubroEvaluacionProcesoC proceso :
                    list) {
                int formaEvaluacionId = proceso.getFormaEvaluacionId();
                int tipoEvaluacionId = proceso.getTipoEvaluacionId();
                Tipos tipo = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.is(formaEvaluacionId))
                        .querySingle(databaseWrapper);

                T_RN_MAE_TIPO_EVALUACION tipoEval = SQLite.select()
                        .from(T_RN_MAE_TIPO_EVALUACION.class)
                        .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.is(tipoEvaluacionId))
                        .querySingle(databaseWrapper);
                conteo++;
                if (tipo == null || tipoEval == null) continue;
                TipoUi formaEvaluacion = new TipoUi();
                formaEvaluacion.setId(formaEvaluacionId);
                formaEvaluacion.setTitle(tipo.getNombre());

                TipoUi tipoEvaluacion = new TipoUi();
                tipoEvaluacion.setId(tipoEvaluacionId);
                tipoEvaluacion.setTitle(tipoEval.getNombre());


                RubBidUi rubBidUi = new RubBidUi(0, proceso.getTitulo());
                rubBidUi.setEditar(calendarioEditar);
                rubBidUi.setDisabledEval(calendarioVigente);
                rubBidUi.setAlias(proceso.getSubtitulo());
                rubBidUi.setExportado(proceso.getSyncFlag()!=BaseEntity.FLAG_ADDED&&
                        proceso.getSyncFlag()!=BaseEntity.FLAG_UPDATED&&
                        proceso.getSyncFlag()!=BaseEntity.FLAG_ERROREXPORTED);
                rubBidUi.setKey(proceso.getKey());
                rubBidUi.setEstadoMsje(proceso.getMsje());
                rubBidUi.setPosicion(conteo);
                countIndicador = proceso.getCountIndicador();
                rubBidUi.setFormaEvaluacion(formaEvaluacion);
                rubBidUi.setTipoEvaluacion(tipoEvaluacion);
                rubBidUi.setFecha(Utils.f_fecha_letras(proceso.getFechaCreacion()));
                switch (proceso.getFormaEvaluacionId()) {
                    case 477:
                        rubBidUi.setForma(RubBidUi.Forma.INDIVIDUAL);
                        break;
                    case 478:
                        rubBidUi.setForma(RubBidUi.Forma.GRUPAL);
                        break;
                }
                rubBidUi.setRubroEvalProcesoList(listaRubroEvalProc(rubBidUi));
                rubBidUi.setMedia(proceso.getPromedio());
                rubBidUi.setDesviacionE(proceso.getDesviacionEstandar());
                rubBidUi.setSesionAprendizajeId(sesionAprendizajeId);
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

                rubBidList.add(rubBidUi);
            }

            Log.d("RUBRICA_BID_LOCAL_TAG", "rubBidUiList size: " + rubBidList.size());
            databaseWrapper.setTransactionSuccessful();
            Collections.reverse(rubBidList);
            callBackListRub.onListaRubBidList(rubBidList);
        } catch (Exception e){
            e.printStackTrace();
            callBackListRub.onListaRubBidList(new ArrayList<RubBidUi>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, CallBack<PeriodoUi> callBack) {
        try {

           CalendarioPeriodo calendarioPeriodo =  calendarioPeriodoDao.get(calendarioPeriodoId);

            Tipos tipo = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();
            String tipoNombre = "";
            if(tipo!=null)tipoNombre = tipo.getNombre();
            PeriodoUi periodoUi = new PeriodoUi(String.valueOf(calendarioPeriodo.getCalendarioPeriodoId()),tipoNombre , "", false);

            if(calendarioPeriodo.getEstadoId()==CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE){
                periodoUi.setStatus(true);
                periodoUi.setVigente(true);
            }

            callBack.onListaRubBidList(true, periodoUi);
        }catch (Exception e){
            e.printStackTrace();
            callBack.onListaRubBidList(false, null);
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
