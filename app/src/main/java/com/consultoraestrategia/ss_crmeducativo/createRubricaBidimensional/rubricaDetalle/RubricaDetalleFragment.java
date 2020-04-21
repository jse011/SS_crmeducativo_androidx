package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.TableViewAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 16/02/2018.
 */

public class RubricaDetalleFragment implements DetalleView  {
    public static final String TAG = RubricaDetalleFragment.class.getSimpleName();
    private final Unbinder unbinder;
    private final BaseActivity baseActivity;
    @BindView(R.id.table)
    TableView table;

    //@BindView(R.id.titulo)
    //TextView textTitle;
    @Nullable
    @BindView(R.id.rootTableTitle)
    ConstraintLayout rootTableTitle;
    @BindView(R.id.btnBuscarCampoAccionList)
    ImageView btnBuscarCampoAccionList;
    @BindView(R.id.btnCompetenciaList)
    ImageView btnCompetenciaList;
    @BindView(R.id.btnCampoAccionList)
    ImageView btnCampoAccionList;
    @BindView(R.id.rc_pre_view)
    RecyclerView rcPreView;
    @BindView(R.id.txt_buscar_campo_accion)
    TextView txtBuscarCampoAccion;
    @BindView(R.id.txt_campo_accion)
    TextView txtCampoAccion;
    @BindView(R.id.img_more_indicador)
    ImageView imgMoreIndicador;
    @BindView(R.id.img_more_competencia)
    ImageView imgMoreCompetencia;
    @BindView(R.id.txt_competencia)
    TextView txtCompetencia;



    private RubricaDetalleListener listener;

    public RubricaDetalleFragment(BaseActivity activity, RubricaDetalleListener listener) {
        this.listener = listener;
        this.baseActivity = activity;
        unbinder = ButterKnife.bind(this,activity);
        setupTextTableEmpty();
    }



    private void setupTextTableEmpty() {
        //SpannableString tuTexto= new SpannableString("Tabla vacía para agregar indicadores de click aquí X");
        //tuTexto.setSpan(new ImageSpan(txtTableEmpty.getContext(), R.drawable.ic_search_book), tuTexto.length()-1, tuTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //txtTableEmpty.setText(tuTexto);
    }




    @Override
    public void showTipoNivelSelected(TipoNotaUi tipoNota) {
        Log.d(TAG, "showTipoNotaSelected: " + tipoNota.getTitle());
    }

    @Override
    public void showTipoNivelChooser( List<TipoNotaUi> tipoNotaList) {
        List<Object> items = new ArrayList<>();
        items.addAll(tipoNotaList);
        baseActivity.showListSingleChooser(baseActivity.getString(R.string.fragment_create_rubbid_title_tiponivel), items, -1);
    }

    private TableViewAdapter adapter;

    @Override
    public void showTableview(List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, ITableViewListener listener) {
        if (adapter == null) {
            Log.d(TAG, "showTableview");
            adapter = new TableViewAdapter(table.getContext());
            table.setAdapter(adapter);
            table.setTableViewListener(listener);
            table.setIgnoreSelectionColors(true);
        }
        try {
            List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.RowHeader> rowHeaders =
                    (List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.RowHeader>) (Object) headerList;
            List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.ColumnHeader> columnHeaders =
                    (List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.ColumnHeader>) (Object) rows;
            List<List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell>> cell =
                    (List<List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell>>) (Object) bodyList;
            adapter.setAllItems(rowHeaders, columnHeaders, cell);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCellItem(int x, int y, Cell item) {
        Log.d(TAG, "updateCellItem");
        com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell cell = (com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell) item;

        adapter.changeCellItem(x, y, cell);
    }


    @OnClick({R.id.btnCompetenciaList, R.id.btnCampoAccionList, R.id.btnBuscarCampoAccionList, R.id.txt_competencia, R.id.txt_campo_accion, R.id.txt_buscar_campo_accion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rootTableTitle:
                listener.onTableTitleClicked();
                break;
            case R.id.btnCompetenciaList:
                listener.onBtnDetalleCompetenciaListClicked();
                break;
            case R.id.txt_competencia:
                listener.onBtnDetalleCompetenciaListClicked();
                break;
            case R.id.btnCampoAccionList:
                listener.onBtnDetalleCampoAccionListClicked();
                break;
            case R.id.txt_campo_accion:
                listener.onBtnDetalleCampoAccionListClicked();
                break;
            case R.id.txt_buscar_campo_accion:
                listener.onBtnDetalleBuscarCampoAccionListClicked();
                break;
            case R.id.btnBuscarCampoAccionList:
                listener.onBtnDetalleBuscarCampoAccionListClicked();
                break;
        }
    }

    @Override
    public void refrescar() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void disabledBusquedaCamposAccion() {
        btnBuscarCampoAccionList.setVisibility(View.GONE);
        txtBuscarCampoAccion.setVisibility(View.GONE);
        imgMoreCompetencia.setVisibility(View.GONE);
    }

    @Override
    public void disabledSelectorIndicador() {
        btnCompetenciaList.setVisibility(View.GONE);
        txtCompetencia.setVisibility(View.GONE);
        imgMoreIndicador.setVisibility(View.GONE);
    }

    @Override
    public void disabledSelectorCamposAccion() {
        btnCampoAccionList.setEnabled(false);
    }

    @Override
    public void showTxtTableEmprty() {
        //txtTableEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTxtTableEmprty() {
        //txtTableEmpty.setVisibility(View.GONE);
    }

}
