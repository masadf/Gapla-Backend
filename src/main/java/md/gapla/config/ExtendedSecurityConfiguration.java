package md.gapla.config;

import lombok.RequiredArgsConstructor;
import md.kobalt.security.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor
public class ExtendedSecurityConfiguration {
    protected final JwtAuthenticationFilter jwtAuthFilter;
    protected final AuthenticationProvider authenticationProvider;

    private final CorsConfigurationSource corsConfigurationSource;


    @Bean
    public SecurityFilterChain securityExtendedFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf()
                .disable()
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .authorizeHttpRequests()
//        .requestMatchers("/api/v1/auth/**","/**")
                .requestMatchers("/api/v1/auth/**", "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
