package com.financieloo.config;

import com.financieloo.entity.Usuario;
import com.financieloo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class DataInitConfig {

    @Bean
    @Profile("dev")
    CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            Usuario user = Usuario.builder()
                    .nome("Usuário Teste")
                    .email("teste@financieloo.com")
                    .senha("123456")
                    .build();
            usuarioRepository.save(user);

        };
    }
}
