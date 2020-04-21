package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.data.source.CrmNotificationGetway;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.entities.Paquete;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEFireBase;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

import java.util.HashMap;
import java.util.Map;

public class CrmeNotificationPresenterImpl implements CrmeNotificationPresenter {
    private CrmeNotificationService service;
    private String TAG = CrmeNotificationPresenterImpl.class.getSimpleName();
    private CrmNotificationGetway crmNotificationGetway;
    private int usuarioId;
    private Paquete[] modulos;

    public CrmeNotificationPresenterImpl(CrmNotificationGetway crmNotificationGetway) {
        this.crmNotificationGetway = crmNotificationGetway;
    }

    @Override
    public void attachService(CrmeNotificationService crmeNotificationService) {
        this.service = crmeNotificationService;
    }

    @Override
    public void onInicio() {
        Log.d(TAG, "onInicio ");
        try {
            usuarioId = SessionUser.getCurrentUser().getUserId();
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        //initTable();
        unsubscribeFromTopic(usuarioId);
        //comprobarCambios();
        try {
            service.setListnerFirebase("crme-notification", String.valueOf(usuarioId));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void subscribeToTopic(int usuarioId){
        service.subscribeToTopic(String.valueOf(usuarioId));
    }

    private void unsubscribeFromTopic(int usuarioId){
        service.unsubscribeFromTopic(String.valueOf(usuarioId));
    }


    @Override
    public void onChildChanged(String metodo, BEFireBase value) {
        Log.d("CrmeNotificationService", "onChildChanged");
        Log.d("CrmeNotificationService", "metodo :" + metodo);
        Log.d("CrmeNotificationService", value.toString());
        ejecutar(metodo, value);
    }

    @Override
    public void onDestroy() {
        subscribeToTopic(usuarioId);
    }

    @Override
    public void onReceive(TipoImportacion tipoImportacion, boolean success, BEVariables beVariables) {
        crmNotificationGetway.updatePaquete(beVariables.getPaquete(), usuarioId, beVariables.getDataTime());
    }

    @Override
    public void onFinalizarSingleFirebase(HashMap<String, BEFireBase> beFireBaseList) {
        /*
        for (Map.Entry<String, BEFireBase> entry : beFireBaseList.entrySet()) {
            String paquete = entry.getKey();
            BEFireBase beFireBase = entry.getValue();
            long dataTimeLocal = crmNotificationGetway.getFechaPaquete(paquete, beFireBase.getUsuarioId());
            if(dataTimeLocal != -1){
                if(beFireBase.getTimestamp() > dataTimeLocal){
                    ejecutar(paquete, beFireBase);
                }
            }else {
                crmNotificationGetway.savePaquete(paquete, beFireBase.getUsuarioId());
            }

        }*/

    }

    @Override
    public void onCancelled() {
        subscribeToTopic(usuarioId);
    }

    private void ejecutar(String metodo, BEFireBase value){
        Log.d(TAG, value.toString());
        BEVariables beVariables = transfor(metodo,value);
        Log.d(TAG, value.toString());
        Log.d(TAG, Paquete.fromString(metodo)+"");
        switch (Paquete.fromString(metodo)){
            case BEDATOSTAREARECURSOS:
                ejecutarbedatostarearecursos(beVariables);
                break;
            case GEDATOSRUBROEVALUACIONPROCESO:
                ejecutargedatosrubroevaluacionproceso(beVariables);
                break;
            case GEDATOSENVIOASISTENCIA:
                ejecutargedatosenvioasistencia(beVariables);
                break;
            case BEDATOSSESIONAPRENDIZAJE:
                ejecutarbedatossesionaprendizaje(beVariables);
                break;
            case STRATEGYLOGIN:
                ejecutarstrategylogin();
                break;
            case BEDATOSUNIDADPRENDIZAJE:
                ejecutarbedatosunidadprendizaje(beVariables);
                break;
            case BEDATOSTIPONOTA:
                ejecutarbedatostiponota(beVariables);
                break;
            case ERROR:
                break;
            case ALUMNOVIGENCIA:
                ejecutarcutarAlumnoVigencia(beVariables);
                break;
            case BEDATOSCALENDARIOPERIODO:
                ejecutarcutarCalendarioPeriodo(beVariables);
                break;
            default:
                break;
        }
    }

    private void ejecutarcutarCalendarioPeriodo(BEVariables beVariables) {
        Log.d(TAG, "ejecutarcutarCalendarioPeriodo");
        service.ejecutarcutarCalendarioPeriodo(beVariables);
    }

    private void ejecutarcutarAlumnoVigencia(BEVariables beVariables) {
        service.ejecutarcutarAlumnoVigencia(beVariables);
    }

    private void ejecutarbedatoscalendarioperiodo(BEVariables beVariables) {
        service.ejecutarbedatoscalendarioperiodo(beVariables);
    }

    private void ejecutarbedatostiponota(BEVariables beVariables) {
        service.ejecutarbedatostiponota(beVariables);
    }

    private void ejecutarbedatosunidadprendizaje(BEVariables beVariables) {
        service.ejecutarbedatosunidadprendizaje(beVariables);
    }

    private void ejecutarstrategylogin() {
        service.ejecutarstrategylogin();
    }

    private void ejecutarbedatossesionaprendizaje(BEVariables beVariables) {
        service.ejecutarbedatossesionaprendizaje(beVariables);
    }

    private void ejecutargedatosenvioasistencia(BEVariables beVariables) {
        service.ejecutargedatosenvioasistencia(beVariables);
    }

    private void ejecutargedatosrubroevaluacionproceso(BEVariables beVariables) {
        service.ejecutargedatosrubroevaluacionproceso(beVariables);
    }

    private void ejecutarbedatostarearecursos(BEVariables beVariables) {
        service.ejecutarbedatostarearecursos(beVariables);
    }

    private BEVariables transfor(String metodo, BEFireBase beFireBase){

        BEVariables beVariables = new BEVariables();
        beVariables.setUsuarioId(beFireBase.getUsuarioId());
        beVariables.setCalendarioPeriodoId(beFireBase.getCalendarioPeriodoId());
        beVariables.setDocenteId(beFireBase.getDocenteId());
        beVariables.setSilavoEventoId(beFireBase.getSilaboEventoId());
        beVariables.setRubroEvaluacionId(beFireBase.getRubroEvalProcesoId());
        beVariables.setSesionEventoId(beFireBase.getSesionAprendizajeId());
        //beVariables.setGrupoEquipoId(beFireBase.getG());
        beVariables.setUnidadAprendizajeId(beFireBase.getUnidadAprendizajeId());
        beVariables.setProgramaEducativoId(beFireBase.getProgramaEducativoId());
        beVariables.setCargaCursoId(beFireBase.getCargaCursoId());
        beVariables.setAnioAcademicoId(beFireBase.getAnioAcademicoId());
        //beVariables.setAnioAcademicoId(beFireBase.getA);
        beVariables.setPaquete(metodo);
        return beVariables;
    }


}
