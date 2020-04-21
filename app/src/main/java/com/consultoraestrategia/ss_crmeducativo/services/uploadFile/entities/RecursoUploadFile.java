package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities;

public class RecursoUploadFile {

    private String id;
    private String nombre;
    private String extencion;
    private String url;
    private String path;
    private EnumFile tipo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtencion() {
        return extencion;
    }

    public void setExtencion(String extencion) {
        this.extencion = extencion;
    }

    public void setTipo(EnumFile tipo) {
        this.tipo = tipo;
    }

    public EnumFile getTipo() {
        return tipo;
    }
}
