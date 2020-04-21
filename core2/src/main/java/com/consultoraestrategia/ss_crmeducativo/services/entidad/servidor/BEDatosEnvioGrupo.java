package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEnvioGrupo extends BEDatosServidor {
    public List<GrupoEquipoC> grupo_equipo;
    public List<EquipoC> equipo;
    public List<EquipoIntegranteC> equipo_integrante;

    public BEDatosEnvioGrupo() {
    }

    public List<GrupoEquipoC> getGrupo_equipo() {
        return grupo_equipo;
    }

    public void setGrupo_equipo(List<GrupoEquipoC> grupo_equipo) {
        this.grupo_equipo = grupo_equipo;
    }

    public List<EquipoC> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<EquipoC> equipo) {
        this.equipo = equipo;
    }

    public List<EquipoIntegranteC> getEquipo_integrante() {
        return equipo_integrante;
    }

    public void setEquipo_integrante(List<EquipoIntegranteC> equipo_integrante) {
        this.equipo_integrante = equipo_integrante;
    }

    @Override
    public String toString() {
        return "BEDatosEnvioGrupo{" +
                "grupo_equipo=" + grupo_equipo +
                ", equipo=" + equipo +
                ", equipo_integrante=" + equipo_integrante +
                '}';
    }
}
