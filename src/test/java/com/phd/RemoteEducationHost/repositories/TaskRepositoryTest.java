package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class TaskRepositoryTest {
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void getTaskByIdTest() {
        Task taskFromDB = taskRepository.getTaskById(1).get();
        assertEquals("Implement a sorting algorithm.", taskFromDB.getDescription());
    }
    @Test
    public void getTaskWithDetailsByIdTest() {
        Task taskFromDB = taskRepository.getTaskWithDetailsById(2).get();
        assertEquals("Analyze a given digital signal.", taskFromDB.getDescription());
        assertEquals("Dave", taskFromDB.getAClass().getTeacher().getFirstName());
        assertEquals("EE-101", taskFromDB.getAClass().getGroup().getName());
        assertEquals("Digital Signal Processing", taskFromDB.getAClass().getDiscipline().getName());
        assertNull(taskFromDB.getFilePath());
    }
    @Test
    public void getAllTasksByClassIdTest() {
        Task taskFromDB = taskRepository.getAllTasksByClassId(2).get(0);
        assertEquals(1, taskRepository.getAllTasksByClassId(2).size());
        assertEquals("Analyze a given digital signal.", taskFromDB.getDescription());
        assertEquals("Dave", taskFromDB.getAClass().getTeacher().getFirstName());
        assertEquals("EE-101", taskFromDB.getAClass().getGroup().getName());
        assertEquals("Digital Signal Processing", taskFromDB.getAClass().getDiscipline().getName());
    }
    @Test
    public void saveTaskTest() {
        Task task = new Task();
        task.setId(38);
        task.setDeadline(new Date());
        task.setDescription("test description");

//        task.setFilePath("/uploads/example_document.pdf"); //constraint violation
        Class aClass = new Class();
        aClass.setId(1);
        task.setAClass(aClass);

        taskRepository.saveTask(task);
        assertEquals(3, taskRepository.getAllTasks().size());
        assertNull(taskRepository.getTaskById(3).get().getFilePath());
    }
    @Test
    public void updateTaskTest() {
        Task updatedTask = taskRepository.getTaskById(3).get();
        updatedTask.setDescription("updated Description");
        taskRepository.updateTask(updatedTask);
        assertEquals(updatedTask, taskRepository.getTaskById(3).get());
    }
    @Test
    public void deleteTastByIdTest() {
        taskRepository.deleteTask(3);
        assertFalse(taskRepository.getTaskById(3).isPresent());
        assertEquals(2, taskRepository.getAllTasks().size());
    }
}
