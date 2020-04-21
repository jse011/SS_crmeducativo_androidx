package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;




import java.util.List;


public class TipoNotaUi {
    private String id;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private TipoUi tipoUi;
    private EscalaUi escalaUi;
    private String nombre;
    private boolean checket;

    public TipoNotaUi() {
    }

    public TipoNotaUi(String id, TipoUi tipoUi, EscalaUi escalaUi) {
        this.id = id;
        this.tipoUi = tipoUi;
        this.escalaUi = escalaUi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUiList() {
        return valorTipoNotaUiList;
    }

    public void setValorTipoNotaUiList(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
    }

    public TipoUi getTipoUi() {
        return tipoUi;
    }

    public void setTipoUi(TipoUi tipoUi) {
        this.tipoUi = tipoUi;
    }

    public EscalaUi getEscalaUi() {
        return escalaUi;
    }

    public void setEscalaUi(EscalaUi escalaUi) {
        this.escalaUi = escalaUi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isChecket() {
        return checket;
    }

    public void setChecket(boolean checket) {
        this.checket = checket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoNotaUi that = (TipoNotaUi) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
