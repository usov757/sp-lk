package ru.bgpu.splk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bgpu.splk.models.Group;
import ru.bgpu.splk.repositories.GroupRepository;

@Service
public class GroupService {

    @Autowired private GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }
}
