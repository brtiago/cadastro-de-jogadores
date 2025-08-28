package com.example.cadastro_de_jogadores.controller;

import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("jogador", new JogadorRequest("", "", "", null));
        return "index";
    }


}