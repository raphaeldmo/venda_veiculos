/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author vsilva
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Veiculo;

public class VeiculoDAO {

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

    public static boolean Salvar(Veiculo v) {
        boolean retorno = false;

        Connection connection = null;
        try {
            connection = obterConexao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement Create = connection.prepareStatement(
                    "INSERT INTO tades_locadora.veiculo ("
                    + "modelo,"
                    + "montadora,"
                    + "ano,"
                    + "placa,"
                    + "renavam,"
                    + "valor_diaria,"       
                    + "id_combustivel,"
                    + "id_cambio,"
                    + "id_status,"
                    + "acessorios)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            Create.setString(1, v.getModelo());
            Create.setString(2, v.getMontadora());
            Create.setInt(3, v.getAno());
            Create.setString(4, v.getPlaca());
            Create.setString(5, v.getRenavam());
            Create.setDouble(6, v.getValor());
            Create.setInt(7, v.getIdCombustivel());
            Create.setInt(8, v.getIdCambio());
            Create.setInt(9, v.getIdStatus());
            Create.setString(10, v.getAcessorio());

            int linhasAfetadas = Create.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static boolean Atualizar(Veiculo v) {

        boolean retorno = false;
        System.out.println(v);
        try {
            Connection Conexao = obterConexao();

            PreparedStatement Update = Conexao.prepareStatement(
                    "UPDATE veiculo SET modelo = ? , "
                    + "montadora = ? , "
                    + "ano = ? , "
                    + "placa = ? , "
                    + "renavam = ? , "
                    + "id_combustivel = ? ,"
                    + "id_cambio = ? , "
                    + "id_status = ? , "
                    + "acessorios = ?"
                    + "WHERE id = " + v.getId());

            Update.setString(1, v.getModelo());
            Update.setString(2, v.getMontadora());
            Update.setInt(3, v.getAno());
            Update.setString(4, v.getPlaca());
            Update.setString(5, v.getRenavam());
            Update.setInt(6, v.getIdCombustivel());
            Update.setInt(7, v.getIdCambio());
            Update.setInt(8, v.getIdStatus());
            Update.setString(9, v.getAcessorio());
            int linhasAfetadas = Update.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public static boolean Excluir(int id) {
        boolean retorno = false;
        try {
            System.out.println(id);
            Connection Conexao = obterConexao();

            PreparedStatement Update = Conexao.prepareStatement(
                    "DELETE FROM veiculo WHERE ID = " + id);

            int linhasAfetadas = Update.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static boolean Inativar(int id) {
        boolean retorno = false;
        try {
            Connection Conexao = obterConexao();

            PreparedStatement Update = Conexao.prepareStatement(
                    "UPDATE veiculo SET id_status_atividade = 2 "
                    + "WHERE id = ?");

            Update.setInt(1, id);
            int linhasAfetadas = Update.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

//    public static ArrayList<StatusVeiculo> getStatus() {
//        ArrayList<StatusVeiculo> listaStatus = new ArrayList<StatusVeiculo>();
//
//        String query = "SELECT * FROM status_veiculo;";
//
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(query);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                StatusVeiculo status = new StatusVeiculo();
//                status.setId(rs.getInt("id"));
//                status.setStatus(rs.getString("status"));
//                listaStatus.add(status);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return listaStatus;
//    }

//    public static ArrayList<CombustivelVeiculo> getCombustivel() {
//        ArrayList<CombustivelVeiculo> listaCombustivel = new ArrayList<CombustivelVeiculo>();
//
//        String query = "SELECT * FROM combustivel;";
//
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(query);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                CombustivelVeiculo combustivel = new CombustivelVeiculo();
//                combustivel.setId(rs.getInt("id"));
//                combustivel.setCombustivel(rs.getString("combustivel"));
//                listaCombustivel.add(combustivel);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return listaCombustivel;
//    }

//    public static ArrayList<CambioVeiculo> getCambio() {
//        ArrayList<CambioVeiculo> listaCambio = new ArrayList<CambioVeiculo>();
//
//        String query = "SELECT * FROM cambio;";
//
//        try (Connection conn = obterConexao();
//                PreparedStatement stmt = conn.prepareStatement(query);
//                ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                CambioVeiculo cambio = new CambioVeiculo();
//                cambio.setId(rs.getInt("id"));
//                cambio.setCambio(rs.getString("cambio"));
//                listaCambio.add(cambio);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return listaCambio;
//    }

    public static ArrayList<ListaVeiculos> getVeiculos(int id) {
        ArrayList<ListaVeiculos> listaVeiculos = new ArrayList<ListaVeiculos>();

        String query = "";
        if (id != 0) {
            query = "SELECT * FROM veiculo where id_status_atividade = 1 AND id = " + id;
        } else {
            query = "SELECT \n"
                    + "	veiculo.id, \n"
                    + "	veiculo.modelo, \n"
                    + "	veiculo.montadora, \n"
                    + "	veiculo.ano, \n"
                    + "	veiculo.placa, \n"
                    + "	veiculo.renavam,\n"
                    + "	veiculo.valor_diaria,\n"
                    + "	combustivel.combustivel,\n"
                    + "	cambio.cambio,\n"
                    + "	`status_veiculo`.status,\n"
                    + "	veiculo.acessorios FROM veiculo\n"
                    + "	INNER JOIN `combustivel` ON `combustivel`.id = `veiculo`.id_combustivel\n"
                    + "	INNER JOIN `status_veiculo` ON `status_veiculo`.id = `veiculo`.id_status\n"
                    + "	INNER JOIN `cambio` ON `cambio`.id = `veiculo`.id_cambio"
                    + " WHERE id_status_atividade = 1";
        }

        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ListaVeiculos lista = new ListaVeiculos();
                lista.setId(rs.getInt("id"));
                lista.setModelo(rs.getString("modelo"));
                lista.setMontadora(rs.getString("montadora"));
                lista.setAno(rs.getInt("ano"));
                lista.setPlaca(rs.getString("placa"));
                lista.setRenavam(rs.getString("renavam"));
                lista.setValor(rs.getDouble("valor_diaria"));
                if (id != 0) {
                    lista.setCombustivel(rs.getString("id_combustivel"));
                    lista.setCambio(rs.getString("id_cambio"));
                    lista.setStatus(rs.getString("id_status"));
                } else {
                    lista.setCombustivel(rs.getString("combustivel"));
                    lista.setCambio(rs.getString("cambio"));
                    lista.setStatus(rs.getString("status"));
                }
                lista.setAcessorio(rs.getString("acessorios"));
                //System.out.println(p.getData_cadastro());  
                listaVeiculos.add(lista);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return listaVeiculos;
    }

    //public static ArrayList<ListaVeiculos> getVeiculos(int id) {
}
