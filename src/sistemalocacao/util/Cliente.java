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
public class Cliente {
    
    private String nome;
    
    private String sobrenome;
    
    private String rg;
    
    private long cpf;
    
    private String endereco;
    
    public Cliente(String Nome, String Sobrenome, String RG, long CPF, String Endereco) throws IllegalArgumentException{
        try {
            if ("".equals(Nome))
                throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
            
            nome = Nome;
            if ("".equals(Sobrenome))
                throw new IllegalArgumentException("Sobrenome do cliente não pode ser vazio.");
            
            sobrenome = Sobrenome;
            
            if ("".equals(RG))
                throw new IllegalArgumentException("RG do cliente não pode ser vazio.");
            
            rg = RG;
            
            if (CPF == 0)
                throw new IllegalArgumentException("CPF do cliente não pode ser vazio.");
            
            cpf = CPF;
            
            if ("".equals(Endereco))
                throw new IllegalArgumentException("Endereço do cliente não pode ser vazio.");
            
            endereco = Endereco;
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException(ex);
        }
    }  
  
    public long getCPF(){
        return cpf;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getSobrenome(){
        return sobrenome;
    }
    
    public String getRG(){
        return rg;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setCPF(long valor){
        cpf = valor;
    }
    
    public void setNome(String valor){
        nome = valor;
    }
    
    public void setSobrenome(String valor){
        sobrenome = valor;
    }
    
    public void setRG(String valor){
        rg = valor;
    }
    
    public void setEndereco(String valor){
        endereco = valor;
    }
    
    
}
