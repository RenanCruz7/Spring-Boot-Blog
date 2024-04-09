package sping.bot.blog.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "posts")
@Entity(name = "Post")
@Getter
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

    public Post(DadosCadastroPost dados) {
        this.title = dados.title();
        this.content = dados.content();
        this.author = dados.author();
        this.ligue = dados.ligue();
        this.createdAt = LocalDateTime.now();
    }

    public void atualizar(DadosEditaPost dados){

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