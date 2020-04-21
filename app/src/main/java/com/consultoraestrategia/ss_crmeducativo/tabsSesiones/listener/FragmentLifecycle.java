package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.listener;

import android.os.Bundle;

/**
 * Created by SCIEV on 19/12/2017.
 */

public interface FragmentLifecycle {

    void onPauseFragment();
    void onResumeFragment();
    void onResumeFragment(Bundle bundle);
}
