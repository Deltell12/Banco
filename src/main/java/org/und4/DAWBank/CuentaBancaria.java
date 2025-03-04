package org.und4.DAWBank;

import java.util.ArrayList;

public class CuentaBancaria {
    private String IBAN = creadorIBAN();
    String titular;
    static double saldo = 0;
    static ArrayList<String> movimientos = new ArrayList<>();

    public CuentaBancaria(String IBAN, String dni) {
        this.IBAN = IBAN;
        this.titular = dni;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {

        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<String> movimientos) {
        this.movimientos = movimientos;
    }

    public String getIBAN() {
        return IBAN;
    }

    public static String creadorIBAN (){
        String IBAN = "";
        for (int i = 0; i <= 22; i++){
            IBAN += String.valueOf((int) (Math.random()*10));
        }
        return "ES"+IBAN;
    }

    public static void ingreso(double cantidad, String concepto){
        String movimiento;

        if (cantidad > 3000){
            System.out.println("¡¡AVISO!! Al tratarse de un movimiento superior a los 3000€ procederemos a noticar a hacienda.");
        }

        else if (cantidad < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        saldo = saldo + cantidad;
        movimiento = (String.format("%-40s | %-10s | %-10s", (movimientos.size()+1)+"# "+concepto, "+"+cantidad + "€", "Saldo: "+saldo + "€"));

        System.out.println("--Ingreso realizado con éxito--");

        movimientos.add(movimiento);
    }

    public static void retirada(double cantidad, String concepto){
        String movimiento;
        saldo = saldo - cantidad;

        if(saldo < -50){
            throw new IllegalArgumentException("No se puede realizar una retirada si tu saldo es menor a -50€");
        }

        else if (cantidad < 0){
            throw new IllegalArgumentException("LA cantidad no puede ser negativa.");
        }

        else{
            movimiento = (String.format("%-40s | %-10s | %-10s", movimientos.size()+1+"# "+concepto, "-"+cantidad + "€", "Saldo: "+saldo + "€"));
            System.out.println("--Retirada realizada con éxito--");
        }
        movimientos.add(movimiento);
    }


}
