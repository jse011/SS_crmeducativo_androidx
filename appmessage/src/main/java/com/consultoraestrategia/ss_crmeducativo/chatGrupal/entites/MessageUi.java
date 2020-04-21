package com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites;

import java.util.Date;
import java.util.Map;

public class MessageUi {

    public enum State{SEND, RECEIVED,SEEN }
    private String id;
    private Date date;
    private int idsender;
    private String message;
    private Map<String, Object> views;
    public State state;

    private int idreceiver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int idsender) {
        this.idsender = idsender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getViews() {
        return views;
    }

    public void setViews(Map<String, Object> views) {
        this.views = views;
    }

    public int getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(int idreceiver) {
        this.idreceiver = idreceiver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageUi messageUi = (MessageUi) o;

        return id != null ? id.equals(messageUi.id) : messageUi.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
