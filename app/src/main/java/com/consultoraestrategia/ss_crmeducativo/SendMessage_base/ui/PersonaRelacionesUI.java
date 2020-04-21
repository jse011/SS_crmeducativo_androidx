package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class PersonaRelacionesUI {

    public static final int PARENTEZCO_PADRE = 1;
    public static final int PARENTEZCO_ALUMNO = 2;

    private Persona persona;
    private String parentezco;
    private int tipoParentezco;
    private boolean apoderado;
    private List<PersonaRelacionesUI> personasRelacionadas;
    private Persona personaPrincipal;

    public PersonaRelacionesUI() {
    }

    public PersonaRelacionesUI(Persona persona, String parentezco, int tipoParentezco, boolean apoderado, List<PersonaRelacionesUI> personasRelacionadas, Persona personaPrincipal) {
        this.persona = persona;
        this.parentezco = parentezco;
        this.tipoParentezco = tipoParentezco;
        this.apoderado = apoderado;
        this.personasRelacionadas = personasRelacionadas;
        this.personaPrincipal = personaPrincipal;
    }

    public boolean isApoderado() {
        return apoderado;
    }

    public int getTipoParentezco() {
        return tipoParentezco;
    }

    public void setTipoParentezco(int tipoParentezco) {
        this.tipoParentezco = tipoParentezco;
    }

    public void setApoderado(boolean apoderado) {
        this.apoderado = apoderado;
    }

    public Persona getPersonaPrincipal() {
        return personaPrincipal;
    }

    public void setPersonaPrincipal(Persona personaPrincipal) {
        this.personaPrincipal = personaPrincipal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public List<PersonaRelacionesUI> getPersonasRelacionadas() {
        return personasRelacionadas;
    }

    public void setPersonasRelacionadas(List<PersonaRelacionesUI> personasRelacionadas) {
        this.personasRelacionadas = personasRelacionadas;
    }

    @Override
    public String toString() {
        return persona.getNombreCompleto();
    }
}
