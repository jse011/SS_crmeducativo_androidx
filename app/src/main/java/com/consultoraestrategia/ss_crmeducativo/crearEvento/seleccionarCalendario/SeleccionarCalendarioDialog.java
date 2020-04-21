package com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario;

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
import com.consultoraestrategia.ss_crmeducativo.crearEvento.CrearEventoPresenter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter.CalendarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SeleccionarCalendarioDialog extends DialogFragment implements SeleccionarCalendarioView, CalendarioAdapter.Listener {


    @BindView(R.id.rc_comportamiento)
    RecyclerView rcComportamiento;
    @BindView(R.id.searchView)
    SearchView searchView;
    private Unbinder unbinder;
    private CalendarioAdapter adapter;
    private CrearEventoPresenter presenter;


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
        searchView.setQueryHint("Buscar");
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();
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
        adapter = new CalendarioAdapter(this);
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

    @OnClick(R.id.btn_salir)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void onClickTipoCalendario(TipoCalendarioUi tipoComportamientoUi) {
        this.presenter.onItemClickTipoCalendario(tipoComportamientoUi);
        dismiss();
    }

    @Override
    public void onAttach(CrearEventoPresenter crearEventoPresenter) {
        this.presenter = crearEventoPresenter;
    }

    @Override
    public void showListComportamiento(List<TipoCalendarioUi> tipoCalendarioUis) {
        adapter.setTipoComportamientoList(tipoCalendarioUis);
    }
}
