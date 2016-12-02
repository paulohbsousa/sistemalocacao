/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sistemalocacao.bean.Cliente;

/**
 *
 * @author marcelo
 */
public class ModeloTabelaClienteLocar extends AbstractTableModel{
    private String[] colunas = new String[]{"CPF","Nome", "Sobrenome", "RG","Endereco"};

    private List<Cliente> lista=new ArrayList();
    
    
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
        Cliente cliente = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getCPF();//if column 0 (code)
            case 1: return cliente.getNome();//if column 1 (name)
            case 2: return cliente.getSobrenome();//if column 2 (birthday)
            case 3: return cliente.getRG() ;
            case 4: return cliente.getEndereco() ;
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Cliente cliente = lista.get(row);
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
    }

    public void setListaClientes(List<Cliente> clientes) {
        this.lista = clientes;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Cliente getCliente(int linha){
        return lista.get(linha);
    }
    
}