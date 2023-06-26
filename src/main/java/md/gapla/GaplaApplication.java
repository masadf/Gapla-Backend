package md.gapla;

import md.kobalt.security.config.ApplicationConfig;
import md.kobalt.security.config.JwtAuthenticationFilter;
import md.kobalt.security.config.JwtService;
import md.kobalt.security.config.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AutoConfiguration
@Import({ ApplicationConfig.class })
//@Import({SecurityConfiguration.class,JwtAuthenticationFilter.class, UserDetailsService.class})
public class GaplaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaplaApplication.class, args);
	}

}
