package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
import com.phd.RemoteEducationHost.repositories.GroupRepository;
import com.phd.RemoteEducationHost.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public GroupDTO getGroupById(Integer id) {
        return GroupMapper.groupToGroupDTO(groupRepository.getGroupWithDetailsById(id));
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        return groupRepository.getAllGroups().stream().map(GroupMapper::groupToGroupDTO).toList();
    }

    @Override
    public void saveGroup(GroupDTO groupDTO) {
        try {
            groupRepository.saveGroup(GroupMapper.groupDTOtoGroup(groupDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Group with such name already exists");
        }
    }

    @Override
    public void updateGroup(GroupDTO groupDTO) {
        try {
            groupRepository.updateGroup(GroupMapper.groupDTOtoGroup(groupDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Group with such name already exists");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Invalid Group data " + e.getMessage());
        }
    }

    @Override
    public void deleteGroupById(Integer id) {
        try {
            groupRepository.deleteGroup(id);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Group with such id does is used in other entities");
        }
    }
}
