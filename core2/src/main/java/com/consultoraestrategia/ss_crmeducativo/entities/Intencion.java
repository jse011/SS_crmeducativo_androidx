package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 17/08/2017.
 */


@Table(database = AppDatabase.class)
public class Intencion extends BaseModel {

    @PrimaryKey
    private int intencionId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int nivel;
    @Column
    private String fechaCreacion;
    @Column
    private String fechaAccion;
    @Column
    private int usuarioAccionId;
    @Column
    private int usuarioCreacionId;
    @Column
    private int entidadId;
    @Column
    private int georeferenciaId;
    @Column
    private int organigramaId;
    @Column
    private int estadoId;

    public Intencion() {
    }

    public Intencion(int intencionId, String nombre, String descripcion, int nivel, String fechaCreacion, String fechaAccion, int usuarioAccionId, int usuarioCreacionId, int entidadId, int georeferenciaId, int organigramaId, int estadoId) {
        this.intencionId = intencionId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.fechaCreacion = fechaCreacion;
        this.fechaAccion = fechaAccion;
        this.usuarioAccionId = usuarioAccionId;
        this.usuarioCreacionId = usuarioCreacionId;
        this.entidadId = entidadId;
        this.georeferenciaId = georeferenciaId;
        this.organigramaId = organigramaId;
        this.estadoId = estadoId;
    }

    public int getIntencionId() {
        return intencionId;
    }

    public void setIntencionId(int intencionId) {
        this.intencionId = intencionId;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
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

    public int getOrganigramaId() {
        return organigramaId;
    }

    public void setOrganigramaId(int organigramaId) {
        this.organigramaId = organigramaId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }
}
