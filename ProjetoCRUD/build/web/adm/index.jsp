<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Alfashop - Acesso Restrito</title>

    <!-- Bootstrap core CSS -->
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/login.css" rel="stylesheet">
  </head>
  <body class="text-center">
    
<main class="form-signin">
  <form action="login-exec" method="post">
    <img class="mb-4" src="./assets/img/logo.png" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Acesso Restrito</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="ema">
      <label for="floatingInput">Email</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="senhaacesso" name="sen">
      <label for="floatingPassword">Senha</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Lembrar login
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Validar</button>
    
    <%
    if (request.getParameter("msg")!=null && request.getParameter("msg").equals("err")) {
        out.print("Não foi possível validar o login.");
    }
    %>
    
    <p class="mt-5 mb-3 text-muted">&copy; Alfashop</p>
  </form>
</main>


    
  </body>
</html>
