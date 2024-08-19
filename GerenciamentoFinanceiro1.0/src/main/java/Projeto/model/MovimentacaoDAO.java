package Projeto.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        String sql = "INSERT INTO movimentacao(data, descricao, valor, fk_categoria, tipo) VALUES(?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                movimentacao.getData(),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getCategoria().getPk_categoria(),
                movimentacao.getTipo() // Incluindo o campo tipo
            );
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para atualizar uma movimentação existente
    public boolean update(Movimentacao movimentacao) {
        String sql = "UPDATE movimentacao SET data = ?, descricao = ?, valor = ?, fk_categoria = ?, tipo = ? WHERE pk_movimentacao = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                movimentacao.getData(),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getCategoria().getPk_categoria(),
                movimentacao.getTipo(), // Incluindo o campo tipo
                movimentacao.getPk_movimentacao()
            );
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
    @SuppressWarnings("deprecation")
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
    @SuppressWarnings("deprecation")
	public List<Movimentacao> retrieveByDate(LocalDate data) {
        String sql = "SELECT * FROM movimentacao WHERE data = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{data}, new MovimentacaoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Implementação do RowMapper para mapear os resultados do ResultSet para a classe Movimentacao
    private static class MovimentacaoRowMapper implements RowMapper<Movimentacao> {
        @Override
        public Movimentacao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setPk_movimentacao(rs.getInt("pk_movimentacao"));
            movimentacao.setData(rs.getObject("data", LocalDate.class));
            movimentacao.setDescricao(rs.getString("descricao"));
            movimentacao.setValor(rs.getDouble("valor"));
            Categoria categoria = new Categoria();
            categoria.setPk_categoria(rs.getInt("fk_categoria"));
            movimentacao.setCategoria(categoria);
            movimentacao.setTipo(rs.getString("tipo")); // Incluindo o campo tipo
            return movimentacao;
        }
    }
}
