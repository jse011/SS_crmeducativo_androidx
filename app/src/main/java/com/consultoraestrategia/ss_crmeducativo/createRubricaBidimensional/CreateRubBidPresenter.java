package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.RubroBidICell.RubricaBidCellCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser.CampoTematicoChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.changePesoIndicador.PesoIndicadorCellCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser.IndicadorChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera.RubricaCabeceraListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle.RubricaDetalleListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public interface CreateRubBidPresenter extends BasePresenter<CreateRubBidView>, RubricaCabeceraListener, RubricaDetalleListener, ITableViewListener, IndicadorChooserCallback, CampoTematicoChooserCallback, RubricaBidCellCallback, PesoIndicadorCellCallback {
    void onBtnCreateClicked();
    void onSingleItemSelectedTipoNota(String tipoNotaId);
    void onSelectBuscarCamosAccion(List<CampoAccionUi> campoAccionUiList);
    void onSelectAcetarEditarRubroDetalle(IndicadorUi indicadorUi);
    void onSelectCancelarEditarRubroDetalle();
    void sendList(IndicadorUi indicadorUi);

    void setEstrategiaSelected(EstrategiaEvalUi estrategiaEvalUiselected);

    void onClikInfoTipoNota();


    /*Headers controls*/
    /*void onCompetenciaSelected(CompetenciaUi competencia);
    void onCampoAccionClicked();
    void onTipoNotaSelected(TipoNotaUi tipoNota);


    //TableView Controls
    void onHeaderSelected(Header header);
    void onRowSelected(Row row);
    void onCellSelected(Cell cell);

    //Cuando se termina de seleccionar la lista de indicadores asociados
    void onIndicadorChooserOk(List<CompetenciaUi> competenciaList);

    void onIndicadorChooserCancel();

    void onBtnCompetenciaClicked();

    void onBtnCampoAccionClicked();

    void onBtnTipoNotaClicked();

    void onCamposAccionSucces(List<IndicadorUi> indicadorList);

    void onTipoNivelSelected(TipoNotaUi valorTipoNotaUi);*/
}
