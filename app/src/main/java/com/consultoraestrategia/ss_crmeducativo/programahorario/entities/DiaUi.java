package com.consultoraestrategia.ss_crmeducativo.programahorario.entities;

public class   DiaUi {
    public enum COLOR{AZUL("#ff0399ac"), AMARILLO("#ffeabb15"), VERDE("#ff89b93a"), ROSADO("#fff3939a"), ANARANJADO("#ffe16c02"),GUINDA("#ffb21437"),PLOMO("#ff284448");
        private String nombre;

        COLOR(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    private int id;
    private String nombre;
    private String alias;
    private boolean desabled = true;
    private COLOR color = COLOR.PLOMO;

    public DiaUi(int id, String nombre, String alias, COLOR color) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.color = color;
    }

    public DiaUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public boolean isDesabled() {
        return desabled;
    }

    public void setDesabled(boolean desabled) {
        this.desabled = desabled;
    }
}
