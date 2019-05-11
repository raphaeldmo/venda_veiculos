/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author raphael.dmorlandi
 */
public class ClienteFisico extends Cliente {

    private String cpf;

    public ClienteFisico(int cId, String cpf, String cNome, int cIdsexo, String cRg, String cEmail, String cNacionalidade,
            String cDatanascimento, String cEndereco, String cCep,
            String cBairro, String cComplemento, String cCidade, String cEstado, String cCelular) {
        super(cId, cNome, cId, cRg, cEmail, cNacionalidade, cDatanascimento, cEndereco, cCep, cBairro, cComplemento, cCidade, cEstado, cCelular);

    }
    
    
}
