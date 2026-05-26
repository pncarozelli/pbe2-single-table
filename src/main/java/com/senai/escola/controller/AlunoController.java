package com.senai.escola.controller;

import com.senai.escola.entity.Aluno;
import com.senai.escola.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno") //este é nosso endpoint
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public void adicionar(@RequestBody Aluno aluno) {
        this.alunoService.adicionar(aluno);
    }

    @GetMapping
    public List<Aluno> listar(){
        return this.alunoService.listar();
    }

    @PutMapping("/{id}")
    // os { } (chaves) indicam que uma variável será enviada na URL (chamada de path variable)
    // e deve ser capturada utilizando @PathVariable e o Spring injeta o valor passado em {id}
    // no parâmetro id do método
    public void atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        this.alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.alunoService.remover(id);
    }


}
