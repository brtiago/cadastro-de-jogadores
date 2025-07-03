package com.example.cadastro_de_jogadores.model.dto.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "liga_da_justica")
public record LigaDaJusticaResponse(
        @JacksonXmlProperty(localName = "codinome") List<String> codinomes
) {
    public List<String> codinomes() {
        return codinomes;
    }
}
