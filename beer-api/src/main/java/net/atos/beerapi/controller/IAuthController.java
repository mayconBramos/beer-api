package net.atos.beerapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface IAuthController {

    @Operation(summary = "Gerar Token de Autenticação", description = "Gera um token de autenticação.")
    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    ResponseEntity<String> generateToken();
}