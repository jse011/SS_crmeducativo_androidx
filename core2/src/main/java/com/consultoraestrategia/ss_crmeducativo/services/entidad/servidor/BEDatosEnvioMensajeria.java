package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.CanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.entities.CanalDestinoEstadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Intencion;
import com.consultoraestrategia.ss_crmeducativo.entities.IntencionItem;
import com.consultoraestrategia.ss_crmeducativo.entities.InteraccionTextual;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeIntencionItemC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredIntencion;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminadoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEnvioMensajeria  extends BEDatosServidor {
    private List<MensajeUsuarioC> mensajeUsuario;//no se modifico el id de tipo int a String
    private List<MensajeIntencionItemC> mensajeIntencionItem;
    private List<UsuarioCanalComunicacion> usCanalComunicacion;
    private List<CanalComunicacion> canalComunicacion;
    private List<CanalDestinoEstadoC> canalDestinoEstado;
    private List<MensajeC> mensajes;
    private List<Intencion> intenciones;
    private List<IntencionItem> intencionItems;
    private List<ListaUsuario> listaUsuarios;
    private List<ListaUsuarioDetalle> listUsuarioDetalle;
    private List<MensajePredeterminado> listaMensajePredeterminado;
    private List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle;
    private List<MensajePredIntencion> listMensajePredIntencion;
    private List<InteraccionTextual> listInteraccionTextual;



    //public List<BEMensajeArchivo> obtenerMensajeArchivo { get; set; }

    public BEDatosEnvioMensajeria() {
    }

    public BEDatosEnvioMensajeria(List<MensajeUsuarioC> mensajeUsuario, List<MensajeIntencionItemC> mensajeIntencionItem, List<UsuarioCanalComunicacion> usCanalComunicacion, List<CanalComunicacion> canalComunicacion, List<CanalDestinoEstadoC> canalDestinoEstado, List<MensajeC> mensajes, List<Intencion> intenciones, List<IntencionItem> intencionItems, List<ListaUsuario> listaUsuarios, List<ListaUsuarioDetalle> listUsuarioDetalle, List<MensajePredeterminado> listaMensajePredeterminado, List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle, List<MensajePredIntencion> listMensajePredIntencion) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeIntencionItem = mensajeIntencionItem;
        this.usCanalComunicacion = usCanalComunicacion;
        this.canalComunicacion = canalComunicacion;
        this.canalDestinoEstado = canalDestinoEstado;
        this.mensajes = mensajes;
        this.intenciones = intenciones;
        this.intencionItems = intencionItems;
        this.listaUsuarios = listaUsuarios;
        this.listUsuarioDetalle = listUsuarioDetalle;
        this.listaMensajePredeterminado = listaMensajePredeterminado;
        this.listMensajePredeterminadoDetalle = listMensajePredeterminadoDetalle;
        this.listMensajePredIntencion = listMensajePredIntencion;
    }

    public List<MensajeUsuarioC> getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(List<MensajeUsuarioC> mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public List<MensajeIntencionItemC> getMensajeIntencionItem() {
        return mensajeIntencionItem;
    }

    public void setMensajeIntencionItem(List<MensajeIntencionItemC> mensajeIntencionItem) {
        this.mensajeIntencionItem = mensajeIntencionItem;
    }

    public List<UsuarioCanalComunicacion> getUsCanalComunicacion() {
        return usCanalComunicacion;
    }

    public void setUsCanalComunicacion(List<UsuarioCanalComunicacion> usCanalComunicacion) {
        this.usCanalComunicacion = usCanalComunicacion;
    }

    public List<CanalComunicacion> getCanalComunicacion() {
        return canalComunicacion;
    }

    public void setCanalComunicacion(List<CanalComunicacion> canalComunicacion) {
        this.canalComunicacion = canalComunicacion;
    }

    public List<CanalDestinoEstadoC> getCanalDestinoEstado() {
        return canalDestinoEstado;
    }

    public void setCanalDestinoEstado(List<CanalDestinoEstadoC> canalDestinoEstado) {
        this.canalDestinoEstado = canalDestinoEstado;
    }

    public List<MensajeC> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeC> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Intencion> getIntenciones() {
        return intenciones;
    }

    public void setIntenciones(List<Intencion> intenciones) {
        this.intenciones = intenciones;
    }

    public List<IntencionItem> getIntencionItems() {
        return intencionItems;
    }

    public void setIntencionItems(List<IntencionItem> intencionItems) {
        this.intencionItems = intencionItems;
    }

    public List<ListaUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<ListaUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<ListaUsuarioDetalle> getListUsuarioDetalle() {
        return listUsuarioDetalle;
    }

    public void setListUsuarioDetalle(List<ListaUsuarioDetalle> listUsuarioDetalle) {
        this.listUsuarioDetalle = listUsuarioDetalle;
    }

    public List<MensajePredeterminado> getListaMensajePredeterminado() {
        return listaMensajePredeterminado;
    }

    public void setListaMensajePredeterminado(List<MensajePredeterminado> listaMensajePredeterminado) {
        this.listaMensajePredeterminado = listaMensajePredeterminado;
    }

    public List<MensajePredeterminadoDetalle> getListMensajePredeterminadoDetalle() {
        return listMensajePredeterminadoDetalle;
    }

    public void setListMensajePredeterminadoDetalle(List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle) {
        this.listMensajePredeterminadoDetalle = listMensajePredeterminadoDetalle;
    }

    public List<MensajePredIntencion> getListMensajePredIntencion() {
        return listMensajePredIntencion;
    }

    public void setListMensajePredIntencion(List<MensajePredIntencion> listMensajePredIntencion) {
        this.listMensajePredIntencion = listMensajePredIntencion;
    }

    public List<InteraccionTextual> getListInteraccionTextual() {
        return listInteraccionTextual;
    }

    public void setListInteraccionTextual(List<InteraccionTextual> listInteraccionTextual) {
        this.listInteraccionTextual = listInteraccionTextual;
    }
}
