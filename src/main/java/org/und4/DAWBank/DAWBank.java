package org.und4.DAWBank;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.und4.DAWBank.Cliente.*;

public class DAWBank {
    String dni;
    String nombre;
    String correo;
    static String hoy = String.valueOf(LocalDate.now());


    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<String, CuentaBancaria> cuentas = new HashMap<>();

    public static void main(String[] args) {


        clientes.put("20525446Q", new Cliente("20525446Q", "Adrian Deltell López", "adrideltelllopez12@gmail.com"));
        clientes.put("78451296T", new Cliente("78451296T", "Paco Hidalgo Juan", "pacohidalgo@gmail.com"));
        clientes.put("15484275P", new Cliente("15484275P", "Ramon Gonzalez Gutierrez", "ramongonzalez@gmail.com"));


        Scanner sc = new Scanner(System.in);

        String opcion = "";
        int intentos = 3;
        String usuario = "Adri";
        String contrasenia = "123";

        do {
            System.out.print("Introduce el nombre del usuario: ");
            String user = sc.nextLine();
            System.out.print("Introduce la contraseña: ");
            String password = sc.nextLine();

            if (user.equals(usuario) && password.equals(contrasenia)) {
                System.out.println();
                do {

                    System.out.println("--MENU DE INICIO--");
                    System.out.println("1->\tNuevo cliente");
                    System.out.println("2->\tDar cliente de baja");
                    System.out.println("3->\tCrear cuenta");
                    System.out.println("4->\tEliminar cuenta");
                    System.out.println("5->\tConsultar cuenta");
                    System.out.println("6->\tConsultar movimientos");
                    System.out.println("7->\tOperaciones");
                    System.out.println("8->\tSalir");
                    System.out.println();

                    System.out.print("Introduce una opción: ");
                    opcion = sc.nextLine();

                    switch (opcion) {

                        case "1":
                            nuevoCliente();
                            System.out.println();
                            break;

                        case "2":
                            eliminarCliente();
                            System.out.println();
                            break;

                        case "3":
                            nuevaCuenta();
                            System.out.println();
                            break;

                        case "4":
                            eliminarCuenta();
                            System.out.println();
                            break;

                        case "5":
                            consultarCuenta();
                            System.out.println();
                            break;

                        case "6":
                            consultarMovimientos();
                            System.out.println();
                            break;

                        case "7":
                            operaciones();
                            System.out.println();
                            break;

                        case "8":
                            System.out.println("Saliendo...");
                            break;

                        default:
                            System.out.println("La opción no es valida");
                            System.out.println();
                            break;

                    }


                }
                while (!opcion.equals("8"));
                break;
            } else {
                System.out.println("Error, uno de los datos no es correcto.");
                intentos--;
                if (intentos >= 1) {
                    System.out.println("Le quedan " + intentos + " intentos.");
                } else {
                    System.out.println("Te has quedados sin intentos.");
                }
            }
            System.out.println();
        }
        while (intentos != 0);
    }

    public static void nuevoCliente() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Introduce tu DNI: ");
            String dni = sc.next();
            sc.nextLine();
            if (clientes.containsKey(dni)) {
                throw new IllegalArgumentException("Error, ya existe un cliente con ese DNI.");
            }
            System.out.print("Introduce tu nombre completo: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce tu correo electronico: ");
            String correo = sc.nextLine();

            for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
                if (entry.getValue().getCorreoElectronico().equals(correo)) {
                    throw new IllegalArgumentException("Error, ya existe un cliente con ese correo.");
                }
            }
            Cliente cliente = new Cliente(dni, nombre, correo);
            clientes.put(dni, (cliente));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void eliminarCliente() {
        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el DNI asociado al cliente que desea dar de baja: ");
            String dni = sc.next();
            if (!Cliente.verificarDNI(dni)) {
                throw new IllegalArgumentException("Error,el DNI debe de ser válido");
            }
            if (clientes.containsKey(dni)) {
                System.out.print("¿Está seguro que desea dar de baja a este cliente? (True/False): ");
                boolean preguntaEliminar = sc.nextBoolean();

                if (preguntaEliminar) {
                    System.out.println("Dando de baja al cliente seleccionado...");
                    clientes.remove(dni);
                    System.out.println("Cliente dado de baja correctamente.");
                }
                else {
                    System.out.println("Operación cancelada");
                }
            }
            else {
                System.out.println("Error, no hay ningun cliente con ese DNI.");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void nuevaCuenta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu DNI: ");
        String dni = sc.next();

        if (clientes.containsKey(dni)) {
            String IBAN = CuentaBancaria.creadorIBAN();
            CuentaBancaria C1 = new CuentaBancaria(IBAN, dni);
            System.out.println("Su IBAN es: " +IBAN);
            cuentas.put(IBAN, C1);

        } else {
                System.out.println("Error, no existe ningún cliente con ese DNI.");
                System.out.println("--Logueate como Cliente--");
                nuevoCliente();
            }
        }

    public static void eliminarCuenta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el IBAN asociado a la cuenta que desea eliminar: ");
        String iban = sc.next();
        try {

            if (cuentas.containsKey(iban)) {
                System.out.print("¿Está seguro que desea eliminar esta cuenta? (True/False): ");
                boolean preguntaEliminar = sc.nextBoolean();

                if (preguntaEliminar) {
                    System.out.println("Eliminando cuenta seleccionada...");
                    cuentas.remove(iban);
                    System.out.println("Cuenta eliminada correctamente.");
                } else {
                    System.out.println("Operación cancelada");
                }
            }
            else {
                System.out.println("Error, no hay ninguna cuenta con ese IBAN.");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void consultarCuenta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu IBAN: ");
        String iban = sc.nextLine();

        try {


            for (Map.Entry<String, CuentaBancaria> entradaCuenta : cuentas.entrySet()) {

                if (entradaCuenta.getValue().getIBAN().equals(iban)) {
                    System.out.println("IBAN: " + entradaCuenta.getValue().getIBAN() + " | Nombre Titular: " + clientes.get(entradaCuenta.getValue().getTitular()).getNombreCompleto() + " | Saldo de la cuenta: " + entradaCuenta.getValue().getSaldo() + "€ | Fecha de la consulta: " + hoy);
                    if (clientes.get(entradaCuenta.getValue().getTitular()).getNombreCompleto() == null) {
                        System.out.println("No existe ninguna cuenta asociada a este IBAN");
                        System.out.println("--Logueate como Cliente--");
                        nuevoCliente();
                    }
                    return;
                }
            }
            System.out.println("Error, no existe ninguna cuenta con ese IBAN.");
            System.out.println("--Create una Cuenta--");
            nuevaCuenta();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void consultarMovimientos() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu IBAN: ");
        String iban = sc.nextLine();

        try {
            for (Map.Entry<String, CuentaBancaria> entradaCuenta : cuentas.entrySet()) {
                if (entradaCuenta.getValue().getIBAN().equals(iban)) {
                    Collections.reverse(entradaCuenta.getValue().getMovimientos());
                    for (String parte : CuentaBancaria.movimientos) {
                        System.out.println(parte);
                    }
                    if (entradaCuenta.getValue().getMovimientos().isEmpty()) {
                        throw new IllegalArgumentException("Error, no hay movimientos.");
                    }
                }
                else{
                    System.out.println("Error, no existe ninguna cuenta con ese IBAN.");
                    System.out.println("--Create una Cuenta--");
                    nuevaCuenta();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void operaciones() {
        Scanner sc = new Scanner(System.in);
        int contador = 0;

        System.out.print("Introduce tu IBAN: ");
        String iban = sc.nextLine();
        if (!cuentas.containsKey(iban)) {
            System.out.println("No existe ninguna cuenta con este IBAN.");
            System.out.println("--Create una Cuenta--");
            nuevaCuenta();
        }


        try {

        for (Map.Entry<String, CuentaBancaria> entradaCuenta : cuentas.entrySet()) {
            if (entradaCuenta.getValue().getIBAN().equals(iban)) {
                System.out.println("1. INGRESO");
                System.out.println("2. RETIRADA");
                System.out.print("Introduce una opción: ");
                String opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.print("Introduce el concepto del ingreso: ");
                        String conceptoIngresos = sc.nextLine();
                        System.out.print("Introduce la cantidad del ingreso: ");
                        double cantidadIngreso = sc.nextDouble();

                        CuentaBancaria.ingreso(cantidadIngreso, conceptoIngresos);

                        break;

                    case "2":
                        System.out.print("Introduce el concepto del ingreso: ");
                        String conceptoRetirada = sc.nextLine();
                        System.out.print("Introduce la cantidad del ingreso: ");
                        double cantidadRetirada = sc.nextDouble();

                        CuentaBancaria.retirada(cantidadRetirada, conceptoRetirada);

                        break;

                    default:
                        System.out.println("La opción seleccionada no es válida.");
                        break;
                }
            }

            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}