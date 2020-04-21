package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.adapter.InicialAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Inicial extends Fragment implements InicialView, InicialAdapter.Listener {
    Unbinder unbinder;
    @BindView(R.id.rec_preview)
    RecyclerView recPreview;
    @BindView(R.id.txt_cargando)
    TextView txtCargando;
    @BindView(R.id.body)
    ImageView body;
    @BindView(R.id.btn_siguiente)
    Button btnSiguiente;
    private InicialAdapter sendAdapter;
    private PreviewPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_renocimiento_inicio, container, false);
        unbinder = ButterKnife.bind(this, view);
        recPreview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        sendAdapter = new InicialAdapter(this);
        recPreview.setAdapter(sendAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(SentimientoUi sentimientoUi) {
        Glide.with(body)
                .load(sentimientoUi.getFoto())
                .apply(Utils.getGlideRequestOptionsSimple().centerCrop())
                .into(body);
    }

    @Override
    public void addList(SentimientoUi sentimientoUi) {
        sendAdapter.addList(sentimientoUi);
    }

    @Override
    public void setPresenter(PreviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress() {
        txtCargando.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        txtCargando.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_siguiente)
    public void onViewClicked() {
        presenter.btnIniciar();
    }
}
