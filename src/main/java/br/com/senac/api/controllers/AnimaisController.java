package br.com.senac.api.controllers;

import br.com.senac.api.dtos.AnimaisRequestDTO;
import br.com.senac.api.entidades.Animais;
import br.com.senac.api.entidades.Pessoas;
import br.com.senac.api.repositorios.animaisRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@CrossOrigin
@RequestMapping("/animais")
public class AnimaisController {
    private animaisRepositorio animaisRepositorio;

    public AnimaisController(animaisRepositorio animaisRepositorio) {
        this.animaisRepositorio = animaisRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Animais>> listar(){
        List<Animais> animaisList = animaisRepositorio.findAll();

        if (animaisList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return  ok(animaisList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Animais> criar(
            @RequestBody AnimaisRequestDTO Animais) {

        Animais animalPersist = new Animais();
        animalPersist.setNome(Animais.getNome());
        animalPersist.setRaca(Animais.getRaca());
        animalPersist.setPeso(Animais.getPeso());

        Animais retorno = animaisRepositorio.save(animalPersist);


        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar(
            @RequestBody AnimaisRequestDTO Animais, @PathVariable Long id) {

       if(animaisRepositorio.existsById(id)){
           Animais animalPersist = new Animais();
           animalPersist.setNome(Animais.getNome());
           animalPersist.setRaca(Animais.getRaca());
           animalPersist.setPeso(Animais.getPeso());
           animalPersist.setId(id);

           Animais retorno = animaisRepositorio.save(animalPersist);

           return ResponseEntity.ok(retorno);
       }
       return ResponseEntity.badRequest().body(null);
 }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Pessoas> deletar(
            @PathVariable Long id){
        if (animaisRepositorio.existsById(id)){
            animaisRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return  ResponseEntity.badRequest().body(null);
    }
}

