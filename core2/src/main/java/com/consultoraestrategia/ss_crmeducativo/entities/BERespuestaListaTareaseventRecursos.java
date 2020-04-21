package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by irvinmarin on 27/12/2017.
 */

public class BERespuestaListaTareaseventRecursos {
    private List<BERespuesta_TareaEvento> ListTareaEventoRecurso;

    public BERespuestaListaTareaseventRecursos() {
    }

    public BERespuestaListaTareaseventRecursos(List<BERespuesta_TareaEvento> listTareaEventoRecurso) {
        ListTareaEventoRecurso = listTareaEventoRecurso;
    }

    public List<BERespuesta_TareaEvento> getListTareaEventoRecurso() {
        return ListTareaEventoRecurso;
    }

    public void setListTareaEventoRecurso(List<BERespuesta_TareaEvento> listTareaEventoRecurso) {
        ListTareaEventoRecurso = listTareaEventoRecurso;
    }

    @Override
    public String toString() {
        return "BERespuestaListaTareaseventRecursos{" +
                "ListTareaEventoRecurso=" + ListTareaEventoRecurso +
                '}';
    }
}
