package cz.uhk.pproprojektexpensetracker.service.user;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.repository.UserRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    @Override
    public List<String> validateRegistration(User user) {
        List<String> errors = new ArrayList<>();
        ((UserRepository) repository).findByUsername(user.getUsername())
                .ifPresent(s -> errors.add("Uživatelské jméno již existuje."));
        ((UserRepository) repository).findByEmail(user.getEmail())
                .ifPresent(s -> errors.add("Email již existuje."));
        return errors;
    }
}
