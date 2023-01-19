package br.com.tech4me.procedimentos.service;

public class ProcedimentosService {
    
    ProcedimentosCompletoDto cadastrar(ProcedimentosCompletoDto dto);
    List<ProcedimentosCompletoDto>obterTodos();
    Optional<ProcedimentosDto> obterPorId(String id);
    void excluirPorId(String id);
    Optional<PacienteCompletoDto> atualizarPorId(String id, PacienteCompletoDto dto);
}
