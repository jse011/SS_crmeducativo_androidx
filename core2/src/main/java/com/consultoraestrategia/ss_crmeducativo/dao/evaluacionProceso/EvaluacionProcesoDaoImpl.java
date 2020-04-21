package com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public class EvaluacionProcesoDaoImpl extends BaseDaoImpl<EvaluacionProcesoC,EvaluacionProcesoC_Table> implements EvaluacionProcesoDao {

    private static EvaluacionProcesoDao mInstance;
    private PersonaDao personaDao;
    private RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;
    private EscalaEvaluacionDao escalaEvaluacionDao;
    private TipoNotaDao tipoNotaDao;
    private TiposDao tiposDao;
    private final static String TAG = EvaluacionProcesoDaoImpl.class.getSimpleName();

    public EvaluacionProcesoDaoImpl(PersonaDao personaDao, RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao, EscalaEvaluacionDao escalaEvaluacionDao, TipoNotaDao tipoNotaDao, TiposDao tiposDao) {
        this.personaDao = personaDao;
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
        this.escalaEvaluacionDao = escalaEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
        this.tiposDao = tiposDao;
    }

    public static EvaluacionProcesoDao getInstance(PersonaDao personaDao, RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao, EscalaEvaluacionDao escalaEvaluacionDao, TipoNotaDao tipoNotaDao, TiposDao tiposDao) {
        if (mInstance == null) {
            mInstance = new EvaluacionProcesoDaoImpl(personaDao, rubroEvalRNPFormulaDao, escalaEvaluacionDao, tipoNotaDao, tiposDao);
        }
        return mInstance;
    }

    @Override
    public List<EvaluacionProcesoC> getListaEvaluacionProceso(String rubroProcesoKey,int personaKey) {
        return  SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroProcesoKey))
                .and(EvaluacionProcesoC_Table.alumnoId.is(personaKey))
                .queryList();
    }

    @Override
    public EvaluacionProcesoC getEvaluacionProceso(String rubroProcesoKey, int personaKey) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroProcesoKey))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(personaKey))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc()
                )
                .querySingle();
    }

    @Override
    public List<EvaluacionProcesoC> getListaEvaluacionProcesoRubroFormula(String rubroProcesKey, int personaKey) {
        return  SQLite.select()
                .from(EvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(Integer.valueOf(personaKey)))
                .and(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(rubroProcesKey))
                .queryList();
    }

    @Override
    protected Class<EvaluacionProcesoC> getEntityClass() {
        return EvaluacionProcesoC.class;
    }

    @Override
    protected Class<EvaluacionProcesoC_Table> getTableclass() {
        return EvaluacionProcesoC_Table.class;
    }

    public boolean crearEvaluacionProceso(String rubroEvalProcesoId ,int cargaCursoId){
        boolean success = true;
        try {
            List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            for (PersonaContratoQuery persona : personaList) {
                EvaluacionProcesoC evaluacionProceso = new EvaluacionProcesoC();
                evaluacionProceso.setRubroEvalProcesoId(rubroEvalProcesoId);
                evaluacionProceso.setAlumnoId(persona.getPersonaId());
                evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                boolean successEvalProcesoAlumn = evaluacionProceso.save();
                if (!successEvalProcesoAlumn) throw new Error("Error creating eval proceso for alumn!!!");
            }
        }catch (Exception e){
            success = false;
        }

        return success;
    }

    @Override
    public boolean crearEvaluacionProcesoFormula(String rubroEvalProcesoId, int cargaCursoId, DatabaseWrapper databaseWrapper) {
        boolean success = true;

            List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId, databaseWrapper);
            boolean successEvalProcesoAlumn = false;
            boolean successEvaluacion = false;
            for (PersonaContratoQuery persona : personaList) {
                EvaluacionProcesoC evaluacionProceso = new EvaluacionProcesoC();
                evaluacionProceso.setRubroEvalProcesoId(rubroEvalProcesoId);
                evaluacionProceso.setAlumnoId(persona.getPersonaId());
                evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                successEvalProcesoAlumn = evaluacionProceso.save(databaseWrapper);
                if (!successEvalProcesoAlumn){
                    success = false;
                    break;
                }
                successEvaluacion = evaluarRubroFormulaPersona(rubroEvalProcesoId, persona.getPersonaId());
                if (!successEvaluacion){
                    success = false;
                    break;
                }
            }

        return success;
    }

    @Override
    public boolean evaluarRubroFormulaPersona(String rubroEvalProcesoId, int personaId){
        return evaluarRubroFormulaPersona(rubroEvalProcesoId, personaId, null);
    }

    private boolean evaluarRubroFormulaPersona(String rubroEvalProcesoId, int personaId, DatabaseWrapper databaseWrapper) {
        //Log.d(TAG, "evaluarRubroFormulaPersona "+ rubroEvalProcesoId+ " ALUMNO "+ personaId);
        boolean success = true;

        try {

            int TIPO_FORMULA_MEDIA_PONDERADA = 413;
            int TIPO_FORMULA_MEDIA_ARITMETICA = 414;
            int TIPO_FORMULA_SUMA = 415;
            int TIPO_FORMULA_MAXIMO = 416;
            int TIPO_FORMULA_MINIMO = 417;
            int TIPO_FORMULA_MODA = 479;

            Where<RubroEvaluacionProcesoC> procesoCWhere = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalProcesoId));


            RubroEvaluacionProcesoC rubroFormula;
            if(databaseWrapper!=null){
                rubroFormula = procesoCWhere.querySingle(databaseWrapper);
            }else {
                rubroFormula = procesoCWhere.querySingle();
            }


            String tipoNotaId= "";
            int tipoFormulaId = 0;
            int valorRedondedoId = 0;
            int calendarioperiodoId = 0;
            if(rubroFormula!=null){
                tipoNotaId = rubroFormula.getTipoNotaId();
                tipoFormulaId = rubroFormula.getTipoFormulaId();
                valorRedondedoId = rubroFormula.getValorRedondeoId();
                calendarioperiodoId = rubroFormula.getCalendarioPeriodoId();
                rubroFormula.setSyncFlag(BaseEntity.FLAG_UPDATED);
                if(databaseWrapper!=null){
                    rubroFormula.save(databaseWrapper);
                }else {
                    rubroFormula.save();
                }

            }

            Where<EvaluacionProcesoC> evaluacionProcesoCWhere = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvalProcesoId))
                    .and(EvaluacionProcesoC_Table.alumnoId.eq(personaId))
                    .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc());
            EvaluacionProcesoC evaluacionProcesoC;

            if(databaseWrapper!=null){
                evaluacionProcesoC = evaluacionProcesoCWhere.querySingle(databaseWrapper);
            }else {
                evaluacionProcesoC = evaluacionProcesoCWhere.querySingle();
            }


            Where<EscalaEvaluacion> escalaEvaluacionWhere = SQLite.select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                    .from(EscalaEvaluacion.class)
                    .innerJoin(TipoNotaC.class)
                    .on(EscalaEvaluacion_Table.escalaEvaluacionId.withTable().eq(TipoNotaC_Table.escalaEvaluacionId.withTable()))
                    .where(TipoNotaC_Table.key.withTable().withTable().is(tipoNotaId));
            EscalaEvaluacion escalaEvaluacion;

            if(databaseWrapper!=null){
                escalaEvaluacion = escalaEvaluacionWhere.querySingle(databaseWrapper);
            }else {
                escalaEvaluacion = escalaEvaluacionWhere.querySingle();
            }



            Where<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCWhere = SQLite.select()
                    .from(RubroEvalRNPFormulaC.class)
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(rubroEvalProcesoId));
            List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList;
            if(databaseWrapper!=null){
                rubroEvalRNPFormulaCList = rubroEvalRNPFormulaCWhere.queryList(databaseWrapper);
            }else {
                rubroEvalRNPFormulaCList = rubroEvalRNPFormulaCWhere.queryList();
            }

            //Log.d(TAG, "rubroEvalRNPFormulaCList "+ rubroEvalRNPFormulaCList.size());
            //Log.d(TAG, "personaId "+ personaId);
            double notasFinales = 0D;
            if (tipoFormulaId == TIPO_FORMULA_MEDIA_PONDERADA) {
                notasFinales = initMediaPonderada(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (tipoFormulaId == TIPO_FORMULA_MEDIA_ARITMETICA) {
                notasFinales = initMediaArimetica(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId,databaseWrapper);
            } else if (tipoFormulaId == TIPO_FORMULA_SUMA) {
                notasFinales = initFormulaSuma(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (tipoFormulaId == TIPO_FORMULA_MAXIMO) {
                notasFinales = initFormulaMaximo(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (tipoFormulaId == TIPO_FORMULA_MINIMO) {
                notasFinales = initFormulaMinima(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (tipoFormulaId == TIPO_FORMULA_MODA) {
                notasFinales = initFormulaModa(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            }

            //*region redondeo

            Where<Tipos> tiposWhere = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(valorRedondedoId));

            Tipos tipos;
            if(databaseWrapper!=null){
                tipos = tiposWhere.querySingle(databaseWrapper);
            }else {
                tipos = tiposWhere.querySingle();
            }
            //  Log.d(TAG,"tipos "+ tipos.getNombre() );
            if(tipos!=null && tipos.getTipoId() != 425){
                double valorRedondeo = Double.valueOf(tipos.getNombre());
                BigDecimal bigDecimal  = new BigDecimal(notasFinales);
                long iPart = bigDecimal.longValue();
                double fraccion = bigDecimal.remainder(BigDecimal.ONE).doubleValue();
                //0.45  >= 0.25
                if(fraccion > valorRedondeo){
                    notasFinales = iPart + 1D;
                }else {
                    notasFinales = iPart;
                }
                //Log.d(TAG,"Tansfromacion"+notasFinales );
            }

            //*endregion redondeo

            ValorTipoNotaC valorTipoNota = initTipoNotaValor(tipoNotaId,notasFinales, databaseWrapper);

            if(evaluacionProcesoC!=null){
                //Log.d(TAG,"notasFinales "+notasFinales );
                //Log.d(TAG,"redondeado notasFinales "+Utils.formatearDecimales(notasFinales, 2) );
                evaluacionProcesoC.setNota(Utils.formatearDecimales(notasFinales, 2));
                if(valorTipoNota != null){
                    evaluacionProcesoC.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());
                }else {
                    evaluacionProcesoC.setValorTipoNotaId("");
                }
                evaluacionProcesoC.setCalendarioPeriodoId(calendarioperiodoId);
                evaluacionProcesoC.setSyncFlag(EvaluacionProcesoC.FLAG_UPDATED);
                if(databaseWrapper!=null){
                    evaluacionProcesoC.save(databaseWrapper);
                }else {
                    evaluacionProcesoC.save();
                }

            }
        } catch (Exception error){
            error.printStackTrace();
            success = false;
        }
        return success;

    }


    @Override
    public List<EvaluacionProcesoC> getEvaluacionProcesoRubrica(List<String> rubroEvalProcesoKeyList, List<Integer> personIdList) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .and(EvaluacionProcesoC_Table.alumnoId.in(personIdList))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .queryList();
    }

    @Override
    public List<EvaluacionProcesoC> getEvaluacionProcesoRubricaEquipo(List<String> rubroEvalProcesoKeyList, List<String> equipoRubroKeys) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .and(EvaluacionProcesoC_Table.equipoId.in(equipoRubroKeys))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .queryList();
    }

    private double initMediaPonderada(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double notaAcumulacion = 0;
        double notaPesoAcumulacion = 0;
        double pesoTotal = 0;
        double notass = 0;

        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            try {
                EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());


                //1.- valor de los hijos
                if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue= Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }

                double pesoActual = rnpFormula.getPeso();
                notaAcumulacion += notaValue;
                notaPesoAcumulacion += notaValue * pesoActual;
                pesoTotal += pesoActual;
                notass = notaPesoAcumulacion / pesoTotal;
            }catch (Exception e){
                e.printStackTrace();
            e.getCause();}

        }
        return notass;
    }

    private double initMediaArimetica(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId, DatabaseWrapper databaseWrapper) {
        double notaAcumulacion = 0;
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;


            Where<EscalaEvaluacion> escalaEvaluacionWhere = SQLite.select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                    .from(EscalaEvaluacion.class)
                    .innerJoin(TipoNotaC.class)
                    .on(EscalaEvaluacion_Table.escalaEvaluacionId.withTable().eq(TipoNotaC_Table.escalaEvaluacionId.withTable()))
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable().eq(TipoNotaC_Table.key.withTable()))
                    .where(RubroEvaluacionProcesoC_Table.key.withTable().is(rnpFormula.getRubroEvaluacionSecId()));

            EscalaEvaluacion escalaEvaluacionAsociados;
            if(databaseWrapper!=null){
                escalaEvaluacionAsociados = escalaEvaluacionWhere.querySingle(databaseWrapper);
            }else {
                escalaEvaluacionAsociados = escalaEvaluacionWhere.querySingle();
            }

            Where<EvaluacionProcesoC> evaluacionProcesoCWhere = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rnpFormula.getRubroEvaluacionSecId()))
                    .and(EvaluacionProcesoC_Table.alumnoId.eq(personaId))
                    .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc());

            EvaluacionProcesoC evaluacionProcesoC;

            if(databaseWrapper!=null){
                evaluacionProcesoC = evaluacionProcesoCWhere.querySingle(databaseWrapper);
            }else {
                evaluacionProcesoC = evaluacionProcesoCWhere.querySingle();
            }

            //Log.d(TAG, "rnpFormula.getRubroEvaluacionSecId() " +rnpFormula.getRubroEvaluacionSecId() + " personaId: " + personaId);
            //1.- valor de los hijos
            if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            notaAcumulacion += notaValue;
            notass = notaAcumulacion / rubroEvalRNPFormulaCList.size();
        }
        return notass;
    }

    private double initFormulaSuma(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }

            notass += notaValue;
        }
        return notass;
    }

    private double initFormulaMinima(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double minimoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            double notaValues = notaValue;
            list.add(notaValues);
            minimoValor = Collections.min(list);
            notass = minimoValor;
        }

        return notass;
    }

    private double initFormulaMaximo(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double maximoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;

            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            double notaValuea = notaValue;
            list.add(notaValuea);
            maximoValor = Collections.max(list);
            Log.d(TAG, "SIZEDOUBLE: " + list.size());
            notass = maximoValor;
        }
        return notass;
    }

    private double initFormulaModa(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        Integer[] integer;
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (evaluacionProcesoC!=null && escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            int notaRedondeada = (int) Math.round(notaValue);
            //  aDoubleMModa = new Double[]{notaValue};
            integer = new Integer[]{notaRedondeada};
            notass = Utils.moda(integer);
        }
        return notass;
    }

    private ValorTipoNotaC initTipoNotaValor(String tipoNotaId, double notass, DatabaseWrapper databaseWrapper) {
        ValorTipoNotaC valorTipoNota = null;
        try {

            Where<TipoNotaC> tipoNotaCWhere = SQLite.select()
                    .from(TipoNotaC.class)
                    .where(TipoNotaC_Table.key.eq(tipoNotaId));

            TipoNotaC tipoNotaC;
            if(databaseWrapper!=null){
                tipoNotaC = tipoNotaCWhere.querySingle(databaseWrapper);
            }else {
                tipoNotaC = tipoNotaCWhere.querySingle();
            }

            if(tipoNotaC!=null){

                Where<ValorTipoNotaC> valorTipoNotaCWhere = SQLite.select()
                        .from(ValorTipoNotaC.class)
                        .where(ValorTipoNotaC_Table.tipoNotaId.eq(tipoNotaC.getKey()));

                if(databaseWrapper!=null){
                    tipoNotaC.setValorTipoNotaList(valorTipoNotaCWhere.queryList(databaseWrapper));
                }else {
                    tipoNotaC.setValorTipoNotaList(valorTipoNotaCWhere.queryList());
                }

                List<ValorTipoNotaC> valorTipoNotaListRubroFinales = tipoNotaC.getValorTipoNotaList();
                if(tipoNotaC.getTipoId() == TipoNotaC.SELECTOR_VALORES ||
                        tipoNotaC.getTipoId() == TipoNotaC.SELECTOR_ICONOS){


                    if(tipoNotaC.isIntervalo()){
                        for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaListRubroFinales) {
                            if (itemValorTipoNota.getLimiteInferior() <= notass && itemValorTipoNota.getLimiteSuperior() >= notass) {
                                valorTipoNota = itemValorTipoNota;
                            }
                        }
                    }else {
                        long notaEntera = Math.round(notass);
                        ValorTipoNotaC minimoValorNumerico = null;
                        for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaListRubroFinales) {
                            if (itemValorTipoNota.getValorNumerico() == notaEntera) {
                                valorTipoNota = itemValorTipoNota;
                            }
                            if(minimoValorNumerico==null||minimoValorNumerico.getValorNumerico() > itemValorTipoNota.getValorNumerico()){
                                minimoValorNumerico = itemValorTipoNota;
                            }
                        }
                        if(valorTipoNota==null){
                            valorTipoNota = minimoValorNumerico;
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return valorTipoNota;
    }
    public  boolean f_mediaDesviacionEstandar(String key) {

        boolean status=false;
        long count =SQLite.selectCountOf()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                .count();

        Log.d(TAG, "evaluacionProcesoCList "+ count);
        if(count!=0){
            status= f_mediaDesviacionEstandarFormula(key);
        }else status=true;


       return status;
    }

    private boolean f_mediaDesviacionEstandarFormula(String key, DatabaseWrapper databaseWrapper) {
        List<Double>listNotas= new ArrayList<>();
        double media=0.0;
        List<EvaluacionProcesoC>evaluciones= SQLite.select().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(key)).
                        queryList(databaseWrapper);

        for(EvaluacionProcesoC e: evaluciones){
            media+=e.getNota()/evaluciones.size();
            listNotas.add(e.getNota());
        }
        Log.d(TAG, "media " + media);
        double stdDev = Utils.stdDev(media, listNotas);
        Log.d(TAG, "desviacion " + stdDev);

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select().from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(key)).querySingle();

        if (rubroEvaluacionProcesoC == null) return false;
        rubroEvaluacionProcesoC.setPromedio(Utils.formatearDecimales(media, 2));
        rubroEvaluacionProcesoC.setDesviacionEstandar(Utils.formatearDecimales(stdDev, 2));
        rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        rubroEvaluacionProcesoC.update(databaseWrapper);
        return true;
    }
    @Override
    public  boolean f_mediaDesviacionEstandarFormula(String key) {
        List<Double>listNotas= new ArrayList<>();
        double media=0.0;
        List<EvaluacionProcesoC>evaluciones= SQLite.select().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(key)).
                        queryList();

        for(EvaluacionProcesoC e: evaluciones){
            media+=e.getNota()/evaluciones.size();
            listNotas.add(e.getNota());
        }
        Log.d(TAG, "media " + media);
        double stdDev = Utils.stdDev(media, listNotas);
        Log.d(TAG, "desviacion " + stdDev);

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select().from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(key)).querySingle();

        if (rubroEvaluacionProcesoC == null) return false;
        rubroEvaluacionProcesoC.setPromedio(Utils.formatearDecimales(media, 2));
        rubroEvaluacionProcesoC.setDesviacionEstandar(Utils.formatearDecimales(stdDev, 2));
        rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        rubroEvaluacionProcesoC.update();
        return true;
    }


    @Override
    public void onUpdateEvaluacionFormula(final UpdateEvaluacionFormulaCallback callback) {
        Log.d(getClass().getSimpleName(), "onUpdateEvaluacionFormula");
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {


                List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                        .from(EvaluacionProcesoC.class)
                        .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                        .queryList(databaseWrapper);

                if(!evaluacionProcesoCList.isEmpty())callback.onPreload();




                //region update sesion evaluacion
                List<SesionAprendizaje> sesionAprendizajeList = SQLite.select(Utils.f_allcolumnTable(SesionAprendizaje_Table.ALL_COLUMN_PROPERTIES))
                        .from(SesionAprendizaje.class)
                        .innerJoin(RubroEvaluacionProcesoC.class)
                        .on(SesionAprendizaje_Table.sesionAprendizajeId.withTable()
                                .eq(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.withTable()))
                        .innerJoin(EvaluacionProcesoC.class)
                        .on(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                        .where(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().eq(0))
                        .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                        .and(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                        .queryList(databaseWrapper);


                Log.d(TAG,"sesionAprendizajeList size: " + sesionAprendizajeList.size());


                for (SesionAprendizaje sesionAprendizaje : sesionAprendizajeList){
                    RubroEvaluacionProcesoC rubroEvaluacionProcesoC = null;

                    rubroEvaluacionProcesoC = SQLite.select()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(sesionAprendizaje.getSesionAprendizajeId()))
                            .and(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                            .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.desc())
                            .querySingle(databaseWrapper);

                    if(rubroEvaluacionProcesoC == null){
                        rubroEvaluacionProcesoC = SQLite.select()
                                .from(RubroEvaluacionProcesoC.class)
                                .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(sesionAprendizaje.getSesionAprendizajeId()))
                                .and(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL))
                                .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                                //.orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.desc())
                                .querySingle(databaseWrapper);
                    }

                    if(rubroEvaluacionProcesoC == null){
                        sesionAprendizaje.setEvaluados(0);
                        sesionAprendizaje.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                        sesionAprendizaje.save(databaseWrapper);
                    }else {
                        List<EvaluacionProcesoC> evaluacionSesionProcesoCList = SQLite.select()
                                .from(EvaluacionProcesoC.class)
                                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                                .queryList(databaseWrapper);

                        int evaluados = 0;
                        for (EvaluacionProcesoC evaluacionProcesoC :evaluacionSesionProcesoCList){
                            if(evaluacionProcesoC.getNota() > 0){
                                evaluados++;
                            }
                        }

                        sesionAprendizaje.setEvaluados(evaluados);
                        sesionAprendizaje.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                        sesionAprendizaje.save(databaseWrapper);
                    }


                }
                //endregion


                List<String> rubrosAsociadosIdList = new ArrayList<>();
                for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList)rubrosAsociadosIdList.add(evaluacionProcesoC.getRubroEvalProcesoId());
                Log.d(TAG,"size: " + evaluacionProcesoCList.size());


                IProperty[] parametros = Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES);
                List<RubroEvalRNPFormulaC> rubroFormulaList = SQLite.select(parametros)
                        .from(RubroEvalRNPFormulaC.class)
                        .innerJoin(RubroEvaluacionProcesoC.class)
                        .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                                .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                        .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                        .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                        //.and(RubroEvaluacionProcesoC_Table.silaboEventoId.withTable().eq(SilaboEvento.ESTADO_PUBLICADO))
                        .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                        .groupBy(parametros)
                        .queryList(databaseWrapper);


                Log.d(TAG,"rubroFormulaList: " + rubroFormulaList.size());
                Log.d(TAG,"time: " + Utils.getHoraConFecha(new Date().getTime()) + " "+ Calendar.getInstance().get(Calendar.SECOND) + " "+Calendar.getInstance().get(Calendar.MILLISECOND));
                List<String> listIdRubrosActualizados = new ArrayList<>();
                for (EvaluacionProcesoC itemEvaluacionProcesoC: evaluacionProcesoCList){

                    for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC: rubroFormulaList){
                        if(itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId())){
                            boolean success = evaluarRubroFormulaPersona(itemRubroEvalRNPFormulaC.getRubroEvaluacionPrimId(),itemEvaluacionProcesoC.getAlumnoId() ,databaseWrapper);
                            if(success){
                                //Log.d(EvaluacionFormulaLocal.class.getSimpleName(),"success: " + success);
                                int poscion = listIdRubrosActualizados.indexOf(itemRubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                                if(poscion!=-1)listIdRubrosActualizados.add(itemRubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                            }
                        }

                    }

                    itemEvaluacionProcesoC.setFormulaSinc(false);
                    itemEvaluacionProcesoC.save(databaseWrapper);
                }
                Log.d(TAG,"evaluacion finished time: " + Utils.getHoraConFecha(new Date().getTime()) + " "+ Calendar.getInstance().get(Calendar.SECOND) + " "+Calendar.getInstance().get(Calendar.MILLISECOND));
                for (String itemId: listIdRubrosActualizados){
                    RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.key.eq(itemId))
                            .querySingle(databaseWrapper);
                    if(rubroEvaluacionProcesoC!=null){
                        rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                        rubroEvaluacionProcesoC.save(databaseWrapper);
                    }
                }
                Log.d(TAG," actulizar rubros finished time: " + Utils.getHoraConFecha(new Date().getTime()) + " "+ Calendar.getInstance().get(Calendar.SECOND) + " "+Calendar.getInstance().get(Calendar.MILLISECOND));
                for(RubroEvalRNPFormulaC rubroformula : rubroFormulaList){
                //    Log.d(TAG, "rubroformula id  "+ rubroformula.getRubroEvaluacionPrimId());
                    f_mediaDesviacionEstandarFormula(rubroformula.getRubroEvaluacionPrimId(),databaseWrapper);
                }

                Log.d(TAG," actulizar rubros formula finished time: " + Utils.getHoraConFecha(new Date().getTime()) + " "+ Calendar.getInstance().get(Calendar.SECOND) + " "+Calendar.getInstance().get(Calendar.MILLISECOND));


            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callback.onSucces();
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callback.onError();
            }
        }).build();

        transaction.execute();

    }



}
