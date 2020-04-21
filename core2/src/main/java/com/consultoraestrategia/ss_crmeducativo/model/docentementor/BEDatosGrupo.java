package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;

import java.util.List;

public class BEDatosGrupo {
    private List<EquipoC> equipo;

    private List<GrupoEquipoC> grupoEquipo;

    private List<EquipoIntegranteC> equipoIntegrante;

    private long fecha_servidor;

    public List<EquipoC> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<EquipoC> equipo) {
        this.equipo = equipo;
    }

    public List<GrupoEquipoC> getGrupoEquipo() {
        return grupoEquipo;
    }

    public void setGrupoEquipo(List<GrupoEquipoC> grupoEquipo) {
        this.grupoEquipo = grupoEquipo;
    }

    public List<EquipoIntegranteC> getEquipoIntegrante() {
        return equipoIntegrante;
    }

    public void setEquipoIntegrante(List<EquipoIntegranteC> equipoIntegrante) {
        this.equipoIntegrante = equipoIntegrante;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
}
