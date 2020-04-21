package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

/**
 * Created by SCIEV on 23/05/2018.
 */

public class GEDatosEnvioAsistencia  extends BEDatosServidor {
   private BEDatosEnvioAsistencia beDatosEnvioAsistencia;
   private BEDatosEnvioTipoNota beDatosEnvioTipoNota;

    public GEDatosEnvioAsistencia() {
    }

    public BEDatosEnvioAsistencia getBeDatosEnvioAsistencia() {
        return beDatosEnvioAsistencia;
    }

    public void setBeDatosEnvioAsistencia(BEDatosEnvioAsistencia beDatosEnvioAsistencia) {
        this.beDatosEnvioAsistencia = beDatosEnvioAsistencia;
    }

    public BEDatosEnvioTipoNota getBeDatosEnvioTipoNota() {
        return beDatosEnvioTipoNota;
    }

    public void setBeDatosEnvioTipoNota(BEDatosEnvioTipoNota beDatosEnvioTipoNota) {
        this.beDatosEnvioTipoNota = beDatosEnvioTipoNota;
    }
}
