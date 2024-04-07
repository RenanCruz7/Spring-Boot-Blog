package sping.bot.blog.post;

import java.time.LocalDateTime;

public record DadosDetalhesPost(Long id,String title, String author, String content , LocalDateTime createdAt){

    public DadosDetalhesPost(Post post){
        this(post.getId(),post.getTitle(), post.getAuthor(), post.getContent(), post.getCreatedAt());
    }
}
