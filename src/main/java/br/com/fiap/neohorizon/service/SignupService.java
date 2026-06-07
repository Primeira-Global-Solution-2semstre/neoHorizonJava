package br.com.fiap.neohorizon.service;

import br.com.fiap.neohorizon.dto.SignupRequest;
import br.com.fiap.neohorizon.dto.SignupResponse;
import br.com.fiap.neohorizon.entity.User;
import br.com.fiap.neohorizon.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public SignupResponse registerUser(SignupRequest signupRequest) {
        // Check if username already exists
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return new SignupResponse(
                    "Username já existe!",
                    null,
                    null,
                    false
            );
        }

        // Check if email already exists
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return new SignupResponse(
                    "Email ja existe!",
                    null,
                    null,
                    false
            );
        }

        // Create new user
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFullName(signupRequest.getFullName());

        // Save user
        try {
            userRepository.save(user);
            return new SignupResponse(
                    "Usuario cadastrado com sucesso!",
                    user.getUsername(),
                    user.getEmail(),
                    true
            );
        } catch (Exception e) {
            return new SignupResponse(
                    "Error registrando usuario: " + e.getMessage(),
                    null,
                    null,
                    false
            );
        }
    }
}