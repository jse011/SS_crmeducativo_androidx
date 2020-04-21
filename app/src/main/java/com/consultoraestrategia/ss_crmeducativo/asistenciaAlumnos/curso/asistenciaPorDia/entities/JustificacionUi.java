package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;

import java.util.List;

public class JustificacionUi {

    private String  Id;
    private TipoUi tipo;
    private String razon;
    private List<TipoUi>tipoUiList;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public TipoUi getTipo() {
        return tipo;
    }

    public void setTipo(TipoUi tipo) {
        this.tipo = tipo;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public List<TipoUi> getTipoUiList() {
        return tipoUiList;
    }

    public void setTipoUiList(List<TipoUi> tipoUiList) {
        this.tipoUiList = tipoUiList;
    }
}
