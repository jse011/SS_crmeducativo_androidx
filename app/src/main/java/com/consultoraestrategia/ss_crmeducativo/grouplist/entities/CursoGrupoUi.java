package com.consultoraestrategia.ss_crmeducativo.grouplist.entities;

import org.parceler.Parcel;

import java.util.List;
@Parcel
public class CursoGrupoUi {

    public String nombreCurso;
    public String seccion;
    public String periodo;
    public List<Group> grupos;
    public String color1;
    public String color2;
    public String color3;

    public CursoGrupoUi() {
    }


    public List<Group> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Group> grupos) {
        this.grupos = grupos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    @Override
    public String toString() {
        return "CursoGrupoUi{" +
                "nombreCurso='" + nombreCurso + '\'' +
                ", seccion='" + seccion + '\'' +
                ", periodo='" + periodo + '\'' +
                ", grupos=" + grupos +
                ", color1='" + color1 + '\'' +
                ", color2='" + color2 + '\'' +
                ", color3='" + color3 + '\'' +
                '}';
    }
}
