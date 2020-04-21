package com.consultoraestrategia.ss_crmeducativo.services.entidad.response;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;

/**
 * Created by SCIEV on 5/06/2018.
 */

public class BERespuesta extends BEDatosServidor {
    private boolean commit;
    public BEObtenerDatosLogin login;
    public BEDatosRubroEvaluacionProceso rubroEvaluacionProceso;
    public BEDatosEvaluacionResultado rubroevaluacionResultado;
    public BEDatosEnvioTipoNota tiponota;
    public BEDatosEnvioAsistencia asistencia;
    public GEDatosSilaboEventoEnvio silaboEvento;
    public BEDatosEnvioHorario envioHorario;
    public BEDatosEnvioGrupo grupo;
    public BEDatosCargaAcademica cargaAcademica;
    public BEDatosEnvioMensajeria mensajeria;

    public boolean commit_TareaRecurso = false;
    public boolean commit_Sesion = false;
    public boolean commit_Asistencia = false;
    public boolean commit_Grupo = false;
    public boolean commit_Mensajes = false;
    public boolean commit_RubroEvaluacionProceso = false;


    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public BEObtenerDatosLogin getLogin() {
        return login;
    }

    public void setLogin(BEObtenerDatosLogin login) {
        this.login = login;
    }

    public BEDatosRubroEvaluacionProceso getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(BEDatosRubroEvaluacionProceso rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public BEDatosEvaluacionResultado getRubroevaluacionResultado() {
        return rubroevaluacionResultado;
    }

    public void setRubroevaluacionResultado(BEDatosEvaluacionResultado rubroevaluacionResultado) {
        this.rubroevaluacionResultado = rubroevaluacionResultado;
    }

    public BEDatosEnvioTipoNota getTiponota() {
        return tiponota;
    }

    public void setTiponota(BEDatosEnvioTipoNota tiponota) {
        this.tiponota = tiponota;
    }

    public BEDatosEnvioAsistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(BEDatosEnvioAsistencia asistencia) {
        this.asistencia = asistencia;
    }

    public GEDatosSilaboEventoEnvio getSilaboEvento() {
        return silaboEvento;
    }

    public void setSilaboEvento(GEDatosSilaboEventoEnvio silaboEvento) {
        this.silaboEvento = silaboEvento;
    }

    public BEDatosEnvioHorario getEnvioHorario() {
        return envioHorario;
    }

    public void setEnvioHorario(BEDatosEnvioHorario envioHorario) {
        this.envioHorario = envioHorario;
    }

    public BEDatosEnvioGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(BEDatosEnvioGrupo grupo) {
        this.grupo = grupo;
    }

    public BEDatosCargaAcademica getCargaAcademica() {
        return cargaAcademica;
    }

    public void setCargaAcademica(BEDatosCargaAcademica cargaAcademica) {
        this.cargaAcademica = cargaAcademica;
    }

    public BEDatosEnvioMensajeria getMensajeria() {
        return mensajeria;
    }

    public void setMensajeria(BEDatosEnvioMensajeria mensajeria) {
        this.mensajeria = mensajeria;
    }

    public boolean isCommit() {
        return commit;
    }

    public boolean getCommit_TareaRecurso() {
        return commit_TareaRecurso;
    }

    public void setCommit_TareaRecurso(boolean commit_TareaRecurso) {
        this.commit_TareaRecurso = commit_TareaRecurso;
    }

    public boolean getCommit_Sesion() {
        return commit_Sesion;
    }

    public void setCommit_Sesion(boolean commit_Sesion) {
        this.commit_Sesion = commit_Sesion;
    }

    public boolean getCommit_Asistencia() {
        return commit_Asistencia;
    }

    public void setCommit_Asistencia(boolean commit_Asistencia) {
        this.commit_Asistencia = commit_Asistencia;
    }

    public boolean getCommit_Grupo() {
        return commit_Grupo;
    }

    public void setCommit_Grupo(boolean commit_Grupo) {
        this.commit_Grupo = commit_Grupo;
    }

    public boolean getCommit_Mensajes() {
        return commit_Mensajes;
    }

    public void setCommit_Mensajes(boolean commit_Mensajes) {
        this.commit_Mensajes = commit_Mensajes;
    }

    public boolean isCommit_RubroEvaluacionProceso() {
        return commit_RubroEvaluacionProceso;
    }

    public void setCommit_RubroEvaluacionProceso(boolean commit_RubroEvaluacionProceso) {
        this.commit_RubroEvaluacionProceso = commit_RubroEvaluacionProceso;
    }

    public boolean isCommit_TareaRecurso() {
        return commit_TareaRecurso;
    }

    public boolean isCommit_Sesion() {
        return commit_Sesion;
    }

    public boolean isCommit_Asistencia() {
        return commit_Asistencia;
    }

    public boolean isCommit_Grupo() {
        return commit_Grupo;
    }

    public boolean isCommit_Mensajes() {
        return commit_Mensajes;
    }

    @Override
    public String toString() {
        return "BERespuesta{" +
                "commit=" + commit +
                ", commit_TareaRecurso=" + commit_TareaRecurso +
                ", commit_Sesion=" + commit_Sesion +
                ", commit_Asistencia=" + commit_Asistencia +
                ", commit_Grupo=" + commit_Grupo +
                ", commit_Mensajes=" + commit_Mensajes +
                ", commit_RubroEvaluacionProceso=" + commit_RubroEvaluacionProceso +
                '}';
    }
}
