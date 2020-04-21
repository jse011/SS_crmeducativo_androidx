package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.TableView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 16/02/2018.
 */

public interface DetalleView extends TableView {
    void showTipoNivelSelected(TipoNotaUi tipoNota);
    void showTipoNivelChooser(List<TipoNotaUi> tipoNotaList);

    void refrescar();

    void disabledBusquedaCamposAccion();
    void disabledSelectorIndicador();


    void showTxtTableEmprty();

    void hideTxtTableEmprty();

    void disabledSelectorCamposAccion();
}
