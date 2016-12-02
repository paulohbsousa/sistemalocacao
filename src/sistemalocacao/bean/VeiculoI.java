/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.bean;

import java.util.Calendar;

/**
 *
 * @author paulo
 */
public interface VeiculoI {
	
    /**
     *
     * @param dias
     * @param data
     * @param cliente
     */
    public void locar(int dias, Calendar data, Cliente cliente);

	public void vender();

	public void devolver();

	public Estado getEstado();

	public Marca getMarca();

	public Categoria getCategoria();

	public Locacao getLocacao();

	public String getPlaca();

	public int getAno();

	public double getValorParaVenda();

	public double getValorDiariaLocacao();

}
