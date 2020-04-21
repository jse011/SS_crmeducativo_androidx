package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.MensajePredeterminadoPresenter;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.MensajePredeterminadoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.MensajePredeterminadoView;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.adapters.MensajePredeterminadoAdapter;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.ui.CreateMensPredeActivity;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.MensajePredeterminadoRepository;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.local.MensajePredeterminadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.remote.MensajePredeterninadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.MsgConfirm;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public class DialogMensajePredeterminado extends DialogFragment
        implements MensajePredeterminadoAdapter.OnMensajePredeterminadoClickedListener,
        MensajePredeterminadoView {


    private static final String TAG = DialogMensajePredeterminado.class.getSimpleName();
    public static final String EXTRA_ID_INTENCION = "id_intencion_selecetd";
    @BindView(R.id.rvMensajesPret)
    RecyclerView rvMensajesPret;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    Unbinder unbinder;


    MensajePredeterminadoPresenter presenter;

    @BindView(R.id.btnSelector)
    ImageButton btnSelector;
    private static onMensajePredeterminadoListener mensajePredeterminadoSelected;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.txtTipoMensajeSelected)
    TextView txtTipoMensajeSelected;

    public static DialogMensajePredeterminado newInstace(onMensajePredeterminadoListener mensajePredeterminadoSelected, int idIntencionSelected) {
        DialogMensajePredeterminado.mensajePredeterminadoSelected = mensajePredeterminadoSelected;
        DialogMensajePredeterminado dialogMensajePredeterminado = new DialogMensajePredeterminado();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID_INTENCION, idIntencionSelected);
        dialogMensajePredeterminado.setArguments(bundle);
        return dialogMensajePredeterminado;
    }


    @OnClick({R.id.btnSelector, R.id.fabAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSelector:
                presenter.getListTiposMensajePredeterninado();
//
                break;
            case R.id.fabAdd:
                presenter.onFabAddClicked();

                break;
        }
    }

    public interface onMensajePredeterminadoListener {
        void onClickMensajeSelectedListener(MensajePredeterminadoUI mensajePredeterminadoUI);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_mensaje_predeterminado, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupPresenter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void setupPresenter() {
        presenter = new MensajePredeterminadoPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                new GetMensajePredeterninadoUIListUseCase(
                        MensajePredeterminadoRepository.getInstance(
                                new MensajePredeterminadoLocalDataSource(),
                                new MensajePredeterninadoRemoteDataSource()))
        );
        setPresenter(presenter);
    }

    @Override
    public void setPresenter(MensajePredeterminadoPresenter presenter) {
        presenter.setExtras(getArguments());
        presenter.attachView(this);
        presenter.onCreate();
    }

    MensajePredeterminadoAdapter adapter;

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
//            dialog.requestWindowFeature(STYLE_NO_TITLE);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    public void onItemClickListener(MensajePredeterminadoUI mensajePredeterminadoUI) {
        mensajePredeterminadoSelected.onClickMensajeSelectedListener(mensajePredeterminadoUI);
        getDialog().dismiss();
    }

    @Override
    public void onClickEditarListener(MensajePredeterminadoUI mensajePredeterminadoUI) {
        presenter.onClickEditarMensajePred(mensajePredeterminadoUI);
    }

    @Override
    public void showEditMensajePredActivity(MensajePredeterminadoUI mensajePredeterminadoUI, int idIntencionSelected) {
        CreateMensPredeActivity.launchActivity(getContext(), mensajePredeterminadoUI, idIntencionSelected);
    }

    @Override
    public void onClickDeleteListener(final MensajePredeterminadoUI mensajePredeterminadoUI) {
        MsgConfirm(getActivity(), "Eliminado mensaje Predeterminado...",
                "¿Esta seguro que desea Eliminar?, \nSe Agregará a al Opcion  de Eliminados",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.deleteLogicMensajePred(mensajePredeterminadoUI);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void onItemAlreadyDeleted() {
        showMessage("Este mensaje Esta Eliminado, Restaurar para Usarlo");
    }

    @Override
    public void onRestoreItem(final MensajePredeterminadoUI mensajePredeterminadoUI) {
        MsgConfirm(getActivity(), "Restaurar mensaje Predeterminado...",
                "¿Esta seguro que desea Restaurar este Elemento?",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.restoreItemMensajePred(mensajePredeterminadoUI);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


    }

    @Override
    public void showListSingleChooser(String dialogTitle, final List<String> items, int positionSelected) {
        if (items.isEmpty()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogAppTheme);
        int size = items.size();
        final CharSequence[] singleItems = new CharSequence[size];

        for (int i = 0; i < size; i++) {
            singleItems[i] = items.get(i);
        }
        if (positionSelected >= items.size()) {
            positionSelected = -1;
        }
        builder.setTitle(dialogTitle)
                .setSingleChoiceItems(singleItems, positionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setSingleChoiceItems onClickTipoComportamiento i: " + which);
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setPositiveButton onClickTipoComportamiento i: " + which);
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            String objectSelected = items.get(selectedPosition);
                            presenter.onSingleItemSelected(objectSelected, selectedPosition);
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(getTag(), "setNegativeButton onClickTipoComportamiento i: " + which);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void showMessage(String msj) {
        Snackbar.make(txtTipoMensajeSelected.getRootView(), msj, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void removeItemMensajePredRV(MensajePredeterminadoUI mensajePredeterminadoUI) {
        adapter.delete(mensajePredeterminadoUI);
    }

    @Override
    public void showCreateMensajePredActivity(int idIntencionSelected) {
        CreateMensPredeActivity.launchActivity(getContext(), null, idIntencionSelected);
    }


    @Override
    public void showListaMensajesPredeterminador(List<MensajePredeterminadoUI> mensajePredeterminadoUIList) {
        if (adapter == null) {
            adapter = new MensajePredeterminadoAdapter(rvMensajesPret, new ArrayList<MensajePredeterminadoUI>(), this);
            rvMensajesPret.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMensajesPret.setAdapter(adapter);
        }

        adapter.setMensajePredeterminadoUIList(mensajePredeterminadoUIList);
    }

    @Override
    public void setOpcionSelected(String objectSelected) {

        txtTipoMensajeSelected.setText(objectSelected);
    }
}

