package com.comunidade.service;

import com.comunidade.dao.MoradorDAO;
import com.comunidade.model.Morador;

import java.sql.SQLException;
import java.util.List;

public class MoradorService {
    private MoradorDAO moradorDAO;

    public MoradorService() {
        this.moradorDAO = new MoradorDAO();
    }

    public void cadastrarMorador(Morador morador) throws SQLException {
        // Aqui você pode adicionar validações ou lógica de negócios antes de cadastrar
        if (morador.getNome() == null || morador.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do morador não pode ser vazio");
        }
        moradorDAO.inserir(morador);
    }

    public Morador buscarMoradorPorId(int id) throws SQLException {
        return moradorDAO.buscarPorId(id);
    }

    public List<Morador> listarTodosMoradores() throws SQLException {
        return moradorDAO.listarTodos();
    }

    public void atualizarMorador(Morador morador) throws SQLException {
        // Aqui você pode adicionar validações ou lógica de negócios antes de atualizar
        if (morador.getId() <= 0) {
            throw new IllegalArgumentException("ID do morador inválido");
        }
        moradorDAO.atualizar(morador);
    }

    public void deletarMorador(int id) throws SQLException {
        // Aqui você pode adicionar verificações antes de deletar
        // Por exemplo, verificar se o morador não tem demandas associadas
        moradorDAO.deletar(id);
    }
}
