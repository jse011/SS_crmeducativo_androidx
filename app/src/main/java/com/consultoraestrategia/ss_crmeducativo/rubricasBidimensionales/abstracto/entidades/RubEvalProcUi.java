package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades;

import org.parceler.Parcel;

/**
 * Created by CCIE on 19/03/2018.
 */
@Parcel
public class RubEvalProcUi {

    public int id;
    public String key;
    public String titulo;
    public double peso;


    public RubEvalProcUi() {
    }

    public RubEvalProcUi(int id, String titulo, double peso) {
        this.id = id;
        this.titulo = titulo;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
