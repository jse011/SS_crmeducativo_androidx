package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import java.io.Serializable;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class Nivel implements Serializable {
    private String id;
    private String titulo;
    private String subtitulo;
    private String bgColor;
    private String txtColor;
    private double limiteInferior;
    private double limiteSuperior;
    private double logroGlobal;
    private double progreso;

    public Nivel(String id, String titulo, String subtitulo, String bgColor, String txtColor, double limiteInferior, double limiteSuperior, double logroGlobal, double progreso) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.bgColor = bgColor;
        this.txtColor = txtColor;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.logroGlobal = logroGlobal;
        this.progreso = progreso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double getLogroGlobal() {
        return logroGlobal;
    }

    public void setLogroGlobal(double logroGlobal) {
        this.logroGlobal = logroGlobal;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }
}
