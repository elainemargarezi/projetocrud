package br.com.alfashop.controller;

import br.com.alfashop.model.Produto;
import br.com.alfashop.repository.ProdutoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author odair.souza
 */
public class ProdutosExec extends HttpServlet {

    public void postServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String inf = request.getParameter("inf");
        String pes = request.getParameter("pes");
        String val = request.getParameter("val");
        String dtq = request.getParameter("dtq");
        String cid = request.getParameter("cid");
        
        //criar o objeto Produto
        Produto objpro = new Produto();
        objpro.setNome(nom);
        objpro.setDescricao(des);
        objpro.setMaisinfo(inf);
        objpro.setPeso(Float.parseFloat(pes));
        objpro.setValor(Float.parseFloat(val));
        objpro.setDestaque(dtq);
        objpro.setCategoriaid(Long.parseLong(cid));
        objpro.setAtivo("s");
        
        //criar o objeto DAO e insere na tabela
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.inserir(objpro);
        
        //voltar para a lista
        response.sendRedirect("produtos.jsp");
    }
    
    public void putServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String idp = request.getParameter("idp");
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String inf = request.getParameter("inf");
        String pes = request.getParameter("pes");
        String val = request.getParameter("val");
        String dtq = request.getParameter("dtq");
        String ati = request.getParameter("ati");
        String cid = request.getParameter("cid");
        
        //criar o objeto Produto
        Produto objpro = new Produto();
        objpro.setIdproduto(Long.parseLong(idp));
        objpro.setNome(nom);
        objpro.setDescricao(des);
        objpro.setMaisinfo(inf);
        objpro.setPeso(Float.parseFloat(pes));
        objpro.setValor(Float.parseFloat(val));
        objpro.setDestaque(dtq);
        objpro.setAtivo(ati);
        objpro.setCategoriaid(Long.parseLong(cid));
        
        //criar o objeto DAO e atualiza na tabela
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.atualizar(objpro);
        
        //voltar para a lista
        response.sendRedirect("produtos.jsp");
    }
    
    public void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String idp = request.getParameter("idp");
        
        //criar o objeto Produto
        Produto objpro = new Produto();
        objpro.setIdproduto(Long.parseLong(idp));
        
        //criar o objeto DAO e remove na tabela
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.excluir(objpro);
        
        //voltar para a lista
        response.sendRedirect("produtos.jsp");
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
        response.sendRedirect("categorias.jsp");
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
        
        //pegar o parametro que verifica a ação (insert, update ou delete)
        String act = request.getParameter("action");
        if (act.equals("insert")) {
            postServlet(request, response);
        }
        
        if (act.equals("update")) {
            putServlet(request, response);
        }
        
        if (act.equals("delete")) {
            deleteServlet(request, response);
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
