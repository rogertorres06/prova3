package br.com.tech4me.paciente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.paciente.service.PacienteService;
import br.com.tech4me.paciente.shared.PacienteCompletoDto;
import br.com.tech4me.paciente.shared.PacienteDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired 
    private PacienteService servico;

//cadastrar paciente
  @PostMapping
    public ResponseEntity<PacienteCompletoDto> cadastrarPaciente(@RequestBody @Valid PacienteCompletoDto paciente){
        return new ResponseEntity<>(servico.cadastrar(paciente), HttpStatus.CREATED);
    }

    //buscar todos os pacientes
    @GetMapping
    public ResponseEntity<List<PacienteCompletoDto>> obterPacientes(){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }
//buscar paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obterPacienteId(@PathVariable String id){
        Optional<PacienteDto> retorno = servico.obterPorId(id);

        if(retorno.isPresent()){
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Excluir Paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable String id){
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Atualizar Pacientes
   @PutMapping("/{id}")
   public ResponseEntity<PacienteCompletoDto> atualizarPaciente(@PathVariable String id, @RequestBody PacienteCompletoDto paciente){
    Optional<PacienteCompletoDto> retorno = servico.atualizarPorId(id, paciente);

    if(retorno.isPresent()){
        return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
    } else{
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
   }

   @GetMapping("/porta")
   public String obterPorta(@Value("${local.server.port}") String porta){
     return "A instância que respondeu a requisição está rodando na porta" + porta;
   }


    
}
