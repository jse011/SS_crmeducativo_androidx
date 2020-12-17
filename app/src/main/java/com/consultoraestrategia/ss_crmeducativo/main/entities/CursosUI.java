package com.consultoraestrategia.ss_crmeducativo.main.entities;

/**
 * Created by irvinmarin on 16/10/2017.
 */

public class CursosUI {
    private int cantidadPersonas;
    private int silaboId;
    private int grupoAcademicoId;
    private int periodoAcademicoId;
    private String planEstudios;
    private String nivelAcademico;
    private boolean tutor;
    private boolean complejo;
    private String backgroundSolidColor2;
    private String backgroundSolidColor3;

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public int getSilaboId() {
        return silaboId;
    }

    public void setSilaboId(int silaboId) {
        this.silaboId = silaboId;
    }

    public void setGrupoAcademicoId(int grupoAcademicoId) {
        this.grupoAcademicoId = grupoAcademicoId;
    }

    public int getGrupoAcademicoId() {
        return grupoAcademicoId;
    }

    public void setPeriodoAcademicoId(int periodoAcademicoId) {
        this.periodoAcademicoId = periodoAcademicoId;
    }

    public int getPeriodoAcademicoId() {
        return periodoAcademicoId;
    }

    public void setPlanEstudios(String planEstudios) {
        this.planEstudios = planEstudios;
    }

    public String getPlanEstudios() {
        return planEstudios;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setTutor(boolean isTutor) {
        this.tutor = isTutor;
    }

    public boolean isTutor() {
        return tutor;
    }

    public void setComplejo(boolean complejo) {
        this.complejo = complejo;
    }

    public boolean getComplejo() {
        return complejo;
    }

    public void setBackgroundSolidColor2(String backgroundSolidColor2) {
        this.backgroundSolidColor2 = backgroundSolidColor2;
    }

    public String getBackgroundSolidColor2() {
        return backgroundSolidColor2;
    }

    public void setBackgroundSolidColor3(String backgroundSolidColor3) {
        this.backgroundSolidColor3 = backgroundSolidColor3;
    }

    public String getBackgroundSolidColor3() {
        return backgroundSolidColor3;
    }

    public enum Estado {CREADO, AUTORIZADO, SINSILABO}

    private int idCurso;
    private int cargaCurso;
    private String nombreCurso;
    private String gradoSeccion;
    private String diaHora;
    private String nroSalon;
    private String nombreDocente;
    private String urlImgDoncente;
    private String urlBackgroundItem;
    private String backgroundSolidColor;
    private boolean ImgVisible;
    private boolean nombreDocenteVisible;
    private int idCursDetalleHorario;
    private int parametroDisenioId;
    private int cargaAcademicaId;
    public Estado estado = Estado.CREADO;

    public CursosUI() {
    }

    public CursosUI(int idCurso, int cargaCurso, String nombreCurso, String gradoSeccion, String diaHora, String nroSalon, String nombreDocente, String urlImgDoncente, String urlBackgroundItem, String backgroundSolidColor, boolean imgVisible, boolean nombreDocenteVisible, int idCursDetalleHorario) {
        this.idCurso = idCurso;
        this.cargaCurso = cargaCurso;
        this.nombreCurso = nombreCurso;
        this.gradoSeccion = gradoSeccion;
        this.diaHora = diaHora;
        this.nroSalon = nroSalon;
        this.nombreDocente = nombreDocente;
        this.urlImgDoncente = urlImgDoncente;
        this.urlBackgroundItem = urlBackgroundItem;
        this.backgroundSolidColor = backgroundSolidColor;
        ImgVisible = imgVisible;
        this.nombreDocenteVisible = nombreDocenteVisible;
        this.idCursDetalleHorario = idCursDetalleHorario;
    }

    public int getIdCursDetalleHorario() {
        return idCursDetalleHorario;
    }

    public void setIdCursDetalleHorario(int idCursDetalleHorario) {
        this.idCursDetalleHorario = idCursDetalleHorario;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getCargaCurso() {
        return cargaCurso;
    }

    public void setCargaCurso(int cargaCurso) {
        this.cargaCurso = cargaCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getGradoSeccion() {
        return gradoSeccion;
    }

    public void setGradoSeccion(String gradoSeccion) {
        this.gradoSeccion = gradoSeccion;
    }

    public String getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(String diaHora) {
        this.diaHora = diaHora;
    }

    public String getNroSalon() {
        return nroSalon;
    }

    public void setNroSalon(String nroSalon) {
        this.nroSalon = nroSalon;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getUrlImgDoncente() {
        return urlImgDoncente;
    }

    public void setUrlImgDoncente(String urlImgDoncente) {
        this.urlImgDoncente = urlImgDoncente;
    }

    public String getUrlBackgroundItem() {
        return urlBackgroundItem;
    }

    public void setUrlBackgroundItem(String urlBackgroundItem) {
        this.urlBackgroundItem = urlBackgroundItem;
    }

    public String getBackgroundSolidColor() {
        return backgroundSolidColor;
    }

    public void setBackgroundSolidColor(String backgroundSolidColor) {
        this.backgroundSolidColor = backgroundSolidColor;
    }

    public boolean isImgVisible() {
        return ImgVisible;
    }

    public void setImgVisible(boolean imgVisible) {
        ImgVisible = imgVisible;
    }

    public boolean isNombreDocenteVisible() {
        return nombreDocenteVisible;
    }

    public void setNombreDocenteVisible(boolean nombreDocenteVisible) {
        this.nombreDocenteVisible = nombreDocenteVisible;
    }

    public int getParametroDisenioId() {
        return parametroDisenioId;
    }

    public void setParametroDisenioId(int parametroDisenioId) {
        this.parametroDisenioId = parametroDisenioId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CursosUI{" +
                "idCurso=" + idCurso +
                ", cargaCurso=" + cargaCurso +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", gradoSeccion='" + gradoSeccion + '\'' +
                ", diaHora='" + diaHora + '\'' +
                ", nroSalon='" + nroSalon + '\'' +
                ", nombreDocente='" + nombreDocente + '\'' +
                ", urlImgDoncente='" + urlImgDoncente + '\'' +
                ", urlBackgroundItem='" + urlBackgroundItem + '\'' +
                ", backgroundSolidColor='" + backgroundSolidColor + '\'' +
                ", ImgVisible=" + ImgVisible +
                ", nombreDocenteVisible=" + nombreDocenteVisible +
                ", idCursDetalleHorario=" + idCursDetalleHorario +
                ", parametroDisenioId=" + parametroDisenioId +
                ", cargaAcademicaId=" + cargaAcademicaId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursosUI cursosUI = (CursosUI) o;

        return idCurso == cursosUI.idCurso;
    }

    @Override
    public int hashCode() {
        return idCurso;
    }




    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }
}
