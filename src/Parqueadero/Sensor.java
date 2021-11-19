package Parqueadero;

public class Sensor {

    public static Sensor SENSORES[][] = null;
    private int estado;

    public Sensor() {

    }

    public Sensor(int e) {
        this.estado = e;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {

        String estado = "";

        if (this.estado == 1) {
            estado = "Ocupado";
        } else {
            estado = "Libre";
        }

        return estado;
    }

    public static String sensorLibre() {

        String libres = "";
        System.out.println(SENSORES.length);
        for (int i = 0; i < SENSORES.length; i++) {
            for (int j = 0; j < SENSORES[i].length; j++) {
                if (SENSORES[i][j].estado == 0) {
                    libres = libres + ("Piso: " + (i + 1) + " espacio: " + (j + 1) + " libre\n");
                }
            }
        }
        return libres;
    }

    public static String sensoresEstado() {

        String estado = "";
        for (int i = 0; i < SENSORES.length; i++) {
            for (int j = 0; j < SENSORES[i].length; j++) {
                estado = estado + ("Piso: " + (i + 1) + " espacio: " + (j + 1) + " estado " + (SENSORES[i][j]) + "\n");
            }
        }
        return estado;
    }

    public static void llenarSensor(int posicion, int espacio, Sensor a) {
        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
        y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
         */
        posicion = posicion - 1;
        espacio = espacio - 1;
        
        SENSORES[posicion][espacio] = a;
    }
    
    public static void liberarSensor(int posicion, int espacio) {
        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
        y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
         */
        posicion = posicion - 1;
        espacio = espacio - 1;
        for (int i = 0; i < SENSORES.length; i++) {
            for (int j = 0; j < SENSORES[i].length; j++) {
                if (i==posicion && j==espacio){
                    SENSORES[posicion][espacio].setEstado(0);
                }
            }
        }
    }

    public static void llenarSensorDisponible(Sensor a) {

        for (int i = 0; i < SENSORES.length; i++) {
            for (int j = 0; j < SENSORES[i].length; j++) {
                SENSORES[i][j] = a;
            }
        }
    }

}
