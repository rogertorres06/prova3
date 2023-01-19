package br.com.tech4me.procedimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.procedimentos.service.ProcedimentosService;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentosService servico;
    
   //Cadastrar pedido
   @PostMapping
   public ResponseEntity<ProcedimentosCompletoDto> cadastrarPedido(@RequestBody @Valid Procedimentos pedido)
   {
       return new ResponseEntity<>(servico.CadastrarPedido(pedido),HttpStatus.CREATED);
   }  
    
}
