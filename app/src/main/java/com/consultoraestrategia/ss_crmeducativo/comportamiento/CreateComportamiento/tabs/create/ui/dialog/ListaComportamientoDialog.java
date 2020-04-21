package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters.ComportamientoaAdapter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter.TabCreateComportPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListaComportamientoDialog extends DialogFragment implements ListaComportamientoView, ComportamientoaAdapter.TipoComportamientoListListener {


    @BindView(R.id.rc_comportamiento)
    RecyclerView rcComportamiento;
    @BindView(R.id.searchView)
    SearchView searchView;
    private Unbinder unbinder;
    private ComportamientoaAdapter adapter;
    private TabCreateComportPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_lista_comportamiento, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setupAdapter();
        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setQueryHint("Buscar comportamiento");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setupAdapter() {
        adapter = new ComportamientoaAdapter(this);
        rcComportamiento.setAdapter(adapter);
        rcComportamiento.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(TabCreateComportPresenter tabCreateComportPresenter) {
        this.presenter = tabCreateComportPresenter;
    }

    @Override
    public void showListComportamiento(List<TipoComportamientoUi> tipoUiListHijos) {
        adapter.setTipoComportamientoList(tipoUiListHijos);
    }

    @Override
    public void onClickTipoComportamiento(TipoComportamientoUi tipoComportamientoUi) {
        this.presenter.onClickTipoComportamiento(tipoComportamientoUi);
    }

    @OnClick(R.id.btn_salir)
    public void onViewClicked() {
        dismiss();
    }
}
