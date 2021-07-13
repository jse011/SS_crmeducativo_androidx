package com.consultoraestrategia.ss_crmeducativo.eventos

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity
import com.consultoraestrategia.ss_crmeducativo.crearEvento.CrearEventoActivity
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.PreviewArchivoActivity
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.util.DriveYoutubePreview
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventoColumnCountProvider
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventosAdapter
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventosTipoAdapter
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.local.EventoLocalDataSource
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.remote.EventoRemoteDataSource
import com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase.*
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogFragment
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos.DialogListaBannerEvento
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos.InformacionListaEventosView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioDialog
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioView
import com.consultoraestrategia.ss_crmeducativo.eventos.listaAdjuntoDownload.AdjuntoEventoDownload
import com.consultoraestrategia.ss_crmeducativo.eventos.listaAdjuntoDownload.DialogAdjuntoDownload
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper
import com.consultoraestrategia.ss_crmeducativo.util.LifeCycleFragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_eventos_docente.*

class EventosActivty : BaseActivity<EventosView, EventosPresenter>(), EventosView, SwipeRefreshLayout.OnRefreshListener, LifeCycleFragment.LifecycleListener {

    private lateinit var eventosAdapter: EventosAdapter
    private lateinit var adapter: EventosTipoAdapter
    private lateinit var adapterEventos: EventosAdapter

    override fun getTag(): String = "EventosActivtyTAG"

    override fun getActivity(): AppCompatActivity = this;

    override fun getPresenter(): EventosPresenter {
       val eventosRepository = EventosRepository(EventoLocalDataSource(), EventoRemoteDataSource(UtilServidor.getInstance(), FirebaseDatabase.getInstance()))
        return EventosPresenterImpl(UseCaseHandler(UseCaseThreadPoolScheduler()), resources,
                GetLikeSaveCountLike(eventosRepository),
                SaveLike(eventosRepository),
                SincronizarEventos((eventosRepository)),
                GetEventosColegio(eventosRepository),
                GetTipoEvento(eventosRepository),
                DeleteEvento(eventosRepository),
                EnviarEvento(eventosRepository),
                GetListaUsuario(eventosRepository))
    }

    override fun getBaseView(): EventosView = this

    override fun getExtrasInf(): Bundle ? = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        desbloqOrientation()
    }
    override fun setContentView() {
        setContentView(R.layout.activity_eventos_docente)
        setupToolbar()
        setupAdapter()
        swipe_container.setOnRefreshListener(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(LifeCycleFragment(this), true)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //change color of status bar
            val window = this.window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.parseColor("#FAFAFA")
        }
    }

    private fun setupAdapter() {
        adapter = EventosTipoAdapter(listener = {
            presenter.onClickTipoEvento(it)
        })
        rc_tipo_eventos.adapter = adapter
        val columnCountProvider = EventoColumnCountProvider(this)
        val autoColumnGridLayoutManager = AutoColumnStaggeredGridLayoutManager(OrientationHelper.VERTICAL, 4,this)
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider)
        rc_tipo_eventos.layoutManager = autoColumnGridLayoutManager


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        eventosRv.layoutManager = layoutManager
        eventosAdapter = EventosAdapter({onClickLike(it)}, { eventosUi, eventoAdjuntoUi -> itemClickEventos(eventosUi, eventoAdjuntoUi) }, { eventosUi, viewHolder -> itemRenderEvento(eventosUi, viewHolder) } ,{itemClickEnviar(it)} ,{itemClickInfoEnviar(it)},{ onOpEditarEventoClicked(it)},{onOpEventoDelteClicked(it)},
                { eventosUi, eventoAdjuntoUi, more -> itemClickAdjunto(eventosUi, eventoAdjuntoUi, more) })
        eventosAdapter.recyclerView = eventosRv
        eventosRv.adapter = eventosAdapter
    }

    private fun itemClickInfoEnviar(eventosUi: EventosUi) {
        presenter.itemClickInfoEnviar(eventosUi)
    }

    private fun itemClickEnviar(eventosUi: EventosUi) {
        presenter.itemClickEnviar(eventosUi)
    }

    private fun onOpEventoDelteClicked(eventosUi: EventosUi) {
        presenter.onItemOpcionElimnarEvento(eventosUi)
    }

    private fun onOpEditarEventoClicked(eventosUi: EventosUi) {
        presenter.onItemOpcionEditarEvento(eventosUi)
    }

    private fun itemClickAdjunto(eventosUi: EventosUi, eventoAdjuntoUi:EventoAdjuntoUi, more:Boolean) {
        presenter.onClickAdjunto(eventosUi, eventoAdjuntoUi, more)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.evento_activity_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_crear) {
            presenter.onClickCrearAsistencia()
        }else{
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun itemRenderEvento(eventosUi: EventosUi, viewHolder: EventosAdapter.ViewHolder) {
        presenter.renderItemEvento(eventosUi,{
            if(viewHolder.eventosUi?.idEvento == it.idEvento){
                viewHolder.changeLike(eventosUi)
            }
        })
    }

    private fun itemClickEventos(eventosUi: EventosUi, eventoAdjuntoUi:EventoAdjuntoUi?) {
        presenter.onclikInfoEventos(eventosUi, eventoAdjuntoUi)
    }

    private fun onClickLike(eventosUi: EventosUi) {
        presenter.onClikLike(eventosUi)
    }

    override fun getRootLayout(): ViewGroup = root

    override fun getProgressBar(): ProgressBar ? = null

    override fun setListaEventos(tipoEventosList: MutableList<TiposEventosUi>) {
        adapter.setList(tipoEventosList)
    }

    override fun showListEventos(positionInicial: Boolean, tipoEventosList: MutableList<EventosUi>) {
        if(positionInicial) eventosRv.scrollToPosition(0);
        eventosAdapter.setObjetList(tipoEventosList);
    }

    override fun showDialogInfoEvento() {

    }

    override fun isInternetAvailable(): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun hideTextEmpty() {
        txtVacio.visibility = View.GONE
    }

    override fun showTextEmpty() {
        txtVacio.visibility = View.VISIBLE
    }

    override fun notificationChangeEvento() {

    }

    override fun showCrearEventos(usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int, tutorCargaAcademicaId: Int, cargaCursoId: Int) {
        CrearEventoActivity.start(this, "", usuarioId, georeferenciaId, empleadoId, anioAcademicoId, entidadId,tutorCargaAcademicaId, cargaCursoId)
    }

    override fun showEditarEventos(idEvento: String?, usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int) {
        CrearEventoActivity.start(this, idEvento, usuarioId, georeferenciaId, empleadoId, anioAcademicoId, entidadId, 0, 0)
    }

    override fun showListaUsuarioDiaolog() {
        val listaUsuario = ListaUsuarioDialog()
        listaUsuario.show(supportFragmentManager, "ListaUsuarioDialog")
    }

    override fun showDialogListaBannerEvento() {
        val eventoDialogFragment = DialogListaBannerEvento.newInstance()
        eventoDialogFragment.show(supportFragmentManager, "DialogListaBannerEvento")
    }

    override fun showDialogAdjuntoEvento() {
        val eventoDialogFragment = InformacionEventoDialogFragment.newInstance()
        eventoDialogFragment.show(supportFragmentManager, "InformacionEventoDialogFragment2")
    }

    override fun showDialogEventoDownload() {
        DialogAdjuntoDownload()
                .show(supportFragmentManager, "dialogEventoDownload")
    }

    override fun showVinculo(url: String?) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al abrir el vínculo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showPreviewArchivo(driveYoutubePreview: DriveYoutubePreview?) {
        val intent = Intent(this, PreviewArchivoActivity::class.java)
        intent.putExtra("serial", driveYoutubePreview)
        startActivity(intent)
    }

    override fun startCompartirEvento(eventosUi: EventosUi) {
        Glide
                .with(this)
                .asBitmap()
                .load(eventosUi.imagen)
                .apply(RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(850))
                .listener(object : RequestListener<Bitmap?> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap?>?, isFirstResource: Boolean): Boolean {
                        IntentHelper.sendEmailUri(this@EventosActivty, null, eventosUi.titulo, eventosUi.descripcion, null)
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                })
                .into(object : SimpleTarget<Bitmap?>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                        IntentHelper.sendEmailUri(this@EventosActivty, null, eventosUi.titulo, eventosUi.descripcion, Uri.parse(eventosUi.imagen))
                    }
                })
    }

    override fun updateEvento(eventosUiSelected: EventosUi) {
        eventosAdapter.update(eventosUiSelected);
    }

    override fun onRefresh() {
        presenter.onRefresh()
    }

    override fun showProgress() {
        Log.d(tag, "showProgress")
        swipe_container.setRefreshing(true)
    }

    override fun hideProgress() {
        swipe_container.setRefreshing(false)
    }

    override fun onFragmentViewCreated(f: Fragment?, view: View?, savedInstanceState: Bundle?) {

    }

    override fun onFragmentResumed(f: Fragment?) {

    }

    override fun onFragmentViewDestroyed(f: Fragment?) {
        if (f is InformacionEventoDialogView) {
            presenter.onInformacionEventoDialogViewDestroyed()
        }else if( f is ListaUsuarioView){
            presenter.onListaUsuarioViewDestroyed()
        }else if( f is InformacionListaEventosView){
            presenter.onInformacionListaEventosViewDestroyed()
        } else if (f is AdjuntoEventoDownload) {
            presenter.onAdjuntoEventoDownloadDestroy()
        }
    }

    override fun onFragmentActivityCreated(f: Fragment?, savedInstanceState: Bundle?) {
        if (f is InformacionEventoDialogView) {
            presenter.attachView(f as InformacionEventoDialogView?)
            (f as InformacionEventoDialogView).setPresenter(presenter)
        }else if (f is ListaUsuarioView) {
            presenter.attachView(f as ListaUsuarioView?)
            (f as ListaUsuarioView).setPresenter(presenter)
        }else if (f is InformacionListaEventosView) {
            presenter.attachView(f as InformacionListaEventosView?)
            (f as InformacionListaEventosView).setPresenter(presenter)
        } else if (f is AdjuntoEventoDownload) {
            presenter.attachView(f as AdjuntoEventoDownload?)
            (f as AdjuntoEventoDownload).setPresenter(presenter)
        }
    }
}