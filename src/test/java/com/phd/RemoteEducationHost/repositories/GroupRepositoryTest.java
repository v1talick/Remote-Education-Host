package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void saveGroupTest() {
        Specialty specialty = new Specialty();
        specialty.setId(1);
        Group group = new Group(0, specialty, "testName", new Date());
        groupRepository.saveGroup(group);
        assertEquals(7, groupRepository.getAllGroups().size());
    }

    @Test
    public void getGroupByIdTest() {
        Group group = groupRepository.getGroupById(1);
        assertEquals("CS-101", group.getName());
    }

    @Test
    public void updateGroupTest() {
        Specialty specialty = new Specialty();
        specialty.setId(2);
        Group group = new Group(2, specialty, "testName2", new Date());
        groupRepository.updateGroup(group);
        assertEquals("testName2", groupRepository.getGroupById(2).getName());
    }

    @Test
    public void deleteGroupTest() {
        groupRepository.deleteGroup(7);
    }
}
