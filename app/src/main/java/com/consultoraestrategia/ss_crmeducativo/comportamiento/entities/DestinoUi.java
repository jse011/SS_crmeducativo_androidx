package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;


import java.util.List;

public class DestinoUi {

    public enum Tipo{PADRES, TUTOR, ADODERADO}
    List<Tipo>tipos;
    List<Integer>destinosIds;
    int georeferenciaId;
    List<UsuarioUi>usuarioUiList;

    public List<Integer> getDestinosIds() {
        return destinosIds;
    }

    public void setDestinosIds(List<Integer> destinosIds) {
        this.destinosIds = destinosIds;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }


    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public List<UsuarioUi> getUsuarioUiList() {
        return usuarioUiList;
    }

    public void setUsuarioUiList(List<UsuarioUi> usuarioUiList) {
        this.usuarioUiList = usuarioUiList;
    }
}
