package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */
@Parcel
public class RubroProcesoUi extends FormulaCelda {
    public enum PublicarEval{TODOS, PARCIAL, NINGUNO}
    public enum FormEvaluacion{INDIVIDUAL ,GRUPAL, FORMULA}
    public enum TiposRubros{RUBRICA,FORMULA,NORMAL}
    public enum Origen{SESION, SILABO, TAREA}
    public enum Tipo{UNIDIMENCIONAL, BIDIMENCIONAL, BIDIMENCIONAL_DETALLE}
    public enum TipoEvaluacion{SUMATIVA,FORMATIVA}
    public enum TipoFormula{ANCLADA, EVALUADA,DEFECTO}
    public enum EstiloFormula{PLOMO,NEGRO,ANARANJADO}
    public boolean checkDisbled;
    public String key;
   // private int id;
   public String titulo;
    public String tituloRubrica;
    public String subTitulo;
    public String valorPorDefecto;
    public int posicion;
    public boolean check;
    public String fecha;
    public double media;
    public double desviacionE;
    public int tipoEvaluacionId;
    public String tipoNotaId;
    public int tipoFormulaId;
    public int tipoValorRedondeoId;
    public int tipoEscalaEvaluacionId;
    public int colorRubro;
    public int androidId;
    public double peso;
    public int calendarioPeriodId;
    public int capacidadId;
    public int silaboEventoId;
    public FormEvaluacion formEvaluacion;
    public TiposRubros tiposRubros;
    public Origen origen;
    public Tipo tipo;
    public List<RubroProcesoUi> rubroProcesoUis;
    public List<RubrosAsociadosUi> rubrosAsociadosUiList;
    public int sesionAprendizajeId;
    public String tipoEvaluacion;
    public boolean disabledEval;// Si esta en true no se podra evaluar
    public boolean tipoAncla;
    public TipoFormula tipoFormula;
    public IndicadoresCamposAccionUi indicadoresCamposAccionUi;
    public int desempenioIcdId;
    public EstiloFormula estiloFormula;
    public int formaEvaluacionId;
    public PublicarEval publicarEval = PublicarEval.NINGUNO;
    public int estrategiaId;


    public RubroProcesoUi() {
        this.rubroProcesoUis = new ArrayList<>();
        this.formEvaluacion = FormEvaluacion.INDIVIDUAL;
        this.origen = Origen.SILABO;
        this.tipo = Tipo.UNIDIMENCIONAL;
        this.tipoFormula = TipoFormula.DEFECTO;
        this.estiloFormula = EstiloFormula.ANARANJADO;
    }

    public void setPublicarEval(PublicarEval publicarEval) {
        this.publicarEval = publicarEval;
    }

    public PublicarEval getPublicarEval() {
        return publicarEval;
    }

    public List<RubroProcesoUi> getRubroProcesoUis() {
        return rubroProcesoUis;
    }

    public void setRubroProcesoUis(List<RubroProcesoUi> rubroProcesoUis) {
        this.rubroProcesoUis = rubroProcesoUis;
    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getValorPorDefecto() {
        return valorPorDefecto;
    }

    public void setValorPorDefecto(String valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean setCheck(boolean check) {
        this.check = check;
        return check;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public int getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(int tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public int getFormaEvaluacionId() {
        return formaEvaluacionId;
    }

    public void setFormaEvaluacionId(int formaEvaluacionId) {
        this.formaEvaluacionId = formaEvaluacionId;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public int getColorRubro() {
        return colorRubro;
    }

    public void setColorRubro(int colorRubro) {
        this.colorRubro = colorRubro;
    }

    public int getAndroidId() {
        return androidId;
    }

    public void setAndroidId(int androidId) {
        this.androidId = androidId;
    }

    public int getTipoFormulaId() {
        return tipoFormulaId;
    }

    public void setTipoFormulaId(int tipoFormulaId) {
        this.tipoFormulaId = tipoFormulaId;
    }

    public int getTipoValorRedondeoId() {
        return tipoValorRedondeoId;
    }

    public void setTipoValorRedondeoId(int tipoValorRedondeoId) {
        this.tipoValorRedondeoId = tipoValorRedondeoId;
    }

    public int getTipoEscalaEvaluacionId() {
        return tipoEscalaEvaluacionId;
    }

    public void setTipoEscalaEvaluacionId(int tipoEscalaEvaluacionId) {
        this.tipoEscalaEvaluacionId = tipoEscalaEvaluacionId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCalendarioPeriodId() {
        return calendarioPeriodId;
    }

    public void setCalendarioPeriodId(int calendarioPeriodId) {
        this.calendarioPeriodId = calendarioPeriodId;
    }

    public int getCapacidadId() {
        return capacidadId;
    }

    public void setCapacidadId(int capacidadId) {
        this.capacidadId = capacidadId;
    }

    public List<RubrosAsociadosUi> getRubrosAsociadosUiList() {
        return rubrosAsociadosUiList;
    }

    public void setRubrosAsociadosUiList(List<RubrosAsociadosUi> rubrosAsociadosUiList) {
        this.rubrosAsociadosUiList = rubrosAsociadosUiList;
    }

    public FormEvaluacion getFormEvaluacion() {
        return formEvaluacion;
    }

    public void setFormEvaluacion(FormEvaluacion formEvaluacion) {
        this.formEvaluacion = formEvaluacion;
    }

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public TiposRubros getTiposRubros() {
        return tiposRubros;
    }

    public void setTiposRubros(TiposRubros tiposRubros) {
        this.tiposRubros = tiposRubros;
    }

    public boolean isDisabledEval() {
        return disabledEval;
    }

    public void setDisabledEval(boolean disabledEval) {
        this.disabledEval = disabledEval;
    }

    public boolean isTipoAncla() {
        return tipoAncla;
    }

    public void setTipoAncla(boolean tipoAncla) {
        this.tipoAncla = tipoAncla;
    }

    public TipoFormula getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(TipoFormula tipoFormula) {
        this.tipoFormula = tipoFormula;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTituloRubrica() {
        return tituloRubrica;
    }

    public void setTituloRubrica(String tituloRubrica) {
        this.tituloRubrica = tituloRubrica;
    }

    public IndicadoresCamposAccionUi getIndicadoresCamposAccionUi() {
        return indicadoresCamposAccionUi;
    }

    public void setIndicadoresCamposAccionUi(IndicadoresCamposAccionUi indicadoresCamposAccionUi) {
        this.indicadoresCamposAccionUi = indicadoresCamposAccionUi;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public boolean isCheckDisbled() {
        return checkDisbled;
    }

    public void setCheckDisbled(boolean checkDisbled) {
        this.checkDisbled = checkDisbled;
    }

    public EstiloFormula getEstiloFormula() {
        return estiloFormula;
    }

    public void setEstiloFormula(EstiloFormula estiloFormula) {
        this.estiloFormula = estiloFormula;
    }

    public int getEstrategiaId() {
        return estrategiaId;
    }

    public void setEstrategiaId(int estrategiaId) {
        this.estrategiaId = estrategiaId;
    }

    @Override
    public String toString() {
        return "RubroProcesoUi{" +
                "checkDisbled=" + checkDisbled +
                ", key='" + key + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tituloRubrica='" + tituloRubrica + '\'' +
                ", subTitulo='" + subTitulo + '\'' +
                ", valorPorDefecto='" + valorPorDefecto + '\'' +
                ", posicion=" + posicion +
                ", check=" + check +
                ", fecha='" + fecha + '\'' +
                ", media='" + media + '\'' +
                ", tipoEvaluacionId=" + tipoEvaluacionId +
                ", tipoNotaId='" + tipoNotaId + '\'' +
                ", tipoFormulaId=" + tipoFormulaId +
                ", tipoValorRedondeoId=" + tipoValorRedondeoId +
                ", tipoEscalaEvaluacionId=" + tipoEscalaEvaluacionId +
                ", colorRubro=" + colorRubro +
                ", androidId=" + androidId +
                ", peso=" + peso +
                ", calendarioPeriodId=" + calendarioPeriodId +
                ", capacidadId=" + capacidadId +
                ", silaboEventoId=" + silaboEventoId +
                ", formEvaluacion=" + formEvaluacion +
                ", tiposRubros=" + tiposRubros +
                ", origen=" + origen +
                ", tipo=" + tipo +
                ", rubroProcesoUis=" + rubroProcesoUis +
                ", rubrosAsociadosUiList=" + rubrosAsociadosUiList +
                ", sesionAprendizajeId=" + sesionAprendizajeId +
                ", tipoEvaluacion=" + tipoEvaluacion +
                ", disabledEval=" + disabledEval +
                ", tipoAncla=" + tipoAncla +
                ", tipoFormula=" + tipoFormula +
                ", indicadoresCamposAccionUi=" + indicadoresCamposAccionUi +
                ", desempenioIcdId=" + desempenioIcdId +
                ", estiloFormula=" + estiloFormula +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubroProcesoUi)) return false;

        RubroProcesoUi that = (RubroProcesoUi) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
