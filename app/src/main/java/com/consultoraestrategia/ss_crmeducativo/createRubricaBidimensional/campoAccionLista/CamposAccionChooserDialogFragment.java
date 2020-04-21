package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class CamposAccionChooserDialogFragment extends DialogFragment implements CamposAccionView, CamposAccionChooserCallback {

    private static final String ARG_CAMPOACION_LIST = "CampoAccionList";
    Unbinder unbinder;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.txt_vacio)
    TextView vacio;
    private static final String TAG = CamposAccionChooserDialogFragment.class.getSimpleName();

    public CamposAccionChooserDialogFragment() {
    }

    public static CamposAccionChooserDialogFragment newInstance(List<Object> campoAccionUiList) {
        CamposAccionChooserDialogFragment frag = new CamposAccionChooserDialogFragment();
        CamposAccionListWrapper container = new CamposAccionListWrapper(campoAccionUiList);
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAMPOACION_LIST,container);
        frag.setArguments(args);
        return frag;
    }

    private CamposAccionChooserCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CamposAccionChooserCallback) {
            callback = (CamposAccionChooserCallback) context;
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
        View view = inflater.inflate(R.layout.layout_create_rubabid_buscar_campoaccion_competencia, container, false);
        unbinder = ButterKnife.bind(this, view);
        CamposAccionListWrapper wrapper=(CamposAccionListWrapper) getArguments().getSerializable(ARG_CAMPOACION_LIST);
        List<Object> campoAccionUiList =wrapper.getItems();
        showCamposAccionList(campoAccionUiList);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private CamposAccionAdapter adapter;

    @Override
    public void showCamposAccionList(List<Object> campoAccionUiList) {
        if(campoAccionUiList.isEmpty()) vacio.setVisibility(View.VISIBLE);
        else vacio.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new CamposAccionAdapter(campoAccionUiList, this);
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycler.setAdapter(adapter);
            ((SimpleItemAnimator) recycler.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCheckedCamposAccion(CampoAccionUi campoAccionUi) {
        IndicadorCampoAccionUi indicadorCampoAccionUi = campoAccionUi.getIndicadorCampoAccionUi();
        if(indicadorCampoAccionUi!=null){
            if(campoAccionUi.isChecked()){
                indicadorCampoAccionUi.setChecked(true);
            }else {
                int count = 0;
                CampoAccionUi campoAccionUiPadre = indicadorCampoAccionUi.getCampoAccionUi();
                if(campoAccionUiPadre.getCampoAccionUiList()!=null)
                    for (CampoAccionUi itemCampoAccionUi: campoAccionUiPadre.getCampoAccionUiList()){
                        if(itemCampoAccionUi.getIndicadorCampoAccionUi()!=null
                                &&itemCampoAccionUi.getIndicadorCampoAccionUi().equals(indicadorCampoAccionUi)){
                            if(itemCampoAccionUi.isChecked()){
                                count++;
                            }
                        }
                    }
                indicadorCampoAccionUi.setChecked(count!=0);
            }
        }
        adapter.update(indicadorCampoAccionUi);
        //adapter.notifyAllChildChanged(recycler);
        callback.onCheckedCamposAccion(campoAccionUi);
    }

    @Override
    public void onCheckedIndicador(IndicadorCampoAccionUi indicadorCampoAccionUi) {

        CampoAccionUi campoAccionUiPadre = indicadorCampoAccionUi.getCampoAccionUi();
        if(campoAccionUiPadre.getCampoAccionUiList()!=null)
            for (CampoAccionUi itemCampoAccionUi: campoAccionUiPadre.getCampoAccionUiList()){
                if(itemCampoAccionUi.getIndicadorCampoAccionUi()!=null&&
                        itemCampoAccionUi.getIndicadorCampoAccionUi().equals(indicadorCampoAccionUi)){
                    itemCampoAccionUi.setChecked(indicadorCampoAccionUi.getChecked());
                    adapter.update(itemCampoAccionUi);
                }
            }

        //adapter.notifyAllChildChanged(recycler);
    }

    public CamposAccionAdapter getAdapter() {
        return adapter;
    }
}
