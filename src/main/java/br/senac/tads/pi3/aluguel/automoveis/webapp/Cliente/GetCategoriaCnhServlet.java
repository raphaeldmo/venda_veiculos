package br.senac.tads.pi3.aluguel.automoveis.webapp.Cliente;

import br.senac.tads.pi3.aluguel.automoveis.webapp.Cliente.ClienteServlet;
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

 @WebServlet(name = "GetCategoriaCnhServlet", urlPatterns = {"/cliente/categoria-cnh"})
 public class GetCategoriaCnhServlet extends HttpServlet {

     private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
                response.setContentType("application/json");
                String json = new Gson().toJson(ClienteDAO.getCategoriaCNH()); // AQUI uso uma API do Google que converte um ArrayList em JSON. Faço isso por que é melhor para tratar os dados no javascript/jquery
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
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