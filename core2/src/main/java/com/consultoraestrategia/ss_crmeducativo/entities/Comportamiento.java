package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class Comportamiento extends BaseModel {
    @PrimaryKey
    public int comportamientoId;
    @Column
    public String nombre;
    @Column
    public String descripcion;
    @Column
    public int cantidad;
    @Column
    public int tipoCasoId;
    @Column
    public String icon;

    public int getComportamientoId() {
        return comportamientoId;
    }

    public void setComportamientoId(int comportamientoId) {
        this.comportamientoId = comportamientoId;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipoCasoId() {
        return tipoCasoId;
    }

    public void setTipoCasoId(int tipoCasoId) {
        this.tipoCasoId = tipoCasoId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comportamiento)) return false;

        Comportamiento that = (Comportamiento) o;

        return comportamientoId == that.comportamientoId;
    }

    @Override
    public int hashCode() {
        return comportamientoId;
    }
}
