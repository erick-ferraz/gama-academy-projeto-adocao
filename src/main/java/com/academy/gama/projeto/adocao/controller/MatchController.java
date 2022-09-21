package com.academy.gama.projeto.adocao.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping
    @ApiOperation(value = "Consultar todos os adotantes", notes = "Consultar adotantes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Adotantes listados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro não esperado no servidor")})

}
