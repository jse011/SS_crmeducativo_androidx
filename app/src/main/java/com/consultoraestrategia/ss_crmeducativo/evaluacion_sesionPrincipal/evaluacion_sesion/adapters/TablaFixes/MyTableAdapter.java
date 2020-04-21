package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.TablaFixes;

import android.content.Context;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupImagen;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupSelectorNumerico;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupTecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupTexto;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.adapters.BaseTableAdapter;

import java.util.List;


/**
 * Created by @stevecampos on 16/10/2017.
 */

public class MyTableAdapter {

    private Context context;
    private String firstHeader;
    private List<NotaUi> header;
    private List<AlumnosEvaluacionUi> body;
    private NotasListener listener;
    private TableFixAdapter adapter;
    private RubroEvaluacionUi.TipoNota tipoNota;
    private boolean disabledEval;
    private static final String TAG = MyTableAdapter.class.getSimpleName();


    public interface NotasListener {
        void onClickNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi);
        void onClickSelectorNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi);
        void onClickTecladoNumerico(AlumnosEvaluacionUi item, NotaUi nota);
        void onClickAlumno(AlumnosEvaluacionUi item);
        void onLongClickNota(AlumnosEvaluacionUi item, NotaUi nota);
        void onClickBtnPublicar(AlumnosEvaluacionUi item, OptionPublicar optionPublicar);
        void onClickBtnComentario(AlumnosEvaluacionUi item, OptionComentario comentario);
    }

    public MyTableAdapter(Context context) {
        this.context = context;
    }

    public void setListener(NotasListener listener, boolean disabledEval) {
        this.listener = listener;
        this.disabledEval = disabledEval;
        Log.d(TAG, "set disabledEval :" + disabledEval);
    }

    public BaseTableAdapter getInstance(String titulo, List<AlumnosEvaluacionUi> alumnosEvaluacionUis, List<NotaUi> notaUis) {
        firstHeader = titulo;
        body = alumnosEvaluacionUis;
        header = notaUis;
            adapter = new TableFixAdapter(context, header.size(), notaUis);
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

        TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, BodyCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, BodyCellViewGroup>() {
            @Override
            public void onClickItem(AlumnosEvaluacionUi item, BodyCellViewGroup bodyCellViewGroup1, int row, int column) {
                Log.d(TAG, "disabledEval "+disabledEval);
                if(disabledEval)return;
                if(!item.isVigente())return;
                List<NotaUi> notaUiList = item.getNotaUis();
                if (notaUiList.size() > column) {
                    NotaUi nota = item.getNotaUis().get(column);
                    if(item.getFrameLayout() != null && item.getFrameLayout() instanceof BodyCellViewGroup ){
                        BodyCellViewGroup bodyCellViewGroupHold = (BodyCellViewGroup) item.getFrameLayout();
                        if(nota.equals(bodyCellViewGroupHold.getNotaUi())){
                            if (listener != null)listener.onLongClickNota(item,nota);
                            return;
                        }
                    }
                    bodyCellViewGroup1.clickViewHold(item.getFrameLayout());
                    bodyCellViewGroup1.clickView(item,nota);

                    if (listener != null) {
                        if(bodyCellViewGroup1 instanceof BodyCellViewGroupSelectorNumerico){
                            listener.onClickSelectorNota(item,nota);
                        }else if(bodyCellViewGroup1 instanceof BodyCellViewGroupTecladoNumerico){
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

        TableFixHeaderAdapter.LongClickListener<AlumnosEvaluacionUi, BodyCellViewGroup> longClickListenerBody = new TableFixHeaderAdapter.LongClickListener<AlumnosEvaluacionUi, BodyCellViewGroup>() {
            @Override
            public void onLongClickItem(AlumnosEvaluacionUi item, BodyCellViewGroup bodyCellViewGroup, int row, int column) {
                if(disabledEval)return;
                if(!item.isVigente())return;
                List<NotaUi> notaUiList = item.getNotaUis();
                if (notaUiList.size() > column) {
                    NotaUi nota = item.getNotaUis().get(column);
                    if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
                    bodyCellViewGroup.clickViewHold(item.getFrameLayout());
                    bodyCellViewGroup.clickView(item,nota);
                        if(bodyCellViewGroup instanceof BodyCellViewGroupImagen ||
                                bodyCellViewGroup instanceof BodyCellViewGroupTexto){
                            if(listener != null)listener.onClickNota(item,nota);
                        }

                }
            }
        };

        TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, FirstBodyCellViewGroup> clickListenerFirstBody = new TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, FirstBodyCellViewGroup>() {
            @Override
            public void onClickItem(AlumnosEvaluacionUi item, FirstBodyCellViewGroup viewGroup, int row, int column) {
                if(!item.isVigente())return;
                listener.onClickAlumno(item);
            }
        };

        TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, SectionCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<AlumnosEvaluacionUi, SectionCellViewGroup>() {
            @Override
            public void onClickItem(AlumnosEvaluacionUi item, SectionCellViewGroup viewGroup, int row, int column) {

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

    public void notifyChange(){
        adapter.notifyDataSetChanged();
    }

    public void setTypeBody(RubroEvaluacionUi.TipoNota tipoNota){
        this.tipoNota = tipoNota;
        if(adapter != null){
            adapter.setTypeBody(tipoNota);
        }

    }

    public void changeAvanzado(){
        adapter.changeAvanzado();
        notifyChange();
    }

    public void changeSimple(){
        adapter.changeSimple();
        notifyChange();
    }
}
