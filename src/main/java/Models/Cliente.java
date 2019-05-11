package Models;

import java.util.Date;

/**
 *
 * @author RaphaelOrlandi
 */
public  abstract class Cliente {

    private int id;
    private String nome;
//    private String cpfCnpj;
    private int idsexo;
    private String rg;
    private String email;
    private String nacionalidade;
    private String dataNascimento;
    private String endereco;
    private String cep;
    private String bairro;
    private String complemento;
    private int numero;
    private String cidade;
    private String estado;
    private String celular;

    public Cliente() {

    }

    public Cliente(int cId, String cNome, int cIdsexo, String cRg, String cEmail, String cNacionalidade, String cDatanascimento, String cEndereco,
            String cCep, String cBairro, String cComplemento, String cCidade, String cEstado, String cCelular) {
        this.id = cId;
        this.nome = cNome;
        this.rg = cRg;
        this.email = cEmail;
        this.nacionalidade = cNacionalidade;
        this.dataNascimento = cDatanascimento;
        this.endereco = cEndereco;
        this.cep = cCep;
        this.bairro = cBairro;
        this.complemento = cComplemento;
        this.cidade = cCidade;
        this.estado = cEstado;
        this.celular = cCelular;
    }

    public Cliente( int cIdsexo, String cNome, String cRg, String cEmail, String cNacionalidade, String cDatanascimento, String cEndereco,
            String cCep, String cBairro, String cComplemento, String cCidade, String cEstado, String cCelular) {
        this.nome = cNome;
        this.rg = cRg;
        this.email = cEmail;
        this.nacionalidade = cNacionalidade;
        this.dataNascimento = cDatanascimento;
        this.endereco = cEndereco;
        this.cep = cCep;
        this.bairro = cBairro;
        this.complemento = cComplemento;
        this.cidade = cCidade;
        this.estado = cEstado;
        this.celular = cCelular;
    
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdsexo() {
        return idsexo;
    }

    public String getRg() {
        return rg;
    }

    public String getEmail() {
        return email;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCelular() {
        return celular;
    }
    
    
    
}
    