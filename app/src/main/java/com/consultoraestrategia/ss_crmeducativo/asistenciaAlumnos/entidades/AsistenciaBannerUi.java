package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades;


public enum  AsistenciaBannerUi {
    ENERO("enero"),
    FEBRERO("febrero"),
    MARZO("marzo"),
    ABRIL("abril"),
    MAYO("mayo"),
    JUNIO("junio"),
    JULIO("julio"),
    AGOSTO("agosto"),
    SETIEMBRE("setiembre"),
    OCTUBRE("octubre"),
    NOVIEMBRE("noviembre"),
    DICIEMBRE("diciembre");
    private String titulo;
    private String mes;

    AsistenciaBannerUi(String mes) {
        this.mes = mes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMes() {
        return mes;
    }
}
