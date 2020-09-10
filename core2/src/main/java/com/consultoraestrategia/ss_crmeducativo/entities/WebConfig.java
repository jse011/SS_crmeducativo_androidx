package com.consultoraestrategia.ss_crmeducativo.entities;

public class WebConfig {
    private String nombre;
    private String content;

    public WebConfig() {
    }

    public WebConfig(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebConfig webConfig = (WebConfig) o;

        return nombre != null ? nombre.equals(webConfig.nombre) : webConfig.nombre == null;
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
