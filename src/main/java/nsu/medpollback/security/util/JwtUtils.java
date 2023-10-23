package nsu.medpollback.security.util;

import io.jsonwebtoken.Claims;
import nsu.medpollback.model.entities.Role;
import nsu.medpollback.security.domain.JwtAuthentication;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtils {
    private JwtUtils() {
    }

    public static JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setLogin(claims.get("login", String.class));
        return jwtInfoToken;
    }

    @SuppressWarnings("unchecked")
    private static Set<Role> getRoles(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(Role::new).collect(Collectors.toSet());
    }
}