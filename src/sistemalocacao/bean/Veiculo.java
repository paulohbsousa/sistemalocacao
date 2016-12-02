/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sistemalocacao.dao.LocacaoDAO;
import sistemalocacao.dao.VeiculoDAO;

/**
 *
 * @author dell
 */
public abstract class Veiculo implements VeiculoI {
	
	private Marca marca;
        private Estado estado;
        private Locacao locacao;
        Categoria categoria;
        private double valorDeCompra;
        private String placa;
        private int ano;


	public Veiculo(Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano) throws IllegalArgumentException{
		try { 
                    Pattern placaPattern = Pattern.compile("[a-zA-Z]{3,3}-\\d{4,4}");
                                           
                    Matcher placaMatcher = placaPattern.matcher(placa);

                    if (!placaMatcher.find())
                            throw new IllegalArgumentException("Padrão da placa, inválido.");

                    if (ano < 0)
                            throw new IllegalArgumentException("Ano não pode ser negativo.");

                    if (valorDeCompra < 0)
                            throw new IllegalArgumentException("Valor de Compra não pode ser negativo.");

                    this.marca = marca;
                    this.estado = estado;

                    if (estado == Estado.Locado)
                            this.locacao = locacao;
                    else
                            this.locacao = null;

                    this.categoria = categoria;
                    this.valorDeCompra = valorDeCompra;
                    this.placa = placa;
                    this.ano = ano;
		} catch (IllegalArgumentException ex){
                    throw new IllegalArgumentException(ex);
		}		
	}
        
        @Override
	public void locar(int dias, Calendar data, Cliente cliente){
            if (estado == Estado.Disponivel || this.estado == Estado.Novo){
                    estado = Estado.Locado;
                    locacao = new Locacao(dias, getValorDiariaLocacao(), data, cliente);
            }
	}

        @Override
	public void vender(){
            try {
                if (estado == Estado.Disponivel || estado == Estado.Novo){
                        VeiculoDAO dao = new VeiculoDAO();
                        estado = Estado.Vendido;
                        dao.atualiza(this);
                }
            } catch (Exception ex){
                System.out.println("Erro ao vender veiculo. Excecao = "+ex.getMessage());
            }
	}

        @Override
	public void devolver(){
            if (estado == Estado.Locado){
                try {
                    VeiculoDAO dao = new VeiculoDAO();
                    LocacaoDAO ldao = new LocacaoDAO();
                    Locacao locacaoAntiga = locacao;
                    locacao = null;
                    estado = Estado.Disponivel;
                    dao.atualiza(this);
                    ldao.deleta(locacaoAntiga);
                } catch (Exception ex){
                    System.out.println("Erro ao devolver veiculo. Excecao = "+ex.getMessage());
                }
            }
	}

        @Override
	public Estado getEstado(){
            return estado;
	}

        @Override
	public Marca getMarca(){
            return marca;
	}
        
        @Override
	public Categoria getCategoria(){
            return categoria;
	}

        @Override
	public Locacao getLocacao(){
            return locacao;
	}

        @Override
	public String getPlaca(){
            return placa;
	}

        @Override
	public int getAno(){
            return ano;
	}

        @Override
	public double getValorParaVenda(){
            int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
            double valorVenal = valorDeCompra - ((anoAtual-ano)*0.15*valorDeCompra);
            if (valorVenal < valorDeCompra*0.10 || valorVenal < 0){
                    valorVenal = valorDeCompra * 0.1;
            }
            return valorVenal;
	}

        @Override
	public abstract double getValorDiariaLocacao();

}
