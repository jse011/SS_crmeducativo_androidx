package com.consultoraestrategia.ss_crmeducativo.eventos

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import com.consultoraestrategia.ss_crmeducativo.R
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity
import com.consultoraestrategia.ss_crmeducativo.crearEvento.CrearEventoActivity
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventoColumnCountProvider
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventosAdapter
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.EventosTipoAdapter
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.local.EventoLocalDataSource
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.remote.EventoRemoteDataSource
import com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase.*
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogFragment
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento.InformacionEventoDialogView
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioDialog
import com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaUsuarios.ListaUsuarioView
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor
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
        eventosAdapter = EventosAdapter({onClickLike(it)}, { itemClickEventos(it)}, { itemRenderEvento(it)} ,{itemClickEnviar(it)} ,{itemClickInfoEnviar(it)},{ onOpEditarEventoClicked(it)},{onOpEventoDelteClicked(it)})
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
    private fun itemRenderEvento(eventosUi: EventosUi) {
        presenter.renderItemEvento(eventosUi)
    }

    private fun itemClickEventos(eventosUi: EventosUi) {
        presenter.onclikInfoEventos(eventosUi)
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
        val eventoDialogFragment = InformacionEventoDialogFragment.newInstance()
        eventoDialogFragment.show(supportFragmentManager, "InformacionEventoDialogFragment2")
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

    override fun showCrearEventos(usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int) {
        CrearEventoActivity.start(this, "", usuarioId, georeferenciaId, empleadoId, anioAcademicoId, entidadId)
    }

    override fun showEditarEventos(idEvento: String?, usuarioId: Int, georeferenciaId: Int, empleadoId: Int, anioAcademicoId: Int, entidadId: Int) {
        CrearEventoActivity.start(this, idEvento, usuarioId, georeferenciaId, empleadoId, anioAcademicoId, entidadId)
    }

    override fun showListaUsuarioDiaolog() {
        val listaUsuario = ListaUsuarioDialog()
        listaUsuario.show(supportFragmentManager, "ListaUsuarioDialog")
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
        }
    }

    override fun onFragmentActivityCreated(f: Fragment?, savedInstanceState: Bundle?) {
        if (f is InformacionEventoDialogView) {
            presenter.attachView(f as InformacionEventoDialogView?)
            (f as InformacionEventoDialogView).setPresenter(presenter)
        }else if (f is ListaUsuarioView) {
            presenter.attachView(f as ListaUsuarioView?)
            (f as ListaUsuarioView).setPresenter(presenter)
        }
    }
}