package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.presenter.UnidadesPresenter;

import java.util.List;

public interface FragmentUnidadesView extends BaseView<UnidadesPresenter> {
  void showUnidadesList(List<UnidadAprendizajeUi> unidadesList, int programaEducativoId);
  void clearUnidades();
  void actualizarUnidadesPeriodo(String idCalendarioPeriodo, boolean status);
  void showTextVaciounidades();
  void hideTextVaciounidades();
  void showTabSesiones(int parametroDisenioId, int personaId, int idCargaCurso, int cursoId, int cargaAdemicaId, int backgroudColor, SesionAprendizajeUi sesionAprendizaje, int entidadId, int georeferenciaId);
  void showProgress();
  void hideProgress();
  void startActivityUnidadDetalle(  UnidadParametros unidadParametros );
  void updateItem(UnidadAprendizajeUi unidadAprendizajeUi);
  int getColumnasSesionesList();
}

