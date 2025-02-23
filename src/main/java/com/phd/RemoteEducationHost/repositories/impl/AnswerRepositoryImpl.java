package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Answer;
import com.phd.RemoteEducationHost.mappers.AnswerMapper;
import com.phd.RemoteEducationHost.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public Optional<Answer> getAnswerById(int id) {
        String sql = "select * from answers a " +
                "join students s on s.student_id=a.student " +
                "join profiles p on p.profile_id=a.student " +
                "join tasks t on t.task_id=a.task " +
                "where a.answer_id=?";
        try {
            return Optional.of((Answer) jdbcTemplate.queryForObject(sql, answerMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Answer> getAnswersByStudentId(int studentId) {
        String sql = "select * from answers a " +
                "join students s on s.student_id=a.student " +
                "join profiles p on p.profile_id=a.student " +
                "join tasks t on t.task_id=a.task " +
                "where s.student_id=?";
        return jdbcTemplate.query(sql, answerMapper, studentId);
    }

    @Override
    public List<Answer> getAnswersByTaskId(int taskId) {
        String sql = "select * from answers a " +
                "join students s on s.student_id=a.student " +
                "join profiles p on p.profile_id=a.student " +
                "join tasks t on t.task_id=a.task " +
                "where a.task=?";
        return jdbcTemplate.query(sql, answerMapper, taskId);
    }

    @Override
    public List<Answer> getAllAnswers() {
        String sql = "select * from answers a " +
                "join students s on s.student_id=a.student " +
                "join profiles p on p.profile_id=a.student " +
                "join tasks t on t.task_id=a.task";
        return jdbcTemplate.query(sql, answerMapper);
    }

    @Override
    public void saveAnswer(Answer answer) {
        String sql = "INSERT INTO answers(\n" +
                "\t task, student, grade, file_path, task_delivery_time)\n" +
                "\t VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, answer.getTask().getId(), answer.getStudent().getId(),
                answer.getGrade(), answer.getFilePath(), answer.getTaskDeliveryTime());
    }

    @Override
    public void updateAnswer(Answer answer) {
        String sql = "UPDATE public.answers\n" +
                "\tSET task=?, student=?, grade=?, file_path=?, task_delivery_time=?\n" +
                "\tWHERE answer_id=?;";
        jdbcTemplate.update(sql, answer.getTask().getId(), answer.getStudent().getId(),
                answer.getGrade(), answer.getFilePath(), answer.getTaskDeliveryTime(), answer.getId());
    }

    @Override
    public void deleteAnswer(int answerId) {
        String sql = "delete from answers where answer_id=?";
        jdbcTemplate.update(sql, answerId);
    }
}
