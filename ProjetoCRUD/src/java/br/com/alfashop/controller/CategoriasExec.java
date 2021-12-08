
package br.com.alfashop.controller;

import br.com.alfashop.model.Categoria;
import br.com.alfashop.repository.CategoriaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author odair.souza
 */
public class CategoriasExec extends HttpServlet {

    
    public void postServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        
        //criar o objeto Categoria
        Categoria objcat = new Categoria();
        objcat.setNome(nom);
        objcat.setDescricao(des);
        objcat.setAtivo("s");
        
        //criar o objeto DAO e insere na tabela
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.inserir(objcat);
        
        //voltar para a lista
        response.sendRedirect("categorias.jsp");
    }
    
    public void putServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String idc = request.getParameter("idc");
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String ati = request.getParameter("ati");
        
        //criar o objeto Categoria
        Categoria objcat = new Categoria();
        objcat.setIdcategoria(Long.parseLong(idc));
        objcat.setNome(nom);
        objcat.setDescricao(des);
        objcat.setAtivo(ati);
        
        //criar o objeto DAO e atualiza na tabela
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.atualizar(objcat);
        
        //voltar para a lista
        response.sendRedirect("categorias.jsp");
    }
    
    public void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pegar os parametros enviados do form
        String idc = request.getParameter("idc");
        
        //criar o objeto Categoria
        Categoria objcat = new Categoria();
        objcat.setIdcategoria(Long.parseLong(idc));
        
        //criar o objeto DAO e remove na tabela
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.excluir(objcat);
        
        //voltar para a lista
        response.sendRedirect("categorias.jsp");
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
            System.out.println("ID para excluir" + request.getParameter("idc"));
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
