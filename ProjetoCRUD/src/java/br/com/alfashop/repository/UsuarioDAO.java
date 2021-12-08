package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Usuario;
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
public class UsuarioDAO 
{
    Connection conn;
    
    /**
     * Construtor da classe
     * sempre que criar um objeto, chama esse método
     */
    public UsuarioDAO() {
        this.conn = Conex.getConnection();
    }
    
    
    /**
     * @return List<Usuario> 
     */
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select * from usuarios";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Usuario obj = new Usuario();
                obj.setIdusuario(rset.getLong("idusuarios"));
                obj.setNome(rset.getString("nome"));
                obj.setEmail(rset.getString("email"));
                obj.setSenha(rset.getString("senha"));
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
     * método para ser usado como JavaBean getUsuario()
     */
    public List<Usuario> getUsuarios() {
        return listar();
    }
    
    
    /**
     * @return Usuario
     */
    public List<Usuario> buscar(String filtro) {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "select * from usuarios where "+filtro;
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                Usuario obj = new Usuario();
                obj.setIdusuario(rset.getLong("idusuarios"));
                obj.setNome(rset.getString("nome"));
                obj.setEmail(rset.getString("email"));
                obj.setSenha(rset.getString("senha"));
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
     * @return Usuario
     */
    public List<Usuario> validar(String email, String senha) {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            String sql = "select * from usuarios where email=? and senha=?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                Usuario obj = new Usuario();
                obj.setIdusuario(rset.getLong("idusuarios"));
                obj.setNome(rset.getString("nome"));
                obj.setEmail(rset.getString("email"));
                obj.setSenha(rset.getString("senha"));
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
     * método para cadastrar um novo curso
     * @param obj 
     */
    public int inserir(Usuario obj) {
        int res = 0;
        String nom = obj.getNome();
        //String des = obj.getDescricao();
        //Double val = obj.getValor();
        String sql = "insert into curso (nome, descricao, valor) values (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            //stmt.setString(2, des);
            //stmt.setDouble(3, val);
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
    public int atualizar(Usuario obj) {
        int res = 0;
        //Long cod = obj.getCodigo();
        String nom = obj.getNome();
        //String des = obj.getDescricao();
        //Double val = obj.getValor();
        String sql = "update curso set nome=?, descricao=?, valor=? where codigo=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            //stmt.setString(2, des);
            //stmt.setDouble(3, val);
            //stmt.setLong(4, cod);
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
    public int excluir(Usuario obj) {
        int res = 0;
        //Long cod = obj.getCodigo();
        String sql = "delete from curso where codigo=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            //stmt.setLong(1, cod);
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
