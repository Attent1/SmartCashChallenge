package br.com.fiap.SmartCash.RegistroAssinatura;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND; 
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
@RequestMapping("registroAssinatura")
@Tag(name = "Registro de Assinatura", description = "Endpoint relacionado com Registro de Assinatura")
public class RegistroAssinaturaController {

    @Autowired
    RegistroAssinaturaRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo registro de assinatura.", description = "Cria um novo registro de assinatura no sistema.")  // Adicionado
    public RegistroAssinatura create(@RequestBody @Valid RegistroAssinatura registroAssinatura) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_";                
        String StringAleatoria = RandomStringUtils.random(10, caracteres);
        registroAssinatura.setTOKEN_EMPRESA(StringAleatoria);
        return repository.save(registroAssinatura);
    }

    @GetMapping
    @Operation(summary = "Lista todos os registros de assinatura.", description = "Retorna uma lista de todos os registros de assinatura no sistema.")  // Adicionado
    public List<RegistroAssinatura> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um registro de assinatura por ID.", description = "Retorna um registro de assinatura pelo seu ID.")  // Adicionado
    public ResponseEntity<RegistroAssinatura> readItem(@PathVariable Long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualiza um registro de assinatura.", description = "Atualiza um registro de assinatura pelo seu ID.")  // Adicionado
    public RegistroAssinatura update(@PathVariable Long id, @RequestBody RegistroAssinatura registroAssinatura) {
        verificarSeExisteRegistroAssinatura(id);        
        registroAssinatura.setID_ASSINATURA_EMPRESA(id);
        return repository.save(registroAssinatura);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um registro de assinatura.", description = "Remove um registro de assinatura pelo seu ID.")  // Adicionado
    public void delete(@PathVariable Long id) {
        verificarSeExisteRegistroAssinatura(id);
        repository.deleteById(id);
    }

    private RegistroAssinatura verificarSeExisteRegistroAssinatura(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Registro de assinatura não encontrado"));
    }
}
