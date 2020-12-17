package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CreativeUi;
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.kaelaela.verticalviewpager.VerticalViewPager;

public class ContenidoTutorialCentProcesamiento extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.view_pager_tutorial)
    ViewPager viewPagerTutorial;
    @BindView(R.id.tab_registro_eval)
    CardView tabRegistroEval;
    @BindView(R.id.linea_registro_eval)
    View lineaRegistroEval;

    @BindView(R.id.tab_proceso_notas)
    CardView tabProcesoNotas;
    @BindView(R.id.linea_proceso_notas)
    View lineaProcesoNotas;

    @BindView(R.id.tab_guardar_notas)
    CardView tabGuardarNotas;
    @BindView(R.id.linea_guardar_notas)
    View lineaGuardarNotas;

    @BindView(R.id.tab_proteger_notas)
    CardView tabProtegerNotas;
    @BindView(R.id.linea_proteger_notas)
    View lineaProtegerNotas;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cont_tutorial_cent_procesamiento, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
        setupViewPager();
    }

    private void setupViewPager() {
        cambiarColorLine(0);
        viewPagerTutorial.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cambiarColorLine(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void cambiarColorLine(int posicion){
        lineaRegistroEval.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_grey_300));
        lineaProcesoNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_grey_300));
        lineaGuardarNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_grey_300));
        lineaProtegerNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_grey_300));

        switch (posicion){
            case 0:
                lineaRegistroEval.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_blue_700));
                break;
            case 1:
                lineaProcesoNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_blue_700));
                break;
            case 2:
                lineaGuardarNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_blue_700));
                break;
            case 3:
                lineaProtegerNotas.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.md_blue_700));
                break;
        }
    }

    private void setupAdapter() {
        viewPagerTutorial.setAdapter(new ContenidoViewPager());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public  class ContenidoViewPager extends PagerAdapter{

        String[] titles = {"Banner","Registro de evaluación"/*, "Procesar mis notas", "Guardar mis notas", "Proteger mis notas"*/};
        PagerTutorial[] layouts = {new BannerPager(), new RegistroEvaluacionPager()/*, new ProcesarNotasPager(), new GuardarNotasPager(), new ProtegerNotasPager()*/};

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewGroup layout = layouts[position].bind(container);
            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public static class NatureCreativePagerAdapter implements CreativePagerAdapter {

        public List<CreativeUi> creativeUiList;
        public Context context;

        public NatureCreativePagerAdapter(Context context, List<CreativeUi> creativeUiList) {
            this.context = context;
            this.creativeUiList = creativeUiList;
        }

        @Override
        public int getCount() {
            return creativeUiList.size();
        }

        @NotNull
        @Override
        public View instantiateContentItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
            View headerRoot = layoutInflater.inflate(R.layout.item_contenido_header_tutorial, viewGroup, false);
            TextView titulo = headerRoot.findViewById(R.id.titulo);

            titulo.setText(String.valueOf(i+1));
            return headerRoot;

        }

        @NotNull
        @Override
        public View instantiateHeaderItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
            View contentRoot = layoutInflater.inflate(R.layout.item_contenido_contenido_tutorial, viewGroup, false);
            //contentRoot.findViewById(R.id.)
            return contentRoot;
        }

        @Override
        public boolean isUpdatingBackgroundColor() {
            return false;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public Bitmap requestBitmapAtPosition(int i) {
            return null;
        }

    }

    private interface PagerTutorial{
        ViewGroup bind(ViewGroup container);
    }

    public class BannerPager implements PagerTutorial {
        @BindView(R.id.btn_comensar)
        Button btnComensar;

        public ViewGroup bind(ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_fragment_banner_cont_tutorial_cent_procesamiento, container, false);
            ButterKnife.bind(this, layout);
            init();
            return layout;
        }

        private void init(){
            btnComensar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPagerTutorial.setCurrentItem(1);
                }
            });
        }


    }

    public class RegistroEvaluacionPager implements PagerTutorial {
        @BindView(R.id.scroll)
        NestedScrollView scroll;
        @BindView(R.id.btn_salir)
        Button btnSalir;

        public ViewGroup bind(ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_fragment_reg_eval_cont_tutorial_cent_procesamiento, container, false);
            ButterKnife.bind(this, layout);
            init();
            return layout;
        }

        private void init(){
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }

        
    }

    private static class ProcesarNotasPager implements PagerTutorial{

        public ViewGroup bind(ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_fragment_procesar_notas_cont_tutorial_cent_procesamiento, container, false);

            return layout;
        }
    }

    private static class GuardarNotasPager implements PagerTutorial {

        public ViewGroup bind(ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_fragment_guardar_notas_cont_tutorial_cent_procesamiento, container, false);

            return layout;
        }
    }

    private static class ProtegerNotasPager implements PagerTutorial {
        public ViewGroup bind(ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_fragment_protegar_notas_cont_tutorial_cent_procesamiento, container, false);

            return layout;
        }
    }


    @OnClick({R.id.tab_registro_eval, R.id.tab_proceso_notas, R.id.tab_guardar_notas, R.id.tab_proteger_notas, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_registro_eval:
                viewPagerTutorial.setCurrentItem(0);
                break;
            case R.id.tab_proceso_notas:
                viewPagerTutorial.setCurrentItem(1);
                break;
            case R.id.tab_guardar_notas:
                viewPagerTutorial.setCurrentItem(2);
                break;
            case R.id.tab_proteger_notas:
                viewPagerTutorial.setCurrentItem(3);
                break;
            case R.id.btn_back:
                getActivity().onBackPressed();
                break;
        }

    }


}
