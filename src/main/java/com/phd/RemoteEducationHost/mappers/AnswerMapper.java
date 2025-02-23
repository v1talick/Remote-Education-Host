package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.Answer;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.enteties.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt("answer_id"));
        answer.setFilePath((String) rs.getString("file_path"));
        answer.setGrade(rs.getObject("grade", Integer.class)); // nullable
        answer.setTaskDeliveryTime(rs.getTime("task_delivery_time"));
        Student student = new Student();
        student.setId(rs.getInt("student"));
        Task task = new Task();
        task.setId(rs.getInt("task"));
        if(rs.getMetaData().getColumnCount() > 6){
            Group group = new Group();
            group.setId(rs.getInt("group_"));
            student.setGroup(group);
            student.setBirthdayDate(rs.getDate("birthday_date"));
            student.setCreateAt(rs.getDate("creation_date"));
            student.setEmail(rs.getString("email"));
            student.setFirstName(rs.getString("firstname"));
            student.setLastName(rs.getString("lastname"));
            student.setPassword(rs.getString("encrypted_password"));
            task.setDescription(rs.getString("description"));
            task.setDeadline(rs.getDate("deadline"));
            task.setFilePath((String) rs.getObject("file_path"));
        }
        answer.setTask(task);
        answer.setStudent(student);
        return answer;
    }
}
