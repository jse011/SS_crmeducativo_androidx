package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by kike on 11/12/2017.
 */
/*Esta entidad no sincroniza con los Servicios*/
@Table(database = AppDatabase.class)
public class RubrosAsociados extends BaseModel{
    @Unique
    @PrimaryKey(autoincrement = true)
    private int idRubrosAsociados;
    @Column
    private int numeroRubroAsociado;
    @Column
    private String circleColor;
    @Column
    private int idRubroEvaluacionProcesoPrincipal;
    @Column
    private int idRubroEvaluacionProcesoSecundario;

    public RubrosAsociados() {
    }

    public RubrosAsociados(int idRubrosAsociados, int numeroRubroAsociado, String circleColor, int idRubroEvaluacionProcesoPrincipal, int idRubroEvaluacionProcesoSecundario) {
        this.idRubrosAsociados = idRubrosAsociados;
        this.numeroRubroAsociado = numeroRubroAsociado;
        this.circleColor = circleColor;
        this.idRubroEvaluacionProcesoPrincipal = idRubroEvaluacionProcesoPrincipal;
        this.idRubroEvaluacionProcesoSecundario = idRubroEvaluacionProcesoSecundario;
    }

    public int getIdRubrosAsociados() {
        return idRubrosAsociados;
    }

    public void setIdRubrosAsociados(int idRubrosAsociados) {
        this.idRubrosAsociados = idRubrosAsociados;
    }

    public int getNumeroRubroAsociado() {
        return numeroRubroAsociado;
    }

    public void setNumeroRubroAsociado(int numeroRubroAsociado) {
        this.numeroRubroAsociado = numeroRubroAsociado;
    }

    public String getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(String circleColor) {
        this.circleColor = circleColor;
    }

    public int getIdRubroEvaluacionProcesoPrincipal() {
        return idRubroEvaluacionProcesoPrincipal;
    }

    public void setIdRubroEvaluacionProcesoPrincipal(int idRubroEvaluacionProcesoPrincipal) {
        this.idRubroEvaluacionProcesoPrincipal = idRubroEvaluacionProcesoPrincipal;
    }

    public int getIdRubroEvaluacionProcesoSecundario() {
        return idRubroEvaluacionProcesoSecundario;
    }

    public void setIdRubroEvaluacionProcesoSecundario(int idRubroEvaluacionProcesoSecundario) {
        this.idRubroEvaluacionProcesoSecundario = idRubroEvaluacionProcesoSecundario;
    }
}
