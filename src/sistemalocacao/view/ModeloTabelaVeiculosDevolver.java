/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemalocacao.view;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import sistemalocacao.bean.*;

public class ModeloTabelaVeiculosDevolver extends AbstractTableModel{
    private String[] colunas = new String[]{"Nome do Cliente", "Placa" ,"Marca","Modelo", "Ano", "Data Locacao", "Preco Diaria", "Quantidade de dias locados", "Valor locacao"};

<<<<<<< HEAD:src/sistemalocacao/view/ModeloTabelaVeiculosLocar.java
    private List<Veiculo> lista=new ArrayList();
=======
<<<<<<< HEAD:src/sistemalocacao/view/ModeloTabelaVeiculosLocar.java
<<<<<<< HEAD:src/sistemalocacao/ModeloTabelaVeiculos.java
=======
    private List<Veiculo> lista= new ArrayList();
>>>>>>> ff71e4b28702015fddc9895adf6959c53c7334a5:src/sistemalocacao/view/ModeloTabelaVeiculosDevolver.java
    private List<Van> listaVan = new ArrayList();
    private List<Automovel> listaAutomovel = new ArrayList();
    private List<Motocicleta> listaMotocicleta = new ArrayList();
<<<<<<< HEAD:src/sistemalocacao/view/ModeloTabelaVeiculosLocar.java
=======
    private List<Veiculo> lista=new ArrayList();
>>>>>>> 38e8cb262897ba22da3b1948ba43322287ee5ebb:src/sistemalocacao/view/ModeloTabelaVeiculosLocar.java
=======
>>>>>>> ff71e4b28702015fddc9895adf6959c53c7334a5:src/sistemalocacao/view/ModeloTabelaVeiculosDevolver.java
>>>>>>> marmleite-master:src/sistemalocacao/view/ModeloTabelaVeiculosDevolver.java

    
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = lista.get(rowIndex);
        Automovel automovel = null;
        Motocicleta motocicleta = null;
        Van van = null;
        switch (columnIndex) {
            case 0: return veiculo.getLocacao().getCliente();
            case 1: return veiculo.getPlaca();
            case 2: return veiculo.getMarca();
            case 3: 
                switch(veiculo.getClass().getSimpleName()){
                    case "Automovel":   automovel = (Automovel) veiculo;  return automovel.getModelo();
                    case "Motocicleta": motocicleta = (Motocicleta) veiculo; return motocicleta.getModelo();
                    case "Van":         van = (Van) veiculo; return van.getModelo();
                }
            case 4: return veiculo.getAno();
            case 5: return veiculo.getLocacao().getData();
            case 6: return veiculo.getValorDiariaLocacao();
            case 7: return veiculo.getLocacao().getDias();
            case 8: return veiculo.getLocacao().getValor();
            
            default : return null;
        }
    }

    //veiculo nao possui metodos set
    /*@Override
    public void setValueAt(Object value, int row, int col) {
        Veiculo veiculo = lista.get(row);
        switch (col) {
            case 0:
                veiculo.setMarca(String) value);
                break;
            case 1:
                veiculo.setNome((String) value);
                break;
            case 2:
                veiculo.setSobrenome((String) value);
                break;
            case 3:
                veiculo.setRG((String) value);
                break;
            case 4:
                veiculo.setEndereco((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        int linha = this.lista.indexOf(veiculo);
        boolean result = this.lista.remove(veiculo);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaVeiculo(Veiculo veiculo) {
        this.lista.add(veiculo);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }*/

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
