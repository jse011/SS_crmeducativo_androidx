package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = AppDatabase.class, allFields = true)
public class CampoTematicoRubroQuery {
    @Column
    public int campoTematicoId;
    @Column
    public String titulo;
    @Column
    public String descripcion;
    @Column
    public int silaboEventoId;
    @Column
    public int estado;
    @Column
    public int SesionAprendizajeId;
    @Column
    public int parentId;
    @Column
    public String rubroEvalProcesoId;

    public int getCampoTematicoId() {
        return campoTematicoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public int getEstado() {
        return estado;
    }

    public int getSesionAprendizajeId() {
        return SesionAprendizajeId;
    }

    public int getParentId() {
        return parentId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }
}
