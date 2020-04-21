package com.consultoraestrategia.ss_crmeducativo.login2.principal.listaUsuario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Presenter;
import com.consultoraestrategia.ss_crmeducativo.login2.adapter.PersonaAdapter;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.listener.PersonaListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListaUsuarioFragment extends Fragment implements ListaUsuarioView, PersonaListener {

    @BindView(R.id.rc_contactos)
    RecyclerView rcContactos;
    @BindView(R.id.btn_quitar_usuario)
    TextView btnQuitarUsuario;
    private Unbinder unbinder;
    private Login2Presenter presenter;
    private PersonaAdapter usuarioAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_lista_usuario, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //   initAdapter();
        rcContactos.setLayoutManager(new LinearLayoutManager(getContext()));
        this.usuarioAdapter = new PersonaAdapter(new ArrayList<PersonaUi>(), this);
        rcContactos.setAdapter(this.usuarioAdapter);
    }

    @Override
    public void onAttach(Login2Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void listaUsuarioView(List<PersonaUi> usuarioUiList, boolean quitarUsuario) {
        usuarioAdapter.setUsuarios(usuarioUiList, quitarUsuario);
    }

    @Override
    public void setTextoBtnQuitarUsuario(String texto) {
        btnQuitarUsuario.setText(texto);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_quitar_usuario)
    public void onViewClicked() {
        presenter.onClickQuitarUsuario();
    }

    @Override
    public void onClickPersona(PersonaUi personaUi) {
        presenter.onClickPersona(personaUi);
    }
}
