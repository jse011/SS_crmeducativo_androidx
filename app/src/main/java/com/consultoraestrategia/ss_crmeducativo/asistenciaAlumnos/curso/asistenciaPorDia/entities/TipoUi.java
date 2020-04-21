package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities;

public class TipoUi {
    public enum objeto{	T_AS_MAE_JUSTIFICACION}
    private int  id;
    private String nombre;
    private  String concepto;
    private objeto objetos= objeto.T_AS_MAE_JUSTIFICACION;


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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public objeto getObjetos() {
        return objetos;
    }

    public void setObjetos(objeto objetos) {
        this.objetos = objetos;
    }
}
