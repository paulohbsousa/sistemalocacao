/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.view;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import sistemalocacao.bean.*;

/**
 *
 * @author marcelo
 */
public class ModeloTabelaVeiculoLocar extends AbstractTableModel{
    private String[] colunas = new String[]{"Placa" ,"Marca", "Modelo", "Ano", "Preco Diaria"};

    private List<Veiculo> lista=new ArrayList();

    public ModeloTabelaVeiculoLocar() {
    }
    
    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = lista.get(rowIndex);
        Automovel automovel = null;
        Motocicleta motocicleta = null;
        Van van = null;
        switch (columnIndex) {
            case 0: return veiculo.getPlaca();
            case 1: return veiculo.getMarca();
            case 2: 
                switch(veiculo.getClass().getSimpleName()){
                    case "Automovel":   automovel = (Automovel) veiculo;  return automovel.getModelo();
                    case "Motocicleta": motocicleta = (Motocicleta) veiculo; return motocicleta.getModelo();
                    case "Van":         van = (Van) veiculo; return van.getModelo();
                }
            case 3: return veiculo.getAno();
            case 4: return veiculo.getValorDiariaLocacao();
            default : return null;
        }
    }

    /*@Override
    public void setValueAt(Object value, int row, int col) {
        Veiculo veiculo = lista.get(row);
        switch (col) {
            case 0:
                cliente.setCPF((Integer) value);
                break;
            case 1:
                cliente.setNome((String) value);
                break;
            case 2:
                cliente.setSobrenome((String) value);
                break;
            case 3:
                cliente.setRG((String) value);
                break;
            case 4:
                cliente.setEndereco((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }*/

    public void setListaVeiculos(List<Veiculo> veiculos) {
        this.lista = veiculos;
        this.fireTableDataChanged();
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Veiculo getVeiculo(int linha){
        return lista.get(linha);
    }
    
}