package com.example.demo2.service;

import java.util.List;
import java.util.Optional;
import com.example.demo2.compartilhado.MusicaDto;


public interface MusicaService {
    MusicaDto criarMusica(MusicaDto musDto);
    List<MusicaDto> obterTodos();
    Optional<MusicaDto> obterPorId(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musica);
    void excluirMusica(String id);
    public Long contar();
}
