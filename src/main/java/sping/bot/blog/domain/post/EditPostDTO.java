package sping.bot.blog.domain.post;

import jakarta.validation.constraints.NotNull;

public record EditPostDTO(
        @NotNull
        Long id,
        String title,
        String content,
        Ligue ligue ){
}
