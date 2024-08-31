package br.com.fiap.SmartCash.Empresa;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("empresa")
@CacheConfig(cacheNames = "empresas")
@Tag(name = "Empresas", description = "Endpoint relacionado com empresas")
public class EmpresaController {

    @Autowired
    EmpresaRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cria uma nova empresa.", description = "Cadastra uma nova empresa no sistema.")
    public Empresa create(@RequestBody @Valid Empresa empresa) {
        return repository.save(empresa);
    }

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todas as empresas.", description = "Retorna uma lista de todas as empresas cadastradas no sistema.")
    public List<Empresa> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Lista empresa por ID.", description = "Retorna uma empresa pelo seu ID.")
    public ResponseEntity<Empresa> readItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza uma empresa.", description = "Atualiza uma empresa pelo seu ID.")
    public Empresa update(@PathVariable Long id, @RequestBody Empresa empresa) {
        verificarSeExisteEmpresa(id);
        empresa.setID_EMPRESA(id);
        return repository.save(empresa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deleta uma empresa.", description = "Remove uma empresa pelo seu ID.")
    public void delete(@PathVariable Long id) {
        verificarSeExisteEmpresa(id);
        repository.deleteById(id);
    }

    @GetMapping("usuarioAcesso/{idUsuario}")
    @Operation(summary = "Empresas que um usuário tem acesso.", description = "Lista as empresas que um usuário específico tem acesso.")
    public List<Empresa> getEmpresasUsuarioTemAcesso(@PathVariable Long idUsuario) {
        return repository.getEmpresasUsuarioTemAcesso(idUsuario);
    }

    private void verificarSeExisteEmpresa(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empresa não encontrada"));
    }

}
