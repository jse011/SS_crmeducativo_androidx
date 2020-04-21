package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

/**
 * Created by kike on 11/05/2018.
 */

public class NotaUi extends FormulaCelda{
    public String id;
    public double nota;//nota
    public double aDoubleNota;//nota
    public String escala;
    public String valorTipoNotaId;
    public AlumnosUi alumno;
  //  public RubroProcesoUi rubro;
    public EvaluacionFormula_RubroEvaluacionUi rubro;
    public EvaluacionFormula_TipoNotaUi tipoNota;
    public int competenciaId;
    public int tipos;
    private String icono;
    private String alias;

    public NotaUi() {
    }


    public NotaUi(String id, double nota, String escala, String valorTipoNotaId, AlumnosUi alumno, EvaluacionFormula_RubroEvaluacionUi rubro, EvaluacionFormula_TipoNotaUi tipoNota, int competenciaId, int tipos) {
        this.id = id;
        this.nota = nota;
        this.escala = escala;
        this.valorTipoNotaId = valorTipoNotaId;
        this.alumno = alumno;
        this.rubro = rubro;
        this.tipoNota = tipoNota;
        this.competenciaId = competenciaId;
        this.tipos = tipos;
    }

    public NotaUi(String id, double nota) {
        this.id = id;
        this.nota = nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public AlumnosUi getAlumno() {
        return alumno;
    }

    public EvaluacionFormula_RubroEvaluacionUi getRubro() {
        return rubro;
    }

    public EvaluacionFormula_TipoNotaUi getTipoNota() {
        return tipoNota;
    }

    public void setAlumno(AlumnosUi alumno) {
        this.alumno = alumno;
    }

    public void setRubro(EvaluacionFormula_RubroEvaluacionUi rubro) {
        this.rubro = rubro;
    }

    public void setTipoNota(EvaluacionFormula_TipoNotaUi tipoNota) {
        this.tipoNota = tipoNota;
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

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        boolean equals = false;
        if (this.getClass() != obj.getClass()) {
            return equals;
        }
        NotaUi nota = (NotaUi) obj;
        if (nota.getId().equals(id)) {
            equals = true;
        }
        return equals;
    }

    public int getTipos() {
        return tipos;
    }

    public void setTipos(int tipos) {
        this.tipos = tipos;
    }

    public double getaDoubleNota() {
        return aDoubleNota;
    }

    public void setaDoubleNota(double aDoubleNota) {
        this.aDoubleNota = aDoubleNota;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }


    @Override
    public String toString() {
        return "NotaUi{" +
                "id='" + id + '\'' +
                ", nota='" + nota + '\'' +
                ", aDoubleNota=" + aDoubleNota +
                ", escala='" + escala + '\'' +
                ", valorTipoNotaId='" + valorTipoNotaId + '\'' +
                ", alumno=" + alumno +
                ", rubro=" + rubro +
                ", tipoNota=" + tipoNota +
                ", competenciaId=" + competenciaId +
                ", tipos=" + tipos +
                ", icono='" + icono + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
