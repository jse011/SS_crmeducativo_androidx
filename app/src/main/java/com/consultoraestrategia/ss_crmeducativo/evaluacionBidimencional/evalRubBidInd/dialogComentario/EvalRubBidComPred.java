package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.dialogComentario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.EvalRubBidIndPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter.ComentarioPredeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EvalRubBidComPred extends DialogFragment implements EvalRubBidComPredView {
    EvalRubBidIndPresenter presenter;
    @BindView(R.id.recyclerCome)
    RecyclerView recyclerCome;
    @BindView(R.id.btnAddCome)
    FloatingActionButton btnAddCome;
    Unbinder unbinder;
    ComentarioPredeAdapter comentarioPredeAdapter;

    @Override
    public void onAttach(EvalRubBidIndPresenter evalRubBidIndPresenter) {
        presenter = evalRubBidIndPresenter;
    }

    @Override
    public void showListComentarios(List<MensajeUi> mensajeUiList) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_comentario_pred, container, false);
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intAdapter();
    }

    private void intAdapter() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
