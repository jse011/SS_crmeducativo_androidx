package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/02/2018.
 */

@Parcel
public class EscalaEvaluacionUi implements Serializable {
    public int id;
    public String nombre;

    public int valorMinimo;

    public int valorMaximo;

    public int estado;

    public int entidadId;

    public EscalaEvaluacionUi() {
    }


    @ParcelConstructor
    public EscalaEvaluacionUi(int id, String nombre, int valorMinimo, int valorMaximo, int estado, int entidadId) {
        this.id = id;
        this.nombre = nombre;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.estado = estado;
        this.entidadId = entidadId;
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


    @Override
    public String toString() {
        return nombre;
    }


    public static List<EscalaEvaluacionUi> transform(List<EscalaEvaluacion> escalaEvaluacionList) {
        List<EscalaEvaluacionUi> escalaEvaluacionUiList = new ArrayList<>();
        for (EscalaEvaluacion escala :
                escalaEvaluacionList) {
            escalaEvaluacionUiList.add(transform(escala));
        }
        return escalaEvaluacionUiList;
    }

    public static EscalaEvaluacionUi transform(EscalaEvaluacion escala) {
        return new EscalaEvaluacionUi(
                escala.getEscalaEvaluacionId(),
                escala.getNombre(),
                escala.getValorMinimo(),
                escala.getValorMaximo(),
                escala.getEstado(),
                escala.getEntidadId()
        );
    }
}
