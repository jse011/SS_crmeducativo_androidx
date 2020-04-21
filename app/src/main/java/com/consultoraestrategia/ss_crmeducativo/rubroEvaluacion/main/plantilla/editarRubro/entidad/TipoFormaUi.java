package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad;

import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;

import java.util.ArrayList;
import java.util.List;

public class TipoFormaUi {
    private int id;
    private String nombre;

    public TipoFormaUi() {
    }

    public TipoFormaUi(int id, String nombre) {
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

    public static List<TipoFormaUi> transformFormaEvaluacion(List<Tipos> tiposList) {
        List<TipoFormaUi> tipoFormaUiList = new ArrayList<>();
        for (Tipos tipo : tiposList) {
            TipoFormaUi tipoFormaUi = new TipoFormaUi(
                    tipo.getTipoId(),
                    tipo.getNombre()
            );
            tipoFormaUiList.add(tipoFormaUi);
        }
        return tipoFormaUiList;
    }
}
