package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 17/10/2017.
 */

@Parcel
public class NotaUi {
    public ValorTipoNotaUi valorTipoNotaUi;

    public enum Tipo{ VALOR_NUMERO, SELECTOR_VALORES, NOTA_FINAL }
    public String id;
    public String nota;//nota
    public String escala;
    public String valorTipoNotaId;
    public AlumnoUi alumno;
    public RubroEvaluacionUi rubro;
    public TipoNotaUi tipoNota;
    public int competenciaId;
    public int tipos;
    public Tipo tipo= Tipo.VALOR_NUMERO;


    public NotaUi() {
    }


    public NotaUi(String id, String nota, String escala, String valorTipoNotaId, AlumnoUi alumno, RubroEvaluacionUi rubro, TipoNotaUi tipoNota, int competenciaId,int tipos) {
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

    public NotaUi(String id, String nota) {
        this.id = id;
        this.nota = nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public AlumnoUi getAlumno() {
        return alumno;
    }

    public RubroEvaluacionUi getRubro() {
        return rubro;
    }

    public TipoNotaUi getTipoNota() {
        return tipoNota;
    }

    public void setAlumno(AlumnoUi alumno) {
        this.alumno = alumno;
    }

    public void setRubro(RubroEvaluacionUi rubro) {
        this.rubro = rubro;
    }

    public void setTipoNota(TipoNotaUi tipoNota) {
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

    public int getTipos() {
        return tipos;
    }

    public void setTipos(int tipos) {
        this.tipos = tipos;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "nota: " + nota + "\n" +
                "competenciaId: " + competenciaId + "\n" +
                "escala: " + escala + "\n" +
                "valorTipoNotaId: " + valorTipoNotaId + "\n" +
                "alumno: " + alumno.toString() + "\n" +
                "RubroEvaluacionUi: " + rubro.toString() + "\n" +
                "tipoNota: " + (tipoNota == null ? null : tipoNota.toString());
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }
}
