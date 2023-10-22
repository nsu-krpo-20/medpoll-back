package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void postUser(UserDto userDto) throws NotFoundException, BadRequestException {

    }
}
