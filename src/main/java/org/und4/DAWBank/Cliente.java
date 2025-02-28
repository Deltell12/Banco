package org.und4.DAWBank;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private String DNI;
    String nombreCompleto;
    String correoElectronico;

    public Cliente(String DNI, String nombreCompleto, String correoElectronico) {
        if (verificarDNI(DNI)) {
            this.DNI  = DNI;
        }

        this.nombreCompleto = nombreCompleto;

        if (verificarCorreo(correoElectronico)) {
            this.correoElectronico = correoElectronico;
        }

    }

    public Cliente(Cliente c) {
        this.DNI = c.DNI;
        this.nombreCompleto = c.nombreCompleto;
        this.correoElectronico = c.correoElectronico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDNI() {
        return DNI;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    public static boolean verificarCorreo(String correo) throws IllegalArgumentException{
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        boolean correoValido = false;

        if (correo.matches(regex)) {
            correoValido = true;
        }
        else {
            throw new IllegalArgumentException("El Correo Electrónico debe de ser válido");
        }

        return correoValido;
    }

    public static boolean verificarDNI(String dni) {
        String regex =  "^\\d{8}[A-Z]$";
        boolean dniValido = false;

        if (dni.matches(regex)) {
            dniValido = true;
        }
        else {
            throw new IllegalArgumentException("El DNI debe de ser válido");
        }

        return dniValido;
    }




}
