package com.comunidade.dao;

import com.comunidade.model.Informacao;
import com.comunidade.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformacaoDAO {

    public void inserir(Informacao informacao) throws SQLException {
        String sql = "INSERT INTO informacoes (titulo, conteudo, dataPublicacao, autor, categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, informacao.getTitulo());
            pstmt.setString(2, informacao.getConteudo());
            pstmt.setTimestamp(3, new Timestamp(informacao.getDataPublicacao().getTime()));
            pstmt.setString(4, informacao.getAutor());
            pstmt.setString(5, informacao.getCategoria());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar informação, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    informacao.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar informação, nenhum ID obtido.");
                }
            }
        }
    }

    public Informacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM informacoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extrairInformacaoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Informacao> listarTodos() throws SQLException {
        List<Informacao> informacoes = new ArrayList<>();
        String sql = "SELECT * FROM informacoes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                informacoes.add(extrairInformacaoDoResultSet(rs));
            }
        }
        return informacoes;
    }

    public void atualizar(Informacao informacao) throws SQLException {
        String sql = "UPDATE informacoes SET titulo = ?, conteudo = ?, dataPublicacao = ?, autor = ?, categoria = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, informacao.getTitulo());
            pstmt.setString(2, informacao.getConteudo());
            pstmt.setTimestamp(3, new Timestamp(informacao.getDataPublicacao().getTime()));
            pstmt.setString(4, informacao.getAutor());
            pstmt.setString(5, informacao.getCategoria());
            pstmt.setInt(6, informacao.getId());

            pstmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM informacoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Informacao extrairInformacaoDoResultSet(ResultSet rs) throws SQLException {
        Informacao informacao = new Informacao(
                rs.getString("titulo"),
                rs.getString("conteudo"),
                rs.getString("autor"),
                rs.getString("categoria")
        );
        informacao.setId(rs.getInt("id"));
        informacao.setDataPublicacao(rs.getTimestamp("dataPublicacao"));
        return informacao;
    }
}
