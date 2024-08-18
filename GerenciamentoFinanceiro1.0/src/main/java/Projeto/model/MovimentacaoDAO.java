package Projeto.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Projeto.movimentacao.Categoria;
import Projeto.movimentacao.Movimentacao;

@Repository
public class MovimentacaoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para criar uma nova movimentação
    public boolean create(Movimentacao movimentacao) {
        String sql = "INSERT INTO movimentacao(fk_categoria, descricao, valor, tipo, data_movimentacao) VALUES(?, ?, ?, ?, ?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql, 
                movimentacao.getCategoria().getPk_categoria(), 
                movimentacao.getDescricao(), 
                movimentacao.getValor(), 
                movimentacao.getTipo(), 
                new java.sql.Date(movimentacao.getData().getTime()));
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para atualizar uma movimentação existente
    public boolean update(Movimentacao movimentacao) {
        String sql = "UPDATE movimentacao SET fk_categoria = ?, descricao = ?, valor = ?, tipo = ?, data_movimentacao = ? WHERE pk_movimentacao = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, 
                movimentacao.getCategoria().getPk_categoria(),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getTipo(),
                new java.sql.Date(movimentacao.getData().getTime()), 
                movimentacao.getPk_movimentacao());
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar uma movimentação pelo ID
    public boolean delete(int pk_movimentacao) {
        String sql = "DELETE FROM movimentacao WHERE pk_movimentacao = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, pk_movimentacao);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para recuperar uma movimentação pelo ID
    public Movimentacao retrieve(int pk_movimentacao) {
        String sql = "SELECT * FROM movimentacao WHERE pk_movimentacao = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{pk_movimentacao}, new MovimentacaoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para recuperar todas as movimentações
    public List<Movimentacao> retrieveAll() {
        String sql = "SELECT * FROM movimentacao";

        try {
            return jdbcTemplate.query(sql, new MovimentacaoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para recuperar movimentações por data
    public List<Movimentacao> retrieveByDate(Date data) {
        String sql = "SELECT * FROM movimentacao WHERE data_movimentacao = ?";
        
        try {
            return jdbcTemplate.query(sql, new Object[]{new java.sql.Date(data.getTime())}, new MovimentacaoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Implementação do RowMapper para mapear os resultados do ResultSet para a classe Movimentacao
   /* private static class MovimentacaoRowMapper implements RowMapper<Movimentacao> {
        @Override
        public Movimentacao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categoria categoria = new Categoria(); // Aqui você precisa obter a categoria correspondente
            categoria.setPk_categoria(rs.getInt("fk_categoria"));

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setPk_movimentacao(rs.getInt("pk_movimentacao"));
            movimentacao.setCategoria(categoria);
            movimentacao.setDescricao(rs.getString("descricao"));
            movimentacao.setValor(rs.getDouble("valor"));
            movimentacao.setTipo(rs.getString("tipo"));
            movimentacao.setData(rs.getDate("data_movimentacao"));

            return movimentacao;
        }
    }*/
    
    public List<Movimentacao> retrieveByDate(LocalDate data) {
        String sql = "SELECT * FROM movimentacao WHERE data_movimentacao = ?";
        
        try {
            return jdbcTemplate.query(sql, new Object[]{Date.valueOf(data)}, new MovimentacaoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Implementação do RowMapper para mapear os resultados do ResultSet para a classe Movimentacao
    private static class MovimentacaoRowMapper implements RowMapper<Movimentacao> {
        @Override
        public Movimentacao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categoria categoria = new Categoria(); // Aqui você precisa obter a categoria correspondente
            categoria.setPk_categoria(rs.getInt("fk_categoria"));

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setPk_movimentacao(rs.getInt("pk_movimentacao"));
            movimentacao.setCategoria(categoria);
            movimentacao.setDescricao(rs.getString("descricao"));
            movimentacao.setValor(rs.getDouble("valor"));
            movimentacao.setTipo(rs.getString("tipo"));
            movimentacao.setData(rs.getDate("data_movimentacao").toLocalDate());

            return movimentacao;
        }
    }
}
