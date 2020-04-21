package com.consultoraestrategia.ss_crmeducativo.chats.contacs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs.ContacsAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment implements ContacsView, ViewPagerItemListener<ChatPresenter>, ChatListener {

    String TAG=ContactsFragment.class.getSimpleName();

    private RecyclerView recy_contact;

    private ContacsAdapter contacsAdapter;
    private ChatPresenter chatPresenter;
    private ProgressBar progress;
    @Override
    public void onAttach(ChatPresenter presenter) {
        this.chatPresenter=presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Log.e(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        recy_contact=(RecyclerView)getActivity().findViewById(R.id.recy_contacts);
        progress=(ProgressBar)getActivity().findViewById(R.id.progress);


        initAdapter();


    }

    private void initAdapter() {
        contacsAdapter = new ContacsAdapter(new ArrayList<Object>(), this);
        recy_contact.setLayoutManager(new LinearLayoutManager(getContext()));
        recy_contact.setAdapter(contacsAdapter);
    }

    @Override
    public void setListContacts(List<Object> listContacts) {
        Log.d(TAG, "setListContacts "+ listContacts.size());
        contacsAdapter.setList(listContacts);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }


    @Override
    public void searhContacts(String toString) {
        contacsAdapter.search(toString);
    }

    @Override
    public void setOldListContacts() {
        contacsAdapter.listOld();
    }


    @Override
    public void clickChat(Object object) {

        if(object instanceof ContactUi){
            chatPresenter.onClickChatContacto((ContactUi)object);
        }

    }


}
