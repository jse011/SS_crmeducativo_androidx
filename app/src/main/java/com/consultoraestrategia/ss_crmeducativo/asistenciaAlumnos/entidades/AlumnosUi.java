package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades;


public class AlumnosUi  {
    private int id;
    private String nombre;
    public String lastName;
    public String urlProfile;


    public AlumnosUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        boolean equals = false;
        AlumnosUi alumno = (AlumnosUi) obj;
        if (alumno.getId()==id) {
            equals = true;
        }
        return equals;
    }
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AlumnosUi{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lastName='" + lastName + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                '}';
    }
}
