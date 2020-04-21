package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.GroupListAdapter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.listener.GroupListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public class GroupItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,RecyclerItemClickListener.RecyclerViewOnItemClickListener {

    private static final String TAG = GroupItemViewHolder.class.getSimpleName();
    /*@BindView(R.id.imgGroupIcon)
    ImageView imgGroupIcon;*/
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtitle)
    TextView txtSubtitle;
    @BindView(R.id.rc_team)
    RecyclerView rcTeam;
    @BindView(R.id.btn_opcion)
    ImageView btnOpcion;
    @BindView(R.id.imgCheck)
    ImageView imgCheck;

    private GroupListener listener;
    private Group group;
    private Animation slideUp;
    private Animation slideDown;

    public GroupItemViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }


    public void bind(final Group group, final GroupListener listener, Boolean tipoItem, final Animation slideUp, final Animation slideDown, final String cargaCursoId){
        this.slideUp = slideUp;
        this.slideDown = slideDown;
        this.listener = listener;
        this.group=group;
        int teamCount = group.getTeams() == null || group.getTeams().isEmpty()  ? 0 : group.getTeams().size();
        txtTitle.setText(group.getTitle());
        try {
            String color1 = group.getCursoGrupoUi().getColor1();
            txtTitle.setTextColor(Color.parseColor(color1));
        }catch (Exception e){
            e.printStackTrace();
        }
        txtSubtitle.setText(String.valueOf(teamCount + " Equipo(s)"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcTeam.setLayoutManager(linearLayoutManager);
        //rcTeam.getLayoutManager().setAutoMeasureEnabled(true);
        rcTeam.setNestedScrollingEnabled(false);
        rcTeam.setHasFixedSize(false);
        List<Object> objectList = new ArrayList<>();
        if(group.getTeams()!=null)objectList.addAll(group.getTeams());
        GroupListAdapter listAdapter = new GroupListAdapter(objectList,null,slideUp, slideDown);
        rcTeam.setAdapter(listAdapter);
        rcTeam.addOnItemTouchListener(new RecyclerItemClickListener(rcTeam,this));

        if (group.isChecked()){
            Drawable drawable = ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_success);
            imgCheck.setImageDrawable(drawable);
            imgCheck.startAnimation(slideUp);
        }else{
            Drawable drawable = ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_success_disabled);
            imgCheck.setImageDrawable(drawable);
            imgCheck.startAnimation(slideDown);
        }

        if(tipoItem){
            btnOpcion.setVisibility(View.GONE);
            imgCheck.setVisibility(View.VISIBLE);
            txtSubtitle.setVisibility(View.GONE);
        }else{ if(group.getCargaCursoId().equals(cargaCursoId))btnOpcion.setVisibility(View.VISIBLE);
            else btnOpcion.setVisibility(View.GONE);
            imgCheck.setVisibility(View.GONE);
            txtSubtitle.setVisibility(View.VISIBLE);
        }

        btnOpcion.setOnClickListener(this);
        rcTeam.setOnClickListener(this);
        imgCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_opcion:
                listener.onOptionSelect(view,group);
                break;
            case R.id.rc_team:
                listener.onGroupSelected(group);
                break;
            case R.id.imgCheck:
                setPersonMember(group, slideUp, slideDown);
                listener.onCheckSelected(group);
                listener.onClickSaveGrupoSesion();
                break;
        }
    }
    private void setPersonMember(Group group, Animation slideUp, Animation slideDown){
        if (!group.isChecked()){
            Drawable drawable = ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_success);
            imgCheck.setImageDrawable(drawable);
            imgCheck.startAnimation(slideUp);
        }else{
            Drawable drawable = ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_success_disabled);
            imgCheck.setImageDrawable(drawable);
            imgCheck.startAnimation(slideDown);
        }


    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View clickedView) {
        if(listener!=null)listener.onGroupSelected(group);
    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View clickedView) {

    }
}
