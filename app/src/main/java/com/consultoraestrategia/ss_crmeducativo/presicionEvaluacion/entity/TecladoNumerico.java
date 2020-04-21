package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity;

public enum TecladoNumerico {
    UNO("1"),DOS("2"), TRES("3"), CUATRO("4"), CINCO("5"), SEIS("6"), SIETE("7"), OCHO("8"), NUEVE("9"), CERO("0"), REMOVER("X"), PUNTO(".");
    private String valor;

    TecladoNumerico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
