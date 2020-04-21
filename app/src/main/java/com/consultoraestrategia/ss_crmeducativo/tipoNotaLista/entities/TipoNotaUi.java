package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities;




import java.io.Serializable;
import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */


@SuppressWarnings("serial")
public class TipoNotaUi implements Serializable {

    private static final String GREY = "#90A4AE",AZUL ="#1976d2",ANARANJADO="#FF6D00",ROJO="#D32F2F",VERDE="#388e3c";

    private String id;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private TipoUi tipoUi;
    private EscalaUi escalaUi;
    private String nombre;
    private boolean checket;
    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoNotaUi that = (TipoNotaUi) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public String toString() {
        return "TipoNotaUi{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", checket=" + checket +
                ", enabled=" + enabled +
                '}';
    }

}
