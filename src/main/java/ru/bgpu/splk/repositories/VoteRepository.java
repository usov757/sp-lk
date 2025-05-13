package ru.bgpu.splk.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.bgpu.splk.models.User;
import ru.bgpu.splk.models.Vote;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    public List<Vote> findAll();

}
