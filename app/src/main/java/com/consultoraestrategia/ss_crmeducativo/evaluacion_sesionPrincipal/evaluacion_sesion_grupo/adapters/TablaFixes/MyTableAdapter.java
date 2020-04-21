package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.TablaFixes;

import android.content.Context;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupImagen;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupTecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupTexto;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.adapters.BaseTableAdapter;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by @stevecampos on 16/10/2017.
 */

public class MyTableAdapter {
    private Context context;
    private String firstHeader;
    private List<NotaUi> header;
    private List<Object> body;
    private NotasListener listener;
    private TableFixAdapter adapter;
    boolean disabledEval;
    private static final String TAG = MyTableAdapter.class.getSimpleName();


    public interface NotasListener {
        void onClickNota(Object itemEvaluacionUi, NotaUi notaUi);
        void onClickAlumnoGrupo(Object itemEvaluacionUi);
        void onClickTecladoNumerico(Object item, NotaUi nota);
        void onLongClickNota(Object item, NotaUi nota);
        void onClickBtnPublicar(Object item, OptionPublicar optionPublicar);
        void onClickBtnComentario(Object item, OptionComentario nota);
    }

    public MyTableAdapter(Context context) {
        this.context = context;
    }

    public void setListener(NotasListener listener, boolean disabledEval) {
        this.listener = listener;
        this.disabledEval = disabledEval;
    }

    public BaseTableAdapter getInstance(String titulo, List<Object> itemEvaluacionUis, List<NotaUi> notaUis) {
        firstHeader = titulo;
        body = itemEvaluacionUis;
        header = notaUis;
        adapter = new TableFixAdapter(context, header.size(), itemEvaluacionUis);
        adapter.setFirstHeader(firstHeader);
        adapter.setHeader(header);
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);
        setListeners(adapter);
        return adapter;
    }

    private void setListeners(final TableFixAdapter adapter) {

        TableFixHeaderAdapter.ClickListener<String, FirstHeaderCellViewGroup> clickListenerFirstHeader = new TableFixHeaderAdapter.ClickListener<String, FirstHeaderCellViewGroup>() {
            @Override
            public void onClickItem(String item, FirstHeaderCellViewGroup viewGroup, int row, int column) {
                //Snackbar.make(viewGroup, "Click on " + item + " (" + row + "," + column + ") ", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<NotaUi, HeaderCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<NotaUi, HeaderCellViewGroup>() {
            @Override
            public void onClickItem(NotaUi notaUi, HeaderCellViewGroup headerCellViewGroup, int row, int column) {

            }
        };

        TableFixHeaderAdapter.ClickListener<Object, BodyCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Object, BodyCellViewGroup>() {
            @Override
            public void onClickItem(Object item, BodyCellViewGroup bodyCellViewGroup1, int row, int column) {

                List<NotaUi> notaUiList = new ArrayList<>();
                if(item instanceof AlumnosEvaluacionUi){
                    AlumnosEvaluacionUi alumnosEvaluacionUi= (AlumnosEvaluacionUi)item;
                    notaUiList = alumnosEvaluacionUi.getNotaUis();
                    if(!alumnosEvaluacionUi.isVigente())return;
                }else if(item instanceof GrupoEvaluacionUi){
                    GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) item;
                    notaUiList = grupoEvaluacionUi.getNotaUis();
                }

                if(disabledEval)return;
                if (notaUiList.size() > column) {
                    BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) item;
                    NotaUi nota = notaUiList.get(column);
                    if(bodyCellViewUi.getFrameLayout() != null && bodyCellViewUi.getFrameLayout() instanceof BodyCellViewGroup ){
                        BodyCellViewGroup bodyCellViewGroupHold = (BodyCellViewGroup) bodyCellViewUi.getFrameLayout();
                        if(nota.equals(bodyCellViewGroupHold.getNotaUi())){
                            if (listener != null)listener.onLongClickNota(item,nota);
                            return;
                        }
                    }
                    bodyCellViewGroup1.clickViewHold(bodyCellViewUi.getFrameLayout());
                    bodyCellViewGroup1.clickView(item,nota);


                    if (listener != null) {
                        if(bodyCellViewGroup1 instanceof BodyCellViewGroupTecladoNumerico){
                            listener.onClickTecladoNumerico(item,nota);
                        }else if(bodyCellViewGroup1 instanceof BodyCellViewGroupPublicar){
                            listener.onClickBtnPublicar(item, (OptionPublicar) nota);
                        }else if(bodyCellViewGroup1 instanceof BodyCellViewGroupComentario){
                            listener.onClickBtnComentario(item, (OptionComentario) nota);
                        }else if(bodyCellViewGroup1 instanceof BodyCellViewGroupEmpty){

                        }else {
                            listener.onClickNota(item,nota);
                        }

                    }
                }
            }
        };

        TableFixHeaderAdapter.LongClickListener<Object, BodyCellViewGroup> longClickListenerBody = new TableFixHeaderAdapter.LongClickListener<Object, BodyCellViewGroup>() {
            @Override
            public void onLongClickItem(Object item, BodyCellViewGroup bodyCellViewGroup, int row, int column) {
                BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) item;

                List<NotaUi> notaUiList = new ArrayList<>();
                if(item instanceof AlumnosEvaluacionUi){
                    AlumnosEvaluacionUi alumnosEvaluacionUi= (AlumnosEvaluacionUi)item;
                    notaUiList = alumnosEvaluacionUi.getNotaUis();
                    if(!alumnosEvaluacionUi.isVigente())return;
                }else if(item instanceof GrupoEvaluacionUi){
                    GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) item;
                    notaUiList = grupoEvaluacionUi.getNotaUis();
                }

                if(disabledEval)return;
                if (notaUiList.size() > column) {
                    NotaUi nota = notaUiList.get(column);
                    if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
                    bodyCellViewGroup.clickViewHold(bodyCellViewUi.getFrameLayout());
                    bodyCellViewGroup.clickView(item,nota);
                    if(bodyCellViewGroup instanceof BodyCellViewGroupImagen ||
                            bodyCellViewGroup instanceof BodyCellViewGroupTexto){
                        if(listener != null)listener.onClickNota(item,nota);
                    }
                }

            }
        };

        TableFixHeaderAdapter.ClickListener<Object, FirstBodyCellViewGroup> clickListenerFirstBody = new TableFixHeaderAdapter.ClickListener<Object, FirstBodyCellViewGroup>() {
            @Override
            public void onClickItem(Object item, FirstBodyCellViewGroup viewGroup, int row, int column) {
                if (listener != null) {
                    if(item instanceof AlumnosEvaluacionUi){
                        AlumnosEvaluacionUi alumnosEvaluacionUi= (AlumnosEvaluacionUi)item;
                        if(!alumnosEvaluacionUi.isVigente())return;
                    }
                    listener.onClickAlumnoGrupo(item);
                }
            }
        };

        TableFixHeaderAdapter.ClickListener<Object, SectionCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Object, SectionCellViewGroup>() {
            @Override
            public void onClickItem(Object item, SectionCellViewGroup viewGroup, int row, int column) {

            }
        };

        adapter.setClickListenerFirstHeader(clickListenerFirstHeader);
        adapter.setClickListenerHeader(clickListenerHeader);
        adapter.setClickListenerFirstBody(clickListenerFirstBody);
        adapter.setClickListenerBody(clickListenerBody);
        adapter.setLongClickListenerBody(longClickListenerBody);
        adapter.setClickListenerSection(clickListenerSection);
    }

    public void updatebody(){
        adapter.setBody(body);
    }

    public void setTypeBody(RubroEvaluacionUi.TipoNota tipoNota){
        if(adapter != null){
            adapter.setTypeBody(tipoNota);
        }

    }

    public void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }


    public void changeAvanzado(){
        adapter.changeAvanzado();
        notifyDataSetChanged();
    }

    public void changeSimple(){
        adapter.changeSimple();
        notifyDataSetChanged();
    }
}
