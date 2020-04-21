package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.ComentarioCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.EnptyCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.ImageCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.PublicarCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.SelectorNumericoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TecladoNumericoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TextoAvanzadoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TextoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.colum.EmptyColumViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.row.AlumnoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.row.GrupoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.List;

public class TableAdapter extends AbstractTableAdapter<NotaUi, BodyCellViewUi, NotaUi> {
    private static final int ROW_GRUPO = 1, ROW_ALUMNO = 2;
    private static final int CELL_EMPTY = 1, CELL_COMENTARIO = 2, CELL_PUBLICAR = 3, CELL_IMAGEN = 5, CELL_TEXTO_AVANZADO = 6, CELL_TEXTO = 7, CELL_SELECTOR_NUMERICO = 8, CELL_TECLADO_NUMERICO = 9;
    private static final int COLUMN = 1, COLUMN_ALTERNO = 2;
    private boolean diseniotablaAlterno;

    public TableAdapter(Context context) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        if(diseniotablaAlterno){
            return COLUMN_ALTERNO;
        }else {
            return COLUMN;
        }
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        Object o = getRowHeaderRecyclerViewAdapter().getItem(position);
        if(o instanceof AlumnosEvaluacionUi){
            return ROW_ALUMNO;
        }else if(o instanceof GrupoEvaluacionUi){
            return ROW_GRUPO;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        List<NotaUi> notaUiList = (List<NotaUi>) getCellRecyclerViewAdapter().getItem(0);
        NotaUi notaUi = notaUiList.get(position);
        if(notaUi instanceof OptionEmpty){
            return CELL_EMPTY;
        }else if(notaUi instanceof OptionComentario){
            return CELL_COMENTARIO;
        }else if(notaUi instanceof OptionPublicar){
            return CELL_PUBLICAR;
        }else  {
            NotaUi.TipoNota tipoNota = notaUi.getTipoNota();
            switch (tipoNota){
                case IMAGEN:
                    if(diseniotablaAlterno){
                        return CELL_TEXTO_AVANZADO;
                    }else {
                        return CELL_IMAGEN;
                    }
                case TEXTO:
                    if(diseniotablaAlterno){
                        return CELL_TEXTO_AVANZADO;
                    }else {
                        return CELL_TEXTO;
                    }
                case SELECTOR_NUMERICO:
                    return CELL_SELECTOR_NUMERICO;
                case TECLADO_NUMERICO:
                    return CELL_TECLADO_NUMERICO;
                default:
                    return CELL_TEXTO;
            }
        }
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            case CELL_EMPTY:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluacion_sesion_item_option_empty, parent, false);
                return new EnptyCellViewHolder(layout);
            case CELL_COMENTARIO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluacion_sesion_item_option_comentario, parent, false);
                return new ComentarioCellViewHolder(layout);
            case CELL_IMAGEN:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_body_imagen, parent, false);
                return new ImageCellViewHolder(layout);
            case CELL_PUBLICAR:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluacion_sesion_item_option_publicar, parent, false);
                return new PublicarCellViewHolder(layout);
            case CELL_SELECTOR_NUMERICO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_body, parent, false);
                return new SelectorNumericoCellViewHolder(layout);
            case CELL_TEXTO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_body, parent, false);
                return new TextoCellViewHolder(layout);
            case CELL_TEXTO_AVANZADO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_body_avanzado, parent, false);
                return new TextoAvanzadoCellViewHolder(layout);
            case CELL_TECLADO_NUMERICO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_body, parent, false);
                return new TecladoNumericoCellViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluacion_sesion_item_option_empty, parent, false);
                return new EnptyCellViewHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        switch (holder.getItemViewType()){
            case CELL_EMPTY:
                break;
            case CELL_COMENTARIO:
                ComentarioCellViewHolder comentarioCellViewHolder = (ComentarioCellViewHolder)holder;
                OptionComentario optionComentario = (OptionComentario)cellItemModel;
                comentarioCellViewHolder.bind(optionComentario);
                break;
            case CELL_PUBLICAR:
                PublicarCellViewHolder publicarCellViewHolder = (PublicarCellViewHolder)holder;
                OptionPublicar optionPublicar = (OptionPublicar)cellItemModel;
                publicarCellViewHolder.bind(optionPublicar);
                break;
            case CELL_TEXTO:
                TextoCellViewHolder textoCellViewHolder = (TextoCellViewHolder)holder;
                NotaUi notaUi = (NotaUi)cellItemModel;
                textoCellViewHolder.bind(notaUi);
                break;
            case CELL_TEXTO_AVANZADO:
                TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder = (TextoAvanzadoCellViewHolder)holder;
                NotaUi notaUi3 = (NotaUi)cellItemModel;
                textoAvanzadoCellViewHolder.bind(notaUi3);
                break;
            case CELL_IMAGEN:
                ImageCellViewHolder imageCellViewHolder = (ImageCellViewHolder)holder;
                NotaUi notaUi2 = (NotaUi)cellItemModel;
                imageCellViewHolder.bindBody(notaUi2);
                break;
            case CELL_SELECTOR_NUMERICO:
                SelectorNumericoCellViewHolder selectorNumericoCellViewHolder = (SelectorNumericoCellViewHolder)holder;
                NotaUi notaUi4 = (NotaUi)cellItemModel;
                selectorNumericoCellViewHolder.bindBody(notaUi4);
                break;
            case CELL_TECLADO_NUMERICO:
                TecladoNumericoCellViewHolder tecladoNumericoCellViewHolder = (TecladoNumericoCellViewHolder)holder;
                NotaUi notaUi5 = (NotaUi)cellItemModel;
                tecladoNumericoCellViewHolder.bindBody(notaUi5);
                break;
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType){
            case COLUMN_ALTERNO:
                layout = R.layout.evaluacion_sesion_item_column;
                break;
            default:
                layout = R.layout.evaluacion_sesion_item_column_alterno;
                break;
        }

        return new EmptyColumViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {

    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            case ROW_ALUMNO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_firstbody, parent, false);
                return new AlumnoRowViewHolder(layout);
            case ROW_GRUPO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_firstbody, parent, false);
                return new GrupoRowViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_evaluacion_sesion_item_firstbody, parent, false);
                return new AlumnoRowViewHolder(layout);

        }
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        switch (holder.getItemViewType()){
            case ROW_ALUMNO:
                AlumnoRowViewHolder alumnoRowViewHolder = (AlumnoRowViewHolder)holder;
                AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi)rowHeaderItemModel;
                alumnoRowViewHolder.bind(alumnosEvaluacionUi);
                break;
            case ROW_GRUPO:
                GrupoRowViewHolder grupoRowViewHolder = (GrupoRowViewHolder)holder;
                GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)rowHeaderItemModel;
                grupoRowViewHolder.bind(grupoEvaluacionUi);
                break;
        }
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout_rubrica_individual, null);
    }

    public void setDiseniotablaAlterno(boolean diseniotablaAlterno) {
        this.diseniotablaAlterno = diseniotablaAlterno;
    }

    public boolean getDiseniotablaAlterno() {
        return diseniotablaAlterno;
    }

    public void changeTableAvanzado() {
        this.diseniotablaAlterno = true;
        mCellRecyclerViewAdapter.notifyCellDataSetChanged();
        mColumnHeaderRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void changeTableSimple() {
        this.diseniotablaAlterno = false;
        mCellRecyclerViewAdapter.notifyCellDataSetChanged();
        mColumnHeaderRecyclerViewAdapter.notifyDataSetChanged();
    }
}
