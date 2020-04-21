package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class Tipos extends BaseModel {

    public static final int MERITO=541, DEMERITO=542, INDIVIDUAL=477, GRUPAL=478;
    @Unique
    @PrimaryKey
    private int tipoId;
    @Column
    private String objeto;
    @Column
    private String concepto;
    @Column
    private String nombre;
    @Column
    private String codigo;
    @Column
    private Integer parentId;

    public Tipos(int tipoId, String objeto, String concepto, String nombre, String codigo) {
        this.tipoId = tipoId;
        this.objeto = objeto;
        this.concepto = concepto;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Tipos() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Tipos getTipoById(int objetivoMensajeId) {
        return SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.tipoId.is(objetivoMensajeId))
                .querySingle();
    }

    public static List<Tipos> getTipoByConcepto(String concepto) {
        return SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.concepto.is(concepto))
                .queryList();
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
