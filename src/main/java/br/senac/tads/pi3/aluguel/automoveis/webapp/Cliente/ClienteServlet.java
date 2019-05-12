/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.aluguel.automoveis.webapp.Cliente;

import DAO.ClienteDAO;
import Models.Cliente;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Date.parse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rapha
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {

    private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        // RECUPERA INFORMACOES DA REQUISICAO
        if (metodoHttp.equals("POST")) { // Aqui verifico se o que veio na requisição é POST
            if (request.getParameter("inativar") != null) { // Aqui verifico se o parametro INATIVAR veio na requisição. Caso sim, o usuário está tentando inativar um Cliente.
                response.setContentType("application/json"); //aqui defino que o tipo de retorno deve ser JSON, para conseguirmos capturar a mensagem na view
                int id = Integer.parseInt(request.getParameter("id")); //Aqui defino o ID com o que veio na requisição
                if (ClienteDAO.Inativar(id)) { // Aqui chamo o metodo inativar da DAO, passando o ID por parametro
                    String resposta = "{\"return\" : \"success\"}"; //Aqui defino o que será retornado para a view em caso de sucesso
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                } else {
                    String resposta = "{\"return\" : \"error\"}"; //Aqui defino o que será retornado para a view em caso de erro
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                }
            }
            String nome;
            String cpfCnpj;
            int idSexo;
            String cnh;
            int idCategoriaCnh;
            String rg;
            String email;
            String nacionalidade;
            String dataNascimentoString;
            String validadeCnh;
            String endereco;
            String cep;
            String bairro;
            String complemento;
            String cidade;
            String estado;
            String celular;
            int status;
            int categoria;

            int tipo = Integer.parseInt(request.getParameter("tipo"));
            /*if (tipo == 2) {
             try(PrintWriter out = response.getWriter()) {
                    
             }
             }*/

            response.setContentType("application/json");

            //Nas linhas acima defini as variaveis com os parametros que vieram na requisicao
            Cliente c;
            if (request.getParameter("id") == null) {// aqui pergunto se veio ID na requisição. Caso não tenha vindo (igual a null) o usuário está tentando cadastrar um Cliente
                if (tipo == 2) {
                    nome = request.getParameter("nome");
                    cpfCnpj = request.getParameter("cpf_cnpj");
                    cnh = request.getParameter("cnh");
                    email = request.getParameter("email");
                    //idCategoriaCnh = Integer.parseInt(request.getParameter("categoria_cnh"));
                    endereco = request.getParameter("endereco");
                    cep = request.getParameter("cep");
                    bairro = request.getParameter("bairro");
                    complemento = "";
                    validadeCnh = request.getParameter("validade_cnh");
                    cidade = request.getParameter("cidade");
                    estado = "SP";
                    celular = request.getParameter("celular");
                    categoria = Integer.parseInt(request.getParameter("categoria_cnh"));
                    c = new Cliente(nome, cpfCnpj, cnh, categoria, email, validadeCnh, endereco, cep, bairro, complemento, cidade, estado, celular, tipo);
                    if (ClienteDAO.Salvar(c)) {
                        String resposta = "{\"return\" : \"success\"}";
                        try (PrintWriter out = response.getWriter()) {
                            out.println(resposta);
                        }
                    } else {
                        String resposta = "{\"return\" : \"error\"}";
                        try (PrintWriter out = response.getWriter()) {
                            out.println(resposta);
                        }
                    }
                } else {
                    idSexo = Integer.parseInt(request.getParameter("Sexo"));

                    rg = request.getParameter("Rg");
                    nome = request.getParameter("nome");
                    cpfCnpj = request.getParameter("cpf_cnpj");
                    cnh = request.getParameter("cnh");
                    email = request.getParameter("email");
                    categoria = Integer.parseInt(request.getParameter("categoria_cnh"));
                    endereco = request.getParameter("endereco");
                    cep = request.getParameter("cep");
                    bairro = request.getParameter("bairro");
                    
                    cidade = request.getParameter("cidade");
                    
                    celular = request.getParameter("11952944432");
                    //categoria = request.getParameter("Categoria");
                    nacionalidade = request.getParameter("Nacionalidade");
                    dataNascimentoString = request.getParameter("Data de Nascimento");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = formatter.parse(dataNascimentoString);

                    status = Integer.parseInt(request.getParameter("Status"));
                    //c = new Cliente(nome, cpfCnpj, idSexo, cnh, idCategoriaCnh, rg, email, nacionalidade, dataNascimento, endereco,
                      //      cep, bairro, complemento, cidade, estado, celular, status, categoria); // Então instancio um objeto da model para cadastrar (sem id)
                }
                /*if (ClienteDAO.Salvar(c)) {
                    String resposta = "{\"return\" : \"success\"}";
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                } else {
                    String resposta = "{\"return\" : \"error\"}";
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                }*/
            } /*else { // Caso caia no ELSE, significa que o usuario está tentando EDITAR. Ou sejam veio ID na requisição
                int id = Integer.parseInt(request.getParameter("Id"));
                c = new Cliente(id, nome, cpfCnpj, idSexo, cnh, idCategoriaCnh, rg, email, nacionalidade, dataNascimento, endereco,
                        cep, bairro, complemento, cidade, estado, celular, status, categoria);
                if (ClienteDAO.Atualizar(c)) {
                    String resposta = "{\"return\" : \"success\"}";
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                } else {
                    String resposta = "{\"return\" : \"error\"}";
                    try (PrintWriter out = response.getWriter()) {
                        out.println(resposta);
                    }
                }
            }*/

        } else { // ESSE ELSE diz que o tipo de requisição foi GET. A requisição GET foi utilizada maioritariamente para buscar registros no banco de dados
            response.setContentType("application/json");
            if (request.getParameter("getStatus_Usuario_Cliente") != null) { //se Veio status na requisição, significa que estou pedindo os registros da tabela status no banco de DAdos, ou seja, estou querendo preencher meus combobox na view. O mesmo serve para os IFs abaixo*
                String json = new Gson().toJson(ClienteDAO.getStatusClienteUsuarios()); // AQUI uso uma API do Google que converte um ArrayList em JSON. Faço isso por que é melhor para tratar os dados no javascript/jquery
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else if (request.getParameter("getSexo") != null) {
                String json = new Gson().toJson(ClienteDAO.getSexo());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else if (request.getParameter("getCategoria_CNH") != null) {
                String json = new Gson().toJson(ClienteDAO.getCategoriaCNH());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else if (request.getParameter("getCategoria_Cliente") != null) {
                String json = new Gson().toJson(ClienteDAO.getCategoriaCliente());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else {
                int id = Integer.parseInt(request.getParameter("id")); // nesse else, estou buscando os registros para preencher a tabela
                String json = new Gson().toJson(ClienteDAO.getCliente(id));// AQUI uso uma API do Google que converte um ArrayList em JSON. Faço isso por que é melhor para tratar os dados no javascript/jquery
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processarRequisicao("GET", request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processarRequisicao("POST", request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
