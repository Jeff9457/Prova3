package com.example.demo2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo2.model.Musica;
import com.example.demo2.compartilhado.MusicaDto;

public interface MusicaRepository extends MongoRepository<Musica, String> {

    MusicaDto save(MusicaDto dto);
    //ressalva:
    //insert > save
    //find > findAll
    //métodos vão mudar de nome aqui dentro da nossa aplicação
    //porém, vão continuar fazendo a mesma coisa que faziam dentro do Mongo
    //findByBlablabla
    //@Query()
    
}
