package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import java.util.List;

public class BETransResultResponse {
    public boolean success;
    public List<Error> errores;

    public class Error {
        public int tipo;
        public int PersonaId;
        public int resultadoEvalId;
    }
}
