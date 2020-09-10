package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.PersonaUi
import com.consultoraestrategia.ss_crmeducativo.util.Utils
import kotlinx.android.synthetic.main.item_evento_lista_usuario.view.*
import kotlinx.android.synthetic.main.item_evento_lista_usuario_detalle.view.*

class ListaUsuarioAdapter(val itemClickUsuario: (PersonaUi) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TITULO: Int = 1;
    private val USUARIO: Int = 2;
    private var usuarioLista:MutableList<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when(viewType){
            1 -> TituloViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_evento_lista_usuario, parent, false))
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_evento_lista_usuario_detalle, parent, false))
        }
    }


    override fun getItemCount(): Int = usuarioLista.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(viewHolder is TituloViewHolder){
            viewHolder.bind((usuarioLista[position] as String))
        }else if(viewHolder is ViewHolder){
            viewHolder.bind((usuarioLista[position] as PersonaUi), itemClickUsuario)
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

    fun setList(usuarioLista: MutableList<Any>) {
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
            Glide.with(img_foto)
                    .load(usuarioUi.imagen)
                    .apply(Utils.getGlideRequestOptions())
                    .into(img_foto)

            txt_nombre.text = usuarioUi.nombre


            check_select.setChecked(usuarioUi.alumnoSelected || usuarioUi.padreSelected)
            check_alumno.setChecked(usuarioUi.alumnoSelected)
            check_padres.setChecked(usuarioUi.padreSelected)

            val states = arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf())
            val colors = intArrayOf(ContextCompat.getColor(itemView.context, R.color.md_blue_grey_20), ContextCompat.getColor(itemView.context, R.color.md_blue_grey_20))
            CompoundButtonCompat.setButtonTintList(check_select, ColorStateList(states, colors))
            CompoundButtonCompat.setButtonTintList(check_alumno, ColorStateList(states, colors))
            CompoundButtonCompat.setButtonTintList(check_padres, ColorStateList(states, colors))
        }
    }

}