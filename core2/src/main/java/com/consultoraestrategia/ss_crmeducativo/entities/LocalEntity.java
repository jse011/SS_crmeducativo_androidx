package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by @stevecampos on 3/01/2018.
 */
public class LocalEntity extends BaseModel {

    public static final int FLAG_EXPORTED = 4;
    public static final int FLAG_ADDED = 1;
    public static final int FLAG_UPDATED = 2;
    public static final int FLAG_DELETED = 3;
    @PrimaryKey(autoincrement = true)
    public int androidId;
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


    public LocalEntity() {

    }


    public LocalEntity(int androidId) {
        this.androidId = androidId;
    }

    public int getAndroidId() {
        return androidId;
    }

    public void setAndroidId(int androidId) {
        this.androidId = androidId;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalEntity that = (LocalEntity) o;

        return androidId == that.androidId;
    }

    @Override
    public int hashCode() {
        return androidId;
    }

    @Override
    public long insert() {
        SessionUser user = SessionUser.getCurrentUser();
        if (user != null) {
            usuarioAccionId = user.getUserId();
            fechaAccion = getTime();
        }
        return super.insert();
    }

    @Override
    public boolean save() {
        SessionUser user = SessionUser.getCurrentUser();
        if (user != null) {
            usuarioAccionId = user.getUserId();
            fechaAccion = getTime();
        }
        return super.save();
    }
}
