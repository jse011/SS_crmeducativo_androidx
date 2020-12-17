package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import java.util.List;

public class BEMatrizResultadoDocente {

    public List<Alumno> alumnos;
    public List<Capacidad> capacidades;
    public List<Competencia> competencias;
    public List<Evaluacion> evaluaciones;
    public int EstadoCargaCurCalPerId;
    public int EstadoCalendarioPeriodoId;
    public int Habilitado;
    public int RangoFecha;


    public class Alumno {
        public int personaId;
        public String apellidoPaterno;
        public String apellidoMaterno;
        public String nombres;
        public String foto;
        public boolean vigencia;

    }
    public class Capacidad {
        public int rubroEvalResultadoId;
        public String titulo;
        public String tipoNotaId;
        public int tipoId;
        public int valorMinimo;
        public int valorMaximo;
        public int orden;
        public int orden2;
        public String competencia;
        public int parentId;
        public String competenciaId;
        public boolean evaluado;
        public boolean intervalo;
        public int rubroEvaluacionPrinId;
        public boolean rFEditable;
        public boolean notaDup;
    }
    public class Competencia {
        public String titulo;
        public int tipoId;
        public String competencia;
        public int competenciaId;
        public boolean notaDup;
        public int rubroEvalResultadoId;

    }
    public class Evaluacion {
        public int evaluacionResultadoId;
        public int alumnoId;
        public int rubroEvalResultadoId;
        public double nota;
        public String valorTipoNotaId;
        public String tituloNota;
        public String tipoId;
        public int orden;
        public int orden2;
        public boolean evaluado;
        public boolean rFEditable;
        public String color;
        public boolean notaDup;
        public String conclusionDescriptiva;

    }
    
}
