package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by kelvi on 24/02/2017.
 */

public class AlumnosAsistenciaUI {
    private int idAlumno;
    private String nombres;
    private String apellidos;
    private String imagen;
    private List<AsistenciaSesionAlumnoC> sesionAlumnoList;
    private List<Sesion> sesionList;

    public AlumnosAsistenciaUI() {
    }

    public AlumnosAsistenciaUI(int idAlumno, String nombres, String apellidos, String imagen, List<AsistenciaSesionAlumnoC> sesionAlumnoList, List<Sesion> sesionList) {
        this.idAlumno = idAlumno;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.sesionAlumnoList = sesionAlumnoList;
        this.sesionList = sesionList;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<AsistenciaSesionAlumnoC> getSesionAlumnoList() {
        return sesionAlumnoList;
    }

    public void setSesionAlumnoList(List<AsistenciaSesionAlumnoC> sesionAlumnoList) {
        this.sesionAlumnoList = sesionAlumnoList;
    }

    public List<Sesion> getSesionList() {
        return sesionList;
    }

    public void setSesionList(List<Sesion> sesionList) {
        this.sesionList = sesionList;
    }

    @Override
    public String toString() {
        return "AlumnosAsistenciaUI{" +
                "idAlumno=" + idAlumno +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sesionAlumnoList=" + sesionAlumnoList +
                ", sesionList=" + sesionList +
                '}';
    }

}
