package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Answer;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.enteties.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class AnswerRepositoryTest {
    @Autowired
    AnswerRepository answerRepository;

    @Test
    public void getAnswerByIdTest() {
        Answer answerFromDb = answerRepository.getAnswerById(2).get();
        assertEquals(90, answerFromDb.getGrade());
        assertEquals("alice.smith@example.com", answerFromDb.getStudent().getEmail());
        assertEquals("Implement a sorting algorithm.", answerFromDb.getTask().getDescription());
        assertTrue(answerRepository.getAnswerById(90).isEmpty());
    }
    @Test
    public void getAnswersByTaskIdTest() {
        Answer answerFromDb = answerRepository.getAnswersByTaskId(1).get(0);
        assertEquals(1, answerRepository.getAnswersByTaskId(1).size());
        assertEquals(90, answerFromDb.getGrade());
        assertEquals("alice.smith@example.com", answerFromDb.getStudent().getEmail());
        assertEquals("Implement a sorting algorithm.", answerFromDb.getTask().getDescription());
    }
    @Test
    public void getAnswersByStudentIdTest() {
        Answer answerFromDb = answerRepository.getAnswersByStudentId(2).get(0);
        assertEquals(85, answerFromDb.getGrade());
        assertEquals("bob.johnson@example.com", answerFromDb.getStudent().getEmail());
        assertEquals("Analyze a given digital signal.", answerFromDb.getTask().getDescription());
    }
    @Test
    public void saveAnswerTest() {
        Answer newAnswer = new Answer();
        newAnswer.setId(3);
        newAnswer.setGrade(60);
        newAnswer.setTaskDeliveryTime(new Date());
        Task task = new Task();
        task.setId(2);
        newAnswer.setTask(task);
        Student student = new Student();
        student.setId(1);
        newAnswer.setStudent(student);

        answerRepository.saveAnswer(newAnswer);

        assertEquals(3, answerRepository.getAllAnswers().size());
    }
    @Test
    public void updateAnswerTest() {
        Answer updatedAnswer = answerRepository.getAnswerById(3).get();
        updatedAnswer.setGrade(66);
        assertEquals(66, updatedAnswer.getGrade());
    }
}
