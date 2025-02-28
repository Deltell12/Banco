package org.und4.DAWBank;

import java.util.ArrayList;

public class CuentaBancaria {
    private String IBAN = creadorIBAN();
    String titular;
    double saldo = 0;
    ArrayList<String> movimientos;

    public CuentaBancaria(String IBAN, String titular) {
        this.IBAN = IBAN;
        this.titular = titular;
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


}
