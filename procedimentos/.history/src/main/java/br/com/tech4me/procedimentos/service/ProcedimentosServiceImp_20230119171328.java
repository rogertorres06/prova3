package br.com.tech4me.procedimentos.service;

import org.springframework.stereotype.Service;

@Service
public class ProcedimentosServiceImp {

    @Autowired
    private ProcedimentoRep repository;

    @Autowired
    private PizzariaClient pizzariaClient;

    @Override
    public List<PedidoCompletoDto> obterPedidos() {
        List<Pedido> pedidos = repository.findAll();
        List<PedidoCompletoDto> dto = pedidos.stream().map(p -> new ModelMapper().map(p, PedidoCompletoDto.class)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public Optional<PedidoDto> obterPedidoPorId(String id) {
        
        Optional<Pedido> pedido = repository.findById(id);
        if(pedido.isPresent()){
            PedidoDto pedidoComPizza = new ModelMapper().map(pedido, PedidoDto.class);
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
