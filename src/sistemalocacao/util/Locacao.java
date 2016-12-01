/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.util;

import java.util.Calendar;

/**
 *
 * @author dell
 */
public class Locacao {
	
    private int dias;
    private double valor;
    private Calendar data;
    private Cliente cliente;

    public Locacao (int Dias, double Valor, Calendar Data, Cliente Cliente){
            dias = Dias;
            valor = Valor;
            data = Data;
            cliente = Cliente;
    }

    public double getValor(){
            return valor;
    }

    public Calendar getData(){
            return data;
    }

    public Cliente getCliente(){
            return cliente;
    }
    
    public int getDias(){
        return dias;
    }
}
