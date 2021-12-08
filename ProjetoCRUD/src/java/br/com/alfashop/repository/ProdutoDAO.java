package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author odair.souza
 */
public class ProdutoDAO 
{
    Connection conn;
    
    /**
     * Construtor da classe
     * sempre que criar um objeto, chama esse método
     */
    public ProdutoDAO() {
        this.conn = Conex.getConnection();
    }
    
    
    /**
     * @return List<Produto> 
     */
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<Produto>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select * from produtos";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Produto obj = new Produto();
                obj.setIdproduto(rset.getLong("idprodutos"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setMaisinfo(rset.getString("maisinfo"));
                obj.setPeso(rset.getFloat("peso"));
                obj.setValor(rset.getFloat("valor"));
                obj.setDestaque(rset.getString("destaque"));
                obj.setAtivo(rset.getString("ativo"));
                obj.setCategoriaid(rset.getLong("categorias_idcategorias"));
                //adiciono o obj em uma lista 
                lista.add(obj);
            }
            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e) {
            lista = null;
        }
        return lista;
    }
    /**
     * método para ser usado como JavaBean getProduto()
     */
    public List<Produto> getProdutos() {
        return listar();
    }
    
    
    /**
     * @return Produto
     */
    public List<Produto> buscar(String filtro) {
        List<Produto> lista = new ArrayList<Produto>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select p.*, c.nome as nomecat from produtos p "
                        + "inner join categorias c on c.idcategorias = p.categorias_idcategorias "
                        + "where "+filtro;
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Produto obj = new Produto();
                obj.setIdproduto(rset.getLong("idprodutos"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setMaisinfo(rset.getString("maisinfo"));
                obj.setPeso(rset.getFloat("peso"));
                obj.setValor(rset.getFloat("valor"));
                obj.setDestaque(rset.getString("destaque"));
                obj.setAtivo(rset.getString("ativo"));
                obj.setCategoriaid(rset.getLong("categorias_idcategorias"));
                obj.setNomecat(rset.getString("nomecat"));
                //adiciono o obj em uma lista 
                lista.add(obj);
            }
            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e) {
            lista = null;
            System.out.println(e.getMessage());
        }
        return lista;
    }
    /**
     * método para ser usado como JavaBean getProduto()
     */
    public List<Produto> getDestaques() {
        return buscar("destaque='s'");
    }
    
    
    /**
     * @return Categoria
     */
    public Produto buscarPorId(Long id) {
        Produto obj = new Produto();
        try {
            String sql = "select * from produtos where idprodutos = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                obj.setIdproduto(rset.getLong("idprodutos"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setMaisinfo(rset.getString("maisinfo"));
                obj.setPeso(rset.getFloat("peso"));
                obj.setValor(rset.getFloat("valor"));
                obj.setDestaque(rset.getString("destaque"));
                obj.setAtivo(rset.getString("ativo"));
                obj.setCategoriaid(rset.getLong("categorias_idcategorias"));
            }
            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e) {
            obj = null;
        }
        return obj;
    }
    
    
    /**
     * método para cadastrar um novo curso
     * @param obj 
     */
    public int inserir(Produto obj) {
        int res = 0;
        String nom = obj.getNome();
        String des = obj.getDescricao();
        String inf = obj.getMaisinfo();
        Float pes = obj.getPeso();
        Float val = obj.getValor();
        String dtq = obj.getDestaque();
        String ati = obj.getAtivo();
        Long cid = obj.getCategoriaid();
        String sql = "insert into produtos (nome, descricao, maisinfo, peso, valor, destaque, ativo, categorias_idcategorias) "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, inf);
            stmt.setFloat(4, pes);
            stmt.setFloat(5, val);
            stmt.setString(6, dtq);
            stmt.setString(7, ati);
            stmt.setLong(8, cid);
            res = stmt.executeUpdate();
            //fechar os objetos de manipulação do SGBD
            stmt.close();
            this.conn.close();
        } 
        catch(Exception e) {
            res = 0;
        }
        
        return res;
    }
    
    /**
     * método para atualizar um curso específico
     * @param obj 
     */
    public int atualizar(Produto obj) {
        int res = 0;
        Long idp = obj.getIdproduto();
        String nom = obj.getNome();
        String des = obj.getDescricao();
        String inf = obj.getMaisinfo();
        Float pes = obj.getPeso();
        Float val = obj.getValor();
        String dtq = obj.getDestaque();
        String ati = obj.getAtivo();
        Long cid = obj.getCategoriaid();
        String sql = "update produtos set nome=?, descricao=?, maisinfo=?, peso=?, valor=?, destaque=?, ativo=?, categorias_idcategorias=?"
                        + "where idprodutos=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, inf);
            stmt.setFloat(4, pes);
            stmt.setFloat(5, val);
            stmt.setString(6, dtq);
            stmt.setString(7, ati);
            stmt.setLong(8, cid);
            stmt.setLong(9, idp);
            res = stmt.executeUpdate();
            //fechar os objetos de manipulação do SGBD
            stmt.close();
            this.conn.close();
        } 
        catch(Exception e) {
            res = 0;
        }
        
        return res;
    }
    
    /** 
     * método para excluir um curso específico
     * @param obj
     */
    public int excluir(Produto obj) {
        int res = 0;
        Long idp = obj.getIdproduto();
        String sql = "delete from produtos where idprodutos=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, idp);
            res = stmt.executeUpdate();
            //fechar os objetos de manipulação do SGBD
            stmt.close();
            this.conn.close();
        } 
        catch(Exception e) {
            res = 0;
        }
        
        return res;
    }
}
