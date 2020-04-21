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
public class ListaUsuario extends BaseModel {

    @PrimaryKey
    private int listaUsuarioId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int entidadId;
    @Column
    private int georeferenciaId;
    @Column
    private int organigramaId;
    @Column
    private String fechaCreacion;
    @Column
    private int usuarioCreacionId;
    @Column
    private String fechaAccion;
    @Column
    private int usuarioAcccionId;
    @Column
    private boolean estado;
    //cargaAcademica // 28 de julio //Maritsa.jarol

    public ListaUsuario() {
    }

    public ListaUsuario(int listaUsuarioId, String nombre, String descripcion, int entidadId, int georeferenciaId, int organigramaId, String fechaCreacion, int usuarioCreacionId, String fechaAccion, int usuarioAcccionId, boolean estado) {
        this.listaUsuarioId = listaUsuarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.entidadId = entidadId;
        this.georeferenciaId = georeferenciaId;
        this.organigramaId = organigramaId;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacionId = usuarioCreacionId;
        this.fechaAccion = fechaAccion;
        this.usuarioAcccionId = usuarioAcccionId;
        this.estado = estado;
    }

    public int getListaUsuarioId() {
        return listaUsuarioId;
    }

    public void setListaUsuarioId(int listaUsuarioId) {
        this.listaUsuarioId = listaUsuarioId;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getUsuarioAcccionId() {
        return usuarioAcccionId;
    }

    public void setUsuarioAcccionId(int usuarioAcccionId) {
        this.usuarioAcccionId = usuarioAcccionId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
