package nsu.medpollback.services;

import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;

public interface UserService {
    void postUser(UserDto userDto) throws NotFoundException, BadRequestException;
}
