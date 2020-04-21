package com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CampoTematicoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.desempenioIcdDao.DesempenioIcdDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.RubroEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.InfoRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.EscalaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoCamTemEn;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.UploadFile;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class InfoRubroLocalDataSource implements InfoRubroDataSource {

    private RubroEvaluacionDao rubroProcesoDao;
    private IndicadorDao indicadorDao;
    private DesempenioIcdDao desempenioIcdDao;
    private CampoTematicoDao campoTematicoDao;
    private CompetenciaDao competenciaDao;
    private TipoNotaDao tipoNotaDao;

    private final static int SELECTOR_ICONOS = 409;
    private final static int SELECTOR_NUMERICO = 411;
    private final static int SELECTOR_VALORES = 412;
    private final static int VALOR_NUMERICO = 410;

    public InfoRubroLocalDataSource(RubroEvaluacionDao rubroProcesoDao, IndicadorDao indicadorDao, DesempenioIcdDao desempenioIcdDao, CampoTematicoDao campoTematicoDao, CompetenciaDao competenciaDao, TipoNotaDao tipoNotaDao) {
        this.rubroProcesoDao = rubroProcesoDao;
        this.indicadorDao = indicadorDao;
        this.desempenioIcdDao = desempenioIcdDao;
        this.campoTematicoDao = campoTematicoDao;
        this.competenciaDao = competenciaDao;
        this.tipoNotaDao = tipoNotaDao;
    }

    @Override
    public void getInformacionRubro(String rubroEvalProcesoId, Callback<RubroEvalProcesoUi> callback) {

        Log.d("tamaniodeLIsta", "as"+rubroEvalProcesoId + " a " );
        try {
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroProcesoDao.get(rubroEvalProcesoId);
            Competencia capacidad = competenciaDao.getCompetenciaRubro(rubroEvalProcesoId);
            Competencia competencia = competenciaDao.get(capacidad.getSuperCompetenciaId());
            DesempenioIcd desempenioIcd = desempenioIcdDao.get(rubroEvaluacionProcesoC.getDesempenioIcdId());
            Desempenio desempenio = SQLite.select()
                    .from(Desempenio.class)
                    .where(Desempenio_Table.desempenioId.eq(desempenioIcd.getDesempenioId()))
                    .querySingle();

            Icds icds = indicadorDao.get(desempenioIcd.getIcdId());
            List<CriterioRubroEvaluacionC> criterioRubroEvaluacionC = rubroProcesoDao.getCriterio(rubroEvalProcesoId);
            List<CampoTematico> campoTematicos = campoTematicoDao.getCampotematicoRubro(rubroEvalProcesoId);

            TipoNotaC tipoNotaC = SQLite.select()
                    .from(TipoNotaC.class)
                    .where(TipoNotaC_Table.key.eq(rubroEvaluacionProcesoC.getTipoNotaId()))
                    .querySingle();


            assert tipoNotaC != null;
            TipoNotaUi tipoNotaUi = getCompletoTipoNotaUi(tipoNotaC, criterioRubroEvaluacionC);

            RubroEvalProcesoUi rubroEvalProcesoUi = new RubroEvalProcesoUi();
            rubroEvalProcesoUi.setId(rubroEvaluacionProcesoC.getKey());
            rubroEvalProcesoUi.setTitulo(rubroEvaluacionProcesoC.getTitulo());
            rubroEvalProcesoUi.setSubtitulo(rubroEvalProcesoUi.getSubtitulo());
            rubroEvalProcesoUi.setTipoNotaUi(tipoNotaUi);

            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(competencia.getCompetenciaId());
            competenciaUi.setTitulo(competencia.getNombre());
            switch (competencia.getTipoId()){
                case Competencia.COMPETENCIA_BASE:
                    competenciaUi.setTipo(CompetenciaUi.Tipo.COMPETENCIA_BASE);
                    break;
                case Competencia.COMPETENCIA_ENFQ:
                    competenciaUi.setTipo(CompetenciaUi.Tipo.COMPETENCIA_ENFQ);
                    break;
                case Competencia.COMPETENCIA_TRANS:
                    competenciaUi.setTipo(CompetenciaUi.Tipo.COMPETENCIA_TRANS);
                    break;
            }

            CapacidadUi capacidadUi = new CapacidadUi();
            capacidadUi.setId(capacidad.getCompetenciaId());
            capacidadUi.setTitulo(capacidad.getNombre());
            capacidadUi.setCompetenciaUi(competenciaUi);

            rubroEvalProcesoUi.setCapacidadUi(capacidadUi);

            DesempenioUi desempenioUi = new DesempenioUi();
            desempenioUi.setId(desempenio.getDesempenioId());
            desempenioUi.setDescripcion(desempenio.getDescripcion());

            rubroEvalProcesoUi.setDesempenioUi(desempenioUi);

            IndicadorUi indicadorUi = new IndicadorUi();
            indicadorUi.setId(icds.getIcdId());
            indicadorUi.setTitulo(icds.getTitulo());
            indicadorUi.setAlias(icds.getAlias());
            indicadorUi.setSubtitulo(icds.getAlias());
            indicadorUi.setDescripcion(desempenioIcd.getDescripcion());
            indicadorUi.setUrl(desempenioIcd.getUrl());

            switch (desempenioIcd.getTipoId()){
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

            desempenioUi.setIndicadorUi(indicadorUi);



            List<CampoTematicoUi> campoTematicoUiList = getListCampoAccion(campoTematicos);
            rubroEvalProcesoUi.setCampoTematicoUiList(campoTematicoUiList);

            callback.onLoad(true, rubroEvalProcesoUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }



    }

    private List<CampoTematicoUi> getListCampoAccion(List<CampoTematico> campoTematicoList) {
        List<CampoTematicoUi> campotematicoUipadresList = new ArrayList<>();


        for (CampoTematico itemCampoTematico : campoTematicoList) {
            CampoTematicoUi campotematicoUi = new CampoTematicoUi();
            campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());

            CampoTematico campoTematicoPadre = SQLite.select()
                    .from(CampoTematico.class)
                    .where(CampoTematico_Table.parentId.withTable().is(0))
                    .and(CampoTematico_Table.campoTematicoId.is(itemCampoTematico.getParentId()))
                    .querySingle();

            if (campoTematicoPadre != null) {
                CampoTematicoUi campotematicoUiPadre = new CampoTematicoUi();
                campotematicoUiPadre.setId(campoTematicoPadre.getCampoTematicoId());
                campotematicoUiPadre.setTipoCampoTematicoE(TipoCamTemEn.PARENT);
                int posicionPadre = campotematicoUipadresList.indexOf(campotematicoUiPadre);
                if (posicionPadre == -1) {
                    campotematicoUiPadre.setTitulo(campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setTitulo(campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }
                campotematicoUi.setBinieta("- ");
                campotematicoUi.setTipoCampoTematicoE(TipoCamTemEn.CHILDREN);
                campotematicoUi.setTitulo(itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampoAccion(campotematicoUi);

            } else {
                campotematicoUi.setTitulo(itemCampoTematico.getTitulo());
                campotematicoUi.setTipoCampoTematicoE(TipoCamTemEn.DEFAULT);
                campotematicoUipadresList.add(campotematicoUi);

            }
        }
        int count = 0;
        Set<CampoTematicoUi> campotematicoUiList = new LinkedHashSet<>();
        for (CampoTematicoUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            count ++;
            String binieta = count + ". ";
            itemCampotematicoUiPadre.setBinieta(binieta);
            campotematicoUiList.add(itemCampotematicoUiPadre);
            if ( itemCampotematicoUiPadre.getCampoTematicoUiList() == null) continue;
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampoTematicoUiList());
        }

        return new ArrayList<>(campotematicoUiList);
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList(TipoNotaUi tipoNotaUi, List<CriterioRubroEvaluacionC> criterioRubroEvaluacionList){

        List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();
        List<ValorTipoNotaC> valorTipoNotaList = SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.is(tipoNotaUi.getId()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico.desc())
                .queryList();


        for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaList){
            ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
            valorTipoNotaUi.setId(itemValorTipoNota.getValorTipoNotaId());
            valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
            valorTipoNotaUi.setIcono(itemValorTipoNota.getIcono());
            valorTipoNotaUi.setTitulo(itemValorTipoNota.getTitulo());
            valorTipoNotaUi.setAlias(itemValorTipoNota.getAlias());
            valorTipoNotaUiList.add(valorTipoNotaUi);
        }

        if (criterioRubroEvaluacionList.size()>0){
            for (ValorTipoNotaUi valorTipoNotaUi: valorTipoNotaUiList){
                for (CriterioRubroEvaluacionC c: criterioRubroEvaluacionList){
                    if (valorTipoNotaUi.getId().equals(c.getValorTipoNotaId())){
                        valorTipoNotaUi.setDetalle(c.getDescripcion());
                    }
                }
            }
        }

        return valorTipoNotaUiList;
    }

    private TipoNotaUi getCompletoTipoNotaUi(TipoNotaC tipoNotaC, List<CriterioRubroEvaluacionC> criterioRubroEvaluacionList){

        EscalaEvaluacion escalaEvaluacion = SQLite.select()
                .from(EscalaEvaluacion.class)
                .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotaC.getEscalaEvaluacionId()))
                .querySingle();
        EscalaUi escalaUi = new EscalaUi();
        if(escalaEvaluacion != null){
            escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
            escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
            escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
            escalaUi.setDescripcion(escalaEvaluacion.getNombre());
        }

        TipoUi tipoUi = new TipoUi();
        switch (tipoNotaC.getTipoId()){
            case SELECTOR_ICONOS:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_ICONOS);
                break;
            case SELECTOR_NUMERICO:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_NUMERICO);
                break;
            case SELECTOR_VALORES:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_VALORES);
                break;
            default:
                tipoUi.setTipo(TipoUi.Tipo.VALOR_NUMERICO);
                break;
        }

        TipoNotaUi tipoNotaUi = new TipoNotaUi(tipoNotaC.getTipoNotaId(),tipoUi,escalaUi);
        tipoNotaUi.setNombre(tipoNotaC.getNombre());
        switch (tipoUi.getTipo()){
            case SELECTOR_VALORES:
                tipoNotaUi.setValorTipoNotaUiList(getValorTipoNotaList(tipoNotaUi, criterioRubroEvaluacionList));
                break;
            case SELECTOR_ICONOS:
                tipoNotaUi.setValorTipoNotaUiList(getValorTipoNotaList(tipoNotaUi, criterioRubroEvaluacionList));
                break;
        }

        return tipoNotaUi;

    }


}
