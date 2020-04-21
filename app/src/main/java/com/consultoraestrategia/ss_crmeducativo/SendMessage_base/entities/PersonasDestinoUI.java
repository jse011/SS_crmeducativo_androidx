package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities;

/**
 * Created by irvinmarin on 19/12/2017.
 */

public class PersonasDestinoUI {
    private int personaId;
    private String nombres;
    private String nroTelefono;
    private int personaPrincipalVinculada;
    private int idUsuario;

    public PersonasDestinoUI() {
    }

    public PersonasDestinoUI(int personaId, String nombres, String nroTelefono, int personaPrincipalVinculada, int idUsuario) {
        this.personaId = personaId;
        this.nombres = nombres;
        this.nroTelefono = nroTelefono;
        this.personaPrincipalVinculada = personaPrincipalVinculada;
        this.idUsuario = idUsuario;
    }

    public int getPersonaPrincipalVinculada() {
        return personaPrincipalVinculada;
    }

    public void setPersonaPrincipalVinculada(int personaPrincipalVinculada) {
        this.personaPrincipalVinculada = personaPrincipalVinculada;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    @Override
    public String toString() {
        return "PersonasDestinoUI{" +
                "personaId=" + personaId +
                ", nombres='" + nombres + '\'' +
                ", nroTelefono='" + nroTelefono + '\'' +
                ", personaPrincipalVinculada=" + personaPrincipalVinculada +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
