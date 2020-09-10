package com.consultoraestrategia.ss_crmeducativo.crearEvento.entities;

public class AgendaUi {
    private String id;
    private String nombre;
    private String descripcion;
    private int cargaCursoId;
    private int cargaAcademicaId;
    private String color1;
    private String color2;
    private String color3;
    private boolean selected;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor3() {
        return color3;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaUi agendaUi = (AgendaUi) o;

        return id != null ? id.equals(agendaUi.id) : agendaUi.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
