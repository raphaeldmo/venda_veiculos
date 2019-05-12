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
 * @author oem
 */
@WebServlet(name = "ClientePfSalvar", urlPatterns = {"/cliente/pf/salvar"})
public class ClientePfSalvarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        /**
         Inicio da coletagem de parametros
         **/
        response.setContentType("text/html;charset=UTF-8");
        int categoriaCliente = Integer.parseInt(request.getParameter("tipo"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        String cpfCnpj = request.getParameter("cpf_cnpj");
        String dataNascimento = request.getParameter("nascimento");
        String cnh = request.getParameter("cnh");
        String validadeCnh = request.getParameter("validade_cnh");
        int categoriaCnh = Integer.parseInt(request.getParameter("categoria_cnh"));
        String rg = request.getParameter("rg");
        String cep = request.getParameter("cep");
        String endereco = request.getParameter("endereco");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String celular = request.getParameter("celular");
        String nacionalidade = request.getParameter("nacionalidade");
        /*FIM DA COLETAGEM DE PARAMETROS*/
        /*INSTANCIO O OBJETO PASSANDO AS VARIAVEIS POR PARAMETRO*/
        Cliente c = new Cliente(nome, cpfCnpj, sexo, categoriaCliente, cnh, categoriaCnh, rg, email, nacionalidade, dataNascimento, validadeCnh, cep, endereco, numero, bairro, complemento, cidade, estado, celular);

        
        /*CHAMO O METODO DA DAO PASSANDO O OBJETO QUE ACABEI DE CRIAR*/
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClientePfSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClientePfSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
