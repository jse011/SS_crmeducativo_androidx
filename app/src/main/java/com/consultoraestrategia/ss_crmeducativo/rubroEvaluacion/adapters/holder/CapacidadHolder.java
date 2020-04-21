package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.RubroColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.RubroProcesoAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class CapacidadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int SESIONES=2;
    @BindView(R.id.txt_cant_rubro)
    TextView txtCantRubro;
    @BindView(R.id.btn_select)
    ImageView btnSelect;
    @BindView(R.id.btn_addrubro)
    ImageView btnAddrubro;
    @BindView(R.id.recycler_two)
    RecyclerView recyclerViewClases;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.card_view_rubro)
    CardView cardView;
    @BindView(R.id.card2)
    CardView card2;
    @BindView(R.id.card3)
    CardView card3;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.btn_ancla)
    ImageView btnAncla;
    private RubroProcesoAdapter rubroProcesoAdapter;
    private CapacidadUi capacidadUi;
    private RubroEvaluacionProcesoListener listener;
    private String TAG = "CapacidadHolder";
    private int tipoList;
    private boolean editar;
    private boolean anclar;


    public CapacidadHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        btnAncla.setOnClickListener(this);
    }

    public void bind(final CapacidadUi capacidadUi, final RubroEvaluacionProcesoListener listener, final List<Object> list, int tipoList) {
        if(this.capacidadUi!=null)clearPositionList();

        this.capacidadUi = capacidadUi;
        this.listener = listener;
        this.tipoList = tipoList;
        this.editar = capacidadUi.isCalendarioEditar();
        this.anclar = capacidadUi.isCalendarioAnclar();
        btnAddrubro.setOnClickListener(this);
        itemView.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        cardView.setOnClickListener(this);

        if(capacidadUi.isBtnSelect()){
            btnSelect.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);
        }else {
            btnSelect.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
        }


        if (editar){
            card3.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);
        } else{
            card3.setVisibility(View.INVISIBLE);
            card2.setVisibility(View.INVISIBLE);

        }

        initAncla();
        initAdapter();

        if (capacidadUi.isToogle()) recyclerViewClases.setVisibility(View.VISIBLE);
        else recyclerViewClases.setVisibility(View.GONE);


        txtCantRubro.setText(String.valueOf(capacidadUi.getCantidad()));
        txtTitulo.setText(capacidadUi.getTitle());
        int cantG;
        if (capacidadUi.getRubroProcesoUis().size()<3)cantG = 1;
        else cantG =2;
        switch (tipoList){
            case CompetenciaAdapter.LIST_HORIZONTAL:
                final GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerViewClases.getContext(), cantG, GridLayoutManager.HORIZONTAL, false);
                recyclerViewClases.setLayoutManager(gridLayoutManager);
                if(capacidadUi.getRecylerViewState()!=null){
                    gridLayoutManager.onRestoreInstanceState(capacidadUi.getRecylerViewState());

                }
                // add pager behavior
                /*PagerSnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(getRcCurso());*/
                // add a background color to the recyclerview
                //LinePagerIndicatorDecoration linePagerIndicatorDecoration = new LinePagerIndicatorDecoration();
                //linePagerIndicatorDecoration.setColorActive(ContextCompat.getColor(recyclerViewClases.getContext(), R.color.colorPrimaryDark));
                //linePagerIndicatorDecoration.setColorInactive(ContextCompat.getColor(recyclerViewClases.getContext(), R.color.md_blue_grey_20));
                //linePagerIndicatorDecoration.setFilas(cantG);
                break;
            case CompetenciaAdapter.LIST_VERTICAL:
                if(recyclerViewClases.getLayoutManager()==null||!(recyclerViewClases.getLayoutManager() instanceof AutoColumnStaggeredGridLayoutManager)){
                    AutoColumnStaggeredGridLayoutManager autoColumnGridLayoutManager = new AutoColumnStaggeredGridLayoutManager( OrientationHelper.VERTICAL, itemView.getContext());
                    RubroColumnCountProvider columnCountProvider = new RubroColumnCountProvider(itemView.getContext());
                    autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
                    recyclerViewClases.setLayoutManager(autoColumnGridLayoutManager);

                }
        }

        //scrollPosition = rcRubroEvaluacionResultado.getScrollY();
        rubroProcesoAdapter.setItems(capacidadUi.getRubroProcesoUis());
        /*recyclerViewClases.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "capacidadUi dx2: " + capacidadUi.getRecylerViewState());

            }
        });*/

    }

    private void initAdapter() {
        rubroProcesoAdapter = new RubroProcesoAdapter(new ArrayList<RubroProcesoUi>(), listener, capacidadUi, tipoList);
        rubroProcesoAdapter.setRecyclerView(recyclerViewClases);
        recyclerViewClases.setAdapter(rubroProcesoAdapter);
        recyclerViewClases.setNestedScrollingEnabled(false);
        recyclerViewClases.setHasFixedSize(false);
    }

    public void initAncla() {
        RubroProcesoUi rubroProcesoUi = capacidadUi.getRubroEvalAnclado();
        if(rubroProcesoUi!=null){
            switch (rubroProcesoUi.getTipoFormula()){
                case EVALUADA:
                    btnAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor_red));
                    btnAncla.setVisibility(View.VISIBLE);
                    card.setVisibility(View.VISIBLE);
                    break;
                case ANCLADA:
                    btnAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor2));
                    btnAncla.setVisibility(View.VISIBLE);
                    card.setVisibility(View.VISIBLE);
                    break;
                default:
                    btnAncla.setVisibility(View.GONE);
                    card.setVisibility(View.GONE);
                    break;

            }
        }else {
            btnAncla.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
        }
        if (tipoList==SESIONES){
            card2.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
        }

        if (anclar){
            card.setVisibility(View.VISIBLE);
        } else{
            card.setVisibility(View.GONE);
        }
    }

    private List<RubroProcesoUi> reverseListOrder(List<RubroProcesoUi> rubroProcesoUis)
    {
        Collections.reverse(rubroProcesoUis);
        return rubroProcesoUis;
    }

    public RubroProcesoAdapter getRubroProcesoAdapter() {
        return rubroProcesoAdapter;
    }

    public void setCantiadadRubro(int cantidad) {
        txtCantRubro.setText(String.valueOf(cantidad));
    }

    public void setCapacidadUi(RubroProcesoUi rubroProcesoUi){
        int position = this.capacidadUi.getRubroProcesoUis().indexOf(rubroProcesoUi);
        if (position != -1) {
            this.capacidadUi.getRubroProcesoUis().remove(rubroProcesoUi);
        }
    }

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_rubro:
                onClickCarView();
                break;
            case R.id.btn_addrubro:
                onClickBtnAddrubro();
                break;
            case R.id.btn_select:
                onClickSelectRubros();
                break;
            case R.id.btn_ancla:
                onClickAncla();
                break;
        }
    }

    private void onClickAncla() {
        recyclerViewClases.setVisibility(View.VISIBLE);
        listener.onClickAncla(capacidadUi);
    }

    private void onClickSelectRubros() {
        recyclerViewClases.setVisibility(View.VISIBLE);
        listener.onClickSelectRubros(capacidadUi);
    }

    private void onClickBtnAddrubro() {
        Log.d(TAG, "onClickBtnAddrubro " + capacidadUi.getTitle());
        recyclerViewClases.setVisibility(View.VISIBLE);
        //recyclerViewClases.scrollToPosition(0);

        listener.onClickAddRubroEvaluacionCapacidad(capacidadUi);
    }

    private void onClickCarView() {
        if (!capacidadUi.isToogle()) {
            //recyclerViewClases.setVisibility(View.VISIBLE);
            //recyclerViewClases.getLayoutManager().scrollToPosition(tamano);
            //recyclerViewClases.scrollToPosition(0);
            Log.d("capacidaUisa", "list1"+capacidadUi.toString());

            //more.setText("MÍN");
        } else {
           // recyclerViewClases.setVisibility(View.GONE);
            //recyclerViewClases.scrollToPosition(0);
            Log.d("capacidaUisa", "list3"+capacidadUi.toString());
            //more.setText("MÁS");
        }
        listener.onClickCapacidad(capacidadUi);
        Log.d("capacidaUisa", "list1"+capacidadUi.toString());
    }

    public void savePositionList() {
        RecyclerView.LayoutManager layoutManager = recyclerViewClases.getLayoutManager();
        if(layoutManager!=null){
            Parcelable recylerViewState = layoutManager.onSaveInstanceState();
            Log.d(TAG, "capacidadUi dx1: " + recylerViewState);
            capacidadUi.setRecylerViewState(recylerViewState);
        }

    }

    public void clearPositionList() {
        Log.d(TAG, "capacidadUi clear dx1: ");
        capacidadUi.setRecylerViewState(null);
    }
}
