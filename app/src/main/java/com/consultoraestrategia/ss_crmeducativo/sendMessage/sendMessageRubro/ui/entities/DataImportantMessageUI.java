package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui.entities;

/**
 * Created by irvinmarin on 17/07/2018.
 */

public class DataImportantMessageUI {
    private String nombreCargaAcademica;
    private String grado;
    private String seccion;
    private String salon;
    private String nombreCurso;
    private int cursoId;

    public DataImportantMessageUI(String nombreCargaAcademica, String grado, String seccion, String salon, String nombreCurso, int cursoId) {
        this.nombreCargaAcademica = nombreCargaAcademica;
        this.grado = grado;
        this.seccion = seccion;
        this.salon = salon;
        this.nombreCurso = nombreCurso;
        this.cursoId = cursoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCargaAcademica() {
        return nombreCargaAcademica;
    }

    public void setNombreCargaAcademica(String nombreCargaAcademica) {
        this.nombreCargaAcademica = nombreCargaAcademica;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }
}
