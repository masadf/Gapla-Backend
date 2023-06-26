package md.gapla.config;

import lombok.RequiredArgsConstructor;
import md.gapla.mapper.AppMapper;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.repository.account.AccountRepository;
import md.kobalt.security.user.JwtUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@AutoConfiguration
@Configuration
@RequiredArgsConstructor
//@Import({ ApplicationConfig.class })
//@ComponentScan(basePackages = { "md.kobalt.security.config.ApplicationConfig"})
public class CommonConfig {
    private final AccountRepository accountRepository;
    private final AppMapper appMapper;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> getJwtUserDetails(username);
    }

    private JwtUserDetails getJwtUserDetails(String userName) {
        AccountEntity accountEntity = accountRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        JwtUserDetails jwtUserDetails = appMapper.mapToDetails(accountEntity);
        jwtUserDetails.setRole(accountEntity.getRoles().get(0).getAccountRoleName().getValue());
        return jwtUserDetails;
    }

}
