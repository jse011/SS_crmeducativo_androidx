package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable;

import android.content.Context;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 27/11/2017.
 */
public class HeaderCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.HeaderBinder<RubroEvaluacionUi> {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtitle)
    TextView txtSubtitle;
    @BindView(R.id.root)
    LinearLayout root;
    private Context context;

    public HeaderCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public HeaderCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rubrbid_headers, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindHeader(RubroEvaluacionUi item, int column) {
        @ColorRes int bgColor = R.color.md_blue_500;
        String title = "Destacado";
        String subtitle = "20";
        switch (column) {
            case 1:
                bgColor = R.color.md_green_500;
                title = "Previsto";
                subtitle = "15";
                break;
            case 2:
                bgColor = R.color.md_orange_500;
                title = "En Proceso";
                subtitle = "10";
                break;
            case 3:
                bgColor = R.color.md_pink_300;
                title = "En Inicio";
                subtitle = "5";
                break;
        }
        root.setBackgroundColor(ContextCompat.getColor(context, bgColor));
        txtTitle.setText(title);
        txtSubtitle.setText(subtitle);
    }

}

