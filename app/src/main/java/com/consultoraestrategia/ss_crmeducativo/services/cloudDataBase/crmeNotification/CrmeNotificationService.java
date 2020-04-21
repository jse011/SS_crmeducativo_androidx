package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification;

import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.entities.Paquete;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

public interface CrmeNotificationService {

    void setListnerFirebase(String referenciaFirebase, String nodo);

    void setListnerSingleFirebase(String referenciaFirebase, String nodo, Paquete[] paquetes);

    void ejecutarbedatoscalendarioperiodo(BEVariables beVariables);

    void ejecutarbedatostiponota(BEVariables beVariables);

    void ejecutarbedatosunidadprendizaje(BEVariables beVariables);

    void ejecutarstrategylogin();

    void ejecutarbedatossesionaprendizaje(BEVariables beVariables);

    void ejecutargedatosenvioasistencia(BEVariables beVariables);

    void ejecutargedatosrubroevaluacionproceso(BEVariables beVariables);

    void ejecutarbedatostarearecursos(BEVariables beVariables);

    void ejecutarcutarAlumnoVigencia(BEVariables beVariables);

    void subscribeToTopic(String id);

    void unsubscribeFromTopic(String id);

    void ejecutarcutarCalendarioPeriodo(BEVariables beVariables);
}
