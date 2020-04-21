package com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

public class GroupCourseHolder extends RecyclerView.ViewHolder {


    TextView sectionName;
    TextView name;
    ImageView circle;
    ConstraintLayout root;

    public GroupCourseHolder(@NonNull View itemView) {
        super(itemView);
        sectionName=(TextView)itemView.findViewById(R.id.section);
        name=(TextView)itemView.findViewById(R.id.name);
        circle=(ImageView)itemView.findViewById(R.id.circle);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);

    }
    public void bind(final GroupUi groupUi, final ChatListener chatListener){
        String j="";
        for(int i=0;groupUi.getNameCourse().length()>i; i++){
            j=j+groupUi.getNameCourse().charAt(i);
            if(i==2)break;
        }
        sectionName.setText(j);
        name.setText(groupUi.getName());
        sectionName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        try {
            ColorStateList csl = ColorStateList.valueOf(Color.parseColor(groupUi.getColor()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                circle.setImageTintList(csl);
            }
        }catch (Exception e){
            Log.d(getClass().getSimpleName(),"error "+ e.getMessage());
        }
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(groupUi);
            }
        });
    }
}
