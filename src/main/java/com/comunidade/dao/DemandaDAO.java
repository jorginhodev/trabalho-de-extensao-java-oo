package com.comunidade.dao;

import com.comunidade.model.Demanda;
import com.comunidade.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandaDAO {

    public void inserir(Demanda demanda) throws SQLException {
        String sql = "INSERT INTO demandas (titulo, descricao, status, dataCriacao, moradorId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, demanda.getTitulo());
            pstmt.setString(2, demanda.getDescricao());
            pstmt.setString(3, demanda.getStatus());
            pstmt.setTimestamp(4, new Timestamp(demanda.getDataCriacao().getTime()));
            pstmt.setInt(5, demanda.getMoradorId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar demanda, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    demanda.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar demanda, nenhum ID obtido.");
                }
            }
        }
    }

    public Demanda buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM demandas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extrairDemandaDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Demanda> listarTodos() throws SQLException {
        List<Demanda> demandas = new ArrayList<>();
        String sql = "SELECT * FROM demandas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                demandas.add(extrairDemandaDoResultSet(rs));
            }
        }
        return demandas;
    }

    public void atualizar(Demanda demanda) throws SQLException {
        String sql = "UPDATE demandas SET titulo = ?, descricao = ?, status = ?, dataCriacao = ?, moradorId = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, demanda.getTitulo());
            pstmt.setString(2, demanda.getDescricao());
            pstmt.setString(3, demanda.getStatus());
            pstmt.setTimestamp(4, new Timestamp(demanda.getDataCriacao().getTime()));
            pstmt.setInt(5, demanda.getMoradorId());
            pstmt.setInt(6, demanda.getId());

            pstmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM demandas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Demanda extrairDemandaDoResultSet(ResultSet rs) throws SQLException {
        Demanda demanda = new Demanda(
                rs.getString("titulo"),
                rs.getString("descricao"),
                rs.getInt("moradorId")
        );
        demanda.setId(rs.getInt("id"));
        demanda.setStatus(rs.getString("status"));
        demanda.setDataCriacao(rs.getTimestamp("dataCriacao"));
        return demanda;
    }
}
