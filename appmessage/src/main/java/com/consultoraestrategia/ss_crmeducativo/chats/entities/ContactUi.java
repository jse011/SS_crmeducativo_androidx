package com.consultoraestrategia.ss_crmeducativo.chats.entities;

import java.io.Serializable;
import java.util.List;

public class ContactUi implements Serializable {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public enum Type{CONTACT, SUBCONTACT}
    public enum TypeContact{STUDENT, EXECUTIVE, TEACHER, ALL}
    private String name;
    private int idPerson;
    private String photo;
    private boolean enable;
    private Type type;
    private List<ContactUi> contactUiList;
    private ContactUi contactUiPrincipal;
    private String rol;
    private int originIdPerson;
    private TypeContact typeContact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<ContactUi> getContactUiList() {
        return contactUiList;
    }

    public void setContactUiList(List<ContactUi> contactUiList) {
        this.contactUiList = contactUiList;
    }

    public ContactUi getContactUiPrincipal() {
        return contactUiPrincipal;
    }

    public void setContactUiPrincipal(ContactUi contactUiPrincipal) {
        this.contactUiPrincipal = contactUiPrincipal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getOriginIdPerson() {
        return originIdPerson;
    }

    public void setOriginIdPerson(int originIdPerson) {
        this.originIdPerson = originIdPerson;
    }

    public TypeContact getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(TypeContact typeContact) {
        this.typeContact = typeContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactUi contactUi = (ContactUi) o;

        return idPerson == contactUi.idPerson;
    }

    @Override
    public int hashCode() {
        return idPerson;
    }
}
