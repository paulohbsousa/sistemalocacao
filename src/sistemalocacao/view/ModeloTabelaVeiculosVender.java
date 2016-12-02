/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemalocacao.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sistemalocacao.bean.Automovel;
import sistemalocacao.bean.Motocicleta;
import sistemalocacao.bean.Van;
import sistemalocacao.bean.Veiculo;

/**
 *
 * @author Rafael
 */
public class ModeloTabelaVeiculosVender extends AbstractTableModel{
    private String[] colunas = new String[]{"Placa","Marca", "Modelo", "Ano","Preço para Venda"};

    private List<Veiculo> lista=new ArrayList();

    
    public ModeloTabelaVeiculosVender(List lista){
        this.lista=lista;
    }
    
    public ModeloTabelaVeiculosVender(){
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
        DecimalFormat df = new DecimalFormat("R$########,##0.00");
        switch (columnIndex) {
            case 0: return veiculo.getPlaca();//if column 0 (code)
            case 1: return veiculo.getMarca();//if column 1 (name)
            case 2: 
                switch (veiculo.getClass().getSimpleName()){
                    case "Automovel":
                        Automovel automovel = (Automovel) veiculo;
                        return automovel.getModelo().toString();
                    case "Motocicleta":
                        Motocicleta motocicleta = (Motocicleta) veiculo;
                        return motocicleta.getModelo().toString();
                    case "Van":
                        Van van = (Van) veiculo;
                        return van.getModelo().toString();
                }
            case 3: return veiculo.getAno() ;
            case 4: return df.format(veiculo.getValorParaVenda());
            default : return null;
        }
    }

  

    public boolean removeVeiculo(Veiculo veiculo) {
        int linha = this.lista.indexOf(veiculo);
        boolean result = this.lista.remove(veiculo);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void setListaVeiculos(List veiculos) {
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
