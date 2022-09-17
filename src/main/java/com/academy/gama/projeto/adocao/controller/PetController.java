package com.academy.gama.projeto.adocao.controller;

import com.academy.gama.projeto.adocao.dto.AdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.model.entity.Pet;
import com.academy.gama.projeto.adocao.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService service;

    @GetMapping("/pet/{id}")
    public ResponseEntity<Pet> getPet(){
        Pet pet2 =  Pet.builder().name("carv").build();
        return ResponseEntity.ok(pet2);
    }

    @PostMapping
    @ApiOperation(value = "Inserir um novo pet", notes = "Inserir pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Pet cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 409, message = "Adotante já cadastrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<Pet> create(@RequestBody PetDto pet) {
        return ResponseEntity.ok().body(service.createPet(pet));
    }

    @GetMapping
    @ApiOperation(value = "Consultar todos os pets", notes = "Consultar pets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Pets listados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<List<PetResponseDto>> list() {
        return ResponseEntity.ok().body(service.list());
    }

    @GetMapping("/{type}")
    @ApiOperation(value = "Consultar pet pelo tipo", notes = "Consulta pet pelo tipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Pet retornado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Tipo de pet não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<List<PetResponseDto>> getByType(@PathVariable String tipo) {
        return ResponseEntity.ok().body(service.getPetByType(tipo));
    }

}
