package com.consultoraestrategia.ss_crmeducativo.repositorio.entities;


import android.net.Uri;

import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class RepositorioFileUi implements Serializable {
    private String recursoId;
    private String archivoId;
    private String nombreRecurso;
    private String nombreArchivo;
    private String descripcion;
    private String url;
    @Deprecated
    private String path;
    private String pathLocal;
    private Uri uri;
    private RepositorioTipoFileU tipoFileU = RepositorioTipoFileU.VINCULO;
    private RepositorioEstadoFileU estadoFileU = RepositorioEstadoFileU.SIN_DESCARGAR;
    private boolean select;
    private boolean cancel;
    private long fechaCreacionRecuros;
    private long fechaAccionArchivo;
    private long downloadId;

    public String getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(String recursoId) {
        this.recursoId = recursoId;
    }

    public String getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(String archivoId) {
        this.archivoId = archivoId;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Deprecated
    public String getPath() {
        return path;
    }

    @Deprecated
    public void setPath(String path) {
        this.path = path;
    }

    public RepositorioTipoFileU getTipoFileU() {
        return tipoFileU;
    }

    public void setTipoFileU(RepositorioTipoFileU tipoFileU) {
        this.tipoFileU = tipoFileU;
    }

    public RepositorioEstadoFileU getEstadoFileU() {
        return estadoFileU;
    }

    public void setEstadoFileU(RepositorioEstadoFileU estadoFileU) {
        this.estadoFileU = estadoFileU;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }


    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public long getFechaCreacionRecuros() {
        return fechaCreacionRecuros;
    }

    public void setFechaCreacionRecuros(long fechaCreacionRecuros) {
        this.fechaCreacionRecuros = fechaCreacionRecuros;
    }

    public long getFechaAccionArchivo() {
        return fechaAccionArchivo;
    }

    public void setFechaAccionArchivo(long fechaAccionArchivo) {
        this.fechaAccionArchivo = fechaAccionArchivo;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    public long getDownloadId() {
        return downloadId;
    }

    public String getPathLocal() {
        return pathLocal;
    }

    public void setPathLocal(String pathLocal) {
        this.pathLocal = pathLocal;
    }
}
