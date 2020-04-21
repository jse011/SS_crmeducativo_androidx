package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 17/08/2017.
 */
@Table(database = AppDatabase.class)
public class ListaUsuarioDetalle extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int listaUsuarioDetalleId;
    @Column
    private int listaUsuarioId;
    @Column
    private int usuarioId;


    public ListaUsuarioDetalle() {
    }

    public ListaUsuarioDetalle(int listaUsuarioDetalleId, int listaUsuarioId, int usuarioId) {
        this.listaUsuarioDetalleId = listaUsuarioDetalleId;
        this.listaUsuarioId = listaUsuarioId;
        this.usuarioId = usuarioId;
    }

    public int getListaUsuarioDetalleId() {
        return listaUsuarioDetalleId;
    }

    public void setListaUsuarioDetalleId(int listaUsuarioDetalleId) {
        this.listaUsuarioDetalleId = listaUsuarioDetalleId;
    }

    public int getListaUsuarioId() {
        return listaUsuarioId;
    }

    public void setListaUsuarioId(int listaUsuarioId) {
        this.listaUsuarioId = listaUsuarioId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
