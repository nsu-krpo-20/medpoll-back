package nsu.medpollback.security.controller;

import jakarta.validation.Valid;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.model.exceptions.UserException;
import nsu.medpollback.security.dto.JwtRequest;
import nsu.medpollback.security.dto.JwtResponse;
import nsu.medpollback.security.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Valid
@RequestMapping(Constants.BASE_API_PATH + "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public JwtResponse registration(@RequestBody UserDto user) throws AuthException, NotFoundException,
            BadRequestException {
        return authService.register(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest authRequest) throws AuthException,
            NotFoundException {
        return authService.login(authRequest);
    }

    @PostMapping("/refresh")
    public JwtResponse getNewRefreshToken(
            @CookieValue(value = "refreshToken") String refreshToken) throws AuthException, NotFoundException {
        return authService.refresh(refreshToken);
    }

    @PostMapping("/check")
    public ResponseEntity<Void> checkJwt(@RequestBody JwtResponse jwt) {
        return authService.checkJwt(jwt);
    }

}
