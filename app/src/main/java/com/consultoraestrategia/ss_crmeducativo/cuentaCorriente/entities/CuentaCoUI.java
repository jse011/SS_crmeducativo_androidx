package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities;

/**
 * Created by irvinmarin on 12/10/2017.
 */

public class CuentaCoUI {
    private int idCuentaUi;
    private String glosa;
    private String fecha;
    private double debito;
    private double credito;

    public int getIdCuentaUi() {
        return idCuentaUi;
    }

    public void setIdCuentaUi(int idCuentaUi) {
        this.idCuentaUi = idCuentaUi;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public CuentaCoUI(int idCuentaUi, String glosa, String fecha, double debito, double credito) {
        this.idCuentaUi = idCuentaUi;
        this.glosa = glosa;
        this.fecha = fecha;
        this.debito = debito;
        this.credito = credito;
    }

    @Override
    public String toString() {
        return "CuentaCoUI{" +
                "idCuentaUi=" + idCuentaUi +
                ", glosa='" + glosa + '\'' +
                ", fecha='" + fecha + '\'' +
                ", debito=" + debito +
                ", credito=" + credito +
                '}';
    }
}
