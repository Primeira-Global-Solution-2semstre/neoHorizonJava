package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.LoginRequest;
import br.com.fiap.neohorizon.dto.LoginResponse;
import br.com.fiap.neohorizon.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        // Replace with DB validation later

        if ("admin".equals(request.getUsername())
                && "1234".equals(request.getPassword())) {

            String token =
                    jwtService.generateToken(request.getUsername());

            return new LoginResponse(token);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
