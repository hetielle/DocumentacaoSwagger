package com.ifsul.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsul.restaurante.model.Restaurante;
import com.ifsul.restaurante.service.RestauranteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/restaurantes")
@Tag(name = "Restaurante", description = "Métodos ligados a classe Restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "Retorna uma lista de restaurantes")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Retorna um array com os restaurantes encontrados", 
    		    content = { @Content(mediaType = "application/json", 
    		            array = @ArraySchema(schema = @Schema(implementation = Restaurante.class))) })
    })
    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @Operation(summary = "Retorna um restaurante específico")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Retorna o restaurante encontrado", 
    		    content = { @Content(mediaType = "application/json", 
    		        schema = @Schema(implementation = Restaurante.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um restaurante")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "201", description = "Cria um restaurante", 
    		    content = { @Content(mediaType = "application/json", 
    		        schema = @Schema(implementation = Restaurante.class)) })
    })
    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }

    @Operation(summary = "Atualiza um restaurante")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Atualiza um restaurante a partir de um ID e uma instância de restaurante", 
    		    content = { @Content(mediaType = "application/json", 
    		        schema = @Schema(implementation = Restaurante.class)) })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteExistente = restauranteService.buscarRestaurantePorId(id);
        if (restauranteExistente != null) {
            restaurante.setId(id);
            return ResponseEntity.ok(restauranteService.criarRestaurante(restaurante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um restaurante")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Deleta um restaurante a partir de um ID", 
    		    content = { @Content(mediaType = "application/json", 
    		            array = @ArraySchema(schema = @Schema(implementation = Restaurante.class))) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        Restaurante restauranteExistente = restauranteService.buscarRestaurantePorId(id);
        if (restauranteExistente != null) {
            restauranteService.deletarRestaurante(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Retorna uma lista de restaurantes")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Retorna um array com os restaurantes encontrados a partir de um nome", 
    		    content = { @Content(mediaType = "application/json", 
    		            array = @ArraySchema(schema = @Schema(implementation = Restaurante.class))) })
    })
    @GetMapping("/nome/{nome}")
    public List<Restaurante> buscarRestaurantesPorNome(@PathVariable String nome) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorNome(nome);
        return restaurantes;
    }

    @Operation(summary = "Retorna uma lista de restaurantes")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Retorna um array com os restaurantes encontrados a partir de um endereço", 
    		    content = { @Content(mediaType = "application/json", 
    		            array = @ArraySchema(schema = @Schema(implementation = Restaurante.class))) })
    })
    @GetMapping("/endereco/{endereco}")
    public List<Restaurante> buscarRestaurantesPorEndereco(@PathVariable String endereco) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorEndereco(endereco);
        return restaurantes;
    }

    @Operation(summary = "Retorna uma lista de restaurantes")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Retorna um array com os restaurantes encontrados a partir de um tipo de cozinha", 
    		    content = { @Content(mediaType = "application/json", 
    		            array = @ArraySchema(schema = @Schema(implementation = Restaurante.class))) })
    })
    @GetMapping("/cozinha/{cozinha}")
    public List<Restaurante> buscarRestaurantesPorTipoCozinha(@PathVariable String cozinha) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorTipoCozinha(cozinha);
        return restaurantes;
    }
}
