package nsu.medpollback.security.services;

import lombok.NonNull;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.security.dto.JwtRequest;
import nsu.medpollback.security.dto.JwtResponse;

public interface AuthService {
    JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException, NotFoundException;

    JwtResponse refresh(@NonNull String refreshToken) throws AuthException, NotFoundException;

    JwtResponse register(UserDto userDto) throws AuthException, NotFoundException, BadRequestException;
}
