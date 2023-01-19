package br.com.tech4me.paciente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.paciente.model.Paciente;

public interface PacienteRepository extends MongoRepository<Paciente, String> {
    
}
