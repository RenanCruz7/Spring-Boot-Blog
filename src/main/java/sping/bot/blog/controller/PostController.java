package sping.bot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sping.bot.blog.post.*;

import java.util.List;

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
    public ResponseEntity<List<Post>> listar(){
        var posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosEditaPost dados){
        var post = postRepository.getReferenceById(dados.id());
        post.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhesPost(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
