package Parqueadero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        String menu, menu2;
        //Variable que lleva el control de piso y espacio disponible
        boolean disponibilidad;
        int tipoVehiculo;

        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor ingrese la cantidad de pisos del edificio: ");

        //La variable pisos ingresada por el usuario define la dimensión de la matriz
        //Ejemplo pisos=3,celdas 4 las dimensiones son 3 filas y 4 columnas
        int pisos = scan.nextInt();

        System.out.println("Por favor ingrese la cantidad de espacios del edificio: ");
        int celdas = scan.nextInt();
        // Creación de las matrices de objetos con la dimensión recibida
        Vehiculo.VEHICULOS = new Vehiculo[pisos][celdas];
        Sensor.SENSORES = new Sensor[pisos][celdas];

        System.out.println("Por favor ingrese el valor a cobrar por hora para motos: ");
        double valorMotos = scan.nextDouble();

        System.out.println("Por favor ingrese el valor a cobrar por hora para carros: ");
        double valorCarros = scan.nextDouble();

        //Se crea el objeto disponible de tipo sensor para llenar la matriz de SENSORES de espacios libres
        Sensor disponible = new Sensor();
        disponible.setEstado(0);
        Sensor.llenarSensorDisponible(disponible);

        Vehiculo.TAMANO = pisos * celdas;

        menu = """
               Por favor ingrese una opci\u00f3n: 
               0.Salir 
               1.Sensores desocupados 
               2.Solicitar parqueo con valor comercial por defecto
               3.Solicitar parqueo con valor comercial
               4.Informaci\u00f3n vehiculos parqueados
               5.Historial cantidad de vehiculos
               6.Estado de un solo sensor
               7.Estado de todos los sensores
               8.Busqueda por color de vehiculo
               9.Vehiculos ordenados por valor comercial
               10.Liberar sensor
               11.Exportar información vehiculos""";
        System.out.println(menu);

        menu2 = """
               Por favor ingrese el tipo de vehiculo: 
               1.Moto
               2.Carro""";

        //Se recibe la opción ingresada por usuario
        int opcion = scan.nextInt();

        while (opcion != 0) {
            switch (opcion) {
                case 1:

                    //Imprime los sensores desocupados
                    System.out.println("Opcion 1");
                    System.out.println(Sensor.sensorLibre());
                    break;

                case 2,3:
                    //opcion 2 almacena el vehiculo con un valor comercial por defecto
                    //opcion 3 almacena el vehiculo con un valor comercial ingresado por el usuario

                    System.out.println("Opcion: " + opcion);

                    //La fila (i) en donde se va a almacenar el objeto vehiculo y sensor
                    System.out.println("Por favor indique piso: ");
                    int posicion = scan.nextInt();

                    //La columna (j) en donde se va a almacenar el objeto vehiculo y sensor
                    System.out.println("Por favor indique espacio: ");
                    int espacio = scan.nextInt();

                    //La fila (posicion) debe ser mayor a 1 porque no hay pisos 0 ni negativos
                    //La fila (posición) debe de ser menor o igual al tamaño de la matriz
                    //La columna(espacio) debe de ser mayor o igual que 1 y menor o igual que la dimensión definida al inicio del programa
                    // Se verifica que los pisos y espacios deben estar dentro de las dimensiones de la matriz (i)(j)
                    if (posicion >= 1 && posicion <= Vehiculo.TAMANO && espacio >= 1 && espacio <= celdas && posicion <= pisos) {

                        disponibilidad = Vehiculo.consultarDisponibilidad(posicion, espacio);
                        if (disponibilidad == true) {
                            System.out.println("El parqueadero del piso " + posicion + " celda " + espacio + " está ocupado. Por favor ingrese otra");
                            break;
                        } else {
                            System.out.println("Ingrese placa: ");
                            String placa = scan.next();
                            System.out.println("Ingrese marca: ");
                            String marca = scan.next();
                            System.out.println("Ingrese color: ");
                            String color = scan.next();

                            if (opcion == 2) {

                                System.out.println(menu2);
                                tipoVehiculo = scan.nextInt();
                                if (tipoVehiculo == 1) {

                                    Date date = new Date();
                                    Moto moto = new Moto(placa, marca, color, date);
                                    //Almacena el objeto moto creado en la matriz de objetos
                                    Vehiculo.llenarMatriz(posicion, espacio, moto);

                                    System.out.println(moto.toString());
                                    Sensor estados = new Sensor();
                                    estados.setEstado(1);
                                    Sensor.llenarSensor(posicion, espacio, estados);
                                    break;
                                } else if (tipoVehiculo == 2) {

                                    Date date = new Date();
                                    Carro carro = new Carro(placa, marca, color, date);
                                    //Almacena el objeto moto creado en la matriz de objetos
                                    Vehiculo.llenarMatriz(posicion, espacio, carro);

                                    System.out.println(carro.toString());
                                    Sensor estados = new Sensor();
                                    estados.setEstado(1);
                                    Sensor.llenarSensor(posicion, espacio, estados);
                                    break;
                                }

                                //Vehiculo carro = new Vehiculo(placa, marca, color);
                            } else if (opcion == 3) {

                                System.out.println("Opcion 3");
                                System.out.println("Ingrese el valor comercial: ");
                                int valor = scan.nextInt();

                                System.out.println(menu2);
                                tipoVehiculo = scan.nextInt();

                                if (tipoVehiculo == 1) {

                                    Date date = new Date();
                                    Moto moto = new Moto(placa, marca, color, date, valor);
                                    Vehiculo.llenarMatriz(posicion, espacio, moto);
                                    System.out.println(moto.toString());
                                    Sensor estados = new Sensor();
                                    estados.setEstado(1);
                                    Sensor.llenarSensor(posicion, espacio, estados);
                                    break;
                                } else if (tipoVehiculo == 2) {

                                    Date date = new Date();
                                    Carro carro = new Carro(placa, marca, color, date, valor);
                                    Vehiculo.llenarMatriz(posicion, espacio, carro);
                                    System.out.println(carro.toString());
                                    Sensor estados = new Sensor();
                                    estados.setEstado(1);
                                    Sensor.llenarSensor(posicion, espacio, estados);
                                    break;
                                }

                            }

                        }
                    } else {
                        System.out.println("Piso o espacio incorrecto");
                        break;
                    }

                case 4:
                    //Imprime los vehiculos parqueados

                    System.out.println("Opcion 4");
                    System.out.println(Vehiculo.toStringVehiculos());
                    break;
                case 5:
                    //Muestra el historial de los vehiculos creados

                    System.out.println("Opcion 5");
                    System.out.println("La cantidad de vehiculos creados hasta el momento es: " + Vehiculo.cantidadVehiculos());
                    break;
                case 6:
                    //Realiza la busqueda del estado de un sensor según un piso y un espacio

                    System.out.println("Por favor indique piso: ");
                    int posicion2 = scan.nextInt();
                    System.out.println("Por favor indique espacio: ");
                    int espacio2 = scan.nextInt();
                    if (posicion2 >= 1 && posicion2 <= Sensor.SENSORES.length && espacio2 >= 1 && espacio2 <= pisos) {

                        /* Se resta un 1 a los atributos posición y espacio debido a que la matriz comienzan a partir de 0 
                            y el usuario para la primera posiciòn ingresa 1 y asi se da con las demás posiciones
                         */
                        posicion2 = posicion2 - 1;
                        espacio2 = espacio2 - 1;
                        System.out.println("El estado del espacio consultado es: " + Sensor.SENSORES[posicion2][espacio2]);
                    }
                    break;
                case 7:
                    //Realiza la busqueda del estado de todos los sensores

                    System.out.println("Opcion 7");
                    System.out.println(Sensor.sensoresEstado());
                    break;
                case 8:
                    //Realiza busqueda de un vehiculo por un color ingresado por el usuario
                    System.out.println("Opcion 8");
                    String resultadoColor;
                    System.out.println("Por favor ingrese el color que desea buscar");
                    String busquedaColor = scan.next();
                    resultadoColor = Vehiculo.busquedaColor(busquedaColor);
                    System.out.println(resultadoColor);
                    break;
                case 9:

                    // Se recibe el arreglo vehiculos para después ordenarlo por el valor comercial
                    Vehiculo[] vehiculosVector = Vehiculo.llenarVector();

                    //Se ordena el arreglo de vehiculos por valor comercial utilizando el metodo compareTo()
                    Vehiculo[] vehiculosOrdenado = Vehiculo.VehiculosOrdenados(vehiculosVector);

                    System.out.println(Arrays.toString(vehiculosOrdenado));
                    break;
                case 10:

                    double valorCobrar = 0;
                    //Liberar sensor
                    System.out.println("Por favor indique piso: ");
                    int posicion3 = scan.nextInt();
                    System.out.println("Por favor indique espacio: ");
                    int espacio3 = scan.nextInt();

                    if (posicion3 >= 1 && posicion3 <= Vehiculo.TAMANO && espacio3 >= 1 && espacio3 <= celdas && posicion3 <= pisos) {
                        Date horaIngreso = new Date();

                        disponibilidad = Vehiculo.consultarDisponibilidad(posicion3, espacio3);
                        if (disponibilidad == true) {
                            horaIngreso = Vehiculo.informacionIngreso(posicion3, espacio3);

                            String time = new SimpleDateFormat("HH:mm:ss").format(horaIngreso);
                            System.out.println("Hora ingreso: " + time);

                            Date horaActual = new Date();
                            String time2 = new SimpleDateFormat("HH:mm:ss").format(horaActual);
                            System.out.println("Hora actual: " + time2);

                            long x = horaActual.getTime() - horaIngreso.getTime();

                            //pasar de milisegundos a segundos
                            //x=(x/1000)%60;
                            x = (x / (1000 * 60)) % 60;
                            System.out.println("Diferencia minutos: " + x);

                            if (Vehiculo.VEHICULOS[posicion3 - 1][espacio3 - 1] instanceof Carro) {
                                valorCobrar = (valorCarros / 60) * x;

                            } else {
                                valorCobrar = (valorMotos / 60) * x;
                            }
                            System.out.println(Vehiculo.VEHICULOS[posicion3 - 1][espacio3 - 1]);
                            System.out.println("El valor a cobrar es: " + valorCobrar + "$");
                            Sensor.liberarSensor(posicion3, espacio3);
                            Vehiculo.liberarVehiculo(posicion3, espacio3);
                        } else {
                            System.out.println("No hay vehiculo en la posición y espacio indicado");
                        }
                    } else {
                        System.out.println("Posición y espacio inválida");
                    }
                    break;

                case 11:
                    Vehiculo.txtVehiculos();
                    System.out.println("Proceso Finalizado.Verifique el archivo TXT en la carpeta del proyecto ");
                    break;

                default:
                    System.out.println("Comando incorrecto");
                    break;
            }
            System.out.println(menu);
            opcion = scan.nextInt();
        }
        System.out.println("Saliendo.....");

    }

}
