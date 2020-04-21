package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.PersonaUi
import com.consultoraestrategia.ss_crmeducativo.util.Utils
import kotlinx.android.synthetic.main.item_evento_lista_usuario_detalle.view.*
import kotlinx.android.synthetic.main.item_evento_lista_usuario.view.*

class ListaUsuarioAdapter(val itemClickUsuario: (PersonaUi) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TITULO: Int = 1;
    private val USUARIO: Int = 2;
    private var usuarioLista:MutableList<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when(viewType){
            1 -> TituloViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_evento_lista_usuario, parent,false))
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_evento_lista_usuario_detalle, parent,false))
        }
    }


    override fun getItemCount(): Int = usuarioLista.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(viewHolder is TituloViewHolder){
            viewHolder.bind((usuarioLista[position] as String))
        }else if(viewHolder is ViewHolder){
            viewHolder.bind((usuarioLista[position] as PersonaUi),itemClickUsuario)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val o = usuarioLista[position];

        if(o is String){
            return TITULO;
        }else{
            return USUARIO;
        }

    }

    fun setList(usuarioLista:  MutableList<Any>) {
        this.usuarioLista.clear()
        this.usuarioLista.addAll(usuarioLista)
        notifyDataSetChanged()
    }


    class TituloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(titulo: String) = with(itemView){
            txt_titulo.text = titulo
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usuarioUi: PersonaUi, itemClickUsuario: (PersonaUi) -> Unit) = with(itemView){
            Glide.with(img_usuario)
                    .load(usuarioUi.imagen)
                    .apply(Utils.getGlideRequestOptions())
                    .into(img_usuario)

            txt_usuario.text = usuarioUi.nombre
        }
    }

}