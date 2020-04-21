package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades;


import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;

import java.util.List;

public class AsistenciaUi {
    private String id;
    private int idProgramaEducativo;//
    private int idCargaCurso;//
    private int idGeoreferencia;//
    private int idCalendarioPeriodo;//
    private int idDocente;//
    private int idCargaAcademica;
    private AlumnosUi alumnosUi;
    private ValorTipoNotaUi valorTipoNotaUi;
    private TipoNotaUi tipoNotaUi;
    private long fecha;//
    private RecyclerView.ViewHolder viewHolder;
    private JustificacionUi justificacionUi;
    private List<AsistenicaArchivoUi> asistenicaArchivoUiList;
    private String color;
    private boolean selected;
    private boolean modificado;
    private String hora;



    public AsistenciaUi() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdCargaAcademica() {
        return idCargaAcademica;
    }

    public void setIdCargaAcademica(int idCargaAcademica) {
        this.idCargaAcademica = idCargaAcademica;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public int getIdCargaCurso() {
        return idCargaCurso;
    }

    public void setIdCargaCurso(int idCargaCurso) {
        this.idCargaCurso = idCargaCurso;
    }

    public int getIdGeoreferencia() {
        return idGeoreferencia;
    }

    public void setIdGeoreferencia(int idGeoreferencia) {
        this.idGeoreferencia = idGeoreferencia;
    }

    public int getIdCalendarioPeriodo() {
        return idCalendarioPeriodo;
    }

    public void setIdCalendarioPeriodo(int idCalendarioPeriodo) {
        this.idCalendarioPeriodo = idCalendarioPeriodo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlumnosUi getAlumnosUi() {
        return alumnosUi;
    }

    public void setAlumnosUi(AlumnosUi alumnosUi) {
        this.alumnosUi = alumnosUi;
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public RecyclerView.ViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public JustificacionUi getJustificacionUi() {
        return justificacionUi;
    }

    public void setJustificacionUi(JustificacionUi justificacionUi) {
        this.justificacionUi = justificacionUi;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsistenciaUi that = (AsistenciaUi) o;

        return alumnosUi != null ? alumnosUi.equals(that.alumnosUi) : that.alumnosUi == null;
    }

    @Override
    public int hashCode() {
        return alumnosUi != null ? alumnosUi.hashCode() : 0;
    }

    public List<AsistenicaArchivoUi> getAsistenicaArchivoUiList() {
        return asistenicaArchivoUiList;
    }

    public void setAsistenicaArchivoUiList(List<AsistenicaArchivoUi> asistenicaArchivoUiList) {
        this.asistenicaArchivoUiList = asistenicaArchivoUiList;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public boolean isModificado() {
        return modificado;
    }
}
