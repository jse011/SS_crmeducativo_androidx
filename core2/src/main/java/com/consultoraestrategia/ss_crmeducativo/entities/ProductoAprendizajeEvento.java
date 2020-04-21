package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ProductoAprendizajeEvento extends BaseModel {
    @PrimaryKey
    private int productoAprendizajeId;
    @Column
    private String titulo;
    @Column
    private String descripcion;
    @Column
    private boolean estado;
    @Column
    private int unidadAprendizajeId;

    public ProductoAprendizajeEvento() {
    }

    public int getProductoAprendizajeId() {
        return productoAprendizajeId;
    }

    public void setProductoAprendizajeId(int productoAprendizajeId) {
        this.productoAprendizajeId = productoAprendizajeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    @Override
    public String toString() {
        return "ProductoAprendizajeEvento{" +
                "productoAprendizajeId=" + productoAprendizajeId +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", unidadAprendizajeId=" + unidadAprendizajeId +
                '}';
    }
}
