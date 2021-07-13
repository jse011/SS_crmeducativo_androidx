package com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.util;

import java.io.Serializable;

public class DriveYoutubePreview implements Serializable {
    private String tareaId;
    private String nombreArchivoPreview;
    private String driveId;
    private Tipo tipo;
    private String url;
    private int silaboEventoId;
    private int unidadAprendizajeId;
    private int alumnoId;

    public enum Tipo{
        DOCUMENTO_DRIVE, MULTIMEDIA_DRIVE, YOUTUBE, TAREA_DOCUMENTO_FIREBASE, TAREA_MULTIMEDIA_FIREBASE, DRIVE_LINK
    }

    private DriveYoutubePreview() {
    }

    public static class Build {
        public static DriveYoutubePreview setupYoutube(String url){
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.url = url;
            PreviewDriveYoutube.tipo = Tipo.YOUTUBE;
            return PreviewDriveYoutube;
        }

        public static DriveYoutubePreview setupDriveDocumento(String driveId, String nombreArchivo){
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.driveId = driveId;
            PreviewDriveYoutube.nombreArchivoPreview = nombreArchivo;
            PreviewDriveYoutube.tipo = Tipo.DOCUMENTO_DRIVE;
            return PreviewDriveYoutube;
        }

        public static DriveYoutubePreview setupDriveMultimedia(String driveId, String nombreArchivo){
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.driveId = driveId;
            PreviewDriveYoutube.nombreArchivoPreview = nombreArchivo;
            PreviewDriveYoutube.tipo = Tipo.MULTIMEDIA_DRIVE;
            return PreviewDriveYoutube;
        }

        public static DriveYoutubePreview setupFirbaseTareaDocumento(String tareaId, int silaboEventoId, int unidadAprendizajeId, int alumnoId, String nombreArchivo){
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.tareaId = tareaId;
            PreviewDriveYoutube.silaboEventoId = silaboEventoId;
            PreviewDriveYoutube.unidadAprendizajeId = unidadAprendizajeId;
            PreviewDriveYoutube.alumnoId = alumnoId;
            PreviewDriveYoutube.nombreArchivoPreview = nombreArchivo;
            PreviewDriveYoutube.tipo = Tipo.TAREA_DOCUMENTO_FIREBASE;
            return PreviewDriveYoutube;
        }

        public static DriveYoutubePreview setupFirbaseTareaMultimedia(String tareaId, int silaboEventoId, int unidadAprendizajeId, int alumnoId, String nombreArchivo){
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.tareaId = tareaId;
            PreviewDriveYoutube.silaboEventoId = silaboEventoId;
            PreviewDriveYoutube.unidadAprendizajeId = unidadAprendizajeId;
            PreviewDriveYoutube.alumnoId = alumnoId;
            PreviewDriveYoutube.nombreArchivoPreview = nombreArchivo;
            PreviewDriveYoutube.tipo = Tipo.TAREA_MULTIMEDIA_FIREBASE;
            return PreviewDriveYoutube;
        }


        public static DriveYoutubePreview setupDriveLink(String url) {
            DriveYoutubePreview PreviewDriveYoutube = new DriveYoutubePreview();
            PreviewDriveYoutube.url = url;
            PreviewDriveYoutube.tipo = Tipo.TAREA_MULTIMEDIA_FIREBASE;
            return PreviewDriveYoutube;
        }
    }

    public String getTareaId() {
        return tareaId;
    }

    public String getNombreArchivoPreview() {
        return nombreArchivoPreview;
    }

    public String getDriveId() {
        return driveId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getUrl() {
        return url;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }
}
