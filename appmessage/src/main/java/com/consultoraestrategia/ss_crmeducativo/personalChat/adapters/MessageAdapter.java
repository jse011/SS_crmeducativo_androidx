package com.consultoraestrategia.ss_crmeducativo.personalChat.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MessageAdapter extends RecyclerView.Adapter {

    final int MESSAGE=0, DATE=1;

    List<Object> messageUiList;
    int idSender;
    RecyclerView recyclerView;

    public MessageAdapter(List<Object> messageUiList, RecyclerView recyclerView) {
        this.messageUiList = messageUiList;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case DATE:
                return new DateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_date, parent, false));
            default:
                return new MessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       Object object= messageUiList.get(position);

        if(holder instanceof MessageHolder)
        {
            MessageHolder messageHolder= (MessageHolder)holder;
            messageHolder.bind((MessageUi) object, idSender);
        }
        if(holder instanceof DateHolder){
            DateHolder dateHolder=(DateHolder)holder;
            dateHolder.bind((String) object);
        }

    }

    @Override
    public int getItemCount() {
        return messageUiList.size();
    }

    public void  setList(List<Object> messageUis, int idSender){
        this.messageUiList.clear();
        this.messageUiList.addAll(messageUis);
        this.idSender=idSender;
        notifyDataSetChanged();
    }

    public void addMessages(List<Object> messageUis) {

        Set<Object>objects= new LinkedHashSet<>();
        objects.addAll(messageUis);
        objects.addAll(messageUiList);
        messageUiList.clear();
        messageUiList.addAll(objects);
        recyclerView.scrollToPosition(messageUis.size());
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        Object object= messageUiList.get(position);
        if(object instanceof String )return DATE;
        else return MESSAGE;

    }

    public void updatePosition(String messageId) {

        for(Object object: messageUiList){
            if(object instanceof MessageUi)
            {
                MessageUi messageUi= (MessageUi)object;
                if(messageUi.getId().equals(messageId))
                {
                    int position=messageUiList.indexOf(object);
                    recyclerView.scrollToPosition(position);
                    notifyDataSetChanged();
                    break;

                }
            }
        }
    }
}
