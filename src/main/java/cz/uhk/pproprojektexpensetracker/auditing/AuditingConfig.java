package cz.uhk.pproprojektexpensetracker.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    private static final Long SYSTEM_USER = 1L;

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(Objects.nonNull(SecurityContextHolder.getContext())
                && Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())
                ? getUserId()
                : SYSTEM_USER);
    }

    private Long getUserId() {
        //todo
        return SYSTEM_USER;
    }
}
