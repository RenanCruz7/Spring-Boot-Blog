package sping.bot.blog.domain.post;

import jakarta.validation.constraints.NotNull;

public record DadosEditaPost(
        @NotNull
        Long id,
        String title,
        String content,
        Ligue ligue ){
}
