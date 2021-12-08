<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean class="br.com.alfashop.repository.CategoriaDAO" id="lstcat"/>

        <div class="container">
            <div class="row">
              
              <%@include file="_inc/_menu.jsp" %>

              <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Lista das Categorias</h2>
                    <a href="categorias-insert.jsp" class="btn btn-info">Cadastrar Categoria</a>
                </div>
                
                <div class="table-responsive">
                  <table class="table table-striped table-sm">
                    <thead>
                      <tr>
                        <th scope="col">Cod</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Descricao</th>
                        <th scope="col">Ativo</th>
                        <th scope="col">Opções</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${lstcat.categorias}" var="objcat">
                      <tr>
                        <td>${objcat.idcategoria}</td>
                        <td>${objcat.nome}</td>
                        <td>${objcat.descricao}</td>
                        <td>${objcat.ativo}</td>
                        <td>
                            <a href="categorias-update.jsp?idc=${objcat.idcategoria}" class="btn btn-warning">[ A ]</a>
                            
                            <form action="categorias-exec" method="post" id="fdel${objcat.idcategoria}">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="idc" value="${objcat.idcategoria}">
                                <button class="btn btn-danger" type="button" onclick="avisoDel('${objcat.nome}', ${objcat.idcategoria})">[ X ]</button>
                            </form>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
                
              </main>
            </div>
        </div>
              
        <script type="text/javascript">
            function avisoDel(nom, idc) {
                var ok = confirm("Deseja excluir a categoria "+nom+"???");
                if (ok) {
                    var div = document.getElementById("fdel"+idc).submit();
                }
            }
        </script>
    </body>
</html>
