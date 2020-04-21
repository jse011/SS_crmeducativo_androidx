package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ProductoEventoReferencia extends BaseModel{
    @PrimaryKey
    private int silaboEventoId;
    @PrimaryKey
    private int unidadAprendizajeId;
    @PrimaryKey
    private int sesionAprendizajeId;
    @PrimaryKey
    private int productoAprendizajeId;

    public ProductoEventoReferencia() {
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getProductoAprendizajeId() {
        return productoAprendizajeId;
    }

    public void setProductoAprendizajeId(int productoAprendizajeId) {
        this.productoAprendizajeId = productoAprendizajeId;
    }
}
