package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
import com.phd.RemoteEducationHost.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public Optional<Group> getGroupById(@lombok.NonNull int id) {
        String sql = "select * from groups_ g where g.group_id=?";
        try {
            return Optional.of((Group) jdbcTemplate.queryForObject(sql, groupMapper, id));
        } catch (EmptyResultDataAccessException e) {
//            System.out.println("Group with id " + id + " not found");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Group> getGroupWithDetailsById(@NonNull int id) {
        String sql = "select * from groups_ g join specialties s on g.specialty=s.specialty_id where g.group_id=?";
        try {
            return Optional.of((Group) jdbcTemplate.queryForObject(sql, groupMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();

        }
    }

    @Override
    public List<Group> getAllGroups() {
        return jdbcTemplate.query("select * from groups_", groupMapper);
    }

    @Override
    public void saveGroup(Group group) {
        String sql = "insert into groups_ (group_id, group_name, creation_date, specialty) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, group.getId(), group.getName(), group.getCreationDate(), group.getSpecialty().getId());
    }

    @Override
    public void updateGroup(Group group) {
        String sql = "update groups_ set group_name=?, creation_date=?, specialty=? where group_id=?";
        jdbcTemplate.update(sql, group.getName(), group.getCreationDate(), group.getSpecialty().getId(), group.getId());
    }
}
