package sping.bot.blog.post;

import java.time.LocalDateTime;

public record DadosDetalhesPost(Long id,String title, String author, String content ,Ligue ligue ,LocalDateTime createdAt){

    public DadosDetalhesPost(Post post){
        this(post.getId(),post.getTitle(), post.getAuthor(), post.getContent(),post.getLigue(),post.getCreatedAt());
    }
}
