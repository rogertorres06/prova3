package br.com.tech4me.procedimentos.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.procedimentos.httpClient.ProcedimentosClient;
import br.com.tech4me.procedimentos.model.Procedimentos;
import br.com.tech4me.procedimentos.repository.ProcedimentosRepository;
import br.com.tech4me.procedimentos.shared.ProcedimentosCompletoDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

@Service
public class ProcedimentosServiceImp implements ProcedimentosService {

    @Autowired
    private ProcedimentosRepository repository;

    @Autowired
    private ProcedimentosClient procedimentoClient;
    

    @Override
    public ProcedimentosCompletoDto CadastrarPaciente(ProcedimentosCompletoDto dto) {
        Procedimentos procedimento = new ModelMapper().map(dto, Procedimentos.class);

        repository.save(procedimento);
        return new ModelMapper().map(procedimento,ProcedimentosCompletoDto.class);
    }

    @Override
    public List<ProcedimentosCompletoDto> obterProcedimentos() {
        List<Procedimentos> procedimentos = repository.findAll();
        List<ProcedimentosCompletoDto> dto = procedimentos.stream()
        .map(p -> new ModelMapper().map(p, ProcedimentosCompletoDto.class)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public Optional<ProcedimentosDto> obterProcedimentoPorId(String id) {
        Optional<Procedimentos> procedimento = repository.findById(id);

        if(procedimento.isPresent()){
            ProcedimentosDto procedimentoComPaciente = new ModelMapper().map(procedimento, ProcedimentosDto.class);
          procedimentoComPaciente.setDadosPaciente(procedimentoClient.obterPaciente(procedimentoComPaciente.getIdPaciente())); 

            return Optional.of(procedimentoComPaciente);
        }
        return Optional.empty();
    }

    @Override
    public void excluirProcedimento(String id) {
        repository.deleteById(id);
        
    }

    @Override
    public Optional<ProcedimentosDto> atualizarProcedimentoPorId(String id, ProcedimentosDto dto) {
        Optional<Procedimentos> retorno = repository.findById(id);

        if(retorno.isPresent()){
            Procedimentos procedimentoRetorno = new ModelMapper()
            .map(dto, Procedimentos.class);
            procedimentoRetorno.setId(id);
            repository.save(procedimentoRetorno);
            return Optional.of(new ModelMapper().map(procedimentoRetorno, ProcedimentosDto.class));
        }
        return Optional.empty();
    }

 
    
}
