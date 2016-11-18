/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao;

/**
 *
 * @author dell
 */
public class Motocicleta extends Veiculo {
	
	private ModeloMotocicleta modelo;

	public Motocicleta (Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloMotocicleta modelo){
            super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
            this.modelo = modelo;
	}

	public ModeloMotocicleta getModelo(){
            return this.modelo;
	}

        @Override
	public double getValorDiariaLocacao(){
            switch (this.categoria){
                    case Popular:
                        return 70.00;
                    case Intermediario:
                        return 200.00;
                    case Luxo:
                        return 350.00;
                    default:
                        return 0;
            }
	}

}
