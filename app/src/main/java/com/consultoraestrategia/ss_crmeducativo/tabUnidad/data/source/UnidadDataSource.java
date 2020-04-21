package com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source;

import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import java.util.List;

public interface UnidadDataSource {

    interface Callback<T> {;
        void onLoad(boolean success ,T item);
    }

    void getCompentenciasCapacidades(int unidadId, int tipoCompetenciaId,int calendarioPeriodoId,Callback<List<CompetenciaUi>> callback);

    void getCamposAccion(int unidadId, int tipoCompetenciaId,int calendarioPeriodoId,Callback<List<CampoAccionUi>> callback);

    void getProductosUnidad(int unidadId, int calendarioPeriodoId, Callback<List<ProductoUi>> callback);

    void getSituacion(int unidadId, int entidadId,Callback<List<SituacionUi>> callback);
    List<IndicadorUi> getIndicadoresXunidad(int unidadAprendizajeId);
    void getRubricasXunidad(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<RubricaUi>> callback);
    void getRubrosXcompetencias(int unidadAprendizajeId, int cargaCursoId, int calendarioPeriodoId, Callback<List<Object>> callback);
    void getRecursoDidactico(int unidadAprendizajeId, Callback<List<RecursosDidacticoUi>> callback);
    void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback);
}
