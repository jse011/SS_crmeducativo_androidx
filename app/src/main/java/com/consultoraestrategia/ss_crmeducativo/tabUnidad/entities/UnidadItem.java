package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

public class UnidadItem {

    public enum Tipo{
        APRENDIZAJE("Aprendizaje"),
        SITUACION("Situacion"),
        PRODUCTOS("Productos"),
        RECURSOS("Recursos"),
        EVALUACION("Evaluacion");

        private String nombre;

        Tipo(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    public UnidadItem(boolean select, Tipo tipo) {
        this.select = select;
        this.tipo = tipo;
    }

    public boolean select;
    private Tipo tipo;

    public boolean isSelect() {
        return select;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
