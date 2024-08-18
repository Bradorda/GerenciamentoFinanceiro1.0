package Projeto.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Projeto.movimentacao.Categoria;

@Repository
public class CategoriaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para criar uma nova categoria
    public boolean create(Categoria categoria) {
        String sql = "INSERT INTO categoria(descricao) VALUES(?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql, categoria.getDescricao());
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para atualizar uma categoria existente
    public boolean update(Categoria categoria) {
        String sql = "UPDATE categoria SET descricao = ? WHERE pk_categoria = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, categoria.getDescricao(), categoria.getPk_categoria());
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar uma categoria pelo ID
    public boolean delete(int pk_categoria) {
        String sql = "DELETE FROM categoria WHERE pk_categoria = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, pk_categoria);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para recuperar uma categoria pelo ID
    public Categoria retrieve(int pk_categoria) {
        String sql = "SELECT * FROM categoria WHERE pk_categoria = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{pk_categoria}, new CategoriaRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para recuperar todas as categorias
    public List<Categoria> retrieveAll() {
        String sql = "SELECT * FROM categoria";

        try {
            return jdbcTemplate.query(sql, new CategoriaRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Implementação do RowMapper para mapear os resultados do ResultSet para a classe Categoria
    private static class CategoriaRowMapper implements RowMapper<Categoria> {
        @Override
        public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categoria categoria = new Categoria();
            categoria.setPk_categoria(rs.getInt("pk_categoria"));
            categoria.setDescricao(rs.getString("descricao"));
            return categoria;
        }
    }
}
