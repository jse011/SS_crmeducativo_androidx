package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities;

import java.util.List;

public class PersonaUi {
    private int personaId;
    private int georeferenciId;
    private int entidadId;
    private int usuaroId;
    private String nombre;
    private int numeracion;
    private String nombres;
    private String apellidos;
    private String foto;
    private List<SentimientoUi> fotos;

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getGeoreferenciId() {
        return georeferenciId;
    }

    public void setGeoreferenciId(int georeferenciId) {
        this.georeferenciId = georeferenciId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getUsuaroId() {
        return usuaroId;
    }

    public void setUsuaroId(int usuaroId) {
        this.usuaroId = usuaroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public List<SentimientoUi> getFotos() {
        return fotos;
    }

    public void setFotos(List<SentimientoUi> fotos) {
        this.fotos = fotos;
    }
}
