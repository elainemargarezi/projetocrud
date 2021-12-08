
Projeto CRUD = Catálogo de Produtos
	- Modelagem de dados e criação da estrutura de DB
	- Criação das telas visuais (web e adm com Bootstrap e jQuery)
	- Uso de JSP, JSTL, Servlet, Classes de Dados (ORM, DAO) => MVC

/web
	- página principal com lista dos produtos em destaque
	- menu com os nomes das categorias (e link para seus produtos)
	- página com a lista dos produtos conforme a categoria escolhida
	- página com os detalhes do produto (quando clicado para ver mais infos)
	- páginas de contato e sobre a empresa (sem conteúdo dinâmico)

/adm
	- página de login
	- validação do login (Servlet e classes DAO)
	- página inicial com avisos (fixos inicialmente)
	- menu lateral com opções (acesso, categorais, produtos)
		- Acesso
			- exibir dados e opção para alterar senha

		- Categorias
			- página com lista das categorias
			- link para ativar / desativar
			- link para excluir
			- link para editar (vai para página com dados para edição)
			- link para novo cadastro
			- sempre envia para classe "controller", que fará a comunicação com as classes de dados

		- Produtos
			- idem às opções de gerenciamento das categorias
			- link para colocar/retirar um produto de destaque