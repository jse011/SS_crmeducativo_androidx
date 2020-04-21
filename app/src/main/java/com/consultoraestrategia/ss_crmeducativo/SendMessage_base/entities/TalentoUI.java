package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities;

/**
 * Created by irvinmarin on 19/12/2017.
 */

public class TalentoUI {
    private int idIntencionItemId;
    private String nombre;
    private String descripcion;

    public TalentoUI() {
    }

    public TalentoUI(int idIntencionItemId, String nombre, String descripcion) {
        this.idIntencionItemId = idIntencionItemId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdIntencionItemId() {
        return idIntencionItemId;
    }

    public void setIdIntencionItemId(int idIntencionItemId) {
        this.idIntencionItemId = idIntencionItemId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TalentoUI{" +
                "idIntencionItemId=" + idIntencionItemId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
