package com.phd.RemoteEducationHost.facades;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskFacade {
    void createTask(MultipartFile file, TaskDTO taskDTO);

    void updateTask(MultipartFile file, TaskDTO taskDTO);

    void deleteTask(Integer id);

    TaskDTO getTaskById(Integer id);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTasksByClassId(Integer classId);

    List<TaskDTO> getTasksByGroupId(Integer groupId);
}
