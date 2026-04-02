package br.com.senac.api.controllers;

import br.com.senac.api.dtos.PessoasRequestDTO;
import br.com.senac.api.entidades.Pessoas;
import br.com.senac.api.repositorios.pessoaRepositorio;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/pessoas")
public class PessoasController {

    private pessoaRepositorio pessoaRepositorio;

    public PessoasController(pessoaRepositorio pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar(){
        List<Pessoas> pessoasList = pessoaRepositorio.findAll();
        if (pessoasList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return  ResponseEntity.ok(pessoasList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoas> criar(
            @RequestBody PessoasRequestDTO pessoas){

        Pessoas pessoaPersist = new Pessoas();
        pessoaPersist.setNome(pessoas.getNome());
        pessoaPersist.setSobrenome(pessoas.getSobrenome());

        Pessoas retorno = pessoaRepositorio.save(pessoaPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoas> atualizar(
           @RequestBody PessoasRequestDTO pessoas, @PathVariable Long id){

        if (pessoaRepositorio.existsById(id)){
            Pessoas pessoaPersist = new Pessoas();
            pessoaPersist.setNome(pessoas.getNome());
            pessoaPersist.setSobrenome(pessoas.getSobrenome());
            pessoaPersist.setId(id);

            Pessoas retorno = pessoaRepositorio.save(pessoaPersist);

            return ResponseEntity.ok(retorno);
        }
        return  ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Pessoas> deletar(
            @PathVariable Long id){
        if (pessoaRepositorio.existsById(id)){
            pessoaRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return  ResponseEntity.badRequest().body(null);
    }

}
