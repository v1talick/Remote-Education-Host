package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
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
    public GroupDTO getGroupById(Integer id) {
        return GroupMapper.groupToGroupDTO(groupRepository.getGroupById(id));
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        return groupRepository.getAllGroups().stream().map(GroupMapper::groupToGroupDTO).toList();
    }

    @Override
    public void saveGroup(GroupDTO groupDTO) {
        groupRepository.saveGroup(GroupMapper.groupDTOtoGroup(groupDTO));
    }

    @Override
    public void updateGroup(GroupDTO groupDTO) {
        groupRepository.updateGroup(GroupMapper.groupDTOtoGroup(groupDTO));
    }

    @Override
    public void deleteGroupById(Integer id) {
        groupRepository.deleteGroup(id);
    }
}
