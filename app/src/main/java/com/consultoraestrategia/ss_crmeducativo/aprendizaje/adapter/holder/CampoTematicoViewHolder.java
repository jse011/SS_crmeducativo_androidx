package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarCampoAccion;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class CampoTematicoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.rec_campoTematicoBase)
    RecyclerView recCampotematicoBase;
    @BindView(R.id.rec_campoTematicoTrasversal)
    RecyclerView recCampotematicoTransversal;
    @BindView(R.id.rec_campoTematicoEnfoque)
    RecyclerView recCampotematicoEnfoque;
    @BindView(R.id.vacio_base)
    TextView vacioBase;
    @BindView(R.id.vacio_transversal)
    TextView vacioTransversal;
    @BindView(R.id.vacio_enfoque)
    TextView vacioEnfoque;
    @BindView(R.id.constraintLayoutBase)
    ConstraintLayout constraintLayoutBase;
    @BindView(R.id.constraintLayoutGeneral)
    ConstraintLayout constraintLayoutGeneral;
    @BindView(R.id.txt_tituloBase)
    TextView tituloBase;
    @BindView(R.id.txt_tituloEnfoque)
    TextView tituloEnfoque;
    @BindView(R.id.txt_tituloTransversal)
    TextView tituloTransversal;
    @BindView(R.id.ver_mas)
    ImageView verMas;
    int campotematicoUisBase=0;
    int campotematicoUisEnfoque=0;
    int campotematicoUisTransversal=0;

    private boolean verMasState=true;


    public CampoTematicoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(CarCampoAccion carCampoAccion) {

        Set<CampotematicoUi> listBase= new LinkedHashSet<>();
        listBase.addAll(carCampoAccion.getCompetenciaUiListCompetenciaBase());

        if (listBase.isEmpty()) {
            vacioBase.setVisibility(View.VISIBLE);
            recCampotematicoBase.setVisibility(View.GONE);
        } else {
            recCampotematicoBase.setVisibility(View.VISIBLE);
            campotematicoUisBase=listBase.size();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recCampotematicoBase.getContext());
        AprendizajeAdapter adapter = new AprendizajeAdapter(new ArrayList<Object>(listBase), null, null);
        recCampotematicoBase.setAdapter(adapter);
        recCampotematicoBase.setLayoutManager(linearLayoutManager);
        recCampotematicoBase.setNestedScrollingEnabled(false);


        Set<CampotematicoUi> listTransversal= new LinkedHashSet<>();
        listTransversal.addAll(carCampoAccion.getCompetenciaUiListCompetenciaTransversales());

        if (listTransversal.isEmpty()) {
            vacioTransversal.setVisibility(View.VISIBLE);
            recCampotematicoTransversal.setVisibility(View.GONE);
        } else {
            recCampotematicoTransversal.setVisibility(View.VISIBLE);
            campotematicoUisTransversal=listTransversal.size();
        }

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(recCampotematicoTransversal.getContext());
        AprendizajeAdapter adapter1 = new AprendizajeAdapter(new ArrayList<Object>(listTransversal), null, null);
        recCampotematicoTransversal.setAdapter(adapter1);
        recCampotematicoTransversal.setLayoutManager(linearLayoutManager2);
        recCampotematicoTransversal.setNestedScrollingEnabled(false);



        Set<CampotematicoUi> listEnfoque= new LinkedHashSet<>();
        listEnfoque.addAll(carCampoAccion.getCompetenciaUiListEnfoqueTransversal());
        if (listEnfoque.isEmpty()) {
            vacioEnfoque.setVisibility(View.VISIBLE);
            recCampotematicoEnfoque.setVisibility(View.GONE);
        } else {
            recCampotematicoEnfoque.setVisibility(View.VISIBLE);
            campotematicoUisEnfoque=listEnfoque.size();
        }


        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(recCampotematicoEnfoque.getContext());
        AprendizajeAdapter adapter2 = new AprendizajeAdapter(new ArrayList<Object>(listEnfoque), null, null);
        recCampotematicoEnfoque.setAdapter(adapter2);
        recCampotematicoEnfoque.setLayoutManager(linearLayoutManager3);
        recCampotematicoEnfoque.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.constraintLayoutGeneral, R.id.ver_mas})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraintLayoutGeneral:
                verMasMethod();
                break;
            case R.id.ver_mas:
                verMasMethod();
                break;
        }
    }

    private void verMasMethod(){
        if (verMasState){
            verMas.setRotation(0);
            verMasState=false;
            hideAll();
        }
        else {
            verMas.setRotation(180);
            verMasState=true;
            showAll();
        }
    }

    private void hideAll(){
        tituloBase.setVisibility(View.GONE);
        tituloEnfoque.setVisibility(View.GONE);
        tituloTransversal.setVisibility(View.GONE);
        recCampotematicoBase.setVisibility(View.GONE);
        recCampotematicoEnfoque.setVisibility(View.GONE);
        recCampotematicoTransversal.setVisibility(View.GONE);
        vacioBase.setVisibility(View.GONE);
        vacioEnfoque.setVisibility(View.GONE);
        vacioTransversal.setVisibility(View.GONE);
    }

    private void showAll(){
        tituloBase.setVisibility(View.VISIBLE);
        tituloEnfoque.setVisibility(View.VISIBLE);
        tituloTransversal.setVisibility(View.VISIBLE);
        recCampotematicoBase.setVisibility(View.VISIBLE);
        recCampotematicoEnfoque.setVisibility(View.VISIBLE);
        recCampotematicoTransversal.setVisibility(View.VISIBLE);
        if (campotematicoUisEnfoque==0)vacioEnfoque.setVisibility(View.VISIBLE);
        if (campotematicoUisTransversal==0)vacioTransversal.setVisibility(View.VISIBLE);
        if (campotematicoUisBase==0)vacioBase.setVisibility(View.VISIBLE);
    }

}
