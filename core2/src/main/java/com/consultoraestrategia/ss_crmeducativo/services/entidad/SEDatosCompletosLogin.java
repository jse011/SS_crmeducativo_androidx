package com.consultoraestrategia.ss_crmeducativo.services.entidad;

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

/**
 * Created by SCIEV on 18/05/2018.
 */

public class SEDatosCompletosLogin {
    private BEDatosCargaAcademica beDatosCargaAcademica;
    private BEDatosEnvioAsistencia beDatosEnvioAsistencia;
    private BEDatosEnvioGrupo beDatosEnvioGrupo;
    private BEDatosEnvioHorario beDatosEnvioHorario;
    private BEDatosEnvioMensajeria beDatosEnvioMensajeria;
    private BEDatosEnvioTipoNota beDatosEnvioTipoNota;
    private BEDatosEvaluacionResultado beDatosEvaluacionResultado;
    private BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso;
    private GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio;
    private BEObtenerDatosLogin beObtenerDatosLogin;

    public SEDatosCompletosLogin() {
    }

    public BEDatosCargaAcademica getBeDatosCargaAcademica() {
        return beDatosCargaAcademica;
    }

    public void setBeDatosCargaAcademica(BEDatosCargaAcademica beDatosCargaAcademica) {
        this.beDatosCargaAcademica = beDatosCargaAcademica;
    }

    public BEDatosEnvioAsistencia getBeDatosEnvioAsistencia() {
        return beDatosEnvioAsistencia;
    }

    public void setBeDatosEnvioAsistencia(BEDatosEnvioAsistencia beDatosEnvioAsistencia) {
        this.beDatosEnvioAsistencia = beDatosEnvioAsistencia;
    }

    public BEDatosEnvioGrupo getBeDatosEnvioGrupo() {
        return beDatosEnvioGrupo;
    }

    public void setBeDatosEnvioGrupo(BEDatosEnvioGrupo beDatosEnvioGrupo) {
        this.beDatosEnvioGrupo = beDatosEnvioGrupo;
    }

    public BEDatosEnvioHorario getBeDatosEnvioHorario() {
        return beDatosEnvioHorario;
    }

    public void setBeDatosEnvioHorario(BEDatosEnvioHorario beDatosEnvioHorario) {
        this.beDatosEnvioHorario = beDatosEnvioHorario;
    }

    public BEDatosEnvioMensajeria getBeDatosEnvioMensajeria() {
        return beDatosEnvioMensajeria;
    }

    public void setBeDatosEnvioMensajeria(BEDatosEnvioMensajeria beDatosEnvioMensajeria) {
        this.beDatosEnvioMensajeria = beDatosEnvioMensajeria;
    }

    public BEDatosEnvioTipoNota getBeDatosEnvioTipoNota() {
        return beDatosEnvioTipoNota;
    }

    public void setBeDatosEnvioTipoNota(BEDatosEnvioTipoNota beDatosEnvioTipoNota) {
        this.beDatosEnvioTipoNota = beDatosEnvioTipoNota;
    }

    public BEDatosEvaluacionResultado getBeDatosEvaluacionResultado() {
        return beDatosEvaluacionResultado;
    }

    public void setBeDatosEvaluacionResultado(BEDatosEvaluacionResultado beDatosEvaluacionResultado) {
        this.beDatosEvaluacionResultado = beDatosEvaluacionResultado;
    }

    public BEDatosRubroEvaluacionProceso getBeDatosRubroEvaluacionProceso() {
        return beDatosRubroEvaluacionProceso;
    }

    public void setBeDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso) {
        this.beDatosRubroEvaluacionProceso = beDatosRubroEvaluacionProceso;
    }

    public GEDatosSilaboEventoEnvio getBeDatosSilaboEventoEnvio() {
        return beDatosSilaboEventoEnvio;
    }

    public void setBeDatosSilaboEventoEnvio(GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio) {
        this.beDatosSilaboEventoEnvio = beDatosSilaboEventoEnvio;
    }

    public BEObtenerDatosLogin getBeObtenerDatosLogin() {
        return beObtenerDatosLogin;
    }

    public void setBeObtenerDatosLogin(BEObtenerDatosLogin beObtenerDatosLogin) {
        this.beObtenerDatosLogin = beObtenerDatosLogin;
    }

    @Override
    public String toString() {
        return "SEDatosCompletosLogin{" +
                "beDatosCargaAcademica=" + (beDatosCargaAcademica==null) +
                ", beDatosEnvioAsistencia=" + (beDatosEnvioAsistencia==null) +
                ", beDatosEnvioGrupo=" + (beDatosEnvioGrupo==null) +
                ", beDatosEnvioHorario=" + (beDatosEnvioHorario==null) +
                ", beDatosEnvioMensajeria=" + (beDatosEnvioMensajeria==null) +
                ", beDatosEnvioTipoNota=" + (beDatosEnvioTipoNota==null) +
                ", beDatosEvaluacionResultado=" + (beDatosEvaluacionResultado==null) +
                ", beDatosRubroEvaluacionProceso=" + (beDatosRubroEvaluacionProceso==null) +
                ", beDatosSilaboEventoEnvio=" + (beDatosSilaboEventoEnvio==null) +
                ", beObtenerDatosLogin=" + (beObtenerDatosLogin==null) +
                '}';
    }
}
