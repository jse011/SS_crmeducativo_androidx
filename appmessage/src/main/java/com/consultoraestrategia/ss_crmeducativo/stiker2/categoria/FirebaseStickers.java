package com.consultoraestrategia.ss_crmeducativo.stiker2.categoria;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.sticker.StickerCategory;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.StikersUi;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStickers implements StickerCategory<CategoriaUi> {
    private CategoriaUi categoriaUi;

    public FirebaseStickers(CategoriaUi categoriaUi) {
        this.categoriaUi = categoriaUi;
    }

    @NonNull
    @Override
    public StikersUi[] getStickers() {
        List<StikersUi>  stickerList = categoriaUi.getStikersUiList();

        StikersUi[] array = new StikersUi[stickerList.size()];
        array = stickerList.toArray(array);
        return array;
    }

    @Override
    public CategoriaUi getCategoryData() {
        return categoriaUi;
    }

    @Override
    public boolean useCustomView() {
        return false;
    }

    @Override
    public View getView(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public View getEmptyView(ViewGroup viewGroup) {
        return null;
    }
}
