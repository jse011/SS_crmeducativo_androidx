package com.consultoraestrategia.ss_crmeducativo.repositorio.entities;

public class UpdateRepositorioFileUi extends RepositorioFileUi {
    private RepositorioUploadEstadoFileU uploadEstadoFileU = RepositorioUploadEstadoFileU.SIN_SUBIR;
    private DownloadCancelUi downloadCancelUi;
    private String extencionArchivoId;
    private int count;

    public RepositorioUploadEstadoFileU getUploadEstadoFileU() {
        return uploadEstadoFileU;
    }

    public void setUploadEstadoFileU(RepositorioUploadEstadoFileU uploadEstadoFileU) {
        this.uploadEstadoFileU = uploadEstadoFileU;
    }

    public DownloadCancelUi getDownloadCancelUi() {
        return downloadCancelUi;
    }

    public void setDownloadCancelUi(DownloadCancelUi downloadCancelUi) {
        this.downloadCancelUi = downloadCancelUi;
    }

    public void setExtencionArchivoId(String extencionArchivoId) {
        this.extencionArchivoId = extencionArchivoId;
    }

    public String getExtencionArchivoId() {
        return extencionArchivoId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
