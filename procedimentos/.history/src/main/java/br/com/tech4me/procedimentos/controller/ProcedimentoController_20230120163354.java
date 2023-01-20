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

import br.com.tech4me.procedimentos.model.Procedimentos;
import br.com.tech4me.procedimentos.service.ProcedimentosService;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {
    @Autowired
    private ProcedimentosService servico;

    //Cadastrar pedido
    @PostMapping
    public ResponseEntity<ProcedimentosCompletoDto> cadastrarPaciente(@RequestBody @Valid ProcedimentosCompletoDto procedimentos)
    {
        return new ResponseEntity<>(servico.CadastrarPaciente(procedimentos),HttpStatus.CREATED);
    }

    //Buscar Pedidos
    @GetMapping
    public ResponseEntity<List<ProcedimentosCompletoDto>> obterProcedimentos()
    {
        return new ResponseEntity<>(servico.obterProcedimentos(),HttpStatus.OK);
    } 

    //Buscar pedido por id
    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentosDto> obterProcedimentoPorId(@PathVariable String id)
    {
        Optional<ProcedimentosDto> retorno = servico.obterProcedimentoPorId(id);
        
        if(retorno.isPresent()){
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
    }

    //Deletar Pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProcedimento(@PathVariable String id)
    {
        servico.excluirProcedimento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentosDto> atualizarProcedimento(@PathVariable String id, @Valid ProcedimentosDto procedimentosDto){
        Optional<ProcedimentosDto> retorno = servico.atualizarProcedimentoPorId(id, Procedimentos);

        if(retorno.isPresent()){
            return new ResponseEntity<>(retorno.get(),HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    } 
    
}
