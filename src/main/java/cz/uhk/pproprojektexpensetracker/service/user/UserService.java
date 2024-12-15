package cz.uhk.pproprojektexpensetracker.service.user;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;


public interface UserService extends AbstractService<User> {
    List<String> validateRegistration(User user);

    User editUser(User user, User loggedUser);
}
