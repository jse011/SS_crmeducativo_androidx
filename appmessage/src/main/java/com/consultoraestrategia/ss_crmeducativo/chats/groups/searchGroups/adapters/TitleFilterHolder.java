package com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

public class TitleFilterHolder extends RecyclerView.ViewHolder {

    TextView name;

    public TitleFilterHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.name);

    }

    public void bind(String title){
        name.setText(title);
    }
}
