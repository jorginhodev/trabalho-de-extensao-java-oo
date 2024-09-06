package com.comunidade.controller;

import com.comunidade.model.Usuario;
import com.comunidade.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registerUserAccount(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            usuario.setRole("USER");
            usuarioService.registrarNovoUsuario(usuario);
        } catch (Exception e) {
            model.addAttribute("registroError", "Erro ao registrar usuário. O nome de usuário pode já estar em uso.");
            return "registro";
        }

        return "redirect:/login?registroSucesso";
    }
}
