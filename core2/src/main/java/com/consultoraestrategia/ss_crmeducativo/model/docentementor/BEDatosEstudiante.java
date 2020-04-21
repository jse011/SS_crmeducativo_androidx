package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

import java.util.List;

public class BEDatosEstudiante {
    private List<Persona> personas;

    private List<Relaciones> relaciones;

    private List<Usuario> usuarios;

    private List<Contrato> contratos;

    private List<DetalleContratoAcad> detalleContratoAcad;

    //private List<BEContratoDetalle> detalleContrato;

    private List<CargaCursoDocenteDet> cargaCursoDocenteDet;

    private long fecha_servidor;

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Relaciones> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<Relaciones> relaciones) {
        this.relaciones = relaciones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<DetalleContratoAcad> getDetalleContratoAcad() {
        return detalleContratoAcad;
    }

    public void setDetalleContratoAcad(List<DetalleContratoAcad> detalleContratoAcad) {
        this.detalleContratoAcad = detalleContratoAcad;
    }

    public List<CargaCursoDocenteDet> getCargaCursoDocenteDet() {
        return cargaCursoDocenteDet;
    }

    public void setCargaCursoDocenteDet(List<CargaCursoDocenteDet> cargaCursoDocenteDet) {
        this.cargaCursoDocenteDet = cargaCursoDocenteDet;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
}
