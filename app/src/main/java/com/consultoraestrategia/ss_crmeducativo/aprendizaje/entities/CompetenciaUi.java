package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class CompetenciaUi {

    public enum Tabla{CAPASIDAD,COMPETENCIA}
    public enum  Tipo{BASE, TRASVERSAL, EFOQUE}
    private int id;
    private List<CompetenciaUi> capasidadesUis = new ArrayList<>();
    private String tipo;
    private String Titulo;
    private List<DesempenioUi> desempenioUis = new ArrayList<>();
    private Tabla tabla;
    private int tipoId;
    private TipoUi tipoUi;
    private List<CampotematicoUi> campotematicoUis = new ArrayList<>();
    private Tipo tipoCompetencia = Tipo.BASE;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CompetenciaUi> getCapasidadesUis() {
        return capasidadesUis;
    }

    public void setCapasidadesUis(List<CompetenciaUi> capasidadesUis) {
        this.capasidadesUis = capasidadesUis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public List<DesempenioUi> getDesempenioUis() {
        return desempenioUis;
    }

    public void setDesempenioUis(List<DesempenioUi> desempenioUis) {
        this.desempenioUis = desempenioUis;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public TipoUi getTipoUi() {
        return tipoUi;
    }

    public void setTipoUi(TipoUi tipoUi) {
        this.tipoUi = tipoUi;
    }

    public List<CampotematicoUi> getCampotematicoUis() {
        return campotematicoUis;
    }

    public void setCampotematicoUis(List<CampotematicoUi> campotematicoUis) {
        this.campotematicoUis = campotematicoUis;
    }

    public Tipo getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(Tipo tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }
}
