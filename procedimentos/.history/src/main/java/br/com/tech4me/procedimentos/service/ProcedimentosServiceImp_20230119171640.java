package br.com.tech4me.procedimentos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.procedimentos.httpclient.ProcedimentosClient;
import br.com.tech4me.procedimentos.model.Procedimentos;
import br.com.tech4me.procedimentos.repository.ProcedimentosRepository;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

@Service
public class ProcedimentosServiceImp {

    @Autowired
    private ProcedimentosRepository repository;

    @Autowired
    private ProcedimentosClient pizzariaClient;

    @Override
    public List<ProcedimentosCompletoDto> obterPedidos() {
        List<Procedimentos> pedidos = repository.findAll();
        List<ProcedimentosCompletoDto> dto = pedidos.stream().map(p -> new ModelMapper().map(p, ProcedimentosCompletoDto.class)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public Optional<ProcedimentosDto> obterPedidoPorId(String id) {
        
        Optional<Procedimentos> pedido = repository.findById(id);
        if(pedido.isPresent()){
            ProcedimentosDto pedidoComPizza = new ModelMapper().map(pedido, ProcedimentosDto.class);
           pedidoComPizza.setDadosPizza (pizzariaClient.obterPizza(pedidoComPizza.getIdPizza()));
           ////
            return Optional.of(pedidoComPizza);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void excluirPedido(String id) {
        repository.deleteById(id);
    }

    @Override
    public PedidoCompletoDto CadastrarPedido(PedidoCompletoDto dto) {
        Pedido pedido = new ModelMapper().map(dto, Pedido.class);

        repository.save(pedido);
        return new ModelMapper().map(pedido,PedidoCompletoDto.class);
    }

    @Override
    public Optional<PedidoDto> atualizarPedidoPorId(String id, PedidoDto dto) {
        Optional<Pedido> retorno = repository.findById(id);

        if(retorno.isPresent()){
            Pedido pedidoRetorno = new ModelMapper().map(dto, Pedido.class);
            pedidoRetorno.setId(id);
            repository.save(pedidoRetorno);
            return Optional.of(new ModelMapper().map(pedidoRetorno, PedidoDto.class));
        }else{
            return Optional.empty();
        }
    }   
    
}
