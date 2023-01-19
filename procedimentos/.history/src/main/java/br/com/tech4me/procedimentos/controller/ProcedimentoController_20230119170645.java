package br.com.tech4me.procedimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    @Autowired
    private PedidoService servico;

    //Cadastrar pedido
    @PostMapping
    public ResponseEntity<PedidoCompletoDto> cadastrarPedido(@RequestBody @Valid PedidoCompletoDto pedido)
    {
        return new ResponseEntity<>(servico.CadastrarPedido(pedido),HttpStatus.CREATED);
    }

    //Buscar Pedidos
    @GetMapping
    public ResponseEntity<List<PedidoCompletoDto>> obterPedidos()
    {
        return new ResponseEntity<>(servico.obterPedidos(),HttpStatus.OK);
    } 

    //Buscar pedido por id
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obterPedidoPorId(@PathVariable String id)
    {
        Optional<PedidoDto> retorno = servico.obterPedidoPorId(id);
        
        if(retorno.isPresent()){
            return new ResponseEntity<>(retorno.get(), HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
    }

    //Deletar Pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable String id)
    {
        servico.excluirPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable String id, @Valid PedidoDto pedido){
        Optional<PedidoDto> retorno = servico.atualizarPedidoPorId(id, pedido);

        if(retorno.isPresent()){
            return new ResponseEntity<>(retorno.get(),HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    } 
    
}
