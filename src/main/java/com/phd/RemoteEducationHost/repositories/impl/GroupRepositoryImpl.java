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
    public Group getGroupById(@lombok.NonNull int id) {
        String sql = "select * from groups_ g where g.group_id=?";

        return (Group) jdbcTemplate.queryForObject(sql, groupMapper, id);
    }

    @Override
    public Group getGroupWithDetailsById(@NonNull int id) {
        String sql = "select * from groups_ g join specialties s on g.specialty=s.specialty_id where g.group_id=?";

        return (Group) jdbcTemplate.queryForObject(sql, groupMapper, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return jdbcTemplate.query("select * from groups_", groupMapper);
    }

    @Override
    public void saveGroup(Group group) {
        String sql = "insert into groups_ (group_name, creation_date, specialty) values (?, ?, ?)";
        jdbcTemplate.update(sql, group.getName(), group.getCreationDate(), group.getSpecialty().getId());
    }

    @Override
    public void updateGroup(Group group) {
        String sql = "update groups_ set group_name=?, creation_date=?, specialty=? where group_id=?";
        jdbcTemplate.update(sql, group.getName(), group.getCreationDate(), group.getSpecialty().getId(), group.getId());
    }

    @Override
    public void deleteGroup(int id) {
        String sql = "delete from groups_ where group_id=?";
        jdbcTemplate.update(sql, id);
    }
}
