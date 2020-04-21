package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.TablaFixes;

import android.content.Context;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupImagen;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupTecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupTexto;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.BodyCellViewGroupTextoAvanzado;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by @stevecampos on 16/10/2017.
 */

public class TableFixAdapter extends TableFixHeaderAdapter<
        String, FirstHeaderCellViewGroup,
        NotaUi, HeaderCellViewGroup,
        Object,FirstBodyCellViewGroup,
        BodyCellViewGroup,SectionCellViewGroup> {

    private Context context;
    private int rowCount;
    public  static final int IMAGEN = 1,TEXTO = 2;
    private int typeBody;
    private RubroEvaluacionUi.TipoNota tipoNota;
    private boolean vistaAbanzada;
    List<Object> rowList;

    public TableFixAdapter(Context context, int rowCount, List<Object> rowList) {
        super(context);
        this.context = context;
        this.rowCount = rowCount;
        this.rowList = rowList;
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
        NotaUi notaUi;
        Object o = rowList.get(row);
        if(o instanceof GrupoEvaluacionUi){
            notaUi = ((GrupoEvaluacionUi)o).getNotaUis().get(column);
        }else {
            notaUi = ((AlumnosEvaluacionUi)o).getNotaUis().get(column);
        }

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
    protected boolean isSection(List<Object> list, int i) {
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
