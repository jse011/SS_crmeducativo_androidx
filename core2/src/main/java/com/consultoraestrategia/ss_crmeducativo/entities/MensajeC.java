package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.List;

/**
 * Created by irvinmarin on 17/08/2017.
 */
@Table(database = AppDatabase.class)
public class MensajeC extends BaseEntity {

    @Column
    private String mensajeId;
    @Column
    private int usuarioOrigenId;
    @Column
    private int categoriaId;
    @Column
    private int canalComId;
    @Column
    private int cargaCursoId;
    @Column
    private int alumnoId;
    @Column
    private int entidadId;
    @Column
    private int georeferenciaId;
    @Column
    private int organigramaId;
    @Column
    private int intencionId;
    @Column
    private String asunto;
    @Column
    private String contenido;
    @Column
    private int asistenciaAlumnoId;
    @Column
    private int evaluacionResultadoId;
    @Column
    private boolean destacado;
    @Column
    private boolean importante;
    @Column
    private String fechaEnvio;

    @Column
    private int estadoExportado;
    @Column
    private String idChatMessage;

    public List<MensajeUsuarioC> mensajeUsuario;

    public List<MensajeIntencionItemC> mensajeIntencionItem;


    public MensajeC() {
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public int getUsuarioOrigenId() {
        return usuarioOrigenId;
    }

    public void setUsuarioOrigenId(int usuarioOrigenId) {
        this.usuarioOrigenId = usuarioOrigenId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getCanalComId() {
        return canalComId;
    }

    public void setCanalComId(int canalComId) {
        this.canalComId = canalComId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
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

    public int getOrganigramaId() {
        return organigramaId;
    }

    public void setOrganigramaId(int organigramaId) {
        this.organigramaId = organigramaId;
    }

    public int getIntencionId() {
        return intencionId;
    }

    public void setIntencionId(int intencionId) {
        this.intencionId = intencionId;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getAsistenciaAlumnoId() {
        return asistenciaAlumnoId;
    }

    public void setAsistenciaAlumnoId(int asistenciaAlumnoId) {
        this.asistenciaAlumnoId = asistenciaAlumnoId;
    }

    public int getEvaluacionResultadoId() {
        return evaluacionResultadoId;
    }

    public void setEvaluacionResultadoId(int evaluacionResultadoId) {
        this.evaluacionResultadoId = evaluacionResultadoId;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }

    public String getIdChatMessage() {
        return idChatMessage;
    }

    public void setIdChatMessage(String idChatMessage) {
        this.idChatMessage = idChatMessage;
    }

    public List<MensajeUsuarioC> getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(List<MensajeUsuarioC> mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public List<MensajeIntencionItemC> getMensajeIntencionItem() {
        return mensajeIntencionItem;
    }

    public void setMensajeIntencionItem(List<MensajeIntencionItemC> mensajeIntencionItem) {
        this.mensajeIntencionItem = mensajeIntencionItem;
    }
}
