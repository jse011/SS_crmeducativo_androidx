package com.consultoraestrategia.ss_crmeducativo.comentarios.entidades;

public class TipoUi {

        public enum Tipo {EMISOR, RECEPTOR}

        private Tipo tipo;

        public TipoUi() {
            tipo = Tipo.EMISOR;
        }

        public Tipo getTipo() {
            return tipo;
        }

        public void setTipo(Tipo tipo) {
            this.tipo = tipo;
        }

    @Override
    public String toString() {
        return "TipoUi{" +
                "tipo=" + tipo +
                '}';
    }
}
