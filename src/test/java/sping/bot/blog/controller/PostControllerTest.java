package sping.bot.blog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import sping.bot.blog.domain.post.*;
import sping.bot.blog.domain.user.User;
import sping.bot.blog.infra.security.TokenService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static sping.bot.blog.domain.post.Ligue.LaLiga;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CreatePostDTO> jsonCreate;

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Devolver codigo http 400 quando informações inválidas forem passadas")
    void publishPost_cenario1() throws Exception {
        var response = mvc.perform(post("/Posts"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Devolver codigo http 201 quando informações validas forem passadas")
    void publishPost_cenario2() throws Exception {
        var createPostDTO = new CreatePostDTO("Titulo", "Conteudo", LaLiga);

        // Criar um usuário de teste
        User testUser = new User();
        testUser.setUsername("renan.santos");
        // Gerar um token válido
        String validToken = tokenService.GenerateToken(testUser);

        // Modificar a requisição para incluir o token válido
        var response = mvc.perform(post("/Posts")
                        .header("Authorization", "Bearer " + validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreate.write(createPostDTO).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}