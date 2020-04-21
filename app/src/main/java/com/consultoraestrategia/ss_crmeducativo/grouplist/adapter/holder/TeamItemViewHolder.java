package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.TeamAdapter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.helper.ItemTouchHelperViewHolder;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;


import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class TeamItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {

    private static final String TAG = TeamItemViewHolder.class.getSimpleName();
    @BindView(R.id.imgPicture)
    CircleImageView imgPicture;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtitle)
    TextView txtSubtitle;
    @BindView(R.id.txtMembers)
    TextView txtMembers;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.frameLayout)
    FrameLayout deleteContainer;
    private Team team;
    private TeamAdapter.TeamListener listener;


    public TeamItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Team team, final TeamAdapter.TeamListener listener) {
        this.team = team;
        this.listener = listener;
        txtTitle.setText(team.getName());
        int countMembers = team.getPersonList()==null||team.getPersonList().isEmpty() ? 0 : team.getPersonList().size();
        txtSubtitle.setText(String.valueOf(countMembers + " Integrante(s)"));
        txtMembers.setText(team.getMembersName());
        Glide.with(itemView.getContext())
                .load(team.getUrlImage())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_menu_camera))
                .into(imgPicture);
        /*setupTeamAdapter(team.getUserList(), itemView.getContext());
        txtTeamName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null){
                    listener.onTeamButtonDeleteClick(team);
                }
                return false;
            }
        });*/
        itemView.setOnClickListener(this);
    }

    @Override
    public void onItemSelected() {
        //itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        Log.d(TAG, "onItemClear");
        //itemView.setBackgroundColor(0);
    }

    @Override
    public void onItemSwiped() {
        Log.d(TAG, "onItemSwiped");
    }

    /*
    private void setupTeamAdapter(List<User> users, Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        UserAdapter adapter = new UserAdapter(users, context);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.txtTeamName, R.id.img})
    public void changeVisibilityTeams(){
        int actualVisibility = recyclerView.getVisibility();
        int newVisibility = View.GONE;
        if (actualVisibility == View.VISIBLE){
            newVisibility = View.GONE;
        }else if (actualVisibility == View.GONE){
            newVisibility = View.VISIBLE;
        }
        recyclerView.setVisibility(newVisibility);
    }*/


    public void setGroupTranslation(float dX, float dY, boolean isCurrentlyActive) {
        float widthDeleteContainer = deleteContainer.getWidth();
        Log.d(TAG, "dX: " + dX + ",dY: " + dY + ", isCurrentlyActive: " + isCurrentlyActive + ", widthDeleteContainer: " + widthDeleteContainer);
        constraintLayout.setTranslationX(dX);
    }


    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onTeamSelected(team);
        }
    }
}
