package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import java.io.Serializable;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class NivelPorRubro implements Serializable {
    private String id;
    private String titulo;

    public NivelPorRubro(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
