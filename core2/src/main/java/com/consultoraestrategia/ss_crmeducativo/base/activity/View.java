package com.consultoraestrategia.ss_crmeducativo.base.activity;

import androidx.annotation.ColorRes;


/**
 * Created by @stevecampos on 15/01/2018.
 */

public interface View extends BaseView<Presenter> {
    void changeBackground(@ColorRes int color);
}
