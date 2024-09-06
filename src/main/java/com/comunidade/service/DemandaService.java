package com.comunidade.service;

import com.comunidade.dao.DemandaDAO;
import com.comunidade.model.Demanda;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class DemandaService {
    private DemandaDAO demandaDAO;

    public DemandaService() {
        this.demandaDAO = new DemandaDAO();
    }

    public void cadastrarDemanda(Demanda demanda) throws SQLException {
        if (demanda.getTitulo() == null || demanda.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título da demanda não pode ser vazio");
        }
        if (demanda.getMoradorId() <= 0) {
            throw new IllegalArgumentException("ID do morador inválido");
        }
        // Define o status inicial e a data de criação
        demanda.setStatus("Aberta");
        demanda.setDataCriacao(new Date());
        demandaDAO.inserir(demanda);
    }

    public Demanda buscarDemandaPorId(int id) throws SQLException {
        return demandaDAO.buscarPorId(id);
    }

    public List<Demanda> listarTodasDemandas() throws SQLException {
        return demandaDAO.listarTodos();
    }

    public void atualizarDemanda(Demanda demanda) throws SQLException {
        if (demanda.getId() <= 0) {
            throw new IllegalArgumentException("ID da demanda inválido");
        }
        demandaDAO.atualizar(demanda);
    }

    public void deletarDemanda(int id) throws SQLException {
        // Aqui você pode adicionar verificações antes de deletar
        // Por exemplo, verificar se a demanda já não foi resolvida
        demandaDAO.deletar(id);
    }

    public void atualizarStatusDemanda(int id, String novoStatus) throws SQLException {
        Demanda demanda = demandaDAO.buscarPorId(id);
        if (demanda == null) {
            throw new IllegalArgumentException("Demanda não encontrada");
        }
        demanda.setStatus(novoStatus);
        demandaDAO.atualizar(demanda);
    }
}
