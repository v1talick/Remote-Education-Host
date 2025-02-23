package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.mappers.ClassMapper;
import com.phd.RemoteEducationHost.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ClassRepositoryImpl implements ClassRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ClassMapper classMapper;
    @Override
    public Optional<Class> getClassById(int id) {
        String sql = "select * from classes where class_id=?";
        try {
            return Optional.of((Class) jdbcTemplate.queryForObject(sql, classMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Class> getClassWithDetailsById(int id) {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "join profiles p on p.profile_id=c.teacher " +
                "where class_id=?";
        try {
            return Optional.of((Class) jdbcTemplate.queryForObject(sql, classMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Class> getAllClasses() {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_";
        return jdbcTemplate.query(sql,classMapper);
    }

    @Override
    public List<Class> getClassesByTeacherId(int teacherId) {
        return null;
    }

    @Override
    public List<Class> getClassesByGroupId(int groupId) {
        return null;
    }

    @Override
    public void saveClass(Class aClass) {
        String sql = "INSERT INTO classes(" +
                "teacher, group_, discipline, active, started) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, aClass.getTeacher().getId(), aClass.getGroup().getId(),
                aClass.getDiscipline().getId(), aClass.isActive(), aClass.getStartedAt());
    }

    @Override
    public void updateClass(Class aClass) {
        String sql = "UPDATE classes\n" +
                "\tSET teacher=?, group_=?, discipline=?, active=?, started=?\n" +
                "\tWHERE class_id=?;";
        jdbcTemplate.update(sql, aClass.getTeacher().getId(), aClass.getGroup().getId(),
                aClass.getDiscipline().getId(), aClass.isActive(), aClass.getStartedAt(), aClass.getId());
    }

    @Override
    public void deleteClass(int classId) {
        String sql = "delete from classes where class_id=?";
        jdbcTemplate.update(sql, classId);
    }
}
