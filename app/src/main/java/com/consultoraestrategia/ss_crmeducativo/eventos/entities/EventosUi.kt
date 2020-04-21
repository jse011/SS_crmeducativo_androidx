package com.consultoraestrategia.ss_crmeducativo.eventos.entities

class EventosUi {

    enum class Filtro {
        DEFAULT, CALENDAR
    }

    var nombreCalendario: String? = null
    var cantidaEnviar: Int = 0
    var idEvento: String? = null
    var titulo: String? = null
    var tipo: String? = null
    var descripcion: String? = null
    var calendarioId: String? = null
    var estado = false
    var fechaEvento: Long = 0
    var imagen: String? = null
    var fechaCreacion: Long = 0
    var idUsuarioCreacion = 0
    var nombrePersonUi: String? = null
    var nombreEntidad: String? = null
    var fotoEntidad: String? = null
    var tiposUi: TiposEventosUi = TiposEventosUi(TiposUi.NOTICIA)
    var tipoFiltro = Filtro.DEFAULT
    var fechaUiEvento: FechaUi? = null
    var fechaUiCreacion: FechaUi? = null
    var count = 0
    var likeCount = 0
    var like = false
    var persona: String? = null
    var cargo: String? = null
    var datosfirefase = false
    var publicado = false
    var loading = false

    var externo = false
}