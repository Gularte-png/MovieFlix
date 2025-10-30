package br.com.movieflix.controller.doc;

import br.com.movieflix.entity.request.LoginRequestDTO;
import br.com.movieflix.entity.request.UsuarioRequestDto;
import br.com.movieflix.entity.response.LoginResponseDTO;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuário", description = "Operações relacionadas a usuários")
public interface UsuarioControllerOpenAPI {

    @Operation(summary = "Cria usuário", description = "Realiza a criação de um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioReponseDTO.class)))
    ResponseEntity<UsuarioReponseDTO> salvarUsuario(UsuarioRequestDto usuarioRequestDto);

    @Operation(summary = "Login de usuário", description = "Realiza o login de um usuário")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class)))
    ResponseEntity<LoginResponseDTO> salvarUsuario(LoginRequestDTO loginRequestDTO);

}
