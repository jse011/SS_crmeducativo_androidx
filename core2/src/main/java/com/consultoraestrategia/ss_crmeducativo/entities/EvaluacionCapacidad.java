package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */
@Table(database = AppDatabase.class)
public class EvaluacionCapacidad extends BaseModel {

    @Unique
    @PrimaryKey
    private int evalCapacidadId;
    @Column
    private int desCompetenciaId;
    @Column
    private double nota;
    @Column
    private String escala;
    @Column
    private int competenciaId;
    @Column
    private String fechaAccion;
    @Column
    private int usuarioAccion;


    public EvaluacionCapacidad() {
    }

    public EvaluacionCapacidad(int evalCapacidadId, int desCompetenciaId, double nota, String escala, int competenciaId, String fechaAccion, int usuarioAccion) {
        this.evalCapacidadId = evalCapacidadId;
        this.desCompetenciaId = desCompetenciaId;
        this.nota = nota;
        this.escala = escala;
        this.competenciaId = competenciaId;
        this.fechaAccion = fechaAccion;
        this.usuarioAccion = usuarioAccion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public int getDesCompetenciaId() {
        return desCompetenciaId;
    }

    public int getEvalCapacidadId() {
        return evalCapacidadId;
    }

    public void setEvalCapacidadId(int evalCapacidadId) {
        this.evalCapacidadId = evalCapacidadId;
    }

    public void setDesCompetenciaId(int desCompetenciaId) {
        this.desCompetenciaId = desCompetenciaId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    @Override
    public String toString() {
        return "EvaluacionCapacidad{" +
                "evalCapacidadId=" + evalCapacidadId +
                ", desCompetenciaId=" + desCompetenciaId +
                ", nota=" + nota +
                ", escala='" + escala + '\'' +
                ", competenciaId=" + competenciaId +
                ", fechaAccion='" + fechaAccion + '\'' +
                ", usuarioAccion=" + usuarioAccion +
                '}';
    }
}
