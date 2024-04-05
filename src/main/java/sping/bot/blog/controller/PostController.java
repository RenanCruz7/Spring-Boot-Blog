package sping.bot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sping.bot.blog.post.DadosCadastroPost;
import sping.bot.blog.post.Post;
import sping.bot.blog.post.PostRepository;

@RestController
@RequestMapping("Posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    @Transactional
    public void publicarPost(@RequestBody DadosCadastroPost dados){
        postRepository.save(new Post(dados));
    }

    @GetMapping
    public void listar(){
    }

}
