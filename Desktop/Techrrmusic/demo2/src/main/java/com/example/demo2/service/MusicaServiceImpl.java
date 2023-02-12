package com.example.demo2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.compartilhado.MusicaDto;
import com.example.demo2.model.Musica;
import com.example.demo2.repository.MusicaRepository;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository repositorio;

    ModelMapper mapper = new ModelMapper();

    
    @Override
    public MusicaDto criarMusica(MusicaDto musDto) {
    
        Musica m = mapper.map(musDto, Musica.class); 
        m = repositorio.save(m);
        MusicaDto dto = mapper.map(m, MusicaDto.class);

        return dto;
    }
        
    @Override
    public List<MusicaDto> obterTodos() {
        List<Musica> mus = repositorio.findAll();
        List<MusicaDto> musdto = 
        mus.
        stream().
        map(m -> mapper.map(m, MusicaDto.class)).
        collect(Collectors.toList());
        
        return musdto;
    }
            
    @Override
    public Optional <MusicaDto> obterPorId(String id) {
        Optional<Musica> optmus = repositorio.findById(id);

        if(optmus.isPresent()) {
            MusicaDto musdto = mapper.map(optmus.get(), MusicaDto.class);
            return Optional.of(musdto);
        }

        return Optional.empty();
    }
    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musdto) {
        Musica m = mapper.map(musdto, Musica.class);
        m.setId(id);
        MusicaDto dto = mapper.map(m, MusicaDto.class);
        return repositorio.save(dto);
    }  
    @Override
    public void excluirMusica(String id) {
        repositorio.deleteById(id);
    }
    @Override
    public Long contar() {
        return repositorio.count();
    }

}
