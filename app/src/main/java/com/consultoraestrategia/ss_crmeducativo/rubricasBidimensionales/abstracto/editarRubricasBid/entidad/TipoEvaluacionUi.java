package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad;

import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;

import java.util.ArrayList;
import java.util.List;

public class TipoEvaluacionUi {

    private int id;
    private String nombre;

    public TipoEvaluacionUi() {
    }

    public TipoEvaluacionUi(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
    }


    public static List<TipoEvaluacionUi> transformTipoEvaluacion(List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacionList) {
        List<TipoEvaluacionUi> tipoFormaUis = new ArrayList<>();
        for (T_RN_MAE_TIPO_EVALUACION itemTipoEvaluacion : tipoEvaluacionList) {
            TipoEvaluacionUi tipoEvaluacionUi = new TipoEvaluacionUi();
            tipoEvaluacionUi.setId(itemTipoEvaluacion.getTipoEvaluacionId());
            tipoEvaluacionUi.setNombre(itemTipoEvaluacion.getNombre());
            tipoFormaUis.add(tipoEvaluacionUi);
        }
        return tipoFormaUis;
    }
}
