package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.entities;

public enum Paquete {

    BEDATOSTAREARECURSOS("bedatostarearecursos"),
    GEDATOSRUBROEVALUACIONPROCESO("gedatosrubroevaluacionproceso"),
    GEDATOSENVIOASISTENCIA("gedatosenvioasistencia"),
    BEDATOSSESIONAPRENDIZAJE("bedatossesionaprendizaje"),
    STRATEGYLOGIN("strategylogin"),
    BEDATOSUNIDADPRENDIZAJE("bedatosunidadprendizaje"),
    BEDATOSTIPONOTA("bedatostiponota"),
    BEDATOSCALENDARIOPERIODO("bedatoscalendarioperiodo"),
    ALUMNOVIGENCIA("alumnovigencia"),
    ERROR("defauld");


    Paquete(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }


    public static Paquete fromString(String text) {
        for (Paquete b : Paquete.values()) {
            if (b.nombre.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return ERROR;
    }

}
