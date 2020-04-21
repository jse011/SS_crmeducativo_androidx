package com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios

import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter

interface ListaUsuarioView {

    fun setListUsuarios(listUsuario: MutableList<Any>)
    fun setPresenter(presenter: EventosPresenter?)
    fun hideProgress()
    fun showProgress()

    fun close()
}