package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */
@Parcel
public class CompetenciaUi {
    public int id;
    public int posicion;
    public String titulo;
    public List<CapacidadUi> capacidadUis;
    public int nivel;
    public int idCalendarioPeriodo;
    public ParametroDisenioUi parametroDisenioUi;

    public CompetenciaUi() {
        this.capacidadUis = new ArrayList<>();
    }

    public List<CapacidadUi> getCapacidadUis() {
        return capacidadUis;
    }

    public void setCapacidadUis(List<CapacidadUi> capacidadUis) {
        this.capacidadUis = capacidadUis;
    }

    public void addCapacidad(CapacidadUi capacidadUi){

        this.capacidadUis.add(capacidadUi);
    }

    public ParametroDisenioUi getParametroDisenioUi() {
        return parametroDisenioUi;
    }

    public void setParametroDisenioUi(ParametroDisenioUi parametroDisenioUi) {
        this.parametroDisenioUi = parametroDisenioUi;
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

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdCalendarioPeriodo() {
        return idCalendarioPeriodo;
    }

    public void setIdCalendarioPeriodo(int idCalendarioPeriodo) {
        this.idCalendarioPeriodo = idCalendarioPeriodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetenciaUi that = (CompetenciaUi) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public enum Tipo {
        COMPETENCIA_BASE, COMPETENCIA_TRANS, COMPETENCIA_ENFQ
    }
    public Tipo tipo = Tipo.COMPETENCIA_BASE;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
