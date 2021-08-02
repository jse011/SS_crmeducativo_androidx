package com.consultoraestrategia.ss_crmeducativo.eventos

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.base.UseCase.UseCaseCallback
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.util.DriveYoutubePreview
import com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase.*
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.*
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos.InformacionListaEventosView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioView
import com.consultoraestrategia.ss_crmeducativo.eventos.listaAdjuntoDownload.AdjuntoEventoDownload
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel

class EventosPresenterImpl(handler: UseCaseHandler?,
                           res: Resources?,
                           val getLikeSaveCountLike: GetLikeSaveCountLike,
                           val saveLike: SaveLike,
                           var sincronizarEventos: SincronizarEventos,
                           var getEventosColegio: GetEventosColegio,
                           var getTipoEvento : GetTipoEvento,
                           var deleteEvento : DeleteEvento,
                           var enviarEvento: EnviarEvento ,
                           var getListaUsuario: GetListaUsuario) : BasePresenterImpl<EventosView>(handler, res), EventosPresenter{


    private var AdjuntoEventoDownloadView: AdjuntoEventoDownload? = null
    private var informacionListaEventosView: InformacionListaEventosView? = null
    private var adjuntoUiPreviewSelected: EventoAdjuntoUi? = null;
    private var tutorCargaAcademicaId: Int = 0
    private var cargaCursoId: Int = 0
    private var listaUsuarioView: ListaUsuarioView? = null
    private var informacionEventoDialogView: InformacionEventoDialogView? = null
    private var initial: Boolean = false
    private var retrofitCancel: RetrofitCancel<Any>? = null
    private var entidadId: Int = 0
    private var anioAcademicoId: Int = 0
    private var empleadoId: Int = 0
    private var cancelSincronizarEventos: RetrofitCancel<Any>? = null
    private var georeferenciaId: Int = 0
    private var usuarioId: Int = 0
    private var eventosUiSelected: EventosUi? = null
    private var tipoEventosList:MutableList<TiposEventosUi> = ArrayList()
    private var eventosUiList:MutableList<EventosUi> = ArrayList()
    private var tiposEventosUiSelect:TiposEventosUi? = null

    override fun getTag(): String = "EventosPresenterImpl"

    override fun onSingleItemSelected(singleItem: Any?, selectedPosition: Int) {

    }

    override fun onCreate() {
        super.onCreate()
        _getEventosColegio(false, object : UseCaseCallback<GetEventosColegio.ResponseValues> {
            override fun onSuccess(response: GetEventosColegio.ResponseValues) {
                showProgress()
                if (response.list.isEmpty()) {
                    getEventosColegio(false)
                } else {
                    Handler().postDelayed({ getEventosColegio(false) }, 2000)
                }
            }

            override fun onError() {}
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        retrofitCancel?.cancel()
        cancelSincronizarEventos?.cancel()
    }

    private fun getTipos() {
        tipoEventosList.clear()
        tipoEventosList.addAll(getTipoEvento.execute())

        val tiposEventosUi = TiposEventosUi(TiposUi.TODOS)
        tiposEventosUi.nombre = "Todos"
        tiposEventosUi.toogle = false
        tipoEventosList.add(tiposEventosUi)

        var existe = false
        tipoEventosList.forEach({
            if(it.id==tiposEventosUiSelect?.id){
                existe = true
                it.toogle = true
            }
        })

        if(!existe){
            tiposEventosUiSelect = TiposEventosUi(TiposUi.TODOS)
            tiposEventosUiSelect?.nombre = "Todos"
            tiposEventosUiSelect?.toogle = false
        }

        if(view!=null)view.setListaEventos(tipoEventosList)
    }

    override fun setExtras(extras: Bundle?) {
        super.setExtras(extras)
        val crmBundle = CRMBundle(extras)
        usuarioId = crmBundle.usuarioId
        georeferenciaId = crmBundle.georeferenciaId
        empleadoId = crmBundle.empleadoId
        anioAcademicoId = crmBundle.anioAcademico
        entidadId = crmBundle.entidadId
        tutorCargaAcademicaId = crmBundle.cargaAcademicaId;
        cargaCursoId = crmBundle.cargaCursoId;
    }

    override fun onClikLikeInfoCompartir() {
        if (eventosUiSelected != null) view?.startCompartirEvento(eventosUiSelected!!)
    }

    override fun onClickDialogAdjunto(adjuntoUi: EventoAdjuntoUi) {
        when (adjuntoUi.tipoArchivo) {
            TipoAdjuntoUi.DOCUMENTO, TipoAdjuntoUi.HOJACALCULO, TipoAdjuntoUi.PRESENTACION, TipoAdjuntoUi.IMAGEN, TipoAdjuntoUi.PDF ->  view?.showPreviewArchivo(DriveYoutubePreview.Build.setupDriveDocumento(adjuntoUi.driveId, adjuntoUi.titulo))
            TipoAdjuntoUi.VIDEO, TipoAdjuntoUi.AUDIO -> view?.showPreviewArchivo(DriveYoutubePreview.Build.setupDriveMultimedia(adjuntoUi.driveId, adjuntoUi.titulo))
            else -> view?.showVinculo(adjuntoUi.titulo)
        }
    }

    override fun onAdjuntoEventoDownloadDestroy() {
        AdjuntoEventoDownloadView = null;
    }

    override fun itemLinkEncuesta(adjuntoUi: EventoAdjuntoUi) {
        view?.showVinculo(adjuntoUi.titulo);
    }

    override fun onClikLikeInfoEvento() {
        if (eventosUiSelected != null) {

            if (!eventosUiSelected!!.like) {
                eventosUiSelected!!.like = true
            } else {
                eventosUiSelected!!.like = false
            }
            var cantidad: Int = eventosUiSelected!!.likeCount
            if (eventosUiSelected!!.like) {
                cantidad++
            } else {
                cantidad--
            }
            if (cantidad < 0) cantidad = 0
            eventosUiSelected!!.likeCount = cantidad
            onClikLike(eventosUiSelected!!)
            informacionListaEventosView?.changeLike(eventosUiSelected)
            informacionEventoDialogView?.changeLike(eventosUiSelected)
            view?.updateEvento(eventosUiSelected!!)
        }
    }

    override fun onClickTipoEvento(tiposEventosUi: TiposEventosUi) {
        tipoEventosList.forEach({
            it.toogle = false
        })
        tiposEventosUi.toogle=true

        tiposEventosUiSelect = tiposEventosUi

        if(view!=null)view.setListaEventos(tipoEventosList)
        if(view!=null)view.showProgress()
        _getEventosColegio(false,null)

    }

    override fun renderItemEvento(eventosUi: EventosUi, onSuccess: (EventosUi) -> Unit) {
        if (!eventosUi.datosfirefase) {
            eventosUi.datosfirefase=true
            getLikeSaveCountLike.execute(eventosUi, UseCaseSincrono.Callback<EventosUi?> { success, value ->
                if (success) {
                    onSuccess(value!!)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if(initial){
            _getEventosColegio(false, null)
        }
        initial = true
    }

    override fun onclikInfoEventos(eventosUi: EventosUi, eventoAdjuntoUi: EventoAdjuntoUi?) {
        eventosUiSelected = eventosUi
        adjuntoUiPreviewSelected = eventoAdjuntoUi
        if (eventosUiSelected!=null && eventosUiSelected?.adjuntoUiPreviewList?.size!! > 1) {
            if (view != null) view.showDialogListaBannerEvento()
        } else {
            if (view != null) view.showDialogAdjuntoEvento()
        }
    }

    override fun onClikLike(eventosUi: EventosUi) {
        if (view != null && view.isInternetAvailable()) {
            saveLike.execute(eventosUi)
        } else {
            if (view != null) view.showMessage(res.getString(R.string.msg_unknow_internet_2))
        }
    }

    override fun onRefresh() {
        getEventosColegio(false)
    }

    override fun onClickCrearAsistencia() {
        if(view!=null)view.showCrearEventos(usuarioId,georeferenciaId, empleadoId, anioAcademicoId, entidadId, tutorCargaAcademicaId, cargaCursoId)
    }

    override fun onItemOpcionElimnarEvento(eventosUi: EventosUi) {
        if (view != null && view.isInternetAvailable()) {
            retrofitCancel  = deleteEvento.execute(eventosUi, { success, value ->
                if (success) {
                    _getEventosColegio(false,null)
                } else {
                    if (view != null) view.showMessage("Server connection timeout")
                }
            })
        } else {
            if (view != null) view.showMessage("Sin conexión a internet")
        }
    }

    override fun onItemOpcionEditarEvento(eventosUi: EventosUi) {
        Log.d(tag, "eventosUi.idEvento: " + eventosUi.idEvento)
        if(view!=null)view.showEditarEventos(eventosUi.idEvento,usuarioId,georeferenciaId, empleadoId, anioAcademicoId, entidadId)
    }

    override fun onClickBtnCompartirInfoEventos() {
        if(eventosUiSelected!=null)this.informacionEventoDialogView?.showCompartirEvento(eventosUiSelected)
    }

    override fun onInformacionEventoDialogViewDestroyed() {
        informacionEventoDialogView = null;
    }

    override fun attachView(informacionEventoDialogView: InformacionEventoDialogView?) {
        this.informacionEventoDialogView = informacionEventoDialogView;
        if(eventosUiSelected!=null)this.informacionEventoDialogView?.showInformacionEvento(eventosUiSelected, adjuntoUiPreviewSelected)
    }

    override fun attachView(listaUsuarioView: ListaUsuarioView?) {
        this.listaUsuarioView = listaUsuarioView
        if(eventosUiSelected!=null){
            listaUsuarioView?.setListUsuarios(getListaUsuario.execute(eventosUiSelected?.calendarioId, eventosUiSelected?.idEvento))
            listaUsuarioView?.hideProgress()
        }
    }

    override fun attachView(informacionListaEventosView: InformacionListaEventosView?) {
        this.informacionListaEventosView = informacionListaEventosView;
        if (eventosUiSelected != null && adjuntoUiPreviewSelected != null) {
            informacionListaEventosView?.showInformacionEvento(eventosUiSelected, adjuntoUiPreviewSelected, false)
        } else if (eventosUiSelected != null) {
            informacionListaEventosView?.showInformacionEvento(eventosUiSelected, null, true)
        }
    }

    override fun attachView(adjuntoEventoDownload: AdjuntoEventoDownload?) {
        this.AdjuntoEventoDownloadView = adjuntoEventoDownload
        adjuntoEventoDownload!!.setList(eventosUiSelected?.adjuntoUiList)
    }

    override fun itemClickEnviar(eventosUi: EventosUi) {
        if (view != null && view.isInternetAvailable()) {
            retrofitCancel  = enviarEvento.execute(eventosUi, { success, value ->
                if (!success){
                    if (view != null) view.showMessage("Server connection timeout")
                }

                if (view != null) view.showListEventos(false, eventosUiList)

            })
        } else {
            if (view != null) view.showMessage("Sin conexión a internet")
        }
    }

    override fun itemClickInfoEnviar(eventosUi: EventosUi) {
        this.eventosUiSelected = eventosUi
        if(view!=null)view.showListaUsuarioDiaolog();
    }

    override fun onListaUsuarioViewDestroyed() {
        listaUsuarioView = null
    }

    override fun onClickDialogListBannerAdjuntoPreview(eventoUi: EventosUi, adjuntoUi: EventoAdjuntoUi) {
        eventosUiSelected = eventoUi
        adjuntoUiPreviewSelected = adjuntoUi
        view?.showDialogAdjuntoEvento()
    }

    override fun onClickAdjunto(eventoUi: EventosUi, adjuntoUi: EventoAdjuntoUi, more: Boolean) {
        if (more) {
            eventosUiSelected = eventoUi
            view?.showDialogEventoDownload()
        } else {
            when (adjuntoUi.tipoArchivo) {
                TipoAdjuntoUi.DOCUMENTO, TipoAdjuntoUi.HOJACALCULO, TipoAdjuntoUi.PRESENTACION, TipoAdjuntoUi.IMAGEN, TipoAdjuntoUi.PDF ->  view?.showPreviewArchivo(DriveYoutubePreview.Build.setupDriveDocumento(adjuntoUi.driveId, adjuntoUi.titulo))
                TipoAdjuntoUi.VIDEO, TipoAdjuntoUi.AUDIO -> view?.showPreviewArchivo(DriveYoutubePreview.Build.setupDriveMultimedia(adjuntoUi.driveId, adjuntoUi.titulo))
                else -> view?.showVinculo(adjuntoUi.titulo)
            }
        }
    }

    override fun onInformacionListaEventosViewDestroyed() {
        informacionListaEventosView = null;
    }

    override fun onCLickAcceptButtom() {

    }

    private fun _getEventosColegio(positionInicial: Boolean, callback: UseCaseCallback<GetEventosColegio.ResponseValues>?) {
        getTipos();
        handler.execute(getEventosColegio, GetEventosColegio.RequestValues(usuarioId, georeferenciaId,tiposEventosUiSelect), object : UseCaseCallback<GetEventosColegio.ResponseValues> {
            override fun onSuccess(response: GetEventosColegio.ResponseValues) {
                eventosUiList.clear()
                eventosUiList.addAll(response.list)
                hideProgress()
                Log.d(tag, "_getEventosColegio")
                if (view != null) view.showListEventos(positionInicial, eventosUiList)
                if (eventosUiList.size > 0) {
                    if (view != null) view.hideTextEmpty()
                } else {
                    if (view != null) view.showTextEmpty()
                }
                callback?.onSuccess(response)
            }

            override fun onError() {
                hideProgress()
                eventosUiList.clear()
                if (view != null) view.showListEventos(positionInicial, eventosUiList)
                callback?.onError()
            }
        })
    }

    private fun getEventosColegio(positionInicial: Boolean){
        Log.d(getTag(), "getEventosColegio");

        if(view!=null&&view.isInternetAvailable()){
            cancelSincronizarEventos?.cancel()
            cancelSincronizarEventos = sincronizarEventos.execute(SincronizarEventos.Request(usuarioId, georeferenciaId)
            ) { success, value ->
                _getEventosColegio(positionInicial, object: UseCaseCallback<GetEventosColegio.ResponseValues> {
                    override fun onSuccess(response: GetEventosColegio.ResponseValues?) {
                        if (view != null) view.notificationChangeEvento()
                    }
                    override fun onError() {}
                })
            }
        }
    }




}