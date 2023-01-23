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
import br.com.tech4me.procedimentos.shared.ProcedimentosEscritaDto;
import br.com.tech4me.procedimentos.shared.ProcedimentosDto;

@Service
public class ProcedimentosServiceImpl implements ProcedimentosService {

    @Autowired
    private ProcedimentosRepository repositorio;

    @Autowired
    private ProcedimentosClient procedimentoClient;

    @Override
    public ProcedimentosEscritaDto CadastrarProcedimento(ProcedimentosEscritaDto dto) {
        Procedimentos procedimento = new ModelMapper().map(dto, Procedimentos.class);

        repositorio.save(procedimento);
        return new ModelMapper().map(procedimento, ProcedimentosEscritaDto.class);
    }

    @Override
    public List<ProcedimentosDto> obterProcedimentos() {
        List<Procedimentos> procedimentos = repositorio.findAll();
        List<ProcedimentosDto> listaProcedimentos = procedimentos.stream()
                .map(p -> new ModelMapper().map(p, ProcedimentosDto.class)).collect(Collectors.toList());
       
        for (ProcedimentosDto procedimento : listaProcedimentos ) {
           
            procedimento.setDadosPaciente(procedimentoClient.obterPaciente(procedimento.getIdPaciente()));

            
        }
        return listaProcedimentos;
    }

    @Override
    public Optional<ProcedimentosDto> obterProcedimentoPorId(String id) {
        Optional<Procedimentos> procedimento = repositorio.findById(id);

        if (procedimento.isPresent()) {
            ProcedimentosDto procedimentoComPaciente = new ModelMapper().map(procedimento, ProcedimentosDto.class);
            procedimentoComPaciente
                    .setDadosPaciente(procedimentoClient.obterPaciente(procedimentoComPaciente.getIdPaciente()));

            return Optional.of(procedimentoComPaciente);
        }
        return Optional.empty();
    }

    @Override
    public void excluirProcedimento(String id) {
        repositorio.deleteById(id);

    }

    @Override
    public Optional<ProcedimentosEscritaDto> atualizarProcedimentoPorId(String id, ProcedimentosEscritaDto dto) {
        Optional<Procedimentos> velho_procedimento = repositorio.findById(id);

        if (velho_procedimento.isPresent()) {
            Procedimentos procedimento = new ModelMapper().map(dto, Procedimentos.class);
            procedimento.setId(id);
            repositorio.save(procedimento);
            return Optional.of(new ModelMapper().map(procedimento, ProcedimentosEscritaDto.class));
        } else {
            return Optional.empty();
        }

    }

}
