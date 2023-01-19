package br.com.tech4me.procedimentos.service;

public class ProcedimentosService {
    
    ProcedimentosCompletoDto cadastrar(PCompletoDto dto);
    List<PacienteCompletoDto>obterTodos();
    Optional<PacienteDto> obterPorId(String id);
    void excluirPorId(String id);
    Optional<PacienteCompletoDto> atualizarPorId(String id, PacienteCompletoDto dto);
}
