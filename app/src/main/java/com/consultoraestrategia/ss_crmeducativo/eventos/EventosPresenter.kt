package com.consultoraestrategia.ss_crmeducativo.eventos

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos.InformacionListaEventosView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioView
import com.consultoraestrategia.ss_crmeducativo.eventos.listaAdjuntoDownload.AdjuntoEventoDownload

interface EventosPresenter : BasePresenter<EventosView> {
    fun onClickTipoEvento(tiposEventosUi: TiposEventosUi)
    fun renderItemEvento(eventosUi: EventosUi, success: (EventosUi) -> Unit)
    fun onclikInfoEventos(eventosUi: EventosUi, eventoAdjuntoUi: EventoAdjuntoUi?)
    fun onClikLike(eventosUi: EventosUi)
    fun onRefresh()
    fun onClickCrearAsistencia()
    fun onItemOpcionElimnarEvento(eventosUi: EventosUi)
    fun onItemOpcionEditarEvento(eventosUi: EventosUi)
    fun onClickBtnCompartirInfoEventos()
    fun onInformacionEventoDialogViewDestroyed()
    fun attachView(informacionEventoDialogView: InformacionEventoDialogView?)
    fun itemClickEnviar(eventosUi: EventosUi)
    fun itemClickInfoEnviar(eventosUi: EventosUi)
    fun onListaUsuarioViewDestroyed()
    fun attachView(listaUsuarioView: ListaUsuarioView?)
    fun onClickDialogListBannerAdjuntoPreview(eventoUi: EventosUi, adjuntoUi: EventoAdjuntoUi)
    fun onClickAdjunto(eventoUi: EventosUi, adjuntoUi: EventoAdjuntoUi, more: Boolean)
    fun onInformacionListaEventosViewDestroyed()
    fun attachView(informacionListaEventosView: InformacionListaEventosView?)
    fun onClikLikeInfoEvento()
    fun onClikLikeInfoCompartir()
    fun onClickDialogAdjunto(adjuntoUi: EventoAdjuntoUi)
    fun onAdjuntoEventoDownloadDestroy()
    fun attachView(adjuntoEventoDownload: AdjuntoEventoDownload?)

}