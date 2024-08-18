package Projeto.movimentacao;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categoria {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_categoria;
    
    private String descricao;

    // Construtor sem parâmetros (necessário para JPA)
    public Categoria() {
    }

    // Construtor com descrição
    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    // Construtor completo
    public Categoria(int pk_categoria, String descricao) {
        this.pk_categoria = pk_categoria;
        this.descricao = descricao;
    }

    public int getPk_categoria() {
        return pk_categoria;
    }

    public void setPk_categoria(int pk_categoria) {
        this.pk_categoria = pk_categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria other = (Categoria) obj;
        return pk_categoria == other.pk_categoria && Objects.equals(descricao, other.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_categoria, descricao);
    }

    @Override
    public String toString() {
        return String.format("Categoria [pk_categoria=%d, descricao=%s]", pk_categoria, descricao);
    }
}
