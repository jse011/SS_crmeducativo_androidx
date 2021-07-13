package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi
import kotlinx.android.synthetic.main.item_tipo_evento_docente.view.*

class EventosTipoAdapter(val listener: (TiposEventosUi) -> Unit): RecyclerView.Adapter<EventosTipoAdapter.ViewHolder>() {

    private var tipoEventosList:MutableList<TiposEventosUi> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tipo_evento_docente, parent,false))

    override fun getItemCount(): Int = tipoEventosList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(tipoEventosList[position],listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tiposEventosUi: TiposEventosUi, listener: (TiposEventosUi) -> Unit) = with(itemView){
            txt_tipo.text = tiposEventosUi.nombre
            //EVENTOS, NOTICIA, ACTIVIDADES, TAREAS, CITAS, DEFAULT,TODOS;
            val (recurso,rgb) = when(tiposEventosUi.tipos){
                TiposUi.EVENTOS->Pair(R.drawable.ic_rocket, "#bfca52")
                TiposUi.NOTICIA->Pair(R.drawable.ic_newspaper_o,"#ffc107")
                TiposUi.ACTIVIDADES->Pair(R.drawable.ic_gif,"#ff6b9d")
                TiposUi.TAREAS->Pair(R.drawable.ic_tasks,"#ff9800")
                TiposUi.CITAS->Pair(R.drawable.ic_calendar_3,"#00bcd4")
                TiposUi.DEFAULT->Pair(R.drawable.ic_calendar_3,"#00BCD4")
                TiposUi.AGENDA->Pair(R.drawable.ic_graduation_cap,"#71bb74")
                TiposUi.TODOS->Pair(R.drawable.ic_check_square,"#0091EA")
                else -> {
                    Pair(R.drawable.ic_calendar,"#00BCD4")
                }
            }

            Glide.with(img_tipo)
                    .load(recurso)
                    .into(img_tipo)

            var color = Color.RED
            try {
                color = Color.parseColor(rgb)
            }catch (e:Exception){
                e.printStackTrace()
            }

            card_view_eventos.setCardBackgroundColor(color)

            if(tiposEventosUi.toogle){
                itemView.alpha = 1f
            }else{
                itemView.alpha = .6f
            }

            setOnClickListener{listener(tiposEventosUi)}
        }
    }

    fun setList(tiposEventosList: MutableList<TiposEventosUi>){
        this.tipoEventosList.clear()
        this.tipoEventosList.addAll(tiposEventosList)
        notifyDataSetChanged()
    }

}