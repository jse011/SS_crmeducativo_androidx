package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ProductoEventoCampoTematico extends BaseModel {

    @PrimaryKey
    private int productoEventoCampoTematico;
    @Column
    private int productoAprendizaje;
    @Column
    private int campoTematico;

    public ProductoEventoCampoTematico() {
    }

    public int getProductoEventoCampoTematico() {
        return productoEventoCampoTematico;
    }

    public void setProductoEventoCampoTematico(int productoEventoCampoTematico) {
        this.productoEventoCampoTematico = productoEventoCampoTematico;
    }

    public int getProductoAprendizaje() {
        return productoAprendizaje;
    }

    public void setProductoAprendizaje(int productoAprendizaje) {
        this.productoAprendizaje = productoAprendizaje;
    }

    public int getCampoTematico() {
        return campoTematico;
    }

    public void setCampoTematico(int campoTematico) {
        this.campoTematico = campoTematico;
    }

    @Override
    public String toString() {
        return "ProductoEventoCampoTematico{" +
                "productoEventoCampoTematico=" + productoEventoCampoTematico +
                ", productoAprendizaje=" + productoAprendizaje +
                ", campoTematico=" + campoTematico +
                '}';
    }
}
