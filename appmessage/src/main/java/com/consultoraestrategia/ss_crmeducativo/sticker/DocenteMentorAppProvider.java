package com.consultoraestrategia.ss_crmeducativo.sticker;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;

import com.aghajari.emojiview.AXEmojiManager;
import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.sticker.StickerCategory;
import com.aghajari.emojiview.sticker.StickerLoader;
import com.aghajari.emojiview.sticker.StickerProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.sticker.category.ShopStickers;
import com.consultoraestrategia.ss_crmeducativo.sticker.category.WhatsAppStickers;

public class DocenteMentorAppProvider implements StickerProvider {

    public DocenteMentorAppProvider() {
    }

    @NonNull
    @Override
    public StickerCategory[] getCategories() {
        return new StickerCategory[0];
    }

    @NonNull
    @Override
    public StickerLoader getLoader() {
        return new StickerLoader() {
            @Override
            public void onLoadSticker(View view, Sticker sticker) {

            }

            @Override
            public void onLoadStickerCategory(View icon, StickerCategory stickerCategory, boolean selected) {

            }
        };
    }

    @Override
    public boolean isRecentEnabled() {
        return true;
    }
}
