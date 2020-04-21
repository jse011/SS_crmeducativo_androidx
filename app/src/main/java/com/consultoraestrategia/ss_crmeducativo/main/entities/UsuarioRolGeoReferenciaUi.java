package com.consultoraestrategia.ss_crmeducativo.main.entities;

import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public class UsuarioRolGeoReferenciaUi {
    private int id;
    private int georeferenciaId;
    private int entidadId;
    private String nombreEntidad;
    private int rolid;
    private String nombreRol;
    private String nombreGeoreferencia;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public int getRolid() {
        return rolid;
    }

    public void setRolid(int rolid) {
        this.rolid = rolid;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }


    public void setNombreGeoreferencia(String nombreGeoreferencia) {
        this.nombreGeoreferencia = nombreGeoreferencia;
    }

    public String getNombreGeoreferencia() {
        return nombreGeoreferencia;
    }
}
