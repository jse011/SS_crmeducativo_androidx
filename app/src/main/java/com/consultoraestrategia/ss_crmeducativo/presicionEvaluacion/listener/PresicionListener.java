package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.listener;

public interface PresicionListener {
    void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId);
}
