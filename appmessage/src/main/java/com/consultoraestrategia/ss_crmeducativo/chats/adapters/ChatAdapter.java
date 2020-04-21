package com.consultoraestrategia.ss_crmeducativo.chats.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends  RecyclerView.Adapter implements Filterable {
    private final static String TAG = "ChatAdapter";
    private List<ChatUi>chatUis;
    private List<ChatUi> mFilteredList;
    private ChatListener chatListener;

    private final int GROUP=1, PERSONAL=0;

    public ChatAdapter(List<ChatUi> chatUis , ChatListener chatListener) {
        this.chatUis = chatUis;
        this.mFilteredList = chatUis;
        this.chatListener=chatListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case GROUP:
                return new ChatHolderGroup(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_group, parent, false));
            default:
                return new ChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatHolderGroup ){
            ChatHolderGroup chatHolderGroup=(ChatHolderGroup)holder;
            chatHolderGroup.bind(chatUis.get(position), chatListener);
        }else{
            ChatHolder chatHolder=(ChatHolder)holder;
            chatHolder.bind(chatUis.get(position), chatListener);
        }

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }
    public void setList(List<ChatUi>list)
    {
        Log.d(TAG, "setList: " + list.size());
        chatUis.clear();
        chatUis.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ChatUi chatUi= chatUis.get(position);
        if(chatUi.getTypeChat()==ChatUi.TypeChat.GROUP)
            return GROUP;
        else return PERSONAL;
    }

    @Override
    public Filter getFilter() {
       return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = chatUis;
                } else {

                    List<ChatUi> filteredList = new ArrayList<>();

                    for (ChatUi chatUi : chatUis) {
                        if (chatUi.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(chatUi);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<ChatUi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
