package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;


import java.util.ArrayList;
import java.util.List;

public class ComportamientoUi {
    private TipoComportamientoUi tipoComportamientoUi;

    public void setTipoComportamientoUi(TipoComportamientoUi tipoComportamientoUi) {

        this.tipoComportamientoUi = tipoComportamientoUi;
    }

    public TipoComportamientoUi getTipoComportamientoUi() {
        return tipoComportamientoUi;
    }

    public enum Tipo{ORIGEN, DESTINO}
    private String id;
    private long fecha;
    private TipoUi tipoConducta;
    private String descripcion;
    private AlumnoUi alumnoUi;
    private int idprogramaEducativo;
    private int cargaAcademicaId;
    private int cargaCursoId;
    private int docenteId;
    private int calendarioPeridoId;
    private int entidadId;
    private int georeferenciaId;
    private List<ArchivoUi> archivoUiList = new ArrayList<>();
    private  Tipo tipo=Tipo.ORIGEN;
    private UsuarioUi usuarioDestino;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public TipoUi getTipoConducta() {
        return tipoConducta;
    }

    public void setTipoConducta(TipoUi tipoConducta) {
        this.tipoConducta = tipoConducta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AlumnoUi getAlumnoUi() {
        return alumnoUi;
    }

    public void setAlumnoUi(AlumnoUi alumnoUi) {
        this.alumnoUi = alumnoUi;
    }

    public int getIdprogramaEducativo() {
        return idprogramaEducativo;
    }

    public void setIdprogramaEducativo(int idprogramaEducativo) {
        this.idprogramaEducativo = idprogramaEducativo;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getCalendarioPeridoId() {
        return calendarioPeridoId;
    }

    public void setCalendarioPeridoId(int calendarioPeridoId) {
        this.calendarioPeridoId = calendarioPeridoId;
    }

    public List<ArchivoUi> getArchivoUiList() {
        return archivoUiList;
    }

    public void setArchivoUiList(List<ArchivoUi> archivoUiList) {
        this.archivoUiList = archivoUiList;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public UsuarioUi getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(UsuarioUi usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }
}
