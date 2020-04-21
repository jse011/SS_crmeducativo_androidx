package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetCompetenciasCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetRecursoDidacticoCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetSesionCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.IcdsUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CampotematicoSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CapacidadSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CompetenciaSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.DesempenioIcdSesionAprendizajeModel;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class AprendizajeLocalDataSource implements AprendizajeDataSource {

    private final static String TAG = "AprenLocalDataSource";

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public List<EvidenciaUi> getEvidencias(int sesionAprenId) {
        List<ProductoAprendizajeEvento> productoAprendizajeEventos = SQLite.select(Utils.f_allcolumnTable(ProductoAprendizajeEvento_Table.ALL_COLUMN_PROPERTIES))
                .from(ProductoAprendizajeEvento.class)
                .innerJoin(ProductoEventoReferencia.class)
                .on(ProductoEventoReferencia_Table.productoAprendizajeId.withTable()
                        .eq(ProductoAprendizajeEvento_Table.productoAprendizajeId.withTable()))
                .where(ProductoEventoReferencia_Table.sesionAprendizajeId.withTable().eq(sesionAprenId))
                .queryList();

        List<EvidenciaUi> evidenciaUis = new ArrayList<>();
        int count = 0;
        for (ProductoAprendizajeEvento productoAprendizajeEvento: productoAprendizajeEventos){
            count++;
            EvidenciaUi evidenciaUi = new EvidenciaUi();
            evidenciaUi.setId(productoAprendizajeEvento.getProductoAprendizajeId());
            evidenciaUi.setTitulo(productoAprendizajeEvento.getTitulo());
            evidenciaUi.setNumeracion(count);
            evidenciaUis.add(evidenciaUi);
        }

        return evidenciaUis;
    }

    @Override
    public void getCompencias(int usuarioId, int sesionAprendizajeId, GetCompetenciasCallback callback) {

        LinkedHashSet<Object> objects = new LinkedHashSet<>();

        List<Competencia> competencias = CompetenciaSesionAprendizajeModel.SQLView()
                .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .getQuery(sesionAprendizajeId)
                .queryList();

        CompetenciaUi competenciaAnterioUi = null;

        for (Competencia itemCompetencias : competencias) {
            List<CampotematicoUi> campoTematicosList = new ArrayList<>();
            CompetenciaUi competenciaUi = new CompetenciaUi();
            competenciaUi.setCampotematicoUis(campoTematicosList);
            objects.add(competenciaUi);
            List<CompetenciaUi> capasidadesUis = competenciaUi.getCapasidadesUis();

            competenciaUi.setId(itemCompetencias.getCompetenciaId());
            competenciaUi.setTitulo(itemCompetencias.getNombre());
            competenciaUi.setTabla(CompetenciaUi.Tabla.COMPETENCIA);

            switch (itemCompetencias.getTipoId()) {
                case Competencia.COMPETENCIA_BASE:

                    competenciaUi.setTipoCompetencia(CompetenciaUi.Tipo.BASE);
                    break;

                case Competencia.COMPETENCIA_ENFQ:

                    competenciaUi.setTipoCompetencia(CompetenciaUi.Tipo.EFOQUE);
                    break;

                case Competencia.COMPETENCIA_TRANS:

                    competenciaUi.setTipoCompetencia(CompetenciaUi.Tipo.TRASVERSAL);
                    break;
            }

            Tipos tipos = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.is(itemCompetencias.getTipoId()))
                    .querySingle();
            if (tipos != null) {
                competenciaUi.setTipo(tipos.getNombre());
                competenciaUi.setTipoId(tipos.getTipoId());
            } else {
                competenciaUi.setTipo("Competencia");
                itemCompetencias.setTipoId(-1);
            }
            try {
                if (competenciaUi.getTipoId() == competenciaAnterioUi.getTipoId()) {
                    competenciaUi.setTipo(null);
                }
            } catch (Exception ignored) {
            } finally {
                competenciaAnterioUi = competenciaUi;
            }

            List<Competencia> capasidades = CapacidadSesionAprendizajeModel.SQLView()
                    .select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(itemCompetencias.getCompetenciaId(), sesionAprendizajeId)
                    .queryList();

            for (Competencia itemCapasidad : capasidades) {
                CompetenciaUi capasidadUi = new CompetenciaUi();
                capasidadesUis.add(capasidadUi);
                objects.add(capasidadUi);
                List<DesempenioUi> desempenioUis = capasidadUi.getDesempenioUis();

                capasidadUi.setId(itemCapasidad.getCompetenciaId());
                capasidadUi.setTitulo(itemCapasidad.getNombre());
                capasidadUi.setTabla(CompetenciaUi.Tabla.CAPASIDAD);

                List<DesempenioIcd> desempenioIcds = DesempenioIcdSesionAprendizajeModel.SQLView()
                        .select(Utils.f_allcolumnTable(DesempenioIcd_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(itemCapasidad.getCompetenciaId(), sesionAprendizajeId)
                        .queryList();
                List<Integer> desempenioIds = new ArrayList<>();
                for (DesempenioIcd desempenioIcd : desempenioIcds)
                        desempenioIds.add(desempenioIcd.getDesempenioId());


                List<Desempenio> desempenios = SQLite.select()
                        .from(Desempenio.class)
                        .where(Desempenio_Table.desempenioId.in(desempenioIds))
                        .queryList();

                boolean primerDesempenioIcd = true;
                for (Desempenio desempenio : desempenios) {
                    DesempenioUi desempenioUi = new DesempenioUi();
                    if (!primerDesempenioIcd) desempenioUi.setOcultaCabecera(true);
                    primerDesempenioIcd = false;
                    desempenioUis.add(desempenioUi);
                    objects.add(desempenioUi);
                    desempenioUi.setId(desempenio.getDesempenioId());
                    desempenioUi.setDescripcion(desempenio.getDescripcion());
                    List<IcdsUi> icdsList = new ArrayList<>();
                    desempenioUi.setIcdsUiList(icdsList);
                    for (DesempenioIcd itemDesempenioIcd : desempenioIcds) {

                            if (itemDesempenioIcd.getDesempenioId() == desempenio.getDesempenioId()) {
                                Icds icds = SQLite.select()
                                        .from(Icds.class)
                                        .where(Icds_Table.icdId.is(itemDesempenioIcd.getIcdId()))
                                        .querySingle();

                                if (icds == null) continue;
                                IcdsUi icdsUi = new IcdsUi();
                                icdsUi.setId(icds.getIcdId());
                                icdsUi.setTitulo(icds.getTitulo());
                                icdsList.add(icdsUi);

                                List<CampotematicoUi> campotematicoUis = getCampotematicoUI(CampotematicoSesionAprendizajeModel.SQLView()
                                        .select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                                        .getQuery(itemDesempenioIcd.getDesempenioIcdId(), sesionAprendizajeId)
                                        .queryList());

                                campoTematicosList.addAll(campotematicoUis);

                        }
                    }
                }
            }
        }

        callback.onRecursoLoad(new ArrayList<>(objects), new ArrayList<CampotematicoUi>());
    }

    private List<CampotematicoUi> getCampotematicoUI(List<CampoTematico> campoTematicoList) {
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


        List<CampotematicoUi> campotematicoUiList = new ArrayList<>();
        for (CampoTematico itemCampoTematicoPadre: campoTematicoListPadres){

            CampotematicoUi campotematicoPadreUi = new CampotematicoUi();
            campotematicoPadreUi.setId(itemCampoTematicoPadre.getCampoTematicoId());
            campotematicoPadreUi.setTipo(CampotematicoUi.Tipo.PARENT);
            campotematicoPadreUi.setDescripcion(itemCampoTematicoPadre.getTitulo());
            campotematicoUiList.add(campotematicoPadreUi);
            List<CampotematicoUi> campoAccionUis = new ArrayList<>();
            for (CampoTematico itemCampoTematico: campoTematicoList){
                if(itemCampoTematico.getParentId() == itemCampoTematicoPadre.getCampoTematicoId()){
                    CampotematicoUi campotematicoUi = new CampotematicoUi();
                    campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());
                    campotematicoUi.setDescripcion(itemCampoTematico.getTitulo());
                    campotematicoUi.setTipo(CampotematicoUi.Tipo.CHILDREN);
                    campoAccionUis.add(campotematicoUi);
                    campotematicoUiList.add(campotematicoUi);
                }
            }
            campotematicoPadreUi.setCampotematicoUiListHijo(campoAccionUis);

        }

        for (CampoTematico itemCampoTematico: campoTematicoSinHijo) {
            CampotematicoUi campotematicoUi = new CampotematicoUi();
            campotematicoUi.setId(itemCampoTematico.getCampoTematicoId());
            campotematicoUi.setTipo(CampotematicoUi.Tipo.DEFAULD);
            campotematicoUi.setDescripcion(itemCampoTematico.getTitulo());
            campotematicoUiList.add(campotematicoUi);
        }
        return campotematicoUiList;

    }

    @Override
    public void getSesion(int sesionAprendizajeId, GetSesionCallback callback) {

            SesionAprendizaje sesionAprendizaje = SQLite.select()
                    .from(SesionAprendizaje.class)
                    .where(SesionAprendizaje_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                    .querySingle();
            CardSesionUi sesionUi = new CardSesionUi();

            if (sesionAprendizaje != null) {
                sesionUi.setTitulo(sesionAprendizaje.getTitulo());
                sesionUi.setProposito(sesionAprendizaje.getProposito());
            }
            callback.onRecursoLoad(sesionUi);

    }

    @Override
    public void getRecursoDidactico(int sesionAprendizajeId, GetRecursoDidacticoCallback callback) {
        try {
            List<RecursosDidacticoUi> recursosDidacticoUis = new ArrayList<>();
            List<RecursoDidacticoEventoC> recursoDidacticoEventos = SQLite.select(Utils.f_allcolumnTable(RecursoDidacticoEventoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(RecursoDidacticoEventoC.class)
                    .innerJoin(RecursoReferenciaC.class)
                    .on(RecursoDidacticoEventoC_Table.key.withTable()
                            .eq(RecursoReferenciaC_Table.recursoDidacticoId.withTable()))
                    .where(RecursoReferenciaC_Table.sesionAprendizajeId.withTable()
                            .eq(sesionAprendizajeId))
                    .queryList();

            //            case 379:"Video";case 380:"Vinculo";case 397:"docx"case 398:"Imagen";case 399:"Audio"case 400:"xlsx"case 401:"pptx"case 402:"pdf"case 403:"Materiales"
            int count = 0;
            for (RecursoDidacticoEventoC recursoDidacticoEvento : recursoDidacticoEventos) {
                    RecursosDidacticoUi recursosUI = new RecursosDidacticoUi();
                    recursosUI.setRecursoId(recursoDidacticoEvento.getKey());
                    recursosUI.setNombreRecurso(recursoDidacticoEvento.getTitulo());
                    recursosUI.setDescripcion(recursoDidacticoEvento.getDescripcion());
                    recursosUI.setFechaCreacionRecuros(recursoDidacticoEvento.getFechaCreacion());
                    boolean isYoutube = false;
                    switch (recursoDidacticoEvento.getTipoId()) {
                        case RecursoDidacticoEventoC.TIPO_AUDIO:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.AUDIO);
                            break;
                        case RecursoDidacticoEventoC.TIPO_DIAPOSITIVA:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                            break;
                        case RecursoDidacticoEventoC.TIPO_DOCUMENTO:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                            break;
                        case RecursoDidacticoEventoC.TIPO_HOJA_CALCULO:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                            break;
                        case RecursoDidacticoEventoC.TIPO_IMAGEN:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                            break;
                        case RecursoDidacticoEventoC.TIPO_PDF:
                            recursosUI.setTipoFileU(RepositorioTipoFileU.PDF);
                            break;
                        case RecursoDidacticoEventoC.TIPO_VIDEO:
                            isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursoDidacticoEvento.getUrl()));
                            if (!isYoutube) {
                                isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursosUI.getDescripcion()));
                                if (isYoutube) {
                                    recursosUI.setUrl(recursosUI.getDescripcion());
                                }
                            }
                            if (isYoutube) {
                                recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                                recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                                recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                                recursosUI.setTipoFileU(RepositorioTipoFileU.YOUTUBE);
                            } else {
                                recursosUI.setTipoFileU(RepositorioTipoFileU.VIDEO);
                            }
                            break;
                        case RecursoDidacticoEventoC.TIPO_VINCULO:
                            recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                            recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                            recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                            recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                            break;

                    }

                    Log.d(TAG, "archivo:( " + recursoDidacticoEvento.getUrl());
                    if (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_AUDIO ||
                            recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DIAPOSITIVA ||
                            recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DOCUMENTO ||
                            recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_HOJA_CALCULO ||
                            recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_IMAGEN ||
                            recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_PDF ||
                            (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO
                                    && !isYoutube)
                    ) {

                        Archivo archivo = SQLite.select(Utils.f_allcolumnTable(Archivo_Table.ALL_COLUMN_PROPERTIES))
                                .from(Archivo.class)
                                .innerJoin(RecursoArchivo.class)
                                .on(Archivo_Table.key.withTable().eq(RecursoArchivo_Table.archivoId.withTable()))
                                .where(RecursoArchivo_Table.recursoDidacticoId.withTable().eq(recursoDidacticoEvento.getKey()))
                                .querySingle();

                        Log.d(TAG, "archivo:(");
                        if (archivo != null) {
                            Log.d(TAG, "great");
                            recursosUI.setArchivoId(archivo.getKey());
                            recursosUI.setNombreArchivo(archivo.getNombre());
                            recursosUI.setPath(archivo.getLocalpath());
                            recursosUI.setUrl(archivo.getPath());
                            recursosUI.setFechaCreacionRecuros(archivo.getFechaCreacion());
                            //recursosUI.setDescripcion(recursoDidacticoEvento.getDescripcion());
                            if (TextUtils.isEmpty(archivo.getLocalpath())) {
                                recursosUI.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                            } else {
                                recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                            }
                            recursosUI.setUrl(archivo.getPath());
                            recursosUI.setPath(archivo.getLocalpath());
                        } else if (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO) {
                            recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                            recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                            recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                            recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                        }

                    } else {
                        String url = recursoDidacticoEvento.getUrl();
                        if (TextUtils.isEmpty(url)) url = recursoDidacticoEvento.getDescripcion();
                        recursosUI.setUrl(url);
                    }
                    recursosDidacticoUis.add(recursosUI);

            }
            callback.onRecursoLoad(recursosDidacticoUis);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }


    }
}
