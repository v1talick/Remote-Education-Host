package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> getTaskById(int id);
    Optional<Task> getTaskWithDetailsById(int id);
    List<Task> getAllTasksByClassId(int classId);
    void saveTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
}
