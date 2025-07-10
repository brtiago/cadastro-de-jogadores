package com.example.cadastro_de_jogadores.controller;

import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import com.example.cadastro_de_jogadores.model.TipoGrupo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("jogador", new JogadorRequest("", "", "", null));
        return "index";
    }
}