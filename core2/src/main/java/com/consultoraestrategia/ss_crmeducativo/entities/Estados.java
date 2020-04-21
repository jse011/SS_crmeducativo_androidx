package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class Estados extends BaseModel {
    @PrimaryKey
    private int estadoId;

    @Column
    private String objeto;
    @Column
    private String nombre;
    @Column
    private int tipoId;
    @Column
    private String color;
    @Column
    private String concepto;

    public Estados() {

    }

    public Estados(int estadoId, String objeto, String nombre, int tipoId, String color, String concepto) {
        this.estadoId = estadoId;
        this.objeto = objeto;
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.color = color;
        this.concepto = concepto;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }


}
