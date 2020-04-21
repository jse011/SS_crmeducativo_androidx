package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder.UserViewHolder;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.User;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.bind(user);
    }


    @Override
    public int getItemCount() {
        return users.size();
    }
}
