package Parqueadero;

import java.util.Date;

public class Moto extends Vehiculo {

    private String tipoVehiculo;

    public Moto() {
    }

    public Moto(String p, String m, String c, Date t) {
        super(p, m, c, t);
        this.tipoVehiculo="Moto";
    }

    public Moto(String p, String m, String c,Date t, int v) {
        super(p, m, c,t, v);
        this.tipoVehiculo="Moto";
    }

    public Moto(String tipoVehiculo) {
        this.tipoVehiculo = "Moto";
    }

    public String TipoVehiculo() {
        return "Tipo Vehiculo= " + tipoVehiculo;
    }

    @Override
    public String toString() {
        String info=this.tipoVehiculo+super.toString();
        return info;
    }
}
