package com.academy.gama.projeto.adocao.controller;

import com.academy.gama.projeto.adocao.dto.AdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.AdopterWithPreferencesDTO;
import com.academy.gama.projeto.adocao.service.AdopterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/adotantes")
public class AdopterController {

    AdopterService service;

    public AdopterController(AdopterService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Inserir um novo adotante", notes = "Inserir adotante")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Adotante cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 409, message = "Adotante já cadastrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<AdopterResponseDTO> insert(@RequestBody AdopterWithPreferencesDTO dto) {
        AdopterResponseDTO adopter = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(adopter.getCpf())
                .toUri();

        return ResponseEntity.created(uri).body(adopter);
    }

    @GetMapping
    @ApiOperation(value = "Consultar todos os adotantes", notes = "Consultar adotantes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Adotantes listados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<List<AdopterResponseDTO>> list() {
        List<AdopterResponseDTO> list = service.list();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{cpf}")
    @ApiOperation(value = "Consultar adotante por CPF", notes = "Consulta adotante por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Adotante retornado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "CPF do adotante não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<AdopterResponseDTO> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(service.getAdopterByCpf(cpf));
    }

    @PutMapping("/{cpf}")
    @ApiOperation(value = "Alterar dados do adotante por CPF", notes = "Alteração de dados do adotante por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Adotante alterado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "CPF do adotante não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<AdopterResponseDTO> updateByCpf(@PathVariable String cpf, @RequestBody AdopterRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateByCpf(cpf, dto));
    }

    @DeleteMapping("/{cpf}")
    @ApiOperation(value = "Excluir adotante por CPF", notes = "Exclusão de dados do adotante por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT - Adotante excluído com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "CPF do adotante não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<String> deleteById(@PathVariable String cpf) {
        service.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
