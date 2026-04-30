package rs.ac.singidunum.itws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.model.LoginModel;
import rs.ac.singidunum.itws.model.TokenModel;
import rs.ac.singidunum.itws.service.AuthService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenModel> login(@RequestBody LoginModel model) {
        return ResponseEntity.ok(authService.login(model));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenModel> refresh(@RequestBody TokenModel model) {
        return ResponseEntity.ok(authService.refresh(model));
    }
}