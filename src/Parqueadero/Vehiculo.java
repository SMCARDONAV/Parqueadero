package Parqueadero;

import java.util.Date;
import java.io.*;

public class Vehiculo {

    private String placa, marca, color;
    private Date ingreso;
    private int valorComercial = 0;
    public static int CANTIDAD = 0;
    public static int TAMANO;
    public static Vehiculo[][] VEHICULOS;

    public Vehiculo() {

    }

    public Vehiculo(String p, String m, String c, Date t) {
        this(p, m, c, t, 30000000);
    }

    public Vehiculo(String p, String m, String c, Date t, int v) {
        this.placa = p;
        this.marca = m;
        this.color = c;
        this.valorComercial = v;
        this.ingreso = t;
        Vehiculo.CANTIDAD = CANTIDAD + 1;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(int valorComercial) {
        this.valorComercial = valorComercial;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    @Override
    public String toString() {
        return " {" + "placa=" + placa + ", marca=" + marca + ", color=" + color + ", ingreso=" + ingreso + ", valorComercial=" + valorComercial + '}';
    }

    public static String toStringVehiculos() {

        String ocupados = "";
        for (int i = 0; i < VEHICULOS.length; i++) {
            for (int j = 0; j < VEHICULOS[i].length; j++) {
                if (VEHICULOS[i][j] != null) {
                    ocupados = ocupados + (VEHICULOS[i][j].toString() + "\n");
                }
            }
        }
        return ocupados;
    }

    public static int cantidadVehiculos() {

        return Vehiculo.CANTIDAD;
    }

    public static void llenarMatriz(int posicion, int espacio, Vehiculo a) {
        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
        y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
         */

        posicion = posicion - 1;
        espacio = espacio - 1;

        VEHICULOS[posicion][espacio] = a;

        //Guarda en un vector todos los objetos vehiculo para luego ser organizados por el atributo valor comercial
    }

    // Método que llena el vector con la información que se encuentra en la matriz
    public static Vehiculo[] llenarVector() {

        int contador = 0;
        Vehiculo[] aux = new Vehiculo[CANTIDAD];
        for (int i = 0; i < VEHICULOS.length; i++) {

            for (int j = 0; j < VEHICULOS[i].length; j++) {
                if (VEHICULOS[i][j] != null) {

                    Vehiculo carro = VEHICULOS[i][j];
                    aux[contador] = carro;
                    contador++;
                }
            }

        }
        return aux;
    }

    //Método que ordena por valor comercial los vehiculos creados desde el menor valor hasta el mayor
    public static Vehiculo[] VehiculosOrdenados(Vehiculo[] a) {

        Vehiculo temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j].getValorComercial() > a[j + 1].getValorComercial()) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static boolean consultarDisponibilidad(int posicion, int espacio) {

        boolean dispo = false;
        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
        y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
         */

        posicion = posicion - 1;
        espacio = espacio - 1;

        if (VEHICULOS[posicion][espacio] != null) {
            // El parqueadero esta ocupado
            dispo = true;
        } else {
            //El parqueadero esta libre
            dispo = false;
        }
        return dispo;
    }

    public static String busquedaColor(String color) {

        String busqueda = "";
        boolean control = false;
        for (int i = 0; i < VEHICULOS.length; i++) {
            for (int j = 0; j < VEHICULOS[i].length; j++) {
                if (VEHICULOS[i][j] != null && VEHICULOS[i][j].getColor().equalsIgnoreCase(color)) {

                    busqueda = busqueda + (VEHICULOS[i][j] + "\n");
                    control = true;
                }
            }
        }
        if (control == false) {
            busqueda = ("No se encontraron vehiculos con el color " + color + " ingresado");
        }
        return busqueda;
    }

    public static Date informacionIngreso(int posicion, int espacio) {

        Date horaIngreso = null;

        posicion = posicion - 1;
        espacio = espacio - 1;

        for (int i = 0; i < VEHICULOS.length; i++) {
            for (int j = 0; j < VEHICULOS[i].length; j++) {
                if (i == posicion && j == espacio) {
                    horaIngreso = VEHICULOS[posicion][espacio].getIngreso();
                }
            }
        }
        return horaIngreso;
    }

    public static void liberarVehiculo(int posicion, int espacio) {
        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
        y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
         */
        posicion = posicion - 1;
        espacio = espacio - 1;
        for (int i = 0; i < VEHICULOS.length; i++) {
            for (int j = 0; j < VEHICULOS[i].length; j++) {
                if (i == posicion && j == espacio) {
                    VEHICULOS[posicion][espacio] = null;
                }
            }
        }
    }

    public static void txtVehiculos() {

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("./VehiculosParqueados.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < VEHICULOS.length; i++) {
                for (int j = 0; j < VEHICULOS[i].length; j++) {
                    if (VEHICULOS[i][j] != null) {
                        pw.println(VEHICULOS[i][j].toString() + "\n");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
