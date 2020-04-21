package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class DimensionDesarrollo extends BaseModel {

    @PrimaryKey
    @Column
    private int dimensionDesarrolloId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int entidadId;
    @Column
    private int usuarioCreadorId;
    @Column
    private String fechaCreacion;
    @Column
    private String fechaAccion;
    @Column
    private boolean estado;
    @Column
    private int tipoCompetenciaId;
    @Column
    private String color;
    @Column
    private int programaCurricularId;


    public int getDimensionDesarrolloId() {
        return dimensionDesarrolloId;
    }

    public void setDimensionDesarrolloId(int dimensionDesarrolloId) {
        this.dimensionDesarrolloId = dimensionDesarrolloId;
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

    public int getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(int usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
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

    public int getTipoCompetenciaId() {
        return tipoCompetenciaId;
    }

    public void setTipoCompetenciaId(int tipoCompetenciaId) {
        this.tipoCompetenciaId = tipoCompetenciaId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProgramaCurricularId() {
        return programaCurricularId;
    }

    public void setProgramaCurricularId(int programaCurricularId) {
        this.programaCurricularId = programaCurricularId;
    }
}
