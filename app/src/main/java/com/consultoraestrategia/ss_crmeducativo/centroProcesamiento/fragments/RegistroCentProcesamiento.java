package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter.CabeceraTableRegEvalApadter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter.TableRegEvalAdapter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.ColumnTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RowTableRegEvalUi;
import com.evrencoskun.tableview.adapter.recyclerview.CellRecyclerView;
import com.evrencoskun.tableview.TableView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegistroCentProcesamiento extends Fragment {

    @BindView(R.id.tb_evaluacion)
    TableView tbEvaluacion;
    @BindView(R.id.rc_cabecera)
    CellRecyclerView rcCabecera;


    private Unbinder unbinder;
    private TableRegEvalAdapter adapter;
    private CabeceraTableRegEvalApadter adapterCabecera;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_cent_procesamiento, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTableView();
        setupCabecera();
        //setupCabecera2();
       // AlignmentManager.join(AligningRecyclerView.ALIGN_ORIENTATION_HORIZONTAL, tbEvaluacion.getColumnHeaderRecyclerView(), tbEvaluacion.getCellRecyclerView(), rcCabecera, rcCabecera2);
        tbEvaluacion.getHorizontalRecyclerViewListener().setRecyclerViewColumnAdd(rcCabecera);
        rcCabecera.addOnItemTouchListener( tbEvaluacion.getHorizontalRecyclerViewListener());
        rcCabecera.addItemDecoration(tbEvaluacion.getHorizontalItemDecoration());
    }

    private void setupCabecera() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcCabecera.setLayoutManager(linearLayoutManager);
        adapterCabecera = new CabeceraTableRegEvalApadter();
        rcCabecera.setAdapter(adapterCabecera);

        List<CabeceraUi> cabeceraUiList = new ArrayList<>();
        cabeceraUiList.add(new CabeceraUi(CabeceraUi.ALUMNO, 1));
        cabeceraUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA, 5));
        cabeceraUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA, 5));
        cabeceraUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA, 4));
        cabeceraUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA, 1));
        adapterCabecera.setList(cabeceraUiList);
    }

    private void setupTableView() {

        adapter = new TableRegEvalAdapter(getContext());
        tbEvaluacion.setAdapter(adapter);
        //tbEvaluacion.setIgnoreSelectionColors(true);
        //tbEvaluacion.setHasFixedWidth(true);
        //tbEvaluacion.setIgnoreSelectionColors(true);
        tbEvaluacion.setShowHorizontalSeparators(true);
        tbEvaluacion.setShowVerticalSeparators(true);

        //tbEvaluacion.setTableViewListener(this);
        List<ColumnTableRegEvalUi> columnTableRegEvalUis = new ArrayList<>();
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.ALUMNO));
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//1
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//2
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//3
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//4
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//5
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//1
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//2
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//3
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//4
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//5
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//1
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//2
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//3
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.NOTA));//4
        columnTableRegEvalUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.FINAL));

        List<RowTableRegEvalUi> rowTableRegEvalUis = new ArrayList<>();
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());
        rowTableRegEvalUis.add(new RowTableRegEvalUi());

        List<CellTableRegEvalUi> cellTableRegEvalUiList = new ArrayList<>();
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.ALUMNO));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));
        cellTableRegEvalUiList.add(new CellTableRegEvalUi(CellTableRegEvalUi.NOTA));

        List<List<CellTableRegEvalUi>> listList = new ArrayList<>();
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);
        listList.add(cellTableRegEvalUiList);


        adapter.setAllItems(columnTableRegEvalUis, rowTableRegEvalUis, listList);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
