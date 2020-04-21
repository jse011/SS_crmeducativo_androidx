package com.consultoraestrategia.ss_crmeducativo.login.entity;

import java.io.Serializable;

public class LoginProgressPagerUi implements Serializable {
    private int posicion;
    private String titulo;
    private int color;
    private int drawable;
    private String descripcion;

    public static LoginProgressPagerUi bind(int posicion, String titulo, String descripcion,  int color, int drawable) {
        LoginProgressPagerUi  loginProgressPagerUi = new LoginProgressPagerUi();
        loginProgressPagerUi.posicion = posicion;
        loginProgressPagerUi.titulo = titulo;
        loginProgressPagerUi.color = color;
        loginProgressPagerUi.drawable = drawable;
        loginProgressPagerUi.descripcion = descripcion;
        return loginProgressPagerUi;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
