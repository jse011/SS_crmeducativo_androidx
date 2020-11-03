package com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.List;

import butterknife.BindView;

public class GroupAdapter extends RecyclerView.Adapter {

    private List<Object> objects;
    private List<Object> objectsOld;
    private final int GROUP_TEAM = 1, GROUP_ACADEMIC = 2, GROUP_COURSE = 3, TITLE = 4;
    private ChatListener chatListener;

    public GroupAdapter(List<Object> objects, ChatListener chatListener) {
        this.objects = objects;
        this.objectsOld = null;
        this.chatListener = chatListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        switch (i) {
            case TITLE:
                return new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_group, parent, false));
            case GROUP_TEAM:
                return new GroupTeamHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_team, parent, false));
            case GROUP_COURSE:
                return new GroupCourseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_course, parent, false));
            default:
                return new GroupHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_academic_chat, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Object object = objects.get(position);
        if (viewHolder instanceof TitleHolder) {
            TitleHolder titleHolder = (TitleHolder) viewHolder;
            titleHolder.bind((String) object);

        } else if (viewHolder instanceof GroupTeamHolder) {
            GroupTeamHolder groupTeamHolder = (GroupTeamHolder) viewHolder;
            groupTeamHolder.bind((GroupUi) object, chatListener);
        } else if (viewHolder instanceof GroupCourseHolder) {
            GroupCourseHolder groupCourseHolder = (GroupCourseHolder) viewHolder;
            groupCourseHolder.bind((GroupUi) object, chatListener);
        } else {
            GroupHolder groupHolder = (GroupHolder) viewHolder;
            groupHolder.bind((GroupUi) object, chatListener);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = objects.get(position);
        if (object instanceof GroupUi) {
            GroupUi groupUi = (GroupUi) object;
            switch (groupUi.getType()) {
                case TEAM:
                    return GROUP_TEAM;
                case COURSE:
                    return GROUP_COURSE;
                default:
                    return GROUP_ACADEMIC;
            }
        } else return TITLE;
    }

    public void setList(List<Object> objects) {
        if (objectsOld == null) objectsOld = objects;
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

}
