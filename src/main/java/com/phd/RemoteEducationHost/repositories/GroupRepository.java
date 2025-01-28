package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    Optional<Group> getGroupById(int id);
    Optional<Group> getGroupWithDetailsById(int id);
    List<Group> getAllGroups();
    void saveGroup(Group group);
    void updateGroup(Group group);

}

