package sping.bot.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Posts")
public class PostController {


    @PostMapping
    public void publicarPost(){

    }

    @GetMapping
    public void listar(){

    }


}
