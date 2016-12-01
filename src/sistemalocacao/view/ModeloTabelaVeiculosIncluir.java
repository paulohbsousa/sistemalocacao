/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemalocacao.view;

import sistemalocacao.util.Veiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael
 */
public class ModeloTabelaVeiculosIncluir extends AbstractTableModel{
    private String[] colunas = new String[]{"Marca","Modelo", "Estado", "Categoria", "Placa", "Ano"};

    private List<Veiculo> lista=new ArrayList();

    
    public ModeloTabelaVeiculosIncluir(List<Veiculo> lista){
        this.lista=lista;
    }

    public ModeloTabelaVeiculosIncluir(){
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
        switch (columnIndex) {
            case 0: return veiculo.getMarca();//if column 0 (code)
            case 1: return veiculo.getClass().getName();//if column 1 (name)
            case 2: return veiculo.getEstado();//if column 2 (birthday)
            case 3: return veiculo.getCategoria() ;
            case 4: return veiculo.getValorParaVenda();
            case 5: return veiculo.getPlaca();
            case 6: return veiculo.getAno();
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
    }*/

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
