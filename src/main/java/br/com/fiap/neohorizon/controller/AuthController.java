package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.LoginRequest;
import br.com.fiap.neohorizon.dto.LoginResponse;
import br.com.fiap.neohorizon.dto.SignupRequest;
import br.com.fiap.neohorizon.dto.SignupResponse;
import br.com.fiap.neohorizon.service.JwtService;
import br.com.fiap.neohorizon.service.SignupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autentificação")
public class AuthController {
    private final JwtService jwtService;
    private final SignupService signupService;

    public AuthController(JwtService jwtService, SignupService signupService) {
        this.jwtService = jwtService;
        this.signupService = signupService;
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

        if ("admin".equals(request.getUsername())
                && "1234".equals(request.getPassword())) {

            String token =
                    jwtService.generateToken(request.getUsername());

            return new LoginResponse(token);
        }

        throw new RuntimeException("Invalid credentials");
    }
    @PostMapping("/signup")
    @Operation(
            summary = "Cadastrar novo usuario",
            description = "Cria um novo usuario com nome, email e senha"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data enviada invalida"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Username ou email ja existe"
            )
    })
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        SignupResponse response = signupService.registerUser(signupRequest);

        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
