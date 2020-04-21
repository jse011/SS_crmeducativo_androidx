package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.local;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetIndicadorCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListCriterioEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListTipoEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.SaveRubroCallBack;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EstrategiaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ColorCondicionalC;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.TipoEvaluacionModel;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class CrearRubroLocalSource  implements CrearRubroDataSource {
    private String TAG = "CrearRubroLocalSource";
    private EvaluacionProcesoDao evaluacionProcesoDao;
    private CompetenciaDao competenciaDao;
    private TareasDao tareasDao;
    private TipoNotaDao tipoNotaDao;
    public CrearRubroLocalSource() {
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
        competenciaDao = InjectorUtils.provideCompetenciaDao();
        tareasDao = InjectorUtils.provideTareasDao();
        tipoNotaDao = InjectorUtils.provideTipoNotaDao();
    }

    @Override
    public void SaveRubro(final CrearRubroEvalUi rubroUi, final SaveRubroCallBack callBack) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                SessionUser sessionUser = SessionUser.getCurrentUser();
                if(sessionUser == null) throw new Error("no se guardó correctamente el RubroEvaluacion por que no se encontro al Usuario");
                TipoNotaUi tipoNotaUi = rubroUi.getTipoNotaUi();
                TipoEvaluacionUi tipoEvaluacionUi = rubroUi.getTipoEvaluacionUi();
                FormaEvaluacionUi formaEvaluacionUi = rubroUi.getFormaEvaluacionUi();
                int TipoEvaluacionId = tipoEvaluacionUi.getId();
                if (TipoEvaluacionId == 0) {
                    TipoEvaluacionId = 345;
                }


                RubroEvaluacionProcesoC rubroEvaluacionProcesoC= SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(rubroUi.getKey())).querySingle();
                
                if(rubroEvaluacionProcesoC !=null){
                    rubroEvaluacionProcesoC.setTitulo(rubroUi.getTitulo());
                    rubroEvaluacionProcesoC.setSubtitulo(rubroUi.getSubtitulo());
                    rubroEvaluacionProcesoC.setTipoEvaluacionId(TipoEvaluacionId);
                    rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.setEstrategiaEvaluacionId(rubroUi.getEstrategiaEvalId());
                    rubroEvaluacionProcesoC.update();

                    //actualizar criterios
                   // Log.d(TAG, "tipo de notas  "+ rubroUi.getTipoNotaUi().getValorTipoNotaUis().size());
                    for (ValorTipoNotaUi valorTipoNotaUi : rubroUi.getTipoNotaUi().getValorTipoNotaUis()) {
                        CriterioEvalUi criterioEvalUi = valorTipoNotaUi.getCriterioEvalUi();
//                        Log.d(TAG, "descrpcion  "+ criterioEvalUi.getDescripcion());
//                        Log.d(TAG, "rubroEvalProcesoId  "+ rubroEvaluacionProcesoC.getKey());
//                        Log.d(TAG, "valorTipoNotaId  "+valorTipoNotaUi.getId());

                        CriterioRubroEvaluacionC criterioRubroEvaluacionC= SQLite.select().from(CriterioRubroEvaluacionC.class)
                                .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionProcesoC.getKey()))
                                .and(CriterioRubroEvaluacionC_Table.valorTipoNotaId.withTable().eq(valorTipoNotaUi.getId())).querySingle();

                        if(criterioRubroEvaluacionC!=null){

                            criterioRubroEvaluacionC.setDescripcion(criterioEvalUi.getDescripcion());
                            criterioRubroEvaluacionC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
                            criterioRubroEvaluacionC.update();
                        }

                    }
                    callBack.localSuccess(rubroEvaluacionProcesoC.getKey(), true);

                } else {
                    RubroEvaluacionProcesoC rubro = new RubroEvaluacionProcesoC();
                    rubro.setTitulo(rubroUi.getTitulo());
                    rubro.setSubtitulo(rubroUi.getSubtitulo());
                    rubro.setTipoNotaId(tipoNotaUi.getId());
                    rubro.setFormaEvaluacionId(formaEvaluacionUi.getId());
                    rubro.setEstrategiaEvaluacionId(rubroUi.getEstrategiaEvalId());
                    rubro.setSesionAprendizajeId(rubroUi.getSesionAprendizajeId());
                    rubro.setSilaboEventoId(rubroUi.getSilavoEventoId());
                    rubro.setCalendarioPeriodoId(rubroUi.getCalendarioPeriodoId());
                    rubro.setCompetenciaId(rubroUi.getCompetenciaId());
                    rubro.setDesempenioIcdId(rubroUi.getIndicadorId());

                    rubro.setValorDefecto(rubroUi.getValorDefecto());
                    rubro.setTipoEvaluacionId(TipoEvaluacionId);
                    rubro.setEstadoId(237);
                    rubro.setTiporubroid(470);
                    rubro.setTipoFormulaId(0);
                    rubro.setTipoRedondeoId(439);
                    rubro.setValorRedondeoId(426);
                    rubro.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
                    rubro.setUsuarioAccionId(sessionUser.getUserId());
                    rubro.setUsuarioCreacionId(sessionUser.getUserId());


                    Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(rubroUi.getCompetenciaId());
                    if(competencia.getTipoId() == Competencia.COMPETENCIA_BASE)rubro.setRubroFormal(1);                //rubro.setRubroEvalResultadoId(rubroUi.getIdRubroResultadoId());
                    /*Color*/
                    Log.d(TAG,"TipoEvaluacionId : "+TipoEvaluacionId );
                    Log.d(TAG,"tipoNotaUi : "+tipoNotaUi.getId() +" /   "+ tipoNotaUi.getTipoId() );
                    boolean estadoRubro = rubro.save();
                    String rubroid = rubro.getKey();
                    Log.d(TAG, "Insert rubroid " + rubroid);
                    if (estadoRubro) {
                        for (ValorTipoNotaUi valorTipoNotaUi : rubroUi.getTipoNotaUi().getValorTipoNotaUis()) {
                            String valorTipoNotaId = valorTipoNotaUi.getId();
                            CriterioEvalUi criterioEvalUi = valorTipoNotaUi.getCriterioEvalUi();
                            String descripcion = criterioEvalUi.getDescripcion();
                            Log.d(TAG, "ValorTipoNota"+  valorTipoNotaId);
                            Log.d(TAG, "descripcion"+  descripcion);
                            CriterioRubroEvaluacionC criterioRubroEvaluacion = new CriterioRubroEvaluacionC();
                            criterioRubroEvaluacion.setDescripcion(criterioEvalUi.getDescripcion());
                            criterioRubroEvaluacion.setRubroEvalProcesoId(rubroid);
                            criterioRubroEvaluacion.setValorTipoNotaId(valorTipoNotaId);
                            criterioRubroEvaluacion.setSyncFlag(BaseEntity.FLAG_ADDED);
                            criterioRubroEvaluacion.setDescripcion(descripcion);
                            boolean succesCriterio = criterioRubroEvaluacion.save();
                            //Log.d(TAG, "CriterioEvaluacionasd"+  criterioRubroEvaluacion.toString());
                            if (!succesCriterio)throw new Error("no se guardó correctamente el RubroEvaluacion por un error en el CriterioRubroEvaluacion con valorTipoNotaId: " + valorTipoNotaId + " Descripcion: " + criterioEvalUi.getDescripcion());
                        }

                        for (ColorCondicionalUi colorCondicionalUi : rubroUi.getColorCondicionalUis()) {
                            ColorCondicionalC colorCondicional = new ColorCondicionalC();
                            colorCondicional.setRubroEvalProcesoId(rubroid);
                            colorCondicional.setDesde(colorCondicionalUi.getDesde());
                            colorCondicional.setHasta(colorCondicionalUi.getHasta());
                            colorCondicional.setIncluidoDesde(colorCondicionalUi.isSelectDesde());
                            colorCondicional.setIncluidoHasta(colorCondicionalUi.isSelectHasta());
                            colorCondicional.setColorFondo(String.format("#%06X", (0xFFFFFF & colorCondicionalUi.getColorFondo())));
                            colorCondicional.setColorTexto(String.format("#%06X", (0xFFFFFF & colorCondicionalUi.getColorTexto())));
                            colorCondicional.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
                            colorCondicional.setUsuarioAccionId(sessionUser.getUserId());
                            colorCondicional.setUsuarioCreacionId(sessionUser.getUserId());
                            boolean estadoColor = colorCondicional.save();
                            String colorCondicionalId = colorCondicional.getKey();

                            if (!estadoColor) throw new Error("no se guardó correctamente el RubroEvaluacion por un error en el ColorCondicionalUi con el  valor Desde:" + colorCondicionalUi.getDesde() + " Hasta: " + colorCondicionalUi.getHasta());

                        }

                        for (Integer campotematicoId : rubroUi.getCampotematicoIds()){
                            RubroEvaluacionProcesoCampotematicoC rubroEvaluacionProcesoCampotematico = new RubroEvaluacionProcesoCampotematicoC();
                            rubroEvaluacionProcesoCampotematico.setCampoTematicoId(campotematicoId);
                            rubroEvaluacionProcesoCampotematico.setRubroEvalProcesoId(rubroid);
                            rubroEvaluacionProcesoCampotematico.setSyncFlag(RubroEvaluacionProcesoCampotematicoC.FLAG_ADDED);
                            rubroEvaluacionProcesoCampotematico.setUsuarioAccionId(sessionUser.getUserId());
                            rubroEvaluacionProcesoCampotematico.setUsuarioCreacionId(sessionUser.getUserId());
                            boolean estadoRubroCampotematico = rubroEvaluacionProcesoCampotematico.save();
                            if (!estadoRubroCampotematico)throw new Error("no se guardó correctamente el RubroEvaluacion por un error en el RubroEvaluacionProcesoCampotematico");
                        }


                    } else {
                        throw new Error("no se guardó correctamente el RubroEvaluacion");
                    }


                    if(rubro.getFormaEvaluacionId() == 477){
                        SilaboEvento silaboEvento = SQLite.select()
                                .from(SilaboEvento.class)
                                .where(SilaboEvento_Table.silaboEventoId.eq(rubroUi.getSilavoEventoId()))
                                .querySingle();
                        if(!evaluacionProcesoDao.crearEvaluacionProceso(rubroid, silaboEvento.getCargaCursoId())){
                            throw new Error("no se guardó correctamente el EvaluacionProceso");
                        }

                    }

                    if(!TextUtils.isEmpty(rubroUi.getTareaId())){
                        TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso = new TareaRubroEvaluacionProceso();
                        tareaRubroEvaluacionProceso.setRubroEvalProcesoId(rubroid);
                        tareaRubroEvaluacionProceso.setTareaId(rubroUi.getTareaId());
                        tareaRubroEvaluacionProceso.setSyncFlag(TareaRubroEvaluacionProceso.FLAG_ADDED);
                        boolean succesTarea = tareaRubroEvaluacionProceso.save();
                        if(!succesTarea)throw new Error("Error al guardar TareaRubroEvaluacionProceso");
                        TareasC tareasC = SQLite.select()
                                .from(TareasC.class)
                                .where(TareasC_Table.key.eq(rubroUi.getTareaId()))
                                .querySingle();
                        if(tareasC!=null){
                            tareasC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                            tareasC.save();
                        }
                    }
                    rubroUi.setKey(rubroid);
                    callBack.localSuccess(rubroid, true);
                }

            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {

            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.localSuccess("", false);
            }
        }).build();

        transaction.execute();

    }

    @Override
    public void GetCriterioEvaluacion(CrearRubroEvalUi crearRubroEvalUi, GetListCriterioEvaluacionCallback callback) {

        try{
            if(crearRubroEvalUi == null) return;
            TipoNotaUi tipoNotaUi = crearRubroEvalUi.getTipoNotaUi();
            if(tipoNotaUi == null) return;

            List<ValorTipoNotaC> valorTipoNotas = SQLite.select()
                    .from(ValorTipoNotaC.class)
                    .where(ValorTipoNotaC_Table.tipoNotaId.is(tipoNotaUi.getId()))
                    .queryList();

            List<ValorTipoNotaUi> valorTipoNotaUis = new ArrayList<>();
            for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
                String ValorTipoNotaId = valorTipoNota.getValorTipoNotaId();
                valorTipoNotaUi.setId(ValorTipoNotaId);
                valorTipoNotaUi.setTitulo(valorTipoNota.getTitulo());
                valorTipoNotaUi.setDescripcion(valorTipoNota.getAlias());
                valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
                valorTipoNotaUi.setTipoNota(tipoNotaUi.getTipoNota());
                valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);

                Log.d(TAG, "rubroEvalProcesoId "+ crearRubroEvalUi.getKey());
                CriterioRubroEvaluacionC criterioRubroEvaluacionC= SQLite.select().from(CriterioRubroEvaluacionC.class)
                        .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.eq(crearRubroEvalUi.getKey())).
                        and(CriterioRubroEvaluacionC_Table.valorTipoNotaId.withTable().eq(ValorTipoNotaId)).querySingle();
                CriterioEvalUi criterioEvalUi = new CriterioEvalUi();
                if(criterioRubroEvaluacionC!=null){
                    Log.d(TAG, "criterioRubroEvaluacionC "+ criterioRubroEvaluacionC.getDescripcion());
                    Log.d(TAG, "criterio id "+ criterioRubroEvaluacionC.getKey());
                    criterioEvalUi.setId(criterioRubroEvaluacionC.getKey());
                    criterioEvalUi.setDescripcion(criterioRubroEvaluacionC.getDescripcion());
                }
                valorTipoNotaUi.setCriterioEvalUi(criterioEvalUi);
                valorTipoNotaUis.add(valorTipoNotaUi);
            }
            callback.onTipoNotaLoad(valorTipoNotaUis);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void GetIndicaor(int indicadorId, ArrayList<Integer> campotematicoIds, GetIndicadorCallback callback) {

        Log.d(TAG, "GETINTICADOR" + indicadorId);
        try{
            IndicadorUi indicadorUi = new IndicadorUi();
            Icds icds = SQLite.select(Icds_Table.icdId.withTable(), Icds_Table.titulo.withTable(), DesempenioIcd_Table.tipoId.withTable())
                    .from(Icds.class)
                    .innerJoin(DesempenioIcd.class)
                    .on(DesempenioIcd_Table.icdId.withTable()
                            .eq(Icds_Table.icdId.withTable()))
                    .where(DesempenioIcd_Table.desempenioIcdId.withTable().is(indicadorId))
                    .querySingle();
            int IcdId = icds!=null?icds.getIcdId():0;
            DesempenioIcd desempenioIcd = SQLite.select()
                    .from(DesempenioIcd.class)
                    .where(DesempenioIcd_Table.icdId.withTable().eq(IcdId))
                    .querySingle();

            int desempenioId = desempenioIcd!=null?desempenioIcd.getDesempenioId():0;
            Desempenio desempenio = SQLite.select()
                    .from(Desempenio.class)
                    .where(Desempenio_Table.desempenioId.eq(desempenioId))
                    .querySingle();

            if (icds != null) {
                indicadorUi.setId(icds.getIcdId());
                indicadorUi.setTitulo(icds.getTitulo());
                if(desempenioIcd!=null)indicadorUi.setUrl(desempenioIcd.getUrl());
                if(desempenio!=null)indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                indicadorUi.setAlias(icds.getAlias());
                if(desempenioIcd!=null)indicadorUi.setDescripcion(desempenioIcd.getDescripcion());
                switch (icds.getTipoId()){
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
                Log.d(TAG, "NombreIndicador : " + icds.getTitulo());

                List<CampoTematico> campotematicos = SQLite.select()
                        .from(CampoTematico.class)
                        .where(CampoTematico_Table.campoTematicoId.in(campotematicoIds))
                        .queryList();
                indicadorUi.setCamposAccionUiList(getListCampoAccion(campotematicos));
            }
            callback.onIndicadorLoad(indicadorUi);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private List<CamposAccionUi> getListCampoAccion(List<CampoTematico> campoTematicoList) {
        List<CamposAccionUi> campotematicoUipadresList = new ArrayList<>();


        for (CampoTematico itemCampoTematico : campoTematicoList) {
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
        HashSet<CamposAccionUi> campotematicoUiList = new LinkedHashSet<>();
        for (CamposAccionUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            count ++;
            String binieta = count + ". ";
            itemCampotematicoUiPadre.setBinieta(binieta);
            campotematicoUiList.add(itemCampotematicoUiPadre);
            if ( itemCampotematicoUiPadre.getCampoAccionUiList() == null) continue;
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampoAccionUiList());
        }

        return new ArrayList<>(campotematicoUiList);
    }
    @Override
    public void GetTipoEvaluacion(GetListTipoEvaluacionCallback callback) {

        try{
            List<T_RN_MAE_TIPO_EVALUACION> lstTipos = TipoEvaluacionModel.SQLView()
                    .select()
                    .getQuery()
                    .queryList();


            List<TipoEvaluacionUi> tipoEvaluacionUis = new ArrayList<>();
            for (T_RN_MAE_TIPO_EVALUACION tipo : lstTipos) {
                TipoEvaluacionUi tipoEvaluacionUi = new TipoEvaluacionUi();
                tipoEvaluacionUi.setId(tipo.getTipoEvaluacionId());
                tipoEvaluacionUi.setNombre(tipo.getNombre());
                tipoEvaluacionUis.add(tipoEvaluacionUi);
            }
            callback.onEvaluacionLoad(tipoEvaluacionUis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void GetFormaEvaluacion(ListCallck<FormaEvaluacionUi> callback) {
        try{
            List<Tipos> tiposList = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.concepto.is("Forma Evaluacion"))
                    .and(Tipos_Table.objeto.is("T_RN_MAE_RUBRO_EVALUACION_PROCESO"))
                    .queryList();
            List<FormaEvaluacionUi> formaEvaluacionUiList = new ArrayList<>();
            for (Tipos itemTipos: tiposList){
                FormaEvaluacionUi formaEvaluacionUi = new FormaEvaluacionUi();
                formaEvaluacionUi.setId(itemTipos.getTipoId());
                formaEvaluacionUi.setNombre(itemTipos.getNombre());
                formaEvaluacionUiList.add(formaEvaluacionUi);
            }
            callback.onSucess(formaEvaluacionUiList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback) {

       try{
           TipoNotaUi tipoNotaUi = new TipoNotaUi();
           TipoNotaC tipoNota = tipoNotaDao.getTipoNotaConValores(tipoNotaId);

           EscalaEvaluacion escalaEvaluacion = SQLite.select()
                   .from(EscalaEvaluacion.class)
                   .where(EscalaEvaluacion_Table.escalaEvaluacionId.eq(tipoNota.getEscalaEvaluacionId()))
                   .querySingle();

           EscalaEvaluacionUi escalaEvaluacionUi = new EscalaEvaluacionUi();
           if(escalaEvaluacion!=null){
               escalaEvaluacionUi.setEscalaEvaluacionId(escalaEvaluacion.getEscalaEvaluacionId());
               escalaEvaluacionUi.setNombre(escalaEvaluacion.getNombre());
               escalaEvaluacionUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
               escalaEvaluacionUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
           }

           tipoNotaUi.setId(tipoNota.getKey());
           tipoNotaUi.setNombre(tipoNota.getNombre());
           tipoNotaUi.setTipoId(tipoNota.getTipoId());
           tipoNotaUi.setEscalaEvaluacionUi(escalaEvaluacionUi);
           switch (tipoNota.getTipoId()){
               case TipoNotaC.SELECTOR_ICONOS:
                   tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.IMAGEN);
                   break;
               case TipoNotaC.SELECTOR_NUMERICO:
                   tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.SELECTOR_NUMERICO);
                   break;
               case TipoNotaC.SELECTOR_VALORES:
                   tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.TEXTO);
                   break;
               case TipoNotaC.VALOR_NUMERICO:
                   tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.VALOR_NUMERICO);
                   break;
               default:
                   tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.DEFECTO);
                   break;
           }

           int count = 0;
           List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();
           for (ValorTipoNotaC valorTipoNota : tipoNota.getValorTipoNotaList()) {

               ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
               valorTipoNotaUi.setId(valorTipoNota.getValorTipoNotaId());
               valorTipoNotaUi.setTitulo(valorTipoNota.getTitulo());
               valorTipoNotaUi.setDescripcion(valorTipoNota.getAlias());
               valorTipoNotaUi.setValorDefecto(valorTipoNota.getValorNumerico());
               valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
               valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
               valorTipoNotaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
               valorTipoNotaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
               valorTipoNotaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
               valorTipoNotaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
               valorTipoNotaUi.setValorNumerico(valorTipoNota.getValorNumerico());
               switch (count) {
                   case 0:
                       valorTipoNotaUi.setColor(ValorTipoNotaUi.AZUL);
                       break;
                   case 1:
                       valorTipoNotaUi.setColor(ValorTipoNotaUi.VERDE);
                       break;
                   case 2:
                       valorTipoNotaUi.setColor(ValorTipoNotaUi.ANARANJADO);
                       break;
                   case 3:
                       valorTipoNotaUi.setColor(ValorTipoNotaUi.ROJO);
                       break;
                   default:
                       valorTipoNotaUi.setColor(ValorTipoNotaUi.GREY);
                       break;
               }
               count++;
               valorTipoNotaUiList.add(valorTipoNotaUi);


           }

           tipoNotaUi.setValorTipoNotaUis(valorTipoNotaUiList);

           callback.onSucess(tipoNotaUi);
       }catch (Exception e){
           e.printStackTrace();
           callback.onSucess(new TipoNotaUi());
       }
    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {
        try {
            TareasC tareasC = tareasDao.getTareaId(id);
            if (tareasC==null)return;
            TareasUI tareasUI = new TareasUI();
            tareasUI.setDescripcion(tareasC.getInstrucciones());
            tareasUI.setFechaCreacionTarea(tareasC.getFechaEntrega());
            tareasUI.setTituloTarea(tareasC.getTitulo());
            getTareaUICallback.onTareaLoaded(tareasUI);
        }catch (Exception e){
            e.printStackTrace();
            getTareaUICallback.onError();
        }
    }

    @Override
    public List<EstrategiaUi> getEstrategiasEvaluacion(int cursoId) {

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

        List<EstrategiaUi>estrategiaEvalUiList= new ArrayList<>();
        for(EstrategiaEvaluacion estrategiaEvaluacion: estrategiaEvaluacions){
            EstrategiaUi estrategiaEvalUi= new EstrategiaUi();
            estrategiaEvalUi.setEstrategiaId(estrategiaEvaluacion.getEstrategiaEvaluacionId());
            estrategiaEvalUi.setEstrategia(estrategiaEvaluacion.getNombre());
            estrategiaEvalUiList.add(estrategiaEvalUi);
        }


//        List<EstrategiaUi >estrategiaEvalUiList2= new ArrayList<>();
//        EstrategiaUi e1= new EstrategiaUi();
//        e1.setEstrategiaId(1);
//        e1.setEstrategia("Resolucion de CAso");
//        estrategiaEvalUiList2.add(e1);
//        EstrategiaUi e2= new EstrategiaUi();
//        e2.setEstrategiaId(2);
//        e2.setEstrategia("Experimento");
//        estrategiaEvalUiList2.add(e2);
//
//        EstrategiaUi e3= new EstrategiaUi();
//        e3.setEstrategiaId(3);
//        e3.setEstrategia("Taller de Clase");
//        estrategiaEvalUiList2.add(e3);

        return estrategiaEvalUiList;
    }

    @Override
    public TipoNotaUi getTipoNota(int promaEducativoId) {
        List<Integer> tipoIdList = new ArrayList<>();
        tipoIdList.add(TipoNotaC.VALOR_NUMERICO);
        tipoIdList.add(TipoNotaC.SELECTOR_ICONOS);
        tipoIdList.add(TipoNotaC.SELECTOR_VALORES);
        tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);

        List<TipoNotaC> tipoNotaUiList = tipoNotaDao.getTipoNotaList(promaEducativoId, tipoIdList);

        Log.d(TAG, "tipoNotaUiList " + tipoNotaUiList.size());
        TipoNotaUi tipoNotaUi = null;
        if(!tipoNotaUiList.isEmpty()){
            tipoNotaUi = new TipoNotaUi();
            TipoNotaC tipoNotaC = tipoNotaUiList.get(0);

            EscalaEvaluacion escalaEvaluacion = SQLite.select()
                    .from(EscalaEvaluacion.class)
                    .where(EscalaEvaluacion_Table.escalaEvaluacionId.eq(tipoNotaC.getEscalaEvaluacionId()))
                    .querySingle();

            EscalaEvaluacionUi escalaEvaluacionUi = new EscalaEvaluacionUi();
            if(escalaEvaluacion!=null){
                escalaEvaluacionUi.setEscalaEvaluacionId(escalaEvaluacion.getEscalaEvaluacionId());
                escalaEvaluacionUi.setNombre(escalaEvaluacion.getNombre());
                escalaEvaluacionUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
                escalaEvaluacionUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
            }

            tipoNotaUi.setId(tipoNotaC.getKey());
            tipoNotaUi.setNombre(tipoNotaC.getNombre());
            tipoNotaUi.setTipoId(tipoNotaC.getTipoId());
            tipoNotaUi.setEscalaEvaluacionUi(escalaEvaluacionUi);
            switch (tipoNotaC.getTipoId()){
                case TipoNotaC.SELECTOR_ICONOS:
                    tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.IMAGEN);
                    break;
                case TipoNotaC.SELECTOR_NUMERICO:
                    tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.SELECTOR_NUMERICO);
                    break;
                case TipoNotaC.SELECTOR_VALORES:
                    tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.TEXTO);
                    break;
                case TipoNotaC.VALOR_NUMERICO:
                    tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.VALOR_NUMERICO);
                    break;
                default:
                    tipoNotaUi.setTipoNota(TipoNotaUi.TipoNota.DEFECTO);
                    break;
            }

            tipoNotaUi.setEscalaEvaluacionUi(escalaEvaluacionUi);

            List<ValorTipoNotaC> valorTipoNotas = tipoNotaC.getValorTipoNotaList();
            int count = 0;
            List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();
            for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {

                ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
                valorTipoNotaUi.setId(valorTipoNota.getValorTipoNotaId());
                valorTipoNotaUi.setTitulo(valorTipoNota.getTitulo());
                valorTipoNotaUi.setDescripcion(valorTipoNota.getAlias());
                valorTipoNotaUi.setValorDefecto(valorTipoNota.getValorNumerico());
                valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
                valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
                valorTipoNotaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
                valorTipoNotaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
                valorTipoNotaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
                valorTipoNotaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
                valorTipoNotaUi.setValorNumerico(valorTipoNota.getValorNumerico());
                switch (count) {
                    case 0:
                        valorTipoNotaUi.setColor(ValorTipoNotaUi.AZUL);
                        break;
                    case 1:
                        valorTipoNotaUi.setColor(ValorTipoNotaUi.VERDE);
                        break;
                    case 2:
                        valorTipoNotaUi.setColor(ValorTipoNotaUi.ANARANJADO);
                        break;
                    case 3:
                        valorTipoNotaUi.setColor(ValorTipoNotaUi.ROJO);
                        break;
                    default:
                        valorTipoNotaUi.setColor(ValorTipoNotaUi.GREY);
                        break;
                }
                count++;
                valorTipoNotaUiList.add(valorTipoNotaUi);


            }

            tipoNotaUi.setValorTipoNotaUis(valorTipoNotaUiList);
        }
        return tipoNotaUi;
    }

    @Override
    public CapacidadUi getCompetencia(int competenciaId) {
        Competencia capacidad = competenciaDao.get(competenciaId);
        CapacidadUi capacidadUi = new CapacidadUi();
        CompetenciaUi competenciaUi = new CompetenciaUi();
        if(capacidad!=null){
            capacidadUi.setId(capacidad.getCompetenciaId());
            capacidadUi.setNombre(capacidad.getNombre());
            Competencia competencia = competenciaDao.get(capacidad.getSuperCompetenciaId());
            if(competencia!=null){
                competenciaUi.setId(competencia.getCompetenciaId());
                competenciaUi.setNombre(competencia.getNombre());
            }
        }
        capacidadUi.setCompetencia(competenciaUi);
        return capacidadUi;
    }


}
