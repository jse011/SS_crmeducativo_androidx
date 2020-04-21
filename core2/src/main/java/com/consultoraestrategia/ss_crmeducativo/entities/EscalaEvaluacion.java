package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by kike on 6/11/2017.
 */

@Table(database = AppDatabase.class)
public class EscalaEvaluacion extends BaseModel {
    @PrimaryKey
    @Column
    private int escalaEvaluacionId;
    @Column
    private String nombre;
    @Column
    private int valorMinimo;
    @Column
    private int valorMaximo;
    @Column
    private int estado;
    @Column
    private int entidadId;

    public EscalaEvaluacion() {
    }

    public EscalaEvaluacion(int escalaEvaluacionId, String nombre, int valorMinimo, int valorMaximo, int estado, int entidadId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
        this.nombre = nombre;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.estado = estado;
        this.entidadId = entidadId;
    }

    public int getEscalaEvaluacionId() {
        return escalaEvaluacionId;
    }

    public void setEscalaEvaluacionId(int escalaEvaluacionId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }
}
