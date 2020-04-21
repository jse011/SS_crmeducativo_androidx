package com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class MessageUi2 {
    public enum TIPO{TEXTO, IMAGEN}
    private boolean pendingWrites;
    private String salaId;
    private String salaTipo;
    private int cargaAcademicaId;
    private int cargaCursoId;
    private String grupoEquipoId;
    private List<Long> docenteId;
    private PersonaUi personaUi;
    private String nombreEmisor;
    private String menssageId;
    private boolean enviarNotificacion;
    private String nombreGrupo;
    private String colorGrupo;
    private String aliasGrupo;
    private List<String> mensajes;
    private boolean view;
    private TIPO tipo = TIPO.TEXTO;
    private transient String imagen;
    private transient String imagenFcm;
    private transient boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setPendingWrites(boolean pendingWrites) {
        this.pendingWrites = pendingWrites;
    }

    public boolean getPendingWrites() {
        return pendingWrites;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaTipo(String salaTipo) {
        this.salaTipo = salaTipo;
    }

    public String getSalaTipo() {
        return salaTipo;
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

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public List<Long> getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(List<Long> docenteId) {
        this.docenteId = docenteId;
    }

    public void setPersonaUi(PersonaUi personaUi) {
        this.personaUi = personaUi;
    }

    public PersonaUi getPersonaUi() {
        return personaUi;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setEnviarNotificacion(boolean enviarNotificacion) {
        this.enviarNotificacion = enviarNotificacion;
    }

    public boolean getEnviarNotificacion() {
        return enviarNotificacion;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setColorGrupo(String colorGrupo) {
        this.colorGrupo = colorGrupo;
    }

    public String getColorGrupo() {
        return colorGrupo;
    }

    public void setAliasGrupo(String aliasGrupo) {
        this.aliasGrupo = aliasGrupo;
    }

    public String getAliasGrupo() {
        return aliasGrupo;
    }

    public boolean getView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public enum ESTADO{CREADO,GUARDADO,ENVIADO, VISTO , ELIMINADO}
    private String Id;
    private int emisorId;
    private int receptorId;
    private String mensaje;
    private List<String> referencia;
    private transient Date fecha;
    private long dataTime;
    private ESTADO estado = ESTADO.ELIMINADO;
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(int emisorId) {
        this.emisorId = emisorId;
    }

    public int getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getReferencia() {
        return referencia;
    }

    public void setReferencia(List<String> referencia) {
        this.referencia = referencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    public String getMenssageId() {
        return menssageId;
    }

    public void setMenssageId(String menssageId) {
        this.menssageId = menssageId;
    }

    public boolean isPendingWrites() {
        return pendingWrites;
    }

    public boolean isEnviarNotificacion() {
        return enviarNotificacion;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }


    //Region Replick
    private transient String mensajeReplickId;
    private transient String mensajeReplick;
    private transient String imagenReplick;
    private transient String personaReplick;

    public String getMensajeReplickId() {
        return mensajeReplickId;
    }

    public void setMensajeReplickId(String mensajeReplickId) {
        this.mensajeReplickId = mensajeReplickId;
    }

    public String getMensajeReplick() {
        return mensajeReplick;
    }

    public void setMensajeReplick(String mensajeReplick) {
        this.mensajeReplick = mensajeReplick;
    }

    public String getImagenReplick() {
        return imagenReplick;
    }

    public void setImagenReplick(String imagenReplick) {
        this.imagenReplick = imagenReplick;
    }

    public String getPersonaReplick() {
        return personaReplick;
    }

    public void setPersonaReplick(String personaReplick) {
        this.personaReplick = personaReplick;
    }

    public String getImagenFcm() {
        return imagenFcm;
    }

    public void setImagenFcm(String imagenFcm) {
        this.imagenFcm = imagenFcm;
    }
}
