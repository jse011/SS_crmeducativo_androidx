package com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.ListaUsuarioAdapter
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.PersonaUi

class ListaUsuarioDialog : DialogFragment(),ListaUsuarioView {


    private var rcListaUsuario: RecyclerView?=null
    private  var progressBar: ProgressBar?=null
    private  var btnCancelar: ImageView?=null
    private var presenter: EventosPresenter? = null
    private var txtTitulo: TextView? = null

    private lateinit var adapter: ListaUsuarioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.dialog.requestWindowFeature(STYLE_NO_TITLE)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return  view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return createSimpleDialog()
    }

    /**
     * Crea un diálogo de alerta sencillo
     *
     * @return Nuevo diálogo
     */
    fun createSimpleDialog(): AlertDialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_evento_lista_usuario, null)
        val builder = AlertDialog.Builder(activity!!)
        rcListaUsuario = view.findViewById(R.id.rc_lista_usuario)
        progressBar = view.findViewById(R.id.progress_bar)
        txtTitulo = view.findViewById(R.id.txt_titulo)
        btnCancelar = view.findViewById(R.id.btn_cancelar)
        btnCancelar?.setOnClickListener({dismiss()})
        builder.setView(view)
        setupAdapter()
        return builder.create()
    }

    private fun setupAdapter() {
        adapter = ListaUsuarioAdapter({itemClickUsuario(it)})
        rcListaUsuario?.adapter = adapter
        rcListaUsuario?.layoutManager = LinearLayoutManager(context)
    }

    private fun itemClickUsuario(usuarioUi: PersonaUi) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressBar = null
        rcListaUsuario = null
        btnCancelar?.setOnClickListener(null)
        btnCancelar=null
        txtTitulo = null
    }

    override fun setListUsuarios(listUsuario: MutableList<Any>) {
        Log.d("getListaUsuarios", "Size: " + listUsuario.size)
        var count = 0
        listUsuario.forEach {
            if(it is PersonaUi)count++
        }
        val titulo = "$count participantes"
        txtTitulo?.text = titulo
        adapter.setList(listUsuario)
    }

    override fun setPresenter(presenter: EventosPresenter?) {
        this.presenter = presenter
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun close() {
       dismiss()
    }


}