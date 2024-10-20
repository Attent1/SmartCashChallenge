package br.com.fiap.SmartCash.Usuario;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import br.com.fiap.SmartCash.Auth.Credenciais;
import br.com.fiap.SmartCash.Usuario.dto.UsuarioRequest;
import br.com.fiap.SmartCash.Usuario.dto.UsuarioResponse;
import br.com.fiap.SmartCash.Email.Email;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("usuario")
@CacheConfig(cacheNames = "usuarios")
@Tag(name = "Usuários", description = "Endpoint relacionado com Usuários")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    UsuarioService usuarioService;

    private final RabbitTemplate rabbitTemplate;

    public UsuarioController(UsuarioService usuarioService, RabbitTemplate rabbitTemplate) {
        this.usuarioService = usuarioService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cria um novo usuário.", description = "Cria um novo usuário no sistema.")
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.create(usuarioRequest.toModel());

        Email email = new Email(usuario.getEMAIL(),
                        "Criação de conta",
                       "Conta criada com sucesso, Bem vindo ao SmartCash " + usuario.getNOME()
        );

        rabbitTemplate.convertAndSend("email-queue", email);

        var uri = uriBuilder
                .path("/usuario/{id}")
                .buildAndExpand(usuario.getID_USUARIO())
                .toUri();

        return ResponseEntity
               .created(uri)
               .body(UsuarioResponse.from(usuario));
    }

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todos os usuários.", description = "Retorna uma lista de todos os usuários no sistema.")
    public List<Usuario> readAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um usuário por ID.", description = "Retorna um usuário pelo seu ID.")
    public ResponseEntity<Usuario> readItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("email")
    public UsuarioResponse getUsuarioByEmail(@RequestParam String email) {
        var usuario = repository.findByEMAIL(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return UsuarioResponse.from(usuario);
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza um usuário.", description = "Atualiza um usuário pelo seu ID.")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        verificarSeExisteUsuario(id);
        usuario.setID_USUARIO(id);
        return repository.save(usuario);
    }

    @PutMapping("novaSenha")
    public UsuarioResponse atualizarSenha(@RequestBody Credenciais credenciais){
        var usuario = repository.findByEMAIL(credenciais.email()).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        usuarioService.update(usuario, credenciais);
        return UsuarioResponse.from(usuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um usuário.", description = "Remove um usuário pelo seu ID.")
    public void delete(@PathVariable Long id) {
        verificarSeExisteUsuario(id);
        repository.deleteById(id);
    }

    private void verificarSeExisteUsuario(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

}
