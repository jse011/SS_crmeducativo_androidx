package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades;

import java.util.List;

public class AsistenicaArchivoUi {


    private long fechaAccionArchivo;
    public enum  Tipo {DEFAUL, VIDEO, VINCULO, DOCUMENTO, IMAGEN, AUDIO, HOJA_CALCULO, PRESENTACION, PDF, MATERIALES}
    private String key;
    private String url;
    private String path;
    private String nombre;
    private Tipo tipo = Tipo.VINCULO;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public long getFechaAccionArchivo() {
        return fechaAccionArchivo;
    }

    public void setFechaAccionArchivo(long fechaAccionArchivo) {
        this.fechaAccionArchivo = fechaAccionArchivo;
    }
}
