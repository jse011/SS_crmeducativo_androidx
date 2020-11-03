package com.consultoraestrategia.ss_crmeducativo.sticker.entities;

import com.aghajari.emojiview.sticker.Sticker;

public class ShopSticker extends Sticker {
        private static final long serialVersionUID = 3L;

        public String title;
        public int count;
        public ShopSticker(Sticker[] data, String Title, int StickersSize) {
            super(data);
            this.title = Title;
            this.count = StickersSize;
        }
    }