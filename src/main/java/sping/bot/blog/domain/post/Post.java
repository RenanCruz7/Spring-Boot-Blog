package sping.bot.blog.domain.post;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "posts")
@Entity(name = "Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    @Enumerated(EnumType.STRING)
    private Ligue ligue;
    private LocalDateTime createdAt;

    public Post(CreatePostDTO dados) {
        this.title = dados.title();
        this.content = dados.content();
        this.ligue = dados.ligue();
        this.createdAt = LocalDateTime.now();
    }


    public void atualizar(EditPostDTO dados){

        if(dados.title() != null){
            this.title = dados.title();
        }

        if(dados.content() != null){
            this.content = dados.content();
        }
        if(dados.ligue() != null){
            this.ligue = dados.ligue();
        }
    }
}