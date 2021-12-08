<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean class="br.com.alfashop.repository.CategoriaDAO" id="lstcat"/>

        <div class="container">
            <div class="row">
              
              <%@include file="_inc/_menu.jsp" %>

              <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Cadastro de Produto</h2>
                    <a href="produtos.jsp">&laquo; Voltar para Lista</a>
                </div>
                
                <form action="produtos-exec" method="post">
                    <input type="hidden" name="action" value="insert">
                    
                    Categoria: <br>
                    <select name="cid">
                        <c:forEach items="${lstcat.categorias}" var="objcat">
                        <option value="${objcat.idcategoria}">${objcat.nome}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    
                    Nome: <br>
                    <input type="text" name="nom">
                    <br><br>
                    
                    Descrição: <br>
                    <input type="text" name="des">
                    <br><br>
                    
                    Mais Info: <br>
                    <input type="text" name="inf">
                    <br><br>
                    
                    Peso: <br>
                    <input type="text" name="pes">
                    <br><br>
                    
                    Valor: <br>
                    <input type="text" name="val">
                    <br><br>
                    
                    Destaque: <br>
                    <input type="radio" name="dtq" value="s"> SIM <br>
                    <input type="radio" name="dtq" value="n" checked="checked"> NÃO
                    <br><br>
                    
                    <button type="submit" class="btn btn-info">Cadastrar</button>
                </form>
                
              </main>
            </div>
        </div>
        
    </body>
</html>
