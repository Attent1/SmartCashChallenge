package br.com.fiap.SmartCash.Usuario;

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
        usuario.setLOGIN_USUARIO(geraLoginUsuario(usuario));
        usuario.setSENHA(passwordEncoder.encode(usuario.getSENHA()));
        return usuarioRepository.save(usuario);
    }

    private String geraLoginUsuario(Usuario usuario) {
        String documentoNumerico = usuario.getDOCUMENTO().replaceAll("[^0-9]", "");
        return usuario.getNOME().charAt(0) +
                (documentoNumerico.length() == 11 ? documentoNumerico.substring(7)
                        : documentoNumerico.substring(10));
    }

}
