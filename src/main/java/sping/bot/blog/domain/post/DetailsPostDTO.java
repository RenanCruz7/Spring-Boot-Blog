package sping.bot.blog.domain.post;

import java.time.LocalDateTime;

public record DetailsPostDTO(Long id, String title, String author, String content , Ligue ligue , LocalDateTime createdAt){

    public DetailsPostDTO(Post post){
        this(post.getId(),post.getTitle(), post.getAuthor(), post.getContent(),post.getLigue(),post.getCreatedAt());
    }
}
