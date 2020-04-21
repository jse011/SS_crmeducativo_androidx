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
public class CanalComunicacion extends BaseModel {
    @PrimaryKey
    private int canalComId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int usuarioCreacionId;
    @Column
    private int usuarioAccionId;
    @Column
    private String fechaCreacion;
    @Column
    private String fechaAccion;
    @Column
    private boolean estado;

    public CanalComunicacion() {
    }

    public CanalComunicacion(int canalComId, String nombre, String descripcion, int usuarioCreacionId, int usuarioAccionId, String fechaCreacion, String fechaAccion, boolean estado) {
        this.canalComId = canalComId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioCreacionId = usuarioCreacionId;
        this.usuarioAccionId = usuarioAccionId;
        this.fechaCreacion = fechaCreacion;
        this.fechaAccion = fechaAccion;
        this.estado = estado;
    }

    public int getCanalComId() {
        return canalComId;
    }

    public void setCanalComId(int canalComId) {
        this.canalComId = canalComId;
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

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
