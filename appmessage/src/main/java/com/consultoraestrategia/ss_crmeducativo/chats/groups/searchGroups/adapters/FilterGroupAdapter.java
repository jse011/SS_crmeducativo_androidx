package com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups.GroupCourseHolder;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups.GroupHolder;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups.GroupTeamHolder;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.CategoryUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

public class FilterGroupAdapter extends RecyclerView.Adapter {

    private List<Object>objects;
    private List<Object>objectsOld;
    private List<Object>objectsFilter;
    final int TITLE=0, CATEGORY=1, GROUP_TEAM=2 ,GROUP_COURSE=3,GROUP_ACADEMIC=4;
    private ChatListener chatListener;

    public FilterGroupAdapter(List<Object> objects , ChatListener chatListener) {
        this.objects = objects;
        this.objectsOld=null;
        this.chatListener=chatListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case TITLE:
                return new TitleFilterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_filter_group, viewGroup, false));
            case CATEGORY:
                return new CategoryGroupHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_groups, viewGroup, false));
            case GROUP_TEAM:
                return new GroupTeamHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_team, viewGroup, false));
            case GROUP_COURSE:
                return new GroupCourseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_course, viewGroup, false));
            default:   return new GroupHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_academic_chat, viewGroup, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Object object=objects.get(position);
        if(viewHolder instanceof TitleFilterHolder){
            TitleFilterHolder titleFilterHolder=(TitleFilterHolder)viewHolder;
            titleFilterHolder.bind((String)object);
        }else if(viewHolder instanceof  CategoryGroupHolder){
            CategoryGroupHolder categoryGroupHolder=(CategoryGroupHolder)viewHolder;
            categoryGroupHolder.bind((CategoryUi) object,chatListener);
        }else if(viewHolder instanceof GroupTeamHolder){
            GroupTeamHolder groupTeamHolder=(GroupTeamHolder)viewHolder;
            groupTeamHolder.bind((GroupUi) object, chatListener);
        }
        else if(viewHolder instanceof GroupCourseHolder){
            GroupCourseHolder groupCourseHolder=(GroupCourseHolder)viewHolder;
            groupCourseHolder.bind((GroupUi)object, chatListener);
        }
        else {
            GroupHolder groupHolder=(GroupHolder)viewHolder;
            groupHolder.bind((GroupUi) object, chatListener);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object=objects.get(position);
        if(object instanceof  String)return TITLE;
        else if(object instanceof CategoryUi)return CATEGORY;
        else {
            GroupUi groupUi=(GroupUi)object;
            switch (groupUi.getType()){
                case TEAM:
                    return GROUP_TEAM;
                case COURSE:
                    return GROUP_COURSE;
                default:return GROUP_ACADEMIC;
            }
        }
    }
    public void setList(List<Object>objects){
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public void search(String text){
        List<Object>objects= new ArrayList<>();
        for(Object object: objectsFilter){
            if(object instanceof GroupUi){
                GroupUi groupUi=(GroupUi)object;
                String  name= groupUi.getName().toUpperCase();
                int position=name.indexOf(text.toUpperCase());
                if(position!=-1)objects.add(object);

            }
        }
        setList(objects);
    }
    public void filterXCatefory(CategoryUi categoryUi){
        List<Object>objects= new ArrayList<>();
        for(Object object: objectsOld){
            if(object instanceof GroupUi){
                GroupUi groupUi=(GroupUi)object;
                switch (categoryUi.getType()){
                    case PERIOD:
                        if(groupUi.getType()== GroupUi.Type.ACADEMIC &&groupUi.getIdperiod()==categoryUi.getId())objects.add(object);
                        break;
                    case SECTION:
                        if(groupUi.getType()== GroupUi.Type.ACADEMIC && groupUi.getIdsection()==categoryUi.getId())objects.add(object);
                        break;
                    case ACADEMIC:
                        if(groupUi.getType()==GroupUi.Type.ACADEMIC)objects.add(object);
                        break;
                    case COURSE:
                        if(groupUi.getType()==GroupUi.Type.COURSE)objects.add(object);
                            break;
                    default:
                        if(groupUi.getType()== GroupUi.Type.TEAM && groupUi.getIdCourse()==categoryUi.getId())objects.add(object);
                        break;
                }

            }
        }
        setList(objects);
        objectsFilter= objects;

    }


    public void setListForFilter(List<Object> objectListGroups) {
        if(objectsOld==null)objectsOld=objectListGroups;
        objectsFilter=objectListGroups;
    }

    public void setlistOld() {
        setList(objectsFilter);
    }
}
