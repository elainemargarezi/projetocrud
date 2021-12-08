package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Categoria;
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
public class CategoriaDAO {
    
    Connection conn;
    
    /**
     * Construtor da classe
     * sempre que criar um objeto, chama esse método
     */
    public CategoriaDAO() {
        this.conn = Conex.getConnection();
    }
    
    
    /**
     * @return List<Categoria> 
     */
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select * from categorias";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Categoria obj = new Categoria();
                obj.setIdcategoria(rset.getLong("idcategorias"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setAtivo(rset.getString("ativo"));
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
     * método para ser usado como JavaBean getCategoria()
     */
    public List<Categoria> getCategorias() {
        return listar();
    }
    
    
    /**
     * @return Categoria
     */
    public List<Categoria> buscar(String filtro) {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select * from categorias where "+filtro;
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Categoria obj = new Categoria();
                obj.setIdcategoria(rset.getLong("idcategorias"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setAtivo(rset.getString("ativo"));
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
     * @return Categoria
     */
    public Categoria buscarPorId(Long id) {
        Categoria obj = new Categoria();
        try {
            String sql = "select * from categorias where idcategorias = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                obj.setIdcategoria(rset.getLong("idcategorias"));
                obj.setNome(rset.getString("nome"));
                obj.setDescricao(rset.getString("descricao"));
                obj.setAtivo(rset.getString("ativo"));
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
    public int inserir(Categoria obj) {
        int res = 0;
        String nom = obj.getNome();
        String des = obj.getDescricao();
        String ati = obj.getAtivo();
        String sql = "insert into categorias (nome, descricao, ativo) values (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, ati);
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
    public int atualizar(Categoria obj) {
        int res = 0;
        Long idc = obj.getIdcategoria();
        String nom = obj.getNome();
        String des = obj.getDescricao();
        String ati = obj.getAtivo();
        String sql = "update categorias set nome=?, descricao=?, ativo=? where idcategorias=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, ati);
            stmt.setLong(4, idc);
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
    public int excluir(Categoria obj) {
        int res = 0;
        Long idc = obj.getIdcategoria();
        String sql = "delete from categorias where idcategorias=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, idc);
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
