package br.com.tech4me.procedimentos.service;

import java.util.List;
import java.util.Optional;
import br.com.tech4me.procedimentos.shared.ProcedimentosEscritaDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

public interface ProcedimentosService {

    ProcedimentosEscritaDto CadastrarProcedimento(ProcedimentosEscritaDto dto);

    List<ProcedimentosDto> obterProcedimentos();

    Optional<ProcedimentosDto> obterProcedimentoPorId(String id);

    void excluirProcedimento(String id);

    Optional<ProcedimentosEscritaDto> atualizarProcedimentoPorId(String id, ProcedimentosEscritaDto dto);
}
