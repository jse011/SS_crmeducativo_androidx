package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.DiaHora;
import com.consultoraestrategia.ss_crmeducativo.entities.Hora;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioHora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEnvioHorario  extends BEDatosServidor {
    private List<Hora> hora;
    private List<HorarioPrograma> horarioPrograma;
    private List<HorarioHora> horarioHora;
    private List<DetalleHorario> detalleHorario;
    private List<Dia> dia;
    private List<DiaHora> obtenerDiaHora;
    private List<Horario> horario;
    private List<HorarioDia> horarioDia;
    private List<CursosDetHorario> cursosDetHorario;
    //public List<BEDiaHora> obtenerDiaHora; Irvin cre una entidad

    public BEDatosEnvioHorario() {
    }

    public List<Hora> getHora() {
        return hora;
    }

    public void setHora(List<Hora> hora) {
        this.hora = hora;
    }

    public List<HorarioPrograma> getHorarioPrograma() {
        return horarioPrograma;
    }

    public void setHorarioPrograma(List<HorarioPrograma> horarioPrograma) {
        this.horarioPrograma = horarioPrograma;
    }

    public List<HorarioHora> getHorarioHora() {
        return horarioHora;
    }

    public void setHorarioHora(List<HorarioHora> horarioHora) {
        this.horarioHora = horarioHora;
    }

    public List<DetalleHorario> getDetalleHorario() {
        return detalleHorario;
    }

    public void setDetalleHorario(List<DetalleHorario> detalleHorario) {
        this.detalleHorario = detalleHorario;
    }

    public List<Dia> getDia() {
        return dia;
    }

    public void setDia(List<Dia> dia) {
        this.dia = dia;
    }

    public List<DiaHora> getObtenerDiaHora() {
        return obtenerDiaHora;
    }

    public void setObtenerDiaHora(List<DiaHora> obtenerDiaHora) {
        this.obtenerDiaHora = obtenerDiaHora;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public List<HorarioDia> getHorarioDia() {
        return horarioDia;
    }

    public void setHorarioDia(List<HorarioDia> horarioDia) {
        this.horarioDia = horarioDia;
    }

    public List<CursosDetHorario> getCursosDetHorario() {
        return cursosDetHorario;
    }

    public void setCursosDetHorario(List<CursosDetHorario> cursosDetHorario) {
        this.cursosDetHorario = cursosDetHorario;
    }
}

