package ru.bgpu.splk.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.bgpu.splk.models.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
