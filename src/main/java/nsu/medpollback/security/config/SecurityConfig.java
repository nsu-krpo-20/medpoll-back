package nsu.medpollback.security.config;

import lombok.RequiredArgsConstructor;
import nsu.medpollback.config.Constants;
import nsu.medpollback.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests -> requests.requestMatchers(Constants.BASE_API_PATH + "/auth/*").permitAll().requestMatchers(
                        Constants.BASE_API_PATH + "/cards/**").permitAll().requestMatchers(
                        Constants.BASE_API_PATH + "/prescriptions/**").permitAll().anyRequest().authenticated()).addFilterAfter(
                jwtFilter, UsernamePasswordAuthenticationFilter.class).logout(LogoutConfigurer::permitAll);
        http.cors(configurer -> configurer.configurationSource(request -> {
            var out = new CorsConfiguration().applyPermitDefaultValues();
            out.setAllowedOriginPatterns(List.of("http://localhost:*"));
            out.setAllowCredentials(true);
            return out;
        }));
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


}

