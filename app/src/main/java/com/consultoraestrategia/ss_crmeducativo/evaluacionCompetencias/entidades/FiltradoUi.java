package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

/**
 * Created by kike on 11/04/2018.
 */

public class   FiltradoUi {

    public enum Tipo{
        COMPETENCIA_BASE("Competencia Base"), COMPETENCIA_TRANSVERSAL_ENFOQUE("Competencia Transversal / Enfoque Transversal");
        private String nombre;

        Tipo(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    private Tipo tipo = Tipo.COMPETENCIA_BASE;
    private boolean aBoolean;
    private boolean aBooleanTemporal;


    public FiltradoUi(Tipo tipo, boolean aBoolean) {
        this.tipo = tipo;
        this.aBoolean = aBoolean;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean isaBooleanTemporal() {
        return aBooleanTemporal;
    }

    public void setaBooleanTemporal(boolean aBooleanTemporal) {
        this.aBooleanTemporal = aBooleanTemporal;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo.getNombre();
    }
}
