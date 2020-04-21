package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.TablaFixes;

import android.content.Context;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionEmpty;
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
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroupTextoAvanzado;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by @stevecampos on 16/10/2017.
 */

public class TableFixAdapter extends TableFixHeaderAdapter<
        String, FirstHeaderCellViewGroup,
        NotaUi, HeaderCellViewGroup,
        AlumnosEvaluacionUi,FirstBodyCellViewGroup,
        BodyCellViewGroup,SectionCellViewGroup> {


    private static final String TAG = TableFixAdapter.class.getSimpleName();
    private Context context;
    private int rowCount;
    private RubroEvaluacionUi.TipoNota tipoNota;
    private boolean vistaAbanzada;
    private List<NotaUi> cellList;

    public TableFixAdapter(Context context, int rowCount, List<NotaUi> cellList) {
        super(context);
        this.context = context;
        this.rowCount = rowCount;
        this.tipoNota = RubroEvaluacionUi.TipoNota.DEFECTO;
        this.cellList = cellList;
    }

    public void setCellList(List<NotaUi> cellList) {
        this.cellList = cellList;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    protected FirstHeaderCellViewGroup inflateFirstHeader() {
        return new FirstHeaderCellViewGroup(context);
    }

    @Override
    protected HeaderCellViewGroup inflateHeader() {
        return new HeaderCellViewGroup(context);
    }

    @Override
    protected FirstBodyCellViewGroup inflateFirstBody() {
        return new FirstBodyCellViewGroup(context);
    }

    @Override
    protected BodyCellViewGroup inflateBody(int row, int column) {
        NotaUi notaUi = cellList.get(column);
        if(notaUi instanceof OptionEmpty){
            return new BodyCellViewGroupEmpty(context);
        }else if(notaUi instanceof OptionComentario){
            return new BodyCellViewGroupComentario(context);
        }else if(notaUi instanceof OptionPublicar){
            return new BodyCellViewGroupPublicar(context);
        }else {
            switch (tipoNota){
                case IMAGEN:
                    if(vistaAbanzada){
                        return new BodyCellViewGroupTextoAvanzado(context);
                    }else {
                        return new BodyCellViewGroupImagen(context);
                    }
                case TEXTO:
                    if(vistaAbanzada){
                        return new BodyCellViewGroupTextoAvanzado(context);
                    }else {
                        return new BodyCellViewGroupTexto(context);
                    }
                case SELECTOR_NUMERICO:
                    return new BodyCellViewGroupSelectorNumerico(context);
                case TECLADO_NUMERICO:
                    return new BodyCellViewGroupTecladoNumerico(context);
                default:
                    return new BodyCellViewGroupTexto(context);
            }
        }

    }

    @Override
    protected SectionCellViewGroup inflateSection() {
        return new SectionCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        /*Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen.table_firstheader_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
                (int) context.getResources().getDimension(R.dimen.table_header_width),
        };*/
        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i <= rowCount; i++) {
            int width;
            if (i == 0) {//FirstRow
                width = (int) context.getResources().getDimension(R.dimen.table_firstheader_width_eval_session);
            } else {
                width = (int) context.getResources().getDimension(R.dimen.table_header_width_eval_session);
            }
            widths.add(width);
        }

        return widths;
    }

    @Override
    protected int getHeaderHeight() {
        return (int) context.getResources().getDimension(R.dimen.table_header_height_eval_session);
    }

    @Override
    protected int getSectionHeight() {
        return (int) context.getResources().getDimension(R.dimen.table_body_height_eval_session);
    }

    @Override
    protected int getBodyHeight() {
        return (int) context.getResources().getDimension(R.dimen.table_body_height_eval_session);
    }

    @Override
    protected boolean isSection(List<AlumnosEvaluacionUi> list, int i) {
        return false;
    }

    public void setTypeBody(RubroEvaluacionUi.TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public void changeAvanzado(){
        this.vistaAbanzada = true;
        notifyDataSetChanged();
    }

    public void changeSimple(){
        this.vistaAbanzada = false;
        notifyDataSetChanged();
    }
}
