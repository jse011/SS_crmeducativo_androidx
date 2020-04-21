package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/02/2018.
 */

@Parcel
public class TipoUi implements Serializable {
    public int id;
    public String title;

    public TipoUi() {
    }

    public TipoUi(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static List<TipoUi> transform(List<Tipos> tiposList) {
        List<TipoUi> tipoUiList = new ArrayList<>();
        for (Tipos tipo :
                tiposList) {
            TipoUi tipoUi = new TipoUi(
                    tipo.getTipoId(),
                    tipo.getNombre()
            );
            tipoUiList.add(tipoUi);
        }
        return tipoUiList;
    }

    public static List<TipoUi> transformTipoEvaluacion(List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacionList) {
        List<TipoUi> tipoEvaluacionUis = new ArrayList<>();
        for (T_RN_MAE_TIPO_EVALUACION itemTipoEvaluacion : tipoEvaluacionList) {
            TipoUi tipoUi = new TipoUi();
            tipoUi.setId(itemTipoEvaluacion.getTipoEvaluacionId());
            tipoUi.setTitle(itemTipoEvaluacion.getNombre());
            tipoEvaluacionUis.add(tipoUi);
        }
        return tipoEvaluacionUis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoUi)) return false;

        TipoUi tipoUi = (TipoUi) o;

        return id == tipoUi.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
