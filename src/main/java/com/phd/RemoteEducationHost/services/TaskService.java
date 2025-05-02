package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;

import java.util.List;

public interface TaskService {
    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void deleteTask(Integer id);

    TaskDTO getTaskById(Integer id);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTasksByClassId(Integer classId);

    List<TaskDTO> getTasksByGroupId(Integer groupId);
}
