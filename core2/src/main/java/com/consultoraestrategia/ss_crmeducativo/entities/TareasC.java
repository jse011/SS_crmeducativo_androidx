package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by irvinmarin on 10/11/2017.
 */
@Table(database = AppDatabase.class)
public class TareasC extends BaseEntity {
    public static final int PUBLICADO=264;
    @Column
    private String tareaId;
    @Column
    private String titulo;
    @Column
    private String instrucciones;
    @Column
    private int estadoId;
    @Column
    private int unidadAprendizajeId;
    @Column
    private int sesionAprendizajeId;
    @Column
    private long fechaEntrega;
    @Column
    private String horaEntrega;
    @Column
    private int estadoExportado;

    private String rubroEvalProcesoId;
    private int tiporubroId;
    private String tipoNotaId;
    private int silaboEventoId;
    private int tipoPeriodoId;
    private String nombreSesion;
    private String datosUsuarioCreador;
    private int competenciaId;
    private int nroSesion;


    public TareasC() {
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public long getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(long fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }

    public static TareasC getTareaById(String keyTarea) {
        return SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.key.is(keyTarea))
                .querySingle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubroEvaluacionProcesoC)) return false;

        RubroEvaluacionProcesoC that = (RubroEvaluacionProcesoC) o;

        return getKey() != null ? getKey().equals(that.getKey()) : that.getKey() == null;
    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setTiporubroId(int tiporubroId) {
        this.tiporubroId = tiporubroId;
    }

    public int getTiporubroId() {
        return tiporubroId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setTipoPeriodoId(int tipoPeriodoId) {
        this.tipoPeriodoId = tipoPeriodoId;
    }

    public int getTipoPeriodoId() {
        return tipoPeriodoId;
    }

    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
    }

    public String getNombreSesion() {
        return nombreSesion;
    }

    public void setDatosUsuarioCreador(String datosUsuarioCreador) {
        this.datosUsuarioCreador = datosUsuarioCreador;
    }

    public String getDatosUsuarioCreador() {
        return datosUsuarioCreador;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setNroSesion(int nroSesion) {
        this.nroSesion = nroSesion;
    }

    public int getNroSesion() {
        return nroSesion;
    }
}
