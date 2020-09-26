package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.local;


import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.EvaluacionResultadoDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class EvaluacionResultadoLocalDataSource implements EvaluacionResultadoDataSource {
    private static final String TAG = EvaluacionResultadoLocalDataSource.class.getSimpleName();
    public static final int TABLE_EVAL_RESULTADO_TIPO_COMPETENCIA=1, TABLE_EVAL_RESULTADO_TIPO_CAPACIDAD=0, TABLE_EVAL_RESULTADO_TIPO_FINAL=2;

    public EvaluacionResultadoLocalDataSource() {
    }

    @Override
    public void getTableEvaluacionResultado(int rubroEvalResultadoId, int tipoCompetencia, SucessCallback<List<AlumnoUi>> callback) {
        try {
            Log.d(TAG, "rubroEvalResultadoId "+  rubroEvalResultadoId);
            RubroEvaluacionResultado rubroEvaluacionResultado= SQLite.select().from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.eq(rubroEvalResultadoId))
                    .querySingle();

            List<AlumnoUi> alumnoUiList= new ArrayList<>();
            List<EvaluacionResultadoC> evaluacionResultadoList = SQLite.select(Utils.f_allcolumnTable(EvaluacionResultadoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(EvaluacionResultadoC.class)
                    .innerJoin(Persona.class)
                    .on(Persona_Table.personaId.withTable()
                            .eq(EvaluacionResultadoC_Table.alumnoId.withTable()))
                    .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(rubroEvaluacionResultado.getRubroEvalResultadoId()))
                    .orderBy(Persona_Table.apellidoPaterno.asc())
                    .queryList();


            for(EvaluacionResultadoC evaluacion:evaluacionResultadoList){
                Log.d(TAG, "evaluacion "+  evaluacion.getEvaluacionResultadoId());
                Persona persona = SQLite.select().from(Persona.class).where(Persona_Table.personaId.eq(evaluacion.getAlumnoId())).querySingle();

                if(persona==null)continue;
                AlumnoUi alumnoUi = new AlumnoUi();
                Log.d(TAG, "PERSONA "+ persona.getFirstName()+ "id alumno "+ persona.getPersonaId());
                alumnoUi.setId(String.valueOf(persona.getPersonaId()));
                alumnoUi.setLastName(persona.getApellidos());
                alumnoUi.setName(persona.getFirstName());
                alumnoUi.setUrlProfile(persona.getUrlPicture());

                List<EvaluacionResultadoC> evaluacionResultados;

                From<EvaluacionResultadoC> evaluacionResultadoFrom = SQLite.select(Utils.f_allcolumnTable(EvaluacionResultadoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(EvaluacionResultadoC.class).
                                innerJoin(RubroEvaluacionResultado.class).on(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable()
                                .eq(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable()))
                        .innerJoin(Competencia.class)
                        .on(RubroEvaluacionResultado_Table.competenciaId.withTable()
                                .eq(Competencia_Table.competenciaId.withTable()));


                Where<EvaluacionResultadoC> evaluacionResultadoWhere = null;

                switch (tipoCompetencia){
                   case TABLE_EVAL_RESULTADO_TIPO_CAPACIDAD:
                       evaluacionResultadoWhere = evaluacionResultadoFrom
                               .where(Competencia_Table.competenciaId.withTable().eq(rubroEvaluacionResultado.getCompetenciaId()))
                       .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1));
                       break;
                   default:
                        evaluacionResultadoWhere = evaluacionResultadoFrom
                                .where(Competencia_Table.superCompetenciaId.withTable().eq(rubroEvaluacionResultado.getCompetenciaId()))
                           .and(RubroEvaluacionResultado_Table.evaluable.withTable().eq(1));
                        break;
                }
                evaluacionResultados = evaluacionResultadoWhere.and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()
                        .eq(rubroEvaluacionResultado.getCalendarioPeriodoId()))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(rubroEvaluacionResultado.getSilaboEventoId()))
                        .and(EvaluacionResultadoC_Table.alumnoId.withTable().eq(evaluacion.getAlumnoId()))
                                .queryList();

                //Log.d(TAG, "evaluacionResultado SQL "+evaluacionResultados.size());
                Log.d(TAG, "evaluacionResultado1 "+evaluacionResultados.size());

                if(tipoCompetencia!=TABLE_EVAL_RESULTADO_TIPO_CAPACIDAD) {
                    EvaluacionResultadoC evaluacionResultado = SQLite.select().from(EvaluacionResultadoC.class)
                            .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().eq(rubroEvaluacionResultado.getRubroEvalResultadoId()))
                            .and(EvaluacionResultadoC_Table.alumnoId.withTable().eq(evaluacion.getAlumnoId())).querySingle();
                    if (evaluacionResultado != null)
                        evaluacionResultados.add(0, evaluacionResultado);
                }

                Log.d(TAG, "evaluacionResultado2 "+evaluacionResultados.size());

                List<NotaUi> notaUiList= new ArrayList<>();
                for(EvaluacionResultadoC evaluacionResultado: evaluacionResultados){
                    Log.d(TAG, "evaluacionResultado rubro "+evaluacionResultado.getRubroEvalResultadoId() );
                    Log.d(TAG, "evaluacionResultadoiD  "+evaluacionResultado.getEvaluacionResultadoId() );
                    Log.d(TAG, "evaluacionResultado nota   "+evaluacionResultado.getNota() );

                    NotaUi notaUi = new NotaUi();
                    String id = evaluacionResultado.getRubroEvalResultadoId() + "&_&" + evaluacionResultado.getAlumnoId();
                    notaUi.setId(id);
                    notaUi.setCompetenciaId(evaluacionResultado.getCompetenciaId());
                    notaUi.setAlumno(alumnoUi);
                    notaUi.setNota(String.valueOf(evaluacionResultado.getNota()));
                    notaUi.setValorTipoNotaId(evaluacionResultado.getValorTipoNotaId());

                    Log.d(TAG, "NOTAS "+ evaluacionResultado.getNota()+ " valor tip nota " +evaluacionResultado.getValorTipoNotaId() );

                   RubroEvaluacionResultado rubroEvalResultado= SQLite.select().from(RubroEvaluacionResultado.class)
                            .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId
                                    .eq(evaluacionResultado.getRubroEvalResultadoId()))
                            .querySingle();

                    if(rubroEvalResultado==null)continue;

                    TipoNotaC tipoNotaC= SQLite.select().from(TipoNotaC.class).where(TipoNotaC_Table.key.withTable().eq(rubroEvalResultado.getTipoNotaId())).querySingle();
                    Log.d(TAG, "TIPO NOTA Jse "+rubroEvalResultado.getTipoNotaId());
                    if(tipoNotaC==null)continue;
                    switch (tipoNotaC.getTipoId()){
                        case  TipoNotaC.SELECTOR_VALORES:
                           // Log.d(TAG, "SELECTOR_VALORES ");
                            notaUi.setTipo(NotaUi.Tipo.SELECTOR_VALORES);
                            Log.d(TAG, "NOTAS Jse "+evaluacionResultado.getValorTipoNotaId());
                            notaUi.setValorTipoNotaUi(getValorTipoNota(evaluacionResultado.getValorTipoNotaId()));
                            break;
                        default:
                            //  Log.d(TAG, "VALOR_NUMERICO ");
                            notaUi.setTipo(NotaUi.Tipo.VALOR_NUMERO);
                            break;
                    }

                    RubroEvaluacionUi rubroEvaluacionUi= new RubroEvaluacionUi();
                    rubroEvaluacionUi.setId(String.valueOf(rubroEvalResultado.getRubroEvalResultadoId()));
                    rubroEvaluacionUi.setPeso(rubroEvalResultado.getPeso());
                    rubroEvaluacionUi.setTitle(rubroEvalResultado.getTitulo());
                    rubroEvaluacionUi.setSubtitle(rubroEvalResultado.getSubtitulo());
                    rubroEvaluacionUi.setNotaUi(notaUi);
                    notaUi.setRubro(rubroEvaluacionUi);
                    notaUiList.add(notaUi);

                }

                if(tipoCompetencia!=TABLE_EVAL_RESULTADO_TIPO_CAPACIDAD){
                    NotaUi notaUi = notaUiList.get(0);
                    if(notaUi!=null){
                        notaUi.getRubro().setTipo(true);
                        notaUi.setTipo(NotaUi.Tipo.NOTA_FINAL);
                    }

                }

                alumnoUi.setNotaUiList(notaUiList);
                alumnoUiList.add(alumnoUi);

            }

            callback.onLoad(true,alumnoUiList );
        }catch (Exception e){
            Log.d(TAG, "Exception "+ e.getMessage());
        }
    }
    public ValorTipoNotaUi getValorTipoNota(String valorTipoNotaId){
        ValorTipoNotaC valorTipoNotac= SQLite.select().from(ValorTipoNotaC.class).where(ValorTipoNotaC_Table.key.withTable().eq(valorTipoNotaId)).querySingle();
        ValorTipoNotaUi valorTipoNotaUi =  new ValorTipoNotaUi();
        if(valorTipoNotac!=null){
            valorTipoNotaUi.setAlias(valorTipoNotac.getAlias());
            valorTipoNotaUi.setId(valorTipoNotac.getTipoNotaId());
            valorTipoNotaUi.setTitulo(valorTipoNotac.getTitulo());
            valorTipoNotaUi.setIcono(valorTipoNotac.getIcono());

        }
        return valorTipoNotaUi;
    }



}
