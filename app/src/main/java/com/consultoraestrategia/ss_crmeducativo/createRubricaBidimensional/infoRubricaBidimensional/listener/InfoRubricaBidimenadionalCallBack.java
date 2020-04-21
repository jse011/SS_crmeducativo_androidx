package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;

import java.util.List;

public interface InfoRubricaBidimenadionalCallBack extends BaseFragmentListener {

    void sendList(List<CriterioEvaluacionUi> criterioEvalUis);
}
