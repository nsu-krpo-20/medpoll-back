package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.entities.Role;
import nsu.medpollback.model.entities.User;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.RoleRepository;
import nsu.medpollback.repositories.UserRepository;
import nsu.medpollback.security.config.Encoder;
import nsu.medpollback.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Encoder passwordEncoder;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, Encoder passwordEncoder,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void postUser(UserDto userDto) throws NotFoundException {
        User user = mapper.map(userDto, User.class);
        user.setId(null);
        user.setLogin(userDto.getLogin());
        Role userRole = findRole("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(user.getPassword()));
        user.setEmail(userDto.getEmail());
        User savedUser = userRepository.save(user);
    }

    private Role findRole(String name) throws NotFoundException {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Couldn't find role " + name));
    }
}