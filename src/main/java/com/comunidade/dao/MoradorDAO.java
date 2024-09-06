package com.comunidade.dao;

import com.comunidade.model.Morador;
import com.comunidade.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoradorDAO {

    public void inserir(Morador morador) throws SQLException {
        String sql = "INSERT INTO moradores (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, morador.getNome());
            pstmt.setString(2, morador.getEndereco());
            pstmt.setString(3, morador.getTelefone());
            pstmt.setString(4, morador.getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar morador, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    morador.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar morador, nenhum ID obtido.");
                }
            }
        }
    }

    public Morador buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM moradores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extrairMoradorDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Morador> listarTodos() throws SQLException {
        List<Morador> moradores = new ArrayList<>();
        String sql = "SELECT * FROM moradores";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                moradores.add(extrairMoradorDoResultSet(rs));
            }
        }
        return moradores;
    }

    public void atualizar(Morador morador) throws SQLException {
        String sql = "UPDATE moradores SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, morador.getNome());
            pstmt.setString(2, morador.getEndereco());
            pstmt.setString(3, morador.getTelefone());
            pstmt.setString(4, morador.getEmail());
            pstmt.setInt(5, morador.getId());

            pstmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM moradores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Morador extrairMoradorDoResultSet(ResultSet rs) throws SQLException {
        Morador morador = new Morador(
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("telefone"),
                rs.getString("email")
        );
        morador.setId(rs.getInt("id"));
        return morador;
    }
}
