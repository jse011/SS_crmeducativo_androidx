package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;





import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */
@Parcel
public class IndicadorUi extends Cell implements Serializable {
    public int indicadorId;
    public String titulo;
    public String alias;
    public String tituloRubro;
    public String subTituloRubro;
    public String descripcion;
    public String estado;
    public String peso;
    public boolean checked;
    public TipoIndicadorUi tipoIndicadorUi = TipoIndicadorUi.DEFAULT;
    public List<CampoAccionUi> campoAccionList = new ArrayList<>();
    public CapacidadUi capacidadOwner;
    public CompetenciaUi competenciaOwner;
    public String desempenioDesc;
    public String TipoNotaId;
    public String rubroId;
    public List<CriterioEvaluacionUi> criterioEvaluacionUiList;
    public String url;

    //Rubrica
    public String posicion;
    public String media;
    public String desviacionE;
    public String origen;
    public String formEvaluacion;
    public String tipoEvaluacion;
    public String fecha;
    public String tituloRubrica;

    public IndicadorUi() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipoNotaId() {
        return TipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        TipoNotaId = tipoNotaId;
    }

    public int getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPeso() {
        return peso;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public List<CampoAccionUi> getCampoAccionList() {
        return campoAccionList;
    }

    public void setCampoAccionList(List<CampoAccionUi> campoAccionList) {
        this.campoAccionList = campoAccionList;
    }

    public CapacidadUi getCapacidadOwner() {
        return capacidadOwner;
    }

    public void setCapacidadOwner(CapacidadUi capacidadOwner) {
        this.capacidadOwner = capacidadOwner;
    }

    public CompetenciaUi getCompetenciaOwner() {
        return competenciaOwner;
    }

    public void setCompetenciaOwner(CompetenciaUi competenciaOwner) {
        this.competenciaOwner = competenciaOwner;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndicadorUi)) return false;

        IndicadorUi that = (IndicadorUi) o;

        if (getIndicadorId() != that.getIndicadorId()) return false;
        if (getCapacidadOwner() != null ? !getCapacidadOwner().equals(that.getCapacidadOwner()) : that.getCapacidadOwner() != null)
            return false;
        return getCompetenciaOwner() != null ? getCompetenciaOwner().equals(that.getCompetenciaOwner()) : that.getCompetenciaOwner() == null;
    }

    @Override
    public int hashCode() {
        int result = getIndicadorId();
        result = 31 * result + (getCapacidadOwner() != null ? getCapacidadOwner().hashCode() : 0);
        result = 31 * result + (getCompetenciaOwner() != null ? getCompetenciaOwner().hashCode() : 0);
        return result;
    }

    public String getTituloRubro() {
        return tituloRubro;
    }

    public void setTituloRubro(String tituloRubro) {
        this.tituloRubro = tituloRubro;
    }

    public String getDesempenioDesc() {
        return desempenioDesc;
    }

    public void setDesempenioDesc(String desempenioDesc) {
        this.desempenioDesc = desempenioDesc;
    }

    public TipoIndicadorUi getTipoIndicadorUi() {
        return tipoIndicadorUi;
    }

    public void setTipoIndicadorUi(TipoIndicadorUi tipoIndicadorUi) {
        this.tipoIndicadorUi = tipoIndicadorUi;
    }

    @Override
    public String toString() {
        return "IndicadorUi{" +
                "indicadorId=" + indicadorId +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", tituloRubro='" + tituloRubro + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", peso='" + peso + '\'' +
                ", checked=" + checked +
                ", capacidadOwner=" + capacidadOwner +
                ", competenciaOwner=" + competenciaOwner +
                ", desempenioDesc='" + desempenioDesc + '\'' +
                ", TipoNotaId='" + TipoNotaId + '\'' +
                '}';
    }

    public String getRubroId() {
        return rubroId;
    }

    public void setRubroId(String rubroId) {
        this.rubroId = rubroId;
    }

    public List<CriterioEvaluacionUi> getCriterioEvaluacionUiList() {
        return criterioEvaluacionUiList;
    }

    public void setCriterioEvaluacionUiList(List<CriterioEvaluacionUi> criterioEvaluacionUiList) {
        this.criterioEvaluacionUiList = criterioEvaluacionUiList;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDesviacionE() {
        return desviacionE;
    }

    public void setDesviacionE(String desviacionE) {
        this.desviacionE = desviacionE;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getFormEvaluacion() {
        return formEvaluacion;
    }

    public void setFormEvaluacion(String formEvaluacion) {
        this.formEvaluacion = formEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTituloRubrica() {
        return tituloRubrica;
    }

    public void setTituloRubrica(String tituloRubrica) {
        this.tituloRubrica = tituloRubrica;
    }

    public String getSubTituloRubro() {
        return subTituloRubro;
    }

    public void setSubTituloRubro(String subTituloRubro) {
        this.subTituloRubro = subTituloRubro;
    }
}
