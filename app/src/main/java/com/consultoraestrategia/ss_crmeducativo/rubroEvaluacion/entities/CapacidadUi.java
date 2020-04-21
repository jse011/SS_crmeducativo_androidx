package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */
@Parcel
public class CapacidadUi {
    public int id;
    public boolean toogle;
    public String title;
    public List<RubroProcesoUi> rubroProcesoUis;
    public int cantidad;
    public int posicion;
    public CompetenciaUi competenciaUi;
    public int silaboEventoId;
    public int nivel;
    public int calendarioId;
    public TipoFormulaUi tipoFormulaUi;
    public TipoRedondeadoUi tipoRedondeadoUi;
    public TipoEscalaEvaluacionUi tipoEscalaEvaluacionUi;
    public TipoEvaluacionUi tipoEvaluacionUi;
    public TipoNotaUi tipoNotaUi;
    public String rubroProcesoIdSelect;
    public boolean btnSelect;
    public RubroProcesoUi rubroEvalAnclado;
    public boolean tieneResultado;
    public boolean calendarioEditar;
    public boolean calendarioAnclar;
    @Transient
    Parcelable recylerViewState;

    public CapacidadUi() {
        this.rubroProcesoUis = new ArrayList<>();
    }

    public CapacidadUi(int id) {
        this.id = id;
        this.rubroProcesoUis = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<RubroProcesoUi> getRubroProcesoUis() {
        return rubroProcesoUis;
    }

    public void setRubroProcesoUis(List<RubroProcesoUi> rubroProcesoUis) {
        this.rubroProcesoUis = rubroProcesoUis;
    }

    public void addRubroProceso(RubroProcesoUi rubroProcesoUi){
        this.rubroProcesoUis.add(rubroProcesoUi);
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public CompetenciaUi getCompetenciaUi() {
        return competenciaUi;
    }

    public void setCompetenciaUi(CompetenciaUi competenciaUi) {
        this.competenciaUi = competenciaUi;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(int calendarioId) {
        this.calendarioId = calendarioId;
    }

    public TipoFormulaUi getTipoFormulaUi() {
        return tipoFormulaUi;
    }

    public void setTipoFormulaUi(TipoFormulaUi tipoFormulaUi) {
        this.tipoFormulaUi = tipoFormulaUi;
    }

    public TipoRedondeadoUi getTipoRedondeadoUi() {
        return tipoRedondeadoUi;
    }

    public void setTipoRedondeadoUi(TipoRedondeadoUi tipoRedondeadoUi) {
        this.tipoRedondeadoUi = tipoRedondeadoUi;
    }

    public TipoEscalaEvaluacionUi getTipoEscalaEvaluacionUi() {
        return tipoEscalaEvaluacionUi;
    }

    public void setTipoEscalaEvaluacionUi(TipoEscalaEvaluacionUi tipoEscalaEvaluacionUi) {
        this.tipoEscalaEvaluacionUi = tipoEscalaEvaluacionUi;
    }

    public TipoEvaluacionUi getTipoEvaluacionUi() {
        return tipoEvaluacionUi;
    }

    public void setTipoEvaluacionUi(TipoEvaluacionUi tipoEvaluacionUi) {
        this.tipoEvaluacionUi = tipoEvaluacionUi;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapacidadUi that = (CapacidadUi) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getRubroProcesoIdSelect() {
        return rubroProcesoIdSelect;
    }

    public void setRubroProcesoIdSelect(String rubroProcesoIdSelect) {
        this.rubroProcesoIdSelect = rubroProcesoIdSelect;
    }

    public boolean isBtnSelect() {
        return btnSelect;
    }

    public void setBtnSelect(boolean btnSelect) {
        this.btnSelect = btnSelect;
    }

    public RubroProcesoUi getRubroEvalAnclado() {
        return rubroEvalAnclado;
    }

    public void setRubroEvalAnclado(RubroProcesoUi rubroEvalAnclado) {
        this.rubroEvalAnclado = rubroEvalAnclado;
    }

    @Override
    public String toString() {
        return "CapacidadUi{" +
                "id=" + id +
                ", toogle=" + toogle +
                ", title='" + title + '\'' +
                '}';
    }

    public boolean isTieneResultado() {
        return tieneResultado;
    }

    public void setTieneResultado(boolean tieneResultado) {
        this.tieneResultado = tieneResultado;
    }

    public boolean isCalendarioEditar() {
        return calendarioEditar;
    }

    public void setCalendarioEditar(boolean calendarioEditar) {
        this.calendarioEditar = calendarioEditar;
    }

    public boolean isCalendarioAnclar() {
        return calendarioAnclar;
    }

    public void setCalendarioAnclar(boolean calendarioAnclar) {
        this.calendarioAnclar = calendarioAnclar;
    }

    public Parcelable getRecylerViewState() {
        return recylerViewState;
    }

    public void setRecylerViewState(Parcelable recylerViewState) {
        this.recylerViewState = recylerViewState;
    }
}
