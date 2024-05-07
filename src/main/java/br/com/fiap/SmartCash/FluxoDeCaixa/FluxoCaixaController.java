package br.com.fiap.SmartCash.FluxoDeCaixa;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("fluxoCaixa")
@Tag(name = "Fluxo de Caixa", description = "Endpoint relacionado com Fluxo de Caixa")
public class FluxoCaixaController {

    @Autowired
    FluxoCaixaRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo Fluxo de Caixa.", description = "Cadastra um novo fluxo de caixa no sistema.")
    public FluxoCaixa create(@RequestBody @Valid FluxoCaixa fluxoCaixa) {
        return repository.save(fluxoCaixa);
    }

    @GetMapping
    @Operation(summary = "Lista todos os Fluxos de Caixa.", description = "Retorna uma lista de todos os fluxos de caixa cadastrados.")
    public List<FluxoCaixa> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um Fluxo de Caixa por ID.", description = "Retorna um fluxo de caixa pelo seu ID.")
    public ResponseEntity<FluxoCaixa> readItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    public void delete(@PathVariable Long id) {
        verificarSeExisteFluxoCaixa(id);
        repository.deleteById(id);
    }

    private FluxoCaixa verificarSeExisteFluxoCaixa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fluxo de Caixa não encontrado"));
    }

}
