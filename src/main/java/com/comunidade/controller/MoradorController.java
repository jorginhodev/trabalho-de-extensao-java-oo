package com.comunidade.controller;

import com.comunidade.model.Morador;
import com.comunidade.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moradores")
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    @GetMapping
    public String listarMoradores(Model model) {
        model.addAttribute("moradores", moradorRepository.findAll());
        return "listar-moradores";
    }

    @GetMapping("/novo")
    public String formularioNovoMorador(Model model) {
        model.addAttribute("morador", new Morador());
        return "formulario-morador";
    }

    @PostMapping
    public String salvarMorador(@ModelAttribute Morador morador) {
        moradorRepository.save(morador);
        return "redirect:/moradores";
    }
}
