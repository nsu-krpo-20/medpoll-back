package nsu.medpollback.security.services.impl;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.entities.User;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.UserRepository;
import nsu.medpollback.security.config.Encoder;
import nsu.medpollback.security.dto.JwtRequest;
import nsu.medpollback.security.dto.JwtResponse;
import nsu.medpollback.security.services.AuthService;
import nsu.medpollback.security.services.JwtProvider;
import nsu.medpollback.security.util.AuthServiceCommon;
import nsu.medpollback.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final Encoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, JwtProvider jwtProvider,
                           Encoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Void> checkJwt(JwtResponse jwt) {
        if (!(jwtProvider.validateAccessToken(jwt.getAccessToken()) && jwtProvider.validateRefreshToken(jwt.getRefreshToken()))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @Transactional
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException, NotFoundException {
        User user = findUserByLogin(authRequest.getLogin());
        try {
        }

        if (passwordEncoder.getPasswordEncoder().matches(authRequest.getPassword(), user.getPassword())) {
            return getJwtResponseAndFillCookie(user);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    @Override
    @Transactional
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException, NotFoundException {
        if (!jwtProvider.validateRefreshToken(refreshToken)) {
            throw new AuthException("Invalid JWT");
        }
        Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        String login = claims.getSubject();
        User user = findUserByLogin(login);
        return getJwtResponseAndFillCookie(user);
    }

    @Override
    @Transactional
    public JwtResponse register(UserDto userDto) throws AuthException, NotFoundException, BadRequestException {
        if (userDto.getLogin() == null) {
            throw new BadRequestException("Login is null");
        }
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new AuthException("User already exists");
        }
        AuthServiceCommon.checkRegisterConstraints(userDto);
        userService.postUser(userDto);
        User user = findUserByLogin(userDto.getLogin());
        return getJwtResponseAndFillCookie(user);
    }

    private User findUserByLogin(String login) throws NotFoundException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("Couldn't find user with uid: " + login));
    }

    private User findUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Couldn't find user with email: " + email));
    }

    private JwtResponse getJwtResponseAndFillCookie(User user) {
        JwtResponse jwtResponse;
        jwtResponse = getJwtResponse(user);
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletResponse response = requestAttributes.getResponse();
            if (response != null) {
                final String refreshToken = jwtProvider.generateRefreshToken(user);
                fillRefreshTokenCookie(response, refreshToken);
            }
        }
        return jwtResponse;
    }

    private JwtResponse getJwtResponse(User user) {
        String accessToken = jwtProvider.generateAccessToken(user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.accessToken(accessToken);
        return jwtResponse;
    }

    private static void fillRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days in seconds
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);
    }
}
