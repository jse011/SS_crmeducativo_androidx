package com.consultoraestrategia.ss_crmeducativo.chats.contacs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs.ContacsAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends Fragment implements ContacsView, ViewPagerItemListener<ChatPresenter>, ChatListener, SwipeRefreshLayout.OnRefreshListener {

    String TAG = ContactsFragment.class.getSimpleName();
    @BindView(R2.id.recy_contacts)
    RecyclerView recyContacts;
    @BindView(R2.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R2.id.progress)
    ProgressBar progress;
    private ContacsAdapter contacsAdapter;
    private ChatPresenter chatPresenter;
    private Unbinder unbinder;

    @Override
    public void onAttach(ChatPresenter presenter) {
        this.chatPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Log.e(TAG, "onCreateView");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        initAdapter();
        swipeContainer.setOnRefreshListener(this);
    }

    private void initAdapter() {
        contacsAdapter = new ContacsAdapter(new ArrayList<Object>(), this);
        recyContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyContacts.setAdapter(contacsAdapter);
    }

    @Override
    public void setListContacts(List<Object> listContacts) {
        Log.d(TAG, "setListContacts " + listContacts.size());
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
    public void hideProgress2() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showProgress2() {
        swipeContainer.setRefreshing(true);
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

        if (object instanceof ContactUi) {
            chatPresenter.onClickChatContacto((ContactUi) object);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        chatPresenter.onRefreshContactos();
    }
}
