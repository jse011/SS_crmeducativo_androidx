package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities;

import android.net.Uri;

public class ArchivoUi {
    private String id;
    private String nombre;
    private Uri uri;
    private String url;
    private int progress;
    private boolean isCancel = false;
    private String evaluacionProcesoId;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public void setEvaluacionProcesoId(String evaluacionProcesoId) {
        this.evaluacionProcesoId = evaluacionProcesoId;
    }

    public String getEvaluacionProcesoId() {
        return evaluacionProcesoId;
    }
}
