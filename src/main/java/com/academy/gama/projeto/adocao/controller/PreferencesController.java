package com.academy.gama.projeto.adocao.controller;

import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterResponseDTO;
import com.academy.gama.projeto.adocao.service.PreferencesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/preferencias")
public class PreferencesController {

    PreferencesService service;

    public PreferencesController(PreferencesService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Inserir novas preferências", notes = "Inserir preferência")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Preferência cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 409, message = "Preferência já cadastrada"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<PrefsWithAdopterResponseDTO> insert(@RequestBody PrefsWithAdopterRequestDTO dto) {
        PrefsWithAdopterResponseDTO prefs = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(prefs.getCpf())
                .toUri();

        return ResponseEntity.created(uri).body(prefs);
    }

    @GetMapping
    @ApiOperation(value = "Consultar todas as preferências", notes = "Consultar preferências")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Preferências listadas com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<List<PrefsWithAdopterResponseDTO>> list() {
        List<PrefsWithAdopterResponseDTO> list = service.list();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/cpf/{cpf}")
    @ApiOperation(value = "Consultar preferências por CPF do adotante", notes = "Consulta preferências de adotante por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Preferências do adotante retornadas com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "CPF do adotante não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<List<PrefsWithAdopterResponseDTO>> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(service.getByAdopterCpf(cpf));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar preferências por ID da preferência", notes = "Consulta preferências de adotante por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Preferências do adotante retornadas com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "ID da preferência não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<PrefsWithAdopterResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar dados das preferências por ID", notes = "Alteração de dados das preferências por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Preferências alteradas com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "ID da preferência não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<PrefsWithAdopterResponseDTO> updateById(@PathVariable String id,
                                                                  @RequestBody PrefsWithAdopterRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir preferências por ID", notes = "Exclusão de dados das preferências por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT - Preferências excluídas com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "ID da preferência não encontrado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
