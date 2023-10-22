package nsu.medpollback.security.controller;

import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.model.exceptions.UserException;
import nsu.medpollback.security.dto.JwtRequest;
import nsu.medpollback.security.dto.JwtResponse;
import nsu.medpollback.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.BASE_API_PATH)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<JwtResponse> registration(@RequestBody UserDto user) throws AuthException, NotFoundException,
            UserException, BadRequestException {
        final JwtResponse token = authService.register(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException,
            NotFoundException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(
            @CookieValue(value = "refreshToken") String refreshToken) throws AuthException, NotFoundException {
        final JwtResponse token = authService.refresh(refreshToken);
        return ResponseEntity.ok(token);
    }

}
