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
public class IntencionItem extends BaseModel {
    @PrimaryKey
    private int intencionItemId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int intencionId;

    public IntencionItem() {
    }

    public IntencionItem(int intencionItemId, String nombre, String descripcion, int intencionId) {
        this.intencionItemId = intencionItemId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intencionId = intencionId;
    }

    public int getIntencionItemId() {
        return intencionItemId;
    }

    public void setIntencionItemId(int intencionItemId) {
        this.intencionItemId = intencionItemId;
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

    public int getIntencionId() {
        return intencionId;
    }

    public void setIntencionId(int intencionId) {
        this.intencionId = intencionId;
    }

    @Override
    public String toString() {
        return "IntencionItem{" +
                "intencionItemId=" + intencionItemId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", intencionId=" + intencionId +
                '}';
    }
}
