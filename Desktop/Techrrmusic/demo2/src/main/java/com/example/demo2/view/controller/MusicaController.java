package com.example.demo2.view.controller;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo2.service.MusicaService;
import com.example.demo2.model.Musica;
import com.example.demo2.compartilhado.MusicaDto;
import org.modelmapper.ModelMapper;
import com.example.demo2.view.model.MusicaRequest;
import com.example.demo2.view.model.MusicaResponse;


@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    @Autowired
    private MusicaService servico;
    ModelMapper mapper = new ModelMapper();
       
    @PostMapping
    public ResponseEntity<MusicaResponse> criarMusica (@RequestBody @Valid MusicaRequest musicrequest){
        
        MusicaDto dtorequest = mapper.map(musicrequest, MusicaDto.class);
        MusicaDto dtoresponse = servico.criarMusica(dtorequest);
        MusicaResponse musicresponse = mapper.map(dtoresponse, MusicaResponse.class);

        return new ResponseEntity<>(musicresponse, HttpStatus.CREATED);
    }
    
        
    @GetMapping
    public ResponseEntity <List<MusicaResponse>> obterMusica(){
        List<MusicaDto> musdto = servico.obterTodos();
        List<MusicaResponse> musicresponse = musdto.stream().
        map(m -> mapper.map(m, MusicaResponse.class)).
        collect(Collectors.toList());

        return new ResponseEntity<>(musicresponse, HttpStatus.OK);        
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> obterPorId(@PathVariable String id){
        Optional<MusicaDto> musdto = servico.obterPorId(id);
        
        if(musdto.isPresent()) {
            MusicaResponse musicresponse = mapper.map(musdto.get(), MusicaResponse.class);
            return new ResponseEntity<>(musicresponse, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value="/{id}")
    public MusicaDto atualizarMusica(@PathVariable String id, @RequestBody MusicaDto musdto){
        return servico.atualizarMusica(id, musdto);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity <String> excluirMusica(@PathVariable String id) {
        servico.excluirMusica(id);
        return new ResponseEntity<>("Musica deletada com sucesso", HttpStatus.OK);
    }
    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.status(HttpStatus.OK).body(servico.contar());
    }    
}
