package br.com.fiap.SmartCash.Auth;

import br.com.fiap.SmartCash.Usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Token login(Credenciais credenciais) {
        var user = usuarioRepository.findByEMAIL(credenciais.email())
                .orElseThrow(() -> new RuntimeException("Acesso negado"));

        if (!passwordEncoder.matches(credenciais.senha(), user.getSENHA()))
            throw new RuntimeException("Acesso negado");

        return tokenService.gerarToken(credenciais.email());
    }

}
