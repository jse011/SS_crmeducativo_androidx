package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;


import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kike on 22/10/2017.
 */
@Parcel
public class RubroEvaluacionProcesoUi {

    public String title;
    public String subTitulo; //new
    public String date;
    public int countRubro;
    public int randomRubroRelation;
    public int currentColorCircle;
    public boolean isSelected;
    public int idRubroEvalResultado;
    public int idRubroEvalProceso;
    public int idTipoNota;
    public int idTipoFormula;
    public int idValorRedondeo;
    public int idTipoEscalaEvaluacion;

    public int androidId;

    public double desviacionEstandar;

    public double peso;
    public List<RubrosAsociadosUi> itemCircleUis; // sacado del constructor

    /*Advanced*/
    public int idtipoEvaluacionUi;
    public String valorDefecto;
    public List<ColorCondicionalUi> colorCondicionalUis;
    public int sesionAprendizajeId ;
    public RubroEvaluacionProcesoUi() {
    }

    public RubroEvaluacionProcesoUi(String title, String subTitulo, String date, int countRubro, int randomRubroRelation, int currentColorCircle, boolean isSelected, int idRubroEvalResultado, int idRubroEvalProceso, int idTipoNota, int idTipoFormula, int idValorRedondeo, int idTipoEscalaEvaluacion, int idtipoEvaluacionUi, String valorDefecto, double peso, List<RubrosAsociadosUi> rubrosAsociadosUis, int sesionAprendizajeId, int androidId) {
        this.title = title;
        this.subTitulo = subTitulo;
        this.date = date;
        this.countRubro = countRubro;
        this.randomRubroRelation = randomRubroRelation;
        this.currentColorCircle = currentColorCircle;
        this.isSelected = isSelected;
        this.idRubroEvalResultado = idRubroEvalResultado;
        this.idRubroEvalProceso = idRubroEvalProceso;
        this.idTipoNota = idTipoNota;
        this.idTipoFormula = idTipoFormula;
        this.idValorRedondeo = idValorRedondeo;
        this.idTipoEscalaEvaluacion = idTipoEscalaEvaluacion;
        this.idtipoEvaluacionUi = idtipoEvaluacionUi;
        this.valorDefecto = valorDefecto;
        this.peso = peso;
      // this.colorCondicionalUis =colorCondicionalUis;
        this.itemCircleUis=rubrosAsociadosUis;
        this.sesionAprendizajeId=sesionAprendizajeId;
        this.androidId = androidId;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCountRubro() {
        return countRubro;
    }

    public void setCountRubro(int countRubro) {
        this.countRubro = countRubro;
    }

    public int getRandomRubroRelation() {
        return randomRubroRelation;
    }

    public void setRandomRubroRelation(int randomRubroRelation) {
        this.randomRubroRelation = randomRubroRelation;
    }

    public int getCurrentColorCircle() {
        return currentColorCircle;
    }

    public void setCurrentColorCircle(int currentColorCircle) {
        this.currentColorCircle = currentColorCircle;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIdRubroEvalResultado() {
        return idRubroEvalResultado;
    }

    public void setIdRubroEvalResultado(int idRubroEvalResultado) {
        this.idRubroEvalResultado = idRubroEvalResultado;
    }

    public int getIdRubroEvalProceso() {
        return idRubroEvalProceso;
    }

    public void setIdRubroEvalProceso(int idRubroEvalProceso) {
        this.idRubroEvalProceso = idRubroEvalProceso;
    }

    public int getIdTipoNota() {
        return idTipoNota;
    }

    public void setIdTipoNota(int idTipoNota) {
        this.idTipoNota = idTipoNota;
    }

    public int getIdTipoFormula() {
        return idTipoFormula;
    }

    public void setIdTipoFormula(int idTipoFormula) {
        this.idTipoFormula = idTipoFormula;
    }

    public int getIdValorRedondeo() {
        return idValorRedondeo;
    }

    public void setIdValorRedondeo(int idValorRedondeo) {
        this.idValorRedondeo = idValorRedondeo;
    }

    public int getIdTipoEscalaEvaluacion() {
        return idTipoEscalaEvaluacion;
    }

    public void setIdTipoEscalaEvaluacion(int idTipoEscalaEvaluacion) {
        this.idTipoEscalaEvaluacion = idTipoEscalaEvaluacion;
    }

    public int getIdtipoEvaluacionUi() {
        return idtipoEvaluacionUi;
    }

    public void setIdtipoEvaluacionUi(int idtipoEvaluacionUi) {
        this.idtipoEvaluacionUi = idtipoEvaluacionUi;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<RubrosAsociadosUi> getItemCircleUis() {
        return itemCircleUis;
    }

    public void setItemCircleUis(List<RubrosAsociadosUi> itemCircleUis) {
        this.itemCircleUis = itemCircleUis;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public List<ColorCondicionalUi> getColorCondicionalUis() {
        return colorCondicionalUis;
    }

    public void setColorCondicionalUis(List<ColorCondicionalUi> colorCondicionalUis) {
        this.colorCondicionalUis = colorCondicionalUis;
    }

    public void addRubroAsociados(RubrosAsociadosUi rubrosAsociadosUi){
        this.itemCircleUis.add(rubrosAsociadosUi);
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }

    public int getAndroidId() {
        return androidId;
    }

    public void setAndroidId(int androidId) {
        this.androidId = androidId;
    }

}
