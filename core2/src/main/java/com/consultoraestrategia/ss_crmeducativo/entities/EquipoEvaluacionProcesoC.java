package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class EquipoEvaluacionProcesoC extends BaseEntity {

    @Column
    private String equipoEvaluacionProcesoId;
    @Column
    private String rubroEvalProcesoId;
    @Column
    private int sesionAprendizajeId;
    @Column
    private String equipoId;
    @Column
    private double nota;
    @Column
    private String escala;
    @Column
    private String valorTipoNotaId;

    public EquipoEvaluacionProcesoC() {
    }

    public String getEquipoEvaluacionProcesoId() {
        return equipoEvaluacionProcesoId;
    }

    public void setEquipoEvaluacionProcesoId(String equipoEvaluacionProcesoId) {
        this.equipoEvaluacionProcesoId = equipoEvaluacionProcesoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
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

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }
}
