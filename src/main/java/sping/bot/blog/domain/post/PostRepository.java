package sping.bot.blog.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository é uma interface que estende JpaRepository
// JpaRepository é uma interface que possui métodos para manipular dados no banco de dados
// JpaRepository recebe dois parâmetros, o tipo da entidade e o tipo do id da entidade
// Neste caso, a entidade é Post e o id é Long

public interface PostRepository extends JpaRepository<Post, Long> {
}