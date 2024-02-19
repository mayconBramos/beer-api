package net.atos.beerapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.atos.beerapi.model.Beer;

public interface IBeerController {

	@Operation(summary = "Obter todas as cervejas", description = "Retorna uma lista de todas as cervejas disponíveis.")
    @ApiResponse(responseCode = "200", description = "Lista de cervejas", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	ResponseEntity<List<Beer>> getAllBeer(@RequestHeader("Authorization") String token);

    @Operation(summary = "Obter cerveja por ID", description = "Retorna uma cerveja com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Cerveja encontrada", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    Beer getBeerById(@PathVariable Long id);

    @Operation(summary = "Salvar cerveja", description = "Salva uma nova cerveja.")
    @ApiResponse(responseCode = "200", description = "Cerveja salva com sucesso", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    Beer saveBeer(@RequestBody Beer beer);

    @Operation(summary = "Excluir cerveja por ID", description = "Exclui uma cerveja com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Cerveja excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    void deleteBeer(@PathVariable Long id);

    @Operation(summary = "Obter cervejas por tipo", description = "Retorna uma lista de cervejas do tipo especificado.")
    @ApiResponse(responseCode = "200", description = "Lista de cervejas por tipo", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    List<Beer> getBeersByType(@PathVariable String type);

    @Operation(summary = "Obter cervejas fabricadas em uma data específica", description = "Retorna uma lista de cervejas fabricadas na data especificada.")
    @ApiResponse(responseCode = "200", description = "Lista de cervejas fabricadas na data", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    List<Beer> getBeersManufacturedOn(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manufacturingDate);

    @Operation(summary = "Obter cervejas com validade antes de uma data específica", description = "Retorna uma lista de cervejas com validade antes da data especificada.")
    @ApiResponse(responseCode = "200", description = "Lista de cervejas com validade antes da data", content = @Content(schema = @Schema(implementation = Beer.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    List<Beer> getBeersExpiringBefore(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate);
}
