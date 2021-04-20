package com.consultoraestrategia.ss_crmeducativo.eventos.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper
import com.consultoraestrategia.ss_crmeducativo.util.Utils
import com.consultoraestrategia.ss_crmeducativo.utils.LinkAppUtils
import com.shehabic.droppy.DroppyMenuItem
import com.shehabic.droppy.DroppyMenuPopup
import com.shehabic.droppy.animations.DroppyFadeInAnimation
import kotlinx.android.synthetic.main.item_colegio_eventos.view.*
import kotlinx.android.synthetic.main.layout_placeholder.view.*
import java.io.ByteArrayOutputStream

class EventosAdapter(val itemClickLike: (EventosUi) -> Unit,
                     val itemClickEventos: (EventosUi) -> Unit,
                     val itemRenderEvento: (EventosUi) -> Unit,
                     val itemClickEnviar: (EventosUi) -> Unit,
                     val itemClickInfoEnviar: (EventosUi) -> Unit,
                     val onOpEditarEventoClicked: (EventosUi) -> Unit,
                     val onOpEventoDelteClicked: (EventosUi) -> Unit): RecyclerView.Adapter<EventosAdapter.ViewHolder>() {

    private var eventosUiList:MutableList<EventosUi> = ArrayList()

    var recyclerView: RecyclerView?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_colegio_eventos, parent,false))

    override fun getItemCount(): Int = eventosUiList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        viewHolder.bind(eventosUiList[position],itemClickLike,itemClickEventos, itemRenderEvento,itemClickEnviar, itemClickInfoEnviar,onOpEditarEventoClicked, onOpEventoDelteClicked)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(eventosUi: EventosUi,
                 itemClickLike: (EventosUi) -> Unit,
                 itemClickEventos: (EventosUi) -> Unit,
                 itemRenderEvento: (EventosUi) -> Unit,
                 itemClickEnviar: (EventosUi) -> Unit,
                 itemClickInfoEnviar: (EventosUi) -> Unit,
                 onOpEditarEventoClicked: (EventosUi)-> Unit,
                 onOpEventoDelteClicked: (EventosUi)-> Unit)  = with(itemView) {


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


            cont_me_gusta.setOnClickListener({
                Log.d(TAG, "cont_me_gusta")
                cont_me_gusta.postDelayed({ itemClickLike(eventosUi) }, 400)
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

            setOnClickListener({
                itemClickEventos(eventosUi)
            })

            btn_me_compartir.setOnClickListener {
                val bytes = ByteArrayOutputStream()
                IntentHelper.sendEmailUri(itemView.context, null, eventosUi.titulo, eventosUi.descripcion, Uri.parse(eventosUi.imagen))


            }


            text_contenido.text = eventosUi.descripcion?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            txt_titulo.text = eventosUi.titulo
            itemRenderEvento(eventosUi)
            Log.d(TAG, eventosUi.tiposUi.tipos.toString() + "")
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

            })
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

    companion object {
        private val TAG: String = "EventosAdapter"
    }

}