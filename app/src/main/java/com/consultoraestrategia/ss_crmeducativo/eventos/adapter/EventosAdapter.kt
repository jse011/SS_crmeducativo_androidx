package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper
import com.consultoraestrategia.ss_crmeducativo.util.Utils
import com.consultoraestrategia.ss_crmeducativo.utils.LinkUtils
import com.shehabic.droppy.DroppyMenuItem
import com.shehabic.droppy.DroppyMenuPopup
import com.shehabic.droppy.animations.DroppyFadeInAnimation
import kotlinx.android.synthetic.main.item_colegio_eventos.view.*
import java.io.ByteArrayOutputStream

class EventosAdapter(val itemClickLike: (EventosUi) -> Unit,
                     val itemClickEventos: (EventosUi, EventoAdjuntoUi?) -> Unit,
                     val itemRenderEvento: (EventosUi, EventosAdapter.ViewHolder) -> Unit,
                     val itemClickEnviar: (EventosUi) -> Unit,
                     val itemClickInfoEnviar: (EventosUi) -> Unit,
                     val onOpEditarEventoClicked: (EventosUi) -> Unit,
                     val onOpEventoDelteClicked: (EventosUi) -> Unit,
                     val itemClickAdjunto: (EventosUi, EventoAdjuntoUi, Boolean) -> Unit): RecyclerView.Adapter<EventosAdapter.ViewHolder>() {

    private var eventosUiList:MutableList<EventosUi> = ArrayList()

    var recyclerView: RecyclerView?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_colegio_eventos, parent,false))

    override fun getItemCount(): Int = eventosUiList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        viewHolder.bind(eventosUiList[position],itemClickLike,itemClickEventos, itemRenderEvento,itemClickEnviar, itemClickInfoEnviar,onOpEditarEventoClicked, onOpEventoDelteClicked, itemClickAdjunto)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LinkUtils.OnClickListener{
        private var adjuntoUiPreview1: EventoAdjuntoUi? = null
        private var adjuntoUiPreview2: EventoAdjuntoUi? = null
        private var adjuntoUiPreview3: EventoAdjuntoUi? = null
        private var adjuntoUiPreview4: EventoAdjuntoUi? = null
        var eventosUi: EventosUi? = null;

        fun bind(eventosUi: EventosUi,
                 itemClickLike: (EventosUi) -> Unit,
                 itemClickEventos: (EventosUi, EventoAdjuntoUi?) -> Unit,
                 itemRenderEvento: (EventosUi, EventosAdapter.ViewHolder) -> Unit,
                 itemClickEnviar: (EventosUi) -> Unit,
                 itemClickInfoEnviar: (EventosUi) -> Unit,
                 onOpEditarEventoClicked: (EventosUi)-> Unit,
                 onOpEventoDelteClicked: (EventosUi)-> Unit,
                 itemClickAdjunto: (EventosUi, EventoAdjuntoUi, Boolean)->Unit) = with(itemView) {


            cont_enviar.setOnClickListener({
                Log.d(TAG, "setOnClickListener")
                itemClickInfoEnviar(eventosUi)
            })

            btn_enviar.setOnClickListener({
                Log.d(TAG, "btn_enviar")
                eventosUi.publicado = !eventosUi.publicado
                eventosUi.loading = true
                setEnviar(eventosUi)
                setProgresEnviar(eventosUi)
                btn_enviar.postDelayed({ itemClickEnviar(eventosUi) }, 400)
            })


            btn_me_gusta.setOnClickListener({
                Log.d(TAG, "cont_me_gusta")

                cont_me_gusta.postDelayed({
                    if (!eventosUi.like) {
                        eventosUi.like = true
                    } else {
                        eventosUi.like = false
                    }
                    var cantidad: Int = eventosUi.likeCount
                    if (eventosUi.like) {
                        cantidad++
                    } else {
                        cantidad--
                    }
                    if (cantidad < 0) cantidad = 0
                    eventosUi.likeCount = cantidad
                    changeLike(eventosUi)
                }, 400)

                itemClickLike(eventosUi)
            })

            spinne.setOnClickListener({
                DroppyMenuPopup.Builder(spinne.context, spinne)
                        .addMenuItem(DroppyMenuItem("Editar"))
                        .addMenuItem(DroppyMenuItem("Eliminar")) //.addMenuItem(new DroppyMenuItem("Notificar por Mensaje"))
                        .triggerOnAnchorClick(false)
                        .setOnClick { v, positionMenu ->
                            Log.d("positionMenu:", positionMenu.toString())
                            when (positionMenu) {
                                0 -> onOpEditarEventoClicked(eventosUi)
                                1 -> onOpEventoDelteClicked(eventosUi)
                            }
                        }
                        .setOnDismissCallback {  }
                        .setPopupAnimation(DroppyFadeInAnimation())
                        .build().show()
            })

            this@ViewHolder.eventosUi = eventosUi;
            preview_1.setOnClickListener({
                itemClickEventos(eventosUi, adjuntoUiPreview1)
            });
            preview_2.setOnClickListener({
                itemClickEventos(eventosUi, adjuntoUiPreview2)
            });
            preview_3.setOnClickListener({
                itemClickEventos(eventosUi, adjuntoUiPreview3)
            });
            preview_4.setOnClickListener({
                itemClickEventos(eventosUi, adjuntoUiPreview4)
            });


            btn_me_compartir.setOnClickListener {
                val bytes = ByteArrayOutputStream()
                IntentHelper.sendEmailUri(itemView.context, null, eventosUi.titulo, eventosUi.descripcion, Uri.parse(eventosUi.imagen))
            }


            text_contenido.text = eventosUi.descripcion
            txt_titulo.text = eventosUi.titulo
            itemRenderEvento(eventosUi, this@ViewHolder)
            when (eventosUi.tiposUi.tipos) {
                TiposUi.EVENTOS -> {
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#bfca52"))
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#bfca52"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_rocket))
                }
                TiposUi.NOTICIA -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#ffc107"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#ffc107"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_newspaper_o))
                }
                TiposUi.ACTIVIDADES -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#ff6b9d"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#ff6b9d"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_gif))
                }
                TiposUi.TAREAS -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#ff9800"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#ff9800"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_tasks))
                }
                TiposUi.CITAS -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#00bcd4"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#00bcd4"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_calendar_3))
                }
                TiposUi.AGENDA -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#71bb74"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#71bb74"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_graduation_cap))
                }
                else -> {
                    cont_tipo.setCardBackgroundColor(Color.parseColor("#00BCD4"))
                    cont_fecha.setCardBackgroundColor(Color.parseColor("#00BCD4"))
                    img_tipo_ico.setImageDrawable(ContextCompat.getDrawable(img_tipo_ico.getContext(), R.drawable.ic_calendar_3))
                }
            }

            txt_tipo_evento.text = eventosUi.tiposUi.nombre;

            var fecha: String? = eventosUi.tiposUi.nombre
            fecha += when (eventosUi.tiposUi.tipos) {
                TiposUi.EVENTOS ->  if(eventosUi.fechaEvento > 0)" " + Utils.tiempoFechaCreacion(eventosUi.fechaEvento)else ""
                TiposUi.NOTICIA -> if(eventosUi.fechaEvento > 0)" " + Utils.getFechaDiaMesAnho(eventosUi.fechaEvento)else ""
                else -> if(eventosUi.fechaEvento > 0)" " + Utils.tiempoFechaCreacion(eventosUi.fechaEvento)else ""
            }
          
            txt_fecha.text = fecha;
            cont_fecha.visibility = (if (eventosUi.fechaEvento > 0) View.VISIBLE else View.GONE)
            nombrePersonaEvento.text = eventosUi.nombreEntidad;
            //imagenEntidad.setVisibility(View.VISIBLE);
            Glide.with(circleImageView)
                    .load(eventosUi.fotoEntidad)
                    .apply(Utils.getGlideRequestOptionsSimple())
                    .into(circleImageView)
            if (eventosUi.tiposUi.tipos === TiposUi.AGENDA) {
                val fechaPublicacion: String = eventosUi.nombreCalendario + " " + eventosUi.nombreFechaPublicion
                directivo.text = fechaPublicacion
            } else {
                //" - Publicado jue 1 jun 2021"
                val fechaPublicacion: String = eventosUi.cargo + " " + eventosUi.nombreFechaPublicion
                directivo.text = fechaPublicacion
            }
            nombre_directivo.text = eventosUi.persona;
            var megusta = "me gusta"
            if (eventosUi.likeCount != 0) {
                megusta = eventosUi.likeCount.toString() + " me gusta"
            } else if (eventosUi.likeCount > 1000) {
                megusta += "1k me gusta"
            }

            if (eventosUi.like) {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like))
            } else {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like_disabled))
            }
            txt_megusta.setText(megusta)
            LinkUtils.autoLink(text_contenido, this@ViewHolder)
            
            if (eventosUi.tiposUi.tipos == TiposUi.NOTICIA || eventosUi.tiposUi.tipos == TiposUi.EVENTOS || eventosUi.tiposUi.tipos == TiposUi.AGENDA) {
                if(eventosUi.adjuntoUiPreviewList.isNotEmpty()){
                    contentEvento.visibility = View.VISIBLE
                    when (eventosUi.adjuntoUiPreviewList.size) {
                        1 -> {
                            confPreviewOne()
                            preview_1.bindPreview(eventosUi.adjuntoUiPreviewList.get(0).imagePreview, eventosUi.adjuntoUiPreviewList.get(0).video)
                            adjuntoUiPreview1 = eventosUi.adjuntoUiPreviewList.get(0)
                        }
                        2 -> {
                            confPreviewTwo()
                            preview_1.bindPreview(eventosUi.adjuntoUiPreviewList.get(0).imagePreview, eventosUi.adjuntoUiPreviewList.get(0).video)
                            preview_2.bindPreview(eventosUi.adjuntoUiPreviewList.get(1).imagePreview, eventosUi.adjuntoUiPreviewList.get(1).video)
                            adjuntoUiPreview1 = eventosUi.adjuntoUiPreviewList.get(0)
                            adjuntoUiPreview2 = eventosUi.adjuntoUiPreviewList.get(1)
                        }
                        3 -> {
                            confPreviewThree()
                            preview_1.bindPreview(eventosUi.adjuntoUiPreviewList.get(0).imagePreview, eventosUi.adjuntoUiPreviewList.get(0).video)
                            preview_3.bindPreview(eventosUi.adjuntoUiPreviewList.get(1).imagePreview, eventosUi.adjuntoUiPreviewList.get(1).video)
                            preview_4.bindPreview(eventosUi.adjuntoUiPreviewList.get(2).imagePreview, eventosUi.adjuntoUiPreviewList.get(2).video)
                            adjuntoUiPreview1 = eventosUi.adjuntoUiPreviewList.get(0)
                            adjuntoUiPreview3 = eventosUi.adjuntoUiPreviewList.get(1)
                            adjuntoUiPreview4 = eventosUi.adjuntoUiPreviewList.get(2)
                        }
                        4 -> {
                            confPreviewFour()
                            preview_1.bindPreview(eventosUi.adjuntoUiPreviewList.get(0).imagePreview, eventosUi.adjuntoUiPreviewList.get(0).video)
                            preview_2.bindPreview(eventosUi.adjuntoUiPreviewList.get(1).imagePreview, eventosUi.adjuntoUiPreviewList.get(1).video)
                            preview_3.bindPreview(eventosUi.adjuntoUiPreviewList.get(2).imagePreview, eventosUi.adjuntoUiPreviewList.get(2).video)
                            preview_4.bindPreview(eventosUi.adjuntoUiPreviewList.get(3).imagePreview, eventosUi.adjuntoUiPreviewList.get(3).video)
                            adjuntoUiPreview1 = eventosUi.adjuntoUiPreviewList.get(0)
                            adjuntoUiPreview2 = eventosUi.adjuntoUiPreviewList.get(1)
                            adjuntoUiPreview3 = eventosUi.adjuntoUiPreviewList.get(2)
                            adjuntoUiPreview4 = eventosUi.adjuntoUiPreviewList.get(3)
                        }
                        else -> {
                            confPreviewFour()
                            preview_1.bindPreview( eventosUi.adjuntoUiPreviewList.get(0).imagePreview,  eventosUi.adjuntoUiPreviewList.get(0).video)
                            preview_2.bindPreview( eventosUi.adjuntoUiPreviewList.get(1).imagePreview,  eventosUi.adjuntoUiPreviewList.get(1).video)
                            preview_3.bindPreview( eventosUi.adjuntoUiPreviewList.get(2).imagePreview,  eventosUi.adjuntoUiPreviewList.get(2).video)
                            preview_4.bindMore( eventosUi.adjuntoUiPreviewList.get(3).imagePreview,  eventosUi.adjuntoUiPreviewList.get(3).video,  eventosUi.adjuntoUiPreviewList.size - 4)
                            adjuntoUiPreview1 =  eventosUi.adjuntoUiPreviewList.get(0)
                            adjuntoUiPreview2 =  eventosUi.adjuntoUiPreviewList.get(1)
                            adjuntoUiPreview3 =  eventosUi.adjuntoUiPreviewList.get(2)
                            adjuntoUiPreview4 =  eventosUi.adjuntoUiPreviewList.get(3)
                        }
                    }
                } else {
                    contentEvento.visibility = View.GONE
                    previewUnBindAll()
                }
            }else{
                contentEvento.visibility = View.GONE
                previewUnBindAll();
            }
            val count: Int
            count = when (eventosUi.adjuntoUiList.size) {
                1 -> 2
                2 -> 2
                3 -> 3
                else -> 4
            }
            val adjuntoEventoAdapter = AdjuntoEventoAdapterMore(eventosUi.adjuntoUiList, eventosUi, AdjuntoEventoAdapterMore.Listener {
                eventoUi, adjuntoUi, more -> itemClickAdjunto(eventoUi, adjuntoUi, more)
            })

            rc_recursos.adapter = adjuntoEventoAdapter
            rc_recursos.layoutManager = GridLayoutManager(rc_recursos.context, count)

            if (eventosUi.adjuntoUiList.isEmpty()) {
                line_recurso.visibility = View.GONE
                content_recurso.visibility = View.GONE
            } else {
                line_recurso.visibility = View.VISIBLE
                content_recurso.visibility = View.VISIBLE
            }

            val info_envio = "Información del envío"
            txt_enviar_info.text = info_envio

            if(eventosUi.externo){
                btn_enviar.visibility = View.INVISIBLE
                btn_me_gusta.visibility = View.VISIBLE
                cont_enviar.visibility = View.INVISIBLE
                cont_me_gusta.visibility = View.VISIBLE
                spinne.visibility = View.GONE
            }else{
                btn_enviar.visibility = View.VISIBLE
                btn_me_gusta.visibility = View.GONE
                cont_enviar.visibility = View.VISIBLE
                cont_me_gusta.visibility = View.INVISIBLE
                spinne.visibility = View.VISIBLE
            }

            setEnviar(eventosUi)
            setProgresEnviar(eventosUi)
/*
            if(eventosUi.tiposUi.tipos == TiposUi.NOTICIA || eventosUi.tiposUi.tipos == TiposUi.EVENTOS ||
                    (eventosUi.tiposUi.tipos ==  TiposUi.AGENDA && !TextUtils.isEmpty(eventosUi.imagen))) {
                    conten_image.visibility = View.VISIBLE
                    if (!TextUtils.isEmpty(eventosUi.imagen)) {
                        lay_placeholder.visibility = View.VISIBLE
                        imagenEvento.visibility = View.INVISIBLE
                        imagenEvento.setImageDrawable(null)

                        Glide.with(this)
                                .load(eventosUi.imagen)
                                .thumbnail(0.25f)
                                .apply(Utils.getGlideRequestOptionsSimple()
                                        .centerCrop())
                                .listener(object : RequestListener<Drawable?> {
                                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                        lay_placeholder.setVisibility(View.VISIBLE)
                                        imagenEvento.visibility = View.INVISIBLE
                                        return false
                                    }

                                    override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                        lay_placeholder.setVisibility(View.GONE)
                                        imagenEvento.visibility = View.VISIBLE
                                        return false
                                    }
                                })
                                .into(imagenEvento)
                    } else {
                        imagenEvento.setImageDrawable(null)
                        imagenEvento.visibility = View.GONE
                        conten_image.visibility = View.GONE
                    }
                } else  {
                    imagenEvento.visibility = View.GONE
                    conten_image.visibility = View.GONE
                    Glide.with(this).clear(imagenEvento)
                    imagenEvento.setImageDrawable(null)
                }

            nombrePersonaEvento.text = (eventosUi.nombreEntidad)
            if (!TextUtils.isEmpty(eventosUi.fotoEntidad)) {
                circleImageView.setVisibility(View.VISIBLE)
                Glide.with(this)
                        .load(eventosUi.fotoEntidad)
                        .apply(Utils.getGlideRequestOptionsSimple().override(90))
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                Glide.with(itemView.context)
                                        .load(R.drawable.insignia_colegio)
                                        .apply(Utils.getGlideRequestOptionsSimple())
                                        .into(circleImageView)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        })
                        .into(circleImageView)
            } else {
                circleImageView.setImageDrawable(null)
                circleImageView.setVisibility(View.GONE)
            }

            directivo.setText(eventosUi.cargo)
            nombre_directivo.setText(eventosUi.persona)
            var megusta = "me gusta"
            if (eventosUi.likeCount != 0) {
                megusta = eventosUi.likeCount.toString() + " me gusta"
            } else if (eventosUi.likeCount > 1000) {
                megusta += "1k me gusta"
            }

            if (eventosUi.like) {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like))
            } else {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like_disabled))
            }
            txt_megusta.setText(megusta)

            var tipo: String? = eventosUi.tiposUi.nombre


            tipo += when (eventosUi.tiposUi.tipos) {
                TiposUi.EVENTOS ->  if(eventosUi.fechaEvento > 0)" " + Utils.tiempoFechaCreacion(eventosUi.fechaEvento)else ""
                TiposUi.NOTICIA -> if(eventosUi.fechaEvento > 0)" del " + Utils.getFechaDiaMesAnho(eventosUi.fechaEvento)else ""
                else -> if(eventosUi.fechaEvento > 0)" " + Utils.tiempoFechaCreacion(eventosUi.fechaEvento)else ""
            }

            fechaPublicacion.text = tipo

            val info_envio = "Información del envío"
            txt_enviar_info.text = info_envio
            cont_enviar.visibility = if(eventosUi.tiposUi.tipos==TiposUi.AGENDA) View.VISIBLE else View.GONE

            if(eventosUi.externo){
                btn_enviar.visibility = View.INVISIBLE
                cont_enviar.visibility = View.INVISIBLE
                spinne.visibility = View.INVISIBLE
            }else{
                btn_enviar.visibility = View.VISIBLE
                cont_enviar.visibility = View.VISIBLE
                spinne.visibility = View.VISIBLE
            }

            setEnviar(eventosUi)
            setProgresEnviar(eventosUi)

            txt_titulo_calendario.text = eventosUi.nombreCalendario
            LinkAppUtils.autoLink(text_contenido, object: LinkAppUtils.OnClickListener{
                override fun onLinkClicked(v: View?, link: String?) {

                }

                override fun onClicked(v: View?) {

                }

            })*/
        }

        fun setEnviar(eventosUi: EventosUi) = with(itemView){

            if(eventosUi.publicado){
                img_enviar.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_internet))
                txt_enviar.text = "Publicar"
            }else{
                img_enviar.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_global))
                txt_enviar.text = "Sin publicar"
            }
        }

        fun setProgresEnviar(eventosUi: EventosUi)= with(itemView){
            if(eventosUi.loading){
                img_enviar.visibility = View.INVISIBLE
                prog_enviar.visibility = View.VISIBLE
            }else{
                img_enviar.visibility = View.VISIBLE
                prog_enviar.visibility = View.GONE
            }
            btn_enviar.setEnabled(!eventosUi.loading)
        }
        
        

        override fun onClicked(v: View?) {
           
        }

        override fun onLinkClicked(v: View?, link: String?) {
            
        }

        fun previewUnBindAll() = with(itemView)  {
            preview_1.unbind()
            preview_2.unbind()
            preview_3.unbind()
            preview_4.unbind()
        }

        fun confPreviewOne() = with(itemView)  {
            val contentPreview1Params = content_preview_1.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview1Params.weight = 1f
            val preview1Params = preview_1.getLayoutParams() as LinearLayout.LayoutParams
            preview1Params.weight = 1f
            content_preview_1.setVisibility(View.VISIBLE)
            preview_1.setVisibility(View.VISIBLE)
            preview_2.setVisibility(View.GONE)
            content_preview_2.setVisibility(View.GONE)
            separador_vertical_1.setVisibility(View.GONE)
            separador_vertical_2.setVisibility(View.GONE)
            separador_horizontal.setVisibility(View.GONE)
            preview_2.unbind()
            preview_3.unbind()
            preview_4.unbind()
        }

        fun confPreviewTwo() = with(itemView)  {
            val contentPreview1Params = content_preview_1.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview1Params.weight = 1f
            val preview1Params = preview_1.getLayoutParams() as LinearLayout.LayoutParams
            preview1Params.weight = 0.5f
            val preview2Params = preview_2.getLayoutParams() as LinearLayout.LayoutParams
            preview2Params.weight = 0.5f
            content_preview_1.setVisibility(View.VISIBLE)
            preview_1.setVisibility(View.VISIBLE)
            preview_2.setVisibility(View.VISIBLE)
            content_preview_2.setVisibility(View.GONE)
            separador_vertical_1.setVisibility(View.VISIBLE)
            separador_vertical_2.setVisibility(View.GONE)
            separador_horizontal.setVisibility(View.GONE)
            preview_3.unbind()
            preview_4.unbind()
        }

        fun confPreviewThree() = with(itemView)  {
            val contentPreview1Params = content_preview_1.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview1Params.weight = 0.5f
            val preview1Params = preview_1.getLayoutParams() as LinearLayout.LayoutParams
            preview1Params.weight = 1f
            val contentPreview2Params = content_preview_2.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview2Params.weight = 0.5f
            val preview3Params = preview_3.getLayoutParams() as LinearLayout.LayoutParams
            preview3Params.weight = 0.5f
            val preview4Params = preview_4.getLayoutParams() as LinearLayout.LayoutParams
            preview4Params.weight = 0.5f
            content_preview_1.setVisibility(View.VISIBLE)
            preview_1.setVisibility(View.VISIBLE)
            preview_2.setVisibility(View.GONE)
            content_preview_2.setVisibility(View.VISIBLE)
            preview_3.setVisibility(View.VISIBLE)
            preview_4.setVisibility(View.VISIBLE)
            separador_vertical_1.setVisibility(View.GONE)
            separador_vertical_2.setVisibility(View.VISIBLE)
            separador_horizontal.setVisibility(View.VISIBLE)
            preview_2.unbind()
        }

        fun confPreviewFour() = with(itemView)  {
            val contentPreview1Params = content_preview_1.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview1Params.weight = 0.5f
            val preview1Params = preview_1.getLayoutParams() as LinearLayout.LayoutParams
            preview1Params.weight = 0.5f
            val preview2Params = preview_2.getLayoutParams() as LinearLayout.LayoutParams
            preview2Params.weight = 0.5f
            val contentPreview2Params = content_preview_2.getLayoutParams() as LinearLayout.LayoutParams
            contentPreview2Params.weight = 0.5f
            val preview3Params = preview_3.getLayoutParams() as LinearLayout.LayoutParams
            preview3Params.weight = 0.5f
            val preview4Params = preview_4.getLayoutParams() as LinearLayout.LayoutParams
            preview4Params.weight = 0.5f
            content_preview_1.setVisibility(View.VISIBLE)
            preview_1.setVisibility(View.VISIBLE)
            preview_2.setVisibility(View.VISIBLE)
            content_preview_2.setVisibility(View.VISIBLE)
            preview_3.setVisibility(View.VISIBLE)
            preview_4.setVisibility(View.VISIBLE)
            separador_vertical_1.setVisibility(View.VISIBLE)
            separador_vertical_2.setVisibility(View.VISIBLE)
            separador_horizontal.setVisibility(View.VISIBLE)
        }

        fun changeLike(eventosUi: EventosUi) = with(itemView){
            var megusta = "me gusta"
            if (eventosUi.likeCount != 0) {
                megusta = eventosUi.likeCount.toString() + " me gusta"
            } else if (eventosUi.likeCount > 1000) {
                megusta += "1k me gusta"
            }

            if (eventosUi.like) {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like))
            } else {
                img_megusta.setImageDrawable(ContextCompat.getDrawable(img_megusta.getContext(), R.drawable.ic_like_disabled))
            }
            txt_megusta.setText(megusta)
        }
    }


    fun setObjetList(eventosUiList: List<EventosUi>) {
        Log.d("EventosFragment", "setObjetList ")
        this.eventosUiList.clear()
        this.eventosUiList.addAll(eventosUiList)
        notifyDataSetChanged()
    }

    fun setPositionBanner(eventosUi: EventosUi?) {
        Log.d("EventosFragment", "setPositionBanner ")
        val position: Int = eventosUiList.indexOf(eventosUi)
        Log.d(TAG, "position $position")
        if (position > -1) {
            (recyclerView?.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(position, 8)
        }
    }

    fun update(eventosUiSelected: EventosUi) {
        val postion: Int = this.eventosUiList.indexOf(eventosUiSelected);
        if(postion != -1){
            eventosUiList.set(postion, eventosUiSelected);
            notifyItemChanged(postion)
        }
    }

    companion object {
        private val TAG: String = "EventosAdapter"
    }

}