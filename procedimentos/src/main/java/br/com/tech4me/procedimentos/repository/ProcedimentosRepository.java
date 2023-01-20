package br.com.tech4me.procedimentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.procedimentos.model.Procedimentos;

public interface ProcedimentosRepository extends MongoRepository<Procedimentos,String> {
    
}
