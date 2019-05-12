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

 @WebServlet(name = "ClientePjSalvarServlet", urlPatterns = {"/cliente/pj/salvar"})
 public class ClientePjSalvarServlet extends HttpServlet {

     private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
                    String nome = request.getParameter("nome");
                    String cpfCnpj = request.getParameter("cpf_cnpj");
                    String cnh = request.getParameter("cnh");
                    String email = request.getParameter("email");
                    //int idCategoriaCnh = Integer.parseInt(request.getParameter("categoria_cnh"));
                    String endereco = request.getParameter("endereco");
                    String cep = request.getParameter("cep");
                    String bairro = request.getParameter("bairro");
                    String complemento = "";
                    String validadeCnh = request.getParameter("validade_cnh");
                    String cidade = request.getParameter("cidade");
                    String estado = "SP";
                    String celular = request.getParameter("celular");
                    int categoria = Integer.parseInt(request.getParameter("categoria_cnh"));
                    int tipo = Integer.parseInt(request.getParameter("tipo"));
                    Cliente c = new Cliente(nome, cpfCnpj, cnh, categoria, email, validadeCnh, endereco, cep, bairro, complemento, cidade, estado, celular, tipo);
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