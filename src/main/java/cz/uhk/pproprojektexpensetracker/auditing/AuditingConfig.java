package cz.uhk.pproprojektexpensetracker.auditing;

import cz.uhk.pproprojektexpensetracker.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class AuditingConfig {

    private static final Long SYSTEM_USER = 1L;

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(Objects.nonNull(SecurityContextHolder.getContext())
                && Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())
                ? getUserId()
                : SYSTEM_USER);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return ((User) authentication.getPrincipal()).getId();
            }
        }
        return SYSTEM_USER;
    }
}
