package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.GraficoEstilosAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.ui.InfoEstiloAprendizajeFragment;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.ui.InfoEstiloAprendizajeView;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.usecase.GetListDimensionObse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InfoEstiloAprendizajePresenterImpl extends BaseFragmentPresenterImpl<InfoEstiloAprendizajeView> implements InfoEstiloAprendizajePresenter {

    private int alumnoId;
    private int entidadId;
    private int georeferenciaId;
    private GetListDimensionObse getListDimensionObse;

    public InfoEstiloAprendizajePresenterImpl(UseCaseHandler handler, Resources res, GetListDimensionObse getListDimensionObse) {
        super(handler, res);
        this.getListDimensionObse = getListDimensionObse;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.alumnoId = extras.getInt(InfoEstiloAprendizajeFragment.ALUMNOID,0);
        this.entidadId = extras.getInt(InfoEstiloAprendizajeFragment.ENTIDADID, 0);
        this.georeferenciaId = extras.getInt(InfoEstiloAprendizajeFragment.GEOREFERENCIAID, 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        getListDimensionObservada();
    }

    private void getListDimensionObservada() {
        handler.execute(getListDimensionObse,
                new GetListDimensionObse.RequestValues(alumnoId, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<GetListDimensionObse.ResponseValue>() {
                    @Override
                    public void onSuccess(GetListDimensionObse.ResponseValue response) {
                        AlumnoUi alumnoUi = response.getAlumnoUi();
                        if(view!=null)view.setCabecera(alumnoUi);
                        if(view!=null)view.setListTestEvalaucion(alumnoUi.getInstrumentoObservadoUiList());
                        InstrumentoObservadoUi instrumentoObservadoUi = null;
                        try {
                            instrumentoObservadoUi = alumnoUi.getInstrumentoObservadoUiList().get(0);
                            view.setCabeceraResultadoEvalaucion(instrumentoObservadoUi);
                            view.setListResultadoEvaluacion(new ArrayList<Object>(instrumentoObservadoUi.getDimensionObservadaUiListOrdenada()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(instrumentoObservadoUi!=null)showGraficoEstiloAprendizaje(instrumentoObservadoUi);

                    }

                    @Override
                    public void onError() {
                        showMessage("Error al listar");
                    }
                });
    }

    private void showGraficoEstiloAprendizaje(InstrumentoObservadoUi instrumentoObservadoUi) {

        GraficoEstilosAprendizajeUi graficoEstilosAprendizajeUi = new GraficoEstilosAprendizajeUi();
        try {

            DimensionObservadaUi dimensionObservadaUisUno = instrumentoObservadoUi.getDimensionObservadaUiList().get(0);
            DimensionObservadaUi dimensionObservadaUisDos = instrumentoObservadoUi.getDimensionObservadaUiList().get(1);
            DimensionObservadaUi dimensionObservadaUisTres = instrumentoObservadoUi.getDimensionObservadaUiList().get(2);
            DimensionObservadaUi dimensionObservadaUisCuatro = instrumentoObservadoUi.getDimensionObservadaUiList().get(3);

            graficoEstilosAprendizajeUi.setDimenUno(dimensionObservadaUisUno.getDimensionUi().getSigla());
            graficoEstilosAprendizajeUi.setDimenDos(dimensionObservadaUisDos.getDimensionUi().getSigla());
            graficoEstilosAprendizajeUi.setDimenTres(dimensionObservadaUisTres.getDimensionUi().getSigla());
            graficoEstilosAprendizajeUi.setDimenCuatro(dimensionObservadaUisCuatro.getDimensionUi().getSigla());

            graficoEstilosAprendizajeUi.setEstiloApUno(dimensionObservadaUisUno.getDimensionUi().getNombre());
            graficoEstilosAprendizajeUi.setEstiloApDos(dimensionObservadaUisDos.getDimensionUi().getNombre());
            graficoEstilosAprendizajeUi.setEstiloApTres(dimensionObservadaUisTres.getDimensionUi().getNombre());
            graficoEstilosAprendizajeUi.setEstiloApCuatro(dimensionObservadaUisCuatro.getDimensionUi().getNombre());

            graficoEstilosAprendizajeUi.setColorDimenUno(dimensionObservadaUisUno.getDimensionUi().getColor());
            graficoEstilosAprendizajeUi.setColorDimenDos(dimensionObservadaUisDos.getDimensionUi().getColor());
            graficoEstilosAprendizajeUi.setColorDimenTres(dimensionObservadaUisTres.getDimensionUi().getColor());
            graficoEstilosAprendizajeUi.setColorDimenCuatro(dimensionObservadaUisCuatro.getDimensionUi().getColor());

            graficoEstilosAprendizajeUi.setPuntoDimenUno(dimensionObservadaUisUno.getValor());
            graficoEstilosAprendizajeUi.setPuntoDimenDos(dimensionObservadaUisDos.getValor());
            graficoEstilosAprendizajeUi.setPuntoDimenTres(dimensionObservadaUisTres.getValor());
            graficoEstilosAprendizajeUi.setPuntoDimenCuatro(dimensionObservadaUisCuatro.getValor());

        }catch (Exception e){
            e.printStackTrace();
            graficoEstilosAprendizajeUi = null;
        }

        if (graficoEstilosAprendizajeUi!=null){
            if(view!=null)view.showGraficoAprendizaje(graficoEstilosAprendizajeUi);
        }
    }


}
