package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte;

import java.util.List;

public class BEDatosCasos {
    private List<Caso> caso;
    private List<CasoArchivo> casoArchivo;
    private List<CasoReporte> casoReporte;

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
}
