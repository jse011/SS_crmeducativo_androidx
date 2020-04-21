package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class CuentaCorriente extends BaseModel {

    @PrimaryKey
    private int ctaCorrienteId;
    @Column
    private int personaId;
    @Column
    private String fecha;
    @Column
    private String docReferencia;
    @Column
    private long cajaLiquidacionId;
    @Column
    private String glosa;
    @Column
    private double debito;
    @Column
    private double credito;
    @Column
    private int anio;
    @Column
    private long idPlanPago;

    public CuentaCorriente() {
    }

    public CuentaCorriente(int ctaCorrienteId, int personaId, String fecha, String docReferencia, long cajaLiquidacionId, String glosa, double debito, double credito, int anio, long idPlanPago) {
        this.ctaCorrienteId = ctaCorrienteId;
        this.personaId = personaId;
        this.fecha = fecha;
        this.docReferencia = docReferencia;
        this.cajaLiquidacionId = cajaLiquidacionId;
        this.glosa = glosa;
        this.debito = debito;
        this.credito = credito;
        this.anio = anio;
        this.idPlanPago = idPlanPago;
    }

    public int getCtaCorrienteId() {
        return ctaCorrienteId;
    }

    public void setCtaCorrienteId(int ctaCorrienteId) {
        this.ctaCorrienteId = ctaCorrienteId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDocReferencia() {
        return docReferencia;
    }

    public void setDocReferencia(String docReferencia) {
        this.docReferencia = docReferencia;
    }

    public long getCajaLiquidacionId() {
        return cajaLiquidacionId;
    }

    public void setCajaLiquidacionId(long cajaLiquidacionId) {
        this.cajaLiquidacionId = cajaLiquidacionId;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public long getIdPlanPago() {
        return idPlanPago;
    }

    public void setIdPlanPago(long idPlanPago) {
        this.idPlanPago = idPlanPago;
    }
}
