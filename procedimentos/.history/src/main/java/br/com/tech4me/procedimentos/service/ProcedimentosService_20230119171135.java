package br.com.tech4me.procedimentos.service;

import java.util.List;
import java.util.Optional;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

public interface ProcedimentosService {
    
    ProcedimentosCompletoDto cadastrar(ProcedimentosCompletoDto dto);
    List<ProcedimentosCompletoDto>obterTodos();
    Optional<ProcedimentosDto> obterPorId(String id);
    void excluirPorId(String id);
    Optional<ProcedimentosCompletoDto> atualizarPorId(String id, ProcedimentosCompletoDto dto);
}
