package com.consultoraestrategia.ss_crmeducativo.stiker2.useCase;

import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.repositorio.StickersRepositorio;

import java.util.List;

public class GetStickerFireBaseUseCase {
    private StickersRepositorio repositorio;

    public GetStickerFireBaseUseCase(StickersRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void execute(final Callback callback){
        repositorio.getStikersFirebase(new StickersRepositorio.Callback<List<CategoriaUi>>() {
            @Override
            public void onLoad(boolean succes, List<CategoriaUi> item) {
                callback.onLoad(succes, item);
            }
        });
    }

    public interface Callback{
        void onLoad(boolean success, List<CategoriaUi> categoriaUiList);
    }
}
