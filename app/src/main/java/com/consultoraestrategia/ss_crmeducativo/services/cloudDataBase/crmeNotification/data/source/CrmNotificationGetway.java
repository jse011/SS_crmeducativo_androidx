package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.data.source;

import java.util.HashMap;

public interface CrmNotificationGetway {
    boolean savePaquete(String paquete, int usuario);
    boolean updatePaquete(String paquete, int usuario, long dataTime);
    long getFechaPaquete(String paquete, int usuario);
    HashMap<Long, String> getPaquete(int usuario);
}
