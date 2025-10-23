package br.com.movieflix.controller;

import br.com.movieflix.config.SecurityConfig;
import br.com.movieflix.entity.request.LoginRequestDTO;
import br.com.movieflix.entity.request.UsuarioRequestDto;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import br.com.movieflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/criar")
    public ResponseEntity<UsuarioReponseDTO> salvarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioReponseDTO> salvarUsuario(LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authentication.getPrincipal();

    }


}
