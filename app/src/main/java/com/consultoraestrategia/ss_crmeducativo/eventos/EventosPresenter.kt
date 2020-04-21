package com.consultoraestrategia.ss_crmeducativo.eventos

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioView

interface EventosPresenter : BasePresenter<EventosView> {
    fun onClickTipoEvento(tiposEventosUi: TiposEventosUi)
    fun renderItemEvento(eventosUi: EventosUi)
    fun onclikInfoEventos(eventosUi: EventosUi)
    fun onClikLike(eventosUi: EventosUi)
    fun onRefresh()
    fun onClickCrearAsistencia()
    fun onItemOpcionElimnarEvento(eventosUi: EventosUi)
    fun onItemOpcionEditarEvento(eventosUi: EventosUi)
    fun onClickBtnCompartirInfoEventos()
    fun onClickBtnMegustaInfoEventos()
    fun onInformacionEventoDialogViewDestroyed()
    fun attachView(informacionEventoDialogView: InformacionEventoDialogView?)
    fun itemClickEnviar(eventosUi: EventosUi)
    fun itemClickInfoEnviar(eventosUi: EventosUi)
    fun onListaUsuarioViewDestroyed()
    fun attachView(listaUsuarioView: ListaUsuarioView?)


}