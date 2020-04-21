package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class NotaUi extends Cell {
    String id;
    String nombre;
    double porcentaje;
    double nota;
    private boolean alumnoActivo;
    private int msj;
    private boolean publicar;
    private boolean publicarVisible;

    public int getMsj() {
        return msj;
    }

    public void setMsj(int msj) {
        this.msj = msj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean isAlumnoActivo() {
        return alumnoActivo;
    }

    public void setAlumnoActivo(boolean alumnoActivo) {
        this.alumnoActivo = alumnoActivo;
    }

    public void setPublicar(boolean publicar) {
        this.publicar = publicar;
    }

    public boolean isPublicar() {
        return publicar;
    }

    public boolean isPublicarVisible() {
        return publicarVisible;
    }

    public void setPublicarVisible(boolean publicarVisible) {
        this.publicarVisible = publicarVisible;
    }
}
