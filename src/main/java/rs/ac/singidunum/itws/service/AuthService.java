package rs.ac.singidunum.itws.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.itws.entity.User;
import rs.ac.singidunum.itws.model.LoginModel;
import rs.ac.singidunum.itws.model.TokenModel;
import rs.ac.singidunum.itws.repo.UserRepository;
import rs.ac.singidunum.itws.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenModel login(LoginModel model) {
        User user = userRepository.findByUsernameAndIsActiveTrue(model.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        boolean passwordOk = passwordEncoder.matches(
                model.getPassword(),
                user.getPassword()
        );

        if (!passwordOk) {
            throw new RuntimeException("Invalid username or password");
        }

        String access = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        TokenModel token = new TokenModel();
        token.setAccess(access);
        token.setRefresh(refresh);
        token.setUsername(user.getUsername());
        return token;
    }

    public TokenModel refresh(TokenModel model) {
        String oldRefreshToken = model.getRefresh();

        if (oldRefreshToken == null || oldRefreshToken.isBlank()) {
            throw new RuntimeException("Refresh token is required");
        }

        if (!jwtService.isTokenValid(oldRefreshToken) || !jwtService.isRefreshToken(oldRefreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = jwtService.extractUsername(oldRefreshToken);

        User user = userRepository.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TokenModel token = new TokenModel();
        token.setAccess(jwtService.generateAccessToken(user));
        token.setRefresh(oldRefreshToken);
        token.setUsername(user.getUsername());
        return token;
    }
}