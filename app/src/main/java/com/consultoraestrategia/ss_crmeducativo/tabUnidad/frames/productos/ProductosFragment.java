package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.productos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.ProductosAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetProductosUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.FragmentListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProductosFragment extends BaseFragment<ProductosView, ProductosPresenter, FragmentListener> implements ProductosView {


    @BindView(R.id.rvProducto)
    RecyclerView rvProducto;
    @BindView(R.id.fragmentProductos)
    ConstraintLayout fragmentProductos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    ProductosAdapter adapter;

    public static ProductosFragment newInstance(Bundle bundle){
        ProductosFragment fragment = new ProductosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return ProductosFragment.class.getSimpleName();
    }

    @Override
    protected ProductosPresenter getPresenter() {
        UnidadRepository repository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        presenter = new ProductosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetProductosUnidad(repository));
        return presenter;
    }

    @Override
    protected ProductosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_productos_unidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new ProductosAdapter(new ArrayList<ProductoUi>());
        rvProducto.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProducto.setAdapter(adapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void showProductosUnidad(List<ProductoUi> productoUis) {
        adapter.setProductoUis(productoUis);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showProductosFragment() {
        fragmentProductos.setVisibility(View.VISIBLE);
    }

    public void hideProductosFragment() {
        fragmentProductos.setVisibility(View.GONE);
    }
}
