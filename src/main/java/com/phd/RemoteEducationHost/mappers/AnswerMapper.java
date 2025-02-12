package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.Answer;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.enteties.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt("answer_id"));
        answer.setFilePath(rs.getString("file_path"));
        answer.setGrade(rs.getObject("grade", Integer.class)); // nullable
        answer.setTaskDeliveryTime(rs.getTime("task_delivery_time"));
        Student student = new Student();
        student.setId(rs.getInt("student"));
        Task task = new Task();
        task.setId(rs.getInt("task"));
        if(rowNum > 6){

        }
        answer.setTask(task);
        answer.setStudent(student);
        return answer;
    }
}
