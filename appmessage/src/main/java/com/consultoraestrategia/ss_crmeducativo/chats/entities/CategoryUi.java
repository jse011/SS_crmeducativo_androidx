package com.consultoraestrategia.ss_crmeducativo.chats.entities;

import java.io.Serializable;

public class CategoryUi implements Serializable {

    public enum Type{PERIOD, SECTION, COURSE, TEAM, ACADEMIC}
    private String name;
    private Type type;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
