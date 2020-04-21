package com.consultoraestrategia.ss_crmeducativo.grouplist.entities;

public enum  TipoGrupoUi {
    ESTATICO("Lista de grupo estático"),
    DINAMICO("Lista de grupo dinámico"),
    APRENDIZAJE_UNICO("Lista de grupo mismo estilo aprendizaje"),
    APRENDIZAJE_DIFERENTE("Lista de grupo diferente estilo aprendizaje");
    private String nombre;

    TipoGrupoUi(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
