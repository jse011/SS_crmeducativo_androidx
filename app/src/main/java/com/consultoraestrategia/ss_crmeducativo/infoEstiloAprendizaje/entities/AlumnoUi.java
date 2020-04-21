package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities;

import java.util.List;

public class AlumnoUi {
    private String nombre;
    private String apellido;
    private String imagen;
    private List<InstrumentoObservadoUi> instrumentoObservadoUiList;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<InstrumentoObservadoUi> getInstrumentoObservadoUiList() {
        return instrumentoObservadoUiList;
    }

    public void setInstrumentoObservadoUiList(List<InstrumentoObservadoUi> instrumentoObservadoUiList) {
        this.instrumentoObservadoUiList = instrumentoObservadoUiList;
    }
}
