package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class TipoNotaC extends BaseEntity {

    public static final int VALOR_NUMERICO = 410;
    public static final int SELECTOR_NUMERICO = 411;
    public static final int SELECTOR_VALORES = 412;
    public static final int SELECTOR_ICONOS = 409;
    public static final int VALOR_ASISTENCIA= 474;

    @Column
    private String tipoNotaId;
    @Column
    private String nombre;
    @Column
    private int tipoId;
    @Column
    private String valorDefecto;
    @Column
    private int valorMaximo;
    @Column
    private int valorMinino;
    @Column
    private double longitudPaso;
    @Column
    private boolean intervalo;
    @Column
    private boolean estatico;
    @Column
    private int entidadId;
    @Column
    private int georeferenciaId;
    @Column
    private int organigramaId;
    @Column
    private int estadoId;
    @Column
    private int escalaEvaluacionId;
    @Column
    private int tipoFuenteId;


    public TipoNotaC() {
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getTipoFuenteId() {
        return tipoFuenteId;
    }

    public void setTipoFuenteId(int tipoFuenteId) {
        this.tipoFuenteId = tipoFuenteId;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getValorMinino() {
        return valorMinino;
    }

    public void setValorMinino(int valorMinino) {
        this.valorMinino = valorMinino;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }

    public void setLongitudPaso(double longitudPaso) {
        this.longitudPaso = longitudPaso;
    }


    public boolean isIntervalo() {
        return intervalo;
    }

    public void setIntervalo(boolean intervalo) {
        this.intervalo = intervalo;
    }

    public boolean isEstatico() {
        return estatico;
    }

    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public int getOrganigramaId() {
        return organigramaId;
    }

    public void setOrganigramaId(int organigramaId) {
        this.organigramaId = organigramaId;
    }

    public int getEscalaEvaluacionId() {
        return escalaEvaluacionId;
    }

    public void setEscalaEvaluacionId(int escalaEvaluacionId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
    }


    private List<ValorTipoNotaC> valorTipoNotaList = new ArrayList<>();
    private EscalaEvaluacion escalaEvaluacion;

    public List<ValorTipoNotaC> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public void setValorTipoNotaList(List<ValorTipoNotaC> valorTipoNotaList) {
        if (valorTipoNotaList != null) {
            this.valorTipoNotaList = valorTipoNotaList;
        }
    }

    public EscalaEvaluacion getEscalaEvaluacion() {
        return escalaEvaluacion;
    }

    public void setEscalaEvaluacion(EscalaEvaluacion escalaEvaluacion) {
        this.escalaEvaluacion = escalaEvaluacion;
    }

    @Override
    public String toString() {
        return "TipoNotaC{" +
                "tipoNotaId='" + tipoNotaId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoId=" + tipoId +
                ", valorDefecto='" + valorDefecto + '\'' +
                ", valorMaximo=" + valorMaximo +
                ", valorMinino=" + valorMinino +
                ", longitudPaso=" + longitudPaso +
                ", intervalo=" + intervalo +
                ", estatico=" + estatico +
                ", entidadId=" + entidadId +
                ", georeferenciaId=" + georeferenciaId +
                ", organigramaId=" + organigramaId +
                ", estadoId=" + estadoId +
                ", escalaEvaluacionId=" + escalaEvaluacionId +
                ", tipoFuenteId=" + tipoFuenteId +
                ", valorTipoNotaList=" + valorTipoNotaList +
                ", escalaEvaluacion=" + escalaEvaluacion +
                '}';
    }
}
