package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.data.source;

import com.consultoraestrategia.ss_crmeducativo.entities.CrmeNotificacion;
import com.consultoraestrategia.ss_crmeducativo.entities.CrmeNotificacion_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CrmNotificationGetwayImpl implements CrmNotificationGetway{
    @Override
    public boolean savePaquete(String paquete, int usuario) {
        try {
            CrmeNotificacion crmeNotification = new CrmeNotificacion();
            crmeNotification.setUserId(usuario);
            crmeNotification.setPackageId(paquete);
            crmeNotification.setDataTime(new Date().getTime());
            return crmeNotification.save();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePaquete(String paquete, int usuario, long dataTime) {
        try {
            SQLite.update(CrmeNotificacion.class)
                    .set(CrmeNotificacion_Table.dataTime.eq(dataTime))
                    .where(CrmeNotificacion_Table.userId.eq(usuario))
                    .and(CrmeNotificacion_Table.packageId.eq(paquete))
                    .execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long getFechaPaquete(String paquete, int usuario) {
        CrmeNotificacion crmeNotificacion = SQLite.select()
                .from(CrmeNotificacion.class)
                .where(CrmeNotificacion_Table.userId.eq(usuario))
                .and(CrmeNotificacion_Table.packageId.eq(paquete))
                .querySingle();
        if(crmeNotificacion!=null)return crmeNotificacion.getDataTime();

        return -1;
    }

    @Override
    public HashMap<Long, String> getPaquete(int usuario) {
        HashMap<Long, String> paqueteList = new HashMap<>();
        List<CrmeNotificacion> crmeNotificacionList = SQLite.select()
                .from(CrmeNotificacion.class)
                .where(CrmeNotificacion_Table.userId.eq(usuario))
                .queryList();

        for (CrmeNotificacion crmeNotificacion : crmeNotificacionList){
            paqueteList.put(crmeNotificacion.getDataTime(), crmeNotificacion.getPackageId());
        }
        return paqueteList;

    }
}
