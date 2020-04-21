package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.consultoraestrategia.ss_crmeducativo.R
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
                    TiposUi.EVENTOS->Pair(R.drawable.ic_sun, "#4CAF50")
                    TiposUi.NOTICIA->Pair(R.drawable.ic_sun_1,"#03A9F4")
                    TiposUi.ACTIVIDADES->Pair(R.drawable.ic_calendar_day,"#FF9800")
                    TiposUi.TAREAS->Pair(R.drawable.ic_tareas_white,"#E91E63")
                    TiposUi.CITAS->Pair(R.drawable.ic_calendar_day,"#00BCD4")
                    TiposUi.DEFAULT->Pair(R.drawable.ic_calendar_day,"#E91E63")
                    TiposUi.TODOS->Pair(R.drawable.ic_left_arrow,"#00BCD4")
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
                itemView.alpha = .5f
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