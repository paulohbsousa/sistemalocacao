/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemalocacao.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import sistemalocacao.bean.*;

public class ModeloTabelaVeiculosDevolver extends AbstractTableModel{
    private String[] colunas = new String[]{"Nome do Cliente", "Placa" ,"Marca","Modelo", "Ano", "Data Locacao", "Preco Diaria", "Quantidade de dias locados", "Valor locacao"};

    private List<Veiculo> lista= new ArrayList();
    private List<Van> listaVan = new ArrayList();
    private List<Automovel> listaAutomovel = new ArrayList();
    private List<Motocicleta> listaMotocicleta = new ArrayList();

    
    public ModeloTabelaVeiculosDevolver(List<Veiculo> lista){
        this.lista=lista;
    }

    public ModeloTabelaVeiculosDevolver(){
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
    
    public boolean removeVeiculo(Veiculo veiculo) {
        int linha = this.lista.indexOf(veiculo);
        boolean result = this.lista.remove(veiculo);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = lista.get(rowIndex);
        Automovel automovel = null;
        Motocicleta motocicleta = null;
        Van van = null;
        switch (columnIndex) {
            case 0: return veiculo.getLocacao().getCliente().getNome();
            case 1: return veiculo.getPlaca();
            case 2: return veiculo.getMarca();
            case 3: 
                switch(veiculo.getClass().getSimpleName()){
                    case "Automovel":   automovel = (Automovel) veiculo;  return automovel.getModelo();
                    case "Motocicleta": motocicleta = (Motocicleta) veiculo; return motocicleta.getModelo();
                    case "Van":         van = (Van) veiculo; return van.getModelo();
                }
            case 4: return veiculo.getAno();
            case 5: 
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new java.sql.Date(veiculo.getLocacao().getData().getTimeInMillis());  
                return dateFormat.format(date);
            case 6: return veiculo.getValorDiariaLocacao();
            case 7: return veiculo.getLocacao().getDias();
            case 8: return veiculo.getLocacao().getValor();
            
            default : return null;
        }
    }

    public void setListaVeiculos(List<Veiculo> veiculos) {
        this.lista = veiculos;
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

    public Veiculo getVeiculo(int linha){
        return lista.get(linha);
    }
    
}
