package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder.TeamItemViewHolder;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.helper.ItemTouchHelperAdapter;
import com.consultoraestrategia.ss_crmeducativo.helper.SimpleItemTouchHelperCallback;

import java.util.Collections;
import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{


    private static final String TAG = TeamAdapter.class.getSimpleName();

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(teams, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(teams, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        if (listener != null){
            listener.onTeamButtonDeleteClick(teams.get(position));
        }
    }


    public interface TeamListener{
        void onTeamSelected(Team team);
        void onTeamButtonDeleteClick(Team team);
    }

    private List<Team> teams;
    private TeamListener listener;
    private RecyclerView recyclerView;

    public TeamAdapter(List<Team> teams, TeamListener listener) {
        this.teams = teams;
        this.listener = listener;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        setupItemTouchHelper();
    }

    private void setupItemTouchHelper(){
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }


    public List<Team> getTeams() {
        return teams;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Team team = teams.get(position);
        TeamItemViewHolder teamItemViewHolder = (TeamItemViewHolder)holder;
        teamItemViewHolder.bind(team, listener);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams){
        this.teams.clear();
        this.teams.addAll(teams);
        notifyDataSetChanged();
    }

    private boolean showUsers = false;
    public void showUsers(){
        showUsers = true;
        notifyDataSetChanged();
    }

    public void removeTeam(Team team){
        int position = teams.indexOf(team);
        if (position != -1){
            this.teams.remove(position);
            notifyItemRemoved(position);
        }
    }


    public void addTeam(Team team) {
        Log.d(TAG, "addTeam: " + team.toString());
        this.teams.add(0, team);
        notifyItemInserted(0);
        /*int position = teams.indexOf(team);
        Log.d(TAG, "position: " + position);
        if (position == -1){
            this.teams.add(team);
            notifyItemInserted(0);
        }else{
            this.teams.set(position, team);
            notifyItemChanged(position);
        }*/
    }

    public void updateTeam(Team team){
        Log.d(TAG, "updateTeam: " + team.toString());
        int position = this.teams.indexOf(team);
        if(position != -1){
            this.teams.set(position, team);
            notifyDataSetChanged();
        }else {
            addTeam(team);
        }
    }

    public boolean stado(){
        if (teams.size()>0)return true;
        else return false;
    }



    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        Log.d(TAG, "onViewDetachedFromWindow");
        holder.setIsRecyclable(false);
        super.onViewDetachedFromWindow(holder);
    }
}
