package br.com.tech4me.paciente.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.paciente.model.Paciente;
import br.com.tech4me.paciente.repository.PacienteRepository;
import br.com.tech4me.paciente.shared.PacienteCompletoDto;
import br.com.tech4me.paciente.shared.PacienteDto;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository repositorio;

    @Override
    public PacienteCompletoDto cadastrar(PacienteCompletoDto dto) {
        Paciente paciente = new ModelMapper().map(dto, Paciente.class);
        repositorio.save(paciente);
        return new ModelMapper().map(paciente, PacienteCompletoDto.class);
    }

    @Override
    public List<PacienteCompletoDto> obterTodos() {
        List<Paciente> pacientes = repositorio.findAll();
        
        return pacientes.stream()
        .map(p -> new ModelMapper().map(p, PacienteCompletoDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDto> obterPorId(String id) {
        Optional<Paciente> paciente  = repositorio.findById(id);

        if(paciente.isPresent()){
            return Optional.of(new ModelMapper().map(paciente.get(), PacienteDto.class));
        }
        return Optional.empty();
    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
        
    }

    @Override
    public Optional<PacienteCompletoDto> atualizarPorId(String id, PacienteCompletoDto dto) {
        Optional<Paciente> retorno =  repositorio.findById(id);

        if(retorno.isPresent()){
            Paciente paciente = new ModelMapper().map(dto, Paciente.class);
            paciente.setId(id);
            repositorio.save(paciente);
            return Optional.of(new ModelMapper().map(paciente, PacienteCompletoDto.class));
        }else{
            return Optional.empty();
        }
        
    }

    
    
}
