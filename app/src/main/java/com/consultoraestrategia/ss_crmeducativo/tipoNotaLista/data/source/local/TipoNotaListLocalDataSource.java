package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.EscalaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.ValorTipoNotaUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaListLocalDataSource implements TipoNotaListDataSource {
    private final static String TAG = TipoNotaListLocalDataSource.class.getSimpleName();
    private final static int SELECTOR_ICONOS = 409;
    private final static int SELECTOR_NUMERICO = 411;
    private final static int SELECTOR_VALORES = 412;
    private final static int VALOR_NUMERICO = 410;
    private TipoNotaDao tipoNotaDao;

    public TipoNotaListLocalDataSource(TipoNotaDao tipoNotaDao) {
        this.tipoNotaDao = tipoNotaDao;
    }

    private static final String GREY = "#90A4AE",AZUL ="#1976d2",ANARANJADO="#FF6D00",ROJO="#D32F2F",VERDE="#388e3c";

    @Override
    public void getTipoNotaList(CallbackList<TipoNotaUi> callback,GetTipoNotaList.RequestValues requestValues) {
        TipoUi.Tipo[] tipos = requestValues.getTipos();
        Log.d(TAG, "PROGRAMA EDUCAI"+ requestValues.getProgramaEducativo());
        int programaEducativoId = requestValues.getProgramaEducativo();
        TipoNotaUi tipoNotaUiT = new TipoNotaUi();
        boolean state = false;

        List<TipoNotaUi> tipoNotaUis = new ArrayList<>();
        List<TipoNotaC> tipoNotas = getTipoNotaList(tipos, programaEducativoId);
        for (TipoNotaC itemTipoNota : tipoNotas) {
            if (itemTipoNota.getTipoNotaId().equals("3")){
                state=true;
                tipoNotaUiT = getCompletoTipoNotaUi(itemTipoNota);
            }
            else
                tipoNotaUis.add(getCompletoTipoNotaUi(itemTipoNota));
        }

        if (state)tipoNotaUis.add(tipoNotaUiT);
        callback.onLoadList(tipoNotaUis);
    }

    @Override
    public void getTipoNotaDefault(Callback<TipoNotaUi> callback, GetTipoNotaDefault.RequestValues requestValues) {
        try {
            TipoUi.Tipo[] tipos = requestValues.getTipos();
            int programaEducativoId = requestValues.getProgramaEducativo();
            TipoNotaC tipoNotaC = getTipoNotaList(tipos, programaEducativoId).get(0);
            TipoNotaUi tipoNotaUi = getCompletoTipoNotaUi(tipoNotaC);
            callback.onLoad(true, tipoNotaUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }
    }

    @Override
    public void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback) {
            Log.d(TAG,"tipoNotaId: "+ tipoNotaId);
            TipoNotaC tipoNotaC = SQLite.select()
                    .from(TipoNotaC.class)
                    .where(TipoNotaC_Table.key.eq(tipoNotaId))
                    .querySingle();

            if(tipoNotaC!=null)callback.onLoad(true, getCompletoTipoNotaUi(tipoNotaC));
            else callback.onLoad(false, null);
    }

    private List<TipoNotaC> getTipoNotaList(TipoUi.Tipo[] tipos, int programaEducativoId){
        List<Integer> tipoIdList = new ArrayList<>();
        int cantidad = tipos.length;
        if(cantidad == 0){
            tipoIdList.add(TipoNotaC.SELECTOR_ICONOS);
            tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);
            tipoIdList.add(TipoNotaC.SELECTOR_VALORES);
            tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);
        }else {

            for (TipoUi.Tipo tipo : Arrays.asList(tipos)) {
                switch (tipo) {
                    case SELECTOR_VALORES:
                        tipoIdList.add(TipoNotaC.SELECTOR_VALORES);
                        break;
                    case SELECTOR_ICONOS:
                        tipoIdList.add(TipoNotaC.SELECTOR_ICONOS);
                        break;
                    case SELECTOR_NUMERICO:
                        tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);
                        break;
                    case VALOR_NUMERICO:
                        tipoIdList.add(TipoNotaC.SELECTOR_NUMERICO);
                        break;
                }

            }

        }

        return tipoNotaDao.getTipoNotaList(programaEducativoId,tipoIdList);
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList(TipoNotaUi tipoNotaUi, TipoNotaC tipoNotaC){
        List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();

        int count = 0;
        for (ValorTipoNotaC itemValorTipoNota : tipoNotaC.getValorTipoNotaList()){
            ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
            valorTipoNotaUi.setId(itemValorTipoNota.getValorTipoNotaId());
            valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
            valorTipoNotaUi.setIcono(itemValorTipoNota.getIcono());
            valorTipoNotaUi.setTitulo(itemValorTipoNota.getTitulo());
            valorTipoNotaUi.setAlias(itemValorTipoNota.getAlias());
            valorTipoNotaUi.setIncluidoLInferior(itemValorTipoNota.isIncluidoLInferior());
            valorTipoNotaUi.setIncluidoLSuperior(itemValorTipoNota.isIncluidoLSuperior());
            valorTipoNotaUi.setLimiteInferior(itemValorTipoNota.getLimiteInferior());
            valorTipoNotaUi.setLimiteSuperior(itemValorTipoNota.getLimiteSuperior());
            valorTipoNotaUi.setValorNumerico(itemValorTipoNota.getValorNumerico());
            switch (count) {
                case 0:
                    valorTipoNotaUi.setColor(AZUL);
                    break;
                case 1:
                    valorTipoNotaUi.setColor(VERDE);
                    break;
                case 2:
                    valorTipoNotaUi.setColor(ANARANJADO);
                    break;
                case 3:
                    valorTipoNotaUi.setColor(ROJO);
                    break;
                default:
                    valorTipoNotaUi.setColor(GREY);
                    break;
            }
            count++;
            valorTipoNotaUiList.add(valorTipoNotaUi);
        }

        return valorTipoNotaUiList;
    }

    private TipoNotaUi getCompletoTipoNotaUi(TipoNotaC tipoNotaC){

        EscalaEvaluacion escalaEvaluacion = SQLite.select()
                .from(EscalaEvaluacion.class)
                .where(EscalaEvaluacion_Table.escalaEvaluacionId.is(tipoNotaC.getEscalaEvaluacionId()))
                .querySingle();
        EscalaUi escalaUi = new EscalaUi();
        if(escalaEvaluacion != null){
            escalaUi.setId(escalaEvaluacion.getEscalaEvaluacionId());
            escalaUi.setValorMinimo(escalaEvaluacion.getValorMinimo());
            escalaUi.setValorMaximo(escalaEvaluacion.getValorMaximo());
            escalaUi.setDescripcion(escalaEvaluacion.getNombre());
        }

        TipoUi tipoUi = new TipoUi();
        switch (tipoNotaC.getTipoId()){
            case SELECTOR_ICONOS:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_ICONOS);
                break;
            case SELECTOR_NUMERICO:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_NUMERICO);
                break;
            case SELECTOR_VALORES:
                tipoUi.setTipo(TipoUi.Tipo.SELECTOR_VALORES);
                break;
            default:
                tipoUi.setTipo(TipoUi.Tipo.VALOR_NUMERICO);
                break;
        }

        TipoNotaUi tipoNotaUi = new TipoNotaUi(tipoNotaC.getTipoNotaId(),tipoUi,escalaUi);
        tipoNotaUi.setEnabled(true);
        tipoNotaUi.setNombre(tipoNotaC.getNombre());
        switch (tipoUi.getTipo()){
            case SELECTOR_VALORES:
                tipoNotaUi.setValorTipoNotaUiList(getValorTipoNotaList(tipoNotaUi,tipoNotaC));
                break;
            case SELECTOR_ICONOS:
                tipoNotaUi.setValorTipoNotaUiList(getValorTipoNotaList(tipoNotaUi,tipoNotaC));
                break;
        }

        return tipoNotaUi;

    }

}
