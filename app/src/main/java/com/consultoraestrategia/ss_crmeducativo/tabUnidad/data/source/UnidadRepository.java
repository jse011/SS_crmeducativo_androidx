package com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source;

import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import java.util.List;

    public class UnidadRepository implements UnidadDataSource {

    private UnidadLocalDataSource localDataSource;

    public UnidadRepository(UnidadLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }


    @Override
    public void getCompentenciasCapacidades(int unidadId, int tipoCompetenciaId, int calendarioPeriodoId,Callback<List<CompetenciaUi>> callback) {
        localDataSource.getCompentenciasCapacidades(unidadId, tipoCompetenciaId, calendarioPeriodoId,callback);
    }

    @Override
    public void getCamposAccion(int unidadId, int tipoCompetenciaId,int calendarioPeriodoId,Callback<List<CampoAccionUi>> callback) {
        localDataSource.getCamposAccion(unidadId, tipoCompetenciaId, calendarioPeriodoId,callback);
    }

    @Override
    public void getProductosUnidad(int unidadId, int calendarioPeriodoId, Callback<List<ProductoUi>> callback) {
        localDataSource.getProductosUnidad(unidadId, calendarioPeriodoId,callback);
    }

    @Override
    public void getSituacion(int unidadId, int entidadId, Callback<List<SituacionUi>> callback) {
        localDataSource.getSituacion(unidadId, entidadId, callback);
    }

        @Override
        public List<IndicadorUi> getIndicadoresXunidad(int unidadAprendizajeId) {
            return localDataSource.getIndicadoresXunidad(unidadAprendizajeId);
        }

        @Override
        public void getRubricasXunidad(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<RubricaUi>> callback) {
            localDataSource.getRubricasXunidad(unidadAprendizajeId, cargaCursoId, calendarioPeriodoId, callback );
        }

        @Override
        public void getRubrosXcompetencias(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<Object>> callback) {
            localDataSource.getRubrosXcompetencias(unidadAprendizajeId, cargaCursoId, calendarioPeriodoId, callback);
        }

        @Override
        public void getRecursoDidactico(int unidadAprendizajeId, Callback<List<RecursosDidacticoUi>> callback) {
            localDataSource.getRecursoDidactico(unidadAprendizajeId, callback);
        }

        @Override
        public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {
            localDataSource.updateSucessDowload(archivoId, path, booleanCallback);
        }
    }
