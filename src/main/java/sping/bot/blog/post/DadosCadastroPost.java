package sping.bot.blog.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//DTO = Data Transfer Object
//Classe que representa o cadastro de um post
public record DadosCadastroPost(
        @NotBlank
        @Size(min = 5, max = 100)
        String title,
        @NotBlank @NotNull
        String author,
        @NotBlank @NotNull
        String content) {
}