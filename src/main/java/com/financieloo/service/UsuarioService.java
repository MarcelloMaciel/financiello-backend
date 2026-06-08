package com.financieloo.service;

import com.financieloo.dto.UsuarioRequest;
import com.financieloo.dto.UsuarioResponse;
import com.financieloo.entity.Usuario;
import com.financieloo.exception.RecursoNaoEncontradoException;
import com.financieloo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return toResponse(usuario);
    }

    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .ativo(true)
                .build();
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponse update(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataCadastro());
    }
}
