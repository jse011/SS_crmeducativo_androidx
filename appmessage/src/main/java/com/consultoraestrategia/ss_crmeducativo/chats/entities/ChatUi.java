package com.consultoraestrategia.ss_crmeducativo.chats.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatUi {
    private int cargaCursoId;
    private int cargaAcademicaId;
    private String grupoEquipoId;
    private List<Long> docenteId;
    private String id;
    private String mensageId;
    private long count;

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setDocenteId(List<Long> docenteId) {
        this.docenteId = docenteId;
    }

    public List<Long> getDocenteId() {
        return docenteId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMensageId(String mensageId) {
        this.mensageId = mensageId;
    }

    public String getMensageId() {
        return mensageId;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public enum ESTADO{ CREADO, GUARDADO, ENVIADO, VISTO , ELIMINADO}
    public  enum TypeChat{PERSONAL, GROUP}
    public  enum TypeGroup{ACADEMIC, COURSE, TEAM}
    public  enum TypePerson{TEACHERS, STUDENTS, PARENTS}
    private String imageRec;
    private String name;
    private String lastMsg;
    private int idSender;
    private int idReceiver;
    private Date lastDate;
    private String code;
    private TypeChat typeChat = TypeChat.PERSONAL;
    private TypeGroup typeGroup=TypeGroup.TEAM;
    private String color;
    private TypePerson typePerson;
    private ESTADO estado = ESTADO.ELIMINADO;
    private String salaId;
    private String salaTipo;
    private String salaLocalId;

    public String getImageRec() {
        return imageRec;
    }

    public void setImageRec(String imageRec) {
        this.imageRec = imageRec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeChat getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(TypeChat typeChat) {
        this.typeChat = typeChat;
    }

    public TypeGroup getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(TypeGroup typeGroup) {
        this.typeGroup = typeGroup;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePerson typePerson) {
        this.typePerson = typePerson;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }


    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getSalaTipo() {
        return salaTipo;
    }

    public void setSalaTipo(String salaTipo) {
        this.salaTipo = salaTipo;
    }

    public String getSalaLocalId() {
        return salaLocalId;
    }

    public void setSalaLocalId(String salaLocalId) {
        this.salaLocalId = salaLocalId;
    }

}
