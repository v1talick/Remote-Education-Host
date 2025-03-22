package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.repositories.GroupRepository;
import com.phd.RemoteEducationHost.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    @Override
    public GroupDTO getGroupById(int id) {
        return null;
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        return null;
    }

    @Override
    public void saveGroup(GroupDTO groupDTO) {

    }

    @Override
    public void updateGroup(GroupDTO groupDTO) {

    }

    @Override
    public void deleteGroupById(int id) {

    }
}
