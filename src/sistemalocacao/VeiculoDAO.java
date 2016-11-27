/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author dell
 * @param <Type>
 */
public class VeiculoDAO<Type extends Veiculo> {

    private PreparedStatement stmt = null;
    
    private void setModelo(Type veiculo) throws SQLException{
        String nomeClasse = veiculo.getClass().getCanonicalName();
        switch (nomeClasse){ // Pegar o nome da classe
            case "Van":
                Van van = (Van) veiculo;
                stmt.setInt(6, van.getModelo().ordinal() );
            break;
            case "Automovel":
                Automovel automovel = (Automovel) veiculo;
                stmt.setInt(6, automovel.getModelo().ordinal() );
            break;
            case "Motocicleta":
                Motocicleta moto = (Motocicleta) veiculo;
                stmt.setInt(6, moto.getModelo().ordinal() );
            break;
        }
        stmt.setString(7, nomeClasse);
    }
    
    public void insere(Type veiculo) throws SQLException {
        Connection connection = null;
        
        String sql = "insert into veiculos (marca,estado,categoria,valor,placa,modelo,tipo) values (?,?,?,?,?,?,?,?)";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getMarca().ordinal());
            stmt.setInt(2, veiculo.getEstado().ordinal());
            stmt.setInt(3, veiculo.getCategoria().ordinal());
            stmt.setDouble(4, veiculo.getValorParaVenda());
            stmt.setString(5, veiculo.getPlaca());
            setModelo(veiculo);
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
    
    public void deleta(Type veiculo) throws SQLException {
        Connection connection = null;
        String sql = "delete from veiculos where placa = ?";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
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
    
    public void devolver(Type veiculo) throws SQLException {
        Connection connection = null;
        String sql = "update veiculos set estado=? where placa = ?";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Estado.Disponivel.ordinal());
            stmt.setString(2, veiculo.getPlaca());
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
    
    public Automovel getListaVanAutomovel() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Automovel'";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            Locacao locacao = locacaoDAO.pega(rs.getInt("id"));
            Automovel automovel = new Automovel(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloAutomovel.values()[rs.getInt("modelo")]);
            return automovel;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    public Motocicleta getListaMotocicleta() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Motocicleta'";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            Locacao locacao = locacaoDAO.pega(rs.getInt("id"));
            Motocicleta motocicleta = new Motocicleta(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloMotocicleta.values()[rs.getInt("modelo")]);
            return motocicleta;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    public Van getListaVan() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Van'";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            stmt.close();
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            Locacao locacao = locacaoDAO.pega(rs.getInt("id"));
            Van van = new Van(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloVan.values()[rs.getInt("modelo")]);
            return van;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    
//    public Type pega(String placa) throws SQLException {
//        Connection connection = null;
//        String sql = "select * from veiculos where placa = ?";
//        ResultSet rs = null;
//        
//        try {
//            connection = new ConnectionFactoryComProperties().getConnection();
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, placa);
//            rs = stmt.executeQuery();
//            rs.next();
//            stmt.close();
//            LocacaoDAO locacaoDAO = new LocacaoDAO();
//            Locacao locacao = locacaoDAO.pega(rs.getInt("id"));
//            Type veiculo = (Type) new Object(rs.getInt("marca"), rs.getInt("estado"), locacao, rs.getInt("categoria"),rs.getInt("valor"), rs.getInt("placa"), rs.getInt("ano"), rs.getInt("modelo"));
//            return veiculo;           
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (connection != null){
//                connection.close();
//            }
//        }
//    }
}