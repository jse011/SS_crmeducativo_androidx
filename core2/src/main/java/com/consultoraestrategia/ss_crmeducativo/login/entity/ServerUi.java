package com.consultoraestrategia.ss_crmeducativo.login.entity;

public class ServerUi {
    private String nombre;
    private String direccion;
    private String path;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServer(){
        return direccion + path;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
