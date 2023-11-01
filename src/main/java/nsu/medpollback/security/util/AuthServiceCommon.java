package nsu.medpollback.security.util;

import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.UserDto;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.security.domain.JwtAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthServiceCommon {
    public static JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean checkAuthorities(String login) {
        JwtAuthentication principal = getAuthInfo();
        if (principal == null) {
            return false;
        }
        return isContainsRole(principal.getAuthorities(), "ADMIN") || isContainsRole(principal.getAuthorities(),
                                                                                     "DOCTOR") || principal.getLogin().equals(
                login);
    }

    public static String getUserLogin() {
        JwtAuthentication principal = getAuthInfo();
        if (principal == null) {
            return null;
        }
        return principal.getLogin();
    }

    private static boolean isContainsRole(Collection<? extends GrantedAuthority> authorities, String name) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority() != null && authority.getAuthority().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSamePerson(String userUid) {
        JwtAuthentication principal = AuthServiceCommon.getAuthInfo();
        return principal.getLogin().equals(userUid);
    }

    public static void checkRegisterConstraints(UserDto dto) throws BadRequestException {
        if (!isLoginValid(dto.getLogin())) {
            throw new BadRequestException(
                    "Invalid login, must be  " + Constants.LOGIN_MIN_SYMBOLS + "-" + Constants.LOGIN_MAX_SYMBOLS + " " +
                            "symbols, and contain valid symbols");
        }
        if (!isPasswordValid(dto.getPassword())) {
            throw new BadRequestException(
                    "Invalid password, must be  " + Constants.PASSWORD_MIN_SYMBOLS + "-" + Constants.PASSWORD_MAX_SYMBOLS + " symbols, and contain at least 1 digit and 1 non-digit");
        }
    }

    private static boolean isLoginValid(String login) {
        int len = login.length();
        boolean match = login.matches(Constants.LOGIN_PATTERN);
        return len >= Constants.LOGIN_MIN_SYMBOLS && len <= Constants.LOGIN_MAX_SYMBOLS && match;
    }

    private static boolean isPasswordValid(String password) {
        int len = password.length();
        boolean match = password.matches(Constants.PASSWORD_PATTERN);
        return len >= Constants.PASSWORD_MIN_SYMBOLS && len <= Constants.PASSWORD_MAX_SYMBOLS && match;
    }

    private static boolean isEmailValid(String email) {
        return email.matches(Constants.EMAIL_PATTERN);
    }

}
