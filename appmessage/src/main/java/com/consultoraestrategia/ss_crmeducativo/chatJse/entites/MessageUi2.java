package com.consultoraestrategia.ss_crmeducativo.chatJse.entites;

import com.google.firebase.firestore.Blob;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MessageUi2 {
    public enum TIPO{TEXTO, IMAGEN}
    private boolean pendingWrites;
    private boolean view;
    private long dataTime;
    private boolean enviarNotificacion;
    private List<String> mensajes;
    private String tokenFcm;
    private MessageUi2.TIPO tipo = TIPO.TEXTO;
    private transient String imagen;
    private transient String imagenFcm;
    private transient boolean selected;

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
