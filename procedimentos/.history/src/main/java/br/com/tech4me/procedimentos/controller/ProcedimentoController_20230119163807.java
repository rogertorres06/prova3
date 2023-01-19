package br.com.tech4me.procedimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.procedimentos.service.ProcedimentosService;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentosService servico;
    
   //Cadastrar pedido
   @PostMapping
   public ResponseEntity<ProcedimentosCompletoDto> cadastrarPedido(@RequestBody @Valid PedidoCompletoDto pedido)
   {
       return new ResponseEntity<>(servico.CadastrarPedido(pedido),HttpStatus.CREATED);
   }  
    
}
