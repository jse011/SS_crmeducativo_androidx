package com.consultoraestrategia.ss_crmeducativo.personalChat.entities;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;

import java.io.Serializable;
import java.util.List;

public class SendDataChatBundle implements Serializable {
    public enum TypeChat{PERSONAL, GROUP}
    public  enum TypeGroup{ACADEMIC, COURSE, TEAM}
    public  enum TypePerson{TEACHERS, STUDENTS, PARENTS}
    private int senderId;
    private int cargaCursoId;
    private int cargaAcademicaId;
    private String grupoEquipoId;
    private List<Long> docenteId;

    private TypeChat typeChat;
    private TypeGroup typeGroup;
    private TypePerson typePerson;

    private transient static final String SendDataChatBundle =  "SendDataChatBundle";
    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(SendDataChatBundle, this);
        return bundle;
    }

    public static SendDataChatBundle clone(Bundle bundle) {
        return (SendDataChatBundle)bundle.getSerializable(SendDataChatBundle);
    }

    public TypeGroup getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(TypeGroup typeGroup) {
        this.typeGroup = typeGroup;
    }

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePerson typePerson) {
        this.typePerson = typePerson;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
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

    public TypeChat getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(TypeChat typeChat) {
        this.typeChat = typeChat;
    }


}
