package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte;
import com.consultoraestrategia.ss_crmeducativo.entities.Comportamiento;
import com.consultoraestrategia.ss_crmeducativo.entities.GeoRefOrganigrama;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoEntidadGeo;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;

import java.util.List;

public class BEDatosCasos {
    private List<Caso> caso;
    private List<CasoArchivo> casoArchivo;
    private List<CasoReporte> casoReporte;
    private List<Archivo> archivo;
    private List<Comportamiento> comportamiento;
    private List<TipoEntidadGeo> relTipoEntidadGeo;
    private long fecha_servidor;
    private List<Tipos> tipos;
    private List<GeoRefOrganigrama> geoRefOrganigrama;

    public List<Caso> getCaso() {
        return caso;
    }

    public void setCaso(List<Caso> caso) {
        this.caso = caso;
    }

    public List<CasoArchivo> getCasoArchivo() {
        return casoArchivo;
    }

    public void setCasoArchivo(List<CasoArchivo> casoArchivo) {
        this.casoArchivo = casoArchivo;
    }

    public List<CasoReporte> getCasoReporte() {
        return casoReporte;
    }

    public void setCasoReporte(List<CasoReporte> casoReporte) {
        this.casoReporte = casoReporte;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public List<Comportamiento> getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(List<Comportamiento> comportamiento) {
        this.comportamiento = comportamiento;
    }

    public List<TipoEntidadGeo> getRelTipoEntidadGeo() {
        return relTipoEntidadGeo;
    }

    public void setRelTipoEntidadGeo(List<TipoEntidadGeo> relTipoEntidadGeo) {
        this.relTipoEntidadGeo = relTipoEntidadGeo;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<GeoRefOrganigrama> getGeoRefOrganigrama() {
        return geoRefOrganigrama;
    }

    public void setGeoRefOrganigrama(List<GeoRefOrganigrama> geoRefOrganigrama) {
        this.geoRefOrganigrama = geoRefOrganigrama;
    }
}
