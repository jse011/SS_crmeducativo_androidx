package com.consultoraestrategia.ss_crmeducativo.stiker2.repositorio;

import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;

import java.util.List;

public interface StickersRepositorio {
    interface Callback<T>{
        void onLoad(boolean succes , T item);
    }

    void getStikersFirebase(Callback<List<CategoriaUi>> callback);
}
