package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades;

import org.parceler.Parcel;

/**
 * Created by CCIE on 20/03/2018.
 */

@Parcel
public class TipoUi {
    public int id;
    public String title;

    public TipoUi() {
    }

    public TipoUi(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
