package com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites;

public enum TipoSalaEnum {
    SALON_GENERAL("Salon_G"),SALON_ALUMNOS("Salon_A"),SALON_PADRES("Salon_P"),
    CURSO_GENERAL("Curso_G"),CURSO_ALUMNOS("Curso_A"),CURSO_PADRES("Curso_P"),
    LISTA_GENERAL("Lista_G"),LISTA_ALUMNOS("Lista_A"),LISTA_PADRES("Lista_P");
    String nombre;

    TipoSalaEnum(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
}
