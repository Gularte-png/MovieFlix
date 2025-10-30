package br.com.movieflix.controller;

import br.com.movieflix.controller.doc.UsuarioControllerOpenAPI;
import br.com.movieflix.entity.Usuario;
import br.com.movieflix.entity.request.LoginRequestDTO;
import br.com.movieflix.entity.request.UsuarioRequestDto;
import br.com.movieflix.entity.response.LoginResponseDTO;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import br.com.movieflix.service.TokenService;
import br.com.movieflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsuarioController implements UsuarioControllerOpenAPI {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/criar")
    public ResponseEntity<UsuarioReponseDTO> salvarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> salvarUsuario(@RequestBody LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuario user = (Usuario) authentication.getPrincipal();

        LoginResponseDTO loginResponseDTO = tokenService.gerarToken(user);
        return ResponseEntity.ok(loginResponseDTO);
    }


}
