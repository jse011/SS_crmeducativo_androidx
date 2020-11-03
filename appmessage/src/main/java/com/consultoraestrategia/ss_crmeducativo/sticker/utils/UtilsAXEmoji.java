package com.consultoraestrategia.ss_crmeducativo.sticker.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aghajari.emojiview.listener.OnStickerActions;
import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.view.AXEmojiPager;
import com.aghajari.emojiview.view.AXEmojiView;
import com.aghajari.emojiview.view.AXSingleEmojiView;
import com.aghajari.emojiview.view.AXStickerView;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.sticker.WhatsAppProvider;

public class UtilsAXEmoji {
    public static AXEmojiPager loadEmojiEdit(Context context, EditText editText, OnStickerActions stickerActions) {
        AXEmojiPager emojiPager = new AXEmojiPager(context);

        if (false) {
            /**
             * add single emoji view
             */
            AXSingleEmojiView singleEmojiView = new AXSingleEmojiView(context);
            emojiPager.addPage(singleEmojiView, R.drawable.ic_msg_panel_smiles);
        }

        if (true) {
            /**
             * add emoji view (with viewpager)
             */
            AXEmojiView emojiView = new AXEmojiView(context);
            emojiPager.addPage(emojiView, R.drawable.ic_msg_panel_smiles);
        }

        if (stickerActions!=null) {
            /**
             * add Sticker View
             */
            AXStickerView stickerView = new AXStickerView(context, "stickers", new WhatsAppProvider());
            emojiPager.addPage(stickerView, R.drawable.ic_msg_panel_stickers);

            //add sticker click listener
            stickerView.setOnStickerActionsListener(stickerActions);
        }

        //if (mCustomView){
        //  emojiPager.addPage(new LoadingView(context), R.drawable.msg_round_load_m);
        // }

        // set target emoji edit text to emojiViewPager
        emojiPager.setEditText(editText);

        emojiPager.setSwipeWithFingerEnabled(true);

        if (false) {
            //initCustomFooter(context,emojiPager);
        } else {

            //emojiPager.setLeftIcon(R.drawable.ic_ab_search);
            /*emojiPager.setOnFooterItemClicked(new AXEmojiPager.OnFooterItemClicked() {
                @Override
                public void onClick(View view, boolean leftIcon) {
                    if (leftIcon)
                        Toast.makeText(ChatActivity.this, "Search Clicked", Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        return emojiPager;
    }
}
