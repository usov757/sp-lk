package ru.bgpu.splk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.bgpu.splk.exceptions.NotFoundException;
import ru.bgpu.splk.exceptions.UnauthorizedException;
import ru.bgpu.splk.models.User;
import ru.bgpu.splk.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User getByName(String name) {
        return userRepository.findOneByName(name).orElseThrow(
                () -> new NotFoundException("User not found by name: "+name)
        );
    }

    public User getByLogin(String name) {
        return userRepository.findOneByLogin(name).orElseThrow(
                () -> new NotFoundException("User not found by login: "+name)
        );
    }

    public User sessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String userLogin = authentication.getName();
            return userRepository.findOneByLogin(userLogin).orElseThrow(
                    () -> new UnauthorizedException("In session not found user")
            );
        } else {
            throw new UnauthorizedException("Session not found");
        }
    }


}
