package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;

import java.util.List;

public class ClipboardMessage {
    private Context context;

    public ClipboardMessage(Context context) {
        this.context = context;
    }

    public void execute(List<MessageUi2> messageUi2List){
        ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip;

        StringBuilder text = new StringBuilder();
        int count = 0;
        for (MessageUi2 messageUi2: messageUi2List){
            text.append(messageUi2.getMensaje());
            count++;
            if(messageUi2List.size()>1&&count!=messageUi2List.size()){
                text.append("\n");
            }
        }
        myClip = ClipData.newPlainText("text", text.toString());
        myClipboard.setPrimaryClip(myClip);


        if(messageUi2List.size()==1){
            Toast.makeText(context, "Mensaje copiado", Toast.LENGTH_SHORT).show();
        }else if(messageUi2List.size()>1) {
            Toast.makeText(context, messageUi2List.size() + " mensajes copiados", Toast.LENGTH_SHORT).show();
        }

    }
}
