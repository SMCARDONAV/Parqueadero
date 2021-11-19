package Parqueadero;

import java.util.Date;

public class Carro extends Vehiculo{
    
    private String tipoVehiculo;

    public Carro() {
    }

    public Carro(String p, String m, String c, Date t) {
        super(p, m, c, t);
        this.tipoVehiculo="Carro";
    }

    public Carro(String p, String m, String c, Date t, int v) {
        super(p, m, c, t, v);
        this.tipoVehiculo="Carro";
    }
   
    public Carro(String tipoVehiculo) {
        this.tipoVehiculo = "Carro";
    }

    public String TipoVehiculo() {
        return "Tipo Vehiculo=" + tipoVehiculo;
    }
    
    @Override
    public String toString() {
        String info=this.tipoVehiculo+super.toString();
        return info;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    
    
}
