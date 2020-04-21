package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

public class FiltroTableUi {
    private String persona;
    private OrderByASC orderByASC = OrderByASC.NOMBRE;

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public OrderByASC getOrderByASC() {
        return orderByASC;
    }

    public void setOrderByASC(OrderByASC orderByASC) {
        this.orderByASC = orderByASC;
    }

    public enum OrderByASC{ NOMBRE("Nombres"), APELLIDO("Apellidos");
        String nombre;

        OrderByASC(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    @Override
    public String toString() {
        return "FiltroTableUi{" +
                "persona='" + persona + '\'' +
                ", orderByASC=" + orderByASC +
                '}';
    }
}
