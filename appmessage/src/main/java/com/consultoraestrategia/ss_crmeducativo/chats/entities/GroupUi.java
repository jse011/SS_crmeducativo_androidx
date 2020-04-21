package com.consultoraestrategia.ss_crmeducativo.chats.entities;

import java.io.Serializable;
import java.util.List;

public class GroupUi  implements Serializable {

    public enum Type{ACADEMIC, TEAM, COURSE}
    private int cargaCursoId;
    private int cargaAcademicaId;
    private String grupoEquipoId;
    private List<Long> docenteId;
    private String name;
    private String color;
    private String programEducate;
    private String year;
    private int idperiod;
    private String section;
    private int idsection;
    private String photo;
    private Type type;
    private int idSender;
    private String nameCourse;
    private int idCourse;

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public List<Long> getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(List<Long> docenteId) {
        this.docenteId = docenteId;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProgramEducate() {
        return programEducate;
    }

    public void setProgramEducate(String programEducate) {
        this.programEducate = programEducate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getIdperiod() {
        return idperiod;
    }

    public void setIdperiod(int idperiod) {
        this.idperiod = idperiod;
    }

    public int getIdsection() {
        return idsection;
    }

    public void setIdsection(int idsection) {
        this.idsection = idsection;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }


}
