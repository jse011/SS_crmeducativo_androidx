package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import android.graphics.Bitmap;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by @stevecampos on 23/02/2018.
 */


public class AlumnoProcesoUi extends ColumnHeader {
    private int id;
    private int pocision;
    private String nombre;
    private String apellidos;
    private String urlProfile;
    private List<EvalProcUi> evalProcList;
    private List<NotaUi> notaUiList;
    private GrupoProcesoUi grupoProcesoUi;
    private String equipoId;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private NotaUi notaUi;
    private Bitmap bitmap;
    private DimensionObservadaUi dimensionObservadaUi;
    private boolean comentario;
    private boolean vigente;


    public AlumnoProcesoUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public void setCellEvalProcList(List<Cell> evalProcList) {
        this.evalProcList = new ArrayList<>();
        for (Cell itemCell : evalProcList) {
            if(itemCell instanceof EvalProcUi){
                EvalProcUi evalProcUi = (EvalProcUi)itemCell;
                this.evalProcList.add(evalProcUi);
            }
        }
    }

    public List<NotaUi> getNotaUiList() {
        return notaUiList;
    }

    public void setNotaUiList(List<NotaUi> notaUiList) {
        this.notaUiList = notaUiList;
    }

    public GrupoProcesoUi getGrupoProcesoUi() {
        return grupoProcesoUi;
    }

    public void setGrupoProcesoUi(GrupoProcesoUi grupoProcesoUi) {
        this.grupoProcesoUi = grupoProcesoUi;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUiList() {
        return valorTipoNotaUiList;
    }

    public void setValorTipoNotaUiList(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

    public void setNotaUi(NotaUi notaUi) {
        this.notaUi = notaUi;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public DimensionObservadaUi getDimensionObservadaUi() {
        return dimensionObservadaUi;
    }

    public void setDimensionObservadaUi(DimensionObservadaUi dimensionObservadaUi) {
        this.dimensionObservadaUi = dimensionObservadaUi;
    }

    public int getPocision() {
        return pocision;
    }

    public void setPocision(int pocision) {
        this.pocision = pocision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoProcesoUi that = (AlumnoProcesoUi) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public boolean isComentario() {
        return comentario;
    }

    public void setComentario(boolean comentario) {
        this.comentario = comentario;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
