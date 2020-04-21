package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class InteraccionTextual  extends BaseEntity
{
    @Column
    private String interaccionTextualId;
    @Column
    private String entidad ;
    @Column
    private int referenciaId ;
    @Column
    private int usuarioId;
    @Column
    private String contenido ;


    public static final String ENTIDAD_SESSION= "T_GC_MAE_SESION_APRENDIZAJE_EVENTO";

    public String getInteraccionTextualId() {
        return interaccionTextualId;
    }

    public void setInteraccionTextualId(String interaccionTextualId) {
        this.interaccionTextualId = interaccionTextualId;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public int getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(int referenciaId) {
        this.referenciaId = referenciaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
