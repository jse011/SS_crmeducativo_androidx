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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupCourseHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.circle)
    ImageView circle2;

    TextView section;

    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.root)
    ConstraintLayout root;
    @BindView(R2.id.fondo)
    ImageView fondo;



    public GroupCourseHolder(@NonNull View itemView) {
        super(itemView);
        section = (TextView)itemView.findViewById(R.id.section21);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final GroupUi groupUi, final ChatListener chatListener){
        StringBuilder j= new StringBuilder();
        for(int i=0;groupUi.getNameCourse().length()>i; i++){
            j.append(groupUi.getNameCourse().charAt(i));
            if(i==2)break;
        }
        section.setText(j.toString());
        switch (groupUi.getGrupo()){
            case Padre:
                name.setText("Grupo de Padres");
                break;
            case Alumno:
                name.setText("Grupo de Alumnos");
                break;
            default:
                name.setText("Grupo Oficial");
                break;
        }
        //name.setText(groupUi.getName());
        section.setTextColor(Color.WHITE);
        try {
            ColorStateList csl = ColorStateList.valueOf(Color.parseColor(groupUi.getColor()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                circle2.setImageTintList(csl);
            }
        }catch (Exception e){
            Log.d(getClass().getSimpleName(),"error "+ e.getMessage());
        }

        Glide.with(fondo)
                .load(groupUi.getPhoto())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(fondo);
        fondo.setVisibility(View.VISIBLE);
        fondo.setAlpha(0.4f);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(groupUi);
            }
        });
    }
}
