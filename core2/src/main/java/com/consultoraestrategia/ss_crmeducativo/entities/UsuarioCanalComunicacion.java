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
public class UsuarioCanalComunicacion extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int usuarioCanalComunicacionId;
    @Column
    private int canalComId;
    @Column
    private int usuarioId;

    public UsuarioCanalComunicacion() {
    }

    public UsuarioCanalComunicacion(int usuarioCanalComunicacionId, int canalComId, int usuarioId) {
        this.usuarioCanalComunicacionId = usuarioCanalComunicacionId;
        this.canalComId = canalComId;
        this.usuarioId = usuarioId;
    }

    public int getUsuarioCanalComunicacionId() {
        return usuarioCanalComunicacionId;
    }

    public void setUsuarioCanalComunicacionId(int usuarioCanalComunicacionId) {
        this.usuarioCanalComunicacionId = usuarioCanalComunicacionId;
    }

    public int getCanalComId() {
        return canalComId;
    }

    public void setCanalComId(int canalComId) {
        this.canalComId = canalComId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
