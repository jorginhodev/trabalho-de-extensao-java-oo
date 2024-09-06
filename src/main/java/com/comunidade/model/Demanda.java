package com.comunidade.model;

import java.util.Date;

public class Demanda {
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private Date dataCriacao;
    private int moradorId;

    public Demanda(String titulo, String descricao, int moradorId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = "Aberta";
        this.dataCriacao = new Date();
        this.moradorId = moradorId;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(int moradorId) {
        this.moradorId = moradorId;
    }

    @Override
    public String toString() {
        return "Demanda{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", moradorId=" + moradorId +
                '}';
    }
}
