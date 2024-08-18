package Projeto.movimentacao;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimentacao {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_movimentacao;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;

    private String descricao;
    private double valor;
    private String tipo;
    private LocalDate data;

    // Construtor padrão
    public Movimentacao() {
    }

    // Construtor com campos essenciais
    public Movimentacao(Categoria categoria, String descricao, double valor, String tipo, LocalDate data) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    // Construtor com chave primária
    public Movimentacao(int pk_movimentacao, Categoria categoria, String descricao, double valor, String tipo, LocalDate data) {
        this.pk_movimentacao = pk_movimentacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    // Getters e Setters
    public int getPk_movimentacao() {
        return pk_movimentacao;
    }

    public void setPk_movimentacao(int pk_movimentacao) {
        this.pk_movimentacao = pk_movimentacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, data, descricao, pk_movimentacao, tipo, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao other = (Movimentacao) obj;
        return pk_movimentacao == other.pk_movimentacao &&
               Double.compare(other.valor, valor) == 0 &&
               Objects.equals(categoria, other.categoria) &&
               Objects.equals(descricao, other.descricao) &&
               Objects.equals(tipo, other.tipo) &&
               Objects.equals(data, other.data);
    }

    @Override
    public String toString() {
        return String.format("Movimentacao [pk_movimentacao=%d, categoria=%s, descricao=%s, valor=%.2f, tipo=%s, data=%s]",
                             pk_movimentacao, categoria, descricao, valor, tipo, data);
    }
}
