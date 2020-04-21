package com.consultoraestrategia.ss_crmeducativo.personalChat.entities;

import java.util.Date;
import java.util.List;

public class ChatUi  {
    public enum TypeChat{PERSONAL, GROUP}
    public enum TypeGroup{COURSE, ACADEMIC, TEAM}
    public  enum TypePerson{TEACHERS, STUDENTS, PARENTS}
    private String nameReceiver;
    private String url_image;
    private String lastDateConexion;
    private String Subtitle;
    private boolean active;
    private String code;
    private TypeChat typeChat;
    private String color;
    private TypeGroup typeGroup;
    private TypePerson typePerson;


    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getLastDateConexion() {
        return lastDateConexion;
    }

    public void setLastDateConexion(String lastDateConexion) {
        this.lastDateConexion = lastDateConexion;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
}
