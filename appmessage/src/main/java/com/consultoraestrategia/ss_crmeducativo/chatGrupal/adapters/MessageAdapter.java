package com.consultoraestrategia.ss_crmeducativo.chatGrupal.adapters;


import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.adapters.holder.DateHolder;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.adapters.holder.MessageFotoGrupoHolder;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.adapters.holder.MessageHolder;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.ItemTouchHelperAdapter;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private final int MESSAGE = 1, DATE = 2, MESSAGE_IMAGEN = 3;
    private final Listener listener;

    private int personaId;
    private List<Object> messageUiList = new ArrayList<>();
    private RecyclerView recyclerView;
    private final OnStartDragListener mDragStartListener;

    public MessageAdapter(RecyclerView recyclerView, OnStartDragListener dragStartListener, Listener listener) {
        mDragStartListener = dragStartListener;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATE:
                return new DateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_date, parent, false));
            case MESSAGE_IMAGEN:
                return new MessageFotoGrupoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_grupo_imagen, parent, false));
            default:
                return new MessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_grupo, parent, false));
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        Object object = messageUiList.get(position);
        switch (holder.getItemViewType()) {
            case DATE:
                DateHolder dateHolder = (DateHolder) holder;
                dateHolder.bind((String) object);

                break;
            case MESSAGE:
                final MessageHolder messageHolder = (MessageHolder) holder;
                messageHolder.bind((MessageUi2) object, personaId, listener);
                messageHolder.foregroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                            mDragStartListener.onStartDrag(messageHolder);
                        }
                        return false;
                    }
                });
                break;
            case MESSAGE_IMAGEN:
                final MessageFotoGrupoHolder messageGrupoHolder = (MessageFotoGrupoHolder) holder;
                messageGrupoHolder.bind((MessageUi2) object, personaId, listener);
                messageGrupoHolder.foregroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                            mDragStartListener.onStartDrag(messageGrupoHolder);
                        }
                        return false;
                    }
                });

                break;
        }


    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messageUiList.size();
    }

    public void setList(List<Object> messageUis, int personaId) {
        this.personaId = personaId;
        this.messageUiList.clear();
        this.messageUiList.addAll(messageUis);
        notifyDataSetChanged();
    }

    public void addListMessages(List<Object> messageUis) {//Eliminar
        messageUiList.clear();
        messageUiList.addAll(messageUis);
        recyclerView.scrollToPosition(messageUis.size());
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        Object object = messageUiList.get(position);
        if (object instanceof String) return DATE;
        else if (object instanceof MessageUi2) {
            switch (((MessageUi2) object).getTipo()) {
                case IMAGEN:
                    return MESSAGE_IMAGEN;
                default:
                    return MESSAGE;
            }
        }
        return 0;
    }

    public void updatePosition(String messageId) {

        for (Object object : messageUiList) {
            if (object instanceof MessageUi2) {
                MessageUi2 messageUi = (MessageUi2) object;
                if (messageUi.getId().equals(messageId)) {
                    int position = messageUiList.indexOf(object);
                    recyclerView.scrollToPosition(position);
                    notifyDataSetChanged();
                    break;

                }
            }
        }
    }

    public void addMessages(List<Object> messageUis) {
        messageUiList.clear();
        messageUiList.addAll(messageUis);
        recyclerView.scrollToPosition(messageUis.size());
        notifyDataSetChanged();
        recyclerView.scrollToPosition(messageUiList.size() - 1);
    }

    public void scrollToPositionBotton() {
        recyclerView.scrollToPosition(messageUiList.size() - 1);
    }

    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(messageUiList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        notifyItemChanged(position);
        //messageUiList.remove(position);
        //notifyItemRemoved(position);
    }

    public interface Listener {
        void onSeleccionar(MessageUi2 messageUi2);
        void onLongClick(MessageUi2 messageUi2);
        void onClick(MessageUi2 messageUi2);
    }
}
