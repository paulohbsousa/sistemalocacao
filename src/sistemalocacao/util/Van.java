/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.util;

/**
 *
 * @author dell
 */
public class Van extends Veiculo {
	
	private ModeloVan modelo;

	public Van (Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloVan modelo){
            super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
            this.modelo = modelo;
	}

	public ModeloVan getModelo(){
            return this.modelo;
	}

        @Override
	public double getValorDiariaLocacao(){
            switch (this.categoria){
                    case Popular:
                        return 200.00;
                    case Intermediario:
                        return 400.00;
                    case Luxo:
                        return 600.00;
                    default:
                        return 0;
            }
	}

}