package br.com.fiap.SmartCash.UsuarioEmpresa;

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
@RequestMapping("usuarioEmpresa")
@Tag(name = "Relacionamento entre usuário e empresas", description = "Endpoint relacionado a relação de usuários com empresas")
public class UsuarioEmpresaController {

    @Autowired
    UsuarioEmpresaRepository repository;
        
    @PostMapping
    @ResponseStatus(CREATED)
    public UsuarioEmpresa create(@RequestBody @Valid UsuarioEmpresa UsuarioEmpresa){                                                        
        return repository.save(UsuarioEmpresa);
    }
    
    @GetMapping
    public List<UsuarioEmpresa> readAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioEmpresa> readItem(@PathVariable Long id){
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());      
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public UsuarioEmpresa update(@PathVariable Long id, @RequestBody UsuarioEmpresa UsuarioEmpresa){
        verificarSeExisteUsuarioEmpresa(id);
        UsuarioEmpresa.setID_USUARIO_EMPRESA(id);
        return repository.save(UsuarioEmpresa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){        
        verificarSeExisteUsuarioEmpresa(id);
        repository.deleteById(id);
    }

    private UsuarioEmpresa verificarSeExisteUsuarioEmpresa(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrada"));
    }
}
