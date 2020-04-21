package com.consultoraestrategia.ss_crmeducativo.chats.entities;

public class UsuarioUi {
    private int usuarioId;
    private String nombreUsuario;
    private int personaId;
    private int docenteId;
    private String nombrePersona;
    private int anioActivadoId;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public int getAnioActivadoId() {
        return anioActivadoId;
    }

    public void setAnioActivadoId(int anioActivadoId) {
        this.anioActivadoId = anioActivadoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }
}
