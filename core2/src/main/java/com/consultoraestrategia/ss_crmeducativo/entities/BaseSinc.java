package com.consultoraestrategia.ss_crmeducativo.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 18/12/2017.
 */

public abstract class BaseSinc extends BaseModel{
    @Unique
    @PrimaryKey(autoincrement = true)
    long androidId;
    @Column
    boolean androidSync;

    public long getAndroidId() {
        if(isAndroidSync()){
            return getId();
        }
        return androidId;
    }

    public void setAndroidId(long androidId) {
        this.androidId = androidId;
    }

    public boolean isAndroidSync() {
        return androidSync;
    }

    public void setAndroidSync(boolean androidSync) {
        this.androidSync = androidSync;
    }

    abstract public long getId();

}
