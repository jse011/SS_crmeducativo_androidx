package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import java.util.List;

/**
 * Created by kike on 11/05/2018.
 */

public class EvaluacionFormula_TipoNotaUi {

    String id;
    String nombre;
    String valorDefecto;
    int valorMinimo;
    int valorMaximo;
    double longitudPaso;
    int tipoId;
    List<ValorTipoNotaUi> valorTipoNotaList;
    //EscalaEvaluacionUi escalaEvaluacionUi;

    public EvaluacionFormula_TipoNotaUi() {
    }

    public EvaluacionFormula_TipoNotaUi(String id, String nombre, String valorDefecto, int valorMinimo, int valorMaximo, double longitudPaso, int tipoId, List<ValorTipoNotaUi> valorTipoNotaList) {
        this.id = id;
        this.nombre = nombre;
        this.valorDefecto = valorDefecto;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.longitudPaso = longitudPaso;
        this.tipoId = tipoId;
        this.valorTipoNotaList = valorTipoNotaList;
    }

    public int getTipoId() {
        return tipoId;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setLongitudPaso(double longitudPaso) {
        this.longitudPaso = longitudPaso;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "nombre: " + nombre + "\n" +
                "valorDefecto: " + valorDefecto + "\n" +
                "valorMinimo: " + valorMinimo + "\n" +
                "valorMaximo: " + valorMaximo + "\n" +
                "longitudPaso: " + longitudPaso + "\n" +
                "tipoId: " + tipoId;
    }

   /* public EscalaEvaluacionUi getEscalaEvaluacionUi() {
        return escalaEvaluacionUi;
    }

    public void setEscalaEvaluacionUi(EscalaEvaluacionUi escalaEvaluacionUi) {
        this.escalaEvaluacionUi = escalaEvaluacionUi;
    }
*/
    public void setValorTipoNotaList(List<ValorTipoNotaUi> valorTipoNotaList) {
        this.valorTipoNotaList = valorTipoNotaList;
    }
}
