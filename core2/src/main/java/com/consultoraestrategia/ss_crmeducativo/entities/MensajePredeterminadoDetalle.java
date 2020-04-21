package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.List;

/**
 * Created by irvinmarin on 09/08/2018.
 */
@Table(database = AppDatabase.class)
public class MensajePredeterminadoDetalle extends BaseEntity {
    @Column
    String idMensajePredeterminadoDetalle;
    @Column
    private String mensajePredeterminadoId;
    @Column
    private int listaUsuarioId;

    public MensajePredeterminadoDetalle() {
    }

    public String getIdMensajePredeterminadoDetalle() {
        return idMensajePredeterminadoDetalle;
    }

    public void setIdMensajePredeterminadoDetalle(String idMensajePredeterminadoDetalle) {
        this.idMensajePredeterminadoDetalle = idMensajePredeterminadoDetalle;
    }

    public String getMensajePredeterminadoId() {
        return mensajePredeterminadoId;
    }

    public void setMensajePredeterminadoId(String mensajePredeterminadoId) {
        this.mensajePredeterminadoId = mensajePredeterminadoId;
    }

    public int getListaUsuarioId() {
        return listaUsuarioId;
    }

    public void setListaUsuarioId(int listaUsuarioId) {
        this.listaUsuarioId = listaUsuarioId;
    }

    public static List<MensajePredeterminadoDetalle> getAll() {
        return null;
//                SQLite.select()
//                .from(MensajePredeterminadoDetalle.class)
//                .where(MensajePredeterminadoDetalle_Table.)
//                .queryList();
    }
}
