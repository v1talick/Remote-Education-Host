package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.mappers.rowmappers.ClassRowMapper;
import com.phd.RemoteEducationHost.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRepositoryImpl implements ClassRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ClassRowMapper classRowMapper;
    @Override
    public Class getClassById(Integer id) {
        String sql = "select * from classes where class_id=?";

        return (Class) jdbcTemplate.queryForObject(sql, classRowMapper, id);
    }

    @Override
    public Class getClassWithDetailsById(Integer id) {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "join profiles p on p.profile_id=c.teacher " +
                "where class_id=?";
        return (Class) jdbcTemplate.queryForObject(sql, classRowMapper, id);
    }

    @Override
    public List<Class> getAllClasses() {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_";
        return jdbcTemplate.query(sql, classRowMapper);
    }

    @Override
    public List<Class> getClassesByTeacherId(Integer teacherId) {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where c.teacher=?";
        return jdbcTemplate.query(sql, classRowMapper, teacherId);
    }

    @Override
    public List<Class> getClassesByGroupId(Integer groupId) {
        String sql = "select * from classes c " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where c.group_=?";
        return jdbcTemplate.query(sql, classRowMapper, groupId);
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
    public void deleteClass(Integer classId) {
        String sql = "delete from classes where class_id=?";
        jdbcTemplate.update(sql, classId);
    }
}
