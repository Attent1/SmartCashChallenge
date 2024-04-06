package br.com.fiap.SmartCash.Empresa;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("empresa")
public class EmpresaController {

    @Autowired
    EmpresaRepository repository;
    
    @PostMapping
    @ResponseStatus(CREATED)
    public Empresa create(@RequestBody @Valid Empresa empresa){
        return repository.save(empresa);
    }
    
    @GetMapping
    public List<Empresa> readAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> readItem(@PathVariable Long id){
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());      
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public Empresa update(@PathVariable Long id, @RequestBody Empresa empresa){
        verificarSeExisteEmpresa(id);
        empresa.setID_EMPRESA(id);
        return repository.save(empresa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){        
        verificarSeExisteEmpresa(id);
        repository.deleteById(id);
    }

    private Empresa verificarSeExisteEmpresa(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empresa não encontrada"));
    }

}
