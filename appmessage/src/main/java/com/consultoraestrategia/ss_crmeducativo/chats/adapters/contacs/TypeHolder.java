package com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

public class TypeHolder extends RecyclerView.ViewHolder {

    TextView type;


    public TypeHolder(@NonNull View itemView) {
        super(itemView);
        type=(TextView)itemView.findViewById(R.id.type);
    }
    public void bind(String type){
        this.type.setText(type);
    }
}
