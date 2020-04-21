package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;;
import com.raizlabs.android.dbflow.annotation.QueryModel;

import java.util.Objects;

@QueryModel(database = AppDatabase.class, allFields = true)
public class PersonaContratoQuery {
    @Column
    public int personaId;
    @Column
    public String nombres;
    @Column
    public String apellidoPaterno;
    @Column
    public String apellidoMaterno;
    @Column
    public String celular;
    @Column
    public String telefono;
    @Column
    public String foto;
    @Column
    public String fechaNac;
    @Column
    public String genero;
    @Column
    public String estadoCivil;
    @Column
    public String numDoc;
    @Column
    public String ocupacion;
    @Column
    public int estadoId;
    @Column
    public String correo;
    @Column
    public String direccion;
    @Column
    public int idContrato;
    @Column
    public int vigente;
    @Column
    private String path;


    public PersonaContratoQuery() {
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getVigente() {
        return vigente;
    }

    public void setVigente(int vigente) {
        this.vigente = vigente;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PersonaContratoQuery{" +
                "personaId=" + personaId +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", celular='" + celular + '\'' +
                ", telefono='" + telefono + '\'' +
                ", foto='" + foto + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", genero='" + genero + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", numDoc='" + numDoc + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", estadoId=" + estadoId +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", idContrato=" + idContrato +
                ", vigente=" + vigente +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaContratoQuery that = (PersonaContratoQuery) o;
        return personaId == that.personaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId);
    }
}
