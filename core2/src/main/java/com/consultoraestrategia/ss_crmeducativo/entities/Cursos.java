package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class Cursos extends BaseModel {

    @Column
    @PrimaryKey
    private int cursoId;

    @Column
    private String nombre;
    @Column
    private int estadoId;
    @Column
    private String descripcion;
    @Column
    private String alias;
    @Column
    private int entidadId;
    @Column
    private int nivelAcadId;
    @Column
    private int tipoCursoId;
    @Column
    private String color;


    public Cursos() {
    }

    public Cursos(int cursoId, String nombre, int estadoId, String descripcion, String alias, int entidadId, int nivelAcadId, int tipoCursoId, String color) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.estadoId = estadoId;
        this.descripcion = descripcion;
        this.alias = alias;
        this.entidadId = entidadId;
        this.nivelAcadId = nivelAcadId;
        this.tipoCursoId = tipoCursoId;
        this.color = color;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getNivelAcadId() {
        return nivelAcadId;
    }

    public void setNivelAcadId(int nivelAcadId) {
        this.nivelAcadId = nivelAcadId;
    }

    public int getTipoCursoId() {
        return tipoCursoId;
    }

    public void setTipoCursoId(int tipoCursoId) {
        this.tipoCursoId = tipoCursoId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "cursoId=" + cursoId +
                ", nombre='" + nombre + '\'' +
                ", estadoId=" + estadoId +
                ", descripcion='" + descripcion + '\'' +
                ", alias='" + alias + '\'' +
                ", entidadId=" + entidadId +
                ", nivelAcadId=" + nivelAcadId +
                ", tipoCursoId=" + tipoCursoId +
                ", color='" + color + '\'' +
                '}';
    }

    public static Cursos getCurso(int id){
        return SQLite.select()
                .from(Cursos.class)
                .where(Cursos_Table.cursoId.eq(id))
                .querySingle();
    }
}
