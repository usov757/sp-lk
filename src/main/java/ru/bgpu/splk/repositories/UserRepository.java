package ru.bgpu.splk.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.bgpu.splk.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAll();

    public Optional<User> findOneByName(String name);

    Optional<User> findOneByLogin(String login);
}
