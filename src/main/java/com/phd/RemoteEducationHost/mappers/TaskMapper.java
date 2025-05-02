package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.enteties.Task;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        if (task == null) {
            return new TaskDTO();
        }
        return new TaskDTO(task.getId(), ClassMapper.mapToDTO(task.getAClass()), task.getDescription(),
                task.getFilePath(), task.getDeadline());
    }

    public static Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return new Task();
        }
        return new Task(taskDTO.getId(), ClassMapper.mapToEntity(taskDTO.getAClass()), taskDTO.getDescription(),
                taskDTO.getFilePath(), taskDTO.getDeadline());
    }
}
