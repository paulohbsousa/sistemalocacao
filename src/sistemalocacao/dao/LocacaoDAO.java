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
import java.util.Calendar;

/**
 *
 * @author dell
 */
public class LocacaoDAO {
    public void insere(Locacao locacao) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "insert into locacoes (idClientes,data,dias,valor) values (?,?,?,?)";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, locacao.getCliente().getCPF());
            Calendar calendar = Calendar.getInstance();
            Date date = new Date(calendar.getTimeInMillis());
            stmt.setDate(2, date); // Conversao
            stmt.setInt(3, locacao.getDias()); // Falta
            stmt.setDouble(4, locacao.getValor());
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
    
    public void deleta(Locacao locacao) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "delete from locacoes where id = ?";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getId());
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
    
    public Locacao getLocacaoCliente(long cpf) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select * from locacoes where idClientes = ? limit 1";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cpf);
            rs = stmt.executeQuery();
            if (!rs.next())
                return null;
            
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.pega(cpf);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rs.getDate("data"));
            Locacao locacao = new Locacao(rs.getInt("dias"), rs.getDouble("valor"), calendar , cliente);
            return locacao;           
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            stmt.close();
            }
        }
    }
    
    public Locacao pega(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select * from locacoes where id = ? limit 1";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Locacao locacao = null;
            
            if ( rs.next() ){
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.pega(rs.getLong("idClientes"));
                Calendar calendar = Calendar.getInstance(); //verificar isso
                calendar.setTime(rs.getDate("data"));
                locacao = new Locacao(rs.getInt("dias"), rs.getDouble("valor"), calendar , cliente);
                locacao.setId(rs.getInt("id"));
            }
            return locacao;           
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            stmt.close();
            }
        }
    }
}
