package sping.bot.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sping.bot.blog.domain.post.*;
import sping.bot.blog.domain.post.DadosCadastroPost;
import java.util.List;

@RestController
@RequestMapping("Posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PublicarPost publicarPost;


    @PostMapping
    @Transactional
    public ResponseEntity publicarPost(@RequestBody DadosCadastroPost dados, HttpServletRequest request , UriComponentsBuilder uriBuilder){
        Post post = publicarPost.publicar(dados,request);
        var uri = uriBuilder.path("/Posts/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhesPost> detalhar(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        return  ResponseEntity.ok(new DadosDetalhesPost(post));
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
