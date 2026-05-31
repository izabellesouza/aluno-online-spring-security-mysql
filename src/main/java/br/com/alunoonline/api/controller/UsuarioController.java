package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.DadosCadastroUsuario;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastros")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        if (usuarioRepository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado.");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCriptografada);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }
}