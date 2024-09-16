package br.com.fiap.SmartCash.UsuarioEmpresa;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND; 
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;  
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
    @Operation(summary = "Cria o registro do relacionamento entre usuário e empresa.", description = "Cria uma nova relação entre usuário e empresa no sistema.")  // Adicionado
    public UsuarioEmpresa create(@RequestBody @Valid UsuarioEmpresa usuarioEmpresa) {                                                        
        return repository.save(usuarioEmpresa);
    }
    
    @GetMapping
    @Operation(summary = "Lista todos os relacionamentos entre usuário e empresa.", description = "Retorna uma lista de todos os relacionamentos entre usuários e empresas no sistema.")  // Adicionado
    public List<UsuarioEmpresa> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna o registro do relacionamento entre usuário e empresa por ID.", description = "Retorna um relacionamento entre usuário e empresa pelo seu ID.")  // Adicionado
    public ResponseEntity<UsuarioEmpresa> readItem(@PathVariable Long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("relacaoEmpresaByUsuarioEmail")
    public List<UsuarioEmpresa> getUsuarioEmpresaByUsuarioEmail(@RequestParam String email) {
        return repository.getUsuarioEmpresaByUsuarioEmail(email);
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualiza o registro do relacionamento entre usuário e empresa.", description = "Atualiza um relacionamento entre usuário e empresa pelo seu ID.")  // Adicionado
    public UsuarioEmpresa update(@PathVariable Long id, @RequestBody UsuarioEmpresa usuarioEmpresa) {
        verificarSeExisteUsuarioEmpresa(id);
        usuarioEmpresa.setID_USUARIO_EMPRESA(id);
        return repository.save(usuarioEmpresa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta o registro do relacionamento entre usuário e empresa.", description = "Remove um relacionamento entre usuário e empresa pelo seu ID.")  // Adicionado
    public void delete(@PathVariable Long id) {
        verificarSeExisteUsuarioEmpresa(id);
        repository.deleteById(id);
    }

    private UsuarioEmpresa verificarSeExisteUsuarioEmpresa(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Relacionamento entre usuário e empresa não encontrado"));
    }

}
