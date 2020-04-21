package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaFilaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.CeldasFormulaSelIconosHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.CeldasFormulaSelNumericoHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.CeldasFormulaSelValoresHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.FilasAlumnosHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.ColumnaRubroFormulaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.ColumnaRubroNormalHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.ColumnaRubroRubricaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.FilasValorTipoNotaHolder;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.List;

/**
 * Created by kike on 11/05/2018.
 */
//Rubros,LadoIzquierdo,Celda   <FormulaFilaCabecera, FormulaColumnaCabecera, FormulaCelda> {
public class EvaluacionFormulaAdapter extends AbstractTableAdapter<FormulaColumnaCabecera, FormulaFilaCabecera, FormulaCelda> {

    public static final String EVALUACION_FORMULA_TAG = EvaluacionFormulaAdapter.class.getSimpleName();

    public static final int COLUMMNA_RUBRO_NORMAL = 1;
    public static final int COLUMMNA_RUBRO_RUBRICA = 2;
    public static final int COLUMMNA_RUBRO_FORMULA = 3;

    public static final int COLUMNA_ALUMNOS = 1;


    private final int FILA_VALOR_TIPO_NOTA = 1;


    private final int FILAS_RUBROS = 1;


    public static final int VALOR_NUMERICO = 410;
    public static final int SELECTOR_NUMERICO = 411;
    public static final int SELECTOR_VALORES = 412;
    public static final int SELECTOR_ICONOS = 409;
    public static final int SELECTOR_DEFECTO_NUMEROS = 10;

    public EvaluacionFormulaAdapter(Context p_jContext) {
        super(p_jContext);
    }

    /*Columnas Cabeceras*/


    @Override
    public int getColumnHeaderItemViewType(int position) {
        FormulaCelda filaCabecera = (FormulaCelda)getColumnHeaderRecyclerViewAdapter().getItem(position);
        if (filaCabecera instanceof EvaluacionFormula_RubroEvaluacionUi) {
            EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi = (EvaluacionFormula_RubroEvaluacionUi) filaCabecera;
            if (rubroProcesoUi.getTipoRubro() == COLUMMNA_RUBRO_FORMULA) {
                return COLUMMNA_RUBRO_FORMULA;
            } else if (rubroProcesoUi.getTipoRubro() == 473) {
                return COLUMMNA_RUBRO_RUBRICA;
            } else {
                return COLUMMNA_RUBRO_NORMAL;
            }

        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        /*Celdas del lado Izquierdo*/
        FormulaCelda filasLadiIzquierdoCabeceras = (FormulaCelda)getRowHeaderRecyclerViewAdapter().getItem(position);
        if (filasLadiIzquierdoCabeceras instanceof AlumnosUi) {
            return COLUMNA_ALUMNOS;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {

        List<List<FormulaCelda>> list = (List<List<FormulaCelda>>)getCellRecyclerViewAdapter().getItems();
        int cantidad = list.size();
        if (cantidad != 0) {
            FormulaCelda celdasItemContador = list.get(0).get(position);
            if (celdasItemContador instanceof NotaUi) {
                NotaUi notaUiCeldas = (NotaUi) celdasItemContador;
                EvaluacionFormula_TipoNotaUi tipoNotaUi = notaUiCeldas.getTipoNota();
                if (tipoNotaUi == null) return 0;
                int tipoId = tipoNotaUi.getTipoId();
                Log.d(EVALUACION_FORMULA_TAG, "tipoId" + tipoId);
                if (tipoId == SELECTOR_NUMERICO) {
                    return SELECTOR_NUMERICO;
                } else if (tipoId == SELECTOR_VALORES) {
                    return SELECTOR_VALORES;
                } else if (tipoId == SELECTOR_ICONOS) {
                    return SELECTOR_ICONOS;
                } else {
                    return SELECTOR_DEFECTO_NUMEROS;
                }
            }
        }
        return 0;
    }


    /*Filas,Cuerpo de la tabla*/
    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        AbstractViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case SELECTOR_NUMERICO:
                View vistaSelectorNumerico = layoutInflater.inflate(R.layout.tabla_celdas_selector_numerico, parent, false);
                viewHolder = new CeldasFormulaSelNumericoHolder(vistaSelectorNumerico);
                break;
            case SELECTOR_VALORES:
                View vistaSelectorValores = layoutInflater.inflate(R.layout.tabla_celdas_selector_valores, parent, false);
                viewHolder = new CeldasFormulaSelValoresHolder(vistaSelectorValores);
                break;
            case SELECTOR_ICONOS:
                View vistaSelectorIconos = layoutInflater.inflate(R.layout.tabla_celdas_selector_iconos, parent, false);
                viewHolder = new CeldasFormulaSelIconosHolder(vistaSelectorIconos);
                break;
            default:
                View vistaValorTipoNota = layoutInflater.inflate(R.layout.tabla_celdas_valor_tipo_nota, parent, false);
                viewHolder = new FilasValorTipoNotaHolder(vistaValorTipoNota);
        }
        return viewHolder;
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {

        if (holder instanceof CeldasFormulaSelNumericoHolder && p_jValue instanceof NotaUi) {
            CeldasFormulaSelNumericoHolder celdasFormulaSelNumericoHolder = (CeldasFormulaSelNumericoHolder) holder;
            NotaUi valorTipoNotaUi = (NotaUi) p_jValue;
            celdasFormulaSelNumericoHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof CeldasFormulaSelValoresHolder && p_jValue instanceof NotaUi) {
            CeldasFormulaSelValoresHolder celdasFormulaSelValoresHolder = (CeldasFormulaSelValoresHolder) holder;
            NotaUi valorTipoNotaUi = (NotaUi) p_jValue;
            celdasFormulaSelValoresHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof CeldasFormulaSelIconosHolder && p_jValue instanceof NotaUi) {
            CeldasFormulaSelIconosHolder celdasFormulaSelIconosHolder = (CeldasFormulaSelIconosHolder) holder;
            NotaUi valorTipoNotaUi = (NotaUi) p_jValue;
            celdasFormulaSelIconosHolder.bind(valorTipoNotaUi);
        } else {
            FilasValorTipoNotaHolder notaCellViewHolder = (FilasValorTipoNotaHolder) holder;
            NotaUi valorTipoNotaUi = (NotaUi) p_jValue;
            notaCellViewHolder.bind(valorTipoNotaUi);
        }
    }

    /*Lista de Rubros Cabecera */
    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        AbstractViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case COLUMMNA_RUBRO_RUBRICA:
                View vistaRubroRubrica = layoutInflater.inflate(R.layout.tabla_columna_rubro_rubrica, parent, false);
                viewHolder = new ColumnaRubroRubricaHolder(vistaRubroRubrica);
                break;
            case COLUMMNA_RUBRO_FORMULA:
                View vistaRubroFormula = layoutInflater.inflate(R.layout.tabla_columna_rubro_formula, parent, false);
                viewHolder = new ColumnaRubroFormulaHolder(vistaRubroFormula);
                break;
            default:
                View vistaRubroNormal = layoutInflater.inflate(R.layout.tabla_columna_rubro_normal, parent, false);
                viewHolder = new ColumnaRubroNormalHolder(vistaRubroNormal);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if (holder instanceof ColumnaRubroFormulaHolder && p_jValue instanceof EvaluacionFormula_RubroEvaluacionUi) {
            ColumnaRubroFormulaHolder rubroFormulaNotaHolder = (ColumnaRubroFormulaHolder) holder;
            EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi = (EvaluacionFormula_RubroEvaluacionUi) p_jValue;
            rubroFormulaNotaHolder.bind(rubroProcesoUi);
        } else if (holder instanceof ColumnaRubroRubricaHolder && p_jValue instanceof EvaluacionFormula_RubroEvaluacionUi) {
            ColumnaRubroRubricaHolder rubroRubricaHolder = (ColumnaRubroRubricaHolder) holder;
            EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi = (EvaluacionFormula_RubroEvaluacionUi) p_jValue;
            rubroRubricaHolder.bind(rubroProcesoUi);
        } else if (holder instanceof ColumnaRubroNormalHolder && p_jValue instanceof EvaluacionFormula_RubroEvaluacionUi) {
            ColumnaRubroNormalHolder rubroNormalHolder = (ColumnaRubroNormalHolder) holder;
            EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi = (EvaluacionFormula_RubroEvaluacionUi) p_jValue;
            rubroNormalHolder.bind(rubroProcesoUi);
        }


    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        AbstractViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case COLUMNA_ALUMNOS:
                View vistaAlumnos = layoutInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
                viewHolder = new FilasAlumnosHolder(vistaAlumnos);
                break;
            default:
                View vistaRubrosNormal2 = layoutInflater.inflate(R.layout.tabla_columna_rubro_normal, parent, false);
                viewHolder = new ColumnaRubroNormalHolder(vistaRubrosNormal2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        if (holder instanceof FilasAlumnosHolder && p_jValue instanceof AlumnosUi) {
            FilasAlumnosHolder alumnoColumnViewHolder = (FilasAlumnosHolder) holder;
            AlumnosUi alumnosUi = (AlumnosUi) p_jValue;
            alumnoColumnViewHolder.bind(alumnosUi);
        }

    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tabla_vista_disenio_esquina_formula, null, false);
    }
}
