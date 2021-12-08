<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%@page import="br.com.alfashop.model.Categoria" %>
<%@page import="br.com.alfashop.repository.CategoriaDAO" %>
<%
String sidc = request.getParameter("idc");
Long idc = Long.parseLong(sidc);
CategoriaDAO objdao = new CategoriaDAO();
Categoria objcat = objdao.buscarPorId(idc);

//teste para o checked do radio (ativo s ou n)
String chksim = "";
if (objcat.getAtivo().equals("s")) {
    chksim = "checked='checked'";
}
String chknao = "";
if (objcat.getAtivo().equals("n")) {
    chknao = "checked='checked'";
}

//adiciona variáveis na página
pageContext.setAttribute("cat", objcat);
pageContext.setAttribute("chksim", chksim);
pageContext.setAttribute("chknao", chknao);
%>

        <div class="container">
            <div class="row">
              
              <%@include file="_inc/_menu.jsp" %>

              <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Atualização da Categorias</h2>
                    <a href="categorias.jsp">&laquo; Voltar para Lista</a>
                </div>
                
                <form action="categorias-exec" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="idc" value="${cat.idcategoria}">
                    
                    Nome: <br>
                    <input type="text" name="nom" value="${cat.nome}">
                    <br><br>
                    
                    Descrição: <br>
                    <input type="text" name="des" value="${cat.descricao}">
                    <br><br>
                    
                    Ativo: <br>
                    <input type="radio" name="ati" value="s" ${chksim}> SIM <br>
                    <input type="radio" name="ati" value="n" ${chknao}> NÃO
                    <br><br>
                    
                    <button type="submit" class="btn btn-info">Atualizar</button>
                </form>
                
              </main>
            </div>
        </div>
        
    </body>
</html>
