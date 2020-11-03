package com.consultoraestrategia.ss_crmeducativo.sticker.category;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.sticker.StickerCategory;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

public class WhatsAppStickers implements StickerCategory<Integer> {
    @NonNull
    @Override
    public Sticker[] getStickers() {
        return new Sticker[]{
                new Sticker<Integer>(R.drawable.sticker4),
                new Sticker<Integer>(R.drawable.sticker9),
                new Sticker<Integer>(R.drawable.sticker14),
        };
    }

    @Override
    public Integer getCategoryData() {
        return R.drawable.sticker4;
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
    public void bindView(View view) {}

    @Override
    public View getEmptyView(ViewGroup viewGroup) {
        return null;
    }
}
