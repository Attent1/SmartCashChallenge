package br.com.fiap.SmartCash.Usuario;

import br.com.fiap.SmartCash.Auth.Credenciais;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario create(Usuario usuario) {
        usuario.setSENHA(passwordEncoder.encode(usuario.getSENHA()));
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario, Credenciais credenciais) {
        usuario.setSENHA(passwordEncoder.encode(credenciais.senha()));
        return usuarioRepository.save(usuario);
    }

}
