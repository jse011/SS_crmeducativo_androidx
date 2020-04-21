package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;

public class UsuarioUi {

    private  int usuarioId;
    private  int personaId;
    private String nombrePersona;
    private String apellidoPersona;
    private String urlpicture;
    private boolean selected;

    private boolean enabled;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getUrlpicture() {
        return urlpicture;
    }

    public void setUrlpicture(String urlpicture) {
        this.urlpicture = urlpicture;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioUi)) return false;

        UsuarioUi usuarioUi = (UsuarioUi) o;

        return usuarioId == usuarioUi.usuarioId;
    }

    @Override
    public int hashCode() {
        return usuarioId;
    }
}
