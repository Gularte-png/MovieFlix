package br.com.movieflix.service;

import br.com.movieflix.entity.Usuario;
import br.com.movieflix.entity.request.LoginRequestDTO;
import br.com.movieflix.entity.request.UsuarioRequestDto;
import br.com.movieflix.entity.response.UsuarioReponseDTO;
import br.com.movieflix.mapper.UsuarioMapper;
import br.com.movieflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioReponseDTO salvarUsuario(UsuarioRequestDto usuarioRequestDto){
        Usuario usuario = UsuarioMapper.paraEntidade(usuarioRequestDto);
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDto.senha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioMapper.paraUsuarioResponseDTO(usuarioSalvo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o email fornecido"));
    }
}
