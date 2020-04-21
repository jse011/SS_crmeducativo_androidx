package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 16/10/2017.
 */
@Parcel
public class TipoEvaluacionUi {
    int id;
    String nombre;

    public TipoEvaluacionUi() {
    }

    public TipoEvaluacionUi(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
