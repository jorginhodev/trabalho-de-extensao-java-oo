package com.comunidade;

import com.comunidade.model.*;
import com.comunidade.service.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SistemaComunicacao {

    private MoradorService moradorService;
    private EventoService eventoService;
    private DemandaService demandaService;
    private InformacaoService informacaoService;

    public SistemaComunicacao() {
        moradorService = new MoradorService();
        eventoService = new EventoService();
        demandaService = new DemandaService();
        informacaoService = new InformacaoService();
    }

    public void executar() {
        try {
            // Cadastrar um morador
            Morador morador = new Morador("João Silva", "Rua das Flores, 123", "11999999999", "joao@email.com");
            moradorService.cadastrarMorador(morador);
            System.out.println("Morador cadastrado: " + morador);

            // Cadastrar um evento
            Evento evento = new Evento("Reunião de Moradores", new Date(System.currentTimeMillis() + 86400000), "Praça Central", "Discussão sobre melhorias no bairro");
            eventoService.cadastrarEvento(evento);
            System.out.println("Evento cadastrado: " + evento);

            // Cadastrar uma demanda
            Demanda demanda = new Demanda("Iluminação Pública", "Solicitar melhoria na iluminação da Rua das Flores", morador.getId());
            demandaService.cadastrarDemanda(demanda);
            System.out.println("Demanda cadastrada: " + demanda);

            // Publicar uma informação
            Informacao informacao = new Informacao("Coleta de Lixo", "Novo horário de coleta: segundas e quintas às 9h", "Prefeitura", "Serviços Públicos");
            informacaoService.publicarInformacao(informacao);
            System.out.println("Informação publicada: " + informacao);

            // Listar todos os moradores
            List<Morador> moradores = moradorService.listarTodosMoradores();
            System.out.println("Moradores cadastrados:");
            for (Morador m : moradores) {
                System.out.println(m);
            }

            // Listar todos os eventos
            List<Evento> eventos = eventoService.listarTodosEventos();
            System.out.println("Eventos cadastrados:");
            for (Evento e : eventos) {
                System.out.println(e);
            }

            // Atualizar status de uma demanda
            demandaService.atualizarStatusDemanda(demanda.getId(), "Em andamento");
            Demanda demandaAtualizada = demandaService.buscarDemandaPorId(demanda.getId());
            System.out.println("Demanda atualizada: " + demandaAtualizada);

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SistemaComunicacao sistema = new SistemaComunicacao();
        sistema.executar();
    }
}
