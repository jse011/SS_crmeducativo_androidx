package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorContainer;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class CampoTematicoChooserDialogFragment extends DialogFragment implements IndicadorCampoTematicoView {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.txtvacio)
    TextView vacio;
    Unbinder unbinder;
    IndicadorCampoTematicoView view;

    public CampoTematicoChooserDialogFragment() {
    }

    public static final String ARG_INDICADOR_LIST = "indicador_list";

    public static CampoTematicoChooserDialogFragment newInstance(List<IndicadorUi> indicadorList) {
        CampoTematicoChooserDialogFragment frag = new CampoTematicoChooserDialogFragment();
        IndicadorContainer container = new IndicadorContainer(indicadorList);
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDICADOR_LIST, Parcels.wrap(container));
        frag.setArguments(args);
        return frag;
    }

    private CampoTematicoChooserCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CampoTematicoChooserCallback) {
            callback = (CampoTematicoChooserCallback) context;
        } else
            throw new ClassCastException(context.getClass().getSimpleName() + "must be implement CampoTematicoChooserCallback");
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
        View view = inflater.inflate(R.layout.layout_createrubabid_campoaccion, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private List<IndicadorUi> indicadorList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        initAdapter();
        IndicadorContainer container = Parcels.unwrap(getArguments().getParcelable(ARG_INDICADOR_LIST));
        indicadorList = container.getIndicadorList();
        showIndicadorCampoTematicoList(indicadorList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bttn_negative, R.id.bttn_positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_negative:
                callback.onCampoTematicoCancel();
                break;
            case R.id.bttn_positive:
                callback.onCampoTematicoListOk(indicadorList);
                break;
        }
        dismiss();
    }

    private IndicadorCampoTematicoAdapter adapter;

    public void initAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new IndicadorCampoTematicoAdapter(new ArrayList<Object>());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }
    @Override
    public void showIndicadorCampoTematicoList(List<IndicadorUi> indicadorList) {
        if (indicadorList.isEmpty())vacio.setVisibility(View.VISIBLE);
         else vacio.setVisibility(View.GONE);

        List<Object> items = new ArrayList<>();
        for (IndicadorUi indicador :
                indicadorList) {
            if (indicador == null) break;
            Log.d(getClass().getSimpleName(), "indicador " +indicador.getTitulo() );
            items.add(indicador);
            List<CampoAccionUi> accionList = indicador.getCampoAccionList();
            items.addAll(accionList);
            adapter.setItems(items);
        }
    }





}
