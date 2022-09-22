package com.academy.gama.projeto.adocao.controller;

import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.MatchDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.gama.projeto.adocao.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {
	
    MatchService service;

    public MatchController(MatchService service) {
        this.service = service;
    }
    
    @GetMapping("/{cpf}")
    @ApiOperation(value = "Consultar todos os adotantes", notes = "Consultar adotantes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Adotantes listados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})
    public ResponseEntity<MatchDto> matchByAdopterCpf(@PathVariable String cpf) {
        MatchDto dto = service.performMatch(cpf);
        return ResponseEntity.ok().body(dto);
    }

}
