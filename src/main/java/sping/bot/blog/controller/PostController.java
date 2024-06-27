package sping.bot.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sping.bot.blog.domain.post.*;
import sping.bot.blog.domain.post.CreatePostDTO;
import java.util.List;

@RestController
@RequestMapping("Posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PublishPost publishPost;


    @PostMapping
    @Transactional
    public ResponseEntity PublishPost(@RequestBody CreatePostDTO data, HttpServletRequest request , UriComponentsBuilder uriBuilder){
        Post post = publishPost.publish(data,request);
        var uri = uriBuilder.path("/Posts/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsPostDTO(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsPostDTO> Detail(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        return  ResponseEntity.ok(new DetailsPostDTO(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> List(){
        var posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @PutMapping
    @Transactional
    public ResponseEntity Update(@RequestBody EditPostDTO data){
        var post = postRepository.getReferenceById(data.id());
        post.atualizar(data);

        return ResponseEntity.ok(new DetailsPostDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> Delete(@PathVariable Long id){
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
