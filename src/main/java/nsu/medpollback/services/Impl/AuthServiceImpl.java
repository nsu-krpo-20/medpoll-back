package nsu.medpollback.services.Impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.entities.User;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.UserRepository;
import nsu.medpollback.security.dto.JwtRequest;
import nsu.medpollback.security.dto.JwtResponse;
import nsu.medpollback.services.AuthService;
import nsu.medpollback.services.JwtProvider;
import nsu.medpollback.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException, NotFoundException {
        return null;
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException, NotFoundException {
        return null;
    }

    @Override
    public JwtResponse register(UserDto userDto) throws AuthException, NotFoundException, BadRequestException {
        if (userDto.getLogin() == null) {
            throw new BadRequestException("Login must be present");
        }
        if (userRepository.findByUid(userDto.getLogin()).isPresent()) {
            throw new AuthException("User already exists");
        }
        // todo check requirements for login
        userService.postUser(userDto);
        User user = userRepository.findByUid(userDto.getLogin()).orElseThrow(
                () -> new NotFoundException("Couldn't find user with uid: " + userDto.getLogin()));
        return getJwtResponseAndFillCookie(user);
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
        final String accessToken = jwtProvider.generateAccessToken(user);
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
