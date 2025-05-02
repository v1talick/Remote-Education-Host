package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Group;

import java.util.List;

public interface GroupService {
    GroupDTO getGroupById(Integer id);

    List<GroupDTO> getAllGroups();

    void saveGroup(GroupDTO groupDTO);

    void updateGroup(GroupDTO groupDTO);

    void deleteGroupById(Integer id);
}
