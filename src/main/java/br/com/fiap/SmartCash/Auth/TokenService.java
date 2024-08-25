package br.com.fiap.SmartCash.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public Token gerarToken(String loginUsuario){

        var expirationAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC);
        Algorithm algorithm = Algorithm.HMAC256("assinatura");
        String token = JWT.create()
                .withSubject(loginUsuario)
                .withExpiresAt(expirationAt)
                .withIssuer("SmartCash")
                .sign(algorithm);

        return new Token(token, loginUsuario);

    }

}
