package cz.uhk.pproprojektexpensetracker.service.user;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.repository.UserRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<String> validateRegistration(User user) {
        List<String> errors = new ArrayList<>();
        ((UserRepository) repository).findByUsername(user.getUsername())
                .ifPresent(s -> errors.add("Uživatelské jméno již existuje."));
        ((UserRepository) repository).findByEmail(user.getEmail())
                .ifPresent(s -> errors.add("Email již existuje."));
        return errors;
    }

    @Override
    public User editUser(User user, User loggedUser) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(loggedUser.getPassword());
        }
        user.setUserRoles(loggedUser.getUserRoles());
        user.setEmail(loggedUser.getEmail());
        user.setUsername(loggedUser.getUsername());
        return super.update(user);
    }
}
