package DAO;

/**
 *
 * @author Raphael Orlandi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Cliente;
import Models.ListaCliente;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClienteDAO {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        // 1) Declarar o driver JDBC de acordo com o Banco de dados usado
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2) Abrir a conexão
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tades_locadora?useTimezone=true&serverTimezone=UTC",
                "root",
                "");
        return conn;
    }

    public static boolean Salvar(Cliente c) throws ParseException {
        boolean retorno = false;

        Connection connection = null;
        try {
            connection = obterConexao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String data = dfUsa.format();
        //Date parsed = (Date) dfUsa.parse(data);

        try {
            PreparedStatement Create = connection.prepareStatement(
                    "INSERT INTO cliente ("
                    + "nome,"
                    + "	cpf_cnpj,"
                    + "	id_sexo,"
                    + "	 id_categoria_cliente ,"
                    + "	 cnh ,"
                    + "	 id_categoria_cnh ,"
                    + "	 rg ,"
                    + "	 email ,"
                    + "	 nacionalidade ,"
                    + "	 data_nascimento ,"
                    + "	 validade_cnh ,"
                    + "	 cep ,"
                    + "	 endereco ,"
                    + "	 numero ,"
                    + "	 bairro ,"
                    + "	 complemento ,"
                    + "	 cidade ,"
                    + "	 estado ,"
                    + "	 celular)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            Create.setString(1, c.getNome());
            Create.setInt(3, c.getIdsexo());
            Create.setString(7, c.getRg());
            Create.setString(8, c.getEmail());
            Create.setString(9, c.getNacionalidade());
            Create.setString(10, c.getDataNascimento());
            Create.setString(12, c.getCep());
            Create.setString(13, c.getEndereco());
            Create.setInt(14, c.getNumero());
            Create.setString(15, c.getBairro());
            Create.setString(16, c.getComplemento());
            Create.setString(17, c.getCidade());
            Create.setString(18, c.getEstado());
            Create.setString(19, c.getCelular());

            int linhasAfetadas = Create.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    //implementar metodo inativar - recebe ID e realizar UPDATE no registro
    public static boolean Inativar(int id) {
        boolean retorno = false;
        try {
            Connection Conexao = obterConexao();

            PreparedStatement Update = Conexao.prepareStatement(
                    "UPDATE cliente SET "
                    + "id_status = 2 "
                    + "WHERE id = ?");
            Update.setInt(1, id);
            int linhasAfetadas = Update.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static boolean Atualizar(Cliente c) {

        boolean retorno = false;
        try {
            Connection Conexao = obterConexao();

            PreparedStatement Update = Conexao.prepareStatement(
                    "UPDATE cliente SET "
                    + "NOME,"
                    + "CPF_CNPJ,"
                    + "ID_SEXO,"
                    + "CNH,"
                    + "ID_CATEGORIA_CNH,"
                    + "RG,"
                    + "EMAIL,"
                    + "NACIONALIDASE,"
                    + "DATA_NASCIMENTO,"
                    + "ENDERECO,"
                    + "CEP,"
                    + "BAIRRO,"
                    + "COMPLEMENTO,"
                    + "CIDADE,"
                    + "ESTADO,"
                    + "CELULAR,"
                    + "STATUS,"
                    + "ID_CATEGORIA_CLIENTE)"
                    + "WHERE ID = ");

            Update.setString(1, c.getNome());
            Update.setInt(3, c.getIdsexo());
            Update.setString(6, c.getRg());
            Update.setString(7, c.getEmail());
            Update.setString(8, c.getNacionalidade());
            Update.setString(9, c.getDataNascimento());
            Update.setString(10, c.getEndereco());
            Update.setString(11, c.getCep());
            Update.setString(12, c.getBairro());
            Update.setString(13, c.getComplemento());
            Update.setString(14, c.getCidade());
            Update.setString(15, c.getEstado());
            Update.setString(16, c.getCelular());
            Update.setInt(19, c.getId());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

//    public static ArrayList<CategoriaCliente> getCategoriaCliente() {
//        ArrayList<CategoriaCliente> ListaCategoriaCliente = new ArrayList<CategoriaCliente>();
//
//        String query = "SELECT * FROM CATEGORIA_CLIENTE;";
//
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(query);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                CategoriaCliente categoria_cliente = new CategoriaCliente();
//                categoria_cliente.setId(rs.getInt("id"));
//                categoria_cliente.setCategoria(rs.getString("Categoria"));
//                ListaCategoriaCliente.add(categoria_cliente);
//
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return ListaCategoriaCliente;
//    }
    public static ArrayList<ListaCliente> getCliente(int id) {
        ArrayList<ListaCliente> listaClientes = new ArrayList<ListaCliente>();

        String query = "select "
                + "cliente.id, "
                + "nome, "
                + "categoria_cliente.categoria, "
                + "cpf_cnpj, "
                + "email, "
                + "`status_cliente_usuario`.`status` "
                + "from tades_locadora.cliente "
                + "INNER JOIN categoria_cliente ON cliente.id_categoria_cliente = categoria_cliente.id "
                + "INNER JOIN status_cliente_usuario ON cliente.id_status = status_cliente_usuario.id";
        if (id != 0) {
            query += " where cliente.id = " + id;
        }

        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ListaCliente lista = new ListaCliente();
                lista.setId(rs.getInt("id"));
                lista.setNome(rs.getString("nome"));
                lista.setCpfcnpj(rs.getString("cpf_cnpj"));
                lista.setEmail(rs.getString("email"));
                lista.setStatus(rs.getString("status"));
                lista.setCategoria(rs.getString("categoria"));
                listaClientes.add(lista);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return listaClientes;
    }
//
//    public static ArrayList<Cliente> getClienteCpf(String cpf) {
//        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
//        int numeroLinhas = 0;
//        
//        String verificaLocacao = "SELECT * FROM cliente inner join locacoes on cliente.id = locacoes.id_cliente where cliente.cpf_cnpj = '" + cpf + "' AND locacoes.id_status_locacao = 1;";
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(verificaLocacao);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                numeroLinhas = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        if(numeroLinhas > 0 ) {
//            return listaCliente;
//        } 
//
//        String query = "SELECT * FROM cliente where cpf_cnpj = '" + cpf + "';";
//
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(query);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(rs.getInt("id"));
//                cliente.setIdCategoriaCliente(rs.getInt("id_categoria_cliente"));
//                cliente.setNome(rs.getString("nome"));
//                cliente.setCnh(rs.getString("cnh"));
//                cliente.setValidadeCnh(rs.getString("validade_cnh"));
//                listaCliente.add(cliente);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//
//        return listaCliente;
//    }

}
