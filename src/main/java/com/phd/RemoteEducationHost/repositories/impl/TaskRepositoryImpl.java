package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Task;
import com.phd.RemoteEducationHost.mappers.rowmappers.TaskRowMapper;
import com.phd.RemoteEducationHost.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TaskRowMapper taskRowMapper;

    @Override
    public Task getTaskById(Integer id) {
        String sql = "select * from tasks " +
                "where task_id=?";

        return (Task) jdbcTemplate.queryForObject(sql, taskRowMapper, id);
    }

    @Override
    public Task getTaskWithDetailsById(Integer id) {
        String sql = "select * from tasks t " +
                "join classes c on t.class_=c.class_id " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_ " +
                "where task_id=?";

        return (Task) jdbcTemplate.queryForObject(sql, taskRowMapper, id);
    }

    @Override
    public List<Task> getAllTasksByClassId(Integer classId) {
        String sql = "select * from tasks t " +
                "join classes c on t.class_=c.class_id " +
                "join disciplines d on d.discipline_id=c.discipline " +
                "join teachers t on t.teacher_id=c.teacher " +
                "join profiles p on p.profile_id=c.teacher " +
                "join groups_ g on g.group_id=c.group_  " +
                "where class_=?";
        return jdbcTemplate.query(sql, taskRowMapper, classId);
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = "select * from tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }

    @Override
    public void saveTask(Task task) {
        String sql = "INSERT INTO tasks(\n" +
                "\t class_, description, file_path, deadline)\n" +
                "\t VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, task.getAClass().getId(),task.getDescription()
                , task.getFilePath(), task.getDeadline());
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE public.tasks\n" +
                "\tSET class_=?, description=?, file_path=?, deadline=?\n" +
                "\tWHERE task_id=?;";
        jdbcTemplate.update(sql, task.getAClass().getId(),task.getDescription()
                , task.getFilePath(), task.getDeadline(), task.getId());
    }

    @Override
    public void deleteTask(Integer taskId) {
        String sql =  "delete from tasks where task_id=?;";
        jdbcTemplate.update(sql, taskId);
    }
}
