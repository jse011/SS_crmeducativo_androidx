package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.RubroEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoGrupoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetNotaEvaluacionListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionGrupoCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveListEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.transform.TranforSesionPrincipalDao;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.EscalaEvaluacionUI;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.util.Utils.capitalize;

/**
 * Created by SCIEV on 6/10/2017.
 */

public class RubroLocalDataSourse implements RubroDataSource {


    private String TAG = RubroLocalDataSourse.class.getSimpleName();
    private RubroEvaluacionDao rubroEvaluacionDao;
    private TipoNotaDao tipoNotaDao;
    private IndicadorDao indicadorDao;
    private PersonaDao personaDao;
    private CompetenciaDao competenciaDao;
    private DimensionObservadaDao dimensionObservadaDao;
    private EvaluacionProcesoDao evaluacionProcesoDao;

    public RubroLocalDataSourse(RubroEvaluacionDao rubroEvaluacionDao, TipoNotaDao tipoNotaDao, IndicadorDao indicadorDao, EvaluacionProcesoDao evaluacionProcesoDao) {
        this.rubroEvaluacionDao = rubroEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
        this.indicadorDao = indicadorDao;
        this.personaDao = InjectorUtils.providePersonaDao();
        this.competenciaDao = InjectorUtils.provideCompetenciaDao();
        this.dimensionObservadaDao = InjectorUtils.provideDimensionObservadaDao();
        this.evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
    }

    /*Eliminar*/
    @Override
    public void getRubroList(int sesionAprendizajeId, GetRubroListCallback callback) {

        List<RubroEvaluacionUi> lst_rubroEvaluacionUi = new ArrayList<>();

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesos = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                .queryList();

        for (RubroEvaluacionProcesoC rubro : rubroEvaluacionProcesos) {

            TipoNotaC tipoNota = SQLite.select()
                    .from(TipoNotaC.class)
                    .where(TipoNotaC_Table.tipoNotaId.is(rubro.getTipoNotaId()))
                    .querySingle();
            if (tipoNota != null) {

                RubroEvaluacionUi ui = new RubroEvaluacionUi();
                ui.setId(rubro.getKey());
                ui.setTitulo(rubro.getTitulo());
                ui.setTipoNotaId(tipoNota.getTipoNotaId());
                switch (rubro.getTiporubroid()){
                    case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                        ui.setTipo(RubroEvaluacionUi.Tipo.RUBRICA_DETALLE);
                        break;
                    case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                        ui.setTipo(RubroEvaluacionUi.Tipo.NORMAL);
                        break;
                }
                lst_rubroEvaluacionUi.add(ui);

                switch (tipoNota.getTipoId()) {
                    case 409:
                        ui.setTipoNota(RubroEvaluacionUi.TipoNota.IMAGEN);
                        break;
                    case 411:
                        ui.setTipoNota(RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case 412:
                        ui.setTipoNota(RubroEvaluacionUi.TipoNota.TEXTO);
                        break;
                    case 410:
                        ui.setTipoNota(RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    default:
                        ui.setTipoNota(RubroEvaluacionUi.TipoNota.DEFECTO);
                        break;
                }

                Icds icds = SQLite.select()
                        .from(Icds.class)
                        .where(Icds_Table.icdId.is(rubro.getDesempenioIcdId()))
                        .querySingle();

                IndicadorUi indicadorUi = new IndicadorUi();
                if (icds != null) {
                    indicadorUi.setId(icds.getIcdId());
                    indicadorUi.setTitle(icds.getTitulo());
                    indicadorUi.setAlias(icds.getAlias());
                }
                ui.setIndicadorUi(indicadorUi);
            }
        }

        callback.onRecursoLoad(lst_rubroEvaluacionUi);
    }

    /*Eliminar*/
    @Override
    public void getRubro(int rubroEvaluacionProcesoId, GetRubroCallback callback) {
        Log.d(TAG, "rubroEvaluacionProcesoId :" + rubroEvaluacionProcesoId);
        RubroEvaluacionProcesoC rubroEvaluacionProcesos = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.androidId.is(rubroEvaluacionProcesoId))
                .querySingle();

        RubroEvaluacionUi rubroEvaluacionUi = new RubroEvaluacionUi();

        if (rubroEvaluacionProcesos != null) {

            rubroEvaluacionUi.setId(rubroEvaluacionProcesos.getKey());
            rubroEvaluacionUi.setTitulo(rubroEvaluacionProcesos.getTitulo());
            rubroEvaluacionUi.setSesionAprendizajeId(rubroEvaluacionProcesos.getSesionAprendizajeId());
            rubroEvaluacionUi.setStatus(true);
            switch (rubroEvaluacionProcesos.getTiporubroid()){
                case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                    rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.RUBRICA_DETALLE);
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                    rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.NORMAL);
                    break;
            }
            TipoNotaC tipoNota = SQLite.select()
                    .from(TipoNotaC.class)
                    .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionProcesos.getTipoNotaId()))
                    .querySingle();
            if (tipoNota != null) {
                rubroEvaluacionUi.setTipoNotaId(tipoNota.getTipoNotaId());
                switch (tipoNota.getTipoId()) {
                    case 409:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.IMAGEN);
                        break;
                    case 411:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case 412:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.TEXTO);
                        break;
                    case 410:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    default:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.DEFECTO);
                        break;
                }
            }

            Icds icds = SQLite.select()
                    .from(Icds.class)
                    .innerJoin(DesempenioIcd.class)
                    .on(Icds_Table.icdId.withTable()
                            .eq(DesempenioIcd_Table.icdId.withTable()))
                    .where(DesempenioIcd_Table.desempenioIcdId.is(rubroEvaluacionProcesos.getDesempenioIcdId()))
                    .querySingle();

            IndicadorUi indicadorUi = new IndicadorUi();
            if (icds != null) {
                indicadorUi.setId(icds.getIcdId());
                indicadorUi.setTitle(icds.getTitulo());
                indicadorUi.setAlias(icds.getAlias());
            }
            rubroEvaluacionUi.setIndicadorUi(indicadorUi);

        }
        callback.onRecursoLoad(rubroEvaluacionUi);
    }

    @Override
    public void getRubro(String rubroEvaluacionProcesoId, GetRubroCallback callback) {
        Log.d(TAG, "rubroEvaluacionProcesoId :" + rubroEvaluacionProcesoId);
        final int COMPETENCIA_BASE = 347;
        final int COMPETENCIA_TRANS = 348;
        final int COMPETENCIA_ENFQ = 349;

        RubroEvaluacionProcesoC rubroEvaluacionProcesos = rubroEvaluacionDao.get(rubroEvaluacionProcesoId);
        RubroEvaluacionUi rubroEvaluacionUi = new RubroEvaluacionUi();
        if (rubroEvaluacionProcesos != null) {
            rubroEvaluacionUi = TranforSesionPrincipalDao.getTansforRubroEvaluacionUi(rubroEvaluacionProcesos);
            rubroEvaluacionUi.setStatus(true);
            TipoNotaC tipoNota = tipoNotaDao.get(rubroEvaluacionProcesos.getTipoNotaId());
            if (tipoNota != null) {
                rubroEvaluacionUi.setTipoNotaId(tipoNota.getTipoNotaId());
                switch (tipoNota.getTipoId()) {
                    case 409:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.IMAGEN);
                        break;
                    case 411:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case 412:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.TEXTO);
                        break;
                    case 410:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    default:
                        rubroEvaluacionUi.setTipoNota(RubroEvaluacionUi.TipoNota.DEFECTO);
                        break;
                }
            }

            Icds icds = indicadorDao.getIcdsporDesempenioIcd(rubroEvaluacionProcesos.getDesempenioIcdId());
            IndicadorUi indicadorUi = new IndicadorUi();
            if (icds != null) indicadorUi = TranforSesionPrincipalDao.getTansforIndicadorUi(icds);
            rubroEvaluacionUi.setIndicadorUi(indicadorUi);

            Competencia competencia = competenciaDao.obtenerCompenciaPorCapacidad(rubroEvaluacionProcesos.getCompetenciaId());
            if (competencia == null) return;
            Log.d(TAG, "TipoId: " + competencia.getTipoId());
            switch (competencia.getTipoId()) {
                case COMPETENCIA_BASE:
                    rubroEvaluacionUi.setTipoCompetencia(TipoCompetencia.COMPETENCIA_BASE);
                    break;
                case COMPETENCIA_ENFQ:
                    rubroEvaluacionUi.setTipoCompetencia(TipoCompetencia.COMPETENCIA_ENFQ);
                    break;
                case COMPETENCIA_TRANS:
                    rubroEvaluacionUi.setTipoCompetencia(TipoCompetencia.COMPETENCIA_TRANS);
                    break;
            }

            switch (rubroEvaluacionProcesos.getTiporubroid()){
                case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                    rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.RUBRICA_DETALLE);
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL:
                    rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.RUBRICA_DETALLE);
                    break;
                case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                    rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.NORMAL);
                    break;
            }

        }
        callback.onRecursoLoad(rubroEvaluacionUi);
    }

    @Override
    public void getAlumnoListGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, int entidadId, int georeferenciaId,GetAlumnoGrupoListCallback callback) {
        Log.d(TAG, "sesionAprendizajeId: " + sesionAprendizajeId + ", RubroEvaluacionUi: " + rubroEvaluacionUi.getId());
        //#region Listar Notas Evaluacion
        List<ValorTipoNotaC> valorTipoNotas = SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico, false)
                .queryList();
        //#endregion

        List<GrupoEvaluacionUi> grupoEvaluacionUis = new ArrayList<>();
        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> equipoList = SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.is(rubroEvaluacionUi.getId()))
                .queryList();

        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemEquipo : equipoList) {
            GrupoEvaluacionUi grupo_EvaluacionUi = new GrupoEvaluacionUi();
            grupo_EvaluacionUi.setId(itemEquipo.getKey());
            grupo_EvaluacionUi.setName(itemEquipo.getNombreEquipo());
            //#region Agregar Notas Evaluacion
            int countEstilo = 0;
            List<NotaUi> grupo1_evaluacionProcesoUis = new ArrayList<>();
            for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                NotaUi notaUi = new NotaUi();
                notaUi.setId(valorTipoNota.getValorTipoNotaId());
                notaUi.setNotaDefault(valorTipoNota.getValorNumerico());
                notaUi.setContenido(valorTipoNota.getTitulo());
                notaUi.setTipo(valorTipoNota.getTipoNotaId());
                notaUi.setNota(valorTipoNota.getValorNumerico());
                notaUi.setNotaEvaluacion(valorTipoNota.getValorNumerico());
                notaUi.setIcono(valorTipoNota.getIcono());
                notaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
                notaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
                notaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
                notaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
                notaUi.setIntervalo(tipoNotaDao.validarTipoNotas(valorTipoNota.getTipoNotaId()));
                notaUi.setGrupoEvaluacion(grupo_EvaluacionUi);

                switch (rubroEvaluacionUi.getTipoNota()){
                    case SELECTOR_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case TEXTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                        break;
                    case IMAGEN:
                        notaUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                        break;
                    case TECLADO_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    case DEFECTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                        break;
                }



                grupo1_evaluacionProcesoUis.add(notaUi);

                EquipoEvaluacionProcesoC equipoEvaluacionProcesoC = SQLite.select(Utils.f_allcolumnTable(EquipoEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(EquipoEvaluacionProcesoC.class)
                        .innerJoin(ValorTipoNotaC.class)
                        .on(EquipoEvaluacionProcesoC_Table.valorTipoNotaId.withTable().eq(ValorTipoNotaC_Table.valorTipoNotaId.withTable()))
                        .where(EquipoEvaluacionProcesoC_Table.equipoId.withTable().is(itemEquipo.getKey()))
                        .and(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(itemEquipo.getRubroEvalProcesoId()))
                        .and(ValorTipoNotaC_Table.valorTipoNotaId.withTable().is(notaUi.getId()))
                        .querySingle();


                if (equipoEvaluacionProcesoC != null) {
                    notaUi.setResaltar(true);
                    Log.d(TAG, "Nota Grupo: " + equipoEvaluacionProcesoC.getNota());
                    notaUi.setNota(equipoEvaluacionProcesoC.getNota());
                    grupo_EvaluacionUi.setNotaUi(notaUi);
                    Log.d(TAG, "setResaltarGrupo");

                }

                switch (countEstilo) {
                    case 0:
                        notaUi.setEstilo(Estilo.AZUL);
                        break;
                    case 1:
                        notaUi.setEstilo(Estilo.VERDE);
                        break;
                    case 2:
                        notaUi.setEstilo(Estilo.ANARANJADO);
                        break;
                    case 3:
                        notaUi.setEstilo(Estilo.ROJO);
                        break;
                }
                countEstilo++;

            }
            //#region Teclado Numerico
            if (rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO ||
                    rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO) {

                grupo1_evaluacionProcesoUis.clear();
                TipoNotaC tipoNota = SQLite.select()
                        .from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                        .querySingle();

                if (tipoNota != null) {
                    SelectorNumericoUi selectorNumericoUi = new SelectorNumericoUi();
                    selectorNumericoUi.setId(null);
                    selectorNumericoUi.setNota(0);
                    selectorNumericoUi.setValorMaximo(tipoNota.getValorMaximo());
                    selectorNumericoUi.setValorMinino(tipoNota.getValorMinino());
                    selectorNumericoUi.setLongitudPaso(tipoNota.getLongitudPaso());
                    EscalaEvaluacionUI escalaEvaluacionUI = new EscalaEvaluacionUI();
                    selectorNumericoUi.setEscalaEvaluacionUi(escalaEvaluacionUI);

                    switch (rubroEvaluacionUi.getTipoNota()){
                        case SELECTOR_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                            break;
                        case TEXTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                            break;
                        case IMAGEN:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                            break;
                        case TECLADO_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                            break;
                        case DEFECTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                            break;
                    }

                    grupo1_evaluacionProcesoUis.add(selectorNumericoUi);

                    EscalaEvaluacion escalaEvaluacion = SQLite.select()
                            .from(EscalaEvaluacion.class)
                            .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNota.getEscalaEvaluacionId()))
                            .querySingle();
                    if (escalaEvaluacion != null) {
                        escalaEvaluacionUI.setId(escalaEvaluacion.getEscalaEvaluacionId());
                        escalaEvaluacionUI.setName(escalaEvaluacion.getNombre());
                        escalaEvaluacionUI.setValorMaximo(escalaEvaluacion.getValorMaximo());
                        escalaEvaluacionUI.setValorMinimo(escalaEvaluacion.getValorMinimo());
                    }

                    EquipoEvaluacionProcesoC equipoEvaluacionProceso = SQLite.select()
                            .from(EquipoEvaluacionProcesoC.class)
                            .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(itemEquipo.getRubroEvalProcesoId()))
                            .and(EquipoEvaluacionProcesoC_Table.equipoId.withTable().is(itemEquipo.getKey()))
                            .querySingle();
                    if (equipoEvaluacionProceso != null) {
                        selectorNumericoUi.setResaltar(true);
                        selectorNumericoUi.setNota(equipoEvaluacionProceso.getNota());
                        grupo_EvaluacionUi.setNotaUi(selectorNumericoUi);
                        Log.d(TAG, "setResaltar " + selectorNumericoUi.getNota());

                    }
                }

            }
            //#endregion
            switch (rubroEvaluacionUi.getTipo()){
                case NORMAL:
                    grupo1_evaluacionProcesoUis.add(new OptionComentario());
                    grupo1_evaluacionProcesoUis.add(new OptionPublicar());
                    break;
                case RUBRICA_DETALLE:
                    break;
            }

            Log.d(TAG, "Error_Jse 2: grupo" +rubroEvaluacionUi.getTitulo()+" "+ grupo1_evaluacionProcesoUis.size());
            grupo_EvaluacionUi.setNotaUis(grupo1_evaluacionProcesoUis);
            //#endregion
            grupoEvaluacionUis.add(grupo_EvaluacionUi);

            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.is(itemEquipo.getKey()))
                    .queryList();

            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC integrante : integranteList) {

                PersonaContratoQuery persona =  personaDao.getPersonContrato(integrante.getPersonaId(), rubroEvaluacionUi.getCargaCursosId());



                if (persona == null) continue;
                String espacio = " ";
                AlumnosEvaluacionUi alumnosEvaluacionUi = new AlumnosEvaluacionUi();
                alumnosEvaluacionUi.setId(persona.getPersonaId());
                String nombreSimple = "";
                if (persona.getNombres() != null && !persona.getNombres().isEmpty()) {
                    int hasta = persona.getNombres().indexOf(espacio);
                    if (hasta != -1) {
                        nombreSimple = persona.getNombres().substring(0, hasta);
                    } else {
                        nombreSimple = persona.getNombres();
                    }

                }
                alumnosEvaluacionUi.setName(nombreSimple);
                alumnosEvaluacionUi.setLastName(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
                alumnosEvaluacionUi.setFotoPerfil(persona.getFoto());
                alumnosEvaluacionUi.setGrupoId(grupo_EvaluacionUi.getId());
                alumnosEvaluacionUi.setParent(grupo_EvaluacionUi);
                alumnosEvaluacionUi.setVigente(persona.getVigente()==1);
                addListDimencion(alumnosEvaluacionUi, entidadId, georeferenciaId);
                //#region Agregar Notas Evaluacion
                int countEstiloAlumno = 0;
                List<NotaUi> alumnos_evaluacionProcesoUis = new ArrayList<>();
                EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                        .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                        .and(EvaluacionProcesoC_Table.equipoId.withTable().is(grupo_EvaluacionUi.getId()))
                        .querySingle();

                for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                    NotaUi notaUi = new NotaUi();
                    notaUi.setId(valorTipoNota.getValorTipoNotaId());
                    notaUi.setNotaDefault(valorTipoNota.getValorNumerico());
                    notaUi.setContenido(valorTipoNota.getTitulo());
                    notaUi.setTipo(valorTipoNota.getTipoNotaId());
                    notaUi.setNota(valorTipoNota.getValorNumerico());
                    notaUi.setNotaEvaluacion(valorTipoNota.getValorNumerico());
                    notaUi.setIcono(valorTipoNota.getIcono());
                    notaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
                    notaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
                    notaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
                    notaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
                    notaUi.setIntervalo(tipoNotaDao.validarTipoNotas(valorTipoNota.getTipoNotaId()));
                    notaUi.setAlumnoEvaluacion(alumnosEvaluacionUi);
                    alumnos_evaluacionProcesoUis.add(notaUi);

                    switch (rubroEvaluacionUi.getTipoNota()){
                        case SELECTOR_NUMERICO:
                            notaUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                            break;
                        case TEXTO:
                            notaUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                            break;
                        case IMAGEN:
                            notaUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                            break;
                        case TECLADO_NUMERICO:
                            notaUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                            break;
                        case DEFECTO:
                            notaUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                            break;
                    }

                    if (evaluacionProcesoC != null
                            &&!TextUtils.isEmpty(notaUi.getId())
                            && notaUi.getId().equals(evaluacionProcesoC.getValorTipoNotaId())) {
                        notaUi.setResaltar(true);
                        notaUi.setNota(evaluacionProcesoC.getNota());
                        alumnosEvaluacionUi.setNotaUi(notaUi);
                        Log.d(TAG, "setResaltar");

                    }

                    switch (countEstiloAlumno) {
                        case 0:
                            notaUi.setEstilo(Estilo.AZUL);
                            break;
                        case 1:
                            notaUi.setEstilo(Estilo.VERDE);
                            break;
                        case 2:
                            notaUi.setEstilo(Estilo.ANARANJADO);
                            break;
                        case 3:
                            notaUi.setEstilo(Estilo.ROJO);
                            break;
                    }
                    countEstiloAlumno++;

                }
                //#region tecladoNumerico
                if (rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO ||
                        rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO) {
                    alumnos_evaluacionProcesoUis.clear();
                    TipoNotaC tipoNota = SQLite.select()
                            .from(TipoNotaC.class)
                            .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                            .querySingle();
                    if (tipoNota!=null){
                        SelectorNumericoUi selectorNumericoUi = new SelectorNumericoUi();
                        selectorNumericoUi.setId(null);
                        selectorNumericoUi.setNota(0);
                        selectorNumericoUi.setValorMaximo(tipoNota.getValorMaximo());
                        selectorNumericoUi.setValorMinino(tipoNota.getValorMinino());
                        selectorNumericoUi.setLongitudPaso(tipoNota.getLongitudPaso());
                        EscalaEvaluacionUI escalaEvaluacionUI = new EscalaEvaluacionUI();
                        selectorNumericoUi.setEscalaEvaluacionUi(escalaEvaluacionUI);

                        switch (rubroEvaluacionUi.getTipoNota()){
                            case SELECTOR_NUMERICO:
                                selectorNumericoUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                                break;
                            case TEXTO:
                                selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                                break;
                            case IMAGEN:
                                selectorNumericoUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                                break;
                            case TECLADO_NUMERICO:
                                selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                                break;
                            case DEFECTO:
                                selectorNumericoUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                                break;
                        }

                        alumnos_evaluacionProcesoUis.add(selectorNumericoUi);

                        EscalaEvaluacion escalaEvaluacion = SQLite.select()
                                .from(EscalaEvaluacion.class)
                                .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNota.getEscalaEvaluacionId()))
                                .querySingle();

                        if (escalaEvaluacion != null) {
                            escalaEvaluacionUI.setId(escalaEvaluacion.getEscalaEvaluacionId());
                            escalaEvaluacionUI.setName(escalaEvaluacion.getNombre());
                            escalaEvaluacionUI.setValorMaximo(escalaEvaluacion.getValorMaximo());
                            escalaEvaluacionUI.setValorMinimo(escalaEvaluacion.getValorMinimo());
                        }

                        if (evaluacionProcesoC != null) {
                            selectorNumericoUi.setResaltar(true);
                            selectorNumericoUi.setNota(evaluacionProcesoC.getNota());
                            alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
                            Log.d(TAG, "setResaltar " + selectorNumericoUi.getNota());
                        }
                    }
                }
                //#endregion tecladoNumerico
                OptionComentario optionComentario = new OptionComentario();
                optionComentario.setVigente(persona.getVigente()==1);
                optionComentario.setAlumnoEvaluacion(alumnosEvaluacionUi);
                OptionPublicar optionPublicar = new OptionPublicar();
                optionPublicar.setAlumnoEvaluacion(alumnosEvaluacionUi);
                if(evaluacionProcesoC!=null){
                    optionPublicar.setEvaluacionId(evaluacionProcesoC.getKey());
                    optionPublicar.setSelected(evaluacionProcesoC.getPublicado()==1);
                    optionPublicar.setVigente(persona.getVigente()==1);
                    optionComentario.setEvaluacionId(evaluacionProcesoC.getKey());
                }else {
                    Log.d(TAG, "Error_Jse 2: AlumnoId: "+ alumnosEvaluacionUi.getId() +" rubroEvaluacionUi: "+rubroEvaluacionUi.getId() + " grupo_EvaluacionUi: "+ grupo_EvaluacionUi.getId());
                }

                switch (rubroEvaluacionUi.getTipo()){
                    case NORMAL:
                        alumnos_evaluacionProcesoUis.add(optionComentario);
                        alumnos_evaluacionProcesoUis.add(optionPublicar);
                        break;
                    case RUBRICA_DETALLE:
                        break;
                }
                Log.d(TAG, "Error_Jse 2:" +rubroEvaluacionUi.getTitulo()+" "+ alumnos_evaluacionProcesoUis.size());
                alumnosEvaluacionUi.setNotaUis(alumnos_evaluacionProcesoUis);
                //#endregion Agregar Notas Evaluacion
                grupo_EvaluacionUi.addAlumnosEvaluacionUi(alumnosEvaluacionUi);

            }

        }

        callback.onRecursoLoad(grupoEvaluacionUis);
    }

    @Override
    public void getAlumnoList(RubroEvaluacionUi rubroEvaluacionUi, FiltroTableUi filtroTableUi,int cargaCursoId, int entidadId, int georeferenciaId,GetAlumnoListCallback callback) {

        List<AlumnosEvaluacionUi> listaEvaluacion = new ArrayList<>();

        //#region Listar Notas Evaluacion
        List<ValorTipoNotaC> valorTipoNotas = SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                .orderBy(ValorTipoNotaC_Table.valorNumerico, false)
                .queryList();

        //#endregion
        //#region Listar Docente

        List<PersonaContratoQuery> personaList = new ArrayList<>();
        switch (filtroTableUi.getOrderByASC()) {
            case NOMBRE:
                personaList = personaDao.getAlumnosDeRubro(true, false, filtroTableUi.getPersona(), rubroEvaluacionUi.getId(), cargaCursoId);
                break;
            case APELLIDO:
                personaList = personaDao.getAlumnosDeRubro(false, true, filtroTableUi.getPersona(), rubroEvaluacionUi.getId(), cargaCursoId);
                break;
        }


        //#endregion
        String espacio = " ";
        for (PersonaContratoQuery persona : personaList) {
            //#region Agregar Alumno Evaluacion
            AlumnosEvaluacionUi alumnosEvaluacionUi = new AlumnosEvaluacionUi();
            alumnosEvaluacionUi.setId(persona.getPersonaId());
            String nombreSimple = "";
            if (persona.getNombres() != null && !persona.getNombres().isEmpty()) {
                int hasta = persona.getNombres().indexOf(espacio);
                if (hasta != -1) {
                    nombreSimple = persona.getNombres().substring(0, hasta);
                } else {
                    nombreSimple = persona.getNombres();
                }

            }
            alumnosEvaluacionUi.setName(nombreSimple);
            alumnosEvaluacionUi.setLastName(capitalize(persona.getApellidoPaterno()) + " " + capitalize(persona.getApellidoMaterno()));
            alumnosEvaluacionUi.setFotoPerfil(persona.getFoto());

            alumnosEvaluacionUi.setVigente(persona.getVigente()==1);
            addListDimencion(alumnosEvaluacionUi, entidadId, georeferenciaId);
            //#region Agregar Notas Evaluacion
            int countEstiloAlumno = 0;
            List<NotaUi> evaluacionProcesoUis = new ArrayList<>();
            EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                    .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                    //.and(EvaluacionProcesoC_Table.EquipoId.withTable().isNull())
                    .querySingle();;
            for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                NotaUi notaUi = new NotaUi();
                notaUi.setId(TextUtils.isEmpty(valorTipoNota.getValorTipoNotaId())? "" : valorTipoNota.getValorTipoNotaId());
                notaUi.setNotaDefault(valorTipoNota.getValorNumerico());
                notaUi.setContenido(valorTipoNota.getTitulo());
                notaUi.setNota(valorTipoNota.getValorNumerico());
                notaUi.setNotaEvaluacion(valorTipoNota.getValorNumerico());
                notaUi.setIcono(valorTipoNota.getIcono());
                notaUi.setIntervalo(tipoNotaDao.validarTipoNotas(valorTipoNota.getTipoNotaId()));
                notaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
                notaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
                notaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
                notaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
                notaUi.setAlumnoEvaluacion(alumnosEvaluacionUi);

                switch (rubroEvaluacionUi.getTipoNota()){
                    case SELECTOR_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case TEXTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                        break;
                    case IMAGEN:
                        notaUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                        break;
                    case TECLADO_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    case DEFECTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                        break;
                }

                if (evaluacionProcesoC != null
                        &&!TextUtils.isEmpty(notaUi.getId())
                        && notaUi.getId().equals( evaluacionProcesoC.getValorTipoNotaId())) {
                    notaUi.setResaltar(true);
                    notaUi.setNota(evaluacionProcesoC.getNota());
                    alumnosEvaluacionUi.setNotaUi(notaUi);
                }

                evaluacionProcesoUis.add(notaUi);

                switch (countEstiloAlumno) {
                    case 0:
                        notaUi.setEstilo(Estilo.AZUL);
                        break;
                    case 1:
                        notaUi.setEstilo(Estilo.VERDE);
                        break;
                    case 2:
                        notaUi.setEstilo(Estilo.ANARANJADO);
                        break;
                    case 3:
                        notaUi.setEstilo(Estilo.ROJO);
                        break;
                }
                countEstiloAlumno++;


            }

            if (rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO ||
                    rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO) {
                evaluacionProcesoUis.clear();

                TipoNotaC tipoNota = SQLite.select()
                        .from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                        .querySingle();
                if (tipoNota != null) {
                    SelectorNumericoUi selectorNumericoUi = new SelectorNumericoUi();
                    selectorNumericoUi.setId(null);
                    selectorNumericoUi.setNota(0);
                    selectorNumericoUi.setValorMaximo(tipoNota.getValorMaximo());
                    selectorNumericoUi.setValorMinino(tipoNota.getValorMinino());
                    selectorNumericoUi.setLongitudPaso(tipoNota.getLongitudPaso());

                    switch (rubroEvaluacionUi.getTipoNota()){
                        case SELECTOR_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                            break;
                        case TEXTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                            break;
                        case IMAGEN:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                            break;
                        case TECLADO_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                            break;
                        case DEFECTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                            break;
                    }

                    EscalaEvaluacionUI escalaEvaluacionUI = new EscalaEvaluacionUI();
                    selectorNumericoUi.setEscalaEvaluacionUi(escalaEvaluacionUI);
                    evaluacionProcesoUis.add(selectorNumericoUi);

                    EscalaEvaluacion escalaEvaluacion = SQLite.select()
                            .from(EscalaEvaluacion.class)
                            .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNota.getEscalaEvaluacionId()))
                            .querySingle();
                    if (escalaEvaluacion != null) {
                        escalaEvaluacionUI.setId(escalaEvaluacion.getEscalaEvaluacionId());
                        escalaEvaluacionUI.setName(escalaEvaluacion.getNombre());
                        escalaEvaluacionUI.setValorMaximo(escalaEvaluacion.getValorMaximo());
                        escalaEvaluacionUI.setValorMinimo(escalaEvaluacion.getValorMinimo());
                    }

                    if (evaluacionProcesoC != null) {
                        selectorNumericoUi.setResaltar(true);
                        selectorNumericoUi.setNota(evaluacionProcesoC.getNota());
                        alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
                        Log.d(TAG, "setResaltar " + selectorNumericoUi.getNota());
                    }
                }


            }

            OptionComentario optionComentario = new OptionComentario();
            optionComentario.setAlumnoEvaluacion(alumnosEvaluacionUi);
            optionComentario.setVigente(persona.getVigente()==1);
            OptionPublicar optionPublicar = new OptionPublicar();
            optionPublicar.setVigente(persona.getVigente()==1);
            optionPublicar.setAlumnoEvaluacion(alumnosEvaluacionUi);
            if(evaluacionProcesoC!=null){
                optionPublicar.setEvaluacionId(evaluacionProcesoC.getKey());
                optionPublicar.setSelected(evaluacionProcesoC.getPublicado()==1);
                optionComentario.setEvaluacionId(evaluacionProcesoC.getKey());
            }
            switch (rubroEvaluacionUi.getTipo()){
                case NORMAL:
                    evaluacionProcesoUis.add(optionComentario);
                    evaluacionProcesoUis.add(optionPublicar);
                    break;
                case RUBRICA_DETALLE:
                    break;
            }
            Log.d(TAG, "Error_Jse 3:" +rubroEvaluacionUi.getTitulo()+" "+ evaluacionProcesoUis.size());
            alumnosEvaluacionUi.setNotaUis(evaluacionProcesoUis);
            //#endregion
            listaEvaluacion.add(alumnosEvaluacionUi);
            //#endregion
        }

        callback.onRecursoLoad(listaEvaluacion);

    }

    @Override
    public void getIndicadoresList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GetIndicadorListCallback callback) {


        List<IndicadorUi> indicadorUis = new ArrayList<>();
        List<Icds> icds_list = SQLite.select()
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .innerJoin(SesionEventoDesempenioIcd.class)
                .on(SesionEventoDesempenioIcd_Table.desempenioicdid.withTable().eq(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .where(SesionEventoDesempenioIcd_Table.sesionaprendizajeid.is(sesionAprendizajeId))
                .queryList();

        int count1 = 0;
        for (Icds icds1 : icds_list) {
            count1++;
            IndicadorUi indicadorUi = new IndicadorUi();
            indicadorUi.setId(icds1.getIcdId());
            indicadorUi.setSelector(String.valueOf(count1));
            indicadorUi.setTitle(icds1.getTitulo());
            indicadorUi.setAlias(icds1.getAlias());
            indicadorUis.add(indicadorUi);
        }


        callback.onRecursoLoad(indicadorUis);

    }

    @Override
    public void SaveRubro(final int sesionAprendizajeId, final RubroEvaluacionUi RubroEvaluacionUi, final AlumnosEvaluacionUi alumnosEvaluacionUi, final SaveEvaluacionCallBack callBack) {
        Log.d(TAG, " SaveRubro "+ alumnosEvaluacionUi.getNotaUi().getContenido());
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);

        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                SessionUser sessionUser = SessionUser.getCurrentUser();
                Log.d(TAG, " rubro"+ RubroEvaluacionUi.getId());
                if (sessionUser == null)
                    throw new Error("no se guardó correctamente el RubroEvaluacion por que no se encontro al Usuario");

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.is(RubroEvaluacionUi.getId()))
                        .querySingle();
                if (rubroEvaluacionProcesoC != null) {
                    rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save(databaseWrapper);

                    int calendarioPeriodoId = rubroEvaluacionProcesoC.getCalendarioPeriodoId();

                    Log.d(TAG, " alumnoid "+ alumnosEvaluacionUi.getId());

                    NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();
                    EvaluacionProcesoC evaluacionProceso = SQLite.select()
                            .from(EvaluacionProcesoC.class)
                            .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                            .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(RubroEvaluacionUi.getId()))
                            //.and(EvaluacionProcesoC_Table.EquipoId.withTable().isNull())
                            .querySingle(databaseWrapper);

                    if (evaluacionProceso != null) {
                        if (notaUi.getResaltar()) {
                            evaluacionProceso.setSesionAprendizajeId(sesionAprendizajeId);
                            evaluacionProceso.setRubroEvalProcesoId(RubroEvaluacionUi.getId());
                            evaluacionProceso.setAlumnoId(alumnosEvaluacionUi.getId());
                            evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                            evaluacionProceso.setUsuarioAccionId(sessionUser.getUserId());
                            evaluacionProceso.setUsuarioCreacionId(sessionUser.getUserId());

                            switch (RubroEvaluacionUi.getTipoNota()) {
                                case TECLADO_NUMERICO:
                                    SelectorNumericoUi tecladoNumericoUi = (SelectorNumericoUi) notaUi;
                                    Log.d(TAG, "NOTA "+ Utils.formatearDecimales(tecladoNumericoUi.getNota(), 2));
                                    evaluacionProceso.setNota(Utils.formatearDecimales(tecladoNumericoUi.getNota(),2));
                                    break;
                                case TEXTO:
                                    evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                    evaluacionProceso.setEscala(notaUi.getContenido());
                                    evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                    break;
                                case IMAGEN:
                                    evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                    evaluacionProceso.setEscala(notaUi.getIcono());
                                    evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                    break;
                                case SELECTOR_NUMERICO:
                                    SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;
                                    evaluacionProceso.setNota(Utils.formatearDecimales(selectorNumericoUi.getNota(),2));
                                    break;
                            }
                            evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);
                            evaluacionProceso.setFormulaSinc(true);
                            boolean status = evaluacionProceso.save(databaseWrapper);
                            Log.d(TAG, " valortipo nota "+ alumnosEvaluacionUi.getNotaUi().getContenido()+ " key "+ evaluacionProceso.getKey());
                            if (!status)
                                throw new Error("no se guardó correctamente la EvaluacionProceso");
                        } else {
                            evaluacionProceso.setNota(0);
                            evaluacionProceso.setEscala(null);
                            evaluacionProceso.setValorTipoNotaId(null);
                            evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                            evaluacionProceso.setSesionAprendizajeId(rubroEvaluacionProcesoC.getSesionAprendizajeId());
                            evaluacionProceso.setFormulaSinc(true);
                            boolean success = evaluacionProceso.save(databaseWrapper);
                            if (!success) throw new Error("Error updating Evaluación processo!!!");
                        }
                    }

                }


            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callBack.localSuccess(alumnosEvaluacionUi, true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.localSuccess(alumnosEvaluacionUi, false);
            }
        }).build();

        transaction.execute();
    }

    @Override
    public void SaveEvaluacionList(final int sesionAprendizajeId, final RubroEvaluacionUi rubroEvaluacionUi, final List<AlumnosEvaluacionUi> alumnosEvaluacionUiList, final SaveListEvaluacionCallBack callBack) {

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                SessionUser sessionUser = SessionUser.getCurrentUser();
                if (sessionUser == null)
                    throw new Error("no se guardó correctamente el RubroEvaluacion por que no se encontro al Usuario");

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.is(rubroEvaluacionUi.getId()))
                        .querySingle();
                if (rubroEvaluacionProcesoC != null) {
                    rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save(databaseWrapper);

                    int calendarioPeriodoId = rubroEvaluacionProcesoC.getCalendarioPeriodoId();

                    for(AlumnosEvaluacionUi alumnosEvaluacionUi: alumnosEvaluacionUiList){
                        NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();
                        EvaluacionProcesoC evaluacionProceso = SQLite.select()
                                .from(EvaluacionProcesoC.class)
                                .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                                .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                                //.and(EvaluacionProcesoC_Table.EquipoId.withTable().isNull())
                                .querySingle(databaseWrapper);

                        if (evaluacionProceso != null) {
                            if (notaUi.getResaltar()) {
                                evaluacionProceso.setSesionAprendizajeId(sesionAprendizajeId);
                                evaluacionProceso.setRubroEvalProcesoId(rubroEvaluacionUi.getId());
                                evaluacionProceso.setAlumnoId(alumnosEvaluacionUi.getId());
                                evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                                evaluacionProceso.setUsuarioAccionId(sessionUser.getUserId());
                                evaluacionProceso.setUsuarioCreacionId(sessionUser.getUserId());

                                switch (rubroEvaluacionUi.getTipoNota()) {
                                    case TECLADO_NUMERICO:
                                        SelectorNumericoUi tecladoNumericoUi = (SelectorNumericoUi) notaUi;
                                        Log.d(TAG, "NOTA "+ Utils.formatearDecimales(tecladoNumericoUi.getNota(), 2));
                                        evaluacionProceso.setNota(Utils.formatearDecimales(tecladoNumericoUi.getNota(),2));
                                        break;
                                    case TEXTO:
                                        evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                        evaluacionProceso.setEscala(notaUi.getContenido());
                                        evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                        break;
                                    case IMAGEN:
                                        evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                        evaluacionProceso.setEscala(notaUi.getIcono());
                                        evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                        break;
                                    case SELECTOR_NUMERICO:
                                        SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;
                                        evaluacionProceso.setNota(Utils.formatearDecimales(selectorNumericoUi.getNota(),2));
                                        break;
                                }
                                evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);
                                evaluacionProceso.setFormulaSinc(true);
                                boolean status = evaluacionProceso.save(databaseWrapper);
                                if (!status)
                                    throw new Error("no se guardó correctamente la EvaluacionProceso");
                            } else {
                                evaluacionProceso.setNota(0);
                                evaluacionProceso.setEscala(null);
                                evaluacionProceso.setValorTipoNotaId(null);
                                evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                                evaluacionProceso.setSesionAprendizajeId(rubroEvaluacionProcesoC.getSesionAprendizajeId());
                                evaluacionProceso.setFormulaSinc(true);
                                boolean success = evaluacionProceso.save(databaseWrapper);
                                if (!success) throw new Error("Error updating Evaluación processo!!!");
                            }
                        }
                    }
                }


            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callBack.localSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.localSuccess(false);
            }
        }).build();

        transaction.execute();

    }

    @Override
    public void saveEvaluacionGrupo(final int sesionAprendizajeId, final RubroEvaluacionUi rubroEvaluacionUi, final GrupoEvaluacionUi grupoEvaluacionUi, final AlumnosEvaluacionUi alumnosEvaluacionUi, final SaveEvaluacionGrupoCallBack callBack) {

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.is(rubroEvaluacionUi.getId()))
                        .querySingle();

                if (rubroEvaluacionProcesoC != null) {
                    rubroEvaluacionProcesoC.setSyncFlag(RubroEvaluacionProcesoC.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save();

                    int calendarioPeriodoId = rubroEvaluacionProcesoC.getCalendarioPeriodoId();

                    NotaUi grupoNotaUi = grupoEvaluacionUi.getNotaUi();
                    if (grupoNotaUi != null) {

                        EquipoEvaluacionProcesoC equipoEvaluacionProceso = SQLite.select()
                                .from(EquipoEvaluacionProcesoC.class)
                                .where(EquipoEvaluacionProcesoC_Table.equipoId.withTable().is(grupoEvaluacionUi.getId()))
                                .and(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                                .querySingle();
                        if (equipoEvaluacionProceso != null) {
                            if (grupoNotaUi.getResaltar()) {
                                equipoEvaluacionProceso.setSesionAprendizajeId(sesionAprendizajeId);
                                equipoEvaluacionProceso.setRubroEvalProcesoId(rubroEvaluacionUi.getId());
                                equipoEvaluacionProceso.setEquipoId(grupoEvaluacionUi.getId());
                                equipoEvaluacionProceso.setEscala(grupoNotaUi.getContenido());
                                equipoEvaluacionProceso.setNota(Utils.formatearDecimales(grupoNotaUi.getNota(),2));
                                equipoEvaluacionProceso.setValorTipoNotaId(grupoNotaUi.getId());
                                equipoEvaluacionProceso.setEscala(grupoNotaUi.getContenido());
                                equipoEvaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_ADDED);
                                boolean sucess = equipoEvaluacionProceso.save();
                                if (!sucess)
                                    throw new Error("no se guardó correctamente la EquipoEvaluacionProceso");
                            } else {
                                equipoEvaluacionProceso.setNota(0);
                                equipoEvaluacionProceso.setEscala(null);
                                equipoEvaluacionProceso.setValorTipoNotaId(null);
                                equipoEvaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                                boolean success = equipoEvaluacionProceso.save();
                                if (!success)
                                    throw new Error("Error updating Evaluación processo!!!");
                            }

                            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integranteList = SQLite.select(Utils.f_allcolumnTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.ALL_COLUMN_PROPERTIES))
                                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable().is(grupoEvaluacionUi.getId()))
                                    .queryList();
                            Log.d(TAG, "integranteList Size: " + integranteList.size());

                            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC equipoIntegrante : integranteList) {

                                EvaluacionProcesoC evaluacionProceso = SQLite.select()
                                        .from(EvaluacionProcesoC.class)
                                        .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionUi.getId()))
                                        .and(EvaluacionProcesoC_Table.equipoId.eq(equipoIntegrante.getRubroEvaluacionEquipoId()))
                                        .and(EvaluacionProcesoC_Table.alumnoId.eq(equipoIntegrante.getPersonaId()))
                                        .querySingle();

                                if (evaluacionProceso != null) {

                                    Log.d(TAG, "grupoNotaUi.getResaltar(): " + grupoNotaUi.getResaltar());
                                    if (!grupoNotaUi.getResaltar()) {
                                        evaluacionProceso.setNota(0);
                                        evaluacionProceso.setEscala(null);
                                        evaluacionProceso.setValorTipoNotaId(null);
                                        evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                                        evaluacionProceso.setFormulaSinc(true);
                                        boolean success = evaluacionProceso.save();
                                        if (!success)
                                            throw new Error("Error updating Evaluación processo!!!");
                                        continue;
                                    }

                                    evaluacionProceso.setSesionAprendizajeId(sesionAprendizajeId);
                                    evaluacionProceso.setRubroEvalProcesoId(rubroEvaluacionUi.getId());
                                    evaluacionProceso.setAlumnoId(equipoIntegrante.getPersonaId());
                                    evaluacionProceso.setEscala(grupoNotaUi.getContenido());
                                    evaluacionProceso.setNota(Utils.formatearDecimales(grupoNotaUi.getNota(),2));
                                    evaluacionProceso.setValorTipoNotaId(grupoNotaUi.getId());
                                    evaluacionProceso.setEquipoId(equipoIntegrante.getRubroEvaluacionEquipoId());
                                    evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);

                                    switch (rubroEvaluacionUi.getTipoNota()) {
                                        case TECLADO_NUMERICO:
                                            SelectorNumericoUi tecladoNumericoUi = (SelectorNumericoUi) grupoNotaUi;
                                            evaluacionProceso.setNota(Utils.formatearDecimales(tecladoNumericoUi.getNota(),2));
                                            break;
                                        case TEXTO:
                                            evaluacionProceso.setValorTipoNotaId(grupoNotaUi.getId());
                                            evaluacionProceso.setEscala(grupoNotaUi.getContenido());
                                            evaluacionProceso.setNota(Utils.formatearDecimales(grupoNotaUi.getNota(),2));
                                            break;
                                        case IMAGEN:
                                            evaluacionProceso.setValorTipoNotaId(grupoNotaUi.getId());
                                            evaluacionProceso.setEscala(grupoNotaUi.getIcono());
                                            evaluacionProceso.setNota(Utils.formatearDecimales(grupoNotaUi.getNota(),2));
                                            break;
                                        case SELECTOR_NUMERICO:
                                            SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) grupoNotaUi;
                                            evaluacionProceso.setNota(Utils.formatearDecimales(selectorNumericoUi.getNota(),2));
                                            break;
                                    }
                                    evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);
                                    evaluacionProceso.setFormulaSinc(true);
                                    boolean sucessEvaluacionProceso = evaluacionProceso.save();
                                    if (!sucessEvaluacionProceso)
                                        throw new Error("no se guardó correctamente la EvaluacionProceso");
                                }
                            }
                        }
                    }


                    NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();

                    if (notaUi != null) {

                        From<EvaluacionProcesoC> tModelFrom = SQLite.select()
                                .from(EvaluacionProcesoC.class);

                        Where<EvaluacionProcesoC> tModelWhere = tModelFrom.where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                                .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()));
                        if (!TextUtils.isEmpty(alumnosEvaluacionUi.getGrupoId())) {
                            tModelWhere.and(EvaluacionProcesoC_Table.equipoId.withTable().is(alumnosEvaluacionUi.getGrupoId()));
                        }

                        EvaluacionProcesoC evaluacionProceso = tModelWhere.querySingle();

                        if (evaluacionProceso != null) {
                            if (!notaUi.getResaltar()) {
                                evaluacionProceso.setNota(0);
                                evaluacionProceso.setEscala(null);
                                evaluacionProceso.setValorTipoNotaId(null);
                                evaluacionProceso.setSyncFlag(EquipoEvaluacionProcesoC.FLAG_UPDATED);
                                evaluacionProceso.setFormulaSinc(true);
                                boolean success = evaluacionProceso.save();
                                if (!success)
                                    throw new Error("Error updating Evaluación processo!!!");
                            }
                            evaluacionProceso.setSesionAprendizajeId(sesionAprendizajeId);
                            evaluacionProceso.setRubroEvalProcesoId(rubroEvaluacionUi.getId());
                            evaluacionProceso.setAlumnoId(alumnosEvaluacionUi.getId());
                            evaluacionProceso.setEscala(notaUi.getContenido());
                            evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                            evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                            evaluacionProceso.setEquipoId(alumnosEvaluacionUi.getGrupoId());
                            evaluacionProceso.setEscala(notaUi.getContenido());
                            switch (rubroEvaluacionUi.getTipoNota()) {
                                case TECLADO_NUMERICO:
                                    SelectorNumericoUi tecladoNumericoUi = (SelectorNumericoUi) notaUi;
                                    evaluacionProceso.setNota(Utils.formatearDecimales(tecladoNumericoUi.getNota(),2));
                                    break;
                                case TEXTO:
                                    evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                    evaluacionProceso.setEscala(notaUi.getContenido());
                                    evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                    break;
                                case IMAGEN:
                                    evaluacionProceso.setValorTipoNotaId(notaUi.getId());
                                    evaluacionProceso.setEscala(notaUi.getIcono());
                                    evaluacionProceso.setNota(Utils.formatearDecimales(notaUi.getNota(),2));
                                    break;
                                case SELECTOR_NUMERICO:
                                    SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;
                                    evaluacionProceso.setNota(Utils.formatearDecimales(selectorNumericoUi.getNota(),2));
                                    break;
                            }
                            evaluacionProceso.setCalendarioPeriodoId(calendarioPeriodoId);
                            evaluacionProceso.setFormulaSinc(true);
                            boolean successEvaluacionProcesoId = evaluacionProceso.save();
                            Log.d(TAG, "EvaluacionProcesoId: " + evaluacionProceso.getKey());
                            if (!successEvaluacionProcesoId)
                                throw new Error("no se guardó correctamente la EvaluacionProceso");
                        }
                    }
                    callBack.localSuccess(grupoEvaluacionUi, alumnosEvaluacionUi, true);
                }
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {

            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.localSuccess(grupoEvaluacionUi, alumnosEvaluacionUi, false);
            }
        }).build();

        transaction.execute();
    }

    @Override
    public void getNotaEvaluacionList(RubroEvaluacionUi rubroEvaluacionUi, List<GrupoEvaluacionUi> itemEvaluacionUis, GetNotaEvaluacionListCallback callback) {
        //#region Listar Notas Evaluacion
        List<ValorTipoNotaC> valorTipoNotas = SQLite.select()
                .from(ValorTipoNotaC.class)
                .where(ValorTipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                .queryList();
        //#endregion

        for (GrupoEvaluacionUi grupoEvaluacionUi : itemEvaluacionUis) {

            List<NotaUi> grupo1_evaluacionProcesoUis = new ArrayList<>();
            for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                NotaUi notaUi = new NotaUi();
                notaUi.setId(valorTipoNota.getValorTipoNotaId());
                notaUi.setNotaDefault(valorTipoNota.getValorNumerico());
                notaUi.setContenido(valorTipoNota.getTitulo());
                notaUi.setTipo(valorTipoNota.getTipoNotaId());
                notaUi.setNota(valorTipoNota.getValorNumerico());
                notaUi.setNotaEvaluacion(valorTipoNota.getValorNumerico());

                switch (rubroEvaluacionUi.getTipoNota()){
                    case SELECTOR_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                        break;
                    case TEXTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                        break;
                    case IMAGEN:
                        notaUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                        break;
                    case TECLADO_NUMERICO:
                        notaUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                        break;
                    case DEFECTO:
                        notaUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                        break;
                }



                grupo1_evaluacionProcesoUis.add(notaUi);

                EquipoEvaluacionProcesoC equipoEvaluacionProcesoC = SQLite.select(Utils.f_allcolumnTable(EquipoEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(EquipoEvaluacionProcesoC.class)
                        .innerJoin(ValorTipoNotaC.class)
                        .on(EquipoEvaluacionProcesoC_Table.valorTipoNotaId.withTable().eq(ValorTipoNotaC_Table.valorTipoNotaId.withTable()))
                        .where(EquipoEvaluacionProcesoC_Table.equipoId.withTable().is(grupoEvaluacionUi.getId()))
                        .and(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                        .and(ValorTipoNotaC_Table.valorTipoNotaId.withTable().is(notaUi.getId()))
                        .querySingle();


                if (equipoEvaluacionProcesoC != null) {
                    notaUi.setResaltar(true);
                    notaUi.setNota(equipoEvaluacionProcesoC.getNota());
                    grupoEvaluacionUi.setNotaUi(notaUi);
                    Log.d(TAG, "setResaltarGrupo");

                }

            }

            //#region Teclado Numerico
            if (rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO ||
                    rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO) {

                grupo1_evaluacionProcesoUis.clear();
                TipoNotaC tipoNota = SQLite.select()
                        .from(TipoNotaC.class)
                        .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                        .querySingle();
                if (tipoNota != null) {
                    SelectorNumericoUi selectorNumericoUi = new SelectorNumericoUi();
                    selectorNumericoUi.setId(null);
                    selectorNumericoUi.setNota(0);
                    selectorNumericoUi.setValorMaximo(tipoNota.getValorMaximo());
                    selectorNumericoUi.setValorMinino(tipoNota.getValorMinino());
                    selectorNumericoUi.setLongitudPaso(tipoNota.getLongitudPaso());


                    switch (rubroEvaluacionUi.getTipoNota()){
                        case SELECTOR_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                            break;
                        case TEXTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                            break;
                        case IMAGEN:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                            break;
                        case TECLADO_NUMERICO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                            break;
                        case DEFECTO:
                            selectorNumericoUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                            break;
                    }

                    EscalaEvaluacionUI escalaEvaluacionUI = new EscalaEvaluacionUI();
                    selectorNumericoUi.setEscalaEvaluacionUi(escalaEvaluacionUI);
                    grupo1_evaluacionProcesoUis.add(selectorNumericoUi);

                    EscalaEvaluacion escalaEvaluacion = SQLite.select()
                            .from(EscalaEvaluacion.class)
                            .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNota.getEscalaEvaluacionId()))
                            .querySingle();
                    if (escalaEvaluacion != null) {
                        escalaEvaluacionUI.setId(escalaEvaluacion.getEscalaEvaluacionId());
                        escalaEvaluacionUI.setName(escalaEvaluacion.getNombre());
                        escalaEvaluacionUI.setValorMaximo(escalaEvaluacion.getValorMaximo());
                        escalaEvaluacionUI.setValorMinimo(escalaEvaluacion.getValorMinimo());
                    }

                    EquipoEvaluacionProcesoC equipoEvaluacionProceso = SQLite.select()
                            .from(EquipoEvaluacionProcesoC.class)
                            .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                            .and(EquipoEvaluacionProcesoC_Table.equipoId.withTable().is(grupoEvaluacionUi.getId()))
                            .querySingle();
                    if (equipoEvaluacionProceso != null) {
                        selectorNumericoUi.setResaltar(true);
                        selectorNumericoUi.setNota(equipoEvaluacionProceso.getNota());
                        grupoEvaluacionUi.setNotaUi(selectorNumericoUi);
                        Log.d(TAG, "setResaltar " + selectorNumericoUi.getNota());

                    }
                }


            }
            //#endregion

            grupoEvaluacionUi.setNotaUis(grupo1_evaluacionProcesoUis);
            if (grupoEvaluacionUi.getAlumnosEvaluacionUis() != null) {
                for (AlumnosEvaluacionUi alumnosEvaluacionUi : grupoEvaluacionUi.getAlumnosEvaluacionUis()) {


                    //#region Agregar Notas Evaluacion
                    List<NotaUi> alumnos_evaluacionProcesoUis = new ArrayList<>();
                    for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {
                        NotaUi notaUi = new NotaUi();
                        notaUi.setId(valorTipoNota.getValorTipoNotaId());
                        notaUi.setNotaDefault(valorTipoNota.getValorNumerico());
                        notaUi.setContenido(valorTipoNota.getTitulo());
                        notaUi.setTipo(valorTipoNota.getTipoNotaId());
                        notaUi.setNota(valorTipoNota.getValorNumerico());
                        notaUi.setNotaEvaluacion(valorTipoNota.getValorNumerico());

                        switch (rubroEvaluacionUi.getTipoNota()){
                            case SELECTOR_NUMERICO:
                                notaUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                                break;
                            case TEXTO:
                                notaUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                                break;
                            case IMAGEN:
                                notaUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                                break;
                            case TECLADO_NUMERICO:
                                notaUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                                break;
                            case DEFECTO:
                                notaUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                                break;
                        }

                        alumnos_evaluacionProcesoUis.add(notaUi);

                        EvaluacionProcesoC evaluacionProceso = SQLite.select(Utils.f_allcolumnTable(EvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                                .from(EvaluacionProcesoC.class)
                                .innerJoin(ValorTipoNotaC.class)
                                .on(EvaluacionProcesoC_Table.valorTipoNotaId.withTable().eq(ValorTipoNotaC_Table.valorTipoNotaId.withTable()))
                                .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                                .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                                .and(ValorTipoNotaC_Table.valorTipoNotaId.withTable().is(notaUi.getId()))
                                .and(EvaluacionProcesoC_Table.equipoId.withTable().is(grupoEvaluacionUi.getId()))
                                .querySingle();


                        if (evaluacionProceso != null) {
                            notaUi.setResaltar(true);
                            notaUi.setNota(evaluacionProceso.getNota());
                            alumnosEvaluacionUi.setNotaUi(notaUi);
                            Log.d(TAG, "setResaltar");

                        }

                    }
                    Log.d(TAG, "Error_Jse 1:" +rubroEvaluacionUi.getTitulo()+" "+ alumnos_evaluacionProcesoUis.size());
                    alumnosEvaluacionUi.setNotaUis(alumnos_evaluacionProcesoUis);
                    //#endregion Agregar Notas Evaluacion

                    //#region tecladoNumerico
                    if (rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.SELECTOR_NUMERICO ||
                            rubroEvaluacionUi.getTipoNota() == RubroEvaluacionUi.TipoNota.TECLADO_NUMERICO) {
                        alumnos_evaluacionProcesoUis.clear();
                        TipoNotaC tipoNota = SQLite.select()
                                .from(TipoNotaC.class)
                                .where(TipoNotaC_Table.tipoNotaId.is(rubroEvaluacionUi.getTipoNotaId()))
                                .querySingle();

                        if (tipoNota != null) {
                            SelectorNumericoUi selectorNumericoUi = new SelectorNumericoUi();
                            selectorNumericoUi.setId(null);
                            selectorNumericoUi.setNota(0);
                            selectorNumericoUi.setValorMaximo(tipoNota.getValorMaximo());
                            selectorNumericoUi.setValorMinino(tipoNota.getValorMinino());
                            selectorNumericoUi.setLongitudPaso(tipoNota.getLongitudPaso());
                            EscalaEvaluacionUI escalaEvaluacionUI = new EscalaEvaluacionUI();
                            selectorNumericoUi.setEscalaEvaluacionUi(escalaEvaluacionUI);

                            switch (rubroEvaluacionUi.getTipoNota()){
                                case SELECTOR_NUMERICO:
                                    selectorNumericoUi.setTipoNota(NotaUi.TipoNota.SELECTOR_NUMERICO);
                                    break;
                                case TEXTO:
                                    selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TEXTO);
                                    break;
                                case IMAGEN:
                                    selectorNumericoUi.setTipoNota(NotaUi.TipoNota.IMAGEN);
                                    break;
                                case TECLADO_NUMERICO:
                                    selectorNumericoUi.setTipoNota(NotaUi.TipoNota.TECLADO_NUMERICO);
                                    break;
                                case DEFECTO:
                                    selectorNumericoUi.setTipoNota(NotaUi.TipoNota.DEFECTO);
                                    break;
                            }

                            alumnos_evaluacionProcesoUis.add(selectorNumericoUi);

                            EscalaEvaluacion escalaEvaluacion = SQLite.select()
                                    .from(EscalaEvaluacion.class)
                                    .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNota.getEscalaEvaluacionId()))
                                    .querySingle();
                            if (escalaEvaluacion != null) {
                                escalaEvaluacionUI.setId(escalaEvaluacion.getEscalaEvaluacionId());
                                escalaEvaluacionUI.setName(escalaEvaluacion.getNombre());
                                escalaEvaluacionUI.setValorMaximo(escalaEvaluacion.getValorMaximo());
                                escalaEvaluacionUI.setValorMinimo(escalaEvaluacion.getValorMinimo());
                            }

                            EvaluacionProcesoC evaluacionProceso = SQLite.select()
                                    .from(EvaluacionProcesoC.class)
                                    .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(alumnosEvaluacionUi.getId()))
                                    .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().is(rubroEvaluacionUi.getId()))
                                    .and(EvaluacionProcesoC_Table.equipoId.withTable().is(grupoEvaluacionUi.getId()))
                                    .querySingle();

                            if (evaluacionProceso != null) {
                                selectorNumericoUi.setResaltar(true);
                                selectorNumericoUi.setNota(evaluacionProceso.getNota());
                                alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
                                Log.d(TAG, "setResaltar " + selectorNumericoUi.getNota());

                            }
                        }
                    }
                    //#endregion tecladoNumerico
                }
            }
        }

        callback.onRecursoLoad(itemEvaluacionUis);
    }

    @Override
    public boolean recalcularMediaDv(String rubroEvaluacionProcesoId) {
        try {
            boolean success = evaluacionProcesoDao.f_mediaDesviacionEstandar(rubroEvaluacionProcesoId);
            return success;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }


    }

    @Override
    public void publicarEvaluacion(OptionPublicar optionPublicar) {
        try {
            EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.key.eq(optionPublicar.getEvaluacionId()))
                    .querySingle();

            Log.d(TAG, "optionPublicar: " + optionPublicar.getEvaluacionId());

            if(evaluacionProcesoC!=null){

                evaluacionProcesoC.setPublicado(optionPublicar.isSelected()? 1 : 0);
                evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                evaluacionProcesoC.save();

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                        .querySingle();
                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save();
                }


            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateEvaluacionFormula(final UpdateEvaluacionFormulaCallback callback) {
        evaluacionProcesoDao.onUpdateEvaluacionFormula(new EvaluacionProcesoDao.UpdateEvaluacionFormulaCallback() {
            @Override
            public void onPreload() {
                callback.onPreLoad();
            }

            @Override
            public void onSucces() {
                callback.onLoad(true);
            }

            @Override
            public void onError() {
                callback.onLoad(true);
            }
        });
    }

    private void addListDimencion(AlumnosEvaluacionUi alumnoProcesoUi, int entidadId, int georeferenciaId) {
        List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumno(alumnoProcesoUi.getId(), entidadId, georeferenciaId);
        List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();
        Log.d(TAG, "dimensionObservadaList Size:" + dimensionObservadaList.size());
        for (DimensionObservada dimensionObservada : dimensionObservadaList) {
            Dimension dimension = SQLite.select()
                    .from(Dimension.class)
                    .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                    .querySingle();


            if (dimensionObservada != null) {
                DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
                dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
                int valor = (int) (dimensionObservada.getValor() * 100);
                dimensionObservadaUi.setValor(valor);
                if (dimension == null) continue;
                DimensionUi dimensionUi = new DimensionUi();
                dimensionUi.setId(dimension.getDimensionId());
                dimensionUi.setNombre(dimension.getNombre());
                dimensionUi.setDescripcion(dimension.getDescripcion());
                dimensionUi.setInstrumentoEvalId(dimension.getInstrumentoEvalId());
                dimensionUi.setSigla(dimension.getSigla());
                dimensionUi.setEnfoque(dimension.getEnfoque());
                dimensionUi.setConfiabilidad(dimension.getConfiabilidad());
                dimensionUi.setIntervaloInicio(dimension.getIntervaloInicio());
                dimensionUi.setIntervaloFin(dimension.getIntervaloFin());
                dimensionUi.setIncluidoIInicio(dimension.getIncluidoIInicio());
                dimensionUi.setIncluidoIFin(dimension.getIncluidoIFin());
                dimensionUi.setColor(dimension.getColor());
                dimensionUi.setIcono(dimension.getIcono());
                dimensionUi.setMedida(dimension.getMedida());
                dimensionUi.setOrden(dimension.getOrden());
                dimensionObservadaUi.setDimensionUi(dimensionUi);

                dimensionObservadaUiList.add(dimensionObservadaUi);
            }

            List<DimensionObservadaUi> newDimensionObservadaUiList = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
            if(newDimensionObservadaUiList.size()>=1)alumnoProcesoUi.setDimensionObservadaUi(newDimensionObservadaUiList.get(0));
        }
    }

    private List<DimensionObservadaUi> getNewdimensionObservadaUiListOrderValor(List<DimensionObservadaUi> dimensionObservadaUiList) {

        List<DimensionObservadaUi> dimensionObservadaUiListNew = new ArrayList<>(dimensionObservadaUiList);
        if(dimensionObservadaUiList.size()>=4){
            try {
                int cantidadTotal = 0;
                DimensionObservadaUi dimensionObservadaUisUno = dimensionObservadaUiList.get(0);
                DimensionObservadaUi dimensionObservadaUisDos = dimensionObservadaUiList.get(1);
                DimensionObservadaUi dimensionObservadaUisTres = dimensionObservadaUiList.get(2);
                DimensionObservadaUi dimensionObservadaUisCuatro = dimensionObservadaUiList.get(3);
                int areaUno = (int)(dimensionObservadaUisUno.getValor() * dimensionObservadaUisDos.getValor() / 2);
                int areaDos = (int)(dimensionObservadaUisDos.getValor() * dimensionObservadaUisTres.getValor() / 2);
                int areaTres = (int)(dimensionObservadaUisTres.getValor() * dimensionObservadaUisCuatro.getValor() / 2);
                int areaCuatro = (int)(dimensionObservadaUisCuatro.getValor() * dimensionObservadaUisUno.getValor() / 2);
                cantidadTotal = areaUno + areaDos + areaTres + areaCuatro;

                dimensionObservadaUisUno.setArea(areaUno);
                float porsentajeOne = (float) areaUno / cantidadTotal;
                dimensionObservadaUisUno.setPorcentaje((int) (porsentajeOne*100));

                dimensionObservadaUisDos.setArea(areaDos);
                float porsentajeDos =(float) areaDos/cantidadTotal;
                porsentajeDos = porsentajeDos * 100;
                dimensionObservadaUisDos.setPorcentaje((int)porsentajeDos);
                dimensionObservadaUisTres.setArea(areaTres);
                float porsentajeTres = (float)areaTres/cantidadTotal;
                porsentajeTres = porsentajeTres * 100;
                dimensionObservadaUisTres.setPorcentaje((int)porsentajeTres);
                dimensionObservadaUisCuatro.setArea(areaCuatro);
                float porsentajeCuatro = (float)areaCuatro/cantidadTotal;
                porsentajeCuatro = porsentajeCuatro * 100;
                dimensionObservadaUisCuatro.setPorcentaje((int)porsentajeCuatro);


                Collections.sort(dimensionObservadaUiListNew, new Comparator<DimensionObservadaUi>() {
                    @Override
                    public int compare(DimensionObservadaUi p1, DimensionObservadaUi p2) {
                        return Integer.compare(p2.getArea(), p1.getArea());
                    }
                });

                int posicion2=0;
                for (DimensionObservadaUi dimensionObservadaUi: dimensionObservadaUiListNew){
                    posicion2++;
                    dimensionObservadaUi.setPoscion(posicion2);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return dimensionObservadaUiListNew;

    }
}
