package br.com.fiap.SmartCash.Usuario;

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
@RequestMapping("usuario")
@CacheConfig(cacheNames = "usuarios")
@Tag(name = "Usuários", description = "Endpoint relacionado com Usuários")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;
        
    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cria um novo usuário.", description = "Cria um novo usuário no sistema.")  
    public Usuario create(@RequestBody @Valid Usuario usuario){        
        usuario.setLOGIN_USUARIO(geraLoginUsuario(usuario));                                         
        return repository.save(usuario);
    }
    
    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todos os usuários.", description = "Retorna uma lista de todos os usuários no sistema.")  // Adicionado
    public List<Usuario> readAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um usuário por ID.", description = "Retorna um usuário pelo seu ID.")  
    public ResponseEntity<Usuario> readItem(@PathVariable Long id){
        return repository.findById(id)
                         .map(ResponseEntity::ok) 
                         .orElse(ResponseEntity.notFound().build());     
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza um usuário.", description = "Atualiza um usuário pelo seu ID.")  
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario){
        verificarSeExisteUsuario(id);
        usuario.setID_USUARIO(id);
        usuario.setLOGIN_USUARIO(geraLoginUsuario(usuario));   
        return repository.save(usuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um usuário.", description = "Remove um usuário pelo seu ID.")  
    public void delete(@PathVariable Long id){        
        verificarSeExisteUsuario(id);
        repository.deleteById(id);
    }

    private Usuario verificarSeExisteUsuario(Long id) {
        return repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    private String geraLoginUsuario(Usuario usuario) {
        String documentoNumerico = usuario.getDOCUMENTO().replaceAll("[^0-9]", "");
        String loginUsuario = usuario.getNOME().substring(0, 1) + 
        (documentoNumerico.length() == 11 ? 
        documentoNumerico.substring(7) 
        : 
        documentoNumerico.substring(10));
        return loginUsuario;
    }
}
