/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemalocacao;

/**
 *
 * @author dell
 */
class CantDeleteException extends Exception {

    public CantDeleteException(String motivo) {
        super("Não é possível deletar o registro. Motivo: "+motivo);
    }
    
}
