package com.consultoraestrategia.ss_crmeducativo.chats.listchats;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.ChatAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment implements ChatsView, ViewPagerItemListener<ChatPresenter>, ChatListener, SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recy_chats;
    private ChatAdapter chatAdapter;
    private ChatPresenter chatPresenter;
    private SwipeRefreshLayout setRefreshing;

    String TAG= ChatsFragment.class.getSimpleName();
    @Override
    public void onAttach(ChatPresenter presenter) {
        this.chatPresenter=presenter;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Log.e(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recy_chats=(RecyclerView)getActivity().findViewById(R.id.recy_chats);
        setRefreshing= (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_container);
        initAdapter();

        Log.e(TAG, "onViewCreated");
        setRefreshing.setOnRefreshListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initAdapter() {
        chatAdapter = new ChatAdapter(new ArrayList<ChatUi>(), this);
        recy_chats.setLayoutManager(new LinearLayoutManager(getContext()));
        recy_chats.setAdapter(chatAdapter);
    }


    @Override
    public void setListChats(List<ChatUi> chats) {
        chatAdapter.setList(chats);
    }
    @Override
    public void clickChat(Object object) {
        if(object instanceof ChatUi) {
            chatPresenter.onClickChat((ChatUi)object);
        }
 /*       if(object instanceof ChatUi){
            ChatUi chatUi=(ChatUi)object;
            SendDataChatBundle sendDataChatBundle= new SendDataChatBundle();
            sendDataChatBundle.setSenderId( chatUi.getIdSender());
            sendDataChatBundle.setReceiverId(chatUi.getIdReceiver());
            switch (chatUi.getTypeChat()){
                case GROUP:
                    sendDataChatBundle.setTypeChat(SendDataChatBundle.TypeChat.GROUP);
                    switch (chatUi.getTypePerson()){
                        case STUDENTS:
                            sendDataChatBundle.setTypePerson(SendDataChatBundle.TypePerson.STUDENTS);
                            break;
                        case TEACHERS:
                            sendDataChatBundle.setTypePerson(SendDataChatBundle.TypePerson.TEACHERS);
                            break;
                        default:
                            sendDataChatBundle.setTypePerson(SendDataChatBundle.TypePerson.PARENTS);
                            break;
                    }
                    switch (chatUi.getTypeGroup()){
                        case COURSE:
                            sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.COURSE);
                            break;
                        case ACADEMIC:
                            sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.ACADEMIC);
                            break;
                        default:
                            sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.TEAM);
                            break;
                    }
                    break;
                default:
                    sendDataChatBundle.setTypeChat(SendDataChatBundle.TypeChat.PERSONAL);
                    break;
            }
            Intent intent= new Intent(getContext(), PersonalChatActivity.class);
            intent.putExtras(sendDataChatBundle.getBundle());
            startActivity(intent);
        }*/

    }

    @Override
    public void hideProgress() {
        setRefreshing.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        setRefreshing.setRefreshing(true);
    }

    @Override
    public void searhChats(String toString) {
        chatAdapter.getFilter().filter(toString);
    }

    @Override
    public void onRefresh() {
        chatPresenter.onRefreshChats();
    }


}
