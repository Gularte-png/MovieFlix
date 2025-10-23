package br.com.movieflix.service;

import br.com.movieflix.entity.Usuario;
import br.com.movieflix.entity.dto.LoginResponseDTO;
import br.com.movieflix.entity.request.LoginRequestDTO;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public LoginResponseDTO gerarToken(Usuario user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return new LoginResponseDTO(JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(8640))
                .withClaim("id", user.getId())
                .withClaim("nome", user.getNome())
                .sign(algorithm));
    }

    public Optional<UsuarioReponseDTO> verificarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(UsuarioReponseDTO
                    .builder()
                    .id(decode.getClaim("id").asLong())
                    .nome(decode.getClaim("nome").asString())
                    .email(decode.getSubject())
                    .build());

        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
