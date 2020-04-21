package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades;



import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.ValorTipoNotaUi;


import org.parceler.Parcel;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */
@Parcel
public class RubBidUi {

    public enum Forma{GRUPAL,INDIVIDUAL}
    public enum PublicarEval{TODOS, PARCIAL, NINGUNO}
    public int id;
    public int posicion;
    public String key;
    public String title;
    public String alias;
    public String fecha;
    public int sesionAprendizajeId;
    public List<RubEvalProcUi> rubroEvalProcesoList;
    public List<ValorTipoNotaUi> valorTipoNotaUiList;
    public TipoUi tipoEvaluacion;
    public TipoUi formaEvaluacion;
    public Forma forma;
    public double media;
    public double desviacionE;
    public int estadoMsje;
    public boolean disabledEval;//Si esta true se desabilita la actividad de evalucion;
    public OrigenUi origenUi = OrigenUi.AREA;
    public RubBidUi() {
        forma = Forma.INDIVIDUAL;
    }
    public PublicarEval publicarEval = PublicarEval.NINGUNO;
    public boolean editar;

    public RubBidUi(int id, String title) {
        this.id = id;
        this.title = title;
        forma = Forma.INDIVIDUAL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<RubEvalProcUi> getRubroEvalProcesoList() {
        return rubroEvalProcesoList;
    }

    public void setRubroEvalProcesoList(List<RubEvalProcUi> rubroEvalProcesoList) {
        this.rubroEvalProcesoList = rubroEvalProcesoList;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUiList() {
        return valorTipoNotaUiList;
    }

    public void setValorTipoNotaUiList(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
    }

    public TipoUi getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(TipoUi tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public TipoUi getFormaEvaluacion() {
        return formaEvaluacion;
    }

    public void setFormaEvaluacion(TipoUi formaEvaluacion) {
        this.formaEvaluacion = formaEvaluacion;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public boolean isDisabledEval() {
        return disabledEval;
    }

    public void setDisabledEval(boolean disabledEval) {
        this.disabledEval = disabledEval;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getDesviacionE() {
        return desviacionE;
    }

    public void setDesviacionE(double desviacionE) {
        this.desviacionE = desviacionE;
    }

    public void setEstadoMsje(int estadoMsje) {
        this.estadoMsje = estadoMsje;
    }

    public int getEstadoMsje() {
        return estadoMsje;
    }

    public OrigenUi getOrigenUi() {
        return origenUi;
    }

    public void setOrigenUi(OrigenUi origenUi) {
        this.origenUi = origenUi;
    }

    public PublicarEval getPublicarEval() {
        return publicarEval;
    }

    public void setPublicarEval(PublicarEval publicarEval) {
        this.publicarEval = publicarEval;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    @Override
    public String toString() {
        return "RubBidUi{" +
                "id=" + id +
                ", posicion=" + posicion +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", alias='" + alias + '\'' +
                ", fecha='" + fecha + '\'' +
                ", sesionAprendizajeId=" + sesionAprendizajeId +
                '}';
    }
}
