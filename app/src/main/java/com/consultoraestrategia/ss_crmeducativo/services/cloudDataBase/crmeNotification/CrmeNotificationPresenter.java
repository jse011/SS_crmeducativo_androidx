package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEFireBase;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

import java.util.HashMap;

public interface CrmeNotificationPresenter {
    void attachService(CrmeNotificationService crmeNotificationService);
    void onInicio();
    void onChildChanged(String metodo, BEFireBase value);
    void onDestroy();
    void onReceive(TipoImportacion tipoImportacion, boolean success, BEVariables beVariables);
    void onFinalizarSingleFirebase(HashMap<String, BEFireBase> beFireBaseList);

    void onCancelled();

}
