package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class GrupoEvaluacionUi extends BodyCellViewUi {
    String Id;
    String name;
    List<NotaUi> notaUis;
    NotaUi notaUi;
    boolean Togle;
    List<AlumnosEvaluacionUi> alumnosEvaluacionUis;


    public GrupoEvaluacionUi() {
        notaUis = new ArrayList<>();
        alumnosEvaluacionUis = new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NotaUi> getNotaUis() {
        return notaUis;
    }

    public void setNotaUis(List<NotaUi> notaUis) {
        this.notaUis = notaUis;
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

    public void setNotaUi(NotaUi notaUi) {
        this.notaUi = notaUi;
    }

    public List<AlumnosEvaluacionUi> getAlumnosEvaluacionUis() {
        return alumnosEvaluacionUis;
    }

    public void setAlumnosEvaluacionUis(List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        this.alumnosEvaluacionUis = alumnosEvaluacionUis;
    }
    public void addAlumnosEvaluacionUi(AlumnosEvaluacionUi alumnosEvaluacionUi){
        if(alumnosEvaluacionUis != null){
            alumnosEvaluacionUis.add(alumnosEvaluacionUi);
        }
    }

    public boolean isTogle() {
        return Togle;
    }

    public void setTogle(boolean togle) {
        Togle = togle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrupoEvaluacionUi that = (GrupoEvaluacionUi) o;

        return Id != null ? Id.equals(that.Id) : that.Id == null;
    }

    @Override
    public int hashCode() {
        return Id != null ? Id.hashCode() : 0;
    }
}
