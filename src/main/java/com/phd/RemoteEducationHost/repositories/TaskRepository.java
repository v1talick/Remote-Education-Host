package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task getTaskById(Integer id);
    Task getTaskWithDetailsById(Integer id);
    List<Task> getAllTasksByClassId(Integer classId);
    List<Task> getAllTasks();
    void saveTask(Task task);
    void updateTask(Task task);
    void deleteTask(Integer taskId);
}
