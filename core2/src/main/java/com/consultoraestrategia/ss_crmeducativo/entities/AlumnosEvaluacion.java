package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by kelvi on 24/02/2017.
 */

public class AlumnosEvaluacion {
    private int id;
    private Persona persona;
    private String nombres;
    private String imagen;
    private List<EvaluacionResultadoC> evaluacionResultadoList;


    public AlumnosEvaluacion(Persona persona, String imagen, List<EvaluacionResultadoC> evaluacionResultadoList) {
        this.persona = persona;
        this.imagen = imagen;
        this.evaluacionResultadoList = evaluacionResultadoList;
    }

    public AlumnosEvaluacion(int id, String nombres, String imagen, List<EvaluacionResultadoC> evaluacionResultadoList) {
        this.id = id;
        this.nombres = nombres;
        this.imagen = imagen;
        this.evaluacionResultadoList = evaluacionResultadoList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<EvaluacionResultadoC> getEvaluacionResultadoList() {
        return evaluacionResultadoList;
    }

    public void setEvaluacionResultadoList(List<EvaluacionResultadoC> evaluacionResultadoList) {
        this.evaluacionResultadoList = evaluacionResultadoList;
    }

    @Override
    public String toString() {
        return "AlumnosEvaluacion{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", imagen='" + imagen + '\'' +
                ", evaluacionResultadoList=" + evaluacionResultadoList +
                '}';
    }
}
