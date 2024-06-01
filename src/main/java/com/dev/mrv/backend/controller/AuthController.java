package com.dev.mrv.backend.controller; 

import com.dev.mrv.backend.dto.AuthenticationRequest;
import com.dev.mrv.backend.dto.AuthenticationResponse;
import com.dev.mrv.backend.dto.PessoaDTO;
import com.dev.mrv.backend.dto.RegisterDTO;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.repository.PessoaRepository;
import com.dev.mrv.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;


    private final UserDetailsService userDetailsService;


    private final JwtUtil jwtUtil;


    private final PessoaRepository pessoaRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, PessoaRepository pessoaRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(registerDTO.getNome());
        pessoa.setNomeUsuario(registerDTO.getNomeUsuario());
        pessoa.setEmail(registerDTO.getEmail());
        pessoa.setSenha(passwordEncoder.encode(registerDTO.getSenha()));
        pessoa.setDataNascimento(registerDTO.getDataNascimento());

        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(new PessoaDTO(savedPessoa));
    }
}
