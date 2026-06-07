package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.LoginRequest;
import br.com.fiap.neohorizon.dto.LoginResponse;
import br.com.fiap.neohorizon.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autentificação")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Retorna um token",
            description = "Ao receber um username a senha, retorna um token que pode ser usado para realizar as chamadas do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Username ou senha incorreto."
            )
    })
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
