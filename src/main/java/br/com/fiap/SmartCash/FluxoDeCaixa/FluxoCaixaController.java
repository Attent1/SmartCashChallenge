package br.com.fiap.SmartCash.FluxoDeCaixa;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("fluxoCaixa")
@Tag(name = "Fluxo de Caixa", description = "Endpoint relacionado com Fluxo de Caixa")
public class FluxoCaixaController {

    @Autowired
    FluxoCaixaRepository repository;

    @Autowired
    PagedResourcesAssembler<FluxoCaixa> pageAssembler;
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo Fluxo de Caixa.", description = "Cadastra um novo fluxo de caixa no sistema.")
    public FluxoCaixa create(@RequestBody @Valid FluxoCaixa fluxoCaixa) {
        return repository.save(fluxoCaixa);
    }

    @GetMapping
    @Operation(summary = "Lista todos os Fluxos de Caixa.", 
               description = "Retorna uma lista de todos os fluxos de caixa cadastrados e seus respectivos links para outros recursos." +
               "Trocar string por VALOR no 'sort' na hora de testar pela doc da web"
               )
    public PagedModel<EntityModel<FluxoCaixa>> readAll(@PageableDefault(size = 5, sort = "VALOR", direction = Direction.ASC) Pageable pageable) {
        Page<FluxoCaixa> page = repository.findAll(pageable);
        return pageAssembler.toModel(page, FluxoCaixa::toEntityModel);
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um Fluxo de Caixa por ID.", description = "Retorna um fluxo de caixa pelo seu ID.")
    public ResponseEntity<FluxoCaixa> readItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dataInclusao")
    @Operation(summary = "Lista todos os Fluxos de Caixa pela data de inclusão informada.")
    public PagedModel<EntityModel<FluxoCaixa>> getFluxoPorDataInclusao(@RequestParam String dataInclusao, @RequestParam Long idEmpresa, @PageableDefault(size = 100) Pageable pageable) {
        Page<FluxoCaixa> page = repository.findByDataInclusao(dataInclusao, idEmpresa, pageable);
        return pageAssembler.toModel(page, FluxoCaixa::toEntityModel);
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualiza um Fluxo de Caixa.", description = "Atualiza um fluxo de caixa pelo seu ID.")
    public FluxoCaixa update(@PathVariable Long id, @RequestBody FluxoCaixa fluxoCaixa) {
        verificarSeExisteFluxoCaixa(id);
        fluxoCaixa.setID_FLUXO(id);
        return repository.save(fluxoCaixa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um Fluxo de Caixa.", description = "Remove um fluxo de caixa pelo seu ID.")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        verificarSeExisteFluxoCaixa(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private FluxoCaixa verificarSeExisteFluxoCaixa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fluxo de Caixa não encontrado"));
    }

}
