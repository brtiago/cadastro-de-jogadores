package com.example.cadastro_de_jogadores.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerYamlController {

    @GetMapping("/api/swagger.yaml")
    public ResponseEntity<Resource> getSwaggerYaml() {
        Resource swaggerYaml = new ClassPathResource("api/swagger.yaml");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/yaml"))
                .body(swaggerYaml);
    }
}