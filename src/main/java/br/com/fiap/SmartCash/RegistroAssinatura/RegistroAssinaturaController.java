package br.com.fiap.SmartCash.RegistroAssinatura;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND; 
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import br.com.fiap.SmartCash.Email.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    EmailService emailService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo registro de assinatura.", description = "Cria um novo registro de assinatura no sistema.")
    public RegistroAssinatura create(@RequestBody @Valid RegistroAssinatura registroAssinatura) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_";                
        String StringAleatoria = RandomStringUtils.random(10, caracteres);
        registroAssinatura.setTOKEN_EMPRESA(StringAleatoria);
        return repository.save(registroAssinatura);
    }

    @GetMapping
    @Operation(summary = "Lista todos os registros de assinatura.", description = "Retorna uma lista de todos os registros de assinatura no sistema.")
    public List<RegistroAssinatura> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um registro de assinatura por ID.", description = "Retorna um registro de assinatura pelo seu ID.")
    public ResponseEntity<RegistroAssinatura> readItem(@PathVariable Long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualiza um registro de assinatura.", description = "Atualiza um registro de assinatura pelo seu ID.")
    public RegistroAssinatura update(@PathVariable Long id, @RequestBody RegistroAssinatura registroAssinatura) {
        verificarSeExisteRegistroAssinatura(id);        
        registroAssinatura.setID_ASSINATURA_EMPRESA(id);
        return repository.save(registroAssinatura);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um registro de assinatura.", description = "Remove um registro de assinatura pelo seu ID.")
    public void delete(@PathVariable Long id) {
        verificarSeExisteRegistroAssinatura(id);
        repository.deleteById(id);
    }

    private void verificarSeExisteRegistroAssinatura(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Registro de assinatura não encontrado"));
    }

    @GetMapping("/token")
    public void enviarToken(@RequestParam String email, @RequestParam String tipoEnvio ) {
        if (tipoEnvio.equals("I")){
            var token = repository.getTokenByEmailEmpresa(email);
            if (token == null) {
                throw new RuntimeException("Email inválido");
            }
            emailService.sendEmailText(email, "Token de acesso", "Aqui está seu token: " + token);
        }
        if (tipoEnvio.equals("R")){
            var token = repository.getTokenByEmailEmpresa(email);
            if (token == null) {
                throw new RuntimeException("Email inválido");
            }
            emailService.sendEmailText(email, "Reenvio de token", token);
        }

    }

    @GetMapping("tokenByAssinatura")
    public RegistroAssinatura getRegistroAssinaturaByToken(@RequestParam String token) {
        return repository.getRegistroAssinaturaByToken(token);
    }

}
