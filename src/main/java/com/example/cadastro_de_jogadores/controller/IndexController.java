package com.example.cadastro_de_jogadores.controller;

import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import com.example.cadastro_de_jogadores.model.dto.JogadorResponse;
import com.example.cadastro_de_jogadores.service.JogadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final JogadorService jogadorService;

    public IndexController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("jogador", new JogadorRequest("", "", "", null));
        return "index";
    }

    @GetMapping("/jogadores")
    public String listarJogadores(Model model) {
        List<JogadorResponse> jogadores = jogadorService.listarTodos();
        model.addAttribute("jogadores", jogadores);
        return "JogadoresCadastrados";
    }

    @GetMapping("/jogadores/novo")
    public String novoJogador(Model model) {
        model.addAttribute("jogador", new JogadorRequest("", "", "", null));
        return "index";
    }
}