package com.comunidade.model;

import java.util.Date;

public class Informacao {
    private int id;
    private String titulo;
    private String conteudo;
    private Date dataPublicacao;
    private String autor;
    private String categoria;

    public Informacao(String titulo, String conteudo, String autor, String categoria) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublicacao = new Date();
        this.autor = autor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Informacao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
