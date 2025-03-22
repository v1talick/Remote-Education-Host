package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Lesson;
import com.phd.RemoteEducationHost.mappers.LessonMapper;
import com.phd.RemoteEducationHost.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public class LessonRepositoryImpl implements LessonRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    LessonMapper lessonMapper;

    @Override
    public Lesson getLessonById(int id) {
        String sql = "select * from lessons " +
                "where lesson_id=?";

        return (Lesson) jdbcTemplate.queryForObject(sql, lessonMapper, id);
    }

    @Override
    public Lesson getLessonWithDetailsById(int id) {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where l.lesson_id=?";

        return (Lesson) jdbcTemplate.queryForObject(sql, lessonMapper, id);
    }

    @Override
    public List<Lesson> getAllLessons() {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_" ;
        return jdbcTemplate.query(sql, lessonMapper);
    }

    @Override
    public List<Lesson> getLessonsByDay(DayOfWeek day) {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where week_day=?";
        return jdbcTemplate.query(sql, lessonMapper, day.getValue());
    }

    @Override
    public List<Lesson> getLessonsByGroupId(int groupId) {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where c.group_=?";
        return jdbcTemplate.query(sql, lessonMapper, groupId);
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(int teacherId) {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where c.teacher=?";
        return jdbcTemplate.query(sql, lessonMapper, teacherId);
    }

    @Override
    public List<Lesson> getLessonsByClassId(int classId) {
        String sql = "select * from lessons l " +
                "join classes c on c.class_id=l.class_ " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where l.class_=?";
        return jdbcTemplate.query(sql, lessonMapper, classId);
    }

    @Override
    public Lesson getLessonByGroupIdAndDay(int groupId, DayOfWeek day) {
        return null;
    }

    @Override
    public void saveLesson(Lesson lesson) {
        String sql = "INSERT INTO lessons(\n" +
                "\tweek_day, lesson_number, class_, lesson_type, lesson_link)\n" +
                "\tVALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, lesson.getDayOfWeek().getValue(), lesson.getLessonNumber(),
                lesson.getAClass().getId(), lesson.getLessonType().toString(), lesson.getLessonLink());
    }

    @Override
    public void updateLesson(Lesson lesson) {
        String sql = "UPDATE lessons\n" +
                "\tSET week_day=?, lesson_number=?, class_=?, lesson_type=?, lesson_link=?\n" +
                "\tWHERE lesson_id=?;";
        jdbcTemplate.update(sql, lesson.getDayOfWeek().getValue(), lesson.getLessonNumber(),
                lesson.getAClass().getId(), lesson.getLessonType().toString(), lesson.getLessonLink(), lesson.getId());
    }

    @Override
    public void deleteLesson(int lessonId) {
        String sql = "delete from lessons where lesson_id=?";
        jdbcTemplate.update(sql, lessonId);
    }
}
