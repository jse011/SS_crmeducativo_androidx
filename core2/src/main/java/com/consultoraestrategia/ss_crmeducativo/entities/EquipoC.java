package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

@Table(database = AppDatabase.class)
public class EquipoC extends BaseEntity {
    public final static int CREADO = 322, ACTUALIZADO = 323, ELIMINADO = 324;
    @Column
    private String grupoEquipoId;
    @Column
    private String nombre;
    @Column
    private String urlImagen;
    @Column
    private int orden;
    @Column
    private boolean deleted;
    @Column
    private int estado;

    public static final int ESTADO_ELIMINADO = 324;
    public static final int ESTADO_CREADO = 322;

    public EquipoC() {
    }

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    List<EquipoIntegranteC> integrantes = new ArrayList<>();

    public List<EquipoIntegranteC> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<EquipoIntegranteC> integrantes) {
        if (integrantes != null && !integrantes.isEmpty()) {
            this.integrantes = integrantes;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
