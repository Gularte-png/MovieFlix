package br.com.movieflix.mapper;

import br.com.movieflix.entity.Usuario;
import br.com.movieflix.entity.request.UsuarioRequestDto;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class UsuarioMapper {

    public static Usuario paraEntidade(UsuarioRequestDto usuarioRequestDto){
        return Usuario
                .builder()
                .nome(usuarioRequestDto.nome())
                .email(usuarioRequestDto.email())
                .senha(usuarioRequestDto.senha())
                .build();
    }

    public static UsuarioReponseDTO paraUsuarioResponseDTO (Usuario usuario){
        return UsuarioReponseDTO
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}
