package br.com.tech4me.paciente.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.paciente.shared.PacienteCompletoDto;
import br.com.tech4me.paciente.shared.PacienteDto;

public interface PacienteService {
    
    PacienteCompletoDto cadastrar(PacienteCompletoDto dto);
    List<PacienteCompletoDto>obterTodos();
    Optional<PacienteDto> obterPorId(String id);
    void excluirPorId(String id);
    Optional<PacienteCompletoDto> atualizarPorId(String id, PacienteCompletoDto dto);
}
