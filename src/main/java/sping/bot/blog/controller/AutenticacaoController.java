package sping.bot.blog.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sping.bot.blog.domain.user.DadosAutenticacao;
import sping.bot.blog.domain.user.User;
import sping.bot.blog.infra.security.DadosTokenJWT;
import sping.bot.blog.infra.security.TokenService;

@RestController
@RequestMapping("Login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
