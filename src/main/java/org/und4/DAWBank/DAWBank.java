package org.und4.DAWBank;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

        int opcion = 0;
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
                    System.out.println("2->\tCrear cuenta");
                    System.out.println("3->\tConsultar cuenta");
                    System.out.println("4->\tConsultar movimientos");
                    System.out.println("5->\tOperaciones");
                    System.out.println("6->\tSalir");
                    System.out.println();

                    System.out.print("Introduce una opción: ");
                    opcion = sc.nextInt();

                    switch (opcion) {

                        case 1:
                            nuevoCliente();
                            break;

                        case 2:
                            nuevaCuenta();
                            break;

                        case 3:
                            consultarCuenta();
                            break;

                        case 4:
                            consultarMovimientos();
                            break;

                        case 6:
                            break;

                    }


                }
                while (opcion != 6);
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
            if (clientes.containsKey(dni)) {
                throw new IllegalArgumentException("Error, ya existe un cliente con ese DNI.");
            }
            System.out.print("Introduce tu nombre completo: ");
            String nombre = sc.next();
            System.out.print("Introduce tu correo electronico: ");
            String correo = sc.next();
            for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
                if (entry.getValue().getCorreoElectronico().equals(correo)) {
                    throw new IllegalArgumentException("Error, ya existe un cliente con ese correo.");
                }
            }
            Cliente cliente = new Cliente(dni, nombre, correo);
            clientes.put(dni, new Cliente(cliente));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void nuevaCuenta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu DNI: ");
        String dni = sc.next();

        if (clientes.containsKey(dni)) {
                String IBAN = CuentaBancaria.creadorIBAN();
                System.out.println("Su IBAN es: " +IBAN);
                cuentas.put(IBAN, new CuentaBancaria(IBAN, dni));

        } else {
                System.out.println("Error, no existe ningún cliente con ese DNI.");
                System.out.println("--Logueate como Cliente--");
                nuevoCliente();
            }
        }

    public static void consultarCuenta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu IBAN: ");
        String iban = sc.nextLine();

        for (Map.Entry<String, CuentaBancaria> entradaCuenta : cuentas.entrySet()) {

                if (entradaCuenta.getValue().getIBAN().equals(iban)) {
                    System.out.println("IBAN: "+entradaCuenta.getValue().getIBAN() + " | Nombre Titular: " + clientes.get(entradaCuenta.getValue().getTitular()).getNombreCompleto() + " | Saldo de la cuenta: " + entradaCuenta.getValue().getSaldo() + " | Fecha de la consulta: " + hoy);
                    return;
                }
            }
        System.out.println("Error, no existe ninguna cuenta con ese IBAN.");
        System.out.println("--Create una Cuenta--");
        nuevaCuenta();
        }

    public static void consultarMovimientos() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu IBAN: ");
        String iban = sc.nextLine();

        try {
            for (Map.Entry<String, CuentaBancaria> entradaCuenta : cuentas.entrySet()) {
                if (entradaCuenta.getValue().getIBAN().equals(iban)) {
                    System.out.println(entradaCuenta.getValue().movimientos);
                    if (entradaCuenta.getValue().getMovimientos().isEmpty()) {
                        throw new IllegalArgumentException("Error, no hay movimientos.");
                    }
                }
            }
            System.out.println("Error, no existe ninguna cuenta con ese IBAN.");
            System.out.println("--Create una Cuenta--");
            nuevaCuenta();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


