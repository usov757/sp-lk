package ru.bgpu.splk.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bgpu.splk.models.Group;
import ru.bgpu.splk.models.User;
import ru.bgpu.splk.models.Vote;
import ru.bgpu.splk.services.GroupService;
import ru.bgpu.splk.services.UserService;
import ru.bgpu.splk.services.VoteService;

import java.util.Collections;
import java.util.Random;


@Configuration
public class DevConfiguration {

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private VoteService voteService;


    private String[] names ={"John", "Alex", "Stepan", "Anton", "Ivan"};
    private String[] surnames ={"Johnson", "Alexson", "Stepson"};
    private String[] voteNames = {"Vote1", "Vote2", "Vote3", "Vote4"}; // Названия голосов
    @Bean
    public CommandLineRunner init() {
        return (arg) -> {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10) + 10; i++) {
                userService.save(new User(
                        names[random.nextInt(names.length)],
                        surnames[random.nextInt(surnames.length)]
                ));
            }
            Group adminGroup = new Group();
            adminGroup.setName("ADMIN");
            groupService.save(adminGroup);
            User admin = new User();
            admin.setLogin("admin");
            admin.setGroups(Collections.singletonList(adminGroup));
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            admin.setPassword(encoder.encode("admin"));
            userService.save(admin);

            for (String voteName : voteNames) {
                Vote vote = new Vote();
                vote.setName(voteName);
                vote.setCount(0);
                voteService.save(vote);
            }
        };
    }
}
