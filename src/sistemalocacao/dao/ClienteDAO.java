/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.dao;

import sistemalocacao.bean.Locacao;
import sistemalocacao.bean.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import sistemalocacao.bean.CantDeleteException;

/**
 *
 * @author dell
 */
public class ClienteDAO {

    public Cliente pega(long cpf) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select * from clientes where cpf = ?";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cpf);
            rs = stmt.executeQuery();
            if ( !rs.next() )
                throw new RuntimeException("Iterando recurso invalido");
            
            Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("rg"),rs.getLong("cpf"),rs.getString("endereco"));
            stmt.close();
            return cliente;
            
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    public List<Cliente> getLista() throws SQLException{
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select * from clientes";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                // criando o objeto Cliente
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("rg"),rs.getLong("cpf"),rs.getString("endereco"));
                // adicionando o objeto à lista
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmt.close();
        }

    }
    
    public void deleta(Cliente cliente) throws SQLException, Exception {
        Connection connection = null;
        String sql = "delete from clientes where cpf = ?";
        PreparedStatement stmt = null;
        
        try {
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            
            if ( locacaoDAO.getLocacaoCliente(cliente.getCPF()) instanceof Locacao )
                throw new CantDeleteException("Cliente possui veículo locado.");
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cliente.getCPF());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    public void insere(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "insert into clientes (cpf,nome,sobrenome,rg,endereco) values (?,?,?,?,?)";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobrenome()); 
            stmt.setString(4, cliente.getRG());
            stmt.setString(5, cliente.getEndereco());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    public void atualiza(Cliente cliente,long cpf) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "update clientes set cpf=?,nome=?,sobrenome=?,rg=?,endereco=? where cpf=?";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobrenome()); 
            stmt.setString(4, cliente.getRG());
            stmt.setString(5, cliente.getEndereco());
            stmt.setLong(6, cpf);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
}
