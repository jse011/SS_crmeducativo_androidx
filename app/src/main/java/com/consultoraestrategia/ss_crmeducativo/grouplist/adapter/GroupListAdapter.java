package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder.GroupCursoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder.GroupItemViewHolder;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder.GroupTeamViewHolder;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.CursoGrupoUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.grouplist.listener.GroupListener;

import java.util.List;


/**
 * Created by @stevecampos on 15/09/2017.
 */

public class GroupListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = GroupListAdapter.class.getSimpleName();
    private static final int GRUPO = 1, TEAM =2, CURSOGRUPO=3;
    private List<Object> groupList;
    private GroupListener listener;
    private boolean tipoitem;
    private Animation slideUp;
    private Animation slideDown;
    private  String cargacursoId;

    public GroupListAdapter(List<Object> groupList, GroupListener listener, Animation slideUp, Animation slideDown) {
        this.groupList = groupList;
        this.listener = listener;
        this.slideUp = slideUp;
        this.slideDown = slideDown;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType){
            case GRUPO:
                view = layoutInflater.inflate(R.layout.item_group, parent, false);
                viewHolder = new GroupItemViewHolder(view);
                break;
            case CURSOGRUPO:
                view = layoutInflater.inflate(R.layout.grupos_curso, parent, false);
                viewHolder = new GroupCursoViewHolder(view);
                break;
            default:
                view = layoutInflater.inflate(R.layout.item_groupteam, parent, false);
                viewHolder = new GroupTeamViewHolder(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){

            case CURSOGRUPO:
                CursoGrupoUi cursoGrupo = (CursoGrupoUi) groupList.get(position);
                GroupCursoViewHolder viewHoldergrupo = (GroupCursoViewHolder) holder;
                viewHoldergrupo.bind(cursoGrupo);
                break;

            case GRUPO:
                Group group = (Group) groupList.get(position);
                GroupItemViewHolder viewHolder = (GroupItemViewHolder) holder;
                viewHolder.bind(group,  listener, tipoitem,slideUp, slideDown, cargacursoId);
                break;

            case TEAM:
                 Team team = (Team) groupList.get(position);
                GroupTeamViewHolder groupTeamViewHolder = (GroupTeamViewHolder) holder;
                groupTeamViewHolder.bind(team);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void setItems(List<Object> cursoGrupos, Boolean tipoitem, String cargaCursoId){
        this.tipoitem=tipoitem;
        this.cargacursoId= cargaCursoId;
        this.groupList.clear();
        this.groupList.addAll(cursoGrupos);
        notifyDataSetChanged();
    }

    public void addGroup(Group group){
        if (group != null){
            this.groupList.add(group);
            notifyItemInserted(getItemCount()-1);
        }
    }

    public void updateGroup(Object group){
        if (group != null){
            int position = groupList.indexOf(group);
            Log.d(TAG, "updateGroup: "+ position);
            if (position >= 0) {
                this.groupList.set(position, group);
                notifyItemChanged(position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object o = groupList.get(position);
        if(o instanceof CursoGrupoUi){
            return CURSOGRUPO;
        }
       else if(o instanceof Group){
            return GRUPO;
        }else {
            return TEAM;
        }
    }

    public void clearList() {
        this.groupList.clear();
        notifyDataSetChanged();
    }
}
