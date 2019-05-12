/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.aluguel.automoveis.webapp.Veiculos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.VeiculoDAO;
import Models.Veiculo;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "VeiculoServlet", urlPatterns = {"/veiculos"})
public class VeiculoServlet extends HttpServlet {

    private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // RECUPERA INFORMACOES DA REQUISICAO
        if (metodoHttp.equals("POST")) { // Aqui verifico se o que veio na requisição é POST
            if (request.getParameter("excluir") != null) { // Aqui verifico se o parametro EXCLUIR veio na requisição. Caso sim, o usuário está tentando excluir o veiculo.
                response.setContentType("application/json"); //aqui defino que o tipo de retorno deve ser JSON, para conseguirmos capturar a mensagem na view
                int id = Integer.parseInt(request.getParameter("id")); //Aqui defino o ID com o que veio na requisição
                if (VeiculoDAO.Inativar(id)) { // Aqui chamo o metodo excluir da DAO, passando o ID por parametro
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
            String modelo = request.getParameter("modelo");
            String montadora = request.getParameter("montadora");
            int ano = Integer.parseInt(request.getParameter("ano"));
            String placa = request.getParameter("placa");
            String renavam = request.getParameter("renavam");
            double valor = Double.parseDouble(request.getParameter("valor"));
            int combustivel = Integer.parseInt(request.getParameter("combustivel"));
            int cambio = Integer.parseInt(request.getParameter("cambio"));
            int status = Integer.parseInt(request.getParameter("status"));
            String acessorios = request.getParameter("acessorios");
            response.setContentType("application/json");
            //Nas linhas acima defini as variaveis com os parametros que vieram na requisicao
            if (request.getParameter("id") == null) { // aqui pergunto se veio ID na requisição. Caso não tenha vindo (igual a null) o usuário está tentando cadastrar um veiculo
                Veiculo v = new Veiculo(modelo, montadora, ano, placa, renavam, valor, combustivel, cambio, status, acessorios); // Então instancio um objeto da model para cadastrar (sem id)
                if (VeiculoDAO.Salvar(v)) {
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
            } else { // Caso caia no ELSE, significa que o usuario está tentando EDITAR. Ou sejam veio ID na requisição
                int id = Integer.parseInt(request.getParameter("id"));
                Veiculo v = new Veiculo(id, modelo, montadora, ano, placa, renavam, valor, combustivel, cambio, status, acessorios);
                if (VeiculoDAO.Atualizar(v)) {
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

        } else { // ESSE ELSE diz que o tipo de requisição foi GET. A requisição GET foi utilizada maioritariamente para buscar registros no banco de dados
            response.setContentType("application/json");
            if (request.getParameter("getStatus") != null) { //se Veio status na requisição, significa que estou pedindo os registros da tabela status no banco de DAdos, ou seja, estou querendo preencher meus combobox na view. O mesmo serve para os IFs abaixo*
                String json = new Gson().toJson(VeiculoDAO.getStatus()); // AQUI uso uma API do Google que converte um ArrayList em JSON. Faço isso por que é melhor para tratar os dados no javascript/jquery
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else if(request.getParameter("getCombustivel") != null) {
                String json = new Gson().toJson(VeiculoDAO.getCombustivel());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            } else if(request.getParameter("getCambio") != null) {
                String json = new Gson().toJson(VeiculoDAO.getCambio());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            }
            else {
                int id = Integer.parseInt(request.getParameter("id")); // nesse else, estou buscando os registros para preencher a tabela
                String json = new Gson().toJson(VeiculoDAO.getVeiculos(id));// AQUI uso uma API do Google que converte um ArrayList em JSON. Faço isso por que é melhor para tratar os dados no javascript/jquery
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processarRequisicao("GET", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processarRequisicao("POST", request, response);
    }

}
