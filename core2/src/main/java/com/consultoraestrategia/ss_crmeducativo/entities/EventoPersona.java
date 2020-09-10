package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class EventoPersona extends BaseEntity{
    @Column
    private String eventoPersonaId;
    @Column
    private String eventoId;
    @Column
    private int personaId;
    @Column
    private boolean estado;
    @Column
    int rolId;

    private int apoderadoId;

    public String getEventoPersonaId() {
        return eventoPersonaId;
    }

    public void setEventoPersonaId(String eventoPersonaId) {
        this.eventoPersonaId = eventoPersonaId;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public void setApoderadoId(int apoderadoId) {
        this.apoderadoId = apoderadoId;
    }

    public int getApoderadoId() {
        return apoderadoId;
    }
}
