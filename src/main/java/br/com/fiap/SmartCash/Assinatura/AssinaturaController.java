package br.com.fiap.SmartCash.Assinatura;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("assinatura")
@CacheConfig(cacheNames = "assinaturas")
@Tag(name = "Assinaturas", description = "Endpoint relacionado com Assinaturas")
public class AssinaturaController {

    @Autowired
    AssinaturaRepository repository;

    @Autowired
    PagedResourcesAssembler<Assinatura> pageAssembler;

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cadastra uma nova assinatura no sistema.")
    public Assinatura create(@RequestBody @Valid Assinatura assinatura) {
        return repository.save(assinatura);
    }

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todas as assinaturas cadastradas no sistema.", 
               description = "Endpoint que retorna um array de objetos do tipo assinatura" +
               "com todas as assinaturas do sistema e seus respectivos links para outros recursos." +
               "Trocar string por VALOR no 'sort' na hora de testar pela doc da web")
    public PagedModel<EntityModel<Assinatura>> readAll(
            @PageableDefault(size = 5, sort = "VALOR", direction = Direction.ASC) Pageable pageable) {
        Page<Assinatura> page = repository.findAll(pageable);
        return pageAssembler.toModel(page, Assinatura::toEntityModel);
    }

    @GetMapping("{id}")
    @Operation(summary = "Lista uma assinatura por ID.")
    public ResponseEntity<Assinatura> readItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza uma assinatura ja existente no sistema.")
    public Assinatura update(@PathVariable Long id, @RequestBody Assinatura assinatura) {
        verificarSeExisteAssinatura(id);
        assinatura.setID_ASSINATURA(id);
        return repository.save(assinatura);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deleta uma assinatura ja existente no sistema.")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        verificarSeExisteAssinatura(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Assinatura verificarSeExisteAssinatura(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Assinatura não encontrada"));
    }

}
