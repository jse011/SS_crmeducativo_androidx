package com.consultoraestrategia.ss_crmeducativo.chatJse.entites;

import java.util.Date;
import java.util.List;

public class MessageUi2 {

    private String nameChat;
    private String imagenChat;

    public String getNameChat() {
        return nameChat;
    }

    public void setNameChat(String nameChat) {
        this.nameChat = nameChat;
    }

    public void setImagenChat(String imagenChat) {
        this.imagenChat = imagenChat;
    }

    public String getImagenChat() {
        return imagenChat;
    }

    public enum TIPO{TEXTO, IMAGEN, STICKER}
    private boolean pendingWrites;
    private boolean view;
    private long dataTime;
    private boolean enviarNotificacion;
    private List<String> mensajes;
    private String tokenFcm;
    private TIPO tipo = TIPO.TEXTO;
    private transient String imagen;
    private transient String imagenFcm;
    private transient boolean selected;
    private int personaIdReplick;

    public void setPersonaIdReplick(int personaIdReplick) {
        this.personaIdReplick = personaIdReplick;
    }

    public int getPersonaIdReplick() {
        return personaIdReplick;
    }
    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenFcm() {
        return imagenFcm;
    }

    public void setImagenFcm(String imagenFcm) {
        this.imagenFcm = imagenFcm;
    }

    public void setPendingWrites(boolean pendingWrites) {
        this.pendingWrites = pendingWrites;
    }

    public boolean getPendingWrites() {
        return pendingWrites;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean getView() {
        return view;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public boolean getEnviarNotificacion() {
        return enviarNotificacion;
    }

    public void setEnviarNotificacion(boolean enviarNotificacion) {
        this.enviarNotificacion = enviarNotificacion;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public String getTokenFcm() {
        return tokenFcm;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public enum ESTADO{CREADO,GUARDADO,ENVIADO, VISTO , ELIMINADO}
    private String Id;
    private int emisorId;
    private int receptorId;
    private String mensaje;
    private List<String> referencia;
    private Date fecha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageUi2 that = (MessageUi2) o;

        if (pendingWrites != that.pendingWrites) return false;
        if (view != that.view) return false;
        if (dataTime != that.dataTime) return false;
        if (enviarNotificacion != that.enviarNotificacion) return false;
        if (selected != that.selected) return false;
        if (personaIdReplick != that.personaIdReplick) return false;
        if (emisorId != that.emisorId) return false;
        if (receptorId != that.receptorId) return false;
        if (nameChat != null ? !nameChat.equals(that.nameChat) : that.nameChat != null)
            return false;
        if (imagenChat != null ? !imagenChat.equals(that.imagenChat) : that.imagenChat != null)
            return false;
        if (mensajes != null ? !mensajes.equals(that.mensajes) : that.mensajes != null)
            return false;
        if (tokenFcm != null ? !tokenFcm.equals(that.tokenFcm) : that.tokenFcm != null)
            return false;
        if (tipo != that.tipo) return false;
        if (imagen != null ? !imagen.equals(that.imagen) : that.imagen != null) return false;
        if (imagenFcm != null ? !imagenFcm.equals(that.imagenFcm) : that.imagenFcm != null)
            return false;
        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (mensaje != null ? !mensaje.equals(that.mensaje) : that.mensaje != null) return false;
        if (referencia != null ? !referencia.equals(that.referencia) : that.referencia != null)
            return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (estado != that.estado) return false;
        if (mensajeReplickId != null ? !mensajeReplickId.equals(that.mensajeReplickId) : that.mensajeReplickId != null)
            return false;
        if (mensajeReplick != null ? !mensajeReplick.equals(that.mensajeReplick) : that.mensajeReplick != null)
            return false;
        if (imagenReplick != null ? !imagenReplick.equals(that.imagenReplick) : that.imagenReplick != null)
            return false;
        return personaReplick != null ? personaReplick.equals(that.personaReplick) : that.personaReplick == null;
    }

    @Override
    public int hashCode() {
        int result = nameChat != null ? nameChat.hashCode() : 0;
        result = 31 * result + (imagenChat != null ? imagenChat.hashCode() : 0);
        result = 31 * result + (pendingWrites ? 1 : 0);
        result = 31 * result + (view ? 1 : 0);
        result = 31 * result + (int) (dataTime ^ (dataTime >>> 32));
        result = 31 * result + (enviarNotificacion ? 1 : 0);
        result = 31 * result + (mensajes != null ? mensajes.hashCode() : 0);
        result = 31 * result + (tokenFcm != null ? tokenFcm.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (imagen != null ? imagen.hashCode() : 0);
        result = 31 * result + (imagenFcm != null ? imagenFcm.hashCode() : 0);
        result = 31 * result + (selected ? 1 : 0);
        result = 31 * result + personaIdReplick;
        result = 31 * result + (Id != null ? Id.hashCode() : 0);
        result = 31 * result + emisorId;
        result = 31 * result + receptorId;
        result = 31 * result + (mensaje != null ? mensaje.hashCode() : 0);
        result = 31 * result + (referencia != null ? referencia.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (mensajeReplickId != null ? mensajeReplickId.hashCode() : 0);
        result = 31 * result + (mensajeReplick != null ? mensajeReplick.hashCode() : 0);
        result = 31 * result + (imagenReplick != null ? imagenReplick.hashCode() : 0);
        result = 31 * result + (personaReplick != null ? personaReplick.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageUi2{" +
                "pendingWrites=" + pendingWrites +
                ", Id='" + Id + '\'' +
                ", emisorId=" + emisorId +
                ", receptorId=" + receptorId +
                ", mensaje='" + mensaje + '\'' +
                ", referencia=" + referencia +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }


}
