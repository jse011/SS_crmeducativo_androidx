package com.consultoraestrategia.ss_crmeducativo.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by @stevecampos on 3/01/2018.
 */
public class BaseUpdateModel extends BaseModel {

    public static final int FLAG_EXPORTED = 4;
    public static final int FLAG_ADDED = 1;
    public static final int FLAG_UPDATED = 2;
    public static final int FLAG_DELETED = 3;

    @Column
    public int syncFlag;
    @Column
    public long timestampFlag;
    @Column
    public int usuarioCreacionId;
    @Column
    public long fechaCreacion;
    @Column
    int usuarioAccionId;
    @Column
    long fechaAccion;


    public int getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
        switch (syncFlag) {
            case FLAG_ADDED:
                SessionUser user = SessionUser.getCurrentUser();
                this.fechaCreacion = getTime();
                if (user != null) {
                    usuarioCreacionId = user.getUserId();
                }
                break;
        }

        this.timestampFlag = getTime();
    }

    public long getTimestampFlag() {
        return timestampFlag;
    }

    public void setTimestampFlag() {
        this.timestampFlag = getTime();
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(long fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public static long getTime() {
        return new Date().getTime();
    }

}
