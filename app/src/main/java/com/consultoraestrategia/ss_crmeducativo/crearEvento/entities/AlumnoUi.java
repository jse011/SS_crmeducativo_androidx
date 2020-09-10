package com.consultoraestrategia.ss_crmeducativo.crearEvento.entities;

public class AlumnoUi {

    private int id;
    private String nombres;
    private String apellidos;
    private String foto;
    private boolean enviarPadre;
    private boolean enviarAlumno;
    private int apoderadoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEnviarPadre() {
        return enviarPadre;
    }

    public void setEnviarPadre(boolean enviarPadre) {
        this.enviarPadre = enviarPadre;
    }

    public boolean isEnviarAlumno() {
        return enviarAlumno;
    }

    public void setEnviarAlumno(boolean enviarAlumno) {
        this.enviarAlumno = enviarAlumno;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlumnoUi alumnoUi = (AlumnoUi) o;

        return id == alumnoUi.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void setApoderadoId(int apoderadoId) {
        this.apoderadoId = apoderadoId;
    }

    public int getApoderadoId() {
        return apoderadoId;
    }
}
