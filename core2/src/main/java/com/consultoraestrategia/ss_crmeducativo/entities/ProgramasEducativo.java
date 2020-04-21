package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ProgramasEducativo extends BaseModel {

    @PrimaryKey
    private int programaEduId;
    @Column
    private String nombre;
    @Column
    private String nroCiclos;
    @Column
    private int nivelAcadId;
    @Column
    private int tipoEvaluacionId;
    @Column
    private int estadoId;
    @Column
    private int entidadId;
    @Column
    private int tipoInformeSiagieId;
    @Column
    private boolean toogle;

    public ProgramasEducativo() {
    }

    public ProgramasEducativo(int programaEduId, String nombre, String nroCiclos, int nivelAcadId, int tipoEvaluacionId, int estadoId, int entidadId) {
        this.programaEduId = programaEduId;
        this.nombre = nombre;
        this.nroCiclos = nroCiclos;
        this.nivelAcadId = nivelAcadId;
        this.tipoEvaluacionId = tipoEvaluacionId;
        this.estadoId = estadoId;
        this.entidadId = entidadId;
    }

    public int getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(int tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getProgramaEduId() {
        return programaEduId;
    }

    public void setProgramaEduId(int programaEduId) {
        this.programaEduId = programaEduId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroCiclos() {
        return nroCiclos;
    }

    public void setNroCiclos(String nroCiclos) {
        this.nroCiclos = nroCiclos;
    }

    public int getNivelAcadId() {
        return nivelAcadId;
    }

    public void setNivelAcadId(int nivelAcadId) {
        this.nivelAcadId = nivelAcadId;
    }

    public int getTipoInformeSiagieId() {
        return tipoInformeSiagieId;
    }

    public void setTipoInformeSiagieId(int tipoInformeSiagieId) {
        this.tipoInformeSiagieId = tipoInformeSiagieId;
    }

    @Override
    public String toString() {
        return "ProgramasEducativo{" +
                "programaEduId=" + programaEduId +
                ", nombre='" + nombre + '\'' +
                ", nroCiclos='" + nroCiclos + '\'' +
                ", nivelAcadId=" + nivelAcadId +
                ", tipoEvaluacionId=" + tipoEvaluacionId +
                ", estadoId=" + estadoId +
                ", entidadId=" + entidadId +
                '}';
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }
}
