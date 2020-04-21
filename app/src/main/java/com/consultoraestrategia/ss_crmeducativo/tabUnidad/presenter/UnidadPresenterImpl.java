package com.consultoraestrategia.ss_crmeducativo.tabUnidad.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.TipoEnum;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.UnidadItem;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.view.UnidadView;

import java.util.ArrayList;
import java.util.List;

public class UnidadPresenterImpl extends BasePresenterImpl<UnidadView> implements UnidadPresenter{

    private UnidadParametros unidadParametros;
    private TipoEnum oldTipo=TipoEnum.COMPETENCIA_BASE;
    private UnidadItem oldUnidadItem;
    private List<UnidadItem> unidadItemList = new ArrayList<>();

    public UnidadPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return UnidadPresenterImpl.class.getSimpleName();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        unidadParametros = UnidadParametros.clone(extras);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.unidadItemList.clear();
        this.unidadItemList.add(new UnidadItem(true, UnidadItem.Tipo.APRENDIZAJE));
        this.unidadItemList.add(new UnidadItem(false, UnidadItem.Tipo.SITUACION));
        this.unidadItemList.add(new UnidadItem(false, UnidadItem.Tipo.PRODUCTOS));
        this.unidadItemList.add(new UnidadItem(false, UnidadItem.Tipo.RECURSOS));
        this.unidadItemList.add(new UnidadItem(false, UnidadItem.Tipo.EVALUACION));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view!=null)view.onShowAprendizaje(unidadParametros);
            }
        },300);

    }

    @Override
    public void onStart() {
        super.onStart();
        settitulo();
        if(view!=null)view.setListMenu(unidadItemList);
    }

    private void settitulo() {
        String titulo= unidadParametros.getUnidadTitulo();
        if(view!=null)view.showTituloUnidad(titulo);
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onChangeToogle(UnidadItem unidadItem) {
       for (UnidadItem itemUnidadItem: unidadItemList)itemUnidadItem.setSelect(false);
       unidadItem.setSelect(true);
       if (unidadItem != oldUnidadItem){
            if (view!=null)view.onChangeMenuItem();
            if(view!=null)view.onShowFragment(unidadItem, unidadParametros);
        }
        oldUnidadItem = unidadItem;
    }

    @Override
    public void onClickFilter() {
        if (view!=null)view.showListChoose(oldTipo);
    }

    @Override
    public void onChangeFilter(Object o) {
        if (o instanceof TipoEnum){
            oldTipo = (TipoEnum) o;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
