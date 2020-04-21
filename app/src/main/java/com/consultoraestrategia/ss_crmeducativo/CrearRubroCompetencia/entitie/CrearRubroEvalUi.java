package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class CrearRubroEvalUi {
    private  String key;
    private  int id;
    private String titulo;
    private String subtitulo;
    private TipoNotaUi tipoNotaUi;
    private int sesionAprendizajeId;
    private int indicadorId;
    private TipoEvaluacionUi tipoEvaluacionUi;
    private FormaEvaluacionUi formaEvaluacionUi;
    private String valorDefecto;
    private List<ColorCondicionalUi> colorCondicionalUis;
    private List<Integer> campotematicoIds;
    private int silavoEventoId;
    private int competenciaId;
    private int calendarioPeriodoId;
    private String tareaId;
    public CrearRubroEvalUi() {
        tipoNotaUi = new TipoNotaUi();
        colorCondicionalUis = new ArrayList<>();
        tipoEvaluacionUi = new TipoEvaluacionUi();
        formaEvaluacionUi = new FormaEvaluacionUi();
    }
    private int estrategiaEvalId;

    public List<Integer> getCampotematicoIds() {
        return campotematicoIds;
    }

    public void setCampotematicoIds(List<Integer> campotematicoIds) {
        this.campotematicoIds = campotematicoIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }

    public TipoEvaluacionUi getTipoEvaluacionUi() {
        return tipoEvaluacionUi;
    }

    public void setTipoEvaluacionUi(TipoEvaluacionUi tipoEvaluacionUi) {
        this.tipoEvaluacionUi = tipoEvaluacionUi;
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

    public int getSilavoEventoId() {
        return silavoEventoId;
    }

    public void setSilavoEventoId(int silavoEventoId) {
        this.silavoEventoId = silavoEventoId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public FormaEvaluacionUi getFormaEvaluacionUi() {
        return formaEvaluacionUi;
    }

    public void setFormaEvaluacionUi(FormaEvaluacionUi formaEvaluacionUi) {
        this.formaEvaluacionUi = formaEvaluacionUi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public int getEstrategiaEvalId() {
        return estrategiaEvalId;
    }

    public void setEstrategiaEvalId(int estrategiaEvalId) {
        this.estrategiaEvalId = estrategiaEvalId;
    }
}
