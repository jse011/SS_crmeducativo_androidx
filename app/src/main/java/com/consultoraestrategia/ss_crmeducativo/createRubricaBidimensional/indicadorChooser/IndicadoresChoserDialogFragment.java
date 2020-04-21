package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciasContainer;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import org.parceler.Parcels;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 5/02/2018.
 */

public class IndicadoresChoserDialogFragment extends DialogFragment implements IndicadorChooserView, CompeteneciaIndicadorAdapter.CompetenciaListener {
    private static final String TAG = IndicadoresChoserDialogFragment.class.getSimpleName();
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.txt_vacio)
    TextView vacio;
    Unbinder unbinder;
    private CompeteneciaIndicadorAdapter adapter;
    private IndicadorChooserPresenter presenter;


    public IndicadoresChoserDialogFragment() {
    }

    public static final String ARG_COMPETENCIA_LIST = "competencia_list";

    public static IndicadoresChoserDialogFragment newInstance(List<CompetenciaUi> competenciaUiList) {
        IndicadoresChoserDialogFragment frag = new IndicadoresChoserDialogFragment();
        CompetenciasContainer container = new CompetenciasContainer(competenciaUiList);
        Bundle args = new Bundle();
        args.putParcelable(ARG_COMPETENCIA_LIST, Parcels.wrap(container));
        frag.setArguments(args);
        return frag;
    }

    private IndicadorChooserCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IndicadorChooserCallback) {
            callback = (IndicadorChooserCallback) context;
        } else
            throw new ClassCastException(context.getClass().getSimpleName() + "must be implement IndicadorChooserCallback");
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_rubrica_indicador_chooser, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupAdapter();

        setupPresenter();
//        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return view;
    }

    private void setupAdapter() {
        adapter = new CompeteneciaIndicadorAdapter(new ArrayList<CompetenciaUi>(), this);
        StickyHeaderLayoutManager layoutManager = new StickyHeaderLayoutManager();
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        ((SimpleItemAnimator) recycler.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void setupPresenter() {
        presenter = new IndicadorChooserPresenterImpl();
        setPresenter(presenter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(IndicadorChooserPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtra(getArguments());
        presenter.onCreate();
    }

    @Override
    public void showCompetenciaList(List<CompetenciaUi> competenciaList) {
        adapter.setList(competenciaList);
    }


    @Override
    public void showMensajeVacio() {
        vacio.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMensajeVacio() {
        vacio.setVisibility(View.GONE);
    }

    @Override
    public void notyChangeList() {
        adapter.notifyAllChildChanged(recycler);
    }

    @Override
    public void updateIndicador(CompetenciaUi competenciaUi, IndicadorUi indicadorUi) {
        adapter.updateItemIndex(competenciaUi, indicadorUi);
    }

    @Override
    public void updateCampotematico(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampoAccionUi campoAccionUi) {
        adapter.updateItemIndexStatus(competenciaUi, indicadorUi,campoAccionUi);
    }

    @Override
    public void onClickCampoAccion(CampoAccionUi campoAccionUi) {
        presenter.onClickCampoAccion(campoAccionUi);
    }

    @Override
    public void onClickIndicador(IndicadorUi indicadorUi) {
        presenter.onClickIndicador(indicadorUi);
    }
}
