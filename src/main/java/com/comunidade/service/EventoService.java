package com.comunidade.service;

import com.comunidade.dao.EventoDAO;
import com.comunidade.model.Evento;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class EventoService {
    private EventoDAO eventoDAO;

    public EventoService() {
        this.eventoDAO = new EventoDAO();
    }

    public void cadastrarEvento(Evento evento) throws SQLException {
        if (evento.getTitulo() == null || evento.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do evento não pode ser vazio");
        }
        if (evento.getData() == null || evento.getData().before(new Date())) {
            throw new IllegalArgumentException("Data do evento deve ser futura");
        }
        eventoDAO.inserir(evento);
    }

    public Evento buscarEventoPorId(int id) throws SQLException {
        return eventoDAO.buscarPorId(id);
    }

    public List<Evento> listarTodosEventos() throws SQLException {
        return eventoDAO.listarTodos();
    }

    public void atualizarEvento(Evento evento) throws SQLException {
        if (evento.getId() <= 0) {
            throw new IllegalArgumentException("ID do evento inválido");
        }
        eventoDAO.atualizar(evento);
    }

    public void deletarEvento(int id) throws SQLException {
        // Aqui você pode adicionar verificações antes de deletar
        // Por exemplo, verificar se o evento já não ocorreu
        eventoDAO.deletar(id);
    }
}
