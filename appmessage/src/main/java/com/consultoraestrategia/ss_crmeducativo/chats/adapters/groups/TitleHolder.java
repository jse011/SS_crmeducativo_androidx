package com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

public class TitleHolder extends RecyclerView.ViewHolder {


    TextView name;
    public TitleHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.type);

    }
    public void bind(String title){
        name.setText(title);
    }
}
