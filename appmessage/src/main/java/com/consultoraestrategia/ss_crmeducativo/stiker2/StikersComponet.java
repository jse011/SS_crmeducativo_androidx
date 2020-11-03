package com.consultoraestrategia.ss_crmeducativo.stiker2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.aghajari.emojiview.listener.OnStickerActions;
import com.aghajari.emojiview.sticker.StickerCategory;
import com.aghajari.emojiview.sticker.StickerLoader;
import com.aghajari.emojiview.sticker.StickerProvider;
import com.aghajari.emojiview.view.AXEmojiPager;
import com.aghajari.emojiview.view.AXEmojiView;
import com.aghajari.emojiview.view.AXStickerView;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.sticker.WhatsAppProvider;
import com.consultoraestrategia.ss_crmeducativo.stiker2.adapter.FirebaseStikersAdapter;
import com.consultoraestrategia.ss_crmeducativo.stiker2.categoria.FirebaseStickers;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.StikersUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.repositorio.StickersRepositorioImpl;
import com.consultoraestrategia.ss_crmeducativo.stiker2.useCase.GetStickerFireBaseUseCase;

import java.util.ArrayList;
import java.util.List;

public class StikersComponet extends AXEmojiPager {

    private GetStickerFireBaseUseCase getStickerFireBaseUseCase;

    public StikersComponet(Context context) {
        super(context);
        init();
    }

    public StikersComponet(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StikersComponet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        AXEmojiView emojiView = new AXEmojiView(this.getContext());
        addPage(emojiView, R.drawable.ic_msg_panel_smiles);
        setSwipeWithFingerEnabled(true);
        getStickerFireBaseUseCase = new GetStickerFireBaseUseCase(new StickersRepositorioImpl());
    }

    public void initStikersFirebase(OnStickerActions stickerActions) {

        final FirebaseStikersAdapter firebaseStikersAdapter = new FirebaseStikersAdapter();
        final AXStickerView stickerView = new AXStickerView(getContext(), "stickers", firebaseStikersAdapter );
        addPage(stickerView, R.drawable.ic_msg_panel_stickers);
        getStickerFireBaseUseCase.execute(new GetStickerFireBaseUseCase.Callback() {
            @Override
            public void onLoad(boolean success, List<CategoriaUi> categoriaUiList) {
                if(success){
                    firebaseStikersAdapter.setList(categoriaUiList);
                    stickerView.refreshNow();
                }
            }
        });

        //add sticker click listener
        stickerView.setOnStickerActionsListener(stickerActions);




    }




}
