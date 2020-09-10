package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso.TareaRubroEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 19/02/2018.
 */

public class CasoUsoLocalDataSource implements CasoUsoDataSource {

    private static String TAG = CasoUsoLocalDataSource.class.getSimpleName();

    public static int REGISTRO_ERROR = 0;
    public static int REGISTRO_SUCCESS = 1;
    public static int REGISTRO_MESSAGE = 2;

    private TareaRubroEvaluacionProcesoDao tareaRubroEvaluacionProcesoDao;
    private CompetenciaDao competenciaDao;

    public CasoUsoLocalDataSource() {
        tareaRubroEvaluacionProcesoDao = InjectorUtils.provideTareaRubroEvaluacionProcesoDao();
        competenciaDao = InjectorUtils.provideCompetenciaDao();
    }

    @Override
    public void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback) {
        initValidacion(rubroProcesoUi,objectCallback);
    }

    private void initValidacion(RubroProcesoUi rubroProcesoUi,ObjectCallback objectCallback) {
        if (rubroProcesoUi.getTipoFormula() == RubroProcesoUi.TipoFormula.ANCLADA || rubroProcesoUi.getTipoFormula() == RubroProcesoUi.TipoFormula.EVALUADA) {
            objectCallback.onDelete(rubroProcesoUi, REGISTRO_MESSAGE);
        } else if (rubroProcesoUi.getTipo() == RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE) {
           objectCallback.onDelete(rubroProcesoUi, REGISTRO_MESSAGE);
        } else if (rubroProcesoUi.getTipo() == RubroProcesoUi.Tipo.UNIDIMENCIONAL||rubroProcesoUi.getTipoFormulaId() > 0) {
            /**/
            initDeleteRubros(rubroProcesoUi,objectCallback);
        }
    }

    private void initDeleteRubros(RubroProcesoUi rubroProcesoUi,ObjectCallback objectCallback) {

        boolean success = false;

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(rubroProcesoUi.getKey()))
                .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(280))
                .querySingle();

        if(rubroEvaluacionProcesoC==null)success = true;

        if(success){
            //TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso = tareaRubroEvaluacionProcesoDao.getTareaRubroPorRubroId(rubroProcesoUi.getKey());
            //if(tareaRubroEvaluacionProceso!=null)tareaRubroEvaluacionProcesoDao.elimarTareaRubroEvaluacionProceso(rubroProcesoUi.getKey());
            RubroEvaluacionProcesoC item = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                    .querySingle();

            if(item!=null){
                item.setTareaId("");
                item.save();
            }

            SQLite.update(RubroEvaluacionProcesoC.class)
                    .set(RubroEvaluacionProcesoC_Table.estadoId.is(280),
                            RubroEvaluacionProcesoC_Table.syncFlag.is(RubroEvaluacionProcesoC.FLAG_UPDATED),
                            RubroEvaluacionProcesoC_Table.fechaAccion.is(RubroEvaluacionProcesoC.getTime()))
                    .where(RubroEvaluacionProcesoC_Table.key.is(rubroProcesoUi.getKey()))
                    .execute();

            objectCallback.onDelete(rubroProcesoUi, REGISTRO_SUCCESS);

        }else {
            objectCallback.onDelete(rubroProcesoUi, REGISTRO_MESSAGE);
        }


    }

    @Override
    public void showListCamposTematicos(String rubroProcesoKey, ObjectCallbackList<List<IndicadoresCamposAccionUi>> listCamposTematicosCallBack) {
        RubroEvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.is(rubroProcesoKey))
                .querySingle();


        if (evaluacionProcesoC == null) return;


        List<Icds> icdsList = SQLite.select(
                Icds_Table.icdId.withTable(),
                Icds_Table.descripcion.withTable(),
                Icds_Table.titulo.withTable(),
                Icds_Table.alias.withTable(),
                Icds_Table.peso.withTable(),
                Icds_Table.estado.withTable(),
                DesempenioIcd_Table.tipoId.withTable())
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.withTable().is(evaluacionProcesoC.getDesempenioIcdId()))
                .queryList();

        boolean isBase=false;
        boolean isEnfoque=false;
        boolean isTransversal=false;
        Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(evaluacionProcesoC.getCompetenciaId());
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

        List<IndicadoresCamposAccionUi> indicadoresUiList = new ArrayList<>();
        for (Icds icds : icdsList) {

            Log.d(TAG, "icds : " + icds.getTitulo());
            IndicadoresCamposAccionUi indicadoresUi = new IndicadoresCamposAccionUi();
            indicadoresUi.setId(icds.getIcdId());
            indicadoresUi.setDescripcion(icds.getDescripcion());
            indicadoresUi.setTitulo(icds.getTitulo());
            indicadoresUi.setAlias(icds.getAlias());
            indicadoresUi.setPeso(icds.getPeso());
            indicadoresUi.setEstado(icds.getEstado());

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

            List<CampoTematico> campotematicos = SQLite.select()
                    .from(CampoTematico.class)
                    .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                    .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.is(evaluacionProcesoC.getKey()))
                    .queryList();

            indicadoresUi.setCampoAccionList(getListCampoAccion(campotematicos));

            indicadoresUiList.add(indicadoresUi);
        }
        listCamposTematicosCallBack.onLoadList(indicadoresUiList);

    }

    @Override
    public void showListIndicadores(String rubroProcesoKey, ObjectCallbackList<List<IndicadoresUi>> listObjectCallbackList) {
        Log.d(TAG, "showListIndicadores ; " + rubroProcesoKey);
    }

    @Override
    public boolean changeToogleCapacidad(int capacidadId, boolean toogle) {
        try {
            Competencia competencia = SQLite.select()
                    .from(Competencia.class)
                    .where(Competencia_Table.competenciaId.eq(capacidadId))
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

    private List<CamposAccionUi> getListCampoAccion(List<CampoTematico> campoTematicoList) {
        List<CamposAccionUi> campotematicoUipadresList = new ArrayList<>();


        for (CampoTematico itemCampoTematico : campoTematicoList) {
            CamposAccionUi campotematicoUi = new CamposAccionUi();
            campotematicoUi.setKey(String.valueOf(itemCampoTematico.getCampoTematicoId()));
            //campotematicoUi.setCapacidadUi(capacidadUi);
            //campotematicoUi.setCompetenciaUi(competenciaUi);
            //campotematicoUi.setIndicadorUi(indicadorUi);

            CampoTematico campoTematicoPadre = SQLite.select()
                    .from(CampoTematico.class)
                    .where(CampoTematico_Table.parentId.withTable().is(0))
                    .and(CampoTematico_Table.campoTematicoId.is(itemCampoTematico.getParentId()))
                    .querySingle();


           /* List<CampoTematico> campotematicos = SQLite.select()
                    .from(CampoTematico.class)
                    .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                    .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.is(rubroProcesoKey))
                    .queryList();*/
            /*CampoTematico campotematicosHijos = SQLite.select()
                    .from(CampoTematico.class)
                    .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                    .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.is(rubroProcesoKey))
                    .querySingle();
*/
            if (campoTematicoPadre != null) {
                CamposAccionUi campotematicoUiPadre = new CamposAccionUi();
                campotematicoUiPadre.setKey(String.valueOf(campoTematicoPadre.getCampoTematicoId()));
                campotematicoUiPadre.setTipo(CamposAccionUi.Tipo.PARENT);
                // campotematicoUiPadre.setCapacidadUi(capacidadUi);
                //campotematicoUiPadre.setCompetenciaUi(competenciaUi);
                //campotematicoUiPadre.setIndicadorUi(indicadorUi);
                int posicionPadre = campotematicoUipadresList.indexOf(campotematicoUiPadre);
                if (posicionPadre == -1) {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setNombre(campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }


                //for(CampoTematico campotematicosHijos: campotematicos){
                campotematicoUi.setTipo(CamposAccionUi.Tipo.CHILDREN);
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                //}
                campotematicoUiPadre.addCampoAccion(campotematicoUi);


            } else {
                // for(CampoTematico campotematicosHijos: campotematicos){
                // Log.d(TAG,"Default : "+campotematicosHijos.getTitulo()+ " / Defaultas : "+campoTematicoPadre.getTitulo());
                campotematicoUi.setNombre(itemCampoTematico.getTitulo());
                campotematicoUi.setTipo(CamposAccionUi.Tipo.DEFAULT);
                campotematicoUipadresList.add(campotematicoUi);
                //}


            }
        }


        List<CamposAccionUi> campotematicoUiList = new ArrayList<>();

        for (CamposAccionUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            campotematicoUiList.add(itemCampotematicoUiPadre);
            List<CamposAccionUi> campoAccionUiList = itemCampotematicoUiPadre.getCampoAccionUiList();
            if (campoAccionUiList == null) continue;
            campotematicoUiList.addAll(campoAccionUiList);
        }

        return campotematicoUiList;
    }


    /*@Override
    public void showListCamposTematicos(RubroProcesoUi rubroProcesoUi, ListCamposTematicosCallBack listCamposTematicosCallBack) {
        List<Object> objectList = new ArrayList<>();
        List<CamposTematicosUi> tematicosUis = new ArrayList<>();
        List<CampoTematico> campotematicos = SQLite.select()
                .from(CampoTematico.class)
                .innerJoin(RubroEvaluacionProcesoCampotematico.class)
                .on(CampoTematico_Table.campoTematicoId.withTable().eq(RubroEvaluacionProcesoCampotematico_Table.campoTematicoId.withTable()))
                .where(RubroEvaluacionProcesoCampotematico_Table.rubroEvalProcesoAndroidId.is(rubroProcesoUi.getId()))
                .queryList();
        for (CampoTematico procesoCampotematico : campotematicos) {
            tematicosUis.add(new CamposTematicosUi(procesoCampotematico.getCampoTematicoId(),
                    procesoCampotematico.getTitulo()));

        }
        objectList.addAll(tematicosUis);
        listCamposTematicosCallBack.onListCamposTematicos(objectList);
    }*/

    /*@Override
    public void showListDesempenioIcd(RubroProcesoUi rubroProcesoUi, ListDesempenioIcdsCallBack listDesempenioIcdsCallBack) {
        List<Object> objectList = new ArrayList<>();
        List<DesempenioIcdUi> desempenioIcds = new ArrayList<>();
        List<Icds> desempenioIcdList = SQLite.select()
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable().eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(RubroEvaluacionProceso.class)
                .on(RubroEvaluacionProceso_Table.desempenioIcdId.withTable().eq(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable().eq(RubroEvaluacionProceso_Table.tiporubroid.withTable()))
                .where(RubroEvaluacionProceso_Table.androidId.withTable().is(rubroProcesoUi.getId()))
                .and(Tipos_Table.tipoId.withTable().is(473))
                .queryList();
        for (Icds desempenioIcd : desempenioIcdList) {
            desempenioIcds.add(new DesempenioIcdUi(desempenioIcd.getDesempenioId(),
                    desempenioIcd.getTitulo()));

        }
        objectList.addAll(desempenioIcds);
        listDesempenioIcdsCallBack.onListDesempenioIcds(objectList);
    }*/


}
