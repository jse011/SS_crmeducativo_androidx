package com.consultoraestrategia.ss_crmeducativo.eventos

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi

interface EventosView:BaseView<EventosPresenter> {
    fun setListaEventos(tipoEventosList: MutableList<TiposEventosUi>)
    fun showListEventos(positionInicial: Boolean, tipoEventosList: MutableList<EventosUi>)
    fun showDialogInfoEvento()
    fun isInternetAvailable(): Boolean
    fun hideTextEmpty()
    fun showTextEmpty()
    fun notificationChangeEvento()
    fun showCrearEventos(usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int)
    fun showEditarEventos(idEvento: String?, usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int)
    fun showListaUsuarioDiaolog()

}