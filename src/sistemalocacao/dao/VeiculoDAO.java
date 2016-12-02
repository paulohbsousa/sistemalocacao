/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.dao;

import sistemalocacao.bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 *
 * @author dell
 * @param <Type>
 */
public class VeiculoDAO<Type extends Veiculo> {

    private PreparedStatement stmt = null;
    
    private void setModelo(Type veiculo) throws SQLException{
        
    }
    
    public void insere(Type veiculo) throws SQLException {
        Connection connection = null;
        int modelo = 0;
        String sql = "insert into veiculos (marca,estado,categoria,valor,placa,modelo,tipo,ano) values (?,?,?,?,?,?,?,?)";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getMarca().ordinal());
            stmt.setInt(2, veiculo.getEstado().ordinal());
            stmt.setInt(3, veiculo.getCategoria().ordinal());
            stmt.setDouble(4, veiculo.getValorParaVenda());
            stmt.setString(5, veiculo.getPlaca());
            switch (veiculo.getClass().getSimpleName()){ // Pegar o nome da classe
                case "Van":
                    Van van = (Van) veiculo;
                    modelo = van.getModelo().ordinal();
                break;
                case "Automovel":
                    Automovel automovel = (Automovel) veiculo;
                    modelo = automovel.getModelo().ordinal();
                break;
                case "Motocicleta":
                    Motocicleta moto = (Motocicleta) veiculo;
                    modelo = moto.getModelo().ordinal();
                break;
            }
            stmt.setInt(6, modelo);
            stmt.setString(7, veiculo.getClass().getSimpleName());
            stmt.setInt(8, veiculo.getAno());
            stmt.executeUpdate();
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
    
    public void atualiza(Type veiculo) throws SQLException {
        Connection connection = null;
        String sql = "update veiculos set estado=? where placa = ?";
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getEstado().ordinal());
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
    
    public List<Automovel> getListaAutomovel() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Automovel'";
        ResultSet rs = null;
        List<Automovel> listaAutomovel = new ArrayList<Automovel>();
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Automovel> automoveis = new ArrayList();
            while(rs.next()){
                LocacaoDAO locacaoDAO = new LocacaoDAO();
                int locacaoId = rs.getInt("idLocacao");
                Locacao locacao = null;
                if (locacaoId > 0)
                    locacao = locacaoDAO.pega(rs.getInt("idLocacao"));
                
                Automovel automovel = new Automovel(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloAutomovel.values()[rs.getInt("modelo")]);
                automoveis.add(automovel);
            }
            stmt.close();
            return automoveis;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
    
    public List getLista(int marca, int categoria, String tipo) throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = ? and estado = 0";
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            if (marca > -1){
                sql = sql + " and marca = "+marca;
            }
            if (categoria > -1){
                sql = sql + " and categoria = "+categoria;
            }
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            List veiculos = new ArrayList();
            while(rs.next()){
                LocacaoDAO locacaoDAO = new LocacaoDAO();
                int locacaoId = rs.getInt("idLocacao");
                Locacao locacao = null;
                if (locacaoId > 0)
                    locacao = locacaoDAO.pega(rs.getInt("idLocacao"));
                switch (tipo){
                    case "Automovel":
                        Automovel automovel = new Automovel(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloAutomovel.values()[rs.getInt("modelo")]);
                        veiculos.add(automovel);
                        break;
                    case "Motocicleta":
                        Motocicleta moto = new Motocicleta(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloMotocicleta.values()[rs.getInt("modelo")]);
                        veiculos.add(moto);
                        break;
                    case "Van":
                        Van van = new Van(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloVan.values()[rs.getInt("modelo")]);
                        veiculos.add(van);
                        break;
                }
                
            }
            stmt.close();
            return veiculos;       

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            stmt.close();
            }
        }
    }
    
    public List<Motocicleta> getListaMotocicleta() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Motocicleta'";
        ResultSet rs = null;
        List<Motocicleta> listaMotocicleta = new ArrayList<Motocicleta>();
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            while(rs.next()){
                Locacao locacao = locacaoDAO.pega(rs.getInt("idLocacao"));
                Motocicleta motocicleta = new Motocicleta(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloMotocicleta.values()[rs.getInt("modelo")]);
                listaMotocicleta.add(motocicleta);
            }
            return listaMotocicleta;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            stmt.close();
            }
        }
    }
    
    public List<Van> getListaVan() throws SQLException {
        Connection connection = null;
        String sql = "select * from veiculos where tipo = 'Van'";
        ResultSet rs = null;
        List<Van> listaVan = new ArrayList<Van>();
        try {
            connection = new ConnectionFactoryComProperties().getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            while(rs.next()){
                Locacao locacao = locacaoDAO.pega(rs.getInt("idLocacao"));
                Van van = new Van(Marca.values()[rs.getInt("marca")], Estado.values()[rs.getInt("estado")], locacao, Categoria.values()[rs.getInt("categoria")],rs.getInt("valor"), rs.getString("placa"), rs.getInt("ano"), ModeloVan.values()[rs.getInt("modelo")]);
                listaVan.add(van);
            }
            return listaVan;       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null){
                connection.close();
            stmt.close();
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