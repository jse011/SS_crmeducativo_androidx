package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

import java.util.List;

public class BEDatosContacto {
    private List<Contrato> contratos;

    private List<Persona> personas;

    private List<Usuario> usuariosrelacionados;

    private List<Relaciones> relaciones;

    private List<Tipos> tipos;

    private List<Empleado> empleados;

    private List<CargaCursoDocente> cargaCursoDocente;

    private List<CargaCursoDocenteDet> cargaCursoDocenteDet;

    private List<DetalleContratoAcad> detalleContratoAcad;

    private List<Directivos> directivos;

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Usuario> getUsuariosrelacionados() {
        return usuariosrelacionados;
    }

    public void setUsuariosrelacionados(List<Usuario> usuariosrelacionados) {
        this.usuariosrelacionados = usuariosrelacionados;
    }

    public List<Relaciones> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<Relaciones> relaciones) {
        this.relaciones = relaciones;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<CargaCursoDocente> getCargaCursoDocente() {
        return cargaCursoDocente;
    }

    public void setCargaCursoDocente(List<CargaCursoDocente> cargaCursoDocente) {
        this.cargaCursoDocente = cargaCursoDocente;
    }

    public List<CargaCursoDocenteDet> getCargaCursoDocenteDet() {
        return cargaCursoDocenteDet;
    }

    public void setCargaCursoDocenteDet(List<CargaCursoDocenteDet> cargaCursoDocenteDet) {
        this.cargaCursoDocenteDet = cargaCursoDocenteDet;
    }

    public List<DetalleContratoAcad> getDetalleContratoAcad() {
        return detalleContratoAcad;
    }

    public void setDetalleContratoAcad(List<DetalleContratoAcad> detalleContratoAcad) {
        this.detalleContratoAcad = detalleContratoAcad;
    }

    public List<Directivos> getDirectivos() {
        return directivos;
    }

    public void setDirectivos(List<Directivos> directivos) {
        this.directivos = directivos;
    }
}
