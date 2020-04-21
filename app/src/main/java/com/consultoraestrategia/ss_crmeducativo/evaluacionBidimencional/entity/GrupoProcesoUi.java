package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;

import java.util.List;
import java.util.Objects;

/**
 * Created by @stevecampos on 23/02/2018.
 */


public class GrupoProcesoUi extends ColumnHeader {
    private String id;
    private String nombre;
    private String urlProfile;
    private List<EvalProcUi> evalProcList;
    private List<AlumnoProcesoUi> alumnoProcesoUis;
    private List<List<Cell>> cellList;
    private List<NotaUi> notaUiList;
    private String equipoId;
    private boolean toogle;
    private List<EvalProcUi> evalProcUi;
    private NotaUi notaUi;
    public GrupoProcesoUi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlProfile() {

        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public List<EvalProcUi> getEvalProcList() {
        return evalProcList;
    }

    public void setEvalProcList(List<EvalProcUi> evalProcList) {
        this.evalProcList = evalProcList;
    }

    public List<AlumnoProcesoUi> getAlumnoProcesoUis() {
        return alumnoProcesoUis;
    }

    public void setAlumnoProcesoUis(List<AlumnoProcesoUi> alumnoProcesoUis) {
        this.alumnoProcesoUis = alumnoProcesoUis;
    }

    public List<NotaUi> getNotaUiList() {
        return notaUiList;
    }

    public void setNotaUiList(List<NotaUi> notaUiList) {
        this.notaUiList = notaUiList;
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }

    public List<List<Cell>> getCellList() {
        return cellList;
    }

    public void setCellList(List<List<Cell>> cellList) {
        this.cellList = cellList;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
    }

    public List<EvalProcUi> getEvalProcUi() {
        return evalProcUi;
    }

    public void setEvalProcUi(List<EvalProcUi> evalProcUi) {
        this.evalProcUi = evalProcUi;
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

    public void setNotaUi(NotaUi notaUi) {
        this.notaUi = notaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoProcesoUi that = (GrupoProcesoUi) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GrupoProcesoUi{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                ", evalProcList=" + evalProcList +
                ", alumnoProcesoUis=" + alumnoProcesoUis +
                ", cellList=" + cellList +
                ", notaUiList=" + notaUiList +
                ", equipoId='" + equipoId + '\'' +
                ", toogle=" + toogle +
                ", evalProcUi=" + evalProcUi +
                ", notaUi=" + notaUi +
                '}';
    }
}
