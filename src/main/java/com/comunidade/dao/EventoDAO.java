package com.comunidade.dao;

import com.comunidade.model.Evento;
import com.comunidade.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public void inserir(Evento evento) throws SQLException {
        String sql = "INSERT INTO eventos (titulo, data, local, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, evento.getTitulo());
            pstmt.setTimestamp(2, new Timestamp(evento.getData().getTime()));
            pstmt.setString(3, evento.getLocal());
            pstmt.setString(4, evento.getDescricao());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar evento, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    evento.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar evento, nenhum ID obtido.");
                }
            }
        }
    }

    public Evento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM eventos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extrairEventoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Evento> listarTodos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                eventos.add(extrairEventoDoResultSet(rs));
            }
        }
        return eventos;
    }

    public void atualizar(Evento evento) throws SQLException {
        String sql = "UPDATE eventos SET titulo = ?, data = ?, local = ?, descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, evento.getTitulo());
            pstmt.setTimestamp(2, new Timestamp(evento.getData().getTime()));
            pstmt.setString(3, evento.getLocal());
            pstmt.setString(4, evento.getDescricao());
            pstmt.setInt(5, evento.getId());

            pstmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Evento extrairEventoDoResultSet(ResultSet rs) throws SQLException {
        Evento evento = new Evento(
                rs.getString("titulo"),
                rs.getTimestamp("data"),
                rs.getString("local"),
                rs.getString("descricao")
        );
        evento.setId(rs.getInt("id"));
        return evento;
    }
}
