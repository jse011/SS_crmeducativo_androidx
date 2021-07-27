package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities;


public class DriveFileUi {
    private String nombre;
    private String driveId;
    private TipoArchivo tipoFileU = TipoArchivo.OTROS;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getDriveId() {
        return driveId;
    }

    public TipoArchivo getTipoFileU() {
        return tipoFileU;
    }

    public void setTipoFileU(TipoArchivo tipoFileU) {
        this.tipoFileU = tipoFileU;
    }

    public enum TipoArchivo{DOCUMENTO("Documento"), IMAGEN("Imagen"), PDF("Documento Portátil"), PRESENTACION("Presentación"), HOJACALCULO("Hoja de cálculo"), VIDEO("Video"), AUDIO("Múscia"), YOUTUBE(""), DRIVE(""), LINK(""), OTROS("Desconocido");
        private String nombre;

        TipoArchivo(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

}
