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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
        prefs.add(linkTo(methodOn(PreferencesController.class)
                .getById(String.valueOf(prefs.getId()))).withSelfRel());

        prefs.add(linkTo(methodOn(AdopterController.class)
                .getByCpf(prefs.getCpf())).withRel("adotante"));

        prefs.add(linkTo(methodOn(PreferencesController.class)
                .list()).withRel("lista de preferencias"));

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
        list.forEach(pref -> {
            pref.add(linkTo(methodOn(PreferencesController.class)
                    .getById(String.valueOf(pref.getId()))).withSelfRel());

            pref.add(linkTo(methodOn(AdopterController.class)
                    .getByCpf(pref.getCpf())).withRel("adotante"));
        });

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
        List<PrefsWithAdopterResponseDTO> prefsList = service.getByAdopterCpf(cpf);
        prefsList.forEach(pref -> {
            pref.add(linkTo(methodOn(PreferencesController.class)
                    .getById(String.valueOf(pref.getId()))).withSelfRel());

            pref.add(linkTo(methodOn(AdopterController.class)
                    .getByCpf(pref.getCpf())).withRel("adotante"));

            pref.add(linkTo(methodOn(PreferencesController.class)
                    .list()).withRel("lista de preferências"));
        });

        return ResponseEntity.ok().body(prefsList);
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
        PrefsWithAdopterResponseDTO prefs = service.getById(id);
        prefs.add(linkTo(methodOn(PreferencesController.class)
                .list()).withRel("Lista de preferências"));

        prefs.add(linkTo(methodOn(AdopterController.class)
                .getByCpf(prefs.getCpf())).withRel("adotante"));

        return ResponseEntity.ok().body(prefs);
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
        PrefsWithAdopterResponseDTO prefs = service.updateById(id, dto);
        prefs.add(linkTo(methodOn(PreferencesController.class)
                .getById(String.valueOf(prefs.getId()))).withSelfRel());

        prefs.add(linkTo(methodOn(PreferencesController.class)
                .list()).withRel("lista de preferências"));

        return ResponseEntity.ok().body(prefs);
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
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
