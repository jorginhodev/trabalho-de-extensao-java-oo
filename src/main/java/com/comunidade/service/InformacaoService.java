package com.comunidade.service;

import com.comunidade.dao.InformacaoDAO;
import com.comunidade.model.Informacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class InformacaoService {
    private InformacaoDAO informacaoDAO;

    public InformacaoService() {
        this.informacaoDAO = new InformacaoDAO();
    }

    public void publicarInformacao(Informacao informacao) throws SQLException {
        if (informacao.getTitulo() == null || informacao.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título da informação não pode ser vazio");
        }
        if (informacao.getConteudo() == null || informacao.getConteudo().trim().isEmpty()) {
            throw new IllegalArgumentException("Conteúdo da informação não pode ser vazio");
        }
        informacao.setDataPublicacao(new Date());
        informacaoDAO.inserir(informacao);
    }

    public Informacao buscarInformacaoPorId(int id) throws SQLException {
        return informacaoDAO.buscarPorId(id);
    }

    public List<Informacao> listarTodasInformacoes() throws SQLException {
        return informacaoDAO.listarTodos();
    }

    public void atualizarInformacao(Informacao informacao) throws SQLException {
        if (informacao.getId() <= 0) {
            throw new IllegalArgumentException("ID da informação inválido");
        }
        informacaoDAO.atualizar(informacao);
    }

    public void deletarInformacao(int id) throws SQLException {
        // Aqui você pode adicionar verificações antes de deletar
        // Por exemplo, verificar se a informação não é muito antiga para ser deletada
        informacaoDAO.deletar(id);
    }

    public List<Informacao> buscarInformacoesPorCategoria(String categoria) throws SQLException {
        // Este método requer uma implementação adicional no InformacaoDAO
        // Você pode adicionar um método similar no InformacaoDAO para buscar por categoria
        throw new UnsupportedOperationException("Método ainda não implementado");
    }
}
