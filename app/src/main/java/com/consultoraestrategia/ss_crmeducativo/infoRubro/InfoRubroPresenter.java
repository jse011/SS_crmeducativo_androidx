package com.consultoraestrategia.ss_crmeducativo.infoRubro;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroView;

/**
 * Created by SCIEV on 28/08/2018.
 */

public interface InfoRubroPresenter extends BaseFragmentPresenter<InfoRubroView> {
    void postCantidadLineasDesempenio(int lineCount);

    void onClickVerMasDesempenio();
}
