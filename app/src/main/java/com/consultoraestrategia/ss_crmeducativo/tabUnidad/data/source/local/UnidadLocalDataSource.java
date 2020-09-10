package com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoCampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoCampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TecnicaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.TecnicaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.SilaboEventoCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class UnidadLocalDataSource implements UnidadDataSource {


    RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;

    public UnidadLocalDataSource(RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao) {
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
    }

    String TAG= UnidadLocalDataSource.class.getSimpleName();


    @Override
    public void getCompentenciasCapacidades(int unidadId, int tipoCompetenciaId, int calendarioPeriodoId,Callback<List<CompetenciaUi>> callback) {
        try{
            List<Integer> integerList = new ArrayList<>();
            List<Competencia> competenciaList = capacidadList(unidadId, calendarioPeriodoId, 0, new ArrayList<Integer>());

            for (Competencia capacidades:competenciaList)integerList.add(capacidades.getSuperCompetenciaId());
            List<CompetenciaUi> competenciaUis = new ArrayList<>();
            List<Competencia> competenciaListF = SQLite.select()
                    .from(Competencia.class)
                    .where(Competencia_Table.competenciaId.in(integerList))
                    .and(Competencia_Table.tipoId.eq(tipoCompetenciaId))
                    .queryList();
            for (Competencia competencia : competenciaListF){
                CompetenciaUi competenciaUi = new CompetenciaUi();
                competenciaUi.setNombre(competencia.getNombre());
                competenciaUi.setCompetenciaId(competencia.getCompetenciaId());

                List<CapacidadUi> capacidadUiList = new ArrayList<>();
                List<Competencia> capacidadList = capacidadList(unidadId, calendarioPeriodoId, competencia.getCompetenciaId(), new ArrayList<Integer>());

                for (Competencia capacidad : capacidadList){
                        CapacidadUi capacidadUi = new CapacidadUi();
                        capacidadUi.setCapacidadId(capacidad.getCompetenciaId());
                        capacidadUi.setCompetenciaId(competencia.getCompetenciaId());
                        capacidadUi.setNombre(capacidad.getNombre());
                        capacidadUi.setTipoId(capacidad.getTipoId());
                        capacidadUiList.add(capacidadUi);
                }
                competenciaUi.setCapacidadUis(capacidadUiList);
                competenciaUis.add(competenciaUi);
            }
            callback.onLoad(true, competenciaUis);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<CompetenciaUi>());
        }
    }

    @Override
    public void getCamposAccion(int unidadId,int tipoCompetenciaId, int calendarioPeriodoId, Callback<List<CampoAccionUi>> callback) {
        List<Integer> integerList = new ArrayList<>();
        List<Competencia> capacidadesUnidadList = capacidadList(unidadId, calendarioPeriodoId, 0, new ArrayList<Integer>());
        for (Competencia competencia:capacidadesUnidadList)integerList.add(competencia.getSuperCompetenciaId());
        List<Competencia> competenciaListF = SQLite.select()
                .from(Competencia.class)
                .where(Competencia_Table.competenciaId.in(integerList))
                .and(Competencia_Table.tipoId.eq(tipoCompetenciaId))
                .queryList();
        integerList.clear();
        for (Competencia competencia:competenciaListF)integerList.add(competencia.getCompetenciaId());
        List<Competencia> capacidadList = capacidadList(unidadId, calendarioPeriodoId, calendarioPeriodoId, integerList);
        integerList.clear();
        for (Competencia competencia:capacidadList)integerList.add(competencia.getCompetenciaId());

        List<CampoTematico> campoTematicoHijos = SQLite.select(CampoTematico_Table.ALL_COLUMN_PROPERTIES)
                .from(CampoTematico.class)
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.campoTematicoIcd.withTable().eq(CampoTematico_Table.campoTematicoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.unidadCompetenciaId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                .where(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(unidadId))
                .and(CompetenciaUnidad_Table.competenciaId.withTable().in(integerList))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                .groupBy(CampoTematico_Table.campoTematicoId)
                .queryList();

        Log.d("asdas", "asd"+getCompetencia(unidadId, calendarioPeriodoId, 347));
        List<Integer> campoIntegerList = new ArrayList<>();
        for (CampoTematico campoTematico : campoTematicoHijos){
            if (campoTematico.getParentId()==0)campoIntegerList.add(campoTematico.getCampoTematicoId());
            else campoIntegerList.add(campoTematico.getParentId());
        }
        List<CampoAccionUi> campoAccionUisPadre = new ArrayList<>();
        List<CampoTematico> campoTematicoPadreList = SQLite.select()
                .from(CampoTematico.class)
                .where(CampoTematico_Table.campoTematicoId.in(campoIntegerList))
                .queryList();
        for (CampoTematico campoTematico: campoTematicoPadreList){
            CampoAccionUi campoAccionUi = new CampoAccionUi();
            campoAccionUi.setCampoAccionId(campoTematico.getCampoTematicoId());
            campoAccionUi.setCampoAccionNombre(campoTematico.getTitulo());
            campoAccionUi.setTipo(CampoAccionUi.Tipo.PADRE);
            List<CampoAccionUi> campoAccionUiListHijo = new ArrayList<>();
            List<CampoTematico> campoTematicoList = SQLite.select()
                    .from(CampoTematico.class)
                    .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class)
                    .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.campoTematicoIcd.withTable().eq(CampoTematico_Table.campoTematicoId.withTable()))
                    .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                    .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                    .innerJoin(CompetenciaUnidad.class)
                    .on(CompetenciaUnidad_Table.unidadCompetenciaId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()))
                    .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                    .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()))
                    .innerJoin(Tipos.class)
                    .on(Tipos_Table.tipoId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                    .innerJoin(CalendarioPeriodo.class)
                    .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                    .where(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(unidadId))
                    .and(CompetenciaUnidad_Table.competenciaId.withTable().in(integerList))
                    .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                    .and(CampoTematico_Table.parentId.withTable().eq(campoTematico.getCampoTematicoId()))
                    .groupBy(CampoTematico_Table.campoTematicoId)
                    .queryList();
            for (CampoTematico campoTematicoHijo: campoTematicoList){
                CampoAccionUi campoAccionUiHijo = new CampoAccionUi();
                campoAccionUiHijo.setCampoAccionId(campoTematicoHijo.getCampoTematicoId());
                campoAccionUiHijo.setCampoAccionNombre(campoTematicoHijo.getTitulo());
                campoAccionUiHijo.setTipo(CampoAccionUi.Tipo.HIJO);
                campoAccionUiListHijo.add(campoAccionUiHijo);
            }
            campoAccionUi.setCampoAccionUis(campoAccionUiListHijo);
            campoAccionUisPadre.add(campoAccionUi);
        }
        callback.onLoad(true, campoAccionUisPadre);
    }


    @Override
    public void getProductosUnidad(int unidadId, int calendarioPeriodoId, Callback<List<ProductoUi>> callback) {
        Log.d("getProductosUnidad", "unidadId: "+unidadId+" cale:"+calendarioPeriodoId);
        int cantTotalCampos = getCompetencia(unidadId, calendarioPeriodoId, 0).size();
        List<Integer> camposIdsBase = getCompetencia(unidadId, calendarioPeriodoId, Competencia.COMPETENCIA_BASE);
        List<Integer> camposIdsTransversal = getCompetencia(unidadId, calendarioPeriodoId, Competencia.COMPETENCIA_TRANS);
        List<Integer> camposIdsEnfoque = getCompetencia(unidadId, calendarioPeriodoId, Competencia.COMPETENCIA_ENFQ);
        List<ProductoUi> productoUis = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        List<SesionAprendizaje> sesionAprendizajes = SQLite.select()
                .from(SesionAprendizaje.class)
                .where(SesionAprendizaje_Table.unidadAprendizajeId.eq(unidadId))
                .queryList();
        List<ProductoEventoReferencia> productoEventoReferencias = SQLite.select()
                .from(ProductoEventoReferencia.class)
                .where(ProductoEventoReferencia_Table.unidadAprendizajeId.eq(unidadId))
                .queryList();

        for (ProductoEventoReferencia productoEventoReferencia: productoEventoReferencias)integerList.add(productoEventoReferencia.getProductoAprendizajeId());
        List<ProductoAprendizajeEvento> productoAprendizajeEventos = SQLite.select(Utils.f_allcolumnTable(ProductoAprendizajeEvento_Table.ALL_COLUMN_PROPERTIES))
                .from(ProductoAprendizajeEvento.class)
                .where(ProductoAprendizajeEvento_Table.productoAprendizajeId.in(integerList))
                .queryList();
        for (ProductoAprendizajeEvento productoAprendizajeEvento : productoAprendizajeEventos){
            ProductoUi productoUi = new ProductoUi();
            productoUi.setProductoId(productoAprendizajeEvento.getProductoAprendizajeId());
            productoUi.setProductoNombre(productoAprendizajeEvento.getTitulo());
            productoUi.setDescripcion(productoAprendizajeEvento.getDescripcion());

            List<Integer> integerList1  = new ArrayList<>();
            List<ProductoEventoCampoTematico> list = SQLite.select()
                    .from(ProductoEventoCampoTematico.class)
                    .where(ProductoEventoCampoTematico_Table.productoAprendizaje.eq(productoAprendizajeEvento.getProductoAprendizajeId()))
                    .queryList();
            int tipo=0;
            for (ProductoEventoCampoTematico productoEventoCampoTematico: list){
                for(Integer integer: camposIdsBase)if(integer==productoEventoCampoTematico.getCampoTematico())integerList1.add(integer);
                if (integerList.size()>0)tipo=Competencia.COMPETENCIA_BASE;
                if (tipo==0){
                    for(Integer integer: camposIdsEnfoque)if(integer==productoEventoCampoTematico.getCampoTematico())integerList1.add(integer);
                    if (integerList.size()>0)tipo=Competencia.COMPETENCIA_TRANS;
                }if (tipo==0){
                    for(Integer integer: camposIdsTransversal)if(integer==productoEventoCampoTematico.getCampoTematico())integerList1.add(integer);
                    tipo=Competencia.COMPETENCIA_ENFQ;
                }
            }
            int result = 0;
            result=(integerList1.size()*100)/getCompetencia(unidadId, calendarioPeriodoId, tipo).size();
            productoUi.setAvance(result+" %");
            productoUis.add(productoUi);

        }
        callback.onLoad(true, productoUis);
    }



    @Override
    public void getSituacion(int unidadId,int entidadId, Callback<List<SituacionUi>> callback) {
        Log.d("getSituacion", "entdad: "+entidadId);

        boolean estado = true;

        while (estado){

            long cantidad = SQLite.selectCountOf()
                    .from(ParametroConfiguracion.class)
                    .where(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                    .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                    .count();

            if(cantidad==0){
                Entidad entidad = SQLite.select()
                        .from(Entidad.class)
                        .where(Entidad_Table.entidadId.eq(entidadId))
                        .querySingle();

                if(entidad==null||entidad.getParentId()==0){
                    estado = false;
                }else {
                    entidadId = entidad.getParentId();
                    Log.d(TAG,"entidadId: " + entidadId);
                }

            }else {
                estado = false;

            }
        }


        UnidadAprendizaje unidadAprendizaje = SQLite.select()
                .from(UnidadAprendizaje.class)
                .where(UnidadAprendizaje_Table.unidadAprendizajeId.eq(unidadId))
                .querySingle();




        ParametroConfiguracion contenidoPrincipal= SQLite.select()
                .from(ParametroConfiguracion.class)
                .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O1))
                .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                .querySingle();

        ParametroConfiguracion subcontenido= SQLite.select().from(ParametroConfiguracion.class)
                .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O2))
                .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                .querySingle();

        ParametroConfiguracion contenidoPrincipal2= SQLite.select().from(ParametroConfiguracion.class)
                .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O3))
                .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                .querySingle();

        ParametroConfiguracion subcontenido2= SQLite.select().from(ParametroConfiguracion.class)
                .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O4))
                .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                .querySingle();

        List<SituacionUi> situacionUIList = new ArrayList<>();
        if (unidadAprendizaje!=null){
            SituacionUi situacionUi = new SituacionUi();
            situacionUi.setSessiones(unidadAprendizaje.getNroSesiones());
            situacionUi.setSemana(unidadAprendizaje.getNroSesiones());
            situacionUi.setHoras(unidadAprendizaje.getNroHoras());
            situacionUi.setIdUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId());
            situacionUi.setDescripcionTipo(unidadAprendizaje.getSituacionSignificativa());

            if(contenidoPrincipal!=null)situacionUi.setTitulo(contenidoPrincipal.getParametro());
            if(subcontenido!=null)situacionUi.setSubtitulo(subcontenido.getParametro());


            situacionUi.setTipo(SituacionUi.TipoAprendizaje.SITUACION_SIG);
            situacionUi.setDescripcionSubtitulo(unidadAprendizaje.getDesafio());
            situacionUIList.add(situacionUi);

            SituacionUi situacionUiReto = new SituacionUi();
            situacionUiReto.setSessiones(unidadAprendizaje.getNroSesiones());
            situacionUiReto.setSemana(unidadAprendizaje.getNroSesiones());
            situacionUiReto.setHoras(unidadAprendizaje.getNroHoras());
            situacionUiReto.setIdUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId());
            situacionUiReto.setDescripcionTipo(unidadAprendizaje.getSituacionSignificativaComplementaria());


            if(contenidoPrincipal2!=null)situacionUiReto.setTitulo(contenidoPrincipal2.getParametro());
            if(subcontenido2!=null)situacionUiReto.setSubtitulo(subcontenido2.getParametro());

            situacionUiReto.setTipo(SituacionUi.TipoAprendizaje.IDEA_CLAVE);
            situacionUiReto.setDescripcionSubtitulo(unidadAprendizaje.getReto());
            situacionUIList.add(situacionUiReto);
        }

        Log.d(TAG,"situacionSize: " + situacionUIList.size());
        callback.onLoad(true, situacionUIList);
    }

    @Override
    public List<IndicadorUi> getIndicadoresXunidad(int unidadAprendizajeId) {
        List<IndicadorUi> indicadorUis= new ArrayList<>();

        List<Icds>icds= SQLite.select(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable().eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable().eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .where(UnidadAprendizaje_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId)).queryList();

        Log.d(TAG, "icds size "+ icds.size());
        int count=0;
        for(Icds indicador: icds){
            Log.d(TAG, "icd key  "+ indicador.getIcdId() + "undiad "+ unidadAprendizajeId);
            count++;
            IndicadorUi indicadorUi= new IndicadorUi();
            indicadorUi.setCount(count);
            indicadorUi.setNombre(indicador.getTitulo());
            indicadorUi.setInstrumento("-");
            indicadorUi.setTecnica("-");

            //asignar instrumento
            InstrumentoEvaluacion instrumentoEvaluacion= SQLite.select(Utils.f_allcolumnTable(InstrumentoEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                    .from(InstrumentoEvaluacion.class)
                    .innerJoin(UnidadEventoCompetenciaDesempenioIcdInstrumento.class)
                    .on(InstrumentoEvaluacion_Table.instrumentoevalid.withTable().eq(UnidadEventoCompetenciaDesempenioIcdInstrumento_Table.instrumentoEvalId.withTable()))
                    .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                    .on(UnidadEventoCompetenciaDesempenioIcdInstrumento_Table.unidadCompetenciaDesempenioIcdId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                    .innerJoin(CompetenciaUnidad.class)
                    .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable().eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                    .innerJoin(DesempenioIcd.class)
                    .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable().eq(DesempenioIcd_Table.desempenioIcdId.withTable()))
                    .innerJoin(Icds.class)
                    .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                    .where(Icds_Table.icdId.withTable().eq(indicador.getIcdId()))
                    .and(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId))
                    .querySingle();

            if(instrumentoEvaluacion!=null){
                //Log.d(TAG, "instrumentoEvaluacion  "+ instrumentoEvaluacion.getNombre());
                indicadorUi.setInstrumento(instrumentoEvaluacion.getNombre());
                //asignar tecnica
                TecnicaEvaluacion tecnicaEvaluacion= SQLite.select(Utils.f_allcolumnTable(TecnicaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                        .from(TecnicaEvaluacion.class)
                        .innerJoin(UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica.class)
                        .on(TecnicaEvaluacion_Table.tecnicaEvaluacionId.withTable().eq(UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica_Table.tecnicaEvaluacionId.withTable()))
                        .innerJoin(UnidadEventoCompetenciaDesempenioIcdInstrumento.class)
                        .on(UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica_Table.unidadCompetenciaDesempenioIcdInstrumentoId.withTable().eq(UnidadEventoCompetenciaDesempenioIcdInstrumento_Table.unidadCompetenciaDesempenioIcdInstrumentoId.withTable()))
                        .innerJoin(InstrumentoEvaluacion.class)
                        .on(UnidadEventoCompetenciaDesempenioIcdInstrumento_Table.instrumentoEvalId.withTable().eq(InstrumentoEvaluacion_Table.instrumentoevalid.withTable()))
                        .querySingle();

                if(tecnicaEvaluacion!=null){
                   // Log.d(TAG, "tecnicaEvaluacion  "+ tecnicaEvaluacion.getNombre());
                    indicadorUi.setTecnica(tecnicaEvaluacion.getNombre());
                }
            }

            indicadorUis.add(indicadorUi);
        }
        return indicadorUis;
    }

    @Override
    public void getRubricasXunidad(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<RubricaUi>> callback) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<RubricaUi>rubricaUis= new ArrayList<>();
            SilaboEvento silaboEvento = SilaboEventoCargaCursoModel.SQLView()
                    .select(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES))
                    .getQuery(cargaCursoId)
                    .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                    .querySingle(databaseWrapper);
            if(silaboEvento!=null)
            {
                List<RubroEvaluacionProcesoC> list = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL))
                        .and(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                        .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                        .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                        .and(RubroEvaluacionProcesoC_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId))
                        .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().asc())
                        .queryList(databaseWrapper);

                Log.d(TAG, "list rubricas  "+list.size() );

                int contador=0;
                for(RubroEvaluacionProcesoC rubro: list)
                {
                    contador++;

                    RubricaUi rubricaUi= new RubricaUi();
                    rubricaUi.setTipo(RubricaUi.Tipo.RUBRICA);
                    rubricaUi.setContador(contador);
                    rubricaUi.setIdRubrica(rubro.getKey());
                    rubricaUi.setTitulo(rubro.getTitulo());
                    rubricaUi.setAlias(rubro.getSubtitulo());
                    rubricaUi.setFecha(Utils.f_fecha_letras(rubro.getFechaCreacion()));
                    rubricaUi.setMedia(rubro.getPromedio());
                    rubricaUi.setDesviacionE(rubro.getDesviacionEstandar());
                    rubricaUi.setEstadoMensaje(rubro.getMsje());
                    rubricaUi= asignarOtrosDatos(rubricaUi, rubro, databaseWrapper);

                    //cantidad de evaluaciones rubros
                    List<RubroEvalRNPFormulaC> listaRubrosAsociados= rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(rubro.getKey());
                    int contadorEval=0;
                    List<Integer>integerList= new ArrayList<>();
                    for(RubroEvalRNPFormulaC eval: listaRubrosAsociados){
                        contadorEval++;
                        integerList.add(contadorEval);
                    }
                    rubricaUi.setCantEvalRubros(integerList);

                    rubricaUi.setHabilitado(false);
                    rubricaUis.add(rubricaUi);

                }

            }
            databaseWrapper.setTransactionSuccessful();
            callback.onLoad(true, rubricaUis);
        }
        catch (Exception e){
              e.printStackTrace();
            callback.onLoad(false, new ArrayList<RubricaUi>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }
    public RubricaUi asignarOtrosDatos(RubricaUi rubro, RubroEvaluacionProcesoC rubroProceso, DatabaseWrapper databaseWrapper ){

        //habllar forma
        Tipos tipoFormulacion = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.tipoId.is(rubroProceso.getFormaEvaluacionId()))
                .querySingle(databaseWrapper);
        if(tipoFormulacion!=null)rubro.setFormaEvaluacion(tipoFormulacion.getNombre());

        switch (rubroProceso.getFormaEvaluacionId()){
            case Tipos.INDIVIDUAL:
                rubro.setForma(RubricaUi.Forma.INDIVIDUAL);
                break;
            default:
                rubro.setForma(RubricaUi.Forma.GRUPAL);
                break;
        }

        T_RN_MAE_TIPO_EVALUACION tipoEval = SQLite.select()
                .from(T_RN_MAE_TIPO_EVALUACION.class)
                .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.is(rubroProceso.getTipoEvaluacionId()))
                .querySingle(databaseWrapper);
        if(tipoEval!=null)rubro.setTipoEvaluacion(tipoEval.getNombre());

        if(rubroProceso.getSesionAprendizajeId()!=0){
            rubro.setOrigen(RubricaUi.Origen.SESION);
        }else {
            rubro.setOrigen(RubricaUi.Origen.AREA);
        }

        RubroEvaluacionProcesoC tareaRubroEvaluacionProceso = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProceso.getKey()))
                .querySingle(databaseWrapper);

        if(tareaRubroEvaluacionProceso!=null&&!TextUtils.isEmpty(tareaRubroEvaluacionProceso.getTareaId())){
            rubro.setOrigen(RubricaUi.Origen.TAREA);
        }

        //publicado
        Where<EvaluacionProcesoC> evaluacionProcesoCWhere = SQLite.selectCountOf()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroProceso.getKey()));

        long cantidadEval = evaluacionProcesoCWhere
                .count(databaseWrapper);
        long cantidadEvalPublicado = evaluacionProcesoCWhere
                .and(EvaluacionProcesoC_Table.publicado.eq(1))
                .count(databaseWrapper);

        if(cantidadEval == cantidadEvalPublicado){
            rubro.setPublicado(RubricaUi.Publicado.TODOS);
        }else if(cantidadEvalPublicado == 0){
            rubro.setPublicado(RubricaUi.Publicado.NINGUNO);
        }else {
            rubro.setPublicado(RubricaUi.Publicado.PARCIAL);
        }
        return rubro;
    }

    @Override
    public void getRubrosXcompetencias(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<Object>> callback) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {

            databaseWrapper.beginTransaction();
            List<Competencia> competenciaList = SQLite.select()
                    .from(Competencia.class)
                    .innerJoin(CompetenciaUnidad.class)
                    .on(Competencia_Table.competenciaId.withTable().eq(CompetenciaUnidad_Table.competenciaId.withTable()))
                    .innerJoin(UnidadAprendizaje.class)
                    .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                    .innerJoin(SilaboEvento.class)
                    .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                    .where(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId)).
                            and(SilaboEvento_Table.cargaCursoId.withTable().eq(cargaCursoId)).queryList(databaseWrapper);
            HashSet<Object>objects= new LinkedHashSet<>();
            for(Competencia competencia : competenciaList){
                Log.d(TAG, "competencia"+ competencia.getNombre());
                Competencia padre=SQLite.select().from(Competencia.class)
                        .where(Competencia_Table.competenciaId.withTable().eq(competencia.getSuperCompetenciaId()))
                        .querySingle(databaseWrapper);

                CompetenciaUi competenciaUiPadre= new CompetenciaUi();
                if(padre!=null)
                {
                    competenciaUiPadre.setNombre(padre.getNombre());
                    competenciaUiPadre.setCompetenciaId(padre.getCompetenciaId());

                }

                CapacidadUi capacidadUi= new CapacidadUi();
                capacidadUi.setCapacidadId(competencia.getCompetenciaId());
                capacidadUi.setNombre(competencia.getNombre());

                //traer rubros
                List<RubricaUi>rubros= new ArrayList<>();

                SilaboEvento silaboEvento= SQLite.select().from(SilaboEvento.class)
                        .where(SilaboEvento_Table.cargaCursoId.withTable().eq(cargaCursoId)).querySingle(databaseWrapper);
                if(silaboEvento!=null)
                {
                    List<RubroEvaluacionProcesoC> rubrosList = SQLite.select()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                            .and(RubroEvaluacionProcesoC_Table.competenciaId.withTable().eq(competencia.getCompetenciaId()))
                            .and(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(silaboEvento.getSilaboEventoId()))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                            .and(RubroEvaluacionProcesoC_Table.unidadAprendizajeId.withTable().eq(unidadAprendizajeId))
                            .and(RubroEvaluacionProcesoC_Table.tiporubroid.withTable().eq(RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL))
                            .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.withTable().desc()).queryList(databaseWrapper);
                    Log.d(TAG, "Rubros de capacidad "+ rubrosList.size());
                    int cont=0;
                    for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubrosList)
                    {
                        cont++;
                        RubricaUi rubroProcesoUi = new RubricaUi();
                        rubroProcesoUi.setTipo(RubricaUi.Tipo.RUBRO);
                        rubroProcesoUi.setContador(cont);
                        rubroProcesoUi.setTipoNotaId(itemRubroEvaluacionProceso.getTipoNotaId());
                        rubroProcesoUi.setIdRubrica(itemRubroEvaluacionProceso.getKey());
                        rubroProcesoUi.setFecha(Utils.f_fecha_letras(itemRubroEvaluacionProceso.getFechaCreacion()));
                        rubroProcesoUi.setTitulo(itemRubroEvaluacionProceso.getTitulo());
                        rubroProcesoUi.setAlias(itemRubroEvaluacionProceso.getSubtitulo());
                        rubroProcesoUi.setEstadoMensaje(itemRubroEvaluacionProceso.getMsje());
                        //   rubroProcesoUi.setColorRubro(itemRubroEvaluacionProceso.getTipoColorRubroProceso());
                        rubroProcesoUi.setMedia(itemRubroEvaluacionProceso.getPromedio());
                        rubroProcesoUi.setDesviacionE(itemRubroEvaluacionProceso.getDesviacionEstandar());

                        //traer indicador
                        Icds indicador =SQLite.select(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                                .from(Icds.class)
                                .innerJoin(DesempenioIcd.class)
                                .on(Icds_Table.icdId.withTable().eq(DesempenioIcd_Table.icdId.withTable()))
                                .where(DesempenioIcd_Table.desempenioIcdId.withTable().eq(itemRubroEvaluacionProceso.getDesempenioIcdId()))
                                .querySingle(databaseWrapper);

                        if(indicador!=null){
                            Log.d(TAG, "indicador "+ indicador.getTitulo());
                            IndicadorUi indicadorUi= new IndicadorUi();
                            indicadorUi.setNombre(indicador.getTitulo());
                            switch (competencia.getTipoId()){
                                case Competencia.COMPETENCIA_BASE:
                                    indicadorUi.setTipo(IndicadorUi.Tipo.BASE);
                                    break;
                                case Competencia.COMPETENCIA_ENFQ:
                                    indicadorUi.setTipo(IndicadorUi.Tipo.ENFOQUE);
                                    break;
                                case Competencia.COMPETENCIA_TRANS:
                                    indicadorUi.setTipo(IndicadorUi.Tipo.TRANSVERSAL);
                                    break;
                            }
                            rubroProcesoUi.setIndicadorUi(indicadorUi);
                        }

                        rubroProcesoUi= asignarOtrosDatos(rubroProcesoUi, itemRubroEvaluacionProceso, databaseWrapper);
                        rubros.add(rubroProcesoUi);
                    }

                }
                if(padre!=null) objects.add(competenciaUiPadre);
                capacidadUi.setRubricaUis(rubros);
                objects.add(capacidadUi);
            }
            databaseWrapper.setTransactionSuccessful();
            callback.onLoad(true,new ArrayList<Object>(objects));
        } catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Object>());
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void getRecursoDidactico(int unidadAprendizajeId, Callback<List<RecursosDidacticoUi>> callback) {
        try {
            List<RecursosDidacticoUi> recursosDidacticoUis = new ArrayList<>();
            List<RecursoDidacticoEventoC> recursoDidacticoEventos = SQLite.select(Utils.f_allcolumnTable(RecursoDidacticoEventoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(RecursoDidacticoEventoC.class)
                    .innerJoin(RecursoReferenciaC.class)
                    .on(RecursoDidacticoEventoC_Table.key.withTable()
                            .eq(RecursoReferenciaC_Table.recursoDidacticoId.withTable()))
                    .where(RecursoReferenciaC_Table.unidadAprendizajeId.withTable()
                            .eq(unidadAprendizajeId))
                    .queryList();

            //            case 379:"Video";case 380:"Vinculo";case 397:"docx"case 398:"Imagen";case 399:"Audio"case 400:"xlsx"case 401:"pptx"case 402:"pdf"case 403:"Materiales"
            int count = 0;
            for (RecursoDidacticoEventoC recursoDidacticoEvento : recursoDidacticoEventos) {
                RecursosDidacticoUi recursosUI = new RecursosDidacticoUi();
                recursosUI.setRecursoId(recursoDidacticoEvento.getRecursoDidacticoId());
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
                        recursosUI.setArchivoId(archivo.getArchivoId());
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
            callback.onLoad(true, recursosDidacticoUis);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<RecursosDidacticoUi>());
        }
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {
        Archivo archivo = SQLite.select()
                .from(Archivo.class)
                .where(Archivo_Table.archivoId.eq(archivoId))
                .querySingle();
        if (archivo != null) {
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            booleanCallback.onLoad(success, success);
        } else {
            booleanCallback.onLoad(false, false);
        }
    }

    List<Competencia> capacidadList(int unidadAprendizajeId, int calendarioPeriodoId, int competenciaId, List<Integer> integers){
        List<Competencia> capacidadList = new ArrayList<>();
        Where<Competencia> capacidadesList = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.competenciaId.withTable().eq(Competencia_Table.competenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable().eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(Tipos_Table.tipoId.withTable().eq(CalendarioPeriodo_Table.tipoId.withTable()))
                .where(UnidadAprendizaje_Table.unidadAprendizajeId.withTable().is(unidadAprendizajeId))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPeriodoId));

        if (competenciaId==0)capacidadesList.groupBy(Competencia_Table.superCompetenciaId);
        else {
            if (integers.size()==0)capacidadesList.and(Competencia_Table.superCompetenciaId.eq(competenciaId));
            else capacidadesList.and(Competencia_Table.superCompetenciaId.in(integers));
        }
        capacidadList = capacidadesList.queryList();
        return capacidadList;
    }

    List<Integer> getCompetencia(int unidadAprendizajeId, int calendarioPeriodoId, int tipoId){
        List<Integer> integerList = new ArrayList<>();
        List<Competencia> capacidadesUnidadList = capacidadList(unidadAprendizajeId, calendarioPeriodoId, 0, new ArrayList<Integer>());
        for (Competencia competencia:capacidadesUnidadList)integerList.add(competencia.getSuperCompetenciaId());
        Where<Competencia> competenciaListF = SQLite.select()
                .from(Competencia.class)
                .where(Competencia_Table.competenciaId.in(integerList));
        if (tipoId!=0) competenciaListF.and(Competencia_Table.tipoId.eq(tipoId));
        integerList.clear();
        for (Competencia competencia:competenciaListF.queryList())integerList.add(competencia.getCompetenciaId());
        List<Competencia> capacidadList = capacidadList(unidadAprendizajeId, calendarioPeriodoId, calendarioPeriodoId, integerList);
        integerList.clear();
        for (Competencia competencia:capacidadList)integerList.add(competencia.getCompetenciaId());

        List<Integer> cant = new ArrayList<>();
        List<CampoTematico> campoTematicoHijos = SQLite.select(CampoTematico_Table.ALL_COLUMN_PROPERTIES)
                .from(CampoTematico.class)
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.campoTematicoIcd.withTable().eq(CampoTematico_Table.campoTematicoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.unidadCompetenciaId.withTable().eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                .where(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().eq(unidadAprendizajeId))
                .and(CompetenciaUnidad_Table.competenciaId.withTable().in(integerList))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                .groupBy(CampoTematico_Table.campoTematicoId)
                .queryList();
        for (CampoTematico campoTematico: campoTematicoHijos){
            if (campoTematico.getParentId()!=0)cant.add(campoTematico.getCampoTematicoId());
            else{
                List<CampoTematico> campoTematicoList = SQLite.select()
                        .from(CampoTematico.class)
                        .where(CampoTematico_Table.parentId.eq(campoTematico.getCampoTematicoId()))
                        .queryList();
                if (campoTematicoList.size()==0)cant.add(campoTematico.getCampoTematicoId());
            }
        }
        return cant;
    }
}
