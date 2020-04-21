package com.consultoraestrategia.ss_crmeducativo.createTeam.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.holder.MemberViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.listener.PersonListener;

import java.util.List;
/**
 * Created by @stevecampos on 19/09/2017.
 */

public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MemberAdapter.class.getSimpleName();
    private List<Person> persons;
    private Context context;
    private PersonListener listener;
    private RecyclerView recyclerView;

    public MemberAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setListener(PersonListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = persons.get(position);
        ((MemberViewHolder)holder).bind(person, context, listener);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void addMember(Person person){
        if (!this.persons.contains(person)){
            this.persons.add(person);
            notifyItemInserted(getItemCount()-1);
            scrollToLastItem();
        }
    }

    private void scrollToLastItem(){
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public void removeMember(Person person){
        int position = persons.indexOf(person);
        Log.d(TAG, "position: " + position);
        if (position != -1){
            this.persons.remove(position);
            notifyItemRemoved(position);
        }

    }

}
