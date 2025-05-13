package ru.bgpu.splk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bgpu.splk.repositories.UserRepository;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByLogin: " + username);
        ru.bgpu.splk.models.User user =  userRepository.findOneByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        return new User(
                user.getLogin(), user.getPassword(),
                true, true, true, true,
                user.getGroups().stream().map(
                        g -> new SimpleGrantedAuthority(g.getName())
                ).collect(Collectors.toList())
        );
    }
}
