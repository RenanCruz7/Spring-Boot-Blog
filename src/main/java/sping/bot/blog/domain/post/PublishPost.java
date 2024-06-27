package sping.bot.blog.domain.post;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sping.bot.blog.infra.security.TokenService;

@Service
public class PublishPost {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TokenService jwtService;

    public Post publish(CreatePostDTO dados, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String author = jwtService.getSubject(token);

        var post = new Post(dados);
        post.setAuthor(String.valueOf(author));
        return postRepository.save(post);
    }

}
