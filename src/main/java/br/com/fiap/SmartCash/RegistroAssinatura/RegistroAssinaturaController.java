package br.com.fiap.SmartCash.RegistroAssinatura;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND; 
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("registroAssinatura")
@Tag(name = "Registro de Assinatura", description = "Endpoint relacionado com Registro de Assinatura")
public class RegistroAssinaturaController {

    @Autowired
    RegistroAssinaturaRepository repository;
    
    @PostMapping
    @ResponseStatus(CREATED)
    public RegistroAssinatura create(@RequestBody @Valid RegistroAssinatura registroAssinatura){
        return repository.save(registroAssinatura);
    }
    
    @GetMapping
    public List<RegistroAssinatura> readAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<RegistroAssinatura> readItem(@PathVariable Long id){
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());      
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public RegistroAssinatura update(@PathVariable Long id, @RequestBody RegistroAssinatura registroAssinatura){
        verificarSeExisteRegistroAssinatura(id);        
        registroAssinatura.setID_ASSINATURA_EMPRESA(id);
        return repository.save(registroAssinatura);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){        
        verificarSeExisteRegistroAssinatura(id);
        repository.deleteById(id);
    }

    private RegistroAssinatura verificarSeExisteRegistroAssinatura(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Registro da assinatura não encontrada"));
    }

}
