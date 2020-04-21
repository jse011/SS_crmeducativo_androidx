package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */
@Table(database = AppDatabase.class)
public class GrupoEquipoC extends BaseEntity {
    public final static int CREADO = 319, ACTUALIZADO = 320, ELIMINADO = 321;
    @Column
    private int cargaCursoId;
    @Column
    private int tipoId;
    @Column
    private String nombre;
    @Column
    private int cargaAcademicaId;
    @Column
    private int docenteId;
    @Column
    private boolean finished;
    @Column
    private int estado;

    public GrupoEquipoC() {
        finished = true;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    List<EquipoC> equipos = new ArrayList<>();

    public List<EquipoC> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoC> equipos) {
        if (equipos != null && !equipos.isEmpty()){
            this.equipos = equipos;
        }
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "GrupoEquipoC{" +
                "cargaCursoId=" + cargaCursoId +
                ", tipoId=" + tipoId +
                ", nombre='" + nombre + '\'' +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", docenteId=" + docenteId +
                ", finished=" + finished +
                ", estado=" + estado +
                ", equipos=" + equipos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrupoEquipoC)) return false;

        GrupoEquipoC that = (GrupoEquipoC) o;

        return getKey() != null ? getKey().equals(that.getKey()) : that.getKey() == null;
    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }
}
