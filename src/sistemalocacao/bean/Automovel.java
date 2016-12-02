/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.bean;

/**
 *
 * @author dell
 */
public class Automovel extends Veiculo {
	
	private ModeloAutomovel modelo;

	public Automovel (Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloAutomovel modelo){
            super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
            this.modelo = modelo;
	}

	public ModeloAutomovel getModelo(){
            return this.modelo;
	}

        @Override
	public double getValorDiariaLocacao(){
            switch (this.categoria){
                    case Popular:
                        return 100.00;
                    case Intermediario:
                        return 300.00;
                    case Luxo:
                        return 450.00;
                    default:
                        return 0;
            }
	}

}
