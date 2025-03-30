package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Group;

import java.util.List;

public interface GroupService {
    public GroupDTO getGroupById(Integer id);
    public List<GroupDTO> getAllGroups();
    public void saveGroup(GroupDTO groupDTO);
    public void updateGroup(GroupDTO groupDTO);
    public void deleteGroupById(Integer id);
}
