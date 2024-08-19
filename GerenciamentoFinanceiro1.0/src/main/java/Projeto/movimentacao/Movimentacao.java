package Projeto.movimentacao;

import java.time.LocalDate;

public class Movimentacao {

    private int pk_movimentacao;
    private LocalDate data;
    private String descricao;
    private double valor;
    private Categoria categoria;
    private String tipo; // Campo obrigatório

    // Getters e Setters

    public int getPk_movimentacao() {
        return pk_movimentacao;
    }

    public void setPk_movimentacao(int pk_movimentacao) {
        this.pk_movimentacao = pk_movimentacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
