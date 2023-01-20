package br.com.tech4me.procedimentos.httpClient;



import org.springframework.cloud.openfeign.FeignClient;

import br.com.tech4me.procedimentos.model.Paciente;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("paciente")
public interface ProcedimentosClient {
    
    @RequestMapping(method =RequestMethod.GET, value="/paciente/{id}")
    Paciente obterPaciente(@PathVariable String id);
}
