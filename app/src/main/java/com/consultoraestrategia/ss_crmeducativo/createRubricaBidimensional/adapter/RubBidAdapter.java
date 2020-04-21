package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter;

import android.content.Context;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.BodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 27/11/2017.
 */

public class RubBidAdapter extends TableFixHeaderAdapter<
        String, FirstHeaderCellViewGroup,
        RubroEvaluacionUi, HeaderCellViewGroup,
        AlumnoUi,
        FirstBodyCellViewGroup,
        BodyCellViewGroup,
        SectionCellViewGroup> {
    private Context context;


    private int rowCount;

    public RubBidAdapter(Context context, int rowCount) {
        super(context);
        this.context = context;
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
        return new BodyCellViewGroup(context);
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
                width = (int) context.getResources().getDimension(R.dimen.rub_bid_firstheader_width);
            } else {
                width = (int) context.getResources().getDimension(R.dimen.rub_bid_header_width);
            }
            widths.add(width);
        }

        return widths;
    }

    @Override
    protected int getHeaderHeight() {
        return (int) context.getResources().getDimension(R.dimen.rub_bid_header_height);
    }

    @Override
    protected int getSectionHeight() {
        return (int) context.getResources().getDimension(R.dimen.rub_bid_body_height);
    }

    @Override
    protected int getBodyHeight() {
        return (int) context.getResources().getDimension(R.dimen.rub_bid_body_height);
    }

    @Override
    protected boolean isSection(List<AlumnoUi> list, int i) {
        return false;
    }
}

