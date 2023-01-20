package br.com.tech4me.procedimentos.service;

import java.util.List;
import java.util.Optional;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

public interface ProcedimentosService {
    
    ProcedimentosCompletoDto CadastrarPaciente(ProcedimentosCompletoDto dto);
    List<ProcedimentosCompletoDto>obterProcedimentos();
    Optional<ProcedimentosDto> obterProcedimentoPorId(String id);
    void excluirProcedimento(String id);
    Optional<ProcedimentosDto> atualizarProcedimentoPorId(String id ,ProcedimentosDto dto);
}
