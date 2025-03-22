package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    Group getGroupById(int id);
    Group getGroupWithDetailsById(int id);
    List<Group> getAllGroups();
    void saveGroup(Group group);
    void updateGroup(Group group);
    void deleteGroup(int id);

}

