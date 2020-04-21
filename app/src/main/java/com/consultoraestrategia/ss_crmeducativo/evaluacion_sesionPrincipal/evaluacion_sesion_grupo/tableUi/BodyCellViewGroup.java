package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.Unbinder;


/**
 * Created by @stevecampos on 16/10/2017.
 */

public abstract class BodyCellViewGroup extends FrameLayout  implements
        TableFixHeaderAdapter.BodyBinder<Object> {

    private static final String TAG = BodyCellViewGroup.class.getSimpleName();
    public Context context;
    public View root;
    protected Unbinder unbinder;

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

    public abstract void init();

    public abstract void clickViewHold(FrameLayout frameLayout);

    public abstract void clickView(Object row, Object colum);

    public abstract NotaUi getNotaUi();

    @Override
    public void destroyView() {
        if(unbinder!=null)unbinder.unbind();
    }
}
