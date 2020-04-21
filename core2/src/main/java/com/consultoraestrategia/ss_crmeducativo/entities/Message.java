package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDatabase.class)
public class Message extends BaseModel {
    @PrimaryKey
    private String id ;
    @Column
    private String mensaje ;
    @Column
    private int cargaCursoId ;
    @Column
    private int cargaAcademicaId;
    @Column
    private int personaId;
    @Column
    private String docenteId;
    @Column
    private String tipo;
    @Column
    private int estado;
    @Column
    private String chatId ;
    @Column
    private Date lastdate;
    @Column
    private String nombreChat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(String docenteId) {
        this.docenteId = docenteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public String getNombreChat() {
        return nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", cargaCursoId=" + cargaCursoId +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", personaId=" + personaId +
                ", docenteId='" + docenteId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado=" + estado +
                ", chatId='" + chatId + '\'' +
                ", lastdate=" + lastdate +
                ", nombreChat='" + nombreChat + '\'' +
                '}';
    }
}
