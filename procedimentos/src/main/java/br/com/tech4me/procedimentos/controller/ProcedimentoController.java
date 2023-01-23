package br.com.tech4me.procedimentos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.procedimentos.service.ProcedimentosService;
import br.com.tech4me.procedimentos.shared.ProcedimentosEscritaDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {
    @Autowired
    private ProcedimentosService servico;

    // Cadastrar procedimento
    @PostMapping
    public ResponseEntity<ProcedimentosEscritaDto> cadastrarProcedimento(
            @RequestBody @Valid ProcedimentosEscritaDto procedimentos) {
        return new ResponseEntity<>(servico.CadastrarProcedimento(procedimentos), HttpStatus.CREATED);
    }

    // Buscar procedimento
    @GetMapping
    public ResponseEntity<List<ProcedimentosDto>> obterProcedimentos() {
        return new ResponseEntity<>(servico.obterProcedimentos(), HttpStatus.OK);
    }

    // Buscar procedimento
    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentosDto> obterProcedimentoPorId(@PathVariable String id) {
        Optional<ProcedimentosDto> retorno = servico.obterProcedimentoPorId(id);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar Procedimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProcedimento(@PathVariable String id) {
        servico.excluirProcedimento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar procedimento
    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentosEscritaDto> atualizarProcedimento(@PathVariable String id,
            @RequestBody @Valid ProcedimentosEscritaDto procedimentos) {
        Optional<ProcedimentosEscritaDto> retorno = servico.atualizarProcedimentoPorId(id, procedimentos);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
