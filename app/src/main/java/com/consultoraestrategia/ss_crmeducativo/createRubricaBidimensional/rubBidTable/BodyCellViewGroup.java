package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

/**
 * Created by @stevecampos on 27/11/2017.
 */

public class BodyCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.BodyBinder<AlumnoUi> {

    private static final String TAG = BodyCellViewGroup.class.getSimpleName();
    private Context context;
    public TextView txtContent;
    public View root;

    public BodyCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public BodyCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.item_rubrbid_content, this, true);
        txtContent = findViewById(R.id.txtContent);
        root = findViewById(R.id.root);
    }

    @Override
    public void bindBody(AlumnoUi item, int row, int column) {
        /*List<NotaUi> notaUiList = item.getNotaUiList();
        Log.d(TAG, "position: " + column);
        if (notaUiList.size() > column) {
            NotaUi notaUi = notaUiList.get(column);
            txtResultado.setText(notaUi.getNota());
        }*/
    }

    @Override
    public void destroyView() {
        root = null;
        txtContent =null;
        context = null;
    }
}