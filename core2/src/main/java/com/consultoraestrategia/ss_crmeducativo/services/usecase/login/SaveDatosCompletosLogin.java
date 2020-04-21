package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.services.cache.CacheImageRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class SaveDatosCompletosLogin extends UseCaseSincrono<SaveDatosCompletosLogin.RequestValues, SaveDatosCompletosLogin.ResponseValue> {
    private SEDatosCompletosLoginRepository repository;
    private CacheImageRepository cacheImageRepository;

    public SaveDatosCompletosLogin(SEDatosCompletosLoginRepository repository, CacheImageRepository cacheImageRepository) {
        this.repository = repository;
        this.cacheImageRepository = cacheImageRepository;
    }

    @Override
    public void execute(final RequestValues request, final Callback<ResponseValue> callback) {
        repository.save(request.getSeDatosCompletosLogin(), new SEDatosCompletosLoginDataSource.SuccessCallBack() {
            @Override
            public void onResponse(boolean success) {
                if(success){
                    saveCacheImageValorTipoNota(request.getSeDatosCompletosLogin());
                    saveCacheImageBanner(request.getSeDatosCompletosLogin());
                    saveCacheImagePersona(request.getSeDatosCompletosLogin());
                    saveCacheImageDesempenioId(request.getSeDatosCompletosLogin());

                    callback.onResponse(true, new ResponseValue(success));
                }else {
                    callback.onResponse(false, new ResponseValue(success));
                }

            }
        });


    }

    private void saveCacheImageDesempenioId(SEDatosCompletosLogin seDatosCompletosLogin) {
        Set<String> urlImageList = new LinkedHashSet<>();
        GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio = seDatosCompletosLogin.getBeDatosSilaboEventoEnvio();
        if(beDatosSilaboEventoEnvio!=null){
            for (DesempenioIcd desempenioIcd : beDatosSilaboEventoEnvio.getRel_desempenio_icd() ){
                urlImageList.add(desempenioIcd.getUrl());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList), 35);
    }

    private void saveCacheImageValorTipoNota(SEDatosCompletosLogin seDatosCompletosLogin){
        Set<String> urlImageList = new LinkedHashSet<>();
        BEDatosEnvioTipoNota beDatosEnvioTipoNota = seDatosCompletosLogin.getBeDatosEnvioTipoNota();
        if(beDatosEnvioTipoNota!=null){
            for (ValorTipoNotaC valorTipoNotaC : beDatosEnvioTipoNota.getValorTipoNota() ){
                urlImageList.add(valorTipoNotaC.getIcono());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList), 35);
    }

    private void saveCacheImageBanner(SEDatosCompletosLogin seDatosCompletosLogin){
        Set<String> urlImageList = new LinkedHashSet<>();
        BEObtenerDatosLogin beObtenerDatosLogin = seDatosCompletosLogin.getBeObtenerDatosLogin();
        if(beObtenerDatosLogin!=null){
            for (ParametrosDisenio parametrosDisenio : beObtenerDatosLogin.getObtener_parametros_disenio() ){
                urlImageList.add(parametrosDisenio.getPath());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList), 100);
    }

    private void saveCacheImagePersona(SEDatosCompletosLogin seDatosCompletosLogin){
        Set<String> urlImageList = new LinkedHashSet<>();
        BEObtenerDatosLogin beObtenerDatosLogin = seDatosCompletosLogin.getBeObtenerDatosLogin();
        if(beObtenerDatosLogin!=null){
            for (Persona persona : beObtenerDatosLogin.getPersonas() ){
                urlImageList.add(persona.getFoto());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList), 35);
    }

    public static class RequestValues implements UseCase.RequestValues{
        private SEDatosCompletosLogin seDatosCompletosLogin;

        public RequestValues() {
        }

        public SEDatosCompletosLogin getSeDatosCompletosLogin() {
            return seDatosCompletosLogin;
        }

        public void setSeDatosCompletosLogin(Object o) {
            if(this.seDatosCompletosLogin == null){
                this.seDatosCompletosLogin= new SEDatosCompletosLogin();
            }
           if(o instanceof BEDatosCargaAcademica){
               seDatosCompletosLogin.setBeDatosCargaAcademica((BEDatosCargaAcademica)o);
           }else if(o instanceof BEDatosEnvioAsistencia){
               seDatosCompletosLogin.setBeDatosEnvioAsistencia((BEDatosEnvioAsistencia)o);
           }else if(o instanceof BEDatosEnvioGrupo){
               seDatosCompletosLogin.setBeDatosEnvioGrupo((BEDatosEnvioGrupo)o);
           }else if(o instanceof BEDatosEnvioHorario){
               seDatosCompletosLogin.setBeDatosEnvioHorario((BEDatosEnvioHorario)o);
           }else if(o instanceof BEDatosEnvioMensajeria){
               seDatosCompletosLogin.setBeDatosEnvioMensajeria((BEDatosEnvioMensajeria)o);
           }else if(o instanceof BEDatosEnvioTipoNota){
               seDatosCompletosLogin.setBeDatosEnvioTipoNota((BEDatosEnvioTipoNota)o);
           }else if(o instanceof BEDatosEvaluacionResultado){
               seDatosCompletosLogin.setBeDatosEvaluacionResultado((BEDatosEvaluacionResultado)o);
           }else if(o instanceof BEDatosRubroEvaluacionProceso){
               seDatosCompletosLogin.setBeDatosRubroEvaluacionProceso((BEDatosRubroEvaluacionProceso)o);
           }else if(o instanceof GEDatosSilaboEventoEnvio){
               seDatosCompletosLogin.setBeDatosSilaboEventoEnvio((GEDatosSilaboEventoEnvio)o);
           }else if(o instanceof BEObtenerDatosLogin){
               seDatosCompletosLogin.setBeObtenerDatosLogin((BEObtenerDatosLogin)o);
           }
        }

        public Boolean isfullRequest(){
            if(seDatosCompletosLogin.getBeDatosCargaAcademica() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioAsistencia() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioGrupo() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioHorario() == null||
                    seDatosCompletosLogin.getBeDatosEnvioMensajeria() == null||
                    seDatosCompletosLogin.getBeDatosEnvioTipoNota() == null||
                    //seDatosCompletosLogin.getBeDatosEvaluacionResultado() == null||
                    seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso() == null||
                    seDatosCompletosLogin.getBeDatosSilaboEventoEnvio() == null||
                    seDatosCompletosLogin.getBeObtenerDatosLogin() == null
                    )return false;
            return true;
        }
        public Boolean isfullRequestImportacion(){
            if(seDatosCompletosLogin.getBeDatosEnvioAsistencia() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioGrupo() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioMensajeria() == null||
                    seDatosCompletosLogin.getBeDatosEnvioTipoNota() == null||
                    seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso() == null||
                    seDatosCompletosLogin.getBeDatosSilaboEventoEnvio() == null
                    )return false;
            return true;
        }

        @Override
        public String toString() {
            return "RequestValues{" +
                    seDatosCompletosLogin.toString() +
                    '}';
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private boolean sucesses;

        public ResponseValue(boolean sucesses) {
            this.sucesses = sucesses;
        }

        public boolean isSucesses() {
            return sucesses;
        }


    }

}
