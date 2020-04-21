package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CampotematicoSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CampotematicoUnidadAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadUnidadAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaUnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.IcdsSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.IcdsUnidadEventoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class ListaIndicadoresLocalSource implements ListaIndicadoresDataSource {
    private String TAG = ListaIndicadoresLocalSource.class.getSimpleName();

    public ListaIndicadoresLocalSource() {
    }


    @Override
    public void getIndicadoresList(int sesionAprendizajeId, int nivel, int competenciaId, GetIndicadorListCallback callback) {
        List<CompetenciaUi> indicadorUis = getIndicadoresNivel3(competenciaId, sesionAprendizajeId);
        callback.onRecursoLoad(indicadorUis);
    }

    private List<CompetenciaUi> getIndicadoresNivel3(int competenciaId, int sesionAprendizajeId) {

        List<CompetenciaUi> competenciaUiList = new ArrayList<>();
        List<Competencia> competenciaList = CompetenciaSesionAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(sesionAprendizajeId, competenciaId)
                .queryList();
        for (Competencia competencia : competenciaList) {

            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(String.valueOf(competencia.getCompetenciaId()));
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

            competenciaUi.setTitulo(competencia.getNombre());
            List<Object> objectList = new ArrayList<>();

            List<Competencia> capacidadList = new ArrayList<>();
            if (competenciaId != 0) {
                Competencia itemcompetencia = SQLite.select().from(Competencia.class).where(Competencia_Table.competenciaId.eq(competenciaId)).querySingle();
                if (itemcompetencia != null) capacidadList.add(itemcompetencia);
            } else {
                capacidadList.addAll(CapacidadSesionAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(competencia.getCompetenciaId(), sesionAprendizajeId)
                        .queryList());
            }


            for (Competencia capacidad : capacidadList) {

                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(String.valueOf(capacidad.getCompetenciaId()));
                capacidadUi.setTitulo(capacidad.getNombre());
                objectList.add(capacidadUi);
                List<IndicadorQuery> icdss = IcdsSesionAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(
                                Icds_Table.icdId,
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
                                DesempenioIcd_Table.url)))
                        .getQuery(capacidad.getCompetenciaId(), sesionAprendizajeId,
                                Utils.f_allcolumnTable(Utils.f_allcolumnTable(
                                        Icds_Table.icdId,
                                        Icds_Table.desempenioId,
                                        DesempenioIcd_Table.desempenioIcdId)))
                        .queryCustomList(IndicadorQuery.class);
                Log.d(TAG, "Cantidad icdss: " + icdss.size());

                int count = 0;
                for (IndicadorQuery itemIcds : icdss) {
                    count++;
                    Desempenio desempenio = SQLite.select()
                            .from(Desempenio.class)
                            .where(Desempenio_Table.desempenioId.eq(itemIcds.getDesempenioId()))
                            .querySingle();
                    IndicadorUi indicadorUi = new IndicadorUi();
                    indicadorUi.setId(itemIcds.getDesempenioIcdId());
                    indicadorUi.setSelector(String.valueOf(count));
                    indicadorUi.setTitulo(itemIcds.getTitulo());
                    indicadorUi.setCapacidadUi(capacidadUi);
                    indicadorUi.setUrl(itemIcds.getUrl());
                    if(desempenio!=null)indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
                    indicadorUi.setAlias(itemIcds.getAlias());
                    indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
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
                    objectList.add(indicadorUi);
                    indicadorUi.setCampotematicoUis(getCampotematico(itemIcds.getDesempenioIcdId(), sesionAprendizajeId));
                }

            }
            competenciaUi.setItems(objectList);
            competenciaUiList.add(competenciaUi);
        }


        return competenciaUiList;
    }

    private List<CampotematicoUi> getCampotematico(int indicadorId, int sesionAprendizajeId) {

        List<CampoTematico> campoTematicos = CampotematicoSesionAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(indicadorId, sesionAprendizajeId)
                .queryList();


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
                    campotematicoUiPadre.setDescripcion(campotematicoUiPadre.getNumeracion() + " " + campoTematicoPadre.getTitulo());
                    campotematicoUipadresList.add(campotematicoUiPadre);
                } else {
                    campotematicoUiPadre.setNumeracion(posicionPadre + 1);
                    campotematicoUiPadre.setDescripcion(campotematicoUiPadre.getNumeracion() + " " + campoTematicoPadre.getTitulo());
                    campotematicoUiPadre = campotematicoUipadresList.get(posicionPadre);
                }
                campotematicoUi.setDisable(false);
                campotematicoUi.setHijo(true);
                campotematicoUi.setDescripcion(campotematicoUiPadre.getNumeracion() + "." + (campotematicoUiPadre.getCampotematicoUis().size() + 1) + " " + itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampotematicos(campotematicoUi);
                campotematicoUi.setCampotematicoUiPadre(campotematicoUiPadre);

            } else {
                campotematicoUi.setNumeracion(campotematicoUipadresList.size() + 1);
                campotematicoUi.setDescripcion(campotematicoUi.getNumeracion() + " " + itemCampoTematico.getTitulo());
                campotematicoUi.setDisable(false);
                campotematicoUi.setHijo(false);
                campotematicoUipadresList.add(campotematicoUi);
            }
        }


        List<CampotematicoUi> campotematicoUiList = new ArrayList<>();

        for (CampotematicoUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            campotematicoUiList.add(itemCampotematicoUiPadre);
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampotematicoUis());
        }


        return campotematicoUiList;
    }

    @Override
    public void getIndicadoresListSilabo(int silaboEventoId, int nivel, GetIndicadorListCallback callback) {
    }

    @Override
    public void getIndicadoresListSilaboCompetencia(int competenciaId, int silavoEventoId, int nivel, int calendarioPeriodoId, GetIndicadorListCallback callback) {
        Log.d(TAG, "competenciaId  " + competenciaId + " silavoEventoId " + silavoEventoId);
        List<CompetenciaUi> competenciaUiList = new ArrayList<>();
        List<Competencia> competenciaList = CompetenciaUnidadAprendizaje.SQlView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(silavoEventoId, calendarioPeriodoId, competenciaId)
                .queryList();

        for (Competencia competencia : competenciaList) {

            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setId(String.valueOf(competencia.getCompetenciaId()));
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
            competenciaUi.setTitulo(competencia.getNombre());
            List<Object> objectList = new ArrayList<>();

            List<Competencia> capacidadList = new ArrayList<>();
            if (competenciaId != 0) {
                Competencia itemcompetencia = SQLite.select().from(Competencia.class).where(Competencia_Table.competenciaId.eq(competenciaId)).querySingle();
                if (itemcompetencia != null) capacidadList.add(itemcompetencia);
            } else {
                capacidadList.addAll(CapacidadUnidadAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(silavoEventoId, calendarioPeriodoId, competencia.getCompetenciaId())
                        .queryList());
            }

            Log.d(TAG, "capacidadList size: " + capacidadList.size());
            objectList.clear();
            for (Competencia capacidad : capacidadList) {
                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(String.valueOf(capacidad.getCompetenciaId()));
                capacidadUi.setTitulo(capacidad.getNombre());
                // competenciaUi.setCapacidadUi(capacidadUi);
                objectList.add(capacidadUi);
                List<IndicadorQuery> icdss = IcdsUnidadEventoModel.SQLView()
                        .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(Utils.f_allcolumnTable(Icds_Table.icdId,
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
                                DesempenioIcd_Table.url))))
                        .getQuery(silavoEventoId, capacidad.getCompetenciaId(), calendarioPeriodoId,
                                Utils.f_allcolumnTable(Utils.f_allcolumnTable(Utils.f_allcolumnTable(Icds_Table.icdId,
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
                        .queryCustomList(IndicadorQuery.class);

                Log.d(TAG, "Cantidad icdss: " + icdss);
                int count = 0;
                for (IndicadorQuery itemIcds : icdss) {
                    count++;
                    Desempenio desempenio = SQLite.select()
                            .from(Desempenio.class)
                            .where(Desempenio_Table.desempenioId.eq(itemIcds.getDesempenioId()))
                            .querySingle();
                    IndicadorUi indicadorUi = new IndicadorUi();
                    indicadorUi.setId(itemIcds.getDesempenioIcdId());
                    indicadorUi.setSelector(String.valueOf(count));
                    indicadorUi.setTitulo(itemIcds.getTitulo());
                    indicadorUi.setCapacidadUi(capacidadUi);
                    indicadorUi.setCampotematicoUis(getCampotematicoSilavo(itemIcds.getDesempenioIcdId(), silavoEventoId, calendarioPeriodoId));
                    indicadorUi.setUrl(itemIcds.getUrl());
                    indicadorUi.setDescripcion(itemIcds.getDesempenioDesc());
                    indicadorUi.setAlias(itemIcds.getAlias());
                    if(desempenio!=null)indicadorUi.setDesempenioDesc(desempenio.getDescripcion());
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
                    objectList.add(indicadorUi);

                }
            }
            competenciaUi.setItems(objectList);
            competenciaUiList.add(competenciaUi);
        }
        for (CompetenciaUi c : competenciaUiList) {
            for (Object o : c.getItems()) {
                if (o instanceof IndicadorUi) {
                    IndicadorUi u = (IndicadorUi) o;
                    Log.d(TAG, "indixcador" + u.getTitulo() + " / " + u.getCapacidadUi().getId() + "  / " + u.getId());

                    for (CampotematicoUi campo : u.getCampotematicoUis())
                        Log.d(TAG, "CAMPOS " + campo.isChecked() + "  / " + campo.getDescripcion() + "/ " + campo.getId());
                } else {
                    CapacidadUi ca = (CapacidadUi) o;
                    Log.d(TAG, "capacidad" + ca.getId() + " / " + ca.getTitulo());
                }
            }
        }
        callback.onRecursoLoad(competenciaUiList);

    }

    @Override
    public void getCompetencia(int nivel, int competenciId, Callback<CompetenciaUi> callback) {

        CompetenciaUi competenciaUi = new CompetenciaUi();
        Competencia capacidad = SQLite.select()
                .from(Competencia.class)
                .where(Competencia_Table.competenciaId.is(competenciId))
                .querySingle();
        if (capacidad != null) {
            CapacidadUi capacidadUi = new CapacidadUi();
            capacidadUi.setTitulo(capacidad.getNombre());
            competenciaUi.setCapacidadUi(capacidadUi);

            Competencia competencia = SQLite.select()
                    .from(Competencia.class)
                    .where(Competencia_Table.competenciaId.is(capacidad.getSuperCompetenciaId()))
                    .querySingle();

            if (competencia != null) {
                competenciaUi.setTitulo(competencia.getNombre());
            }
        }

        callback.onSucces(competenciaUi);
    }

    private List<CampotematicoUi> getCampotematicoSilavo(int indicadorId, int silaboEventoId, int calendarioPeriodoId) {

        List<CampoTematico> campoTematicos = CampotematicoUnidadAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(silaboEventoId, indicadorId, calendarioPeriodoId)
                .queryList();


        List<CampotematicoUi> campotematicoUipadresList = new ArrayList<>();
        for (CampoTematico itemCampoTematico : campoTematicos) {
            Log.d(TAG, "campoTematicoPadre " + itemCampoTematico.getTitulo() + " / " + itemCampoTematico.getSilaboEventoId() + " / " + itemCampoTematico.getCampoTematicoId());

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
                campotematicoUi.setDescripcion(itemCampoTematico.getTitulo());
                campotematicoUiPadre.addCampotematicos(campotematicoUi);
                campotematicoUi.setCampotematicoUiPadre(campotematicoUiPadre);

            } else {
                campotematicoUi.setNumeracion(campotematicoUipadresList.size() + 1);
                //campotematicoUi.setDescripcion(campotematicoUi.getNumeracion() + " " + itemCampoTematico.getTitulo());
                campotematicoUi.setDescripcion(itemCampoTematico.getTitulo());
                campotematicoUi.setDisable(false);
                campotematicoUi.setHijo(false);
                campotematicoUipadresList.add(campotematicoUi);
            }

        }

        List<CampotematicoUi> campotematicoUiList = new ArrayList<>();

        for (CampotematicoUi itemCampotematicoUiPadre : campotematicoUipadresList) {
            campotematicoUiList.add(itemCampotematicoUiPadre);
            campotematicoUiList.addAll(itemCampotematicoUiPadre.getCampotematicoUis());
        }


        return campotematicoUiList;
    }
}
