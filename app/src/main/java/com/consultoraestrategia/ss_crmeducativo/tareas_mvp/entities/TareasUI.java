package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by irvinmarin on 06/11/2017.
 */

@Parcel
public class TareasUI  {
    public boolean calendarioVigente;
    public boolean calendarioEditar;
    public boolean progress;
    public boolean exportado;
    public String nombreSesion;

    public boolean isCalendarioVigente() {
        return calendarioVigente;
    }

    public void setCalendarioVigente(boolean calendarioVigente) {
        this.calendarioVigente = calendarioVigente;
    }

    public boolean isCalendarioEditar() {
        return calendarioEditar;
    }

    public void setCalendarioEditar(boolean calendarioEditar) {
        this.calendarioEditar = calendarioEditar;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean isExportado() {
        return exportado;
    }

    public void setExportado(boolean exportado) {
        this.exportado = exportado;
    }

    public String getNombreSesion() {
        return nombreSesion;
    }

    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
    }

    //IU

    public enum Estado{Creado("Creado","#000000"), Publicado("Publicado","#0090f7"), Eliminado("Eliminado","#ff0000");
        private String nombre;
        private String color;
        Estado(String nombre, String color) {
            this.nombre = nombre;
            this.color = color;
        }

        public String getNombre() {
            return nombre;
        }

        public String getColor() {
            return color;
        }
    }
    public String keyTarea;
    public String tituloTarea;
    public String descripcion;
    public String nombreCurso;
    public long fechaCreacionTarea;
    public long fechaLimite;
    public String horaEntrega;
    public String personaPuclicacion;
    public Estado estado = Estado.Creado;
    public boolean isDocente;
    public int idUnidaddAprendizaje;
    public int idSesionAprendizaje;
    public int idSilaboEvento;
    public boolean btnCreateVisible;
    public List<RecursosUI> recursosUIList;
    public RubroEvalProcesoUi rubroEvalProcesoUi;


    public TareasUI() {
    }

    public boolean isDocente() {
        return isDocente;
    }

    public void setDocente(boolean docente) {
        isDocente = docente;
    }

    public boolean isBtnCreateVisible() {
        return btnCreateVisible;
    }

    public void setBtnCreateVisible(boolean btnCreateVisible) {
        this.btnCreateVisible = btnCreateVisible;
    }

    public int getIdSesionAprendizaje() {
        return idSesionAprendizaje;
    }

    public void setIdSesionAprendizaje(int idSesionAprendizaje) {
        this.idSesionAprendizaje = idSesionAprendizaje;
    }

    public TareasUI(String keyTarea, String tituloTarea, String descripcion, String nombreCurso, long fechaCreacionTarea, long fechaLimite, String horaEntrega, String personaPuclicacion, Estado estado, boolean isDocente, int idUnidaddAprendizaje, int idSilaboEvento, boolean btnCreateVisible, List<RecursosUI> recursosUIList) {
        this.keyTarea = keyTarea;
        this.tituloTarea = tituloTarea;
        this.descripcion = descripcion;
        this.nombreCurso = nombreCurso;
        this.fechaCreacionTarea = fechaCreacionTarea;
        this.fechaLimite = fechaLimite;
        this.horaEntrega = horaEntrega;
        this.personaPuclicacion = personaPuclicacion;
        this.estado = estado;
        this.isDocente = isDocente;
        this.idUnidaddAprendizaje = idUnidaddAprendizaje;
        this.idSilaboEvento = idSilaboEvento;
        this.btnCreateVisible = btnCreateVisible;
        this.recursosUIList = recursosUIList;
    }

    public List<RecursosUI> getRecursosUIList() {
        return recursosUIList;
    }

    public void setRecursosUIList(List<RecursosUI> recursosUIList) {
        this.recursosUIList = recursosUIList;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(long fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getPersonaPuclicacion() {
        return personaPuclicacion;
    }

    public void setPersonaPuclicacion(String personaPuclicacion) {
        this.personaPuclicacion = personaPuclicacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getKeyTarea() {
        return keyTarea;
    }

    public void setKeyTarea(String keyTarea) {
        this.keyTarea = keyTarea;
    }

    public String getTituloTarea() {
        return tituloTarea;
    }

    public void setTituloTarea(String tituloTarea) {
        this.tituloTarea = tituloTarea;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public long getFechaCreacionTarea() {
        return fechaCreacionTarea;
    }

    public void setFechaCreacionTarea(long fechaCreacionTarea) {
        this.fechaCreacionTarea = fechaCreacionTarea;
    }

    public int getIdUnidaddAprendizaje() {
        return idUnidaddAprendizaje;
    }

    public void setIdUnidaddAprendizaje(int idUnidaddAprendizaje) {
        this.idUnidaddAprendizaje = idUnidaddAprendizaje;
    }

    public RubroEvalProcesoUi getRubroEvalProcesoUi() {
        return rubroEvalProcesoUi;
    }

    public void setRubroEvalProcesoUi(RubroEvalProcesoUi rubroEvalProcesoUi) {
        this.rubroEvalProcesoUi = rubroEvalProcesoUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TareasUI tareasUI = (TareasUI) o;

        return keyTarea != null ? keyTarea.equals(tareasUI.keyTarea) : tareasUI.keyTarea == null;
    }

    @Override
    public int hashCode() {
        return keyTarea != null ? keyTarea.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TareasUI{" +
                "keyTarea=" + keyTarea +
                ", tituloTarea='" + tituloTarea + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", fechaCreacionTarea='" + fechaCreacionTarea + '\'' +
                ", fechaLimite='" + fechaLimite + '\'' +
                ", horaEntrega='" + horaEntrega + '\'' +
                ", personaPuclicacion='" + personaPuclicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", isDocente=" + isDocente +
                ", idUnidaddAprendizaje=" + idUnidaddAprendizaje +
                ", idSilaboEvento=" + idSilaboEvento +
                ", btnCreateVisible=" + btnCreateVisible +
                ", recursosUIList=" + recursosUIList +
                '}';
    }
}
