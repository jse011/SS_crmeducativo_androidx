package com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.AnioAcadimicoAdapter;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SeleccionAnioAcademico extends DialogFragment implements SeleccionarAnioAcademicoView, AnioAcadimicoAdapter.Listener {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.rc_anio_academico)
    RecyclerView rcAnioAcademico;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    Unbinder unbinder;
    private AnioAcadimicoAdapter anioAcadimicoAdapter;
    private MainPresenter presenter;

    public SeleccionAnioAcademico() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupAdapter() {
        anioAcadimicoAdapter = new AnioAcadimicoAdapter(this);
        rcAnioAcademico.setAdapter(anioAcadimicoAdapter);
        rcAnioAcademico.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    /**
     * Crea un diálogo de alerta sencillo
     *
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_main_seleccionar_anio_academico, null);

        unbinder = ButterKnife.bind(this, view);
        setupAdapter();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    public static SeleccionAnioAcademico newInstance(String title) {
        SeleccionAnioAcademico frag = new SeleccionAnioAcademico();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public void onResume() {
        /*if( getDialog()!=null&& getDialog().getWindow()!=null){
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }*/
        super.onResume();
    }

    @Override
    public void setListAnioAcademico(List<Object> listAnioAcademico) {
        anioAcadimicoAdapter.setList(listAnioAcademico);
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void close() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickAnioSelected(AnioAcademicoUi anioAcademicoUi) {
        presenter.onClickAnioSelected(anioAcademicoUi);
    }

    @OnClick(R.id.btn_cancelar)
    public void onViewClicked() {
        dismiss();
    }
}
