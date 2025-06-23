package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Auth.LoginRequest;
import cl.ucm.bookapi.ApiBook.dto.Auth.LoginResponse;
import cl.ucm.bookapi.ApiBook.dto.Auth.RegisterRequest;
import cl.ucm.bookapi.ApiBook.models.Rol;
import cl.ucm.bookapi.ApiBook.models.User;
import cl.ucm.bookapi.ApiBook.models.UserRol;
import cl.ucm.bookapi.ApiBook.repository.RolRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import cl.ucm.bookapi.ApiBook.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceI {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        // 1. Obtener el rol "LECTOR"
        Rol rol = rolRepository.findByName("LECTOR")
                .orElseThrow(() -> new RuntimeException("Rol 'Lector' no encontrado"));

        // 2. Crear el usuario desde el DTO y Codificar la contraseña antes de guardar
        User user = new User(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Crear relación usuario-rol
        UserRol userRol = new UserRol();
        userRol.setUserFk(user);
        userRol.setRolFk(rol);

        // 4. Asignar el rol al usuario
        user.setUserRol(List.of(userRol));

        // 5. Guardar en base de datos (se guarda todo por cascade)
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findById(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Contraseña Incorrecta");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Usuario Deshabilitado");
        }

        String token = jwtUtil.create(user);
        System.out.println("JWT generado: " + token);
        return new LoginResponse(user, token);
    }
}
